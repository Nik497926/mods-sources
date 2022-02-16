/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vethea;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.divinerpg.utils.items.VanillaItemsWeapons;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityVermenous
extends EntityDivineRPGMob {
    public int ability;
    private static final ItemStack defaultHeldItem = new ItemStack(VanillaItemsWeapons.corruptedMaul, 1);

    public EntityVermenous(World var1) {
        super(var1);
        this.addAttackingAI();
        this.ability = 0;
    }

    public ItemStack getHeldItem() {
        return defaultHeldItem;
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.vermenousHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.vermenousDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.vermenousSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.vermenousFollowRange);
    }

    public void onLivingUpdate() {
        super.onLivingUpdate();
    }

    protected String getLivingSound() {
        return Sounds.vermenous.getPrefixedName();
    }

    protected String getHurtSound() {
        return Sounds.vermenousHurt.getPrefixedName();
    }

    protected String getDeathSound() {
        return this.getHurtSound();
    }

    protected void dropFewItems(boolean par1, int par2) {
        this.dropItem(VanillaItemsOther.enchantChunk, this.rand.nextInt(4) + 1 + par2);
    }

    @Override
    public String mobName() {
        return "Vermenous";
    }
}

