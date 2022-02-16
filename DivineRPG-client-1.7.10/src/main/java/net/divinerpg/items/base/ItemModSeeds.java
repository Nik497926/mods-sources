/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemSeeds
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package net.divinerpg.items.base;

import cpw.mods.fml.common.registry.GameRegistry;
import net.divinerpg.blocks.base.BlockModCrop;
import net.divinerpg.utils.LangRegistry;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemModSeeds
extends ItemSeeds {
    public BlockModCrop crop;

    public ItemModSeeds(String name, BlockModCrop block) {
        super((Block)block, Blocks.farmland);
        this.crop = block;
        this.setUnlocalizedName(name);
        this.setTextureName("divinerpg:" + name);
        this.setCreativeTab(DivineRPGTabs.food);
        GameRegistry.registerItem((Item)this, (String)name);
        LangRegistry.addItem((Item)this);
    }

    public ItemModSeeds(String name, BlockModCrop block, Block placeOn) {
        super((Block)block, placeOn);
        this.crop = block;
        this.setUnlocalizedName(name);
        this.setTextureName("divinerpg:" + name);
        this.setCreativeTab(DivineRPGTabs.food);
        GameRegistry.registerItem((Item)this, (String)name);
        LangRegistry.addItem((Item)this);
    }

    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        if (side != 1) {
            return false;
        }
        if (player.canPlayerEdit(x, y, z, side, stack) && player.canPlayerEdit(x, y + 1, z, side, stack)) {
            if (this.crop.canPlaceBlockOn(world.getBlock(x, y, z)) && this.crop.canPlaceBlockAt(world, x, y, z) && world.isAirBlock(x, y + 1, z)) {
                world.setBlock(x, y + 1, z, (Block)this.crop);
                --stack.stackSize;
                return true;
            }
            return false;
        }
        return false;
    }
}

