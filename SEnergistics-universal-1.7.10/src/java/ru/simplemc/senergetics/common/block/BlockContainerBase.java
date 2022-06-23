/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.block;

import cpw.mods.fml.common.registry.GameRegistry;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.simplemc.senergetics.SEnergetics;

public class BlockContainerBase
extends BlockContainer {
    public BlockContainerBase(Material material, String name, Class<? extends ItemBlock> itemClass) {
        super(material);
        this.setBlockName(name);
        this.setBlockTextureName("senergetics:" + name);
        this.setCreativeTab(SEnergetics.CREATIVE_TAB);
        if (itemClass == null) {
            this.registerBlock(name);
        } else {
            this.registerBlock(name, itemClass);
        }
    }

    public Item getItemFromBlock() {
        return Item.getItemFromBlock((Block)this);
    }

    public void registerBlock(String blockRegisterName, Class<? extends ItemBlock> itemClass) {
        GameRegistry.registerBlock((Block)this, itemClass, (String)blockRegisterName);
    }

    public void registerBlock(String blockRegisterName) {
        GameRegistry.registerBlock((Block)this, (String)blockRegisterName);
    }

    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return Item.getItemFromBlock((Block)this);
    }

    public TileEntity createNewTileEntity(World world, int meta) {
        return null;
    }
}

