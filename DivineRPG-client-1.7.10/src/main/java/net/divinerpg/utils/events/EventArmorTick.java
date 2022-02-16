/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.ObfuscationReflectionHelper
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.gameevent.TickEvent$PlayerTickEvent
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.monster.EntityMob
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.PlayerCapabilities
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.stats.StatBase
 *  net.minecraft.world.World
 */
package net.divinerpg.utils.events;

import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import java.util.List;
import net.divinerpg.libs.DivineRPGAchievements;
import net.divinerpg.utils.FlyingHelper;
import net.divinerpg.utils.config.ConfigurationHelper;
import net.divinerpg.utils.events.ArcanaHelper;
import net.divinerpg.utils.events.EventArmorFullSet;
import net.divinerpg.utils.events.Ticker;
import net.divinerpg.utils.items.ArcanaItems;
import net.divinerpg.utils.items.IceikaItems;
import net.divinerpg.utils.items.TwilightItemsArmor;
import net.divinerpg.utils.items.VanillaItemsArmor;
import net.divinerpg.utils.items.VetheaItems;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatBase;
import net.minecraft.world.World;

public class EventArmorTick {
    private float flyTemp;
    private Item boots = null;
    private Item body = null;
    private Item legs = null;
    private Item helmet = null;
    public static final String[] isImmuneToFire = new String[]{"ae", "isImmuneToFire", "isImmuneToFire"};
    public static final String[] isJumping = new String[]{"bc", "isJumping", "isJumping"};
    public static final String[] walkSpeed = new String[]{"g", "walkSpeed", "walkSpeed"};
    private World world;

