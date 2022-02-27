package arima.anotherpack.blocks;

import arima.anotherpack.core.Main;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;

public class FencesStone extends BlockFence {

	   public FencesStone(String name, String texture) {
	      super(texture, Material.wood);
	      this.setBlockName(name);
	      this.setCreativeTab(Main.tabAP2StoneBlocks);
	      this.setStepSound(soundTypeStone);
	      this.setHardness(3.0F);
	      this.setHarvestLevel("pickaxe", 1);
	      GameRegistry.registerBlock(this, name);
	   }
	}
