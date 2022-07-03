/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block;

import com.meteor.extrabotany.common.block.BlockAuraControler;
import com.meteor.extrabotany.common.block.BlockAutoPlate;
import com.meteor.extrabotany.common.block.BlockAutoPool;
import com.meteor.extrabotany.common.block.BlockAutoTrade;
import com.meteor.extrabotany.common.block.BlockBoost;
import com.meteor.extrabotany.common.block.BlockDaisy;
import com.meteor.extrabotany.common.block.BlockEAltar;
import com.meteor.extrabotany.common.block.BlockElfPool;
import com.meteor.extrabotany.common.block.BlockElfUpdater;
import com.meteor.extrabotany.common.block.BlockExPylon;
import com.meteor.extrabotany.common.block.BlockExtraAspects;
import com.meteor.extrabotany.common.block.BlockFlower;
import com.meteor.extrabotany.common.block.BlockGaiaChest;
import com.meteor.extrabotany.common.block.BlockGenDust;
import com.meteor.extrabotany.common.block.BlockMods;
import com.meteor.extrabotany.common.block.BlockPearl;
import com.meteor.extrabotany.common.block.BlockPoolEfir;
import com.meteor.extrabotany.common.block.BlockRelicPlate;
import com.meteor.extrabotany.common.block.BlockSpawner;
import com.meteor.extrabotany.common.block.BlockSpecial;
import com.meteor.extrabotany.common.block.BlockSummon;
import com.meteor.extrabotany.common.block.BlockTradeMana;
import com.meteor.extrabotany.common.block.BlockTransformater;
import com.meteor.extrabotany.common.block.ExtraSpreader;
import com.meteor.extrabotany.common.block.decor.BlockCompressTerra;
import com.meteor.extrabotany.common.block.decor.BlockElfirium;
import com.meteor.extrabotany.common.block.decor.BlockElvenQuartzSlab;
import com.meteor.extrabotany.common.block.decor.BlockElvenQuartzStairs;
import com.meteor.extrabotany.common.block.decor.BlockExtraStabilizer;
import com.meteor.extrabotany.common.block.decor.BlockGaiaQuartzSlab;
import com.meteor.extrabotany.common.block.decor.BlockGaiaQuartzStairs;
import com.meteor.extrabotany.common.block.decor.BlockSoulSteel;
import com.meteor.extrabotany.common.block.decor.ElfiriumOre;
import com.meteor.extrabotany.common.block.subtile.SubTileInfernoidisy;
import com.meteor.extrabotany.common.block.subtile.SubTileManalinkuim;
import com.meteor.extrabotany.common.block.subtile.functional.SubTileAnnoyobloom;
import com.meteor.extrabotany.common.block.subtile.functional.SubTileArtifaconia;
import com.meteor.extrabotany.common.block.subtile.functional.SubTileDiplopbamboo;
import com.meteor.extrabotany.common.block.subtile.functional.SubTileIcebirdium;
import com.meteor.extrabotany.common.block.subtile.functional.SubTileJudasvow;
import com.meteor.extrabotany.common.block.subtile.functional.SubTileJudasvowSakura;
import com.meteor.extrabotany.common.block.subtile.functional.SubTileLaunchish;
import com.meteor.extrabotany.common.block.subtile.functional.SubTileNecrofluer;
import com.meteor.extrabotany.common.block.subtile.functional.SubTileVoiduim;
import com.meteor.extrabotany.common.block.subtile.functional.SubTileVolatilily;
import com.meteor.extrabotany.common.block.subtile.functional.SubTileWoodienia;
import com.meteor.extrabotany.common.block.subtile.generating.SubTileBellflower;
import com.meteor.extrabotany.common.block.subtile.generating.SubTileBlueenchantress;
import com.meteor.extrabotany.common.block.subtile.generating.SubTileCandyflower;
import com.meteor.extrabotany.common.block.subtile.generating.SubTileGeminiorchid;
import com.meteor.extrabotany.common.block.subtile.generating.SubTileMoonlightlily;
import com.meteor.extrabotany.common.block.subtile.generating.SubTileNumeronbalsam;
import com.meteor.extrabotany.common.block.subtile.generating.SubTileNumerondandelife;
import com.meteor.extrabotany.common.block.subtile.generating.SubTileOmniviolet;
import com.meteor.extrabotany.common.block.subtile.generating.SubTilePyschobloom;
import com.meteor.extrabotany.common.block.subtile.generating.SubTileStonesia;
import com.meteor.extrabotany.common.block.subtile.generating.SubTileSunshinelily;
import com.meteor.extrabotany.common.block.tile.TileAuraControler;
import com.meteor.extrabotany.common.block.tile.TileBlockBoost;
import com.meteor.extrabotany.common.block.tile.TileBlockElfUpdater;
import com.meteor.extrabotany.common.block.tile.TileBlockPoolEfir;
import com.meteor.extrabotany.common.block.tile.TileBlockSpawner;
import com.meteor.extrabotany.common.block.tile.TileBlockSummon;
import com.meteor.extrabotany.common.block.tile.TileEAltar;
import com.meteor.extrabotany.common.block.tile.TileElfPool;
import com.meteor.extrabotany.common.block.tile.TileGaiaChest;
import com.meteor.extrabotany.common.block.tile.TileRelicPlate;
import com.meteor.extrabotany.common.block.tile.TileTradeMana;
import com.meteor.extrabotany.common.block.tile.TileTransformater;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.common.block.decor.slabs.BlockModSlab;

