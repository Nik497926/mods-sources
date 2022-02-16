package net.divinerpg.items.vanilla;

import com.gamerforea.eventhelper.util.EventUtils;
import net.divinerpg.items.base.ItemMod;
import net.divinerpg.utils.TooltipLocalizer;
import net.divinerpg.utils.Util;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;

import java.util.List;

public class ItemSerenadeStriker extends ItemMod
{
	public ItemSerenadeStriker(String name)
	{
		super(name);
		this.setCreativeTab(DivineRPGTabs.ranged);
		this.setMaxDamage(100);
		this.setMaxStackSize(1);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		final double eyeHeight = 1.62;
		final double reachDistance = 300;

		MovingObjectPosition rarTrace = Util.rayTrace(player, 100);

		if (rarTrace != null && rarTrace.typeOfHit == MovingObjectType.BLOCK)
		{
			int x = rarTrace.blockX;
			int y = rarTrace.blockY;
			int z = rarTrace.blockZ;

			if (Math.abs(Math.sqrt(player.posX * player.posX + player.posY * player.posY + player.posZ * player.posZ) - Math.sqrt(x * x + y * y + z * z)) < 100)
			{
				// TODO gamerforEA code start
				if (EventUtils.cantBreak(player, x, y, z))
					return stack;

				double d = 3.0D;
				List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(player, AxisAlignedBB.getBoundingBox(x - d, y - d, z - d, x + d, y + 6.0D + d, z + d));
				for (Entity entity : list)
				{
					if (EventUtils.cantDamage(player, entity))
						return stack;
				}
				// TODO gamerforEA code start

				world.spawnEntityInWorld(new EntityLightningBolt(world, x, y, z));
				world.spawnEntityInWorld(new EntityLightningBolt(world, x, y, z));
				world.spawnEntityInWorld(new EntityLightningBolt(world, x, y, z));
				if (!player.capabilities.isCreativeMode)
					stack.damageItem(1, player);
			}
		}
		return stack;
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
	{
		list.add("Shoots Lightning");
		list.add(TooltipLocalizer.usesRemaining(stack.getMaxDamage() - stack.getItemDamage()));
	}
}
