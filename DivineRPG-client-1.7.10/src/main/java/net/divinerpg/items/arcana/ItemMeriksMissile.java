/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 */
package net.divinerpg.items.arcana;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.divinerpg.entities.arcana.projectile.EntityMerikMissile;
import net.divinerpg.items.base.ItemModBow;
import net.divinerpg.utils.TooltipLocalizer;
import net.divinerpg.utils.events.ArcanaHelper;
import net.divinerpg.utils.items.ArcanaItems;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class ItemMeriksMissile
extends ItemModBow {
    private int arcana;
    private IIcon[] texture;
    private String[] textures = new String[]{"divinerpg:meriksMissile_0", "divinerpg:meriksMissile_1", "divinerpg:meriksMissile_2", "divinerpg:meriksMissile_3"};

    public ItemMeriksMissile(String name, int uses, int damage, int arcana) {
        super(name, uses, damage, 72000, null, null);
        this.arcana = arcana;
        this.setMaxStackSize(1);
        bowList.remove((Object)this);
    }

    @Override
    public void registerIcons(IIconRegister par1IconRegister) {
        this.itemIcon = par1IconRegister.registerIcon(this.textures[0]);
        this.texture = new IIcon[this.textures.length];
        for (int i = 0; i < this.textures.length; ++i) {
            this.texture[i] = par1IconRegister.registerIcon("divinerpg:meriksMissile_" + i);
        }
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public IIcon getItemIconForUseDuration(int icon) {
        return this.texture[icon];
    }

    public EnumAction getItemUseAction(ItemStack par1ItemStack) {
        return EnumAction.none;
    }

    @Override
    public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) {
        if (player.getItemInUse() == null) {
            return this.itemIcon;
        }
        int pulling = stack.getMaxItemUseDuration() - useRemaining;
        if (pulling >= 18) {
            return this.texture[3];
        }
        if (pulling > 13) {
            return this.texture[2];
        }
        if (pulling > 0) {
            return this.texture[1];
        }
        return this.texture[0];
    }

    @Override
    public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3) {
        var3.setItemInUse(var1, this.getMaxItemUseDuration(var1));
        return var1;
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World world, EntityPlayer player, int par4) {
        if (stack.getItem() == ArcanaItems.meriksMissile) {
            boolean var5;
            int var6 = this.getMaxItemUseDuration(stack) - par4;
            boolean bl = var5 = player.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel((int)Enchantment.infinity.effectId, (ItemStack)stack) > 0;
            if (ArcanaHelper.getProperties(player).useBar(this.arcana)) {
                float var7 = (float)var6 / 20.0f;
                if ((double)(var7 = (var7 * var7 + var7 * 2.0f) / 3.0f) < 0.1) {
                    return;
                }
                if (var7 > 1.0f) {
                    var7 = 1.0f;
                }
                EntityMerikMissile var8 = new EntityMerikMissile(world, (EntityLivingBase)player);
                world.playSoundAtEntity((Entity)player, "random.bow", 1.0f, 1.0f / (itemRand.nextFloat() * 0.4f + 1.2f) + var7 * 0.5f);
                if (!world.isRemote) {
                    world.spawnEntityInWorld((Entity)var8);
                }
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    public boolean isFull3D() {
        return true;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        list.add("Explosive homing projectile");
        list.add(TooltipLocalizer.rangedDam(22.0));
        list.add(TooltipLocalizer.arcanaConsumed(this.arcana));
        list.add(TooltipLocalizer.infiniteUses());
    }
}

