/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block;

import com.meteor.extrabotany.ExtraBotany;
import com.meteor.extrabotany.common.block.BlockSpecialFlower;
import java.util.Arrays;
import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;

public class BlockSpecial
extends BlockSpecialFlower {
    @Override
    public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        for (Object s : ExtraBotany.subtilesForCreativeMenu) {
            par3List.add(ItemBlockSpecialFlower.ofType((String) s));
        }
    }

    static {
        ExtraBotany.subtilesForCreativeMenu.addAll(Arrays.asList("candyflower", "blueenchantress", "sunshinelily", "moonlightlily", "omniviolet", "geminiorchid", "stonesia", "pyschobloom", "bellflower", "numerondandelife", "necrofleur", "woodienia", "volatilily", "icebirdium", "numeronbalsam", "judasvow", "artifaconia", "voiduim", "diplopbamboo", "infernoidisy", "launchish", "annoyobloom", "manalinkuim", "judasvowsakura"));
    }
}

