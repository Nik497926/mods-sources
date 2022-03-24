package mireille.common.blocks;

import net.minecraft.block.*;
import net.minecraft.block.material.*;
import mireille.*;
import cpw.mods.fml.common.registry.*;
import net.minecraft.world.*;
import net.minecraft.entity.player.*;

public class BlockWorkbench extends Block
{
    public BlockWorkbench(final String name, final String texture) {
        super(Material.iron);
        this.setBlockName(name);
        this.setBlockTextureName(texture);
        this.setCreativeTab(StrangerMind.StrangerMind);
        this.setStepSound(Block.soundTypeMetal);
        this.setHardness(3.0f);
        this.setHarvestLevel("pickaxe", 1);
        GameRegistry.registerBlock((Block)this, name);
    }
    
    public boolean onBlockActivated(final World world, final int x, final int y, final int z, final EntityPlayer player, final int par1, final float par2, final float par3, final float par4) {
        if (!world.isRemote) {
            player.openGui((Object)StrangerMind.instance, 4, world, x, y, z);
        }
        return true;
    }
}
