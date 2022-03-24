package mireille.common.blocks;

import net.minecraft.block.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.block.material.*;
import mireille.*;
import cpw.mods.fml.common.registry.*;
import net.minecraft.creativetab.*;
import java.util.*;
import net.minecraft.item.*;
import net.minecraft.client.renderer.texture.*;
import net.minecraft.world.*;
import net.minecraft.util.*;

public class BlocksPortal extends Block
{
    private int count;
    @SideOnly(Side.CLIENT)
    private IIcon[] icon;
    
    public BlocksPortal(final String name, final String texture, final int count) {
        super(Material.glass);
        this.setBlockName(name);
        this.setBlockTextureName(texture);
        this.setCreativeTab(StrangerMind.StrangerMind);
        this.setLightLevel(1.0f);
        this.setLightOpacity(255);
        this.count = count;
        GameRegistry.registerBlock((Block)this, (Class)ItemBlockMetadata.class, name);
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
    
    public AxisAlignedBB getCollisionBoundingBoxFromPool(final World par1World, final int par2, final int par3, final int par4) {
        return null;
    }
}
