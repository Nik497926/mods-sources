package mireille.common.container;

import mireille.common.tileentity.*;
import net.minecraft.inventory.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import com.brandon3055.draconicevolution.common.*;

public class ContainerSkinWorkbench extends Container
{
    private final TileSkinWorkbench tile;
    
    public ContainerSkinWorkbench(final InventoryPlayer inv, final TileSkinWorkbench tile) {
        this.tile = tile;
        this.addSlotToContainer((Slot)new StrangerMindSlots((IInventory)tile, 0, 8, 5, 1));
        this.addSlotToContainer((Slot)new StrangerMindSlots((IInventory)tile, 1, 8, 27, 1));
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot((IInventory)inv, j + i * 9 + 9, 8 + j * 18, 87 + i * 18));
            }
        }
        for (int i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot((IInventory)inv, i, 8 + i * 18, 145));
        }
    }
    
    public ItemStack transferStackInSlot(final EntityPlayer player, final int slotID) {
        return null;
    }
    
    public boolean canInteractWith(final EntityPlayer player) {
        return this.tile.isUseableByPlayer(player);
    }
    
    private static class StrangerMindSlots extends Slot
    {
        private int limit;
        private int index;
        
        public StrangerMindSlots(final IInventory inventory, final int index, final int posX, final int posY, final int limit) {
            super(inventory, index, posX, posY);
            this.limit = limit;
            this.index = index;
        }
        
        public boolean isItemValid(final ItemStack stack) {
            if (this.index == 0) {
                return stack != null && stack.getItem() != null && stack.getItem() == ModItems.draconicDestructionStaff;
            }
            return this.index != 1 || (stack != null && stack.getItem() != null && this.isSkinItem(stack));
        }
        
        public int getSlotStackLimit() {
            return this.limit;
        }
        
        protected boolean isSkinItem(final ItemStack stack) {
            for (int i = 0; i < mireille.core.ModItems.AllSkins.length; ++i) {
                String str1;
                if (i == 0) {
                    str1 = ModItems.draconicDestructionStaff.getUnlocalizedName();
                }
                else {
                    str1 = mireille.core.ModItems.AllSkins[i].getUnlocalizedName();
                }
                final String str2 = stack.getUnlocalizedName();
                if (str1.equals(str2)) {
                    return true;
                }
            }
            return false;
        }
    }
}
