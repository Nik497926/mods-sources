/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.FMLClientHandler
 *  cpw.mods.fml.common.event.FMLPreInitializationEvent
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.particle.EntityFX
 *  net.minecraft.client.particle.EntityFlameFX
 *  net.minecraft.client.particle.EntityLavaFX
 *  net.minecraft.client.particle.EntityPortalFX
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.MinecraftForgeClient
 *  net.minecraftforge.common.MinecraftForge
 */
package net.divinerpg.utils.proxies;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import java.awt.Color;
import java.util.Random;
import net.divinerpg.client.ArcanaRenderer;
import net.divinerpg.client.particles.ParticleGray;
import net.divinerpg.client.particles.ParticleRed;
import net.divinerpg.client.particles.ParticlesWreck;
import net.divinerpg.client.render.entity.ArcanaEntityRenderer;
import net.divinerpg.client.render.entity.DepthsEntityRenderer;
import net.divinerpg.client.render.entity.IceikaEntityRenderer;
import net.divinerpg.client.render.entity.JourneyEntityRenderer;
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
import net.divinerpg.entities.fx.EntityConjuringFX;
import net.divinerpg.entities.fx.EntityDoomFX;
import net.divinerpg.entities.fx.EntityEdenPortalFX;
import net.divinerpg.entities.fx.EntityFireballFX;
import net.divinerpg.entities.fx.EntityGreenFlameFX;
import net.divinerpg.entities.fx.EntityGreenPortalFX;
import net.divinerpg.entities.fx.EntityGreenpaceFX;
import net.divinerpg.entities.fx.EntityHellstoneFX;
import net.divinerpg.entities.fx.EntityIceballFX;
import net.divinerpg.entities.fx.EntityMortumPortalFX;
import net.divinerpg.entities.fx.EntityRockFX;
import net.divinerpg.entities.fx.EntitySkythernPortalFX;
import net.divinerpg.entities.fx.EntityWildwoodPortalFX;
import net.divinerpg.entities.iceika.EntityWreck;
import net.divinerpg.items.base.ItemModBow;
import net.divinerpg.items.base.ItemProjectileShooter;
import net.divinerpg.items.vethea.ItemStaff;
import net.divinerpg.utils.Util;
import net.divinerpg.utils.events.ArcanaHelper;
import net.divinerpg.utils.events.ClientTicker;
import net.divinerpg.utils.events.DevHatEvent;
import net.divinerpg.utils.items.ArcanaItems;
import net.divinerpg.utils.items.VanillaItemsWeapons;
import net.divinerpg.utils.proxies.CommonProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.EntityFlameFX;
import net.minecraft.client.particle.EntityLavaFX;
import net.minecraft.client.particle.EntityPortalFX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy
extends CommonProxy {
    private static Random rand = new Random();

    @Override
    public void preInitClient(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register((Object)new ArcanaRenderer());
        Util.postFMLEvent(new ClientTicker());
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
        DepthsEntityRenderer.init();
        JourneyEntityRenderer.init();
        Util.postForgeEvent(new DevHatEvent());
    }

    @Override
    public void spawnParticle(World w, double x, double y, double z, String particle, boolean random) {
        this.spawnParticle(w, x, y, z, particle, random, 1);
    }

    @Override
    public void generateParticlesWreck(EntityWreck theEntity) {
        double motionX = theEntity.worldObj.rand.nextGaussian() * 0.6;
        double motionY = theEntity.worldObj.rand.nextGaussian() * 0.6;
        double motionZ = theEntity.worldObj.rand.nextGaussian() * 0.6;
        ParticlesWreck particle = new ParticlesWreck(theEntity.worldObj, theEntity.posX + (theEntity.worldObj.rand.nextDouble() - 0.5) * ((double)theEntity.width + 1.5), theEntity.posY + theEntity.worldObj.rand.nextDouble() * ((double)theEntity.height + 0.5) - (double)theEntity.yOffset, theEntity.posZ + (theEntity.worldObj.rand.nextDouble() - 0.5) * ((double)theEntity.width + 1.5), motionX, motionY, motionZ);
        Minecraft.getMinecraft().effectRenderer.addEffect((EntityFX)particle);
    }

    @Override
    public void spawnParticle(World w, double x, double y, double z, String particle, boolean random, int randFactor) {
        if (w == null) {
            w = Minecraft.getMinecraft().theWorld;
        }
        if (w.getChunkProvider().chunkExists(MathHelper.floor_double((double)(x / 16.0)), MathHelper.floor_double((double)(z / 16.0)))) {
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
            } else if (particle.equals("doom")) {
                fx = new EntityDoomFX(w, x, y, z, 0.0, 0.0, 0.0);
            } else if (particle.equals("iceball")) {
                fx = new EntityIceballFX(w, x, y, z, 0.0, 0.0, 0.0);
            } else if (particle.equals("hellstone")) {
                fx = new EntityHellstoneFX(w, x, y, z, 0.0, 0.0, 0.0);
            } else if (particle.equals("rock")) {
                fx = new EntityRockFX(w, x, y, z, 0.0, 0.0, 0.0);
            } else if (particle.equals("conjuring")) {
                fx = new EntityConjuringFX(w, x, y, z, 0.0, 0.0, 0.0);
            } else if (particle.equals("fire")) {
                fx = new EntityFireballFX(w, x, y, z, 0.0, 0.0, 0.0);
            } else if (particle.equals("poison")) {
                fx = new EntityGreenpaceFX(w, x, y, z, 0.0, 0.0, 0.0);
            } else if (particle.equals("enderFire")) {
                fx = new EntityLavaFX(w, x, y, z);
            } else if (particle.equals("poison")) {
                fx = new EntityGreenpaceFX(w, x, y, z, 0.0, 0.0, 0.0);
            }
            if (fx != null) {
                FMLClientHandler.instance().getClient().effectRenderer.addEffect(fx);
            }
        }
    }

    @Override
    public void spawnParticle(World w, double x, double y, double z, Color c, boolean random) {
        if (w == null) {
            w = Minecraft.getMinecraft().theWorld;
        }
        if (w.getChunkProvider().chunkExists(MathHelper.floor_double((double)(x / 16.0)), MathHelper.floor_double((double)(z / 16.0)))) {
            EntityColoredFX fx;
            if (random) {
                x += (rand.nextDouble() - rand.nextDouble()) / 4.0;
                y += (rand.nextDouble() - rand.nextDouble()) / 4.0;
                z += (rand.nextDouble() - rand.nextDouble()) / 4.0;
            }
            if ((fx = new EntityColoredFX(w, x, y, z, 0.0, 0.0, 0.0, c)) != null) {
                FMLClientHandler.instance().getClient().effectRenderer.addEffect((EntityFX)fx);
            }
        }
    }

    @Override
    public void updateClientArcana(float amount) {
        ArcanaHelper.getProperties((EntityPlayer)Minecraft.getMinecraft().thePlayer).setBarValue(amount);
    }

    @Override
    public void generateParticlesPurple(EntityPlayer theEntity) {
        double motionX = theEntity.worldObj.rand.nextGaussian() * 0.6;
        double motionY = theEntity.worldObj.rand.nextGaussian() * 0.6;
        double motionZ = theEntity.worldObj.rand.nextGaussian() * 0.6;
        ParticleGray particle = new ParticleGray(theEntity.worldObj, theEntity.posX + (theEntity.worldObj.rand.nextDouble() - 0.5) * ((double)theEntity.width + 1.5), theEntity.posY + theEntity.worldObj.rand.nextDouble() * ((double)theEntity.height + 0.5) - (double)theEntity.yOffset, theEntity.posZ + (theEntity.worldObj.rand.nextDouble() - 0.5) * ((double)theEntity.width + 1.5), motionX, motionY, motionZ);
        Minecraft.getMinecraft().effectRenderer.addEffect((EntityFX)particle);
    }

    @Override
    public void generateParticlesRed(Entity theEntity) {
        double motionX = theEntity.worldObj.rand.nextGaussian() * 0.6;
        double motionY = theEntity.worldObj.rand.nextGaussian() * 0.6;
        double motionZ = theEntity.worldObj.rand.nextGaussian() * 0.6;
        ParticleRed particle = new ParticleRed(theEntity.worldObj, theEntity.posX + (theEntity.worldObj.rand.nextDouble() - 0.5) * ((double)theEntity.width + 0.3), theEntity.posY + theEntity.worldObj.rand.nextDouble() * ((double)theEntity.height + 0.1) - (double)theEntity.yOffset, theEntity.posZ + (theEntity.worldObj.rand.nextDouble() - 0.5) * ((double)theEntity.width + 0.3), motionX, motionY, motionZ);
        Minecraft.getMinecraft().effectRenderer.addEffect((EntityFX)particle);
    }
}

