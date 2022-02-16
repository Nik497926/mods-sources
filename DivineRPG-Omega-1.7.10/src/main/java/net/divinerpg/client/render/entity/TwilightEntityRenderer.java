/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ClientRegistry
 *  cpw.mods.fml.client.registry.RenderingRegistry
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.item.Item
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.MinecraftForgeClient
 */
package net.divinerpg.client.render.entity;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.divinerpg.blocks.twilight.TileEntityEdenChest;
import net.divinerpg.client.render.EntityResourceLocation;
import net.divinerpg.client.render.RenderDivineBoss;
import net.divinerpg.client.render.RenderDivineMob;
import net.divinerpg.client.render.RenderProjectile;
import net.divinerpg.client.render.RenderSpecialProjectile;
import net.divinerpg.client.render.block.RenderEdenChest;
import net.divinerpg.client.render.entity.twilight.RenderBunny;
import net.divinerpg.client.render.entity.twilight.RenderDAR;
import net.divinerpg.client.render.entity.twilight.RenderEnchantedArcher;
import net.divinerpg.client.render.entity.twilight.RenderEternalArcher;
import net.divinerpg.client.render.entity.twilight.RenderSunArcher;
import net.divinerpg.client.render.entity.twilight.RenderTwilightArcher;
import net.divinerpg.client.render.entity.twilight.model.ModelAngryBunny;
import net.divinerpg.client.render.entity.twilight.model.ModelBasalisk;
import net.divinerpg.client.render.entity.twilight.model.ModelBehemoth;
import net.divinerpg.client.render.entity.twilight.model.ModelBunny;
import net.divinerpg.client.render.entity.twilight.model.ModelCadillion;
import net.divinerpg.client.render.entity.twilight.model.ModelCori;
import net.divinerpg.client.render.entity.twilight.model.ModelDenseDemon;
import net.divinerpg.client.render.entity.twilight.model.ModelDensos;
import net.divinerpg.client.render.entity.twilight.model.ModelEnchantedArcher;
import net.divinerpg.client.render.entity.twilight.model.ModelEnchantedWarrior;
import net.divinerpg.client.render.entity.twilight.model.ModelEpiphite;
import net.divinerpg.client.render.entity.twilight.model.ModelEternalArcher;
import net.divinerpg.client.render.entity.twilight.model.ModelGreenfeet;
import net.divinerpg.client.render.entity.twilight.model.ModelKarot;
import net.divinerpg.client.render.entity.twilight.model.ModelMadivel;
import net.divinerpg.client.render.entity.twilight.model.ModelMage;
import net.divinerpg.client.render.entity.twilight.model.ModelMegalith;
import net.divinerpg.client.render.entity.twilight.model.ModelMoonWolf;
import net.divinerpg.client.render.entity.twilight.model.ModelMystic;
import net.divinerpg.client.render.entity.twilight.model.ModelSamek;
import net.divinerpg.client.render.entity.twilight.model.ModelSkythernFiend;
import net.divinerpg.client.render.entity.twilight.model.ModelSorcerer;
import net.divinerpg.client.render.entity.twilight.model.ModelSoulFiend;
import net.divinerpg.client.render.entity.twilight.model.ModelSoulSpider;
import net.divinerpg.client.render.entity.twilight.model.ModelSoulStealer;
import net.divinerpg.client.render.entity.twilight.model.ModelTomo;
import net.divinerpg.client.render.entity.twilight.model.ModelTwilightArcher;
import net.divinerpg.client.render.entity.twilight.model.ModelTwilightDemon;
import net.divinerpg.client.render.entity.twilight.model.ModelTwilightGolem;
import net.divinerpg.client.render.entity.twilight.model.ModelVamacheron;
import net.divinerpg.client.render.entity.vanilla.RenderModBiped;
import net.divinerpg.client.render.item.ItemRendererEdenChest;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.entities.twilight.EntityAngryBunny;
import net.divinerpg.entities.twilight.EntityApalachiaArcher;
import net.divinerpg.entities.twilight.EntityApalachiaCadillion;
import net.divinerpg.entities.twilight.EntityApalachiaGolem;
import net.divinerpg.entities.twilight.EntityApalachiaTomo;
import net.divinerpg.entities.twilight.EntityApalachiaWarrior;
import net.divinerpg.entities.twilight.EntityBaslisk;
import net.divinerpg.entities.twilight.EntityBehemoth;
import net.divinerpg.entities.twilight.EntityBunny;
import net.divinerpg.entities.twilight.EntityDensos;
import net.divinerpg.entities.twilight.EntityEdenCadillion;
import net.divinerpg.entities.twilight.EntityEdenCori;
import net.divinerpg.entities.twilight.EntityEdenTomo;
import net.divinerpg.entities.twilight.EntityEpiphite;
import net.divinerpg.entities.twilight.EntityEternalArcher;
import net.divinerpg.entities.twilight.EntityGreenfeet;
import net.divinerpg.entities.twilight.EntityKarot;
import net.divinerpg.entities.twilight.EntityMadivel;
import net.divinerpg.entities.twilight.EntityMage;
import net.divinerpg.entities.twilight.EntityMegalith;
import net.divinerpg.entities.twilight.EntityMoonWolf;
import net.divinerpg.entities.twilight.EntityMortumCadillion;
import net.divinerpg.entities.twilight.EntityMortumDemon;
import net.divinerpg.entities.twilight.EntityMystic;
import net.divinerpg.entities.twilight.EntityParticleBullet;
import net.divinerpg.entities.twilight.EntityReyvor;
import net.divinerpg.entities.twilight.EntitySamek;
import net.divinerpg.entities.twilight.EntitySkythernArcher;
import net.divinerpg.entities.twilight.EntitySkythernCori;
import net.divinerpg.entities.twilight.EntitySkythernFiend;
import net.divinerpg.entities.twilight.EntitySkythernGolem;
import net.divinerpg.entities.twilight.EntitySorcerer;
import net.divinerpg.entities.twilight.EntitySoulFiend;
import net.divinerpg.entities.twilight.EntitySoulSpider;
import net.divinerpg.entities.twilight.EntitySoulStealer;
import net.divinerpg.entities.twilight.EntitySpellbinder;
import net.divinerpg.entities.twilight.EntitySunArcher;
import net.divinerpg.entities.twilight.EntityTwilightArcher;
import net.divinerpg.entities.twilight.EntityTwilightDemon;
import net.divinerpg.entities.twilight.EntityVamacheron;
import net.divinerpg.entities.twilight.EntityVerek;
import net.divinerpg.entities.twilight.EntityWildwoodCadillion;
import net.divinerpg.entities.twilight.EntityWildwoodGolem;
import net.divinerpg.entities.twilight.EntityWildwoodTomo;
import net.divinerpg.entities.twilight.projectile.EntityCoriShot;
import net.divinerpg.entities.twilight.projectile.EntitySoulFiendProjectile;
import net.divinerpg.entities.twilight.projectile.EntityTwilightDemonShot;
import net.divinerpg.entities.twilight.projectile.EntityTwilightMageShot;
import net.divinerpg.utils.Util;
import net.divinerpg.utils.blocks.TwilightBlocks;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

