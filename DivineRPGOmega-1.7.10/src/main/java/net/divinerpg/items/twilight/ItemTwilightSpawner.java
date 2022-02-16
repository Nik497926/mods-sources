/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.stats.StatBase
 *  net.minecraft.world.World
 */
package net.divinerpg.items.twilight;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.divinerpg.entities.twilight.EntityDensos;
import net.divinerpg.entities.twilight.EntityEternalArcher;
import net.divinerpg.entities.twilight.EntityKarot;
import net.divinerpg.entities.twilight.EntityReyvor;
import net.divinerpg.entities.twilight.EntitySoulFiend;
import net.divinerpg.entities.twilight.EntityTwilightDemon;
import net.divinerpg.entities.twilight.EntityVamacheron;
import net.divinerpg.items.base.ItemMod;
import net.divinerpg.libs.DivineRPGAchievements;
import net.divinerpg.utils.MessageLocalizer;
import net.divinerpg.utils.Util;
import net.divinerpg.utils.config.ConfigurationHelper;
import net.divinerpg.utils.items.TwilightItemsOther;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatBase;
import net.minecraft.world.World;

public class ItemTwilightSpawner
extends ItemMod {
    public ItemTwilightSpawner(String name) {
        super(name);
        this.setMaxStackSize(1);
        this.setCreativeTab(DivineRPGTabs.spawner);
    }

    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float par8, float par9, float par10) {
        if (!world.isRemote) {
            if (world.provider.dimensionId == ConfigurationHelper.mortum) {
                if (stack.getItem() == TwilightItemsOther.karotCrystal) {
                    EntityKarot e = new EntityKarot(world);
                    e.setPosition(x, y + 1, z);
                    if (world.getCollidingBoundingBoxes((Entity)e, e.boundingBox).isEmpty()) {
                        world.spawnEntityInWorld((Entity)e);
                        if (!player.capabilities.isCreativeMode) {
                            player.inventory.consumeInventoryItem(stack.getItem());
                        }
                    }
                    return true;
                }
                if (stack.getItem() == TwilightItemsOther.densosCrystal || stack.getItem() == TwilightItemsOther.reyvorCrystal) {
                    EntityDensos e = new EntityDensos(world);
                    e.setPosition(x, y + 1, z);
                    EntityReyvor e1 = new EntityReyvor(world);
                    e1.setPosition(x, y + 1, z);
                    if (world.getCollidingBoundingBoxes((Entity)e, e.boundingBox).isEmpty() && world.getCollidingBoundingBoxes((Entity)e1, e1.boundingBox).isEmpty()) {
                        world.spawnEntityInWorld((Entity)e);
                        world.spawnEntityInWorld((Entity)e1);
                        if (!player.capabilities.isCreativeMode) {
                            player.inventory.consumeInventoryItem(stack.getItem());
                        }
                        player.triggerAchievement((StatBase)DivineRPGAchievements.tenHeadsIsBetterThanOne);
                    }
                    return true;
                }
                if (stack.getItem() == TwilightItemsOther.soulFiendCrystal) {
                    EntitySoulFiend e = new EntitySoulFiend(world);
                    e.setPosition(x, y + 1, z);
                    if (world.getCollidingBoundingBoxes((Entity)e, e.boundingBox).isEmpty()) {
                        world.spawnEntityInWorld((Entity)e);
                        if (!player.capabilities.isCreativeMode) {
                            player.inventory.consumeInventoryItem(stack.getItem());
                        }
                    }
                    return true;
                }
                if (stack.getItem() == TwilightItemsOther.twilightDemonCrystal) {
                    EntityTwilightDemon e = new EntityTwilightDemon(world);
                    e.setPosition(x, y + 1, z);
                    if (world.getCollidingBoundingBoxes((Entity)e, e.boundingBox).isEmpty()) {
                        world.spawnEntityInWorld((Entity)e);
                        if (!player.capabilities.isCreativeMode) {
                            player.inventory.consumeInventoryItem(stack.getItem());
                        }
                    }
                    return true;
                }
                if (stack.getItem() == TwilightItemsOther.vamacheronCrystal) {
                    EntityVamacheron e = new EntityVamacheron(world);
                    e.setPosition(x, y + 1, z);
                    if (world.getCollidingBoundingBoxes((Entity)e, e.boundingBox).isEmpty()) {
                        world.spawnEntityInWorld((Entity)e);
                        if (!player.capabilities.isCreativeMode) {
                            player.inventory.consumeInventoryItem(stack.getItem());
                        }
                    }
                    return true;
                }
                if (stack.getItem() == TwilightItemsOther.eternalArcherCrystal) {
                    EntityEternalArcher e = new EntityEternalArcher(world);
                    e.setPosition(x, y + 1, z);
                    if (world.getCollidingBoundingBoxes((Entity)e, e.boundingBox).isEmpty()) {
                        world.spawnEntityInWorld((Entity)e);
                        if (!player.capabilities.isCreativeMode) {
                            player.inventory.consumeInventoryItem(stack.getItem());
                        }
                        player.triggerAchievement((StatBase)DivineRPGAchievements.sixInOne);
                    }
                    return true;
                }
            } else {
                player.addChatMessage(Util.getChatComponent(MessageLocalizer.normal("item.twilightspawn")));
            }
        }
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        String s = "";
        if (stack.getItem() == TwilightItemsOther.karotCrystal) {
            s = "\u041a\u0430\u0440\u043e\u0442";
        } else if (stack.getItem() == TwilightItemsOther.densosCrystal || stack.getItem() == TwilightItemsOther.reyvorCrystal) {
            s = "\u0414\u0435\u043d\u0441\u043e\u0441, \u0420\u0435\u0439\u0432\u043e\u0440";
        } else if (stack.getItem() == TwilightItemsOther.soulFiendCrystal) {
            s = "\u0414\u0435\u043c\u043e\u043d \u0434\u0443\u0448";
        } else if (stack.getItem() == TwilightItemsOther.twilightDemonCrystal) {
            s = "\u0421\u0443\u043c\u0435\u0440\u0435\u0447\u043d\u044b\u0439 \u0434\u0435\u043c\u043e\u043d";
        } else if (stack.getItem() == TwilightItemsOther.vamacheronCrystal) {
            s = "\u0412\u0430\u043c\u0430\u0448\u0435\u0440\u043e\u043d";
        } else if (stack.getItem() == TwilightItemsOther.eternalArcherCrystal) {
            s = "\u0412\u0435\u0447\u043d\u044b\u0439 \u043b\u0443\u0447\u043d\u0438\u043a";
        }
        list.add(Util.GOLD + I18n.format((String)"items.twilightSpawner.spawn", (Object[])new Object[0]) + Util.RED + s);
        list.add(Util.GOLD + I18n.format((String)"items.twilightSpawner.dim", (Object[])new Object[0]));
    }
}

