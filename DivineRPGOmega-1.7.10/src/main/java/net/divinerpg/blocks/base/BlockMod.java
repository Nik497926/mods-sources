/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.IBlockAccess
 */
package net.divinerpg.blocks.base;

import cpw.mods.fml.common.registry.GameRegistry;
import java.util.Random;
import net.divinerpg.blocks.vanilla.DivineMetaItemBlock;
import net.divinerpg.blocks.vanilla.IDivineMetaBlock;
import net.divinerpg.utils.LangRegistry;
import net.divinerpg.utils.Util;
import net.divinerpg.utils.material.EnumBlockType;
import net.divinerpg.utils.material.EnumToolType;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;

public class BlockMod
extends Block {
    protected String name;
    protected EnumBlockType blockType;
    protected Item drop;
    protected Random rand;
    protected boolean exp = false;
    protected boolean twilightOre = false;
    protected boolean rareOre = false;

    public BlockMod(String name, float hardness) {
        this(EnumBlockType.ROCK, name, hardness, DivineRPGTabs.blocks);
    }

    public BlockMod(String name, float hardness, DivineRPGTabs tab) {
        this(EnumBlockType.ROCK, name, hardness, tab);
    }

    public BlockMod(String name, boolean breakable) {
        this(EnumBlockType.ROCK, name, breakable);
    }

    public BlockMod(EnumBlockType blockType, String name, float hardness) {
        this(blockType, name, hardness, DivineRPGTabs.blocks);
        if (blockType == EnumBlockType.DIRT) {
            this.setHarvestLevel("shovel", 3);
        }
    }

    public BlockMod(String name, boolean breakable, DivineRPGTabs tab) {
        this(EnumBlockType.ROCK, name, breakable, tab);
    }

    public BlockMod(EnumBlockType blockType, String name, boolean breakable) {
        this(blockType, name, breakable, DivineRPGTabs.blocks);
    }

    public BlockMod(EnumBlockType blockType, String name, boolean breakable, DivineRPGTabs tab) {
        this(blockType, name, tab);
        if (!breakable) {
            this.setBlockUnbreakable();
            this.setResistance(6000000.0f);
        }
    }

    public BlockMod(EnumBlockType blockType, String name, DivineRPGTabs tab) {
        super(blockType.getMaterial());
        this.blockType = blockType;
        this.name = name;
        this.rand = new Random();
        this.setTextureName("divinerpg:" + name);
        this.setStepSound(blockType.getSound());
        this.setCreativeTab(tab);
        this.setTextureName(this.textureName);
        this.setBlockName(name);
        if (!(this instanceof IDivineMetaBlock)) {
            GameRegistry.registerBlock((Block)this, (String)name);
        } else {
            GameRegistry.registerBlock((Block)this, DivineMetaItemBlock.class, (String)name);
        }
        LangRegistry.addBlock(this);
    }

    public BlockMod(EnumBlockType blockType, String name, float hardness, DivineRPGTabs tab) {
        super(blockType.getMaterial());
        this.blockType = blockType;
        this.name = name;
        this.rand = new Random();
        this.setTextureName("divinerpg:" + name);
        this.setStepSound(blockType.getSound());
        this.setCreativeTab(tab);
        this.setTextureName(this.textureName);
        this.setBlockName(name);
        this.setHardness(hardness);
        if (hardness == -1.0f) {
            this.setBlockUnbreakable();
            this.setResistance(6000000.0f);
        }
        if (!(this instanceof IDivineMetaBlock)) {
            GameRegistry.registerBlock((Block)this, (String)name);
        } else {
            GameRegistry.registerBlock((Block)this, DivineMetaItemBlock.class, (String)name);
        }
        LangRegistry.addBlock(this);
    }

    public BlockMod(EnumBlockType blockType, String name, float hardness, DivineRPGTabs tab, Class<? extends ItemBlock> item) {
        super(blockType.getMaterial());
        this.blockType = blockType;
        this.name = name;
        this.rand = new Random();
        this.setTextureName("divinerpg:" + name);
        this.setStepSound(blockType.getSound());
        this.setCreativeTab(tab);
        this.setTextureName(this.textureName);
        this.setBlockName(name);
        this.setHardness(hardness);
        if (hardness == -1.0f) {
            this.setBlockUnbreakable();
            this.setResistance(6000000.0f);
        }
        GameRegistry.registerBlock((Block)this, item, (String)name);
        LangRegistry.addBlock(this);
    }

    public Item getItemDropped(int par1, Random par2, int par3) {
        if (this.drop == null) {
            return Util.toItem(this);
        }
        return this.drop;
    }

    public BlockMod toggleExp(boolean exp) {
        this.exp = exp;
        return this;
    }

    public BlockMod setTwilightOre(boolean twilightOre) {
        this.twilightOre = twilightOre;
        return this;
    }

    public BlockMod setRareOre(boolean r) {
        this.rareOre = r;
        return this;
    }

    public boolean isTwilightOre() {
        return this.twilightOre;
    }

    public BlockMod setDropItem(Item drop) {
        this.drop = drop;
        return this;
    }

    public BlockMod setHarvestLevel(EnumToolType type) {
        this.setHarvestLevel(type.getType(), type.getLevel());
        return this;
    }

    public BlockMod setTextureName(String textureName) {
        this.textureName = textureName;
        return this;
    }

    public int getExpDrop(IBlockAccess block, int par5, int par7) {
        if (this.getItemDropped(par5, this.rand, par7) != Util.toItem(this)) {
            int j1 = 0;
            if (this.exp) {
                j1 = MathHelper.getRandomIntegerInRange((Random)this.rand, (int)0, (int)4);
            }
            return j1;
        }
        return 0;
    }

    public int quantityDropped(int meta, int fortune, Random random) {
        if ((this.twilightOre || this.rareOre) && fortune > 0) {
            int j = random.nextInt(fortune + 2) - 1;
            if (j < 0) {
                j = 0;
            }
            return j + 1;
        }
        return 1;
    }
}

