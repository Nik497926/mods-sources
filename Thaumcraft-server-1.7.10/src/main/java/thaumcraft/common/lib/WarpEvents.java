package thaumcraft.common.lib;

import baubles.api.BaublesApi;
import com.gamerforea.thaumcraft.EventConfig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.entities.monster.EntityEldritchGuardian;
import thaumcraft.common.entities.monster.EntityMindSpider;
import thaumcraft.common.items.armor.ItemFortressArmor;
import thaumcraft.common.lib.events.EventHandlerRunic;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.misc.PacketMiscEvent;
import thaumcraft.common.lib.network.playerdata.PacketAspectPool;
import thaumcraft.common.lib.network.playerdata.PacketResearchComplete;
import thaumcraft.common.lib.network.playerdata.PacketSyncWarp;
import thaumcraft.common.lib.network.playerdata.PacketWarpMessage;
import thaumcraft.common.lib.research.ResearchManager;

import static com.gamerforea.thaumcraft.ChatUtils.*;

public class WarpEvents
{
	public static void checkWarpEvent(EntityPlayer player)
	{
		int warp = Thaumcraft.proxy.getPlayerKnowledge().getWarpTotal(player.getCommandSenderName());
		int actualwarp = Thaumcraft.proxy.getPlayerKnowledge().getWarpPerm(player.getCommandSenderName()) + Thaumcraft.proxy.getPlayerKnowledge().getWarpSticky(player.getCommandSenderName());
		warp += getWarpFromGear(player);
		int warpCounter = Thaumcraft.proxy.getPlayerKnowledge().getWarpCounter(player.getCommandSenderName());
		int r = player.worldObj.rand.nextInt(100);
		if (warpCounter > 0 && warp > 0 && r <= Math.sqrt(warpCounter))
		{
			warp = Math.min(100, (warp + warp + warpCounter) / 3);
			warpCounter = (int) (warpCounter - Math.max(5.0D, Math.sqrt(warpCounter) * 2.0D));
			Thaumcraft.proxy.getPlayerKnowledge().setWarpCounter(player.getCommandSenderName(), warpCounter);
			int eff = player.worldObj.rand.nextInt(warp);
			ItemStack helm = player.inventory.armorInventory[3];
			if (helm != null && helm.getItem() instanceof ItemFortressArmor && helm.hasTagCompound() && helm.stackTagCompound.hasKey("mask") && helm.stackTagCompound.getInteger("mask") == 0)
				eff -= 2 + player.worldObj.rand.nextInt(4);

			PacketHandler.INSTANCE.sendTo(new PacketMiscEvent((short) 0), (EntityPlayerMP) player);
			if (eff > 0)
				if (eff <= 4)
				{
					grantResearch(player, 1);

					// TODO gamerforEA code replace, old code:
					// player.addChatMessage(new ChatComponentText("§5§o" + StatCollector.translateToLocal("warp.text.3")));
					player.addChatComponentMessage(italic(color(translate("warp.text.3"), EnumChatFormatting.DARK_PURPLE)));
					// TODO gamerforEA code end
				}
				else if (eff > 8)
					if (eff <= 12)
						// TODO gamerforEA code replace, old code:
						// player.addChatMessage(new ChatComponentText("§5§o" + StatCollector.translateToLocal("warp.text.11")));
						player.addChatComponentMessage(italic(color(translate("warp.text.11"), EnumChatFormatting.DARK_PURPLE)));
						// TODO gamerforEA code end
					else
					{
						PotionEffect pe;

						// TODO gamerforEA add condition [2]
						if (eff <= 16 && EventConfig.enableVisExhaust)
						{
							pe = new PotionEffect(Config.potionVisExhaustID, 5000, Math.min(3, warp / 15), true);
							pe.getCurativeItems().clear();

							try
							{
								player.addPotionEffect(pe);
							}
							catch (Exception var18)
							{
								var18.printStackTrace();
							}

							// TODO gamerforEA code replace, old code:
							// player.addChatMessage(new ChatComponentText("§5§o" + StatCollector.translateToLocal("warp.text.1")));
							player.addChatComponentMessage(italic(color(translate("warp.text.1"), EnumChatFormatting.DARK_PURPLE)));
							// TODO gamerforEA code end
						}
						// TODO gamerforEA add condition [2]
						else if (eff <= 20 && EventConfig.enableThaumarhia)
						{
							pe = new PotionEffect(Config.potionThaumarhiaID, Math.min(32000, 10 * warp), 0, true);
							pe.getCurativeItems().clear();

							try
							{
								player.addPotionEffect(pe);
							}
							catch (Exception var17)
							{
								var17.printStackTrace();
							}

							// TODO gamerforEA code replace, old code:
							// player.addChatMessage(new ChatComponentText("§5§o" + StatCollector.translateToLocal("warp.text.15")));
							player.addChatComponentMessage(italic(color(translate("warp.text.15"), EnumChatFormatting.DARK_PURPLE)));
							// TODO gamerforEA code end
						}
						// TODO gamerforEA add condition [2]
						else if (eff <= 24 && EventConfig.enableUnhunger)
						{
							pe = new PotionEffect(Config.potionUnHungerID, 5000, Math.min(3, warp / 15), true);
							pe.getCurativeItems().clear();
							pe.addCurativeItem(new ItemStack(Items.rotten_flesh));
							pe.addCurativeItem(new ItemStack(ConfigItems.itemZombieBrain));

							try
							{
								player.addPotionEffect(pe);
							}
							catch (Exception var16)
							{
								var16.printStackTrace();
							}

							// TODO gamerforEA code replace, old code:
							// player.addChatMessage(new ChatComponentText("§5§o" + StatCollector.translateToLocal("warp.text.2")));
							player.addChatComponentMessage(italic(color(translate("warp.text.2"), EnumChatFormatting.DARK_PURPLE)));
							// TODO gamerforEA code end
						}
						else if (eff <= 28)
							// TODO gamerforEA code replace, old code:
							// player.addChatMessage(new ChatComponentText("§5§o" + StatCollector.translateToLocal("warp.text.12")));
							player.addChatComponentMessage(italic(color(translate("warp.text.12"), EnumChatFormatting.DARK_PURPLE)));
							// TODO gamerforEA code end
						else if (eff <= 32)
							spawnMist(player, warp, 1);
							// TODO gamerforEA add condition [2]
						else if (eff <= 36 && EventConfig.enableBlurredVision)
							try
							{
								player.addPotionEffect(new PotionEffect(Config.potionBlurredID, Math.min(32000, 10 * warp), 0, true));
							}
							catch (Exception var15)
							{
								var15.printStackTrace();
							}
							// TODO gamerforEA add condition [2]
						else if (eff <= 40 && EventConfig.enableSunScorned)
						{
							pe = new PotionEffect(Config.potionSunScornedID, 5000, Math.min(3, warp / 15), true);
							pe.getCurativeItems().clear();

							try
							{
								player.addPotionEffect(pe);
							}
							catch (Exception var14)
							{
								var14.printStackTrace();
							}

							// TODO gamerforEA code replace, old code:
							// player.addChatMessage(new ChatComponentText("§5§o" + StatCollector.translateToLocal("warp.text.5")));
							player.addChatComponentMessage(italic(color(translate("warp.text.5"), EnumChatFormatting.DARK_PURPLE)));
							// TODO gamerforEA code end
						}
						else if (eff <= 44)
						{
							try
							{
								player.addPotionEffect(new PotionEffect(Potion.digSlowdown.id, 1200, Math.min(3, warp / 15), true));
							}
							catch (Exception var13)
							{
								var13.printStackTrace();
							}

							// TODO gamerforEA code replace, old code:
							// player.addChatMessage(new ChatComponentText("§5§o" + StatCollector.translateToLocal("warp.text.9")));
							player.addChatComponentMessage(italic(color(translate("warp.text.9"), EnumChatFormatting.DARK_PURPLE)));
							// TODO gamerforEA code end
						}
						// TODO gamerforEA add condition [2]
						else if (eff <= 48 && EventConfig.enableInfectiousVisExhaust)
						{
							pe = new PotionEffect(Config.potionInfVisExhaustID, 6000, Math.min(3, warp / 15), false);
							pe.getCurativeItems().clear();

							try
							{
								player.addPotionEffect(pe);
							}
							catch (Exception var12)
							{
								var12.printStackTrace();
							}

							// TODO gamerforEA code replace, old code:
							// player.addChatMessage(new ChatComponentText("§5§o" + StatCollector.translateToLocal("warp.text.1")));
							player.addChatComponentMessage(italic(color(translate("warp.text.1"), EnumChatFormatting.DARK_PURPLE)));
							// TODO gamerforEA code end
						}
						else if (eff <= 52)
						{
							player.addPotionEffect(new PotionEffect(Potion.nightVision.id, Math.min(40 * warp, 6000), 0, true));
							// TODO gamerforEA code replace, old code:
							// player.addChatMessage(new ChatComponentText("§5§o" + StatCollector.translateToLocal("warp.text.10")));
							player.addChatComponentMessage(italic(color(translate("warp.text.10"), EnumChatFormatting.DARK_PURPLE)));
							// TODO gamerforEA code end
						}
						// TODO gamerforEA add condition [2]
						else if (eff <= 56 && EventConfig.enableDeathGase)
						{
							pe = new PotionEffect(Config.potionDeathGazeID, 6000, Math.min(3, warp / 15), true);
							pe.getCurativeItems().clear();

							try
							{
								player.addPotionEffect(pe);
							}
							catch (Exception var11)
							{
								var11.printStackTrace();
							}

							// TODO gamerforEA code replace, old code:
							// player.addChatMessage(new ChatComponentText("§5§o" + StatCollector.translateToLocal("warp.text.4")));
							player.addChatComponentMessage(italic(color(translate("warp.text.4"), EnumChatFormatting.DARK_PURPLE)));
							// TODO gamerforEA code end
						}
						else if (eff <= 60)
							suddenlySpiders(player, warp, false);
						else if (eff <= 64)
							// TODO gamerforEA code replace, old code:
							// player.addChatMessage(new ChatComponentText("§5§o" + StatCollector.translateToLocal("warp.text.13")));
							player.addChatComponentMessage(italic(color(translate("warp.text.13"), EnumChatFormatting.DARK_PURPLE)));
							// TODO gamerforEA code end
						else if (eff <= 68)
							spawnMist(player, warp, warp / 30);
						else if (eff <= 72)
							try
							{
								player.addPotionEffect(new PotionEffect(Potion.blindness.id, Math.min(32000, 5 * warp), 0, true));
							}
							catch (Exception var10)
							{
								var10.printStackTrace();
							}
						else if (eff == 76)
						{
							if (Thaumcraft.proxy.getPlayerKnowledge().getWarpSticky(player.getCommandSenderName()) > 0)
							{
								Thaumcraft.proxy.getPlayerKnowledge().addWarpSticky(player.getCommandSenderName(), -1);
								PacketHandler.INSTANCE.sendTo(new PacketSyncWarp(player, (byte) 1), (EntityPlayerMP) player);
								PacketHandler.INSTANCE.sendTo(new PacketWarpMessage(player, (byte) 1, -1), (EntityPlayerMP) player);
							}

							// TODO gamerforEA code replace, old code:
							// player.addChatMessage(new ChatComponentText("§5§o" + StatCollector.translateToLocal("warp.text.14")));
							player.addChatComponentMessage(italic(color(translate("warp.text.14"), EnumChatFormatting.DARK_PURPLE)));
							// TODO gamerforEA code end
						}
						// TODO gamerforEA add condition [2]
						else if (eff <= 80 && EventConfig.enableUnhunger)
						{
							pe = new PotionEffect(Config.potionUnHungerID, 6000, Math.min(3, warp / 15), true);
							pe.getCurativeItems().clear();
							pe.addCurativeItem(new ItemStack(Items.rotten_flesh));
							pe.addCurativeItem(new ItemStack(ConfigItems.itemZombieBrain));

							try
							{
								player.addPotionEffect(pe);
							}
							catch (Exception var9)
							{
								var9.printStackTrace();
							}

							// TODO gamerforEA code replace, old code:
							// player.addChatMessage(new ChatComponentText("§5§o" + StatCollector.translateToLocal("warp.text.2")));
							player.addChatComponentMessage(italic(color(translate("warp.text.2"), EnumChatFormatting.DARK_PURPLE)));
							// TODO gamerforEA code end
						}
						else if (eff <= 84)
						{
							grantResearch(player, warp / 10);
							// TODO gamerforEA code replace, old code:
							// player.addChatMessage(new ChatComponentText("§5§o" + StatCollector.translateToLocal("warp.text.3")));
							player.addChatComponentMessage(italic(color(translate("warp.text.3"), EnumChatFormatting.DARK_PURPLE)));
							// TODO gamerforEA code end
						}
						else if (eff > 88)
							if (eff <= 92)
								suddenlySpiders(player, warp, true);
							else
								spawnMist(player, warp, warp / 15);
					}

			if (actualwarp > 10 && !ThaumcraftApiHelper.isResearchComplete(player.getCommandSenderName(), "BATHSALTS") && !ThaumcraftApiHelper.isResearchComplete(player.getCommandSenderName(), "@BATHSALTS"))
			{
				// TODO gamerforEA code replace, old code:
				// player.addChatMessage(new ChatComponentText("§5§o" + StatCollector.translateToLocal("warp.text.8")));
				player.addChatComponentMessage(italic(color(translate("warp.text.8"), EnumChatFormatting.DARK_PURPLE)));
				// TODO gamerforEA code end

				PacketHandler.INSTANCE.sendTo(new PacketResearchComplete("@BATHSALTS"), (EntityPlayerMP) player);
				Thaumcraft.proxy.getResearchManager().completeResearch(player, "@BATHSALTS");
			}

			if (actualwarp > 25 && !ThaumcraftApiHelper.isResearchComplete(player.getCommandSenderName(), "ELDRITCHMINOR"))
			{
				grantResearch(player, 10);
				PacketHandler.INSTANCE.sendTo(new PacketResearchComplete("ELDRITCHMINOR"), (EntityPlayerMP) player);
				Thaumcraft.proxy.getResearchManager().completeResearch(player, "ELDRITCHMINOR");
			}

			if (actualwarp > 50 && !ThaumcraftApiHelper.isResearchComplete(player.getCommandSenderName(), "ELDRITCHMAJOR"))
			{
				grantResearch(player, 20);
				PacketHandler.INSTANCE.sendTo(new PacketResearchComplete("ELDRITCHMAJOR"), (EntityPlayerMP) player);
				Thaumcraft.proxy.getResearchManager().completeResearch(player, "ELDRITCHMAJOR");
			}
		}

		Thaumcraft.proxy.getPlayerKnowledge().addWarpTemp(player.getCommandSenderName(), -1);
		PacketHandler.INSTANCE.sendTo(new PacketSyncWarp(player, (byte) 2), (EntityPlayerMP) player);
	}