public class ModBlocks {
    @Mod.Instance(value="extrabotania")
    public static ModBlocks modInstance;
    public static String MODID;
    public static Block specialFlower;
    public static Block gaiaquartz;
    public static Block gaiaquartzslabfull;
    public static Block gaiaquartzslab;
    public static Block gaiaquartzstairs;
    public static Block elvenquartz;
    public static Block elvenquartzslabfull;
    public static Block elvenquartzslab;
    public static Block elvenquartzstairs;
    public static Block relicplate;
    public static Block resonancehouse;
    public static Block gaiachest;
    public static Block gaiainvreader;
    public static Block gaiamaininvreader;
    public static Block gaiamaininvchest;
    public static Block compressedTerra;
    public static Block blocksoulsteel;
    public static Block blockelfirium;
    public static Block awakeupgrade;
    public static Block awakeelfupdater;
    public static Block manatrade;
    public static Block elfpool;
    public static Block auracontroler;
    public static Block poolefir;
    public static Block summon;
    public static Block transformater;
    public static Block eAltar;
    public static Block boost;
    public static Block elfOre;
    public static Block extraSpreader;
    public static Block summonOdin;
    public static Block flowerBlock;
    public static Block genDust;
    public static Block blockPearl;
    public static Block blockAutoPlate;
    public static Block blockAutoPool;
    public static Block blockAutoTrade;
    public static Block blockDaisy;
    public static Block blockExtraStab;
    public static Block extraAspects;
    public static Block blockExPylon;

