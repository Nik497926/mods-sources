package ru.obvilion.additionpanels;

import appeng.block.AEBaseItemBlock;
import appeng.core.sync.GuiBridge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;
import ru.obvilion.additionpanels.common.container.ContainerInterfaceTerminal;
import ru.obvilion.additionpanels.client.gui.GuiInterfaceTerminal;
import ru.obvilion.additionpanels.common.GuiHandler;
import ru.obvilion.additionpanels.common.block.*;
import ru.obvilion.additionpanels.common.items.BaseItemBlock;
import ru.obvilion.additionpanels.common.items.ItemStorageCell;
import ru.obvilion.additionpanels.common.tileentity.*;

@Mod(modid=AdditionPanels.MODID, name="Addition Panels", version= AdditionPanels.VERSION, dependencies = "required-after:appliedenergistics2@rv3-beta-6")
public class AdditionPanels {
    public static final String MODID = "AdditionPanels";
    public static final String VERSION = "1.0.0";

    public static final CreativeTabs TAB = new CreativeTabs(MODID) {
        @Override
        public Item getTabIconItem() {
            return Items.apple;
        }
    };

    @Mod.Instance(value=MODID)
    public static AdditionPanels instance;


//    public static GuiBridge GUI_INTERFACE_CUSTOM = EnumHelper.addEnum(
//            GuiBridge.class,
//            "GUI_INTERFACE_CUSTOM",
//            new Class[] {Class.class, Class.class, GuiHostType.class, SecurityPermissions.class},
//            new Object[] {ContainerInterface.class, IInterfaceHost.class, GuiHostType.WORLD, SecurityPermissions.BUILD});


    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        try {

            EnumHelper.setFailsafeFieldValue(GuiBridge.class.getDeclaredField("containerClass"), GuiBridge.GUI_INTERFACE_TERMINAL, ContainerInterfaceTerminal.class);

            EnumHelper.setFailsafeFieldValue(GuiBridge.class.getDeclaredField("guiClass"), GuiBridge.GUI_INTERFACE_TERMINAL, GuiInterfaceTerminal.class);
        } catch (Exception e) {
            e.printStackTrace();
        }


        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
    }



    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {




        GameRegistry.registerTileEntity(TileEntityAdvancedMFSU.class, "tileadvmfsu");
        GameRegistry.registerTileEntity(TileEntityQGenerator.class, "tileqgenerator");
        GameRegistry.registerTileEntity(TileEntitySolarPanel.class, "tilesolarpanel");
        GameRegistry.registerTileEntity(TileEntityAeInterface18.class, "tileaeinterface18");
        GameRegistry.registerTileEntity(TileEntityAeInterface27.class, "tilesolarpanel27");
        GameRegistry.registerTileEntity(TileEntityAeInterface36.class, "tilesolarpanel36");


        registerPanel("solarpanelinfernal", 49152,16384, 500000);
        registerPanel("solarpanelsupporting", 147456,49152, 1500000);
        registerPanel("solarpanelassembly", 442368,147456, 4500000);

        registerPanel("solarpanelformorite", 1327104,442368, 15000000);
        registerPanel("solarpanelspatial", 3981312,1327104, 40000000);
        registerPanel("solarpanelsun", 147456,49152, 1500000);
        registerPanel("solarpanelproksima", 442368,147456, 4500000);
        registerPanel("solarpanelglize", 1327104,442368, 15000000);
        registerPanel("solarpanelofthevirgin", 3981312,1327104, 50000000);
        registerPanel("solarpanelross", 11943936,3981312, 120000000);

        registerPanel("solarpanelhoneybees", 147456,49152, 1500000);
        registerPanel("solarpaneluraniumbees", 442368,147456, 4500000);
        registerPanel("solarpanelplatinumbees", 1327104,442368, 15000000);
        registerPanel("solarpanelofredneedbees", 3981312,1327104, 40000000);

        registerPanel("solarpanelforesterlevel1", 147456,49152, 1500000);
        registerPanel("solarpanelforesterlevel2", 442368,147456, 4500000);
        registerPanel("solarpanelforesterlevel3", 1327104,442368, 15000000);
        registerPanel("solarpanelforesterlevel4", 3981312,1327104, 40000000);
        registerPanel("solarpanelmeasurement", 50000000,25000000, 500000000);
        registerPanel("solarpanelblades", 75000000,37500000, 750000000);
        registerPanel("solarpaneluniverse", 100000000,50000000, 1000000000);


        registerQGenerator("quantumgenerator", 100000000);
        registerQGenerator("ultimatebluequantumgenerator", 250000000);
        registerQGenerator("ultimatevioletquantumgenerator", 500000000);
        registerQGenerator("ultimateneonquantumgenerator", 750000000);
        registerQGenerator("ultimateintergalacticquantumgenerator", 1000000000);



        GameRegistry.registerItem(new ItemStorageCell("storagecell2k",2,512,2000), "storagecell2k");
        GameRegistry.registerItem(new ItemStorageCell("storagecell4k",2,512,4000), "storagecell4k");
        GameRegistry.registerItem(new ItemStorageCell("storagecell16k",2,512,15625), "storagecell16k");
        GameRegistry.registerItem(new ItemStorageCell("storagecell64k",2,512,62500), "storagecell64k");
        GameRegistry.registerItem(new ItemStorageCell("storagecell256k",2,512,250000), "storagecell256k");

        GameRegistry.registerBlock(new BlockAeInterface(18), AEBaseItemBlock.class, "aeinterface18");
        GameRegistry.registerBlock(new BlockAeInterface(27), AEBaseItemBlock.class, "aeinterface27");
        GameRegistry.registerBlock(new BlockAeInterface(36), AEBaseItemBlock.class, "aeinterface36");

        GameRegistry.registerBlock(new BlockCobblestoneGenerator("cobblestonegenerator"), BaseItemBlock.class, "cobblestonegenerator");
        GameRegistry.registerTileEntity(TileEntityСobblestoneGenerator.class, "cobblestonegenerator");

        GameRegistry.registerBlock(new BlockFruitGenerator("fruitgenerator"), BaseItemBlock.class, "fruitgenerator");
        GameRegistry.registerTileEntity(TileEntityFruitGenerator.class, "fruitgenerator");

        GameRegistry.registerBlock(new BlockAdvancedMFSU(), BaseItemBlock.class, "advancedmfsu");


//        Collection<TileEntitySolarPanel.PanelData> values = TileEntitySolarPanel.panels.values();
//        for (TileEntitySolarPanel.PanelData panelData : values) {
//            System.out.println("tooltip." + panelData.getPanelName()+ ".day=§4§lГенерация днём: %s EU/t");
//            System.out.println("tooltip." + panelData.getPanelName()+ ".night=§4§lГенерация ночью: %s EU/t");
//        }
//
//       TileEntityQGenerator.generators.forEach((s, integer) -> {
//           System.out.println("tooltip."+s+"=§4§lВыход энергии: %s EU/t макс.");
//       });
    }


    public void registerPanel(String name, int genDay, int getNight, int maxStorage){
        TileEntitySolarPanel.PanelData data = new TileEntitySolarPanel.PanelData(name, genDay, getNight, genDay * 2, maxStorage);
        TileEntitySolarPanel.panels.put(name, data);
        GameRegistry.registerBlock(new BlockSolarPanel(data), BaseItemBlock.class, name);
    }

    public void registerQGenerator(String name, int maxGeneration){
        TileEntityQGenerator.generators.put(name, maxGeneration);
        GameRegistry.registerBlock(new BlockQGenerator(name, maxGeneration), BaseItemBlock.class, name);
    }

}
