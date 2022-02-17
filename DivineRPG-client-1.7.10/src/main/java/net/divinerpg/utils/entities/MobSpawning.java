/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.ObfuscationReflectionHelper
 *  cpw.mods.fml.common.registry.EntityRegistry
 *  net.minecraft.entity.EnumCreatureType
 *  net.minecraft.entity.monster.EntityZombie
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.biome.BiomeGenBase$SpawnListEntry
 *  net.minecraftforge.common.BiomeDictionary
 *  net.minecraftforge.common.BiomeDictionary$Type
 */
package net.divinerpg.utils.entities;

import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.registry.EntityRegistry;
import java.util.List;
import net.divinerpg.entities.arcana.EntityCaptianMerik;
import net.divinerpg.entities.arcana.EntityDatticon;
import net.divinerpg.entities.arcana.EntityLeorna;
import net.divinerpg.entities.arcana.EntityVatticus;
import net.divinerpg.entities.arcana.EntityWarGeneral;
import net.divinerpg.entities.arcana.EntityZelus;
import net.divinerpg.entities.iceika.EntityAlicanto;
import net.divinerpg.entities.iceika.EntityFractite;
import net.divinerpg.entities.iceika.EntityFrostArcher;
import net.divinerpg.entities.iceika.EntityFrosty;
import net.divinerpg.entities.iceika.EntityGlacide;
import net.divinerpg.entities.iceika.EntityHastreus;
import net.divinerpg.entities.iceika.EntityRollum;
import net.divinerpg.entities.twilight.EntityApalachiaArcher;
import net.divinerpg.entities.twilight.EntityApalachiaCadillion;
import net.divinerpg.entities.twilight.EntityApalachiaGolem;
import net.divinerpg.entities.twilight.EntityApalachiaTomo;
import net.divinerpg.entities.twilight.EntityApalachiaWarrior;
import net.divinerpg.entities.twilight.EntityBaslisk;
import net.divinerpg.entities.twilight.EntityBehemoth;
import net.divinerpg.entities.twilight.EntityBunny;
import net.divinerpg.entities.twilight.EntityEdenCadillion;
import net.divinerpg.entities.twilight.EntityEdenCori;
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
import net.divinerpg.entities.twilight.EntitySamek;
import net.divinerpg.entities.twilight.EntitySkythernArcher;
import net.divinerpg.entities.twilight.EntitySkythernCori;
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
import net.divinerpg.entities.vanilla.EntityCaveCrawler;
import net.divinerpg.entities.vanilla.EntityCaveclops;
import net.divinerpg.entities.vanilla.EntityCrab;
import net.divinerpg.entities.vanilla.EntityCyclops;
import net.divinerpg.entities.vanilla.EntityDesertCrawler;
import net.divinerpg.entities.vanilla.EntityEnderSpider;
import net.divinerpg.entities.vanilla.EntityEnderTriplets;
import net.divinerpg.entities.vanilla.EntityEnderWatcher;
import net.divinerpg.entities.vanilla.EntityEnthralledDramcryx;
import net.divinerpg.entities.vanilla.EntityFrost;
import net.divinerpg.entities.vanilla.EntityGlacon;
import net.divinerpg.entities.vanilla.EntityHellPig;
import net.divinerpg.entities.vanilla.EntityHellSpider;
import net.divinerpg.entities.vanilla.EntityJackOMan;
import net.divinerpg.entities.vanilla.EntityJungleBat;
import net.divinerpg.entities.vanilla.EntityJungleDramcryx;
import net.divinerpg.entities.vanilla.EntityJungleSpider;
import net.divinerpg.entities.vanilla.EntityKingCrab;
import net.divinerpg.entities.vanilla.EntityKobblin;
import net.divinerpg.entities.vanilla.EntityLiopleurodon;
import net.divinerpg.entities.vanilla.EntityMiner;
import net.divinerpg.entities.vanilla.EntityPumpkinSpider;
import net.divinerpg.entities.vanilla.EntityRainbour;
import net.divinerpg.entities.vanilla.EntityRotatick;
import net.divinerpg.entities.vanilla.EntitySaguaroWorm;
import net.divinerpg.entities.vanilla.EntityScorcher;
import net.divinerpg.entities.vanilla.EntityShark;
import net.divinerpg.entities.vanilla.EntityTheEye;
import net.divinerpg.entities.vanilla.EntityTheGrue;
import net.divinerpg.entities.vanilla.EntityWhale;
import net.divinerpg.entities.vanilla.EntityWildfire;
import net.divinerpg.entities.vethea.EntityAcidHag;
import net.divinerpg.entities.vethea.EntityBiphron;
import net.divinerpg.entities.vethea.EntityBohemite;
import net.divinerpg.entities.vethea.EntityCymesoid;
import net.divinerpg.entities.vethea.EntityDissiment;
import net.divinerpg.entities.vethea.EntityDreamwrecker;
import net.divinerpg.entities.vethea.EntityDuo;
import net.divinerpg.entities.vethea.EntityGalroid;
import net.divinerpg.entities.vethea.EntityGorgosion;
import net.divinerpg.entities.vethea.EntityHelio;
import net.divinerpg.entities.vethea.EntityHoverStinger;
import net.divinerpg.entities.vethea.EntityKazrotic;
import net.divinerpg.entities.vethea.EntityLheiva;
import net.divinerpg.entities.vethea.EntityLorga;
import net.divinerpg.entities.vethea.EntityLorgaFlight;
import net.divinerpg.entities.vethea.EntityMandragora;
import net.divinerpg.entities.vethea.EntityMysteriousManLayer1;
import net.divinerpg.entities.vethea.EntityMysteriousManLayer2;
import net.divinerpg.entities.vethea.EntityMysteriousManLayer3;
import net.divinerpg.entities.vethea.EntityShadahier;
import net.divinerpg.entities.vethea.EntityTocaxin;
import net.divinerpg.entities.vethea.EntityTwins;
import net.divinerpg.entities.vethea.EntityVermenous;
import net.divinerpg.entities.vethea.EntityVhraak;
import net.divinerpg.entities.vethea.EntityZone;
import net.divinerpg.entities.vethea.EntityZoragon;
import net.divinerpg.utils.DimensionHelper;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;

