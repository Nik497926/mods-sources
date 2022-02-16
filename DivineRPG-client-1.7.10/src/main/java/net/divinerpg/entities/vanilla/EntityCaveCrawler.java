/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vanilla;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityCaveCrawler
extends EntityDivineRPGMob {
    public EntityCaveCrawler(World par1World) {
        super(par1World);
        this.setSize(1.5f, 2.0f);
        this.addAttackingAI();
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.caveCrawlerHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.caveCrawlerDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.caveCrawlerSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.caveCrawlerFollowRange);
    }

    @Override
    protected String getLivingSound() {
        return Sounds.getSoundName(Sounds.crawler);
    }

    @Override
    protected String getHurtSound() {
        return Sounds.getSoundName(Sounds.crawlerHurt);
    }

    @Override
    protected String getDeathSound() {
        return Sounds.getSoundName(Sounds.crawlerHurt);
    }

    protected Item getDropItem() {
        return VanillaItemsOther.realmiteIngot;
    }

    protected void dropRareDrop(int par1) {
        switch (this.rand.nextInt(1)) {
            case 0: {
                this.entityDropItem(new ItemStack((Item)Items.potionitem, 1, 8196), 0.0f);
            }
        }
    }

    protected boolean isValidLightLevel() {
        return true;
    }

    @Override
    public boolean getCanSpawnHere() {
        return this.posY < 35.0 && super.getCanSpawnHere();
    }

    @Override
    public String mobName() {
        return "Cave Crawler";
    }
}

