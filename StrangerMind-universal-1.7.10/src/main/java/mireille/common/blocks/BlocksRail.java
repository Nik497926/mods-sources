package mireille.common.blocks;

import mireille.*;
import cpw.mods.fml.common.registry.*;
import net.minecraft.block.*;

public class BlocksRail extends BlockRail
{
    public BlocksRail(final String name, final String texture) {
        this.setBlockName(name);
        this.setBlockTextureName(texture);
        this.setCreativeTab(StrangerMind.StrangerMind);
        this.setHardness(1.0f);
        GameRegistry.registerBlock((Block)this, name);
    }
}
