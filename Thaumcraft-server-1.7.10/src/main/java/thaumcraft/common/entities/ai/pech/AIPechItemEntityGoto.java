package thaumcraft.common.entities.ai.pech;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathPoint;
import thaumcraft.common.config.Config;
import thaumcraft.common.entities.monster.EntityPech;

import java.util.List;

public class AIPechItemEntityGoto extends EntityAIBase
{
	private EntityPech pech;
	private Entity targetEntity;
	float maxTargetDistance = 16.0F;
	private int count;
	private int failedPathFindingPenalty;

	public AIPechItemEntityGoto(EntityPech par1EntityCreature)
	{
		this.pech = par1EntityCreature;
		this.setMutexBits(3);
	}

	@Override
	public boolean shouldExecute()
	{
		if (this.pech.ticksExisted % Config.golemDelay > 0)
			return false;
		if (--this.count > 0)
			return false;
		double range = Double.MAX_VALUE;
		List<Entity> targets = this.pech.worldObj.getEntitiesWithinAABBExcludingEntity(this.pech, this.pech.boundingBox.expand((double) this.maxTargetDistance, (double) this.maxTargetDistance, (double) this.maxTargetDistance));
		if (targets.size() == 0)
			return false;
		for (Entity entity : targets)
		{
			// TODO gamerforEA code start
			if (entity.isDead)
				continue;
			// TODO gamerforEA code end

			if (entity instanceof EntityItem && this.pech.canPickup(((EntityItem) entity).getEntityItem()))
			{
				NBTTagCompound itemData = entity.getEntityData();
				String username = ((EntityItem) entity).func_145800_j();
				if (username == null || !username.equals("PechDrop"))
				{
					double distance = entity.getDistanceSq(this.pech.posX, this.pech.posY, this.pech.posZ);
					if (distance < range && distance <= (double) (this.maxTargetDistance * this.maxTargetDistance))
					{
						range = distance;
						this.targetEntity = entity;
					}
				}
			}
		}

		return this.targetEntity != null;
	}

	@Override
	public boolean continueExecuting()
	{
		return this.targetEntity != null && this.targetEntity.isEntityAlive() && !this.pech.getNavigator().noPath() && this.targetEntity.getDistanceSqToEntity(this.pech) < (double) (this.maxTargetDistance * this.maxTargetDistance);
	}

	@Override
	public void resetTask()
	{
		this.targetEntity = null;
	}

	@Override
	public void startExecuting()
	{
		this.pech.getNavigator().setPath(this.pech.getNavigator().getPathToEntityLiving(this.targetEntity), this.pech.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue() * 1.5D);
		this.count = 0;
	}

	@Override
	public void updateTask()
	{
		this.pech.getLookHelper().setLookPositionWithEntity(this.targetEntity, 30.0F, 30.0F);
		if (this.pech.getEntitySenses().canSee(this.targetEntity) && --this.count <= 0)
		{
			this.count = this.failedPathFindingPenalty + 4 + this.pech.getRNG().nextInt(4);
			this.pech.getNavigator().tryMoveToEntityLiving(this.targetEntity, this.pech.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue() * 1.5D);
			if (this.pech.getNavigator().getPath() != null)
			{
				PathPoint finalPathPoint = this.pech.getNavigator().getPath().getFinalPathPoint();
				if (finalPathPoint != null && this.targetEntity.getDistanceSq((double) finalPathPoint.xCoord, (double) finalPathPoint.yCoord, (double) finalPathPoint.zCoord) < 1.0D)
					this.failedPathFindingPenalty = 0;
				else
					this.failedPathFindingPenalty += 10;
			}
			else
				this.failedPathFindingPenalty += 10;
		}

		double distance = this.pech.getDistanceSq(this.targetEntity.posX, this.targetEntity.boundingBox.minY, this.targetEntity.posZ);
		if (distance <= 1.5D)
		{
			this.count = 0;
			int am = ((EntityItem) this.targetEntity).getEntityItem().stackSize;
			ItemStack is = this.pech.pickupItem(((EntityItem) this.targetEntity).getEntityItem());
			if (is != null && is.stackSize > 0)
				((EntityItem) this.targetEntity).setEntityItemStack(is);
			else
				this.targetEntity.setDead();

			if (is == null || is.stackSize != am)
				this.targetEntity.worldObj.playSoundAtEntity(this.targetEntity, "random.pop", 0.2F, ((this.targetEntity.worldObj.rand.nextFloat() - this.targetEntity.worldObj.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
		}

	}
}
