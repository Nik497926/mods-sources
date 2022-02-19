package thaumcraft.common.lib.events;

import com.gamerforea.thaumcraft.EventConfig;
import com.google.common.io.Files;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import net.minecraftforge.event.entity.player.PlayerEvent.LoadFromFile;
import net.minecraftforge.event.entity.player.PlayerEvent.SaveToFile;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent.Finish;
import thaumcraft.api.IRepairable;
import thaumcraft.api.IRepairableExtended;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.damagesource.DamageSourceThaumcraft;
import thaumcraft.api.entities.ITaintedMob;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchCategoryList;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigEntities;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.entities.EntityAspectOrb;
import thaumcraft.common.entities.golems.EntityGolemBase;
import thaumcraft.common.entities.monster.*;
import thaumcraft.common.entities.monster.boss.EntityThaumcraftBoss;
import thaumcraft.common.entities.monster.mods.ChampionModifier;
import thaumcraft.common.entities.projectile.EntityPrimalArrow;
import thaumcraft.common.items.ItemBathSalts;
import thaumcraft.common.items.ItemCrystalEssence;
import thaumcraft.common.items.armor.Hover;
import thaumcraft.common.items.armor.ItemHoverHarness;
import thaumcraft.common.items.equipment.ItemBowBone;
import thaumcraft.common.items.wands.WandManager;
import thaumcraft.common.lib.WarpEvents;
import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;
import thaumcraft.common.lib.research.ResearchManager;
import thaumcraft.common.lib.research.ScanManager;
import thaumcraft.common.lib.utils.EntityUtils;
import thaumcraft.common.lib.utils.InventoryUtils;
import thaumcraft.common.lib.world.dim.Cell;
import thaumcraft.common.lib.world.dim.CellLoc;
import thaumcraft.common.lib.world.dim.MazeHandler;
import thaumcraft.common.tiles.TileOwned;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import static com.gamerforea.thaumcraft.ChatUtils.*;

public class EventHandlerEntity
{
	public HashMap<Integer, Float> prevStep = new HashMap<>();
	public static HashMap<String, ArrayList<WeakReference<Entity>>> linkedEntities = new HashMap<>();

	@SubscribeEvent
	public void droppedItem(ItemTossEvent event)
	{
		NBTTagCompound itemData = event.entityItem.getEntityData();
		itemData.setString("thrower", event.player.getCommandSenderName());
	}

	@SubscribeEvent
	public void playerLoad(LoadFromFile event)
	{
		EntityPlayer p = event.entityPlayer;
		Thaumcraft.proxy.getPlayerKnowledge().wipePlayerKnowledge(p.getCommandSenderName());
		File file1 = this.getPlayerFile("thaum", event.playerDirectory, p.getCommandSenderName());
		boolean legacy = false;
		if (!file1.exists())
		{
			File filep = event.getPlayerFile("thaum");
			if (filep.exists())
				try
				{
					Files.copy(filep, file1);
					Thaumcraft.log.info("Using and converting UUID Thaumcraft savefile for " + p.getCommandSenderName());
					legacy = true;
					filep.delete();
					File backupFile = event.getPlayerFile("thaumback");
					if (backupFile.exists())
						backupFile.delete();
				}
				catch (IOException ignored)
				{
				}
			else
			{
				File filet = this.getLegacyPlayerFile(p);
				if (filet.exists())
					try
					{
						Files.copy(filet, file1);
						Thaumcraft.log.info("Using pre MC 1.7.10 Thaumcraft savefile for " + p.getCommandSenderName());
						legacy = true;
					}
					catch (IOException ignored)
					{
					}
			}
		}

		ResearchManager.loadPlayerData(p, file1, this.getPlayerFile("thaumback", event.playerDirectory, p.getCommandSenderName()), legacy);

		for (ResearchCategoryList cat : ResearchCategories.researchCategories.values())
		{
			for (ResearchItem ri : cat.research.values())
			{
				if (ri.isAutoUnlock())
					Thaumcraft.proxy.getResearchManager().completeResearch(p, ri.key);
			}
		}

	}

