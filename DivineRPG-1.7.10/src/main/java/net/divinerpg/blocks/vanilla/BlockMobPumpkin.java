/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
 *  net.minecraft.block.BlockDirectional
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package net.divinerpg.blocks.vanilla;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.LangRegistry;
import net.divinerpg.utils.blocks.VanillaBlocks;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockMobPumpkin
extends BlockDirectional {
    @SideOnly(value=Side.CLIENT)
    private IIcon top;
    @SideOnly(value=Side.CLIENT)
    private IIcon front;
    private static Block.SoundType wood = Block.soundTypeWood;
    private static Material pumpkin = Material.gourd;
    private Random rand = new Random();

    public BlockMobPumpkin() {
        super(pumpkin);
        this.setTickRandomly(true);
        this.setHardness(1.0f);
        this.setCreativeTab(DivineRPGTabs.blocks);
        this.setSoundType(wood);
        LangRegistry.addBlock((Block)this);
    }

    public Block setSoundType(Block.SoundType name) {
        return this.setStepSound(name);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int par1, int par2) {
        return par1 == 1 ? this.top : (par1 == 0 ? this.top : (par2 == 2 && par1 == 2 ? this.front : (par2 == 3 && par1 == 5 ? this.front : (par2 == 0 && par1 == 3 ? this.front : (par2 == 1 && par1 == 4 ? this.front : this.blockIcon)))));
    }

    public boolean canPlaceBlockAt(World world, int par1, int par2, int par3) {
        return world.getBlock(par1, par2, par3).isReplaceable((IBlockAccess)world, par1, par2, par3) && World.doesBlockHaveSolidTopSurface((IBlockAccess)world, (int)par1, (int)(par2 - 1), (int)par3);
    }

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack item) {
        int l = MathHelper.floor_double((double)((double)(player.rotationYaw * 4.0f / 360.0f) + 2.5)) & 3;
        world.setBlockMetadataWithNotify(x, y, z, l, 2);
    }

    public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int var6, float var7, float var8, float var9) {
        if (!p.isSneaking()) {
            if (w.getBlock(x, y, z) == VanillaBlocks.spiderPumpkin) {
                w.playSoundAtEntity((Entity)p, "mob.spider.say", 1.0f, 1.0f);
            }
            if (w.getBlock(x, y, z) == VanillaBlocks.glaconPumpkin) {
                w.playSoundAtEntity((Entity)p, Sounds.glacide.getPrefixedName(), 1.0f, 1.0f);
            }
            if (w.getBlock(x, y, z) == VanillaBlocks.enderWatcherPumpkin) {
                w.playSoundAtEntity((Entity)p, "mob.endermen.idle", 1.0f, 1.0f);
            }
            if (w.getBlock(x, y, z) == VanillaBlocks.jungleSpiderPumpkin) {
                w.playSoundAtEntity((Entity)p, Sounds.hellSpider.getPrefixedName(), 1.0f, 1.0f);
            }
            if (w.getBlock(x, y, z) == VanillaBlocks.hellspiderPumpkin) {
                w.playSoundAtEntity((Entity)p, Sounds.hellSpider.getPrefixedName(), 1.0f, 1.0f);
            }
            if (w.getBlock(x, y, z) == VanillaBlocks.enderPumpkin) {
                w.playSoundAtEntity((Entity)p, "mob.endermen.scream", 1.0f, 1.0f);
            }
            if (w.getBlock(x, y, z) == VanillaBlocks.creeperPumpkin) {
                w.playSoundAtEntity((Entity)p, "mob.creeper.say", 1.0f, 1.0f);
            }
            if (w.getBlock(x, y, z) == VanillaBlocks.skeletonPumpkin) {
                w.playSoundAtEntity((Entity)p, "mob.skeleton.say", 100.0f, 1.0f);
            }
            if (w.getBlock(x, y, z) == VanillaBlocks.blazePumpkin) {
                w.playSoundAtEntity((Entity)p, "mob.blaze.breathe", 1.0f, 1.0f);
            }
            if (w.getBlock(x, y, z) == VanillaBlocks.zombiePumpkin) {
                w.playSoundAtEntity((Entity)p, "mob.zombie.say", 1.0f, 1.0f);
            }
            if (w.getBlock(x, y, z) == VanillaBlocks.frostPumpkin) {
                w.playSoundAtEntity((Entity)p, Sounds.frost.getPrefixedName(), 1.0f, 1.0f);
            }
            if (w.getBlock(x, y, z) == VanillaBlocks.cyclopsPumpkin) {
                w.playSoundAtEntity((Entity)p, Sounds.cyclops.getPrefixedName(), 1.0f, 1.0f);
            }
            if (w.getBlock(x, y, z) == VanillaBlocks.ghastPumpkin) {
                w.playSoundAtEntity((Entity)p, "mob.ghast.scream", 1.0f, 1.0f);
            }
        }
        return true;
    }

    public Block setName(String name) {
        this.setBlockName(name);
        this.setBlockTextureName(name);
        GameRegistry.registerBlock((Block)this, (String)name);
        return this;
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister icon) {
        this.front = icon.registerIcon("divinerpg:" + this.getTextureName() + "_front");
        this.top = icon.registerIcon("divinerpg:" + this.getTextureName() + "_top");
        this.blockIcon = icon.registerIcon("divinerpg:" + this.getTextureName() + "_side");
    }
}