public class MobSpawning {
    public static void addSpawns() {
        MobSpawning.addArcanaSpawns();
        MobSpawning.addOverworldSpawns();
        MobSpawning.addTwilightSpawns();
        MobSpawning.addVetheaSpawns();
        MobSpawning.addIceikaSpawns();
    }

    public static void addIceikaSpawns() {
        BiomeGenBase[] iceikaBiome = new BiomeGenBase[]{DimensionHelper.iceikaBiome};
        EntityRegistry.addSpawn(EntityAlicanto.class, (int)2, (int)1, (int)10, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])iceikaBiome);
        EntityRegistry.addSpawn(EntityFractite.class, (int)2, (int)1, (int)10, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])iceikaBiome);
        EntityRegistry.addSpawn(EntityGlacide.class, (int)2, (int)1, (int)10, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])iceikaBiome);
        EntityRegistry.addSpawn(EntityHastreus.class, (int)2, (int)1, (int)10, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])iceikaBiome);
        EntityRegistry.addSpawn(EntityFrostArcher.class, (int)2, (int)1, (int)10, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])iceikaBiome);
        EntityRegistry.addSpawn(EntityFrosty.class, (int)2, (int)1, (int)10, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])iceikaBiome);
        EntityRegistry.addSpawn(EntityRollum.class, (int)2, (int)1, (int)10, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])iceikaBiome);
    }

    public static void addArcanaSpawns() {
        BiomeGenBase[] arcanaBiome = new BiomeGenBase[]{DimensionHelper.arcanaBiome};
        EntityRegistry.addSpawn(EntityLeorna.class, (int)1, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.creature, (BiomeGenBase[])arcanaBiome);
        EntityRegistry.addSpawn(EntityDatticon.class, (int)1, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.creature, (BiomeGenBase[])arcanaBiome);
        EntityRegistry.addSpawn(EntityZelus.class, (int)1, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.creature, (BiomeGenBase[])arcanaBiome);
        EntityRegistry.addSpawn(EntityVatticus.class, (int)1, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.creature, (BiomeGenBase[])arcanaBiome);
        EntityRegistry.addSpawn(EntityCaptianMerik.class, (int)1, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.creature, (BiomeGenBase[])arcanaBiome);
        EntityRegistry.addSpawn(EntityWarGeneral.class, (int)1, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.creature, (BiomeGenBase[])arcanaBiome);
    }

    public static void addTwilightSpawns() {
        BiomeGenBase[] edenBiome = new BiomeGenBase[]{DimensionHelper.edenBiome};
        BiomeGenBase[] wildwoodBiome = new BiomeGenBase[]{DimensionHelper.wildwoodBiome};
        BiomeGenBase[] apalachiaBiome = new BiomeGenBase[]{DimensionHelper.apalachiaBiome};
        BiomeGenBase[] skythernBiome = new BiomeGenBase[]{DimensionHelper.skythernBiome};
        BiomeGenBase[] mortumBiome = new BiomeGenBase[]{DimensionHelper.mortumBiome};
        EntityRegistry.addSpawn(EntityEdenTomo.class, (int)20, (int)4, (int)4, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])edenBiome);
        EntityRegistry.addSpawn(EntityEdenCadillion.class, (int)20, (int)4, (int)4, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])edenBiome);
        EntityRegistry.addSpawn(EntityBunny.class, (int)20, (int)4, (int)4, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])edenBiome);
        EntityRegistry.addSpawn(EntityEdenTomo.class, (int)20, (int)4, (int)4, (EnumCreatureType)EnumCreatureType.creature, (BiomeGenBase[])edenBiome);
        EntityRegistry.addSpawn(EntityEdenCadillion.class, (int)20, (int)4, (int)4, (EnumCreatureType)EnumCreatureType.creature, (BiomeGenBase[])edenBiome);
        EntityRegistry.addSpawn(EntityBunny.class, (int)20, (int)4, (int)4, (EnumCreatureType)EnumCreatureType.creature, (BiomeGenBase[])edenBiome);
        EntityRegistry.addSpawn(EntityEdenCori.class, (int)1, (int)4, (int)4, (EnumCreatureType)EnumCreatureType.creature, (BiomeGenBase[])edenBiome);
        EntityRegistry.addSpawn(EntityEdenCori.class, (int)1, (int)4, (int)4, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])edenBiome);
        EntityRegistry.addSpawn(EntityGreenfeet.class, (int)20, (int)4, (int)4, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])edenBiome);
        EntityRegistry.addSpawn(EntityMadivel.class, (int)20, (int)4, (int)4, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])edenBiome);
        EntityRegistry.addSpawn(EntitySunArcher.class, (int)6, (int)4, (int)4, (EnumCreatureType)EnumCreatureType.creature, (BiomeGenBase[])edenBiome);
        EntityRegistry.addSpawn(EntitySunArcher.class, (int)6, (int)4, (int)4, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])edenBiome);
        EntityRegistry.addSpawn(EntityWildwoodCadillion.class, (int)4, (int)4, (int)4, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])wildwoodBiome);
        EntityRegistry.addSpawn(EntityWildwoodTomo.class, (int)4, (int)4, (int)4, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])wildwoodBiome);
        EntityRegistry.addSpawn(EntityWildwoodCadillion.class, (int)4, (int)4, (int)4, (EnumCreatureType)EnumCreatureType.creature, (BiomeGenBase[])wildwoodBiome);
        EntityRegistry.addSpawn(EntityWildwoodTomo.class, (int)4, (int)4, (int)4, (EnumCreatureType)EnumCreatureType.creature, (BiomeGenBase[])wildwoodBiome);
        EntityRegistry.addSpawn(EntityEpiphite.class, (int)1, (int)2, (int)2, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])wildwoodBiome);
        EntityRegistry.addSpawn(EntityEpiphite.class, (int)1, (int)2, (int)2, (EnumCreatureType)EnumCreatureType.creature, (BiomeGenBase[])wildwoodBiome);
        EntityRegistry.addSpawn(EntityBehemoth.class, (int)1, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])wildwoodBiome);
        EntityRegistry.addSpawn(EntityBehemoth.class, (int)1, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.creature, (BiomeGenBase[])wildwoodBiome);
        EntityRegistry.addSpawn(EntityWildwoodGolem.class, (int)3, (int)4, (int)4, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])wildwoodBiome);
        EntityRegistry.addSpawn(EntityVerek.class, (int)4, (int)4, (int)4, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])wildwoodBiome);
        EntityRegistry.addSpawn(EntityMage.class, (int)2, (int)4, (int)4, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])wildwoodBiome);
        EntityRegistry.addSpawn(EntityMoonWolf.class, (int)4, (int)4, (int)4, (EnumCreatureType)EnumCreatureType.creature, (BiomeGenBase[])wildwoodBiome);
        EntityRegistry.addSpawn(EntityApalachiaCadillion.class, (int)2, (int)4, (int)4, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])apalachiaBiome);
        EntityRegistry.addSpawn(EntityApalachiaGolem.class, (int)2, (int)4, (int)4, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])apalachiaBiome);
        EntityRegistry.addSpawn(EntityApalachiaTomo.class, (int)2, (int)4, (int)4, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])apalachiaBiome);
        EntityRegistry.addSpawn(EntityApalachiaWarrior.class, (int)2, (int)4, (int)4, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])apalachiaBiome);
        EntityRegistry.addSpawn(EntityApalachiaArcher.class, (int)2, (int)4, (int)4, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])apalachiaBiome);
        EntityRegistry.addSpawn(EntitySpellbinder.class, (int)1, (int)4, (int)4, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])apalachiaBiome);
        EntityRegistry.addSpawn(EntitySkythernFiend.class, (int)4, (int)4, (int)4, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])skythernBiome);
        EntityRegistry.addSpawn(EntitySkythernGolem.class, (int)4, (int)4, (int)4, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])skythernBiome);
        EntityRegistry.addSpawn(EntitySkythernArcher.class, (int)4, (int)4, (int)4, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])skythernBiome);
        EntityRegistry.addSpawn(EntitySamek.class, (int)4, (int)4, (int)4, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])skythernBiome);
        EntityRegistry.addSpawn(EntitySkythernCori.class, (int)1, (int)4, (int)4, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])skythernBiome);
        EntityRegistry.addSpawn(EntityMystic.class, (int)2, (int)4, (int)4, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])skythernBiome);
        EntityRegistry.addSpawn(EntityMegalith.class, (int)1, (int)4, (int)4, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])skythernBiome);
        EntityRegistry.addSpawn(EntityMortumDemon.class, (int)2, (int)4, (int)4, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])mortumBiome);
        EntityRegistry.addSpawn(EntityBaslisk.class, (int)2, (int)4, (int)4, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])mortumBiome);
        EntityRegistry.addSpawn(EntitySoulStealer.class, (int)2, (int)4, (int)4, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])mortumBiome);
        EntityRegistry.addSpawn(EntityMortumCadillion.class, (int)2, (int)4, (int)4, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])mortumBiome);
        EntityRegistry.addSpawn(EntityTwilightArcher.class, (int)2, (int)4, (int)4, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])mortumBiome);
        EntityRegistry.addSpawn(EntitySorcerer.class, (int)1, (int)4, (int)4, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])mortumBiome);
    }

    public static void addOverworldSpawns() {
        for (int i = 0; i < BiomeGenBase.getBiomeGenArray().length; ++i) {
            BiomeGenBase biome = BiomeGenBase.getBiomeGenArray()[i];
            if (biome == null) continue;
            if (BiomeDictionary.isBiomeOfType((BiomeGenBase)biome, (BiomeDictionary.Type)BiomeDictionary.Type.END)) {
                EntityRegistry.addSpawn(EntityEnderSpider.class, (int)2, (int)1, (int)4, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])new BiomeGenBase[]{biome});
                EntityRegistry.addSpawn(EntityEnderTriplets.class, (int)1, (int)1, (int)4, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])new BiomeGenBase[]{biome});
                EntityRegistry.addSpawn(EntityEnderWatcher.class, (int)10, (int)4, (int)4, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])new BiomeGenBase[]{biome});
                continue;
            }
            if (BiomeDictionary.isBiomeOfType((BiomeGenBase)biome, (BiomeDictionary.Type)BiomeDictionary.Type.NETHER)) {
                EntityRegistry.addSpawn(EntityHellPig.class, (int)25, (int)5, (int)50, (EnumCreatureType)EnumCreatureType.creature, (BiomeGenBase[])new BiomeGenBase[]{biome});
                EntityRegistry.addSpawn(EntityHellSpider.class, (int)50, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])new BiomeGenBase[]{biome});
                EntityRegistry.addSpawn(EntityScorcher.class, (int)7, (int)4, (int)4, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])new BiomeGenBase[]{biome});
                EntityRegistry.addSpawn(EntityWildfire.class, (int)50, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])new BiomeGenBase[]{biome});
                continue;
            }
            if (!MobSpawning.overworldBiome(biome)) continue;
            if (BiomeDictionary.isBiomeOfType((BiomeGenBase)biome, (BiomeDictionary.Type)BiomeDictionary.Type.SNOWY)) {
                EntityRegistry.addSpawn(EntityGlacon.class, (int)30, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])new BiomeGenBase[]{biome});
                EntityRegistry.addSpawn(EntityGlacon.class, (int)30, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.creature, (BiomeGenBase[])new BiomeGenBase[]{biome});
                EntityRegistry.addSpawn(EntityFrost.class, (int)50, (int)1, (int)4, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])new BiomeGenBase[]{biome});
            }
            if (BiomeDictionary.isBiomeOfType((BiomeGenBase)biome, (BiomeDictionary.Type)BiomeDictionary.Type.SANDY)) {
                EntityRegistry.addSpawn(EntityDesertCrawler.class, (int)50, (int)1, (int)4, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])new BiomeGenBase[]{biome});
                EntityRegistry.addSpawn(EntityAridWarrior.class, (int)35, (int)1, (int)4, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])new BiomeGenBase[]{biome});
                EntityRegistry.addSpawn(EntitySaguaroWorm.class, (int)20, (int)1, (int)4, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])new BiomeGenBase[]{biome});
            }
            if (biome == BiomeGenBase.ocean || biome == BiomeGenBase.deepOcean) {
                EntityRegistry.addSpawn(EntityWhale.class, (int)1, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.waterCreature, (BiomeGenBase[])new BiomeGenBase[]{biome});
                EntityRegistry.addSpawn(EntityShark.class, (int)1, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.waterCreature, (BiomeGenBase[])new BiomeGenBase[]{biome});
                EntityRegistry.addSpawn(EntityLiopleurodon.class, (int)1, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.waterCreature, (BiomeGenBase[])new BiomeGenBase[]{biome});
            }
            if (BiomeDictionary.isBiomeOfType((BiomeGenBase)biome, (BiomeDictionary.Type)BiomeDictionary.Type.BEACH)) {
                EntityRegistry.addSpawn(EntityCrab.class, (int)100, (int)4, (int)4, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])new BiomeGenBase[]{biome});
                EntityRegistry.addSpawn(EntityKingCrab.class, (int)40, (int)4, (int)4, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])new BiomeGenBase[]{biome});
            }
            if (BiomeDictionary.isBiomeOfType((BiomeGenBase)biome, (BiomeDictionary.Type)BiomeDictionary.Type.JUNGLE)) {
                EntityRegistry.addSpawn(EntityJungleBat.class, (int)50, (int)1, (int)4, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])new BiomeGenBase[]{biome});
                EntityRegistry.addSpawn(EntityJungleDramcryx.class, (int)80, (int)1, (int)4, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])new BiomeGenBase[]{biome});
                EntityRegistry.addSpawn(EntityJungleSpider.class, (int)80, (int)1, (int)4, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])new BiomeGenBase[]{biome});
            }
            if (BiomeDictionary.isBiomeOfType((BiomeGenBase)biome, (BiomeDictionary.Type)BiomeDictionary.Type.PLAINS)) {
                EntityRegistry.addSpawn(EntityKobblin.class, (int)5, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])new BiomeGenBase[]{biome});
            }
            if (BiomeDictionary.isBiomeOfType((BiomeGenBase)biome, (BiomeDictionary.Type)BiomeDictionary.Type.PLAINS) || BiomeDictionary.isBiomeOfType((BiomeGenBase)biome, (BiomeDictionary.Type)BiomeDictionary.Type.MOUNTAIN)) {
                EntityRegistry.addSpawn(EntityCyclops.class, (int)80, (int)2, (int)4, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])new BiomeGenBase[]{biome});
            }
            if (BiomeDictionary.isBiomeOfType((BiomeGenBase)biome, (BiomeDictionary.Type)BiomeDictionary.Type.FOREST)) {
                EntityRegistry.addSpawn(EntityPumpkinSpider.class, (int)20, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])new BiomeGenBase[]{biome});
            }
            EntityRegistry.addSpawn(EntityMiner.class, (int)5, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])new BiomeGenBase[]{biome});
            EntityRegistry.addSpawn(EntityJackOMan.class, (int)5, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])new BiomeGenBase[]{biome});
            EntityRegistry.addSpawn(EntityCaveCrawler.class, (int)70, (int)2, (int)3, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])new BiomeGenBase[]{biome});
            EntityRegistry.addSpawn(EntityRotatick.class, (int)70, (int)3, (int)4, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])new BiomeGenBase[]{biome});
            EntityRegistry.addSpawn(EntityEnthralledDramcryx.class, (int)70, (int)3, (int)4, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])new BiomeGenBase[]{biome});
            EntityRegistry.addSpawn(EntityTheEye.class, (int)30, (int)1, (int)4, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])new BiomeGenBase[]{biome});
            EntityRegistry.addSpawn(EntityTheGrue.class, (int)30, (int)1, (int)4, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])new BiomeGenBase[]{biome});
            EntityRegistry.addSpawn(EntityCaveclops.class, (int)70, (int)1, (int)4, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])new BiomeGenBase[]{biome});
            EntityRegistry.addSpawn(EntityEnderSpider.class, (int)4, (int)1, (int)4, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])new BiomeGenBase[]{biome});
            EntityRegistry.addSpawn(EntityRainbour.class, (int)1, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.ambient, (BiomeGenBase[])new BiomeGenBase[]{biome});
        }
    }

    public static void addVetheaSpawns() {
        BiomeGenBase[] vetheaBiome = new BiomeGenBase[]{DimensionHelper.vetheaBiome};
        EntityRegistry.addSpawn(EntityVermenous.class, (int)1, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])vetheaBiome);
        EntityRegistry.addSpawn(EntityCymesoid.class, (int)1, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])vetheaBiome);
        EntityRegistry.addSpawn(EntityDreamwrecker.class, (int)1, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])vetheaBiome);
        EntityRegistry.addSpawn(EntityDuo.class, (int)1, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])vetheaBiome);
        EntityRegistry.addSpawn(EntityTwins.class, (int)1, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])vetheaBiome);
        EntityRegistry.addSpawn(EntityBohemite.class, (int)1, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])vetheaBiome);
        EntityRegistry.addSpawn(EntityTocaxin.class, (int)1, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])vetheaBiome);
        EntityRegistry.addSpawn(EntityMandragora.class, (int)1, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])vetheaBiome);
        EntityRegistry.addSpawn(EntityShadahier.class, (int)1, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])vetheaBiome);
        EntityRegistry.addSpawn(EntityLheiva.class, (int)1, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])vetheaBiome);
        EntityRegistry.addSpawn(EntityHoverStinger.class, (int)1, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])vetheaBiome);
        EntityRegistry.addSpawn(EntityAcidHag.class, (int)1, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])vetheaBiome);
        EntityRegistry.addSpawn(EntityKazrotic.class, (int)1, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])vetheaBiome);
        EntityRegistry.addSpawn(EntityVhraak.class, (int)1, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])vetheaBiome);
        EntityRegistry.addSpawn(EntityHelio.class, (int)1, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])vetheaBiome);
        EntityRegistry.addSpawn(EntityBiphron.class, (int)1, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])vetheaBiome);
        EntityRegistry.addSpawn(EntityZoragon.class, (int)1, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])vetheaBiome);
        EntityRegistry.addSpawn(EntityGorgosion.class, (int)1, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])vetheaBiome);
        EntityRegistry.addSpawn(EntityDissiment.class, (int)1, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])vetheaBiome);
        EntityRegistry.addSpawn(EntityZone.class, (int)1, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])vetheaBiome);
        EntityRegistry.addSpawn(EntityLorga.class, (int)1, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])vetheaBiome);
        EntityRegistry.addSpawn(EntityLorgaFlight.class, (int)1, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])vetheaBiome);
        EntityRegistry.addSpawn(EntityGalroid.class, (int)1, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])vetheaBiome);
        EntityRegistry.addSpawn(EntityMysteriousManLayer1.class, (int)1, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.creature, (BiomeGenBase[])vetheaBiome);
        EntityRegistry.addSpawn(EntityMysteriousManLayer2.class, (int)1, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.creature, (BiomeGenBase[])vetheaBiome);
        EntityRegistry.addSpawn(EntityMysteriousManLayer3.class, (int)1, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.creature, (BiomeGenBase[])vetheaBiome);
    }

    public static boolean overworldBiome(BiomeGenBase b) {
        List<BiomeGenBase.SpawnListEntry> monsterList = (List<BiomeGenBase.SpawnListEntry>)ObfuscationReflectionHelper.getPrivateValue(BiomeGenBase.class, b, new String[]{"as", "spawnableMonsterList", "spawnableMonsterList"});
        for (BiomeGenBase.SpawnListEntry e : monsterList) {
            if (e.entityClass != EntityZombie.class) continue;
            return true;
        }
        return false;
    }
}