	public File getLegacyPlayerFile(EntityPlayer player)
	{
		try
		{
			File playersDirectory = new File(player.worldObj.getSaveHandler().getWorldDirectory(), "players");
			return new File(playersDirectory, player.getCommandSenderName() + ".thaum");
		}
		catch (Exception var3)
		{
			var3.printStackTrace();
			return null;
		}
	}

	public File getPlayerFile(String suffix, File playerDirectory, String playername)
	{
		if ("dat".equals(suffix))
			throw new IllegalArgumentException("The suffix \'dat\' is reserved");
		else
			return new File(playerDirectory, playername + "." + suffix);
	}

	@SubscribeEvent
	public void playerSave(SaveToFile event)
	{
		EntityPlayer p = event.entityPlayer;
		ResearchManager.savePlayerData(p, this.getPlayerFile("thaum", event.playerDirectory, p.getCommandSenderName()), this.getPlayerFile("thaumback", event.playerDirectory, p.getCommandSenderName()));
	}

	public static void doRepair(ItemStack is, EntityPlayer player)
	{
		int level = EnchantmentHelper.getEnchantmentLevel(Config.enchRepair.effectId, is);
		if (level > 0)
		{
			if (level > 2)
				level = 2;

			AspectList cost = ThaumcraftCraftingManager.getObjectTags(is);
			if (cost != null && cost.size() != 0)
			{
				cost = ResearchManager.reduceToPrimals(cost);
				AspectList finalCost = new AspectList();

				for (Aspect a : cost.getAspects())
				{
					if (a != null)
						finalCost.merge(a, (int) Math.sqrt((double) (cost.getAmount(a) * 2)) * level);
				}

				if (is.getItem() instanceof IRepairableExtended)
				{
					if (((IRepairableExtended) is.getItem()).doRepair(is, player, level) && WandManager.consumeVisFromInventory(player, finalCost))
						is.damageItem(-level, player);
				}
				else if (WandManager.consumeVisFromInventory(player, finalCost))
					is.damageItem(-level, player);

			}
		}
	}

	@SubscribeEvent
	public void livingTick(LivingUpdateEvent event)
	{
		if (event.entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.entity;
			if (event.entity.worldObj.provider.dimensionId == Config.dimensionOuterId && !player.capabilities.isCreativeMode && player.ticksExisted % 20 == 0 && (player.capabilities.isFlying || Hover.getHover(player.getEntityId())))
			{
				player.capabilities.isFlying = false;
				Hover.setHover(player.getEntityId(), false);

				// TODO gamerforEA code replace, old code:
				// player.addChatMessage(new ChatComponentText(EnumChatFormatting.ITALIC + "" + EnumChatFormatting.GRAY + StatCollector.translateToLocal("tc.break.fly")));;
				player.addChatComponentMessage(italic(color(translate("tc.break.fly"), EnumChatFormatting.GRAY)));
				// TODO gamerforEA code end
			}

			if (Hover.getHover(player.getEntityId()) && (player.inventory.armorItemInSlot(2) == null || !(player.inventory.armorItemInSlot(2).getItem() instanceof ItemHoverHarness)))
			{
				Hover.setHover(player.getEntityId(), false);
				player.capabilities.isFlying = false;
			}

			if (!event.entity.worldObj.isRemote)
			{
				if (!Config.wuss && player.ticksExisted > 0 && player.ticksExisted % 2000 == 0 && !player.isPotionActive(Config.potionWarpWardID))
					WarpEvents.checkWarpEvent(player);

				if (player.ticksExisted % 10 == 0 && player.isPotionActive(Config.potionDeathGazeID))
					WarpEvents.checkDeathGaze(player);

				if (player.ticksExisted % 40 == 0)
				{
					int a = 0;

					while (true)
					{
						InventoryPlayer var10001 = player.inventory;
						if (a >= InventoryPlayer.getHotbarSize())
						{
							for (a = 0; a < 4; ++a)
							{
								if (player.inventory.armorItemInSlot(a) != null)
								{
									ItemStack is = player.inventory.armorItemInSlot(a);
									if (is.getItemDamage() > 0 && is.getItem() instanceof IRepairable && !player.capabilities.isCreativeMode)
										doRepair(is, player);
								}
							}
							break;
						}

						if (player.inventory.mainInventory[a] != null)
						{
							ItemStack is = player.inventory.mainInventory[a];
							if (is.getItemDamage() > 0 && is.getItem() instanceof IRepairable && !player.capabilities.isCreativeMode && !(is.getItem() instanceof ItemHoverHarness))
								doRepair(is, player);
						}

						++a;
					}
				}
			}

			this.updateSpeed(player);
			if (player.worldObj.isRemote && (player.isSneaking() || player.inventory.armorItemInSlot(0) == null || player.inventory.armorItemInSlot(0).getItem() != ConfigItems.itemBootsTraveller) && this.prevStep.containsKey(player.getEntityId()))
			{
				player.stepHeight = this.prevStep.get(player.getEntityId());
				this.prevStep.remove(player.getEntityId());
			}
		}

		if (event.entity instanceof EntityMob && !event.entity.isDead)
		{
			EntityMob mob = (EntityMob) event.entity;
			int t = (int) mob.getEntityAttribute(EntityUtils.CHAMPION_MOD).getAttributeValue();
			if (t >= 0 && ChampionModifier.mods[t].type == 0)
				ChampionModifier.mods[t].effect.performEffect(mob, null, null, 0.0F);
		}

	}

