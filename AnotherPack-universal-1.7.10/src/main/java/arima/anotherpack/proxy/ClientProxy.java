package arima.anotherpack.proxy;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.google.common.collect.Lists;

import arima.anotherpack.core.AllItems;
import arima.anotherpack.entity.EntityGhoul;
import arima.anotherpack.entity.RenderEntityGhoul;
import arima.anotherpack.items.ItemRender;
import arima.anotherpack.items.weapons.ScytheRenderer;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy {
	@Override
	public void preInit(FMLPreInitializationEvent e) {
		super.preInit(e);
	}

	@Override
	public void registerRenderers() {
		RenderingRegistry.registerEntityRenderingHandler(EntityGhoul.class, new RenderEntityGhoul(new ModelBiped(), 0.5F));
		MinecraftForgeClient.registerItemRenderer(AllItems.Scythe_1, new ScytheRenderer());
	}

	@Override
	public void registerItem(Item item) {
		MinecraftForgeClient.registerItemRenderer(item, new ItemRender());
	}

	@Override
	public void init(FMLInitializationEvent e) {
		super.init(e);
	}

	@Override
	public void postInit(FMLPostInitializationEvent e) {
		super.postInit(e);
	}
}