	private static void spawnMist(EntityPlayer player, int warp, int guardian)
	{
		PacketHandler.INSTANCE.sendTo(new PacketMiscEvent((short) 1), (EntityPlayerMP) player);
		if (guardian > 0)
		{
			guardian = Math.min(8, guardian);

			for (int a = 0; a < guardian; ++a)
			{
				spawnGuardian(player);
			}
		}

		// TODO gamerforEA code replace, old code:
		// player.addChatMessage(new ChatComponentText("§5§o" + StatCollector.translateToLocal("warp.text.6")));
		player.addChatComponentMessage(italic(color(translate("warp.text.6"), EnumChatFormatting.DARK_PURPLE)));
		// TODO gamerforEA code end
	}

	private static void grantResearch(EntityPlayer player, int times)
	{
		int amt = 1 + player.worldObj.rand.nextInt(times);

		for (int a = 0; a < amt; ++a)
		{
			Aspect aspect = Aspect.getPrimalAspects().get(player.worldObj.rand.nextInt(6));
			Thaumcraft.proxy.playerKnowledge.addAspectPool(player.getCommandSenderName(), aspect, (short) 1);
			PacketHandler.INSTANCE.sendTo(new PacketAspectPool(aspect.getTag(), (short) 1, Thaumcraft.proxy.playerKnowledge.getAspectPoolFor(player.getCommandSenderName(), aspect)), (EntityPlayerMP) player);
		}

		ResearchManager.scheduleSave(player);
	}

