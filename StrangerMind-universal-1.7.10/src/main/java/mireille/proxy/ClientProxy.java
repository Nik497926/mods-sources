package mireille.proxy;

import net.minecraftforge.common.*;
import mireille.client.renderer.player.*;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.client.registry.*;
import net.minecraft.client.renderer.tileentity.*;
import mireille.client.renderer.*;
import cpw.mods.fml.common.registry.*;
import net.minecraft.item.*;
import net.minecraft.client.renderer.entity.*;
import mireille.api.*;
import net.minecraftforge.client.*;
import mireille.common.tileentity.*;
import mireille.client.renderer.tileentity.*;
import mireille.core.*;
import net.minecraft.block.*;
import net.minecraft.util.*;
import mireille.client.model.skins.*;
import mireille.client.renderer.item.*;
import mireille.common.entity.*;
import mireille.client.renderer.entity.*;
import mireille.client.handler.*;
import mireille.*;
import cpw.mods.fml.common.*;
import net.minecraft.client.*;

public class ClientProxy extends CommonProxy
{
    public static int modelID;
    public static int modelID2;
    public static String myName;
    
    @Optional.Method(modid = "NotEnoughItems")
    @Override
    public void registerNEIStuff() {
    }
    
    @Override
    public void preInit(final FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register((Object)new ArmorRenderer());
    }
    
    @Override
    public void Init(final FMLInitializationEvent event) {
        this.StreakLoad(event);
        MinecraftForge.EVENT_BUS.register((Object)new ClientRenderHandler());
    }
    
    @Override
    public void postInit(final FMLPostInitializationEvent event) {
    }
    
