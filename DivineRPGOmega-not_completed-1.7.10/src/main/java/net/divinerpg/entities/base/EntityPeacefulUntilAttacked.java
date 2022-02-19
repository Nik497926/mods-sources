/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.ai.EntityAIAttackOnCollide
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAINearestAttackableTarget
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.base;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public abstract class EntityPeacefulUntilAttacked
extends EntityDivineRPGMob {
    public int angerLevel = 0;

    public EntityPeacefulUntilAttacked(World w) {
        super(w);
        this.tasks.addTask(5, (EntityAIBase)new EntityAIAttackOnCollide((EntityCreature)this, EntityPlayer.class, 1.0, false));
    }

    public void writeEntityToNBT(NBTTagCompound tag) {
        super.writeEntityToNBT(tag);
        tag.setShort("Anger", (short)this.angerLevel);
    }

    public void readEntityFromNBT(NBTTagCompound tag) {
        super.readEntityFromNBT(tag);
        this.angerLevel = tag.getShort("Anger");
    }

    protected Entity findPlayerToAttack() {
        return this.angerLevel == 0 ? null : super.findPlayerToAttack();
    }

    public boolean attackEntityFrom(DamageSource source, float par2) {
        if (source.getEntity() instanceof EntityPlayer) {
            this.makeAngryAt((EntityPlayer)source.getEntity());
        }
        return super.attackEntityFrom(source, par2);
    }

    public void makeAngryAt(EntityPlayer p) {
        if (!p.capabilities.isCreativeMode) {
            this.angerLevel = 400;
            this.entityToAttack = p;
            this.targetTasks.addTask(1, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityPlayer.class, 0, true));
        }
    }

    public boolean isValidLightLevel() {
        return true;
    }

    public boolean attackEntityAsMob(Entity entity) {
        if (this.angerLevel > 0) {
            return super.attackEntityAsMob(entity);
        }
        return false;
    }
}

