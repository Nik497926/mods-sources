package arima.anotherpack.blocks;

import arima.anotherpack.core.Main;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class VertBlock extends Block{
	 
	 public IIcon[] icons = new IIcon[6];
	
	 public VertBlock(String name, String texture) {
	      super(Material.wood);
	      this.setBlockName(name);
	      this.setBlockTextureName(texture);
	      this.setCreativeTab(Main.tabAP2DesignBlocks);
	      this.setStepSound(soundTypePiston);
	      this.setHardness(3.0F);
	      this.setHarvestLevel("axe", 1);
			GameRegistry.registerBlock(this, name);

	   }
	 
	 @Override
	 public void registerBlockIcons(IIconRegister reg) {
	     for (int i = 0; i < 6; i ++) {
	         this.icons[i] = reg.registerIcon(this.textureName + "_" + i);
	     }
	 }

	 @Override
	 public IIcon getIcon(int side, int meta) {
	     return this.icons[side];
	 }
	 
}
