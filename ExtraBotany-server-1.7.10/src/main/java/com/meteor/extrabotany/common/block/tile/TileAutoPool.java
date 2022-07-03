/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block.tile;

import com.meteor.extrabotany.common.block.tile.TileEntityContainer;
import com.meteor.extrabotany.common.item.system.ItemModulePool;
import java.awt.Color;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.internal.VanillaPacketDispatcher;
import vazkii.botania.api.item.IDyablePool;
import vazkii.botania.api.mana.IManaItem;
import vazkii.botania.api.mana.IManaPool;
import vazkii.botania.api.mana.IThrottledPacket;
import vazkii.botania.api.mana.spark.ISparkAttachable;
import vazkii.botania.api.mana.spark.ISparkEntity;
import vazkii.botania.api.recipe.RecipeManaInfusion;
import vazkii.botania.common.block.ModBlocks;

public class TileAutoPool
extends TileEntityContainer
implements IManaPool,
IDyablePool,
ISparkAttachable,
IThrottledPacket,
ISidedInventory {
    public int color = new Color(50943).getRGB();
    public static final int MAX_MANA = 10000000;
    public int mana = 0;
    private RecipeManaInfusion cacheRecipe = null;

    public TileAutoPool() {
        super(14);
    }

    @Override
    public String getInventoryName() {
        return "";
    }

    @Override
    public void updateEntityServer() {
        this.craft();
        this.chargeItems();
        this.dischargeItems();
    }

    @Override
    public void updateEntityClient() {
    }

    private int getCountTransfer() {
        return 1000 * Math.max(1, this.getBoostTransfer());
    }

    private int getBoostCapacity() {
        int ret = 0;
        for (int i = 10; i < 14; ++i) {
            ret += ItemModulePool.getBoostCapacityPool(this.getStackInSlot(i));
        }
        return ret;
    }

    private int getBoostTransfer() {
        int ret = 0;
        for (int i = 10; i < 14; ++i) {
            ret += ItemModulePool.getBoostTransferMana(this.getStackInSlot(i));
        }
        return ret;
    }

    private void chargeItems() {
        for (int i = 6; i < 10; ++i) {
            ItemStack a = this.getStackInSlot(i);
            if (a == null || !(a.getItem() instanceof IManaItem) || !((IManaItem)a.getItem()).canReceiveManaFromPool(a, (TileEntity)this) || ((IManaItem)a.getItem()).getMana(a) >= ((IManaItem)a.getItem()).getMaxMana(a) || this.mana <= 0) continue;
            int count = this.getCurrentMana();
            count = Math.min(this.getCountTransfer(), count);
            ((IManaItem)a.getItem()).addMana(a, count);
            this.recieveMana(-count);
            this.setInventorySlotContents(i, a);
        }
    }

    public int getMaxMana() {
        return 10000000 * Math.max(1, this.getBoostCapacity());
    }

    private void dischargeItems() {
        for (int i = 2; i < 6; ++i) {
            ItemStack a = this.getStackInSlot(i);
            if (a == null || !(a.getItem() instanceof IManaItem) || !((IManaItem)a.getItem()).canExportManaToPool(a, (TileEntity)this) || ((IManaItem)a.getItem()).getMana(a) <= 0 || this.mana >= this.getMaxMana()) continue;
            int count = ((IManaItem)a.getItem()).getMana(a);
            count = Math.min(this.getCountTransfer(), count);
            ((IManaItem)a.getItem()).addMana(a, -count);
            this.recieveMana(count);
            this.setInventorySlotContents(i, a);
        }
    }

    private RecipeManaInfusion getRecipe() {
        ItemStack a = this.getStackInSlot(0);
        if (a == null) {
            return null;
        }
        if (this.cacheRecipe != null && this.cacheRecipe.matches(a)) {
            boolean conjuration;
            boolean alchemy = this.worldObj.getBlock(this.xCoord, this.yCoord - 1, this.zCoord) == ModBlocks.alchemyCatalyst;
            boolean bl = conjuration = this.worldObj.getBlock(this.xCoord, this.yCoord - 1, this.zCoord) == ModBlocks.conjurationCatalyst;
            if (this.cacheRecipe.isAlchemy() && alchemy) {
                return this.cacheRecipe;
            }
            if (this.cacheRecipe.isConjuration() && conjuration) {
                return this.cacheRecipe;
            }
            if (!(this.cacheRecipe.isConjuration() || this.cacheRecipe.isAlchemy() || conjuration || alchemy)) {
                return this.cacheRecipe;
            }
        }
        this.cacheRecipe = this.findRecipe();
        return this.cacheRecipe;
    }

    private RecipeManaInfusion findRecipe() {
        ItemStack a = this.getStackInSlot(0);
        boolean alchemy = this.worldObj.getBlock(this.xCoord, this.yCoord - 1, this.zCoord) == ModBlocks.alchemyCatalyst;
        boolean conjuration = this.worldObj.getBlock(this.xCoord, this.yCoord - 1, this.zCoord) == ModBlocks.conjurationCatalyst;
        for (RecipeManaInfusion recipe : BotaniaAPI.manaInfusionRecipes) {
            if (!recipe.matches(a) || recipe.isAlchemy() && !alchemy || recipe.isConjuration() && !conjuration) continue;
            return recipe;
        }
        return null;
    }

    private void craft() {
        ItemStack stack = this.getStackInSlot(0);
        if (stack == null) {
            return;
        }
        RecipeManaInfusion recipe = this.getRecipe();
        if (recipe == null) {
            return;
        }
        if (this.getCurrentMana() < recipe.getManaToConsume()) {
            return;
        }
        int mana = recipe.getManaToConsume();
        if (!this.worldObj.isRemote) {
            ItemStack output = recipe.getOutput().copy();
            boolean doit = false;
            if (this.getStackInSlot(1) != null) {
                if (this.isItemStackEquals(this.getStackInSlot(1), output)) {
                    doit = true;
                    output.stackSize += this.getStackInSlot((int)1).stackSize;
                }
            } else {
                doit = true;
            }
            if (doit) {
                --stack.stackSize;
                if (stack.stackSize == 0) {
                    stack = null;
                }
                this.setInventorySlotContents(0, stack);
                this.setInventorySlotContents(1, output);
                this.recieveMana(-mana);
            }
        }
    }

    @Override
    protected boolean isItemStackEquals(ItemStack a, ItemStack b) {
        if (a == null && b != null) {
            return false;
        }
        if (a != null && b == null) {
            return false;
        }
        if (a == null && b == null) {
            return true;
        }
        return a.getItem() == b.getItem() && a.getItemDamage() == b.getItemDamage() && a.copy().stackSize + b.copy().stackSize <= a.getMaxStackSize() && ItemStack.areItemStackTagsEqual((ItemStack)a, (ItemStack)b);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.mana = nbt.getInteger("mana");
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setInteger("mana", this.mana);
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
    }

    public boolean canAttachSpark(ItemStack itemStack) {
        return true;
    }

    public void attachSpark(ISparkEntity iSparkEntity) {
    }

    public int getAvailableSpaceForMana() {
        return this.getMaxMana() - this.mana;
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
        return this.mana >= this.getMaxMana();
    }

    public boolean isFull() {
        return this.mana >= this.getMaxMana();
    }

    public void recieveMana(int i) {
        this.mana = Math.max(0, Math.min(this.getCurrentMana() + i, this.getMaxMana()));
        this.worldObj.func_147453_f(this.xCoord, this.yCoord, this.zCoord, this.worldObj.getBlock(this.xCoord, this.yCoord, this.zCoord));
        VanillaPacketDispatcher.dispatchTEToNearbyPlayers((TileEntity)this);
    }

    public boolean canRecieveManaFromBursts() {
        return this.mana <= this.getMaxMana();
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

    @Override
    public int getInventoryStackLimit() {
        return super.getInventoryStackLimit();
    }

    public int[] getAccessibleSlotsFromSide(int p_94128_1_) {
        return new int[]{0, 1};
    }

    public boolean canInsertItem(int slot, ItemStack p_102007_2_, int p_102007_3_) {
        return slot == 0;
    }

    public boolean canExtractItem(int slot, ItemStack p_102008_2_, int p_102008_3_) {
        return slot == 1;
    }
}

