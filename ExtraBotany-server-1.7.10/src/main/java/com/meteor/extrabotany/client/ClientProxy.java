/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client;

import com.meteor.extrabotany.ExtraBotany;
import com.meteor.extrabotany.client.lib.EventRecipeElfPool;
import com.meteor.extrabotany.client.lib.HUDHandler;
import com.meteor.extrabotany.client.lib.KeyBindings;
import com.meteor.extrabotany.client.lib.KeyInputHandler;
import com.meteor.extrabotany.client.render.RenderShield;
import com.meteor.extrabotany.client.render.block.RenderBlockAutoPlate;
import com.meteor.extrabotany.client.render.block.RenderBlockAutoPool;
import com.meteor.extrabotany.client.render.block.RenderBlockAutoTrade;
import com.meteor.extrabotany.client.render.block.RenderBlockBoost;
import com.meteor.extrabotany.client.render.block.RenderBlockDaisy;
import com.meteor.extrabotany.client.render.block.RenderBlockEAltar;
import com.meteor.extrabotany.client.render.block.RenderBlockTransformater;
import com.meteor.extrabotany.client.render.block.RenderElfPool;
import com.meteor.extrabotany.client.render.block.RenderExPylon;
import com.meteor.extrabotany.client.render.block.RenderExtraSpreader;
import com.meteor.extrabotany.client.render.block.RenderGaiaChest;
import com.meteor.extrabotany.client.render.block.RenderPoolEfir;
import com.meteor.extrabotany.client.render.entity.RenderAdvSpark;
import com.meteor.extrabotany.client.render.entity.RenderAsgardNpc;
import com.meteor.extrabotany.client.render.entity.RenderExMachine.RenderExMachine;
import com.meteor.extrabotany.client.render.entity.RenderGaiaIII;
import com.meteor.extrabotany.client.render.entity.RenderGaiaIV;
import com.meteor.extrabotany.client.render.entity.RenderLycorisradiataGreen;
import com.meteor.extrabotany.client.render.entity.RenderLycorisradiataPurple;
import com.meteor.extrabotany.client.render.entity.RenderLycorisradiataRed;
import com.meteor.extrabotany.client.render.entity.RenderOdin;
import com.meteor.extrabotany.client.render.entity.RenderSakuraFall;
import com.meteor.extrabotany.client.render.entity.RenderSpear;
import com.meteor.extrabotany.client.render.entity.RenderTeleportPearl;
import com.meteor.extrabotany.client.render.tile.RenderTileAuraControler;
import com.meteor.extrabotany.client.render.tile.RenderTileAutoDaisy;
import com.meteor.extrabotany.client.render.tile.RenderTileAutoPlate;
import com.meteor.extrabotany.client.render.tile.RenderTileAutoPool;
import com.meteor.extrabotany.client.render.tile.RenderTileAutoTrade;
import com.meteor.extrabotany.client.render.tile.RenderTileBlockSpawner;
import com.meteor.extrabotany.client.render.tile.RenderTileEAltar;
import com.meteor.extrabotany.client.render.tile.RenderTileElfPool;
import com.meteor.extrabotany.client.render.tile.RenderTileExPylon;
import com.meteor.extrabotany.client.render.tile.RenderTileExtraSpreader;
import com.meteor.extrabotany.client.render.tile.RenderTileGaiaChest;
import com.meteor.extrabotany.client.render.tile.RenderTileManaBoost;
import com.meteor.extrabotany.client.render.tile.RenderTilePoolEfir;
import com.meteor.extrabotany.client.render.tile.RenderTileRelicPlate;
import com.meteor.extrabotany.client.render.tile.RenderTileTransformer;
import com.meteor.extrabotany.common.CommonProxy;
import com.meteor.extrabotany.common.block.tile.TileAuraControler;
import com.meteor.extrabotany.common.block.tile.TileAutoDaisy;
import com.meteor.extrabotany.common.block.tile.TileAutoPlate;
import com.meteor.extrabotany.common.block.tile.TileAutoPool;
import com.meteor.extrabotany.common.block.tile.TileAutoTradeElf;
import com.meteor.extrabotany.common.block.tile.TileBlockBoost;
import com.meteor.extrabotany.common.block.tile.TileBlockPoolEfir;
import com.meteor.extrabotany.common.block.tile.TileBlockSpawner;
import com.meteor.extrabotany.common.block.tile.TileEAltar;
import com.meteor.extrabotany.common.block.tile.TileElfPool;
import com.meteor.extrabotany.common.block.tile.TileExPylon;
import com.meteor.extrabotany.common.block.tile.TileExtraSpreader;
import com.meteor.extrabotany.common.block.tile.TileGaiaChest;
import com.meteor.extrabotany.common.block.tile.TileRelicPlate;
import com.meteor.extrabotany.common.block.tile.TileTransformater;
import com.meteor.extrabotany.common.core.handler.ConfigHandler;
import com.meteor.extrabotany.common.core.version.UpdateChecker;
import com.meteor.extrabotany.common.entity.EntityAdvanceSpark;
import com.meteor.extrabotany.common.entity.EntityAdvanceSpark1;
import com.meteor.extrabotany.common.entity.EntityAdvanceSpark2;
import com.meteor.extrabotany.common.entity.EntityAsgard;
import com.meteor.extrabotany.common.entity.EntityExMachine;
import com.meteor.extrabotany.common.entity.EntityItemUnbreakable;
import com.meteor.extrabotany.common.entity.EntityLycorisradiataGreen;
import com.meteor.extrabotany.common.entity.EntityLycorisradiataPurple;
import com.meteor.extrabotany.common.entity.EntityLycorisradiataRed;
import com.meteor.extrabotany.common.entity.EntityOdin;
import com.meteor.extrabotany.common.entity.EntitySakuraFall;
import com.meteor.extrabotany.common.entity.EntitySpear;
import com.meteor.extrabotany.common.entity.EntityTeleportPearl;
import com.meteor.extrabotany.common.entity.gaia.EntityGaiaIII;
import com.meteor.extrabotany.common.entity.gaia.EntityGaiaIV;
import com.meteor.extrabotany.common.integration.Intergration;
import com.meteor.extrabotany.common.item.ModItems;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import net.minecraftforge.common.MinecraftForge;
import vazkii.botania.client.render.item.RenderTransparentItem;

