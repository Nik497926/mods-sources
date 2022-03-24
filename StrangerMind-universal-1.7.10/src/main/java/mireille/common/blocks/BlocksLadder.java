package mireille.common.blocks;

import mireille.*;
import cpw.mods.fml.common.registry.*;
import net.minecraft.block.*;

public class BlocksLadder extends BlockLadder
{
    public BlocksLadder(final String name, final String texture) {
        this.setBlockName(name);
        this.setBlockTextureName(texture);
        this.setCreativeTab(StrangerMind.StrangerMind);
        this.setHardness(3.0f);
        this.setStepSound(BlocksLadder.soundTypeLadder);
        this.setHarvestLevel("axe", 1);
        GameRegistry.registerBlock((Block)this, name);
    }
}
