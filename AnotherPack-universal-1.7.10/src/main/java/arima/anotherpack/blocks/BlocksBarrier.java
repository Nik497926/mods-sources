package arima.anotherpack.blocks;

import java.util.Random;

import arima.anotherpack.core.Main;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class BlocksBarrier extends Block {

	public BlocksBarrier(String name, String texture) {
		super(Material.glass);
		this.setBlockName(name);
		this.setBlockTextureName(texture);
		this.setCreativeTab(Main.tabAP2DesignBlocks);
		this.setBlockUnbreakable();
	    this.setLightOpacity(0);
		GameRegistry.registerBlock(this, name);
	}
	
    public int quantityDropped(Random par1Random)
    {
        return 0;
    }
    

    @SideOnly(Side.CLIENT)


    public int getRenderBlockPass()
    {
        return 0;
    }


    public boolean isOpaqueCube()
    {
        return false;
    }


    public boolean renderAsNormalBlock()
    {
        return false;
    }
    
    protected boolean canSilkHarvest()
    {
        return true;
    }
}
