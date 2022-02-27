package arima.anotherpack.blocks;

import java.util.List;

import arima.anotherpack.core.Main;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class BlocksBox extends Block {

	private int count;
	@SideOnly(Side.CLIENT)
	private IIcon[] icon;
	@SideOnly(Side.CLIENT)
	private IIcon[] iconTop;

	public BlocksBox(String name, String texture, int count) {
		super(Material.wood);
		this.setBlockName(name);
		this.setBlockTextureName(texture);
		this.setCreativeTab(Main.tabAP2DesignBlocks);
		this.setHardness(3.0F);
		this.count = count;
		this.setHarvestLevel("axe", 1);
		GameRegistry.registerBlock(this, ItemBlockMetadata.class, name);
	}

	   @SideOnly(Side.CLIENT)
	   public IIcon getIcon(int side, int meta) {
	      return side == 1?this.iconTop[meta]:this.icon[meta];
	   }

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister) {
		this.icon = new IIcon[this.count];
		this.iconTop = new IIcon[this.count];
		for (int i = 0; i < this.count; ++i) {
			this.icon[i] = par1IconRegister.registerIcon(this.getTextureName() + "_" + i);
			this.iconTop[i] = par1IconRegister.registerIcon(this.getTextureName() + "_top_"+i);
		}
	}

	@Override
	public int damageDropped(int meta) {
		return meta;
	}

	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List subItems) {
		for (int n = 0; n < this.count; ++n) {
			subItems.add(new ItemStack(this, 1, n));
		}
	}

}
