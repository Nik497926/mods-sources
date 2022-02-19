package com.gamerforea.thaumcraft.tile;

import com.gamerforea.eventhelper.fake.FakePlayerContainer;
import com.gamerforea.thaumcraft.ModUtils;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public final class OwnerTileEntity extends TileEntity
{
	public final FakePlayerContainer fake = ModUtils.NEXUS_FACTORY.wrapFake(this);

	@Override
	public boolean canUpdate()
	{
		return false;
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		this.fake.writeToNBT(nbt);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		this.fake.readFromNBT(nbt);
	}
}
