/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.twilight;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.ItemsFood;
import net.divinerpg.utils.items.TwilightItemsOther;
import net.minecraft.block.material.Material;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class EntityBehemoth
extends EntityDivineRPGMob {
    public int eatX;
    public int eatY;
    public int eatZ;
    private boolean shouldEat = false;
    private int ability;
    float moveSpeed = 1.0f;

    public EntityBehemoth(World var1) {
        super(var1);
        this.addAttackingAI();
        this.setSize(1.2f, 1.0f);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(EntityStats.behemothHealth);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(EntityStats.behemothDamage);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(EntityStats.behemothSpeed);
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(EntityStats.behemothFollowRange);
    }

    protected void updateAITasks() {
        if ((double)this.getHealth() < (double)this.getMaxHealth() * 0.5 && !this.shouldEat) {
            for (int i = (int)this.posX - 16; i < (int)this.posX + 16; ++i) {
                for (int j = (int)this.posZ - 16; j < (int)this.posZ + 16; ++j) {
                    for (int n = (int)this.posY - 2; n < (int)this.posY + 2; ++n) {
                        boolean var1;
                        boolean bl = var1 = this.worldObj.getBlock(i, (int)this.posY, j).getMaterial() == Material.wood;
                        if (!var1) continue;
                        this.shouldEat = true;
                        this.eatX = i;
                        this.eatY = (int)this.posY;
                        this.eatZ = j;
                    }
                }
            }
        }
        if (this.shouldEat && (double)this.getHealth() >= (double)this.getMaxHealth() * 0.5) {
            this.shouldEat = false;
        }
        super.updateAITasks();
    }

    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (this.shouldEat && this.worldObj.getBlock(this.eatX, this.eatY, this.eatZ).getMaterial() != Material.wood) {
            this.shouldEat = false;
        }
        if (this.shouldEat && this.ability == 0) {
            if (this.getDistance(this.eatX, this.eatY, this.eatZ) < 2.0) {
                this.heal(8.0f);
                this.worldObj.setBlock(this.eatX, this.eatY, this.eatZ, Blocks.air);
                this.shouldEat = false;
                this.ability = 5;
            } else {
                this.getNavigator().tryMoveToXYZ((double)this.eatX, (double)this.eatY, (double)this.eatZ, (double)this.moveSpeed);
                this.getLookHelper().setLookPosition((double)this.eatX, (double)this.eatY, (double)this.eatZ, 15.0f, 15.0f);
                this.moveEntityWithHeading(0.0f, this.moveSpeed / 4.0f);
            }
        } else if (this.shouldEat && this.ability > 0) {
            --this.ability;
        }
    }

    protected float getSoundVolume() {
        return 0.7f;
    }

    @Override
    protected String getLivingSound() {
        return Sounds.endiku.getPrefixedName();
    }

    @Override
    protected String getHurtSound() {
        return Sounds.endikuHurt.getPrefixedName();
    }

    @Override
    protected String getDeathSound() {
        return this.getHurtSound();
    }

    protected void dropFewItems(boolean par1, int par2) {
        this.dropItem(TwilightItemsOther.wildwoodSoul, this.rand.nextInt(3 + par2));
        this.dropItem(ItemsFood.magicMeat, 1);
    }

    public boolean isValidLightLevel() {
        return true;
    }

    @Override
    public String mobName() {
        return "ModelBehemoth";
    }
}

