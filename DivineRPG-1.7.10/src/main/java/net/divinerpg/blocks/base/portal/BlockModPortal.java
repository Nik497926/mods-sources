package net.divinerpg.blocks.base.portal;

import com.gamerforea.divinerpg.EventConfig;
import com.gamerforea.divinerpg.ModUtils;
import com.google.common.collect.ImmutableSet;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.divinerpg.blocks.base.BlockModFire;
import net.divinerpg.dimensions.base.DivineTeleporter;
import net.divinerpg.entities.fx.EntityEdenPortalFX;
import net.divinerpg.entities.fx.EntityMortumPortalFX;
import net.divinerpg.entities.fx.EntitySkythernPortalFX;
import net.divinerpg.entities.fx.EntityWildwoodPortalFX;
import net.divinerpg.libs.DivineRPGAchievements;
import net.divinerpg.libs.Reference;
import net.divinerpg.utils.LangRegistry;
import net.divinerpg.utils.LogHelper;
import net.divinerpg.utils.blocks.IceikaBlocks;
import net.divinerpg.utils.blocks.TwilightBlocks;
import net.divinerpg.utils.config.ConfigurationHelper;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.EntityPortalFX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import java.util.Random;
import java.util.Set;

public class BlockModPortal extends BlockBreakable
{

	public static final int[][] sides = { new int[0], { 3, 1 }, { 2, 0 } };
	protected String name;
	protected int dimensionID;
	protected Block fireBlock, blockFrame;