	private void updateSpeed(EntityPlayer player)
	{
		try
		{
			if (!player.capabilities.isFlying && player.inventory.armorItemInSlot(0) != null && player.moveForward > 0.0F)
			{
				int haste = EnchantmentHelper.getEnchantmentLevel(Config.enchHaste.effectId, player.inventory.armorItemInSlot(0));
				if (haste > 0)
				{
					float bonus = (float) haste * 0.015F;
					if (player.isAirBorne)
						bonus /= 2.0F;

					if (player.isInWater())
						bonus /= 2.0F;

					player.moveFlying(0.0F, 1.0F, bonus);
				}
			}
		}
		catch (Exception ignored)
		{
		}
	}

	@SubscribeEvent
	public void playerJumps(LivingJumpEvent event)
	{
		if (event.entity instanceof EntityPlayer && ((EntityPlayer) event.entity).inventory.armorItemInSlot(0) != null && ((EntityPlayer) event.entity).inventory.armorItemInSlot(0).getItem() == ConfigItems.itemBootsTraveller)
			event.entityLiving.motionY += 0.2750000059604645D;

	}

	@SubscribeEvent
	public void playerInteract(EntityInteractEvent event)
	{
		if (event.target instanceof EntityGolemBase && ((EntityGolemBase) event.target).getOwnerName().length() > 0 && !((EntityGolemBase) event.target).getOwnerName().equals(event.entityPlayer.getCommandSenderName()))
		{
			if (!event.entityPlayer.worldObj.isRemote)
				event.entityPlayer.addChatMessage(new ChatComponentTranslation("You are not my Master!"));

			event.setCanceled(true);
		}

	}

