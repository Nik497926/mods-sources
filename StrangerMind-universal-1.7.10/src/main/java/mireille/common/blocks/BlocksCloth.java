package mireille.common.blocks;

import mireille.*;
import cpw.mods.fml.common.registry.*;
import net.minecraft.block.*;
import net.minecraft.world.*;
import net.minecraft.entity.*;

public class BlocksCloth extends BlockLadder
{
    public BlocksCloth(final String name, final String texture) {
        this.setBlockName(name);
        this.setBlockTextureName(texture);
        this.setCreativeTab(StrangerMind.StrangerMind);
        this.setHardness(1.0f);
        GameRegistry.registerBlock((Block)this, name);
    }
    
    public boolean isLadder(final IBlockAccess world, final int x, final int y, final int z, final EntityLivingBase entity) {
        return false;
    }
}
