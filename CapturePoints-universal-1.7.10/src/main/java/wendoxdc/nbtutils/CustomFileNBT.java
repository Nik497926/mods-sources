/**
 * @author wendoxdc 22.08.2020
 */

package wendoxdc.nbtutils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;

public class CustomFileNBT {
	public static boolean hasWriteUpdate = false;
	private static ArrayList<INBTDataSaver> nbtListeners = new ArrayList<INBTDataSaver>();

	public static void register(INBTDataSaver nbt) {
		for (INBTDataSaver listener : nbtListeners) {
			if (nbt.fileName().equals(listener.fileName()))
				return;
		}
		nbtListeners.add(nbt);
	}

	public static void tick(long tick) {
		nbtListeners.forEach(nbt -> {
			if (tick % nbt.updateTime() == 0) {
				if (!hasWriteUpdate) {
					nbt.readFromNBT(readCompoundByFileName(nbt.fileName()));
					NBTTagCompound compound = new NBTTagCompound();
					nbt.writeToNBT(compound);
					writeCompoundByFileName(nbt.fileName(), compound);
				} else {
					NBTTagCompound compound = new NBTTagCompound();
					nbt.writeToNBT(compound);
					writeCompoundByFileName(nbt.fileName(), compound);
					nbt.readFromNBT(readCompoundByFileName(nbt.fileName()));
					hasWriteUpdate = true;
				}
			}
		});
	}

	public static NBTTagCompound readCompoundByFileName(String name) {
		try {
			File f = new File(name + ".wendoxnbt");
			if (!f.exists()) {
				f.createNewFile();
				writeCompoundByFileName(name, new NBTTagCompound());
			}
			FileInputStream stream = new FileInputStream(f);
			return CompressedStreamTools.readCompressed(stream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void writeCompoundByFileName(String name, NBTTagCompound compound) {
		try {
			File f = new File(name + ".wendoxnbt");
			if (!f.exists())
				f.createNewFile();
			FileOutputStream fileoutputstream = new FileOutputStream(f);
			CompressedStreamTools.writeCompressed(compound, fileoutputstream);
			fileoutputstream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
