/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.gui.autoDaisy;

import com.meteor.extrabotany.client.gui.ContainerMod;
import com.meteor.extrabotany.common.block.tile.TileAutoDaisy;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ServerContainerA
extends ContainerMod {
    private final TileAutoDaisy te;
    private int lastTimer = -1;
    private String lastTarget = "";
    private final Integer fx = 0;
    private final Integer fy = 10;
    public Boolean valid = true;

    public ServerContainerA(InventoryPlayer playerInventory, TileAutoDaisy te) {
        this.addSlotToContainer(new Slot((IInventory)te, 0, 26, 32));
        this.addSlotToContainer(new Slot((IInventory)te, 1, 134, 32));
        this.addPlayerSlots(playerInventory, 8, 84);
        this.te = te;
    }

    public boolean canInteractWith(EntityPlayer p_75145_1_) {
        return true;
    }

    public ItemStack transferStackInSlot(EntityPlayer p_82846_1_, int p_82846_2_) {
        return null;
    }
}

