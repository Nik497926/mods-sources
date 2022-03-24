package mireille.common.blocks;

import net.minecraft.block.*;
import net.minecraft.block.material.*;
import mireille.*;
import cpw.mods.fml.common.registry.*;

public class BlocksOre extends Block
{
    public BlocksOre(final String name, final String texture) {
        super(Material.rock);
        this.setBlockName(name);
        this.setBlockTextureName(texture);
        this.setCreativeTab(StrangerMind.StrangerMind);
        this.setStepSound(Block.soundTypeStone);
        this.setHardness(3.0f);
        this.setHarvestLevel("pickaxe", 3);
        GameRegistry.registerBlock((Block)this, name);
    }
}