    public static void init() {
        System.out.println("extra init");
        auracontroler = new BlockAuraControler();
        elfpool = new BlockElfPool();
        manatrade = new BlockTradeMana(Material.iron);
        awakeelfupdater = new BlockElfUpdater(0);
        blockelfirium = new BlockElfirium("blockelfirium");
        blocksoulsteel = new BlockSoulSteel("blocksoulsteel");
        compressedTerra = new BlockCompressTerra("compressTerra");
        relicplate = new BlockRelicPlate();
        specialFlower = new BlockSpecial();
        gaiaquartz = new BlockMods(Material.iron, "blockgaiaquartz");
        ((BlockMods)gaiaquartz).setBeaconBase(true);
        gaiaquartzslab = new BlockGaiaQuartzSlab(false);
        gaiaquartzslabfull = new BlockGaiaQuartzSlab(true);
        gaiaquartzstairs = new BlockGaiaQuartzStairs();
        elvenquartz = new BlockMods(Material.iron, "blockelvenquartz");
        ((BlockMods)elvenquartz).setBeaconBase(true);
        elvenquartzslab = new BlockElvenQuartzSlab(false);
        elvenquartzslabfull = new BlockElvenQuartzSlab(true);
        elvenquartzstairs = new BlockElvenQuartzStairs();
        gaiachest = new BlockGaiaChest(-1);
        poolefir = new BlockPoolEfir(Material.iron);
        summon = new BlockSummon(Material.iron);
        transformater = new BlockTransformater(Material.iron);
        eAltar = new BlockEAltar(Material.iron);
        boost = new BlockBoost();
        elfOre = new ElfiriumOre("elfiriumOre");
        summonOdin = new BlockSpawner();
        blockPearl = new BlockPearl();
        System.out.println("extra spreader");
        extraSpreader = new ExtraSpreader();
        genDust = new BlockGenDust();
        flowerBlock = new BlockFlower();
        blockAutoPlate = new BlockAutoPlate();
        blockAutoPool = new BlockAutoPool();
        blockAutoTrade = new BlockAutoTrade();
        blockDaisy = new BlockDaisy();
        blockExtraStab = new BlockExtraStabilizer();
        extraAspects = new BlockExtraAspects();
        blockExPylon = new BlockExPylon();
        ((BlockModSlab)gaiaquartzslab).register();
        ((BlockModSlab)gaiaquartzslabfull).register();
        ((BlockModSlab)elvenquartzslab).register();
        ((BlockModSlab)elvenquartzslabfull).register();
        ModBlocks.registerTile(TileBlockSummon.class, "summon");
        ModBlocks.registerTile(TileBlockPoolEfir.class, "poolefir");
        ModBlocks.registerTile(TileAuraControler.class, "auracontroler");
        ModBlocks.registerTile(TileGaiaChest.class, "gaiachest");
        ModBlocks.registerTile(TileRelicPlate.class, "relicplate");
        ModBlocks.registerTile(TileBlockElfUpdater.class, "blockelfupdater");
        ModBlocks.registerTile(TileTradeMana.class, "manatrade");
        ModBlocks.registerTile(TileElfPool.class, "elfpool");
        ModBlocks.registerTile(TileTransformater.class, "trasformater");
        ModBlocks.registerTile(TileEAltar.class, "ealtar");
        ModBlocks.registerTile(TileBlockBoost.class, "boost");
        ModBlocks.registerTile(TileBlockSpawner.class, "spawnerOdin");
        ModBlocks.initTileEntities();
    }

    private static void initTileEntities() {
        ModBlocks.registerSubTile("bellflower", SubTileBellflower.class);
        ModBlocks.registerSubTile("manalinkuim", SubTileManalinkuim.class);
        ModBlocks.registerSubTile("annoyobloom", SubTileAnnoyobloom.class);
        ModBlocks.registerSubTile("launchish", SubTileLaunchish.class);
        ModBlocks.registerSubTile("infernoidisy", SubTileInfernoidisy.class);
        ModBlocks.registerSubTile("pyschobloom", SubTilePyschobloom.class);
        ModBlocks.registerSubTile("stonesia", SubTileStonesia.class);
        ModBlocks.registerSubTile("diplopbamboo", SubTileDiplopbamboo.class);
        ModBlocks.registerSubTile("voiduim", SubTileVoiduim.class);
        ModBlocks.registerSubTile("artifaconia", SubTileArtifaconia.class);
        ModBlocks.registerSubTile("judasvow", SubTileJudasvow.class);
        ModBlocks.registerSubTile("icebirdium", SubTileIcebirdium.class);
        ModBlocks.registerSubTile("numeronbalsam", SubTileNumeronbalsam.class);
        ModBlocks.registerSubTile("volatilily", SubTileVolatilily.class);
        ModBlocks.registerSubTile("omniviolet", SubTileOmniviolet.class);
        ModBlocks.registerSubTile("woodienia", SubTileWoodienia.class);
        ModBlocks.registerSubTile("necrofleur", SubTileNecrofluer.class);
        ModBlocks.registerSubTile("numerondandelife", SubTileNumerondandelife.class);
        ModBlocks.registerSubTile("geminiorchid", SubTileGeminiorchid.class);
        ModBlocks.registerSubTile("candyflower", SubTileCandyflower.class);
        ModBlocks.registerSubTile("sunshinelily", SubTileSunshinelily.class);
        ModBlocks.registerSubTile("moonlightlily", SubTileMoonlightlily.class);
        ModBlocks.registerSubTile("blueenchantress", SubTileBlueenchantress.class);
        ModBlocks.registerSubTile("judasvowsakura", SubTileJudasvowSakura.class);
    }

    private static void registerTile(Class clazz, String key) {
        GameRegistry.registerTileEntity((Class)clazz, (String)("extrabotania:" + key));
    }

    private static void registerSubTile(String key, Class clazz) {
        BotaniaAPI.registerSubTile((String)key, (Class)clazz);
    }

    static {
        MODID = "extrabotania";
    }
}

