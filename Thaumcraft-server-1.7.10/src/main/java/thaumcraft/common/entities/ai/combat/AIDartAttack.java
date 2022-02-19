package thaumcraft.common.entities.ai.combat;

import com.gamerforea.thaumcraft.EventConfig;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import thaumcraft.common.entities.golems.EntityGolemBase;

public class AIDartAttack extends EntityAIBase
{
	private final EntityGolemBase theGolem;
	private EntityLivingBase attackTarget;
	private int rangedAttackTime = 0;
	private int maxRangedAttackTime;

	// TODO gamerforEA code start
	private int counter;

	@Override
	public void startExecuting()
	{
		this.counter = 0;
		super.startExecuting();
	}
	// TODO gamerforEA code end

	public AIDartAttack(EntityGolemBase par1IRangedAttackMob)
	{
		this.theGolem = par1IRangedAttackMob;
		this.maxRangedAttackTime = 30 - this.theGolem.getUpgradeAmount(0) * 8;
		this.rangedAttackTime = this.maxRangedAttackTime / 2;
		this.setMutexBits(3);
	}

	@Override
	public boolean shouldExecute()
	{
		EntityLivingBase attackTarget = this.theGolem.getAttackTarget();
		if (attackTarget == null)
			return false;

		// TODO gamerforEA add condition [2]
		if (!this.theGolem.isValidTarget(attackTarget) || this.theGolem.fake.cantDamage(attackTarget))
		{
			this.theGolem.setAttackTarget(null);
			return false;
		}

		double ra = this.theGolem.getDistanceSq(attackTarget.posX, attackTarget.boundingBox.minY, attackTarget.posZ);
		if (ra < 9.0D)
			return false;
		this.attackTarget = attackTarget;
		return true;
	}

	@Override
	public boolean continueExecuting()
	{
		return this.shouldExecute() && !this.theGolem.getNavigator().noPath();
	}

	@Override
	public void resetTask()
	{
		this.attackTarget = null;
		this.rangedAttackTime = this.maxRangedAttackTime / 2;
	}

	@Override
	public void updateTask()
	{
		double distanceSq = this.theGolem.getDistanceSq(this.attackTarget.posX, this.attackTarget.boundingBox.minY, this.attackTarget.posZ);
		boolean canSee = this.theGolem.getEntitySenses().canSee(this.attackTarget);

		// TODO gamerforEA code start
		boolean optimize = EventConfig.golemAiOptimize;
		if (!optimize || --this.counter <= 0)
		// TODO gamerforEA code end
		{
			// TODO gamerforEA code start
			if (optimize)
				this.counter = 4 + this.theGolem.getRNG().nextInt(7);
			// TODO gamerforEA code end

			this.theGolem.getNavigator().tryMoveToEntityLiving(this.attackTarget, this.theGolem.getAIMoveSpeed());
		}

		if (canSee)
		{
			this.theGolem.getLookHelper().setLookPositionWithEntity(this.attackTarget, 30.0F, 30.0F);
			this.rangedAttackTime = Math.max(this.rangedAttackTime - 1, 0);
			if (this.rangedAttackTime <= 0)
			{
				float maxDistanceSq = this.theGolem.getRange() * 0.8F;
				maxDistanceSq = maxDistanceSq * maxDistanceSq;
				if (distanceSq <= maxDistanceSq && canSee)
				{
					this.theGolem.attackEntityWithRangedAttack(this.attackTarget);
					this.rangedAttackTime = this.maxRangedAttackTime;
				}
			}
		}

	}
}
