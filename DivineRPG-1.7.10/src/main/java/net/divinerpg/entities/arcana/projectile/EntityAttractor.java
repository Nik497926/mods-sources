package net.divinerpg.entities.arcana.projectile;

import com.gamerforea.divinerpg.ModUtils;
import com.gamerforea.eventhelper.fake.FakePlayerContainer;
import com.gamerforea.eventhelper.fake.FakePlayerContainerEntity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityAttractor extends EntityThrowable
{
	int age;

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

	public EntityAttractor(World w)
	{
		super(w);
	}

	public EntityAttractor(World w, EntityLivingBase e)
	{
		super(w, e);
		this.motionX *= 3;
		this.motionY *= 3;
		this.motionZ *= 3;

		// TODO gamerforEA code start
		if (e instanceof EntityPlayer)
			this.fake.setRealPlayer((EntityPlayer) e);
		// TODO gamerforEA code end
	}

	@Override
	protected void onImpact(MovingObjectPosition pos)
	{
		// TODO gamerforEA add condition
		if (pos.entityHit != null && this.getThrower() != null && !this.fake.cantDamage(pos.entityHit))
		{
			double xDist = (this.getThrower().posX - pos.entityHit.posX) / 5, yDist = (this.getThrower().posY - pos.entityHit.posY) / 5, zDist = (this.getThrower().posZ - pos.entityHit.posZ) / 5;
			pos.entityHit.addVelocity(xDist, yDist, zDist);
		}
		this.setDead();
	}

	@Override
	public float getGravityVelocity()
	{
		return 0;
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
		this.age++;
		if (this.age > 18)
			this.setDead();//I'm an Adult!
	}
}
