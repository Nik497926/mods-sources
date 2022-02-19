package thaumcraft.common.lib.crafting;

import com.gamerforea.thaumcraft.EventConfig;
import com.gamerforea.thaumcraft.ObjectTagsCache;
import com.google.common.base.Objects;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.*;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import org.apache.commons.lang3.tuple.Pair;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IEssentiaContainerItem;
import thaumcraft.api.crafting.*;
import thaumcraft.common.config.Config;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.lib.research.ResearchManager;
import thaumcraft.common.lib.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ThaumcraftCraftingManager
{
	public static ShapedRecipes createFakeRecipe(ItemStack par1ItemStack, Object... par2ArrayOfObj)
	{
		StringBuilder var3 = new StringBuilder();
		int var4 = 0;
		int var5 = 0;
		int var6 = 0;
		if (par2ArrayOfObj[var4] instanceof String[])
		{
			String[] var7 = (String[]) par2ArrayOfObj[var4++];

			for (String var11 : var7)
			{
				++var6;
				var5 = var11.length();
				var3.append(var11);
			}
		}
		else
			while (par2ArrayOfObj[var4] instanceof String)
			{
				String var13 = (String) par2ArrayOfObj[var4++];
				++var6;
				var5 = var13.length();
				var3.append(var13);
			}

		HashMap var14;
		for (var14 = new HashMap(); var4 < par2ArrayOfObj.length; var4 += 2)
		{
			Character var16 = (Character) par2ArrayOfObj[var4];
			ItemStack var17 = null;
			if (par2ArrayOfObj[var4 + 1] instanceof Item)
				var17 = new ItemStack((Item) par2ArrayOfObj[var4 + 1]);
			else if (par2ArrayOfObj[var4 + 1] instanceof Block)
				var17 = new ItemStack((Block) par2ArrayOfObj[var4 + 1]);
			else if (par2ArrayOfObj[var4 + 1] instanceof ItemStack)
				var17 = (ItemStack) par2ArrayOfObj[var4 + 1];

			var14.put(var16, var17);
		}

		ItemStack[] var15 = new ItemStack[var5 * var6];

		for (int var9 = 0; var9 < var5 * var6; ++var9)
		{
			char var18 = var3.charAt(var9);
			if (var14.containsKey(var18))
				var15[var9] = ((ItemStack) var14.get(var18)).copy();
			else
				var15[var9] = null;
		}

		return new ShapedRecipes(var5, var6, var15, par1ItemStack);
	}

	public static CrucibleRecipe findMatchingCrucibleRecipe(String username, AspectList aspects, ItemStack lastDrop)
	{
		int highest = 0;
		int index = -1;

		for (int a = 0; a < ThaumcraftApi.getCraftingRecipes().size(); ++a)
		{
			if (ThaumcraftApi.getCraftingRecipes().get(a) instanceof CrucibleRecipe)
			{
				CrucibleRecipe recipe = (CrucibleRecipe) ThaumcraftApi.getCraftingRecipes().get(a);
				ItemStack temp = lastDrop.copy();
				temp.stackSize = 1;
				if (ResearchManager.isResearchComplete(username, recipe.key) && recipe.matches(aspects, temp))
				{
					int result = recipe.aspects.size();
					if (result > highest)
					{
						highest = result;
						index = a;
					}
				}
			}
		}

		if (index < 0)
			return null;
		new AspectList();
		return (CrucibleRecipe) ThaumcraftApi.getCraftingRecipes().get(index);
	}

	public static ItemStack findMatchingArcaneRecipe(IInventory awb, EntityPlayer player)
	{
		int var2 = 0;
		ItemStack var3 = null;
		ItemStack var4 = null;

		for (int var5 = 0; var5 < 9; ++var5)
		{
			ItemStack var6 = awb.getStackInSlot(var5);
			if (var6 != null)
			{
				if (var2 == 0)
					;

				if (var2 == 1)
					;

				++var2;
			}
		}

		IArcaneRecipe var13 = null;

		for (Object var11 : ThaumcraftApi.getCraftingRecipes())
		{
			if (var11 instanceof IArcaneRecipe && ((IArcaneRecipe) var11).matches(awb, player.worldObj, player))
			{
				var13 = (IArcaneRecipe) var11;
				break;
			}
		}

		return var13 == null ? null : var13.getCraftingResult(awb);
	}

	public static AspectList findMatchingArcaneRecipeAspects(IInventory awb, EntityPlayer player)
	{
		int var2 = 0;
		ItemStack var3 = null;
		ItemStack var4 = null;

		for (int var5 = 0; var5 < 9; ++var5)
		{
			ItemStack var6 = awb.getStackInSlot(var5);
			if (var6 != null)
			{
				if (var2 == 0)
					;

				if (var2 == 1)
					;

				++var2;
			}
		}

		IArcaneRecipe var13 = null;

		for (Object var11 : ThaumcraftApi.getCraftingRecipes())
		{
			if (var11 instanceof IArcaneRecipe && ((IArcaneRecipe) var11).matches(awb, player.worldObj, player))
			{
				var13 = (IArcaneRecipe) var11;
				break;
			}
		}

		return var13 == null ? new AspectList() : var13.getAspects() != null ? var13.getAspects() : var13.getAspects(awb);
	}

	public static InfusionRecipe findMatchingInfusionRecipe(ArrayList<ItemStack> items, ItemStack input, EntityPlayer player)
	{
		InfusionRecipe recipe = null;

		for (Object o : ThaumcraftApi.getCraftingRecipes())
		{
			if (o instanceof InfusionRecipe && ((InfusionRecipe) o).matches(items, input, player.worldObj, player))
			{
				recipe = (InfusionRecipe) o;
				break;
			}
		}

		return recipe;
	}

	public static InfusionEnchantmentRecipe findMatchingInfusionEnchantmentRecipe(ArrayList<ItemStack> items, ItemStack input, EntityPlayer player)
	{
		InfusionEnchantmentRecipe var13 = null;

		for (Object var11 : ThaumcraftApi.getCraftingRecipes())
		{
			if (var11 instanceof InfusionEnchantmentRecipe && ((InfusionEnchantmentRecipe) var11).matches(items, input, player.worldObj, player))
			{
				var13 = (InfusionEnchantmentRecipe) var11;
				break;
			}
		}

		return var13;
	}

	public static AspectList getObjectTags(ItemStack stack)
	{
		Item item;
		int meta;
		try
		{
			item = stack.getItem();

			// TODO gamerforEA code start
			if (item == null)
				return null;
			// TODO gamerforEA code end

			meta = stack.getItemDamage();
		}
		catch (Exception e)
		{
			return null;
		}

		// TODO gamerforEA code replace, old code:
		// AspectList tmp = ThaumcraftApi.objectTags.get(Arrays.asList(item, meta));
		boolean useCache = EventConfig.itemAspectMapOptimize;
		AspectList tmp = useCache ? ObjectTagsCache.INSTANCE.getAspectList(item, meta) : ThaumcraftApi.objectTags.get(Arrays.asList(item, meta));
		// TODO gamerforEA code end

		if (tmp == null)
		{
			/* TODO gamerforEA code replace, old code:
			for (List key : ThaumcraftApi.objectTags.keySet())
			{
				if (key.get(0) == item && key.get(1) instanceof int[])
				{
					int[] range = (int[]) key.get(1);
					Arrays.sort(range);
					if (Arrays.binarySearch(range, meta) >= 0)
					{
						tmp = ThaumcraftApi.objectTags.get(Arrays.asList(item, range));
						return tmp;
					}
				}
			} */
			if (useCache)
				for (Pair<int[], AspectList> pair : ObjectTagsCache.INSTANCE.getRangedObjectTags(item))
				{
					int[] range = pair.getKey();
					if (Arrays.binarySearch(range, meta) >= 0)
						return pair.getValue();
				}
			else
				for (List key : ThaumcraftApi.objectTags.keySet())
				{
					if (key.get(0) == item && key.get(1) instanceof int[])
					{
						int[] range = (int[]) key.get(1);
						Arrays.sort(range);
						if (Arrays.binarySearch(range, meta) >= 0)
							return ThaumcraftApi.objectTags.get(key);
					}
				}
			// TODO gamerforEA code end

			// TODO gamerforEA code replace, old code:
			// tmp = ThaumcraftApi.objectTags.get(Arrays.asList(item, OreDictionary.WILDCARD_VALUE));
			tmp = useCache ? ObjectTagsCache.INSTANCE.getAspectList(item, OreDictionary.WILDCARD_VALUE) : ThaumcraftApi.objectTags.get(Arrays.asList(item, OreDictionary.WILDCARD_VALUE));
			// TODO gamerforEA code end

			if (tmp == null && tmp == null)
			{
				if (meta == OreDictionary.WILDCARD_VALUE && tmp == null)
				{
					// TODO gamerforEA code start
					if (useCache)
						tmp = ObjectTagsCache.INSTANCE.getAspectListInRange(item, 0, 16);
					else
					// TODO gamerforEA code end
					{
						int index = 0;
						do
						{
							tmp = ThaumcraftApi.objectTags.get(Arrays.asList(item, index));
							++index;
						}
						while (index < 16 && tmp == null);
					}
				}

				if (tmp == null)
					tmp = generateTags(item, meta);
			}
		}

		if (item instanceof ItemWandCasting)
		{
			ItemWandCasting wand = (ItemWandCasting) item;
			if (tmp == null)
				tmp = new AspectList();
			tmp.merge(Aspect.MAGIC, (wand.getRod(stack).getCraftCost() + wand.getCap(stack).getCraftCost()) / 2);
			tmp.merge(Aspect.TOOL, (wand.getRod(stack).getCraftCost() + wand.getCap(stack).getCraftCost()) / 3);
		}

		// TODO gamerforEA add condition [2]
		if (item == Items.potionitem && item instanceof ItemPotion)
		{
			if (tmp == null)
				tmp = new AspectList();

			tmp.merge(Aspect.WATER, 1);
			ItemPotion ip = (ItemPotion) item;
			List<PotionEffect> effects = ip.getEffects(stack.getItemDamage());
			if (effects != null)
			{
				if (ItemPotion.isSplash(stack.getItemDamage()))
					tmp.merge(Aspect.ENTROPY, 2);

				for (PotionEffect var6 : effects)
				{
					tmp.merge(Aspect.MAGIC, (var6.getAmplifier() + 1) * 2);
					if (var6.getPotionID() == Potion.blindness.id)
						tmp.merge(Aspect.DARKNESS, (var6.getAmplifier() + 1) * 3);
					else if (var6.getPotionID() == Potion.confusion.id)
						tmp.merge(Aspect.ELDRITCH, (var6.getAmplifier() + 1) * 3);
					else if (var6.getPotionID() == Potion.damageBoost.id)
						tmp.merge(Aspect.WEAPON, (var6.getAmplifier() + 1) * 3);
					else if (var6.getPotionID() == Potion.digSlowdown.id)
						tmp.merge(Aspect.TRAP, (var6.getAmplifier() + 1) * 3);
					else if (var6.getPotionID() == Potion.digSpeed.id)
						tmp.merge(Aspect.TOOL, (var6.getAmplifier() + 1) * 3);
					else if (var6.getPotionID() == Potion.fireResistance.id)
					{
						tmp.merge(Aspect.ARMOR, var6.getAmplifier() + 1);
						tmp.merge(Aspect.FIRE, (var6.getAmplifier() + 1) * 2);
					}
					else if (var6.getPotionID() == Potion.harm.id)
						tmp.merge(Aspect.DEATH, (var6.getAmplifier() + 1) * 3);
					else if (var6.getPotionID() == Potion.heal.id)
						tmp.merge(Aspect.HEAL, (var6.getAmplifier() + 1) * 3);
					else if (var6.getPotionID() == Potion.hunger.id)
						tmp.merge(Aspect.DEATH, (var6.getAmplifier() + 1) * 3);
					else if (var6.getPotionID() == Potion.invisibility.id)
						tmp.merge(Aspect.SENSES, (var6.getAmplifier() + 1) * 3);
					else if (var6.getPotionID() == Potion.jump.id)
						tmp.merge(Aspect.FLIGHT, (var6.getAmplifier() + 1) * 3);
					else if (var6.getPotionID() == Potion.moveSlowdown.id)
						tmp.merge(Aspect.TRAP, (var6.getAmplifier() + 1) * 3);
					else if (var6.getPotionID() == Potion.moveSpeed.id)
						tmp.merge(Aspect.MOTION, (var6.getAmplifier() + 1) * 3);
					else if (var6.getPotionID() == Potion.nightVision.id)
						tmp.merge(Aspect.SENSES, (var6.getAmplifier() + 1) * 3);
					else if (var6.getPotionID() == Potion.poison.id)
						tmp.merge(Aspect.POISON, (var6.getAmplifier() + 1) * 3);
					else if (var6.getPotionID() == Potion.regeneration.id)
						tmp.merge(Aspect.HEAL, (var6.getAmplifier() + 1) * 3);
					else if (var6.getPotionID() == Potion.resistance.id)
						tmp.merge(Aspect.ARMOR, (var6.getAmplifier() + 1) * 3);
					else if (var6.getPotionID() == Potion.waterBreathing.id)
						tmp.merge(Aspect.AIR, (var6.getAmplifier() + 1) * 3);
					else if (var6.getPotionID() == Potion.weakness.id)
						tmp.merge(Aspect.DEATH, (var6.getAmplifier() + 1) * 3);
				}
			}
		}

		return capAspects(tmp, 64);
	}

	private static AspectList capAspects(AspectList sourcetags, int amount)
	{
		if (sourcetags == null)
			return null;
		AspectList out = new AspectList();

		for (Aspect aspect : sourcetags.getAspects())
		{
			out.merge(aspect, Math.min(amount, sourcetags.getAmount(aspect)));
		}

		return out;
	}

	public static AspectList getBonusTags(ItemStack stack, AspectList sourcetags)
	{
		AspectList tmp = new AspectList();
		Item item = stack.getItem();

		// TODO gamerforEA code start
		if (item == null)
			return new AspectList();
		// TODO gamerforEA code end

		if (item instanceof IEssentiaContainerItem)
		{
			tmp = ((IEssentiaContainerItem) item).getAspects(stack);
			if (tmp != null && tmp.size() > 0)
				for (Aspect tag : tmp.copy().getAspects())
				{
					if (tmp.getAmount(tag) <= 0)
						tmp.remove(tag);
				}
		}

		if (tmp == null)
			tmp = new AspectList();

		if (sourcetags != null)
			for (Aspect tag : sourcetags.getAspects())
			{
				if (tag != null)
					tmp.add(tag, sourcetags.getAmount(tag));
			}

		if (item != null && (tmp != null || item == Items.potionitem))
		{
			if (item instanceof ItemArmor)
				tmp.merge(Aspect.ARMOR, ((ItemArmor) item).damageReduceAmount);
			else if (item instanceof ItemSword && ((ItemSword) item).func_150931_i() + 1.0F > 0.0F)
				tmp.merge(Aspect.WEAPON, (int) (((ItemSword) item).func_150931_i() + 1.0F));
			else if (item instanceof ItemBow)
				tmp.merge(Aspect.WEAPON, 3).merge(Aspect.FLIGHT, 1);
			else if (item instanceof ItemPickaxe)
			{
				String mat = ((ItemTool) item).getToolMaterialName();

				for (ToolMaterial tm : ToolMaterial.values())
				{
					if (tm.toString().equals(mat))
						tmp.merge(Aspect.MINE, tm.getHarvestLevel() + 1);
				}
			}
			else if (item instanceof ItemTool)
			{
				String mat = ((ItemTool) item).getToolMaterialName();

				for (ToolMaterial tm : ToolMaterial.values())
				{
					if (tm.toString().equals(mat))
						tmp.merge(Aspect.TOOL, tm.getHarvestLevel() + 1);
				}
			}
			else if (item instanceof ItemShears || item instanceof ItemHoe)
				if (item.getMaxDamage() <= ToolMaterial.WOOD.getMaxUses())
					tmp.merge(Aspect.HARVEST, 1);
				else if (item.getMaxDamage() > ToolMaterial.STONE.getMaxUses() && item.getMaxDamage() > ToolMaterial.GOLD.getMaxUses())
					if (item.getMaxDamage() <= ToolMaterial.IRON.getMaxUses())
						tmp.merge(Aspect.HARVEST, 3);
					else
						tmp.merge(Aspect.HARVEST, 4);
				else
					tmp.merge(Aspect.HARVEST, 2);

			NBTTagList ench = stack.getEnchantmentTagList();
			if (item instanceof ItemEnchantedBook)
				ench = ((ItemEnchantedBook) item).func_92110_g(stack);

			if (ench != null)
			{
				int var5 = 0;

				for (int var3 = 0; var3 < ench.tagCount(); ++var3)
				{
					short eid = ench.getCompoundTagAt(var3).getShort("id");
					short lvl = ench.getCompoundTagAt(var3).getShort("lvl");
					if (eid == Enchantment.aquaAffinity.effectId)
						tmp.merge(Aspect.WATER, lvl);
					else if (eid == Enchantment.baneOfArthropods.effectId)
						tmp.merge(Aspect.BEAST, lvl);
					else if (eid == Enchantment.blastProtection.effectId)
						tmp.merge(Aspect.ARMOR, lvl);
					else if (eid == Enchantment.efficiency.effectId)
						tmp.merge(Aspect.TOOL, lvl);
					else if (eid == Enchantment.featherFalling.effectId)
						tmp.merge(Aspect.FLIGHT, lvl);
					else if (eid == Enchantment.fireAspect.effectId)
						tmp.merge(Aspect.FIRE, lvl);
					else if (eid == Enchantment.fireProtection.effectId)
						tmp.merge(Aspect.ARMOR, lvl);
					else if (eid == Enchantment.flame.effectId)
						tmp.merge(Aspect.FIRE, lvl);
					else if (eid == Enchantment.fortune.effectId)
						tmp.merge(Aspect.GREED, lvl);
					else if (eid == Enchantment.infinity.effectId)
						tmp.merge(Aspect.CRAFT, lvl);
					else if (eid == Enchantment.knockback.effectId)
						tmp.merge(Aspect.AIR, lvl);
					else if (eid == Enchantment.looting.effectId)
						tmp.merge(Aspect.GREED, lvl);
					else if (eid == Enchantment.power.effectId)
						tmp.merge(Aspect.WEAPON, lvl);
					else if (eid == Enchantment.projectileProtection.effectId)
						tmp.merge(Aspect.ARMOR, lvl);
					else if (eid == Enchantment.protection.effectId)
						tmp.merge(Aspect.ARMOR, lvl);
					else if (eid == Enchantment.punch.effectId)
						tmp.merge(Aspect.AIR, lvl);
					else if (eid == Enchantment.respiration.effectId)
						tmp.merge(Aspect.AIR, lvl);
					else if (eid == Enchantment.sharpness.effectId)
						tmp.merge(Aspect.WEAPON, lvl);
					else if (eid == Enchantment.silkTouch.effectId)
						tmp.merge(Aspect.EXCHANGE, lvl);
					else if (eid == Enchantment.thorns.effectId)
						tmp.merge(Aspect.WEAPON, lvl);
					else if (eid == Enchantment.smite.effectId)
						tmp.merge(Aspect.ENTROPY, lvl);
					else if (eid == Enchantment.unbreaking.effectId)
						tmp.merge(Aspect.EARTH, lvl);
					else if (eid == Enchantment.field_151370_z.effectId)
						tmp.merge(Aspect.GREED, lvl);
					else if (eid == Enchantment.field_151369_A.effectId)
						tmp.merge(Aspect.BEAST, lvl);
					else if (eid == Config.enchHaste.effectId)
						tmp.merge(Aspect.MOTION, lvl);
					else if (eid == Config.enchRepair.effectId)
						tmp.merge(Aspect.TOOL, lvl);

					var5 += lvl;
				}

				if (var5 > 0)
					tmp.merge(Aspect.MAGIC, var5);
			}
		}

		return ThaumcraftApiHelper.cullTags(tmp);
	}

	// TODO gamerforEA code start
	private static final ThreadLocal<Integer> GENERATE_TAGS_CALLS_COUNTER = new ThreadLocal<>();
	// TODO gamerforEA code end

	public static AspectList generateTags(Item item, int meta)
	{
		// TODO gamerforEA code replace, old code: return generateTags(item, meta, new ArrayList());
		Integer counter = Objects.firstNonNull(GENERATE_TAGS_CALLS_COUNTER.get(), 0);
		if (counter > 100)
			return null;
		GENERATE_TAGS_CALLS_COUNTER.set(counter + 1);
		try
		{
			return generateTags(item, meta, new ArrayList<>());
		}
		finally
		{
			GENERATE_TAGS_CALLS_COUNTER.set(counter);
		}
		// TODO gamerforEA code end
	}

	public static AspectList generateTags(Item item, int meta, ArrayList<List> history)
	{
		int tmeta = meta;

		try
		{
			ItemStack stack = new ItemStack(item, 1, meta);
			Item stackItem = stack.getItem();
			tmeta = !stackItem.isDamageable() && stackItem.getHasSubtypes() ? meta : OreDictionary.WILDCARD_VALUE;
		}
		catch (Exception ignored)
		{
		}

		if (ThaumcraftApi.exists(item, tmeta))
			return getObjectTags(new ItemStack(item, 1, tmeta));
		List<Object> historyRecord = Arrays.asList(item, tmeta);
		if (history.contains(historyRecord))
			return null;
		history.add(historyRecord);
		if (history.size() < 100)
		{
			AspectList ret = generateTagsFromRecipes(item, tmeta == OreDictionary.WILDCARD_VALUE ? 0 : meta, history);
			ret = capAspects(ret, 64);
			ThaumcraftApi.registerObjectTag(new ItemStack(item, 1, tmeta), ret);
			return ret;
		}
		return null;
	}

	private static AspectList generateTagsFromCrucibleRecipes(Item item, int meta, ArrayList<List> history)
	{
		CrucibleRecipe cr = ThaumcraftApi.getCrucibleRecipe(new ItemStack(item, 1, meta));
		if (cr != null)
		{
			AspectList ot = cr.aspects.copy();
			int ss = cr.getRecipeOutput().stackSize;
			ItemStack cat = null;
			if (cr.catalyst instanceof ItemStack)
				cat = (ItemStack) cr.catalyst;
			else if (cr.catalyst instanceof ArrayList && ((ArrayList) cr.catalyst).size() > 0)
				cat = (ItemStack) ((ArrayList) cr.catalyst).get(0);

			// TODO gamerforEA code replace, old code:
			// AspectList ot2 = generateTags(cat.getItem(), cat.getItemDamage());
			if (cat == null)
				return null;
			AspectList ot2 = generateTags(cat.getItem(), cat.getItemDamage(), history);
			// TODO gamerforEA code end

			AspectList out = new AspectList();
			if (ot2 != null && ot2.size() > 0)
				for (Aspect tt : ot2.getAspects())
				{
					out.add(tt, ot2.getAmount(tt));
				}

			for (Aspect tt : ot.getAspects())
			{
				int amt = (int) (Math.sqrt(ot.getAmount(tt)) / ss);
				out.add(tt, amt);
			}

			for (Aspect as : out.getAspects())
			{
				if (out.getAmount(as) <= 0)
					out.remove(as);
			}

			return out;
		}
		return null;
	}

	private static AspectList generateTagsFromArcaneRecipes(Item item, int meta, ArrayList<List> history)
	{
		AspectList ret = null;
		int value = 0;
		List recipeList = ThaumcraftApi.getCraftingRecipes();

		label25:
		for (int q = 0; q < recipeList.size(); ++q)
		{
			if (recipeList.get(q) instanceof IArcaneRecipe)
			{
				IArcaneRecipe recipe = (IArcaneRecipe) recipeList.get(q);
				ItemStack recipeOutput = recipe.getRecipeOutput();
				if (recipeOutput != null)
				{
					int recipeOutputItemDamage = recipeOutput.getItemDamage();
					int idR = recipeOutputItemDamage == OreDictionary.WILDCARD_VALUE ? 0 : recipeOutputItemDamage;
					int idS = meta < 0 ? 0 : meta;
					if (recipeOutput.getItem() == item && idR == idS)
					{
						ArrayList<ItemStack> ingredients = new ArrayList();
						new AspectList();
						int cval = 0;

						try
						{
							if (recipeList.get(q) instanceof ShapedArcaneRecipe)
							{
								int width = ((ShapedArcaneRecipe) recipeList.get(q)).width;
								int height = ((ShapedArcaneRecipe) recipeList.get(q)).height;
								Object[] items = ((ShapedArcaneRecipe) recipeList.get(q)).getInput();

								for (int i = 0; i < width && i < 3; ++i)
								{
									for (int j = 0; j < height && j < 3; ++j)
									{
										if (items[i + j * width] != null)
											if (items[i + j * width] instanceof ArrayList)
												for (ItemStack it : (ArrayList<ItemStack>) items[i + j * width])
												{
													if (Utils.isEETransmutionItem(it.getItem()))
														continue label25;

													AspectList obj = generateTags(it.getItem(), it.getItemDamage(), history);
													if (obj != null && obj.size() > 0)
													{
														ItemStack is = it.copy();
														is.stackSize = 1;
														ingredients.add(is);
														break;
													}
												}
											else
											{
												ItemStack it = (ItemStack) items[i + j * width];
												if (Utils.isEETransmutionItem(it.getItem()))
													continue label25;

												ItemStack is = it.copy();
												is.stackSize = 1;
												ingredients.add(is);
											}
									}
								}
							}
							else if (recipeList.get(q) instanceof ShapelessArcaneRecipe)
							{
								ArrayList items = ((ShapelessArcaneRecipe) recipeList.get(q)).getInput();

								for (int i = 0; i < items.size() && i < 9; ++i)
								{
									if (items.get(i) != null)
										if (items.get(i) instanceof ArrayList)
											for (ItemStack it : (ArrayList<ItemStack>) items.get(i))
											{
												if (Utils.isEETransmutionItem(it.getItem()))
													continue label25;

												AspectList obj = generateTags(it.getItem(), it.getItemDamage(), history);
												if (obj != null && obj.size() > 0)
												{
													ItemStack is = it.copy();
													is.stackSize = 1;
													ingredients.add(is);
													break;
												}
											}
										else
										{
											ItemStack it = (ItemStack) items.get(i);
											if (Utils.isEETransmutionItem(it.getItem()))
												continue label25;

											ItemStack is = it.copy();
											is.stackSize = 1;
											ingredients.add(is);
										}
								}
							}

							AspectList ph = getAspectsFromIngredients(ingredients, recipeOutput, history);
							if (recipe.getAspects() != null)
								for (Aspect a : recipe.getAspects().getAspects())
								{
									ph.add(a, (int) (Math.sqrt(recipe.getAspects().getAmount(a)) / recipeOutput.stackSize));
								}

							for (Aspect as : ph.copy().getAspects())
							{
								if (ph.getAmount(as) <= 0)
									ph.remove(as);
							}

							if (cval >= value)
							{
								ret = ph;
								value = cval;
							}
						}
						catch (Exception var22)
						{
							var22.printStackTrace();
						}
					}
				}
			}
		}

		return ret;
	}

	private static AspectList generateTagsFromInfusionRecipes(Item item, int meta, ArrayList<List> history)
	{
		InfusionRecipe cr = ThaumcraftApi.getInfusionRecipe(new ItemStack(item, 1, meta));
		if (cr == null)
			return null;
		AspectList ot = cr.getAspects().copy();
		ArrayList<ItemStack> ingredients = new ArrayList();
		ItemStack is = cr.getRecipeInput().copy();
		is.stackSize = 1;
		ingredients.add(is);

		for (ItemStack cat : cr.getComponents())
		{
			ItemStack is2 = cat.copy();
			is2.stackSize = 1;
			ingredients.add(is2);
		}

		AspectList out = new AspectList();
		AspectList ot2 = getAspectsFromIngredients(ingredients, (ItemStack) cr.getRecipeOutput(), history);

		for (Aspect tt : ot2.getAspects())
		{
			out.add(tt, ot2.getAmount(tt));
		}

		for (Aspect tt : ot.getAspects())
		{
			int amt = (int) (Math.sqrt(ot.getAmount(tt)) / ((ItemStack) cr.getRecipeOutput()).stackSize);
			out.add(tt, amt);
		}

		for (Aspect as : out.getAspects())
		{
			if (out.getAmount(as) <= 0)
				out.remove(as);
		}

		return out;
	}

	private static AspectList generateTagsFromCraftingRecipes(Item item, int meta, ArrayList<List> history)
	{
		AspectList ret = null;
		int value = Integer.MAX_VALUE;
		List<IRecipe> recipeList = CraftingManager.getInstance().getRecipeList();

		label29:
		for (int q = 0; q < recipeList.size(); ++q)
		{
			IRecipe recipe = recipeList.get(q);
			if (recipe != null)
			{
				ItemStack recipeOutput = recipe.getRecipeOutput();
				if (recipeOutput != null)
				{
					Item recipeOutputItem = recipeOutput.getItem();
					if (recipeOutputItem != null && Item.getIdFromItem(recipeOutputItem) > 0)
					{
						int recipeOutputItemDamage = recipeOutput.getItemDamage();
						int idR = recipeOutputItemDamage == OreDictionary.WILDCARD_VALUE ? 0 : recipeOutputItemDamage;
						int idS = meta == OreDictionary.WILDCARD_VALUE ? 0 : meta;
						if (recipeOutputItem == item && idR == idS)
						{
							ArrayList<ItemStack> ingredients = new ArrayList();

							try
							{
								if (recipeList.get(q) instanceof ShapedRecipes)
								{
									int width = ((ShapedRecipes) recipeList.get(q)).recipeWidth;
									int height = ((ShapedRecipes) recipeList.get(q)).recipeHeight;
									ItemStack[] items = ((ShapedRecipes) recipeList.get(q)).recipeItems;

									for (int i = 0; i < width && i < 3; ++i)
									{
										for (int j = 0; j < height && j < 3; ++j)
										{
											if (items[i + j * width] != null)
											{
												if (Utils.isEETransmutionItem(items[i + j * width].getItem()))
													continue label29;

												ItemStack is = items[i + j * width].copy();
												is.stackSize = 1;
												ingredients.add(is);
											}
										}
									}
								}
								else if (recipeList.get(q) instanceof ShapelessRecipes)
								{
									List<ItemStack> items = ((ShapelessRecipes) recipeList.get(q)).recipeItems;

									for (int i = 0; i < items.size() && i < 9; ++i)
									{
										ItemStack it = items.get(i);
										if (it != null)
										{
											if (Utils.isEETransmutionItem(it.getItem()))
												continue label29;
											ItemStack is = it.copy();
											is.stackSize = 1;
											ingredients.add(is);
										}
									}
								}
								else if (recipeList.get(q) instanceof ShapedOreRecipe)
								{
									int size = recipeList.get(q).getRecipeSize();
									Object[] items = ((ShapedOreRecipe) recipeList.get(q)).getInput();

									for (int i = 0; i < size && i < 9; ++i)
									{
										if (items[i] != null)
											if (items[i] instanceof ArrayList)
												for (ItemStack it : (ArrayList<ItemStack>) items[i])
												{
													Item itItem = it.getItem();
													if (Utils.isEETransmutionItem(itItem))
														continue label29;
													AspectList obj = generateTags(itItem, it.getItemDamage(), history);
													if (obj != null && obj.size() > 0)
													{
														ItemStack is = it.copy();
														is.stackSize = 1;
														ingredients.add(is);
														break;
													}
												}
											else
											{
												ItemStack it = (ItemStack) items[i];
												if (Utils.isEETransmutionItem(it.getItem()))
													continue label29;
												ItemStack is = it.copy();
												is.stackSize = 1;
												ingredients.add(is);
											}
									}
								}
								else if (recipeList.get(q) instanceof ShapelessOreRecipe)
								{
									ArrayList items = ((ShapelessOreRecipe) recipeList.get(q)).getInput();

									for (int i = 0; i < items.size() && i < 9; ++i)
									{
										if (items.get(i) != null)
											if (items.get(i) instanceof ArrayList)
												for (ItemStack it : (ArrayList<ItemStack>) items.get(i))
												{
													Item itItem = it.getItem();
													if (Utils.isEETransmutionItem(itItem))
														continue label29;
													AspectList obj = generateTags(itItem, it.getItemDamage(), history);
													if (obj != null && obj.size() > 0)
													{
														ItemStack is = it.copy();
														is.stackSize = 1;
														ingredients.add(is);
														break;
													}
												}
											else
											{
												ItemStack it = (ItemStack) items.get(i);
												if (Utils.isEETransmutionItem(it.getItem()))
													continue label29;
												ItemStack is = it.copy();
												is.stackSize = 1;
												ingredients.add(is);
											}
									}
								}

								AspectList ph = getAspectsFromIngredients(ingredients, recipeOutput, history);

								for (Aspect as : ph.copy().getAspects())
								{
									if (ph.getAmount(as) <= 0)
										ph.remove(as);
								}

								if (ph.visSize() < value && ph.visSize() > 0)
								{
									ret = ph;
									value = ph.visSize();
								}
							}
							catch (Exception var20)
							{
								var20.printStackTrace();
							}
						}
					}
				}
			}
		}

		return ret;
	}

	private static AspectList getAspectsFromIngredients(ArrayList<ItemStack> ingredients, ItemStack recipeOut, ArrayList<List> history)
	{
		AspectList out = new AspectList();
		AspectList mid = new AspectList();

		for (ItemStack stack : ingredients)
		{
			Item item = stack.getItem();

			// TODO gamerforEA code start
			if (item == null)
				continue;
			// TODO gamerforEA code end

			Item containerItem = item.getContainerItem();
			if (containerItem != null)
			{
				if (containerItem != item)
				{
					AspectList aspectList = generateTags(containerItem, OreDictionary.WILDCARD_VALUE, history);

					// TODO gamerforEA code start
					if (aspectList == null)
						continue;
					// TODO gamerforEA code end

					for (Aspect aspect : aspectList.getAspects())
					{
						out.reduce(aspect, aspectList.getAmount(aspect));
					}
				}
			}
			else
			{
				AspectList aspectList = generateTags(item, stack.getItemDamage(), history);
				if (aspectList != null)
					for (Aspect aspect : aspectList.getAspects())
					{
						if (aspect != null)
							mid.add(aspect, aspectList.getAmount(aspect));
					}
			}
		}

		for (Aspect aspect : mid.getAspects())
		{
			if (aspect != null)
				out.add(aspect, (int) (mid.getAmount(aspect) * 0.75F / recipeOut.stackSize));
		}

		for (Aspect aspect : out.getAspects())
		{
			if (out.getAmount(aspect) <= 0)
				out.remove(aspect);
		}

		return out;
	}

	private static AspectList generateTagsFromRecipes(Item item, int meta, ArrayList<List> history)
	{
		AspectList ret = null;
		int value = 0;
		ret = generateTagsFromCrucibleRecipes(item, meta, history);
		if (ret != null)
			return ret;
		ret = generateTagsFromArcaneRecipes(item, meta, history);
		if (ret != null)
			return ret;
		ret = generateTagsFromInfusionRecipes(item, meta, history);
		if (ret != null)
			return ret;
		ret = generateTagsFromCraftingRecipes(item, meta, history);
		return ret;
	}
}
