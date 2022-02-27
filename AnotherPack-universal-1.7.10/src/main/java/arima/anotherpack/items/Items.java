package arima.anotherpack.items;

import arima.anotherpack.core.Main;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class Items extends Item {

	   public Items(String name, String texture, int j) {
		   this.canRepair = false;
	      this.setUnlocalizedName(name);
	      this.setTextureName(texture);
	      this.setCreativeTab(Main.tabAP2Items);
	      this.setMaxDamage(800);
	      maxStackSize = j;
	      GameRegistry.registerItem(this, name);
	   }
	}
