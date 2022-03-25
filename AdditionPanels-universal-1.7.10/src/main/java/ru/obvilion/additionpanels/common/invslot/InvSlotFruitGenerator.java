package ru.obvilion.additionpanels.common.invslot;

import ic2.api.recipe.RecipeOutput;
import ic2.core.block.TileEntityInventory;
import ic2.core.block.invslot.InvSlotProcessable;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.ArrayList;
import java.util.List;

public class InvSlotFruitGenerator extends InvSlotProcessable {
    public InvSlotFruitGenerator(TileEntityInventory base1, String name1, int oldStartIndex1, int count) {
        super(base1, name1, oldStartIndex1, count);
    }

    @Override
    public boolean accepts(ItemStack itemStack) {
        return itemStack !=null && itemStack.getItem() instanceof ItemFood;
    }

    @Override
    public RecipeOutput process() {
        ItemStack input = null;
        for (int i = 0; i < size(); i++) {
            ItemStack itemStack = get(i);
            if (itemStack != null){
                input = itemStack;
                break;
            }
        }
        if (input == null) {
            return null;
        } else {
            RecipeOutput output = this.getOutputFor(input,false);
            if (output == null) {
                return null;
            } else {
                List<ItemStack> itemsCopy = new ArrayList(output.items.size());

                for (ItemStack itemStack : output.items) {
                    itemsCopy.add(itemStack.copy());
                }

                return new RecipeOutput(output.metadata, itemsCopy);
            }
        }
    }

    @Override
    public void consume() {
        ItemStack input = null;
        int index = 0;
        for (int i = 0; i < size(); i++) {
            ItemStack itemStack = get(i);
            if (itemStack != null){
                input = itemStack;
                index = i;
                break;
            }
        }
        if (input == null) {
            throw new IllegalStateException("consume from empty slot");
        } else {
            RecipeOutput output = this.getOutputFor(input,true);
            if (output == null) {
                throw new IllegalStateException("consume without a processing result");
            } else {
                if (input.stackSize <= 0) {
                    this.put(index,null);
                }

            }
        }
    }

    protected RecipeOutput getOutputFor(ItemStack input, boolean adjustInput) {
        ItemStack copy = input.copy();
        copy.stackSize = 2;
        if (adjustInput){
            --input.stackSize;
        }
        return new RecipeOutput(new NBTTagCompound(), copy);
    }
}
