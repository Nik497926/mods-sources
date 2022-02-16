package net.divinerpg.entities.arcana.projectile;

import com.gamerforea.divinerpg.ExplosionByPlayer;
import com.gamerforea.divinerpg.ModUtils;
import com.gamerforea.eventhelper.fake.FakePlayerContainer;
import com.gamerforea.eventhelper.fake.FakePlayerContainerEntity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityMeteor extends EntityThrowable
{
	// TODO gamerforEA code start
	public final FakePlayerContainer fake = new FakePlayerContainerEntity(ModUtils.profile, this);

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
	// TODO gamerforEA code end

	public EntityMeteor(World par1World)
	{
		super(par1World);
		this.motionX = this.rand.nextGaussian() * 0.05;
		this.motionZ = this.rand.nextGaussian() * 0.05;
		this.motionY = -0.5;
	}

	public EntityMeteor(World par1World, EntityLiving par2EntityLiving)
	{
		super(par1World, par2EntityLiving);
		this.motionX = this.rand.nextGaussian() * 0.05;
		this.motionZ = this.rand.nextGaussian() * 0.05;
		this.motionY = -0.5;
	}

	public EntityMeteor(World par1World, double par2, double par4, double par6)
	{
		super(par1World, par2, par4, par6);
		this.motionX = this.rand.nextGaussian() * 0.05;
		this.motionZ = this.rand.nextGaussian() * 0.05;
		this.motionY = -0.5;
	}

	@Override
	protected void onImpact(MovingObjectPosition mop)
	{
		// TODO gamerforEA add condition [2]
		if (mop.entityHit != null && !this.fake.cantDamage(mop.entityHit))
			mop.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 12);

		// TODO gamerforEA use ExplosionByPlayer
		ExplosionByPlayer.createExplosion(this.fake.get(), this.worldObj, this, this.posX, this.posY, this.posZ, 4.5F, false);

		if (!this.worldObj.isRemote)
			this.setDead();
	}

	@Override
	protected float getGravityVelocity()
	{
		return 0.07F;
	}
}
