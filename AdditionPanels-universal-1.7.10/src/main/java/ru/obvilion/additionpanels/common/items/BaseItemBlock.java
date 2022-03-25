/*
 * Decompiled with CFR 0.150.
 *
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 */
package ru.obvilion.additionpanels.common.items;

import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import ru.obvilion.additionpanels.common.block.BlockQGenerator;
import ru.obvilion.additionpanels.common.block.BlockSolarPanel;
import ru.obvilion.additionpanels.common.tileentity.TileEntitySolarPanel;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class BaseItemBlock extends ItemBlock{
    public BaseItemBlock(Block block) {
        super(block);
    }



    public String getUnlocalizedName(ItemStack itemstack) {
        return this.field_150939_a.getUnlocalizedName();
    }


    @Override
    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List tooltip, boolean p_77624_4_) {
        if (field_150939_a instanceof BlockSolarPanel) {
            TileEntitySolarPanel.PanelData panelData = ((BlockSolarPanel) field_150939_a).getData();
            int genDay = panelData.getGenDay();
            int genNight = panelData.getGenNight();
            tooltip.add(I18n.format("tooltip."+panelData.getPanelName()+".day" ,NumberFormat.getNumberInstance(Locale.UK).format(genDay).replace(","," ")) );
            tooltip.add(I18n.format("tooltip."+panelData.getPanelName()+".night" ,NumberFormat.getNumberInstance(Locale.UK).format(genNight).replace(","," ")) );
        }
        if (field_150939_a instanceof BlockQGenerator) {
            int maxGeneration = ((BlockQGenerator) field_150939_a).getMaxGeneration();
            tooltip.add(I18n.format("tooltip."+((BlockQGenerator) field_150939_a).getName() ,NumberFormat.getNumberInstance(Locale.UK).format(maxGeneration).replace(","," ")) );
        }
    }
}

