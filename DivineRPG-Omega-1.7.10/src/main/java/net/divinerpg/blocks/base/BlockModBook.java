/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.util.IIcon
 */
package net.divinerpg.blocks.base;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.divinerpg.blocks.base.BlockMod;
import net.divinerpg.utils.blocks.IceikaBlocks;
import net.divinerpg.utils.material.EnumBlockType;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

public class BlockModBook
extends BlockMod {
    protected String name;

    public BlockModBook(EnumBlockType type, String name) {
        super(type, name, DivineRPGTabs.blocks);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return side != 1 && side != 0 ? super.getIcon(side, meta) : IceikaBlocks.coalstone.getBlockTextureFromSide(side);
    }

    public int quantityDropped(Random rand) {
        return 3;
    }

    @Override
    public Item getItemDropped(int par1, Random rand, int par3) {
        return Items.book;
    }
}

