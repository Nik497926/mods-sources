/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package net.divinerpg.blocks.arcana;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.divinerpg.blocks.base.BlockMod;
import net.divinerpg.utils.material.EnumBlockType;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockAcceleron
extends BlockMod {
    private IIcon side;
    private IIcon top_n;
    private IIcon top_s;
    private IIcon top_e;
    private IIcon top_w;

    public BlockAcceleron() {
        super(EnumBlockType.ROCK, "acceleron", 3.0f, DivineRPGTabs.utility);
        this.slipperiness = 1.2f;
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister icon) {
        this.side = icon.registerIcon("divinerpg:acceleron_side");
        this.top_n = icon.registerIcon("divinerpg:acceleron_north");
        this.top_s = icon.registerIcon("divinerpg:acceleron_south");
        this.top_e = icon.registerIcon("divinerpg:acceleron_east");
        this.top_w = icon.registerIcon("divinerpg:acceleron_west");
    }

    public IIcon getIcon(int side, int meta) {
        if (side <= 1) {
            return meta == 2 ? this.top_n : (meta == 3 ? this.top_s : (meta == 4 ? this.top_w : this.top_e));
        }
        return this.side;
    }

    public void onBlockPlacedBy(World w, int x, int y, int z, EntityLivingBase living, ItemStack item) {
        int meta = MathHelper.floor_double((double)((double)(living.rotationYaw * 4.0f / 360.0f) + 0.5)) & 3;
        if (meta == 0) {
            w.setBlockMetadataWithNotify(x, y, z, 2, 2);
        }
        if (meta == 1) {
            w.setBlockMetadataWithNotify(x, y, z, 5, 2);
        }
        if (meta == 2) {
            w.setBlockMetadataWithNotify(x, y, z, 3, 2);
        }
        if (meta == 3) {
            w.setBlockMetadataWithNotify(x, y, z, 4, 2);
        }
    }
}

