/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockContainer
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.stats.StatBase
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package net.divinerpg.blocks.vanilla;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.divinerpg.DivineRPG;
import net.divinerpg.blocks.vanilla.container.tileentity.TileEntityAltarOfCorruption;
import net.divinerpg.client.GuiHandler;
import net.divinerpg.libs.DivineRPGAchievements;
import net.divinerpg.utils.LangRegistry;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.stats.StatBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockAltarOfCorruption
extends BlockContainer {
    private IIcon top;
    private IIcon side;
    private IIcon bottom;

    public BlockAltarOfCorruption(String name) {
        super(Material.rock);
        this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 0.75f, 1.0f);
        this.setHardness(5.0f);
        this.setResistance(2000.0f);
        this.setBlockName(name);
        this.setBlockTextureName("divinerpg:" + name);
        this.setCreativeTab(DivineRPGTabs.blocks);
        GameRegistry.registerBlock((Block)this, (String)name);
        LangRegistry.addBlock((Block)this);
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random) {
        super.randomDisplayTick(par1World, par2, par3, par4, par5Random);
        for (int var6 = par2 - 2; var6 <= par2 + 2; ++var6) {
            for (int var7 = par4 - 2; var7 <= par4 + 2; ++var7) {
                if (var6 > par2 - 2 && var6 < par2 + 2 && var7 == par4 - 1) {
                    var7 = par4 + 2;
                }
                if (par5Random.nextInt(16) != 0) continue;
                for (int var8 = par3; var8 <= par3 + 1; ++var8) {
                    par1World.spawnParticle("enchantmenttable", (double)par2 + 0.5, (double)par3 + 2.0, (double)par4 + 0.5, (double)((float)(var6 - par2) + par5Random.nextFloat()) - 0.5, (double)((float)(var8 - par3) - par5Random.nextFloat() - 1.0f), (double)((float)(var7 - par4) + par5Random.nextFloat()) - 0.5);
                }
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return side == 0 ? this.bottom : (side == 1 ? this.top : this.side);
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister icon) {
        this.side = icon.registerIcon(this.getTextureName() + "_side");
        this.top = icon.registerIcon(this.getTextureName() + "_top");
        this.bottom = icon.registerIcon(this.getTextureName() + "_bottom");
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        TileEntityAltarOfCorruption tileEntity = (TileEntityAltarOfCorruption)par1World.getTileEntity(par2, par3, par4);
        if (!par1World.isRemote) {
            if (tileEntity != null) {
                par5EntityPlayer.openGui((Object)DivineRPG.instance, GuiHandler.altar, par1World, par2, par3, par4);
                par5EntityPlayer.triggerAchievement((StatBase)DivineRPGAchievements.perfectlyCorrupted);
            }
            return true;
        }
        return false;
    }

    public TileEntity createNewTileEntity(World var1, int var2) {
        return new TileEntityAltarOfCorruption();
    }
}

