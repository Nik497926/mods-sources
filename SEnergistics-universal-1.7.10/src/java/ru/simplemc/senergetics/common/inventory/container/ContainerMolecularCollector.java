/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.inventory.container;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Optional;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import ru.simplemc.senergetics.common.inventory.InventoryTileEntity;
import ru.simplemc.senergetics.common.inventory.container.ContainerTileEntity;
import ru.simplemc.senergetics.common.tileentity.component.process.molecular.MolecularProcessHolder;
import ru.simplemc.senergetics.common.tileentity.machine.TileEntityMolecularCollector;
import ru.simplemc.senergetics.recipe.MolecularCollectorRecipe;

public class ContainerMolecularCollector
extends ContainerTileEntity<TileEntityMolecularCollector> {
    private final int[] processStatuses = new int[5];

    public ContainerMolecularCollector(InventoryTileEntity<TileEntityMolecularCollector> inventory, EntityPlayer player) {
        super(inventory, player);
    }

    @Override
    protected void bindMachineInventory() {
        int i;
        for (i = 0; i < 5; ++i) {
            this.addSlotToContainer(new Slot((IInventory)this.inventory, i, i % 5 * 32 + 16, 22));
        }
        for (i = 5; i < 10; ++i) {
            this.addSlotToContainer(new Slot((IInventory)this.inventory, i, i % 5 * 32 + 16, 53));
        }
    }

    @Override
    protected void bindPlayerInventory() {
        int i;
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot((IInventory)this.player.inventory, j + i * 9 + 9, 8 + j * 18, 131 + i * 18));
            }
        }
        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot((IInventory)this.player.inventory, i, 8 + i * 18, 189));
        }
    }

    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        MolecularProcessHolder processHolder = ((TileEntityMolecularCollector)((Object)this.inventory.getTileEntity())).getProcessHolder();
        for (int i = 0; i < this.processStatuses.length; ++i) {
            this.processStatuses[i] = processHolder.getProcessProgressPercent(i);
        }
        for (Object o : this.crafters) {
            ICrafting crafter = (ICrafting)o;
            for (int i = 0; i < this.processStatuses.length; ++i) {
                crafter.sendProgressBarUpdate((Container)this, i, this.processStatuses[i]);
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void updateProgressBar(int index, int value) {
        if (this.processStatuses.length > index) {
            this.processStatuses[index] = value;
        }
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotIndex) {
        Slot slot = (Slot)this.inventorySlots.get(slotIndex);
        ItemStack itemStack = null;
        if (slot != null && slot.getHasStack()) {
            ItemStack itemStackInSlot = slot.getStack();
            itemStack = itemStackInSlot.copy();
            int inventoryRange = this.inventorySlots.size() - 36;
            if (slotIndex < inventoryRange) {
                if (!this.mergeItemStack(itemStackInSlot, inventoryRange, this.inventorySlots.size(), true)) {
                    return null;
                }
            } else {
                Optional<MolecularCollectorRecipe> recipeOptional = MolecularCollectorRecipe.getRecipeForItemStack(itemStack);
                if (!recipeOptional.isPresent()) {
                    return null;
                }
                int slotEmptyNext = 0;
                for (int processId = 0; processId < 5; ++processId) {
                    if (this.processStatuses[processId] >= 1) continue;
                    slotEmptyNext = processId;
                    break;
                }
                if (!this.mergeItemStack(itemStackInSlot, slotEmptyNext, 5, false)) {
                    return null;
                }
            }
            if (itemStackInSlot.stackSize == 0) {
                slot.putStack(null);
            } else {
                slot.onSlotChanged();
            }
            if (itemStackInSlot.stackSize == itemStack.stackSize) {
                return null;
            }
            slot.onPickupFromSlot(player, itemStackInSlot);
        }
        return itemStack;
    }

    public int[] getProcessStatuses() {
        return this.processStatuses;
    }
}

