package thaumcraft.common.entities.projectile;

import com.gamerforea.thaumcraft.entity.EntityThrowableByPlayer;
import com.gamerforea.thaumcraft.tile.OwnerTileEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import thaumcraft.codechicken.lib.math.MathHelper;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.lib.utils.EntityUtils;

import java.util.ArrayList;

// TODO gamerforEA code replace EntityThrowable to EntityThrowableByPlayer
public class EntityShockOrb extends EntityThrowableByPlayer
{
	public int area = 4;
	public int damage = 5;

	public EntityShockOrb(World par1World)
	{
		super(par1World);
	}

	public EntityShockOrb(World par1World, EntityLivingBase par2EntityLiving)
	{
		super(par1World, par2EntityLiving);
	}

	@Override
	protected float getGravityVelocity()
	{
		return 0.05F;
	}

	@Override
	protected void onImpact(MovingObjectPosition mop)
	{
		if (!this.worldObj.isRemote)
		{
			ArrayList<Entity> list = EntityUtils.getEntitiesInRange(this.worldObj, this.posX, this.posY, this.posZ, this, Entity.class, this.area);
			for (Entity entity : list)
			{
				// TODO gamerforEA add condition [2]
				if (EntityUtils.canEntityBeSeen(this, entity) && !this.fake.cantDamage(entity))
					entity.attackEntityFrom(DamageSource.causeIndirectMagicDamage(this, this.getThrower()), this.damage);
			}

			for (int i = 0; i < 20; ++i)
			{
				int x = MathHelper.floor_double(this.posX) + this.rand.nextInt(this.area) - this.rand.nextInt(this.area);
				int y = MathHelper.floor_double(this.posY) + this.area;

				int z;
				for (z = MathHelper.floor_double(this.posZ) + this.rand.nextInt(this.area) - this.rand.nextInt(this.area); this.worldObj.isAirBlock(x, y, z) && y > MathHelper.floor_double(this.posY) - this.area; --y)
				{
				}

				if (this.worldObj.isAirBlock(x, y + 1, z) && !this.worldObj.isAirBlock(x, y, z) && this.worldObj.getBlock(x, y + 1, z) != ConfigBlocks.blockAiry && EntityUtils.canEntityBeSeen(this, x + 0.5D, y + 1.5D, z + 0.5D))
				{
					// TODO gamerforEA code start
					if (!this.fake.cantBreak(x, y + 1, z))
					// TODO gamerforEA code end
					{
						this.worldObj.setBlock(x, y + 1, z, ConfigBlocks.blockAiry, 10, 3);

						// TODO gamerforEA code start
						TileEntity tile = this.worldObj.getTileEntity(x, y + 1, z);
						if (tile instanceof OwnerTileEntity)
							((OwnerTileEntity) tile).fake.setParent(this.fake);
						// TODO gamerforEA code end
					}
				}
			}
		}

		Thaumcraft.proxy.burst(this.worldObj, this.posX, this.posY, this.posZ, 3.0F);
		this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, "thaumcraft:shock", 1.0F, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F);
		this.setDead();
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
		if (this.ticksExisted > 500)
			this.setDead();

	}

	@Override
	public float getShadowSize()
	{
		return 0.1F;
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
					this.worldObj.playSoundAtEntity(this, "thaumcraft:zap", 1.0F, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F);
				}

				return true;
			}
			else
				return false;
		}
	}
}
