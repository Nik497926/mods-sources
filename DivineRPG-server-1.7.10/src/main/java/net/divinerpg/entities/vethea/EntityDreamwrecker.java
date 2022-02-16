package net.divinerpg.entities.vethea;

import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.VetheaItems;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityDreamwrecker extends VetheaMob
{

	public EntityDreamwrecker(World var1)
	{
		super(var1);
		this.addAttackingAI();
		this.setSize(0.8f, 4);
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.dreamWreckerHealth);
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.dreamWreckerDamage);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.dreamWreckerSpeed);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.dreamWreckerFollowRange);
	}

	@Override
	public int getSpawnLayer()
	{
		return 2;
	}

	@Override
	public void onLivingUpdate()
	{
		super.onLivingUpdate();
		EntityPlayer var1 = this.worldObj.getClosestVulnerablePlayerToEntity(this, 64.0D);

		if (var1 != null && var1.getDistanceToEntity(this) < 20)
			this.entityToAttack = var1;
		if (this.getEntityToAttack() != null && this.getEntityToAttack() instanceof EntityPlayer && !this.getEntityToAttack().isDead && this.canEntityBeSeen(this.getEntityToAttack()))
			this.getEntityToAttack().addVelocity(Math.signum(this.posX - this.getEntityToAttack().posX) * 0.029, 0, Math.signum(this.posZ - this.getEntityToAttack().posZ) * 0.029);

		// TODO gamerforEA add condition [n-1]
		if (this.getEntityToAttack() != null && (this.getEntityToAttack().getDistanceToEntity(this) >= 20 || this.getEntityToAttack().isDead || this.getEntityToAttack() instanceof EntityPlayer && ((EntityPlayer) this.getEntityToAttack()).capabilities.isCreativeMode))
			this.entityToAttack = null;
	}

	@Override
	protected float getSoundVolume()
	{
		return 0.7F;
	}

	@Override
	protected String getLivingSound()
	{
		return Sounds.dreamWrecker.getPrefixedName();
	}

	@Override
	protected String getHurtSound()
	{
		return Sounds.dreamWreckerHurt.getPrefixedName();
	}

	@Override
	protected String getDeathSound()
	{
		return this.getHurtSound();
	}

	@Override
	protected void dropFewItems(boolean par1, int par2)
	{
		this.dropItem(VetheaItems.cleanPearls, 1);
	}

	@Override
	public String mobName()
	{
		return "Dreamwrecker";
	}
}
