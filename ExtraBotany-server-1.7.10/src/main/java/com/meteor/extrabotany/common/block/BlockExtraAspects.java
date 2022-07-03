/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block;

import com.meteor.extrabotany.ExtraBotany;
import com.meteor.extrabotany.common.block.tile.TileExtraAspect;
import cpw.mods.fml.common.registry.GameRegistry;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.item.block.ItemBlockWithMetadataAndName;

public class BlockExtraAspects
extends BlockContainer {
    public BlockExtraAspects() {
        super(Material.glass);
        this.setBlockName("extraAspects");
        this.setStepSound(soundTypeMetal);
        this.setBlockTextureName(ExtraBotany.modid + ":aspects");
        this.setHardness(5.0f);
        this.setResistance(10.0f);
    }

    public Block setBlockName(String par1Str) {
        GameRegistry.registerBlock((Block)this, ItemBlockWithMetadataAndName.class, (String)par1Str);
        GameRegistry.registerTileEntity(TileExtraAspect.class, (String)"extraBotania.extraAspect");
        return super.setBlockName(par1Str);
    }

    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileExtraAspect();
    }

    private ItemStack genStack(World w, int x, int y, int z) {
        ItemStack stack = new ItemStack((Block)this, 1, w.getBlockMetadata(x, y, z));
        TileEntity te = w.getTileEntity(x, y, z);
        if (te instanceof TileExtraAspect) {
            NBTTagCompound n = new NBTTagCompound();
            ((TileExtraAspect)te).writeCustomNBT(n);
            ItemNBTHelper.injectNBT((ItemStack)stack, (NBTTagCompound)n);
        }
        return stack;
    }

    public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        if (w.isRemote) {
            return true;
        }
        player.openGui((Object)ExtraBotany.instance, 0, w, x, y, z);
        return true;
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z, EntityPlayer player) {
        return this.genStack(world, x, y, z);
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        return this.genStack(world, x, y, z);
    }

    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        ret.add(this.genStack(world, x, y, z));
        return ret;
    }

    public void breakBlock(World w, int x, int y, int z, Block block, int mt) {
        TileEntity te = w.getTileEntity(x, y, z);
        if (te instanceof TileExtraAspect) {
            ((TileExtraAspect)te).onBlockBreak();
        }
        super.breakBlock(w, x, y, z, block, mt);
    }

    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < ExtraAspectType.values().length; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    public static enum ExtraAspectType {
        TIER0(3),
        TIER1(5),
        TIER2(7),
        TIER3(11),
        TIER4(15),
        TIER5(25);

        public final int countAsp;

        private ExtraAspectType(int count) {
            this.countAsp = count;
        }
    }
}

