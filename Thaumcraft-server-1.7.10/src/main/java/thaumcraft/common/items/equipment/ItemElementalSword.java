package thaumcraft.common.items.equipment;

import com.gamerforea.eventhelper.util.EventUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import thaumcraft.api.IRepairable;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.entities.golems.EntityGolemBase;
import thaumcraft.common.lib.utils.Utils;

import java.util.List;

public class ItemElementalSword extends ItemSword implements IRepairable
{
	public IIcon icon;

	public ItemElementalSword(ToolMaterial enumtoolmaterial)
	{
		super(enumtoolmaterial);
		this.setCreativeTab(Thaumcraft.tabTC);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir)
	{
		this.icon = ir.registerIcon("thaumcraft:elementalsword");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int par1)
	{
		return this.icon;
	}

	@Override
	public EnumRarity getRarity(ItemStack itemstack)
	{
		return EnumRarity.rare;
	}

	@Override
	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
	{
		return par2ItemStack.isItemEqual(new ItemStack(ConfigItems.itemResource, 1, 2)) || super.getIsRepairable(par1ItemStack, par2ItemStack);
	}

	@Override
	public void onUsingTick(ItemStack stack, EntityPlayer player, int count)
	{
		super.onUsingTick(stack, player, count);
		int ticks = this.getMaxItemUseDuration(stack) - count;
		if (player.motionY < 0.0D)
		{
			player.motionY /= 1.2000000476837158D;
			player.fallDistance /= 1.2F;
		}

		player.motionY += 0.07999999821186066D;
		if (player.motionY > 0.5D)
			player.motionY = 0.20000000298023224D;

		if (player instanceof EntityPlayerMP)
			Utils.resetFloatCounter((EntityPlayerMP) player);

		List targets = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, player.boundingBox.expand(2.5D, 2.5D, 2.5D));
		if (targets.size() > 0)
			for (int var9 = 0; var9 < targets.size(); ++var9)
			{
				Entity entity = (Entity) targets.get(var9);
				if (!(entity instanceof EntityPlayer) && !entity.isDead && (player.ridingEntity == null || player.ridingEntity != entity))
				{
					// TODO gamerforEA code start
					if (EventUtils.cantDamage(player, entity))
						continue;
					// TODO gamerforEA code end

					Vec3 p = Vec3.createVectorHelper(player.posX, player.posY, player.posZ);
					Vec3 t = Vec3.createVectorHelper(entity.posX, entity.posY, entity.posZ);
					double distance = p.distanceTo(t) + 0.1D;
					Vec3 r = Vec3.createVectorHelper(t.xCoord - p.xCoord, t.yCoord - p.yCoord, t.zCoord - p.zCoord);
					entity.motionX += r.xCoord / 2.5D / distance;
					entity.motionY += r.yCoord / 2.5D / distance;
					entity.motionZ += r.zCoord / 2.5D / distance;
				}
			}

		if (player.worldObj.isRemote)
		{
			int miny = (int) (player.boundingBox.minY - 2.0D);
			if (player.onGround)
				miny = MathHelper.floor_double(player.boundingBox.minY);

			for (int a = 0; a < 5; ++a)
			{
				Thaumcraft.proxy.smokeSpiral(player.worldObj, player.posX, player.boundingBox.minY + (double) (player.height / 2.0F), player.posZ, 1.5F, player.worldObj.rand.nextInt(360), miny, 14540253);
			}

			if (player.onGround)
			{
				float r1 = player.worldObj.rand.nextFloat() * 360.0F;
				float mx = -MathHelper.sin(r1 / 180.0F * 3.1415927F) / 5.0F;
				float mz = MathHelper.cos(r1 / 180.0F * 3.1415927F) / 5.0F;
				player.worldObj.spawnParticle("smoke", player.posX, player.boundingBox.minY + 0.10000000149011612D, player.posZ, (double) mx, 0.0D, (double) mz);
			}
		}
		else if (ticks == 0 || ticks % 20 == 0)
			player.worldObj.playSoundAtEntity(player, "thaumcraft:wind", 0.5F, 0.9F + player.worldObj.rand.nextFloat() * 0.2F);

