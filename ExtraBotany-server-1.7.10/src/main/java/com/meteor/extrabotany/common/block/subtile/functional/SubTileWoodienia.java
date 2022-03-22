/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block.subtile.functional;

import com.meteor.extrabotany.common.lexicon.LexiconModData;
import java.util.List;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.SubTileFunctional;
import vazkii.botania.common.Botania;
import vazkii.botania.common.core.helper.Vector3;

public class SubTileWoodienia
extends SubTileFunctional {
    private static final int RANGE = 5;
    private static final int DELAY = 40;
    private int cd = 0;

    public boolean acceptsRedstone() {
        return true;
    }

    public LexiconEntry getEntry() {
        return LexiconModData.woodienia;
    }

    public void onUpdate() {
        super.onUpdate();
        if (this.mana < 200) {
            return;
        }
        ++this.cd;
        if (this.cd < 40) {
            return;
        }
        this.cd = 0;
        List sheep = this.supertile.getWorldObj().getEntitiesWithinAABB(EntitySheep.class, AxisAlignedBB.getBoundingBox(this.supertile.xCoord - 5, this.supertile.yCoord - 5, this.supertile.zCoord - 5, this.supertile.xCoord + 5 + 1, this.supertile.yCoord + 5 + 1, this.supertile.zCoord + 5 + 1));
        if (sheep.size() == 0) {
            return;
        }
        int rand = 0 + (int)(Math.random() * (double)(sheep.size() - 1));
        EntitySheep ent = (EntitySheep)sheep.get(rand);
        Vector3 vec = Vector3.fromTileEntityCenter(this.supertile);
        int[] pos = new int[]{(int)ent.posX - this.supertile.xCoord, (int)ent.posY - this.supertile.yCoord, (int)ent.posZ - this.supertile.zCoord};
        Vector3 endVec = vec.copy().add(pos[0], pos[1], pos[2]);
        if (!this.supertile.getWorldObj().isRemote) {
            Botania.proxy.lightningFX(this.supertile.getWorldObj(), vec, endVec, 2.0f, 38027, 58583);
        }
        ent.attackEntityFrom(DamageSource.magic, 4.0f);
        this.mana -= 200;
    }

    public int getMaxMana() {
        return 300;
    }

    public int getColor() {
        return 0x664422;
    }
}

