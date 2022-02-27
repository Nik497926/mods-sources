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

public class BlocksIron extends Block {

	   private int count;
	   @SideOnly(Side.CLIENT)
	   private IIcon[] icon;

	   public BlocksIron(String name, String texture, int count) {
	      super(Material.iron);
	      this.setBlockName(name);
	      this.setBlockTextureName(texture);
	      this.setCreativeTab(Main.tabAPIronBlocks);
	      this.setStepSound(soundTypeMetal);
	      this.setHardness(5.0F);
	      this.setResistance(10F);
	      this.setHarvestLevel("pickaxe", 1);
	      this.count = count;
	      GameRegistry.registerBlock(this, ItemBlockMetadata.class, name);
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
	
	public boolean isOpaqueCube() {
	      return false;
	   }

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return this.icon[meta];
	}

	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister icon) {
		this.icon = new IIcon[this.count];
		for (int i = 0; i < this.count; ++i) {
			this.icon[i] = icon.registerIcon(this.getTextureName() + "_" + i);
		}
	}
	}
