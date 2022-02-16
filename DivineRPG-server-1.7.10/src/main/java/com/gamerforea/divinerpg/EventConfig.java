package com.gamerforea.divinerpg;

import com.gamerforea.eventhelper.util.FastUtils;
import net.minecraftforge.common.config.Configuration;

public final class EventConfig
{
	public static boolean teleportGenPrivateCheck = false;
	public static boolean portalGeneration = true;
	public static boolean portalStoneFrame = false;
	public static boolean portalOwnerOnly = false;
	public static boolean safeWorldSave = false;
	public static boolean vetherInvSwapper = true;
	public static boolean nightmareBed = true;

	public static void load()
	{
		try
		{
			Configuration cfg = FastUtils.getConfig("DivineRPG");
			String c = Configuration.CATEGORY_GENERAL;

			teleportGenPrivateCheck = cfg.getBoolean("teleportGenPrivateCheck", c, teleportGenPrivateCheck, "Проверка наличия привата при генерации порталов");
			portalGeneration = cfg.getBoolean("portalGeneration", c, portalGeneration, "Генерация порталов");
			portalStoneFrame = cfg.getBoolean("portalStoneFrame", c, portalStoneFrame, "Каменная рамка генерируемых порталов");
			portalOwnerOnly = cfg.getBoolean("portalOwnerOnly", c, portalOwnerOnly, "Порталы могут использоваться только участниками и владельцами региона");
			safeWorldSave = cfg.getBoolean("safeWorldSave", c, safeWorldSave, "Использование 'безопасных' имён при сохрании миров (решает проблему, когда у одного мира есть несколько разных экземпляров) (замена, например, 'Apalachia' на 'DIM56')");
			vetherInvSwapper = cfg.getBoolean("vetherInvSwapper", c, vetherInvSwapper, "Отдельный инвентарь в Везеи");
			nightmareBed = cfg.getBoolean("nightmareBed", c, nightmareBed, "Кровать кошмаров");

			cfg.save();
		}
		catch (Throwable throwable)
		{
			System.err.println("Failed load config. Use default values.");
			throwable.printStackTrace();
		}
	}

	static
	{
		load();
	}
}
