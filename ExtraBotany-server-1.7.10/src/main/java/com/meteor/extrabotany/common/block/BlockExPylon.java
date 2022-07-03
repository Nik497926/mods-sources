/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block;

import com.meteor.extrabotany.client.ClientProxy;
import com.meteor.extrabotany.common.block.tile.TileExPylon;
import cpw.mods.fml.common.Optional;
import cpw.mods.fml.common.registry.GameRegistry;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import thaumcraft.api.crafting.IInfusionStabiliser;
import vazkii.botania.common.block.BlockModContainer;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.item.block.ItemBlockWithMetadataAndName;

@Optional.Interface(modid="Thaumcraft", iface="thaumcraft.api.crafting.IInfusionStabiliser", striprefs=true)
public class BlockExPylon
extends BlockModContainer
implements IInfusionStabiliser {
    public BlockExPylon() {
        super(Material.rock);
        this.setHardness(5.5f);
        this.setStepSound(soundTypeMetal);
        this.setBlockName("blockExPylon");
        this.setLightLevel(0.5f);
        float f = 0.125f;
        this.setBlockBounds(f, 0.0f, f, 1.0f - f, 1.3125f, 1.0f - f);
    }

    protected boolean shouldRegisterInNameSet() {
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileExPylon();
    }

    public Block setBlockName(String par1Str) {
        GameRegistry.registerBlock((Block)this, ItemBlockWithMetadataAndName.class, (String)par1Str);
        GameRegistry.registerTileEntity(TileExPylon.class, (String)"extraBotania.blockExPylon");
        return super.setBlockName(par1Str);
    }

    public int damageDropped(int par1) {
        return par1;
    }

    public void getSubBlocks(Item par1, CreativeTabs par2, List par3) {
        for (int i = 0; i < 2; ++i) {
            par3.add(new ItemStack(par1, 1, i));
        }
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public int getRenderType() {
        return ClientProxy.renderExPylon;
    }

    public float getEnchantPowerBonus(World world, int x, int y, int z) {
        return world.getBlockMetadata(x, y, z) == 0 ? 8.0f : 15.0f;
    }

    public boolean canStabaliseInfusion(World world, int x, int y, int z) {
        return ConfigHandler.enableThaumcraftStablizers;
    }
}

