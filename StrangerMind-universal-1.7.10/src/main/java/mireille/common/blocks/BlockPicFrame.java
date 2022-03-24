package mireille.common.blocks;

import net.minecraft.block.material.*;
import cpw.mods.fml.common.registry.*;
import net.minecraft.block.*;
import java.util.*;
import mireille.core.*;
import net.minecraft.world.*;
import cpw.mods.fml.relauncher.*;
import com.creativemd.creativecore.common.utils.*;
import net.minecraftforge.common.util.*;
import mireille.common.tileentity.*;
import net.minecraft.tileentity.*;
import net.minecraft.util.*;
import net.minecraft.init.*;
import net.minecraft.client.renderer.texture.*;
import net.minecraft.entity.player.*;
import com.creativemd.creativecore.core.*;
import mireille.proxy.*;
import net.minecraft.item.*;
import com.creativemd.creativecore.common.gui.*;
import mireille.client.gui.*;
import com.creativemd.creativecore.common.container.*;
import mireille.client.gui.container.*;

public class BlockPicFrame extends BlockContainer implements IGuiCreator
{
    private boolean animation;
    
    public BlockPicFrame(final String name, final boolean animation) {
        super(Material.wood);
        this.setBlockName(name);
        GameRegistry.registerBlock((Block)this, name);
        this.animation = animation;
    }
    
    public Item getItemDropped(final int p_149650_1_, final Random p_149650_2_, final int p_149650_3_) {
        return this.animation ? ModItems.UrlPaintAnim : ModItems.UrlPaint;
    }
    
    @SideOnly(Side.CLIENT)
    public Item getItem(final World p_149694_1_, final int p_149694_2_, final int p_149694_3_, final int p_149694_4_) {
        return this.animation ? ModItems.UrlPaintAnim : ModItems.UrlPaint;
    }
    
    public AxisAlignedBB getCollisionBoundingBoxFromPool(final World world, final int x, final int y, final int z) {
        final CubeObject cube = new CubeObject(0.0, 0.0, 0.0, 0.05, 1.0, 1.0);
        final ForgeDirection direction = ForgeDirection.getOrientation(world.getBlockMetadata(x, y, z));
        return CubeObject.rotateCube(cube, direction).getAxis().getOffsetBoundingBox((double)x, (double)y, (double)z);
    }
    
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBoxFromPool(final World world, final int x, final int y, final int z) {
        final TileEntity te = world.getTileEntity(x, y, z);
        if (te instanceof TileEntityPicFrame) {
            return ((TileEntityPicFrame)te).getBoundingBox();
        }
        final ForgeDirection direction = ForgeDirection.getOrientation(world.getBlockMetadata(x, y, z));
        final CubeObject cube = new CubeObject(0.0, 0.0, 0.0, 0.05, 1.0, 1.0);
        return CubeObject.rotateCube(cube, direction).getAxis().getOffsetBoundingBox((double)x, (double)y, (double)z);
    }
    
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(final int side, final int meta) {
        return Blocks.planks.getIcon(side, meta);
    }
    
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(final IIconRegister registry) {
    }
    
    @SideOnly(Side.CLIENT)
    public boolean renderAsNormalBlock() {
        return false;
    }
    
    public boolean onBlockActivated(final World world, final int x, final int y, final int z, final EntityPlayer player, final int side, final float offX, final float offY, final float offZ) {
        if (!world.isRemote) {
            ((EntityPlayerMP)player).openGui((Object)CreativeCore.instance, 0, world, x, y, z);
        }
        return true;
    }
    
    public int onBlockPlaced(final World world, final int x, final int y, final int z, final int side, final float offX, final float offY, final float offZ, final int meta) {
        return side;
    }
    
    public boolean isOpaqueCube() {
        return false;
    }
    
    @SideOnly(Side.CLIENT)
    public int getRenderType() {
        return ClientProxy.modelID;
    }
    
    public boolean isNormalCube() {
        return false;
    }
    
    public TileEntity createNewTileEntity(final World world, final int meta) {
        return (TileEntity)new TileEntityPicFrame();
    }
    
    @SideOnly(Side.CLIENT)
    public SubGui getGui(final EntityPlayer player, final ItemStack stack, final World world, final int x, final int y, final int z) {
        final TileEntity te = world.getTileEntity(x, y, z);
        return (te instanceof TileEntityPicFrame) ? new SubGuiPicOPF((TileEntityPicFrame)te, this.animation) : null;
    }
    
    public SubContainer getContainer(final EntityPlayer player, final ItemStack stack, final World world, final int x, final int y, final int z) {
        final TileEntity te = world.getTileEntity(x, y, z);
        return (te instanceof TileEntityPicFrame) ? new SubContainerPicOPF((TileEntityPicFrame)te, player, this.animation) : null;
    }
}
