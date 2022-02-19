package thaumcraft.common.lib.world.dim;

import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class MazeHandler
{
	public static ConcurrentHashMap<CellLoc, Short> labyrinth = new ConcurrentHashMap<>();

	public static synchronized void putToHashMap(CellLoc key, Cell cell)
	{
		labyrinth.put(key, cell.pack());
	}

	public static synchronized void putToHashMapRaw(CellLoc key, short cell)
	{
		labyrinth.put(key, cell);
	}

	public static synchronized Cell getFromHashMap(CellLoc key)
	{
		// TODO gamerforEA code replace, old code:
		// return labyrinth.containsKey(key) ? new Cell(labyrinth.get(key)) : null;
		Short cell = labyrinth.get(key);
		return cell == null ? null : new Cell(cell);
		// TODO gamerforEA code end
	}

	public static synchronized void removeFromHashMap(CellLoc key)
	{
		labyrinth.remove(key);
	}

	public static synchronized short getFromHashMapRaw(CellLoc key)
	{
		// TODO gamerforEA code replace, old code:
		// return labyrinth.containsKey(key) ? labyrinth.get(key) : 0;
		Short cell = labyrinth.get(key);
		return cell == null ? 0 : cell;
		// TODO gamerforEA code end
	}

	public static synchronized void clearHashMap()
	{
		labyrinth.clear();
	}

	private static void readNBT(NBTTagCompound nbt)
	{
		NBTTagList tagList = nbt.getTagList("cells", 10);

		for (int a = 0; a < tagList.tagCount(); ++a)
		{
			NBTTagCompound cell = tagList.getCompoundTagAt(a);
			int x = cell.getInteger("x");
			int z = cell.getInteger("z");
			short v = cell.getShort("cell");
			putToHashMapRaw(new CellLoc(x, z), v);
		}
	}

	private static NBTTagCompound writeNBT()
	{
		NBTTagCompound nbt = new NBTTagCompound();
		NBTTagList tagList = new NBTTagList();

		for (CellLoc loc : labyrinth.keySet())
		{
			short v = getFromHashMapRaw(loc);
			if (v > 0)
			{
				NBTTagCompound cell = new NBTTagCompound();
				cell.setInteger("x", loc.x);
				cell.setInteger("z", loc.z);
				cell.setShort("cell", v);
				tagList.appendTag(cell);
			}
		}

		nbt.setTag("cells", tagList);
		return nbt;
	}

	public static void loadMaze(World world)
	{
		clearHashMap();
		File file1 = new File(world.getSaveHandler().getWorldDirectory(), "labyrinth.dat");
		if (file1.exists())
			try
			{
				NBTTagCompound nbttagcompound = CompressedStreamTools.readCompressed(new FileInputStream(file1));
				NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag("Data");
				readNBT(nbttagcompound1);
				return;
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}

		file1 = new File(world.getSaveHandler().getWorldDirectory(), "labyrinth.dat_old");
		if (file1.exists())
			try
			{
				NBTTagCompound nbttagcompound = CompressedStreamTools.readCompressed(new FileInputStream(file1));
				NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag("Data");
				readNBT(nbttagcompound1);
				return;
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
	}

	// TODO Optimize it
	public static void saveMaze(World world)
	{
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setTag("Data", writeNBT());

		try
		{
			File file1 = new File(world.getSaveHandler().getWorldDirectory(), "labyrinth.dat_new");
			File file2 = new File(world.getSaveHandler().getWorldDirectory(), "labyrinth.dat_old");
			File file3 = new File(world.getSaveHandler().getWorldDirectory(), "labyrinth.dat");
			CompressedStreamTools.writeCompressed(nbt, new FileOutputStream(file1));
			if (file2.exists())
				file2.delete();

			file3.renameTo(file2);
			if (file3.exists())
				file3.delete();

			file1.renameTo(file3);
			if (file1.exists())
				file1.delete();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static boolean mazesInRange(int chunkX, int chunkZ, int width, int height)
	{
		for (int xOffset = -width; xOffset <= width; ++xOffset)
		{
			for (int zOffset = -height; zOffset <= height; ++zOffset)
			{
				if (getFromHashMap(new CellLoc(chunkX + xOffset, chunkZ + zOffset)) != null)
					return true;
			}
		}

		return false;
	}

	public static void generateEldritch(World world, Random random, int cx, int cz)
	{
		CellLoc loc = new CellLoc(cx, cz);
		Cell cell = getFromHashMap(loc);
		if (cell != null)
		{
			switch (cell.feature)
			{
				case 1:
					GenPortal.generatePortal(world, random, cx, cz, 50, cell);
					break;
				case 2:
				case 3:
				case 4:
				case 5:
					GenBossRoom.generateRoom(world, random, cx, cz, 50, cell);
					break;
				case 6:
					GenKeyRoom.generateRoom(world, random, cx, cz, 50, cell);
					break;
				case 7:
					GenNestRoom.generateRoom(world, random, cx, cz, 50, cell);
					break;
				case 8:
					GenLibraryRoom.generateRoom(world, random, cx, cz, 50, cell);
					break;
				case 9:
				case 10:
				case 11:
				case 12:
				case 13:
				case 14:
				default:
					generatePassage(world, random, cx, cz, 50, cell);
			}

			GenCommon.processDecorations(world);
		}
	}

	private static void generatePassage(World world, Random random, int cx, int cz, int y, Cell cell)
	{
		if (random.nextInt(1) == 0)
			GenPassage.generateDefaultPassage(world, random, cx, cz, y, cell);
	}
}
