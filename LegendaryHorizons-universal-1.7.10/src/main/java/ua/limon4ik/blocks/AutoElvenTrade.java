package ua.limon4ik.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import thaumicenergistics.common.blocks.AbstractBlockAEWrenchable;
import ua.limon4ik.LegendaryHorizons;
import ua.limon4ik.tiles.TileEntityAutoElvenTrade;

public class AutoElvenTrade extends AbstractBlockAEWrenchable {
    public AutoElvenTrade() {
        super(Material.iron);
        setHardness(1.0f);
        setStepSound(Block.soundTypeMetal);
        setCreativeTab(LegendaryHorizons.tab);
        setBlockName("AutoElvenTrade");
        setBlockTextureName("LegendaryHorizons:AutoElvenTrade");
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityAutoElvenTrade(p_149915_1_);
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }
}
