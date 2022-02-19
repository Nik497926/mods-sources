package thaumcraft.common.entities.projectile;

import com.gamerforea.eventhelper.fake.FakePlayerContainer;
import com.gamerforea.thaumcraft.EventConfig;
import com.gamerforea.thaumcraft.ModUtils;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.S2BPacketChangeGameState;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.world.World;
import thaumcraft.api.damagesource.DamageSourceIndirectThaumcraftEntity;

import java.util.List;

public class EntityPrimalArrow extends EntityArrow implements IProjectile, IEntityAdditionalSpawnData
{
	private int xTile = -1;
	private int yTile = -1;
	private int zTile = -1;
	private Block inTile;
	private int inData;
	private boolean inGround;
	public int ticksInGround;
	private int ticksInAir;
	private double damage;
	public int shootingEntityId;
	private int knockbackStrength;
	public int type;

	// TODO gamerforEA code start
	public final FakePlayerContainer fake = ModUtils.NEXUS_FACTORY.wrapFake(this);
	// TODO gamerforEA code end

	@Override
	public void writeSpawnData(ByteBuf data)
	{
		data.writeDouble(this.motionX);
		data.writeDouble(this.motionY);
		data.writeDouble(this.motionZ);
		data.writeFloat(this.rotationYaw);
		data.writeFloat(this.rotationPitch);
		data.writeByte(this.type);
		data.writeInt(this.shootingEntityId);
	}

	@Override
	public void readSpawnData(ByteBuf data)
	{
		this.motionX = data.readDouble();
		this.motionY = data.readDouble();
		this.motionZ = data.readDouble();
		this.rotationYaw = data.readFloat();
		this.rotationPitch = data.readFloat();
		this.prevRotationYaw = this.rotationYaw;
		this.prevRotationPitch = this.rotationPitch;
		this.type = data.readByte();
		this.shootingEntityId = data.readInt();
	}

	public EntityPrimalArrow(World par1World)
	{
		super(par1World);
		this.inTile = Blocks.air;
		this.inData = 0;
		this.inGround = false;
		this.ticksInAir = 0;
		this.damage = 2.1D;
		this.type = 0;
		this.renderDistanceWeight = 10.0D;
		this.setSize(0.5F, 0.5F);
	}

	public EntityPrimalArrow(World par1World, double par2, double par4, double par6)
	{
		super(par1World);
		this.inTile = Blocks.air;
		this.inData = 0;
		this.inGround = false;
		this.ticksInAir = 0;
		this.damage = 2.1D;
		this.type = 0;
		this.renderDistanceWeight = 10.0D;
		this.setSize(0.25F, 0.25F);
		this.setPosition(par2, par4, par6);
		this.yOffset = 0.0F;
	}

	public EntityPrimalArrow(World par1World, EntityLivingBase par2EntityLivingBase, float par3, int type)
	{
		super(par1World);
		this.inTile = Blocks.air;
		this.inData = 0;
		this.inGround = false;
		this.ticksInAir = 0;
		this.damage = 2.1D;
		this.type = 0;
		this.renderDistanceWeight = 10.0D;
		this.shootingEntity = par2EntityLivingBase;
		this.type = type;
		this.canBePickedUp = 0;
		this.shootingEntityId = this.shootingEntity.getEntityId();
		this.setSize(0.5F, 0.5F);
		this.setLocationAndAngles(par2EntityLivingBase.posX, par2EntityLivingBase.posY + par2EntityLivingBase.getEyeHeight(), par2EntityLivingBase.posZ, par2EntityLivingBase.rotationYaw, par2EntityLivingBase.rotationPitch);
		this.posX -= MathHelper.cos(this.rotationYaw / 180.0F * 3.1415927F) * 0.16F;
		this.posY -= 0.10000000014901161D;
		this.posZ -= MathHelper.sin(this.rotationYaw / 180.0F * 3.1415927F) * 0.16F;
		Vec3 vec3d = par2EntityLivingBase.getLook(1.0F);
		this.posX += vec3d.xCoord;
		this.posY += vec3d.yCoord;
		this.posZ += vec3d.zCoord;
		this.setPosition(this.posX, this.posY, this.posZ);
		this.yOffset = 0.0F;
		this.motionX = -MathHelper.sin(this.rotationYaw / 180.0F * 3.1415927F) * MathHelper.cos(this.rotationPitch / 180.0F * 3.1415927F);
		this.motionZ = MathHelper.cos(this.rotationYaw / 180.0F * 3.1415927F) * MathHelper.cos(this.rotationPitch / 180.0F * 3.1415927F);
		this.motionY = -MathHelper.sin(this.rotationPitch / 180.0F * 3.1415927F);
		this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, par3 * 1.5F, 1.0F);

