/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockAir
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.IShearable
 */
package net.divinerpg.utils.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import java.util.ArrayList;
import java.util.Random;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

public class BlockGenericPlant
extends Block
implements IShearable {
    private String name;
    private boolean isDown = false;
    private boolean isStonePlant = false;
    private boolean isTallPlant = false;
    private Block childPlant = this;
    private boolean isBidirectional = false;

    public BlockGenericPlant(Material Rck) {
        super(Rck);
        this.setCreativeTab(DivineRPGTabs.blocks);
        this.setHardness(0.4f);
        this.setResistance(0.5f);
        if (Rck != Material.glass) {
            this.setStepSound(Block.soundTypeGrass);
        } else {
            this.setStepSound(Block.soundTypeGlass);
        }
    }

    public int getRenderType() {
        return 1;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean isReplaceable(IBlockAccess world, int x, int y, int z) {
        return false;
    }

    public BlockGenericPlant setDown() {
        this.isDown = true;
        return this;
    }

    public BlockGenericPlant setTallPlant() {
        this.isTallPlant = true;
        return this;
    }

    public BlockGenericPlant isBidirectional() {
        this.isBidirectional = true;
        return this;
    }

    public BlockGenericPlant setStonePlant() {
        this.isStonePlant = true;
        return this;
    }

    public BlockGenericPlant setChildPlant(Block pl) {
        this.childPlant = pl;
        return this;
    }

    public boolean canPlaceBlockAt(World world, int posX, int posY, int posZ) {
        Block bl = this.isDown ? world.getBlock(posX, posY + 1, posZ) : world.getBlock(posX, posY - 1, posZ);
        String harvestTool = bl.getHarvestTool(0);
        if (harvestTool == null) {
            harvestTool = "";
        }
        if (!this.isBidirectional) {
            return (bl == this || bl == this.childPlant) && this.isTallPlant || (this.isStonePlant ? harvestTool.equals("pickaxe") || bl.getMaterial() == Material.rock : harvestTool.equals("shovel") || bl.getMaterial() == Material.ground) && bl.isOpaqueCube();
        }
        if ((bl == this || bl == this.childPlant) && this.isTallPlant || (this.isStonePlant ? harvestTool.equals("pickaxe") || bl.getMaterial() == Material.rock : harvestTool.equals("shovel") || bl.getMaterial() == Material.ground) && bl.isOpaqueCube()) {
            return true;
        }
        bl = world.getBlock(posX, posY + 1, posZ);
        return (bl == this || bl == this.childPlant) && this.isTallPlant || (this.isStonePlant ? harvestTool.equals("pickaxe") || bl.getMaterial() == Material.rock : harvestTool.equals("shovel") || bl.getMaterial() == Material.ground) && bl.isOpaqueCube();
    }

    public void breakBlock(World world, int posX, int posY, int posZ, Block block, int meta) {
        boolean bool;
        super.breakBlock(world, posX, posY, posZ, block, meta);
        boolean up = !this.isDown;
        switch (block.getUnlocalizedName()) {
            case "tile.lelyetianWiggler": 
            case "tile.lelyetianStem": 
            case "tile.shyreStock": {
                int offset = -1;
                Block hatBlock = world.getBlock(posX, posY + offset, posZ);
                while (hatBlock instanceof BlockGenericPlant) {
                    hatBlock = world.getBlock(posX, posY + --offset, posZ);
                }
                if (!(hatBlock instanceof BlockAir)) break;
                up = false;
            }
        }
        if (up) {
            int i = 1;
            bool = world.getBlock(posX, posY + i, posZ) instanceof BlockGenericPlant;
            while (bool) {
                world.setBlock(posX, posY + i, posZ, Blocks.air);
                bool = world.getBlock(posX, posY + ++i, posZ) instanceof BlockGenericPlant;
            }
        } else {
            int i = -1;
            bool = world.getBlock(posX, posY + i, posZ) instanceof BlockGenericPlant;
            while (bool) {
                world.setBlock(posX, posY + i, posZ, Blocks.air);
                bool = world.getBlock(posX, posY + --i, posZ) instanceof BlockGenericPlant;
            }
        }
    }

    public void onNeighborBlockChange(World world, int posX, int posY, int posZ, Block block) {
        int y;
        int n = y = this.isDown ? posY + 1 : posY - 1;
        if (world.getBlock(posX, y, posZ) == Blocks.air) {
            this.breakBlock(world, posX, y, posZ, this, 0);
        }
    }

    private boolean tracePathUp(World w, int x, int y, int z) {
        int offset = -1;
        Block hatBlock = w.getBlock(x, y + offset, z);
        while (hatBlock instanceof BlockGenericPlant) {
            hatBlock = w.getBlock(x, y + --offset, z);
        }
        return !(hatBlock instanceof BlockAir);
    }

    public void onEntityCollidedWithBlock(World p_149670_1_, int p_149670_2_, int p_149670_3_, int p_149670_4_, Entity p_149670_5_) {
    }

    public int quantityDropped(Random p_149745_1_) {
        return 0;
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_) {
        return null;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public Block setTextureName(String name) {
        return this.setBlockTextureName("divinerpg:" + name);
    }

    public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z) {
        return true;
    }

    public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune) {
        this.breakBlock((World)world, x, y, z, this, world.getBlockMetadata(x, y, z));
        ArrayList<ItemStack> stack = new ArrayList<ItemStack>();
        stack.add(new ItemStack(Item.getItemFromBlock((Block)this), 1));
        return stack;
    }

    public Block setName(String name) {
        this.name = name;
        this.setBlockName(this.name);
        this.setTextureName(name);
        GameRegistry.registerBlock((Block)this, (String)name);
        return this;
    }
}

