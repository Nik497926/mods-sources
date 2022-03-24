package mireille.common.blocks;

import net.minecraft.block.*;
import net.minecraft.block.material.*;
import mireille.*;
import cpw.mods.fml.common.registry.*;
import net.minecraft.world.*;
import net.minecraft.entity.*;
import net.minecraft.util.*;

public class BlocksWeb extends Block
{
    private String texture;
    
    public BlocksWeb(final String name, final String texture) {
        super(Material.web);
        this.setBlockName(name);
        this.setBlockTextureName(texture);
        this.setCreativeTab(StrangerMind.StrangerMind);
        this.setLightOpacity(0);
        GameRegistry.registerBlock((Block)this, name);
    }
    
    public boolean isOpaqueCube() {
        return false;
    }
    
    public AxisAlignedBB getCollisionBoundingBoxFromPool(final World par1World, final int par2, final int par3, final int par4) {
        return null;
    }
    
    public int getRenderType() {
        return 1;
    }
    
    public boolean renderAsNormalBlock() {
        return false;
    }
    
    protected boolean canSilkHarvest() {
        return true;
    }
    
    public void onImpact(final World world, final int x, final int y, final int z, final Entity entity) {
        entity.setInWeb();
        entity.attackEntityFrom(DamageSource.magic, 1.0f);
    }
}
