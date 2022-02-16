/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.effect.EntityLightningBolt
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.stats.StatBase
 *  net.minecraft.world.World
 */
package net.divinerpg.items.arcana;

import net.divinerpg.items.base.ItemModSword;
import net.divinerpg.libs.DivineRPGAchievements;
import net.divinerpg.utils.events.ArcanaHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.stats.StatBase;
import net.minecraft.world.World;

public class ItemStormSword
extends ItemModSword {
    public ItemStormSword(String name, Item.ToolMaterial toolMaterial) {
        super(toolMaterial, name);
    }

    @Override
    protected boolean canUseSpecialEffect(EntityPlayer player) {
        return ArcanaHelper.getProperties(player).useBar(20.0f);
    }

    @Override
    protected void useSpecialEffect(World world, EntityPlayer player) {
        for (int i = 2; i < 5; i += 2) {
            for (double angle = 0.0; angle < Math.PI * 2; angle += 0.39269908169872414) {
                int zOffset;
                int xOffset = (int)Math.round(Math.sin(angle) * (double)i);
                if (!(Math.sqrt(xOffset * xOffset + (zOffset = (int)Math.round(Math.cos(angle) * (double)i)) * zOffset) > 3.0)) continue;
                world.spawnEntityInWorld((Entity)new EntityLightningBolt(world, player.posX + (double)xOffset, player.posY, player.posZ + (double)zOffset));
            }
        }
        player.triggerAchievement((StatBase)DivineRPGAchievements.allHellLoose);
    }
}

