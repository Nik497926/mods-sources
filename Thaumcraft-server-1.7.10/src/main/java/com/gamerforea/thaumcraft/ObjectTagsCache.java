package com.gamerforea.thaumcraft;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Table;
import cpw.mods.fml.common.registry.GameData;
import net.minecraft.item.Item;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.AspectList;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class ObjectTagsCache
{
	public static final ObjectTagsCache INSTANCE = new ObjectTagsCache();
	private final Table<Item, Integer, AspectList> allObjectTags = HashBasedTable.create();
	private final Table<Item, Integer, AspectList> simpleObjectTags = HashBasedTable.create();
	private final ListMultimap<Item, Pair<int[], AspectList>> rangedObjectTags = ArrayListMultimap.create();
	private int prevOriginalSize;

	private ObjectTagsCache()
	{
	}

	public AspectList getAspectList(Item item, int meta)
	{
		return this.getAspectList(item, meta, false);
	}

	public AspectList getAspectList(Item item, int meta, boolean useMergedTable)
	{
		this.tryUpdateCache();
		Table<Item, Integer, AspectList> table = useMergedTable ? this.allObjectTags : this.simpleObjectTags;
		return table.get(item, meta);
	}

	public AspectList getAspectListInRange(Item item, int minMetaInclusive, int maxMetaExclusive)
	{
		return this.getAspectListInRange(item, minMetaInclusive, maxMetaExclusive, false);
	}

	public AspectList getAspectListInRange(Item item, int minMetaInclusive, int maxMetaExclusive, boolean useMergedTable)
	{
		this.tryUpdateCache();
		Table<Item, Integer, AspectList> table = useMergedTable ? this.allObjectTags : this.simpleObjectTags;
		for (int meta = minMetaInclusive; meta < maxMetaExclusive; meta++)
		{
			AspectList aspectList = table.get(item, meta);
			if (aspectList != null)
				return aspectList;
		}
		return null;
	}

	public List<Pair<int[], AspectList>> getRangedObjectTags(Item item)
	{
		this.tryUpdateCache();
		return this.rangedObjectTags.get(item);
	}

	public void resetCache()
	{
		this.prevOriginalSize = 0;
		this.allObjectTags.clear();
		this.simpleObjectTags.clear();
		this.rangedObjectTags.clear();
	}

	public void tryUpdateCache()
	{
		ConcurrentHashMap<List, AspectList> original = ThaumcraftApi.objectTags;
		int size = original.size();
		if (this.prevOriginalSize != size)
			this.forceUpdateCache();
	}

	public void forceUpdateCache()
	{
		this.resetCache();

		ConcurrentHashMap<List, AspectList> original = ThaumcraftApi.objectTags;
		this.prevOriginalSize = original.size();

		for (Map.Entry<List, AspectList> entry : original.entrySet())
		{
			List key = entry.getKey();
			AspectList aspectList = entry.getValue();

			boolean success = false;

			if (key.size() >= 2)
			{
				Object itemObj = key.get(0);
				if (itemObj instanceof Item)
				{
					Item item = (Item) itemObj;
					Object subKey = key.get(1);
					if (subKey instanceof Number)
					{
						int meta = ((Number) subKey).intValue();
						this.simpleObjectTags.put(item, meta, aspectList);
						AspectList prevAspectList = this.allObjectTags.put(item, meta, aspectList);
						if (prevAspectList != null)
							ModUtils.LOGGER.warn("{}@{} replaced in merged object tags table from {} to {}", GameData.getItemRegistry().getNameForObject(item), meta, prevAspectList, aspectList);
						success = true;
					}
					else if (subKey instanceof int[])
					{
						int[] range = (int[]) subKey;
						if (range.length > 0)
						{
							int[] rangeCopy = ArrayUtils.clone(range);
							Arrays.sort(rangeCopy);
							this.rangedObjectTags.put(item, new ImmutablePair<>(rangeCopy, aspectList));
							for (int meta : rangeCopy)
							{
								AspectList prevAspectList = this.allObjectTags.put(item, meta, aspectList);
								if (prevAspectList != null)
									ModUtils.LOGGER.warn("{}@{} replaced in merged object tags table from {} to {}", GameData.getItemRegistry().getNameForObject(item), meta, prevAspectList, aspectList);
							}
							success = true;
						}
					}
				}
			}

			if (!success)
				ModUtils.LOGGER.warn("Invalid ThaumcraftApi.objectTags key: {}", key);
		}
	}
}