	@SubscribeEvent
	public void entitySpawns(EntityJoinWorldEvent event)
	{
		if (!event.world.isRemote)
		{
			if (event.entity instanceof EntityEnderPearl)
			{
				int x = MathHelper.floor_double(event.entity.posX);
				int y = MathHelper.floor_double(event.entity.posY);
				int z = MathHelper.floor_double(event.entity.posZ);

				label84:
				for (int xx = -5; xx <= 5; ++xx)
				{
					for (int yy = -5; yy <= 5; ++yy)
					{
						for (int zz = -5; zz <= 5; ++zz)
						{
							TileEntity tile = event.world.getTileEntity(x + xx, y + yy, z + zz);
							if (tile instanceof TileOwned)
							{
								EntityLivingBase thrower = ((EntityEnderPearl) event.entity).getThrower();
								if (thrower instanceof EntityPlayer)
									// TODO gamerforEA code replace, old code:
									// ((EntityPlayer) thrower).addChatMessage(new ChatComponentText("§5§oThe magic of a nearby warded object destroys the ender pearl."));
									((EntityPlayer) thrower).addChatComponentMessage(italic(color(text("The magic of a nearby warded object destroys the ender pearl."), EnumChatFormatting.DARK_PURPLE)));
								// TODO gamerforEA code end

								event.entity.setDead();
								break label84;
							}
						}
					}
				}
			}

			if (event.entity instanceof EntityPlayer)
			{
				ArrayList<WeakReference<Entity>> dudes = linkedEntities.get(event.entity.getCommandSenderName());
				if (dudes != null)
					for (WeakReference<Entity> dude : dudes)
					{
						Entity dudeEntity = dude.get();
						if (dudeEntity != null && dudeEntity.timeUntilPortal == 0)
						{
							dudeEntity.timeUntilPortal = dudeEntity.getPortalCooldown();
							dudeEntity.travelToDimension(event.world.provider.dimensionId);
						}
					}
			}
			else if (event.entity instanceof EntityMob)
			{
				EntityMob mob = (EntityMob) event.entity;
				if (mob.getEntityAttribute(EntityUtils.CHAMPION_MOD).getAttributeValue() < -1.0D)
				{
					int c = event.world.rand.nextInt(100);
					if (event.world.difficultySetting == EnumDifficulty.EASY || !Config.championMobs)
						c += 2;

					if (event.world.difficultySetting == EnumDifficulty.HARD)
						c -= Config.championMobs ? 2 : 0;

					if (event.world.provider.dimensionId == Config.dimensionOuterId)
						c -= 3;

					BiomeGenBase bg = mob.worldObj.getBiomeGenForCoords(MathHelper.ceiling_double_int(mob.posX), MathHelper.ceiling_double_int(mob.posZ));
					if (BiomeDictionary.isBiomeOfType(bg, Type.SPOOKY) || BiomeDictionary.isBiomeOfType(bg, Type.NETHER) || BiomeDictionary.isBiomeOfType(bg, Type.END))
						c -= Config.championMobs ? 2 : 1;

					if (this.isDangerousLocation(mob.worldObj, MathHelper.ceiling_double_int(mob.posX), MathHelper.ceiling_double_int(mob.posY), MathHelper.ceiling_double_int(mob.posZ)))
						c -= Config.championMobs ? 10 : 3;

					int cc = 0;
					boolean whitelisted = false;

					for (Class clazz : ConfigEntities.championModWhitelist.keySet())
					{
						if (clazz.isAssignableFrom(event.entity.getClass()))
						{
							whitelisted = true;
							if (Config.championMobs || event.entity instanceof EntityThaumcraftBoss)
								cc = Math.max(cc, ConfigEntities.championModWhitelist.get(clazz) - 1);
						}
					}

					c = c - cc;
					if (whitelisted && c <= 0 && mob.getEntityAttribute(SharedMonsterAttributes.maxHealth).getBaseValue() >= 10.0D)
						EntityUtils.makeChampion(mob, false);
					else
					{
						IAttributeInstance modai = mob.getEntityAttribute(EntityUtils.CHAMPION_MOD);
						modai.removeModifier(ChampionModifier.ATTRIBUTE_MOD_NONE);
						modai.applyModifier(ChampionModifier.ATTRIBUTE_MOD_NONE);
					}
				}
			}
		}

	}

	private boolean isDangerousLocation(World world, int x, int y, int z)
	{
		if (world.provider.dimensionId == Config.dimensionOuterId)
		{
			int xx = x >> 4;
			int zz = z >> 4;
			Cell c = MazeHandler.getFromHashMap(new CellLoc(xx, zz));
			return c != null && (c.feature == 6 || c.feature == 8);
		}

		return false;
	}

