package arima.anotherpack.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import arima.anotherpack.core.Main;
import cpw.mods.fml.common.registry.GameRegistry;

public class ProchnItem extends Item{
	   public ProchnItem(String name, String texture) {
		      this.setUnlocalizedName(name);
		      this.setTextureName(texture);
		      this.setCreativeTab(Main.tabAP2Items);
		      this.setMaxDamage(640);
		      this.setMaxStackSize(1);
		      this.setNoRepair();
		      this.setContainerItem(this);
		      GameRegistry.registerItem(this, name);
		   }
	   
	   public ItemStack getContainerItem(ItemStack is) {
		   if (is.getItemDamage() >= 0) {
		   is.setItemDamage(is.getItemDamage() + 1);
		   return is;
		   }
		   return super.getContainerItem(is);
		   }
}
