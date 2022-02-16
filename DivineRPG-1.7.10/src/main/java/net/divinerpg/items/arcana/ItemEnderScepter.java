package net.divinerpg.items.arcana;

import com.gamerforea.eventhelper.util.EventUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.divinerpg.items.base.ItemMod;
import net.divinerpg.utils.TooltipLocalizer;
import net.divinerpg.utils.events.ArcanaHelper;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class ItemEnderScepter extends ItemMod
{

	private Random rand = new Random();

	public ItemEnderScepter(String name)
	{
		super(name, DivineRPGTabs.swords);
		this.setMaxStackSize(1);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		float pitch = player.rotationPitch;
		float yaw = player.rotationYaw;
		double posX = player.posX;
		double posY = player.posY + 1.62D - player.yOffset;
		double posZ = player.posZ;
		Vec3 var12 = Vec3.createVectorHelper(posX, posY, posZ);
		float var13 = MathHelper.cos(-yaw * 0.01745329F - (float) Math.PI);
		float var14 = MathHelper.sin(-yaw * 0.01745329F - (float) Math.PI);
		float var15 = -MathHelper.cos(-pitch * 0.01745329F);
		float var16 = MathHelper.sin(-pitch * 0.01745329F);
		float var17 = var14 * var15;
		float var18 = var13 * var15;
		double var19 = 50.0D;
		Vec3 var21 = var12.addVector(var17 * var19, var16 * var19, var18 * var19);
		MovingObjectPosition mop = world.rayTraceBlocks(var12, var21);

		if (mop == null)
			return stack;
		else if (mop.typeOfHit == MovingObjectType.BLOCK)
		{
			int x = mop.blockX;
			int y = mop.blockY;
			int z = mop.blockZ;
			int side = mop.sideHit;

			if (side == 0)
				--y;
			if (side == 1)
				++y;
			if (side == 2)
				--z;
			if (side == 3)
				++z;
			if (side == 4)
				--x;
			if (side == 5)
				++x;

			if (ArcanaHelper.getProperties(player).useBar(75))
			{
				// TODO gamerforEA code start
				if (EventUtils.cantBreak(player, x, y, z))
					return stack;
				// TODO gamerforEA code end

				player.getLook(1);
				this.teleportTo(player, world, x, y, z);
			}
		}

		return stack;
	}

	protected void teleportTo(EntityPlayer player, World par2, double x, double y, double z)
	{
		player.setPosition(x, y, z);
		par2.playSoundAtEntity(player, "mob.endermen.portal", 1.0F, 1.0F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
	{
		list.add(TooltipLocalizer.arcanaConsumed(75));
		list.add("On use: Teleports the player");
		list.add(TooltipLocalizer.infiniteUses());
	}
}