public class TwilightEntityRenderer {
    private static EntityResourceLocation x;
    private static EntityStats s;

    public static void init() {
        RenderingRegistry.registerEntityRenderingHandler(EntityCoriShot.class, (Render)new RenderSpecialProjectile(EntityResourceLocation.coriShot));
        RenderingRegistry.registerEntityRenderingHandler(EntityParticleBullet.class, (Render)new RenderProjectile());
        RenderingRegistry.registerEntityRenderingHandler(EntityTwilightDemonShot.class, (Render)new RenderSpecialProjectile(EntityResourceLocation.blank));
        RenderingRegistry.registerEntityRenderingHandler(EntitySoulFiendProjectile.class, (Render)new RenderSpecialProjectile(EntityResourceLocation.blank));
        RenderingRegistry.registerEntityRenderingHandler(EntityTwilightMageShot.class, (Render)new RenderSpecialProjectile(EntityResourceLocation.blank));
        RenderingRegistry.registerEntityRenderingHandler(EntityEdenCadillion.class, (Render)new RenderDivineMob(new ModelCadillion(), 0.0f, EntityResourceLocation.edenCadillion));
        RenderingRegistry.registerEntityRenderingHandler(EntityEdenTomo.class, (Render)new RenderDivineMob(new ModelTomo(), 0.0f, EntityResourceLocation.edenTomo));
        RenderingRegistry.registerEntityRenderingHandler(EntityBunny.class, (Render)new RenderBunny(new ModelBunny(), 0.0f));
        RenderingRegistry.registerEntityRenderingHandler(EntityAngryBunny.class, (Render)new RenderDivineMob(new ModelAngryBunny(), 0.0f, 2.0f, EntityResourceLocation.angryBunny));
        RenderingRegistry.registerEntityRenderingHandler(EntityEdenCori.class, (Render)new RenderDivineMob(new ModelCori(), 0.0f, EntityResourceLocation.edenCori));
        RenderingRegistry.registerEntityRenderingHandler(EntityMadivel.class, (Render)new RenderDivineMob(new ModelMadivel(), 0.0f, EntityResourceLocation.madivel));
        RenderingRegistry.registerEntityRenderingHandler(EntityGreenfeet.class, (Render)new RenderDivineMob(new ModelGreenfeet(), 0.0f, EntityResourceLocation.greenfeet));
        RenderingRegistry.registerEntityRenderingHandler(EntitySunArcher.class, (Render)new RenderSunArcher());
        RenderingRegistry.registerEntityRenderingHandler(EntityWildwoodGolem.class, (Render)new RenderDivineMob(new ModelTwilightGolem(), 0.0f, EntityResourceLocation.wildwoodGolem));
        RenderingRegistry.registerEntityRenderingHandler(EntityWildwoodTomo.class, (Render)new RenderDivineMob(new ModelTomo(), 0.0f, EntityResourceLocation.wildwoodTomo));
        RenderingRegistry.registerEntityRenderingHandler(EntityWildwoodCadillion.class, (Render)new RenderDivineMob(new ModelCadillion(), 0.0f, EntityResourceLocation.wildwoodCadillion));
        RenderingRegistry.registerEntityRenderingHandler(EntityEpiphite.class, (Render)new RenderDivineMob(new ModelEpiphite(), 0.0f, 1.25f, EntityResourceLocation.epiphite));
        RenderingRegistry.registerEntityRenderingHandler(EntityMage.class, (Render)new RenderDivineMob(new ModelMage(), 0.0f, EntityResourceLocation.mage));
        RenderingRegistry.registerEntityRenderingHandler(EntitySpellbinder.class, (Render)new RenderDivineMob(new ModelMystic(), 0.0f, EntityResourceLocation.spellbinder));
        RenderingRegistry.registerEntityRenderingHandler(EntityMystic.class, (Render)new RenderDivineMob(new ModelMystic(), 0.0f, EntityResourceLocation.mystic));
        RenderingRegistry.registerEntityRenderingHandler(EntitySorcerer.class, (Render)new RenderDivineMob(new ModelSorcerer(), 0.0f, EntityResourceLocation.sorcerer));
        RenderingRegistry.registerEntityRenderingHandler(EntityBehemoth.class, (Render)new RenderDivineMob(new ModelBehemoth(), 0.0f, EntityResourceLocation.behemoth));
        RenderingRegistry.registerEntityRenderingHandler(EntityVerek.class, (Render)new RenderDivineMob(new ModelSamek(), 0.0f, EntityResourceLocation.varek));
        RenderingRegistry.registerEntityRenderingHandler(EntityMoonWolf.class, (Render)new RenderDivineMob(new ModelMoonWolf(), 0.0f, EntityResourceLocation.wildwoodWolf));
        RenderingRegistry.registerEntityRenderingHandler(EntityApalachiaCadillion.class, (Render)new RenderDivineMob(new ModelCadillion(), 0.0f, EntityResourceLocation.apalachiaCadillion));
        RenderingRegistry.registerEntityRenderingHandler(EntityApalachiaTomo.class, (Render)new RenderDivineMob(new ModelTomo(), 0.0f, EntityResourceLocation.apalachiaTomo));
        RenderingRegistry.registerEntityRenderingHandler(EntityApalachiaGolem.class, (Render)new RenderDivineMob(new ModelTwilightGolem(), 0.0f, EntityResourceLocation.apalachiaGolem));
        RenderingRegistry.registerEntityRenderingHandler(EntityApalachiaWarrior.class, (Render)new RenderModBiped((ModelBase)new ModelEnchantedWarrior(), EntityResourceLocation.apalachiaWarrior));
        RenderingRegistry.registerEntityRenderingHandler(EntityApalachiaArcher.class, (Render)new RenderEnchantedArcher(new ModelEnchantedArcher(), EntityResourceLocation.apalachiaArcher));
        RenderingRegistry.registerEntityRenderingHandler(EntitySkythernArcher.class, (Render)new RenderEnchantedArcher(new ModelEnchantedArcher(), EntityResourceLocation.skythernArcher));
        RenderingRegistry.registerEntityRenderingHandler(EntityTwilightArcher.class, (Render)new RenderTwilightArcher(new ModelTwilightArcher(), EntityResourceLocation.twilightArcher));
        RenderingRegistry.registerEntityRenderingHandler(EntitySamek.class, (Render)new RenderDivineMob(new ModelSamek(), 0.0f, EntityResourceLocation.samek));
        RenderingRegistry.registerEntityRenderingHandler(EntitySkythernGolem.class, (Render)new RenderDivineMob(new ModelTwilightGolem(), 0.0f, EntityResourceLocation.skythernGolem));
        RenderingRegistry.registerEntityRenderingHandler(EntitySkythernCori.class, (Render)new RenderDivineMob(new ModelCori(), 0.0f, EntityResourceLocation.skythernCori));
        RenderingRegistry.registerEntityRenderingHandler(EntitySkythernFiend.class, (Render)new RenderDivineMob(new ModelSkythernFiend(), 0.0f, EntityResourceLocation.skythernFiend));
        RenderingRegistry.registerEntityRenderingHandler(EntityMegalith.class, (Render)new RenderDivineMob(new ModelMegalith(), 0.0f, EntityResourceLocation.megalith));
        RenderingRegistry.registerEntityRenderingHandler(EntityMortumCadillion.class, (Render)new RenderDivineMob(new ModelCadillion(), 0.0f, EntityResourceLocation.mortumCadillion));
        RenderingRegistry.registerEntityRenderingHandler(EntityMortumDemon.class, (Render)new RenderDivineMob(new ModelDenseDemon(), 0.0f, EntityResourceLocation.mortumDemon));
        RenderingRegistry.registerEntityRenderingHandler(EntityBaslisk.class, (Render)new RenderDivineMob(new ModelBasalisk(), 0.0f, EntityResourceLocation.basalisk));
        RenderingRegistry.registerEntityRenderingHandler(EntitySoulStealer.class, (Render)new RenderDivineMob(new ModelSoulStealer(), 0.0f, EntityResourceLocation.soulStealer));
        RenderingRegistry.registerEntityRenderingHandler(EntitySoulSpider.class, (Render)new RenderDivineMob(new ModelSoulSpider(), 0.0f, EntityResourceLocation.soulSpider));
        RenderingRegistry.registerEntityRenderingHandler(EntityDensos.class, (Render)new RenderDAR(new ModelDensos(), 0.0f, EntityResourceLocation.densos, 9));
        RenderingRegistry.registerEntityRenderingHandler(EntityReyvor.class, (Render)new RenderDAR(new ModelDensos(), 0.0f, EntityResourceLocation.reyvor, 10));
        RenderingRegistry.registerEntityRenderingHandler(EntityTwilightDemon.class, (Render)new RenderDivineBoss(new ModelTwilightDemon(), 0.0f, 2.0f, EntityResourceLocation.twilightDemon, 11));
        RenderingRegistry.registerEntityRenderingHandler(EntitySoulFiend.class, (Render)new RenderDivineBoss(new ModelSoulFiend(), 0.0f, 1.0f, EntityResourceLocation.soulFiend, 12));
        RenderingRegistry.registerEntityRenderingHandler(EntityVamacheron.class, (Render)new RenderDivineBoss(new ModelVamacheron(), 0.0f, 1.5f, EntityResourceLocation.vamacheron, 13));
        RenderingRegistry.registerEntityRenderingHandler(EntityKarot.class, (Render)new RenderDivineBoss(new ModelKarot(), 0.0f, 5.0f, EntityResourceLocation.karot, 14));
        RenderingRegistry.registerEntityRenderingHandler(EntityEternalArcher.class, (Render)new RenderEternalArcher(new ModelEternalArcher(), 0.0f, 2.5f, EntityResourceLocation.eternalArcher, 15));
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityEdenChest.class, (TileEntitySpecialRenderer)new RenderEdenChest());
        MinecraftForgeClient.registerItemRenderer((Item)Util.toItem(TwilightBlocks.edenChest), (IItemRenderer)new ItemRendererEdenChest());
    }
}

