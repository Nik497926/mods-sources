/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.IMerchant
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.ContainerMerchant
 *  net.minecraft.world.World
 */
package net.divinerpg.blocks.base.tileentity.container;

import net.minecraft.entity.IMerchant;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerMerchant;
import net.minecraft.world.World;

public class ContainerDivineMerchant
extends ContainerMerchant {
    public ContainerDivineMerchant(InventoryPlayer par1InventoryPlayer, IMerchant par2IMerchant, World par3World) {
        super(par1InventoryPlayer, par2IMerchant, par3World);
    }

    public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
        return true;
    }
}

