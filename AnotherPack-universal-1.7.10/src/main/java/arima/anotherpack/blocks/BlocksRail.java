package arima.anotherpack.blocks;

import arima.anotherpack.core.Main;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.BlockRail;

public class BlocksRail extends BlockRail {

	   public BlocksRail(String name, String texture) {
	      this.setBlockName(name);
	      this.setBlockTextureName(texture);
	      this.setCreativeTab(Main.tabAP2DesignBlocks);
	      this.setHardness(1.0F);
	      GameRegistry.registerBlock(this, name);
	   }
	}
