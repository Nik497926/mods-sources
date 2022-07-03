/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block;

import com.meteor.extrabotany.common.block.BlockModContainer;
import com.meteor.extrabotany.common.block.tile.TileRelicPlate;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.lexicon.LexiconData;

public class BlockRelicPlate
extends BlockModContainer
implements ILexiconable {
    public static IIcon overlay;
    IIcon[] icons;

    public BlockRelicPlate() {
        super(Material.iron);
        this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 0.1875f, 1.0f);
        this.setHardness(3.0f);
        this.setResistance(10.0f);
        this.setStepSound(Block.soundTypeMetal);
        this.setBlockName("relicplate");
        BotaniaAPI.blacklistBlockFromMagnet((Block)this, (int)Short.MAX_VALUE);
    }

    public boolean onBlockActivated(World worldObj, int x, int y, int z, EntityPlayer player, int s, float xs, float ys, float zs) {
        ItemStack stack = player.getCurrentEquippedItem();
        if (stack != null && stack.getItem() == ModItems.manaResource && stack.getItemDamage() < 3) {
            if (player == null || !player.capabilities.isCreativeMode) {
                --stack.stackSize;
                if (stack.stackSize == 0 && player != null) {
                    player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
                }
            }
            ItemStack target = stack.copy();
            target.stackSize = 1;
            EntityItem item = new EntityItem(worldObj, (double)x + 0.5, (double)y + 0.5, (double)z + 0.5, target);
            item.delayBeforeCanPickup = 40;
            item.motionZ = 0.0;
            item.motionY = 0.0;
            item.motionX = 0.0;
            if (!worldObj.isRemote) {
                worldObj.spawnEntityInWorld((Entity)item);
            }
            return true;
        }
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean getBlocksMovement(IBlockAccess p_149655_1_, int p_149655_2_, int p_149655_3_, int p_149655_4_) {
        return false;
    }

    @Override
    public void registerBlockIcons(IIconRegister par1IconRegister) {
        this.icons = new IIcon[3];
        for (int i = 0; i < this.icons.length; ++i) {
            this.icons[i] = IconHelper.forBlock((IIconRegister)par1IconRegister, (Block)this, (int)i);
        }
        overlay = IconHelper.forBlock((IIconRegister)par1IconRegister, (Block)this, (String)"overlay");
    }

    public IIcon getIcon(int par1, int par2) {
        return this.icons[Math.min(2, par1)];
    }

    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        return LexiconData.terrasteel;
    }

    public boolean hasComparatorInputOverride() {
        return true;
    }

    public int getComparatorInputOverride(World par1World, int par2, int par3, int par4, int par5) {
        TileRelicPlate plate = (TileRelicPlate)par1World.getTileEntity(par2, par3, par4);
        int val = (int)((double)plate.getCurrentMana() / 500000.0 * 15.0);
        if (plate.getCurrentMana() > 0) {
            val = Math.max(val, 1);
        }
        return val;
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileRelicPlate();
    }
}

