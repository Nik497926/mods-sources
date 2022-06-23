/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod.manager;

import cpw.mods.fml.common.registry.GameRegistry;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import ru.simplemc.simplecore.mod.SimpleCore;
import ru.simplemc.simplecore.mod.SimpleCoreRegistry;

public class RegistrationManager {
    private final CreativeTabs creativeTab = new CreativeTabs("simplecore"){

        public Item getTabIconItem() {
            return SimpleCoreRegistry.ITEM_BACKPACK;
        }
    };
    private List<Item> itemsToRegister = new ArrayList<Item>();
    private List<Block> blocksToRegister = new ArrayList<Block>();

    public Item registerItem(Item item) {
        item.setCreativeTab(this.creativeTab);
        this.itemsToRegister.add(item);
        SimpleCore.LOGGER.info("Register item: " + item.getUnlocalizedName());
        return item;
    }

    public ItemBlock registerBlock(Block block) {
        this.blocksToRegister.add(block);
        block.setCreativeTab(this.creativeTab);
        return new ItemBlock(block);
    }

    public void onInit() {
        this.itemsToRegister.forEach(item -> GameRegistry.registerItem((Item)item, (String)item.getUnlocalizedName(), (String)"simplecore"));
        this.blocksToRegister.forEach(block -> GameRegistry.registerBlock((Block)block, (String)block.getUnlocalizedName()));
    }

    public void onPostInit() {
        this.itemsToRegister.clear();
        this.blocksToRegister.clear();
        this.itemsToRegister = null;
        this.blocksToRegister = null;
    }
}

