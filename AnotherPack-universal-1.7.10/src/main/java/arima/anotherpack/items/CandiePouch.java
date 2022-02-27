package arima.anotherpack.items;

import java.util.List;
import java.util.Random;

import arima.anotherpack.core.AllItems;
import arima.anotherpack.core.Main;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class CandiePouch extends Item {

	final Random random = new Random();

	public CandiePouch(String name, String texture) {
		this.setUnlocalizedName(name);
		this.setTextureName(texture);
		this.setCreativeTab(CreativeTabs.tabFood);
		maxStackSize = 1;
		this.setMaxDamage(1);
		GameRegistry.registerItem(this, name);
	}

	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player1) {
		int a = 5 + random.nextInt(11 - 5);
		int b = 1 + random.nextInt(6 - 1);
		int c = random.nextInt(2);
		if (!world.isRemote) {
			/**
			 * player1.entityDropItem(new ItemStack(AllItems.candie1, a), 0);
			 * player1.entityDropItem(new ItemStack(AllItems.candie2, b), 0);
			 * player1.entityDropItem(new ItemStack(AllItems.candie3, c), 0);
			 **/
			player1.worldObj.playSoundAtEntity(player1, "random.levelup", 3.0F, 1.0F);
			itemStack.damageItem(2, player1);
		}
		return itemStack;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		par3List.add("§eНажмите ПКМ для открытия");

	}

	/**
	 * Код для лута прямо в инвентарь
	 * 
	 * player1.inventory.addItemStackToInventory(new ItemStack(AllItems.candie1, a,
	 * 4)); player1.inventory.addItemStackToInventory(new
	 * ItemStack(AllItems.candie2, b, 4));
	 * player1.inventory.addItemStackToInventory(new ItemStack(AllItems.candie3, c,
	 * 4)); //player1.inventory.clearInventory(AllItems.candie4, 1); // очистка всех
	 * вещей подобного типа //world.playSoundAtEntity(player1, "random.levelup",
	 * 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F)); //играть песню вокруг
	 * игрока Если выпадет конфета, то будет звук if (c==1) {
	 * player1.worldObj.playSoundAtEntity(player1, "arima:item0", 3.0F, 1.0F); }
	 */
}