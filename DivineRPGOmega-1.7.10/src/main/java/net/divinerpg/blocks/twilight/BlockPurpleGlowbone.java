/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.EnumPlantType
 *  net.minecraftforge.common.IPlantable
 */
package net.divinerpg.blocks.twilight;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Random;
import net.divinerpg.blocks.base.BlockMod;
import net.divinerpg.utils.items.TwilightItemsCrops;
import net.divinerpg.utils.material.EnumBlockType;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

public class BlockPurpleGlowbone
extends BlockMod
implements IPlantable {
    private IIcon[] iconArray = new IIcon[2];

    public BlockPurpleGlowbone() {
        super(EnumBlockType.PLANT, "purpleGlowbonePlant", true);
        float var3 = 0.375f;
        this.setBlockBounds(0.5f - var3, 0.0f, 0.5f - var3, 0.5f + var3, 1.0f, 0.5f + var3);
        this.setTickRandomly(true);
        this.setCreativeTab(null);
    }

    public void onBlockAdded(World par1World, int par2, int par3, int par4) {
    }

    public void updateTick(World world, int i, int j, int k, Random par5Random) {
        if (world.isAirBlock(i, j + 1, k) && world.getBlockMetadata(i, j, k) == 0 && world.getBlock(i, j - 1, k) == Blocks.grass) {
            world.setBlock(i, j + 1, k, (Block)this, 2, 2);
            world.setBlock(i, j, k, (Block)this, 1, 2);
        }
    }

    public boolean canBlockStay(World w, int x, int y, int z) {
        return w.getBlock(x, y - 1, z) == this || w.getBlock(x, y - 1, z) == Blocks.grass;
    }

    public boolean canPlaceBlockAt(World w, int x, int y, int z) {
        return true;
    }

    public void onNeighborBlockChange(World w, int x, int y, int z, Block b) {
        this.checkBlockCoordValid(w, x, y, z);
    }

    protected void checkBlockCoordValid(World world, int i, int j, int k) {
        if (!this.canBlockStay(world, i, j, k)) {
            if (world.getBlockMetadata(i, j, k) == 2) {
                this.dropBlockAsItemWithChance(world, i, j, k, world.getBlockMetadata(i, j, k), 1.0f, 0);
            } else if (world.getBlockMetadata(i, j, k) == 0) {
                this.dropBlockAsItem(world, i, j, k, new ItemStack(TwilightItemsCrops.purpleGlowboneSeeds));
            }
            world.func_147480_a(i, j, k, false);
        } else if (world.getBlockMetadata(i, j, k) != 0 && world.getBlock(i, j + 1, k) != this) {
            world.setBlock(i, j, k, (Block)this, 0, 2);
        }
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World w, int x, int y, int z) {
        return null;
    }

    @Override
    public Item getItemDropped(int meta, Random rand, int fortune) {
        return TwilightItemsCrops.purpleGlowboneSeeds;
    }

    public int quantityDropped(Random rand) {
        return 1 + rand.nextInt(2);
    }

    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int meta, int fortune) {
        ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
        if (meta == 2) {
            drops.add(new ItemStack(TwilightItemsCrops.purpleGlowbone, 1));
            drops.add(new ItemStack(this.getItemDropped(0, this.rand, 0), this.quantityDropped(this.rand)));
        }
        if (meta == 0) {
            drops.add(new ItemStack(this.getItemDropped(0, this.rand, 0), 1));
        }
        return drops;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public int getRenderType() {
        return 1;
    }

    @SideOnly(value=Side.CLIENT)
    public Item getItem(World par1World, int par2, int par3, int par4) {
        return TwilightItemsCrops.purpleGlowboneSeeds;
    }

    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
        return EnumPlantType.Beach;
    }

    public Block getPlant(IBlockAccess world, int x, int y, int z) {
        return this;
    }

    public int getPlantMetadata(IBlockAccess world, int x, int y, int z) {
        return world.getBlockMetadata(x, y, z);
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister i) {
        this.iconArray[0] = i.registerIcon("divinerpg:" + this.name + "_top");
        this.iconArray[1] = i.registerIcon("divinerpg:" + this.name + "_bottom");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return meta == 0 || meta == 2 ? this.iconArray[0] : this.iconArray[1];
    }
}

