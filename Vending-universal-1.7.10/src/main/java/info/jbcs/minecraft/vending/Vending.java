/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.Loader
 *  cpw.mods.fml.common.Mod
 *  cpw.mods.fml.common.Mod$EventHandler
 *  cpw.mods.fml.common.Mod$Instance
 *  cpw.mods.fml.common.SidedProxy
 *  cpw.mods.fml.common.event.FMLInitializationEvent
 *  cpw.mods.fml.common.event.FMLPreInitializationEvent
 *  cpw.mods.fml.common.event.FMLServerStartingEvent
 *  cpw.mods.fml.common.network.FMLEventChannel
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.command.ICommand
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.CraftingManager
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.common.config.Configuration
 */
package info.jbcs.minecraft.vending;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.FMLEventChannel;
import cpw.mods.fml.common.registry.GameRegistry;
import info.jbcs.minecraft.vending.EconomyControl;
import info.jbcs.minecraft.vending.block.BlockVendingMachine;
import info.jbcs.minecraft.vending.command.InfCommand;
import info.jbcs.minecraft.vending.command.ItemPriceCommand;
import info.jbcs.minecraft.vending.gui.GuiAdvancedVendingMachine;
import info.jbcs.minecraft.vending.gui.GuiEcoVendingMachine;
import info.jbcs.minecraft.vending.gui.GuiHandler;
import info.jbcs.minecraft.vending.gui.GuiVendingMachine;
import info.jbcs.minecraft.vending.gui.GuiWrenchVendingMachine;
import info.jbcs.minecraft.vending.inventory.ContainerAdvancedVendingMachine;
import info.jbcs.minecraft.vending.inventory.ContainerEcoVendingMachine;
import info.jbcs.minecraft.vending.inventory.ContainerVendingMachine;
import info.jbcs.minecraft.vending.inventory.DummyContainer;
import info.jbcs.minecraft.vending.item.ItemMetaBlock;
import info.jbcs.minecraft.vending.network.MessagePipeline;
import info.jbcs.minecraft.vending.proxy.CommonProxy;
import info.jbcs.minecraft.vending.tileentity.TileEntityVendingMachine;
import net.minecraft.block.Block;
import net.minecraft.command.ICommand;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;

@Mod(modid="vending", name="MPShop", version="1.2.3")
public class Vending {
    public static final String MOD_ID = "vending";
    public static final String MOD_NAME = "MPShop";
    public static final String VERSION = "1.2.3";
    @Mod.Instance(value="vending")
    public static Vending instance;
    public static FMLEventChannel Channel;
    public static Block blockVendingMachine;
    public static Block blockAdvancedVendingMachine;
    public static Block blockEcoVendingMachine;
    public static Item itemWrench;
    public static GuiHandler guiVending;
    public static GuiHandler guiWrench;
    public static CreativeTabs tabVending;
    static Configuration config;
    public static double priceMultiplier;
    public static final boolean isServer = true;
    public MessagePipeline messagePipeline = new MessagePipeline();
    public static Block[] supports;
    static Object[] reagents;
    @SidedProxy(clientSide="info.jbcs.minecraft.vending.proxy.ClientProxy", serverSide="info.jbcs.minecraft.vending.proxy.CommonProxy")
    public static CommonProxy commonProxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        config = new Configuration(event.getSuggestedConfigurationFile());
        priceMultiplier = config.getFloat("priceMultiplier", "general", 1.0f, 0.0f, 10.0f, "Multiplier for bottom price limit");
        config.load();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register((Object)new EconomyControl.EventLoad());
        commonProxy.registerPackets(this.messagePipeline);
        commonProxy.registerEventHandlers();
        commonProxy.registerRenderers();
        tabVending = config.get("general", "use custom creative tab", true, "Add a new tab to creative mode and put all vending blocks there.").getBoolean(true) ? new CreativeTabs("tabVending"){

            public ItemStack getIconItemStack() {
                return new ItemStack(blockVendingMachine, 1, 4);
            }

            public Item getTabIconItem() {
                return new ItemStack(blockVendingMachine, 1, 4).getItem();
            }
        } : CreativeTabs.tabDecorations;
        blockVendingMachine = new BlockVendingMachine(supports, false, false);
        GameRegistry.registerBlock((Block)blockVendingMachine, ItemMetaBlock.class, (String)"vendingMachine");
        blockAdvancedVendingMachine = new BlockVendingMachine(supports, true, false).setBlockName("vendingMachineAdvanced");
        GameRegistry.registerBlock((Block)blockAdvancedVendingMachine, ItemMetaBlock.class, (String)"vendingMachineAdvanced");
        blockEcoVendingMachine = new BlockVendingMachine(supports, false, true).setBlockName("vendingMachineEco");
        GameRegistry.registerBlock((Block)blockEcoVendingMachine, ItemMetaBlock.class, (String)"vendingMachineEco");
        itemWrench = new Item().setUnlocalizedName("vendingMachineWrench").setCreativeTab(tabVending).setTextureName("Vending:wrench");
        GameRegistry.registerItem((Item)itemWrench, (String)"vendingMachineWrench");
        GameRegistry.registerTileEntity(TileEntityVendingMachine.class, (String)"containerVendingMachine");
        for (int i = 0; i < supports.length; ++i) {
            CraftingManager.getInstance().addRecipe(new ItemStack(blockVendingMachine, 1, i), new Object[]{"XXX", "XGX", "*R*", Character.valueOf('X'), this.getGlass(), Character.valueOf('G'), Items.gold_ingot, Character.valueOf('R'), Items.redstone, Character.valueOf('*'), reagents[i]});
            CraftingManager.getInstance().addRecipe(new ItemStack(blockAdvancedVendingMachine, 1, i), new Object[]{"XXX", "XGX", "*R*", Character.valueOf('X'), this.getGlass(), Character.valueOf('G'), Items.gold_ingot, Character.valueOf('R'), Items.repeater, Character.valueOf('*'), reagents[i]});
            CraftingManager.getInstance().addRecipe(new ItemStack(blockEcoVendingMachine, 1, i), new Object[]{"XXX", "XGX", "*R*", Character.valueOf('X'), this.getGlass(), Character.valueOf('G'), Items.gold_ingot, Character.valueOf('R'), Blocks.dispenser, Character.valueOf('*'), reagents[i]});
        }
        guiVending = new GuiHandler(MOD_ID){

            @Override
            public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
                TileEntity tileEntity = world.getTileEntity(x, y, z);
                if (!(tileEntity instanceof TileEntityVendingMachine)) {
                    return null;
                }
                TileEntityVendingMachine e = (TileEntityVendingMachine)tileEntity;
                if (e.advanced) {
                    return new ContainerAdvancedVendingMachine((IInventory)player.inventory, e);
                }
                if (e.eco) {
                    return new ContainerEcoVendingMachine(player.inventory, e);
                }
                return new ContainerVendingMachine((IInventory)player.inventory, e);
            }

