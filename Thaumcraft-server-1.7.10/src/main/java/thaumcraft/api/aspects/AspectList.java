package thaumcraft.api.aspects;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import thaumcraft.api.ThaumcraftApiHelper;

import java.io.Serializable;
import java.util.LinkedHashMap;

public class AspectList implements Serializable
{
	public LinkedHashMap<Aspect, Integer> aspects = new LinkedHashMap<>();

	public AspectList(ItemStack stack)
	{
		try
		{
			AspectList temp = ThaumcraftApiHelper.getObjectAspects(stack);
			if (temp != null)
				for (Aspect tag : temp.getAspects())
				{
					this.add(tag, temp.getAmount(tag));
				}
		}
		catch (Exception ignored)
		{
		}
	}

	public AspectList()
	{
	}

	// TODO gamerforEA code start
	@Override
	public String toString()
	{
		return this.aspects == null ? "{}" : this.aspects.toString();
	}
	// TODO gamerforEA code end

	public AspectList copy()
	{
		AspectList out = new AspectList();

		for (Aspect a : this.getAspects())
		{
			out.add(a, this.getAmount(a));
		}

		return out;
	}

	public int size()
	{
		return this.aspects.size();
	}

	public int visSize()
	{
		int q = 0;

		for (Aspect as : this.aspects.keySet())
		{
			q += this.getAmount(as);
		}

		return q;
	}

	public Aspect[] getAspects()
	{
		Aspect[] q = new Aspect[1];
		return this.aspects.keySet().toArray(q);
	}

	public Aspect[] getPrimalAspects()
	{
		AspectList t = new AspectList();

		for (Aspect as : this.aspects.keySet())
		{
			if (as.isPrimal())
				t.add(as, 1);
		}

		Aspect[] q = new Aspect[1];
		return t.aspects.keySet().toArray(q);
	}

	public Aspect[] getAspectsSorted()
	{
		try
		{
			Aspect[] out = this.aspects.keySet().toArray(new Aspect[0]);
			boolean change = false;

			while (true)
			{
				change = false;

				for (int a = 0; a < out.length - 1; ++a)
				{
					Aspect e1 = out[a];
					Aspect e2 = out[a + 1];
					if (e1 != null && e2 != null && e1.getTag().compareTo(e2.getTag()) > 0)
					{
						out[a] = e2;
						out[a + 1] = e1;
						change = true;
						break;
					}
				}

				if (!change)
					break;
			}

			return out;
		}
		catch (Exception var6)
		{
			return this.getAspects();
		}
	}

	public Aspect[] getAspectsSortedAmount()
	{
		try
		{
			Aspect[] out = this.aspects.keySet().toArray(new Aspect[1]);
			boolean change = false;

			while (true)
			{
				change = false;

				for (int a = 0; a < out.length - 1; ++a)
				{
					int e1 = this.getAmount(out[a]);
					int e2 = this.getAmount(out[a + 1]);
					if (e1 > 0 && e2 > 0 && e2 > e1)
					{
						Aspect ea = out[a];
						Aspect eb = out[a + 1];
						out[a] = eb;
						out[a + 1] = ea;
						change = true;
						break;
					}
				}

				if (!change)
					break;
			}

			return out;
		}
		catch (Exception var8)
		{
			return this.getAspects();
		}
	}

	public int getAmount(Aspect key)
	{
		return this.aspects.get(key) == null ? 0 : this.aspects.get(key);
	}

	public boolean reduce(Aspect key, int amount)
	{
		if (this.getAmount(key) >= amount)
		{
			int am = this.getAmount(key) - amount;
			this.aspects.put(key, am);
			return true;
		}
		return false;
	}

	public AspectList remove(Aspect key, int amount)
	{
		int am = this.getAmount(key) - amount;
		if (am <= 0)
			this.aspects.remove(key);
		else
			this.aspects.put(key, am);

		return this;
	}

	public AspectList remove(Aspect key)
	{
		this.aspects.remove(key);
		return this;
	}

	public AspectList add(Aspect aspect, int amount)
	{
		if (this.aspects.containsKey(aspect))
		{
			int oldamount = this.aspects.get(aspect);
			amount += oldamount;
		}

		this.aspects.put(aspect, amount);
		return this;
	}

	public AspectList merge(Aspect aspect, int amount)
	{
		if (this.aspects.containsKey(aspect))
		{
			int oldamount = this.aspects.get(aspect);
			if (amount < oldamount)
				amount = oldamount;
		}

		this.aspects.put(aspect, amount);
		return this;
	}

	public AspectList add(AspectList in)
	{
		for (Aspect a : in.getAspects())
		{
			this.add(a, in.getAmount(a));
		}

		return this;
	}

	public AspectList merge(AspectList in)
	{
		for (Aspect a : in.getAspects())
		{
			this.merge(a, in.getAmount(a));
		}

		return this;
	}

	public void readFromNBT(NBTTagCompound nbttagcompound)
	{
		this.aspects.clear();
		NBTTagList tlist = nbttagcompound.getTagList("Aspects", 10);

		for (int j = 0; j < tlist.tagCount(); ++j)
		{
			NBTTagCompound rs = tlist.getCompoundTagAt(j);
			if (rs.hasKey("key"))
				this.add(Aspect.getAspect(rs.getString("key")), rs.getInteger("amount"));
		}

	}

	public void readFromNBT(NBTTagCompound nbttagcompound, String label)
	{
		this.aspects.clear();
		NBTTagList tlist = nbttagcompound.getTagList(label, 10);

		for (int j = 0; j < tlist.tagCount(); ++j)
		{
			NBTTagCompound rs = tlist.getCompoundTagAt(j);
			if (rs.hasKey("key"))
				this.add(Aspect.getAspect(rs.getString("key")), rs.getInteger("amount"));
		}

	}

	public void writeToNBT(NBTTagCompound nbttagcompound)
	{
		NBTTagList tlist = new NBTTagList();
		nbttagcompound.setTag("Aspects", tlist);

		for (Aspect aspect : this.getAspects())
		{
			if (aspect != null)
			{
				NBTTagCompound f = new NBTTagCompound();
				f.setString("key", aspect.getTag());
				f.setInteger("amount", this.getAmount(aspect));
				tlist.appendTag(f);
			}
		}

	}

	public void writeToNBT(NBTTagCompound nbttagcompound, String label)
	{
		NBTTagList tlist = new NBTTagList();
		nbttagcompound.setTag(label, tlist);

		for (Aspect aspect : this.getAspects())
		{
			if (aspect != null)
			{
				NBTTagCompound f = new NBTTagCompound();
				f.setString("key", aspect.getTag());
				f.setInteger("amount", this.getAmount(aspect));
				tlist.appendTag(f);
			}
		}
	}
}
