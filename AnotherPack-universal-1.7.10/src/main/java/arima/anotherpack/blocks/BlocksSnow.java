package arima.anotherpack.blocks;

import java.util.Random;

import arima.anotherpack.core.Main;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlocksSnow extends Block {
	   public BlocksSnow(String name, String texture) {
		      super(Material.snow);
		      this.setBlockName(name);
		      this.setBlockTextureName(texture);
		      this.setCreativeTab(Main.tabAP2DesignBlocks);
		      this.setTickRandomly(true);
		      this.setHardness(0.1F);
		      this.setStepSound(soundTypeSnow);
		      this.setHarvestLevel("shovel", 1);
		      GameRegistry.registerBlock(this, name);
		   }

		   public int quantityDropped(Random par1Random) {
		      return 0;
		   }
}
