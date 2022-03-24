package mireille.common.blocks;

import net.minecraft.block.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.block.material.*;
import mireille.*;
import cpw.mods.fml.common.registry.*;
import net.minecraft.world.*;
import net.minecraft.util.*;
import net.minecraft.creativetab.*;
import java.util.*;
import net.minecraft.item.*;
import net.minecraft.client.renderer.texture.*;

public class BlocksGrass extends Block
{
    private int count;
    @SideOnly(Side.CLIENT)
    private IIcon[] icon;
    
    public BlocksGrass(final String name, final String texture, final int count) {
        super(Material.grass);
        this.setBlockName(name);
        this.setBlockTextureName(texture);
        this.setCreativeTab(StrangerMind.StrangerMind);
        this.setHardness(1.0f);
        this.count = count;
        GameRegistry.registerBlock((Block)this, (Class)ItemBlockMetadata.class, name);
    }
    
    public boolean isOpaqueCube() {
        return false;
    }
    
    public int getRenderType() {
        return 1;
    }
    
    public boolean renderAsNormalBlock() {
        return false;
    }
    
    public AxisAlignedBB getCollisionBoundingBoxFromPool(final World par1World, final int par2, final int par3, final int par4) {
        return null;
    }
    
    public int damageDropped(final int meta) {
        return meta;
    }
    
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(final Item item, final CreativeTabs tab, final List subItems) {
        for (int n = 0; n < this.count; ++n) {
            subItems.add(new ItemStack((Block)this, 1, n));
        }
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(final int side, final int meta) {
        return this.icon[meta];
    }
    
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(final IIconRegister icon) {
        this.icon = new IIcon[this.count];
        for (int i = 0; i < this.count; ++i) {
            this.icon[i] = icon.registerIcon(this.getTextureName() + "_" + i);
        }
    }
}
