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

public class BlockLamp2
extends VanillaBlock
implements IDivineMetaBlock {
    private static IIcon[] iconArray = new IIcon[12];
    public static String[] names = new String[]{"ice", "jungle", "kraken", "lapisLazuli", "lava", "milky", "molten", "netherite", "realmite", "redstoneOre", "rupee", "terran"};

    public BlockLamp2() {
        super(EnumBlockType.GLASS, "lamp2", 3.0f, 3);
        this.setResistance(30.0f);
        this.setLightLevel(1.0f);
    }

    public int damageDropped(int meta) {
        return meta;
    }

    @SideOnly(value=Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < 12; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister ir) {
        for (int i = 0; i < 12; ++i) {
            BlockLamp2.iconArray[i] = ir.registerIcon("divinerpg:" + names[i] + "Lamp");
        }
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return iconArray[meta];
    }

    @Override
    public void addNames() {
        for (int i = 0; i < 12; ++i) {
            LangRegistry.instance.localizeName("tile", "tile." + names[i] + "Lamp");
        }
    }

    @Override
    public String getSuffix() {
        return "Lamp";
    }

    @Override
    public String[] getNames() {
        return names;
    }
}

