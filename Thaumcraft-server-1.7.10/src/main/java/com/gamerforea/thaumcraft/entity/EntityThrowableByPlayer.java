package com.gamerforea.thaumcraft.entity;

import com.gamerforea.eventhelper.fake.FakePlayerContainer;
import com.gamerforea.thaumcraft.ModUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public abstract class EntityThrowableByPlayer extends EntityThrowable
{
	public final FakePlayerContainer fake = ModUtils.NEXUS_FACTORY.wrapFake(this);

	public EntityThrowableByPlayer(World world)
	{
		super(world);
	}

	public EntityThrowableByPlayer(World world, EntityLivingBase shooter)
	{
		super(world, shooter);
		this.fake.setRealPlayer(shooter);
	}

	public EntityThrowableByPlayer(World world, double x, double y, double z)
	{
		super(world, x, y, z);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		super.writeEntityToNBT(nbt);
		this.fake.writeToNBT(nbt);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		super.readEntityFromNBT(nbt);
		this.fake.readFromNBT(nbt);
	}
}
