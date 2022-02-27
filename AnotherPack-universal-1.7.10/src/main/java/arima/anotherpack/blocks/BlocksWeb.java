package arima.anotherpack.blocks;

import java.util.Random;

import arima.anotherpack.core.Main;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class BlocksWeb extends Block {
	private String texture;

	public BlocksWeb(String name, String texture) {
		super(Material.web);
		this.setBlockName(name);
		this.setBlockTextureName(texture);
		this.setCreativeTab(Main.tabAP2DesignBlocks);
		this.setLightOpacity(0);
		GameRegistry.registerBlock(this, name);
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
		return null;
	}

	public int getRenderType() {
		return 1;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	protected boolean canSilkHarvest() {
		return true;
	}

	public void onImpact(World world, int x, int y, int z, Entity entity) {
		entity.setInWeb();
		entity.attackEntityFrom(DamageSource.magic, 1.0F);
	}
}
