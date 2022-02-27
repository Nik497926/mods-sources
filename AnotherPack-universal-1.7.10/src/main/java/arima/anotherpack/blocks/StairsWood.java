package arima.anotherpack.blocks;

import arima.anotherpack.core.Main;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.util.IIcon;

public class StairsWood extends BlockStairs {
		
	   private int texture_meta;
	   private Block block_t;
	   public StairsWood(String name, Block block, int meta) {
	      super(block, 0);
	      this.setBlockName(name);
	      this.setCreativeTab(Main.tabAP2WoodBlocks);
	      this.setHardness(3.0F);
	      this.setHarvestLevel("axe", 1);
	      this.setStepSound(soundTypeWood);
	      this.setLightOpacity(0);
	      GameRegistry.registerBlock(this, name);
	      this.texture_meta = meta;
	      this.block_t = block;
}
	   @SideOnly(Side.CLIENT)
	   public IIcon getIcon(int side, int meta) {
	      return this.block_t.getIcon(1, this.texture_meta);
	   }
	   
}
