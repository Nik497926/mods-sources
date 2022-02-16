/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vanilla;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;

public class EntityEnthralledDramcryx
extends EntityDivineRPGMob {
    public EntityEnthralledDramcryx(World par1World) {
        super(par1World);
        this.addAttackingAI();
        this.setSize(1.25f, 1.25f);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.corruptedDramcryxHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.corruptedDramcryxDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.corruptedDramcryxSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.corruptedDramcryxFollowRange);
    }

    protected String getLivingSound() {
        return Sounds.getSoundName(Sounds.dramcryx);
    }

    protected String getHurtSound() {
        return Sounds.getSoundName(Sounds.dramcryxHurt);
    }

    protected String getDeathSound() {
        return Sounds.getSoundName(Sounds.dramcryxHurt);
    }

    protected void dropFewItems(boolean par1, int par2) {
        int var4;
        int var3 = this.rand.nextInt(3 + par2);
        for (var4 = 0; var4 < var3; ++var4) {
            this.dropItem(VanillaItemsOther.corruptedShards, 1);
        }
        var3 = this.rand.nextInt(3 + par2);
        for (var4 = 0; var4 < var3; ++var4) {
            this.dropItem(VanillaItemsOther.corruptedShards, 2);
        }
        if (this.rand.nextInt(40) == 0) {
            this.dropItem(VanillaItemsOther.arlemiteIngot, 5);
        }
    }

    protected boolean isValidLightLevel() {
        return true;
    }

    @Override
    public boolean getCanSpawnHere() {
        return this.posY <= 16.0 && super.getCanSpawnHere();
    }

    @Override
    public String mobName() {
        return "Enthralled Dramcryx";
    }
}

