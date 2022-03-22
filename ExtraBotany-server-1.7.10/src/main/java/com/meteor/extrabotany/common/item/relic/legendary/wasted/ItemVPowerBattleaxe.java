/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.item.relic.legendary.wasted;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.meteor.extrabotany.common.item.relic.ItemRelicAdv;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

public class ItemVPowerBattleaxe
extends ItemRelicAdv {
    public static Item.ToolMaterial toolMaterial = EnumHelper.addToolMaterial("B_VPOWERBATTLEAXE", 3, -1, 6.2f, 6.0f, 40);

    public ItemVPowerBattleaxe() {
        super("vpowerbattleaxe");
    }

    public Multimap getItemAttributeModifiers() {
        HashMultimap multimap = HashMultimap.create();
        multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", 18.0, 0));
        multimap.put(SharedMonsterAttributes.movementSpeed.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", -0.2, 1));
        return multimap;
    }

    public boolean isFull3D() {
        return true;
    }

    public EnumAction getItemUseAction(ItemStack par1ItemStack) {
        return EnumAction.bow;
    }
}

