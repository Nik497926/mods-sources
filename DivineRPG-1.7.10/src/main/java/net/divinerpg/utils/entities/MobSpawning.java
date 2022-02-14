/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.EntityRegistry
 *  net.minecraft.entity.EnumCreatureType
 *  net.minecraft.entity.monster.EntityBlaze
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraftforge.common.BiomeDictionary
 *  net.minecraftforge.common.BiomeDictionary$Type
 */
package net.divinerpg.utils.entities;

import cpw.mods.fml.common.registry.EntityRegistry;
import net.divinerpg.entities.arcana.EntityCaptianMerik;
import net.divinerpg.entities.arcana.EntityDatticon;
import net.divinerpg.entities.arcana.EntityLeorna;
import net.divinerpg.entities.arcana.EntityVatticus;
import net.divinerpg.entities.arcana.EntityWarGeneral;
import net.divinerpg.entities.arcana.EntityZelus;
import net.divinerpg.entities.base.EntityTrollTrader;
import net.divinerpg.entities.boil.EntityBurningLight;
import net.divinerpg.entities.boil.EntityFrightener;
import net.divinerpg.entities.boil.EntityScreamer;
import net.divinerpg.entities.euca.EntityDynaster;
import net.divinerpg.entities.euca.EntityEucaCharger;
import net.divinerpg.entities.euca.EntityGoldbot;
import net.divinerpg.entities.euca.EntityGolder;
import net.divinerpg.entities.euca.EntityGolditeMage;
import net.divinerpg.entities.euca.EntityGoldwing;
import net.divinerpg.entities.euca.EntityShimmerer;
import net.divinerpg.entities.euca.EntitySilverbot;
import net.divinerpg.entities.iceika.EntityAlicanto;
import net.divinerpg.entities.iceika.EntityFractite;
import net.divinerpg.entities.iceika.EntityFrostArcher;
import net.divinerpg.entities.iceika.EntityFrosty;
import net.divinerpg.entities.iceika.EntityGlacide;
import net.divinerpg.entities.iceika.EntityHastreus;
import net.divinerpg.entities.iceika.EntityRollum;
import net.divinerpg.entities.lelyetia.EntityExohead;
import net.divinerpg.entities.lelyetia.EntityFlye;
import net.divinerpg.entities.lelyetia.EntityGrobbler;
import net.divinerpg.entities.lelyetia.EntityLelyetianCaster;
import net.divinerpg.entities.lelyetia.EntityLelyetianWarrior;
import net.divinerpg.entities.twilight.EntityApalachiaArcher;
import net.divinerpg.entities.twilight.EntityApalachiaCadillion;
import net.divinerpg.entities.twilight.EntityApalachiaGolem;
import net.divinerpg.entities.twilight.EntityApalachiaTomo;
import net.divinerpg.entities.twilight.EntityApalachiaWarrior;
import net.divinerpg.entities.twilight.EntityBaslisk;
import net.divinerpg.entities.twilight.EntityBehemoth;
import net.divinerpg.entities.twilight.EntityBunny;
import net.divinerpg.entities.twilight.EntityEdenCadillion;
import net.divinerpg.entities.twilight.EntityEdenTomo;
import net.divinerpg.entities.twilight.EntityEpiphite;
import net.divinerpg.entities.twilight.EntityGreenfeet;
import net.divinerpg.entities.twilight.EntityMadivel;
import net.divinerpg.entities.twilight.EntityMage;
import net.divinerpg.entities.twilight.EntityMegalith;
import net.divinerpg.entities.twilight.EntityMoonWolf;
import net.divinerpg.entities.twilight.EntityMortumCadillion;
import net.divinerpg.entities.twilight.EntityMortumDemon;
import net.divinerpg.entities.twilight.EntityMystic;
import net.divinerpg.entities.twilight.EntityRuneTrader;
import net.divinerpg.entities.twilight.EntitySamek;
import net.divinerpg.entities.twilight.EntitySkythernArcher;
import net.divinerpg.entities.twilight.EntitySkythernFiend;
import net.divinerpg.entities.twilight.EntitySkythernGolem;
import net.divinerpg.entities.twilight.EntitySorcerer;
import net.divinerpg.entities.twilight.EntitySoulStealer;
import net.divinerpg.entities.twilight.EntitySpellbinder;
import net.divinerpg.entities.twilight.EntitySunArcher;
import net.divinerpg.entities.twilight.EntityTwilightArcher;
import net.divinerpg.entities.twilight.EntityVerek;
import net.divinerpg.entities.twilight.EntityWildwoodCadillion;
import net.divinerpg.entities.twilight.EntityWildwoodGolem;
import net.divinerpg.entities.twilight.EntityWildwoodTomo;
import net.divinerpg.entities.vanilla.EntityAridWarrior;
import net.divinerpg.entities.vanilla.EntityCrab;
import net.divinerpg.entities.vanilla.EntityCyclops;
import net.divinerpg.entities.vanilla.EntityDesertCrawler;
import net.divinerpg.entities.vanilla.EntityEnderSpider;
import net.divinerpg.entities.vanilla.EntityEnderTriplets;
import net.divinerpg.entities.vanilla.EntityEnderWatcher;
import net.divinerpg.entities.vanilla.EntityFrost;
import net.divinerpg.entities.vanilla.EntityGlacon;
import net.divinerpg.entities.vanilla.EntityHellPig;
import net.divinerpg.entities.vanilla.EntityHellSpider;
import net.divinerpg.entities.vanilla.EntityJungleBat;
import net.divinerpg.entities.vanilla.EntityJungleDramcryx;
import net.divinerpg.entities.vanilla.EntityJungleSpider;
import net.divinerpg.entities.vanilla.EntityKingCrab;
import net.divinerpg.entities.vanilla.EntityKobblin;
import net.divinerpg.entities.vanilla.EntityLiopleurodon;
import net.divinerpg.entities.vanilla.EntityPumpkinSpider;
import net.divinerpg.entities.vanilla.EntitySaguaroWorm;
import net.divinerpg.entities.vanilla.EntityScorcher;
import net.divinerpg.entities.vanilla.EntityShark;
import net.divinerpg.entities.vanilla.EntityWhale;
import net.divinerpg.entities.vanilla.EntityWildfire;
import net.divinerpg.entities.vethea.EntityGalroid;
import net.divinerpg.entities.vethea.EntityHelio;
import net.divinerpg.entities.vethea.EntityVermenous;
import net.divinerpg.utils.DimensionHelper;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;

