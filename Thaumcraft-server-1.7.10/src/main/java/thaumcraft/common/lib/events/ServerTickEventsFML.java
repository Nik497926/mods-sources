package thaumcraft.common.lib.events;

import com.gamerforea.eventhelper.util.EventUtils;
import com.gamerforea.eventhelper.util.FastUtils;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.WorldTickEvent;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
import org.apache.logging.log4j.Level;
import thaumcraft.api.wands.FocusUpgradeType;
import thaumcraft.api.wands.ItemFocusBasic;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.fx.PacketFXBlockSparkle;
import thaumcraft.common.lib.utils.BlockUtils;
import thaumcraft.common.lib.utils.InventoryUtils;
import thaumcraft.common.lib.world.ChunkLoc;
import thaumcraft.common.tiles.TileSensor;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class ServerTickEventsFML
{
	public static Map<Integer, LinkedBlockingQueue<VirtualSwapper>> swapList = new HashMap<>();
	public static HashMap<Integer, ArrayList<ChunkLoc>> chunksToGenerate = new HashMap<>();

	@SubscribeEvent
	public void serverWorldTick(WorldTickEvent event)
	{
		if (event.side != Side.CLIENT && event.phase != Phase.START)
		{
			this.tickChunkRegeneration(event);
			this.tickBlockSwap(event.world);
			if (TileSensor.noteBlockEvents.get(event.world) != null)
				TileSensor.noteBlockEvents.get(event.world).clear();
		}
	}

	public void tickChunkRegeneration(WorldTickEvent event)
	{
		int dim = event.world.provider.dimensionId;
		ArrayList<ChunkLoc> chunks = chunksToGenerate.get(dim);
		if (chunks != null && chunks.size() > 0)
		{
			int count = 0;
			for (int a = 0; a < 10; ++a)
			{
				chunks = chunksToGenerate.get(dim);
				if (chunks == null || chunks.size() <= 0)
					break;

				++count;
				ChunkLoc loc = chunks.get(0);
				long worldSeed = event.world.getSeed();
				Random fmlRandom = new Random(worldSeed);
				long xSeed = fmlRandom.nextLong() >> 3;
				long zSeed = fmlRandom.nextLong() >> 3;
				fmlRandom.setSeed(xSeed * loc.chunkXPos + zSeed * loc.chunkZPos ^ worldSeed);
				Thaumcraft.instance.worldGen.worldGeneration(fmlRandom, loc.chunkXPos, loc.chunkZPos, event.world, false);
				chunks.remove(0);
				chunksToGenerate.put(dim, chunks);
			}
			if (count > 0)
				FMLCommonHandler.instance().getFMLLogger().log(Level.INFO, "[Thaumcraft] Regenerated " + count + " chunks. " + Math.max(0, chunks.size()) + " chunks left");
		}
	}

	private void tickBlockSwap(World world)
	{
		int dim = world.provider.dimensionId;
		LinkedBlockingQueue<VirtualSwapper> queue = swapList.get(dim);
		if (queue != null)
		{
			boolean didSomething = false;

			while (!didSomething)
			{
				VirtualSwapper vs = queue.poll();
				if (vs != null)
				{
					ItemStack targetStack = vs.target;
					Item targetItem = targetStack.getItem();
					Block targetBlock = Block.getBlockFromItem(targetItem);
					EntityPlayer player = vs.player;

					// TODO gamerforEA code start
					if (targetBlock.getClass().getName().contains("BlockArmorStand"))
						continue;
					if (!FastUtils.isOnline(player) || EventUtils.cantBreak(player, vs.x, vs.y, vs.z))
						continue;
					// TODO gamerforEA code end

					ItemWandCasting wand = null;
					ItemFocusBasic focus = null;
					ItemStack focusStack = null;
					ItemStack wandStack = player.inventory.getStackInSlot(vs.wand);
					if (wandStack != null && wandStack.getItem() instanceof ItemWandCasting)
					{
						wand = (ItemWandCasting) wandStack.getItem();
						focusStack = wand.getFocusItem(wandStack);
						focus = wand.getFocus(wandStack);
					}

					if (wand != null && focus != null && world.canMineBlock(player, vs.x, vs.y, vs.z))
					{
						Block block = world.getBlock(vs.x, vs.y, vs.z);
						int meta = world.getBlockMetadata(vs.x, vs.y, vs.z);
						if (!targetStack.isItemEqual(new ItemStack(block, 1, meta)) && !ForgeEventFactory.onPlayerInteract(player, Action.RIGHT_CLICK_BLOCK, vs.x, vs.y, vs.z, 1, world).isCanceled() && wand.consumeAllVis(wandStack, player, focus.getVisCost(focusStack), false, false))
						{
							int slot = InventoryUtils.isPlayerCarrying(player, targetStack);
							if (player.capabilities.isCreativeMode)
								slot = 1;

							if (vs.bSource == block && vs.mSource == meta && slot >= 0)
							{
								didSomething = true;
								if (!player.capabilities.isCreativeMode)
								{
									int fortune = wand.getFocusTreasure(wandStack);
									boolean silktouch = focus.isUpgradedWith(focusStack, FocusUpgradeType.silktouch);
									player.inventory.decrStackSize(slot, 1);
									List<ItemStack> drops = new ArrayList<>();
									if (silktouch && block.canSilkHarvest(world, player, vs.x, vs.y, vs.z, meta))
									{
										ItemStack stack = BlockUtils.createStackedBlock(block, meta);
										if (stack != null)
											drops.add(stack);
									}
									else
										drops = block.getDrops(world, vs.x, vs.y, vs.z, meta, fortune);

									if (drops.size() > 0)
										for (ItemStack stack : drops)
										{
											if (!player.inventory.addItemStackToInventory(stack))
												world.spawnEntityInWorld(new EntityItem(world, vs.x + 0.5D, vs.y + 0.5D, vs.z + 0.5D, stack));
										}

									wand.consumeAllVis(wandStack, player, focus.getVisCost(focusStack), true, false);
								}

								world.setBlock(vs.x, vs.y, vs.z, targetBlock, targetStack.getItemDamage(), 3);
								targetBlock.onBlockPlacedBy(world, vs.x, vs.y, vs.z, player, targetStack);
								PacketHandler.INSTANCE.sendToAllAround(new PacketFXBlockSparkle(vs.x, vs.y, vs.z, 12632319), new TargetPoint(world.provider.dimensionId, vs.x, vs.y, vs.z, 32.0D));
								world.playAuxSFX(2001, vs.x, vs.y, vs.z, Block.getIdFromBlock(vs.bSource) + (vs.mSource << 12));
								if (vs.lifespan > 0)
									for (int xOffset = -1; xOffset <= 1; ++xOffset)
									{
										for (int yOffset = -1; yOffset <= 1; ++yOffset)
										{
											for (int zOffset = -1; zOffset <= 1; ++zOffset)
											{
												if (xOffset != 0 || yOffset != 0 || zOffset != 0)
												{
													int xx = vs.x + xOffset;
													int yy = vs.y + yOffset;
													int zz = vs.z + zOffset;
													if (world.getBlock(xx, yy, zz) == vs.bSource && world.getBlockMetadata(xx, yy, zz) == vs.mSource && BlockUtils.isBlockExposed(world, xx, yy, zz))
														queue.offer(new VirtualSwapper(xx, yy, zz, vs.bSource, vs.mSource, targetStack, vs.lifespan - 1, player, vs.wand));
												}
											}
										}
									}
							}
						}
					}
				}
				else
					didSomething = true;
			}

			swapList.put(dim, queue);
		}
	}

	public static void addSwapper(World world, int x, int y, int z, Block bs, int ms, ItemStack target, int life, EntityPlayer player, int wand)
	{
		int dim = world.provider.dimensionId;
		if (bs != Blocks.air && bs.getBlockHardness(world, x, y, z) >= 0.0F && !target.isItemEqual(new ItemStack(bs, 1, ms)))
		{
			LinkedBlockingQueue<VirtualSwapper> queue = swapList.get(dim);
			if (queue == null)
			{
				swapList.put(dim, new LinkedBlockingQueue<>());
				queue = swapList.get(dim);
			}

			queue.offer(new VirtualSwapper(x, y, z, bs, ms, target, life, player, wand));
			world.playSoundAtEntity(player, "thaumcraft:wand", 0.25F, 1.0F);
			swapList.put(dim, queue);
		}
	}

	public static class RestorableWardedBlock
	{
		int x = 0;
		int y = 0;
		int z = 0;
		Block bi;
		int md = 0;
		NBTTagCompound nbt = null;

		RestorableWardedBlock(World world, int x, int y, int z)
		{
			this.x = x;
			this.y = y;
			this.z = z;
			this.bi = world.getBlock(x, y, z);
			this.md = world.getBlockMetadata(x, y, z);
			TileEntity tile = world.getTileEntity(x, y, z);
			if (tile != null)
			{
				this.nbt = new NBTTagCompound();
				tile.writeToNBT(this.nbt);
			}
		}
	}

	public static class VirtualSwapper
	{
		int lifespan = 0;
		int x = 0;
		int y = 0;
		int z = 0;
		Block bSource;
		int mSource = 0;
		ItemStack target;
		int wand = 0;
		EntityPlayer player = null;

		VirtualSwapper(int x, int y, int z, Block bs, int ms, ItemStack t, int life, EntityPlayer p, int wand)
		{
			this.x = x;
			this.y = y;
			this.z = z;
			this.bSource = bs;
			this.mSource = ms;
			this.target = t;
			this.lifespan = life;
			this.player = p;
			this.wand = wand;
		}
	}
}
