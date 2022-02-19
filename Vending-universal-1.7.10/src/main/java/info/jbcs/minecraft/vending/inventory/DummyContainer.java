/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.Container
 */
package info.jbcs.minecraft.vending.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class DummyContainer
extends Container {
    public boolean canInteractWith(EntityPlayer entityplayer) {
        return true;
    }
}

