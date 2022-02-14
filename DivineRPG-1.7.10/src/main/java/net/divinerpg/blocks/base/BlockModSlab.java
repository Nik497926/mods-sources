/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockSlab
 *  net.minecraft.item.Item
 *  net.minecraft.world.World
 */
package net.divinerpg.blocks.base;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.divinerpg.blocks.base.BlockMod;
import net.divinerpg.utils.LangRegistry;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class BlockModSlab
extends BlockSlab {
    public final String NAME;
    private Block single;

    public BlockModSlab(BlockMod base, boolean stack, String remove, Block single) {
        super(stack, base.blockType.getMaterial());
        this.setHardness(base.getBlockHardness(null, 0, 0, 0));
        this.setResistance(base.getExplosionResistance(null) * 5.0f / 3.0f);
        this.setBlockTextureName("divinerpg:" + base.name);
        this.NAME = base.name.replace(remove, "") + "Slab";
        this.setBlockName(this.NAME);
        if (!stack) {
            LangRegistry.addBlock((Block)this);
            this.setCreativeTab(DivineRPGTabs.blocks);
            this.useNeighborBrightness = true;
        }
        this.single = single;
    }

    public String func_150002_b(int i) {
        return super.getUnlocalizedName();
    }

    public Item getItemDropped(int meta, Random random, int fortune) {
        return this.single == null ? Item.getItemFromBlock((Block)this) : Item.getItemFromBlock((Block)this.single);
    }

    @SideOnly(value=Side.CLIENT)
    public Item getItem(World w, int x, int y, int z) {
        return this.single == null ? Item.getItemFromBlock((Block)this) : Item.getItemFromBlock((Block)this.single);
    }
}

