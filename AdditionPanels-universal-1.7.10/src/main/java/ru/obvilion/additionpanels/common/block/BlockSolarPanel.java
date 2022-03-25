/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  advsolar.common.AdvancedSolarPanel
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package ru.obvilion.additionpanels.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import ru.justagod.cutter.GradleSide;
import ru.justagod.cutter.GradleSideOnly;
import ru.obvilion.additionpanels.AdditionPanels;
import ru.obvilion.additionpanels.common.tileentity.TileEntitySolarPanel;
import ru.obvilion.additionpanels.common.utils.ItemStackUtils;

public class BlockSolarPanel extends BlockContainer {
    protected IIcon[] icons;
    private TileEntitySolarPanel.PanelData data;

    public TileEntitySolarPanel.PanelData getData() {
        return data;
    }

    public BlockSolarPanel(TileEntitySolarPanel.PanelData data) {
        super(Material.rock);
        this.data = data;
        this.setBlockName(data.getPanelName());
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
    public void registerBlockIcons(IIconRegister iIconRegister) {
        this.icons = new IIcon[3];
        this.icons[0] = iIconRegister.registerIcon(AdditionPanels.MODID+":"+data.getPanelName()+"_side");
        this.icons[1] = iIconRegister.registerIcon(AdditionPanels.MODID+":"+data.getPanelName()+"_top");
        this.icons[2] = iIconRegister.registerIcon(AdditionPanels.MODID+":"+data.getPanelName()+"_bottom");
    }

    public IIcon getIcon(int side, int metadata) {
        switch (side) {
            case 0: {
                return this.icons[2];
            }
            case 1: {
                return this.icons[1];
            }
        }
        return this.icons[0];
    }

    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntitySolarPanel(data);
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

