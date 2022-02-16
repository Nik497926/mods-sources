/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vethea;

import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.entities.vethea.VetheaMob;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.VetheaItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityVermenous
extends VetheaMob {
    public int ability;

    public EntityVermenous(World var1) {
        super(var1);
        this.addAttackingAI();
        this.ability = 0;
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.vermenousHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.vermenousDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.vermenousSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.vermenousFollowRange);
    }

    @Override
    public int getSpawnLayer() {
        return 2;
    }

    public void onLivingUpdate() {
        super.onLivingUpdate();
        EntityPlayer var1 = this.worldObj.getClosestVulnerablePlayerToEntity((Entity)this, 64.0);
        if (var1 == null) {
            return;
        }
        Vec3 var3 = var1.getLook(1.0f).normalize();
        Vec3 var4 = Vec3.createVectorHelper((double)(this.posX - var1.posX), (double)(this.boundingBox.minY + (double)(this.height / 2.0f) - (var1.posY + (double)var1.getEyeHeight())), (double)(this.posZ - var1.posZ));
        double var5 = var4.lengthVector();
        double var7 = var3.dotProduct(var4 = var4.normalize());
        if (var7 > 1.0 - 0.025 / var5 && var1.canEntityBeSeen((Entity)this)) {
            var1.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)this), 4.0f);
        }
    }

    @Override
    protected String getLivingSound() {
        return Sounds.vermenous.getPrefixedName();
    }

    @Override
    protected String getHurtSound() {
        return Sounds.vermenousHurt.getPrefixedName();
    }

    @Override
    protected String getDeathSound() {
        return this.getHurtSound();
    }

    protected void dropFewItems(boolean par1, int par2) {
        this.dropItem(VetheaItems.cleanPearls, 1);
    }

    @Override
    public String mobName() {
        return "Vermenous";
    }
}

