package mireille.common.blocks;

import net.minecraft.block.*;
import net.minecraft.block.material.*;
import mireille.*;
import cpw.mods.fml.common.registry.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.world.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.*;
import java.util.*;

public class BlocksFire extends Block
{
    public BlocksFire(final String name, final String texture) {
        super(Material.rock);
        this.setBlockName(name);
        this.setBlockTextureName(texture);
        this.setCreativeTab(StrangerMind.StrangerMind);
        this.setLightLevel(1.0f);
        this.setLightOpacity(255);
        this.setTickRandomly(true);
        GameRegistry.registerBlock((Block)this, name);
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
    
    public AxisAlignedBB getCollisionBoundingBoxFromPool(final World par1World, final int par2, final int par3, final int par4) {
        return null;
    }
    
    public void onEntityCollidedWithBlock(final World world, final int x, final int y, final int z, final Entity entity) {
        if (entity instanceof EntityPlayer) {
            entity.attackEntityFrom(DamageSource.magic, 50000.0f);
        }
    }
    
    public int tickRate(final World world) {
        return 30;
    }
    
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(final World world, final int x, final int y, final int z, final Random rand) {
        final float f = x + rand.nextFloat();
        final float f2 = y + rand.nextFloat();
        final float f3 = z + rand.nextFloat();
        world.spawnParticle("largesmoke", (double)f, (double)f2, (double)f3, 0.0, 0.0, 0.0);
    }
}
