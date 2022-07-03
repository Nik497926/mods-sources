/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.gui.flower;

import com.meteor.extrabotany.client.gui.ContainerMod;
import com.meteor.extrabotany.common.block.BlockFlower;
import com.meteor.extrabotany.common.block.tile.TileFlower;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ServerContainer
extends ContainerMod {
    private final TileFlower te;
    private int lastTimer = -1;
    private String lastTarget = "";
    private final Integer fx = 0;
    private final Integer fy = 10;
    public Boolean valid = true;

    public ServerContainer(InventoryPlayer playerInventory, TileFlower te) {
        BlockFlower.BlockFlowerType type = te.type;
        for (int y = 0; y < 9; ++y) {
            for (int x = 0; x < 9; ++x) {
                if (x < type.stIndex || x > type.maxIndex || y < type.stLine || y > type.maxLine) continue;
                this.addSlotToContainer(new Slot((IInventory)te, y * 9 + x, 8 + x * 18, 8 + y * 18));
            }
        }
        this.addPlayerSlots(playerInventory, 8, 175);
        this.te = te;
    }

    public boolean canInteractWith(EntityPlayer p_75145_1_) {
        return true;
    }

    public ItemStack transferStackInSlot(EntityPlayer p_82846_1_, int p_82846_2_) {
        return null;
    }
}

