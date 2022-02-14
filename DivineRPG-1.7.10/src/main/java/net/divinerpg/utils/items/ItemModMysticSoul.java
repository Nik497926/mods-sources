/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.realmsclient.gui.ChatFormatting
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.resources.I18n
 */
package net.divinerpg.utils.items;

import com.mojang.realmsclient.gui.ChatFormatting;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.divinerpg.items.base.ItemMod;
import net.minecraft.client.resources.I18n;

public class ItemModMysticSoul
extends ItemMod {
    public ItemModMysticSoul(String name) {
        super(name);
    }

    @SideOnly(value=Side.CLIENT)
    public void addInformation(List list) {
        list.add(ChatFormatting.GOLD + I18n.format((String)"items.mysticsoul1", (Object[])new Object[0]));
        list.add(ChatFormatting.GOLD + I18n.format((String)"items.mysticsoul2", (Object[])new Object[0]));
    }
}

