package mireille.common.blocks;

import net.minecraft.block.material.*;
import mireille.*;
import cpw.mods.fml.common.registry.*;
import net.minecraft.block.*;

public class FencesStone extends BlockFence
{
    public FencesStone(final String name, final String texture) {
        super(texture, Material.iron);
        this.setBlockName(name);
        this.setCreativeTab(StrangerMind.StrangerMind);
        this.setStepSound(FencesStone.soundTypeStone);
        this.setHardness(3.0f);
        this.setHarvestLevel("pickaxe", 1);
        GameRegistry.registerBlock((Block)this, name);
    }
}
