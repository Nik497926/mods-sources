/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package net.divinerpg.items.vethea;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import net.divinerpg.entities.vethea.projectile.EntityBouncingProjectile;
import net.divinerpg.entities.vethea.projectile.EntityEvernightProjectile;
import net.divinerpg.items.base.ItemMod;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.TooltipLocalizer;
import net.divinerpg.utils.Util;
import net.divinerpg.utils.events.ArcanaHelper;
import net.divinerpg.utils.items.VetheaItems;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemStaff
extends ItemMod {
    public static List<Item> staffList = new ArrayList<Item>();
    protected int damage;
    protected int cost;

    public ItemStaff(int dam, int cos, String name) {
        super(name);
        this.maxStackSize = 1;
        this.damage = dam;
        this.cost = cos;
        this.setCreativeTab(DivineRPGTabs.vethea);
        this.setFull3D();
        staffList.add(this);
    }

    @SideOnly(value=Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        list.add(TooltipLocalizer.arcanaDam(this.damage));
        list.add("Bouncing Projectile");
        if (stack.getItem() == VetheaItems.evernight) {
            list.add("Deals 20 Damage to Player");
        }
        list.add(TooltipLocalizer.arcanaConsumed(this.cost));
        list.add(TooltipLocalizer.vethean());
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1, World par2, EntityPlayer par3) {
        if (!par2.isRemote && ArcanaHelper.getProperties(par3).useBar(this.cost)) {
            if (par1.getItem() == VetheaItems.evernight) {
                par3.attackEntityFrom(Util.arcanaSource, 20.0f);
                par2.spawnEntityInWorld((Entity)new EntityEvernightProjectile(par2, (EntityLivingBase)par3, this.damage));
            } else {
                par2.spawnEntityInWorld((Entity)new EntityBouncingProjectile(par2, (EntityLivingBase)par3, this.damage));
            }
            par2.playSoundAtEntity((Entity)par3, Sounds.staff.getPrefixedName(), 1.0f, 1.0f);
        }
        return par1;
    }
}

