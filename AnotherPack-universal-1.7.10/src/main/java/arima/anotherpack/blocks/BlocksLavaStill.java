package arima.anotherpack.blocks;

import arima.anotherpack.core.Main;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class BlocksLavaStill extends BlockLiquid {

	@SideOnly(Side.CLIENT)
	protected IIcon[] fluidIcon;

	public BlocksLavaStill(String name) {
		super(Material.lava);
		this.setBlockName(name);
		this.setCreativeTab(Main.tabAP2DesignBlocks);
		this.setLightLevel(1.0F);
		this.setLightOpacity(255);
		GameRegistry.registerBlock(this, name);
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		this.fluidIcon = new IIcon[] { iconRegister.registerIcon("arima:fluid/Magma_still"),
				iconRegister.registerIcon("arima:fluid/Magma_flow") };
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return side != 0 && side != 1 ? this.fluidIcon[1] : this.fluidIcon[0];
	}
}