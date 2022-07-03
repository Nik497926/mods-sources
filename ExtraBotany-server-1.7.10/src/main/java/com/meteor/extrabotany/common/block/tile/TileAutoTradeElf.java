/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block.tile;

import com.meteor.extrabotany.common.block.tile.TileEntityContainer;
import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
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
import vazkii.botania.api.mana.IManaPool;
import vazkii.botania.api.mana.IThrottledPacket;
import vazkii.botania.api.mana.spark.ISparkAttachable;
import vazkii.botania.api.mana.spark.ISparkEntity;
import vazkii.botania.api.recipe.RecipeElvenTrade;

public class TileAutoTradeElf
extends TileEntityContainer
implements IManaPool,
IDyablePool,
ISparkAttachable,
IThrottledPacket,
ISidedInventory {
    public int color = new Color(50943).getRGB();
    public static final int MAX_MANA = 1000000;
    public int mana = 0;
    private Map.Entry<RecipeElvenTrade, Byte> cacheRecipe = null;

    public TileAutoTradeElf() {
        super(3);
    }

    @Override
    public String getInventoryName() {
        return "";
    }

    private Map.Entry<RecipeElvenTrade, Byte> getRecipe() {
        if (this.cacheRecipe != null) {
            List<ItemStack> list = this.createListByPattern(this.cacheRecipe.getValue());
            if (list == null) {
                this.cacheRecipe = this.getCacheRecipe();
                return this.cacheRecipe;
            }
            if (!this.cacheRecipe.getKey().matches(list, false)) {
                this.cacheRecipe = this.getCacheRecipe();
                return this.cacheRecipe;
            }
            return this.cacheRecipe;
        }
        this.cacheRecipe = this.getCacheRecipe();
        return this.cacheRecipe;
    }

    private List<ItemStack> createListByPattern(byte type) {
        CopyOnWriteArrayList<ItemStack> list = new CopyOnWriteArrayList<ItemStack>();
        switch (type) {
            case 0: {
                if (this.getStackInSlot(0) == null) {
                    return null;
                }
                ItemStack a = this.getStackInSlot(0).copy();
                a.stackSize = 1;
                list.add(a);
                return list;
            }
            case 1: {
                if (this.getStackInSlot(1) == null) {
                    return null;
                }
                ItemStack a = this.getStackInSlot(1).copy();
                a.stackSize = 1;
                list.add(a);
                return list;
            }
            case 2: {
                if (this.getStackInSlot(0) == null || this.getStackInSlot(1) == null) {
                    return null;
                }
                ItemStack a = this.getStackInSlot(1).copy();
                a.stackSize = 1;
                ItemStack b = this.getStackInSlot(0).copy();
                b.stackSize = 1;
                list.add(a);
                list.add(b);
                return list;
            }
            case 3: {
                if (this.getStackInSlot(0) == null || this.getStackInSlot((int)0).stackSize < 2) {
                    return null;
                }
                ItemStack a = this.getStackInSlot(0).copy();
                a.stackSize = 1;
                list.add(a);
                list.add(a.copy());
                return list;
            }
            case 4: {
                if (this.getStackInSlot(1) == null || this.getStackInSlot((int)1).stackSize < 2) {
                    return null;
                }
                ItemStack a = this.getStackInSlot(1).copy();
                a.stackSize = 1;
                list.add(a);
                list.add(a.copy());
                return list;
            }
        }
        return null;
    }

    private Map.Entry<RecipeElvenTrade, Byte> getCacheRecipe() {
        HashMap<RecipeElvenTrade, Byte> map;
        RecipeElvenTrade recipe;
        ItemStack a;
        CopyOnWriteArrayList<ItemStack> list = new CopyOnWriteArrayList<ItemStack>();
        if (this.getStackInSlot(0) != null) {
            list = new CopyOnWriteArrayList();
            a = this.getStackInSlot(0).copy();
            a.stackSize = 1;
            list.add(a);
            recipe = this.findRecipe(list);
            if (recipe != null) {
                HashMap<RecipeElvenTrade, Byte> map2 = new HashMap<RecipeElvenTrade, Byte>();
                map2.put(recipe, (byte)0);
                this.cacheRecipe = map2.entrySet().iterator().next();
                return this.cacheRecipe;
            }
        }
        if (this.getStackInSlot(1) != null) {
            list = new CopyOnWriteArrayList();
            a = this.getStackInSlot(1).copy();
            a.stackSize = 1;
            list.add(a);
            recipe = this.findRecipe(list);
            if (recipe != null) {
                HashMap<RecipeElvenTrade, Byte> map3 = new HashMap<RecipeElvenTrade, Byte>();
                map3.put(recipe, (byte)1);
                this.cacheRecipe = map3.entrySet().iterator().next();
                return this.cacheRecipe;
            }
        }
        if (this.getStackInSlot(0) != null && this.getStackInSlot(1) != null) {
            list = new CopyOnWriteArrayList();
            a = this.getStackInSlot(1).copy();
            a.stackSize = 1;
            ItemStack b = this.getStackInSlot(0).copy();
            b.stackSize = 1;
            list.add(a);
            list.add(b);
            RecipeElvenTrade recipe2 = this.findRecipe(list);
            if (recipe2 != null) {
                HashMap<RecipeElvenTrade, Byte> map4 = new HashMap<RecipeElvenTrade, Byte>();
                map4.put(recipe2, (byte)2);
                this.cacheRecipe = map4.entrySet().iterator().next();
                return this.cacheRecipe;
            }
        }
        if (this.getStackInSlot(0) != null && this.getStackInSlot((int)0).stackSize > 1) {
            list = new CopyOnWriteArrayList();
            a = this.getStackInSlot(0).copy();
            a.stackSize = 1;
            list.add(a);
            list.add(a.copy());
            recipe = this.findRecipe(list);
            if (recipe != null) {
                map = new HashMap<RecipeElvenTrade, Byte>();
                map.put(recipe, (byte)3);
                this.cacheRecipe = map.entrySet().iterator().next();
                return this.cacheRecipe;
            }
        }
        if (this.getStackInSlot(1) != null && this.getStackInSlot((int)1).stackSize > 1) {
            list = new CopyOnWriteArrayList();
            a = this.getStackInSlot(1).copy();
            a.stackSize = 1;
            list.add(a);
            list.add(a.copy());
            recipe = this.findRecipe(list);
            if (recipe != null) {
                map = new HashMap();
                map.put(recipe, (byte)4);
                this.cacheRecipe = map.entrySet().iterator().next();
                return this.cacheRecipe;
            }
        }
        return null;
    }

    private RecipeElvenTrade findRecipe(List<ItemStack> list) {
        for (RecipeElvenTrade recipe : BotaniaAPI.elvenTradeRecipes) {
            if (!recipe.matches(list, false)) continue;
            return recipe;
        }
        return null;
    }

    @Override
    public void updateEntityServer() {
        this.execute();
    }

    @Override
    public void updateEntityClient() {
    }

    private void execute() {
        if (this.mana < 500) {
            return;
        }
        Map.Entry<RecipeElvenTrade, Byte> recipeKey = this.getRecipe();
        if (recipeKey == null) {
            return;
        }
        RecipeElvenTrade recipe = recipeKey.getKey();
        byte type = recipeKey.getValue();
        boolean doit = false;
        ItemStack out = recipe.getOutput().copy();
        if (this.getStackInSlot(2) != null) {
            if (this.isItemStackEquals(this.getStackInSlot(2), out)) {
                doit = true;
                out.stackSize += this.getStackInSlot((int)2).stackSize;
            }
        } else {
            doit = true;
        }
        if (doit) {
            this.setInventorySlotContents(2, out);
            this.recieveMana(-500);
            this.updateInventory(type);
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

    private void updateInventory(byte type) {
        switch (type) {
            case 0: {
                ItemStack in = this.getStackInSlot(0);
                --in.stackSize;
                if (in.stackSize <= 0) {
                    in = null;
                }
                this.setInventorySlotContents(0, in);
                break;
            }
            case 1: {
                ItemStack in = this.getStackInSlot(1);
                --in.stackSize;
                if (in.stackSize <= 0) {
                    in = null;
                }
                this.setInventorySlotContents(1, in);
                break;
            }
            case 2: {
                ItemStack in1 = this.getStackInSlot(0);
                --in1.stackSize;
                if (in1.stackSize <= 0) {
                    in1 = null;
                }
                this.setInventorySlotContents(0, in1);
                ItemStack in2 = this.getStackInSlot(1);
                --in2.stackSize;
                if (in2.stackSize <= 0) {
                    in2 = null;
                }
                this.setInventorySlotContents(1, in2);
                break;
            }
            case 3: {
                ItemStack in = this.getStackInSlot(0);
                in.stackSize -= 2;
                if (in.stackSize <= 0) {
                    in = null;
                }
                this.setInventorySlotContents(0, in);
                break;
            }
            case 4: {
                ItemStack in = this.getStackInSlot(1);
                in.stackSize -= 2;
                if (in.stackSize <= 0) {
                    in = null;
                }
                this.setInventorySlotContents(1, in);
                break;
            }
        }
    }

    public int getMaxMana() {
        return 1000000;
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

    public int[] getAccessibleSlotsFromSide(int p_94128_1_) {
        return new int[]{0, 1, 2};
    }

    public boolean canInsertItem(int slot, ItemStack stack, int side) {
        return slot < 2;
    }

    public boolean canExtractItem(int slot, ItemStack stack, int side) {
        return slot == 2;
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

