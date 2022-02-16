/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.item.Item
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.twilight;

import net.divinerpg.entities.base.EntityDivineRPGBoss;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.utils.blocks.VanillaBlocks;
import net.divinerpg.utils.items.TwilightItemsWeapons;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.minecraft.block.Block;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.Item;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class EntityVamacheron
extends EntityDivineRPGBoss {
    private int waitTick;

    public EntityVamacheron(World var1) {
        super(var1);
        this.addAttackingAI();
        this.setSize(1.25f, 2.0f);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.vamacheronHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.vamacheronDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.vamacheronSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.vamacheronFollowRange);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0);
    }

    protected Item getDropItem() {
        return TwilightItemsWeapons.haliteBlade;
    }

    public void dropFewItems(boolean par1, int par2) {
        int chance = this.rand.nextInt(99);
        this.dropItem(this.getDropItem(), 1);
        if (chance < 50) {
            this.dropItem(Item.getItemFromBlock((Block)VanillaBlocks.vamacheronStatue), 1);
        } else if (chance < 40) {
            this.dropItem(VanillaItemsOther.redChunk, 1);
            if (chance < 30) {
                this.dropItem(VanillaItemsOther.tarnishedCrystal, 1);
            }
        }
    }

    @Override
    public String mobName() {
        return "Vamacheron";
    }

    @Override
    public String name() {
        return "Vamacheron";
    }

    @Override
    public IChatComponent chat() {
        return null;
    }
}

