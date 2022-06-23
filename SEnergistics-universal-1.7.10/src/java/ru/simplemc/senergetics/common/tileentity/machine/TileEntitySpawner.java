/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.tileentity.machine;

import com.mojang.authlib.GameProfile;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import crazypants.enderio.EnderIO;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import ru.simplemc.senergetics.SEnergetics;
import ru.simplemc.senergetics.common.inventory.InventorySpawner;
import ru.simplemc.senergetics.common.item.tool.spawner.SpawnerUpgradeType;
import ru.simplemc.senergetics.common.tileentity.component.TileEnergySinkComponent;
import ru.simplemc.senergetics.common.tileentity.component.TileRedStoneComponent;
import ru.simplemc.senergetics.common.tileentity.component.spawner.SpawnerDataManager;
import ru.simplemc.senergetics.handler.ServerEventHandler;
import ru.simplemc.senergetics.util.AttackUtils;
import ru.simplemc.senergetics.util.InventoryUtils;

public class TileEntitySpawner
extends TileEntity
implements IFluidHandler,
ISidedInventory {
    public final InventorySpawner inventory = new InventorySpawner(46, this);
    public final SpawnerDataManager dataManager = new SpawnerDataManager();
    private final TileEnergySinkComponent energySink = new TileEnergySinkComponent(this, 40000000, 0);
    private final FluidTank experienceTank = new FluidTank(new FluidStack(EnderIO.fluidXpJuice, 0), 100000);
    private final TileRedStoneComponent poweredExtension = new TileRedStoneComponent(this);
    private String entityClassName;
    private Class<?> entityClass;
    private Constructor<?> entityClassConstructor;
    private FakePlayer fakePlayer;
    private EntityLivingBase createdEntity;
    private boolean messagesIsSent = false;
    private int tickCounter = 0;
    private int workCounter = 0;
    private int workCounterMax = 600;
    private boolean spawnerForceSync = false;

    public void updateEntity() {
        ++this.tickCounter;
        if (this.worldObj.isRemote) {
            this.dataManager.updateClientEntity(this);
            return;
        }
        this.poweredExtension.updateEntity();
        this.energySink.updateEntity();
        this.dataManager.updateServerEntity(this, this.workCounter, this.workCounterMax, this.energySink, this.experienceTank, this.entityClassName);
        if (this.inventory.isAllItemsReadyToWork() && this.isPowered()) {
            this.createFakePlayer();
            this.initEntityClass();
            this.spawnEntity();
            this.calculateWorkingTicks();
            if (this.workCounter >= this.workCounterMax) {
                this.fakePlayer.setCurrentItemOrArmor(0, this.inventory.getAttackItem());
                AttackUtils.attackTargetEntityWithCurrentItem(this.fakePlayer, this.createdEntity);
                this.createdEntity.onUpdate();
                this.createdEntity.onDeath(DamageSource.causePlayerDamage((EntityPlayer)this.fakePlayer));
                this.inventory.setAttackItemStack(this.fakePlayer.getCurrentEquippedItem());
                this.collectExperience();
                this.addDropsFromMob(this.createdEntity.capturedDrops);
                this.workEnd();
                this.markDirty();
            }
        } else {
            this.entityClassName = null;
            this.workEnd();
        }
    }

    private void collectExperience() {
        int experiencePointsByDefault = AttackUtils.getExperiencePoints(this.createdEntity, (EntityPlayer)this.fakePlayer) * 20;
        int experiencePointsMultiplier = this.inventory.getSpawnerUpgradesAmount(SpawnerUpgradeType.EXPERIENCE) + 1;
        this.experienceTank.fill(new FluidStack(EnderIO.fluidXpJuice, experiencePointsByDefault * experiencePointsMultiplier), true);
    }

    public void syncTileWithClient() {
        if (this.spawnerForceSync || this.tickCounter % 10 == 0) {
            this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
            this.spawnerForceSync = false;
        }
    }

    private boolean isPowered() {
        return !this.poweredExtension.isPowered() && this.energySink.useEnergy(this.getEnergyUsePerTick());
    }

    private void createFakePlayer() {
        if (this.fakePlayer == null) {
            GameProfile gameProfile = new GameProfile(UUID.randomUUID(), "[SilentSpawner]");
            this.fakePlayer = FakePlayerFactory.get((WorldServer)MinecraftServer.getServer().worldServerForDimension(this.worldObj.provider.dimensionId), (GameProfile)gameProfile);
            this.fakePlayer.setPosition((double)this.xCoord, (double)this.yCoord, (double)this.zCoord);
        }
    }

    private void initEntityClass() {
        this.entityClassName = this.inventory.getMobScanner().getTagCompound().getString("MobClassName");
        if (this.entityClass == null || !this.entityClass.getName().equals(this.entityClassName)) {
            try {
                this.entityClass = Class.forName(this.entityClassName);
                this.entityClassConstructor = this.entityClass.getConstructor(World.class);
            }
            catch (ClassNotFoundException | NoSuchMethodException ignored) {
                this.dropScannerItemStack();
            }
        }
    }

    private void spawnEntity() {
        if (this.createdEntity == null) {
            try {
                this.createdEntity = (EntityLivingBase)this.entityClassConstructor.newInstance(this.worldObj);
            }
            catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
                this.dropScannerItemStack();
                return;
            }
            this.createdEntity.setPosition((double)this.xCoord, (double)this.yCoord, (double)this.zCoord);
            this.createdEntity.getEntityData().setBoolean(ServerEventHandler.SPAWNED_MOB_TAG, true);
        }
    }

    private void dropScannerItemStack() {
        EntityItem entityItem = new EntityItem(this.worldObj);
        entityItem.setPosition((double)this.xCoord + 0.5, (double)(this.yCoord + 1), (double)this.zCoord + 0.5);
        entityItem.setEntityItemStack(this.inventory.getMobScanner());
        this.inventory.setMobScannerItemStack(null);
        this.worldObj.spawnEntityInWorld((Entity)entityItem);
        this.workEnd();
        this.markDirty();
    }

    public void calculateWorkingTicks() {
        ++this.workCounter;
        if (this.createdEntity instanceof IBossDisplayData) {
            this.workCounterMax = this.dataManager.spawnerLevel == 2 ? 4800 : 8000;
        } else {
            this.workCounterMax = SEnergetics.getConfig().getSpawnerLevels().get(this.dataManager.spawnerLevel).getProcessTicks();
            int speedUpgradesCount = this.inventory.getSpawnerUpgradesAmount(SpawnerUpgradeType.SPEED);
            if (speedUpgradesCount > 0) {
                this.workCounterMax /= speedUpgradesCount * 2;
            }
        }
    }

    private void addDropsFromMob(ArrayList<EntityItem> entityItems) {
        if (!entityItems.isEmpty()) {
            int lootingUpgradesCount = this.inventory.getSpawnerUpgradesAmount(SpawnerUpgradeType.LOOTING);
            if (lootingUpgradesCount > 2 && (double)this.worldObj.rand.nextFloat() > 0.3) {
                lootingUpgradesCount = 2;
            }
            for (EntityItem entityItem : entityItems) {
                int multiplyStackSize;
                entityItem.setPosition((double)this.xCoord + 0.5, (double)(this.yCoord + 1), (double)this.zCoord + 0.5);
                ItemStack stackToAdd = entityItem.getEntityItem();
                if (lootingUpgradesCount > 0 && (multiplyStackSize = stackToAdd.stackSize * 2 * lootingUpgradesCount) <= stackToAdd.getMaxStackSize()) {
                    stackToAdd.stackSize = multiplyStackSize;
                }
                if (InventoryUtils.putInInventory((IInventory)this, 0, stackToAdd, false) < 1) {
                    this.sendWarningMessages();
                    continue;
                }
                this.messagesIsSent = false;
            }
        }
    }

    private void sendWarningMessages() {
        List<EntityPlayer> nearestPlayers;
        if (!(this.messagesIsSent && this.tickCounter % 1200 != 0 || (nearestPlayers = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox((double)this.xCoord, (double)this.yCoord, (double)this.zCoord, (double)this.xCoord, (double)this.yCoord, (double)this.zCoord).expand(16.0, 16.0, 16.0))).isEmpty())) {
            for (EntityPlayer nearestPlayer : nearestPlayers) {
                nearestPlayer.addChatComponentMessage((IChatComponent)new ChatComponentTranslation("message.block_silent_spawner_inventory_full.text", new Object[]{"[" + this.xCoord + " " + this.yCoord + " " + this.zCoord + "]"}));
            }
            this.messagesIsSent = true;
        }
    }

    private void workEnd() {
        this.createdEntity = null;
        this.workCounter = 0;
    }

    private double getEnergyUsePerTick() {
        return SEnergetics.getConfig().getSpawnerLevels().get(this.dataManager.spawnerLevel).getEnergyUsage();
    }

    public void setStandalone(boolean isStandalone) {
        this.energySink.setEnergyStored(4.0E7);
        this.energySink.setStandalone(isStandalone);
    }

    public void onSpawnerLevelUp(int spawnerLevel) {
        this.worldObj.playSoundEffect((double)this.xCoord, (double)this.yCoord, (double)this.zCoord, "random.click", 0.3f, 0.6f);
        this.dataManager.spawnerLevel = spawnerLevel;
        this.spawnerForceSync = true;
    }

    public int getSpawnerLevel() {
        return this.dataManager.spawnerLevel;
    }

    @SideOnly(value=Side.CLIENT)
    public void onDataPacket(NetworkManager networkManager, S35PacketUpdateTileEntity packet) {
        this.dataManager.receiveServerDataPacket(this, packet);
    }

    public Packet getDescriptionPacket() {
        return this.dataManager.createServerDataPacket(this);
    }

    public void readFromNBT(NBTTagCompound tagCompound) {
        super.readFromNBT(tagCompound);
        this.energySink.readFromNBT(tagCompound);
        this.experienceTank.readFromNBT(tagCompound);
        this.workCounter = tagCompound.getInteger("WorkCounter");
        this.dataManager.spawnerLevel = tagCompound.getByte("SpawnerLevel");
        this.inventory.readFromNBT(tagCompound);
    }

    public void writeToNBT(NBTTagCompound tagCompound) {
        super.writeToNBT(tagCompound);
        this.energySink.writeToNBT(tagCompound);
        this.experienceTank.writeToNBT(tagCompound);
        this.inventory.writeToNBT(tagCompound);
        tagCompound.setInteger("SpawnerLevel", this.dataManager.spawnerLevel);
        tagCompound.setInteger("WorkCounter", this.workCounter);
    }

    public void invalidate() {
        this.energySink.invalidate();
        super.invalidate();
    }

    public void onChunkUnload() {
        this.energySink.onChunkUnload();
        super.onChunkUnload();
    }

    public void onPlayerDrawExperience(EntityPlayerMP player) {
        FluidStack fluidStack = this.experienceTank.drain(this.experienceTank.getFluidAmount(), true);
        if (fluidStack != null) {
            player.addExperience(fluidStack.amount / 20);
        }
    }

    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
        return 0;
    }

    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
        return resource != null && this.canDrain(from, resource.getFluid()) ? this.drain(from, resource.amount, doDrain) : null;
    }

    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
        return this.experienceTank.drain(maxDrain, doDrain);
    }

    public boolean canFill(ForgeDirection from, Fluid fluid) {
        return fluid != null && EnderIO.fluidXpJuice == fluid;
    }

    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        return fluid != null && EnderIO.fluidXpJuice == fluid;
    }

    public FluidTankInfo[] getTankInfo(ForgeDirection from) {
        return new FluidTankInfo[]{this.experienceTank.getInfo()};
    }

    public int[] getAccessibleSlotsFromSide(int side) {
        return this.inventory.getAccessibleSlotsFromSide(side);
    }

    public boolean canInsertItem(int slot, ItemStack itemStack, int side) {
        return this.inventory.canInsertItem(slot, itemStack, side);
    }

    public boolean canExtractItem(int slot, ItemStack itemStack, int side) {
        return this.inventory.canExtractItem(slot, itemStack, side);
    }

    public int getSizeInventory() {
        return this.inventory.getSizeInventory();
    }

    public ItemStack getStackInSlot(int slot) {
        return this.inventory.getStackInSlot(slot);
    }

    public ItemStack decrStackSize(int side, int amount) {
        return this.inventory.decrStackSize(side, amount);
    }

    public ItemStack getStackInSlotOnClosing(int side) {
        return this.inventory.getStackInSlotOnClosing(side);
    }

    public void setInventorySlotContents(int slot, ItemStack itemStack) {
        this.inventory.setInventorySlotContents(slot, itemStack);
    }

    public String getInventoryName() {
        return this.inventory.getInventoryName();
    }

    public boolean hasCustomInventoryName() {
        return this.inventory.hasCustomInventoryName();
    }

    public int getInventoryStackLimit() {
        return this.inventory.getInventoryStackLimit();
    }

    public boolean isUseableByPlayer(EntityPlayer player) {
        return this.inventory.isUseableByPlayer(player);
    }

    public void openInventory() {
        this.inventory.openInventory();
    }

    public void closeInventory() {
        this.inventory.closeInventory();
    }

    public boolean isItemValidForSlot(int slot, ItemStack itemStack) {
        return this.inventory.isItemValidForSlot(slot, itemStack);
    }
}

