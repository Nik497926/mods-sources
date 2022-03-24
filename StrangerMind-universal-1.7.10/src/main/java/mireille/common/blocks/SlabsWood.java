package mireille.common.blocks;

import net.minecraft.block.material.*;
import mireille.*;
import cpw.mods.fml.common.registry.*;
import net.minecraft.block.*;

public class SlabsWood extends BlockSlab
{
    public SlabsWood(final String name, final String texture) {
        super(false, Material.wood);
        this.setBlockName(name);
        this.setBlockTextureName(texture);
        this.setCreativeTab(StrangerMind.StrangerMind);
        this.setHardness(3.0f);
        this.setHarvestLevel("axe", 1);
        this.setLightOpacity(0);
        GameRegistry.registerBlock((Block)this, name);
    }
    
    public String func_150002_b(final int p_150002_1_) {
        return null;
    }
}