	@SubscribeEvent
	public void entityConstuct(EntityConstructing event)
	{
		if (event.entity instanceof EntityMob)
		{
			EntityMob mob = (EntityMob) event.entity;
			mob.getAttributeMap().registerAttribute(EntityUtils.CHAMPION_MOD).setBaseValue(-2.0D);
		}

	}

	@SubscribeEvent
	public void itemPickup(EntityItemPickupEvent event)
	{
		if (event.entityPlayer.getCommandSenderName().startsWith("FakeThaumcraft"))
			event.setCanceled(true);

	}

	@SubscribeEvent
	public void livingDrops(LivingDropsEvent event)
	{
		boolean fakeplayer = event.source.getEntity() != null && event.source.getEntity() instanceof FakePlayer;
		EntityLivingBase entity = event.entityLiving;
		World world = entity.worldObj;
		Random random = world.rand;
		if (!world.isRemote && event.recentlyHit && !fakeplayer && entity instanceof EntityMob && !(entity instanceof EntityThaumcraftBoss) && entity.getEntityAttribute(EntityUtils.CHAMPION_MOD).getAttributeValue() >= 0.0D)
		{
			int i = 5 + random.nextInt(3);

			while (i > 0)
			{
				int j = EntityXPOrb.getXPSplit(i);
				i -= j;
				world.spawnEntityInWorld(new EntityXPOrb(world, entity.posX, entity.posY, entity.posZ, j));
			}

			int lb = Math.min(2, MathHelper.floor_float((float) (random.nextInt(9) + event.lootingLevel) / 5.0F));
			event.drops.add(new EntityItem(world, entity.posX, entity.posY + (double) entity.getEyeHeight(), entity.posZ, new ItemStack(ConfigItems.itemLootbag, 1, lb)));
		}

		if (entity instanceof EntityZombie && !(entity instanceof EntityBrainyZombie) && event.recentlyHit && random.nextInt(10) - event.lootingLevel < 1)
			event.drops.add(new EntityItem(world, entity.posX, entity.posY + (double) entity.getEyeHeight(), entity.posZ, new ItemStack(ConfigItems.itemZombieBrain)));

		if (entity instanceof EntityVillager && random.nextInt(10) - event.lootingLevel < 1)
			event.drops.add(new EntityItem(world, entity.posX, entity.posY + (double) entity.getEyeHeight(), entity.posZ, new ItemStack(ConfigItems.itemResource, 1, 18)));

		if (event.source == DamageSourceThaumcraft.dissolve)
		{
			// TODO gamerforEA code start
			if (EventConfig.disableAspectLivingDrop || (!EventConfig.enablePlayerFluidDeathAspectDrop && entity instanceof EntityPlayer))
				return;
			if (random.nextFloat() > EventConfig.fluidDeathAspectDropChance)
				return;
			// TODO gamerforEA code end

			AspectList aspects = ScanManager.generateEntityAspects(entity);
			if (aspects != null && aspects.size() > 0)
				for (Aspect aspect : aspects.getAspects())
				{
					if (!random.nextBoolean())
					{
						int size = 1 + random.nextInt(aspects.getAmount(aspect));
						size = Math.max(1, size / 2);
						ItemStack stack = new ItemStack(ConfigItems.itemCrystalEssence, size, 0);
						((ItemCrystalEssence) stack.getItem()).setAspects(stack, new AspectList().add(aspect, 1));
						event.drops.add(new EntityItem(world, entity.posX, entity.posY + (double) entity.getEyeHeight(), entity.posZ, stack));
					}
				}
		}

	}

