package thaumcraft.common.items;

import com.gamerforea.eventhelper.util.EventUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigBlocks;

public class ItemBucketDeath extends Item
{
	@SideOnly(Side.CLIENT)
	public IIcon icon;

	public ItemBucketDeath()
	{
		this.setCreativeTab(Thaumcraft.tabTC);
		this.setHasSubtypes(false);
		this.setMaxStackSize(1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir)
	{
		this.icon = ir.registerIcon("thaumcraft:bucket_death");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int par1)
	{
		return this.icon;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		boolean flag = true;
		MovingObjectPosition mop = this.getMovingObjectPositionFromPlayer(world, player, flag);
		if (mop == null)
			return stack;
		else
		{
			if (mop.typeOfHit == MovingObjectType.BLOCK)
			{
				int x = mop.blockX;
				int y = mop.blockY;
				int z = mop.blockZ;
				if (mop.sideHit == 0)
					--y;

				if (mop.sideHit == 1)
					++y;

				if (mop.sideHit == 2)
					--z;

				if (mop.sideHit == 3)
					++z;

				if (mop.sideHit == 4)
					--x;

				if (mop.sideHit == 5)
					++x;

				if (!player.canPlayerEdit(x, y, z, mop.sideHit, stack))
					return stack;

				// TODO gamerforEA code start
				if (EventUtils.cantBreak(player, x, y, z))
					return stack;
				// TODO gamerforEA code end

				if (this.tryPlaceContainedLiquid(world, x, y, z) && !player.capabilities.isCreativeMode)
					return new ItemStack(Items.bucket);
			}

			return stack;
		}
	}

	public boolean tryPlaceContainedLiquid(World world, int x, int y, int z)
	{
		Material material = world.getBlock(x, y, z).getMaterial();
		boolean flag = !material.isSolid();
		if (!world.isAirBlock(x, y, z) && !flag)
			return false;
		else
		{
			if (!world.isRemote && flag && !material.isLiquid())
				world.func_147480_a(x, y, z, true);

			world.setBlock(x, y, z, ConfigBlocks.blockFluidDeath, 3, 3);
			return true;
		}
	}
}
