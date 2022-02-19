package thaumcraft.common.entities.monster;

import com.gamerforea.eventhelper.fake.FakePlayerContainer;
import com.gamerforea.thaumcraft.ModUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.lib.utils.EntityUtils;
import thaumcraft.common.lib.utils.Utils;

public class EntityFireBat extends EntityMob
{
	private ChunkCoordinates currentFlightTarget;
	public EntityPlayer owner = null;
	public int damBonus = 0;

	// TODO gamerforEA code start
	public final FakePlayerContainer fake = ModUtils.NEXUS_FACTORY.wrapFake(this);
	// TODO gamerforEA code end

	public EntityFireBat(World par1World)
	{
		super(par1World);
		this.setSize(0.5F, 0.9F);
		this.setIsBatHanging(true);
		this.isImmuneToFire = true;
	}

	@Override
	public void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(16, (byte) 0);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getBrightnessForRender(float par1)
	{
		return 15728880;
	}

	@Override
	public float getBrightness(float par1)
	{
		return 1.0F;
	}

	@Override
	protected float getSoundVolume()
	{
		return 0.1F;
	}

	@Override
	protected float getSoundPitch()
	{
		return super.getSoundPitch() * 0.95F;
	}

	@Override
	protected String getLivingSound()
	{
		return this.getIsBatHanging() && this.rand.nextInt(4) != 0 ? null : "mob.bat.idle";
	}

	@Override
	protected String getHurtSound()
	{
		return "mob.bat.hurt";
	}

	@Override
	protected String getDeathSound()
	{
		return "mob.bat.death";
	}

	@Override
	public boolean canBePushed()
	{
		return false;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(this.getIsDevil() ? 15.0D : 5.0D);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(this.getIsSummoned() ? (double) ((this.getIsDevil() ? 3 : 2) + this.damBonus) : 1.0D);
	}

	public boolean getIsBatHanging()
	{
		return Utils.getBit(this.dataWatcher.getWatchableObjectByte(16), 0);
	}

	public void setIsBatHanging(boolean par1)
	{
		byte var2 = this.dataWatcher.getWatchableObjectByte(16);
		if (par1)
			this.dataWatcher.updateObject(16, (byte) Utils.setBit(var2, 0));
		else
			this.dataWatcher.updateObject(16, (byte) Utils.clearBit(var2, 0));

	}

	public boolean getIsSummoned()
	{
		return Utils.getBit(this.dataWatcher.getWatchableObjectByte(16), 1);
	}

	public void setIsSummoned(boolean par1)
	{
		byte var2 = this.dataWatcher.getWatchableObjectByte(16);
		if (par1)
			this.dataWatcher.updateObject(16, (byte) Utils.setBit(var2, 1));
		else
			this.dataWatcher.updateObject(16, (byte) Utils.clearBit(var2, 1));

		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(par1 ? (double) ((this.getIsDevil() ? 3 : 2) + this.damBonus) : 1.0D);
	}

	public boolean getIsExplosive()
	{
		return Utils.getBit(this.dataWatcher.getWatchableObjectByte(16), 2);
	}

	public void setIsExplosive(boolean par1)
	{
		byte var2 = this.dataWatcher.getWatchableObjectByte(16);
		if (par1)
			this.dataWatcher.updateObject(16, (byte) Utils.setBit(var2, 2));
		else
			this.dataWatcher.updateObject(16, (byte) Utils.clearBit(var2, 2));

	}

	public boolean getIsDevil()
	{
		return Utils.getBit(this.dataWatcher.getWatchableObjectByte(16), 3);
	}

	public void setIsDevil(boolean par1)
	{
		byte var2 = this.dataWatcher.getWatchableObjectByte(16);
		if (par1)
			this.dataWatcher.updateObject(16, (byte) Utils.setBit(var2, 3));
		else
			this.dataWatcher.updateObject(16, (byte) Utils.clearBit(var2, 3));

		if (par1)
			this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(this.getIsSummoned() ? (double) ((par1 ? 3 : 2) + this.damBonus) : 1.0D);

	}

	public boolean getIsVampire()
	{
		return Utils.getBit(this.dataWatcher.getWatchableObjectByte(16), 4);
	}

	public void setIsVampire(boolean par1)
	{
		byte var2 = this.dataWatcher.getWatchableObjectByte(16);
		if (par1)
			this.dataWatcher.updateObject(16, (byte) Utils.setBit(var2, 4));
		else
			this.dataWatcher.updateObject(16, (byte) Utils.clearBit(var2, 4));

	}

	@Override
	protected boolean isAIEnabled()
	{
		return false;
	}

