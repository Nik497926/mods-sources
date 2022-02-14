/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.block.Block
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.DamageSource
 *  net.minecraftforge.event.entity.living.LivingAttackEvent
 *  net.minecraftforge.event.entity.living.LivingDeathEvent
 *  net.minecraftforge.event.entity.living.LivingEvent$LivingJumpEvent
 *  net.minecraftforge.event.entity.living.LivingFallEvent
 *  net.minecraftforge.event.entity.living.LivingHurtEvent
 *  net.minecraftforge.event.entity.player.UseHoeEvent
 *  net.minecraftforge.event.world.BlockEvent$HarvestDropsEvent
 */
package net.divinerpg.utils.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import java.util.Random;
import net.divinerpg.blocks.base.BlockMod;
import net.divinerpg.enchantment.EnchanmtmentsRegistry;
import net.divinerpg.enchantment.EnchantmentsHelper;
import net.divinerpg.utils.Util;
import net.divinerpg.utils.blocks.JourneyBlocks;
import net.divinerpg.utils.blocks.VanillaBlocks;
import net.divinerpg.utils.config.ConfigurationHelper;
import net.divinerpg.utils.events.ArcanaHelper;
import net.divinerpg.utils.items.IceikaItems;
import net.divinerpg.utils.items.JourneyItemsArmor;
import net.divinerpg.utils.items.JourneyItemsOther;
import net.divinerpg.utils.items.SkinItems;
import net.divinerpg.utils.items.TwilightItemsArmor;
import net.divinerpg.utils.items.VanillaItemsArmor;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.UseHoeEvent;
import net.minecraftforge.event.world.BlockEvent;

