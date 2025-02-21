/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.item.weapon;

import com.meteor.extrabotany.ExtraBotany;
import com.meteor.extrabotany.common.entity.EntityGaiaQuickened;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import vazkii.botania.common.core.helper.ItemNBTHelper;

public class ItemHeliacalClaymore
extends ItemSword {
    public static Item.ToolMaterial toolMaterial = EnumHelper.addToolMaterial((String)"B_HELIACAL", (int)3, (int)-1, (float)6.2f, (float)4.0f, (int)20);
    public static String TAG_COOLDOWN = "cooldown";

    public ItemHeliacalClaymore() {
        super(toolMaterial);
        this.setUnlocalizedName("coronaclaymore");
        this.setTextureName("ExtraBotania:coronaclaymore");
        this.setCreativeTab(ExtraBotany.tabExtraBotany);
        GameRegistry.registerItem((Item)this, (String)"coronaclaymore");
    }

    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase player) {
        if (Math.random() > (double)0.84f && this.getCooldown(stack) == 0) {
            EntityGaiaQuickened g = new EntityGaiaQuickened(player, false, 2.0f + toolMaterial.getDamageVsEntity());
            g.setPosition(target.posX, target.posY, target.posZ);
            this.setCooldown(stack, 200);
            player.worldObj.spawnEntityInWorld((Entity)g);
        }
        return target.attackEntityFrom(DamageSource.causeMobDamage((EntityLivingBase)player), toolMaterial.getDamageVsEntity());
    }

    public void onUpdate(ItemStack stack, World par2World, Entity par3Entity, int par4, boolean par5) {
        if (this.getCooldown(stack) > 0) {
            this.setCooldown(stack, this.getCooldown(stack) - 1);
        }
    }

    public int getCooldown(ItemStack stack) {
        return ItemNBTHelper.getInt((ItemStack)stack, (String)TAG_COOLDOWN, (int)0);
    }

    public void setCooldown(ItemStack stack, int i) {
        ItemNBTHelper.setInt((ItemStack)stack, (String)TAG_COOLDOWN, (int)i);
    }
}

