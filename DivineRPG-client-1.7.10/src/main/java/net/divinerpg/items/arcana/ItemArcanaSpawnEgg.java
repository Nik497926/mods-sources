/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.stats.StatBase
 *  net.minecraft.world.World
 */
package net.divinerpg.items.arcana;

import java.util.List;
import net.divinerpg.entities.arcana.EntityFyracryx;
import net.divinerpg.entities.arcana.EntityGolemOfRejuv;
import net.divinerpg.entities.arcana.EntityParatiku;
import net.divinerpg.entities.arcana.EntitySeimer;
import net.divinerpg.items.base.ItemMod;
import net.divinerpg.libs.DivineRPGAchievements;
import net.divinerpg.utils.items.ArcanaItems;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatBase;
import net.minecraft.world.World;

public class ItemArcanaSpawnEgg
extends ItemMod {
    public ItemArcanaSpawnEgg(String name) {
        super(name, DivineRPGTabs.spawner);
        this.setMaxStackSize(1);
        this.setTextureName("divinerpg:arcanaSpawnEgg");
    }

    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        Item item = stack.getItem();
        EntityFyracryx fyracryx = new EntityFyracryx(world, player);
        EntitySeimer seimer = new EntitySeimer(world, player);
        EntityGolemOfRejuv golem = new EntityGolemOfRejuv(world, player);
        EntityParatiku paratiku = new EntityParatiku(world, player);
        if (!world.isRemote) {
            if (item == ArcanaItems.fyracryxSpawner) {
                fyracryx.setLocationAndAngles(x, y + 1, z, 0.0f, 0.0f);
                world.spawnEntityInWorld((Entity)fyracryx);
            }
            if (item == ArcanaItems.seimerSpawner) {
                seimer.setLocationAndAngles(x, y + 1, z, 0.0f, 0.0f);
                world.spawnEntityInWorld((Entity)seimer);
            }
            if (item == ArcanaItems.golemSpawner) {
                golem.setLocationAndAngles(x, y + 1, z, 0.0f, 0.0f);
                world.spawnEntityInWorld((Entity)golem);
            }
            if (item == ArcanaItems.paratikuSpawner) {
                paratiku.setLocationAndAngles(x, y + 1, z, 0.0f, 0.0f);
                world.spawnEntityInWorld((Entity)paratiku);
            }
            player.triggerAchievement((StatBase)DivineRPGAchievements.littleCreature);
            --stack.stackSize;
            return true;
        }
        return false;
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        Item item = stack.getItem();
        if (item == ArcanaItems.fyracryxSpawner) {
            list.add("Spawns a pet Fyracryx");
        }
        if (item == ArcanaItems.seimerSpawner) {
            list.add("Spawns a pet Seimer");
        }
        if (item == ArcanaItems.golemSpawner) {
            list.add("Spawns a pet Golem Of Rejuvenation");
        }
        if (item == ArcanaItems.paratikuSpawner) {
            list.add("Spawns a pet Paratiku");
        }
    }
}

