package com.gamerforea.thaumcraft;

import static net.minecraftforge.common.config.Configuration.CATEGORY_GENERAL;

import com.gamerforea.eventhelper.config.Config;
import com.gamerforea.eventhelper.config.ConfigBoolean;
import com.gamerforea.eventhelper.config.ConfigFloat;
import com.gamerforea.eventhelper.config.ConfigInt;
import com.gamerforea.eventhelper.config.ConfigItemBlockList;
import com.gamerforea.eventhelper.config.ConfigString;
import com.gamerforea.eventhelper.config.ConfigUtils;
import com.gamerforea.eventhelper.config.ItemBlockList;

import net.minecraftforge.common.config.Configuration;

@Config(name = "ThaumCraft")
public final class EventConfig
{
	private static final String CATEGORY_BLACKLISTS = "blacklists";
	private static final String CATEGORY_OTHER = "other";
	private static final String CATEGORY_OTHER_POTIONS = CATEGORY_OTHER + Configuration.CATEGORY_SPLITTER + "potions";
	private static final String CATEGORY_OTHER_PERMISSIONS = CATEGORY_OTHER + Configuration.CATEGORY_SPLITTER + "permissions";
	private static final String CATEGORY_PERFORMANCE = "performance";

	@ConfigItemBlockList(name = "golemCoreUse",
						 category = CATEGORY_BLACKLISTS,
						 comment = "Чёрный список блоков и предметов для Сердца голема: Использование",
						 oldName = "golemCoreUseBlackList",
						 oldCategory = CATEGORY_GENERAL)
	public static final ItemBlockList golemCoreUseBlackList = new ItemBlockList();

	@ConfigItemBlockList(name = "excavationFocus",
						 category = CATEGORY_BLACKLISTS,
						 comment = "Чёрный список блоков для Набалдашника: Разрушение",
						 oldName = "excavationFocusBlackList",
						 oldCategory = CATEGORY_GENERAL)
	public static final ItemBlockList excavationFocusBlackList = new ItemBlockList();

	@ConfigItemBlockList(name = "runicMatrix",
						 category = CATEGORY_BLACKLISTS,
						 comment = "Чёрный список блоков и предметов для Рунической матрицы",
						 oldName = "runicMatrixBlackList",
						 oldCategory = CATEGORY_GENERAL)
	public static final ItemBlockList runicMatrixBlackList = new ItemBlockList();

	@ConfigBoolean(category = CATEGORY_OTHER, comment = "Включить BlockEldritchNothing", oldCategory = CATEGORY_GENERAL)
	public static boolean blockEldritchNothing = true;

	@ConfigBoolean(category = CATEGORY_OTHER, comment = "Бессмертие для големов", oldCategory = CATEGORY_GENERAL)
	public static boolean invincibleGolems = true;

	@ConfigBoolean(category = CATEGORY_OTHER,
				   comment = "Сингулярный крушитель может использоваться только игроками (защита от дюпа с OpenComputers)",
				   oldCategory = CATEGORY_GENERAL)
	public static boolean primalCrusherPlayerOnly = true;

	@ConfigBoolean(category = CATEGORY_OTHER,
				   comment = "Генерация во всех мирах (кроме Ада и Края)",
				   oldCategory = CATEGORY_GENERAL)
	public static boolean allWorldGen = false;

	@ConfigBoolean(category = CATEGORY_OTHER,
				   comment = "Включить Набалдашник: Укрепление",
				   oldCategory = CATEGORY_GENERAL)
	public static boolean enableFocusWarding = true;

	@ConfigBoolean(category = CATEGORY_OTHER,
				   comment = "Включить Набалдашник: Равноценный обмен",
				   oldCategory = CATEGORY_GENERAL)
	public static boolean enableFocusTrade = true;

	@ConfigBoolean(category = CATEGORY_OTHER,
				   comment = "Добавлять индвидуальные имена для мобов-чемпионов",
				   oldCategory = CATEGORY_GENERAL)
	public static boolean customChampionsNaming = false;

	@ConfigBoolean(category = CATEGORY_OTHER,
				   comment = "Регистрация FluxGas как жидкости (защита от краша с раздатчиком) (аналогичную опцию нужно включить в клиенте)",
				   oldCategory = CATEGORY_GENERAL)
	public static boolean registerFluxGas = false;

