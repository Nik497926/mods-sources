package mireille.common.blocks;

import net.minecraft.block.material.*;
import mireille.*;
import cpw.mods.fml.common.registry.*;
import net.minecraft.block.*;

public class FencesWood extends BlockFence
{
    public FencesWood(final String name, final String texture) {
        super(texture, Material.wood);
        this.setBlockName(name);
        this.setCreativeTab(StrangerMind.StrangerMind);
        this.setStepSound(FencesWood.soundTypeWood);
        this.setHardness(3.0f);
        this.setHarvestLevel("axe", 1);
        GameRegistry.registerBlock((Block)this, name);
    }
}
