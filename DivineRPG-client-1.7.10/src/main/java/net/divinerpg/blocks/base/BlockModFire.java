/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockFire
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package net.divinerpg.blocks.base;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import net.divinerpg.blocks.base.portal.BlockModPortal;
import net.divinerpg.utils.LangRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFire;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockModFire
extends BlockFire {
    protected ArrayList<BlockModPortal> portals = new ArrayList();
    protected String name;
    protected IIcon[] icons;

    public BlockModFire(String name) {
        this.name = name;
        this.setLightLevel(1.0f);
        this.setCreativeTab(null);
        this.setBlockName(name);
        this.setBlockTextureName("divinerpg:" + name);
        GameRegistry.registerBlock((Block)this, (String)name);
        LangRegistry.addBlock((Block)this);
    }

    public void addPortal(BlockModPortal portal) {
        this.portals.add(portal);
    }

    protected void lightPortal(World world, int x, int y, int z) {
    }

    public void onBlockAdded(World world, int x, int y, int z) {
        this.lightPortal(world, x, y, z);
    }

    protected boolean canNeighborBurn(World world, int x, int y, int z) {
        return this.canCatchFire((IBlockAccess)world, x + 1, y, z, ForgeDirection.WEST) || this.canCatchFire((IBlockAccess)world, x - 1, y, z, ForgeDirection.EAST) || this.canCatchFire((IBlockAccess)world, x, y - 1, z, ForgeDirection.UP) || this.canCatchFire((IBlockAccess)world, x, y + 1, z, ForgeDirection.DOWN) || this.canCatchFire((IBlockAccess)world, x, y, z - 1, ForgeDirection.SOUTH) || this.canCatchFire((IBlockAccess)world, x, y, z + 1, ForgeDirection.NORTH);
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconReg) {
        this.icons = new IIcon[]{iconReg.registerIcon("divinerpg:blueFire_0"), iconReg.registerIcon("divinerpg:blueFire_1")};
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getFireIcon(int side) {
        return this.icons[side];
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return this.icons[0];
    }
}

