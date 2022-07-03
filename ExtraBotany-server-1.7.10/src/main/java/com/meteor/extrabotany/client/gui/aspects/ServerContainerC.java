/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.gui.aspects;

import com.meteor.extrabotany.client.gui.ContainerMod;
import com.meteor.extrabotany.common.block.tile.TileExtraAspect;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ServerContainerC
extends ContainerMod {
    private final TileExtraAspect te;
    private int lastTimer = -1;
    private String lastTarget = "";
    private final Integer fx = 0;
    private final Integer fy = 10;
    public Boolean valid = true;

    public ServerContainerC(InventoryPlayer playerInventory, TileExtraAspect te) {
        this.addSlotToContainer(new Slot((IInventory)te, 0, 151, 111));
        this.addPlayerSlots(playerInventory, 8, 174);
        this.te = te;
    }

    public boolean canInteractWith(EntityPlayer p_75145_1_) {
        return true;
    }

    public ItemStack transferStackInSlot(EntityPlayer p_82846_1_, int p_82846_2_) {
        return null;
    }
}

