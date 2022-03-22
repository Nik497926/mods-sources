/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block.tile;

import com.meteor.extrabotany.common.block.BlockElfPool;
import com.meteor.extrabotany.common.block.tile.TileElfPool;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.blocks.BlockCrystal;
import thaumcraft.common.blocks.ItemJarNode;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.items.ItemShard;
import thaumcraft.common.items.ItemWispEssence;
import thaumcraft.common.tiles.TilePedestal;
import vazkii.botania.api.internal.VanillaPacketDispatcher;
import vazkii.botania.client.core.helper.RenderHelper;
import vazkii.botania.common.Botania;
import vazkii.botania.common.block.mana.BlockPool;
import vazkii.botania.common.block.tile.mana.TilePool;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.core.helper.Vector3;
import vazkii.botania.common.item.ItemVineBall;
import vazkii.botania.common.item.ModItems;

public class TileAuraControler
extends TileEntity
implements IInventory,
ISidedInventory {
    private ItemStack[] chestContents = new ItemStack[72];
    public int numPlayersUsing;
    private int playersUsing;
    private String customName;
    private final int MAX_MANA = 10000;
    private int MANA = 0;
    private int proccess = 0;
    private boolean isStoped = false;
    private final int ticksSinceSync = -1;
    public boolean hasItem;
    private NBTTagCompound item = new NBTTagCompound();
    private final Item[][] boostItem = new Item[][]{{ConfigItems.itemShard, ModItems.vineBall}, {Item.getItemFromBlock(ConfigBlocks.blockCrystal), ConfigItems.itemWispEssence, ConfigItems.itemWispEssence, ModItems.vineBall}};

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

    public Packet getDescriptionPacket() {
        NBTTagCompound data = new NBTTagCompound();
        this.writeToNBT(data);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 2, data);
    }

    public void onDataPacket(NetworkManager netManager, S35PacketUpdateTileEntity packet) {
        this.readFromNBT(packet.func_148857_g());
    }

    public void updateEntity() {
        super.updateEntity();
        if (this.getStackInSlot(0) != null && this.getStackInSlot(0).getItem() instanceof ItemJarNode) {
            if (!this.hasItem) {
                VanillaPacketDispatcher.dispatchTEToNearbyPlayers(this.worldObj, this.xCoord, this.yCoord, this.zCoord);
            }
            this.hasItem = true;
            if (!this.isStoped) {
                ArrayList<TilePedestal> ped = this.getPed();
                ArrayList<ItemStack> items = this.getItemsFromPed(ped);
                if (items.size() < 2) {
                    return;
                }
                boolean isnew = this.isNewEssence(items);
                if (!this.trueItem(items, isnew)) {
                    return;
                }
                if (this.proccess < 600) {
                    if (!this.findPool()) {
                        return;
                    }
                    ++this.proccess;
                    for (TilePedestal pe : ped) {
                        this.showLight(pe);
                        Botania.proxy.sparkleFX(this.getWorldObj(), (double)pe.xCoord + Math.random(), (double)pe.yCoord + Math.random(), (double)pe.zCoord + Math.random(), 1.0f, 1.0f, 1.0f, (float)Math.random(), 5);
                    }
                    Botania.proxy.sparkleFX(this.getWorldObj(), (double)this.xCoord + Math.random(), (double)this.yCoord + Math.random(), (double)this.zCoord + Math.random(), 1.0f, 1.0f, 1.0f, (float)Math.random(), 5);
                    return;
                }
                this.proccess = 0;
                if (!this.worldObj.isRemote) {
                    ArrayList<Aspect> newAsp = this.delItem(ped, isnew);
                    this.addAspects(newAsp, this.getStackInSlot(0));
                }
            }
        } else {
            this.hasItem = false;
            VanillaPacketDispatcher.dispatchTEToNearbyPlayers(this.worldObj, this.xCoord, this.yCoord, this.zCoord);
        }
    }

    private ArrayList<ItemStack> getItemsFromPed(ArrayList<TilePedestal> ped) {
        ArrayList<ItemStack> items = new ArrayList<ItemStack>();
        for (TilePedestal pe : ped) {
            items.add(pe.func_70301_a(0));
        }
        return items;
    }

    private ArrayList<TilePedestal> getPed() {
        int crys = 0;
        int ball = 0;
        int wisp = 0;
        int shard = 0;
        ArrayList<TilePedestal> ped = new ArrayList<TilePedestal>();
        for (int i = -3; i <= 3; ++i) {
            for (int j = -3; j <= 3; ++j) {
                TileEntity var0 = this.worldObj.getTileEntity(this.xCoord + i, this.yCoord, this.zCoord + j);
                if (var0 == null || !(var0 instanceof TilePedestal) || ((TilePedestal)var0).func_70301_a(0) == null) continue;
                ItemStack var10 = ((TilePedestal)var0).func_70301_a(0);
                if (crys < 1 && this.isNewEssence(var10) && shard < 1) {
                    ped.add((TilePedestal)var0);
                    ++crys;
                    continue;
                }
                if (ball < 1 && var10.getItem() instanceof ItemVineBall) {
                    ped.add((TilePedestal)var0);
                    ++ball;
                    continue;
                }
                if (wisp < 2 && var10.getItem() instanceof ItemWispEssence && shard < 1) {
                    ped.add((TilePedestal)var0);
                    ++wisp;
                    continue;
                }
                if (shard >= 1 || !(var10.getItem() instanceof ItemShard) || crys >= 1) continue;
                ped.add((TilePedestal)var0);
                ++shard;
            }
        }
        return ped;
    }

    private boolean findPool() {
        int xs = this.xCoord;
        int ys = this.yCoord + 1;
        int zs = this.zCoord;
        Block bl0 = this.worldObj.getBlock(xs, ys, zs);
        if (!bl0.isAir(this.worldObj, xs, ys, zs) && (bl0 instanceof BlockPool || bl0 instanceof BlockElfPool)) {
            TileEntity var0 = this.worldObj.getTileEntity(xs, ys, zs);
            if (var0 instanceof TilePool && this.requestMana((TilePool)var0)) {
                return true;
            }
            return var0 instanceof TileElfPool && this.requestMana((TileElfPool) var0);
        }
        return false;
    }

    private void showLight(TileEntity te) {
        if (this.proccess % 10 == 0) {
            Vector3 vec = Vector3.fromTileEntityCenter(te);
            int[] pos = new int[]{this.xCoord - te.xCoord, this.yCoord - te.yCoord, this.zCoord - te.zCoord};
            Vector3 endVec = vec.copy().add(pos[0], pos[1], pos[2]);
            Botania.proxy.lightningFX(this.worldObj, vec, endVec, 2.0f, 38027, 58583);
        }
    }

    private boolean requestMana(TilePool pool) {
        int mana = pool.getCurrentMana();
        if (mana > 500) {
            pool.recieveMana(-500);
            return true;
        }
        return false;
    }

    private boolean requestMana(TileElfPool pool) {
        int mana = pool.getCurrentMana();
        if (mana > 400) {
            pool.recieveMana(-400);
            return true;
        }
        return false;
    }

    private void addAspects(ArrayList<Aspect> asp, ItemStack var0) {
        ItemJarNode node = (ItemJarNode)var0.getItem();
        AspectList var1 = node.getAspects(var0);
        int rand = 0 + (int)(Math.random() * (double)(asp.size() - 1));
        Aspect[] var2 = var1.getAspects();
        boolean has = false;
        for (Aspect var4 : var2) {
            if (var4.getTag() != asp.get(rand).getTag()) continue;
            has = true;
        }
        if (has) {
            var1.reduce(asp.get(rand), -1);
        } else {
            var1.add(asp.get(rand), 1);
        }
        node.setAspects(var0, var1);
    }

    private ArrayList<Aspect> delItem(ArrayList<TilePedestal> peds, boolean isnew) {
        ArrayList<Aspect> asp = new ArrayList<Aspect>();
        int main = 0;
        int ball = 0;
        int dop = 0;
        for (TilePedestal ped : peds) {
            String s;
            ItemStack var0 = ped.func_70301_a(0);
            if (var0 == null) continue;
            if (isnew) {
                if (var0.getItem() instanceof ItemWispEssence && dop < 2) {
                    NBTTagCompound var1 = ItemNBTHelper.getNBT(var0);
                    NBTTagList var2 = var1.getTagList("Aspects", 10);
                    NBTTagCompound var3 = var2.getCompoundTagAt(0);
                    String s2 = var3.getString("key");
                    if (s2 != "" && Aspect.getAspect(s2) != null) {
                        asp.add(Aspect.getAspect(s2));
                    }
                    ped.func_70299_a(0, null);
                    ++dop;
                    continue;
                }
                if (var0.getItem() instanceof ItemVineBall && ball < 1) {
                    ped.func_70299_a(0, null);
                    ++ball;
                    continue;
                }
                if (!(var0.getItem() instanceof ItemBlock) || !(Block.getBlockFromItem(var0.getItem()) instanceof BlockCrystal) || main >= 1) continue;
                int meta = var0.getItemDamage();
                s = meta == 0 ? "aer" : (meta == 1 ? "ignis" : (meta == 2 ? "aqua" : (meta == 3 ? "terra" : (meta == 4 ? "ordo" : "perditio"))));
                asp.add(Aspect.getAspect(s));
                if (meta == 6) {
                    s = "aer";
                    asp.add(Aspect.getAspect(s));
                    s = "ignis";
                    asp.add(Aspect.getAspect(s));
                    s = "aqua";
                    asp.add(Aspect.getAspect(s));
                    s = "terra";
                    asp.add(Aspect.getAspect(s));
                    s = "ordo";
                    asp.add(Aspect.getAspect(s));
                }
                ped.func_70299_a(0, null);
                ++main;
                continue;
            }
            if (var0.getItem() instanceof ItemShard && main < 1) {
                int meta = var0.getItemDamage();
                s = meta == 0 ? "aer" : (meta == 1 ? "ignis" : (meta == 2 ? "aqua" : (meta == 3 ? "terra" : (meta == 4 ? "ordo" : "perditio"))));
                asp.add(Aspect.getAspect(s));
                ped.func_70299_a(0, null);
                ++main;
                continue;
            }
            if (!(var0.getItem() instanceof ItemVineBall) || ball >= 1) continue;
            ped.func_70299_a(0, null);
            ++ball;
        }
        return asp;
    }

    private boolean trueItem(ArrayList<ItemStack> items, boolean isnew) {
        if (isnew) {
            int wisp = 0;
            int ball = 0;
            for (ItemStack is : items) {
                if (is != null && is.getItem() instanceof ItemWispEssence) {
                    ++wisp;
                }
                if (is == null || !(is.getItem() instanceof ItemVineBall)) continue;
                ++ball;
            }
            return wisp >= 2 && ball >= 1;
        } else {
            int shard = 0;
            int ball = 0;
            for (ItemStack is : items) {
                if (is != null && is.getItem() instanceof ItemShard) {
                    ++shard;
                }
                if (is == null || !(is.getItem() instanceof ItemVineBall)) continue;
                ++ball;
            }
            return shard >= 1 && ball >= 1;
        }
    }

    private boolean isNewEssence(ArrayList<ItemStack> items) {
        for (ItemStack is : items) {
            if (is == null || !(is.getItem() instanceof ItemBlock) || !(Block.getBlockFromItem(is.getItem()) instanceof BlockCrystal)) continue;
            return true;
        }
        return false;
    }

    private boolean isNewEssence(ItemStack items) {
        return items != null && items.getItem() instanceof ItemBlock && Block.getBlockFromItem(items.getItem()) instanceof BlockCrystal;
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
    }

    public String getInventoryName() {
        return this.hasCustomInventoryName() ? this.customName : "container.chest";
    }

    public boolean hasCustomInventoryName() {
        return this.customName != null && this.customName.length() > 0;
    }

    public int getInventoryStackLimit() {
        return 64;
    }

    public boolean isUseableByPlayer(EntityPlayer entityPlayer) {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) == this && entityPlayer.getDistanceSq((double) this.xCoord + 0.5, (double) this.yCoord + 0.5, (double) this.zCoord + 0.5) <= 64.0;
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

    public boolean isItemValidForSlot(int i, ItemStack itemStack) {
        return true;
    }

    public void renderHUD(Minecraft mc, ScaledResolution res) {
        if (this.getStackInSlot(0) == null) {
            return;
        }
        ArrayList<TilePedestal> ped = this.getPed();
        ArrayList<ItemStack> items = this.getItemsFromPed(ped);
        if (items.size() < 2) {
            return;
        }
        boolean isnew = this.isNewEssence(items);
        if (!this.trueItem(items, isnew)) {
            return;
        }
        int x = res.getScaledWidth() / 2 + 20;
        int y = res.getScaledHeight() / 2 - 8;
        RenderHelper.renderProgressPie(x, y, (float)this.proccess / 600.0f, this.getStackInSlot(0));
        String s = "\u00a7f\u0421\u0442\u0430\u0442\u0443\u0441: " + (this.isStoped ? "\u00a7c\u043e\u0441\u0442\u0430\u043d\u043e\u0432\u043b\u0435\u043d" : "\u00a72\u0440\u0430\u0431\u043e\u0442\u0430\u0435\u0442");
        mc.fontRenderer.drawStringWithShadow(s, x - 20 - s.length() / 2, y + 20, 0xFFFFFF);
    }

    public int[] getAccessibleSlotsFromSide(int i) {
        return new int[0];
    }

    public boolean canInsertItem(int i, ItemStack itemStack, int i1) {
        return false;
    }

    public boolean canExtractItem(int i, ItemStack itemStack, int i1) {
        return false;
    }

    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        NBTTagList nbttaglist = nbt.getTagList("Items", 10);
        this.chestContents = new ItemStack[this.getSizeInventory()];
        if (nbt.hasKey("CustomName", 8)) {
            this.customName = nbt.getString("CustomName");
        }
        if (nbt.hasKey("mana")) {
            this.MANA = nbt.getInteger("mana");
        }
        if (nbt.hasKey("isStoped")) {
            this.isStoped = nbt.getBoolean("isStoped");
        }
        if (nbt.hasKey("hasItem")) {
            this.hasItem = nbt.getBoolean("hasItem");
        }
        for (int i = 0; i < nbttaglist.tagCount(); ++i) {
            ItemStack v0;
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            int j = nbttagcompound1.getByte("Slot") & 0xFF;
            if (j < 0 || j >= this.chestContents.length) continue;
            this.chestContents[j] = v0 = ItemStack.loadItemStackFromNBT(nbttagcompound1);
        }
        this.item = nbt;
    }

    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        NBTTagList nbttaglist = new NBTTagList();
        NBTTagCompound var1 = new NBTTagCompound();
        if (nbt != null) {
            var1 = new NBTTagCompound();
        }
        nbt.setInteger("mana", this.MANA);
        nbt.setBoolean("isStoped", this.isStoped);
        if (this.hasItem) {
            nbt.setBoolean("hasItem", this.hasItem);
        }
        for (int i = 0; i < this.chestContents.length; ++i) {
            if (this.chestContents[i] == null) continue;
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();
            nbttagcompound1.setByte("Slot", (byte)i);
            this.chestContents[i].writeToNBT(nbttagcompound1);
            nbttaglist.appendTag(nbttagcompound1);
        }
        nbt.setTag("Items", nbttaglist);
        if (this.hasCustomInventoryName()) {
            nbt.setString("CustomName", this.customName);
        }
    }

    public boolean onUsedByWand(EntityPlayer entityPlayer, ItemStack itemStack, World world, int i, int i1, int i2, int i3) {
        this.isStoped = !this.isStoped;
        return true;
    }
}

