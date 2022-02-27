package arima.anotherpack.items;

import arima.anotherpack.core.Main;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemsSword extends Item {

	private final float damageVsEntity;

	public ItemsSword(String name, String texture, int damage) {
		this.setUnlocalizedName(name);
		this.setTextureName(texture);
		//this.setCreativeTab(Main.tabAP2SwordandOthers);
		this.damageVsEntity=damage;
		this.setMaxDamage(1000);
		maxStackSize=1;
		GameRegistry.registerItem(this, name);
	}

    public float getDamageVsEntity()
    {
        return this.damageVsEntity;
    }
	
	@SideOnly(Side.CLIENT)
	public boolean isFull3D() {
		return true;
	}

	public EnumAction getItemUseAction(ItemStack p_77661_1_) {
		return EnumAction.block;
	}

	public int getMaxItemUseDuration(ItemStack p_77626_1_) {
		return 72000;
	}

	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
		return stack;
	}

	public boolean func_150897_b(Block p_150897_1_) {
		return p_150897_1_ == Blocks.web;
	}

	public int getItemEnchantability() {
		return 0;
	}
}
