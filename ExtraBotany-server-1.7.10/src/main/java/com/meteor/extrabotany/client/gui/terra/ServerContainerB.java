/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.gui.terra;

import com.meteor.extrabotany.client.gui.ContainerMod;
import com.meteor.extrabotany.common.block.tile.TileAutoPlate;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ServerContainerB
extends ContainerMod {
    private final TileAutoPlate te;
    private int lastTimer = -1;
    private String lastTarget = "";
    private final Integer fx = 0;
    private final Integer fy = 10;
    public Boolean valid = true;

    public ServerContainerB(InventoryPlayer playerInventory, TileAutoPlate te) {
        this.addSlotToContainer(new Slot((IInventory)te, 0, 61, 83));
        this.addSlotToContainer(new Slot((IInventory)te, 1, 80, 83));
        this.addSlotToContainer(new Slot((IInventory)te, 2, 99, 83));
        this.addSlotToContainer(new Slot((IInventory)te, 3, 80, 24));
        this.addPlayerSlots(playerInventory, 8, 113);
        this.te = te;
    }

    public boolean canInteractWith(EntityPlayer p_75145_1_) {
        return true;
    }

    public ItemStack transferStackInSlot(EntityPlayer p_82846_1_, int p_82846_2_) {
        return null;
    }
}

