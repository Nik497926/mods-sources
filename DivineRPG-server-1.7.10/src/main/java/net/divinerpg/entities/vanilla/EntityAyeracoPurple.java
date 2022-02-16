package net.divinerpg.entities.vanilla;

import net.divinerpg.utils.Util;
import net.divinerpg.utils.items.VanillaItemsWeapons;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityAyeracoPurple extends EntityAyeraco
{

	private EntityAyeraco aGreen;
	private EntityAyeraco aBlue;
	private EntityAyeraco aRed;
	private EntityAyeraco aYellow;
	private String greenUUID;
	private String blueUUID;
	private String redUUID;
	private String yellowUUID;

	public EntityAyeracoPurple(World par1World)
	{
		super(par1World, "Purple");
	}

	public void initOthers(EntityAyeraco par2, EntityAyeraco par3, EntityAyeraco par4, EntityAyeraco par5)
	{
		this.aGreen = par2;
		this.aBlue = par3;
		this.aRed = par4;
		this.aYellow = par5;
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
		return this.aGreen != null && this.aGreen.abilityActive();
	}

	@Override
	protected boolean canTeleport()
	{
		return true;
	}

	@Override
	protected void dropRareDrop(int par1)
	{
		this.dropItem(VanillaItemsWeapons.enderSword, 1);
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
		if (!this.worldObj.isRemote)
		{
			if (this.aGreen == null && this.greenUUID != null)
			{
				this.aGreen = (EntityAyeraco) Util.findEntityByUUID(this.greenUUID, this.worldObj);
				this.greenUUID = null;
			}
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
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);

		this.greenUUID = tag.getString("greenUUID");
		this.blueUUID = tag.getString("blueUUID");
		this.redUUID = tag.getString("redUUID");
		this.yellowUUID = tag.getString("yellowUUID");
	}

	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);

		/* TODO gamerforEA code replace, old code:
		tag.setString("greenUUID", this.aGreen.getPersistentID().toString());
		tag.setString("blueUUID", this.aBlue.getPersistentID().toString());
		tag.setString("redUUID", this.aRed.getPersistentID().toString());
		tag.setString("yellowUUID", this.aYellow.getPersistentID().toString()); */
		if (this.aGreen != null && this.aGreen.getPersistentID() != null)
			tag.setString("greenUUID", this.aGreen.getPersistentID().toString());

		if (this.aBlue != null && this.aBlue.getPersistentID() != null)
			tag.setString("blueUUID", this.aBlue.getPersistentID().toString());

		if (this.aRed != null && this.aRed.getPersistentID() != null)
			tag.setString("redUUID", this.aRed.getPersistentID().toString());

		if (this.aYellow != null && this.aYellow.getPersistentID() != null)
			tag.setString("yellowUUID", this.aYellow.getPersistentID().toString());
		// TODO gamerforEA code end
	}
}
