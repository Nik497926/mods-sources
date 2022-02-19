package com.gamerforea.thaumcraft;

import cpw.mods.fml.common.Loader;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public final class EventConfig
{
	public static boolean registerFluxGas = false;

	static
	{
		init();
	}

	public static void init()
	{
		try
		{
			File folder = new File(Loader.instance().getConfigDir(), "Events");
			folder.mkdirs();
			Configuration cfg = new Configuration(new File(folder, "ThaumCraft.cfg"));
			String c = Configuration.CATEGORY_GENERAL;
			registerFluxGas = cfg.getBoolean("registerFluxGas", c, registerFluxGas, "Регистрация FluxGas как жидкости (защита от краша с раздатчиком)");
			cfg.save();
		}
		catch (Throwable throwable)
		{
			System.err.println("Failed load config. Use default values.");
			throwable.printStackTrace();
		}
	}
}
