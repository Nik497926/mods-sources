package thaumcraft.common.entities.ai.inventory;

import com.gamerforea.thaumcraft.ModUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import thaumcraft.common.config.Config;
import thaumcraft.common.entities.golems.EntityGolemBase;
import thaumcraft.common.lib.utils.InventoryUtils;

import java.util.List;

public class AIItemPickup extends EntityAIBase
{
	private EntityGolemBase theGolem;
	private Entity targetEntity;
	int count = 0;

	public AIItemPickup(EntityGolemBase par1EntityCreature)
	{
		this.theGolem = par1EntityCreature;
		this.setMutexBits(3);
	}

	@Override
	public boolean shouldExecute()
	{
		return this.theGolem.ticksExisted % Config.golemDelay <= 0 && this.findItem();
	}

	private boolean findItem()
	{
		double range = Double.MAX_VALUE;
		float dmod = this.theGolem.getRange();
		List<Entity> targets = this.theGolem.worldObj.getEntitiesWithinAABBExcludingEntity(this.theGolem, AxisAlignedBB.getBoundingBox(this.theGolem.getHomePosition().posX, this.theGolem.getHomePosition().posY, this.theGolem.getHomePosition().posZ, this.theGolem.getHomePosition().posX + 1, this.theGolem.getHomePosition().posY + 1, this.theGolem.getHomePosition().posZ + 1).expand(dmod, dmod, dmod));
		if (targets.size() == 0)
			return false;
		else
		{
			for (Entity e : targets)
			{
				// TODO gamerforEA code start
				if (e == null || e.isDead)
					continue;
				// TODO gamerforEA code end

				if (e instanceof EntityItem && ((EntityItem) e).delayBeforeCanPickup < 5 && (this.theGolem.inventory.allEmpty() || this.theGolem.inventory.getAmountNeededSmart(((EntityItem) e).getEntityItem(), this.theGolem.getUpgradeAmount(5) > 0) > 0) && (this.theGolem.getCarried() == null || InventoryUtils.areItemStacksEqualStrict(this.theGolem.getCarried(), ((EntityItem) e).getEntityItem()) && ((EntityItem) e).getEntityItem().stackSize <= this.theGolem.getCarrySpace()))
				{
					double distance = e.getDistanceSq(this.theGolem.getHomePosition().posX + 0.5F, this.theGolem.getHomePosition().posY + 0.5F, this.theGolem.getHomePosition().posZ + 0.5F);
					double distance2 = e.getDistanceSq(this.theGolem.posX, this.theGolem.posY, this.theGolem.posZ);
					if (distance2 < range && distance <= dmod * dmod)
					{
						range = distance2;
						this.targetEntity = e;
					}
				}
			}

			return this.targetEntity != null;
		}
	}

	@Override
	public boolean continueExecuting()
	{
		return this.count-- > 0 && !this.theGolem.getNavigator().noPath() && this.targetEntity.isEntityAlive();
	}

	@Override
	public void resetTask()
	{
		this.count = 0;
		this.targetEntity = null;
		this.theGolem.getNavigator().clearPathEntity();
	}

	@Override
	public void updateTask()
	{
		this.theGolem.getLookHelper().setLookPositionWithEntity(this.targetEntity, 30.0F, 30.0F);
		double dist = this.theGolem.getDistanceSqToEntity(this.targetEntity);
		if (dist <= 2.0D)
			this.pickUp();

	}

	private void pickUp()
	{
		int amount = 0;
		if (this.targetEntity instanceof EntityItem)
		{
			EntityItem entity = (EntityItem) this.targetEntity;

			// TODO gamerforEA code start
			if (entity.isDead)
				return;
			// TODO gamerforEA code end

			ItemStack copy = entity.getEntityItem().copy();
			if (entity.getEntityItem().stackSize < this.theGolem.getCarrySpace())
				amount = entity.getEntityItem().stackSize;
			else
				amount = this.theGolem.getCarrySpace();

			copy.stackSize = amount;

			// TODO gamerforEA code replace, old code: entity.getEntityItem().stackSize -= amount;
			ItemStack entityStack = entity.getEntityItem();
			entityStack.stackSize -= amount;
			entity.setEntityItemStack(entityStack);
			if (entityStack.stackSize <= 0)
				ModUtils.killEntity(entity);
			// TODO gamerforEA code end

			if (this.theGolem.getCarried() == null)
				this.theGolem.setCarried(copy);
			else
				this.theGolem.getCarried().stackSize += amount;
		}

		if (amount != 0)
			this.targetEntity.worldObj.playSoundAtEntity(this.targetEntity, "random.pop", 0.2F, ((this.targetEntity.worldObj.rand.nextFloat() - this.targetEntity.worldObj.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
	}

	@Override
	public void startExecuting()
	{
		this.count = 200;
		this.theGolem.getNavigator().tryMoveToEntityLiving(this.targetEntity, this.theGolem.getAIMoveSpeed());
	}
}
