/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.event;

import com.meteor.extrabotany.common.core.handler.EntityHandler;
import com.meteor.extrabotany.common.core.handler.PropertyHandler;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class EventShield {
    ResourceLocation shield = new ResourceLocation("extrabotania", "textures/icons/shield1.png");
    ResourceLocation shield2 = new ResourceLocation("extrabotania", "textures/icons/shield2.png");
    ResourceLocation shield3 = new ResourceLocation("extrabotania", "textures/icons/shield3.png");
    ResourceLocation shield4 = new ResourceLocation("extrabotania", "textures/icons/shield4.png");
    ResourceLocation shield5 = new ResourceLocation("extrabotania", "textures/icons/shield5.png");
    ResourceLocation shield6 = new ResourceLocation("extrabotania", "textures/icons/shield6.png");
    ResourceLocation shield7 = new ResourceLocation("extrabotania", "textures/icons/shield7.png");
    ResourceLocation shield8 = new ResourceLocation("extrabotania", "textures/icons/shield8.png");
    ResourceLocation shield9 = new ResourceLocation("extrabotania", "textures/icons/shield9.png");
    ResourceLocation shield10 = new ResourceLocation("extrabotania", "textures/icons/shield10.png");
    ResourceLocation shield11 = new ResourceLocation("extrabotania", "textures/icons/shield11.png");
    ResourceLocation shield12 = new ResourceLocation("extrabotania", "textures/icons/shield12.png");
    ResourceLocation shield13 = new ResourceLocation("extrabotania", "textures/icons/shield13.png");
    ResourceLocation shield14 = new ResourceLocation("extrabotania", "textures/icons/shield14.png");
    ResourceLocation shield15 = new ResourceLocation("extrabotania", "textures/icons/shield15.png");

    @SubscribeEvent(priority=EventPriority.LOWEST)
    public void onPlayerAttacked(LivingHurtEvent event) {
        EntityPlayer player;
        if (event.entity instanceof EntityPlayer && PropertyHandler.getShieldAmount(player = (EntityPlayer)event.entity) > 0.0f) {
            float dam = event.ammount - PropertyHandler.getShieldAmount(player);
            float cur = PropertyHandler.getShieldAmount(player) - event.ammount;
            event.ammount = Math.max(0.0f, dam);
            PropertyHandler.setShieldAmount(Math.max(0.0f, cur), player);
            player.hurtResistantTime = 25;
            List mobs = player.worldObj.getEntitiesWithinAABB(IMob.class, AxisAlignedBB.getBoundingBox(player.posX - 2.0, player.posY - 2.0, player.posZ - 2.0, player.posX + 3.0, player.posY + 3.0, player.posZ + 3.0));
            List pros = player.worldObj.getEntitiesWithinAABB(IProjectile.class, AxisAlignedBB.getBoundingBox(player.posX - 2.0, player.posY - 2.0, player.posZ - 2.0, player.posX + 3.0, player.posY + 3.0, player.posZ + 3.0));
            for (Object mob : pros) {
                if (!(mob instanceof Entity)) continue;
                ((Entity)mob).setDead();
            }
            for (Object mob1 : mobs) {
                if (!(mob1 instanceof Entity)) continue;
                EntityHandler.knockBack((EntityLiving)mob1, player, 4.0f, 4.0f);
            }
        }
    }

    @SubscribeEvent
    public void onEntityConstructing(EntityEvent.EntityConstructing event) {
        if (event.entity instanceof EntityPlayer && PropertyHandler.get((EntityPlayer)event.entity) == null && event.entity instanceof EntityPlayer && event.entity.getExtendedProperties("Shield") == null) {
            event.entity.registerExtendedProperties("Shield", new PropertyHandler((EntityPlayer)event.entity));
        }
    }
}

