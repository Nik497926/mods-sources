/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vanilla;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.libs.Sounds;
import net.minecraft.block.Block;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class EntityDesertCrawler
extends EntityDivineRPGMob {
    public EntityDesertCrawler(World par1World) {
        super(par1World);
        this.addAttackingAI();
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.desertCrawlerHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.desertCrawlerDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.desertCrawlerSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.desertCrawlerFollowRange);
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
        return Item.getItemFromBlock((Block)Blocks.sandstone);
    }

    protected void dropFewItems(boolean par1, int par2) {
        int var4;
        Item i = this.getDropItem();
        int var3 = this.rand.nextInt(3 + par2);
        for (var4 = 0; var4 < var3; ++var4) {
            this.dropItem(i, 20);
        }
        for (var4 = 0; var4 < var3; ++var4) {
            this.dropItem(i, 60);
        }
    }

    @Override
    public String mobName() {
        return "Desert Crawler";
    }
}

