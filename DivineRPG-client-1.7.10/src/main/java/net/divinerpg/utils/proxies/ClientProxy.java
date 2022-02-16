/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.FMLClientHandler
 *  cpw.mods.fml.common.event.FMLPreInitializationEvent
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.particle.EntityFX
 *  net.minecraft.client.particle.EntityFlameFX
 *  net.minecraft.client.particle.EntityPortalFX
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.MinecraftForgeClient
 */
package net.divinerpg.utils.proxies;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import java.awt.Color;
import java.util.Random;
import net.divinerpg.client.ArcanaRenderer;
import net.divinerpg.client.render.BossTickHandler;
import net.divinerpg.client.render.entity.ArcanaEntityRenderer;
import net.divinerpg.client.render.entity.IceikaEntityRenderer;
import net.divinerpg.client.render.entity.TwilightEntityRenderer;
import net.divinerpg.client.render.entity.VanillaEntityRenderer;
import net.divinerpg.client.render.entity.VetheaEntityRenderer;
import net.divinerpg.client.render.item.RenderArcaniteBlaster;
import net.divinerpg.client.render.item.RenderBow;
import net.divinerpg.client.render.item.RenderPhaser;
import net.divinerpg.client.render.item.RenderProjectileShooter;
import net.divinerpg.client.render.item.RenderStaff;
import net.divinerpg.entities.fx.EntityBlackFlameFX;
import net.divinerpg.entities.fx.EntityBlueFlameFX;
import net.divinerpg.entities.fx.EntityColoredFX;
import net.divinerpg.entities.fx.EntityEdenPortalFX;
import net.divinerpg.entities.fx.EntityGreenFlameFX;
import net.divinerpg.entities.fx.EntityGreenPortalFX;
import net.divinerpg.entities.fx.EntityMortumPortalFX;
import net.divinerpg.entities.fx.EntitySkythernPortalFX;
import net.divinerpg.entities.fx.EntityWildwoodPortalFX;
import net.divinerpg.items.base.ItemModBow;
import net.divinerpg.items.base.ItemProjectileShooter;
import net.divinerpg.items.vethea.ItemStaff;
import net.divinerpg.utils.Util;
import net.divinerpg.utils.events.ArcanaHelper;
import net.divinerpg.utils.events.ClientTicker;
import net.divinerpg.utils.events.DevHatEvent;
import net.divinerpg.utils.events.EventExtraArmor;
import net.divinerpg.utils.events.EventOverlay;
import net.divinerpg.utils.items.ArcanaItems;
import net.divinerpg.utils.items.VanillaItemsWeapons;
import net.divinerpg.utils.proxies.CommonProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.EntityFlameFX;
import net.minecraft.client.particle.EntityPortalFX;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy
extends CommonProxy {
    private static Random rand = new Random();

    @Override
    public void preInitClient(FMLPreInitializationEvent event) {
        Util.postFMLEvent(new ArcanaRenderer());
        Util.postFMLEvent(new ClientTicker());
        Util.postForgeEvent(new EventOverlay());
        Util.postForgeEvent(new EventExtraArmor());
        ItemProjectileShooter.gunList.remove(VanillaItemsWeapons.scythe);
        for (Item bow : ItemModBow.bowList) {
            MinecraftForgeClient.registerItemRenderer((Item)bow, (IItemRenderer)new RenderBow());
        }
        for (Item gun : ItemProjectileShooter.gunList) {
            MinecraftForgeClient.registerItemRenderer((Item)gun, (IItemRenderer)new RenderProjectileShooter());
        }
        for (Item phaser : ItemProjectileShooter.phaserList) {
            MinecraftForgeClient.registerItemRenderer((Item)phaser, (IItemRenderer)new RenderPhaser());
        }
        for (Item staff : ItemStaff.staffList) {
            MinecraftForgeClient.registerItemRenderer((Item)staff, (IItemRenderer)new RenderStaff());
        }
        MinecraftForgeClient.registerItemRenderer((Item)VanillaItemsWeapons.corruptedCannon, (IItemRenderer)new RenderProjectileShooter());
        MinecraftForgeClient.registerItemRenderer((Item)ArcanaItems.arcaniteBlaster, (IItemRenderer)new RenderArcaniteBlaster());
    }

    @Override
    public void renderThings() {
        VanillaEntityRenderer.init();
        TwilightEntityRenderer.init();
        IceikaEntityRenderer.init();
        VetheaEntityRenderer.init();
        ArcanaEntityRenderer.init();
        Util.postFMLEvent(new BossTickHandler());
        Util.postForgeEvent(new DevHatEvent());
    }

    @Override
    public void spawnParticle(World w, double x, double y, double z, String particle, boolean random) {
        this.spawnParticle(w, x, y, z, particle, random, 1);
    }

    @Override
    public void spawnParticle(World w, double x, double y, double z, String particle, boolean random, int randFactor) {
        if (w == null) {
            w = Minecraft.getMinecraft().theWorld;
        }
        if (!w.getChunkProvider().chunkExists(MathHelper.floor_double((double)(x / 16.0)), MathHelper.floor_double((double)(z / 16.0)))) {
            return;
        }
        if (random) {
            x += (rand.nextDouble() - rand.nextDouble()) / (double)(4 / randFactor);
            y += (rand.nextDouble() - rand.nextDouble()) / (double)(4 / randFactor);
            z += (rand.nextDouble() - rand.nextDouble()) / (double)(4 / randFactor);
        }
        EntityFX fx = null;
        if (particle.equals("eden")) {
            fx = new EntityEdenPortalFX(w, x, y, z, 0.0, 0.0, 0.0);
        } else if (particle.equals("wildwood")) {
            fx = new EntityWildwoodPortalFX(w, x, y, z, 0.0, 0.0, 0.0);
        } else if (particle.equals("apalachia")) {
            fx = new EntityPortalFX(w, x, y, z, 0.0, 0.0, 0.0);
        } else if (particle.equals("skythern")) {
            fx = new EntitySkythernPortalFX(w, x, y, z, 0.0, 0.0, 0.0);
        } else if (particle.equals("mortum")) {
            fx = new EntityMortumPortalFX(w, x, y, z, 0.0, 0.0, 0.0);
        } else if (particle.equals("halite")) {
            fx = new EntityGreenPortalFX(w, x, y, z, 0.0, 0.0, 0.0);
        } else if (particle.equals("greenFlame")) {
            fx = new EntityGreenFlameFX(w, x, y, z, 0.0, 0.0, 0.0);
        } else if (particle.equals("blackFlame")) {
            fx = new EntityBlackFlameFX(w, x, y, z, 0.0, 0.0, 0.0);
        } else if (particle.equals("blueFlame")) {
            fx = new EntityBlueFlameFX(w, x, y, z, 0.0, 0.0, 0.0);
        } else if (particle.equals("orangeFlame")) {
            fx = new EntityFlameFX(w, x, y, z, 0.0, 0.0, 0.0);
        }
        if (fx != null) {
            FMLClientHandler.instance().getClient().effectRenderer.addEffect(fx);
        }
    }

    @Override
    public void spawnParticle(World w, double x, double y, double z, Color c, boolean random) {
        EntityColoredFX fx;
        if (w == null) {
            w = Minecraft.getMinecraft().theWorld;
        }
        if (!w.getChunkProvider().chunkExists(MathHelper.floor_double((double)(x / 16.0)), MathHelper.floor_double((double)(z / 16.0)))) {
            return;
        }
        if (random) {
            x += (rand.nextDouble() - rand.nextDouble()) / 4.0;
            y += (rand.nextDouble() - rand.nextDouble()) / 4.0;
            z += (rand.nextDouble() - rand.nextDouble()) / 4.0;
        }
        if ((fx = new EntityColoredFX(w, x, y, z, 0.0, 0.0, 0.0, c)) != null) {
            FMLClientHandler.instance().getClient().effectRenderer.addEffect((EntityFX)fx);
        }
    }

    @Override
    public void updateClientArcana(float amount) {
        ArcanaHelper.getProperties((EntityPlayer)Minecraft.getMinecraft().thePlayer).setBarValue(amount);
    }
}

