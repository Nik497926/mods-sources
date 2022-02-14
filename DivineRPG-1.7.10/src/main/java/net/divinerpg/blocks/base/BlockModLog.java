/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockRotatedPillar
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package net.divinerpg.blocks.base;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.divinerpg.utils.LangRegistry;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockModLog
extends BlockRotatedPillar {
    protected IIcon top;
    protected IIcon side;
    protected IIcon[] sideChange;
    protected IIcon[] topChange;
    protected String name;

    public BlockModLog(String name) {
        super(Material.wood);
        this.name = name;
        this.setBlockName(name);
        this.setBlockTextureName("divinerpg:" + name);
        this.setHardness(3.0f);
        this.setResistance(5.0f);
        this.setCreativeTab(DivineRPGTabs.blocks);
        this.setStepSound(soundTypeWood);
        GameRegistry.registerBlock((Block)this, (String)name);
        LangRegistry.addBlock((Block)this);
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister icon) {
        this.side = icon.registerIcon(this.getTextureName() + "_side");
        this.top = icon.registerIcon(this.getTextureName() + "_top");
    }

    public boolean isWood(IBlockAccess world, int x, int y, int z) {
        return true;
    }

    public boolean canSustainLeaves(IBlockAccess world, int x, int y, int z) {
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    protected IIcon getTopIcon(int side) {
        return this.sideChange[side % this.sideChange.length];
    }

    @SideOnly(value=Side.CLIENT)
    protected IIcon getSideIcon(int side) {
        return this.topChange[side % this.topChange.length];
    }

    public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
        int var10 = meta & 3;
        int var11 = 0;
        switch (side) {
            case 0: 
            case 1: {
                var11 = 0;
                break;
            }
            case 2: 
            case 3: {
                var11 = 8;
                break;
            }
            case 4: 
            case 5: {
                var11 = 4;
            }
        }
        return var10 | var11;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        int var3 = meta & 0xC;
        return var3 == 0 && (side == 1 || side == 0) ? this.top : (var3 == 4 && (side == 5 || side == 4) ? this.top : (var3 == 8 && (side == 2 || side == 3) ? this.top : this.side));
    }
}

