package mireille;

import mireille.client.handler.*;
import mireille.proxy.*;
import net.minecraft.creativetab.*;
import net.minecraftforge.common.*;
import mireille.network.*;
import mireille.handler.*;
import cpw.mods.fml.common.network.*;
import mireille.common.items.*;
import mireille.core.*;
import cpw.mods.fml.common.registry.*;
import cpw.mods.fml.common.*;
import cpw.mods.fml.common.event.*;
import net.minecraft.util.*;
import net.minecraft.item.*;

@Mod(modid = "StrangerMind", name = "StrangerMind", version = "1.48")
public class StrangerMind
{
    public static final String ID = "StrangerMind";
    public static final String NAME = "StrangerMind";
    public static final String VERSION = "1.48";
    public static String ConfigDirectory;
    public static boolean CosmeticArmor;
    public static boolean Baubles;
    public static int streakTime;
    public static int sprintTrail;
    public static StreakTickHandlerClient tickHandlerClient;
    public static ModOreGenerator oreGenerator;
    @Mod.Instance("StrangerMind")
    public static StrangerMind instance;
    @SidedProxy(clientSide = "mireille.proxy.ClientProxy", serverSide = "mireille.proxy.CommonProxy")
    public static CommonProxy proxy;
    public static final CreativeTabs StrangerMind;
    
    @Mod.EventHandler
    public void preInit(final FMLPreInitializationEvent event) {
        ModConfig.registerConfig(mireille.StrangerMind.ConfigDirectory = event.getModConfigurationDirectory().getAbsolutePath() + "/StrangerMind/");
        mireille.StrangerMind.proxy.preInit(event);
        ModBlocks.registerBlocks();
        ModItems.registerItems();
        ModTileEntity.registerTileEntity();
        ModCases.registerBoxes();
    }
    
    @Mod.EventHandler
    public void Init(final FMLInitializationEvent event) {
        mireille.StrangerMind.proxy.Init(event);
        mireille.StrangerMind.proxy.registerRenderers();
        mireille.StrangerMind.proxy.registerNEIStuff();
        MinecraftForge.EVENT_BUS.register((Object)new EventHandlerConfig());
        FMLCommonHandler.instance().bus().register((Object)new EventHandlerConfig());
        NetworkHandler.init("StrangerMind");
        NetworkRegistry.INSTANCE.registerGuiHandler((Object)this, (IGuiHandler)new GuiHandler());
    }
    
    @Mod.EventHandler
    public void postInit(final FMLPostInitializationEvent event) {
        mireille.StrangerMind.proxy.postInit(event);
        CraftingManager.registerSunRecipes(mireille.StrangerMind.ConfigDirectory);
        ModItemsImport.importItems();
        if (Loader.isModLoaded("cosmeticarmorreworked")) {
            mireille.StrangerMind.CosmeticArmor = true;
        }
        if (Loader.isModLoaded("Baubles")) {
            mireille.StrangerMind.Baubles = true;
        }
        GameRegistry.registerWorldGenerator((IWorldGenerator)mireille.StrangerMind.oreGenerator, 0);
    }
    
    @Mod.EventHandler
    public void serverStarting(final FMLServerStartingEvent event) {
        mireille.StrangerMind.proxy.serverStarting(event);
    }
    
    public static String resource(final String id) {
        return StatCollector.translateToLocal(id);
    }
    
    static {
        mireille.StrangerMind.CosmeticArmor = false;
        mireille.StrangerMind.Baubles = false;
        mireille.StrangerMind.streakTime = 100;
        mireille.StrangerMind.sprintTrail = 1;
        mireille.StrangerMind.oreGenerator = new ModOreGenerator();
        StrangerMind = new CreativeTabs("StrangerMind") {
            public Item getTabIconItem() {
                return ModItems.Demonic_ruby;
            }
        };
    }
}
