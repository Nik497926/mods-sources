package thaumcraft.common.lib.research;

import com.gamerforea.thaumcraft.EventConfig;
import com.gamerforea.thaumcraft.ObjectTagsCache;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.GameRegistry.UniqueIdentifier;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.nodes.INode;
import thaumcraft.api.research.IScanEventHandler;
import thaumcraft.api.research.ScanResult;
import thaumcraft.client.lib.PlayerNotifications;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.Config;
import thaumcraft.common.entities.golems.EntityGolemBase;
import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.playerdata.PacketAspectDiscovery;
import thaumcraft.common.lib.network.playerdata.PacketAspectPool;
import thaumcraft.common.lib.utils.Utils;
import thaumcraft.common.tiles.TileNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ScanManager implements IScanEventHandler
{
	@Override
	public ScanResult scanPhenomena(ItemStack stack, World world, EntityPlayer player)
	{
		return null;
	}

	private static int generateEntityHash(Entity entity)
	{
		String hash = EntityList.getEntityString(entity);
		if (hash == null)
			hash = "generic";

		if (entity instanceof EntityPlayer)
			hash = "player_" + entity.getCommandSenderName();

		label61:
		for (ThaumcraftApi.EntityTags et : ThaumcraftApi.scanEntities)
		{
			if (et.entityName.equals(hash) && et.nbts != null && et.nbts.length != 0)
			{
				NBTTagCompound tc = new NBTTagCompound();
				entity.writeToNBT(tc);

				for (ThaumcraftApi.EntityTagsNBT nbt : et.nbts)
				{
					if (!tc.hasKey(nbt.name))
						continue label61;

					Object val = Utils.getNBTDataFromId(tc, tc.func_150299_b(nbt.name), nbt.name);
					Class c = val.getClass();

					try
					{
						if (!c.cast(val).equals(c.cast(nbt.value)))
							continue label61;
					}
					catch (Exception var13)
					{
						continue label61;
					}
				}

				for (ThaumcraftApi.EntityTagsNBT nbt : et.nbts)
				{
					Object val = Utils.getNBTDataFromId(tc, tc.func_150299_b(nbt.name), nbt.name);
					Class c = val.getClass();

					try
					{
						hash = hash + nbt.name + c.cast(nbt.value);
					}
					catch (Exception ignored)
					{
					}
				}
			}
		}

		if (entity instanceof EntityLivingBase)
		{
			EntityLivingBase le = (EntityLivingBase) entity;
			if (le.isChild())
				hash = hash + "CHILD";
		}

		if (entity instanceof EntityZombie && ((EntityZombie) entity).isVillager())
			hash = hash + "VILLAGER";

		if (entity instanceof EntityCreeper)
		{
			if (((EntityCreeper) entity).getCreeperState() == 1)
				hash = hash + "FLASHING";

			if (((EntityCreeper) entity).getPowered())
				hash = hash + "POWERED";
		}

		if (entity instanceof EntityGolemBase)
			hash = hash + "" + ((EntityGolemBase) entity).getGolemType().name();

		return hash.hashCode();
	}

	public static int generateItemHash(Item item, int meta)
	{
		ItemStack t = new ItemStack(item, 1, meta);

		try
		{
			if (t.isItemStackDamageable() || !t.getHasSubtypes())
				meta = -1;
		}
		catch (Exception ignored)
		{
		}

		/* TODO gamerforEA code replace, old code:
		if (ThaumcraftApi.groupedObjectTags.containsKey(Arrays.asList(item, meta)))
			meta = ThaumcraftApi.groupedObjectTags.get(Arrays.asList(item, meta))[0]; */
		int[] group = ThaumcraftApi.groupedObjectTags.get(Arrays.asList(item, meta));
		if (group != null)
			meta = group[0];
		// TODO gamerforEA code end

		String hash;
		try
		{
			UniqueIdentifier ui = GameRegistry.findUniqueIdentifierFor(item);
			if (ui != null)
				hash = ui.toString() + ":" + meta;
			else
				hash = t.getUnlocalizedName() + ":" + meta;
		}
		catch (Exception e)
		{
			hash = "oops:" + meta;
		}

		if (!ThaumcraftApi.objectTags.containsKey(Arrays.asList(item, meta)))
		{
			for (List key : ThaumcraftApi.objectTags.keySet())
			{
				String name = ((Item) key.get(0)).getUnlocalizedName();
				if ((Item.itemRegistry.getObject(name) == item || Block.blockRegistry.getObject(name) == Block.getBlockFromItem(item)) && key.get(1) instanceof int[])
				{
					int[] range = (int[]) key.get(1);
					Arrays.sort(range);
					if (Arrays.binarySearch(range, meta) >= 0)
					{
						UniqueIdentifier ui = GameRegistry.findUniqueIdentifierFor(item);
						if (ui != null)
							hash = ui.toString();
						else
							hash = "" + t.getUnlocalizedName();

						for (int r : range)
						{
							hash = hash + ":" + r;
						}

						return hash.hashCode();
					}
				}
			}

			// TODO gamerforEA code replace, old code:
			// if (!ThaumcraftApi.objectTags.containsKey(Arrays.asList(item, -1)) && meta == -1)
			boolean useCache = EventConfig.itemAspectMapOptimize;
			if (meta == -1 && (useCache ? ObjectTagsCache.INSTANCE.getAspectList(item, -1) == null : !ThaumcraftApi.objectTags.containsKey(Arrays.asList(item, -1))))
			// TODO gamerforEA code end
			{
				int index = 0;
				boolean found;

				do
				{
					found = ThaumcraftApi.objectTags.containsKey(Arrays.asList(item, index));
					++index;
				}
				while (index < 16 && !found);

				if (found)
				{
					UniqueIdentifier ui = GameRegistry.findUniqueIdentifierFor(item);
					if (ui != null)
						hash = ui.toString() + ":" + index;
					else
						hash = t.getUnlocalizedName() + ":" + index;
				}
			}
		}

		return hash.hashCode();
	}

	public static AspectList generateEntityAspects(Entity entity)
	{
		AspectList tags = null;
		String s = null;

		try
		{
			s = EntityList.getEntityString(entity);
		}
		catch (Throwable var11)
		{
			try
			{
				s = entity.getCommandSenderName();
			}
			catch (Throwable ignored)
			{
			}
		}

		if (s == null)
			s = "generic";

		if (entity instanceof EntityPlayer)
		{
			s = "player_" + entity.getCommandSenderName();
			tags = new AspectList();
			tags.add(Aspect.MAN, 4);
			if (entity.getCommandSenderName().equalsIgnoreCase("azanor"))
				tags.add(Aspect.ELDRITCH, 20);
			else if (entity.getCommandSenderName().equalsIgnoreCase("direwolf20"))
				tags.add(Aspect.BEAST, 20);
			else if (entity.getCommandSenderName().equalsIgnoreCase("pahimar"))
				tags.add(Aspect.EXCHANGE, 20);
			else
			{
				Random rand = new Random((long) s.hashCode());
				Aspect[] posa = Aspect.aspects.values().toArray(new Aspect[0]);
				tags.add(posa[rand.nextInt(posa.length)], 4);
				tags.add(posa[rand.nextInt(posa.length)], 4);
				tags.add(posa[rand.nextInt(posa.length)], 4);
			}
		}
		else
			label264:
					for (ThaumcraftApi.EntityTags et : ThaumcraftApi.scanEntities)
					{
						if (et.entityName.equals(s))
							if (et.nbts != null && et.nbts.length != 0)
							{
								NBTTagCompound tc = new NBTTagCompound();
								entity.writeToNBT(tc);

								for (ThaumcraftApi.EntityTagsNBT nbt : et.nbts)
								{
									if (!tc.hasKey(nbt.name) || !Utils.getNBTDataFromId(tc, tc.func_150299_b(nbt.name), nbt.name).equals(nbt.value))
										continue label264;
								}

								tags = et.aspects;
							}
							else
								tags = et.aspects;
					}

		return tags;
	}

	private static AspectList generateNodeAspects(World world, String node)
	{
		AspectList tags = new AspectList();
		ArrayList<Integer> loc = TileNode.locations.get(node);
		if (loc != null && loc.size() > 0)
		{
			int dim = loc.get(0);
			int x = loc.get(1);
			int y = loc.get(2);
			int z = loc.get(3);
			if (dim == world.provider.dimensionId)
			{
				TileEntity tnb = world.getTileEntity(x, y, z);
				if (tnb instanceof INode)
				{
					AspectList ta = ((INode) tnb).getAspects();

					for (Aspect a : ta.getAspectsSorted())
					{
						tags.merge(a, Math.max(4, ta.getAmount(a) / 10));
					}

					switch (((INode) tnb).getNodeType())
					{
						case UNSTABLE:
							tags.merge(Aspect.ENTROPY, 4);
							break;
						case HUNGRY:
							tags.merge(Aspect.HUNGER, 4);
							break;
						case TAINTED:
							tags.merge(Aspect.TAINT, 4);
							break;
						case PURE:
							tags.merge(Aspect.HEAL, 2);
							tags.add(Aspect.ORDER, 2);
							break;
						case DARK:
							tags.merge(Aspect.DEATH, 2);
							tags.add(Aspect.DARKNESS, 2);
					}
				}
			}
		}

		return tags.size() > 0 ? tags : null;
	}

	public static boolean isValidScanTarget(EntityPlayer player, ScanResult scan, String prefix)
	{
		if (scan == null)
			return false;
		if (prefix.equals("@") && !isValidScanTarget(player, scan, "#"))
			return false;
		if (scan.type == 1)
		{
			Item item = Item.getItemById(scan.id);

			/* TODO gamerforEA code replace, old code:
			if (ThaumcraftApi.groupedObjectTags.containsKey(Arrays.asList(item, scan.meta)))
				scan.meta = ThaumcraftApi.groupedObjectTags.get(Arrays.asList(item, scan.meta))[0]; */
			int[] group = ThaumcraftApi.groupedObjectTags.get(Arrays.asList(item, scan.meta));
			if (group != null)
				scan.meta = group[0];
			// TODO gamerforEA code end

			List<String> list = Thaumcraft.proxy.getScannedObjects().get(player.getCommandSenderName());
			return list == null || !list.contains(prefix + generateItemHash(item, scan.meta));
		}
		else if (scan.type == 2)
		{
			// TODO gamerforEA code start
			if (scan.entity == null)
				return false;
			// TODO gamerforEA code end

			if (scan.entity instanceof EntityItem)
			{
				EntityItem item = (EntityItem) scan.entity;

				/* TODO gamerforEA code replace, old code:
				ItemStack t = item.getEntityItem().copy();
				t.stackSize = 1;
				if (ThaumcraftApi.groupedObjectTags.containsKey(Arrays.asList(t.getItem(), t.getItemDamage())))
					t.setItemDamage(ThaumcraftApi.groupedObjectTags.get(Arrays.asList(t.getItem(), t.getItemDamage()))[0]);
				List<String> list = Thaumcraft.proxy.getScannedObjects().get(player.getCommandSenderName());
				return list == null || !list.contains(prefix + generateItemHash(t.getItem(), t.getItemDamage())); */
				ItemStack t = item.getEntityItem();
				Item subItem = t.getItem();
				int subMeta = t.getItemDamage();
				int[] group = ThaumcraftApi.groupedObjectTags.get(Arrays.asList(subItem, subMeta));
				if (group != null)
					subMeta = group[0];
				List<String> list = Thaumcraft.proxy.getScannedObjects().get(player.getCommandSenderName());
				return list == null || !list.contains(prefix + generateItemHash(subItem, subMeta));
				// TODO gamerforEA code end
			}
			else
			{
				List<String> list = Thaumcraft.proxy.getScannedEntities().get(player.getCommandSenderName());
				return list == null || !list.contains(prefix + generateEntityHash(scan.entity));
			}
		}
		else if (scan.type == 3)
		{
			List<String> list = Thaumcraft.proxy.getScannedPhenomena().get(player.getCommandSenderName());
			return list == null || !list.contains(prefix + scan.phenomena);
		}

		return true;
	}

	public static boolean hasBeenScanned(EntityPlayer player, ScanResult scan)
	{
		if (scan.type == 1)
		{
			Item item = Item.getItemById(scan.id);

			/* TODO gamerforEA code replace, old code:
			if (ThaumcraftApi.groupedObjectTags.containsKey(Arrays.asList(item, scan.meta)))
				scan.meta = ThaumcraftApi.groupedObjectTags.get(Arrays.asList(item, scan.meta))[0]; */
			int[] group = ThaumcraftApi.groupedObjectTags.get(Arrays.asList(item, scan.meta));
			if (group != null)
				scan.meta = group[0];
			// TODO gamerforEA code end

			List<String> list = Thaumcraft.proxy.getScannedObjects().get(player.getCommandSenderName());
			return list != null && (list.contains("@" + generateItemHash(item, scan.meta)) || list.contains("#" + generateItemHash(item, scan.meta)));
		}
		else if (scan.type == 2)
		{
			// TODO gamerforEA code start
			if (scan.entity == null)
				return false;
			// TODO gamerforEA code end

			if (scan.entity instanceof EntityItem)
			{
				EntityItem item = (EntityItem) scan.entity;

				/* TODO gamerforEA code replace, old code:
				ItemStack t = item.getEntityItem().copy();
				t.stackSize = 1;
				if (ThaumcraftApi.groupedObjectTags.containsKey(Arrays.asList(t.getItem(), t.getItemDamage())))
					t.setItemDamage(ThaumcraftApi.groupedObjectTags.get(Arrays.asList(t.getItem(), t.getItemDamage()))[0]);
				List<String> list = Thaumcraft.proxy.getScannedObjects().get(player.getCommandSenderName());
				return list != null && (list.contains("@" + generateItemHash(t.getItem(), t.getItemDamage())) || list.contains("#" + generateItemHash(t.getItem(), t.getItemDamage()))); */
				ItemStack t = item.getEntityItem();
				Item subItem = t.getItem();
				int subMeta = t.getItemDamage();
				int[] group = ThaumcraftApi.groupedObjectTags.get(Arrays.asList(subItem, subMeta));
				if (group != null)
					subMeta = group[0];
				List<String> list = Thaumcraft.proxy.getScannedObjects().get(player.getCommandSenderName());
				return list != null && (list.contains("@" + generateItemHash(subItem, subMeta)) || list.contains("#" + generateItemHash(subItem, subMeta)));
				// TODO gamerforEA code end
			}
			else
			{
				List<String> list = Thaumcraft.proxy.getScannedEntities().get(player.getCommandSenderName());
				return list != null && (list.contains("@" + generateEntityHash(scan.entity)) || list.contains("#" + generateEntityHash(scan.entity)));
			}
		}
		else if (scan.type == 3)
		{
			List<String> list = Thaumcraft.proxy.getScannedPhenomena().get(player.getCommandSenderName());
			return list != null && (list.contains("@" + scan.phenomena) || list.contains("#" + scan.phenomena));
		}

		return false;
	}

	public static boolean completeScan(EntityPlayer player, ScanResult scan, String prefix)
	{
		AspectList aspects = null;
		PlayerKnowledge rp = Thaumcraft.proxy.getPlayerKnowledge();
		boolean ret = false;
		boolean scannedByThaumometer = prefix.equals("#") && !isValidScanTarget(player, scan, "@");
		Object clue = null;
		if (scan.type == 1)
		{
			Item item = Item.getItemById(scan.id);

			/* TODO gamerforEA code replace, old code:
			if (ThaumcraftApi.groupedObjectTags.containsKey(Arrays.asList(item, scan.meta)))
				scan.meta = ThaumcraftApi.groupedObjectTags.get(Arrays.asList(item, scan.meta))[0]; */
			int[] group = ThaumcraftApi.groupedObjectTags.get(Arrays.asList(item, scan.meta));
			if (group != null)
				scan.meta = group[0];
			// TODO gamerforEA code end

			aspects = ThaumcraftCraftingManager.getObjectTags(new ItemStack(item, 1, scan.meta));
			aspects = ThaumcraftCraftingManager.getBonusTags(new ItemStack(item, 1, scan.meta), aspects);
			if ((aspects == null || aspects.size() == 0) && scan.id > 0)
			{
				aspects = ThaumcraftCraftingManager.getObjectTags(new ItemStack(item, 1, scan.meta));
				aspects = ThaumcraftCraftingManager.getBonusTags(new ItemStack(item, 1, scan.meta), aspects);
			}

			if (validScan(aspects, player))
			{
				clue = new ItemStack(item, 1, scan.meta);
				Thaumcraft.proxy.getResearchManager().completeScannedObject(player, prefix + generateItemHash(item, scan.meta));
				ret = true;
			}
		}
		else if (scan.type == 2)
		{
			if (scan.entity instanceof EntityItem)
			{
				EntityItem item = (EntityItem) scan.entity;
				ItemStack t = item.getEntityItem().copy();
				t.stackSize = 1;

				/* TODO gamerforEA code replace, old code:
				if (ThaumcraftApi.groupedObjectTags.containsKey(Arrays.asList(t.getItem(), t.getItemDamage())))
					t.setItemDamage(ThaumcraftApi.groupedObjectTags.get(Arrays.asList(t.getItem(), t.getItemDamage()))[0]); */
				int[] group = ThaumcraftApi.groupedObjectTags.get(Arrays.asList(t.getItem(), t.getItemDamage()));
				if (group != null)
					t.setItemDamage(group[0]);
				// TODO gamerforEA code end

				aspects = ThaumcraftCraftingManager.getObjectTags(t);
				aspects = ThaumcraftCraftingManager.getBonusTags(t, aspects);
				if (validScan(aspects, player))
				{
					clue = item.getEntityItem();
					Thaumcraft.proxy.getResearchManager().completeScannedObject(player, prefix + generateItemHash(item.getEntityItem().getItem(), item.getEntityItem().getItemDamage()));
					ret = true;
				}
			}
			// TODO gamerforEA add condition [1]
			else if (scan.entity != null)
			{
				aspects = generateEntityAspects(scan.entity);
				if (validScan(aspects, player))
				{
					clue = EntityList.getEntityString(scan.entity);
					Thaumcraft.proxy.getResearchManager().completeScannedEntity(player, prefix + generateEntityHash(scan.entity));
					ret = true;
				}
			}
		}
		else if (scan.type == 3 && scan.phenomena.startsWith("NODE"))
		{
			aspects = generateNodeAspects(player.worldObj, scan.phenomena.replace("NODE", ""));
			if (validScan(aspects, player))
			{
				Thaumcraft.proxy.getResearchManager().completeScannedPhenomena(player, prefix + scan.phenomena);
				ret = true;
			}
		}

		if (!player.worldObj.isRemote && ret && aspects != null)
		{
			AspectList aspectsFinal = new AspectList();

			for (Aspect aspect : aspects.getAspects())
			{
				if (rp.hasDiscoveredParentAspects(player.getCommandSenderName(), aspect))
				{
					int amt = aspects.getAmount(aspect);
					if (scannedByThaumometer)
						amt = 0;

					if (prefix.equals("#"))
						++amt;

					int a = checkAndSyncAspectKnowledge(player, aspect, amt);
					if (a > 0)
						aspectsFinal.merge(aspect, a);
				}
			}

			if (clue != null)
				ResearchManager.createClue(player.worldObj, player, clue, aspectsFinal);
		}

		return ret;
	}

	public static int checkAndSyncAspectKnowledge(EntityPlayer player, Aspect aspect, int amount)
	{
		PlayerKnowledge rp = Thaumcraft.proxy.getPlayerKnowledge();
		int save = 0;
		if (!rp.hasDiscoveredAspect(player.getCommandSenderName(), aspect))
		{
			PacketHandler.INSTANCE.sendTo(new PacketAspectDiscovery(aspect.getTag()), (EntityPlayerMP) player);
			amount += 2;
			save = amount;
		}

		if (rp.getAspectPoolFor(player.getCommandSenderName(), aspect) >= Config.aspectTotalCap)
			amount = (int) Math.sqrt((double) amount);

		if (amount > 1 && (float) rp.getAspectPoolFor(player.getCommandSenderName(), aspect) >= (float) Config.aspectTotalCap * 1.25F)
			amount = 1;

		if (rp.addAspectPool(player.getCommandSenderName(), aspect, (short) amount))
		{
			PacketHandler.INSTANCE.sendTo(new PacketAspectPool(aspect.getTag(), (short) amount, rp.getAspectPoolFor(player.getCommandSenderName(), aspect)), (EntityPlayerMP) player);
			save = amount;
		}

		if (save > 0)
			Thaumcraft.proxy.getResearchManager().completeAspect(player, aspect, rp.getAspectPoolFor(player.getCommandSenderName(), aspect));

		return save;
	}

	public static boolean validScan(AspectList aspects, EntityPlayer player)
	{
		PlayerKnowledge rp = Thaumcraft.proxy.getPlayerKnowledge();
		if (aspects != null && aspects.size() > 0)
		{
			for (Aspect aspect : aspects.getAspects())
			{
				if (aspect != null && !aspect.isPrimal() && !rp.hasDiscoveredParentAspects(player.getCommandSenderName(), aspect))
				{
					if (player.worldObj.isRemote)
						for (Aspect parent : aspect.getComponents())
						{
							if (!rp.hasDiscoveredAspect(player.getCommandSenderName(), parent))
							{
								PlayerNotifications.addNotification(new ChatComponentTranslation(StatCollector.translateToLocal("tc.discoveryerror"), StatCollector.translateToLocal("tc.aspect.help." + parent.getTag())).getUnformattedText());
								break;
							}
						}

					return false;
				}
			}

			return true;
		}

		if (player.worldObj.isRemote)
			PlayerNotifications.addNotification(StatCollector.translateToLocal("tc.unknownobject"));

		return false;
	}

	public static AspectList getScanAspects(ScanResult scan, World world)
	{
		AspectList aspects = new AspectList();
		boolean ret = false;
		if (scan.type == 1)
		{
			Item item = Item.getItemById(scan.id);

			/* TODO gamerforEA code replace, old code:
			if (ThaumcraftApi.groupedObjectTags.containsKey(Arrays.asList(item, scan.meta)))
				scan.meta = ThaumcraftApi.groupedObjectTags.get(Arrays.asList(item, scan.meta))[0]; */
			int[] group = ThaumcraftApi.groupedObjectTags.get(Arrays.asList(item, scan.meta));
			if (group != null)
				scan.meta = group[0];
			// TODO gamerforEA code end

			aspects = ThaumcraftCraftingManager.getObjectTags(new ItemStack(item, 1, scan.meta));
			aspects = ThaumcraftCraftingManager.getBonusTags(new ItemStack(item, 1, scan.meta), aspects);
			if ((aspects == null || aspects.size() == 0) && scan.id > 0)
			{
				aspects = ThaumcraftCraftingManager.getObjectTags(new ItemStack(item, 1, scan.meta));
				aspects = ThaumcraftCraftingManager.getBonusTags(new ItemStack(item, 1, scan.meta), aspects);
			}
		}
		else if (scan.type == 2)
			if (scan.entity instanceof EntityItem && ((EntityItem) scan.entity).getEntityItem() != null)
			{
				EntityItem item = (EntityItem) scan.entity;
				ItemStack t = item.getEntityItem().copy();
				t.stackSize = 1;

				/* TODO gamerforEA code replace, old code:
				if (ThaumcraftApi.groupedObjectTags.containsKey(Arrays.asList(t.getItem(), t.getItemDamage())))
					t.setItemDamage(ThaumcraftApi.groupedObjectTags.get(Arrays.asList(t.getItem(), t.getItemDamage()))[0]); */
				int[] group = ThaumcraftApi.groupedObjectTags.get(Arrays.asList(t.getItem(), t.getItemDamage()));
				if (group != null)
					t.setItemDamage(group[0]);
				// TODO gamerforEA code end

				aspects = ThaumcraftCraftingManager.getObjectTags(t);
				aspects = ThaumcraftCraftingManager.getBonusTags(t, aspects);
			}
			else
				aspects = generateEntityAspects(scan.entity);
		else if (scan.type == 3 && scan.phenomena.startsWith("NODE"))
			aspects = generateNodeAspects(world, scan.phenomena.replace("NODE", ""));

		return aspects;
	}
}
