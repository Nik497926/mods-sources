/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common;

import com.meteor.extrabotany.ExtraBotany;
import com.meteor.extrabotany.common.achievement.ModAchievement;
import com.meteor.extrabotany.common.block.ModBlocks;
import com.meteor.extrabotany.common.block.ModMultiBlocks;
import com.meteor.extrabotany.common.command.ModCommands;
import com.meteor.extrabotany.common.core.handler.ConfigHandler;
import com.meteor.extrabotany.common.entity.FakePlayer;
import com.meteor.extrabotany.common.entity.ModEntities;
import com.meteor.extrabotany.common.event.ModEvents;
import com.meteor.extrabotany.common.integration.Intergration;
import com.meteor.extrabotany.common.item.ModItems;
import com.meteor.extrabotany.common.lexicon.LexiconModData;
import com.meteor.extrabotany.common.potion.ModPotions;
import com.meteor.extrabotany.common.recipe.ModRecipe;
import com.meteor.extrabotany.common.world.Asgard.AsgardWorldGenerator;
import com.meteor.extrabotany.common.world.Asgard.AsgardWorldProvider;
import com.meteor.extrabotany.common.world.Asgard.Biome.BiomeRegistry;
import com.meteor.extrabotany.common.world.ModWorldGen;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.DimensionManager;
import ru.justagod.cutter.invoke.Invoke;

public class CommonProxy {
    public static ExtraBotany instance;
    public AsgardWorldGenerator worldGen;

    public EntityPlayer getClientPlayer() {
        return null;
    }

    public void preInit(FMLPreInitializationEvent event) {
        new ConfigHandler(event);
        ModPotions.init();
        ModBlocks.init();
        ModItems.init();
        ModEntities.init();
        ModRecipe.init();
        ModAchievement.init();
        ModMultiBlocks.init();
        Intergration.preInit(event);
        ModWorldGen.init();
        BiomeRegistry.mainRegsitry();
        DimensionManager.registerProviderType(150, AsgardWorldProvider.class, false);
        DimensionManager.registerDimension(150, 150);
        LexiconModData.init();
    }

    public void init(FMLInitializationEvent event) {
        ModEvents.init();
        new FakePlayer();
        Intergration.init(event);
        System.out.println("Load asgard...");
        Invoke.server(() -> {});
        System.out.println("Load asgard success!");
    }

    public void postInit(FMLPostInitializationEvent event) {
        Intergration.postInit(event);
    }

    public void serverStarting(FMLServerStartingEvent event) {
        new ModCommands(event);
    }
}

