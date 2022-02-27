package arima.anotherpack.blocks;

import arima.anotherpack.core.Main;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;

public class SlabsWood extends BlockSlab {

	   public SlabsWood(String name, String texture) {
	      super(false, Material.wood);
	      this.setBlockName(name);
	      this.setBlockTextureName(texture);
	      this.setCreativeTab(Main.tabAP2WoodBlocks);
	      this.setHardness(3.0F);
	      this.setHarvestLevel("axe", 1);
	      this.setLightOpacity(0);
	      GameRegistry.registerBlock(this, name);
	   }

	@Override
	public String func_150002_b(int p_150002_1_) {
		return null;
	}
	}