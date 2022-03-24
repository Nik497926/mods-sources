package mireille.common.blocks;

import net.minecraft.util.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.block.material.*;
import mireille.*;
import cpw.mods.fml.common.registry.*;
import net.minecraft.block.*;
import net.minecraft.client.renderer.texture.*;

public class BlocksLavaFlow extends BlockDynamicLiquid
{
    @SideOnly(Side.CLIENT)
    protected IIcon[] fluidIcon;
    
    public BlocksLavaFlow(final String name) {
        super(Material.lava);
        this.setBlockName(name);
        this.setCreativeTab(StrangerMind.StrangerMind);
        this.setLightLevel(1.0f);
        this.setLightOpacity(255);
        GameRegistry.registerBlock((Block)this, name);
    }
    
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(final IIconRegister iconRegister) {
        this.fluidIcon = new IIcon[] { iconRegister.registerIcon("mireille:fluid/Magma_still"), iconRegister.registerIcon("mireille:fluid/Magma_flow") };
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(final int side, final int meta) {
        return (side != 0 && side != 1) ? this.fluidIcon[1] : this.fluidIcon[0];
    }
}
