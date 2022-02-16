/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package net.divinerpg.blocks.iceika;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.divinerpg.blocks.base.BlockModFurnace;
import net.divinerpg.blocks.iceika.tileentity.TileEntityCoalstoneFurnace;
import net.divinerpg.client.GuiHandler;
import net.divinerpg.utils.blocks.IceikaBlocks;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockCoalstoneFurnace
extends BlockModFurnace {
    @SideOnly(value=Side.CLIENT)
    protected IIcon side;
    @SideOnly(value=Side.CLIENT)
    protected IIcon top;
    @SideOnly(value=Side.CLIENT)
    protected IIcon front;

    public BlockCoalstoneFurnace(String name, boolean act) {
        super(name, act, GuiHandler.coalstone);
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister icon) {
        this.side = icon.registerIcon("divinerpg:coalstone");
        this.front = icon.registerIcon(this.active ? "divinerpg:coalstoneFurnace_front_on" : "divinerpg:coalstoneFurnace_front_off");
        this.top = icon.registerIcon("divinerpg:coalstone");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int size, int meta) {
        if (size == 3 && meta == 0) {
            return this.front;
        }
        return size == 1 ? this.top : (size == 0 ? this.top : (size != meta ? this.side : this.front));
    }

    public static void updateActiveStates(boolean active, World w, int x, int y, int z) {
        int meta = w.getBlockMetadata(x, y, z);
        TileEntity tileentity = w.getTileEntity(x, y, z);
        keepInventory = true;
        if (active) {
            w.setBlock(x, y, z, IceikaBlocks.coalstoneFurnaceOn);
        } else {
            w.setBlock(x, y, z, IceikaBlocks.coalstoneFurnace);
        }
        keepInventory = false;
        w.setBlockMetadataWithNotify(x, y, z, meta, 2);
        if (tileentity != null) {
            tileentity.validate();
            w.setTileEntity(x, y, z, tileentity);
        }
    }

    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileEntityCoalstoneFurnace();
    }
}

