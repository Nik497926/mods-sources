/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.world.IBlockAccess
 */
package net.divinerpg.blocks.iceika;

import net.divinerpg.blocks.base.BlockModLadder;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.IBlockAccess;

public class BlockXmasLights
extends BlockModLadder {
    public BlockXmasLights(String name) {
        super(name);
        this.setLightLevel(1.0f);
    }

    public boolean isLadder(IBlockAccess world, int x, int y, int z, EntityLivingBase entity) {
        return false;
    }
}