	@SubscribeEvent
	public void livingTick(LivingDeathEvent event)
	{
		if (!event.entityLiving.worldObj.isRemote && !(event.entityLiving instanceof ITaintedMob) && event.entityLiving.isPotionActive(Config.potionTaintPoisonID))
		{
			Entity entity = null;
			if (event.entityLiving instanceof EntityCreeper)
				entity = new EntityTaintCreeper(event.entityLiving.worldObj);
			else if (event.entityLiving instanceof EntitySheep)
				entity = new EntityTaintSheep(event.entityLiving.worldObj);
			else if (event.entityLiving instanceof EntityCow)
				entity = new EntityTaintCow(event.entityLiving.worldObj);
			else if (event.entityLiving instanceof EntityPig)
				entity = new EntityTaintPig(event.entityLiving.worldObj);
			else if (event.entityLiving instanceof EntityChicken)
				entity = new EntityTaintChicken(event.entityLiving.worldObj);
			else if (event.entityLiving instanceof EntityVillager)
				entity = new EntityTaintVillager(event.entityLiving.worldObj);
			else
			{
				entity = new EntityThaumicSlime(event.entityLiving.worldObj);
				((EntityThaumicSlime) entity).setSlimeSize((int) (1.0F + Math.min(event.entityLiving.getMaxHealth() / 10.0F, 6.0F)));
			}

			entity.setLocationAndAngles(event.entityLiving.posX, event.entityLiving.posY, event.entityLiving.posZ, event.entityLiving.rotationYaw, 0.0F);
			event.entityLiving.worldObj.spawnEntityInWorld(entity);
			event.entityLiving.setDead();
		}
		else if (!event.entityLiving.worldObj.isRemote && EntityUtils.getRecentlyHit(event.entityLiving) > 0)
		{
			AspectList aspectsCompound = ScanManager.generateEntityAspects(event.entityLiving);
			if (aspectsCompound != null && aspectsCompound.size() > 0)
			{
				AspectList aspects = ResearchManager.reduceToPrimals(aspectsCompound);

				for (Aspect aspect : aspects.getAspects())
				{
					if (event.entityLiving.worldObj.rand.nextBoolean())
					{
						EntityAspectOrb orb = new EntityAspectOrb(event.entityLiving.worldObj, event.entityLiving.posX, event.entityLiving.posY, event.entityLiving.posZ, aspect, 1 + event.entityLiving.worldObj.rand.nextInt(aspects.getAmount(aspect)));
						event.entityLiving.worldObj.spawnEntityInWorld(orb);
					}
				}
			}
		}

	}

	@SubscribeEvent
	public void bowNocked(ArrowNockEvent event)
	{
		if (event.entityPlayer.inventory.hasItem(ConfigItems.itemPrimalArrow))
		{
			event.entityPlayer.setItemInUse(event.result, event.result.getItem().getMaxItemUseDuration(event.result));
			event.setCanceled(true);
		}

	}

	@SubscribeEvent
	public void bowShot(ArrowLooseEvent event)
	{
		if (event.entityPlayer.inventory.hasItem(ConfigItems.itemPrimalArrow))
		{
			float f = 0.0F;
			float dam = 2.0F;
			if (event.bow.getItem() instanceof ItemBowBone)
			{
				f = (float) event.charge / 10.0F;
				f = (f * f + f * 2.0F) / 3.0F;
				if ((double) f < 0.1D)
					return;

				dam = 2.5F;
			}
			else
			{
				f = (float) event.charge / 20.0F;
				f = (f * f + f * 2.0F) / 3.0F;
				if ((double) f < 0.1D)
					return;
			}

			if (f > 1.0F)
				f = 1.0F;

			int type = 0;

			for (int j = 0; j < event.entityPlayer.inventory.mainInventory.length; ++j)
			{
				if (event.entityPlayer.inventory.mainInventory[j] != null && event.entityPlayer.inventory.mainInventory[j].getItem() == ConfigItems.itemPrimalArrow)
				{
					type = event.entityPlayer.inventory.mainInventory[j].getItemDamage();
					break;
				}
			}

			EntityPrimalArrow entityarrow = new EntityPrimalArrow(event.entityPlayer.worldObj, event.entityPlayer, f * dam, type);
			if (event.bow.getItem() instanceof ItemBowBone)
				entityarrow.setDamage(entityarrow.getDamage() + 0.5D);
			else if (f == 1.0F)
				entityarrow.setIsCritical(true);

			int k = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, event.bow);
			if (k > 0)
				entityarrow.setDamage(entityarrow.getDamage() + (double) k * 0.5D + 0.5D);

			int l = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, event.bow);
			if (type == 3)
				++l;

