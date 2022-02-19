package thaumcraft.common.entities.projectile;

import com.gamerforea.thaumcraft.EventConfig;
import com.gamerforea.thaumcraft.entity.EntityThrowableByPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import thaumcraft.api.entities.ITaintedMob;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.lib.utils.Utils;
import thaumcraft.common.lib.world.ThaumcraftWorldGenerator;

import java.util.List;

// TODO gamerforEA code replace EntityThrowable to EntityThrowableByPlayer
public class EntityBottleTaint extends EntityThrowableByPlayer
{
	public EntityBottleTaint(World p_i1788_1_)
	{
		super(p_i1788_1_);
	}

	public EntityBottleTaint(World p_i1790_1_, EntityLivingBase par2EntityLiving)
	{
		super(p_i1790_1_, par2EntityLiving);
	}

	@Override
	protected float getGravityVelocity()
	{
		return 0.05F;
	}

	@Override
	protected float func_70182_d()
	{
		return 0.5F;
	}

	@Override
	protected float func_70183_g()
	{
		return -20.0F;
	}

	@Override
	protected void onImpact(MovingObjectPosition p_70184_1_)
	{
		if (!this.worldObj.isRemote)
		{
			List<EntityLivingBase> ents = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(this.posX, this.posY, this.posZ, this.posX, this.posY, this.posZ).expand(5.0D, 5.0D, 5.0D));
			if (ents.size() > 0)
				for (EntityLivingBase el : ents)
				{
					if (!(el instanceof ITaintedMob) && !el.isEntityUndead())
					{
						// TODO gamerforEA code start
						if (this.fake.cantDamage(el))
							continue;
						// TODO gamerforEA cod end

						el.addPotionEffect(new PotionEffect(Config.potionTaintPoisonID, 100, 0, false));
					}
				}

			// TODO gamerforEA code start
			if (!EventConfig.enableBottleTaint)
			{
				this.setDead();
				return;
			}
			// TODO gamerforEA code end

			int x = (int) this.posX;
			int y = (int) this.posY;
			int z = (int) this.posZ;

			for (int a = 0; a < 10; ++a)
			{
				int xx = x + (int) ((this.rand.nextFloat() - this.rand.nextFloat()) * 5.0F);
				int zz = z + (int) ((this.rand.nextFloat() - this.rand.nextFloat()) * 5.0F);
				if (this.worldObj.rand.nextBoolean() && this.worldObj.getBiomeGenForCoords(xx, zz) != ThaumcraftWorldGenerator.biomeTaint)
				{
					// TODO gamerforEA code start
					if (this.fake.cantBreak(xx, y, zz))
						continue;
					// TODO gamerforEA code end

					Utils.setBiomeAt(this.worldObj, xx, zz, ThaumcraftWorldGenerator.biomeTaint);
					if (this.worldObj.isBlockNormalCubeDefault(xx, y - 1, zz, false) && this.worldObj.getBlock(xx, y, zz).isReplaceable(this.worldObj, xx, y, zz))
						this.worldObj.setBlock(xx, y, zz, ConfigBlocks.blockTaintFibres, 0, 3);
				}
			}

			this.setDead();
		}
		else
		{
			for (int a = 0; a < Thaumcraft.proxy.particleCount(100); ++a)
			{
				Thaumcraft.proxy.taintsplosionFX(this);
			}

			Thaumcraft.proxy.bottleTaintBreak(this.worldObj, this.posX, this.posY, this.posZ);
		}

	}
}