		if (ticks % 20 == 0)
			stack.damageItem(1, player);

	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
	{
		if (entity.isEntityAlive())
		{
			List targets = player.worldObj.getEntitiesWithinAABBExcludingEntity(player, entity.boundingBox.expand(1.2D, 1.1D, 1.2D));
			int count = 0;
			if (targets.size() > 1)
			{
				for (int var9 = 0; var9 < targets.size(); ++var9)
				{
					Entity var10 = (Entity) targets.get(var9);
					if (!var10.isDead && (!(var10 instanceof EntityGolemBase) || !((EntityGolemBase) var10).getOwnerName().equals(player.getCommandSenderName())) && (!(var10 instanceof EntityTameable) || !((EntityTameable) var10).func_152113_b().equals(player.getCommandSenderName())) && var10 instanceof EntityLiving && var10.getEntityId() != entity.getEntityId() && (!(var10 instanceof EntityPlayer) || var10.getCommandSenderName() != player.getCommandSenderName()) && var10.isEntityAlive())
					{
						this.attackTargetEntityWithCurrentItem(var10, player);
						++count;
					}
				}

				if (count > 0 && !player.worldObj.isRemote)
					player.worldObj.playSoundAtEntity(entity, "thaumcraft:swing", 1.0F, 0.9F + player.worldObj.rand.nextFloat() * 0.2F);
			}
		}

		return super.onLeftClickEntity(stack, player, entity);
	}

	public void attackTargetEntityWithCurrentItem(Entity entity, EntityPlayer player)
	{
		// TODO gamerforEA code start
		if (EventUtils.cantDamage(player, entity))
			return;
		// TODO gamerforEA code end

		if (!MinecraftForge.EVENT_BUS.post(new AttackEntityEvent(player, entity)))
			if (entity.canAttackWithItem() && !entity.hitByEntity(player))
			{
				float f = (float) player.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
				int i = 0;
				float f1 = 0.0F;
				if (entity instanceof EntityLivingBase)
				{
					f1 = EnchantmentHelper.getEnchantmentModifierLiving(player, (EntityLivingBase) entity);
					i += EnchantmentHelper.getKnockbackModifier(player, (EntityLivingBase) entity);
				}

				if (player.isSprinting())
					++i;

				if (f > 0.0F || f1 > 0.0F)
				{
					boolean flag = player.fallDistance > 0.0F && !player.onGround && !player.isOnLadder() && !player.isInWater() && !player.isPotionActive(Potion.blindness) && player.ridingEntity == null && entity instanceof EntityLivingBase;
					if (flag && f > 0.0F)
						f *= 1.5F;

					f = f + f1;
					boolean flag1 = false;
					int j = EnchantmentHelper.getFireAspectModifier(player);
					if (entity instanceof EntityLivingBase && j > 0 && !entity.isBurning())
					{
						flag1 = true;
						entity.setFire(1);
					}

					boolean flag2 = entity.attackEntityFrom(DamageSource.causePlayerDamage(player), f);
					if (flag2)
					{
						if (i > 0)
						{
							entity.addVelocity((double) (-MathHelper.sin(player.rotationYaw * 3.1415927F / 180.0F) * (float) i * 0.5F), 0.1D, (double) (MathHelper.cos(player.rotationYaw * 3.1415927F / 180.0F) * (float) i * 0.5F));
							player.motionX *= 0.6D;
							player.motionZ *= 0.6D;
							player.setSprinting(false);
						}

						if (flag)
							player.onCriticalHit(entity);

						if (f1 > 0.0F)
							player.onEnchantmentCritical(entity);

						if (f >= 18.0F)
							player.triggerAchievement(AchievementList.overkill);

						player.setLastAttacker(entity);
						if (entity instanceof EntityLivingBase)
							EnchantmentHelper.func_151384_a((EntityLivingBase) entity, player);
					}

					ItemStack itemstack = player.getCurrentEquippedItem();
					Object object = entity;
					if (entity instanceof EntityDragonPart)
					{
						IEntityMultiPart ientitymultipart = ((EntityDragonPart) entity).entityDragonObj;
						if (ientitymultipart instanceof EntityLivingBase)
							object = ientitymultipart;
					}

					if (itemstack != null && object instanceof EntityLivingBase)
					{
						itemstack.hitEntity((EntityLivingBase) object, player);
						if (itemstack.stackSize <= 0)
							player.destroyCurrentEquippedItem();
					}

					if (entity instanceof EntityLivingBase)
					{
						player.addStat(StatList.damageDealtStat, Math.round(f * 10.0F));
						if (j > 0 && flag2)
							entity.setFire(j * 4);
						else if (flag1)
							entity.extinguish();
					}

					player.addExhaustion(0.3F);
				}
			}
	}
}
