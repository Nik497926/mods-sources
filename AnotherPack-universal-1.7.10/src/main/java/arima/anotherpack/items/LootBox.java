package arima.anotherpack.items;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;

import arima.anotherpack.core.Main;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LootBox  extends Item {

	   public int itemsAmount;
	   public ItemStack[] items;
	   public int[] count;
	   //public Item itemKey;


	   public LootBox(ItemStack[] items, int[] count, String texture, String name) {
	      //this.setMaxDamage(1);
	      this.items = items;
	      this.count = count;
	      this.setTextureName(texture);
	      this.setUnlocalizedName(name);
	      //this.itemKey = key;
	      this.setCreativeTab(Main.tabAP2Items);
	      GameRegistry.registerItem(this, name);
	   }

	   public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
	      if(!world.isRemote) {
	         //player.inventory.consumeInventoryItem(this.itemKey);
	         int randChance = world.rand.nextInt(this.items.length);
	         boolean chances = false;
	         ItemStack item = this.items[randChance];
	         int counts = this.count[randChance];
	         if(item != null) {
	            item.stackSize = counts;
	            player.setCurrentItemOrArmor(0, item);
	         }
	      }

	      return stack;
	   }
}