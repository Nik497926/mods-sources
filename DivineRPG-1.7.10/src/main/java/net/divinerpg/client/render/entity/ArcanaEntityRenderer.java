/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ClientRegistry
 *  cpw.mods.fml.client.registry.RenderingRegistry
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraftforge.client.IItemRenderer
 */
package net.divinerpg.client.render.entity;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.divinerpg.blocks.arcana.container.tile_entity.TileEntityDemonFurnace;
import net.divinerpg.blocks.arcana.container.tile_entity.TileEntityDramixAltar;
import net.divinerpg.blocks.arcana.container.tile_entity.TileEntityExtractor;
import net.divinerpg.blocks.arcana.container.tile_entity.TileEntityParasectaAltar;
import net.divinerpg.client.render.EntityResourceLocation;
import net.divinerpg.client.render.RenderDivineBoss;
import net.divinerpg.client.render.RenderDivineMob;
import net.divinerpg.client.render.RenderIconProjectile;
import net.divinerpg.client.render.RenderSizeable;
import net.divinerpg.client.render.RenderSpecialProjectile;
import net.divinerpg.client.render.block.TileEntityDemonFurnaceRenderer;
import net.divinerpg.client.render.block.TileEntityDramixAltarRenderer;
import net.divinerpg.client.render.block.TileEntityExtractorRenderer;
import net.divinerpg.client.render.block.TileEntityParasectaAltarRenderer;
import net.divinerpg.client.render.entity.arcana.RenderStarlight;
import net.divinerpg.client.render.entity.arcana.model.ModelDeathHound;
import net.divinerpg.client.render.entity.arcana.model.ModelDeathcryx;
import net.divinerpg.client.render.entity.arcana.model.ModelDramix;
import net.divinerpg.client.render.entity.arcana.model.ModelDungeonDemon;
import net.divinerpg.client.render.entity.arcana.model.ModelDungeonPrisoner;
import net.divinerpg.client.render.entity.arcana.model.ModelLeorna;
import net.divinerpg.client.render.entity.arcana.model.ModelParasecta;
import net.divinerpg.client.render.entity.arcana.model.ModelParatiku;
import net.divinerpg.client.render.entity.arcana.model.ModelRazorback;
import net.divinerpg.client.render.entity.arcana.model.ModelRejuvGolem;
import net.divinerpg.client.render.entity.arcana.model.ModelRoamer;
import net.divinerpg.client.render.entity.arcana.model.ModelSeimer;
import net.divinerpg.client.render.entity.arcana.model.ModelWraith;
import net.divinerpg.client.render.entity.twilight.model.ModelSamek;
import net.divinerpg.client.render.item.ItemRenderAltar;
import net.divinerpg.client.render.item.ItemRenderDemonFurnace;
import net.divinerpg.entities.arcana.EntityCaptianMerik;
import net.divinerpg.entities.arcana.EntityConstructor;
import net.divinerpg.entities.arcana.EntityDatticon;
import net.divinerpg.entities.arcana.EntityDeathHound;
import net.divinerpg.entities.arcana.EntityDeathcryx;
import net.divinerpg.entities.arcana.EntityDramix;
import net.divinerpg.entities.arcana.EntityDungeonDemon;
import net.divinerpg.entities.arcana.EntityDungeonPrisoner;
import net.divinerpg.entities.arcana.EntityFyracryx;
import net.divinerpg.entities.arcana.EntityGolemOfRejuv;
import net.divinerpg.entities.arcana.EntityLeorna;
import net.divinerpg.entities.arcana.EntityLivingStatue;
import net.divinerpg.entities.arcana.EntityParasecta;
import net.divinerpg.entities.arcana.EntityParatiku;
import net.divinerpg.entities.arcana.EntityRazorback;
import net.divinerpg.entities.arcana.EntityRoamer;
import net.divinerpg.entities.arcana.EntitySeimer;
import net.divinerpg.entities.arcana.EntityVatticus;
import net.divinerpg.entities.arcana.EntityWarGeneral;
import net.divinerpg.entities.arcana.EntityWraith;
import net.divinerpg.entities.arcana.EntityZelus;
import net.divinerpg.entities.arcana.projectile.EntityAttractor;
import net.divinerpg.entities.arcana.projectile.EntityFirefly;
import net.divinerpg.entities.arcana.projectile.EntityGrenade;
import net.divinerpg.entities.arcana.projectile.EntityMerikMissile;
import net.divinerpg.entities.arcana.projectile.EntityMeteor;
import net.divinerpg.entities.arcana.projectile.EntityReflector;
import net.divinerpg.entities.arcana.projectile.EntitySparkler;
import net.divinerpg.entities.arcana.projectile.EntityStar;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.utils.Util;
import net.divinerpg.utils.blocks.ArcanaBlocks;
import net.divinerpg.utils.items.ArcanaItems;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraftforge.client.IItemRenderer;

