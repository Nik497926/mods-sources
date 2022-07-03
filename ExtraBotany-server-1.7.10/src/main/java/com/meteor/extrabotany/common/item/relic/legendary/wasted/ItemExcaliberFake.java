/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.item.relic.legendary.wasted;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.meteor.extrabotany.common.item.relic.ItemRelicAdv;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import vazkii.botania.common.item.relic.ItemRelic;

public class ItemExcaliberFake
extends ItemRelicAdv {
    public static Item.ToolMaterial toolMaterial = EnumHelper.addToolMaterial((String)"B_EXCALIBER", (int)3, (int)-1, (float)6.2f, (float)6.0f, (int)40);

    public ItemExcaliberFake() {
        super("excaliberfake");
    }

    public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {
        if (par3Entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)par3Entity;
            ItemRelic.updateRelic((ItemStack)par1ItemStack, (EntityPlayer)player);
            if (ItemRelic.isRightPlayer((EntityPlayer)player, (ItemStack)par1ItemStack)) {
                PotionEffect haste = player.getActivePotionEffect(Potion.digSpeed);
                if (haste == null) {
                    float var10000 = 0.16666667f;
                } else {
                    float f = haste.getAmplifier() == 1 ? 0.5f : 0.2f;
                }
            }
        }
    }

    public Multimap getItemAttributeModifiers() {
        HashMultimap multimap = HashMultimap.create();
        multimap.put((Object)SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), (Object)new AttributeModifier(field_111210_e, "Weapon modifier", 10.0, 0));
        multimap.put((Object)SharedMonsterAttributes.movementSpeed.getAttributeUnlocalizedName(), (Object)new AttributeModifier(field_111210_e, "Weapon modifier", 0.3, 1));
        return multimap;
    }
}

