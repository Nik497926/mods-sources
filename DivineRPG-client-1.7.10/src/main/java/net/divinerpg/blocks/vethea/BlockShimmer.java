/*
 * Decompiled with CFR 0.152.
 */
package net.divinerpg.blocks.vethea;

import net.divinerpg.blocks.vethea.BlockVetheaPlant;

public class BlockShimmer
extends BlockVetheaPlant {
    public BlockShimmer(String name) {
        super(name);
        this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 0.1f, 1.0f);
    }

    @Override
    public int getRenderType() {
        return 23;
    }
}

