/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.inventory.guiitems;

import com.meteor.extrabotany.client.gui.ContainerMod;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerBookRecipe
extends ContainerMod {
    private final IInventory te;
    private int lastTimer = -1;
    private String lastTarget = "";
    private final Integer fx = 0;
    private final Integer fy = 10;
    public Boolean valid = true;
    private int[] xPos = new int[]{27, 55, 84, 112, 140, 168, 195};

    public ContainerBookRecipe(InventoryPlayer playerInventory, IInventory inv) {
        int xc;
        int ind = 0;
        for (xc = 0; xc < 7; ++xc) {
            this.addSlotToContainer(new Slot(inv, ind, this.xPos[xc], 13){

                public boolean isItemValid(ItemStack itemStack) {
                    return false;
                }
            });
            ++ind;
        }
        for (xc = 0; xc < 7; ++xc) {
            this.addSlotToContainer(new Slot(inv, ind, this.xPos[xc], 39){

                public boolean isItemValid(ItemStack itemStack) {
                    return false;
                }
            });
            ++ind;
        }
        for (xc = 0; xc < 7; ++xc) {
            this.addSlotToContainer(new Slot(inv, ind, this.xPos[xc], 64){

                public boolean isItemValid(ItemStack itemStack) {
                    return false;
                }
            });
            ++ind;
        }
        for (xc = 0; xc < 7; ++xc) {
            this.addSlotToContainer(new Slot(inv, ind, this.xPos[xc], 89){

                public boolean isItemValid(ItemStack itemStack) {
                    return false;
                }
            });
            ++ind;
        }
        for (xc = 0; xc < 7; ++xc) {
            this.addSlotToContainer(new Slot(inv, ind, this.xPos[xc], 114){

                public boolean isItemValid(ItemStack itemStack) {
                    return false;
                }
            });
            ++ind;
        }
        for (xc = 0; xc < 7; ++xc) {
            this.addSlotToContainer(new Slot(inv, ind, this.xPos[xc], 139){

                public boolean isItemValid(ItemStack itemStack) {
                    return false;
                }
            });
            ++ind;
        }
        for (xc = 0; xc < 7; ++xc) {
            this.addSlotToContainer(new Slot(inv, ind, this.xPos[xc], 162){

                public boolean isItemValid(ItemStack itemStack) {
                    return false;
                }
            });
            ++ind;
        }
        this.addSlotToContainer(new Slot(inv, ind, 208, 225){

            public boolean isItemValid(ItemStack itemStack) {
                return false;
            }
        });
        this.te = inv;
    }

    public ItemStack slotClick(int p_slotClick_1_, int p_slotClick_2_, int p_slotClick_3_, EntityPlayer p_slotClick_4_) {
        return null;
    }

    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return false;
    }

    public void detectAndSendChanges() {
        super.detectAndSendChanges();
    }

    @SideOnly(value=Side.CLIENT)
    public void updateProgressBar(int id, int value) {
        super.updateProgressBar(id, value);
    }

    public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex) {
        return null;
    }
}

