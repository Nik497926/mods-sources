package arima.anotherpack.blocks;

import java.util.Random;

import arima.anotherpack.core.Main;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlocksFire extends Block {

	public BlocksFire(String name, String texture) {
		super(Material.rock);
		this.setBlockName(name);
		this.setBlockTextureName(texture);
		this.setCreativeTab(Main.tabAP2DesignBlocks);
		this.setLightLevel(1.0F);
		this.setLightOpacity(255);
		this.setTickRandomly(true);
		GameRegistry.registerBlock(this, name);
	}

	@SideOnly(Side.CLIENT)
	public int getRenderType() {
		return 1;
	}
	@SideOnly(Side.CLIENT)
	public boolean isOpaqueCube(){
	return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4) {
		return null;
	}

	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		if (entity instanceof EntityPlayer) {
			entity.attackEntityFrom(DamageSource.magic, 50000.0F);
		}

	}

	public int tickRate(World world) {
		return 30;
	}

	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
		float f = (float) x + rand.nextFloat();
		float f1 = (float) y + rand.nextFloat();
		float f2 = (float) z + rand.nextFloat();
		world.spawnParticle("largesmoke", (double) f, (double) f1, (double) f2, 0.0D, 0.0D, 0.0D);
	}
}
