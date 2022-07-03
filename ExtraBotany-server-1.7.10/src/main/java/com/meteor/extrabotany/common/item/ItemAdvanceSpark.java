/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.item;

import com.meteor.extrabotany.ExtraBotany;
import com.meteor.extrabotany.common.entity.EntityAdvanceSpark;
import com.meteor.extrabotany.common.entity.EntityAdvanceSpark1;
import com.meteor.extrabotany.common.entity.EntityAdvanceSpark2;
import com.meteor.extrabotany.common.entity.IAdvanceSpark;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import vazkii.botania.api.mana.IManaGivingItem;
import vazkii.botania.common.achievement.ICraftAchievement;
import vazkii.botania.common.item.ItemMod;

public class ItemAdvanceSpark
extends ItemMod
implements ICraftAchievement,
IManaGivingItem {
    public static IIcon[] invIcon = new IIcon[3];
    public static IIcon[] worldIcon = new IIcon[3];

    public ItemAdvanceSpark() {
        this.setUnlocalizedName("advanceSpark");
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
    }

    public String getUnlocalizedName(ItemStack stack) {
        return super.getUnlocalizedName(stack) + ":" + stack.getItemDamage();
    }

    private IAdvanceSpark getSpark(int damage, World world) {
        if (damage == 0) {
            return new EntityAdvanceSpark(world);
        }
        if (damage == 1) {
            return new EntityAdvanceSpark1(world);
        }
        if (damage == 2) {
            return new EntityAdvanceSpark2(world);
        }
        return null;
    }

    public void getSubItems(Item item, CreativeTabs p_150895_2_, List list) {
        for (int i = 0; i < 3; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    public void registerIcons(IIconRegister ir) {
        for (int i = 1; i <= 3; ++i) {
            ItemAdvanceSpark.invIcon[i - 1] = ir.registerIcon(ExtraBotany.modid + ":adv/ItemSparkTier" + i);
            ItemAdvanceSpark.worldIcon[i - 1] = ir.registerIcon(ExtraBotany.modid + ":adv/BlockSparkTier" + i);
        }
    }

    public IIcon getIconFromDamage(int meta) {
        if (meta == 2) {
            return invIcon[2];
        }
        if (meta == 1) {
            return invIcon[1];
        }
        return invIcon[0];
    }

    public Achievement getAchievementOnCraft(ItemStack itemStack, EntityPlayer entityPlayer, IInventory iInventory) {
        return null;
    }
}

