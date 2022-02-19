package thaumcraft.common.lib.events;

import baubles.api.BaublesApi;
import com.gamerforea.eventhelper.util.EventUtils;
import com.gamerforea.eventhelper.util.ExplosionByPlayer;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import thaumcraft.api.IRunicArmor;
import thaumcraft.api.IWarpingGear;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.entities.IEldritchMob;
import thaumcraft.common.config.Config;
import thaumcraft.common.entities.monster.mods.ChampionModifier;
import thaumcraft.common.items.armor.ItemFortressArmor;
import thaumcraft.common.items.baubles.ItemAmuletRunic;
import thaumcraft.common.items.baubles.ItemGirdleRunic;
import thaumcraft.common.items.baubles.ItemRingRunic;
import thaumcraft.common.items.wands.WandManager;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.fx.PacketFXShield;
import thaumcraft.common.lib.network.playerdata.PacketRunicCharge;
import thaumcraft.common.lib.utils.EntityUtils;

import java.util.HashMap;

public class EventHandlerRunic
{
	public HashMap<Integer, Integer> runicCharge = new HashMap<>();
	private HashMap<Integer, Long> nextCycle = new HashMap<>();
	private HashMap<Integer, Integer> lastCharge = new HashMap<>();
	public HashMap<Integer, Integer[]> runicInfo = new HashMap<>();
	private HashMap<String, Long> upgradeCooldown = new HashMap<>();
	public boolean isDirty = true;
	private int rechargeDelay = 0;

	@SubscribeEvent
	public void livingTick(LivingUpdateEvent event)
	{
		if (!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.entity;
			int playerId = player.getEntityId();

			if (this.isDirty || player.ticksExisted % 40 == 0)
			{
				int max = 0;
				int charged = 0;
				int kinetic = 0;
				int healing = 0;
				int emergency = 0;
				this.isDirty = false;

				for (int a = 0; a < 4; ++a)
				{
					if (player.inventory.armorItemInSlot(a) != null && player.inventory.armorItemInSlot(a).getItem() instanceof IRunicArmor)
					{
						int amount = getFinalCharge(player.inventory.armorItemInSlot(a));
						max += amount;
					}
				}

				IInventory baubles = BaublesApi.getBaubles(player);

				for (int a = 0; a < 4; ++a)
				{
					ItemStack baubleStack = baubles.getStackInSlot(a);
					if (baubleStack != null)
					{
						Item baubleItem = baubleStack.getItem();
						if (baubleItem instanceof IRunicArmor)
						{
							int amount = getFinalCharge(baubleStack);
							if (baubleItem instanceof ItemRingRunic)
								switch (baubleStack.getItemDamage())
								{
									case 2:
										++charged;
										break;
									case 3:
										++healing;
								}
							else if (baubleItem instanceof ItemAmuletRunic && baubleStack.getItemDamage() == 1)
								++emergency;
							else if (baubleItem instanceof ItemGirdleRunic && baubleStack.getItemDamage() == 1)
								++kinetic;

							max += amount;
						}
					}
				}

				if (max > 0)
				{
					this.runicInfo.put(playerId, new Integer[] { max, charged, kinetic, healing, emergency });
					if (this.runicCharge.containsKey(playerId))
					{
						int charge = this.runicCharge.get(playerId);
						if (charge > max)
						{
							this.runicCharge.put(playerId, max);
							PacketHandler.INSTANCE.sendTo(new PacketRunicCharge(player, (short) max, max), (EntityPlayerMP) player);
						}
					}
				}
				else
				{
					this.runicInfo.remove(playerId);
					this.runicCharge.put(playerId, 0);
					PacketHandler.INSTANCE.sendTo(new PacketRunicCharge(player, (short) 0, 0), (EntityPlayerMP) player);
				}
			}

			if (this.rechargeDelay > 0)
				--this.rechargeDelay;
			else if (this.runicInfo.containsKey(playerId))
			{
				if (!this.lastCharge.containsKey(playerId))
					this.lastCharge.put(playerId, -1);

				if (!this.runicCharge.containsKey(playerId))
					this.runicCharge.put(playerId, 0);

				if (!this.nextCycle.containsKey(playerId))
					this.nextCycle.put(playerId, 0L);

				long time = System.currentTimeMillis();
				int charge = this.runicCharge.get(playerId);
				if (charge > this.runicInfo.get(playerId)[0])
					charge = this.runicInfo.get(playerId)[0];
				else if (charge < this.runicInfo.get(playerId)[0] && this.nextCycle.get(playerId) < time && WandManager.consumeVisFromInventory(player, new AspectList().add(Aspect.AIR, Config.shieldCost).add(Aspect.EARTH, Config.shieldCost)))
				{
					long interval = (long) (Config.shieldRecharge - this.runicInfo.get(playerId)[1] * 500);
					this.nextCycle.put(playerId, time + interval);
					++charge;
					this.runicCharge.put(playerId, charge);
				}

				if (this.lastCharge.get(playerId) != charge)
				{
					PacketHandler.INSTANCE.sendTo(new PacketRunicCharge(player, (short) charge, this.runicInfo.get(playerId)[0]), (EntityPlayerMP) player);
					this.lastCharge.put(playerId, charge);
				}
			}
		}

	}

