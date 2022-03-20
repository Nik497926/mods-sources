package ua.limon4ik;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import ua.limon4ik.proxy.CommonProxy;

@Mod(modid = LegendaryHorizons.MODID,version = LegendaryHorizons.VERSION/*,dependencies = "required-after:Thaumcraft@[4.2.3.5,);required-after:thaumicenergistics;required-after:Botania;"*/)
public class LegendaryHorizons {
    public final static String MODID="LegendaryHorizons",VERSION="1.1";
    @SidedProxy(clientSide = "ua.limon4ik.proxy.ClientProxy", serverSide = "ua.limon4ik.proxy.CommonProxy")
    public static CommonProxy proxy;
    @Mod.Instance(MODID)
    public static LegendaryHorizons instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);

    }
    @Mod.EventHandler
    public static void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }
    public static CreativeTabs tab = new CreativeTabs("legendaryHorizons") {
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem() {
            return new ItemBlock(Blocks.lava);
        }

    };
}
