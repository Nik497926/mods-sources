/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.IWorldGenerator
 *  cpw.mods.fml.common.event.FMLInitializationEvent
 *  cpw.mods.fml.common.event.FMLPostInitializationEvent
 *  cpw.mods.fml.common.event.FMLPreInitializationEvent
 *  cpw.mods.fml.common.network.IGuiHandler
 *  cpw.mods.fml.common.network.NetworkRegistry
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.world.World
 */
package net.divinerpg.utils.proxies;

import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import java.awt.Color;
import net.divinerpg.DivineRPG;
import net.divinerpg.blocks.arcana.container.tile_entity.TileEntityDemonFurnace;
import net.divinerpg.blocks.arcana.container.tile_entity.TileEntityDramixAltar;
import net.divinerpg.blocks.arcana.container.tile_entity.TileEntityExtractor;
import net.divinerpg.blocks.arcana.container.tile_entity.TileEntityGreenlightFurnace;
import net.divinerpg.blocks.arcana.container.tile_entity.TileEntityMoltenFurnace;
import net.divinerpg.blocks.arcana.container.tile_entity.TileEntityMoonlightFurnace;
import net.divinerpg.blocks.arcana.container.tile_entity.TileEntityOceanfireFurnace;
import net.divinerpg.blocks.arcana.container.tile_entity.TileEntityParasectaAltar;
import net.divinerpg.blocks.arcana.container.tile_entity.TileEntityWhitefireFurnace;
import net.divinerpg.blocks.base.tileentity.TileEntityStupidSpawner;
import net.divinerpg.blocks.iceika.tileentity.TileEntityCoalstoneFurnace;
import net.divinerpg.blocks.iceika.tileentity.TileEntityFrostedChest;
import net.divinerpg.blocks.iceika.tileentity.TileEntityPresentBox;
import net.divinerpg.blocks.twilight.TileEntityEdenChest;
import net.divinerpg.blocks.vanilla.container.tileentity.TileEntityAltarOfCorruption;
import net.divinerpg.blocks.vanilla.container.tileentity.TileEntityAyeracoBeam;
import net.divinerpg.blocks.vanilla.container.tileentity.TileEntityAyeracoSpawn;
import net.divinerpg.blocks.vanilla.container.tileentity.TileEntityBoneChest;
import net.divinerpg.blocks.vethea.container.tileentity.TileEntityDreamLamp;
import net.divinerpg.blocks.vethea.container.tileentity.TileEntityInfusionTable;
import net.divinerpg.client.GuiHandler;
import net.divinerpg.client.render.block.TileEntityStatue;
import net.divinerpg.dimensions.vanilla.DivineWorldgen;
import net.divinerpg.entities.iceika.EntityWreck;
import net.divinerpg.items.base.ItemNoFallPotion;
import net.divinerpg.utils.DimensionHelper;
import net.divinerpg.utils.DivineOredict;
import net.divinerpg.utils.OmegaEvents;
import net.divinerpg.utils.Util;
import net.divinerpg.utils.blocks.ArcanaBlocks;
import net.divinerpg.utils.blocks.IceikaBlocks;
import net.divinerpg.utils.blocks.JourneyBlocks;
import net.divinerpg.utils.blocks.TwilightBlocks;
import net.divinerpg.utils.blocks.VanillaBlocks;
import net.divinerpg.utils.blocks.VetheaBlocks;
import net.divinerpg.utils.entities.ArcanaEntityRegistry;
import net.divinerpg.utils.entities.DepthsEntityRegistry;
import net.divinerpg.utils.entities.IceikaEntityRegistry;
import net.divinerpg.utils.entities.JourneyEntityRegistry;
import net.divinerpg.utils.entities.TwilightEntityRegistry;
import net.divinerpg.utils.entities.VanillaEntityRegistry;
import net.divinerpg.utils.entities.VetheaEntityRegistry;
import net.divinerpg.utils.events.ArcanaTickHandler;
import net.divinerpg.utils.events.EntityConstructorEvent;
import net.divinerpg.utils.events.EventArmorFullSet;
import net.divinerpg.utils.events.EventArmorTick;
import net.divinerpg.utils.events.EventBonemeal;
import net.divinerpg.utils.events.EventBucketFill;
import net.divinerpg.utils.events.EventLightning;
import net.divinerpg.utils.events.EventTooltip;
import net.divinerpg.utils.events.Events;
import net.divinerpg.utils.events.Ticker;
import net.divinerpg.utils.items.ArcanaItems;
import net.divinerpg.utils.items.IceikaItems;
import net.divinerpg.utils.items.ItemsFood;
import net.divinerpg.utils.items.JourneyItemsArmor;
import net.divinerpg.utils.items.JourneyItemsOther;
import net.divinerpg.utils.items.JourneyItemsTools;
import net.divinerpg.utils.items.JourneyItemsWeapon;
import net.divinerpg.utils.items.TwilightItemsArmor;
import net.divinerpg.utils.items.TwilightItemsCrops;
import net.divinerpg.utils.items.TwilightItemsOther;
import net.divinerpg.utils.items.TwilightItemsTools;
import net.divinerpg.utils.items.TwilightItemsWeapons;
import net.divinerpg.utils.items.VanillaItemsArmor;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.divinerpg.utils.items.VanillaItemsTools;
import net.divinerpg.utils.items.VanillaItemsWeapons;
import net.divinerpg.utils.items.VetheaItems;
import net.divinerpg.utils.recipes.JourneyRecipeHelper;
import net.divinerpg.utils.recipes.TwilightRecipeHelper;
import net.divinerpg.utils.recipes.VanillaRecipeHelper;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.world.World;

public class CommonProxy {
    public static Potion nofallPotion;
    public static Potion flamePotion;

