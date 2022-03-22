/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block.subtile.generating;

import com.meteor.extrabotany.common.core.handler.ConfigHandler;
import com.meteor.extrabotany.common.lexicon.LexiconModData;
import java.util.List;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileGenerating;

public class SubTilePyschobloom
extends SubTileGenerating {
    private static final int RANGE = 4;
    private static final int DELAY = 20;

    public LexiconEntry getEntry() {
        return LexiconModData.pyschobloom;
    }

    public int getColor() {
        return 16076207;
    }

    public void onUpdate() {
        super.onUpdate();
        List livings = this.supertile.getWorldObj().getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(this.supertile.xCoord - 4, this.supertile.yCoord - 4, this.supertile.zCoord - 4, this.supertile.xCoord + 4 + 1, this.supertile.yCoord + 4 + 1, this.supertile.zCoord + 4 + 1));
        if (this.linkedCollector != null && livings.size() > 0 && this.ticksExisted % 20 == 0) {
            this.mana += Math.min(livings.size() / ConfigHandler.efficiencyPyschobloom + this.supertile.getWorldObj().rand.nextInt(3), ConfigHandler.pyschobloomMax);
        }
    }

    public int getDelayBetweenPassiveGeneration() {
        return 10;
    }

    public RadiusDescriptor getRadius() {
        return new RadiusDescriptor.Square(this.toChunkCoordinates(), 4);
    }

    public int getMaxMana() {
        return 500;
    }
}