public class EventArmorFullSet {
    private Item boots = null;
    private Item body = null;
    private Item legs = null;
    private Item helmet = null;
    private Random rand = new Random();

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
            if (this.boots == TwilightItemsArmor.arcaniumBoots && this.body == TwilightItemsArmor.arcaniumChestplate && this.legs == TwilightItemsArmor.arcaniumLeggings && this.helmet == TwilightItemsArmor.arcaniumHelmet) {
                evt.ammount = (float)((double)evt.ammount * 0.8);
                if (ArcanaHelper.getProperties(player).useBar(evt.ammount)) {
                    evt.ammount = 0.0f;
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
            }
        }
    }

    @SubscribeEvent
    public void fall(LivingFallEvent e) {
        e.distance /= 2.0f;
    }

    @SubscribeEvent
    public void onPlayerBreakingBlock(BlockEvent.HarvestDropsEvent event) {
        if (event.harvester != null && EnchantmentsHelper.hasSmeltingEnchantment(event.harvester)) {
            int fortune = event.fortuneLevel;
            int level = EnchantmentHelper.getEnchantmentLevel((int)EnchanmtmentsRegistry.AUTO_SMELTING.effectId, (ItemStack)event.harvester.getHeldItem());
            Block block = event.block;
            if (event.harvester.canHarvestBlock(block)) {
                if (block == Blocks.iron_ore) {
                    event.drops.clear();
                    event.drops.add(new ItemStack(Items.iron_ingot, 1 + event.world.rand.nextInt(level) + (fortune > 0 ? event.world.rand.nextInt(fortune + 1) : 0)));
                } else if (block == Blocks.gold_ore) {
                    event.drops.clear();
                    event.drops.add(new ItemStack(Items.gold_ingot, 1 + event.world.rand.nextInt(level) + (fortune > 0 ? event.world.rand.nextInt(fortune + 1) : 0)));
                } else if (block == VanillaBlocks.rupeeOre) {
                    event.drops.clear();
                    event.drops.add(new ItemStack(VanillaItemsOther.rupeeIngot, 1 + event.world.rand.nextInt(level) + (fortune > 0 ? event.world.rand.nextInt(fortune + 1) : 0)));
                } else if (block == VanillaBlocks.arlemiteOre) {
                    event.drops.clear();
                    event.drops.add(new ItemStack(VanillaItemsOther.arlemiteIngot, 1 + event.world.rand.nextInt(level) + (fortune > 0 ? event.world.rand.nextInt(fortune + 1) : 0)));
                } else if (block == VanillaBlocks.realmiteOre) {
                    event.drops.clear();
                    event.drops.add(new ItemStack(VanillaItemsOther.realmiteIngot, 1 + event.world.rand.nextInt(level) + (fortune > 0 ? event.world.rand.nextInt(fortune + 1) : 0)));
                } else if (block == VanillaBlocks.netheriteOre) {
                    event.drops.clear();
                    event.drops.add(new ItemStack(VanillaItemsOther.netheriteIngot, 1 + event.world.rand.nextInt(level) + (fortune > 0 ? event.world.rand.nextInt(fortune + 1) : 0)));
                } else if (block == VanillaBlocks.copperOre) {
                    event.drops.clear();
                    event.drops.add(new ItemStack(VanillaItemsOther.copperIngot, 1 + event.world.rand.nextInt(level) + (fortune > 0 ? event.world.rand.nextInt(fortune + 1) : 0)));
                } else if (block == VanillaBlocks.amazoniteOre) {
                    event.drops.clear();
                    event.drops.add(new ItemStack(VanillaItemsOther.amazonite, 1 + event.world.rand.nextInt(level) + (fortune > 0 ? event.world.rand.nextInt(fortune + 1) : 0)));
                } else if (block == VanillaBlocks.nightmareOre) {
                    event.drops.clear();
                    event.drops.add(new ItemStack(VanillaItemsOther.nightmareSteel, 1 + event.world.rand.nextInt(level) + (fortune > 0 ? event.world.rand.nextInt(fortune + 1) : 0)));
                } else if (block == JourneyBlocks.bloodcrustOre) {
                    event.drops.clear();
                    event.drops.add(new ItemStack(JourneyItemsOther.bloodcrustIngot, 1 + event.world.rand.nextInt(level) + (fortune > 0 ? event.world.rand.nextInt(fortune + 1) : 0)));
                } else if (block == JourneyBlocks.celestiumOre) {
                    event.drops.clear();
                    event.drops.add(new ItemStack(JourneyItemsOther.celestiumIngot, 1 + event.world.rand.nextInt(level)));
                } else if (block == JourneyBlocks.mekyumOre) {
                    event.drops.clear();
                    event.drops.add(new ItemStack(JourneyItemsOther.mekyumIngot, 1 + event.world.rand.nextInt(level) + (fortune > 0 ? event.world.rand.nextInt(fortune + 1) : 0)));
                } else if (block == JourneyBlocks.koriteOre) {
                    event.drops.clear();
                    event.drops.add(new ItemStack(JourneyItemsOther.koriteIngot, 1 + event.world.rand.nextInt(level) + (fortune > 0 ? event.world.rand.nextInt(fortune + 1) : 0)));
                } else if (block == JourneyBlocks.storonOre) {
                    event.drops.clear();
                    event.drops.add(new ItemStack(JourneyItemsOther.storonIngot, 1 + event.world.rand.nextInt(level) + (fortune > 0 ? event.world.rand.nextInt(fortune + 1) : 0)));
                } else if (block == Blocks.stone) {
                    event.drops.clear();
                    event.drops.add(new ItemStack(Blocks.stone, 1));
                } else if (block == Blocks.sand) {
                    event.drops.clear();
                    event.drops.add(new ItemStack(Item.getItemFromBlock((Block)Blocks.glass)));
                } else if (block == Blocks.log || block == Blocks.log2) {
                    event.drops.clear();
                    event.drops.add(new ItemStack(Items.coal, 1 + event.world.rand.nextInt(level) + (fortune > 0 ? event.world.rand.nextInt(fortune + 1) : 0), 1));
                }
            }
        }
    }

    @SubscribeEvent
    public void NameFormatNone(LivingDeathEvent event) {
        Entity attacker = event.source.getEntity();
        Entity target = event.entity;
        if (attacker instanceof EntityPlayer && target instanceof EntityPlayer) {
            EntityPlayer attackerPlayer = (EntityPlayer)attacker;
            EntityPlayer targetPlayer = (EntityPlayer)target;
            InventoryPlayer attackerInventory = attackerPlayer.inventory;
            InventoryPlayer targetInventory = targetPlayer.inventory;
            if (EnchantmentsHelper.hasSafeFallEnchantment(attackerPlayer)) {
                int slot;
                int i;
                for (i = 0; i < targetInventory.mainInventory.length; ++i) {
                    boolean empty;
                    boolean bl = empty = targetInventory.mainInventory[i] == null;
                    if (empty) continue;
                    slot = attackerInventory.getFirstEmptyStack();
                    if (slot < 0) {
                        return;
                    }
                    attackerInventory.mainInventory[slot] = targetInventory.mainInventory[i];
                    targetInventory.mainInventory[i] = null;
                }
                for (i = 0; i < 4; ++i) {
                    ItemStack targetArmor = targetInventory.armorInventory[i];
                    if (targetArmor == null) continue;
                    slot = attackerInventory.getFirstEmptyStack();
                    if (slot < 0) {
                        return;
                    }
                    attackerInventory.mainInventory[slot] = targetInventory.armorInventory[i];
                    targetInventory.armorInventory[i] = null;
                }
            }
        }
    }

    @SubscribeEvent
    public void onUseHoe(UseHoeEvent event) {
        if (event.world.getBlock(event.x, event.y, event.z) == Blocks.grass && EnchantmentsHelper.hasSmeltingEnchantment(event.entityPlayer)) {
            event.world.setBlock(event.x, event.y, event.z, Blocks.dirt, 2, 2);
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onBlockDrops(BlockEvent.HarvestDropsEvent event) {
        if (event.block instanceof BlockMod && ((BlockMod)event.block).isTwilightOre() && event.harvester != null) {
            EntityPlayer player = event.harvester;
            ItemStack stackBoots = player.inventory.armorItemInSlot(0);
            ItemStack stackLegs = player.inventory.armorItemInSlot(1);
            ItemStack stackBody = player.inventory.armorItemInSlot(2);
            ItemStack stackHelmet = player.inventory.armorItemInSlot(3);
            this.boots = stackBoots != null ? stackBoots.getItem() : null;
            this.body = stackBody != null ? stackBody.getItem() : null;
            this.legs = stackLegs != null ? stackLegs.getItem() : null;
            Item item = this.helmet = stackHelmet != null ? stackHelmet.getItem() : null;
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
        if (e.entity instanceof EntityPlayer && e.entityLiving.dimension != 0 && e.source.getEntity() instanceof EntityPlayer) {
            e.setCanceled(true);
        }
        DamageSource s = e.source;
        if (this.boots == VanillaItemsArmor.jungleBoots && this.legs == VanillaItemsArmor.jungleLegs && this.body == VanillaItemsArmor.jungleBody && this.helmet == VanillaItemsArmor.jungleHelmet) {
            if (s.equals(DamageSource.magic)) {
                e.setCanceled(true);
            }
            if (this.boots == TwilightItemsArmor.apalachiaBoots && this.legs == TwilightItemsArmor.apalachiaLeggings && this.body == TwilightItemsArmor.apalachiaChestplate && this.helmet == TwilightItemsArmor.apalachiaHelmet) {
                if (s.equals(DamageSource.cactus) || s.equals(DamageSource.fallingBlock) || s.equals(DamageSource.anvil) || s.equals(DamageSource.inWall) || s.equals(Util.trapSource)) {
                    e.setCanceled(true);
                }
                if (this.boots == VanillaItemsArmor.witherReaperBoots && this.legs == VanillaItemsArmor.witherReaperLegs && this.body == VanillaItemsArmor.witherReaperBody && this.helmet == VanillaItemsArmor.witherReaperHelmet && s.equals(DamageSource.wither)) {
                    e.setCanceled(true);
                }
                if (this.boots == JourneyItemsArmor.bloodcrustBoots && this.legs == JourneyItemsArmor.bloodcrustLeggings && this.body == JourneyItemsArmor.bloodcrustChestplate && this.helmet == JourneyItemsArmor.bloodcrustHelmet && s.equals(DamageSource.wither)) {
                    e.setCanceled(true);
                }
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
            if (stackBoots == null) {
                return;
            }
            this.boots = stackBoots.getItem();
            if (stackBody == null) {
                return;
            }
            this.body = stackBody.getItem();
            if (stackLegs == null) {
                return;
            }
            this.legs = stackLegs.getItem();
            if (stackHelmet != null) {
                this.helmet = stackHelmet.getItem();
            } else {
                return;
            }
        }
        DamageSource s = e.source;
        if (this.boots == TwilightItemsArmor.darkBoots && this.body == TwilightItemsArmor.darkChestplate && this.legs == TwilightItemsArmor.darkLeggings && this.helmet == TwilightItemsArmor.darkHelmet && s.getEntity() instanceof EntityPlayer && !s.isProjectile() && !s.isMagicDamage()) {
            if (this.rand.nextInt(99) < 5) {
                e.ammount *= 2.0f;
            }
            return;
        }
        if (this.boots == TwilightItemsArmor.lightBoots && this.body == TwilightItemsArmor.lightChestplate && this.legs == TwilightItemsArmor.lightLeggings && this.helmet == TwilightItemsArmor.lightHelmet && s.getEntity() instanceof EntityPlayer && !s.isProjectile() && !s.isMagicDamage()) {
            if (this.rand.nextInt(99) < 4) {
                e.entityLiving.attackEntityFrom(e.source, 70.0f);
            }
            return;
        }
        if (this.boots == TwilightItemsArmor.haliteBoots && this.body == TwilightItemsArmor.haliteChestplate && this.legs == TwilightItemsArmor.haliteLeggings && this.helmet == TwilightItemsArmor.haliteHelmet && s.getEntity() instanceof EntityPlayer && !s.isProjectile() && !s.isMagicDamage()) {
            e.ammount += 8.0f;
            return;
        }
        if (this.boots == JourneyItemsArmor.lightstoneBoots && this.body == JourneyItemsArmor.lightstoneChestplate && this.legs == JourneyItemsArmor.lightstoneLeggings && this.helmet == JourneyItemsArmor.lightstoneHelmet && s.getEntity() instanceof EntityPlayer && !s.isProjectile() && !s.isMagicDamage()) {
            e.ammount += 18.0f;
            return;
        }
        if (this.boots == TwilightItemsArmor.awakenedBoots && this.body == TwilightItemsArmor.awakenedChestplate && this.legs == TwilightItemsArmor.awakenedLeggings && this.helmet == TwilightItemsArmor.awakenedHelmet && s.getEntity() instanceof EntityPlayer && !s.isProjectile() && !s.isMagicDamage()) {
            e.ammount += 10.0f;
            return;
        }
        if (this.boots == JourneyItemsArmor.flameBoots && this.body == JourneyItemsArmor.flameChestPlate && this.legs == JourneyItemsArmor.flameLeggings && this.helmet == JourneyItemsArmor.flameHelmet && s.getEntity() instanceof EntityPlayer && !s.isProjectile() && !s.isMagicDamage()) {
            e.ammount += 12.0f;
            return;
        }
        if (this.body == VanillaItemsArmor.corruptedBody && this.legs == VanillaItemsArmor.corruptedLegs && this.boots == VanillaItemsArmor.corruptedBoots && this.helmet == VanillaItemsArmor.corruptedHelmet && s.getEntity() instanceof EntityPlayer && s.isProjectile()) {
            e.ammount = (float)((double)e.ammount * 1.2);
        }
        if (this.boots == JourneyItemsArmor.luniumBoots && this.legs == JourneyItemsArmor.luniumLeggings && this.body == JourneyItemsArmor.luniumChestplate && this.helmet == JourneyItemsArmor.luniumHelmet && s.getEntity() instanceof EntityPlayer && s.isProjectile()) {
            e.ammount = (float)((double)e.ammount * 1.2);
        }
        if (this.boots == JourneyItemsArmor.CoolSetBoots && this.body == JourneyItemsArmor.CoolSetChestplate && this.legs == JourneyItemsArmor.CoolSetLeggings && this.helmet == JourneyItemsArmor.CoolSetHelmet && s.getEntity() instanceof EntityPlayer && !s.isProjectile() && !s.isMagicDamage()) {
            e.ammount += 9.0f;
            return;
        }
        if (this.boots == SkinItems.awakenedpremBoots && this.body == SkinItems.awakenedpremChestplate && this.legs == SkinItems.awakenedpremLeggings && this.helmet == SkinItems.awakenedpremHelmet && s.getEntity() instanceof EntityPlayer && !s.isProjectile() && !s.isMagicDamage()) {
            e.ammount += 11.0f;
            return;
        }
        if (this.boots == TwilightItemsArmor.arcaniumBoots && this.body == TwilightItemsArmor.arcaniumChestplate && this.legs == TwilightItemsArmor.arcaniumLeggings && this.helmet == TwilightItemsArmor.arcaniumHelmet && s.getEntity() instanceof EntityPlayer && !s.isProjectile() && !s.isMagicDamage()) {
            e.ammount += 7.0f;
            return;
        }
        if (this.boots == VanillaItemsArmor.divineBoots && this.body == VanillaItemsArmor.divineBody && this.legs == VanillaItemsArmor.divineLegs && this.helmet == VanillaItemsArmor.divineHelmet && s.getEntity() instanceof EntityPlayer && !s.isProjectile() && !s.isMagicDamage()) {
            e.ammount += 4.0f;
            return;
        }
        if (this.boots == VanillaItemsArmor.netheriumBoots && this.body == VanillaItemsArmor.netheriumChestplate && this.legs == VanillaItemsArmor.netheriumLeggings && this.helmet == VanillaItemsArmor.netheriumHelmet && s.getEntity() instanceof EntityPlayer && !s.isProjectile() && !s.isMagicDamage()) {
            e.ammount += 4.0f;
            return;
        }
        if (this.body == VanillaItemsArmor.archerBody && this.legs == VanillaItemsArmor.archerLegs && this.boots == VanillaItemsArmor.archerBoots && this.helmet == VanillaItemsArmor.archerHelmet && s.getEntity() instanceof EntityPlayer && s.isProjectile()) {
            e.ammount = (float)((double)e.ammount * 1.5);
            return;
        }
        if (this.body == JourneyItemsArmor.luniumChestplate && this.legs == JourneyItemsArmor.luniumLeggings && this.boots == JourneyItemsArmor.luniumBoots && this.helmet == JourneyItemsArmor.luniumHelmet) {
            if (s.getEntity() instanceof EntityPlayer && !s.isProjectile() && !s.isMagicDamage()) {
                e.ammount += 2.0f;
            }
            if (s.getEntity() instanceof EntityPlayer && s.isProjectile()) {
                e.ammount = (float)((double)e.ammount * 1.2);
            }
            return;
        }
        if (this.body == JourneyItemsArmor.bloodcrustChestplate && this.legs == JourneyItemsArmor.bloodcrustLeggings && this.boots == JourneyItemsArmor.bloodcrustBoots && this.helmet == JourneyItemsArmor.bloodcrustHelmet && s.getEntity() instanceof EntityPlayer && !s.isProjectile() && !s.isMagicDamage()) {
            e.ammount += 6.0f;
            return;
        }
        if (this.body == JourneyItemsArmor.celestiumChestplate && this.legs == JourneyItemsArmor.celestiumLeggings && this.boots == JourneyItemsArmor.celestiumBoots && this.helmet == JourneyItemsArmor.celestiumHelmet && s.getEntity() instanceof EntityPlayer && !s.isProjectile() && !s.isMagicDamage()) {
            e.ammount += 8.0f;
        }
    }
}

