package thaumcraft.api.crafting;

import com.gamerforea.thaumcraft.EventConfig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.config.ConfigItems;

import java.util.ArrayList;

public class InfusionRecipe
{
	protected AspectList aspects;
	protected String research;
	private ItemStack[] components;
	private ItemStack recipeInput;
	protected Object recipeOutput;
	protected int instability;

	// TODO gamerforEA code start
	private boolean fuzzy = true;

	public boolean isFuzzy()
	{
		return this.fuzzy;
	}

	public void setFuzzy(boolean fuzzy)
	{
		this.fuzzy = fuzzy;
	}
	// TODO gamerforEA code end

	public InfusionRecipe(String research, Object output, int inst, AspectList aspects2, ItemStack input, ItemStack[] recipe)
	{
		this.research = research;
		this.recipeOutput = output;
		this.recipeInput = input;
		this.aspects = aspects2;
		this.components = recipe;
		this.instability = inst;

		// TODO gamerforEA code start
		if (EventConfig.infusionStrictShardCheck)
			for (ItemStack stack : recipe)
			{
				if (stack != null && stack.getItem() == ConfigItems.itemShard)
				{
					this.setFuzzy(false);
					break;
				}
			}
		// TODO gamerforEA code end
	}

	public boolean matches(ArrayList<ItemStack> input, ItemStack central, World world, EntityPlayer player)
	{
		if (this.getRecipeInput() == null)
			return false;
		if (this.research.length() > 0 && !ThaumcraftApiHelper.isResearchComplete(player.getCommandSenderName(), this.research))
			return false;
		ItemStack i2 = central.copy();
		if (this.getRecipeInput().getItemDamage() == OreDictionary.WILDCARD_VALUE)
			i2.setItemDamage(OreDictionary.WILDCARD_VALUE);

		// TODO gamerforEA code replace, old code:
		// if (!areItemStacksEqual(i2, this.getRecipeInput(), true))
		if (!areItemStacksEqual(i2, this.getRecipeInput(), this.isFuzzy()))
			// TODO gamerforEA code end
			return false;
		ArrayList<ItemStack> ii = new ArrayList<>();

		for (ItemStack is : input)
		{
			ii.add(is.copy());
		}

		for (ItemStack comp : this.getComponents())
		{
			boolean b = false;

			for (int a = 0; a < ii.size(); ++a)
			{
				i2 = ii.get(a).copy();
				if (comp.getItemDamage() == OreDictionary.WILDCARD_VALUE)
					i2.setItemDamage(OreDictionary.WILDCARD_VALUE);

				// TODO gamerforEA code replace, old code:
				// if (areItemStacksEqual(i2, comp, true))
				if (areItemStacksEqual(i2, comp, this.isFuzzy()))
				// TODO gamerforEA code end
				{
					ii.remove(a);
					b = true;
					break;
				}
			}

			if (!b)
				return false;
		}

		return ii.size() == 0;
	}

	public static boolean areItemStacksEqual(ItemStack stack0, ItemStack stack1, boolean fuzzy)
	{
		if (stack0 == null && stack1 != null)
			return false;
		if (stack0 != null && stack1 == null)
			return false;
		if (stack0 == null && stack1 == null)
			return true;
		boolean t1 = ThaumcraftApiHelper.areItemStackTagsEqualForCrafting(stack0, stack1);
		if (!t1)
			return false;

		// TODO gamerforEA code start
		if (fuzzy && EventConfig.infusionStrictShardCheck)
		{
			if (stack0.getItem() == ConfigItems.itemShard || stack1.getItem() == ConfigItems.itemShard)
				fuzzy = false;
		}
		// TODO gamerforEA code end

		if (fuzzy)
		{
			int od = OreDictionary.getOreID(stack0);
			if (od != -1)
			{
				ItemStack[] ores = OreDictionary.getOres(od).toArray(new ItemStack[0]);
				if (ThaumcraftApiHelper.containsMatch(false, new ItemStack[] { stack1 }, ores))
					return true;
			}
		}

		boolean damage = stack0.getItemDamage() == stack1.getItemDamage() || stack1.getItemDamage() == OreDictionary.WILDCARD_VALUE;
		return stack0.getItem() == stack1.getItem() && damage && stack0.stackSize <= stack0.getMaxStackSize();
	}

	public Object getRecipeOutput()
	{
		return this.getRecipeOutput(this.getRecipeInput());
	}

	public AspectList getAspects()
	{
		return this.getAspects(this.getRecipeInput());
	}

	public int getInstability()
	{
		return this.getInstability(this.getRecipeInput());
	}

	public String getResearch()
	{
		return this.research;
	}

	public ItemStack getRecipeInput()
	{
		return this.recipeInput;
	}

	public ItemStack[] getComponents()
	{
		return this.components;
	}

	public Object getRecipeOutput(ItemStack input)
	{
		return this.recipeOutput;
	}

	public AspectList getAspects(ItemStack input)
	{
		return this.aspects;
	}

	public int getInstability(ItemStack input)
	{
		return this.instability;
	}
}
