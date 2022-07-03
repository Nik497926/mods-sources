/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block.tile;

import com.meteor.extrabotany.common.block.BlockGaiaChest;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import thaumcraft.common.config.ConfigItems;
import vazkii.botania.common.item.ModItems;

public class TileGaiaChest
extends TileEntity
implements IInventory {
    private ItemStack[] chestContents = new ItemStack[72];
    public float lidAngle;
    public float prevLidAngle;
    public int numPlayersUsing;
    private int ticksSinceSync;
    private int cachedChestType;
    private String whoPlaced;
    private String customName;
    private IInventory mainInv;
    private int playersUsing;
    private static boolean isRegen = false;

    public TileGaiaChest() {
        this.cachedChestType = -1;
    }

    public IInventory getMainInv() {
        return this.mainInv;
    }

    public void updateInvBlock() {
        BlockGaiaChest chest = (BlockGaiaChest)this.worldObj.getBlock(this.xCoord, this.yCoord, this.zCoord);
        this.mainInv = chest.getInv(this.worldObj, this.xCoord, this.yCoord, this.zCoord);
    }

    public void isGenerated() {
        isRegen = true;
    }

    public void generationLoot() {
        IInventory chest = (IInventory)this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord);
        if (Math.random() <= 0.3) {
            chest.setInventorySlotContents(0, new ItemStack(Items.diamond, 1 + this.worldObj.rand.nextInt(10)));
        }
        if (Math.random() <= 0.3) {
            chest.setInventorySlotContents(1, new ItemStack(Items.iron_ingot, 1 + this.worldObj.rand.nextInt(32)));
        }
        if (Math.random() <= 0.3) {
            chest.setInventorySlotContents(2, new ItemStack(Items.gold_ingot, 1 + this.worldObj.rand.nextInt(16)));
        }
        if (Math.random() <= 0.3) {
            chest.setInventorySlotContents(3, new ItemStack(Items.emerald, 1 + this.worldObj.rand.nextInt(8)));
        }
        if (Math.random() <= 0.3) {
            chest.setInventorySlotContents(4, new ItemStack(ModItems.manaResource, 1 + this.worldObj.rand.nextInt(16), 0));
        }
        if (Math.random() <= 0.3) {
            chest.setInventorySlotContents(5, new ItemStack(ModItems.manaResource, 1 + this.worldObj.rand.nextInt(4), 14));
        }
        if (Math.random() <= 0.3) {
            chest.setInventorySlotContents(6, new ItemStack(ModItems.manaResource, 1 + this.worldObj.rand.nextInt(12), 7));
        }
        if (Math.random() <= 0.3) {
            chest.setInventorySlotContents(7, new ItemStack(ModItems.manaResource, 1 + this.worldObj.rand.nextInt(4), 4));
        }
        if (Math.random() <= 0.3) {
            chest.setInventorySlotContents(8, new ItemStack(ModItems.rune, 1 + this.worldObj.rand.nextInt(4), this.worldObj.rand.nextInt(15)));
        }
        if (Math.random() <= 0.3) {
            chest.setInventorySlotContents(9, new ItemStack(ModItems.rune, 1 + this.worldObj.rand.nextInt(4), this.worldObj.rand.nextInt(15)));
        }
        if (Math.random() <= 0.3) {
            chest.setInventorySlotContents(10, new ItemStack(ModItems.rune, 1 + this.worldObj.rand.nextInt(4), this.worldObj.rand.nextInt(15)));
        }
        if (Math.random() <= 0.3) {
            chest.setInventorySlotContents(11, new ItemStack(ModItems.rune, 1 + this.worldObj.rand.nextInt(4), this.worldObj.rand.nextInt(15)));
        }
        if (Math.random() <= 0.3) {
            chest.setInventorySlotContents(12, new ItemStack(ModItems.rune, 1 + this.worldObj.rand.nextInt(4), this.worldObj.rand.nextInt(15)));
        }
        if (Math.random() <= 0.3) {
            chest.setInventorySlotContents(13, new ItemStack(com.meteor.extrabotany.common.item.ModItems.fragmentEfirium, 1 + this.worldObj.rand.nextInt(4)));
        }
        if (Math.random() <= 0.3) {
            chest.setInventorySlotContents(14, new ItemStack(com.meteor.extrabotany.common.item.ModItems.material, 1 + this.worldObj.rand.nextInt(4), 2));
        }
        if (Math.random() <= 0.3) {
            chest.setInventorySlotContents(15, new ItemStack(com.meteor.extrabotany.common.item.ModItems.material, 1 + this.worldObj.rand.nextInt(4), 0));
        }
        if (Math.random() <= 0.3) {
            chest.setInventorySlotContents(16, new ItemStack(com.meteor.extrabotany.common.item.ModItems.heliacalclaymore));
        }
        if (Math.random() <= 0.3) {
            chest.setInventorySlotContents(17, new ItemStack(com.meteor.extrabotany.common.item.ModItems.material, 1 + this.worldObj.rand.nextInt(4), 12));
        }
        if (Math.random() <= 0.3) {
            chest.setInventorySlotContents(18, new ItemStack(com.meteor.extrabotany.common.item.ModItems.material, 1 + this.worldObj.rand.nextInt(4), 9));
        }
        if (Math.random() <= 0.3) {
            chest.setInventorySlotContents(19, new ItemStack(com.meteor.extrabotany.common.item.ModItems.efirfragment));
        }
        if (Math.random() <= 0.3) {
            chest.setInventorySlotContents(20, new ItemStack(com.meteor.extrabotany.common.item.ModItems.key));
        }
        if (Math.random() <= 0.3) {
            chest.setInventorySlotContents(21, new ItemStack(ConfigItems.itemResource, 1 + this.worldObj.rand.nextInt(8), 9));
        }
        if (Math.random() <= 0.3) {
            chest.setInventorySlotContents(22, new ItemStack(ConfigItems.itemResource, 1 + this.worldObj.rand.nextInt(8), 14));
        }
        if (Math.random() <= 0.3) {
            chest.setInventorySlotContents(23, new ItemStack(ModItems.overgrowthSeed, 1 + this.worldObj.rand.nextInt(5)));
        }
        if (Math.random() <= 0.3) {
            chest.setInventorySlotContents(24, new ItemStack(ModItems.spark, 1 + this.worldObj.rand.nextInt(16)));
        }
        if (Math.random() <= 0.3) {
            chest.setInventorySlotContents(25, new ItemStack(ModItems.blackLotus, 1 + this.worldObj.rand.nextInt(2)));
        }
        if (Math.random() <= 0.3) {
            chest.setInventorySlotContents(26, new ItemStack(ModItems.blackLotus, 1 + this.worldObj.rand.nextInt(2), 1));
        }
        this.worldObj.setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, 3, 3);
    }

    @SideOnly(value=Side.CLIENT)
    public TileGaiaChest(int type) {
        this.cachedChestType = type;
    }

    public int getSizeInventory() {
        return 54;
    }

    public void activate(EntityPlayer player, ItemStack stack) {
    }

    public ItemStack getStackInSlot(int i) {
        return this.chestContents[i];
    }

    public ItemStack decrStackSize(int p_70298_1_, int p_70298_2_) {
        if (this.chestContents[p_70298_1_] != null) {
            if (this.chestContents[p_70298_1_].stackSize <= p_70298_2_) {
                ItemStack itemstack = this.chestContents[p_70298_1_];
                this.chestContents[p_70298_1_] = null;
                this.markDirty();
                return itemstack;
            }
            ItemStack itemstack = this.chestContents[p_70298_1_].splitStack(p_70298_2_);
            if (this.chestContents[p_70298_1_].stackSize == 0) {
                this.chestContents[p_70298_1_] = null;
            }
            this.markDirty();
            return itemstack;
        }
        return null;
    }

    public ItemStack getStackInSlotOnClosing(int p_70304_1_) {
        if (this.chestContents[p_70304_1_] != null) {
            ItemStack itemstack = this.chestContents[p_70304_1_];
            this.chestContents[p_70304_1_] = null;
            return itemstack;
        }
        return null;
    }

    public void setInventorySlotContents(int p_70299_1_, ItemStack p_70299_2_) {
        this.chestContents[p_70299_1_] = p_70299_2_;
        if (p_70299_2_ != null && p_70299_2_.stackSize > this.getInventoryStackLimit()) {
            p_70299_2_.stackSize = this.getInventoryStackLimit();
        }
        this.markDirty();
    }

    public String getInventoryName() {
        return this.hasCustomInventoryName() ? this.customName : "container.chest";
    }

    public boolean hasCustomInventoryName() {
        return this.customName != null && this.customName.length() > 0;
    }

    public void func_145976_a(String name) {
        this.customName = name;
    }

    public void setPlayer(String playerName) {
        this.whoPlaced = playerName;
    }

    public String getPlayer() {
        return this.whoPlaced;
    }

    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        NBTTagList nbttaglist = nbt.getTagList("Items", 10);
        if (nbt != null && nbt.hasKey("player")) {
            this.whoPlaced = nbt.getString("player");
        }
        isRegen = nbt.getBoolean("isRegen");
        this.chestContents = new ItemStack[this.getSizeInventory()];
        if (nbt.hasKey("CustomName", 8)) {
            this.customName = nbt.getString("CustomName");
        }
        this.whoPlaced = nbt.getString("player");
        for (int i = 0; i < nbttaglist.tagCount(); ++i) {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound1.getByte("Slot") & 0xFF;
            if (j < 0 || j >= this.chestContents.length) continue;
            this.chestContents[j] = ItemStack.loadItemStackFromNBT((NBTTagCompound)nbttagcompound1);
        }
    }

    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        NBTTagList nbttaglist = new NBTTagList();
        NBTTagCompound var1 = new NBTTagCompound();
        if (nbt != null) {
            var1 = new NBTTagCompound();
        }
        if (this.whoPlaced == null) {
            nbt.setString("player", "null");
        } else {
            nbt.setString("player", this.whoPlaced);
        }
        nbt.setBoolean("isRegen", isRegen);
        for (int i = 0; i < this.chestContents.length; ++i) {
            if (this.chestContents[i] == null) continue;
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();
            nbttagcompound1.setByte("Slot", (byte)i);
            this.chestContents[i].writeToNBT(nbttagcompound1);
            nbttaglist.appendTag((NBTBase)nbttagcompound1);
        }
        nbt.setTag("Items", (NBTBase)nbttaglist);
        if (this.hasCustomInventoryName()) {
            nbt.setString("CustomName", this.customName);
        }
    }

    public int getInventoryStackLimit() {
        return 64;
    }

    public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : p_70300_1_.getDistanceSq((double)this.xCoord + 0.5, (double)this.yCoord + 0.5, (double)this.zCoord + 0.5) <= 64.0;
    }

    public void updateContainingBlockInfo() {
        super.updateContainingBlockInfo();
    }

    private boolean func_145977_a(int p_145977_1_, int p_145977_2_, int p_145977_3_) {
        if (this.worldObj == null) {
            return false;
        }
        Block block = this.worldObj.getBlock(p_145977_1_, p_145977_2_, p_145977_3_);
        return block instanceof BlockChest && ((BlockChest)block).field_149956_a == this.func_145980_j();
    }

    public void updateEntity() {
        float f;
        super.updateEntity();
        ++this.ticksSinceSync;
        if (!this.worldObj.isRemote && this.numPlayersUsing != 0 && (this.ticksSinceSync + this.xCoord + this.yCoord + this.zCoord) % 200 == 0) {
            this.numPlayersUsing = 0;
            f = 5.0f;
            List<EntityPlayer> d2 = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox((double)((float)this.xCoord - f), (double)((float)this.yCoord - f), (double)((float)this.zCoord - f), (double)((float)(this.xCoord + 1) + f), (double)((float)(this.yCoord + 1) + f), (double)((float)(this.zCoord + 1) + f)));
            for (EntityPlayer f1 : d2) {
                IInventory f2;
                if (!(f1.openContainer instanceof ContainerChest) || (f2 = ((ContainerChest)f1.openContainer).getLowerChestInventory()) != this && (!(f2 instanceof InventoryLargeChest) || !((InventoryLargeChest)f2).isPartOfLargeChest((IInventory)this))) continue;
                ++this.numPlayersUsing;
            }
        }
        this.prevLidAngle = this.lidAngle;
        f = 0.1f;
        if (this.numPlayersUsing > 0 && this.lidAngle == 0.0f) {
            double var9 = (double)this.xCoord + 0.5;
            double var8 = (double)this.zCoord + 0.5;
            this.worldObj.playSoundEffect(var9, (double)this.yCoord + 0.5, var8, "random.chestopen", 0.5f, this.worldObj.rand.nextFloat() * 0.1f + 0.9f);
        }
        if (this.numPlayersUsing == 0 && this.lidAngle > 0.0f || this.numPlayersUsing > 0 && this.lidAngle < 1.0f) {
            float var11;
            float var10 = this.lidAngle;
            this.lidAngle = this.numPlayersUsing > 0 ? (this.lidAngle += f) : (this.lidAngle -= f);
            if (this.lidAngle > 1.0f) {
                this.lidAngle = 1.0f;
            }
            if (this.lidAngle < (var11 = 0.5f) && var10 >= var11) {
                double var8 = (double)this.xCoord + 0.5;
                double d = (double)this.zCoord + 0.5;
            }
            if (this.lidAngle < 0.0f) {
                this.lidAngle = 0.0f;
            }
        }
    }

    public boolean receiveClientEvent(int p_145842_1_, int p_145842_2_) {
        if (p_145842_1_ == 1) {
            this.numPlayersUsing = p_145842_2_;
            return true;
        }
        return super.receiveClientEvent(p_145842_1_, p_145842_2_);
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

    public void invalidate() {
        super.invalidate();
        this.updateContainingBlockInfo();
    }

    public int func_145980_j() {
        if (this.cachedChestType == -1) {
            if (this.worldObj == null || !(this.getBlockType() instanceof BlockChest)) {
                return 0;
            }
            this.cachedChestType = ((BlockChest)this.getBlockType()).field_149956_a;
        }
        return this.cachedChestType;
    }

    public void updateInv(IInventory inv) {
        this.mainInv = inv;
    }
}

