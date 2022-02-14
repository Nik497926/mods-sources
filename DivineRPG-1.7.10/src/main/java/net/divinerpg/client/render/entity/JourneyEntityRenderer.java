/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.RenderingRegistry
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.renderer.entity.Render
 */
package net.divinerpg.client.render.entity;

import cpw.mods.fml.client.registry.RenderingRegistry;
import net.divinerpg.client.render.EntityResourceLocation;
import net.divinerpg.client.render.RenderDivineMob;
import net.divinerpg.client.render.entity.RenderInvisProjectile;
import net.divinerpg.client.render.entity.boiling.ModelBoilTrader;
import net.divinerpg.client.render.entity.boiling.ModelEscapedConvict;
import net.divinerpg.client.render.entity.boiling.ModelFrightener;
import net.divinerpg.client.render.entity.boiling.ModelScreamer;
import net.divinerpg.client.render.entity.depths.ModelDarkfish;
import net.divinerpg.client.render.entity.depths.ModelRoc;
import net.divinerpg.client.render.entity.euca.RenderCorallator;
import net.divinerpg.client.render.entity.euca.model.ModelAlloyMender;
import net.divinerpg.client.render.entity.euca.model.ModelCorallator;
import net.divinerpg.client.render.entity.euca.model.ModelDynaster;
import net.divinerpg.client.render.entity.euca.model.ModelEucaCharger;
import net.divinerpg.client.render.entity.euca.model.ModelEucaFighter;
import net.divinerpg.client.render.entity.euca.model.ModelGoldbot;
import net.divinerpg.client.render.entity.euca.model.ModelGolder;
import net.divinerpg.client.render.entity.euca.model.ModelMage;
import net.divinerpg.client.render.entity.euca.model.ModelShimmerer;
import net.divinerpg.client.render.entity.euca.model.ModelSilverbot;
import net.divinerpg.client.render.entity.vanilla.RenderModBiped;
import net.divinerpg.client.render.entity.vanilla.model.ModelCaveling;
import net.divinerpg.client.render.entity.vanilla.model.ModelNetherBeast;
import net.divinerpg.client.render.entity.vanilla.model.ModelReaper;
import net.divinerpg.client.render.entity.vanilla.model.ModelRockiteGolem;
import net.divinerpg.client.render.entity.vanilla.model.ModelStonewalker;
import net.divinerpg.client.render.entity.vanilla.model.ModelWitherspine;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.entities.base.EntityTrollTrader;
import net.divinerpg.entities.boil.EntityBrisonScreamer;
import net.divinerpg.entities.boil.EntityBurningLight;
import net.divinerpg.entities.boil.EntityFrightener;
import net.divinerpg.entities.boil.EntityLavasnake;
import net.divinerpg.entities.boil.EntityScreamer;
import net.divinerpg.entities.boil.npc.EntityBoilTrader;
import net.divinerpg.entities.boil.npc.EntityEscapedConvict;
import net.divinerpg.entities.boil.npc.RenderTrollTrader;
import net.divinerpg.entities.boil.npc.modelTrollTrader;
import net.divinerpg.entities.euca.EntityAlloyMender;
import net.divinerpg.entities.euca.EntityCorallator;
import net.divinerpg.entities.euca.EntityDynaster;
import net.divinerpg.entities.euca.EntityEucaCharger;
import net.divinerpg.entities.euca.EntityEucaFighter;
import net.divinerpg.entities.euca.EntityGoldbot;
import net.divinerpg.entities.euca.EntityGolder;
import net.divinerpg.entities.euca.EntityGolditeMage;
import net.divinerpg.entities.euca.EntityGoldwing;
import net.divinerpg.entities.euca.EntityNoExpShimmerer;
import net.divinerpg.entities.euca.EntityShimmerer;
import net.divinerpg.entities.euca.EntitySilverbot;
import net.divinerpg.entities.lelyetia.EntityExohead;
import net.divinerpg.entities.lelyetia.EntityFlye;
import net.divinerpg.entities.lelyetia.EntityGraw;
import net.divinerpg.entities.lelyetia.EntityGrawShot;
import net.divinerpg.entities.lelyetia.EntityGrobbler;
import net.divinerpg.entities.lelyetia.EntityLelyetianCaster;
import net.divinerpg.entities.lelyetia.EntityLelyetianShot;
import net.divinerpg.entities.lelyetia.EntityLelyetianWarrior;
import net.divinerpg.entities.lelyetia.modelExohead;
import net.divinerpg.entities.lelyetia.modelFlye;
import net.divinerpg.entities.lelyetia.modelGraw;
import net.divinerpg.entities.lelyetia.modelGrobbler;
import net.divinerpg.entities.lelyetia.modelLelyetianCaster;
import net.divinerpg.entities.lelyetia.modelLelyetianWarrior;
import net.divinerpg.entities.vanilla.EntityCaveling;
import net.divinerpg.entities.vanilla.EntityHellBot;
import net.divinerpg.entities.vanilla.EntityJourneyBlacksmith;
import net.divinerpg.entities.vanilla.EntityJourneyMage;
import net.divinerpg.entities.vanilla.EntityNetherBeast;
import net.divinerpg.entities.vanilla.EntityNoExpHellBot;
import net.divinerpg.entities.vanilla.EntityReaper;
import net.divinerpg.entities.vanilla.EntityRockiteGolem;
import net.divinerpg.entities.vanilla.EntityStonewalker;
import net.divinerpg.entities.vanilla.EntityTempleGuardian;
import net.divinerpg.entities.vanilla.EntityWitherspine;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.Render;