    @Override
    public void registerRenderers() {
        RenderingRegistry.registerBlockHandler(ClientProxy.modelID = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new PicBlockRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer((Class)TileEntityPicFrame.class, (TileEntitySpecialRenderer)new PicTileRenderer());
        RenderingRegistry.registerBlockHandler(ClientProxy.modelID2 = RenderingRegistry.getNextAvailableRenderId(), (ISimpleBlockRenderingHandler)new BarrierBlockRendere());
        final RenderMeowmere RenderMeowmere = new RenderMeowmere();
        GameRegistry.registerItem((Item)RenderMeowmere, "RenderMeowmere");
        RenderingRegistry.registerEntityRenderingHandler((Class)Meowmere_prog.class, (Render)new RenderSnowball((Item)RenderMeowmere));
        MinecraftForgeClient.registerItemRenderer(ModItems.Smoke, (IItemRenderer)new ItemRender());
        ClientRegistry.bindTileEntitySpecialRenderer((Class)TileSkinWorkbench.class, (TileEntitySpecialRenderer)new RenderSkinWorkbench());
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock((Block)ModBlocks.SkinWorkbench), (IItemRenderer)new RenderItemSkinWorkbench());
        MinecraftForgeClient.registerItemRenderer(ModItems.Crescent_rose_skin, (IItemRenderer)new RenderScythe(new ModelSkythe(), new ResourceLocation("mireille:model/skins/crescent_rose.png")));
        MinecraftForgeClient.registerItemRenderer(ModItems.Tenjiry_skin, (IItemRenderer)new RenderTenjiry(new ModelTenjiry(), new ResourceLocation("mireille:model/skins/tenjiry.png")));
        MinecraftForgeClient.registerItemRenderer(ModItems.Shinju_skin, (IItemRenderer)new RenderShinju(new ModelShinju(), new ResourceLocation("mireille:model/skins/shinju.png")));
        MinecraftForgeClient.registerItemRenderer(ModItems.ShikamaDodji_skin, (IItemRenderer)new RenderShikamaDodji(new ModelShikamaDodji(), new ResourceLocation("mireille:model/skins/shikama_dodji.png")));
        MinecraftForgeClient.registerItemRenderer(ModItems.VampireKiss_skin, (IItemRenderer)new RenderVampireKiss(new ModelVampireKiss(), new ResourceLocation("mireille:model/skins/vampire_kiss.png")));
        MinecraftForgeClient.registerItemRenderer(ModItems.Vis_molot_skin, (IItemRenderer)new RenderVisMolot(new ModelMolot(), new ResourceLocation("mireille:model/skins/vis_molot.png")));
        MinecraftForgeClient.registerItemRenderer(ModItems.Sun_molot_skin, (IItemRenderer)new RenderSunMolot(new ModelMolot(), new ResourceLocation("mireille:model/skins/sun_molot.png")));
        MinecraftForgeClient.registerItemRenderer(ModItems.Ice_molot_skin, (IItemRenderer)new RenderIceMolot(new ModelMolot(), new ResourceLocation("mireille:model/skins/ice_molot.png")));
        MinecraftForgeClient.registerItemRenderer(ModItems.Live_molot_skin, (IItemRenderer)new RenderLiveMolot(new ModelMolot(), new ResourceLocation("mireille:model/skins/live_molot.png")));
        MinecraftForgeClient.registerItemRenderer(ModItems.Dark_molot_skin, (IItemRenderer)new RenderDarkMolot(new ModelMolot(), new ResourceLocation("mireille:model/skins/dark_molot.png")));
        MinecraftForgeClient.registerItemRenderer(ModItems.Moon_light_skin, (IItemRenderer)new RenderMoonLight(new ModelVampireKiss(), new ResourceLocation("mireille:model/skins/moon_light.png")));
        MinecraftForgeClient.registerItemRenderer((Item)ModItems.Tron_head_yellow, (IItemRenderer)new RenderArmor(ModItems.Tron_head_yellow));
        MinecraftForgeClient.registerItemRenderer((Item)ModItems.Tron_chest_yellow, (IItemRenderer)new RenderArmor(ModItems.Tron_chest_yellow));
        MinecraftForgeClient.registerItemRenderer((Item)ModItems.Tron_leggs_yellow, (IItemRenderer)new RenderArmor(ModItems.Tron_leggs_yellow));
        MinecraftForgeClient.registerItemRenderer((Item)ModItems.Tron_boots_yellow, (IItemRenderer)new RenderArmor(ModItems.Tron_boots_yellow));
        MinecraftForgeClient.registerItemRenderer((Item)ModItems.Tron_head_blue, (IItemRenderer)new RenderArmor(ModItems.Tron_head_blue));
        MinecraftForgeClient.registerItemRenderer((Item)ModItems.Tron_chest_blue, (IItemRenderer)new RenderArmor(ModItems.Tron_chest_blue));
        MinecraftForgeClient.registerItemRenderer((Item)ModItems.Tron_leggs_blue, (IItemRenderer)new RenderArmor(ModItems.Tron_leggs_blue));
        MinecraftForgeClient.registerItemRenderer((Item)ModItems.Tron_boots_blue, (IItemRenderer)new RenderArmor(ModItems.Tron_boots_blue));
        MinecraftForgeClient.registerItemRenderer((Item)ModItems.Tron_head_red, (IItemRenderer)new RenderArmor(ModItems.Tron_head_red));
        MinecraftForgeClient.registerItemRenderer((Item)ModItems.Tron_chest_red, (IItemRenderer)new RenderArmor(ModItems.Tron_chest_red));
        MinecraftForgeClient.registerItemRenderer((Item)ModItems.Tron_leggs_red, (IItemRenderer)new RenderArmor(ModItems.Tron_leggs_red));
        MinecraftForgeClient.registerItemRenderer((Item)ModItems.Tron_boots_red, (IItemRenderer)new RenderArmor(ModItems.Tron_boots_red));
        MinecraftForgeClient.registerItemRenderer((Item)ModItems.Tron_head_green, (IItemRenderer)new RenderArmor(ModItems.Tron_head_green));
        MinecraftForgeClient.registerItemRenderer((Item)ModItems.Tron_chest_green, (IItemRenderer)new RenderArmor(ModItems.Tron_chest_green));
        MinecraftForgeClient.registerItemRenderer((Item)ModItems.Tron_leggs_green, (IItemRenderer)new RenderArmor(ModItems.Tron_leggs_green));
        MinecraftForgeClient.registerItemRenderer((Item)ModItems.Tron_boots_green, (IItemRenderer)new RenderArmor(ModItems.Tron_boots_green));
        MinecraftForgeClient.registerItemRenderer((Item)ModItems.Tron_head_purple, (IItemRenderer)new RenderArmor(ModItems.Tron_head_purple));
        MinecraftForgeClient.registerItemRenderer((Item)ModItems.Tron_chest_purple, (IItemRenderer)new RenderArmor(ModItems.Tron_chest_purple));
        MinecraftForgeClient.registerItemRenderer((Item)ModItems.Tron_leggs_purple, (IItemRenderer)new RenderArmor(ModItems.Tron_leggs_purple));
        MinecraftForgeClient.registerItemRenderer((Item)ModItems.Tron_boots_purple, (IItemRenderer)new RenderArmor(ModItems.Tron_boots_purple));
        MinecraftForgeClient.registerItemRenderer((Item)ModItems.CrystalArmorHead, (IItemRenderer)new RenderArmor(ModItems.CrystalArmorHead));
        MinecraftForgeClient.registerItemRenderer((Item)ModItems.CrystalArmorChest, (IItemRenderer)new RenderArmor(ModItems.CrystalArmorChest));
        MinecraftForgeClient.registerItemRenderer((Item)ModItems.CrystalArmorLegs, (IItemRenderer)new RenderArmor(ModItems.CrystalArmorLegs));
        MinecraftForgeClient.registerItemRenderer((Item)ModItems.CrystalArmorBoots, (IItemRenderer)new RenderArmor(ModItems.CrystalArmorBoots));
        MinecraftForgeClient.registerItemRenderer((Item)ModItems.DevilArmorHead, (IItemRenderer)new RenderArmor(ModItems.DevilArmorHead));
        MinecraftForgeClient.registerItemRenderer((Item)ModItems.DevilArmorChest, (IItemRenderer)new RenderArmor(ModItems.DevilArmorChest));
        MinecraftForgeClient.registerItemRenderer((Item)ModItems.DevilArmorLegs, (IItemRenderer)new RenderArmor(ModItems.DevilArmorLegs));
        MinecraftForgeClient.registerItemRenderer((Item)ModItems.DevilArmorBoots, (IItemRenderer)new RenderArmor(ModItems.DevilArmorBoots));
        MinecraftForgeClient.registerItemRenderer((Item)ModItems.CreepArmorHead, (IItemRenderer)new RenderArmor(ModItems.CreepArmorHead));
        MinecraftForgeClient.registerItemRenderer((Item)ModItems.CreepArmorChest, (IItemRenderer)new RenderArmor(ModItems.CreepArmorChest));
        MinecraftForgeClient.registerItemRenderer((Item)ModItems.CreepArmorLegs, (IItemRenderer)new RenderArmor(ModItems.CreepArmorLegs));
        MinecraftForgeClient.registerItemRenderer((Item)ModItems.CreepArmorBoots, (IItemRenderer)new RenderArmor(ModItems.CreepArmorBoots));
        MinecraftForgeClient.registerItemRenderer((Item)ModItems.FairyArmorHead, (IItemRenderer)new RenderArmor(ModItems.FairyArmorHead));
        MinecraftForgeClient.registerItemRenderer((Item)ModItems.FairyArmorChest, (IItemRenderer)new RenderArmor(ModItems.FairyArmorChest));
        MinecraftForgeClient.registerItemRenderer((Item)ModItems.FairyArmorLegs, (IItemRenderer)new RenderArmor(ModItems.FairyArmorLegs));
        MinecraftForgeClient.registerItemRenderer((Item)ModItems.FairyArmorBoots, (IItemRenderer)new RenderArmor(ModItems.FairyArmorBoots));
    }
    
    public void StreakLoad(final FMLInitializationEvent event) {
        RenderingRegistry.registerEntityRenderingHandler((Class)EntityStreak.class, (Render)new RenderStreak());
        StrangerMind.tickHandlerClient = new StreakTickHandlerClient();
        FMLCommonHandler.instance().bus().register((Object)StrangerMind.tickHandlerClient);
    }
    
    static {
        ClientProxy.myName = Minecraft.getMinecraft().getSession().getUsername();
    }
}
