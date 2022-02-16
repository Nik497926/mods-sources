package net.divinerpg.entities.vanilla;

import net.divinerpg.utils.Util;
import net.divinerpg.utils.items.VanillaItemsWeapons;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityAyeracoGreen extends EntityAyeraco
{

	private EntityAyeraco aBlue;
	private EntityAyeraco aRed;
	private EntityAyeraco aYellow;
	private EntityAyeraco aPurple;
	private String blueUUID;
	private String redUUID;
	private String yellowUUID;
	private String purpleUUID;

	public EntityAyeracoGreen(World par1World)
	{
		super(par1World, "Green");
	}

	public void initOthers(EntityAyeraco par2, EntityAyeraco par3, EntityAyeraco par4, EntityAyeraco par5)
	{
		this.aBlue = par2;
		this.aRed = par3;
		this.aYellow = par4;
		this.aPurple = par5;
	}

	@Override
	public void onDeath(DamageSource par1DamageSource)
	{
		super.onDeath(par1DamageSource);
		this.worldObj.setBlock(this.beamX, this.beamY, this.beamZ, Blocks.air);
	}

	@Override
	protected boolean canBlockProjectiles()
	{
		return true;
	}

	@Override
	protected boolean canTeleport()
	{
		return this.aPurple != null && this.aPurple.abilityActive();
	}

	@Override
	protected void dropRareDrop(int par1)
	{
		this.dropItem(VanillaItemsWeapons.greenEnderSword, 1);
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
		if (!this.worldObj.isRemote)
		{
			if (this.aBlue == null && this.blueUUID != null)
			{
				this.aBlue = (EntityAyeraco) Util.findEntityByUUID(this.blueUUID, this.worldObj);
				this.blueUUID = null;
			}
			if (this.aRed == null && this.redUUID != null)
			{
				this.aRed = (EntityAyeraco) Util.findEntityByUUID(this.redUUID, this.worldObj);
				this.redUUID = null;
			}
			if (this.aYellow == null && this.yellowUUID != null)
			{
				this.aYellow = (EntityAyeraco) Util.findEntityByUUID(this.yellowUUID, this.worldObj);
				this.yellowUUID = null;
			}
			if (this.aPurple == null && this.purpleUUID != null)
			{
				this.aPurple = (EntityAyeraco) Util.findEntityByUUID(this.purpleUUID, this.worldObj);
				this.purpleUUID = null;
			}
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);

		this.blueUUID = tag.getString("blueUUID");
		this.redUUID = tag.getString("redUUID");
		this.yellowUUID = tag.getString("yellowUUID");
		this.purpleUUID = tag.getString("purpleUUID");
	}

	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);

		/* TODO gamerforEA code replace, old code:
		tag.setString("blueUUID", this.aBlue.getPersistentID().toString());
		tag.setString("redUUID", this.aRed.getPersistentID().toString());
		tag.setString("yellowUUID", this.aYellow.getPersistentID().toString());
		tag.setString("purpleUUID", this.aPurple.getPersistentID().toString()); */
		if (this.aBlue != null && this.aBlue.getPersistentID() != null)
			tag.setString("blueUUID", this.aBlue.getPersistentID().toString());

		if (this.aRed != null && this.aRed.getPersistentID() != null)
			tag.setString("redUUID", this.aRed.getPersistentID().toString());

		if (this.aYellow != null && this.aYellow.getPersistentID() != null)
			tag.setString("yellowUUID", this.aYellow.getPersistentID().toString());

		if (this.aPurple != null && this.aPurple.getPersistentID() != null)
			tag.setString("purpleUUID", this.aPurple.getPersistentID().toString());
		// TODO gamerforEA code end
	}
}
