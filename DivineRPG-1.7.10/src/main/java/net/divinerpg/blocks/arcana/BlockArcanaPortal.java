package net.divinerpg.blocks.arcana;

import com.gamerforea.divinerpg.EventConfig;
import com.gamerforea.divinerpg.ModUtils;
import com.google.common.collect.ImmutableSet;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.divinerpg.blocks.base.BlockMod;
import net.divinerpg.dimensions.arcana.TeleporterArcana;
import net.divinerpg.libs.DivineRPGAchievements;
import net.divinerpg.utils.blocks.ArcanaBlocks;
import net.divinerpg.utils.config.ConfigurationHelper;
import net.divinerpg.utils.material.EnumBlockType;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;
import java.util.Set;

public class BlockArcanaPortal extends BlockMod
{
	private int firetick;
	private int firemax = 200;

	public BlockArcanaPortal(String name)
	{
		super(EnumBlockType.PORTAL, name, 5.0F);
		this.setLightLevel(1.0F);
		this.setBlockUnbreakable();
		this.setResistance(6000000F);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean isReplaceable(IBlockAccess w, int x, int y, int z)
	{
		return false;
	}

	@Override
	public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB mask, List list, Entity collidingEntity)
	{
	}

	@Override
	public int quantityDropped(Random par1Random)
	{
		return 0;
	}

	@Override
	public void dropBlockAsItemWithChance(World worldIn, int x, int y, int z, int meta, float chance, int fortune)
	{

	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		if (entity.ridingEntity == null && entity.riddenByEntity == null && entity instanceof EntityPlayerMP)
		{
			EntityPlayerMP player = (EntityPlayerMP) entity;
			if (player.timeUntilPortal > 0)
				player.timeUntilPortal = 10;
			else
			{
				// TODO gamerforEA code start
				if (EventConfig.portalOwnerOnly && !ModUtils.isMember(player, x, y, z))
					return;
				// TODO gamerforEA code end

				if (player.dimension != ConfigurationHelper.arcana)
				{
					player.timeUntilPortal = 10;
					player.mcServer.getConfigurationManager().transferPlayerToDimension(player, ConfigurationHelper.arcana, new TeleporterArcana(player.mcServer.worldServerForDimension(ConfigurationHelper.arcana)));
					player.triggerAchievement(DivineRPGAchievements.whatLiesWithin);
				}
				else
				{
					player.timeUntilPortal = 10;
					player.mcServer.getConfigurationManager().transferPlayerToDimension(player, 0, new TeleporterArcana(player.mcServer.worldServerForDimension(0)));
				}
			}
		}
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
	{
		int startX = x;
		int startZ = z;

		// TODO gamerforEA code replace, old code: if (block == ArcanaBlocks.arcanaPortalFrame)
		Set<Block> frames = EventConfig.portalStoneFrame ? ImmutableSet.of(Blocks.stone, ArcanaBlocks.arcanaPortalFrame) : ImmutableSet.of(ArcanaBlocks.arcanaPortalFrame);
		if (frames.contains(block))
		// TODO gamerforEA code end
		{
			/* Find upper left hand corner of portal */
			while (world.getBlock(startX - 1, y, startZ) == this)
			{
				startX--;
			}
			while (world.getBlock(startX, y, startZ - 1) == this)
			{
				startZ--;
			}

			/* Replace portal blocks with air */
			for (int scanZ = startZ; scanZ < startZ + 3; scanZ++)
			{
				for (int scanX = startX; scanX < startX + 3; scanX++)
				{
					if (world.getBlock(scanX, y, scanZ) == this)
						world.setBlockToAir(scanX, y, scanZ);
				}
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random rand)
	{
		double distanceX = x + rand.nextFloat();
		double distanceY = y + 0.8F;
		double distanceZ = z + rand.nextFloat();
		world.spawnParticle("smoke", distanceX, distanceY, distanceZ, 0, 0, 0);
	}
}
