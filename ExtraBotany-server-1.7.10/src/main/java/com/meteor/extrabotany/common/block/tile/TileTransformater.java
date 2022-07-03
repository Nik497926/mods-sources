/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block.tile;

import com.meteor.extrabotany.common.block.tile.TileBlockPoolEfir;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.fx.PacketFXEssentiaSource;
import vazkii.botania.api.internal.VanillaPacketDispatcher;
import vazkii.botania.api.mana.spark.ISparkAttachable;
import vazkii.botania.api.mana.spark.ISparkEntity;
import vazkii.botania.client.core.handler.HUDHandler;
import vazkii.botania.client.core.helper.RenderHelper;
import vazkii.botania.common.Botania;
import vazkii.botania.common.core.helper.Vector3;

public class TileTransformater
extends TileEntity
implements ISidedInventory,
IInventory,
ISparkAttachable {
    private int stageCenter = 0;
    private int tick = 0;
    private NBTTagCompound nbtall = new NBTTagCompound();
    private ItemStack[] chestContents = new ItemStack[72];
    private int playersUsing;
    private int manaRequired = 0;
    private int mana = 0;
    private int efir = 0;
    private int serverTick = 0;
    private int[] withConnect = new int[0];

    public int getEfir() {
        return this.efir;
    }

    public void linkPool(TileEntity tile) {
        this.withConnect = new int[]{tile.xCoord, tile.yCoord, tile.zCoord};
        VanillaPacketDispatcher.dispatchTEToNearbyPlayers((TileEntity)this);
    }

    public boolean hasLink() {
        return this.withConnect.length == 3;
    }

    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        if (this.chestContents[0] != null) {
            NBTTagCompound _item = new NBTTagCompound();
            this.chestContents[0].writeToNBT(_item);
            nbt.setTag("Item", (NBTBase)_item);
        }
        nbt.setInteger("mana", this.mana);
        nbt.setInteger("needMana", this.manaRequired);
        nbt.setInteger("efir", this.efir);
        if (this.withConnect.length == 3) {
            NBTTagCompound _n = new NBTTagCompound();
            _n.setInteger("x", this.withConnect[0]);
            _n.setInteger("y", this.withConnect[1]);
            _n.setInteger("z", this.withConnect[2]);
            nbt.setTag("link", (NBTBase)_n);
        }
    }

    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.chestContents = new ItemStack[this.getSizeInventory()];
        if (nbt.hasKey("Item")) {
            this.chestContents[0] = ItemStack.loadItemStackFromNBT((NBTTagCompound)nbt.getCompoundTag("Item"));
        }
        if (nbt.hasKey("mana")) {
            this.mana = nbt.getInteger("mana");
        }
        if (nbt.hasKey("needMana")) {
            this.manaRequired = nbt.getInteger("needMana");
        }
        if (nbt.hasKey("efir")) {
            this.efir = nbt.getInteger("efir");
        }
        if (nbt.hasKey("link")) {
            NBTTagCompound _n = nbt.getCompoundTag("link");
            int xc = _n.getInteger("x");
            int yc = _n.getInteger("y");
            int zc = _n.getInteger("z");
            this.withConnect = new int[]{xc, yc, zc};
        }
    }

    private int nextState() {
        return this.stageCenter == 7 ? 0 : this.stageCenter + 1;
    }

    @SideOnly(value=Side.CLIENT)
    public int getStageCenter() {
        return this.stageCenter;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void updateEntity() {
        TileEntity te;
        super.updateEntity();
        if (this.worldObj.isRemote) {
            TileEntity te2;
            ++this.tick;
            if (this.tick == 2) {
                this.stageCenter = this.nextState();
                this.tick = 0;
            }
            if (this.getStackInSlot(0) != null) {
                Vector3 vec = Vector3.fromTileEntityCenter((TileEntity)this);
                Vector3 endVec = null;
                if (this.stageCenter == 0) {
                    endVec = Math.random() >= 0.5 ? vec.copy().add(0.5, 0.0, 0.0) : vec.copy().add(-0.5, 0.0, -0.5);
                } else if (this.stageCenter == 4) {
                    endVec = Math.random() >= 0.5 ? vec.copy().add(0.0, 0.0, 0.5) : vec.copy().add(-0.5, 0.0, 0.0);
                }
                if (this.stageCenter == 0 || this.stageCenter == 4) {
                    Botania.proxy.lightningFX(this.worldObj, vec, endVec, 2.0f, 38027, 58583);
                }
            }
            if (this.withConnect.length == 3 && (te2 = this.worldObj.getTileEntity(this.withConnect[0], this.withConnect[1], this.withConnect[2])) != null && te2 instanceof TileBlockPoolEfir && this.efir >= 5 && !((TileBlockPoolEfir)te2).canAdd(5)) return;
        }
        if (this.withConnect.length == 3 && !((te = this.worldObj.getTileEntity(this.withConnect[0], this.withConnect[1], this.withConnect[2])) instanceof TileBlockPoolEfir)) {
            this.withConnect = new int[0];
            VanillaPacketDispatcher.dispatchTEToNearbyPlayers((TileEntity)this);
        }
        if (this.getStackInSlot(0) != null) {
            if (Math.floor(this.mana / 10000) > 0.0) {
                int n_efir = (int)Math.floor(this.mana / 10000);
                this.mana -= n_efir * 10000;
                this.manaRequired -= n_efir * 10000;
                this.efir += n_efir;
            }
            if (this.manaRequired < 10000) {
                this.setInventorySlotContents(0, null);
            }
        }
        if (this.withConnect.length == 3 && (te = this.worldObj.getTileEntity(this.withConnect[0], this.withConnect[1], this.withConnect[2])) != null && te instanceof TileBlockPoolEfir && this.efir >= 5 && ((TileBlockPoolEfir)te).canAdd(5)) {
            ((TileBlockPoolEfir)te).addEfir(5);
            this.efir -= 5;
            SimpleNetworkWrapper packhand = PacketHandler.INSTANCE;
            PacketFXEssentiaSource essentiaSource = new PacketFXEssentiaSource(te.xCoord, te.yCoord, te.zCoord, (byte)(te.xCoord - this.xCoord), (byte)(te.yCoord - this.yCoord), (byte)(te.zCoord - this.zCoord), Aspect.LIFE.getColor());
            packhand.sendToAllAround((IMessage)essentiaSource, new NetworkRegistry.TargetPoint(this.getWorldObj().provider.dimensionId, (double)te.xCoord, (double)te.yCoord + 1.0, (double)te.zCoord, 32.0));
        }
        VanillaPacketDispatcher.dispatchTEToNearbyPlayers((TileEntity)this);
    }

    public void onStart(ItemStack stack) {
        this.manaRequired = 1000000;
        this.setInventorySlotContents(0, stack);
    }

    public Packet getDescriptionPacket() {
        NBTTagCompound data = new NBTTagCompound();
        this.writeToNBT(data);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 2, data);
    }

    public void onDataPacket(NetworkManager netManager, S35PacketUpdateTileEntity packet) {
        this.readFromNBT(packet.func_148857_g());
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

    public int getSizeInventory() {
        return 1;
    }

    public ItemStack getStackInSlot(int i) {
        return this.chestContents[i];
    }

    public ItemStack decrStackSize(int i, int i1) {
        if (this.chestContents[i] != null) {
            if (this.chestContents[i].stackSize <= i1) {
                ItemStack itemstack = this.chestContents[i];
                this.chestContents[i] = null;
                this.markDirty();
                return itemstack;
            }
            ItemStack itemstack = this.chestContents[i].splitStack(i1);
            if (this.chestContents[i].stackSize == 0) {
                this.chestContents[i] = null;
            }
            this.markDirty();
            return itemstack;
        }
        return null;
    }

    public ItemStack getStackInSlotOnClosing(int index) {
        if (this.chestContents[index] != null) {
            ItemStack itemstack = this.chestContents[index];
            this.chestContents[index] = null;
            return itemstack;
        }
        return null;
    }

    public void setInventorySlotContents(int index, ItemStack item) {
        this.chestContents[index] = item;
        if (item != null && item.stackSize > this.getInventoryStackLimit()) {
            item.stackSize = this.getInventoryStackLimit();
        }
        this.markDirty();
        VanillaPacketDispatcher.dispatchTEToNearbyPlayers((TileEntity)this);
    }

    public String getInventoryName() {
        return null;
    }

    public boolean hasCustomInventoryName() {
        return false;
    }

    public int getInventoryStackLimit() {
        return 64;
    }

    public boolean isUseableByPlayer(EntityPlayer entityPlayer) {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : entityPlayer.getDistanceSq((double)this.xCoord + 0.5, (double)this.yCoord + 0.5, (double)this.zCoord + 0.5) <= 64.0;
    }

    public void openInventory() {
        this.triggerPlayerUsageChange(1);
    }

    public void closeInventory() {
        this.triggerPlayerUsageChange(-1);
    }

    private void triggerPlayerUsageChange(int change) {
        if (this.worldObj != null) {
            this.playersUsing += change;
            this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, super.getBlockType(), 1, this.playersUsing);
        }
    }

    public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
        return true;
    }

    public boolean canAttachSpark(ItemStack itemStack) {
        return true;
    }

    public void attachSpark(ISparkEntity iSparkEntity) {
    }

    public int getAvailableSpaceForMana() {
        return Math.max(0, this.manaRequired - this.getCurrentMana());
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
        return this.mana >= this.manaRequired;
    }

    public boolean isFull() {
        return this.mana >= this.manaRequired;
    }

    public void recieveMana(int i) {
        this.mana = Math.min(this.manaRequired, this.mana + i);
    }

    public boolean canRecieveManaFromBursts() {
        return this.mana < this.manaRequired;
    }

    public int getCurrentMana() {
        return this.mana;
    }

    public ChunkCoordinates getLinkCoord() {
        if (this.withConnect.length == 3) {
            return new ChunkCoordinates(this.withConnect[0], this.withConnect[1], this.withConnect[2]);
        }
        return new ChunkCoordinates(0, -1, 0);
    }

    public void renderHUD(Minecraft minecraft, ScaledResolution res, World world, int i, int i1, int i2) {
        String s0;
        int x = res.getScaledWidth() / 2;
        int y = res.getScaledHeight() / 2 - 8;
        net.minecraft.client.renderer.RenderHelper.disableStandardItemLighting();
        GL11.glDisable((int)2929);
        if (this.getStackInSlot(0) != null) {
            RenderHelper.renderProgressPie((int)(x - 8), (int)(y - 5), (float)((float)this.mana / 10000.0f), (ItemStack)this.getStackInSlot(0));
            s0 = Integer.toString(this.mana);
            minecraft.fontRenderer.drawString(s0 + "\u00a7r", x - 27 - s0.length() * 5, y, new Color(52735).getRGB());
            minecraft.fontRenderer.drawString(Integer.toString(this.manaRequired) + "\u00a7r", x + 27, y, new Color(52735).getRGB());
        }
        HUDHandler.renderManaBar((int)(x - 50), (int)(y + 15), (int)0xFF3333, (float)0.75f, (int)this.efir, (int)1000000);
        s0 = Integer.toString(this.efir);
        minecraft.fontRenderer.drawString(s0 + "\u00a7r", x - 27 - s0.length() * 5, y + 27, new Color(16747677).getRGB());
        minecraft.fontRenderer.drawString("1000000\u00a7r", x + 27, y + 27, new Color(16747677).getRGB());
        minecraft.fontRenderer.drawString("\u042d\u0444\u0438\u0440\u00a7r", x - "\u042d\u0444\u0438\u0440".length() * 5 / 2, y + 27, new Color(16747677).getRGB());
        GL11.glEnable((int)2929);
    }
}