public class MobSpawning {
    public static void addSpawns() {
        MobSpawning.addArcanaSpawns();
        MobSpawning.addOverworldSpawns();
        MobSpawning.addTwilightSpawns();
        MobSpawning.addEucaSpawns();
        MobSpawning.addBoilSpawns();
        MobSpawning.addIceikaSpawns();
        MobSpawning.addLelySpawns();
    }

    public static void addIceikaSpawns() {
        BiomeGenBase[] iceikaBiome = new BiomeGenBase[]{DimensionHelper.iceikaBiome};
        EntityRegistry.addSpawn(EntityAlicanto.class, (int)4, (int)1, (int)2, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])iceikaBiome);
        EntityRegistry.addSpawn(EntityFractite.class, (int)4, (int)1, (int)2, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])iceikaBiome);
        EntityRegistry.addSpawn(EntityGlacide.class, (int)4, (int)1, (int)2, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])iceikaBiome);
        EntityRegistry.addSpawn(EntityHastreus.class, (int)4, (int)1, (int)2, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])iceikaBiome);
        EntityRegistry.addSpawn(EntityFrostArcher.class, (int)4, (int)1, (int)2, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])iceikaBiome);
        EntityRegistry.addSpawn(EntityFrosty.class, (int)4, (int)1, (int)2, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])iceikaBiome);
        EntityRegistry.addSpawn(EntityRollum.class, (int)4, (int)1, (int)2, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])iceikaBiome);
        EntityRegistry.addSpawn(EntityTrollTrader.class, (int)1, (int)0, (int)1, (EnumCreatureType)EnumCreatureType.creature, (BiomeGenBase[])iceikaBiome);
    }

    public static void addLelySpawns() {
        EntityRegistry.addSpawn(EntityLelyetianWarrior.class, (int)20, (int)0, (int)1, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])new BiomeGenBase[]{DimensionHelper.LelyetiaBiome});
        EntityRegistry.addSpawn(EntityFlye.class, (int)20, (int)0, (int)1, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])new BiomeGenBase[]{DimensionHelper.LelyetiaBiome});
        EntityRegistry.addSpawn(EntityGrobbler.class, (int)6, (int)0, (int)1, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])new BiomeGenBase[]{DimensionHelper.LelyetiaBiome});
        EntityRegistry.addSpawn(EntityLelyetianCaster.class, (int)20, (int)0, (int)1, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])new BiomeGenBase[]{DimensionHelper.LelyetiaBiome});
        EntityRegistry.addSpawn(EntityLelyetianWarrior.class, (int)20, (int)0, (int)1, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])new BiomeGenBase[]{DimensionHelper.LelyetiaBiome});
        EntityRegistry.addSpawn(EntityExohead.class, (int)4, (int)1, (int)3, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])new BiomeGenBase[]{DimensionHelper.LelyetiaBiome});
        EntityRegistry.addSpawn(EntityTrollTrader.class, (int)1, (int)0, (int)1, (EnumCreatureType)EnumCreatureType.creature, (BiomeGenBase[])new BiomeGenBase[]{DimensionHelper.LelyetiaBiome});
    }

    public static void addArcanaSpawns() {
        BiomeGenBase[] arcanaBiome = new BiomeGenBase[]{DimensionHelper.arcanaBiome};
        EntityRegistry.addSpawn(EntityLeorna.class, (int)1, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.creature, (BiomeGenBase[])arcanaBiome);
        EntityRegistry.addSpawn(EntityDatticon.class, (int)1, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.creature, (BiomeGenBase[])arcanaBiome);
        EntityRegistry.addSpawn(EntityZelus.class, (int)1, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.creature, (BiomeGenBase[])arcanaBiome);
        EntityRegistry.addSpawn(EntityVatticus.class, (int)4, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.creature, (BiomeGenBase[])arcanaBiome);
        EntityRegistry.addSpawn(EntityCaptianMerik.class, (int)4, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.creature, (BiomeGenBase[])arcanaBiome);
        EntityRegistry.addSpawn(EntityWarGeneral.class, (int)4, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.creature, (BiomeGenBase[])arcanaBiome);
        EntityRegistry.addSpawn(EntityTrollTrader.class, (int)1, (int)0, (int)1, (EnumCreatureType)EnumCreatureType.creature, (BiomeGenBase[])arcanaBiome);
    }

    public static void addTwilightSpawns() {
        BiomeGenBase[] edenBiome = new BiomeGenBase[]{DimensionHelper.edenBiome};
        BiomeGenBase[] wildwoodBiome = new BiomeGenBase[]{DimensionHelper.wildwoodBiome};
        BiomeGenBase[] apalachiaBiome = new BiomeGenBase[]{DimensionHelper.apalachiaBiome};
        BiomeGenBase[] skythernBiome = new BiomeGenBase[]{DimensionHelper.skythernBiome};
        BiomeGenBase[] mortumBiome = new BiomeGenBase[]{DimensionHelper.mortumBiome};
        EntityRegistry.addSpawn(EntityTrollTrader.class, (int)1, (int)0, (int)1, (EnumCreatureType)EnumCreatureType.creature, (BiomeGenBase[])new BiomeGenBase[]{DimensionHelper.mortumBiome});
        EntityRegistry.addSpawn(EntityEdenTomo.class, (int)4, (int)1, (int)2, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])edenBiome);
        EntityRegistry.addSpawn(EntityEdenCadillion.class, (int)4, (int)1, (int)3, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])edenBiome);
        EntityRegistry.addSpawn(EntityBunny.class, (int)4, (int)1, (int)3, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])edenBiome);
        EntityRegistry.addSpawn(EntityEdenTomo.class, (int)4, (int)1, (int)3, (EnumCreatureType)EnumCreatureType.creature, (BiomeGenBase[])edenBiome);
        EntityRegistry.addSpawn(EntityEdenCadillion.class, (int)4, (int)1, (int)3, (EnumCreatureType)EnumCreatureType.creature, (BiomeGenBase[])edenBiome);
        EntityRegistry.addSpawn(EntityBunny.class, (int)4, (int)1, (int)3, (EnumCreatureType)EnumCreatureType.creature, (BiomeGenBase[])edenBiome);
        EntityRegistry.addSpawn(EntityGreenfeet.class, (int)4, (int)1, (int)3, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])edenBiome);
        EntityRegistry.addSpawn(EntityMadivel.class, (int)4, (int)1, (int)3, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])edenBiome);
        EntityRegistry.addSpawn(EntitySunArcher.class, (int)4, (int)1, (int)3, (EnumCreatureType)EnumCreatureType.creature, (BiomeGenBase[])edenBiome);
        EntityRegistry.addSpawn(EntitySunArcher.class, (int)4, (int)1, (int)3, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])edenBiome);
        EntityRegistry.addSpawn(EntityHelio.class, (int)20, (int)1, (int)3, (EnumCreatureType)EnumCreatureType.creature, (BiomeGenBase[])edenBiome);
        EntityRegistry.addSpawn(EntityHelio.class, (int)20, (int)1, (int)3, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])edenBiome);
        EntityRegistry.addSpawn(EntityTrollTrader.class, (int)1, (int)0, (int)1, (EnumCreatureType)EnumCreatureType.creature, (BiomeGenBase[])edenBiome);
        EntityRegistry.addSpawn(EntityWildwoodCadillion.class, (int)4, (int)1, (int)3, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])wildwoodBiome);
        EntityRegistry.addSpawn(EntityWildwoodTomo.class, (int)4, (int)1, (int)3, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])wildwoodBiome);
        EntityRegistry.addSpawn(EntityWildwoodCadillion.class, (int)4, (int)1, (int)3, (EnumCreatureType)EnumCreatureType.creature, (BiomeGenBase[])wildwoodBiome);
        EntityRegistry.addSpawn(EntityWildwoodTomo.class, (int)4, (int)1, (int)3, (EnumCreatureType)EnumCreatureType.creature, (BiomeGenBase[])wildwoodBiome);
        EntityRegistry.addSpawn(EntityEpiphite.class, (int)4, (int)1, (int)2, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])wildwoodBiome);
        EntityRegistry.addSpawn(EntityEpiphite.class, (int)4, (int)1, (int)2, (EnumCreatureType)EnumCreatureType.creature, (BiomeGenBase[])wildwoodBiome);
        EntityRegistry.addSpawn(EntityBehemoth.class, (int)4, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])wildwoodBiome);
        EntityRegistry.addSpawn(EntityBehemoth.class, (int)4, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.creature, (BiomeGenBase[])wildwoodBiome);
        EntityRegistry.addSpawn(EntityWildwoodGolem.class, (int)4, (int)1, (int)3, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])wildwoodBiome);
        EntityRegistry.addSpawn(EntityVerek.class, (int)4, (int)1, (int)3, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])wildwoodBiome);
        EntityRegistry.addSpawn(EntityMage.class, (int)4, (int)1, (int)3, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])wildwoodBiome);
        EntityRegistry.addSpawn(EntityMoonWolf.class, (int)4, (int)1, (int)3, (EnumCreatureType)EnumCreatureType.creature, (BiomeGenBase[])wildwoodBiome);
        EntityRegistry.addSpawn(EntityHelio.class, (int)20, (int)1, (int)3, (EnumCreatureType)EnumCreatureType.creature, (BiomeGenBase[])wildwoodBiome);
        EntityRegistry.addSpawn(EntityHelio.class, (int)20, (int)1, (int)3, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])wildwoodBiome);
        EntityRegistry.addSpawn(EntityTrollTrader.class, (int)1, (int)0, (int)1, (EnumCreatureType)EnumCreatureType.creature, (BiomeGenBase[])wildwoodBiome);
        EntityRegistry.addSpawn(EntityApalachiaCadillion.class, (int)40, (int)2, (int)4, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])apalachiaBiome);
        EntityRegistry.addSpawn(EntityApalachiaGolem.class, (int)40, (int)2, (int)4, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])apalachiaBiome);
        EntityRegistry.addSpawn(EntityApalachiaTomo.class, (int)40, (int)2, (int)4, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])apalachiaBiome);
        EntityRegistry.addSpawn(EntityApalachiaWarrior.class, (int)40, (int)2, (int)4, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])apalachiaBiome);
        EntityRegistry.addSpawn(EntityApalachiaArcher.class, (int)40, (int)2, (int)4, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])apalachiaBiome);
        EntityRegistry.addSpawn(EntitySpellbinder.class, (int)40, (int)2, (int)4, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])apalachiaBiome);
        EntityRegistry.addSpawn(EntityTrollTrader.class, (int)1, (int)0, (int)1, (EnumCreatureType)EnumCreatureType.creature, (BiomeGenBase[])apalachiaBiome);
        EntityRegistry.addSpawn(EntitySkythernFiend.class, (int)10, (int)2, (int)3, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])skythernBiome);
        EntityRegistry.addSpawn(EntitySkythernGolem.class, (int)10, (int)2, (int)3, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])skythernBiome);
        EntityRegistry.addSpawn(EntitySkythernArcher.class, (int)10, (int)2, (int)3, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])skythernBiome);
        EntityRegistry.addSpawn(EntitySamek.class, (int)10, (int)4, (int)6, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])skythernBiome);
        EntityRegistry.addSpawn(EntityMystic.class, (int)6, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])skythernBiome);
        EntityRegistry.addSpawn(EntityMegalith.class, (int)6, (int)1, (int)3, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])skythernBiome);
        EntityRegistry.addSpawn(EntityHelio.class, (int)20, (int)1, (int)3, (EnumCreatureType)EnumCreatureType.creature, (BiomeGenBase[])skythernBiome);
        EntityRegistry.addSpawn(EntityHelio.class, (int)20, (int)1, (int)3, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])skythernBiome);
        EntityRegistry.addSpawn(EntityTrollTrader.class, (int)1, (int)0, (int)1, (EnumCreatureType)EnumCreatureType.creature, (BiomeGenBase[])skythernBiome);
        EntityRegistry.addSpawn(EntityRuneTrader.class, (int)10, (int)1, (int)2, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])mortumBiome);
        EntityRegistry.addSpawn(EntityVermenous.class, (int)20, (int)1, (int)3, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])mortumBiome);
        EntityRegistry.addSpawn(EntityMortumDemon.class, (int)4, (int)1, (int)3, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])mortumBiome);
        EntityRegistry.addSpawn(EntityBaslisk.class, (int)4, (int)1, (int)3, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])mortumBiome);
        EntityRegistry.addSpawn(EntitySoulStealer.class, (int)4, (int)1, (int)3, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])mortumBiome);
        EntityRegistry.addSpawn(EntityMortumCadillion.class, (int)4, (int)1, (int)3, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])mortumBiome);
        EntityRegistry.addSpawn(EntityTwilightArcher.class, (int)4, (int)1, (int)3, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])mortumBiome);
        EntityRegistry.addSpawn(EntitySorcerer.class, (int)4, (int)1, (int)3, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])mortumBiome);
        EntityRegistry.addSpawn(EntityTrollTrader.class, (int)1, (int)0, (int)1, (EnumCreatureType)EnumCreatureType.creature, (BiomeGenBase[])mortumBiome);
    }

    public static void addEucaSpawns() {
        BiomeGenBase[] eucaBiome = new BiomeGenBase[]{DimensionHelper.eucaBiome};
        EntityRegistry.addSpawn(EntityDynaster.class, (int)7, (int)1, (int)3, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])eucaBiome);
        EntityRegistry.addSpawn(EntityEucaCharger.class, (int)7, (int)1, (int)3, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])eucaBiome);
        EntityRegistry.addSpawn(EntityGoldbot.class, (int)7, (int)1, (int)3, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])eucaBiome);
        EntityRegistry.addSpawn(EntityGolder.class, (int)7, (int)1, (int)3, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])eucaBiome);
        EntityRegistry.addSpawn(EntityGoldwing.class, (int)7, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.creature, (BiomeGenBase[])eucaBiome);
        EntityRegistry.addSpawn(EntityShimmerer.class, (int)7, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])eucaBiome);
        EntityRegistry.addSpawn(EntitySilverbot.class, (int)7, (int)2, (int)3, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])eucaBiome);
        EntityRegistry.addSpawn(EntityGolditeMage.class, (int)7, (int)1, (int)2, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])eucaBiome);
        EntityRegistry.addSpawn(EntityTrollTrader.class, (int)1, (int)0, (int)1, (EnumCreatureType)EnumCreatureType.creature, (BiomeGenBase[])eucaBiome);
    }

    private static void addBoilSpawns() {
        BiomeGenBase[] boilingBiome = new BiomeGenBase[]{DimensionHelper.boilingBiome};
        EntityRegistry.addSpawn(EntityBurningLight.class, (int)3, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])boilingBiome);
        EntityRegistry.addSpawn(EntityFrightener.class, (int)3, (int)1, (int)3, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])boilingBiome);
        EntityRegistry.addSpawn(EntityScreamer.class, (int)1, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])boilingBiome);
        EntityRegistry.addSpawn(EntityGalroid.class, (int)3, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])boilingBiome);
        EntityRegistry.addSpawn(EntityTrollTrader.class, (int)1, (int)0, (int)1, (EnumCreatureType)EnumCreatureType.creature, (BiomeGenBase[])boilingBiome);
    }

    public static void addOverworldSpawns() {
        for (int i = 0; i < BiomeGenBase.getBiomeGenArray().length; ++i) {
            BiomeGenBase biome = BiomeGenBase.getBiomeGenArray()[i];
            if (biome == null) continue;
            if (BiomeDictionary.isBiomeOfType((BiomeGenBase)biome, (BiomeDictionary.Type)BiomeDictionary.Type.END)) {
                EntityRegistry.addSpawn(EntityEnderSpider.class, (int)5, (int)1, (int)4, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])new BiomeGenBase[]{biome});
                EntityRegistry.addSpawn(EntityEnderTriplets.class, (int)5, (int)1, (int)4, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])new BiomeGenBase[]{biome});
                EntityRegistry.addSpawn(EntityEnderWatcher.class, (int)5, (int)4, (int)4, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])new BiomeGenBase[]{biome});
                EntityRegistry.addSpawn(EntityTrollTrader.class, (int)1, (int)0, (int)1, (EnumCreatureType)EnumCreatureType.creature, (BiomeGenBase[])new BiomeGenBase[]{biome});
                continue;
            }
            if (BiomeDictionary.isBiomeOfType((BiomeGenBase)biome, (BiomeDictionary.Type)BiomeDictionary.Type.NETHER)) {
                EntityRegistry.addSpawn(EntityHellPig.class, (int)9, (int)5, (int)1, (EnumCreatureType)EnumCreatureType.creature, (BiomeGenBase[])new BiomeGenBase[]{biome});
                EntityRegistry.addSpawn(EntityHellSpider.class, (int)9, (int)2, (int)4, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])new BiomeGenBase[]{biome});
                EntityRegistry.addSpawn(EntityScorcher.class, (int)40, (int)2, (int)4, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])new BiomeGenBase[]{biome});
                EntityRegistry.addSpawn(EntityBlaze.class, (int)20, (int)2, (int)4, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])new BiomeGenBase[]{biome});
                EntityRegistry.addSpawn(EntityWildfire.class, (int)9, (int)1, (int)4, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])new BiomeGenBase[]{biome});
                EntityRegistry.addSpawn(EntityTrollTrader.class, (int)1, (int)0, (int)1, (EnumCreatureType)EnumCreatureType.creature, (BiomeGenBase[])new BiomeGenBase[]{biome});
                continue;
            }
            if (BiomeDictionary.isBiomeOfType((BiomeGenBase)biome, (BiomeDictionary.Type)BiomeDictionary.Type.SNOWY)) {
                EntityRegistry.addSpawn(EntityGlacon.class, (int)3, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])new BiomeGenBase[]{biome});
                EntityRegistry.addSpawn(EntityGlacon.class, (int)3, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.creature, (BiomeGenBase[])new BiomeGenBase[]{biome});
                EntityRegistry.addSpawn(EntityFrost.class, (int)3, (int)1, (int)2, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])new BiomeGenBase[]{biome});
                EntityRegistry.addSpawn(EntityTrollTrader.class, (int)1, (int)0, (int)1, (EnumCreatureType)EnumCreatureType.creature, (BiomeGenBase[])new BiomeGenBase[]{biome});
            }
            if (BiomeDictionary.isBiomeOfType((BiomeGenBase)biome, (BiomeDictionary.Type)BiomeDictionary.Type.SANDY)) {
                EntityRegistry.addSpawn(EntityDesertCrawler.class, (int)3, (int)1, (int)2, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])new BiomeGenBase[]{biome});
                EntityRegistry.addSpawn(EntityAridWarrior.class, (int)3, (int)1, (int)2, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])new BiomeGenBase[]{biome});
                EntityRegistry.addSpawn(EntitySaguaroWorm.class, (int)3, (int)1, (int)2, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])new BiomeGenBase[]{biome});
                EntityRegistry.addSpawn(EntityTrollTrader.class, (int)1, (int)0, (int)1, (EnumCreatureType)EnumCreatureType.creature, (BiomeGenBase[])new BiomeGenBase[]{biome});
            }
            if (biome == BiomeGenBase.ocean || biome == BiomeGenBase.deepOcean) {
                EntityRegistry.addSpawn(EntityWhale.class, (int)1, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.waterCreature, (BiomeGenBase[])new BiomeGenBase[]{biome});
                EntityRegistry.addSpawn(EntityShark.class, (int)1, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.waterCreature, (BiomeGenBase[])new BiomeGenBase[]{biome});
                EntityRegistry.addSpawn(EntityLiopleurodon.class, (int)1, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.waterCreature, (BiomeGenBase[])new BiomeGenBase[]{biome});
            }
            if (BiomeDictionary.isBiomeOfType((BiomeGenBase)biome, (BiomeDictionary.Type)BiomeDictionary.Type.BEACH)) {
                EntityRegistry.addSpawn(EntityCrab.class, (int)1, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])new BiomeGenBase[]{biome});
                EntityRegistry.addSpawn(EntityKingCrab.class, (int)1, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])new BiomeGenBase[]{biome});
                EntityRegistry.addSpawn(EntityTrollTrader.class, (int)1, (int)0, (int)1, (EnumCreatureType)EnumCreatureType.creature, (BiomeGenBase[])new BiomeGenBase[]{biome});
            }
            if (BiomeDictionary.isBiomeOfType((BiomeGenBase)biome, (BiomeDictionary.Type)BiomeDictionary.Type.JUNGLE)) {
                EntityRegistry.addSpawn(EntityJungleBat.class, (int)1, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])new BiomeGenBase[]{biome});
                EntityRegistry.addSpawn(EntityJungleDramcryx.class, (int)1, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])new BiomeGenBase[]{biome});
                EntityRegistry.addSpawn(EntityJungleSpider.class, (int)1, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])new BiomeGenBase[]{biome});
                EntityRegistry.addSpawn(EntityTrollTrader.class, (int)1, (int)0, (int)1, (EnumCreatureType)EnumCreatureType.creature, (BiomeGenBase[])new BiomeGenBase[]{biome});
            }
            if (BiomeDictionary.isBiomeOfType((BiomeGenBase)biome, (BiomeDictionary.Type)BiomeDictionary.Type.PLAINS)) {
                EntityRegistry.addSpawn(EntityKobblin.class, (int)1, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])new BiomeGenBase[]{biome});
                EntityRegistry.addSpawn(EntityTrollTrader.class, (int)1, (int)0, (int)1, (EnumCreatureType)EnumCreatureType.creature, (BiomeGenBase[])new BiomeGenBase[]{biome});
            }
            if (BiomeDictionary.isBiomeOfType((BiomeGenBase)biome, (BiomeDictionary.Type)BiomeDictionary.Type.PLAINS) || BiomeDictionary.isBiomeOfType((BiomeGenBase)biome, (BiomeDictionary.Type)BiomeDictionary.Type.MOUNTAIN)) {
                EntityRegistry.addSpawn(EntityCyclops.class, (int)1, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])new BiomeGenBase[]{biome});
                EntityRegistry.addSpawn(EntityTrollTrader.class, (int)1, (int)0, (int)1, (EnumCreatureType)EnumCreatureType.creature, (BiomeGenBase[])new BiomeGenBase[]{biome});
            }
            if (!BiomeDictionary.isBiomeOfType((BiomeGenBase)biome, (BiomeDictionary.Type)BiomeDictionary.Type.FOREST)) continue;
            EntityRegistry.addSpawn(EntityPumpkinSpider.class, (int)1, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])new BiomeGenBase[]{biome});
            EntityRegistry.addSpawn(EntityTrollTrader.class, (int)1, (int)0, (int)1, (EnumCreatureType)EnumCreatureType.creature, (BiomeGenBase[])new BiomeGenBase[]{biome});
        }
    }
}

