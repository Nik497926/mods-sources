/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.block.electicity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import ru.simplemc.senergetics.SEnergetics;
import ru.simplemc.senergetics.common.block.BlockContainerBase;
import ru.simplemc.senergetics.common.item.electicity.ItemBlockSmartSolarPanel;
import ru.simplemc.senergetics.common.tileentity.electricity.TileEntitySolarPanel;
import ru.simplemc.senergetics.util.InventoryUtils;

public class BlockSmartSolarPanel
extends BlockContainerBase {
    private final IIcon[] textureTop = new IIcon[9];
    private IIcon textureSide;
    private IIcon textureBottom;

    public BlockSmartSolarPanel() {
        super(Material.iron, "block_solar_panel", ItemBlockSmartSolarPanel.class);
        this.setStepSound(soundTypeMetal);
        this.setHardness(1.0f);
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        this.textureTop[0] = register.registerIcon("senergetics:panel/block_solar_panel_top");
        this.textureTop[1] = register.registerIcon("senergetics:panel/block_solar_panel_top_photon");
        this.textureTop[2] = register.registerIcon("senergetics:panel/block_solar_panel_top_quark");
        this.textureTop[3] = register.registerIcon("senergetics:panel/block_solar_panel_top_barion");
        this.textureTop[4] = register.registerIcon("senergetics:panel/block_solar_panel_top_gluon");
        this.textureTop[5] = register.registerIcon("senergetics:panel/block_solar_panel_top_fermion");
        this.textureTop[6] = register.registerIcon("senergetics:panel/block_solar_panel_top_boson");
        this.textureTop[7] = register.registerIcon("senergetics:panel/block_solar_panel_top_quantum");
        this.textureTop[8] = register.registerIcon("senergetics:panel/block_solar_panel_top_neutron");
        this.textureSide = register.registerIcon("senergetics:panel/block_solar_panel_side");
        this.textureBottom = register.registerIcon("senergetics:panel/block_solar_panel_bottom");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        if (side == 1) {
            return this.textureTop[0];
        }
        if (side == 0) {
            return this.textureBottom;
        }
        return this.textureSide;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(IBlockAccess blockAccess, int x, int y, int z, int side) {
        TileEntity tileEntity;
        if (side == 1 && (tileEntity = blockAccess.getTileEntity(x, y, z)) instanceof TileEntitySolarPanel) {
            return this.textureTop[((TileEntitySolarPanel)tileEntity).getParticleCollectorId()];
        }
        return super.getIcon(blockAccess, x, y, z, side);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntitySolarPanel();
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (tileEntity instanceof TileEntitySolarPanel && !player.isSneaking()) {
            if (!world.isRemote) {
                player.openGui((Object)SEnergetics.getInstance(), 2, world, x, y, z);
            }
            return true;
        }
        return super.onBlockActivated(world, x, y, y, player, side, hitX, hitY, hitZ);
    }

    public void breakBlock(World world, int x, int y, int z, Block block, int p_149749_6_) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (InventoryUtils.dropItemsFromInventory(tileEntity)) {
            world.func_147453_f(x, y, z, block);
        }
        super.breakBlock(world, x, y, z, block, p_149749_6_);
    }
}

