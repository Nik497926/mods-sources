package wendoxd.hooks;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import tueajtwut.twautjwta.asm.Hook;
import tueajtwut.twautjwta.asm.ReturnCondition;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.potion.Potion;
import net.minecraft.server.MinecraftServer;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import wendoxd.events.ClientEvents;
import wendoxd.extendedplayer.ExtendedPlayer;
import wendoxd.init.AbilityMod;
import wendoxd.modules.Ability;

public class Hooks {
	/** уберите к хуям когда билдите клиент привет либа*/
	/*public static Entity pointedEntity;

	@Hook(returnCondition = ReturnCondition.ALWAYS)
	@SideOnly(Side.CLIENT)
	public static void moveFlying(Entity instance, float motionX, float motionY, float speed) {
		float f3 = motionX * motionX + motionY * motionY;
		if (f3 >= 1.0E-4F) {
			if (instance instanceof EntityClientPlayerMP) {
				if (Minecraft.getMinecraft().thePlayer.isInWater() && ClientEvents.speedWaterModifier != 1) {
					speed += ClientEvents.speedWaterModifier / 100;
				}
				if (ClientEvents.speedModifier != 1 && Minecraft.getMinecraft().thePlayer.onGround
						&& !Minecraft.getMinecraft().thePlayer.isInWater())
					speed += ClientEvents.speedModifier / 100;
			}
			f3 = MathHelper.sqrt_float(f3);

			if (f3 < 1.0F) {
				f3 = 1.0F;
			}

			f3 = speed / f3;
			motionX *= f3;
			motionY *= f3;
			float f4 = MathHelper.sin(instance.rotationYaw * (float) Math.PI / 180.0F);
			float f5 = MathHelper.cos(instance.rotationYaw * (float) Math.PI / 180.0F);
			instance.motionX += (double) (motionX * f5 - motionY * f4);
			instance.motionZ += (double) (motionY * f5 + motionX * f4);
		}
	}

	@Hook(returnCondition = ReturnCondition.ALWAYS)
	@SideOnly(Side.CLIENT)
	public static void getMouseOver(EntityRenderer instance, float p_78473_1_) {
		if (Minecraft.getMinecraft().renderViewEntity != null) {
			if (Minecraft.getMinecraft().theWorld != null) {
				Minecraft.getMinecraft().pointedEntity = null;
				double d0 = (double) Minecraft.getMinecraft().playerController.getBlockReachDistance()
						* ClientEvents.distance;
				Minecraft.getMinecraft().objectMouseOver = Minecraft.getMinecraft().renderViewEntity.rayTrace(d0,
						p_78473_1_);
				double d1 = d0;
				Vec3 vec3 = Minecraft.getMinecraft().renderViewEntity.getPosition(p_78473_1_);
				if (Minecraft.getMinecraft().playerController.extendedReach()) {
					d0 = Minecraft.getMinecraft().playerController.getBlockReachDistance() * ClientEvents.distance;
					d1 = Minecraft.getMinecraft().playerController.getBlockReachDistance() * ClientEvents.distance;
				}

				if (Minecraft.getMinecraft().objectMouseOver != null) {
					d1 = Minecraft.getMinecraft().objectMouseOver.hitVec.distanceTo(vec3);
				}

				Vec3 vec31 = Minecraft.getMinecraft().renderViewEntity.getLook(p_78473_1_);
				Vec3 vec32 = vec3.addVector(vec31.xCoord * d0, vec31.yCoord * d0, vec31.zCoord * d0);
				pointedEntity = null;
				Vec3 vec33 = null;
				float f1 = 1.0F;
				List list = Minecraft.getMinecraft().theWorld.getEntitiesWithinAABBExcludingEntity(
						Minecraft.getMinecraft().renderViewEntity,
						Minecraft.getMinecraft().renderViewEntity.boundingBox
								.addCoord(vec31.xCoord * d0, vec31.yCoord * d0, vec31.zCoord * d0)
								.expand((double) f1, (double) f1, (double) f1));
				double d2 = d1;

				for (int i = 0; i < list.size(); ++i) {
					Entity entity = (Entity) list.get(i);

					if (entity.canBeCollidedWith()) {
						float f2 = entity.getCollisionBorderSize();
						AxisAlignedBB axisalignedbb = entity.boundingBox.expand((double) f2, (double) f2, (double) f2);
						MovingObjectPosition movingobjectposition = axisalignedbb.calculateIntercept(vec3, vec32);

						if (axisalignedbb.isVecInside(vec3)) {
							if (0.0D < d2 || d2 == 0.0D) {
								pointedEntity = entity;
								vec33 = movingobjectposition == null ? vec3 : movingobjectposition.hitVec;
								d2 = 0.0D;
							}
						} else if (movingobjectposition != null) {
							double d3 = vec3.distanceTo(movingobjectposition.hitVec);

							if (d3 < d2 || d2 == 0.0D) {
								if (entity == Minecraft.getMinecraft().renderViewEntity.ridingEntity
										&& !entity.canRiderInteract()) {
									if (d2 == 0.0D) {
										pointedEntity = entity;
										vec33 = movingobjectposition.hitVec;
									}
								} else {
									pointedEntity = entity;
									vec33 = movingobjectposition.hitVec;
									d2 = d3;
								}
							}
						}
					}
				}

				if (pointedEntity != null && (d2 < d1 || Minecraft.getMinecraft().objectMouseOver == null)) {
					Minecraft.getMinecraft().objectMouseOver = new MovingObjectPosition(pointedEntity, vec33);

					if (pointedEntity instanceof EntityLivingBase || pointedEntity instanceof EntityItemFrame) {
						Minecraft.getMinecraft().pointedEntity = pointedEntity;
					}
				}
			}
		}
	}
	@Hook(returnCondition = ReturnCondition.ALWAYS)
	@SideOnly(Side.CLIENT)
	public static void jump(EntityLivingBase instance) {
		instance.motionY = (instance instanceof EntityClientPlayerMP) ? 0.41999998688697815D * ClientEvents.jumpModifier
				: 0.41999998688697815D;

		if (instance.isPotionActive(Potion.jump)) {
			instance.motionY += (double) ((float) (instance.getActivePotionEffect(Potion.jump).getAmplifier() + 1)
					* 0.1F);
		}

		if (instance.isSprinting()) {
			float f = instance.rotationYaw * 0.017453292F;
			instance.motionX -= (double) (MathHelper.sin(f) * 0.2F);
			instance.motionZ += (double) (MathHelper.cos(f) * 0.2F);
		}

		instance.isAirBorne = true;
		ForgeHooks.onLivingJump(instance);
	}*/
	@Hook(returnCondition = ReturnCondition.ALWAYS)
	public static void processUseEntity(NetHandlerPlayServer server, C02PacketUseEntity p_147340_1_) {
		if (!AbilityMod.SERVER)
			return;
		WorldServer worldserver = MinecraftServer.getServer().worldServerForDimension(server.playerEntity.dimension);
		Entity entity = p_147340_1_.func_149564_a(worldserver);
		server.playerEntity.func_143004_u();

		if (entity != null) {
			boolean flag = server.playerEntity.canEntityBeSeen(entity);
			ExtendedPlayer extend = ExtendedPlayer.get(server.playerEntity);
			double modifier = 1;
			if (extend.getUnlocked(Ability.DISTANCE) != null)
				modifier = Ability.DISTANCE.getModifier(Ability.DISTANCE, extend.getUnlocked(Ability.DISTANCE));
			double d0 = 36.0D * modifier;

			if (!flag) {
				d0 = 9.0D * modifier;
			}

			if (server.playerEntity.getDistanceSqToEntity(entity) < d0) {
				if (p_147340_1_.func_149565_c() == C02PacketUseEntity.Action.INTERACT) {
					server.playerEntity.interactWith(entity);
				} else if (p_147340_1_.func_149565_c() == C02PacketUseEntity.Action.ATTACK) {
					if (entity instanceof EntityItem || entity instanceof EntityXPOrb || entity instanceof EntityArrow
							|| entity == server.playerEntity) {
						server.kickPlayerFromServer("Attempting to attack an invalid entity");
						MinecraftServer.getServer().logWarning("Player " + server.playerEntity.getCommandSenderName()
								+ " tried to attack an invalid entity");
						return;
					}
					server.playerEntity.attackTargetEntityWithCurrentItem(entity);
				}
			}
		}
	}

}
