package com.gamerforea.thaumcraft;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.ChunkEvent;
import thaumcraft.common.blocks.BlockMetalDevice;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.items.ItemEldritchObject;
import thaumcraft.common.tiles.TileVisRelay;

import java.util.HashSet;
import java.util.Set;

public final class FixHandler
{
	public static void init()
	{
		FixHandler handler = new FixHandler();
		FMLCommonHandler.instance().bus().register(handler);
		MinecraftForge.EVENT_BUS.register(handler);
	}

	@SubscribeEvent
	public void onBlockBreak(BlockEvent.BreakEvent event)
	{
		if (EventConfig.preventOuterLandsDestruction && event.world.provider.dimensionId == Config.dimensionOuterId && event.block == ConfigBlocks.blockEldritchNothing)
			event.setCanceled(true);
	}

	@SubscribeEvent
	public void onFill(FillBucketEvent event)
	{
		if (EventConfig.advCrucibleDupeFix)
		{
			World world = event.world;
			if (!world.isRemote)
			{
				int x = event.target.blockX;
				int y = event.target.blockY;
				int z = event.target.blockZ;
				Block block = world.getBlock(x, y, z);
				if (block instanceof BlockMetalDevice)
				{
					int meta = world.getBlockMetadata(x, y, z);
					if (meta == 0)
						event.setCanceled(true);
				}
			}
		}
	}

	@SubscribeEvent
	public void onPlayerTick(TickEvent.PlayerTickEvent event)
	{
		if (EventConfig.obeliskPlacerCreativeOnly)
		{
			EntityPlayer player = event.player;
			if (!player.worldObj.isRemote && !player.capabilities.isCreativeMode)
			{
				InventoryPlayer inventoryPlayer = player.inventory;
				ItemStack stack = inventoryPlayer.getItemStack();
				if (stack != null)
				{
					Item item = stack.getItem();
					if (item instanceof ItemEldritchObject && stack.getItemDamage() == 4)
						inventoryPlayer.setItemStack(null);
				}
			}
		}
	}

	@SubscribeEvent
	public void onItemToss(ItemTossEvent event)
	{
		if (EventConfig.obeliskPlacerCreativeOnly)
		{
			EntityPlayer player = event.player;
			if (!player.worldObj.isRemote && !player.capabilities.isCreativeMode)
			{
				ItemStack stack = event.entityItem.getEntityItem();
				if (stack != null)
				{
					Item item = stack.getItem();
					if (item instanceof ItemEldritchObject && stack.getItemDamage() == 4)
						event.setCanceled(true);
				}
			}
		}
	}

	@SubscribeEvent
	public void onChunkLoad(ChunkEvent.Load event)
	{
		if (EventConfig.updateVisRelayNetworkOnChunkLoad)
		{
			Chunk chunk = event.getChunk();
			forceUpdateVis(chunk, new HashSet<>());
		}
	}

	private static void forceUpdateVis(Chunk chunk, Set<ChunkCoordIntPair> visitedChunks)
	{
		if (chunk == null)
			return;

		if (!visitedChunks.add(chunk.getChunkCoordIntPair()))
			return;

		boolean hasRelay = false;

		World world = chunk.worldObj;
		for (int y = 0, height = world.getHeight(); y < height; y++)
		{
			for (int xx = 0; xx < 16; xx++)
			{
				for (int zz = 0; zz < 16; zz++)
				{
					TileEntity tile = chunk.getTileEntityUnsafe(xx, y, zz);
					if (tile instanceof TileVisRelay)
					{
						((TileVisRelay) tile).reset();
						hasRelay = true;
					}
				}
			}
		}

		if (hasRelay)
		{
			IChunkProvider chunkProvider = world.getChunkProvider();
			for (int xOffset = -1; xOffset <= 1; xOffset++)
			{
				for (int zOffset = -1; zOffset <= 1; zOffset++)
				{
					if (xOffset != 0 || zOffset != 0)
					{
						int chunkXX = chunk.xPosition + xOffset;
						int chunkZZ = chunk.zPosition + zOffset;
						if (chunkProvider.chunkExists(chunkXX, chunkZZ))
							forceUpdateVis(chunkProvider.provideChunk(chunkXX, chunkZZ), visitedChunks);
					}
				}
			}
		}
	}
}