		// TODO gamerforEA code start
		if (par2EntityLivingBase instanceof EntityPlayer)
			this.fake.setRealPlayer((EntityPlayer) par2EntityLivingBase);
		// TODO gamerforEA code end
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer par1EntityPlayer)
	{
	}

	@Override
	public void onUpdate()
	{
		// TODO gamerforEA code start
		if (!EventConfig.disableSuperPrimalArrowUpdate)
			// TODO gamerforEA code end
			super.onUpdate();

		if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F)
		{
			float i = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
			this.prevRotationYaw = this.rotationYaw = (float) (Math.atan2(this.motionX, this.motionZ) * 180.0D / 3.141592653589793D);
			this.prevRotationPitch = this.rotationPitch = (float) (Math.atan2(this.motionY, i) * 180.0D / 3.141592653589793D);
		}

		Block var16 = this.worldObj.getBlock(this.xTile, this.yTile, this.zTile);
		if (!var16.isAir(this.worldObj, this.xTile, this.yTile, this.zTile))
		{
			var16.setBlockBoundsBasedOnState(this.worldObj, this.xTile, this.yTile, this.zTile);
			AxisAlignedBB vec3 = var16.getCollisionBoundingBoxFromPool(this.worldObj, this.xTile, this.yTile, this.zTile);
			if (vec3 != null && vec3.isVecInside(Vec3.createVectorHelper(this.posX, this.posY, this.posZ)))
				this.inGround = true;
		}

		if (this.arrowShake > 0)
			--this.arrowShake;

		if (this.inGround)
		{
			Block var17 = this.worldObj.getBlock(this.xTile, this.yTile, this.zTile);
			int vec31 = this.worldObj.getBlockMetadata(this.xTile, this.yTile, this.zTile);
			if (var17 == this.inTile && vec31 == this.inData)
			{
				++this.ticksInGround;
				if (this.ticksInGround == 100)
					this.setDead();
			}
			else
			{
				this.inGround = false;
				this.motionX *= this.rand.nextFloat() * 0.2F;
				this.motionY *= this.rand.nextFloat() * 0.2F;
				this.motionZ *= this.rand.nextFloat() * 0.2F;
				this.ticksInGround = 0;
				this.ticksInAir = 0;
			}
		}
		else
		{
			++this.ticksInAir;
			Vec3 var18 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
			Vec3 var19 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
			MovingObjectPosition mop = this.worldObj.func_147447_a(var18, var19, false, true, false);
			var18 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
			var19 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
			if (mop != null)
				var19 = Vec3.createVectorHelper(mop.hitVec.xCoord, mop.hitVec.yCoord, mop.hitVec.zCoord);

			Entity entity = null;
			List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
			double d0 = 0.0D;

			int l;
			float f1;
			for (l = 0; l < list.size(); ++l)
			{
				Entity f2 = (Entity) list.get(l);
				if (f2.canBeCollidedWith() && (f2.getEntityId() != this.shootingEntityId || this.ticksInAir >= 5))
				{
					f1 = 0.3F;
					AxisAlignedBB f3 = f2.boundingBox.expand(f1, f1, f1);
					MovingObjectPosition f4 = f3.calculateIntercept(var18, var19);
					if (f4 != null)
					{
						double j1 = var18.distanceTo(f4.hitVec);
						if (j1 < d0 || d0 == 0.0D)
						{
							entity = f2;
							d0 = j1;
						}
					}
				}
			}

			if (entity != null)
				mop = new MovingObjectPosition(entity);

			if (mop != null && mop.entityHit instanceof EntityPlayer)
			{
				EntityPlayer var20 = (EntityPlayer) mop.entityHit;
				if (var20.capabilities.disableDamage || this.shootingEntity instanceof EntityPlayer && !((EntityPlayer) this.shootingEntity).canAttackPlayer(var20))
					mop = null;
			}

			float var21;
			float var22;
			if (mop != null)
				if (mop.entityHit != null)
				{
					// TODO gamerforEA code start
					if (this.fake.cantDamage(mop.entityHit))
					{
						this.setDead();
						return;
					}
					// TODO gamerforEA code end

					if (this.inflictDamage(mop))
					{
						if (mop.entityHit instanceof EntityLivingBase)
						{
							EntityLivingBase var23 = (EntityLivingBase) mop.entityHit;
							if (this.knockbackStrength > 0)
							{
								var22 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
								if (var22 > 0.0F)
									mop.entityHit.addVelocity(this.motionX * this.knockbackStrength * 0.6000000238418579D / var22, 0.1D, this.motionZ * this.knockbackStrength * 0.6000000238418579D / var22);
							}

							if (this.shootingEntity instanceof EntityLivingBase)
							{
								EnchantmentHelper.func_151384_a(var23, this.shootingEntity);
								EnchantmentHelper.func_151385_b((EntityLivingBase) this.shootingEntity, var23);
							}

							if (mop.entityHit instanceof EntityPlayer && this.shootingEntity instanceof EntityPlayerMP)
								((EntityPlayerMP) this.shootingEntity).playerNetServerHandler.sendPacket(new S2BPacketChangeGameState(6, 0.0F));
						}

						this.playSound("random.bowhit", 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
						if (!(mop.entityHit instanceof EntityEnderman))
							this.setDead();
					}
					else
					{
						this.motionX *= -0.10000000149011612D;
						this.motionY *= -0.10000000149011612D;
						this.motionZ *= -0.10000000149011612D;
						this.rotationYaw += 180.0F;
						this.prevRotationYaw += 180.0F;
						this.ticksInAir = 0;
					}
				}
				else
				{
					this.xTile = mop.blockX;
					this.yTile = mop.blockY;
					this.zTile = mop.blockZ;
					this.inTile = this.worldObj.getBlock(this.xTile, this.yTile, this.zTile);
					this.inData = this.worldObj.getBlockMetadata(this.xTile, this.yTile, this.zTile);
					this.motionX = (float) (mop.hitVec.xCoord - this.posX);
					this.motionY = (float) (mop.hitVec.yCoord - this.posY);
					this.motionZ = (float) (mop.hitVec.zCoord - this.posZ);
					var21 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
					this.posX -= this.motionX / var21 * 0.05000000074505806D;
					this.posY -= this.motionY / var21 * 0.05000000074505806D;
					this.posZ -= this.motionZ / var21 * 0.05000000074505806D;
					this.playSound("random.bowhit", 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
					this.inGround = true;
					this.arrowShake = 7;
					this.setIsCritical(false);
					if (this.inTile.isAir(this.worldObj, this.xTile, this.yTile, this.zTile))
						this.inTile.onEntityCollidedWithBlock(this.worldObj, this.xTile, this.yTile, this.zTile, this);
				}

			if (this.getIsCritical())
				for (l = 0; l < 4; ++l)
				{
					this.worldObj.spawnParticle("crit", this.posX + this.motionX * l / 4.0D, this.posY + this.motionY * l / 4.0D, this.posZ + this.motionZ * l / 4.0D, -this.motionX, -this.motionY + 0.2D, -this.motionZ);
				}

			this.posX += this.motionX;
			this.posY += this.motionY;
			this.posZ += this.motionZ;
			var21 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
			this.rotationYaw = (float) (Math.atan2(this.motionX, this.motionZ) * 180.0D / 3.141592653589793D);

			for (this.rotationPitch = (float) (Math.atan2(this.motionY, var21) * 180.0D / 3.141592653589793D); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F)
			{
			}

			while (this.rotationPitch - this.prevRotationPitch >= 180.0F)
			{
				this.prevRotationPitch += 360.0F;
			}

			while (this.rotationYaw - this.prevRotationYaw < -180.0F)
			{
				this.prevRotationYaw -= 360.0F;
			}

			while (this.rotationYaw - this.prevRotationYaw >= 180.0F)
			{
				this.prevRotationYaw += 360.0F;
			}

			this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
			this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
			float var24 = 0.99F;
			f1 = 0.05F;
			if (this.isInWater())
			{
				for (int var25 = 0; var25 < 4; ++var25)
				{
					var22 = 0.25F;
					this.worldObj.spawnParticle("bubble", this.posX - this.motionX * var22, this.posY - this.motionY * var22, this.posZ - this.motionZ * var22, this.motionX, this.motionY, this.motionZ);
				}

				var24 = 0.8F;
			}

			this.motionX *= var24;
			this.motionY *= var24;
			this.motionZ *= var24;
			this.motionY -= f1;
			this.setPosition(this.posX, this.posY, this.posZ);
			this.func_145775_I();
		}
	}

	public boolean inflictDamage(MovingObjectPosition mop)
	{
		float f2 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
		int i1 = MathHelper.ceiling_double_int(f2 * this.getDamage());
		int fire = this.isBurning() && this.type != 2 ? 5 : 0;
		if (this.getIsCritical())
			i1 += this.rand.nextInt(i1 / 2 + 2);

		DamageSource damagesource = null;
		switch (this.type)
		{
			case 0:
				if (this.shootingEntity == null)
					damagesource = new DamageSourceIndirectThaumcraftEntity("airarrow", this, this).setDamageBypassesArmor().setMagicDamage().setProjectile();
				else
					damagesource = new DamageSourceIndirectThaumcraftEntity("airarrow", this, this.shootingEntity).setDamageBypassesArmor().setMagicDamage().setProjectile();
				break;
			case 1:
				fire += 5;
				if (this.shootingEntity == null)
					damagesource = new DamageSourceIndirectThaumcraftEntity("firearrow", this, this).setFireDamage().setProjectile();
				else
					damagesource = new DamageSourceIndirectThaumcraftEntity("firearrow", this, this.shootingEntity).setFireDamage().setProjectile();
				break;
			case 2:
				if (mop.entityHit instanceof EntityLivingBase)
					((EntityLivingBase) mop.entityHit).addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 200, 4));
			case 5:
				if (this.type == 5 && mop.entityHit instanceof EntityLivingBase)
					((EntityLivingBase) mop.entityHit).addPotionEffect(new PotionEffect(Potion.wither.id, 100));
			case 3:
			default:
				if (this.shootingEntity == null)
					damagesource = new EntityDamageSourceIndirect("arrow", this, this).setProjectile();
				else
					damagesource = new EntityDamageSourceIndirect("arrow", this, this.shootingEntity).setProjectile();
				break;
			case 4:
				if (this.shootingEntity == null)
					damagesource = new DamageSourceIndirectThaumcraftEntity("orderarrow", this, this).setDamageBypassesArmor().setMagicDamage().setProjectile();
				else
					damagesource = new DamageSourceIndirectThaumcraftEntity("orderarrow", this, this.shootingEntity).setDamageBypassesArmor().setMagicDamage().setProjectile();

				if (mop.entityHit instanceof EntityLivingBase)
					((EntityLivingBase) mop.entityHit).addPotionEffect(new PotionEffect(Potion.weakness.id, 200, 4));
		}

		if (fire > 0 && !(mop.entityHit instanceof EntityEnderman))
			mop.entityHit.setFire(fire);

		return mop.entityHit.attackEntityFrom(damagesource, i1);
	}

	@Override
	public double getDamage()
	{
		switch (this.type)
		{
			case 3:
				return this.damage * 1.5D;
			case 4:
				return this.damage * 0.8D;
			case 5:
				return this.damage * 0.8D;
			default:
				return this.damage;
		}
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		super.writeEntityToNBT(nbt);
		nbt.setByte("type", (byte) this.type);

		// TODO gamerforEA code start
		this.fake.writeToNBT(nbt);
		// TODO gamerforEA code end
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		super.readEntityFromNBT(nbt);
		this.type = nbt.getByte("type");

		// TODO gamerforEA code start
		this.fake.readFromNBT(nbt);
		// TODO gamerforEA code end
	}
}
