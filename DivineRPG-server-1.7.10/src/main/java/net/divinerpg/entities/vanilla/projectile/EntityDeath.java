package net.divinerpg.entities.vanilla.projectile;

import com.gamerforea.divinerpg.ModUtils;
import com.gamerforea.eventhelper.fake.FakePlayerContainer;
import com.gamerforea.eventhelper.fake.FakePlayerContainerEntity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityDeath extends EntityThrowable
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

	public EntityDeath(World var1)
	{
		super(var1);
	}

	public EntityDeath(World var1, EntityLivingBase e)
	{
		super(var1, e);

		// TODO gamerforEA code start
		if (e instanceof EntityPlayer)
			this.fake.setRealPlayer((EntityPlayer) e);
		// TODO gamerforEA code end
	}

	public EntityDeath(World var1, double var2, double var4, double var6)
	{
		super(var1, var2, var4, var6);
	}

	@Override
	protected void onImpact(MovingObjectPosition mop)
	{
		// TODO gamerforEA add condition
		if (mop.entityHit != null && !this.fake.cantDamage(mop.entityHit))
		{
			mop.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 14.0F);
			if (mop.entityHit instanceof EntityLivingBase)
				((EntityLivingBase) mop.entityHit).addPotionEffect(new PotionEffect(Potion.poison.id, 45, 3));
		}

		if (!this.worldObj.isRemote)
			this.setDead();
	}
}
