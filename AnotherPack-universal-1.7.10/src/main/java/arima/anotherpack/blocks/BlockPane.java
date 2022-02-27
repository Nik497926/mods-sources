package arima.anotherpack.blocks;

import java.util.List;
import java.util.Random;

import arima.anotherpack.core.Main;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;

public class BlockPane extends Block {
	public BlockPane(String name, String texture) {
		super(Material.rock);
		this.setBlockName(name);
		this.setBlockTextureName(texture);
		this.setCreativeTab(Main.tabAP2DesignBlocks);
		this.setLightLevel(0.4F);
		GameRegistry.registerBlock(this, name);
	}

	@SideOnly(Side.CLIENT)
	public int getRenderType() {
		return 1;
	}

	@SideOnly(Side.CLIENT)
	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	   public Item getItemDropped(int par1, Random par2Random, int par3)
	   {
	       return Items.diamond;
	   }

}