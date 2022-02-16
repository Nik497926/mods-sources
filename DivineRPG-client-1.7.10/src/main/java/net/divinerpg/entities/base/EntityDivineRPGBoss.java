/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.base;

import net.divinerpg.entities.base.EntityDivineRPGMob;
import net.divinerpg.entities.base.IDivineRPGBoss;
import net.minecraft.world.World;

public abstract class EntityDivineRPGBoss
extends EntityDivineRPGMob
implements IDivineRPGBoss {
    public EntityDivineRPGBoss(World par1World) {
        super(par1World);
    }

    public boolean canDespawn() {
        return false;
    }
}