	private static void spawnGuardian(EntityPlayer player)
	{
		EntityEldritchGuardian eg = new EntityEldritchGuardian(player.worldObj);
		int i = MathHelper.floor_double(player.posX);
		int j = MathHelper.floor_double(player.posY);
		int k = MathHelper.floor_double(player.posZ);

		for (int l = 0; l < 50; ++l)
		{
			int i1 = i + MathHelper.getRandomIntegerInRange(player.worldObj.rand, 7, 24) * MathHelper.getRandomIntegerInRange(player.worldObj.rand, -1, 1);
			int j1 = j + MathHelper.getRandomIntegerInRange(player.worldObj.rand, 7, 24) * MathHelper.getRandomIntegerInRange(player.worldObj.rand, -1, 1);
			int k1 = k + MathHelper.getRandomIntegerInRange(player.worldObj.rand, 7, 24) * MathHelper.getRandomIntegerInRange(player.worldObj.rand, -1, 1);
			if (World.doesBlockHaveSolidTopSurface(player.worldObj, i1, j1 - 1, k1))
			{
				eg.setPosition(i1, j1, k1);
				if (player.worldObj.checkNoEntityCollision(eg.boundingBox) && player.worldObj.getCollidingBoundingBoxes(eg, eg.boundingBox).isEmpty() && !player.worldObj.isAnyLiquid(eg.boundingBox))
				{
					eg.setTarget(player);
					eg.setAttackTarget(player);
					player.worldObj.spawnEntityInWorld(eg);
					break;
				}
			}
		}

	}

