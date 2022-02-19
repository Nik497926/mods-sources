package thaumcraft.common.entities.projectile;

import com.gamerforea.thaumcraft.entity.EntityThrowableByPlayer;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.lib.utils.EntityUtils;
import thaumcraft.common.lib.utils.Utils;
import thaumcraft.common.lib.world.ThaumcraftWorldGenerator;

import java.util.List;
import java.util.Random;

// TODO gamerforEA code replace EntityThrowable to EntityThrowableByPlayer
public class EntityPrimalOrb extends EntityThrowableByPlayer implements IEntityAdditionalSpawnData
{
	int count = 0;
	boolean seeker = false;
	int oi = 0;

	// TODO gamerforEA code start
	private static final String TAG_REAL_TICKS_EXISTED = "RealTicksExisted";
	private int realTicksExisted;

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		super.writeEntityToNBT(nbt);
		if (this.realTicksExisted > 0)
			nbt.setInteger(TAG_REAL_TICKS_EXISTED, this.realTicksExisted);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		super.readEntityFromNBT(nbt);
		this.realTicksExisted = nbt.getInteger(TAG_REAL_TICKS_EXISTED);
	}
	// TODO gamerforEA code end

	public EntityPrimalOrb(World par1World)
	{
		super(par1World);
	}

	public EntityPrimalOrb(World par1World, EntityLivingBase par2EntityLiving, boolean seeker)
	{
		super(par1World, par2EntityLiving);
		this.seeker = seeker;
		this.oi = par2EntityLiving.getEntityId();
	}

	@Override
	public void writeSpawnData(ByteBuf data)
	{
		data.writeBoolean(this.seeker);
		data.writeInt(this.oi);
	}

	@Override
	public void readSpawnData(ByteBuf data)
	{
		this.seeker = data.readBoolean();
		this.oi = data.readInt();
	}

	@Override
	protected float getGravityVelocity()
	{
		return 0.001F;
	}

	@Override
	protected float func_70182_d()
	{
		return 0.5F;
	}

	@Override
	public void onUpdate()
	{
		++this.count;
		if (this.isInsideOfMaterial(Material.portal))
			this.onImpact(new MovingObjectPosition(this));

		if (this.worldObj.isRemote)
		{
			for (int rr = 0; rr < 6; ++rr)
			{
				Thaumcraft.proxy.wispFX4(this.worldObj, (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F, (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F, (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F, this, rr, true, 0.0F);
			}

			Thaumcraft.proxy.wispFX2(this.worldObj, this.posX + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F, this.posY + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F, this.posZ + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F, 0.1F, this.rand.nextInt(6), true, true, 0.0F);
		}

		Random rand = new Random(this.getEntityId() + this.count);
		if (this.ticksExisted > 20)
			if (!this.seeker)
			{
				this.motionX += (rand.nextFloat() - rand.nextFloat()) * 0.01F;
				this.motionY += (rand.nextFloat() - rand.nextFloat()) * 0.01F;
				this.motionZ += (rand.nextFloat() - rand.nextFloat()) * 0.01F;
			}
			else
			{
				List<Entity> entities = EntityUtils.getEntitiesInRange(this.worldObj, this.posX, this.posY, this.posZ, this, EntityLivingBase.class, 16.0D);
				double distance = Double.MAX_VALUE;
				Entity entity = null;

				for (Entity e : entities)
				{
					if (e.getEntityId() != this.oi && !e.isDead)
					{
						double distanceSq = this.getDistanceSqToEntity(e);
						if (distanceSq < distance)
						{
							distance = distanceSq;
							entity = e;
						}
					}
				}

				if (entity != null)
				{
					double dx = entity.posX - this.posX;
					double dy = entity.boundingBox.minY + entity.height * 0.9D - this.posY;
					double dz = entity.posZ - this.posZ;
					dx /= distance;
					dy /= distance;
					dz /= distance;
					this.motionX += dx * 0.2D;
					this.motionY += dy * 0.2D;
					this.motionZ += dz * 0.2D;
					this.motionX = MathHelper.clamp_float((float) this.motionX, -0.2F, 0.2F);
					this.motionY = MathHelper.clamp_float((float) this.motionY, -0.2F, 0.2F);
					this.motionZ = MathHelper.clamp_float((float) this.motionZ, -0.2F, 0.2F);
				}
			}

		super.onUpdate();

		/* TODO gamerforEA code replace, old code:
		if (this.ticksExisted > 5000)
			this.setDead(); */
		this.realTicksExisted = Math.max(this.realTicksExisted, this.ticksExisted);
		if (!this.isDead && (this.realTicksExisted > 5000 || this.posY < 0))
			this.setDead();
		// TODO gamerforEA code end
	}

	@Override
	protected void onImpact(MovingObjectPosition mop)
	{
		if (this.worldObj.isRemote)
			for (int specialchance = 0; specialchance < 6; ++specialchance)
			{
				for (int expl = 0; expl < 6; ++expl)
				{
					float fx = (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.5F;
					float fy = (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.5F;
					float fz = (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.5F;
					Thaumcraft.proxy.wispFX3(this.worldObj, this.posX + fx, this.posY + fy, this.posZ + fz, this.posX + fx * 10.0F, this.posY + fy * 10.0F, this.posZ + fz * 10.0F, 0.4F, expl, true, 0.05F);
				}
			}

		if (!this.worldObj.isRemote)
		{
			float f1 = 1.0F;
			float f2 = 2.0F;

			if (mop.typeOfHit == MovingObjectType.BLOCK && this.isInsideOfMaterial(Material.portal))
			{
				f1 = 10.0F;
				f2 = 4.0F;
			}

			// TODO gamerforEA use ExplosionByPlayer
			this.fake.createExplosion(null, this.posX, this.posY, this.posZ, f2, true);

			if (!this.seeker && this.rand.nextInt(100) <= f1)
				if (this.rand.nextBoolean())
					this.taintSplosion();
				else
					ThaumcraftWorldGenerator.createRandomNodeAt(this.worldObj, mop.blockX, mop.blockY, mop.blockZ, this.rand, false, false, true);

			this.setDead();
		}

	}

	public void taintSplosion()
	{
		int x = (int) this.posX;
		int y = (int) this.posY;
		int z = (int) this.posZ;

		for (int i = 0; i < 10; ++i)
		{
			int chunkX = x + (int) (this.rand.nextFloat() - this.rand.nextFloat() * 6.0F);
			int chunkZ = z + (int) (this.rand.nextFloat() - this.rand.nextFloat() * 6.0F);
			if (this.rand.nextBoolean() && this.worldObj.getBiomeGenForCoords(chunkX, chunkZ) != ThaumcraftWorldGenerator.biomeTaint)
			{
				int yy = this.worldObj.getHeightValue(chunkX, chunkZ);

				// TODO gamerforEA code start
				if (this.fake.cantBreak(chunkX, yy, chunkZ))
					continue;
				// TODO gamerforEA code end

				Utils.setBiomeAt(this.worldObj, chunkX, chunkZ, ThaumcraftWorldGenerator.biomeTaint);
				if (!this.worldObj.isAirBlock(chunkX, yy - 1, chunkZ))
					this.worldObj.setBlock(chunkX, yy, chunkZ, ConfigBlocks.blockTaintFibres, 0, 3);
			}
		}
	}

	@Override
	public float getShadowSize()
	{
		return 0.1F;
	}
}
