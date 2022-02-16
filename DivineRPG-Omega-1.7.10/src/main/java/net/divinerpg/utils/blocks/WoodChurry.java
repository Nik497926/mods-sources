/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.BlockLog
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.util.IIcon
 */
package net.divinerpg.utils.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.block.BlockLog;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class WoodChurry
extends BlockLog {
    @SideOnly(value=Side.CLIENT)
    private IIcon top;
    @SideOnly(value=Side.CLIENT)
    private IIcon bottom;
    @SideOnly(value=Side.CLIENT)
    private IIcon side;

    public WoodChurry(Material p_i45394_1_) {
        this.setCreativeTab(DivineRPGTabs.blocks);
        this.setHardness(2.0f);
        this.setResistance(0.5f);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int par1, int par2) {
        int var3 = par2 & 0xC;
        return var3 == 0 && (par1 == 1 || par1 == 0) ? this.top : (var3 == 4 && (par1 == 5 || par1 == 4) ? this.top : (var3 == 8 && (par1 == 2 || par1 == 3) ? this.top : this.side));
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister icon) {
        this.bottom = this.top = icon.registerIcon("divinerpg:woodChurryTop");
        this.side = icon.registerIcon("divinerpg:woodChurry");
        this.blockIcon = icon.registerIcon("divinerpg:woodChurry");
    }
}

