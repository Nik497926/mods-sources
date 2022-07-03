/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block.subtile.generating;

import com.meteor.extrabotany.common.lexicon.LexiconModData;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileGenerating;
import vazkii.botania.common.block.BlockFloatingSpecialFlower;
import vazkii.botania.common.block.BlockSpecialFlower;

public class SubTileNumeronbalsam
extends SubTileGenerating {
    private int cd = 0;
    private int DELAY = 100;
    private int RANGE = 3;

    public int getColor() {
        return 16406359;
    }

    public LexiconEntry getEntry() {
        return LexiconModData.numeronbalsam;
    }

    public void onUpdate() {
        super.onUpdate();
        if (this.supertile.getWorldObj().isRemote) {
            return;
        }
        ++this.cd;
        if (this.cd < this.DELAY) {
            return;
        }
        this.cd = 0;
        List ent = this.supertile.getWorldObj().getEntitiesWithinAABB(EntityItem.class, AxisAlignedBB.getBoundingBox((double)(this.supertile.xCoord - this.RANGE), (double)(this.supertile.yCoord - this.RANGE), (double)(this.supertile.zCoord - this.RANGE), (double)(this.supertile.xCoord + this.RANGE + 1), (double)(this.supertile.yCoord + this.RANGE + 1), (double)(this.supertile.zCoord + this.RANGE + 1)));
        if (ent.size() == 0) {
            return;
        }
        int rand = 0 + (int)(Math.random() * (double)(ent.size() - 1));
        EntityItem inItemEntity = (EntityItem)ent.get(rand);
        if (inItemEntity.isDead) {
            return;
        }
        ItemStack inItem = inItemEntity.getEntityItem().copy();
        if (inItem == null) {
            return;
        }
        if (!(inItem.getItem() instanceof ItemBlock)) {
            return;
        }
        Block inItemBlock = Block.getBlockFromItem((Item)inItem.getItem());
        if (!(inItemBlock instanceof BlockSpecialFlower) && !(inItemBlock instanceof BlockFloatingSpecialFlower)) {
            return;
        }
        if (this.mana == this.getMaxMana()) {
            return;
        }
        double[] posIn = new double[]{inItemEntity.posX, inItemEntity.posY, inItemEntity.posZ};
        inItemEntity.setDead();
        --inItem.stackSize;
        if (inItem.stackSize > 0) {
            EntityItem outItemEntity = new EntityItem(this.supertile.getWorldObj(), posIn[0], posIn[1], posIn[2], inItem);
            this.supertile.getWorldObj().spawnEntityInWorld((Entity)outItemEntity);
        }
        this.mana = this.getMaxMana() - this.mana < 1000 ? this.getMaxMana() : (this.mana += 1000);
    }

    public int getMaxMana() {
        return 5000;
    }

    public boolean acceptsRedstone() {
        return true;
    }

    public RadiusDescriptor getRadius() {
        return new RadiusDescriptor.Square(this.toChunkCoordinates(), 11);
    }
}

