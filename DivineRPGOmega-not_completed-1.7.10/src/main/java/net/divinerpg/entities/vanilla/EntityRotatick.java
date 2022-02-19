/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.init.Items
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vanilla;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.libs.Sounds;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Items;
import net.minecraft.world.World;

public class EntityRotatick
extends EntityDivineRPGMob {
    public EntityRotatick(World par1World) {
        super(par1World);
        this.setSize(1.15f, 1.0f);
        this.addAttackingAI();
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.rotatickHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.rotatickDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.rotatickSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.rotatickFollowRange);
    }

    protected String getLivingSound() {
        return Sounds.getSoundName(Sounds.rotatick);
    }

    protected String getHurtSound() {
        return Sounds.getSoundName(Sounds.rotatickHurt);
    }

    protected String getDeathSound() {
        return Sounds.getSoundName(Sounds.rotatickHurt);
    }

    protected void dropFewItems(boolean par1, int par2) {
        int var4;
        int var3 = this.rand.nextInt(3 + par2);
        for (var4 = 0; var4 < var3; ++var4) {
            this.dropItem(Items.diamond, 0);
        }
        var3 = this.rand.nextInt(3 + par2);
        for (var4 = 0; var4 < var3; ++var4) {
            this.dropItem(Items.diamond, 1);
        }
    }

    protected boolean isValidLightLevel() {
        return true;
    }

    @Override
    public boolean getCanSpawnHere() {
        return this.posY < 25.0 && super.getCanSpawnHere();
    }

    @Override
    public String mobName() {
        return "Rotatick";
    }
}