    public void preInitClient(FMLPreInitializationEvent event) {
    }

    public void renderThings() {
    }

    public void preInitServer(FMLPreInitializationEvent event) {
        Util.postForgeEvent(new EventBucketFill());
        Util.postForgeEvent(new EventBonemeal());
        Util.postForgeEvent(new EventArmorFullSet());
        Util.postFMLEvent(new EventArmorTick());
        Util.postForgeEvent(new Events());
        Util.postFMLEvent(new Events());
        Util.postFMLEvent(new ArcanaTickHandler());
        Util.postForgeEvent(new ArcanaTickHandler());
        Util.postForgeEvent(new EntityConstructorEvent());
        Util.postForgeEvent(new EventTooltip());
        Util.postForgeEvent(new EventLightning());
        Util.postForgeEvent(new OmegaEvents());
        Util.postFMLEvent(new Ticker());
        ItemsFood.init();
        IceikaItems.init();
        IceikaBlocks.init();
        VetheaBlocks.init();
        VetheaItems.init();
        VanillaBlocks.init();
        TwilightItemsOther.init();
        TwilightBlocks.init();
        TwilightItemsCrops.init();
        VanillaItemsOther.init();
        ArcanaBlocks.init();
        ArcanaItems.init();
        JourneyItemsOther.init();
        JourneyItemsTools.init();
        JourneyBlocks.init();
        DivineRPGTabs.init();
        DivineOredict.init();
        TwilightItemsArmor.init();
        TwilightItemsTools.init();
        TwilightItemsWeapons.init();
        JourneyItemsWeapon.init();
        JourneyItemsArmor.init();
        VanillaItemsArmor.init();
        VanillaItemsTools.init();
        VanillaItemsWeapons.init();
        GameRegistry.registerTileEntity(TileEntityInfusionTable.class, (String)"Infusion Table");
        GameRegistry.registerTileEntity(TileEntityStatue.class, (String)"DivineRPGStatue");
        GameRegistry.registerTileEntity(TileEntityFrostedChest.class, (String)"Frosted Chest");
        GameRegistry.registerTileEntity(TileEntityEdenChest.class, (String)"Eden Chest");
        GameRegistry.registerTileEntity(TileEntityBoneChest.class, (String)"Bone Chest");
        GameRegistry.registerTileEntity(TileEntityAyeracoBeam.class, (String)"Ayeraco Beam");
        GameRegistry.registerTileEntity(TileEntityExtractor.class, (String)"Arcanium Extractor");
        GameRegistry.registerTileEntity(TileEntityDramixAltar.class, (String)"Dramix Altar");
        GameRegistry.registerTileEntity(TileEntityParasectaAltar.class, (String)"Parasecta Altar");
        GameRegistry.registerTileEntity(TileEntityCoalstoneFurnace.class, (String)"Coalstone Furnace");
        GameRegistry.registerTileEntity(TileEntityGreenlightFurnace.class, (String)"Greenlight Furnace");
        GameRegistry.registerTileEntity(TileEntityMoltenFurnace.class, (String)"Molten Furnace");
        GameRegistry.registerTileEntity(TileEntityMoonlightFurnace.class, (String)"Moonlight Furnace");
        GameRegistry.registerTileEntity(TileEntityOceanfireFurnace.class, (String)"Oceanfire Furnace");
        GameRegistry.registerTileEntity(TileEntityWhitefireFurnace.class, (String)"Whitefire Furnace");
        GameRegistry.registerTileEntity(TileEntityDemonFurnace.class, (String)"Demon Furnace");
        GameRegistry.registerTileEntity(TileEntityAltarOfCorruption.class, (String)"Altar Of Corruption");
        GameRegistry.registerTileEntity(TileEntityStupidSpawner.class, (String)"StupidMobSpawner");
        GameRegistry.registerTileEntity(TileEntityPresentBox.class, (String)"PresentBox");
        GameRegistry.registerTileEntity(TileEntityAyeracoSpawn.class, (String)"AyeracoSpawn");
        GameRegistry.registerTileEntity(TileEntityDreamLamp.class, (String)"DreamLamp");
        VanillaEntityRegistry.init();
        TwilightEntityRegistry.init();
        IceikaEntityRegistry.init();
        VetheaEntityRegistry.init();
        ArcanaEntityRegistry.init();
        DepthsEntityRegistry.init();
        JourneyEntityRegistry.init();
        DimensionHelper.init();
        NetworkRegistry.INSTANCE.registerGuiHandler((Object)DivineRPG.instance, (IGuiHandler)new GuiHandler());
        nofallPotion = new ItemNoFallPotion(22, false, 0).setPotionName("potion.nofallPotion");
    }

    public void init(FMLInitializationEvent event) {
        GameRegistry.registerWorldGenerator((IWorldGenerator)new DivineWorldgen(), (int)0);
        VanillaRecipeHelper.init();
        TwilightRecipeHelper.init();
        JourneyRecipeHelper.init();
    }

    public void postInit(FMLPostInitializationEvent event) {
        Util.addBucket(DivineRPG.tarFluid, new ItemStack(VanillaItemsOther.tarBucket));
    }

    public void spawnParticle(World w, double x, double y, double z, String particle, boolean random) {
    }

    public void spawnParticle(World w, double x, double y, double z, String particle, boolean random, int randFactor) {
    }

    public void spawnParticle(World w, double x, double y, double z, Color c, boolean random) {
    }

    public void generateParticlesWreck(EntityWreck e) {
    }

    public void updateClientArcana(float amount) {
    }

    public void generateParticlesPurple(EntityPlayer theEntity) {
    }

    public void generateParticlesRed(Entity theEntity) {
    }
}