	public BlockModPortal(String name, int dimensionID, Block fireBlock, Block blockFrame)
	{
		super(Reference.PREFIX + name, Material.portal, false);
		this.name = name;
		this.dimensionID = dimensionID;
		this.fireBlock = fireBlock;
		this.blockFrame = blockFrame;
		this.setBlockName(name);
		this.setBlockTextureName(Reference.PREFIX + name);
		if (Reference.DEBUG)
			this.setCreativeTab(DivineRPGTabs.blocks);
		else
			this.setCreativeTab(null);
		this.setTickRandomly(true);
		GameRegistry.registerBlock(this, name);
		LangRegistry.addBlock(this);
		this.setBlockUnbreakable();
		((BlockModFire) fireBlock).addPortal(this);
		this.setLightLevel(0.8f);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		if (entity.ridingEntity == null && entity.riddenByEntity == null)
			if (entity instanceof EntityPlayerMP)
			{
				EntityPlayerMP player = (EntityPlayerMP) entity;

				// TODO gamerforEA code start
				player.addExperienceLevel(0);
				// TODO gamerforEA code end

				if (player.timeUntilPortal > 0)
					player.timeUntilPortal = 10;
				else
				{
					// TODO gamerforEA code start
					if (EventConfig.portalOwnerOnly && !ModUtils.isMember(player, x, y, z))
						return;
					// TODO gamerforEA code end

					if (player.dimension != this.dimensionID)
					{
						player.timeUntilPortal = 10;
						player.mcServer.getConfigurationManager().transferPlayerToDimension(player, this.dimensionID, new DivineTeleporter(player.mcServer.worldServerForDimension(this.dimensionID), this.dimensionID, this, this.blockFrame));
						if (player.dimension == ConfigurationHelper.iceika)
							player.triggerAchievement(DivineRPGAchievements.frozenLand);

						if (player.dimension == ConfigurationHelper.mortum)
							player.triggerAchievement(DivineRPGAchievements.darkAnotherDay);

						if (player.dimension == ConfigurationHelper.eden)
							player.triggerAchievement(DivineRPGAchievements.possibilities);
					}
					else
					{
						player.timeUntilPortal = 10;
						player.mcServer.getConfigurationManager().transferPlayerToDimension(player, 0, new DivineTeleporter(player.mcServer.worldServerForDimension(0), 0, this, this.blockFrame));
					}
				}
			}
			else if (entity.dimension != this.dimensionID)
				entity.timeUntilPortal = 10;
				//sendEntityToDimension(entity, this.dimensionID, new DivineTeleporter(MinecraftServer.getServer().worldServerForDimension(this.dimensionID), this.dimensionID, this, this.blockFrame));
			else
				entity.timeUntilPortal = 10;
		//sendEntityToDimension(entity, 0, new DivineTeleporter(MinecraftServer.getServer().worldServerForDimension(0), 0, this, this.blockFrame));
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random rand)
	{
		super.updateTick(world, x, y, z, rand);

		if (world.provider.isSurfaceWorld() && world.getGameRules().getGameRuleBooleanValue("doMobSpawning") && rand.nextInt(2000) < world.difficultySetting.getDifficultyId())
		{
			int l = y;
			while (!World.doesBlockHaveSolidTopSurface(world, x, y, z) && l > 0)
			{
				l--;
			}

			if (l > 0 && !world.getBlock(x, l + 1, z).isNormalCube())
			{
				Entity entity = ItemMonsterPlacer.spawnCreature(world, 57, x + 0.5D, l + 1.1D, z + 0.5D);

				if (entity != null)
					entity.timeUntilPortal = entity.getPortalCooldown();
			}
		}
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
	{
		return null;
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess blockAccess, int xPos, int yPos, int zPos)
	{
		int meta = this.getMeta(blockAccess.getBlockMetadata(xPos, yPos, zPos));

		if (meta == 0)
		{
			if (blockAccess.getBlock(xPos - 1, yPos, zPos) != this && blockAccess.getBlock(xPos + 1, yPos, zPos) != this)
				meta = 2;
			else
				meta = 1;

			if (blockAccess instanceof World && !((World) blockAccess).isRemote)
				((World) blockAccess).setBlockMetadataWithNotify(xPos, yPos, zPos, meta, 2);
		}

		float f = 0.125F;
		float f1 = 0.125F;

		if (meta == 1)
			f = 0.5F;

		if (meta == 2)
			f1 = 0.5F;

		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f1, 0.5F + f, 1.0F, 0.5F + f1);
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
	{
		byte size1 = 0;
		byte size2 = 1;

		if (world.getBlock(x - 1, y, z) == this || world.getBlock(x + 1, y, z) == this)
		{
			size1 = 1;
			size2 = 0;
		}

		int i1;

		for (i1 = y; world.getBlock(x, i1 - 1, z) == this; --i1)
		{
		}

		// TODO gamerforEA code start
		Set<Block> frames = EventConfig.portalStoneFrame ? ImmutableSet.of(Blocks.stone, this.blockFrame) : ImmutableSet.of(this.blockFrame);
		// TODO gamerforEA code end

		// TODO gamerforEA code replace, old code: if (world.getBlock(x, i1 - 1, z) != this.blockFrame)
		if (!frames.contains(world.getBlock(x, i1 - 1, z)))
			// TODO gamerforEA code end
			world.setBlockToAir(x, y, z);
		else
		{
			int j1;

			for (j1 = 1; j1 < 4 && world.getBlock(x, i1 + j1, z) == this; ++j1)
			{
			}

			// TODO gamerforEA code replace, old code: if (j1 == 3 && world.getBlock(x, i1 + j1, z) == this.blockFrame)
			if (j1 == 3 && frames.contains(world.getBlock(x, i1 + j1, z)))
			// TODO gamerforEA code end
			{
				boolean flag = world.getBlock(x - 1, y, z) == this || world.getBlock(x + 1, y, z) == this;
				boolean flag1 = world.getBlock(x, y, z - 1) == this || world.getBlock(x, y, z + 1) == this;

				if (flag && flag1)
					world.setBlockToAir(x, y, z);
				else
				{
					Block b1 = world.getBlock(x + size1, y, z + size2);
					Block b2 = world.getBlock(x - size1, y, z - size2);

					// TODO gamerforEA code replace, old code: if ((b1 != this.blockFrame || b2 != this) && (b2 != this.blockFrame || b1 != this))
					if ((!frames.contains(b1) || b2 != this) && (!frames.contains(b2) || b1 != this))
						// TODO gamerforEA code end
						world.setBlockToAir(x, y, z);
				}
			}
			else
				world.setBlockToAir(x, y, z);
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public boolean shouldSideBeRendered(IBlockAccess blockAccess, int xPos, int yPos, int zPos, int side)
	{
		int i1 = 0;

		if (blockAccess.getBlock(xPos, yPos, zPos) == this)
		{
			i1 = this.getMeta(blockAccess.getBlockMetadata(xPos, yPos, zPos));
			if (i1 == 0 || i1 == 1 && side != 3 && side != 2 || i1 == 2 && side != 5 && side != 4)
				return false;
		}

		boolean flag = blockAccess.getBlock(xPos - 1, yPos, zPos) == this && blockAccess.getBlock(xPos - 2, yPos, zPos) != this;
		boolean flag1 = blockAccess.getBlock(xPos + 1, yPos, zPos) == this && blockAccess.getBlock(xPos + 2, yPos, zPos) != this;
		boolean flag2 = blockAccess.getBlock(xPos, yPos, zPos - 1) == this && blockAccess.getBlock(xPos, yPos, zPos - 2) != this;
		boolean flag3 = blockAccess.getBlock(xPos, yPos, zPos + 1) == this && blockAccess.getBlock(xPos, yPos, zPos + 2) != this;
		boolean flag4 = flag || flag1 || i1 == 1;
		boolean flag5 = flag2 || flag3 || i1 == 2;
		return flag4 && side == 4 || flag4 && side == 5 || flag5 && side == 2 || flag5 && side == 3;
	}

	@Override
	public int quantityDropped(Random rand)
	{
		return 0;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public int getRenderBlockPass()
	{
		return 1;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void randomDisplayTick(World world, int xPos, int yPos, int zPos, Random rand)
	{
		for (int l = 0; l < 4; ++l)
		{
			double d0 = xPos + rand.nextFloat();
			double d1 = yPos + rand.nextFloat();
			double d2 = zPos + rand.nextFloat();
			double d3 = 0.0D;
			double d4 = 0.0D;
			double d5 = 0.0D;
			int i1 = rand.nextInt(2) * 2 - 1;
			d3 = (rand.nextFloat() - 0.5D) * 0.5D;
			d4 = (rand.nextFloat() - 0.5D) * 0.5D;
			d5 = (rand.nextFloat() - 0.5D) * 0.5D;

			if (world.getBlock(xPos - 1, yPos, zPos) != this && world.getBlock(xPos + 1, yPos, zPos) != this)
			{
				d0 = xPos + 0.5D + 0.25D * i1;
				d3 = rand.nextFloat() * 2.0F * i1;
			}
			else
			{
				d2 = zPos + 0.5D + 0.25D * i1;
				d5 = rand.nextFloat() * 2.0F * i1;
			}
			EntityFX var20 = this == TwilightBlocks.edenPortal ? new EntityEdenPortalFX(world, d0, d1, d2, d3, d4, d5) : this == TwilightBlocks.wildwoodPortal ? new EntityWildwoodPortalFX(world, d0, d1, d2, d3, d4, d5) : this == TwilightBlocks.apalachiaPortal ? new EntityPortalFX(world, d0, d1, d2, d3, d4, d5) : this == TwilightBlocks.skythernPortal ? new EntitySkythernPortalFX(world, d0, d1, d2, d3, d4, d5) : this == TwilightBlocks.mortumPortal ? new EntityMortumPortalFX(world, d0, d1, d2, d3, d4, d5) : this == IceikaBlocks.iceikaPortal ? new EntitySkythernPortalFX(world, d0, d1, d2, d3, d4, d5) : null;
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(var20);
		}
	}

	public int getMeta(int meta)
	{
		return meta & 3;
	}

	public boolean tryCreatePortal(World world, int x, int y, int z)
	{
		LogHelper.debug("Trying to create portal");
		byte size1 = 0;
		byte size2 = 0;

		if (world.getBlock(x - 1, y, z) == this.blockFrame || world.getBlock(x + 1, y, z) == this.blockFrame)
			size1 = 1;
		if (world.getBlock(x, y, z - 1) == this.blockFrame || world.getBlock(x, y, z + 1) == this.blockFrame)
			size2 = 1;
		if (size1 == size2)
			return false;
		if (world.isAirBlock(x - size1, y, z - size2))
		{
			x -= size1;
			z -= size2;
		}

		for (int i = -1; i <= 2; i++)
		{
			for (int j = -1; j <= 3; j++)
			{
				boolean flag = i == -1 || i == 2 || j == -1 || j == 3;
				if (i != -1 && i != 2 || j != -1 && j != 3)
				{
					Block b1 = world.getBlock(x + size1 * i, y + j, z + size2 * i);
					boolean isAir = world.isAirBlock(x + size1 * i, y + j, z + size2 * i);
					if (flag)
					{
						if (b1 != this.blockFrame)
							return false;
					}
					else if (!isAir && b1 != this.fireBlock)
						return false;
				}
			}
		}

		LogHelper.debug("Creating Portal");
		for (int i = 0; i < 2; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				world.setBlock(x + size1 * i, y + j, z + size2 * i, this, 0, 2);
			}
		}

		return true;
		//        PortalSize size = new PortalSize(world, x, y, z, 1, this, fireBlock, blockFrame);
		//        PortalSize size1 = new PortalSize(world, x, y, z, 2, this, fireBlock, blockFrame);
		//        LogHelper.debug("Size value: " + size.value);
		//        LogHelper.debug("Size1 value: " + size.value);
		//        LogHelper.debug("Size isInChunk: " + size.isInChunk());
		//        LogHelper.debug("Size1 isInChunk: " + size.isInChunk());
		//        if (size.isInChunk() && (size.value == 0)) {
		//            size.setPortalSize();
		//            LogHelper.debug("Portal created succesfully");
		//            return true;
		//        }
		//        if (size1.isInChunk() && size1.value == 0) {
		//            size1.setPortalSize();
		//            LogHelper.debug("Portal created succesfully");
		//            return true;
		//        }
		//        LogHelper.debug("Portal failed to create");
		//        return false;
	}

	public static void sendEntityToDimension(Entity entity, int dimId, Teleporter tp)
	{
		if (!entity.worldObj.isRemote && !entity.isDead)
		{
			entity.worldObj.theProfiler.startSection("changeDimension");
			MinecraftServer minecraftserver = MinecraftServer.getServer();
			int j = entity.dimension;
			WorldServer worldserver = minecraftserver.worldServerForDimension(j);
			WorldServer worldserver1 = minecraftserver.worldServerForDimension(dimId);
			entity.dimension = dimId;

			if (j == dimId && dimId != 0)
			{
				worldserver1 = minecraftserver.worldServerForDimension(0);
				entity.dimension = 0;
			}

			entity.worldObj.removeEntity(entity);
			entity.isDead = false;
			minecraftserver.getConfigurationManager().transferEntityToWorld(entity, j, worldserver, worldserver1, tp);
			Entity newEntity = EntityList.createEntityByName(EntityList.getEntityString(entity), worldserver1);

			if (newEntity != null)
			{
				newEntity.copyDataFrom(entity, true);

				if (j == dimId && dimId != 0)
				{
					ChunkCoordinates chunkcoordinates = worldserver1.getSpawnPoint();
					chunkcoordinates.posY = entity.worldObj.getTopSolidOrLiquidBlock(chunkcoordinates.posX, chunkcoordinates.posZ);
					newEntity.setLocationAndAngles(chunkcoordinates.posX, chunkcoordinates.posY, (double) chunkcoordinates.posZ + 10, newEntity.rotationYaw, newEntity.rotationPitch);
				}

				worldserver1.spawnEntityInWorld(newEntity);
			}

			entity.isDead = true;
			entity.worldObj.theProfiler.endSection();
			worldserver.resetUpdateEntityTick();
			worldserver1.resetUpdateEntityTick();
			entity.worldObj.theProfiler.endSection();
		}
	}

}
