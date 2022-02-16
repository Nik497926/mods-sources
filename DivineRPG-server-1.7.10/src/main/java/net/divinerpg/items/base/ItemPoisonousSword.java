package net.divinerpg.items.base;

import com.gamerforea.eventhelper.util.EventUtils;
import net.divinerpg.utils.TooltipLocalizer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import java.util.List;

public class ItemPoisonousSword extends ItemModSword
{
	private float poisonSeconds;

	public ItemPoisonousSword(ToolMaterial mat, String name, float seconds)
	{
		super(mat, name);
		this.poisonSeconds = seconds;
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
	{
		// TODO gamerforEA add condition [2]
		if (entity instanceof EntityLivingBase && !EventUtils.cantDamage(player, entity))
			((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.poison.id, (int) (this.poisonSeconds * 20), 1));
		return false;
	}

	@Override
	protected void addAdditionalInformation(List list)
	{
		list.add(TooltipLocalizer.poison(this.poisonSeconds));
	}
}