public class ClientProxy
extends CommonProxy {
    public static int renderGaiaInvReader;
    public static int renderGaiaMainInvReader;
    public static int renderGaiaMainInvChest;
    public static int renderPoolEfir;
    public static int renderTransformater;
    public static int renderEAltar;
    public static int renderBoost;
    public static int renderAutoPool;
    public static IModelCustom model;
    public static IModelCustom modelPlate;
    public static IModelCustom modelTrade;
    public static IModelCustom modelDaisy;
    public static int renderExPylon;
    public static int renderPlate;
    public static int renderTrade;
    public static int renderDaisy;
    public static int renderExtraSpreader;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        Intergration.preInitClient(event);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        this.initRenderers();
        this.initEntities();
        new UpdateChecker().init();
        Intergration.initClient(event);
        MinecraftForge.EVENT_BUS.register((Object)new HUDHandler());
        MinecraftForge.EVENT_BUS.register((Object)new EventRecipeElfPool());
        FMLCommonHandler.instance().bus().register((Object)new KeyInputHandler());
        KeyBindings.init();
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
        Intergration.postInitClient(event);
    }

    private void initEntities() {
        RenderingRegistry.registerEntityRenderingHandler(EntityItemUnbreakable.class, (Render)new RenderItem());
        RenderingRegistry.registerEntityRenderingHandler(EntityGaiaIII.class, (Render)new RenderGaiaIII());
        RenderingRegistry.registerEntityRenderingHandler(EntityGaiaIV.class, (Render)new RenderGaiaIV());
        RenderingRegistry.registerEntityRenderingHandler(EntityExMachine.class, (Render)new RenderExMachine());
        RenderingRegistry.registerEntityRenderingHandler(EntityAsgard.class, (Render)new RenderAsgardNpc());
        RenderingRegistry.registerEntityRenderingHandler(EntitySpear.class, (Render)new RenderSpear());
        RenderingRegistry.registerEntityRenderingHandler(EntityLycorisradiataRed.class, (Render)new RenderLycorisradiataRed());
        RenderingRegistry.registerEntityRenderingHandler(EntityLycorisradiataGreen.class, (Render)new RenderLycorisradiataGreen());
        RenderingRegistry.registerEntityRenderingHandler(EntityLycorisradiataPurple.class, (Render)new RenderLycorisradiataPurple());
        RenderingRegistry.registerEntityRenderingHandler(EntityTeleportPearl.class, (Render)new RenderTeleportPearl(1.0f));
        RenderingRegistry.registerEntityRenderingHandler(EntitySakuraFall.class, (Render)new RenderSakuraFall());
        RenderingRegistry.registerEntityRenderingHandler(EntityOdin.class, (Render)new RenderOdin());
        RenderingRegistry.registerEntityRenderingHandler(EntityAdvanceSpark.class, (Render)new RenderAdvSpark());
        RenderingRegistry.registerEntityRenderingHandler(EntityAdvanceSpark1.class, (Render)new RenderAdvSpark());
        RenderingRegistry.registerEntityRenderingHandler(EntityAdvanceSpark2.class, (Render)new RenderAdvSpark());
        MinecraftForgeClient.registerItemRenderer((Item)ModItems.advanceSpark, (IItemRenderer)new RenderTransparentItem());
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new RenderBlockAutoPool());
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new RenderBlockAutoPlate());
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new RenderBlockAutoTrade());
        ClientRegistry.bindTileEntitySpecialRenderer(TileAutoPool.class, (TileEntitySpecialRenderer)new RenderTileAutoPool());
        ClientRegistry.bindTileEntitySpecialRenderer(TileAutoPlate.class, (TileEntitySpecialRenderer)new RenderTileAutoPlate());
        ClientRegistry.bindTileEntitySpecialRenderer(TileAutoTradeElf.class, (TileEntitySpecialRenderer)new RenderTileAutoTrade());
    }

    private void initRenderers() {
        model = AdvancedModelLoader.loadModel((ResourceLocation)new ResourceLocation(ExtraBotany.modid + ":textures/blocks/models/pool.obj"));
        modelPlate = AdvancedModelLoader.loadModel((ResourceLocation)new ResourceLocation(ExtraBotany.modid + ":textures/blocks/models/plate.obj"));
        modelTrade = AdvancedModelLoader.loadModel((ResourceLocation)new ResourceLocation(ExtraBotany.modid + ":textures/blocks/models/trade.obj"));
        modelDaisy = AdvancedModelLoader.loadModel((ResourceLocation)new ResourceLocation(ExtraBotany.modid + ":textures/blocks/models/Margaritka.obj"));
        renderGaiaInvReader = RenderingRegistry.getNextAvailableRenderId();
        renderGaiaMainInvReader = RenderingRegistry.getNextAvailableRenderId();
        renderGaiaMainInvChest = RenderingRegistry.getNextAvailableRenderId();
        renderPoolEfir = RenderingRegistry.getNextAvailableRenderId();
        renderTransformater = RenderingRegistry.getNextAvailableRenderId();
        renderEAltar = RenderingRegistry.getNextAvailableRenderId();
        renderBoost = RenderingRegistry.getNextAvailableRenderId();
        renderExtraSpreader = RenderingRegistry.getNextAvailableRenderId();
        renderAutoPool = RenderingRegistry.getNextAvailableRenderId();
        renderExPylon = RenderingRegistry.getNextAvailableRenderId();
        renderPlate = RenderingRegistry.getNextAvailableRenderId();
        renderTrade = RenderingRegistry.getNextAvailableRenderId();
        renderDaisy = RenderingRegistry.getNextAvailableRenderId();
        if (!ConfigHandler.disableShieldDisplay) {
            MinecraftForge.EVENT_BUS.register((Object)new RenderShield());
            FMLCommonHandler.instance().bus().register((Object)new RenderShield());
        }
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new RenderGaiaChest());
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new RenderElfPool());
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new RenderBlockTransformater());
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new RenderPoolEfir());
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new RenderBlockEAltar());
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new RenderBlockBoost());
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new RenderExtraSpreader());
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new RenderExPylon());
        RenderingRegistry.registerBlockHandler((ISimpleBlockRenderingHandler)new RenderBlockDaisy());
        ClientRegistry.bindTileEntitySpecialRenderer(TileGaiaChest.class, (TileEntitySpecialRenderer)new RenderTileGaiaChest());
        ClientRegistry.bindTileEntitySpecialRenderer(TileRelicPlate.class, (TileEntitySpecialRenderer)new RenderTileRelicPlate());
        ClientRegistry.bindTileEntitySpecialRenderer(TileElfPool.class, (TileEntitySpecialRenderer)new RenderTileElfPool());
        ClientRegistry.bindTileEntitySpecialRenderer(TileAuraControler.class, (TileEntitySpecialRenderer)new RenderTileAuraControler());
        ClientRegistry.bindTileEntitySpecialRenderer(TileTransformater.class, (TileEntitySpecialRenderer)new RenderTileTransformer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileBlockPoolEfir.class, (TileEntitySpecialRenderer)new RenderTilePoolEfir());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEAltar.class, (TileEntitySpecialRenderer)new RenderTileEAltar());
        ClientRegistry.bindTileEntitySpecialRenderer(TileBlockBoost.class, (TileEntitySpecialRenderer)new RenderTileManaBoost());
        ClientRegistry.bindTileEntitySpecialRenderer(TileExtraSpreader.class, (TileEntitySpecialRenderer)new RenderTileExtraSpreader());
        ClientRegistry.bindTileEntitySpecialRenderer(TileBlockSpawner.class, (TileEntitySpecialRenderer)new RenderTileBlockSpawner());
        ClientRegistry.bindTileEntitySpecialRenderer(TileExPylon.class, (TileEntitySpecialRenderer)new RenderTileExPylon());
        ClientRegistry.bindTileEntitySpecialRenderer(TileAutoDaisy.class, (TileEntitySpecialRenderer)new RenderTileAutoDaisy());
        MinecraftForgeClient.registerItemRenderer((Item)ModItems.advanceSpark, (IItemRenderer)new RenderTransparentItem());
        RenderingRegistry.registerEntityRenderingHandler(EntityAdvanceSpark.class, (Render)new RenderAdvSpark());
    }
}

