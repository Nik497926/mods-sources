/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.village.MerchantRecipe
 *  net.minecraft.village.MerchantRecipeList
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.base;

import net.divinerpg.client.GuiHandler;
import net.divinerpg.entities.base.EntityDivineRPGVillager;
import net.divinerpg.entities.lelyetia.StringUtil;
import net.divinerpg.utils.items.IceikaItems;
import net.divinerpg.utils.items.JourneyItemsOther;
import net.divinerpg.utils.items.JourneyItemsWeapon;
import net.divinerpg.utils.items.SkinItems;
import net.divinerpg.utils.items.TwilightItemsArmor;
import net.divinerpg.utils.items.TwilightItemsWeapons;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.divinerpg.utils.items.VanillaItemsWeapons;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;

public class EntityTrollTrader
extends EntityDivineRPGVillager {
    public EntityTrollTrader(World var1) {
        super(var1);
    }

    @Override
    public void extraInteract(EntityPlayer p) {
        p.addChatMessage(StringUtil.getLocale(" \u041a\u0430\u043a \u0442\u044b \u043c\u0435\u043d\u044f \u043d\u0430\u0448\u0451\u043b?"));
    }

    @Override
    protected String getHurtSound() {
        return "mob.villager.hit";
    }

    @Override
    protected String getDeathSound() {
        return "mob.villager.death";
    }

    @Override
    public int guiID() {
        return GuiHandler.trollTrader;
    }

    @Override
    protected boolean canDespawn() {
        return true;
    }

    @Override
    public void addRecipies(MerchantRecipeList list) {
        list.add((Object)new MerchantRecipe(new ItemStack(JourneyItemsOther.doneSoul, 1), new ItemStack(TwilightItemsArmor.haliteHelmet)));
        list.add((Object)new MerchantRecipe(new ItemStack(JourneyItemsOther.doneSoul, 1), new ItemStack(TwilightItemsArmor.haliteChestplate)));
        list.add((Object)new MerchantRecipe(new ItemStack(JourneyItemsOther.doneSoul, 1), new ItemStack(TwilightItemsArmor.haliteLeggings)));
        list.add((Object)new MerchantRecipe(new ItemStack(JourneyItemsOther.doneSoul, 1), new ItemStack(TwilightItemsArmor.haliteBoots)));
        list.add((Object)new MerchantRecipe(new ItemStack(JourneyItemsOther.doneSoul, 10), new ItemStack(TwilightItemsArmor.awakenedHelmet)));
        list.add((Object)new MerchantRecipe(new ItemStack(JourneyItemsOther.doneSoul, 10), new ItemStack(TwilightItemsArmor.awakenedChestplate)));
        list.add((Object)new MerchantRecipe(new ItemStack(JourneyItemsOther.doneSoul, 10), new ItemStack(TwilightItemsArmor.awakenedLeggings)));
        list.add((Object)new MerchantRecipe(new ItemStack(JourneyItemsOther.doneSoul, 10), new ItemStack(TwilightItemsArmor.awakenedBoots)));
        list.add((Object)new MerchantRecipe(new ItemStack(JourneyItemsOther.doneSoul, 35), new ItemStack(JourneyItemsOther.LuckyPickaxe)));
        list.add((Object)new MerchantRecipe(new ItemStack(JourneyItemsOther.doneSoul, 15), new ItemStack(VanillaItemsWeapons.lunaSoulSword)));
        list.add((Object)new MerchantRecipe(new ItemStack(JourneyItemsOther.doneSoul, 10), new ItemStack(JourneyItemsWeapon.celestiumSword)));
        list.add((Object)new MerchantRecipe(new ItemStack(JourneyItemsOther.doneSoul, 1), new ItemStack(TwilightItemsWeapons.haliteBlade)));
        list.add((Object)new MerchantRecipe(new ItemStack(JourneyItemsOther.doneSoul, 40), new ItemStack(VanillaItemsOther.mysticSoul)));
        list.add((Object)new MerchantRecipe(new ItemStack(JourneyItemsOther.doneSoul, 22), new ItemStack(IceikaItems.frossivence)));
        list.add((Object)new MerchantRecipe(new ItemStack(JourneyItemsOther.doneSoul, 28), new ItemStack(SkinItems.premfrossivence)));
        list.add((Object)new MerchantRecipe(new ItemStack(JourneyItemsOther.doneSoul, 2), new ItemStack(VanillaItemsOther.mysticChunk)));
        list.add((Object)new MerchantRecipe(new ItemStack(JourneyItemsOther.doneSoul, 12), new ItemStack(VanillaItemsOther.boilKey)));
        list.add((Object)new MerchantRecipe(new ItemStack(JourneyItemsOther.doneSoul, 12), new ItemStack(VanillaItemsOther.mysticKey)));
        list.add((Object)new MerchantRecipe(new ItemStack(JourneyItemsOther.doneSoul, 1), new ItemStack(JourneyItemsOther.celestiumIngot, 2)));
    }

    @Override
    public String mobName() {
        return "Troll Trader";
    }
}

