/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.client.gui.tabs;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import ru.simplemc.senergetics.SEnergeticsRegistry;

public class GuiCreativeTabs
extends CreativeTabs {
    public GuiCreativeTabs() {
        super("senergetics");
    }

    @SideOnly(value=Side.CLIENT)
    public Item getTabIconItem() {
        return SEnergeticsRegistry.blockSilentSpawnerStandalone.getItemFromBlock();
    }
}

