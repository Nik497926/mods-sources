/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block;

import com.meteor.extrabotany.common.block.tile.TileExtraSpreader;
import cpw.mods.fml.common.registry.GameRegistry;
import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.mana.ILens;
import vazkii.botania.api.wand.IWandHUD;
import vazkii.botania.api.wand.IWandable;
import vazkii.botania.api.wand.IWireframeAABBProvider;
import vazkii.botania.client.lib.LibRenderIDs;
import vazkii.botania.common.block.BlockModContainer;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.block.ItemBlockWithMetadataAndName;
import vazkii.botania.common.lexicon.LexiconData;

public class ExtraSpreader extends BlockModContainer
implements IWandable,
IWandHUD,
ILexiconable,
IWireframeAABBProvider {
    Random random;

    public ExtraSpreader() {
        super(Material.wood);
        this.setHardness(2.0f);
        this.setStepSound(soundTypeWood);
        this.setBlockName("ExtraBotania:Spreader");
        this.random = new Random();
    }

    protected boolean shouldRegisterInNameSet() {
        return false;
    }

    @Override
    public TileEntity func_149915_a(World world, int i) {
        return null;
    }

    public Block setBlockName(String par1Str) {
        GameRegistry.registerTileEntity(TileExtraSpreader.class, "extrabotania." + par1Str);
        GameRegistry.registerBlock(this, ItemBlockWithMetadataAndName.class, par1Str);
        return super.setBlockName(par1Str);
    }

    public void registerBlockIcons(IIconRegister par1IconRegister) {
    }

    public void getSubBlocks(Item par1, CreativeTabs par2, List par3) {
        for (int i = 0; i < 2; ++i) {
            par3.add(new ItemStack(par1, 1, i));
        }
    }

    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
        int orientation = BlockPistonBase.determineOrientation(par1World, par2, par3, par4, par5EntityLivingBase);
        TileExtraSpreader spreader = (TileExtraSpreader)par1World.getTileEntity(par2, par3, par4);
        par1World.setBlockMetadataWithNotify(par2, par3, par4, par6ItemStack.getItemDamage(), 3);
        switch (orientation) {
            case 0: {
                spreader.rotationY = -90.0f;
                break;
            }
            case 1: {
                spreader.rotationY = 90.0f;
                break;
            }
            case 2: {
                spreader.rotationX = 270.0f;
                break;
            }
            case 3: {
                spreader.rotationX = 90.0f;
                break;
            }
            case 4: {
                break;
            }
            default: {
                spreader.rotationX = 180.0f;
            }
        }
    }

    public int damageDropped(int par1) {
        return par1;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public IIcon getIcon(int par1, int par2) {
        return par2 >= 2 ? ModBlocks.dreamwood.getIcon(par1, 0) : ModBlocks.livingwood.getIcon(par1, 0);
    }

    public int getRenderType() {
        return LibRenderIDs.idSpreader;
    }

    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        boolean wool;
        TileEntity tile = par1World.getTileEntity(par2, par3, par4);
        if (!(tile instanceof TileExtraSpreader)) {
            return false;
        }
        TileExtraSpreader spreader = (TileExtraSpreader) tile;
        ItemStack lens = spreader.func_70301_a(0);
        ItemStack heldItem = par5EntityPlayer.getCurrentEquippedItem();
        boolean isHeldItemLens = heldItem != null && heldItem.getItem() instanceof ILens;
        boolean bl = wool = heldItem != null && heldItem.getItem() == Item.getItemFromBlock(Blocks.wool);
        if (heldItem != null && heldItem.getItem() == ModItems.twigWand) {
            return false;
        }
        if (lens == null && isHeldItemLens) {
            if (!par5EntityPlayer.capabilities.isCreativeMode) {
                par5EntityPlayer.inventory.setInventorySlotContents(par5EntityPlayer.inventory.currentItem, null);
            }
            spreader.func_70299_a(0, heldItem.copy());
            spreader.markDirty();
        } else if (lens != null && !wool) {
            ItemStack add = lens.copy();
            if (!par5EntityPlayer.inventory.addItemStackToInventory(add)) {
                par5EntityPlayer.dropPlayerItemWithRandomChoice(add, false);
            }
            spreader.func_70299_a(0, null);
            spreader.markDirty();
        }
        if (wool && spreader.paddingColor == -1) {
            spreader.paddingColor = heldItem.getItemDamage();
            --heldItem.stackSize;
            if (heldItem.stackSize == 0) {
                par5EntityPlayer.inventory.setInventorySlotContents(par5EntityPlayer.inventory.currentItem, null);
            }
        } else if (heldItem == null && spreader.paddingColor != -1 && lens == null) {
            ItemStack pad = new ItemStack(Blocks.wool, 1, spreader.paddingColor);
            if (!par5EntityPlayer.inventory.addItemStackToInventory(pad)) {
                par5EntityPlayer.dropPlayerItemWithRandomChoice(pad, false);
            }
            spreader.paddingColor = -1;
            spreader.markDirty();
        }
        return true;
    }

    public void breakBlock(World par1World, int par2, int par3, int par4, Block par5, int par6) {
        TileEntity tile = par1World.getTileEntity(par2, par3, par4);
        if (!(tile instanceof TileExtraSpreader)) {
            return;
        }
        TileExtraSpreader inv = (TileExtraSpreader)tile;
        if (inv != null) {
            for (int j1 = 0; j1 < inv.getSizeInventory() + 1; ++j1) {
                ItemStack itemstack = j1 >= inv.getSizeInventory() ? (inv.paddingColor == -1 ? null : new ItemStack(Blocks.wool, 1, inv.paddingColor)) : (itemstack = inv.func_70301_a(j1));
                if (itemstack == null) continue;
                float f = this.random.nextFloat() * 0.8f + 0.1f;
                float f1 = this.random.nextFloat() * 0.8f + 0.1f;
                float f2 = this.random.nextFloat() * 0.8f + 0.1f;
                while (itemstack.stackSize > 0) {
                    int k1 = this.random.nextInt(21) + 10;
                    if (k1 > itemstack.stackSize) {
                        k1 = itemstack.stackSize;
                    }
                    itemstack.stackSize -= k1;
                    EntityItem entityitem = new EntityItem(par1World, (float)par2 + f, (float)par3 + f1, (float)par4 + f2, new ItemStack(itemstack.getItem(), k1, itemstack.getItemDamage()));
                    float f3 = 0.05f;
                    entityitem.motionX = (float)this.random.nextGaussian() * f3;
                    entityitem.motionY = (float)this.random.nextGaussian() * f3 + 0.2f;
                    entityitem.motionZ = (float)this.random.nextGaussian() * f3;
                    if (itemstack.hasTagCompound()) {
                        entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
                    }
                    par1World.spawnEntityInWorld(entityitem);
                }
            }
            par1World.func_147453_f(par2, par3, par4, par5);
        }
        super.breakBlock(par1World, par2, par3, par4, par5, par6);
    }

    public boolean onUsedByWand(EntityPlayer player, ItemStack stack, World world, int x, int y, int z, int side) {
        ((TileExtraSpreader)world.getTileEntity(x, y, z)).onWanded(player, stack);
        return true;
    }

    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileExtraSpreader();
    }

    @SideOnly(Side.CLIENT)
    public void renderHUD(Minecraft mc, ScaledResolution res, World world, int x, int y, int z) {

    }

    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        int meta = world.getBlockMetadata(x, y, z);
        return meta == 0 ? LexiconData.spreader : (meta == 1 ? LexiconData.redstoneSpreader : LexiconData.dreamwoodSpreader);
    }

    public AxisAlignedBB getWireframeAABB(World world, int x, int y, int z) {
        float f = 0.0625f;
        return AxisAlignedBB.getBoundingBox((float)x + f, (float)y + f, (float)z + f, (float)(x + 1) - f, (float)(y + 1) - f, (float)(z + 1) - f);
    }
}

