package thaumcraft.common.lib.utils;

import com.gamerforea.eventhelper.util.EventUtils;
import cpw.mods.fml.relauncher.ReflectionHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.S25PacketBlockBreakAnim;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.entities.EntityFollowingItem;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BlockUtils
{
	static HashMap<Integer, ArrayList[]> blockEventCache = new HashMap();
	static int lastx = 0;
	static int lasty = 0;
	static int lastz = 0;
	static double lastdistance = 0D;

	public static boolean harvestBlock(World world, EntityPlayer player, int x, int y, int z)
	{
		return harvestBlock(world, player, x, y, z, false, 0);
	}

	public static boolean harvestBlock(World world, EntityPlayer player, int x, int y, int z, boolean followItem, int color)
	{
		Block block = world.getBlock(x, y, z);
		if (block.getBlockHardness(world, x, y, z) < 0F)
			return false;

		// TODO gamerforEA code start
		if (EventUtils.cantBreak(player, x, y, z))
			return false;
		// TODO gamerforEA code end

		int meta = world.getBlockMetadata(x, y, z);
		world.playAuxSFX(2001, x, y, z, Block.getIdFromBlock(block) + (meta << 12));

		if (player.capabilities.isCreativeMode)
			removeBlock(world, x, y, z, player);
			// TODO gamerforEA code replace, old code: else if (block.canHarvestBlock(player, meta) && removeBlock(world, x, y, z, player))
		else if (block.canHarvestBlock(player, meta) && removeBlock(world, x, y, z, player, true))
		// TODO gamerforEA code end
		{
			block.harvestBlock(world, player, x, y, z, meta);
			if (followItem)
			{
				List<Entity> entities = EntityUtils.getEntitiesInRange(world, x + 0.5D, y + 0.5D, z + 0.5D, player, EntityItem.class, 2D);
				for (Entity entity : entities)
				{
					if (!entity.isDead && entity instanceof EntityItem && entity.ticksExisted == 0 && !(entity instanceof EntityFollowingItem))
					{
						EntityFollowingItem entityItem = new EntityFollowingItem(world, entity.posX, entity.posY, entity.posZ, ((EntityItem) entity).getEntityItem().copy(), player, color);
						entityItem.motionX = entity.motionX;
						entityItem.motionY = entity.motionY;
						entityItem.motionZ = entity.motionZ;
						world.spawnEntityInWorld(entityItem);
						entity.setDead();
					}
				}

			}
		}

		return true;
	}

	public static ArrayList[] getBlockEventList(WorldServer world)
	{
		if (!blockEventCache.containsKey(world.provider.dimensionId))
			try
			{
				blockEventCache.put(world.provider.dimensionId, ReflectionHelper.getPrivateValue(WorldServer.class, world, new String[] { "field_147490_S" }));
			}
			catch (Exception var2)
			{
				return null;
			}

		return blockEventCache.get(world.provider.dimensionId);
	}

	public static ItemStack createStackedBlock(Block block, int md)
	{
		ItemStack dropped = null;

		try
		{
			Method e = ReflectionHelper.findMethod(Block.class, block, new String[] { "createStackedBlock", "func_149644_j" }, Integer.TYPE);
			dropped = (ItemStack) e.invoke(block, new Object[] { md });
		}
		catch (Exception var4)
		{
			Thaumcraft.log.warn("Could not invoke net.minecraft.block.Block method createStackedBlock");
		}

		return dropped;
	}

	public static void dropBlockAsItem(World world, int x, int y, int z, ItemStack stack, Block block)
	{
		try
		{
			Method e = ReflectionHelper.findMethod(Block.class, block, new String[] { "dropBlockAsItem", "func_149642_a" }, World.class, Integer.TYPE, Integer.TYPE, Integer.TYPE, ItemStack.class);
			e.invoke(block, world, x, y, z, stack);
		}
		catch (Exception var7)
		{
			Thaumcraft.log.warn("Could not invoke net.minecraft.block.Block method createStackedBlock");
		}

	}

	public static void dropBlockAsItemWithChance(World world, Block block, int x, int y, int z, int meta, float dropchance, int fortune, EntityPlayer player)
	{
		if (!world.isRemote && !world.restoringBlockSnapshots)
		{
			ArrayList<ItemStack> items = block.getDrops(world, x, y, z, meta, fortune);
			dropchance = ForgeEventFactory.fireBlockHarvesting(items, world, block, x, y, z, meta, fortune, dropchance, false, player);

			for (ItemStack item : items)
			{
				if (world.rand.nextFloat() <= dropchance)
					dropBlockAsItem(world, x, y, z, item, block);
			}
		}

	}

	public static void destroyBlockPartially(World world, int par1, int par2, int par3, int par4, int par5)
	{
		for (EntityPlayerMP player : (Iterable<? extends EntityPlayerMP>) MinecraftServer.getServer().getConfigurationManager().playerEntityList)
		{
			if (player != null && player.worldObj == MinecraftServer.getServer().getEntityWorld() && player.getEntityId() != par1)
			{
				double d0 = par2 - player.posX;
				double d1 = par3 - player.posY;
				double d2 = par4 - player.posZ;
				if (d0 * d0 + d1 * d1 + d2 * d2 < 1024D)
					player.playerNetServerHandler.sendPacket(new S25PacketBlockBreakAnim(par1, par2, par3, par4, par5));
			}
		}
	}

	// TODO gamerforEA code start
	public static boolean removeBlock(World world, int x, int y, int z, EntityPlayer player, boolean willHarvest)
	{
		Block block = world.getBlock(x, y, z);
		int meta = world.getBlockMetadata(x, y, z);

		if (block != null)
		{
			block.onBlockHarvested(world, x, y, z, meta, player);
			boolean flag = block.removedByPlayer(world, player, x, y, z, willHarvest);
			if (flag)
				block.onBlockDestroyedByPlayer(world, x, y, z, meta);
			return flag;
		}

		return false;
	}
	// TODO gamerforEA code end

	public static boolean removeBlock(World world, int x, int y, int z, EntityPlayer player)
	{
		Block block = world.getBlock(x, y, z);
		int meta = world.getBlockMetadata(x, y, z);

		if (block != null)
		{
			block.onBlockHarvested(world, x, y, z, meta, player);
			boolean flag = block.removedByPlayer(world, player, x, y, z);
			if (flag)
				block.onBlockDestroyedByPlayer(world, x, y, z, meta);
			return flag;
		}

		return false;
	}

	public static void findBlocks(World world, int x, int y, int z, Block block)
	{
		boolean count = false;

		for (int xx = -2; xx <= 2; ++xx)
		{
			for (int yy = 2; yy >= -2; --yy)
			{
				for (int zz = -2; zz <= 2; ++zz)
				{
					if (Math.abs(lastx + xx - x) > 24)
						return;

					if (Math.abs(lasty + yy - y) > 48)
						return;

					if (Math.abs(lastz + zz - z) > 24)
						return;

					if (world.getBlock(lastx + xx, lasty + yy, lastz + zz) == block && Utils.isWoodLog(world, lastx + xx, lasty + yy, lastz + zz) && block.getBlockHardness(world, lastx + xx, lasty + yy, lastz + zz) >= 0F)
					{
						double xd = lastx + xx - x;
						double yd = lasty + yy - y;
						double zd = lastz + zz - z;
						double d = xd * xd + yd * yd + zd * zd;
						if (d > lastdistance)
						{
							lastdistance = d;
							lastx += xx;
							lasty += yy;
							lastz += zz;
							findBlocks(world, x, y, z, block);
							return;
						}
					}
				}
			}
		}

	}

	public static boolean breakFurthestBlock(World world, int x, int y, int z, Block block, EntityPlayer player)
	{
		return breakFurthestBlock(world, x, y, z, block, player, false, 0);
	}

	public static boolean breakFurthestBlock(World world, int x, int y, int z, Block block, EntityPlayer player, boolean followitem, int color)
	{
		lastx = x;
		lasty = y;
		lastz = z;
		lastdistance = 0D;
		findBlocks(world, x, y, z, block);

		boolean worked = harvestBlock(world, player, lastx, lasty, lastz, followitem, color);

		world.markBlockForUpdate(x, y, z);
		if (worked)
		{
			world.markBlockForUpdate(lastx, lasty, lastz);

			for (int xx = -3; xx <= 3; ++xx)
			{
				for (int yy = -3; yy <= 3; ++yy)
				{
					for (int zz = -3; zz <= 3; ++zz)
					{
						world.scheduleBlockUpdate(lastx + xx, lasty + yy, lastz + zz, world.getBlock(lastx + xx, lasty + yy, lastz + zz), 150 + world.rand.nextInt(150));
					}
				}
			}
		}

		return worked;
	}

	public static MovingObjectPosition getTargetBlock(World world, double x, double y, double z, float yaw, float pitch, boolean par3, double range)
	{
		Vec3 var13 = Vec3.createVectorHelper(x, y, z);
		float var14 = MathHelper.cos(-yaw * 0.017453292F - 3.1415927F);
		float var15 = MathHelper.sin(-yaw * 0.017453292F - 3.1415927F);
		float var16 = -MathHelper.cos(-pitch * 0.017453292F);
		float var17 = MathHelper.sin(-pitch * 0.017453292F);
		float var18 = var15 * var16;
		float var20 = var14 * var16;
		Vec3 var23 = var13.addVector(var18 * range, var17 * range, var20 * range);
		return world.func_147447_a(var13, var23, par3, !par3, false);
	}

	public static MovingObjectPosition getTargetBlock(World world, Entity entity, boolean par3)
	{
		float var4 = 1F;
		float var5 = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * var4;
		float var6 = entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * var4;
		double var7 = entity.prevPosX + (entity.posX - entity.prevPosX) * var4;
		double var9 = entity.prevPosY + (entity.posY - entity.prevPosY) * var4 + 1.62D - entity.yOffset;
		double var11 = entity.prevPosZ + (entity.posZ - entity.prevPosZ) * var4;
		Vec3 var13 = Vec3.createVectorHelper(var7, var9, var11);
		float var14 = MathHelper.cos(-var6 * 0.017453292F - 3.1415927F);
		float var15 = MathHelper.sin(-var6 * 0.017453292F - 3.1415927F);
		float var16 = -MathHelper.cos(-var5 * 0.017453292F);
		float var17 = MathHelper.sin(-var5 * 0.017453292F);
		float var18 = var15 * var16;
		float var20 = var14 * var16;
		double var21 = 10D;
		Vec3 var23 = var13.addVector(var18 * var21, var17 * var21, var20 * var21);
		return world.func_147447_a(var13, var23, par3, !par3, false);
	}

	public static boolean isBlockAdjacentToAtleast(IBlockAccess world, int x, int y, int z, Block id, int md, int amount)
	{
		return isBlockAdjacentToAtleast(world, x, y, z, id, md, amount, 1);
	}

	public static boolean isBlockAdjacentToAtleast(IBlockAccess world, int x, int y, int z, Block id, int md, int amount, int range)
	{
		int count = 0;

		for (int xx = -range; xx <= range; ++xx)
		{
			for (int yy = -range; yy <= range; ++yy)
			{
				for (int zz = -range; zz <= range; ++zz)
				{
					if (xx != 0 || yy != 0 || zz != 0)
					{
						if (world.getBlock(x + xx, y + yy, z + zz) == id && (md == OreDictionary.WILDCARD_VALUE || world.getBlockMetadata(x + xx, y + yy, z + zz) == md))
							++count;

						if (count >= amount)
							return true;
					}
				}
			}
		}

		return count >= amount;
	}

	public static List<EntityItem> getContentsOfBlock(World world, int x, int y, int z)
	{
		return world.getEntitiesWithinAABB(EntityItem.class, AxisAlignedBB.getBoundingBox(x, y, z, x + 1D, y + 1D, z + 1D));
	}

	public static int countExposedSides(World world, int x, int y, int z)
	{
		int count = 0;
		ForgeDirection[] arr$ = ForgeDirection.VALID_DIRECTIONS;
		int len$ = arr$.length;

		for (int i$ = 0; i$ < len$; ++i$)
		{
			ForgeDirection dir = arr$[i$];
			if (world.isAirBlock(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ))
				++count;
		}

		return count;
	}

	public static boolean isBlockExposed(World world, int x, int y, int z)
	{
		return !world.getBlock(x, y, z + 1).isOpaqueCube() || !world.getBlock(x, y, z - 1).isOpaqueCube() || !world.getBlock(x + 1, y, z).isOpaqueCube() || !world.getBlock(x - 1, y, z).isOpaqueCube() || !world.getBlock(x, y + 1, z).isOpaqueCube() || !world.getBlock(x, y - 1, z).isOpaqueCube();
	}

	public static boolean isAdjacentToSolidBlock(World world, int x, int y, int z)
	{
		for (int a = 0; a < 6; ++a)
		{
			ForgeDirection d = ForgeDirection.getOrientation(a);
			if (world.isSideSolid(x + d.offsetX, y + d.offsetY, z + d.offsetZ, d.getOpposite()))
				return true;
		}

		return false;
	}

	public static boolean isBlockTouching(IBlockAccess world, int x, int y, int z, Block id)
	{
		return world.getBlock(x, y, z + 1) == id || world.getBlock(x, y, z - 1) == id || world.getBlock(x + 1, y, z) == id || world.getBlock(x - 1, y, z) == id || world.getBlock(x, y + 1, z) == id || world.getBlock(x, y - 1, z) == id;
	}

	public static boolean isBlockTouching(IBlockAccess world, int x, int y, int z, Block id, int md)
	{
		return world.getBlock(x, y, z + 1) == id && world.getBlockMetadata(x, y, z + 1) == md || world.getBlock(x, y, z - 1) == id && world.getBlockMetadata(x, y, z - 1) == md || world.getBlock(x + 1, y, z) == id && world.getBlockMetadata(x + 1, y, z) == md || world.getBlock(x - 1, y, z) == id && world.getBlockMetadata(x - 1, y, z) == md || world.getBlock(x, y + 1, z) == id && world.getBlockMetadata(x, y + 1, z) == md || world.getBlock(x, y - 1, z) == id && world.getBlockMetadata(x, y - 1, z) == md;
	}

	public static boolean isBlockTouchingOnSide(IBlockAccess world, int x, int y, int z, Block id, int md, int side)
	{
		if ((side <= 3 || world.getBlock(x, y, z + 1) != id || world.getBlockMetadata(x, y, z + 1) != md) && (side <= 3 || world.getBlock(x, y, z - 1) != id || world.getBlockMetadata(x, y, z - 1) != md) && (side <= 1 || side >= 4 || world.getBlock(x + 1, y, z) != id || world.getBlockMetadata(x + 1, y, z) != md) && (side <= 1 || side >= 4 || world.getBlock(x - 1, y, z) != id || world.getBlockMetadata(x - 1, y, z) != md) && (side <= 1 || world.getBlock(x, y + 1, z) != id || world.getBlockMetadata(x, y + 1, z) != md) && (side <= 1 || world.getBlock(x, y - 1, z) != id || world.getBlockMetadata(x, y - 1, z) != md))
		{
			if ((side <= 3 || world.getBlock(x, y + 1, z + 1) != id || world.getBlockMetadata(x, y + 1, z + 1) != md) && (side <= 3 || world.getBlock(x, y + 1, z - 1) != id || world.getBlockMetadata(x, y + 1, z - 1) != md) && (side <= 1 || side >= 4 || world.getBlock(x + 1, y + 1, z) != id || world.getBlockMetadata(x + 1, y + 1, z) != md) && (side <= 1 || side >= 4 || world.getBlock(x - 1, y + 1, z) != id || world.getBlockMetadata(x - 1, y + 1, z) != md))
			{
				if ((side <= 3 || world.getBlock(x, y - 1, z + 1) != id || world.getBlockMetadata(x, y - 1, z + 1) != md) && (side <= 3 || world.getBlock(x, y - 1, z - 1) != id || world.getBlockMetadata(x, y - 1, z - 1) != md) && (side <= 1 || side >= 4 || world.getBlock(x + 1, y - 1, z) != id || world.getBlockMetadata(x + 1, y - 1, z) != md) && (side <= 1 || side >= 4 || world.getBlock(x - 1, y - 1, z) != id || world.getBlockMetadata(x - 1, y - 1, z) != md))
				{
					switch (side)
					{
						case 0:
							if (world.getBlock(x, y - 1, z) == id && world.getBlockMetadata(x, y - 1, z) == md)
								return true;
							break;
						case 1:
							if (world.getBlock(x, y + 1, z) == id && world.getBlockMetadata(x, y + 1, z) == md)
								return true;
					}

					return false;
				}
				return true;
			}
			return true;
		}
		return true;
	}
}
