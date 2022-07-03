/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block.tile;

import com.meteor.extrabotany.common.block.tile.TileElfPool;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import vazkii.botania.api.internal.VanillaPacketDispatcher;
import vazkii.botania.api.mana.IManaItem;
import vazkii.botania.client.core.handler.LightningHandler;
import vazkii.botania.common.block.tile.mana.TileBellows;
import vazkii.botania.common.block.tile.mana.TilePool;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.core.helper.Vector3;
import vazkii.botania.common.item.ItemBlackHoleTalisman;
import vazkii.botania.common.lib.LibMisc;

public class TileBlockBoost
extends TileEntity {
    private ItemStack saveItem = null;

    public void writeToNBT(NBTTagCompound cmp) {
        super.writeToNBT(cmp);
        if (this.saveItem != null) {
            NBTTagCompound _n = new NBTTagCompound();
            this.saveItem.writeToNBT(_n);
            cmp.setTag("save", (NBTBase)_n);
        }
    }

    public void readFromNBT(NBTTagCompound cmp) {
        super.readFromNBT(cmp);
        if (cmp.hasKey("save")) {
            NBTTagCompound _n = cmp.getCompoundTag("save");
            this.saveItem = ItemStack.loadItemStackFromNBT((NBTTagCompound)_n);
        } else {
            this.saveItem = null;
        }
    }

    public ItemStack getSaveItem() {
        return this.saveItem;
    }

    public void updateEntity() {
        super.updateEntity();
        if (this.worldObj.isRemote) {
            return;
        }
        if (this.saveItem != null && this.saveItem.getItem() instanceof IManaItem) {
            TileEntity te = this.worldObj.getTileEntity(this.xCoord, this.yCoord - 1, this.zCoord);
            if (te != null && (te instanceof TilePool || te instanceof TileElfPool)) {
                if (this.isOutput(te)) {
                    int manaVal = this.getCountMana(te);
                    ((IManaItem)this.saveItem.getItem()).addMana(this.saveItem, manaVal);
                    this.receiveMana(-manaVal, te);
                    if (manaVal > 0 && this.worldObj.isRemote && ConfigHandler.chargingAnimationEnabled && this.worldObj.rand.nextInt(20) == 0) {
                        Vector3 itemVec = Vector3.fromTileEntity((TileEntity)te).add(0.5, 0.5 + Math.random() * 0.3, 0.5);
                        Vector3 tileVec = Vector3.fromTileEntity((TileEntity)te).add(0.2 + Math.random() * 0.6, 0.0, 0.2 + Math.random() * 0.6);
                        LightningHandler.spawnLightningBolt((World)this.worldObj, (Vector3)(this.isOutput(te) ? tileVec : itemVec), (Vector3)(this.isOutput(te) ? itemVec : tileVec), (float)80.0f, (long)this.worldObj.rand.nextLong(), (int)1140881820, (int)1140901631);
                    }
                    return;
                }
                int manaVal = this.getIncomMana(te);
                ((IManaItem)this.saveItem.getItem()).addMana(this.saveItem, -manaVal);
                this.receiveMana(manaVal, te);
                if (manaVal > 0 && this.worldObj.isRemote && ConfigHandler.chargingAnimationEnabled && this.worldObj.rand.nextInt(20) == 0) {
                    Vector3 itemVec = Vector3.fromTileEntity((TileEntity)te).add(0.5, 0.5 + Math.random() * 0.3, 0.5);
                    Vector3 tileVec = Vector3.fromTileEntity((TileEntity)te).add(0.2 + Math.random() * 0.6, 0.0, 0.2 + Math.random() * 0.6);
                    LightningHandler.spawnLightningBolt((World)this.worldObj, (Vector3)(this.isOutput(te) ? tileVec : itemVec), (Vector3)(this.isOutput(te) ? itemVec : tileVec), (float)80.0f, (long)this.worldObj.rand.nextLong(), (int)1140881820, (int)1140901631);
                }
                return;
            }
        } else if (this.saveItem != null && this.saveItem.getItem() instanceof ItemBlackHoleTalisman) {
            ItemStack copySaveItem = this.saveItem.copy();
            Block block = ItemBlackHoleTalisman.getBlock((ItemStack)copySaveItem);
            int count = ItemBlackHoleTalisman.getBlockCount((ItemStack)copySaveItem);
            if (this.worldObj.getTileEntity(this.xCoord, this.yCoord - 1, this.zCoord) instanceof IInventory && block != null && block != Blocks.air && count > 0) {
                int meta = ItemBlackHoleTalisman.getBlockMeta((ItemStack)copySaveItem);
                IInventory inv = (IInventory)this.worldObj.getTileEntity(this.xCoord, this.yCoord - 1, this.zCoord);
                for (int i = 0; i < inv.getSizeInventory(); ++i) {
                    ItemStack out = new ItemStack(block, 1, meta);
                    if (inv.getStackInSlot(i) != null || !inv.isItemValidForSlot(i, out)) continue;
                    if (count >= 64) {
                        out.stackSize = 64;
                        ItemBlackHoleTalisman.remove((ItemStack)copySaveItem, (int)64);
                        inv.setInventorySlotContents(i, out);
                        count -= 64;
                        continue;
                    }
                    out.stackSize = count;
                    ItemBlackHoleTalisman.remove((ItemStack)copySaveItem, (int)count);
                    inv.setInventorySlotContents(i, out);
                    count = 0;
                    break;
                }
                this.saveItem = copySaveItem;
                VanillaPacketDispatcher.dispatchTEToNearbyPlayers((TileEntity)this);
            }
        }
    }

    private int getIncomMana(TileEntity te) {
        if (te instanceof TilePool) {
            return this.getIncomMana((TilePool)te);
        }
        return this.getIncomMana((TileElfPool)te);
    }

    private int getIncomMana(TilePool te) {
        IManaItem it = (IManaItem)this.saveItem.getItem();
        if (!it.canExportManaToPool(this.saveItem, (TileEntity)te)) {
            return 0;
        }
        int minMana = Math.min(it.getMana(this.saveItem), 2000);
        if (te.getAvailableSpaceForMana() >= minMana) {
            return minMana;
        }
        return te.getAvailableSpaceForMana();
    }

    private int getIncomMana(TileElfPool te) {
        IManaItem it = (IManaItem)this.saveItem.getItem();
        if (!it.canExportManaToPool(this.saveItem, (TileEntity)te)) {
            return 0;
        }
        int minMana = Math.min(it.getMana(this.saveItem), 3000);
        if (te.getAvailableSpaceForMana() >= minMana) {
            return minMana;
        }
        return te.getAvailableSpaceForMana();
    }

    private void receiveMana(int mana, TileEntity te) {
        if (te instanceof TilePool) {
            this.receiveMana(mana, (TilePool)te);
        } else {
            this.receiveMana(mana, (TileElfPool)te);
        }
    }

    private void receiveMana(int mana, TilePool te) {
        te.recieveMana(mana);
    }

    private void receiveMana(int mana, TileElfPool te) {
        te.recieveMana(mana);
    }

    private boolean isOutput(TileEntity te) {
        if (te instanceof TilePool) {
            return ((TilePool)te).isOutputtingPower();
        }
        return ((TileElfPool)te).isOutputtingPower();
    }

    private int getCountMana(TileEntity te) {
        if (te instanceof TilePool) {
            return this.getCountMana((TilePool)te);
        }
        return this.getCountMana((TileElfPool)te);
    }

    private int getCountMana(TilePool te) {
        int bellowCount = 0;
        for (ForgeDirection dir : LibMisc.CARDINAL_DIRECTIONS) {
            TileEntity tile = this.worldObj.getTileEntity(this.xCoord + dir.offsetX, this.yCoord, this.zCoord + dir.offsetZ);
            if (tile == null || !(tile instanceof TileBellows) || ((TileBellows)tile).getLinkedTile() != this) continue;
            ++bellowCount;
        }
        int transfRate = 2000 * (bellowCount + 1);
        int manaVal = Math.min(transfRate, Math.min(te.getCurrentMana(), ((IManaItem)this.saveItem.getItem()).getMaxMana(this.saveItem) - ((IManaItem)this.saveItem.getItem()).getMana(this.saveItem)));
        return manaVal;
    }

    private int getCountMana(TileElfPool te) {
        int bellowCount = 0;
        for (ForgeDirection dir : LibMisc.CARDINAL_DIRECTIONS) {
            TileEntity tile = this.worldObj.getTileEntity(this.xCoord + dir.offsetX, this.yCoord, this.zCoord + dir.offsetZ);
            if (tile == null || !(tile instanceof TileBellows) || ((TileBellows)tile).getLinkedTile() != this) continue;
            ++bellowCount;
        }
        int transfRate = 3000 * (bellowCount + 1);
        int manaVal = Math.min(transfRate, Math.min(te.getCurrentMana(), ((IManaItem)this.saveItem.getItem()).getMaxMana(this.saveItem) - ((IManaItem)this.saveItem.getItem()).getMana(this.saveItem)));
        return manaVal;
    }

    public Packet getDescriptionPacket() {
        NBTTagCompound data = new NBTTagCompound();
        this.writeToNBT(data);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 2, data);
    }

    public void onDataPacket(NetworkManager netManager, S35PacketUpdateTileEntity packet) {
        this.readFromNBT(packet.func_148857_g());
    }
}