	@Override
	public void onLivingUpdate()
	{
		if (this.isWet())
			this.attackEntityFrom(DamageSource.drown, 1.0F);

		super.onLivingUpdate();
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
		if (this.worldObj.isRemote && this.getIsExplosive())
			Thaumcraft.proxy.drawGenericParticles(this.worldObj, this.prevPosX + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.1F, this.prevPosY + this.height / 2.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.1F, this.prevPosZ + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.1F, 0.0D, 0.0D, 0.0D, 1.0F, 1.0F, 1.0F, 0.8F, false, 151, 9, 1, 7 + this.rand.nextInt(5), 0, 1.0F + this.rand.nextFloat() * 0.5F);

		if (this.getIsBatHanging())
		{
			this.motionX = this.motionY = this.motionZ = 0.0D;
			this.posY = MathHelper.floor_double(this.posY) + 1.0D - this.height;
		}
		else
			this.motionY *= 0.6000000238418579D;

		if (this.worldObj.isRemote && !this.getIsVampire())
		{
			this.worldObj.spawnParticle("smoke", this.prevPosX + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F, this.prevPosY + this.height / 2.0F + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F, this.prevPosZ + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F, 0.0D, 0.0D, 0.0D);
			this.worldObj.spawnParticle("flame", this.prevPosX + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F, this.prevPosY + this.height / 2.0F + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F, this.prevPosZ + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F, 0.0D, 0.0D, 0.0D);
		}

	}

	@Override
	protected void updateEntityActionState()
	{
		super.updateEntityActionState();
		if (this.getIsBatHanging())
		{
			if (!this.worldObj.isBlockNormalCubeDefault(MathHelper.floor_double(this.posX), (int) this.posY + 1, MathHelper.floor_double(this.posZ), false))
			{
				this.setIsBatHanging(false);
				this.worldObj.playAuxSFXAtEntity(null, 1015, (int) this.posX, (int) this.posY, (int) this.posZ, 0);
			}
			else
			{
				if (this.rand.nextInt(200) == 0)
					this.rotationYawHead = this.rand.nextInt(360);

				if (this.worldObj.getClosestPlayerToEntity(this, 4.0D) != null)
				{
					this.setIsBatHanging(false);
					this.worldObj.playAuxSFXAtEntity(null, 1015, (int) this.posX, (int) this.posY, (int) this.posZ, 0);
				}
			}
		}
		else
		{
			if (this.entityToAttack == null)
			{
				if (this.getIsSummoned())
					this.attackEntityFrom(DamageSource.generic, 2.0F);

				if (this.currentFlightTarget != null && (!this.worldObj.isAirBlock(this.currentFlightTarget.posX, this.currentFlightTarget.posY, this.currentFlightTarget.posZ) || this.currentFlightTarget.posY < 1))
					this.currentFlightTarget = null;

				if (this.currentFlightTarget == null || this.rand.nextInt(30) == 0 || this.currentFlightTarget.getDistanceSquared((int) this.posX, (int) this.posY, (int) this.posZ) < 4.0F)
					this.currentFlightTarget = new ChunkCoordinates((int) this.posX + this.rand.nextInt(7) - this.rand.nextInt(7), (int) this.posY + this.rand.nextInt(6) - 2, (int) this.posZ + this.rand.nextInt(7) - this.rand.nextInt(7));

				double var1 = this.currentFlightTarget.posX + 0.5D - this.posX;
				double var3 = this.currentFlightTarget.posY + 0.1D - this.posY;
				double var5 = this.currentFlightTarget.posZ + 0.5D - this.posZ;
				this.motionX += (Math.signum(var1) * 0.5D - this.motionX) * 0.10000000149011612D;
				this.motionY += (Math.signum(var3) * 0.699999988079071D - this.motionY) * 0.10000000149011612D;
				this.motionZ += (Math.signum(var5) * 0.5D - this.motionZ) * 0.10000000149011612D;
				float var7 = (float) (Math.atan2(this.motionZ, this.motionX) * 180.0D / 3.141592653589793D) - 90.0F;
				float var8 = MathHelper.wrapAngleTo180_float(var7 - this.rotationYaw);
				this.moveForward = 0.5F;
				this.rotationYaw += var8;
				if (this.rand.nextInt(100) == 0 && this.worldObj.isBlockNormalCubeDefault(MathHelper.floor_double(this.posX), (int) this.posY + 1, MathHelper.floor_double(this.posZ), false))
					this.setIsBatHanging(true);
			}
			else if (this.entityToAttack != null)
			{
				double var1 = this.entityToAttack.posX - this.posX;
				double var3 = this.entityToAttack.posY + this.entityToAttack.getEyeHeight() * 0.66F - this.posY;
				double var5 = this.entityToAttack.posZ - this.posZ;
				this.motionX += (Math.signum(var1) * 0.5D - this.motionX) * 0.10000000149011612D;
				this.motionY += (Math.signum(var3) * 0.699999988079071D - this.motionY) * 0.10000000149011612D;
				this.motionZ += (Math.signum(var5) * 0.5D - this.motionZ) * 0.10000000149011612D;
				float var7 = (float) (Math.atan2(this.motionZ, this.motionX) * 180.0D / 3.141592653589793D) - 90.0F;
				float var8 = MathHelper.wrapAngleTo180_float(var7 - this.rotationYaw);
				this.moveForward = 0.5F;
				this.rotationYaw += var8;
			}

			if (this.entityToAttack instanceof EntityPlayer && ((EntityPlayer) this.entityToAttack).capabilities.disableDamage)
				this.entityToAttack = null;
		}

	}

