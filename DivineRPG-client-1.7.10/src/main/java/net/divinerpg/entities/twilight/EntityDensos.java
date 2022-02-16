/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.twilight;

import net.divinerpg.entities.base.EntityDivineRPGBoss;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.blocks.VanillaBlocks;
import net.divinerpg.utils.items.TwilightItemsArmor;
import net.divinerpg.utils.items.TwilightItemsWeapons;
import net.minecraft.block.Block;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class EntityDensos
extends EntityDivineRPGBoss {
    private static final ItemStack defaultHeldItem = new ItemStack(TwilightItemsWeapons.haliteBlade, 1);

    public EntityDensos(World var1) {
        super(var1);
        this.addAttackingAI();
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.densosHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.densosDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.densosSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.densosFollowRange);
    }

    public int getTotalArmorValue() {
        return 10;
    }

    @Override
    protected String getLivingSound() {
        return Sounds.getSoundName(Sounds.densos);
    }

    @Override
    protected String getHurtSound() {
        return Sounds.getSoundName(Sounds.densosHurt);
    }

    public ItemStack getHeldItem() {
        return defaultHeldItem;
    }

    public void dropFewItems(boolean var1, int var2) {
        this.dropItem(TwilightItemsArmor.haliteChestplate, 1);
        if (this.rand.nextInt(3) == 0) {
            this.dropItem(Item.getItemFromBlock((Block)VanillaBlocks.densosStatue), 1);
        }
    }

    @Override
    public String mobName() {
        return "Densos";
    }

    @Override
    public String name() {
        return "Densos";
    }

    @Override
    public IChatComponent chat() {
        return null;
    }
}

