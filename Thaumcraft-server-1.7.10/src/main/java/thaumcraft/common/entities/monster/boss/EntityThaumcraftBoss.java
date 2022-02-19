package thaumcraft.common.entities.monster.boss;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import thaumcraft.api.entities.IEldritchMob;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.lib.utils.EntityUtils;

import java.util.ArrayList;
import java.util.HashMap;

import static com.gamerforea.thaumcraft.ChatUtils.text;
import static com.gamerforea.thaumcraft.ChatUtils.translate;

public class EntityThaumcraftBoss extends EntityMob implements IBossDisplayData
{
	HashMap<Integer, Integer> aggro = new HashMap<>();
	int spawnTimer = 0;

	public EntityThaumcraftBoss(World world)
	{
		super(world);
		this.experienceValue = 50;
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		super.readEntityFromNBT(nbt);
		if (nbt.hasKey("HomeD"))
			this.setHomeArea(nbt.getInteger("HomeX"), nbt.getInteger("HomeY"), nbt.getInteger("HomeZ"), nbt.getInteger("HomeD"));

	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		super.writeEntityToNBT(nbt);
		if (this.getHomePosition() != null && this.func_110174_bM() > 0.0F)
		{
			nbt.setInteger("HomeD", (int) this.func_110174_bM());
			nbt.setInteger("HomeX", this.getHomePosition().posX);
			nbt.setInteger("HomeY", this.getHomePosition().posY);
			nbt.setInteger("HomeZ", this.getHomePosition().posZ);
		}

	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.95D);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(40.0D);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.getDataWatcher().addObject(14, (short) 0);
	}

	@Override
	protected void updateAITasks()
	{
		if (this.getSpawnTimer() == 0)
			super.updateAITasks();

		if (this.getAttackTarget() != null && this.getAttackTarget().isDead)
			this.setAttackTarget(null);

	}

	@Override
	public IEntityLivingData onSpawnWithEgg(IEntityLivingData data)
	{
		this.setHomeArea((int) this.posX, (int) this.posY, (int) this.posZ, 24);
		return data;
	}

	public int getAnger()
	{
		return this.dataWatcher.getWatchableObjectShort(14);
	}

	public void setAnger(int par1)
	{
		this.dataWatcher.updateObject(14, (short) par1);
	}

	public int getSpawnTimer()
	{
		return this.spawnTimer;
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
		if (this.getSpawnTimer() > 0)
			--this.spawnTimer;

		if (this.getAnger() > 0)
			this.setAnger(this.getAnger() - 1);

		if (this.worldObj.isRemote && this.rand.nextInt(15) == 0 && this.getAnger() > 0)
		{
			double d0 = this.rand.nextGaussian() * 0.02D;
			double d1 = this.rand.nextGaussian() * 0.02D;
			double d2 = this.rand.nextGaussian() * 0.02D;
			this.worldObj.spawnParticle("angryVillager", this.posX + (double) (this.rand.nextFloat() * this.width) - (double) this.width / 2.0D, this.boundingBox.minY + (double) this.height + (double) this.rand.nextFloat() * 0.5D, this.posZ + (double) (this.rand.nextFloat() * this.width) - (double) this.width / 2.0D, d0, d1, d2);
		}

		if (!this.worldObj.isRemote)
		{
			if (this.ticksExisted % 30 == 0)
				this.heal(1.0F);

			if (this.getAttackTarget() != null && this.ticksExisted % 20 == 0)
			{
				ArrayList<Integer> dl = new ArrayList<>();
				int players = 0;
				int hei = this.getAttackTarget().getEntityId();
				int ad = this.aggro.getOrDefault(hei, 0);
				int ld = ad;
				Entity newTarget = null;

				for (Integer ei : this.aggro.keySet())
				{
					int ca = this.aggro.get(ei);
					if (ca > ad + 25 && (double) ca > (double) ad * 1.1D && ca > ld)
					{
						newTarget = this.worldObj.getEntityByID(hei);
						if (newTarget != null && !newTarget.isDead && this.getDistanceSqToEntity(newTarget) <= 16384.0D)
						{
							hei = ei;
							ld = ei;
							if (newTarget instanceof EntityPlayer)
								++players;
						}
						else
							dl.add(ei);
					}
				}

				for (Integer ei : dl)
				{
					this.aggro.remove(ei);
				}

				if (newTarget != null && hei != this.getAttackTarget().getEntityId())
					this.setAttackTarget((EntityLivingBase) newTarget);

				float om = this.getMaxHealth();
				IAttributeInstance iattributeinstance = this.getEntityAttribute(SharedMonsterAttributes.maxHealth);
				IAttributeInstance iattributeinstance2 = this.getEntityAttribute(SharedMonsterAttributes.attackDamage);

				for (int a = 0; a < 5; ++a)
				{
					iattributeinstance2.removeModifier(EntityUtils.DMGBUFF[a]);
					iattributeinstance.removeModifier(EntityUtils.HPBUFF[a]);
				}

				for (int a = 0; a < Math.min(5, players - 1); ++a)
				{
					iattributeinstance.applyModifier(EntityUtils.HPBUFF[a]);
					iattributeinstance2.applyModifier(EntityUtils.DMGBUFF[a]);
				}

				double mm = (double) (this.getMaxHealth() / om);
				this.setHealth((float) ((double) this.getHealth() * mm));
			}
		}

	}

	@Override
	public boolean isEntityInvulnerable()
	{
		return super.isEntityInvulnerable() || this.getSpawnTimer() > 0;
	}

	@Override
	public boolean canBreatheUnderwater()
	{
		return true;
	}

	@Override
	public boolean canBePushed()
	{
		return super.canBePushed() && !this.isEntityInvulnerable();
	}

	@Override
	protected int decreaseAirSupply(int air)
	{
		return air;
	}

	@Override
	public void setInWeb()
	{
	}

	@Override
	public boolean canPickUpLoot()
	{
		return false;
	}

	@Override
	protected boolean isAIEnabled()
	{
		return true;
	}

	@Override
	protected void addRandomArmor()
	{
	}

	@Override
	protected void enchantEquipment()
	{
	}

	@Override
	protected boolean canDespawn()
	{
		return false;
	}

	@Override
	public boolean isOnSameTeam(EntityLivingBase el)
	{
		return el instanceof IEldritchMob;
	}

	@Override
	protected void dropFewItems(boolean flag, int fortune)
	{
		EntityUtils.entityDropSpecialItem(this, new ItemStack(ConfigItems.itemEldritchObject, 1, 3), this.height / 2.0F);
		this.entityDropItem(new ItemStack(ConfigItems.itemLootbag, 1, 2), 1.5F);
	}

	@Override
	protected void dropRareDrop(int fortune)
	{
		super.dropRareDrop(fortune);
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float damage)
	{
		if (!this.worldObj.isRemote)
		{
			Entity attacker = source.getEntity();
			if (attacker instanceof EntityLivingBase)
			{
				int target = attacker.getEntityId();
				int ad = (int) damage;
				if (this.aggro.containsKey(target))
					ad += this.aggro.get(target);

				this.aggro.put(target, ad);
			}

			if (damage > 35.0F)
			{
				if (this.getAnger() == 0)
				{
					try
					{
						this.addPotionEffect(new PotionEffect(Potion.regeneration.id, 200, (int) (damage / 15.0F)));
						this.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 200, (int) (damage / 40.0F)));
						this.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 200, (int) (damage / 40.0F)));
						this.setAnger(200);
					}
					catch (Exception ignored)
					{
					}

					if (attacker instanceof EntityPlayer)
						// TODO gamerforEA code replace, old code:
						// ((EntityPlayer) attacker).addChatMessage(new ChatComponentText(this.getCommandSenderName() + " " + StatCollector.translateToLocal("tc.boss.enrage")));
						((EntityPlayer) attacker).addChatComponentMessage(text(this.getCommandSenderName() + ' ').appendSibling(translate("tc.boss.enrage")));
					// TODO gamerforEA code end
				}

				damage = 35.0F;
			}
		}

		return super.attackEntityFrom(source, damage);
	}

	public void generateName()
	{
	}
}