public class ArcanaEntityRenderer {
    private static EntityResourceLocation x;
    private static EntityStats s;

    public static void init() {
        RenderingRegistry.registerEntityRenderingHandler(EntityStar.class, (Render)new RenderStarlight(EntityResourceLocation.starlight));
        RenderingRegistry.registerEntityRenderingHandler(EntitySparkler.class, (Render)new RenderSpecialProjectile(EntityResourceLocation.sparkler));
        RenderingRegistry.registerEntityRenderingHandler(EntityFirefly.class, (Render)new RenderSpecialProjectile(EntityResourceLocation.firefly));
        RenderingRegistry.registerEntityRenderingHandler(EntityGrenade.class, (Render)new RenderIconProjectile(ArcanaItems.grenade));
        RenderingRegistry.registerEntityRenderingHandler(EntityMerikMissile.class, (Render)new RenderSpecialProjectile(EntityResourceLocation.meriksMissile));
        RenderingRegistry.registerEntityRenderingHandler(EntityMeteor.class, (Render)new RenderSpecialProjectile(EntityResourceLocation.meteor, 2.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityAttractor.class, (Render)new RenderSpecialProjectile(EntityResourceLocation.blank, 1.0f));
        RenderingRegistry.registerEntityRenderingHandler(EntityReflector.class, (Render)new RenderSpecialProjectile(EntityResourceLocation.blank, 1.0f));
        RenderingRegistry.registerEntityRenderingHandler(EntityRoamer.class, (Render)new RenderDivineMob(new ModelRoamer(), EntityResourceLocation.roamer));
        RenderingRegistry.registerEntityRenderingHandler(EntityDeathcryx.class, (Render)new RenderDivineMob(new ModelDeathcryx(), EntityResourceLocation.deathcryx));
        RenderingRegistry.registerEntityRenderingHandler(EntityDeathHound.class, (Render)new RenderDivineMob(new ModelDeathHound(), EntityResourceLocation.death_hound));
        RenderingRegistry.registerEntityRenderingHandler(EntityLeorna.class, (Render)new RenderDivineMob(new ModelLeorna(), EntityResourceLocation.leorna));
        RenderingRegistry.registerEntityRenderingHandler(EntityRazorback.class, (Render)new RenderDivineMob(new ModelRazorback(), EntityResourceLocation.razorback));
        RenderingRegistry.registerEntityRenderingHandler(EntityConstructor.class, (Render)new RenderSizeable(new ModelDramix(), 0.0f, 0.4f, EntityResourceLocation.constructor));
        RenderingRegistry.registerEntityRenderingHandler(EntityLivingStatue.class, (Render)new RenderDivineMob((ModelBase)new ModelBiped(), EntityResourceLocation.living_statue));
        RenderingRegistry.registerEntityRenderingHandler(EntityDungeonPrisoner.class, (Render)new RenderDivineMob(new ModelDungeonPrisoner(), EntityResourceLocation.dungeon_prisoner));
        RenderingRegistry.registerEntityRenderingHandler(EntityDungeonDemon.class, (Render)new RenderDivineMob(new ModelDungeonDemon(), EntityResourceLocation.dungeon_demon));
        RenderingRegistry.registerEntityRenderingHandler(EntityCaptianMerik.class, (Render)new RenderDivineMob(new ModelSamek(), EntityResourceLocation.captain_merik));
        RenderingRegistry.registerEntityRenderingHandler(EntityDatticon.class, (Render)new RenderDivineMob(new ModelSamek(), EntityResourceLocation.datticon));
        RenderingRegistry.registerEntityRenderingHandler(EntityVatticus.class, (Render)new RenderDivineMob((ModelBase)new ModelBiped(), EntityResourceLocation.vatticus));
        RenderingRegistry.registerEntityRenderingHandler(EntityWarGeneral.class, (Render)new RenderDivineMob(new ModelSamek(), EntityResourceLocation.warGeneral));
        RenderingRegistry.registerEntityRenderingHandler(EntityZelus.class, (Render)new RenderDivineMob((ModelBase)new ModelBiped(), EntityResourceLocation.zelus));
        RenderingRegistry.registerEntityRenderingHandler(EntityFyracryx.class, (Render)new RenderDivineMob(new ModelDeathcryx(), EntityResourceLocation.fyracryx));
        RenderingRegistry.registerEntityRenderingHandler(EntityParatiku.class, (Render)new RenderDivineMob(new ModelParatiku(), EntityResourceLocation.paratiku));
        RenderingRegistry.registerEntityRenderingHandler(EntityGolemOfRejuv.class, (Render)new RenderDivineMob(new ModelRejuvGolem(), 0.5f, 1.5f, EntityResourceLocation.golem_rejuv));
        RenderingRegistry.registerEntityRenderingHandler(EntitySeimer.class, (Render)new RenderDivineMob(new ModelSeimer(), EntityResourceLocation.seimer));
        RenderingRegistry.registerEntityRenderingHandler(EntityWraith.class, (Render)new RenderDivineMob(new ModelWraith(), EntityResourceLocation.wraith));
        RenderingRegistry.registerEntityRenderingHandler(EntityDramix.class, (Render)new RenderDivineBoss(new ModelDramix(), 0.7f, 1.2f, EntityResourceLocation.dramix, 16));
        RenderingRegistry.registerEntityRenderingHandler(EntityParasecta.class, (Render)new RenderDivineBoss(new ModelParasecta(), 0.7f, 1.5f, EntityResourceLocation.parasecta, 17));
        ArcanaEntityRenderer.addBlockRendering();
    }

    public static void addBlockRendering() {
        Util.registerItemRenderer(ArcanaBlocks.dramixAltar, (IItemRenderer)new ItemRenderAltar("dramixAltar"));
        Util.registerItemRenderer(ArcanaBlocks.parasectaAltar, (IItemRenderer)new ItemRenderAltar("parasectaAltar"));
        Util.registerItemRenderer(ArcanaBlocks.arcanaExtractor, (IItemRenderer)new ItemRenderAltar("extractor"));
        Util.registerItemRenderer(ArcanaBlocks.demonFurnace, (IItemRenderer)new ItemRenderDemonFurnace());
        Util.registerItemRenderer(ArcanaBlocks.demonFurnaceOn, (IItemRenderer)new ItemRenderDemonFurnace());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDramixAltar.class, (TileEntitySpecialRenderer)new TileEntityDramixAltarRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityParasectaAltar.class, (TileEntitySpecialRenderer)new TileEntityParasectaAltarRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityExtractor.class, (TileEntitySpecialRenderer)new TileEntityExtractorRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDemonFurnace.class, (TileEntitySpecialRenderer)new TileEntityDemonFurnaceRenderer());
    }
}

