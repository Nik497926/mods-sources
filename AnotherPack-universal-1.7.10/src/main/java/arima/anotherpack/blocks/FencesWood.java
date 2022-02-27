package arima.anotherpack.blocks;

import arima.anotherpack.core.Main;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;

public class FencesWood extends BlockFence {

	   public FencesWood(String name, String texture) {
	      super(texture, Material.wood);
	      this.setBlockName(name);
	      this.setCreativeTab(Main.tabAP2WoodBlocks);
	      this.setStepSound(soundTypeWood);
	      this.setHardness(3.0F);
	      this.setHarvestLevel("axe", 1);
	      GameRegistry.registerBlock(this, name);
	   }
	}
