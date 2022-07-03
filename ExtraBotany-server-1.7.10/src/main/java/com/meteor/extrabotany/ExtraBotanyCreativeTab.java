/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany;

import com.meteor.extrabotany.common.block.ModBlocks;
import com.meteor.extrabotany.common.item.ModItems;
import com.meteor.extrabotany.common.item.equipment.awake.ItemAwakePick;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import vazkii.botania.common.core.helper.ItemNBTHelper;

public class ExtraBotanyCreativeTab
extends CreativeTabs {
    List list;

    public ExtraBotanyCreativeTab() {
        super("ExtraBotania");
    }

    public ItemStack getIconItemStack() {
        return new ItemStack(ModItems.boxs);
    }

    public Item getTabIconItem() {
        return this.getIconItemStack().getItem();
    }

    public boolean hasSearchBar() {
        return true;
    }

    public void displayAllReleventItems(List list) {
        this.list = list;
        this.addItem(ModItems.SGBee);
        this.addItem(ModItems.SGGaia);
        this.addItem(ModItems.SGEnhanced);
        this.addItem(ModItems.SGMini);
        this.addItem(ModItems.SGQuick);
        this.addBlock(ModBlocks.specialFlower);
        this.addItem(ModItems.manapotato);
        this.addItem(ModItems.gaiatablet);
        this.addItem(ModItems.teleportpearl);
        this.addItem(ModItems.angelwand);
        this.addItem(ModItems.reader);
        this.addItem(ModItems.dice6);
        this.addItem(ModItems.maxwelldemon);
        this.addItem(ModItems.hermeswand);
        this.addItem(ModItems.eternalslience);
        this.addItem(ModItems.valkyriecombatuniform);
        this.addItem(ModItems.oghelm);
        this.addItem(ModItems.ogchest);
        this.addItem(ModItems.oglegs);
        this.addItem(ModItems.ogboots);
        this.addItem(ModItems.itemtest);
        this.addItem(ModItems.material);
        this.addItem(ModItems.key);
        this.addItem(ModItems.boxs);
        this.addItem(ModItems.dungeonbox);
        this.addItem(ModItems.lycorisgreen);
        this.addItem(ModItems.lycorispurple);
        this.addItem(ModItems.lycorisred);
        this.addItem(ModItems.lycorisrandom);
        this.addBlock(ModBlocks.gaiaquartz);
        this.addBlock(ModBlocks.gaiaquartzstairs);
        this.addBlock(ModBlocks.gaiaquartzslab);
        this.addBlock(ModBlocks.elvenquartz);
        this.addBlock(ModBlocks.elvenquartzstairs);
        this.addBlock(ModBlocks.elvenquartzslab);
        this.addBlock(ModBlocks.gaiachest);
        this.addItem(ModItems.emptysoulsteel);
        this.addItem(ModItems.castsoulsteel);
        this.addItem(ModItems.dog);
        this.addItem(ModItems.gaiawise);
        this.addItem(ModItems.scissorpurple);
        this.addItem(ModItems.scissorred);
        this.addItem(ModItems.heliacalclaymore);
        this.addItem(ModItems.dagger);
        this.addItem(ModItems.recordA);
        this.addItem(ModItems.recordB);
        this.addItem(ModItems.recordC);
        for (int i = 0; i < ItemAwakePick.LEVELS.length; ++i) {
            ItemStack v0 = new ItemStack(ModItems.awakepick);
            ItemNBTHelper.setInt((ItemStack)v0, (String)"mana", (int)ItemAwakePick.LEVELS[i]);
            this.addStack(v0);
        }
        this.addItem(ModItems.itemtest);
        this.addBlock(ModBlocks.auracontroler);
        this.addBlock(ModBlocks.elfpool);
        this.addBlock(ModBlocks.manatrade);
        this.addBlock(ModBlocks.awakeelfupdater);
        this.addBlock(ModBlocks.blocksoulsteel);
        this.addBlock(ModBlocks.compressedTerra);
        this.addBlock(ModBlocks.blockelfirium);
        this.addItem(ModItems.elfirium);
        this.addItem(ModItems.awakearmcontrol);
        this.addItem(ModItems.castsoulsteel);
        this.addItem(ModItems.emptysoulsteel);
        this.addItem(ModItems.awakeoghelm);
        this.addItem(ModItems.awakeogchest);
        this.addItem(ModItems.awakeoglegs);
        this.addItem(ModItems.awakeogboots);
        ItemStack v0 = new ItemStack(ModItems.awakeoghelm);
        ItemNBTHelper.setInt((ItemStack)v0, (String)"level", (int)8);
        this.addStack(v0);
        v0 = new ItemStack(ModItems.awakeogchest);
        ItemNBTHelper.setInt((ItemStack)v0, (String)"level", (int)8);
        this.addStack(v0);
        v0 = new ItemStack(ModItems.awakeoglegs);
        ItemNBTHelper.setInt((ItemStack)v0, (String)"level", (int)8);
        this.addStack(v0);
        v0 = new ItemStack(ModItems.awakeogboots);
        ItemNBTHelper.setInt((ItemStack)v0, (String)"level", (int)8);
        this.addStack(v0);
    }

    private void addItem(Item item) {
        item.getSubItems(item, (CreativeTabs)this, this.list);
    }

    private void addBlock(Block block) {
        ItemStack stack = new ItemStack(block);
        block.getSubBlocks(stack.getItem(), (CreativeTabs)this, this.list);
    }

    private void addStack(ItemStack stack) {
        this.list.add(stack);
    }
}