	private static void suddenlySpiders(EntityPlayer player, int warp, boolean real)
	{
		int spawns = Math.min(50, warp);
		int a = 0;

		while (a < spawns)
		{
			EntityMindSpider spider = new EntityMindSpider(player.worldObj);
			int i = MathHelper.floor_double(player.posX);
			int j = MathHelper.floor_double(player.posY);
			int k = MathHelper.floor_double(player.posZ);
			boolean success = false;
			int l = 0;

			while (true)
			{
				if (l < 50)
					label46:
							{
								int i1 = i + MathHelper.getRandomIntegerInRange(player.worldObj.rand, 7, 24) * MathHelper.getRandomIntegerInRange(player.worldObj.rand, -1, 1);
								int j1 = j + MathHelper.getRandomIntegerInRange(player.worldObj.rand, 7, 24) * MathHelper.getRandomIntegerInRange(player.worldObj.rand, -1, 1);
								int k1 = k + MathHelper.getRandomIntegerInRange(player.worldObj.rand, 7, 24) * MathHelper.getRandomIntegerInRange(player.worldObj.rand, -1, 1);
								if (World.doesBlockHaveSolidTopSurface(player.worldObj, i1, j1 - 1, k1))
								{
									spider.setPosition(i1, j1, k1);
									if (player.worldObj.checkNoEntityCollision(spider.boundingBox) && player.worldObj.getCollidingBoundingBoxes(spider, spider.boundingBox).isEmpty() && !player.worldObj.isAnyLiquid(spider.boundingBox))
									{
										success = true;
										break label46;
									}
								}

								++l;
								continue;
							}

				if (success)
				{
					spider.setTarget(player);
					spider.setAttackTarget(player);
					if (!real)
					{
						spider.setViewer(player.getCommandSenderName());
						spider.setHarmless(true);
					}

					player.worldObj.spawnEntityInWorld(spider);
				}

				++a;
				break;
			}
		}

		// TODO gamerforEA code replace, old code:
		// player.addChatMessage(new ChatComponentText("§5§o" + StatCollector.translateToLocal("warp.text.7")));
		player.addChatComponentMessage(italic(color(translate("warp.text.7"), EnumChatFormatting.DARK_PURPLE)));
		// TODO gamerforEA code end
	}

