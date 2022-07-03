/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block.subtile.functional;

import com.meteor.extrabotany.api.ExtraBotanyAPI;
import com.meteor.extrabotany.common.lexicon.LexiconModData;
import java.util.List;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import vazkii.botania.api.item.IRelic;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileFunctional;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.item.ModItems;

public class SubTileArtifaconia
extends SubTileFunctional {
    private static final int RANGE = 2;
    int cd = 0;

    public LexiconEntry getEntry() {
        return LexiconModData.artifaconia;
    }

    public void onUpdate() {
        super.onUpdate();
        if (this.cd > 0) {
            --this.cd;
            return;
        }
        if (this.mana >= 10000) {
            List<EntityItem> items = this.supertile.getWorldObj().getEntitiesWithinAABB(EntityItem.class, AxisAlignedBB.getBoundingBox((double)((double)this.supertile.xCoord + 0.5 - 2.0), (double)((double)this.supertile.yCoord + 0.5 - 2.0), (double)((double)this.supertile.zCoord + 0.5 - 2.0), (double)((double)this.supertile.xCoord + 0.5 + 2.0), (double)((double)this.supertile.yCoord + 0.5 + 2.0), (double)((double)this.supertile.zCoord + 0.5 + 2.0)));
            for (EntityItem it : items) {
                if (it.isDead || it.age <= 25 || !(it.getEntityItem().getItem() instanceof IRelic)) continue;
                NBTTagCompound nbt = ItemNBTHelper.getNBT((ItemStack)it.getEntityItem());
                nbt.removeTag("soulbind");
                ItemNBTHelper.injectNBT((ItemStack)it.getEntityItem(), (NBTTagCompound)nbt);
                this.mana = 0;
                this.cd = 200;
                return;
            }
        }
    }

    public int getColor() {
        return 5960950;
    }

    public boolean acceptsRedstone() {
        return true;
    }

    public int getMaxMana() {
        return 10000;
    }

    public RadiusDescriptor getRadius() {
        return new RadiusDescriptor.Square(this.toChunkCoordinates(), 2);
    }

    static {
        ExtraBotanyAPI.whitelistItemFromArtifaconia(com.meteor.extrabotany.common.item.ModItems.hermeswand);
        ExtraBotanyAPI.whitelistItemFromArtifaconia(com.meteor.extrabotany.common.item.ModItems.maxwelldemon);
        ExtraBotanyAPI.whitelistItemFromArtifaconia(com.meteor.extrabotany.common.item.ModItems.eternalslience);
        ExtraBotanyAPI.whitelistItemFromArtifaconia(com.meteor.extrabotany.common.item.ModItems.excaliber);
        ExtraBotanyAPI.whitelistItemFromArtifaconia(com.meteor.extrabotany.common.item.ModItems.valkyriecombatuniform);
        ExtraBotanyAPI.whitelistItemFromArtifaconia(ModItems.infiniteFruit);
        ExtraBotanyAPI.whitelistItemFromArtifaconia(ModItems.kingKey);
        ExtraBotanyAPI.whitelistItemFromArtifaconia(ModItems.odinRing);
        ExtraBotanyAPI.whitelistItemFromArtifaconia(ModItems.lokiRing);
        ExtraBotanyAPI.whitelistItemFromArtifaconia(ModItems.thorRing);
        ExtraBotanyAPI.whitelistItemFromArtifaconia(ModItems.flugelEye);
    }
}

