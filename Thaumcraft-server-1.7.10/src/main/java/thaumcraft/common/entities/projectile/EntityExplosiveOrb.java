package thaumcraft.common.entities.projectile;

import com.gamerforea.thaumcraft.entity.EntityThrowableByPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import thaumcraft.common.Thaumcraft;

// TODO gamerforEA code replace EntityThrowable to EntityThrowableByPlayer
public class EntityExplosiveOrb extends EntityThrowableByPlayer
{
	public float strength = 1.0F;
	public boolean onFire = false;

	public EntityExplosiveOrb(World par1World)
	{
		super(par1World);
	}

	public EntityExplosiveOrb(World par1World, EntityLivingBase par2EntityLiving)
	{
		super(par1World, par2EntityLiving);
	}

	@Override
	protected float getGravityVelocity()
	{
		return 0.01F;
	}

	@Override
	protected void onImpact(MovingObjectPosition mop)
	{
		if (!this.worldObj.isRemote)
		{
			// TODO gamerforEA add condition [2]
			if (mop.entityHit != null && !this.fake.cantDamage(mop.entityHit))
				mop.entityHit.attackEntityFrom(causeFireballDamage(this, this.getThrower()), this.strength * 1.5F);

			// TODO gamerforEA use ExplosionByPlayer and disable flame (this.onFire)
			this.fake.newExplosion(null, this.posX, this.posY, this.posZ, this.strength, false, false);

			this.setDead();
		}

		this.setDead();
	}

	public static DamageSource causeFireballDamage(EntityExplosiveOrb p_76362_0_, Entity p_76362_1_)
	{
		return p_76362_1_ == null ? new EntityDamageSourceIndirect("onFire", p_76362_0_, p_76362_0_).setFireDamage().setProjectile() : new EntityDamageSourceIndirect("fireball", p_76362_0_, p_76362_1_).setFireDamage().setProjectile();
	}

	@Override
	public float getShadowSize()
	{
		return 0.1F;
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
		if (this.worldObj.isRemote)
			Thaumcraft.proxy.drawGenericParticles(this.worldObj, this.prevPosX + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.3F, this.prevPosY + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.3F, this.prevPosZ + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.3F, 0.0D, 0.0D, 0.0D, 1.0F, 1.0F, 1.0F, 0.8F, false, 151, 9, 1, 7 + this.rand.nextInt(5), 0, 2.0F + this.rand.nextFloat());

		if (this.ticksExisted > 500)
			this.setDead();

	}

	@Override
	public boolean attackEntityFrom(DamageSource p_70097_1_, float p_70097_2_)
	{
		if (this.isEntityInvulnerable())
			return false;
		else
		{
			this.setBeenAttacked();
			if (p_70097_1_.getEntity() != null)
			{
				Vec3 vec3 = p_70097_1_.getEntity().getLookVec();
				if (vec3 != null)
				{
					this.motionX = vec3.xCoord;
					this.motionY = vec3.yCoord;
					this.motionZ = vec3.zCoord;
					this.motionX *= 0.9D;
					this.motionY *= 0.9D;
					this.motionZ *= 0.9D;
				}

				return true;
			}
			else
				return false;
		}
	}
}