	public static void checkDeathGaze(EntityPlayer player)
	{
		/* TODO gamerforEA code clear:
		PotionEffect pe = player.getActivePotionEffect(Potion.potionTypes[Config.potionDeathGazeID]);
		if (pe != null)
		{
			int level = pe.getAmplifier();
			int range = Math.min(8 + level * 3, 24);
			List list = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(range, range, range));

			for (int i = 0; i < list.size(); ++i)
			{
				Entity entity = (Entity) list.get(i);
				if (entity.canBeCollidedWith() && entity instanceof EntityLivingBase && ((EntityLivingBase) entity).isEntityAlive() && EntityUtils.isVisibleTo(0.75F, player, entity, range) && entity != null && player.canEntityBeSeen(entity) && (!(entity instanceof EntityPlayer) || MinecraftServer.getServer().isPVPEnabled()) && !((EntityLivingBase) entity).isPotionActive(Potion.wither.getId()))
				{
					((EntityLivingBase) entity).setRevengeTarget(player);
					((EntityLivingBase) entity).setLastAttacker(player);
					if (entity instanceof EntityCreature)
						((EntityCreature) entity).setTarget(player);

					((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.wither.getId(), 80));
				}
			}
		} */
	}

	private static int getWarpFromGear(EntityPlayer player)
	{
		int w = EventHandlerRunic.getFinalWarp(player.getCurrentEquippedItem(), player);

		for (int baubles = 0; baubles < 4; ++baubles)
		{
			w += EventHandlerRunic.getFinalWarp(player.inventory.armorItemInSlot(baubles), player);
		}

		IInventory var4 = BaublesApi.getBaubles(player);

		for (int a = 0; a < 4; ++a)
		{
			w += EventHandlerRunic.getFinalWarp(var4.getStackInSlot(a), player);
		}

		return w;
	}
}
