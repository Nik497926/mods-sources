/**
 * @author wendoxdc 22.08.2020
 */
package wendoxdc.nbtutils;

import net.minecraft.nbt.NBTTagCompound;

public interface INBTDataSaver {
	public void writeToNBT(NBTTagCompound compound);

	public void readFromNBT(NBTTagCompound compound);

	public String fileName();

	public long updateTime();
}
