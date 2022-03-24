package mireille.common.items;

import mireille.*;
import cpw.mods.fml.common.registry.*;
import net.minecraft.item.*;
import net.minecraft.world.*;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;

public class EnderBag extends Item
{
    public EnderBag(final String name, final String texture, final int j) {
        this.setUnlocalizedName(name);
        this.setTextureName(texture);
        this.setCreativeTab(StrangerMind.StrangerMind);
        this.canRepair = false;
        this.maxStackSize = j;
        GameRegistry.registerItem((Item)this, name);
    }
    
    public ItemStack onItemRightClick(final ItemStack stack, final World world, final EntityPlayer player) {
        final InventoryEnderChest inventoryenderchest = player.getInventoryEnderChest();
        player.displayGUIChest((IInventory)inventoryenderchest);
        player.playSound("mob.endermen.portal", 1.0f, 1.0f);
        return stack;
    }
}
