/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.ObfuscationReflectionHelper
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$PlayerTickEvent
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  ru.harvax.OmegaHolograms
 */
package net.divinerpg.utils.events;

import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.divinerpg.utils.events.ArcanaHelper;
import net.divinerpg.utils.items.JourneyItemsArmor;
import net.divinerpg.utils.items.TwilightItemsArmor;
import net.divinerpg.utils.items.VanillaItemsArmor;
import net.divinerpg.utils.proxies.CommonProxy;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import ru.harvax.OmegaHolograms;

public class EventArmorTick {
    private long timer;
    private Item boots = null;
    private Item body = null;
    private Item legs = null;
    private Item helmet = null;
    public static final String[] walkSpeed = new String[]{"g", "walkSpeed", "walkSpeed"};

    static boolean FlameAttack(EntityLivingBase e, EntityPlayer givedamager) {
        e.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)givedamager), 100.0f);
        return true;
    }

    public static int onHeal(EntityLivingBase e, EntityPlayer givedamager, int secheal) {
        if (secheal > 0) {
            --secheal;
            givedamager.heal(1.0f);
        }
        if (givedamager.isDead) {
            return secheal;
        }
        return secheal;
    }

    @SubscribeEvent
    public void onTickEvent(TickEvent.PlayerTickEvent evt) {
        ItemStack stackBoots = evt.player.inventory.armorItemInSlot(0);
        ItemStack stackLegs = evt.player.inventory.armorItemInSlot(1);
        ItemStack stackBody = evt.player.inventory.armorItemInSlot(2);
        ItemStack stackHelmet = evt.player.inventory.armorItemInSlot(3);
        float speedMultiplier = 1.0f;
        this.boots = stackBoots != null ? stackBoots.getItem() : null;
        this.body = stackBody != null ? stackBody.getItem() : null;
        this.legs = stackLegs != null ? stackLegs.getItem() : null;
        Item item = this.helmet = stackHelmet != null ? stackHelmet.getItem() : null;
        if (this.boots == JourneyItemsArmor.flameBoots && this.body == JourneyItemsArmor.flameChestPlate && this.legs == JourneyItemsArmor.flameLeggings && this.helmet == JourneyItemsArmor.flameHelmet) {
            evt.player.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 40, 0, true));
            if (evt.player.worldObj.getTotalWorldTime() % 800L == 0L) {
                for (Object obj : evt.player.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, evt.player.boundingBox.expand(5.0, 5.0, 5.0))) {
                    EntityLivingBase e = (EntityLivingBase)obj;
                    if (obj.equals(evt.player) || e instanceof EntityPlayer && ((EntityPlayer)e).capabilities.isCreativeMode) continue;
                    e.setFire(2);
                    if (!EventArmorTick.FlameAttack(e, evt.player)) continue;
                    EventArmorTick.FlameAttack(e, evt.player);
                    if (!(evt.player.getHealth() < evt.player.getHealth() * 40.0f / 100.0f) || !EventArmorTick.FlameAttack(e, evt.player)) continue;
                    EventArmorTick.onHeal(e, evt.player, 10);
                    OmegaHolograms.INSTANCE.sendText("\u041e\u0433\u043d\u0435\u043d\u043d\u043e\u0435 \u043a\u0430\u0441\u0430\u043d\u0438\u0435", 15562254, (Entity)e);
                }
            }
        }
        if (this.boots == TwilightItemsArmor.mortumBoots && this.body == TwilightItemsArmor.mortumChestplate && this.legs == TwilightItemsArmor.mortumLeggings && this.helmet == TwilightItemsArmor.mortumHelmet) {
            evt.player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 210, 10, true));
        }
        if (this.boots == VanillaItemsArmor.netheriteBoots && this.legs == VanillaItemsArmor.netheriteLegs && this.body == VanillaItemsArmor.netheriteBody && this.helmet == VanillaItemsArmor.netheriteHelmet || this.boots == VanillaItemsArmor.infernoBoots && this.legs == VanillaItemsArmor.infernoLegs && this.body == VanillaItemsArmor.infernoBody && this.helmet == VanillaItemsArmor.infernoHelmet || this.boots == VanillaItemsArmor.bedrockBoots && this.legs == VanillaItemsArmor.bedrockLegs && this.body == VanillaItemsArmor.bedrockBody && this.helmet == VanillaItemsArmor.bedrockHelmet || this.boots == VanillaItemsArmor.nightmareBoots && this.legs == VanillaItemsArmor.nightmareLeggings && this.body == VanillaItemsArmor.nightmareChestplate && this.helmet == VanillaItemsArmor.nightmareHelmet || this.boots == VanillaItemsArmor.netheriumBoots && this.legs == VanillaItemsArmor.netheriumLeggings && this.body == VanillaItemsArmor.netheriumChestplate && this.helmet == VanillaItemsArmor.netheriumHelmet) {
            evt.player.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 40, 0, true));
        }
        if (this.boots == VanillaItemsArmor.shadowBoots && this.body == VanillaItemsArmor.shadowBody && this.legs == VanillaItemsArmor.shadowLegs && this.helmet == VanillaItemsArmor.shadowHelmet) {
            speedMultiplier = 3.0f;
        }
        if (this.boots == VanillaItemsArmor.eliteRealmiteBoots && this.body == VanillaItemsArmor.eliteRealmiteBody && this.legs == VanillaItemsArmor.eliteRealmiteLegs && this.helmet == VanillaItemsArmor.eliteRealmiteHelmet) {
            evt.player.addPotionEffect(new PotionEffect(CommonProxy.nofallPotion.id, 40, 0, true));
        }
        if (this.boots == VanillaItemsArmor.amazoniteBoots && this.body == VanillaItemsArmor.amazoniteChestplate && this.legs == VanillaItemsArmor.amazoniteLeggings && this.helmet == VanillaItemsArmor.amazoniteHelmet) {
            speedMultiplier = 2.0f;
        }
        if (this.boots == JourneyItemsArmor.bloodcrustBoots && this.legs == JourneyItemsArmor.bloodcrustLeggings && this.body == JourneyItemsArmor.bloodcrustChestplate && this.helmet == JourneyItemsArmor.bloodcrustHelmet) {
            evt.player.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 40, 0, true));
        }
        if (this.helmet == JourneyItemsArmor.lightstoneHelmet && this.body == JourneyItemsArmor.lightstoneChestplate && this.legs == JourneyItemsArmor.lightstoneLeggings && this.boots == JourneyItemsArmor.lightstoneBoots) {
            evt.player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 40, 0, true));
            evt.player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 40, 0, true));
            if (this.timer < System.currentTimeMillis()) {
                this.timer = System.currentTimeMillis() + 1000L;
                ArcanaHelper.getProperties(evt.player).setBarValue(ArcanaHelper.getProperties(evt.player).getBarValue() + 10.0f);
            }
        }
        if (this.boots == JourneyItemsArmor.celestiumBoots && this.body == JourneyItemsArmor.celestiumChestplate && this.legs == JourneyItemsArmor.celestiumLeggings && this.helmet == JourneyItemsArmor.celestiumHelmet) {
            speedMultiplier = 2.0f;
            evt.player.addPotionEffect(new PotionEffect(Potion.jump.id, 40, 1, true));
        }
        ObfuscationReflectionHelper.setPrivateValue(PlayerCapabilities.class, (Object)evt.player.capabilities, (Object)Float.valueOf(0.1f * speedMultiplier), (String[])walkSpeed);
    }
}

