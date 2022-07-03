package wendoxd.events;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.S1FPacketSetExperience;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.Clone;
import wendoxd.extendedplayer.ExtendedPlayer;
import wendoxd.init.AbilityMod;
import wendoxd.modules.Ability;
import wendoxd.utils.Util;

public class ServerEvents {

	@SubscribeEvent
	public void onPlayerConstructing(EntityEvent.EntityConstructing event) {
		EntityPlayerMP player = null;
		if (event.entity instanceof EntityPlayerMP && (player = (EntityPlayerMP) event.entity)
				.getExtendedProperties(ExtendedPlayer.PROPERTIES_NAME) == null) {
			player.registerExtendedProperties(ExtendedPlayer.PROPERTIES_NAME, new ExtendedPlayer(player));
		}
	}

	@SubscribeEvent
	public void livingUpdate(LivingUpdateEvent event) {
		if (!AbilityMod.SERVER)
			return;
		if (event.entity instanceof EntityPlayerMP && event.entity.ticksExisted % 40 == 0) {
			Ability a = null;
			if ((a = ExtendedPlayer.get((EntityPlayer) event.entity).getUnlocked(Ability.REGEN)) != null) {
				((EntityPlayerMP) event.entity).heal((float) Ability.getModifier(Ability.REGEN, a));
			}
		}
	}

	@SubscribeEvent
	public void onClonePlayer(Clone event) {
		if (event.entityPlayer instanceof EntityPlayerMP)
			ExtendedPlayer.get(event.entityPlayer).copyNBT(ExtendedPlayer.get(event.original));
	}

	@SubscribeEvent
	public void livingHurt(LivingHurtEvent event) {
		if (!AbilityMod.SERVER)
			return;
		if (event.source.getSourceOfDamage() instanceof EntityPlayer) {
			Ability a, b = null;
			if ((b = ExtendedPlayer.get((EntityPlayer) event.source.getSourceOfDamage())
					.getUnlocked(Ability.FASTCLICK)) != null) {
				int factor = (int) Ability.getModifier(Ability.FASTCLICK, b);
				event.entityLiving.hurtResistantTime = 20 - factor;
			}
			if ((a = ExtendedPlayer.get((EntityPlayer) event.source.getSourceOfDamage())
					.getUnlocked(Ability.FORCE)) != null) {
				double factor = Ability.getModifier(Ability.FORCE, a);
				event.ammount *= factor;
			}
		}
		if (event.entity instanceof EntityPlayer) {
			Ability a, b = null;
			if ((b = ExtendedPlayer.get((EntityPlayer) event.entity).getUnlocked(Ability.AGILITY)) != null) {
				Random rand = new Random();
				int operand = rand.nextInt(100);
				if (operand < Ability.getModifier(Ability.AGILITY, b)) {
					((EntityPlayer) event.entity).addChatComponentMessage(
							new ChatComponentText(EnumChatFormatting.BLUE + "Вы увернулись от удара"));
					event.setCanceled(true);
					return;
				}
			}
			if ((a = ExtendedPlayer.get((EntityPlayer) event.entity).getUnlocked(Ability.PROTECTION)) != null) {
				event.ammount /= (float) Ability.getModifier(Ability.PROTECTION, a);
			}
		}
	}
}
