/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block.tile;

import com.meteor.extrabotany.common.block.BlockFlower;
import com.meteor.extrabotany.common.block.tile.TileEntityContainer;
import com.meteor.extrabotany.common.core.handler.ConfigHandler;
import com.meteor.extrabotany.common.core.handler.MathHandler;
import com.meteor.extrabotany.common.core.util.ItemNBTHelper;
import com.meteor.extrabotany.common.lib.LibData;
import java.awt.Color;
import java.util.Arrays;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import vazkii.botania.api.internal.VanillaPacketDispatcher;
import vazkii.botania.api.item.IDyablePool;
import vazkii.botania.api.mana.IManaPool;
import vazkii.botania.api.mana.IThrottledPacket;
import vazkii.botania.api.mana.spark.ISparkAttachable;
import vazkii.botania.api.mana.spark.ISparkEntity;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;

public class TileFlower
extends TileEntityContainer
implements ISidedInventory,
IManaPool,
IDyablePool,
ISparkAttachable,
IThrottledPacket {
    public boolean[] overgrowthSlots = new boolean[121];
    public static int MAX_MANA = 10000000;
    public int color = new Color(50943).getRGB();
    private boolean sendPacket = false;
    List<String> allowList = Arrays.asList("daybloom", "nightshade", "thermalily", "hydroangeas", "blueenchantress", "geminiorchid", "moonlightlily", "sunshinelily");
    public BlockFlower.BlockFlowerType type = BlockFlower.BlockFlowerType.TIER1;
    public int mana = 0;
    private int cd = 0;
    private int cd_dispatch = 0;

    public TileFlower() {
        super(121);
    }

    public void setType(BlockFlower.BlockFlowerType type) {
        this.type = type;
        VanillaPacketDispatcher.dispatchTEToNearbyPlayers((TileEntity)this);
    }

    @Override
    public void updateEntityServer() {
        if (this.cd > 0) {
            --this.cd;
            return;
        }
        boolean b = false;
        for (int i = 0; i < this.overgrowthSlots.length; ++i) {
            if (!this.canOvergrowthSlot(i)) continue;
            this.setOvergrowthSlot(i);
            b = true;
        }
        if (this.mana >= MAX_MANA) {
            if (b) {
                this.updateBlock();
            }
            return;
        }
        String currentType = this.getType();
        if (currentType == null) {
            if (b) {
                this.updateBlock();
            }
            return;
        }
        int count = 0;
        for (int i = 0; i < this.getSizeInventory(); ++i) {
            count += this.isCurrentType(i, currentType);
        }
        if (count == 0) {
            if (b) {
                this.updateBlock();
            }
            return;
        }
        int prev = this.mana;
        this.execute(currentType, count);
        this.calcOvergrowth(currentType);
        if (b || prev != this.mana) {
            this.updateBlock();
        }
    }

    private void calcOvergrowth(String type) {
        for (int i = 0; i < this.overgrowthSlots.length; ++i) {
            if (this.overgrowthSlots[i] || this.isCurrentType(i, type) == 0) continue;
            ItemStack s = this.getStackInSlot(i).copy();
            int ticksExisted = ItemNBTHelper.getInteger(s, "tickExisted", 0);
            ItemNBTHelper.setInteger(s, "tickExisted", ticksExisted += this.cd);
            if (ticksExisted >= 24000) {
                this.setInventorySlotContents(i, null);
                continue;
            }
            this.setInventorySlotContents(i, s);
        }
    }

    private void updateBlock() {
        if (this.cd_dispatch > 0) {
            --this.cd_dispatch;
        } else {
            this.cd_dispatch = 10;
            VanillaPacketDispatcher.dispatchTEToNearbyPlayers((TileEntity)this);
        }
    }

    private void execute(String type, int count) {
        switch (type) {
            case "daybloom": {
                boolean sky;
                boolean rain = this.getWorldObj().getWorldChunkManager().getBiomeGenAt(this.xCoord, this.zCoord).getIntRainfall() > 0 && (this.getWorldObj().isRaining() || this.getWorldObj().isThundering());
                boolean bl = sky = this.getWorldObj().isDaytime() && !rain && this.getWorldObj().canBlockSeeTheSky(this.xCoord, this.yCoord + 1, this.zCoord);
                if (sky) {
                    this.mana += count;
                    this.mana = Math.min(this.mana, MAX_MANA);
                }
                this.cd = 10;
                break;
            }
            case "nightshade": {
                boolean sky;
                boolean rain = this.getWorldObj().getWorldChunkManager().getBiomeGenAt(this.xCoord, this.zCoord).getIntRainfall() > 0 && (this.getWorldObj().isRaining() || this.getWorldObj().isThundering());
                boolean bl = sky = !this.getWorldObj().isDaytime() && !rain && this.getWorldObj().canBlockSeeTheSky(this.xCoord, this.yCoord + 1, this.zCoord);
                if (sky) {
                    this.mana += count;
                    this.mana = Math.min(this.mana, MAX_MANA);
                }
                this.cd = 10;
                break;
            }
            case "hydroangeas": {
                boolean can = false;
                for (int xc = -1; xc <= 1; ++xc) {
                    for (int zc = -1; zc <= 1; ++zc) {
                        Block bl = this.getWorldObj().getBlock(this.xCoord + xc, this.yCoord, this.zCoord + zc);
                        if (bl != Blocks.water) continue;
                        if (!this.infiniteIst(bl, this.getWorldObj(), this.xCoord + xc, this.yCoord, this.zCoord + zc)) {
                            this.getWorldObj().setBlockToAir(this.xCoord + xc, this.yCoord, this.zCoord + zc);
                        }
                        can = true;
                        break;
                    }
                    if (can) break;
                }
                if (!can) break;
                boolean rain = this.getWorldObj().getWorldChunkManager().getBiomeGenAt(this.xCoord, this.zCoord).getIntRainfall() > 0 && (this.getWorldObj().isRaining() || this.getWorldObj().isThundering());
                this.mana += 40 * count;
                this.mana = Math.min(MAX_MANA, this.mana);
                this.cd = rain ? 20 : 40;
                break;
            }
            case "blueenchantress": {
                boolean can = false;
                for (int xc = -1; xc <= 1; ++xc) {
                    for (int zc = -1; zc <= 1; ++zc) {
                        Block bl = this.getWorldObj().getBlock(this.xCoord + xc, this.yCoord, this.zCoord + zc);
                        if (bl != Blocks.water) continue;
                        if (!this.infiniteIst(bl, this.getWorldObj(), this.xCoord + xc, this.yCoord, this.zCoord + zc)) {
                            this.getWorldObj().setBlockToAir(this.xCoord + xc, this.yCoord, this.zCoord + zc);
                        }
                        can = true;
                        break;
                    }
                    if (can) break;
                }
                if (!can) break;
                boolean rain = this.getWorldObj().getWorldChunkManager().getBiomeGenAt(this.xCoord, this.zCoord).getIntRainfall() > 0 && (this.getWorldObj().isRaining() || this.getWorldObj().isThundering());
                this.mana += 80 * count;
                this.mana = Math.min(MAX_MANA, this.mana);
                this.cd = rain ? 20 : 40;
                break;
            }
            case "geminiorchid": {
                int bA = LibData.getBlockTemperture(this.getWorldObj().getBlock(this.xCoord + 1, this.yCoord, this.zCoord + 1));
                int bB = LibData.getBlockTemperture(this.getWorldObj().getBlock(this.xCoord + 1, this.yCoord, this.zCoord - 1));
                int bC = LibData.getBlockTemperture(this.getWorldObj().getBlock(this.xCoord - 1, this.yCoord, this.zCoord + 1));
                int bD = LibData.getBlockTemperture(this.getWorldObj().getBlock(this.xCoord - 1, this.yCoord, this.zCoord - 1));
                int temA = Math.min(MathHandler.min(bA, bB, bC), bD);
                int temB = Math.max(MathHandler.max(bA, bB, bC), bD);
                if (temB - temA > 500) {
                    this.mana += ((temB - temA) / 1000 * ConfigHandler.efficiencyGeminiorchid + this.getWorldObj().rand.nextInt(4)) * count;
                    this.mana = Math.min(MAX_MANA, this.mana);
                }
                this.cd = 100;
                break;
            }
            case "moonlightlily": {
                boolean sky;
                boolean rain = this.getWorldObj().getWorldChunkManager().getBiomeGenAt(this.xCoord, this.zCoord).getIntRainfall() > 0 && (this.getWorldObj().isRaining() || this.getWorldObj().isThundering());
                boolean bl = sky = !this.getWorldObj().isDaytime() && !rain && this.getWorldObj().canBlockSeeTheSky(this.xCoord, this.yCoord + 1, this.zCoord);
                if (sky) {
                    this.mana += count * 3;
                    this.mana = Math.min(this.mana, MAX_MANA);
                }
                this.cd = 10;
                break;
            }
            case "sunshinelily": {
                boolean sky;
                boolean rain = this.getWorldObj().getWorldChunkManager().getBiomeGenAt(this.xCoord, this.zCoord).getIntRainfall() > 0 && (this.getWorldObj().isRaining() || this.getWorldObj().isThundering());
                boolean bl = sky = this.getWorldObj().isDaytime() && !rain && this.getWorldObj().canBlockSeeTheSky(this.xCoord, this.yCoord + 1, this.zCoord);
                if (sky) {
                    this.mana += count * 3;
                    this.mana = Math.min(this.mana, MAX_MANA);
                }
                this.cd = 10;
                break;
            }
            case "thermalily": {
                boolean can = false;
                for (int xc = -1; xc <= 1; ++xc) {
                    for (int zc = -1; zc <= 1; ++zc) {
                        Block bl = this.getWorldObj().getBlock(this.xCoord + xc, this.yCoord, this.zCoord + zc);
                        if (bl != Blocks.lava) continue;
                        this.getWorldObj().setBlockToAir(this.xCoord + xc, this.yCoord, this.zCoord + zc);
                        can = true;
                        break;
                    }
                    if (can) break;
                }
                if (!can) break;
                this.mana += 500 * count;
                this.mana = Math.min(MAX_MANA, this.mana);
                this.cd = 6000;
                break;
            }
        }
    }

    private boolean infiniteIst(Block bl, World w, int x, int y, int z) {
        if (w.getBlock(x + 1, y, z) == Blocks.water && w.getBlock(x + 1, y, z - 1) == Blocks.water && w.getBlock(x, y, z - 1) == Blocks.water) {
            return true;
        }
        if (w.getBlock(x + 1, y, z) == Blocks.water && w.getBlock(x + 1, y, z + 1) == Blocks.water && w.getBlock(x, y, z + 1) == Blocks.water) {
            return true;
        }
        if (w.getBlock(x - 1, y, z) == Blocks.water && w.getBlock(x - 1, y, z - 1) == Blocks.water && w.getBlock(x, y, z - 1) == Blocks.water) {
            return true;
        }
        return w.getBlock(x - 1, y, z) == Blocks.water && w.getBlock(x - 1, y, z + 1) == Blocks.water && w.getBlock(x, y, z + 1) == Blocks.water;
    }

    private int isCurrentType(int i, String type) {
        ItemStack stack = this.getStackInSlot(i);
        if (stack == null) {
            return 0;
        }
        if (stack.getItem() instanceof ItemBlockSpecialFlower && ItemBlockSpecialFlower.getType((ItemStack)stack).equals(type)) {
            return stack.stackSize;
        }
        return 0;
    }

    private String getType() {
        for (int i = 0; i < this.getSizeInventory(); ++i) {
            String type;
            ItemStack stack = this.getStackInSlot(i);
            if (stack == null || !(stack.getItem() instanceof ItemBlockSpecialFlower) || !this.allowList.contains(type = ItemBlockSpecialFlower.getType((ItemStack)stack)) || type == null || type.isEmpty()) continue;
            return type;
        }
        return null;
    }

    private void setOvergrowthSlot(int i) {
        this.overgrowthSlots[i] = true;
        ItemStack newStack = this.getStackInSlot(i).copy();
        --newStack.stackSize;
        if (newStack.stackSize <= 0) {
            newStack = null;
        }
        this.setInventorySlotContents(i, newStack);
    }

    private boolean canOvergrowthSlot(int i) {
        if (this.overgrowthSlots[i]) {
            return false;
        }
        if (this.getStackInSlot(i) == null) {
            return false;
        }
        return this.getStackInSlot(i).getItem() == ModItems.overgrowthSeed;
    }

    @Override
    public int getInventoryStackLimit() {
        return this.type.maxStackSize;
    }

    @Override
    public void updateEntityClient() {
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.readFromCustomNBT(nbt);
    }

    public void readFromCustomNBT(NBTTagCompound nbt) {
        NBTTagCompound nbtOS = nbt.getCompoundTag("overgrowth");
        for (int i = 0; i < this.overgrowthSlots.length; ++i) {
            this.overgrowthSlots[i] = nbtOS.getBoolean("slot_" + i);
        }
        this.mana = nbt.getInteger("mana");
        this.type = BlockFlower.BlockFlowerType.values()[nbt.getInteger("type")];
    }

    public void writeCustomNBT(NBTTagCompound nbt) {
        NBTTagCompound nbtOS = new NBTTagCompound();
        for (int i = 0; i < this.overgrowthSlots.length; ++i) {
            nbtOS.setBoolean("slot_" + i, this.overgrowthSlots[i]);
        }
        nbt.setTag("overgrowth", (NBTBase)nbtOS);
        nbt.setInteger("mana", this.mana);
        nbt.setInteger("type", this.type.ordinal());
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        this.writeCustomNBT(nbt);
    }

    public int[] getAccessibleSlotsFromSide(int p_94128_1_) {
        return new int[0];
    }

    public boolean canInsertItem(int p_102007_1_, ItemStack p_102007_2_, int p_102007_3_) {
        return false;
    }

    public boolean canExtractItem(int p_102008_1_, ItemStack p_102008_2_, int p_102008_3_) {
        return false;
    }

    public int getColor() {
        return this.color;
    }

    public void setColor(int i) {
        this.color = i;
    }

    public boolean isOutputtingPower() {
        return true;
    }

    public void markDispatchable() {
        this.sendPacket = true;
    }

    public boolean canAttachSpark(ItemStack itemStack) {
        return true;
    }

    public void attachSpark(ISparkEntity iSparkEntity) {
    }

    public int getAvailableSpaceForMana() {
        return Math.max(0, MAX_MANA - this.getCurrentMana());
    }

    public ISparkEntity getAttachedSpark() {
        List sparks = this.worldObj.getEntitiesWithinAABB(ISparkEntity.class, AxisAlignedBB.getBoundingBox((double)this.xCoord, (double)(this.yCoord + 1), (double)this.zCoord, (double)(this.xCoord + 1), (double)(this.yCoord + 2), (double)(this.zCoord + 1)));
        if (sparks.size() == 1) {
            Entity e = (Entity)sparks.get(0);
            return (ISparkEntity)e;
        }
        return null;
    }

    public boolean areIncomingTranfersDone() {
        return this.mana >= MAX_MANA;
    }

    public boolean isFull() {
        return this.mana >= MAX_MANA;
    }

    public void recieveMana(int i) {
        this.mana = Math.max(0, Math.min(this.getCurrentMana() + this.mana, MAX_MANA));
        this.worldObj.func_147453_f(this.xCoord, this.yCoord, this.zCoord, this.worldObj.getBlock(this.xCoord, this.yCoord, this.zCoord));
        this.markDispatchable();
    }

    public boolean canRecieveManaFromBursts() {
        return this.mana <= MAX_MANA;
    }

    public int getCurrentMana() {
        return this.mana;
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound data = new NBTTagCompound();
        this.writeToNBT(data);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 2, data);
    }

    @Override
    public void onDataPacket(NetworkManager netManager, S35PacketUpdateTileEntity packet) {
        this.readFromNBT(packet.func_148857_g());
    }
}

