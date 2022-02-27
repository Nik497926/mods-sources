package arima.anotherpack.blocks;

import arima.anotherpack.core.Main;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.BlockLadder;

public class BlocksLadder extends BlockLadder {

	   public BlocksLadder(String name, String texture) {
	      this.setBlockName(name);
	      this.setBlockTextureName(texture);
	      this.setCreativeTab(Main.tabAP2DesignBlocks);
	      this.setHardness(3.0F);
	      this.setStepSound(soundTypeLadder);
	      this.setHarvestLevel("axe", 1);
	      GameRegistry.registerBlock(this, name);
	   }
	}