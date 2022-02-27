package arima.anotherpack.items;

import java.util.List;

import arima.anotherpack.core.Main;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EnderBag extends Item {
	private String name;
	public EnderBag(String name, String texture, int j) {
		this.setUnlocalizedName(name);
		this.setTextureName(texture);
		this.setCreativeTab(Main.tabAP2Items);
		this.setMaxDamage(800);
		this.name=name;
		maxStackSize = j;
		GameRegistry.registerItem(this, name);
	}

	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer) {
		InventoryEnderChest inventoryenderchest = entityPlayer.getInventoryEnderChest();
		if (inventoryenderchest != null) {
			entityPlayer.displayGUIChest(inventoryenderchest);
		}
		return itemStack;
	}
	
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4){
        par3List.add("\u00A7e\u041D\u0430\u0436\u043C\u0438\u0442\u0435 \u041F\u041A\u041C \u0434\u043B\u044F \u043E\u0442\u043A\u0440\u044B\u0442\u0438\u044F");
	 } 
}
