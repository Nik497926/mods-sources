/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.World
 *  net.minecraftforge.event.entity.living.LivingAttackEvent
 *  net.minecraftforge.event.entity.living.LivingEvent$LivingJumpEvent
 *  net.minecraftforge.event.entity.living.LivingHurtEvent
 *  net.minecraftforge.event.world.BlockEvent$HarvestDropsEvent
 */
package net.divinerpg.utils.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.divinerpg.blocks.base.BlockMod;
import net.divinerpg.utils.Util;
import net.divinerpg.utils.config.ConfigurationHelper;
import net.divinerpg.utils.items.IceikaItems;
import net.divinerpg.utils.items.TwilightItemsArmor;
import net.divinerpg.utils.items.VanillaItemsArmor;
import net.divinerpg.utils.items.VetheaItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.world.BlockEvent;

public class EventArmorFullSet {
    private float flyTemp;
    private Item boots = null;
    private Item body = null;
    private Item legs = null;
    private Item helmet = null;
    public static final String[] isImmuneToFire = new String[]{"ae", "isImmuneToFire", "isImmuneToFire"};
    public static final String[] isJumping = new String[]{"bc", "isJumping", "isJumping"};
    private World world;

    @SubscribeEvent
    public void onPlayerHurtEvent(LivingHurtEvent evt) {
        if (evt.entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)evt.entity;
            ItemStack stackBoots = player.inventory.armorItemInSlot(0);
            ItemStack stackLegs = player.inventory.armorItemInSlot(1);
            ItemStack stackBody = player.inventory.armorItemInSlot(2);
            ItemStack stackHelmet = player.inventory.armorItemInSlot(3);
            this.boots = stackBoots != null ? stackBoots.getItem() : null;
            this.body = stackBody != null ? stackBody.getItem() : null;
            this.legs = stackLegs != null ? stackLegs.getItem() : null;
            this.helmet = stackHelmet != null ? stackHelmet.getItem() : null;
            DamageSource s = evt.source;
            if (this.boots == VanillaItemsArmor.bedrockBoots && this.legs == VanillaItemsArmor.bedrockLegs && this.body == VanillaItemsArmor.bedrockBody && this.helmet == VanillaItemsArmor.bedrockHelmet && s.isExplosion()) {
                evt.setCanceled(true);
            }
            if (!(this.boots != VanillaItemsArmor.enderBoots && this.boots != VanillaItemsArmor.redEnderBoots && this.boots != VanillaItemsArmor.yellowEnderBoots && this.boots != VanillaItemsArmor.greenEnderBoots && this.boots != VanillaItemsArmor.blueEnderBoots && this.boots != VanillaItemsArmor.grayEnderBoots || this.legs != VanillaItemsArmor.enderLegs && this.legs != VanillaItemsArmor.redEnderLegs && this.legs != VanillaItemsArmor.yellowEnderLegs && this.legs != VanillaItemsArmor.greenEnderLegs && this.legs != VanillaItemsArmor.blueEnderLegs && this.legs != VanillaItemsArmor.grayEnderLegs || this.body != VanillaItemsArmor.enderBody && this.body != VanillaItemsArmor.redEnderBody && this.body != VanillaItemsArmor.yellowEnderBody && this.body != VanillaItemsArmor.greenEnderBody && this.body != VanillaItemsArmor.blueEnderBody && this.body != VanillaItemsArmor.grayEnderBody || this.helmet != VanillaItemsArmor.enderHelmet && this.helmet != VanillaItemsArmor.redEnderHelmet && this.helmet != VanillaItemsArmor.yellowEnderHelmet && this.helmet != VanillaItemsArmor.greenEnderHelmet && this.helmet != VanillaItemsArmor.blueEnderHelmet && this.helmet != VanillaItemsArmor.grayEnderHelmet || !s.isExplosion())) {
                evt.setCanceled(true);
            }
            if (this.boots == VanillaItemsArmor.arlemiteBoots && this.legs == VanillaItemsArmor.arlemiteLegs && this.body == VanillaItemsArmor.arlemiteBody && this.helmet == VanillaItemsArmor.arlemiteHelmet && (s.isProjectile() || s.damageType.equals("thrown"))) {
                evt.ammount = (float)((double)evt.ammount * 0.3);
            }
            if (!(this.boots != VanillaItemsArmor.rupeeBoots && this.boots != VanillaItemsArmor.redRupeeBoots && this.boots != VanillaItemsArmor.yellowRupeeBoots && this.boots != VanillaItemsArmor.greenRupeeBoots && this.boots != VanillaItemsArmor.blueRupeeBoots && this.boots != VanillaItemsArmor.grayRupeeBoots || this.legs != VanillaItemsArmor.rupeeLegs && this.legs != VanillaItemsArmor.redRupeeLegs && this.legs != VanillaItemsArmor.yellowRupeeLegs && this.legs != VanillaItemsArmor.greenRupeeLegs && this.legs != VanillaItemsArmor.blueRupeeLegs && this.legs != VanillaItemsArmor.grayRupeeLegs || this.body != VanillaItemsArmor.rupeeBody && this.body != VanillaItemsArmor.redRupeeBody && this.body != VanillaItemsArmor.yellowRupeeBody && this.body != VanillaItemsArmor.greenRupeeBody && this.body != VanillaItemsArmor.blueRupeeBody && this.body != VanillaItemsArmor.grayRupeeBody || this.helmet != VanillaItemsArmor.rupeeHelmet && this.helmet != VanillaItemsArmor.redRupeeHelmet && this.helmet != VanillaItemsArmor.yellowRupeeHelmet && this.helmet != VanillaItemsArmor.greenRupeeHelmet && this.helmet != VanillaItemsArmor.blueRupeeHelmet && this.helmet != VanillaItemsArmor.grayRupeeHelmet || !s.damageType.equals("mob") || s.isProjectile())) {
                evt.ammount = (float)((double)evt.ammount * 0.3);
            }
            if (this.boots == IceikaItems.santaBoots && this.legs == IceikaItems.santaPants && this.body == IceikaItems.santaTunic && this.helmet == IceikaItems.santaCap && evt.entityLiving.worldObj.provider.dimensionId == ConfigurationHelper.iceika) {
                evt.ammount = (float)((double)evt.ammount * 0.2);
            }
            if (this.body == VetheaItems.degradedBody && this.legs == VetheaItems.degradedLegs && this.boots == VetheaItems.degradedBoots && (this.helmet == VetheaItems.degradedHelmet && !s.isProjectile() && !s.isMagicDamage() || this.helmet == VetheaItems.degradedMask && s.isProjectile() && !s.isMagicDamage() || this.helmet == VetheaItems.degradedHood && s.isMagicDamage())) {
                evt.ammount = (float)((double)evt.ammount * 0.82);
            }
            if (this.body == VetheaItems.finishedBody && this.legs == VetheaItems.finishedLegs && this.boots == VetheaItems.finishedBoots && (this.helmet == VetheaItems.finishedHelmet && !s.isProjectile() && !s.isMagicDamage() || this.helmet == VetheaItems.finishedMask && s.isProjectile() && !s.isMagicDamage() || this.helmet == VetheaItems.finishedHood && s.isMagicDamage())) {
                evt.ammount = (float)((double)evt.ammount * 0.773);
            }
            if (this.body == VetheaItems.glisteningBody && this.legs == VetheaItems.glisteningLegs && this.boots == VetheaItems.glisteningBoots && (this.helmet == VetheaItems.glisteningHelmet && !s.isProjectile() && !s.isMagicDamage() || this.helmet == VetheaItems.glisteningMask && s.isProjectile() && !s.isMagicDamage() || this.helmet == VetheaItems.glisteningHood && s.isMagicDamage())) {
                evt.ammount = (float)((double)evt.ammount * 0.7);
            }
            if (this.body == VetheaItems.demonizedBody && this.legs == VetheaItems.demonizedLegs && this.boots == VetheaItems.demonizedBoots && (this.helmet == VetheaItems.demonizedHelmet && !s.isProjectile() && !s.isMagicDamage() || this.helmet == VetheaItems.demonizedMask && s.isProjectile() && !s.isMagicDamage() || this.helmet == VetheaItems.demonizedHood && s.isMagicDamage())) {
                evt.ammount = (float)((double)evt.ammount * 0.625);
            }
            if (this.body == VetheaItems.tormentedBody && this.legs == VetheaItems.tormentedLegs && this.boots == VetheaItems.tormentedBoots && (this.helmet == VetheaItems.tormentedHelmet && !s.isProjectile() && !s.isMagicDamage() || this.helmet == VetheaItems.tormentedMask && s.isProjectile() && !s.isMagicDamage() || this.helmet == VetheaItems.tormentedHood && s.isMagicDamage())) {
                evt.ammount = (float)((double)evt.ammount * 0.348);
            }
        }
    }

    @SubscribeEvent
    public void onJump(LivingEvent.LivingJumpEvent event) {
        if (event.entityLiving instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)event.entityLiving;
            ItemStack stackBoots = player.inventory.armorItemInSlot(0);
            ItemStack stackLegs = player.inventory.armorItemInSlot(1);
            ItemStack stackBody = player.inventory.armorItemInSlot(2);
            ItemStack stackHelmet = player.inventory.armorItemInSlot(3);
            this.boots = stackBoots != null ? stackBoots.getItem() : null;
            this.body = stackBody != null ? stackBody.getItem() : null;
            this.legs = stackLegs != null ? stackLegs.getItem() : null;
            this.helmet = stackHelmet != null ? stackHelmet.getItem() : null;
            if (this.boots == VanillaItemsArmor.divineBoots && this.body == VanillaItemsArmor.divineBody && this.legs == VanillaItemsArmor.divineLegs && this.helmet == VanillaItemsArmor.divineHelmet) {
                player.addVelocity(0.0, 0.2, 0.0);
            }
            if (this.boots == TwilightItemsArmor.skythernBoots && this.body == TwilightItemsArmor.skythernChestplate && this.legs == TwilightItemsArmor.skythernLeggings && this.helmet == TwilightItemsArmor.skythernHelmet) {
                player.addVelocity(0.0, 0.5, 0.0);
            }
            if (this.body == VetheaItems.glisteningBody && this.legs == VetheaItems.glisteningLegs && this.boots == VetheaItems.glisteningBoots && this.helmet == VetheaItems.glisteningHood) {
                player.addVelocity(0.0, 0.2, 0.0);
            }
            if (this.body == VetheaItems.demonizedBody && this.legs == VetheaItems.demonizedLegs && this.boots == VetheaItems.demonizedBoots && this.helmet == VetheaItems.demonizedHood) {
                player.addVelocity(0.0, 0.3, 0.0);
            }
            if (this.body == VetheaItems.tormentedBody && this.legs == VetheaItems.tormentedLegs && this.boots == VetheaItems.tormentedBoots && this.helmet == VetheaItems.tormentedHood) {
                player.addVelocity(0.0, 0.4, 0.0);
            }
        }
    }

    @SubscribeEvent
    public void onBlockDrops(BlockEvent.HarvestDropsEvent event) {
        if (event.block != null && event.block instanceof BlockMod && ((BlockMod)event.block).isTwilightOre() && event.harvester != null && event.harvester instanceof EntityPlayer) {
            EntityPlayer player = event.harvester;
            ItemStack stackBoots = player.inventory.armorItemInSlot(0);
            ItemStack stackLegs = player.inventory.armorItemInSlot(1);
            ItemStack stackBody = player.inventory.armorItemInSlot(2);
            ItemStack stackHelmet = player.inventory.armorItemInSlot(3);
            this.boots = stackBoots != null ? stackBoots.getItem() : null;
            this.body = stackBody != null ? stackBody.getItem() : null;
            this.legs = stackLegs != null ? stackLegs.getItem() : null;
            this.helmet = stackHelmet != null ? stackHelmet.getItem() : null;
            if (this.boots == TwilightItemsArmor.edenBoots && this.body == TwilightItemsArmor.edenChestplate && this.legs == TwilightItemsArmor.edenLeggings && this.helmet == TwilightItemsArmor.edenHelmet && !event.isSilkTouching) {
                ItemStack fragment = (ItemStack)event.drops.get(0);
                event.drops.add(fragment.copy());
                event.drops.add(fragment.copy());
                event.drops.add(fragment.copy());
            }
        }
    }

    @SubscribeEvent
    public void onPlayerAttackEvent(LivingAttackEvent e) {
        if (e.entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)e.entity;
            ItemStack stackBoots = player.inventory.armorItemInSlot(0);
            ItemStack stackLegs = player.inventory.armorItemInSlot(1);
            ItemStack stackBody = player.inventory.armorItemInSlot(2);
            ItemStack stackHelmet = player.inventory.armorItemInSlot(3);
            this.boots = stackBoots != null ? stackBoots.getItem() : null;
            this.body = stackBody != null ? stackBody.getItem() : null;
            this.legs = stackLegs != null ? stackLegs.getItem() : null;
            this.helmet = stackHelmet != null ? stackHelmet.getItem() : null;
            DamageSource s = e.source;
            if ((this.boots == VanillaItemsArmor.aquastriveBoots && this.body == VanillaItemsArmor.aquastriveBody && this.legs == VanillaItemsArmor.aquastriveLegs && this.helmet == VanillaItemsArmor.aquastriveHelmet || this.boots == VanillaItemsArmor.krakenBoots && this.body == VanillaItemsArmor.krakenBody && this.legs == VanillaItemsArmor.krakenLegs && this.helmet == VanillaItemsArmor.krakenHelmet) && s.equals(DamageSource.drown)) {
                e.setCanceled(true);
            }
            if (this.boots == TwilightItemsArmor.apalachiaBoots && this.legs == TwilightItemsArmor.apalachiaLeggings && this.body == TwilightItemsArmor.apalachiaChestplate && this.helmet == TwilightItemsArmor.apalachiaHelmet && (s.equals(DamageSource.cactus) || s.equals(DamageSource.fallingBlock) || s.equals(DamageSource.anvil) || s.equals(DamageSource.inWall) || s.equals(Util.trapSource))) {
                e.setCanceled(true);
            }
            if (this.boots == VanillaItemsArmor.witherReaperBoots && this.legs == VanillaItemsArmor.witherReaperLegs && this.body == VanillaItemsArmor.witherReaperBody && this.helmet == VanillaItemsArmor.witherReaperHelmet && s.equals(DamageSource.wither)) {
                e.setCanceled(true);
            }
            if (this.boots == VanillaItemsArmor.jungleBoots && this.legs == VanillaItemsArmor.jungleLegs && this.body == VanillaItemsArmor.jungleBody && this.helmet == VanillaItemsArmor.jungleHelmet && s.equals(DamageSource.magic)) {
                e.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public void onLivingHurtEvent(LivingHurtEvent e) {
        if (e.source.getEntity() != null && e.source.getEntity() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)e.source.getEntity();
            ItemStack stackBoots = player.inventory.armorItemInSlot(0);
            ItemStack stackLegs = player.inventory.armorItemInSlot(1);
            ItemStack stackBody = player.inventory.armorItemInSlot(2);
            ItemStack stackHelmet = player.inventory.armorItemInSlot(3);
            this.boots = stackBoots != null ? stackBoots.getItem() : null;
            this.body = stackBody != null ? stackBody.getItem() : null;
            this.legs = stackLegs != null ? stackLegs.getItem() : null;
            this.helmet = stackHelmet != null ? stackHelmet.getItem() : null;
        }
        DamageSource s = e.source;
        if (this.boots == IceikaItems.santaBoots && this.body == IceikaItems.santaTunic && this.legs == IceikaItems.santaPants && this.helmet == IceikaItems.santaCap && e.entityLiving.worldObj.provider.dimensionId == ConfigurationHelper.iceika && s.getEntity() instanceof EntityPlayer && !s.isProjectile() && !s.isMagicDamage()) {
            e.ammount += 6.0f;
        }
        if (this.boots == TwilightItemsArmor.haliteBoots && this.body == TwilightItemsArmor.haliteChestplate && this.legs == TwilightItemsArmor.haliteLeggings && this.helmet == TwilightItemsArmor.haliteHelmet && s.getEntity() instanceof EntityPlayer && !s.isProjectile() && !s.isMagicDamage()) {
            e.ammount += 16.0f;
        }
        if (this.boots == VanillaItemsArmor.divineBoots && this.body == VanillaItemsArmor.divineBody && this.legs == VanillaItemsArmor.divineLegs && this.helmet == VanillaItemsArmor.divineHelmet && s.getEntity() instanceof EntityPlayer && !s.isProjectile() && !s.isMagicDamage()) {
            e.ammount += 6.0f;
        }
        if (this.body == VanillaItemsArmor.corruptedBody && this.legs == VanillaItemsArmor.corruptedLegs && this.boots == VanillaItemsArmor.corruptedBoots && this.helmet == VanillaItemsArmor.corruptedHelmet && s.getEntity() instanceof EntityPlayer && s.isProjectile()) {
            e.ammount = (float)((double)e.ammount * 1.2);
        }
        if (this.body == VetheaItems.glisteningBody && this.legs == VetheaItems.glisteningLegs && this.boots == VetheaItems.glisteningBoots && this.helmet == VetheaItems.glisteningHelmet && s.getEntity() instanceof EntityPlayer && !s.isProjectile() && !s.isMagicDamage()) {
            e.ammount += 3.0f;
        }
        if (this.body == VetheaItems.demonizedBody && this.legs == VetheaItems.demonizedLegs && this.boots == VetheaItems.demonizedBoots && this.helmet == VetheaItems.demonizedHelmet && s.getEntity() instanceof EntityPlayer && !s.isProjectile() && !s.isMagicDamage()) {
            e.ammount += 6.0f;
        }
        if (this.body == VetheaItems.tormentedBody && this.legs == VetheaItems.tormentedLegs && this.boots == VetheaItems.tormentedBoots && this.helmet == VetheaItems.tormentedHelmet && s.getEntity() instanceof EntityPlayer && !s.isProjectile() && !s.isMagicDamage()) {
            e.ammount += 9.0f;
        }
    }
}

