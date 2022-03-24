package mireille.common.blocks;

import net.minecraft.block.*;
import mireille.*;
import cpw.mods.fml.common.registry.*;
import net.minecraft.util.*;
import cpw.mods.fml.relauncher.*;

public class StairsWood extends BlockStairs
{
    private int texture_meta;
    private Block block_t;
    
    public StairsWood(final String name, final Block block, final int meta) {
        super(block, 0);
        this.setBlockName(name);
        this.setCreativeTab(StrangerMind.StrangerMind);
        this.setHardness(3.0f);
        this.setHarvestLevel("axe", 1);
        this.setStepSound(StairsWood.soundTypeWood);
        this.setLightOpacity(0);
        GameRegistry.registerBlock((Block)this, name);
        this.texture_meta = meta;
        this.block_t = block;
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(final int side, final int meta) {
        return this.block_t.getIcon(1, this.texture_meta);
    }
}
