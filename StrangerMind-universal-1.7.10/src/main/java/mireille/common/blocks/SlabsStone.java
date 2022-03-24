package mireille.common.blocks;

import net.minecraft.block.material.*;
import mireille.*;
import cpw.mods.fml.common.registry.*;
import net.minecraft.block.*;

public class SlabsStone extends BlockSlab
{
    public SlabsStone(final String name, final String texture) {
        super(false, Material.rock);
        this.setBlockName(name);
        this.setBlockTextureName(texture);
        this.setCreativeTab(StrangerMind.StrangerMind);
        this.setHardness(3.0f);
        this.setHarvestLevel("pickaxe", 1);
        this.setLightOpacity(0);
        GameRegistry.registerBlock((Block)this, name);
    }
    
    public String func_150002_b(final int p_150002_1_) {
        return null;
    }
}
