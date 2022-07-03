/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block.tile;

import java.awt.Color;
import java.util.*;

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
import net.minecraftforge.oredict.OreDictionary;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.internal.VanillaPacketDispatcher;
import vazkii.botania.api.item.IDyablePool;
import vazkii.botania.api.mana.IManaPool;
import vazkii.botania.api.mana.IThrottledPacket;
import vazkii.botania.api.mana.spark.ISparkAttachable;
import vazkii.botania.api.mana.spark.ISparkEntity;
import vazkii.botania.api.recipe.RecipePureDaisy;

public class TileAutoDaisy
extends TileEntityContainer
implements IManaPool,
IDyablePool,
ISparkAttachable,
IThrottledPacket,
ISidedInventory {
    private static final Map<String, List<Object>> oreMap = new HashMap<String, List<Object>>();
    private RecipePureDaisy cachedRecipe = null;
    public int mana = 0;
    public static final int MAX_MANA = 100000;
    public int color = new Color(50943).getRGB();
    private int cd = 0;

    public TileAutoDaisy() {
        super(2);
    }

    @Override
    public String getInventoryName() {
        return "";
    }

    private RecipePureDaisy getRecipe() {
        int meta;
        Block b;
        if (this.getStackInSlot(0) == null) {
            return null;
        }
        if (!(this.getStackInSlot(0).getItem() instanceof ItemBlock)) {
            return null;
        }
        if (this.cachedRecipe != null && this.matches(this.cachedRecipe, b = Block.getBlockFromItem((Item)this.getStackInSlot(0).getItem()), meta = this.getStackInSlot(0).getItemDamage())) {
            return this.cachedRecipe;
        }
        this.cachedRecipe = this.getCachedRecipe();
        return this.cachedRecipe;
    }

    private RecipePureDaisy getCachedRecipe() {
        if (this.getStackInSlot(0) == null) {
            return null;
        }
        if (!(this.getStackInSlot(0).getItem() instanceof ItemBlock)) {
            return null;
        }
        Iterator iterator = BotaniaAPI.pureDaisyRecipes.iterator();
        Block b = Block.getBlockFromItem((Item)this.getStackInSlot(0).getItem());
        int meta = this.getStackInSlot(0).getItemDamage();
        while (iterator.hasNext()) {
            RecipePureDaisy recipe = (RecipePureDaisy)iterator.next();
            if (!this.matches(recipe, b, meta)) continue;
            return recipe;
        }
        return null;
    }

    @Override
    public void updateEntityServer() {
        if (this.cd > 0) {
            --this.cd;
            return;
        }
        if (this.getStackInSlot(0) == null) {
            return;
        }
        if (!(this.getStackInSlot(0).getItem() instanceof ItemBlock)) {
            return;
        }
        if (this.mana <= 200) {
            return;
        }
        RecipePureDaisy recipe = this.getRecipe();
        if (recipe == null) {
            return;
        }
        ItemStack out = this.getStackInSlot(1);
        ItemStack recipeOut = new ItemStack(recipe.getOutput(), 1, recipe.getOutputMeta());
        if (out == null || this.isItemStackEquals(out, recipeOut)) {
            this.cd = 5;
            if (this.getStackInSlot(1) != null) {
                recipeOut.stackSize += this.getStackInSlot((int)1).stackSize;
            }
            this.recieveMana(-200);
            this.setInventorySlotContents(1, recipeOut);
            ItemStack in = this.getStackInSlot(0).copy();
            --in.stackSize;
            if (in.stackSize <= 0) {
                in = null;
            }
            this.setInventorySlotContents(0, in);
        }
    }

    @Override
    public void updateEntityClient() {
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

    public int[] getAccessibleSlotsFromSide(int p_94128_1_) {
        return new int[]{0, 1};
    }

    public boolean canInsertItem(int p_102007_1_, ItemStack p_102007_2_, int p_102007_3_) {
        return p_102007_1_ == 0;
    }

    public boolean canExtractItem(int p_102008_1_, ItemStack p_102008_2_, int p_102008_3_) {
        return p_102008_1_ == 1;
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
        return 100000 - this.getCurrentMana();
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
        return this.mana >= 100000;
    }

    public boolean isFull() {
        return this.mana >= 100000;
    }

    public void recieveMana(int i) {
        this.mana = Math.max(0, Math.min(this.getCurrentMana() + i, 100000));
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

    public boolean matches(RecipePureDaisy recipe, Block block, int meta) {
        if (recipe.getInput() instanceof Block) {
            return block == recipe.getInput();
        }
        ItemStack stack = new ItemStack(block, 1, meta);
        String oredict = (String)recipe.getInput();
        return this.isOreDict(stack, oredict);
    }

    public boolean isOreDict(ItemStack stack, String entry) {
        if (stack != null && stack.getItem() != null) {
            ItemStack cstack;
            List ores;
            if (oreMap.containsKey(entry)) {
                ores = oreMap.get(entry);
            } else {
                ores = OreDictionary.getOres(entry);
                oreMap.put(entry, ores);
            }
            Iterator var4 = ((List)ores).iterator();
            do {
                if (!var4.hasNext()) {
                    return false;
                }
                ItemStack ostack = (ItemStack)var4.next();
                cstack = ostack.copy();
                if (cstack.getItemDamage() != Short.MAX_VALUE) continue;
                cstack.setItemDamage(stack.getItemDamage());
            } while (!stack.isItemEqual(cstack));
            return true;
        }
        return false;
    }
}

