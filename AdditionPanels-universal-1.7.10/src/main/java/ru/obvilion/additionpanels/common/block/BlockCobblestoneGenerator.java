package ru.obvilion.additionpanels.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.justagod.cutter.GradleSide;
import ru.justagod.cutter.GradleSideOnly;
import ru.obvilion.additionpanels.AdditionPanels;
import ru.obvilion.additionpanels.common.tileentity.TileEntityСobblestoneGenerator;
import ru.obvilion.additionpanels.common.utils.ItemStackUtils;

public class BlockCobblestoneGenerator extends BlockContainer {
    public BlockCobblestoneGenerator(String name) {
        super(Material.iron);
        this.setBlockName(name);
        this.setStepSound(Block.soundTypeMetal);
        this.setHardness(3.0f);
        this.setCreativeTab(AdditionPanels.TAB);
    }
    @GradleSideOnly(GradleSide.SERVER)
    public void breakBlock(World world, int i, int j, int k, Block par5, int par6) {
        TileEntity tileentity = world.getTileEntity(i, j, k);
        ItemStackUtils.dropItems(tileentity, world);
        world.removeTileEntity(i, j, k);
        super.breakBlock(world, i, j, k, par5, par6);
    }


    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityСobblestoneGenerator();
    }
    @SideOnly(Side.CLIENT)
    protected String getTextureName() {
        return AdditionPanels.MODID + ":" + this.getUnlocalizedName().replace("tile.", "") ;
    }
    @GradleSideOnly(GradleSide.SERVER)
    public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer player, int s, float f1, float f2, float f3) {
        if (player.isSneaking()) {
            return false;
        }
        if (world.isRemote) {
            return true;
        }
        TileEntity tileentity = world.getTileEntity(i, j, k);
        if (tileentity != null) {
            player.openGui(AdditionPanels.instance, 1, world, i, j, k);
        }
        return true;
    }
}
