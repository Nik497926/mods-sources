/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameData
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 */
package info.jbcs.minecraft.vending.inventory;

import cpw.mods.fml.common.registry.GameData;
import info.jbcs.minecraft.vending.gui.GuiPickBlock;
import info.jbcs.minecraft.vending.inventory.InventoryStatic;
import info.jbcs.minecraft.vending.inventory.SlotPickBlock;
import java.util.ArrayList;
import java.util.Set;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ContainerPickBlock
extends Container {
    public ArrayList<ItemStack> items = new ArrayList();
    public GuiPickBlock gui;
    EntityPlayer player;
    public int width = 9;
    public int height = 7;
    public SlotPickBlock resultSlot;
    public InventoryStatic inventory = new InventoryStatic(this.width * this.height + 1){

        public void markDirty() {
        }

        @Override
        public boolean isItemValidForSlot(int i, ItemStack itemstack) {
            return false;
        }
    };

    public ContainerPickBlock(EntityPlayer p) {
        String[] itemNames = (String[]) GameData.getItemRegistry().getKeys().toArray();
        for (String itemName : itemNames) {
            Item item = (Item) GameData.getItemRegistry().getObject(itemName);
            if (item == null || item.getCreativeTab() == null) continue;
            item.getSubItems(item, null, this.items);
        }
        int index = 0;
        for (int y = 0; y < this.height; ++y) {
            for (int x = 0; x < this.width; ++x) {
                this.addSlotToContainer(new SlotPickBlock(this, index++, 9 + x * 18, 18 + y * 18));
            }
        }
        this.resultSlot = new SlotPickBlock(this, index++, 18, 153);
        this.addSlotToContainer(this.resultSlot);
        this.player = p;
        this.scrollTo(0.0f);
    }

    public void scrollTo(float offset) {
        int columnsNotFitting = this.items.size() / this.width - this.height + 1;
        if (columnsNotFitting < 0) {
            columnsNotFitting = 0;
        }
        int columnOffset = (int)((double)(offset * (float)columnsNotFitting) + 0.5);
        for (int y = 0; y < this.height; ++y) {
            for (int x = 0; x < this.width; ++x) {
                int index = x + (y + columnOffset) * this.width;
                if (index >= 0 && index < this.items.size()) {
                    this.inventory.setInventorySlotContents(x + y * this.width, this.items.get(index));
                    continue;
                }
                this.inventory.setInventorySlotContents(x + y * this.width, null);
            }
        }
    }

    public ItemStack transferStackInSlot(EntityPlayer player, int index) {
        SlotPickBlock slot = (SlotPickBlock)((Object)this.inventorySlots.get(index));
        return slot.transferStackInSlot(player);
    }

    public boolean canInteractWith(EntityPlayer entityplayer) {
        return true;
    }
}

