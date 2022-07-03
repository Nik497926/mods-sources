/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block.tile;

import com.meteor.extrabotany.common.block.BlockPearl;
import com.meteor.extrabotany.common.block.tile.TileEntityContainer;
import java.awt.Color;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import vazkii.botania.api.internal.VanillaPacketDispatcher;
import vazkii.botania.api.item.IDyablePool;
import vazkii.botania.api.mana.IManaPool;
import vazkii.botania.api.mana.IThrottledPacket;
import vazkii.botania.api.mana.spark.ISparkAttachable;
import vazkii.botania.api.mana.spark.ISparkEntity;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.item.ModItems;

public class TileAutoPlate
extends TileEntityContainer
implements IManaPool,
IDyablePool,
ISparkAttachable,
IThrottledPacket,
ISidedInventory {
    public int mana = 0;
    public static final int MAX_MANA = 10000000;
    public int color = new Color(50943).getRGB();
    private int cd = 0;
    private int progress = 0;
    private ItemStack stackTerra;
    private ItemStack blockTerra;

    public TileAutoPlate() {
        super(4);
    }

    @Override
    public String getInventoryName() {
        return "";
    }

    public int getMaxMana() {
        return 10000000;
    }

    public int getProgress() {
        return this.progress;
    }

    @Override
    public void updateEntityServer() {
        if (this.cd > 0) {
            --this.cd;
            return;
        }
        if (this.getStackInSlot(0) == null || this.getStackInSlot(1) == null || this.getStackInSlot(2) == null) {
            return;
        }
        int type = this.currentItems();
        if (this.mana <= (type == 2 ? 4500000 : 500000)) {
            this.progress = MathHelper.floor_double((double)((double)this.mana / (type == 2 ? 4500000.0 : 500000.0) * 100.0));
            this.cd = 2;
            VanillaPacketDispatcher.dispatchTEToNearbyPlayers((TileEntity)this);
            return;
        }
        if (type > 0) {
            for (int i = 0; i < 3; ++i) {
                ItemStack a = this.getStackInSlot(i);
                --a.stackSize;
                if (a.stackSize <= 0) {
                    a = null;
                }
                this.setInventorySlotContents(i, a);
            }
            ItemStack a = this.getStackInSlot(3);
            if (a == null) {
                a = this.getStackFromType(type);
            } else {
                ++a.stackSize;
            }
            this.setInventorySlotContents(3, a);
            this.cd = 2;
            this.progress = 0;
            this.recieveMana(-(type == 2 ? 4500000 : 500000));
        }
    }

    private ItemStack getStackFromType(int type) {
        if (type == 1) {
            if (this.stackTerra != null) {
                return this.stackTerra.copy();
            }
            this.stackTerra = new ItemStack(ModItems.manaResource, 1, 4);
            return this.stackTerra.copy();
        }
        if (type == 2) {
            if (this.blockTerra != null) {
                return this.blockTerra.copy();
            }
            this.blockTerra = new ItemStack(ModBlocks.storage, 1, 1);
            return this.blockTerra.copy();
        }
        return null;
    }

    @Override
    public void updateEntityClient() {
    }

    private int currentItems() {
        if (this.getStackInSlot(0) == null) {
            return 0;
        }
        boolean diamond = false;
        boolean pearl = false;
        boolean ingot = false;
        boolean blockDiamond = false;
        boolean blockPearl = false;
        boolean blockIngot = false;
        for (int i = 0; i < 3; ++i) {
            int meta;
            if (this.getStackInSlot(i) != null && this.getStackInSlot(i).getItem() == ModItems.manaResource) {
                meta = this.getStackInSlot(i).getItemDamage();
                if (meta == 0 && !ingot) {
                    ingot = true;
                }
                if (meta == 1 && !pearl) {
                    pearl = true;
                }
                if (meta != 2 || diamond) continue;
                diamond = true;
                continue;
            }
            if (this.getStackInSlot(i) != null && this.getStackInSlot(i).getItem() instanceof ItemBlock && Block.getBlockFromItem((Item)this.getStackInSlot(i).getItem()) == ModBlocks.storage) {
                meta = this.getStackInSlot(i).getItemDamage();
                if (meta == 0 && !blockIngot) {
                    blockIngot = true;
                }
                if (meta != 3 || blockDiamond) continue;
                blockDiamond = true;
                continue;
            }
            if (this.getStackInSlot(i) == null || !(this.getStackInSlot(i).getItem() instanceof ItemBlock) || !(Block.getBlockFromItem((Item)this.getStackInSlot(i).getItem()) instanceof BlockPearl) || blockPearl) continue;
            blockPearl = true;
        }
        if (this.canExecuteSlot(3, 1) && diamond && pearl && ingot) {
            if (this.getStackInSlot(3) != null && this.getStackInSlot(3).getItem() != ModItems.manaResource) {
                return 0;
            }
            return 1;
        }
        if (this.canExecuteSlot(3, 1) && blockDiamond && blockPearl && blockIngot) {
            if (this.getStackInSlot(3) != null) {
                if (!(this.getStackInSlot(3).getItem() instanceof ItemBlock)) {
                    return 0;
                }
                if (Block.getBlockFromItem((Item)this.getStackInSlot(3).getItem()) != ModBlocks.storage) {
                    return 0;
                }
            }
            return 2;
        }
        return 0;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.mana = nbt.getInteger("mana");
        this.progress = nbt.getInteger("progress");
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setInteger("mana", this.mana);
        nbt.setInteger("progress", this.progress);
    }

    public int[] getAccessibleSlotsFromSide(int p_94128_1_) {
        return new int[]{0, 1, 2, 3};
    }

    public boolean canInsertItem(int p_102007_1_, ItemStack p_102007_2_, int p_102007_3_) {
        return p_102007_1_ != 3;
    }

    public boolean canExtractItem(int p_102008_1_, ItemStack p_102008_2_, int p_102008_3_) {
        return p_102008_1_ == 3;
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
        VanillaPacketDispatcher.dispatchTEToNearbyPlayers((TileEntity)this);
    }

    public boolean canAttachSpark(ItemStack itemStack) {
        return true;
    }

    public void attachSpark(ISparkEntity iSparkEntity) {
    }

    public int getAvailableSpaceForMana() {
        return 10000000 - this.getCurrentMana();
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
        return this.mana >= 10000000;
    }

    public boolean isFull() {
        return this.mana >= 10000000;
    }

    public void recieveMana(int i) {
        this.mana = Math.max(0, Math.min(this.getCurrentMana() + i, 10000000));
        this.worldObj.func_147453_f(this.xCoord, this.yCoord, this.zCoord, this.worldObj.getBlock(this.xCoord, this.yCoord, this.zCoord));
        VanillaPacketDispatcher.dispatchTEToNearbyPlayers((TileEntity)this);
    }

    public boolean canRecieveManaFromBursts() {
        return true;
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

