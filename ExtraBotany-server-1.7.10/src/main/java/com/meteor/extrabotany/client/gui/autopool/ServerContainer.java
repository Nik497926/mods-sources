/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.gui.autopool;

import com.meteor.extrabotany.client.gui.ContainerMod;
import com.meteor.extrabotany.common.block.tile.TileAutoPool;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ServerContainer
extends ContainerMod {
    private final TileAutoPool te;
    private int lastTimer = -1;
    private String lastTarget = "";
    private final Integer fx = 0;
    private final Integer fy = 10;
    public Boolean valid = true;

    public ServerContainer(InventoryPlayer playerInventory, TileAutoPool te) {
        int i;
        this.addSlotToContainer(new Slot((IInventory)te, 0, 26, 32));
        this.addSlotToContainer(new Slot(te, 1, 98, 32){

            public boolean isItemValid(ItemStack p_75214_1_) {
                return false;
            }
        });
        for (i = 2; i < 6; ++i) {
            this.addSlotToContainer(new Slot((IInventory)te, i, 7 + (i - 2) * 18, 81));
        }
        for (i = 6; i < 10; ++i) {
            this.addSlotToContainer(new Slot((IInventory)te, i, 99 + (i - 6) * 18, 81));
        }
        for (i = 10; i < 14; ++i) {
            this.addSlotToContainer(new Slot((IInventory)te, i, 135, 5 + (i - 10) * 18));
        }
        this.addPlayerSlots(playerInventory, 8, 109);
        this.te = te;
    }

    public boolean canInteractWith(EntityPlayer p_75145_1_) {
        return true;
    }

    public ItemStack transferStackInSlot(EntityPlayer p_82846_1_, int p_82846_2_) {
        return null;
    }
}

