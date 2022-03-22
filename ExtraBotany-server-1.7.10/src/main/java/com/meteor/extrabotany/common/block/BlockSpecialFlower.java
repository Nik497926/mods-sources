/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block;

import cpw.mods.fml.common.registry.GameRegistry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.ILexiconable;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.ISpecialFlower;
import vazkii.botania.api.wand.IWandHUD;
import vazkii.botania.api.wand.IWandable;
import vazkii.botania.client.lib.LibRenderIDs;
import vazkii.botania.common.block.BlockModFlower;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.tile.TileSpecialFlower;
import vazkii.botania.common.core.BotaniaCreativeTab;
import vazkii.botania.common.integration.coloredlights.LightHelper;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;

public class BlockSpecialFlower
extends BlockFlower
implements ITileEntityProvider,
ISpecialFlower,
IWandable,
ILexiconable,
IWandHUD {
    public static Map<String, IIcon> icons = new HashMap<String, IIcon>();
    public static Map<String, IIcon> iconsAlt = new HashMap<String, IIcon>();

    protected BlockSpecialFlower() {
        super(0);
        this.setBlockName("specialFlower");
        this.setHardness(0.1f);
        this.setStepSound(soundTypeGrass);
        this.setTickRandomly(false);
        this.setCreativeTab(BotaniaCreativeTab.INSTANCE);
        this.setBlockBounds(0.3f, 0.0f, 0.3f, 0.8f, 1.0f, 0.8f);
    }

    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        int currentLight = ((TileSpecialFlower)world.getTileEntity(x, y, z)).getLightValue();
        if (currentLight == -1) {
            currentLight = 0;
        }
        return LightHelper.getPackedColor(world.getBlockMetadata(x, y, z), currentLight);
    }

    public boolean hasComparatorInputOverride() {
        return true;
    }

    public int getComparatorInputOverride(World world, int x, int y, int z, int side) {
        return ((TileSpecialFlower)world.getTileEntity(x, y, z)).getComparatorInputOverride(side);
    }

    public int isProvidingWeakPower(IBlockAccess world, int x, int y, int z, int side) {
        return ((TileSpecialFlower)world.getTileEntity(x, y, z)).getPowerLevel(side);
    }

    public int isProvidingStrongPower(IBlockAccess world, int x, int y, int z, int side) {
        return this.isProvidingWeakPower(world, x, y, z, side);
    }

    public boolean canProvidePower() {
        return true;
    }

    public int getRenderType() {
        return LibRenderIDs.idSpecialFlower;
    }

    public Block setBlockName(String par1Str) {
        GameRegistry.registerBlock(this, ItemBlockSpecialFlower.class, par1Str);
        return super.setBlockName(par1Str);
    }

    public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        for (String s : BotaniaAPI.subtilesForCreativeMenu) {
            par3List.add(ItemBlockSpecialFlower.ofType(s));
            if (!BotaniaAPI.miniFlowers.containsKey(s)) continue;
            par3List.add(ItemBlockSpecialFlower.ofType(BotaniaAPI.miniFlowers.get(s)));
        }
    }

    public void registerBlockIcons(IIconRegister par1IconRegister) {
        for (String s : BotaniaAPI.getAllSubTiles()) {
            if (s.isEmpty()) continue;
            BotaniaAPI.getSignatureForName(s).registerIcons(par1IconRegister);
        }
    }

    public IIcon getIcon(IBlockAccess par1iBlockAccess, int par2, int par3, int par4, int par5) {
        return ((TileSpecialFlower)par1iBlockAccess.getTileEntity(par2, par3, par4)).getIcon();
    }

    public IIcon getIcon(int par1, int par2) {
        return BlockModFlower.icons[16];
    }

    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        String name = ((TileSpecialFlower)world.getTileEntity(x, y, z)).subTileName;
        return ItemBlockSpecialFlower.ofType(name);
    }

    protected boolean canPlaceBlockOn(Block block) {
        return super.canPlaceBlockOn(block) || block == ModBlocks.redStringRelay || block == Blocks.mycelium;
    }

    public void onBlockHarvested(World par1World, int par2, int par3, int par4, int par5, EntityPlayer par6EntityPlayer) {
        if (!par6EntityPlayer.capabilities.isCreativeMode) {
            this.dropBlockAsItem(par1World, par2, par3, par4, par5, 0);
            ((TileSpecialFlower)par1World.getTileEntity(par2, par3, par4)).onBlockHarvested(par1World, par2, par3, par4, par5, par6EntityPlayer);
        }
    }

    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        ArrayList<ItemStack> list = new ArrayList<ItemStack>();
        TileEntity tile = world.getTileEntity(x, y, z);
        if (tile != null) {
            String name = ((TileSpecialFlower)tile).subTileName;
            list.add(ItemBlockSpecialFlower.ofType(name));
            ((TileSpecialFlower)tile).getDrops(list);
        }
        return list;
    }

    public boolean onBlockEventReceived(World par1World, int par2, int par3, int par4, int par5, int par6) {
        super.onBlockEventReceived(par1World, par2, par3, par4, par5, par6);
        TileEntity tileentity = par1World.getTileEntity(par2, par3, par4);
        return tileentity != null && tileentity.receiveClientEvent(par5, par6);
    }

    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileSpecialFlower();
    }

    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        return ((TileSpecialFlower)world.getTileEntity(x, y, z)).getEntry();
    }

    public boolean onUsedByWand(EntityPlayer player, ItemStack stack, World world, int x, int y, int z, int side) {
        return ((TileSpecialFlower)world.getTileEntity(x, y, z)).onWanded(stack, player);
    }

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
        ((TileSpecialFlower)world.getTileEntity(x, y, z)).onBlockPlacedBy(world, x, y, z, entity, stack);
    }

    public void onBlockAdded(World world, int x, int y, int z) {
        ((TileSpecialFlower)world.getTileEntity(x, y, z)).onBlockAdded(world, x, y, z);
    }

    public int colorMultiplier(IBlockAccess world, int x, int y, int z) {
        float[] rgb = EntitySheep.fleeceColorTable[world.getBlockMetadata(x, y, z)];
        return ((int)(rgb[0] * 255.0f) << 16) + ((int)(rgb[1] * 255.0f) << 8) + (int)(rgb[2] * 255.0f);
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        int oldMeta;
        int newMeta;
        ItemStack stack = player.getCurrentEquippedItem();
        if (stack != null && stack.getItem() == ModItems.dye && (newMeta = stack.getItemDamage()) != (oldMeta = world.getBlockMetadata(x, y, z))) {
            world.setBlockMetadataWithNotify(x, y, z, newMeta, 3);
        }
        return ((TileSpecialFlower)world.getTileEntity(x, y, z)).onBlockActivated(world, x, y, z, player, side, hitX, hitY, hitZ);
    }

    public void renderHUD(Minecraft mc, ScaledResolution res, World world, int x, int y, int z) {
        ((TileSpecialFlower)world.getTileEntity(x, y, z)).renderHUD(mc, res);
    }

    static {
        BotaniaAPI.subtilesForCreativeMenu.addAll(Arrays.asList("puredaisy", "manastar", "daybloom", "nightshade", "endoflame", "hydroangeas", "thermalily", "arcanerose", "munchdew", "entropinnyum", "kekimurus", "gourmaryllis", "narslimmus", "spectrolus", "rafflowsia", "dandelifeon", "jadedAmaranthus", "bellethorn", "dreadthorn", "heiseiDream", "tigerseye", "marimorphosis", "orechid", "orechidIgnem", "fallenKanade", "exoflame", "agricarnation", "hopperhock", "rannuncarpus", "tangleberrie", "jiyuulia", "hyacidus", "medumone", "pollidisiac", "clayconia", "loonium", "daffomill", "vinculotus", "spectranthemum", "bubbell", "solegnolia"));
    }
}

