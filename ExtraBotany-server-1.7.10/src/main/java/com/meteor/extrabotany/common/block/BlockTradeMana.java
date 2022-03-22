/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block;

import com.meteor.extrabotany.ExtraBotany;
import com.meteor.extrabotany.common.block.tile.TileTradeMana;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.justagod.cutter.invoke.Invoke;
import vazkii.botania.api.wand.IWandHUD;

public class BlockTradeMana
extends BlockContainer
implements IWandHUD {
    protected BlockTradeMana(Material p_i45386_1_) {
        super(Material.iron);
        this.setCreativeTab(ExtraBotany.tabExtraBotany);
        this.setBlockName("manatrade");
        this.setBlockTextureName("ExtraBotania:manatrade");
        this.setHardness(3.0f);
        GameRegistry.registerBlock(this, "manatrade");
    }

    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileTradeMana();
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        Invoke.server(() -> {});
        return true;
    }

    @SideOnly(Side.CLIENT)
    public void renderHUD(Minecraft minecraft, ScaledResolution scaledResolution, World world, int i, int i1, int i2) {

    }
}

