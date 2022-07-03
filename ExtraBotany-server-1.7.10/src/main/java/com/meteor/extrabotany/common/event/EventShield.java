/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.event;

import com.meteor.extrabotany.client.render.RenderShield;
import com.meteor.extrabotany.common.core.handler.EntityHandler;
import com.meteor.extrabotany.common.core.handler.PropertyHandler;
import com.meteor.extrabotany.common.item.relic.legendary.armor.awake.ItemAwakeOGArmor;
import com.meteor.extrabotany.common.item.relic.legendary.armor.killer.ItemKillerArmor;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.IExtendedEntityProperties;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.item.IBaubleRender;
import vazkii.botania.common.core.helper.ItemNBTHelper;

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
            List<IMob> mobs = player.worldObj.getEntitiesWithinAABB(IMob.class, AxisAlignedBB.getBoundingBox((double)(player.posX - 2.0), (double)(player.posY - 2.0), (double)(player.posZ - 2.0), (double)(player.posX + 3.0), (double)(player.posY + 3.0), (double)(player.posZ + 3.0)));
            List<IProjectile> pros = player.worldObj.getEntitiesWithinAABB(IProjectile.class, AxisAlignedBB.getBoundingBox((double)(player.posX - 2.0), (double)(player.posY - 2.0), (double)(player.posZ - 2.0), (double)(player.posX + 3.0), (double)(player.posY + 3.0), (double)(player.posZ + 3.0)));
            for (IProjectile mob : pros) {
                if (!(mob instanceof Entity)) continue;
                ((Entity)mob).setDead();
            }
            for (IMob mob1 : mobs) {
                if (!(mob1 instanceof Entity)) continue;
                EntityHandler.knockBack((EntityLiving)mob1, (Entity)player, 4.0f, 4.0f);
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void onPlayerRender(RenderPlayerEvent.Specials.Post event) {
        EntityPlayer player = event.entityPlayer;
        this.renderShield((RenderPlayerEvent)event, IBaubleRender.RenderType.BODY);
    }

    @SideOnly(value=Side.CLIENT)
    public void renderShield(RenderPlayerEvent event, IBaubleRender.RenderType type) {
        EntityPlayer player = event.entityPlayer;
        if (ItemAwakeOGArmor.getFullArmorSeven(player, 7).booleanValue() || ItemKillerArmor.hasFullArmor(player)) {
            ItemStack st = player.inventory.armorInventory[2];
            int i = ItemNBTHelper.getInt((ItemStack)st, (String)"render", (int)1);
            GL11.glPushMatrix();
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            ResourceLocation res = i == 1 ? this.shield : (i == 2 ? this.shield2 : (i == 3 ? this.shield3 : (i == 4 ? this.shield4 : (i == 5 ? this.shield5 : (i == 6 ? this.shield6 : (i == 7 ? this.shield7 : (i == 8 ? this.shield8 : (i == 9 ? this.shield9 : (i == 10 ? this.shield10 : (i == 11 ? this.shield11 : (i == 12 ? this.shield12 : (i == 13 ? this.shield13 : (i == 14 ? this.shield14 : (i == 15 ? this.shield15 : this.shield))))))))))))));
            RenderShield.renderShield2((Entity)player, event.partialRenderTick, res);
            GL11.glPopMatrix();
        }
    }

    @SubscribeEvent
    public void onEntityConstructing(EntityEvent.EntityConstructing event) {
        if (event.entity instanceof EntityPlayer && PropertyHandler.get((EntityPlayer)event.entity) == null && event.entity instanceof EntityPlayer && event.entity.getExtendedProperties("Shield") == null) {
            event.entity.registerExtendedProperties("Shield", (IExtendedEntityProperties)new PropertyHandler((EntityPlayer)event.entity));
        }
    }
}

