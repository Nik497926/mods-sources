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
import ru.obvilion.additionpanels.common.tileentity.TileEntityQGenerator;
import ru.obvilion.additionpanels.AdditionPanels;

public class BlockQGenerator extends BlockContainer {
//    protected IIcon[] icons;
    private final String name;

    public String getName() {
        return name;
    }

    private final int maxGeneration;
    @SideOnly(Side.CLIENT)
    protected String getTextureName() {
        return AdditionPanels.MODID + ":" + this.getUnlocalizedName().replace("tile.", "") ;
    }

    public int getMaxGeneration() {
        return maxGeneration;
    }

    public BlockQGenerator(String name, int maxGeneration) {
        super(Material.rock);
        this.setBlockName(name);
        this.setStepSound(Block.soundTypeMetal);
        this.setHardness(3.0f);
        this.setCreativeTab(AdditionPanels.TAB);
        this.name = name;
        this.maxGeneration = maxGeneration;
    }

//
//    public IIcon getIcon(int side, int metadata) {
//        switch (side) {
//            case 0: {
//                return this.icons[2];
//            }
//            case 1: {
//                return this.icons[1];
//            }
//        }
//        return this.icons[0];
//    }

    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityQGenerator(name,maxGeneration);
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
