package mireille.common.blocks;

import net.minecraft.block.*;
import net.minecraft.util.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.block.material.*;
import mireille.*;
import cpw.mods.fml.common.registry.*;
import net.minecraft.client.renderer.texture.*;
import net.minecraft.creativetab.*;
import java.util.*;
import net.minecraft.item.*;

public class BlocksGrassBlocks extends Block
{
    private int count;
    @SideOnly(Side.CLIENT)
    private IIcon[] icon;
    @SideOnly(Side.CLIENT)
    private IIcon[] iconTop;
    
    public BlocksGrassBlocks(final String name, final String texture, final int count) {
        super(Material.grass);
        this.setBlockName(name);
        this.setBlockTextureName(texture);
        this.setCreativeTab(StrangerMind.StrangerMind);
        this.setStepSound(BlocksGrassBlocks.soundTypeGrass);
        this.setHardness(3.0f);
        this.count = count;
        this.setHarvestLevel("axe", 1);
        GameRegistry.registerBlock((Block)this, (Class)ItemBlockMetadata.class, name);
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(final int side, final int meta) {
        return (side == 1) ? this.iconTop[meta] : this.icon[meta];
    }
    
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(final IIconRegister par1IconRegister) {
        this.icon = new IIcon[this.count];
        this.iconTop = new IIcon[this.count];
        for (int i = 0; i < this.count; ++i) {
            this.icon[i] = par1IconRegister.registerIcon(this.getTextureName() + "_" + i);
            this.iconTop[i] = par1IconRegister.registerIcon(this.getTextureName() + "_top_" + i);
        }
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
}
