package arima.anotherpack.core;

import arima.anotherpack.entity.EntityGhoul;
import arima.anotherpack.proxy.CommonProxy;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;

@Mod(modid = Main.MODID, name = Main.MODNAME, version = Main.VERSION)
public class Main {
	@Instance("AnotherPack2")
	public static Main instance;

	@SidedProxy(clientSide = "arima.anotherpack.proxy.ClientProxy", serverSide = "arima.anotherpack.proxy.CommonProxy")
	public static CommonProxy proxy;

	public static final String MODID = "AnotherPack2";
	public static final String VERSION = "1.1.0";
	public static final String MODNAME = "Arima - AnotherPack 2";

	public static final CreativeTabs tabAP2WoodBlocks = new CreativeTabs("tabAP2WoodBlocks") {
		public Item getTabIconItem() {
			return Item.getItemFromBlock(AllBlocks.Wood);
		}
	};
	public static final CreativeTabs tabAP2StoneBlocks = new CreativeTabs("tabAP2StoneBlocks") {
		public Item getTabIconItem() {
			return Item.getItemFromBlock(AllBlocks.Stone);
		}
	};
	public static final CreativeTabs tabAP2DesignBlocks = new CreativeTabs("tabAP2DesignBlocks") {
		public Item getTabIconItem() {
			return Item.getItemFromBlock(AllBlocks.BlocksGlass2);
		}
	};
	public static final CreativeTabs tabAPIronBlocks = new CreativeTabs("tabAPIronBlocks") {
		public Item getTabIconItem() {
			return Item.getItemFromBlock(AllBlocks.Iron);
		}
	};
	public static final CreativeTabs tabAP2Items = new CreativeTabs("tabAP2Items") {
		public Item getTabIconItem() {
			return AllItems.Book4;
		}
	};

	public static final CreativeTabs tabAP2Lamps = new CreativeTabs("tabAP2Lamps") {
		public Item getTabIconItem() {
			return Item.getItemFromBlock(AllBlocks.Lamp);
		}
	};

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.preInit(event);
    }

	@EventHandler
	public void preLoad(FMLPreInitializationEvent event) {
		AllBlocks.registerBlocks();
		AllItems.registerItems();
		proxy.init(null);
		proxy.registerRenderers();
		registerEntity(EntityGhoul.class, "Ghoul", 0x00FFFF, 0x00008B);
		EntityRegistry.addSpawn(EntityGhoul.class, 100, 4, 4, EnumCreatureType.monster);
	}

	@EventHandler
	public static void registerEntity(Class entityClass, String name, int primaryColor, int secondaryColor) {
		int entityID = EntityRegistry.findGlobalUniqueEntityId();
		long seed = name.hashCode();

		EntityRegistry.registerGlobalEntityID(entityClass, name, entityID);
		EntityRegistry.registerModEntity(entityClass, name, entityID, instance, 64, 1, true);
		EntityList.entityEggs.put(Integer.valueOf(entityID),
				new EntityList.EntityEggInfo(entityID, primaryColor, secondaryColor));
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}
	public void log(Object arg0) {
		System.out.println("AnotherPack: " + arg0);
	}
}
