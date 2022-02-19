package thaumcraft.api;

import com.gamerforea.thaumcraft.EventConfig;
import com.gamerforea.thaumcraft.ObjectTagsCache;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.*;
import thaumcraft.api.internal.DummyInternalMethodHandler;
import thaumcraft.api.internal.IInternalMethodHandler;
import thaumcraft.api.internal.WeightedRandomLoot;
import thaumcraft.api.research.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ThaumcraftApi
{
	public static ToolMaterial toolMatThaumium = EnumHelper.addToolMaterial("THAUMIUM", 3, 400, 7.0F, 2.0F, 22);
	public static ToolMaterial toolMatVoid = EnumHelper.addToolMaterial("VOID", 4, 150, 8.0F, 3.0F, 10);
	public static ToolMaterial toolMatElemental = EnumHelper.addToolMaterial("THAUMIUM_ELEMENTAL", 3, 1500, 10.0F, 3.0F, 18);
	public static ArmorMaterial armorMatThaumium = EnumHelper.addArmorMaterial("THAUMIUM", 25, new int[] { 2, 6, 5, 2 }, 25);
	public static ArmorMaterial armorMatSpecial = EnumHelper.addArmorMaterial("SPECIAL", 25, new int[] { 1, 3, 2, 1 }, 25);
	public static ArmorMaterial armorMatThaumiumFortress = EnumHelper.addArmorMaterial("FORTRESS", 40, new int[] { 3, 7, 6, 3 }, 25);
	public static ArmorMaterial armorMatVoid = EnumHelper.addArmorMaterial("VOID", 10, new int[] { 3, 7, 6, 3 }, 10);
	public static ArmorMaterial armorMatVoidFortress = EnumHelper.addArmorMaterial("VOIDFORTRESS", 18, new int[] { 4, 8, 7, 4 }, 10);
	public static int enchantFrugal;
	public static int enchantPotency;
	public static int enchantWandFortune;
	public static int enchantHaste;
	public static int enchantRepair;
	public static ArrayList<Block> portableHoleBlackList = new ArrayList<>();
	public static IInternalMethodHandler internalMethods = new DummyInternalMethodHandler();
	public static ArrayList<IScanEventHandler> scanEventhandlers = new ArrayList<>();
	public static ArrayList<ThaumcraftApi.EntityTags> scanEntities = new ArrayList<>();
	private static ArrayList craftingRecipes = new ArrayList();
	private static HashMap<Object, ItemStack> smeltingBonus = new HashMap<>();
	private static HashMap<int[], Object[]> keyCache = new HashMap<>();
	public static ConcurrentHashMap<List, AspectList> objectTags = new ConcurrentHashMap<>();
	public static ConcurrentHashMap<List, int[]> groupedObjectTags = new ConcurrentHashMap<>();
	private static HashMap<Object, Integer> warpMap = new HashMap<>();

	public static void registerScanEventhandler(IScanEventHandler scanEventHandler)
	{
		scanEventhandlers.add(scanEventHandler);
	}

	public static void registerEntityTag(String entityName, AspectList aspects, ThaumcraftApi.EntityTagsNBT... nbt)
	{
		scanEntities.add(new ThaumcraftApi.EntityTags(entityName, aspects, nbt));
	}

	public static void addSmeltingBonus(ItemStack in, ItemStack out)
	{
		smeltingBonus.put(Arrays.asList(in.getItem(), in.getItemDamage()), new ItemStack(out.getItem(), 0, out.getItemDamage()));
	}

	public static void addSmeltingBonus(String in, ItemStack out)
	{
		smeltingBonus.put(in, new ItemStack(out.getItem(), 0, out.getItemDamage()));
	}

	public static ItemStack getSmeltingBonus(ItemStack in)
	{
		Item item = in.getItem();
		ItemStack out = smeltingBonus.get(Arrays.asList(item, in.getItemDamage()));
		if (out == null)
			out = smeltingBonus.get(Arrays.asList(item, OreDictionary.WILDCARD_VALUE));

		if (out == null)
		{
			String od = OreDictionary.getOreName(OreDictionary.getOreID(in));
			out = smeltingBonus.get(od);
		}

		return out;
	}

	public static List getCraftingRecipes()
	{
		return craftingRecipes;
	}

	public static ShapedArcaneRecipe addArcaneCraftingRecipe(String research, ItemStack result, AspectList aspects, Object... recipe)
	{
		ShapedArcaneRecipe r = new ShapedArcaneRecipe(research, result, aspects, recipe);
		craftingRecipes.add(r);
		return r;
	}

	public static ShapelessArcaneRecipe addShapelessArcaneCraftingRecipe(String research, ItemStack result, AspectList aspects, Object... recipe)
	{
		ShapelessArcaneRecipe r = new ShapelessArcaneRecipe(research, result, aspects, recipe);
		craftingRecipes.add(r);
		return r;
	}

	public static InfusionRecipe addInfusionCraftingRecipe(String research, Object result, int instability, AspectList aspects, ItemStack input, ItemStack[] recipe)
	{
		if (!(result instanceof ItemStack) && !(result instanceof Object[]))
			return null;
		InfusionRecipe r = new InfusionRecipe(research, result, instability, aspects, input, recipe);
		craftingRecipes.add(r);
		return r;
	}

	public static InfusionEnchantmentRecipe addInfusionEnchantmentRecipe(String research, Enchantment enchantment, int instability, AspectList aspects, ItemStack[] recipe)
	{
		InfusionEnchantmentRecipe r = new InfusionEnchantmentRecipe(research, enchantment, instability, aspects, recipe);
		craftingRecipes.add(r);
		return r;
	}

	public static InfusionRecipe getInfusionRecipe(ItemStack res)
	{
		for (Object r : getCraftingRecipes())
		{
			if (r instanceof InfusionRecipe && ((InfusionRecipe) r).getRecipeOutput() instanceof ItemStack && ((ItemStack) ((InfusionRecipe) r).getRecipeOutput()).isItemEqual(res))
				return (InfusionRecipe) r;
		}

		return null;
	}

	public static CrucibleRecipe addCrucibleRecipe(String key, ItemStack result, Object catalyst, AspectList tags)
	{
		CrucibleRecipe rc = new CrucibleRecipe(key, result, catalyst, tags);
		getCraftingRecipes().add(rc);
		return rc;
	}

	public static CrucibleRecipe getCrucibleRecipe(ItemStack stack)
	{
		for (Object r : getCraftingRecipes())
		{
			if (r instanceof CrucibleRecipe && ((CrucibleRecipe) r).getRecipeOutput().isItemEqual(stack))
				return (CrucibleRecipe) r;
		}

		return null;
	}

	public static CrucibleRecipe getCrucibleRecipeFromHash(int hash)
	{
		for (Object r : getCraftingRecipes())
		{
			if (r instanceof CrucibleRecipe && ((CrucibleRecipe) r).hash == hash)
				return (CrucibleRecipe) r;
		}

		return null;
	}

	public static Object[] getCraftingRecipeKey(EntityPlayer player, ItemStack stack)
	{
		// TODO gamerforEA code start
		if (stack == null)
			return null;
		// TODO gamerforEA code end

		int[] key = { Item.getIdFromItem(stack.getItem()), stack.getItemDamage() };
		if (keyCache.containsKey(key))
			return keyCache.get(key) == null ? null : ThaumcraftApiHelper.isResearchComplete(player.getCommandSenderName(), (String) keyCache.get(key)[0]) ? keyCache.get(key) : null;
		for (ResearchCategoryList rcl : ResearchCategories.researchCategories.values())
		{
			for (ResearchItem ri : rcl.research.values())
			{
				if (ri.getPages() != null)
					for (int a = 0; a < ri.getPages().length; ++a)
					{
						ResearchPage page = ri.getPages()[a];
						if (page.recipe instanceof CrucibleRecipe[])
						{
							CrucibleRecipe[] crs = (CrucibleRecipe[]) page.recipe;

							for (CrucibleRecipe cr : crs)
							{
								if (cr.getRecipeOutput().isItemEqual(stack))
								{
									keyCache.put(key, new Object[] { ri.key, a });
									if (ThaumcraftApiHelper.isResearchComplete(player.getCommandSenderName(), ri.key))
										return new Object[] { ri.key, a };
								}
							}
						}
						else if (page.recipeOutput != null && stack != null && page.recipeOutput.isItemEqual(stack))
						{
							keyCache.put(key, new Object[] { ri.key, a });
							if (ThaumcraftApiHelper.isResearchComplete(player.getCommandSenderName(), ri.key))
								return new Object[] { ri.key, a };

							return null;
						}
					}
			}
		}

		keyCache.put(key, null);
		return null;
	}

	public static boolean exists(Item item, int meta)
	{
		// TODO gamerforEA code start
		if (EventConfig.itemAspectMapOptimize)
			return ObjectTagsCache.INSTANCE.getAspectList(item, meta) != null || meta == OreDictionary.WILDCARD_VALUE && ObjectTagsCache.INSTANCE.getAspectListInRange(item, 0, 16) != null;
		// TODO gamerforEA code end

		AspectList tmp = objectTags.get(Arrays.asList(item, meta));
		if (tmp == null)
		{
			tmp = objectTags.get(Arrays.asList(item, OreDictionary.WILDCARD_VALUE));
			if (meta == OreDictionary.WILDCARD_VALUE && tmp == null)
			{
				int index = 0;

				do
				{
					tmp = objectTags.get(Arrays.asList(item, index));
					++index;
				}
				while (index < 16 && tmp == null);
			}

			return tmp != null;
		}

		return true;
	}

	public static void registerObjectTag(ItemStack item, AspectList aspects)
	{
		if (aspects == null)
			aspects = new AspectList();

		try
		{
			objectTags.put(Arrays.asList(item.getItem(), item.getItemDamage()), aspects);
		}
		catch (Exception ignored)
		{
		}

		// TODO gamerforEA code start
		ObjectTagsCache.INSTANCE.resetCache();
		// TODO gamerforEA code end
	}

	public static void registerObjectTag(ItemStack stack, int[] meta, AspectList aspects)
	{
		if (aspects == null)
			aspects = new AspectList();

		try
		{
			Item item = stack.getItem();
			objectTags.put(Arrays.asList(item, meta[0]), aspects);

			for (int m : meta)
			{
				groupedObjectTags.put(Arrays.asList(item, m), meta);
			}
		}
		catch (Exception ignored)
		{
		}

		// TODO gamerforEA code start
		ObjectTagsCache.INSTANCE.resetCache();
		// TODO gamerforEA code end
	}

	public static void registerObjectTag(String oreDict, AspectList aspects)
	{
		if (aspects == null)
			aspects = new AspectList();

		ArrayList<ItemStack> ores = OreDictionary.getOres(oreDict);
		if (ores != null && ores.size() > 0)
		{
			for (ItemStack ore : ores)
			{
				try
				{
					objectTags.put(Arrays.asList(ore.getItem(), ore.getItemDamage()), aspects);
				}
				catch (Exception ignored)
				{
				}
			}

			// TODO gamerforEA code start
			ObjectTagsCache.INSTANCE.resetCache();
			// TODO gamerforEA code end
		}
	}

	public static void registerComplexObjectTag(ItemStack stack, AspectList aspects)
	{
		Item item = stack.getItem();
		int meta = stack.getItemDamage();
		if (!exists(item, meta))
		{
			AspectList tmp = ThaumcraftApiHelper.generateTags(item, meta);
			if (tmp != null && tmp.size() > 0)
				for (Aspect tag : tmp.getAspects())
				{
					aspects.add(tag, tmp.getAmount(tag));
				}

			registerObjectTag(stack, aspects);
		}
		else
		{
			AspectList tmp = ThaumcraftApiHelper.getObjectAspects(stack);

			for (Aspect tag : aspects.getAspects())
			{
				tmp.merge(tag, tmp.getAmount(tag));
			}

			registerObjectTag(stack, tmp);
		}

	}

	public static void addWarpToItem(ItemStack craftresult, int amount)
	{
		warpMap.put(Arrays.asList(craftresult.getItem(), craftresult.getItemDamage()), amount);
	}

	public static void addWarpToResearch(String research, int amount)
	{
		warpMap.put(research, amount);
	}

	public static int getWarp(Object in)
	{
		if (in == null)
			return 0;
		if (in instanceof ItemStack)
		{
			ItemStack stack = (ItemStack) in;
			List key = Arrays.asList(stack.getItem(), stack.getItemDamage());

			/* TODO gamerforEA code replace, old code:
			if (warpMap.containsKey(key))
				return warpMap.get(key); */
			Integer warp = warpMap.get(key);
			if (warp != null)
				return warp;
			// TODO gamerforEA code end
		}
		if (in instanceof String)
		{
			/* TODO gamerforEA code replace, old code:
			if (warpMap.containsKey(in))
				return warpMap.get(in); */
			Integer warp = warpMap.get(in);
			if (warp != null)
				return warp;
			// TODO gamerforEA code end
		}
		return 0;
	}

	public static void addLootBagItem(ItemStack item, int weight, int... bagTypes)
	{
		if (bagTypes != null && bagTypes.length != 0)
			for (int rarity : bagTypes)
			{
				switch (rarity)
				{
					case 0:
						WeightedRandomLoot.lootBagCommon.add(new WeightedRandomLoot(item, weight));
						break;
					case 1:
						WeightedRandomLoot.lootBagUncommon.add(new WeightedRandomLoot(item, weight));
						break;
					case 2:
						WeightedRandomLoot.lootBagRare.add(new WeightedRandomLoot(item, weight));
				}
			}
		else
			WeightedRandomLoot.lootBagCommon.add(new WeightedRandomLoot(item, weight));
	}

	public static class EntityTags
	{
		public String entityName;
		public ThaumcraftApi.EntityTagsNBT[] nbts;
		public AspectList aspects;

		public EntityTags(String entityName, AspectList aspects, ThaumcraftApi.EntityTagsNBT... nbts)
		{
			this.entityName = entityName;
			this.nbts = nbts;
			this.aspects = aspects;
		}
	}

	public static class EntityTagsNBT
	{
		public String name;
		public Object value;

		public EntityTagsNBT(String name, Object value)
		{
			this.name = name;
			this.value = value;
		}
	}
}