	@Override
	protected void updateAITasks()
	{
		super.updateAITasks();
	}

	@Override
	protected boolean canTriggerWalking()
	{
		return false;
	}

	@Override
	protected void fall(float par1)
	{
	}

	@Override
	protected void updateFallState(double par1, boolean par3)
	{
	}

	@Override
	public boolean doesEntityNotTriggerPressurePlate()
	{
		return true;
	}

	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{
		if (!this.isEntityInvulnerable() && !par1DamageSource.isFireDamage() && !par1DamageSource.isExplosion())
		{
			if (!this.worldObj.isRemote && this.getIsBatHanging())
				this.setIsBatHanging(false);

			return super.attackEntityFrom(par1DamageSource, par2);
		}
		else
			return false;
	}

	@Override
	protected void attackEntity(Entity entity, float distance)
	{
		if (this.attackTime <= 0 && distance < Math.max(2.5F, entity.width * 1.1F) && entity.boundingBox.maxY > this.boundingBox.minY && entity.boundingBox.minY < this.boundingBox.maxY)
		{
			// TODO gamerforEA code start
			if (this.fake.cantDamage(entity))
				return;

			EntityLivingBase entityLiving = entity instanceof EntityLivingBase ? (EntityLivingBase) entity : null;
			float prevHealth = entityLiving == null ? 0 : entityLiving.getHealth();
			// TODO gamerforEA code end

			if (this.getIsSummoned())
				EntityUtils.setRecentlyHit((EntityLivingBase) entity, 100);

			/* TODO gamerforEA code clear (move down):
			if (this.getIsVampire())
			{
				if (this.owner != null && !this.owner.isPotionActive(Potion.regeneration.id))
					this.owner.addPotionEffect(new PotionEffect(Potion.regeneration.id, 26, 1));
			
				this.heal(1.0F);
			} */

			this.attackTime = 20;
			if ((this.getIsExplosive() || this.worldObj.rand.nextInt(10) == 0) && !this.worldObj.isRemote && !this.getIsDevil())
			{
				entity.hurtResistantTime = 0;
				this.worldObj.newExplosion(this, this.posX, this.posY, this.posZ, 1.5F + (this.getIsExplosive() ? this.damBonus * 0.33F : 0.0F), false, false);
				this.setDead();
			}
			else if (!this.getIsVampire() && !this.worldObj.rand.nextBoolean())
				entity.setFire(this.getIsSummoned() ? 4 : 2);
			else
			{
				double mx = entity.motionX;
				double my = entity.motionY;
				double mz = entity.motionZ;
				this.attackEntityAsMob(entity);
				entity.isAirBorne = false;
				entity.motionX = mx;
				entity.motionY = my;
				entity.motionZ = mz;
			}

			// TODO gamerforEA code start
			if (this.getIsVampire() && entityLiving != null && entityLiving.getHealth() < prevHealth)
			{
				if (this.owner != null && !this.owner.isPotionActive(Potion.regeneration.id))
					this.owner.addPotionEffect(new PotionEffect(Potion.regeneration.id, 26, 1));

				this.heal(1.0F);
			}
			// TODO gamerforEA code end

			this.worldObj.playSoundAtEntity(this, "mob.bat.hurt", 0.5F, 0.9F + this.worldObj.rand.nextFloat() * 0.2F);
		}

	}

	@Override
	protected Entity findPlayerToAttack()
	{
		double var1 = 12.0D;
		return this.getIsSummoned() ? null : this.worldObj.getClosestVulnerablePlayerToEntity(this, var1);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readEntityFromNBT(par1NBTTagCompound);
		this.dataWatcher.updateObject(16, par1NBTTagCompound.getByte("BatFlags"));
		this.damBonus = par1NBTTagCompound.getByte("damBonus");

		// TODO gamerforEA code start
		this.fake.readFromNBT(par1NBTTagCompound);
		// TODO gamerforEA code end
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeEntityToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setByte("BatFlags", this.dataWatcher.getWatchableObjectByte(16));
		par1NBTTagCompound.setByte("damBonus", (byte) this.damBonus);

		// TODO gamerforEA code start
		this.fake.writeToNBT(par1NBTTagCompound);
		// TODO gamerforEA code end
	}

	@Override
	public boolean getCanSpawnHere()
	{
		int var1 = MathHelper.floor_double(this.boundingBox.minY);
		int var2 = MathHelper.floor_double(this.posX);
		int var3 = MathHelper.floor_double(this.posZ);
		int var4 = this.worldObj.getBlockLightValue(var2, var1, var3);
		byte var5 = 7;
		return var4 <= this.rand.nextInt(var5) && super.getCanSpawnHere();
	}

	@Override
	protected Item getDropItem()
	{
		return !this.getIsSummoned() ? Items.gunpowder : Item.getItemById(0);
	}

	@Override
	protected boolean isValidLightLevel()
	{
		return true;
	}
}
