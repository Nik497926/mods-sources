/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 */
package net.divinerpg.items.arcana;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.divinerpg.items.base.ItemMod;
import net.divinerpg.utils.TooltipLocalizer;
import net.divinerpg.utils.events.ArcanaHelper;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ItemEnderScepter
extends ItemMod {
    private Random rand = new Random();

    public ItemEnderScepter(String name) {
        super(name, DivineRPGTabs.swords);
        this.setMaxStackSize(1);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1, World par2, EntityPlayer par3) {
        float var18;
        float var16;
        double var19;
        float var15;
        float var4 = par3.rotationPitch;
        float var5 = par3.rotationYaw;
        double var6 = par3.posX;
        double var8 = par3.posY + 1.62 - (double)par3.yOffset;
        double var10 = par3.posZ;
        Vec3 var12 = Vec3.createVectorHelper((double)var6, (double)var8, (double)var10);
        float var13 = MathHelper.cos((float)(-var5 * 0.01745329f - (float)Math.PI));
        float var14 = MathHelper.sin((float)(-var5 * 0.01745329f - (float)Math.PI));
        float var17 = var14 * (var15 = -MathHelper.cos((float)(-var4 * 0.01745329f)));
        Vec3 var21 = var12.addVector((double)var17 * (var19 = 50.0), (double)(var16 = MathHelper.sin((float)(-var4 * 0.01745329f))) * var19, (double)(var18 = var13 * var15) * var19);
        MovingObjectPosition var22 = par2.rayTraceBlocks(var12, var21);
        if (var22 == null) {
            return par1;
        }
        if (var22.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
            int var23 = var22.blockX;
            int var24 = var22.blockY;
            int var25 = var22.blockZ;
            int var26 = var22.sideHit;
            if (var26 == 0) {
                --var24;
            }
            if (var26 == 1) {
                ++var24;
            }
            if (var26 == 2) {
                --var25;
            }
            if (var26 == 3) {
                ++var25;
            }
            if (var26 == 4) {
                --var23;
            }
            if (var26 == 5) {
                ++var23;
            }
            if (ArcanaHelper.getProperties(par3).useBar(75.0f)) {
                par3.getLook(1.0f);
                this.teleportTo(par3, par2, var23, var24, var25);
            }
        }
        return par1;
    }

    protected void teleportTo(EntityPlayer par1, World par2, double par3, double par4, double par5) {
        par1.setPosition(par3, par4, par5);
        par2.playSoundAtEntity((Entity)par1, "mob.endermen.portal", 1.0f, 1.0f);
    }

    @SideOnly(value=Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        list.add(TooltipLocalizer.arcanaConsumed(75));
        list.add("On use: Teleports the player");
        list.add(TooltipLocalizer.infiniteUses());
    }
}

