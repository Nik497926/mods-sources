/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.pathfinding.PathEntity
 *  net.minecraft.pathfinding.PathNavigate
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.arcana;

import net.divinerpg.entities.base.EntityDivineRPGBoss;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.ArcanaItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityParasecta
extends EntityDivineRPGBoss {
    private ChunkCoordinates currentFlightTarget;
    private EntityCreature theEntity;
    private float farSpeed;
    private float nearSpeed;
    private Entity closestLivingEntity;
    private float distanceFromEntity;
    private PathEntity entityPathEntity;
    private PathNavigate entityPathNavigate;
    private Class targetEntityClass;

    public EntityParasecta(World var1) {
        super(var1);
        this.setSize(1.0f, 2.0f);
        this.targetEntityClass = EntityPlayerMP.class;
        this.addAttackingAI();
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.parasectaHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.parasectaDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.parasectaSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.parasectaFollowRange);
    }

    @Override
    public String mobName() {
        return "Parasecta";
    }

    protected float getSoundVolume() {
        return 0.1f;
    }

    protected float getSoundPitch() {
        return super.getSoundPitch() * 0.95f;
    }

    @Override
    protected String getLivingSound() {
        return this.rand.nextInt(4) != 0 ? null : Sounds.parasecta.getPrefixedName();
    }

    @Override
    protected String getHurtSound() {
        return Sounds.parasectaHurt.getPrefixedName();
    }

    @Override
    protected String getDeathSound() {
        return Sounds.parasectaHurt.getPrefixedName();
    }

    public void onUpdate() {
        super.onUpdate();
        this.motionY *= (double)0.6f;
    }

    protected void updateAITasks() {
        super.updateAITasks();
        if (this.getAttackTarget() != null) {
            int var1 = (int)this.getAttackTarget().posX;
            int var2 = (int)this.getAttackTarget().posY;
            int var3 = (int)this.getAttackTarget().posZ;
            this.currentFlightTarget = new ChunkCoordinates(var1, var2, var3);
        }
        this.motionY = 0.0;
        if (this.currentFlightTarget != null) {
            double var1 = (double)this.currentFlightTarget.posX - this.posX;
            double var3 = (double)this.currentFlightTarget.posY - this.posY;
            double var5 = (double)this.currentFlightTarget.posZ - this.posZ;
            if (Math.signum(var1) != 0.0 || Math.signum(var3) != 0.0 || Math.signum(var5) != 0.0) {
                this.motionX += (Math.signum(var1) * 0.5 - this.motionX) * (double)0.1f;
                this.motionY += (Math.signum(var3) * 1.699999988079071 - this.motionY) * (double)0.1f;
                this.motionZ += (Math.signum(var5) * 0.5 - this.motionZ) * (double)0.1f;
                float var7 = (float)(Math.atan2(this.motionZ, this.motionX) * 180.0 / Math.PI) - 90.0f;
                float var8 = MathHelper.wrapAngleTo180_float((float)(var7 - this.rotationYaw));
                this.moveForward = 0.5f;
                this.rotationYaw += var8;
            }
        }
    }

    protected void fall(float par1) {
    }

    protected void updateFallState(double par1, boolean par3) {
    }

    protected void dropFewItems(boolean var1, int var2) {
        this.dropItem(ArcanaItems.dungeonTokens, 8);
    }

    @Override
    public String name() {
        return this.mobName();
    }

    @Override
    public IChatComponent chat() {
        return null;
    }
}