	@ConfigBoolean(category = CATEGORY_OTHER,
				   comment = "Расширенный фикс дюпа воды с Тиглем",
				   oldCategory = CATEGORY_GENERAL)
	public static boolean advCrucibleDupeFix = false;

	@ConfigBoolean(category = CATEGORY_OTHER,
				   comment = "Eldritch Obelisk Placer только для творческого режима",
				   oldCategory = CATEGORY_GENERAL)
	public static boolean obeliskPlacerCreativeOnly = false;

	@ConfigInt(category = CATEGORY_OTHER,
			   comment = "Пропуск тиков для Полотёра порчи (0 - выключено)",
			   oldCategory = CATEGORY_GENERAL,
			   min = 0)
	public static int fluxScrubberSkipTicks = 0;

	@ConfigInt(category = CATEGORY_OTHER,
			   comment = "Период автоматического перестроения связей Вис-канала (0 - выключено)",
			   oldCategory = CATEGORY_GENERAL,
			   min = 0)
	public static int visRelayUpdatePeriod = 0;

	@ConfigBoolean(category = CATEGORY_OTHER,
				   comment = "Запрет взаимодействия Тигля с предметами посторонних игроков",
				   oldCategory = CATEGORY_GENERAL)
	public static boolean protectCrucible = false;

	@ConfigBoolean(category = CATEGORY_OTHER,
				   comment = "Проверять корректность использования Таумометра (защита от мгновенного исследования всех предметов в игре) (может слегка снизить точность 'прицеливания' Таумометром) (может нарушить работу Таумометра)",
				   oldCategory = CATEGORY_GENERAL)
	public static boolean validateThaumometer = false;

	@ConfigInt(category = CATEGORY_OTHER,
			   comment = "Кулдаун для Таумометра в тиках (0 - нет кулдауна)",
			   oldCategory = CATEGORY_GENERAL,
			   min = 0)
	public static int thaumometerCooldown = 23;

	@ConfigBoolean(category = CATEGORY_OTHER, comment = "Включить Порчу в бутылке", oldCategory = CATEGORY_GENERAL)
	public static boolean enableBottleTaint = true;

	//Pa4ok
	@ConfigBoolean(category = CATEGORY_OTHER, comment = "[Pa4ok] Полностью выключить дроп аспектов с мобов/игроков")
	public static boolean disableAspectLivingDrop = false;
	//
	
	@ConfigBoolean(category = CATEGORY_OTHER,
				   comment = "Включить дроп аспектов при смерти игрока от Жидкой смерти",
				   oldCategory = CATEGORY_GENERAL)
	public static boolean enablePlayerFluidDeathAspectDrop = true;

	@ConfigFloat(category = CATEGORY_OTHER,
				 comment = "Шанс дропа аспектов при смерти моба от Жидкой смерти",
				 oldCategory = CATEGORY_GENERAL,
				 min = 0,
				 max = 1)
	public static float fluidDeathAspectDropChance = 1;

	@ConfigBoolean(category = CATEGORY_OTHER,
				   comment = "Исправить дюп жезлов при использовании некоторых фокусов (небезопасно)",
				   oldCategory = CATEGORY_GENERAL)
	public static boolean fixFocusDupe = false;

	@ConfigBoolean(category = CATEGORY_OTHER,
				   comment = "Проверять subID кристаллов при использовании Рунической матрицы",
				   oldCategory = CATEGORY_GENERAL)
	public static boolean infusionStrictShardCheck = false;

	@ConfigBoolean(category = CATEGORY_OTHER,
				   comment = "Обновлять сети Вис-каналов при загрузке чанков (ВНИМАНИЕ: может снизить производительность)",
				   oldCategory = CATEGORY_GENERAL)
	public static boolean updateVisRelayNetworkOnChunkLoad = false;

	@ConfigBoolean(category = CATEGORY_OTHER,
				   comment = "Защита от разрушения лабиринта",
				   oldCategory = CATEGORY_GENERAL)
	public static boolean preventOuterLandsDestruction = true;

	@ConfigBoolean(category = CATEGORY_OTHER,
				   comment = "Отключение вызова метода EntityArrow#onUpdate из EntityPrimalArrow#onUpdate (может исправить нанесение урона в приватах) (небезопасно)")
	public static boolean disableSuperPrimalArrowUpdate = false;

