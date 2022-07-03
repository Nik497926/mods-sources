/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block;

import com.meteor.extrabotany.ExtraBotany;
import com.meteor.extrabotany.common.block.tile.TileFlower;
import com.meteor.extrabotany.common.item.system.ItemBlockWithMetadataAndName;
import cpw.mods.fml.common.registry.GameRegistry;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class BlockFlower
extends BlockContainer {
    public BlockFlower() {
        super(Material.glass);
        this.setBlockName("blockFlower");
        this.setBlockTextureName("blockFlower");
        GameRegistry.registerBlock((Block)this, ItemBlockWithMetadataAndName.class, (String)"blockFlower");
        GameRegistry.registerTileEntity(TileFlower.class, (String)"extraBotania.blockFlower");
        this.setBlockTextureName(ExtraBotany.modid + ":farm");
        this.setHardness(2.0f);
        this.setResistance(5.0f);
    }

    public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        if (w.isRemote) {
            return true;
        }
        player.openGui((Object)ExtraBotany.instance, 0, w, x, y, z);
        return true;
    }

    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileFlower();
    }

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase ent, ItemStack stack) {
        super.onBlockPlacedBy(world, x, y, z, ent, stack);
        int type = stack.getItemDamage();
        TileEntity te = world.getTileEntity(x, y, z);
        if (te instanceof TileFlower && type < BlockFlowerType.values().length) {
            ((TileFlower)te).setType(BlockFlowerType.values()[type]);
            if (stack.hasTagCompound() && stack.getTagCompound().hasKey("dataBlock")) {
                ((TileFlower)te).readFromCustomNBT(stack.getTagCompound().getCompoundTag("dataBlock"));
            }
        }
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z, EntityPlayer player) {
        TileEntity te = world.getTileEntity(x, y, z);
        if (te instanceof TileFlower) {
            return this.writeData((TileFlower)te);
        }
        return new ItemStack((Block)this, 1, 0);
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        TileEntity te = world.getTileEntity(x, y, z);
        if (te instanceof TileFlower) {
            return this.writeData((TileFlower)te);
        }
        return new ItemStack((Block)this, 1, 0);
    }

    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        TileEntity te = world.getTileEntity(x, y, z);
        if (te instanceof TileFlower) {
            ret.add(this.writeData((TileFlower)te));
        }
        return ret;
    }

    private ItemStack writeData(TileFlower te) {
        ItemStack stack = new ItemStack((Block)this, 1, te.getWorldObj().getBlockMetadata(te.xCoord, te.yCoord, te.zCoord));
        NBTTagCompound nbt = new NBTTagCompound();
        NBTTagCompound data = new NBTTagCompound();
        te.writeCustomNBT(data);
        nbt.setTag("dataBlock", (NBTBase)data);
        stack.setTagCompound(nbt);
        return stack;
    }

    public void getSubBlocks(Item item, CreativeTabs tabs, List list) {
        for (int i = 0; i < BlockFlowerType.values().length; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    protected void dropBlockAsItem(World world, int x, int y, int z, ItemStack p_149642_5_) {
        super.dropBlockAsItem(world, x, y, z, p_149642_5_);
    }

    public static enum BlockFlowerType {
        TIER1(9, 1, 3, 5, 3, 5),
        TIER2(25, 1, 2, 6, 2, 6),
        TIER3(49, 2, 1, 7, 1, 7),
        TIER4(81, 4, 0, 8, 0, 8);

        public final int countSlot;
        public final int maxStackSize;
        public final int stLine;
        public final int maxLine;
        public final int stIndex;
        public final int maxIndex;

        private BlockFlowerType(int countSlot, int maxStackSize, int stL, int maxL, int stI, int maxI) {
            this.countSlot = countSlot;
            this.maxStackSize = maxStackSize;
            this.stLine = stL;
            this.maxLine = maxL;
            this.stIndex = stI;
            this.maxIndex = maxI;
        }

        public String getName() {
            return this.name().toLowerCase().replaceAll("\\d+", "");
        }
    }
}

