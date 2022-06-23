/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.block.electicity;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.simplemc.senergetics.common.block.BlockContainerBaseFacing;
import ru.simplemc.senergetics.common.item.electicity.ItemBlockSmartSolarPanel;
import ru.simplemc.senergetics.common.tileentity.electricity.TileEntitySmartTransformer;
import ru.simplemc.senergetics.util.InventoryUtils;

public class BlockSmartTransformer
extends BlockContainerBaseFacing {
    public BlockSmartTransformer() {
        super(Material.iron, "block_smart_transformer", ItemBlockSmartSolarPanel.class);
        this.setStepSound(soundTypeMetal);
        this.setHardness(1.0f);
    }

    public TileEntity createTileEntity(World world, int metadata) {
        return new TileEntitySmartTransformer();
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.blockIcon = iconRegister.registerIcon(this.getTextureName() + "_side");
        this.frontIcon = iconRegister.registerIcon(this.getTextureName() + "_front");
        this.topIcon = this.blockIcon;
        this.bottomIcon = this.blockIcon;
    }

    public void breakBlock(World world, int x, int y, int z, Block block, int p_149749_6_) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (InventoryUtils.dropItemsFromInventory(tileEntity)) {
            world.func_147453_f(x, y, z, block);
        }
        super.breakBlock(world, x, y, z, block, p_149749_6_);
    }
}

