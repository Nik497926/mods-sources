/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package net.divinerpg.blocks.vanilla;

import java.util.ArrayList;
import java.util.Random;
import net.divinerpg.blocks.vanilla.VanillaBlock;
import net.divinerpg.utils.material.EnumBlockType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockOreDrop
extends VanillaBlock {
    private Item drop;
    private int count;

    public BlockOreDrop(EnumBlockType type, String name, float hardness, int harvest, Item drop, int count) {
        super(type, name, hardness, harvest);
        this.drop = drop;
        this.count = count;
        this.setResistance(2000.0f);
    }

    public BlockOreDrop(EnumBlockType type, String name, float hardness, int harvest, Item drop) {
        super(type, name, hardness, harvest);
        this.drop = drop;
        this.count = 0;
        this.setResistance(2000.0f);
    }

    @Override
    public Item getItemDropped(int i, Random rand, int i2) {
        return this.drop;
    }

    public int quantityDropped(Random rand) {
        if (this.count != 0) {
            return rand.nextInt(this.count) + 1;
        }
        return 1;
    }

    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int meta, int fortune) {
        ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
        drops.add(new ItemStack(this.getItemDropped(0, this.rand, fortune), this.quantityDropped(this.rand)));
        return drops;
    }
}