            @Override
            public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
                TileEntity tileEntity = world.getTileEntity(x, y, z);
                if (!(tileEntity instanceof TileEntityVendingMachine)) {
                    return null;
                }
                TileEntityVendingMachine e = (TileEntityVendingMachine)tileEntity;
                if (e.advanced) {
                    return new GuiAdvancedVendingMachine(player.inventory, e);
                }
                if (e.eco) {
                    return new GuiEcoVendingMachine(player.inventory, e);
                }
                return new GuiVendingMachine(player.inventory, e);
            }
        };
        guiWrench = new GuiHandler("wrench"){

            @Override
            public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
                return new DummyContainer();
            }

            @Override
            public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
                return new GuiWrenchVendingMachine(world, x, y, z, player);
            }
        };
        GuiHandler.register(this);
    }

    public static ItemStack getBlockEnder(String itemString) {
        ItemStack item = null;
        try {
            String itemClass = "crazypants.enderio.EnderIO";
            Object obj = Class.forName(itemClass).getField(itemString).get(null);
            if (obj instanceof Block) {
                item = new ItemStack((Block)obj, 1);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return item;
    }

    public static ItemStack getBlockThaum(String itemString, int meta) {
        ItemStack item = null;
        try {
            String itemClass = "thaumcraft.common.config.ConfigBlocks";
            Object obj = Class.forName(itemClass).getField(itemString).get(null);
            if (obj instanceof Block) {
                item = new ItemStack((Block)obj, 1, meta);
            } else if (obj instanceof ItemStack) {
                item = (ItemStack)obj;
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return item;
    }

    private Object getGlass() {
        if (Loader.isModLoaded((String)"Thaumcraft")) {
            return Vending.getBlockThaum("blockCosmeticOpaque", 2);
        }
        if (Loader.isModLoaded((String)"EnderIO")) {
            return Vending.getBlockEnder("blockFusedQuartz");
        }
        return Blocks.glass;
    }

    @Mod.EventHandler
    public void postInit(FMLServerStartingEvent event) {
        event.registerServerCommand((ICommand)new InfCommand());
        event.registerServerCommand((ICommand)new ItemPriceCommand());
    }

    static {
        priceMultiplier = 1.0;
        supports = new Block[]{Blocks.stone, Blocks.cobblestone, Blocks.stonebrick, Blocks.planks, Blocks.crafting_table, Blocks.gravel, Blocks.noteblock, Blocks.sandstone, Blocks.gold_block, Blocks.iron_block, Blocks.brick_block, Blocks.mossy_cobblestone, Blocks.obsidian, Blocks.diamond_block, Blocks.emerald_block, Blocks.lapis_block};
        reagents = new Object[]{Blocks.stone, Blocks.cobblestone, Blocks.stonebrick, Blocks.planks, Blocks.crafting_table, Blocks.gravel, Blocks.noteblock, Blocks.sandstone, Items.gold_ingot, Items.iron_ingot, Blocks.brick_block, Blocks.mossy_cobblestone, Blocks.obsidian, Items.diamond, Items.emerald, Blocks.lapis_block};
    }
}

