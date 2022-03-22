/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block.tile;

import com.meteor.extrabotany.common.item.ModItems;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import thaumcraft.common.Thaumcraft;
import vazkii.botania.api.internal.VanillaPacketDispatcher;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.api.mana.spark.ISparkAttachable;
import vazkii.botania.api.mana.spark.ISparkEntity;
import vazkii.botania.client.core.handler.HUDHandler;
import vazkii.botania.common.Botania;

public class TileTradeMana
extends TileEntity
implements ISparkAttachable {
    private HashMap<String, Integer> listSeller = new HashMap();
    private ItemStack price = null;
    private final int maxMana = 20000000;
    private int mana = 0;
    private int cd_client = 0;
    private String finishtext = "\u0422\u0440\u0430\u043d\u0437\u0438\u0442 \u043c\u0430\u043d\u044b \u0437\u0430\u0432\u0435\u0440\u0448\u0435\u043d.";

    public ItemStack getPrice() {
        return this.price;
    }

    private boolean hasPrice(EntityPlayer pl, boolean buy) {
        ItemStack st = pl.inventory.getCurrentItem();
        if (st != null && this.price != null && st.getItem().equals(this.price.getItem()) && st.stackSize >= this.price.stackSize && st.getItemDamage() == this.price.getItemDamage() && ItemStack.areItemStackTagsEqual(st, this.price)) {
            if (buy) {
                st.stackSize -= this.price.stackSize;
                if (st.stackSize <= 0) {
                    st = null;
                }
                pl.inventory.setInventorySlotContents(pl.inventory.currentItem, st);
                pl.inventory.markDirty();
            }
            return true;
        }
        return false;
    }

    private boolean savePrice(IInventory inv) {
        if (!this.hasEmptySlot(inv)) {
            return false;
        }
        ItemStack a = this.price.copy();
        for (int i = 1; i < inv.getSizeInventory(); ++i) {
            ItemStack b = inv.getStackInSlot(i);
            if (b == null) {
                inv.setInventorySlotContents(i, a);
                return true;
            }
            if (b == null || !a.getItem().equals(b.getItem()) || a.getItemDamage() != b.getItemDamage() || !ItemStack.areItemStackTagsEqual(a, b) || b.stackSize >= b.getMaxStackSize()) continue;
            if (a.stackSize + b.stackSize <= a.getMaxStackSize()) {
                a.stackSize += b.stackSize;
                inv.setInventorySlotContents(i, a);
                return true;
            }
            a.stackSize = (a.getMaxStackSize() - b.stackSize - a.stackSize) * -1;
            b.stackSize = b.getMaxStackSize();
            inv.setInventorySlotContents(i, b);
        }
        return false;
    }

    private boolean hasEmptySlot(IInventory inv) {
        for (int i = 1; i < inv.getSizeInventory(); ++i) {
            if (inv.getStackInSlot(i) != null) continue;
            return true;
        }
        return false;
    }

    public void updateEntity() {
        super.updateEntity();
        if (!this.worldObj.isRemote) {
            TileEntity te;
            try {
                for (Map.Entry<String, Integer> entry : this.listSeller.entrySet()) {
                    if (entry.getKey() == null) continue;
                    EntityPlayer pls = this.worldObj.getPlayerEntityByName(entry.getKey());
                    if (pls != null) {
                        if (pls.getDistanceSq(this.xCoord, this.yCoord, this.zCoord) > 49.0) {
                            pls.addChatComponentMessage(new ChatComponentText("\u00a7f[\u00a76ExtraBotania Trade\u00a7f]: \u0412\u044b \u043e\u0442\u043e\u0448\u043b\u0438 \u0441\u043b\u0438\u0448\u043a\u043e\u043c \u0434\u0430\u043b\u0435\u043a\u043e. \u041f\u043e\u043a\u0443\u043f\u043a\u0430 \u043e\u0442\u043c\u0435\u043d\u0435\u043d\u0430."));
                            this.listSeller.remove(entry.getKey());
                            continue;
                        }
                        if (entry.getValue() > 0 && this.mana >= 2500) {
                            this.mana -= 2500;
                            this.listSeller.replace(entry.getKey(), entry.getValue(), entry.getValue() - 2500);
                            ManaItemHandler.dispatchManaExact(new ItemStack(ModItems.castsoulsteel), pls, 2500, true);
                            continue;
                        }
                        if (this.mana < 2500) {
                            this.listSeller.replace(entry.getKey(), entry.getValue(), entry.getValue() - 2500);
                            pls.addChatComponentMessage(new ChatComponentText("\u00a7f[\u00a76ExtraBotania Trade\u00a7f]: \u0412 \u0442\u043e\u0440\u0433. \u0430\u043f\u043f\u0430\u0440\u0430\u0442\u0435 \u0437\u0430\u043a\u043e\u043d\u0447\u0438\u043b\u0430\u0441\u044c \u043c\u0430\u043d\u0430"));
                            continue;
                        }
                        pls.addChatComponentMessage(new ChatComponentText("\u00a7f[\u00a76ExtraBotania Trade\u00a7f]: " + this.finishtext.replaceAll("&", "\u00a7")));
                        this.listSeller.remove(entry.getKey());
                        continue;
                    }
                    this.listSeller.remove(entry.getKey());
                }
            }
            catch (Exception exception) {
                // empty catch block
            }
            if ((te = this.worldObj.getTileEntity(this.xCoord, this.yCoord + 1, this.zCoord)) instanceof IInventory && ((IInventory)te).getSizeInventory() > 0) {
                ItemStack st = ((IInventory)te).getStackInSlot(0);
                if (st != null && (this.price == null || this.price != null && !ItemStack.areItemStacksEqual(this.price, st))) {
                    this.price = st.copy();
                }
                if (st == null && this.price != null) {
                    this.price = null;
                }
            } else if (this.price != null) {
                this.price = null;
            }
            VanillaPacketDispatcher.dispatchTEToNearbyPlayers(this);
        }
        if (this.worldObj.isRemote) {
            if (this.cd_client > 0) {
                --this.cd_client;
                return;
            }
            if (this.listSeller.containsKey(Minecraft.getMinecraft().thePlayer.getCommandSenderName())) {
                float radius = 7.0f;
                float r = 0.0f;
                float g = 1.0f;
                float b = 0.0f;
                for (int i = 0; i < 360; i += 8) {
                    float sync = (float)i * (float)Math.PI / 180.0f;
                    double xc = (double)this.xCoord + 0.5 - Math.cos(sync) * (double)radius;
                    double yc = (double)this.yCoord + 0.5;
                    double zc = (double)this.zCoord + 0.5 - Math.sin(sync) * (double)radius;
                    Botania.proxy.sparkleFX(this.worldObj, xc, yc, zc, r, g, b, 5.0f, 20);
                }
                this.cd_client = 20;
                EntityClientPlayerMP pl = Minecraft.getMinecraft().thePlayer;
                Thaumcraft.proxy.arcLightning(this.worldObj, (double)this.xCoord + 0.5, (double)this.yCoord + 0.5, (double)this.zCoord + 0.5, Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.posY, Minecraft.getMinecraft().thePlayer.posZ, 0.1f, 1.0f, 1.0f, 0.4f);
            }
        }
    }

    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        if (this.price != null) {
            NBTTagCompound _n = new NBTTagCompound();
            this.price.writeToNBT(_n);
            compound.setTag("price", _n);
        }
        compound.setInteger("mana", this.mana);
        NBTTagList nb = new NBTTagList();
        for (Map.Entry<String, Integer> entry : this.listSeller.entrySet()) {
            NBTTagCompound _n = new NBTTagCompound();
            _n.setString("name", entry.getKey());
            _n.setInteger("value", entry.getValue().intValue());
            nb.appendTag(_n);
        }
        if (this.finishtext != null && !this.finishtext.isEmpty()) {
            compound.setString("finishtext", this.finishtext);
        }
        compound.setTag("sell", nb);
    }

    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.price = compound.hasKey("price") ? ItemStack.loadItemStackFromNBT(compound.getCompoundTag("price")) : null;
        this.mana = compound.getInteger("mana");
        if (compound.hasKey("sell")) {
            NBTTagList nb = compound.getTagList("sell", 10);
            HashMap<String, Integer> listSell = new HashMap<String, Integer>();
            for (int i = 0; i < nb.tagCount(); ++i) {
                NBTTagCompound _n = nb.getCompoundTagAt(i);
                if (!_n.hasKey("name") || !_n.hasKey("value")) continue;
                listSell.put(_n.getString("name"), _n.getInteger("value"));
            }
            this.listSeller = listSell;
        }
        if (compound.hasKey("finishtext")) {
            this.finishtext = compound.getString("finishtext");
        }
    }

    public Packet getDescriptionPacket() {
        NBTTagCompound data = new NBTTagCompound();
        this.writeToNBT(data);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 2, data);
    }

    public void onDataPacket(NetworkManager netManager, S35PacketUpdateTileEntity packet) {
        this.readFromNBT(packet.func_148857_g());
    }

    public boolean canAttachSpark(ItemStack itemStack) {
        return true;
    }

    public void attachSpark(ISparkEntity iSparkEntity) {
    }

    public int getAvailableSpaceForMana() {
        return Math.max(0, this.maxMana - this.getCurrentMana());
    }

    public ISparkEntity getAttachedSpark() {
        List sparks = this.worldObj.getEntitiesWithinAABB(ISparkEntity.class, AxisAlignedBB.getBoundingBox(this.xCoord, this.yCoord + 1, this.zCoord, this.xCoord + 1, this.yCoord + 2, this.zCoord + 1));
        if (sparks.size() == 1) {
            Entity e = (Entity)sparks.get(0);
            return (ISparkEntity)e;
        }
        return null;
    }

    public boolean areIncomingTranfersDone() {
        return this.mana >= this.maxMana;
    }

    public boolean isFull() {
        return this.mana >= this.maxMana;
    }

    public void recieveMana(int i) {
        this.mana = Math.min(this.maxMana, this.mana + i);
    }

    public boolean canRecieveManaFromBursts() {
        return this.mana < this.maxMana;
    }

    public int getCurrentMana() {
        return this.mana;
    }

    public void renderHUD(Minecraft mc, ScaledResolution res) {
        int x = res.getScaledWidth() / 2;
        int y = res.getScaledHeight() / 2;
        HUDHandler.renderManaBar(x - 51, y - 35, 255, 0.75f, this.mana, this.maxMana);
    }
}

