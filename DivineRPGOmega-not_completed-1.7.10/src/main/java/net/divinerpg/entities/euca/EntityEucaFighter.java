/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.euca;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.ItemsFood;
import net.minecraft.world.World;

public class EntityEucaFighter
extends EntityDivineRPGMob {
    public EntityEucaFighter(World par1World) {
        super(par1World);
        this.addAttackingAI();
        this.setSize(0.7f, 1.7f);
    }

    protected void entityInit() {
        super.entityInit();
    }

    public String getLivingSound() {
        return Sounds.getSoundName(Sounds.insecto);
    }

    public String getHurtSound() {
        return Sounds.getSoundName(Sounds.insectoHurt);
    }

    public String getDeathSound() {
        return Sounds.getSoundName(Sounds.insectoHurt);
    }

    protected void dropFewItems(boolean b, int j) {
        this.dropItem(ItemsFood.eucaMeat, this.rand.nextInt(2));
    }

    @Override
    public String mobName() {
        return "Euca Fighter";
    }
}