	@SubscribeEvent
	public void entityHurt(LivingHurtEvent event)
	{
		Entity damager = event.source.getEntity();
		Entity victim = event.entity;

		// TODO gamerforEA code start
		if ("thorns".equals(event.source.getDamageType()))
			return;
		if (damager != null && victim != null & EventUtils.cantDamage(damager, victim))
			return;
		// TODO gamerforEA code end

		Entity sourceOfDamage = event.source.getSourceOfDamage();
		if (sourceOfDamage instanceof EntityPlayer)
		{
			EntityPlayer attacker = (EntityPlayer) sourceOfDamage;
			ItemStack helm = attacker.inventory.armorInventory[3];
			if (helm != null && helm.getItem() instanceof ItemFortressArmor && helm.hasTagCompound() && helm.stackTagCompound.hasKey("mask") && helm.stackTagCompound.getInteger("mask") == 2 && attacker.worldObj.rand.nextFloat() < event.ammount / 12.0F)
				attacker.heal(1.0F);
		}

		if (victim instanceof EntityPlayer)
		{
			long time = System.currentTimeMillis();
			EntityPlayer player = (EntityPlayer) victim;
			if (sourceOfDamage instanceof EntityLivingBase)
			{
				EntityLivingBase attacker = (EntityLivingBase) sourceOfDamage;
				ItemStack helm = player.inventory.armorInventory[3];
				if (helm != null && helm.getItem() instanceof ItemFortressArmor && helm.hasTagCompound() && helm.stackTagCompound.hasKey("mask") && helm.stackTagCompound.getInteger("mask") == 1 && player.worldObj.rand.nextFloat() < event.ammount / 10.0F)
					try
					{
						attacker.addPotionEffect(new PotionEffect(Potion.wither.getId(), 80));
					}
					catch (Exception ignored)
					{
					}
			}

			if (event.source == DamageSource.drown || event.source == DamageSource.wither || event.source == DamageSource.outOfWorld || event.source == DamageSource.starve)
				return;

			int playerId = player.getEntityId();
			if (this.runicInfo.containsKey(playerId) && this.runicCharge.containsKey(playerId) && this.runicCharge.get(playerId) > 0)
			{
				int target = -1;
				if (damager != null)
					target = damager.getEntityId();

				if (event.source == DamageSource.fall)
					target = -2;

				if (event.source == DamageSource.fallingBlock)
					target = -3;

				PacketHandler.INSTANCE.sendToAllAround(new PacketFXShield(victim.getEntityId(), target), new TargetPoint(victim.worldObj.provider.dimensionId, victim.posX, victim.posY, victim.posZ, 64.0D));
				int charge = this.runicCharge.get(playerId);
				if ((float) charge > event.ammount)
				{
					charge = (int) ((float) charge - event.ammount);
					event.ammount = 0.0F;
				}
				else
				{
					event.ammount -= (float) charge;
					charge = 0;
				}

				String key = playerId + ":" + 2;
				if (charge <= 0 && this.runicInfo.get(playerId)[2] > 0 && (!this.upgradeCooldown.containsKey(key) || this.upgradeCooldown.get(key) < time))
				{
					this.upgradeCooldown.put(key, time + 20000L);

					// TODO gamerforEA use ExplosionByPlayer
					ExplosionByPlayer.newExplosion(player, player.worldObj, player, player.posX, player.posY + (double) (player.height / 2.0F), player.posZ, 1.5F + (float) this.runicInfo.get(playerId)[2] * 0.5F, false, false);
				}

				key = playerId + ":" + 3;
				if (charge <= 0 && this.runicInfo.get(playerId)[3] > 0 && (!this.upgradeCooldown.containsKey(key) || this.upgradeCooldown.get(key) < time))
				{
					this.upgradeCooldown.put(key, time + 20000L);
					synchronized (player)
					{
						try
						{
							player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 240, this.runicInfo.get(playerId)[3]));
						}
						catch (Exception ignored)
						{
						}
					}

					player.worldObj.playSoundAtEntity(player, "thaumcraft:runicShieldEffect", 1.0F, 1.0F);
				}

				key = playerId + ":" + 4;
				if (charge <= 0 && this.runicInfo.get(playerId)[4] > 0 && (!this.upgradeCooldown.containsKey(key) || this.upgradeCooldown.get(key) < time))
				{
					this.upgradeCooldown.put(key, time + 60000L);
					int t = 8 * this.runicInfo.get(playerId)[4];
					charge = Math.min(this.runicInfo.get(playerId)[0], t);
					this.isDirty = true;
					player.worldObj.playSoundAtEntity(player, "thaumcraft:runicShieldCharge", 1.0F, 1.0F);
				}

				if (charge <= 0)
					this.rechargeDelay = Config.shieldWait;

				this.runicCharge.put(playerId, charge);
				PacketHandler.INSTANCE.sendTo(new PacketRunicCharge(player, (short) charge, this.runicInfo.get(playerId)[0]), (EntityPlayerMP) player);
			}
		}
		else if (victim instanceof EntityMob && (((EntityMob) victim).getEntityAttribute(EntityUtils.CHAMPION_MOD).getAttributeValue() >= 0.0D || victim instanceof IEldritchMob))
		{
			EntityMob mob = (EntityMob) victim;
			int t = (int) ((EntityMob) victim).getEntityAttribute(EntityUtils.CHAMPION_MOD).getAttributeValue();
			if ((t == 5 || victim instanceof IEldritchMob) && mob.getAbsorptionAmount() > 0.0F)
			{
				int target = -1;
				if (damager != null)
					target = damager.getEntityId();

				if (event.source == DamageSource.fall)
					target = -2;

				if (event.source == DamageSource.fallingBlock)
					target = -3;

				PacketHandler.INSTANCE.sendToAllAround(new PacketFXShield(mob.getEntityId(), target), new TargetPoint(victim.worldObj.provider.dimensionId, victim.posX, victim.posY, victim.posZ, 32.0D));
				victim.worldObj.playSoundEffect(victim.posX, victim.posY, victim.posZ, "thaumcraft:runicShieldEffect", 0.66F, 1.1F + victim.worldObj.rand.nextFloat() * 0.1F);
			}
			else if (t >= 0 && ChampionModifier.mods[t].type == 2 && sourceOfDamage instanceof EntityLivingBase)
			{
				EntityLivingBase attacker = (EntityLivingBase) sourceOfDamage;
				event.ammount = ChampionModifier.mods[t].effect.performEffect(mob, attacker, event.source, event.ammount);
			}
		}

		if (event.ammount > 0.0F && victim instanceof EntityLivingBase && sourceOfDamage instanceof EntityMob && ((EntityMob) sourceOfDamage).getEntityAttribute(EntityUtils.CHAMPION_MOD).getAttributeValue() >= 0.0D)
		{
			EntityMob mob = (EntityMob) sourceOfDamage;
			int t = (int) mob.getEntityAttribute(EntityUtils.CHAMPION_MOD).getAttributeValue();
			if (ChampionModifier.mods[t].type == 1)
				event.ammount = ChampionModifier.mods[t].effect.performEffect(mob, (EntityLivingBase) victim, event.source, event.ammount);
		}

	}

	@SubscribeEvent
	public void tooltipEvent(ItemTooltipEvent event)
	{
		int charge = getFinalCharge(event.itemStack);
		if (charge > 0)
			event.toolTip.add(EnumChatFormatting.GOLD + StatCollector.translateToLocal("item.runic.charge") + " +" + charge);

		int warp = getFinalWarp(event.itemStack, event.entityPlayer);
		if (warp > 0)
			event.toolTip.add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal("item.warping") + " " + warp);

	}

	public static int getFinalCharge(ItemStack stack)
	{
		if (!(stack.getItem() instanceof IRunicArmor))
			return 0;
		IRunicArmor armor = (IRunicArmor) stack.getItem();
		int base = armor.getRunicCharge(stack);
		if (stack.hasTagCompound() && stack.stackTagCompound.hasKey("RS.HARDEN"))
			base += stack.stackTagCompound.getByte("RS.HARDEN");

		return base;
	}

	public static int getFinalWarp(ItemStack stack, EntityPlayer player)
	{
		if (stack != null && stack.getItem() instanceof IWarpingGear)
		{
			IWarpingGear armor = (IWarpingGear) stack.getItem();
			return armor.getWarp(stack, player);
		}
		return 0;
	}

	public static int getHardening(ItemStack stack)
	{
		if (!(stack.getItem() instanceof IRunicArmor))
			return 0;
		int base = 0;
		if (stack.hasTagCompound() && stack.stackTagCompound.hasKey("RS.HARDEN"))
			base += stack.stackTagCompound.getByte("RS.HARDEN");

		return base;
	}
}
