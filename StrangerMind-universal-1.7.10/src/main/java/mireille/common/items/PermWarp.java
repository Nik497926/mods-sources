package mireille.common.items;

import mireille.*;
import cpw.mods.fml.common.registry.*;
import net.minecraft.item.*;
import net.minecraft.world.*;
import net.minecraft.entity.player.*;
import cpw.mods.fml.common.*;
import net.minecraft.command.*;
import net.minecraft.server.*;

public class PermWarp extends Item
{
    public PermWarp(final String name, final String texture, final int j) {
        this.setUnlocalizedName(name);
        this.setTextureName(texture);
        this.setCreativeTab(StrangerMind.StrangerMind);
        this.setMaxDamage(800);
        this.canRepair = false;
        this.maxStackSize = j;
        GameRegistry.registerItem((Item)this, name);
    }
    
    public ItemStack onItemRightClick(final ItemStack stack, final World world, final EntityPlayer player) {
        if (!world.isRemote) {
            final MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
            final String command = "tc warp $nick add 70 perm".replace("$nick", player.getCommandSenderName());
            server.getCommandManager().executeCommand((ICommandSender)server, command);
            stack.splitStack(1);
            if (stack.stackSize <= 0) {
                player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
            }
        }
        return stack;
    }
}
