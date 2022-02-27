package arima.anotherpack.blocks;

import arima.anotherpack.core.Main;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.BlockLadder;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.IBlockAccess;

public class BlocksCloth extends BlockLadder {

	   public BlocksCloth(String name, String texture) {
	      this.setBlockName(name);
	      this.setBlockTextureName(texture);
	      this.setCreativeTab(Main.tabAP2DesignBlocks);
	      this.setHardness(1.0F);
	      GameRegistry.registerBlock(this, name);
	   }

	   public boolean isLadder(IBlockAccess world, int x, int y, int z, EntityLivingBase entity) {
	      return false;
	   }
}