			if (l > 0)
				entityarrow.setKnockbackStrength(l);

			if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, event.bow) > 0)
				entityarrow.setFire(100);

			event.bow.damageItem(1, event.entityPlayer);
			event.entityPlayer.worldObj.playSoundAtEntity(event.entityPlayer, "random.bow", 1.0F, 1.0F / (event.entityPlayer.worldObj.rand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
			boolean flag = false;
			if (EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, event.bow) > 0 && event.entityPlayer.worldObj.rand.nextFloat() < 0.33F)
				flag = true;

			if (!event.entityPlayer.capabilities.isCreativeMode || !flag)
				InventoryUtils.consumeInventoryItem(event.entityPlayer, ConfigItems.itemPrimalArrow, type);

			if (!event.entityPlayer.worldObj.isRemote)
				event.entityPlayer.worldObj.spawnEntityInWorld(entityarrow);

			event.setCanceled(true);
		}

	}

	@SubscribeEvent
	public void finishedUsingItem(Finish event)
	{
		if (!event.entity.worldObj.isRemote && event.entityPlayer.isPotionActive(Config.potionUnHungerID))
			if (!event.item.isItemEqual(new ItemStack(Items.rotten_flesh)) && !event.item.isItemEqual(new ItemStack(ConfigItems.itemZombieBrain)))
			{
				if (event.item.getItem() instanceof ItemFood)
					// TODO gamerforEA code replace, old code:
					// event.entityPlayer.addChatMessage(new ChatComponentText("§4§o" + StatCollector.translateToLocal("warp.text.hunger.1")));
					event.entityPlayer.addChatComponentMessage(italic(color(translate("warp.text.hunger.1"), EnumChatFormatting.DARK_RED)));
				// TODO gamerforEA code end
			}
			else
			{
				PotionEffect pe = event.entityPlayer.getActivePotionEffect(Potion.potionTypes[Config.potionUnHungerID]);
				int amp = pe.getAmplifier() - 1;
				int duration = pe.getDuration() - 600;
				event.entityPlayer.removePotionEffect(Config.potionUnHungerID);
				if (duration > 0 && amp >= 0)
				{
					pe = new PotionEffect(Config.potionUnHungerID, duration, amp, true);
					pe.getCurativeItems().clear();
					pe.addCurativeItem(new ItemStack(Items.rotten_flesh));
					event.entityPlayer.addPotionEffect(pe);
				}

				// TODO gamerforEA code replace, old code:
				// event.entityPlayer.addChatMessage(new ChatComponentText("§2§o" + StatCollector.translateToLocal("warp.text.hunger.2")));
				event.entityPlayer.addChatComponentMessage(italic(color(translate("warp.text.hunger.2"), EnumChatFormatting.DARK_GREEN)));
				// TODO gamerforEA code end
			}
	}

	@SubscribeEvent
	public void itemExpire(ItemExpireEvent event)
	{
		if (event.entityItem.getEntityItem() != null && event.entityItem.getEntityItem().getItem() != null && event.entityItem.getEntityItem().getItem() instanceof ItemBathSalts)
		{
			int x = MathHelper.floor_double(event.entityItem.posX);
			int y = MathHelper.floor_double(event.entityItem.posY);
			int z = MathHelper.floor_double(event.entityItem.posZ);
			if (event.entityItem.worldObj.getBlock(x, y, z) == Blocks.water && event.entityItem.worldObj.getBlockMetadata(x, y, z) == 0)
				event.entityItem.worldObj.setBlock(x, y, z, ConfigBlocks.blockFluidPure);
		}

	}

	@SubscribeEvent
	public void breakSpeedEvent(BreakSpeed event)
	{
		if (!event.entityPlayer.onGround && Hover.getHover(event.entityPlayer.getEntityId()))
			event.newSpeed = event.originalSpeed * 5.0F;

	}
}
