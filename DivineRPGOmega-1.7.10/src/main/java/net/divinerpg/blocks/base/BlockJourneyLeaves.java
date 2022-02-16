/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemShears
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.IShearable
 */
package net.divinerpg.blocks.base;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Random;
import net.divinerpg.blocks.base.BlockMod;
import net.divinerpg.utils.blocks.VetheaBlocks;
import net.divinerpg.utils.material.EnumBlockType;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;

public class BlockJourneyLeaves
extends BlockMod
implements IShearable {
    protected int[] adjacentTreeBlocks;
    private IIcon[] textures = new IIcon[2];
    protected IIcon top;
    protected IIcon bottom;
    protected IIcon side;
    private Block sapling;

    public BlockJourneyLeaves(String name, float hardness) {
        super(EnumBlockType.LEAVES, name, hardness);
        this.setHardness(0.3f);
        this.setLightOpacity(1);
        this.setTickRandomly(true);
    }

    public BlockJourneyLeaves(String name, float hardness, Block sapling) {
        this(name, hardness);
        this.sapling = sapling;
    }

    public boolean isLeaves(IBlockAccess world, int x, int y, int z) {
        return true;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public void harvestBlock(World w, EntityPlayer p, int x, int y, int z, int meta) {
        super.harvestBlock(w, p, x, y, z, meta);
        if (!w.isRemote && this.sapling != null && this.rand.nextInt(100) == 0 && (p.getHeldItem() == null || p.getHeldItem() != null && !(p.getHeldItem().getItem() instanceof ItemShears))) {
            this.dropBlockAsItem(w, x, y, z, new ItemStack(this.sapling, 1));
        }
    }

    public IIcon getIcon(int intSide, int meta) {
        return intSide == 1 ? this.top : (intSide == 0 ? this.bottom : this.side);
    }

    public void registerBlockIcons(IIconRegister i) {
        this.side = i.registerIcon(this.textureName + "_side");
        this.top = i.registerIcon(this.textureName + "_top");
        this.bottom = i.registerIcon(this.textureName + "_bottom");
    }

    @SideOnly(value=Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess w, int x, int y, int z, int meta) {
        Block block = w.getBlock(x, y, z);
        return (this.isFancy(w, x, y, z) || block != this) && super.shouldSideBeRendered(w, x, y, z, meta);
    }

    @Override
    public Item getItemDropped(int par1, Random rand, int par3) {
        return null;
    }

    public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z) {
        return true;
    }

    public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        ret.add(new ItemStack((Block)this, 1, world.getBlockMetadata(x, y, z) & 3));
        return ret;
    }

    private boolean isFancy(IBlockAccess world, int x, int y, int z) {
        return Minecraft.getMinecraft().gameSettings.fancyGraphics || world.getBlock(x, y, z) == VetheaBlocks.firewoodLeaves || world.getBlock(x, y, z) == VetheaBlocks.hyrewoodLeaves || world.getBlock(x, y, z) == VetheaBlocks.dreamWoodLeaves || world.getBlock(x, y, z) == VetheaBlocks.mintwoodLeaves;
    }
}