public class JourneyEntityRenderer {
    private static EntityResourceLocation x;
    private static EntityStats s;

    public static void init() {
        RenderingRegistry.registerEntityRenderingHandler(EntityDynaster.class, (Render)new RenderDivineMob(new ModelDynaster(), 0.6f, 0.6f, EntityResourceLocation.DYNASTER));
        RenderingRegistry.registerEntityRenderingHandler(EntityAlloyMender.class, (Render)new RenderDivineMob(new ModelAlloyMender(), 0.8f, 0.7f, EntityResourceLocation.ALLOY_MENDER));
        RenderingRegistry.registerEntityRenderingHandler(EntityEucaCharger.class, (Render)new RenderDivineMob(new ModelEucaCharger(), 0.4f, 1.3f, EntityResourceLocation.EUCA_CHARGER));
        RenderingRegistry.registerEntityRenderingHandler(EntityEucaFighter.class, (Render)new RenderDivineMob(new ModelEucaFighter(), 0.4f, 1.0f, EntityResourceLocation.EUCA_FIGHTER));
        RenderingRegistry.registerEntityRenderingHandler(EntityGoldbot.class, (Render)new RenderDivineMob(new ModelGoldbot(), 0.4f, 1.0f, EntityResourceLocation.GOLDBOT));
        RenderingRegistry.registerEntityRenderingHandler(EntityGolder.class, (Render)new RenderDivineMob(new ModelGolder(), 0.4f, 1.0f, EntityResourceLocation.GOLDER));
        RenderingRegistry.registerEntityRenderingHandler(EntityGoldwing.class, (Render)new RenderDivineMob(new ModelRoc(), 0.4f, 1.0f, EntityResourceLocation.GOLDWING));
        RenderingRegistry.registerEntityRenderingHandler(EntityShimmerer.class, (Render)new RenderDivineMob(new ModelShimmerer(), 0.4f, 1.0f, EntityResourceLocation.SHIMMERER));
        RenderingRegistry.registerEntityRenderingHandler(EntityNoExpShimmerer.class, (Render)new RenderDivineMob(new ModelShimmerer(), 0.4f, 1.0f, EntityResourceLocation.SHIMMERER));
        RenderingRegistry.registerEntityRenderingHandler(EntitySilverbot.class, (Render)new RenderDivineMob(new ModelSilverbot(), 0.4f, 1.0f, EntityResourceLocation.SILVERBOT));
        RenderingRegistry.registerEntityRenderingHandler(EntityGolditeMage.class, (Render)new RenderDivineMob((ModelBase)new ModelMage(), 0.4f, 1.0f, EntityResourceLocation.GOLDITE_MAGE));
        RenderingRegistry.registerEntityRenderingHandler(EntityJourneyMage.class, (Render)new RenderDivineMob((ModelBase)new ModelMage(), 0.4f, 1.0f, EntityResourceLocation.JOURNEY_MAGE));
        RenderingRegistry.registerEntityRenderingHandler(EntityTempleGuardian.class, (Render)new RenderDivineMob((ModelBase)new ModelBiped(), 0.4f, 1.5f, EntityResourceLocation.TEMPLE_JOURNEY));
        RenderingRegistry.registerEntityRenderingHandler(EntityJourneyBlacksmith.class, (Render)new RenderDivineMob((ModelBase)new ModelBiped(), 0.4f, 1.0f, EntityResourceLocation.JOURNEY_BLACKSMITH));
        RenderingRegistry.registerEntityRenderingHandler(EntityRockiteGolem.class, (Render)new RenderDivineMob(new ModelRockiteGolem(), 0.4f, 1.0f, EntityResourceLocation.ROCKITE_GOLEM));
        RenderingRegistry.registerEntityRenderingHandler(EntityStonewalker.class, (Render)new RenderDivineMob(new ModelStonewalker(), 0.4f, 1.0f, EntityResourceLocation.STONEWALKER));
        RenderingRegistry.registerEntityRenderingHandler(EntityCaveling.class, (Render)new RenderDivineMob(new ModelCaveling(), 0.4f, 1.0f, EntityResourceLocation.CAVELING));
        RenderingRegistry.registerEntityRenderingHandler(EntityHellBot.class, (Render)new RenderDivineMob(new ModelGoldbot(), 0.4f, 1.0f, EntityResourceLocation.HELLBOT));
        RenderingRegistry.registerEntityRenderingHandler(EntityNoExpHellBot.class, (Render)new RenderDivineMob(new ModelGoldbot(), 0.4f, 1.0f, EntityResourceLocation.HELLBOT));
        RenderingRegistry.registerEntityRenderingHandler(EntityReaper.class, (Render)new RenderDivineMob(new ModelReaper(), 0.4f, 1.0f, EntityResourceLocation.REAPER));
        RenderingRegistry.registerEntityRenderingHandler(EntityWitherspine.class, (Render)new RenderDivineMob(new ModelWitherspine(), 0.4f, 1.0f, EntityResourceLocation.WITHERSPINE));
        RenderingRegistry.registerEntityRenderingHandler(EntityCorallator.class, (Render)new RenderCorallator(new ModelCorallator(), 0.4f, 2.0f, EntityResourceLocation.CORALLATOR, 24));
        RenderingRegistry.registerEntityRenderingHandler(EntityNetherBeast.class, (Render)new RenderCorallator(new ModelNetherBeast(), 0.4f, 2.0f, EntityResourceLocation.NETHER_BEAST, 25));
        RenderingRegistry.registerEntityRenderingHandler(EntityGrawShot.class, (Render)new RenderInvisProjectile());
        RenderingRegistry.registerEntityRenderingHandler(EntityLelyetianShot.class, (Render)new RenderInvisProjectile());
        RenderingRegistry.registerEntityRenderingHandler(EntityTrollTrader.class, (Render)new RenderTrollTrader(new modelTrollTrader(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityScreamer.class, (Render)new RenderDivineMob((ModelBase)new ModelScreamer(), 0.4f, 1.0f, EntityResourceLocation.SCREAMER));
        RenderingRegistry.registerEntityRenderingHandler(EntityBrisonScreamer.class, (Render)new RenderDivineMob((ModelBase)new ModelScreamer(), 0.4f, 1.0f, EntityResourceLocation.SCREAMER));
        RenderingRegistry.registerEntityRenderingHandler(EntityFrightener.class, (Render)new RenderDivineMob((ModelBase)new ModelFrightener(), 0.4f, 1.0f, EntityResourceLocation.FRIGHTENER));
        RenderingRegistry.registerEntityRenderingHandler(EntityBurningLight.class, (Render)new RenderModBiped((ModelBase)new ModelBiped(), EntityResourceLocation.BURNING_LIGHT));
        RenderingRegistry.registerEntityRenderingHandler(EntityLavasnake.class, (Render)new RenderDivineMob(new ModelDarkfish(), 0.4f, 1.0f, EntityResourceLocation.LAVASNAKE));
        RenderingRegistry.registerEntityRenderingHandler(EntityEscapedConvict.class, (Render)new RenderDivineMob(new ModelEscapedConvict(), 0.8f, 1.0f, EntityResourceLocation.ESCAPED_CONVICT));
        RenderingRegistry.registerEntityRenderingHandler(EntityBoilTrader.class, (Render)new RenderDivineMob(new ModelBoilTrader(), 0.0f, 1.0f, EntityResourceLocation.BOIL_TRADER));
        RenderingRegistry.registerEntityRenderingHandler(EntityExohead.class, (Render)new RenderDivineMob(new modelExohead(), 0.0f, 1.0f, EntityResourceLocation.EXOHEAD));
        RenderingRegistry.registerEntityRenderingHandler(EntityFlye.class, (Render)new RenderDivineMob(new modelFlye(), 0.0f, 1.0f, EntityResourceLocation.FLYE));
        RenderingRegistry.registerEntityRenderingHandler(EntityGrobbler.class, (Render)new RenderDivineMob(new modelGrobbler(), 0.0f, 1.0f, EntityResourceLocation.GROBBLER));
        RenderingRegistry.registerEntityRenderingHandler(EntityGraw.class, (Render)new RenderDivineMob(new modelGraw(), 0.0f, 1.0f, EntityResourceLocation.GRAW));
        RenderingRegistry.registerEntityRenderingHandler(EntityLelyetianCaster.class, (Render)new RenderDivineMob(new modelLelyetianCaster(), 0.0f, 1.0f, EntityResourceLocation.CASTER));
        RenderingRegistry.registerEntityRenderingHandler(EntityLelyetianWarrior.class, (Render)new RenderDivineMob(new modelLelyetianWarrior(), 0.0f, 1.0f, EntityResourceLocation.WARRIOR));
    }
}

