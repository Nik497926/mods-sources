/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.gui.autoTrade;

import com.meteor.extrabotany.client.gui.ContainerMod;
import com.meteor.extrabotany.common.block.tile.TileAutoTradeElf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ServerContainerT
extends ContainerMod {
    private final TileAutoTradeElf te;
    private int lastTimer = -1;
    private String lastTarget = "";
    private final Integer fx = 0;
    private final Integer fy = 10;
    public Boolean valid = true;

    public ServerContainerT(InventoryPlayer playerInventory, TileAutoTradeElf te) {
        this.addSlotToContainer(new Slot((IInventory)te, 0, 26, 34));
        this.addSlotToContainer(new Slot((IInventory)te, 1, 44, 34));
        this.addSlotToContainer(new Slot((IInventory)te, 2, 125, 34));
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

