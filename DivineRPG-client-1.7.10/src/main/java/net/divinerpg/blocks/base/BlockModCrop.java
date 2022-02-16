/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockBush
 *  net.minecraft.block.IGrowable
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package net.divinerpg.blocks.base;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Random;
import net.divinerpg.utils.LangRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public abstract class BlockModCrop
extends BlockBush
implements IGrowable {
    @SideOnly(value=Side.CLIENT)
    private IIcon[] icons;
    private int amountOfStages;
    private String cropName;

    public BlockModCrop(String name, int stages, String crop) {
        this.setBlockName(name);
        this.setTickRandomly(true);
        float f = 0.5f;
        this.setBlockBounds(0.5f - f, 0.0f, 0.5f - f, 0.5f + f, 0.25f, 0.5f + f);
        this.setCreativeTab(null);
        this.setHardness(0.0f);
        this.setStepSound(soundTypeGrass);
        this.disableStats();
        GameRegistry.registerBlock((Block)this, (String)name);
        LangRegistry.addBlock((Block)this);
        this.amountOfStages = stages;
        this.cropName = crop;
    }

    public boolean canPlaceBlockOn(Block block) {
        return block == Blocks.farmland;
    }

    public boolean canBlockStay(World w, int x, int y, int z) {
        return this.canPlaceBlockOn(w.getBlock(x, y - 1, z));
    }

    public void updateTick(World w, int x, int y, int z, Random r) {
        float f;
        int l;
        super.updateTick(w, x, y, z, r);
        if (w.getBlockLightValue(x, y + 1, z) >= 9 && (l = w.getBlockMetadata(x, y, z)) < this.amountOfStages && r.nextInt((int)(25.0f / (f = this.grow(w, x, y, z))) + 1) == 0) {
            w.setBlockMetadataWithNotify(x, y, z, ++l, 2);
        }
    }

    public void boneMeal(World w, int x, int y, int z) {
        int meta = w.getBlockMetadata(x, y, z) + MathHelper.getRandomIntegerInRange((Random)w.rand, (int)2, (int)5);
        if (meta > this.amountOfStages) {
            meta = this.amountOfStages;
        }
        w.setBlockMetadataWithNotify(x, y, z, meta, 2);
    }

    private float grow(World w, int x, int y, int z) {
        float f = 1.0f;
        Block block = w.getBlock(x, y, z - 1);
        Block block1 = w.getBlock(x, y, z + 1);
        Block block2 = w.getBlock(x - 1, y, z);
        Block block3 = w.getBlock(x + 1, y, z);
        Block block4 = w.getBlock(x - 1, y, z - 1);
        Block block5 = w.getBlock(x + 1, y, z - 1);
        Block block6 = w.getBlock(x + 1, y, z + 1);
        Block block7 = w.getBlock(x - 1, y, z + 1);
        boolean flag = block2 == this || block3 == this;
        boolean flag1 = block == this || block1 == this;
        boolean flag2 = block4 == this || block5 == this || block6 == this || block7 == this;
        for (int l = x - 1; l <= x + 1; ++l) {
            for (int i1 = z - 1; i1 <= z + 1; ++i1) {
                float f1 = 0.0f;
                if (this.canPlaceBlockOn(w.getBlock(l, y - 1, i1))) {
                    f1 = 1.0f;
                    if (w.getBlock(l, y - 1, i1).isFertile(w, l, y - 1, i1) || w.getBlock(l, y - 1, i1) != Blocks.farmland) {
                        f1 = 3.0f;
                    }
                }
                if (l != x || i1 != z) {
                    f1 /= 4.0f;
                }
                f += f1;
            }
        }
        if (flag2 || flag && flag1) {
            f /= 2.0f;
        }
        return f;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int meta) {
        if (meta < 0 || meta > this.amountOfStages) {
            meta = this.amountOfStages;
        }
        return this.icons[meta];
    }

    public boolean func_149851_a(World w, int x, int y, int z, boolean b) {
        return w.getBlockMetadata(x, y, z) != this.amountOfStages;
    }

    public boolean func_149852_a(World w, Random r, int x, int y, int z) {
        return true;
    }

    public void dropBlockAsItemWithChance(World w, int x, int y, int z, int m, float f, int i) {
        super.dropBlockAsItemWithChance(w, x, y, z, m, f, 0);
    }

    public Item getItemDropped(int meta, Random r, int i) {
        return meta == this.amountOfStages ? this.getDropItem() : this.getSeeds();
    }

    public boolean canPlaceBlockAt(World w, int x, int y, int z) {
        return true;
    }

    public int quantityDropped(Random r) {
        return r.nextInt(2) + 1;
    }

    public Item getDropItem() {
        return null;
    }

    public Item getSeeds() {
        return null;
    }

    @SideOnly(value=Side.CLIENT)
    public Item getItem(World w, int x, int y, int z) {
        return this.getSeeds();
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister icon) {
        this.icons = new IIcon[this.amountOfStages + 1];
        for (int i = 0; i < this.icons.length; ++i) {
            this.icons[i] = icon.registerIcon("divinerpg:" + this.cropName + "_" + i);
        }
    }

    public void func_149853_b(World w, Random r, int x, int y, int z) {
        this.boneMeal(w, x, y, z);
    }

    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList ret = super.getDrops(world, x, y, z, metadata, fortune);
        if (metadata >= this.amountOfStages) {
            for (int i = 0; i < 3 + fortune; ++i) {
                if (world.rand.nextInt(15) > metadata) continue;
                ret.add(new ItemStack(this.getSeeds(), 1, 0));
            }
        }
        return ret;
    }
}

