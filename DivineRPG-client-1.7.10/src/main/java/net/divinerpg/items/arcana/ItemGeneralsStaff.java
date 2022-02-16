/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 */
package net.divinerpg.items.arcana;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.divinerpg.entities.arcana.EntityGeneralsStaff;
import net.divinerpg.items.base.ItemMod;
import net.divinerpg.items.vethea.ItemStaff;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.TooltipLocalizer;
import net.divinerpg.utils.events.ArcanaHelper;
import net.divinerpg.utils.events.Ticker;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemGeneralsStaff
extends ItemMod {
    private Random rand = new Random();

    public ItemGeneralsStaff() {
        super("general'sStaff");
        this.setCreativeTab(DivineRPGTabs.ranged);
        this.setMaxStackSize(1);
        ItemStaff.staffList.add(this);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }
        if ((long)Ticker.tick >= stack.getTagCompound().getLong("CanShootTime") && !world.isRemote && ArcanaHelper.getProperties(player).useBar(20.0f)) {
            world.playSoundAtEntity((Entity)player, Sounds.starlight.getPrefixedName(), 1.0f, 1.0f);
            EntityGeneralsStaff entity = new EntityGeneralsStaff(world, (EntityLivingBase)player);
            world.spawnEntityInWorld((Entity)entity);
            stack.getTagCompound().setLong("CanShootTime", (long)(Ticker.tick + 12));
        }
        if (stack.getTagCompound().getLong("CanShootTime") >= 100000L || stack.getTagCompound().getLong("CanShootTime") > (long)(Ticker.tick + 141)) {
            stack.getTagCompound().setLong("CanShootTime", 0L);
        }
        return stack;
    }

    @SideOnly(value=Side.CLIENT)
    public boolean isFull3D() {
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        list.add(TooltipLocalizer.rangedDam(18.0));
        list.add("Projectile splits into 5 on impact");
        list.add(TooltipLocalizer.arcanaConsumed(20));
        list.add(TooltipLocalizer.infiniteUses());
    }
}

