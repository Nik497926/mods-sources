/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block.decor;

import com.meteor.extrabotany.common.block.ModBlocks;
import net.minecraft.block.BlockSlab;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.common.block.decor.slabs.BlockLivingSlab;
import vazkii.botania.common.lexicon.LexiconData;

public class BlockGaiaQuartzSlab
extends BlockLivingSlab {
    public BlockGaiaQuartzSlab(boolean par2) {
        super(par2, ModBlocks.gaiaquartz, 0);
        this.setHardness(2.0f);
        this.setResistance(10.0f);
        this.setStepSound(soundTypeWood);
    }

    public BlockSlab getFullBlock() {
        return (BlockSlab)ModBlocks.gaiaquartzslabfull;
    }

    public BlockSlab getSingleBlock() {
        return (BlockSlab)ModBlocks.gaiaquartzslab;
    }

    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        return LexiconData.decorativeBlocks;
    }
}