	@ConfigBoolean(name = "unhunger",
				   category = CATEGORY_OTHER_POTIONS,
				   comment = "Включить эффект Странный голод",
				   oldName = "enableUnhunger",
				   oldCategory = CATEGORY_GENERAL)
	public static boolean enableUnhunger = true;

	@ConfigBoolean(name = "deathGase",
				   category = CATEGORY_OTHER_POTIONS,
				   comment = "Включить эффект Смертельный взгляд",
				   oldName = "enableDeathGase",
				   oldCategory = CATEGORY_GENERAL)
	public static boolean enableDeathGase = true;

	@ConfigBoolean(name = "blurredVision",
				   category = CATEGORY_OTHER_POTIONS,
				   comment = "Включить эффект Затуманенное зрение",
				   oldName = "enableBlurredVision",
				   oldCategory = CATEGORY_GENERAL)
	public static boolean enableBlurredVision = true;

	@ConfigBoolean(name = "visExhaust",
				   category = CATEGORY_OTHER_POTIONS,
				   comment = "Включить эффект Магическое истощение",
				   oldName = "enableVisExhaust",
				   oldCategory = CATEGORY_GENERAL)
	public static boolean enableVisExhaust = true;

	@ConfigBoolean(name = "infectiousVisExhaust",
				   category = CATEGORY_OTHER_POTIONS,
				   comment = "Включить эффект Магическая зараза",
				   oldName = "enableInfectiousVisExhaust",
				   oldCategory = CATEGORY_GENERAL)
	public static boolean enableInfectiousVisExhaust = true;

	@ConfigBoolean(name = "thaumarhia",
				   category = CATEGORY_OTHER_POTIONS,
				   comment = "Включить эффект Таумария",
				   oldName = "enableThaumarhia",
				   oldCategory = CATEGORY_GENERAL)
	public static boolean enableThaumarhia = true;

	@ConfigBoolean(name = "sunScorned",
				   category = CATEGORY_OTHER_POTIONS,
				   comment = "Включить эффект Солнечная болезнь",
				   oldName = "enableSunScorned",
				   oldCategory = CATEGORY_GENERAL)
	public static boolean enableSunScorned = true;

	@ConfigString(name = "golemRemove",
				  category = CATEGORY_OTHER_PERMISSIONS,
				  comment = "Permission для удаления чужих големов",
				  oldName = "golemRemovePermission",
				  oldCategory = CATEGORY_GENERAL)
	public static String golemRemovePermission = "thaumcraft.golemremove";

	@ConfigBoolean(category = CATEGORY_PERFORMANCE,
				   comment = "Оптимизировать AI големов (небезопасно)",
				   oldCategory = CATEGORY_GENERAL)
	public static boolean golemAiOptimize = false;

	@ConfigBoolean(category = CATEGORY_PERFORMANCE,
				   comment = "Оптимизировать поиск аспектов для предметов (небезопасно)",
				   oldCategory = CATEGORY_GENERAL)
	public static boolean itemAspectMapOptimize = false;
	
	//Pa4ok
	@ConfigBoolean(category = CATEGORY_OTHER_POTIONS, comment = "[Pa4ok] Магический боб может выдавать только ванильные эффекты (защита от багов с эффектами из модов)")
	public static boolean onlyVanilaPotionsInMagicBean = true;
	
	@ConfigBoolean(category = CATEGORY_PERFORMANCE, comment = "[Pa4ok] Удалять предметы без аспектов брошенные в алхимическую печь")
	public static boolean deleteNotValidItemsFromAlchemyFurnace = true;
	
	@ConfigInt(category = CATEGORY_PERFORMANCE, comment = "[Pa4ok] Дополнительная задержка в тиках между расплавлением предметов в алхимической печи", min = 0)
	public static int alchemyFurnaceDelay = 0;
	
	@ConfigInt(category = CATEGORY_PERFORMANCE, comment = "[Pa4ok] Задержка в тиках между обращениями каждого EntityItem к алхимической печки", min = 0)
	public static int alchemyFurnaceItemInteractDelay = 20;
	//

	public static void init()
	{
		ConfigUtils.readConfig(EventConfig.class);
	}

	static
	{
		init();
	}
}
