/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.EnumSkyBlock
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vanilla;

import java.util.List;
import net.divinerpg.entities.base.EntityPeacefulUntilAttacked;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public class EntityCyclops
extends EntityPeacefulUntilAttacked {
    public EntityCyclops(World var1) {
        super(var1);
        this.setSize(1.5f, 3.9f);
        this.experienceValue = 40;
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.cyclopsHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.cyclopsDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.cyclopsSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.cyclopsFollowRange);
    }

    @Override
    protected String getLivingSound() {
        return Sounds.getSoundName(Sounds.cyclops);
    }

    @Override
    protected String getHurtSound() {
        return Sounds.getSoundName(Sounds.cyclopsHurt);
    }

    @Override
    protected String getDeathSound() {
        return Sounds.getSoundName(Sounds.cyclopsHurt);
    }

    protected void dropFewItems(boolean var1, int loot) {
        this.dropItem(VanillaItemsOther.cyclopsEye, this.rand.nextInt(2 + loot));
        this.dropItem(Items.gold_ingot, this.rand.nextInt(2 + loot));
        if (this.rand.nextInt(40) == 0) {
            this.dropItem(VanillaItemsOther.bloodgem, 1);
        }
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float par2) {
        boolean hurt = super.attackEntityFrom(source, par2);
        if (hurt && source.getEntity() != null && source.getEntity() instanceof EntityPlayer) {
            List<Entity> entities = this.worldObj.getEntitiesWithinAABBExcludingEntity((Entity)this, this.boundingBox.expand(20.0, 20.0, 20.0));
            for (Entity e : entities) {
                if (!(e instanceof EntityCyclops)) continue;
                ((EntityCyclops)e).makeAngryAt((EntityPlayer)source.getEntity());
            }
        }
        return hurt;
    }

    @Override
    public boolean isValidLightLevel() {
        int k;
        int j;
        int i = MathHelper.floor_double((double)this.posX);
        if (this.worldObj.getSavedLightValue(EnumSkyBlock.Sky, i, j = MathHelper.floor_double((double)this.boundingBox.minY), k = MathHelper.floor_double((double)this.posZ)) > this.rand.nextInt(32)) {
            return false;
        }
        int l = this.worldObj.getBlockLightValue(i, j, k);
        if (this.worldObj.isThundering()) {
            int i1 = this.worldObj.skylightSubtracted;
            this.worldObj.skylightSubtracted = 10;
            l = this.worldObj.getBlockLightValue(i, j, k);
            this.worldObj.skylightSubtracted = i1;
        }
        return l <= this.rand.nextInt(8);
    }

    @Override
    public String mobName() {
        return "Cyclops";
    }
}

