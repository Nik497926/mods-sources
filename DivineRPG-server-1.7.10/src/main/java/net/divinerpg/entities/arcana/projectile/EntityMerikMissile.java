package net.divinerpg.entities.arcana.projectile;

import com.gamerforea.divinerpg.ExplosionByPlayer;
import com.gamerforea.divinerpg.ModUtils;
import com.gamerforea.eventhelper.fake.FakePlayerContainer;
import com.gamerforea.eventhelper.fake.FakePlayerContainerEntity;
import net.divinerpg.entities.base.EntityHeatSeekingProjectile;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityMerikMissile extends EntityHeatSeekingProjectile
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

	public EntityMerikMissile(World world)
	{
		super(world);
	}

	public EntityMerikMissile(World world, EntityLivingBase shooter)
	{
		super(world, shooter);

		// TODO gamerforEA code start
		if (shooter instanceof EntityPlayer)
			this.fake.setRealPlayer((EntityPlayer) shooter);
		// TODO gamerforEA code end
	}

	@Override
	protected void onImpact(MovingObjectPosition mop)
	{
		// TODO gamerforEA add condition
		if (mop.entityHit != null && !this.fake.cantDamage(mop.entityHit))
			mop.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 22);

		if (!this.worldObj.isRemote)
		{
			// TODO gamerforEA use ExplosionByPlayer
			ExplosionByPlayer.createExplosion(this.fake.get(), this.worldObj, this, this.posX, this.posY, this.posZ, 2, false);

			this.setDead();
		}
	}
}
