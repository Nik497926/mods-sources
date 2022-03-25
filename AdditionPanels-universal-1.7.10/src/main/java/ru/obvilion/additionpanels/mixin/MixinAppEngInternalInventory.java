package ru.obvilion.additionpanels.mixin;

import appeng.tile.inventory.AppEngInternalInventory;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import ru.obvilion.additionpanels.IAppEngInternalInventory;

@Mixin(value = AppEngInternalInventory.class, remap = false)
public abstract class MixinAppEngInternalInventory implements IAppEngInternalInventory {
    @Shadow(remap = false)  private int size;


    @Shadow(remap = false) private ItemStack[] inv;

    public void setSize(int size) {
        this.size = size;
        inv = new ItemStack[size];
    }
}
