/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.stats.StatBase
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package net.divinerpg.items.arcana;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.divinerpg.items.base.ItemMod;
import net.divinerpg.libs.DivineRPGAchievements;
import net.divinerpg.utils.TooltipLocalizer;
import net.divinerpg.utils.Util;
import net.divinerpg.utils.blocks.ArcanaBlocks;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatBase;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemStaffEnrichment
extends ItemMod {
    public ItemStaffEnrichment(String name) {
        super(name, DivineRPGTabs.tools);
        this.maxStackSize = 1;
        this.setMaxDamage(100);
    }

    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
        if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack)) {
            return false;
        }
        Block var11 = par3World.getBlock(par4, par5, par6);
        Block var12 = par3World.getBlock(par4, par5 + 1, par6);
        if (var11 == ArcanaBlocks.arcanaDirt) {
            Block var13 = ArcanaBlocks.arcanaGrass;
            par3World.playSoundEffect((double)((float)par4 + 0.5f), (double)((float)par5 + 0.5f), (double)((float)par6 + 0.5f), var13.stepSound.getStepResourcePath(), (var13.stepSound.getVolume() + 1.0f) / 2.0f, var13.stepSound.getPitch() * 0.8f);
            par2EntityPlayer.triggerAchievement((StatBase)DivineRPGAchievements.enrichment);
            if (par3World.isRemote) {
                return true;
            }
            par3World.setBlock(par4, par5, par6, var13);
            par1ItemStack.damageItem(1, (EntityLivingBase)par2EntityPlayer);
            return true;
        }
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public void addInformation(ItemStack item, EntityPlayer par2EntityPlayer, List list, boolean par4) {
        list.add(Util.GOLD + StatCollector.translateToLocal((String)"item.staffenrichment"));
        int dur = item.getMaxDamage() - item.getItemDamage();
        double max = item.getMaxDamage();
        int res = (int)((double)dur / max * 100.0);
        list.add(TooltipLocalizer.usesRemaining(dur, max) + " (" + res + "%)");
    }

    @SideOnly(value=Side.CLIENT)
    public boolean isFull3D() {
        return true;
    }
}