    @SubscribeEvent
    public void onTickEvent(TickEvent.PlayerTickEvent evt) {
        float current;
        this.world = evt.player.worldObj;
        ItemStack stackBoots = evt.player.inventory.armorItemInSlot(0);
        ItemStack stackLegs = evt.player.inventory.armorItemInSlot(1);
        ItemStack stackBody = evt.player.inventory.armorItemInSlot(2);
        ItemStack stackHelmet = evt.player.inventory.armorItemInSlot(3);
        float speedMultiplier = 1.0f;
        this.boots = stackBoots != null ? stackBoots.getItem() : null;
        this.body = stackBody != null ? stackBody.getItem() : null;
        this.legs = stackLegs != null ? stackLegs.getItem() : null;
        this.helmet = stackHelmet != null ? stackHelmet.getItem() : null;
        if (this.boots != VanillaItemsArmor.angelicBoots && this.body != VanillaItemsArmor.angelicBody && this.legs != VanillaItemsArmor.angelicLegs && this.helmet != VanillaItemsArmor.angelicHelmet) {
            FlyingHelper.getProperties((EntityPlayer)evt.player).couldFly = evt.player.capabilities.allowFlying;
        }
        if (evt.player.capabilities.isCreativeMode) {
            FlyingHelper.getProperties((EntityPlayer)evt.player).couldFly = false;
        }
        if (this.boots == VanillaItemsArmor.angelicBoots && this.body == VanillaItemsArmor.angelicBody && this.legs == VanillaItemsArmor.angelicLegs && this.helmet == VanillaItemsArmor.angelicHelmet && ArcanaHelper.getProperties(evt.player).getBarValue() != 0.0f) {
            evt.player.fallDistance = -0.5f;
            evt.player.triggerAchievement((StatBase)DivineRPGAchievements.whenPigsFly);
            evt.player.capabilities.allowFlying = true;
            if (evt.player.capabilities.isFlying && !evt.player.capabilities.isCreativeMode && !FlyingHelper.getProperties((EntityPlayer)evt.player).couldFly) {
                ArcanaHelper.getProperties(evt.player).useBar(0.5f);
            }
            if (ArcanaHelper.getProperties(evt.player).getBarValue() < 1.0f && !evt.player.capabilities.isCreativeMode && !FlyingHelper.getProperties((EntityPlayer)evt.player).couldFly) {
                evt.player.capabilities.isFlying = false;
                evt.player.capabilities.allowFlying = false;
            }
        } else if (evt.player.capabilities.allowFlying && !evt.player.capabilities.isCreativeMode && !FlyingHelper.getProperties((EntityPlayer)evt.player).couldFly) {
            evt.player.capabilities.isFlying = false;
            evt.player.capabilities.allowFlying = false;
        }
        if (this.boots == VanillaItemsArmor.eliteRealmiteBoots && this.body == VanillaItemsArmor.eliteRealmiteBody && this.legs == VanillaItemsArmor.eliteRealmiteLegs && this.helmet == VanillaItemsArmor.eliteRealmiteHelmet) {
            evt.player.fallDistance = -0.5f;
        }
        if (this.boots == VanillaItemsArmor.divineBoots && this.body == VanillaItemsArmor.divineBody && this.legs == VanillaItemsArmor.divineLegs && this.helmet == VanillaItemsArmor.divineHelmet) {
            evt.player.fallDistance = -0.5f;
        }
        if (this.boots == TwilightItemsArmor.wildwoodBoots && this.body == TwilightItemsArmor.wildwoodChestplate && this.legs == TwilightItemsArmor.wildwoodLeggings && this.helmet == TwilightItemsArmor.wildwoodHelmet && evt.player.isInsideOfMaterial(Material.water) && (current = evt.player.getHealth()) > 0.0f && current < 20.0f) {
            evt.player.heal(0.25f);
        }
        if (this.boots == ArcanaItems.kormaBoots && this.body == ArcanaItems.kormaBody && this.legs == ArcanaItems.kormaLegs && this.helmet == ArcanaItems.kormaHelmet) {
            ArcanaHelper.getProperties(evt.player).regen(1.0f);
        }
        if (this.boots == ArcanaItems.vemosBoots && this.body == ArcanaItems.vemosBody && this.legs == ArcanaItems.vemosLegs && this.helmet == ArcanaItems.vemosHelmet && (current = evt.player.getHealth()) > 0.0f && current < 20.0f) {
            evt.player.setHealth(current + 0.1f);
        }
        if (this.boots == TwilightItemsArmor.mortumBoots && this.body == TwilightItemsArmor.mortumChestplate && this.legs == TwilightItemsArmor.mortumLeggings && this.helmet == TwilightItemsArmor.mortumHelmet) {
            evt.player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 210, 10, true));
        }
        if (this.boots == TwilightItemsArmor.skythernBoots && this.body == TwilightItemsArmor.skythernChestplate && this.legs == TwilightItemsArmor.skythernLeggings && this.helmet == TwilightItemsArmor.skythernHelmet) {
            evt.player.fallDistance = -0.5f;
        }
        if (this.boots == VanillaItemsArmor.netheriteBoots && this.legs == VanillaItemsArmor.netheriteLegs && this.body == VanillaItemsArmor.netheriteBody && this.helmet == VanillaItemsArmor.netheriteHelmet || this.boots == VanillaItemsArmor.infernoBoots && this.legs == VanillaItemsArmor.infernoLegs && this.body == VanillaItemsArmor.infernoBody && this.helmet == VanillaItemsArmor.infernoHelmet || this.boots == VanillaItemsArmor.bedrockBoots && this.legs == VanillaItemsArmor.bedrockLegs && this.body == VanillaItemsArmor.bedrockBody && this.helmet == VanillaItemsArmor.bedrockHelmet) {
            evt.player.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 40, 0, true));
        }
        if (this.boots == VanillaItemsArmor.aquastriveBoots && this.body == VanillaItemsArmor.aquastriveBody && this.legs == VanillaItemsArmor.aquastriveLegs && this.helmet == VanillaItemsArmor.aquastriveHelmet) {
            float speed = 1.1f;
            boolean isJumping = false;
            isJumping = (Boolean) ObfuscationReflectionHelper.getPrivateValue(EntityLivingBase.class, evt.player, EventArmorFullSet.isJumping);
            if (evt.player.isInWater()) {
                if (!evt.player.isSneaking() && !isJumping) {
                    if (evt.player.motionX > (double)(-speed) && evt.player.motionX < (double)speed) {
                        evt.player.motionX *= (double)speed;
                        evt.player.motionY = 0.0;
                    }
                    if (evt.player.motionZ > (double)(-speed) && evt.player.motionZ < (double)speed) {
                        evt.player.motionZ *= (double)speed;
                        evt.player.motionY = 0.0;
                    }
                }
                if (isJumping || evt.player.isSneaking()) {
                    evt.player.motionY *= (double)speed;
                    if (evt.player.motionX > (double)(-speed) && evt.player.motionX < (double)speed) {
                        evt.player.motionX *= (double)speed;
                    }
                    if (evt.player.motionZ > (double)(-speed) && evt.player.motionZ < (double)speed) {
                        evt.player.motionZ *= (double)speed;
                    }
                }
            }
        }
        if (this.boots == VanillaItemsArmor.shadowBoots && this.body == VanillaItemsArmor.shadowBody && this.legs == VanillaItemsArmor.shadowLegs && this.helmet == VanillaItemsArmor.shadowHelmet) {
            speedMultiplier = 3.0f;
        }
        if (this.boots == VanillaItemsArmor.frozenBoots && this.body == VanillaItemsArmor.frozenBody && this.legs == VanillaItemsArmor.frozenLegs && this.helmet == VanillaItemsArmor.frozenHelmet && !evt.player.worldObj.isRemote && Ticker.tick % 10 == 0) {
            List<Entity> entities = evt.player.worldObj.getEntitiesWithinAABB(EntityMob.class, evt.player.boundingBox.expand(6.0, 6.0, 6.0));
            for (Entity e : entities) {
                ((EntityMob)e).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 40, 1, true));
            }
        }
        if (this.boots == VanillaItemsArmor.terranBoots && this.body == VanillaItemsArmor.terranBody && this.legs == VanillaItemsArmor.terranLegs && this.helmet == VanillaItemsArmor.terranHelmet) {
            evt.player.addPotionEffect(new PotionEffect(Potion.digSpeed.id, 20, 2, true));
        }
        if (this.boots == VanillaItemsArmor.skelemanBoots && this.body == VanillaItemsArmor.skelemanBody && this.legs == VanillaItemsArmor.skelemanLegs && this.helmet == VanillaItemsArmor.skelemanHelmet && evt.player.getFoodStats().needFood()) {
            evt.player.getFoodStats().addStats(1, 0.0f);
        }
        if (this.boots == IceikaItems.santaBoots && this.body == IceikaItems.santaTunic && this.legs == IceikaItems.santaPants && this.helmet == IceikaItems.santaCap && evt.player.worldObj.provider.dimensionId == ConfigurationHelper.iceika) {
            if (evt.player.getFoodStats().needFood()) {
                evt.player.getFoodStats().addStats(1, 0.0f);
            }
            speedMultiplier = 2.0f;
        }
        if (this.body == VetheaItems.glisteningBody && this.legs == VetheaItems.glisteningLegs && this.boots == VetheaItems.glisteningBoots && this.helmet == VetheaItems.glisteningMask) {
            speedMultiplier = 1.4f;
        }
        if (this.body == VetheaItems.demonizedBody && this.legs == VetheaItems.demonizedLegs && this.boots == VetheaItems.demonizedBoots && this.helmet == VetheaItems.demonizedMask) {
            speedMultiplier = 1.8f;
        }
        if (this.body == VetheaItems.tormentedBody && this.legs == VetheaItems.tormentedLegs && this.boots == VetheaItems.tormentedBoots && this.helmet == VetheaItems.tormentedMask) {
            speedMultiplier = 2.2f;
        }
        ObfuscationReflectionHelper.setPrivateValue(PlayerCapabilities.class, evt.player.capabilities, 0.1f*speedMultiplier, walkSpeed);
        if (this.body == VetheaItems.glisteningBody && this.legs == VetheaItems.glisteningLegs && this.boots == VetheaItems.glisteningBoots && this.helmet == VetheaItems.glisteningHood) {
            evt.player.fallDistance = -0.5f;
        }
        if (this.body == VetheaItems.demonizedBody && this.legs == VetheaItems.demonizedLegs && this.boots == VetheaItems.demonizedBoots && this.helmet == VetheaItems.demonizedHood) {
            evt.player.fallDistance = -0.5f;
        }
        if (this.body == VetheaItems.tormentedBody && this.legs == VetheaItems.tormentedLegs && this.boots == VetheaItems.tormentedBoots && this.helmet == VetheaItems.tormentedHood) {
            evt.player.fallDistance = -0.5f;
        }
        if (evt.player.inventory.hasItem(VetheaItems.minersAmulet)) {
            evt.player.addPotionEffect(new PotionEffect(Potion.digSpeed.id, 1, 2, true));
        }
    }
}

