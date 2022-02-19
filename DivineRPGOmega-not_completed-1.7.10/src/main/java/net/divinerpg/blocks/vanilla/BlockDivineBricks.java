/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 */
package net.divinerpg.blocks.vanilla;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.divinerpg.blocks.vanilla.IDivineMetaBlock;
import net.divinerpg.blocks.vanilla.VanillaBlock;
import net.divinerpg.utils.LangRegistry;
import net.divinerpg.utils.material.EnumBlockType;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class BlockDivineBricks
extends VanillaBlock
implements IDivineMetaBlock {
    private static IIcon[] iconArray = new IIcon[15];
    public String[] names = new String[]{"aquatonic", "arlemite", "darkstone", "diamond", "gold", "green", "iron", "lapisLazuli", "lava", "netherite", "pink", "purple", "realmite", "redstone", "milkStone"};

    public BlockDivineBricks() {
        super(EnumBlockType.ROCK, "Bricks", 3.0f, 2);
        this.setResistance(30.0f);
    }

    public int damageDropped(int meta) {
        return meta;
    }

    @SideOnly(value=Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < 15; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister ir) {
        for (int i = 0; i < 15; ++i) {
            BlockDivineBricks.iconArray[i] = ir.registerIcon("divinerpg:" + this.names[i] + "Bricks");
        }
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return iconArray[meta];
    }

    @Override
    public void addNames() {
        for (int i = 0; i < 15; ++i) {
            LangRegistry.instance.localizeName("tile", "tile." + this.names[i] + "Bricks");
        }
    }

    @Override
    public String getSuffix() {
        return "Bricks";
    }

    @Override
    public String[] getNames() {
        return this.names;
    }
}

