package mireille.common.blocks;

import net.minecraft.block.*;
import net.minecraft.util.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.block.material.*;
import mireille.*;
import cpw.mods.fml.common.registry.*;
import net.minecraft.client.renderer.texture.*;
import net.minecraft.world.*;
import java.util.*;
import mireille.proxy.*;

public class BlocksBarrier extends Block
{
    @SideOnly(Side.CLIENT)
    public IIcon icon1;
    @SideOnly(Side.CLIENT)
    public IIcon icon2;
    
    public BlocksBarrier(final String name) {
        super(Material.glass);
        this.setBlockName(name);
        this.setBlockTextureName("mireille:barrier/barrier");
        this.setCreativeTab(StrangerMind.StrangerMind);
        this.setStepSound(Block.soundTypeGlass);
        this.setBlockUnbreakable();
        this.disableStats();
        this.setResistance(6000000.0f);
        GameRegistry.registerBlock((Block)this, name);
    }
    
    @SideOnly(Side.CLIENT)
    public String getItemIconName() {
        return "mireille:barrier_icon";
    }
    
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(final IIconRegister par1IconRegister) {
        this.icon1 = par1IconRegister.registerIcon("mireille:barrier/barrier");
        this.icon2 = par1IconRegister.registerIcon("mireille:barrier/barrier2");
    }
    
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(final World world, final int x, final int y, final int z, final Random rand) {
    }
    
    @SideOnly(Side.CLIENT)
    public int getRenderType() {
        return ClientProxy.modelID2;
    }
    
    public boolean isOpaqueCube() {
        return false;
    }
}
