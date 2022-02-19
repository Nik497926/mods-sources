/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentData
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.village.MerchantRecipe
 *  net.minecraft.village.MerchantRecipeList
 *  net.minecraft.world.World
 *  omegalevels.ItemGem$EnumType
 *  omegalevels.ItemStabilizer$EnumType
 *  omegalevels.OmegaLevels
 */
package net.divinerpg.entities.twilight;

import java.util.Map;
import net.divinerpg.DivineRPG;
import net.divinerpg.client.GuiHandler;
import net.divinerpg.entities.base.EntityDivineRPGVillager;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;
import omegalevels.ItemGem;
import omegalevels.ItemStabilizer;
import omegalevels.OmegaLevels;

public class EntityRuneTrader
extends EntityDivineRPGVillager {
    private static final ItemStack defaultHeldItem = new ItemStack(VanillaItemsOther.healingStone, 1);

    public EntityRuneTrader(World var1) {
        super(var1);
    }

    public ItemStack getHeldItem() {
        return defaultHeldItem;
    }

    @Override
    protected String getLivingSound() {
        return Sounds.getSoundName(Sounds.jackOMan);
    }

    @Override
    protected String getHurtSound() {
        return Sounds.getSoundName(Sounds.jackOMan);
    }

    @Override
    protected String getDeathSound() {
        return Sounds.getSoundName(Sounds.jackOMan);
    }

    @Override
    public boolean isAIEnabled() {
        return false;
    }

    @Override
    public void extraInteract(EntityPlayer p) {
    }

    @Override
    public boolean interact(EntityPlayer var1) {
        if (var1.getHeldItem() == null) {
            var1.openGui((Object)DivineRPG.instance, this.guiID(), this.worldObj, this.getEntityId(), 0, 0);
            return true;
        }
        return false;
    }

    @Override
    public int guiID() {
        return GuiHandler.runeTrader;
    }

    @Override
    public void addRecipies(MerchantRecipeList list) {
        list.add((Object)new MerchantRecipe(new ItemStack(VanillaItemsOther.mysticChunk, 12), VanillaItemsOther.mysticKey));
        list.add((Object)new MerchantRecipe(new ItemStack(VanillaItemsOther.redCrystal, 2), new ItemStack(VanillaItemsOther.shadowCoins, 16), new ItemStack((Item)OmegaLevels.gem, 1, ItemGem.EnumType.RED.ordinal())));
        list.add((Object)new MerchantRecipe(new ItemStack(VanillaItemsOther.greenCrystal, 3), new ItemStack(VanillaItemsOther.shadowCoins, 16), new ItemStack((Item)OmegaLevels.gem, 1, ItemGem.EnumType.GREEN.ordinal())));
        list.add((Object)new MerchantRecipe(new ItemStack(VanillaItemsOther.mysticSoul, 4), new ItemStack(VanillaItemsOther.shadowCoins, 64), new ItemStack((Item)OmegaLevels.gem, 1, ItemGem.EnumType.PURPLE.ordinal())));
        list.add((Object)new MerchantRecipe(new ItemStack(Items.book, 1), new ItemStack(VanillaItemsOther.tarnishedCrystal, 3), new ItemStack((Item)OmegaLevels.stabilizer, 1, ItemStabilizer.EnumType.COMMON.ordinal())));
        ItemStack tome = new ItemStack((Item)Items.enchanted_book);
        Items.enchanted_book.addEnchantment(tome, new EnchantmentData(Enchantment.protection, 4));
        list.add((Object)new MerchantRecipe(new ItemStack(VanillaItemsOther.enchantChunk, 23), tome));
        ItemStack tome7 = new ItemStack((Item)Items.enchanted_book);
        Items.enchanted_book.addEnchantment(tome7, new EnchantmentData(Enchantment.protection, 3));
        list.add((Object)new MerchantRecipe(new ItemStack(VanillaItemsOther.enchantChunk, 12), tome7));
        ItemStack tome17 = new ItemStack((Item)Items.enchanted_book);
        Items.enchanted_book.addEnchantment(tome17, new EnchantmentData(Enchantment.protection, 2));
        list.add((Object)new MerchantRecipe(new ItemStack(VanillaItemsOther.enchantChunk, 7), tome17));
        ItemStack tome1 = new ItemStack((Item)Items.enchanted_book);
        Items.enchanted_book.addEnchantment(tome1, new EnchantmentData(Enchantment.sharpness, 5));
        list.add((Object)new MerchantRecipe(new ItemStack(VanillaItemsOther.enchantChunk, 29), tome1));
        ItemStack tome4 = new ItemStack((Item)Items.enchanted_book);
        Items.enchanted_book.addEnchantment(tome4, new EnchantmentData(Enchantment.sharpness, 4));
        list.add((Object)new MerchantRecipe(new ItemStack(VanillaItemsOther.enchantChunk, 19), tome4));
        ItemStack tome8 = new ItemStack((Item)Items.enchanted_book);
        Items.enchanted_book.addEnchantment(tome8, new EnchantmentData(Enchantment.sharpness, 3));
        list.add((Object)new MerchantRecipe(new ItemStack(VanillaItemsOther.enchantChunk, 9), tome8));
        ItemStack tome2 = new ItemStack((Item)Items.enchanted_book);
        Items.enchanted_book.addEnchantment(tome2, new EnchantmentData(Enchantment.unbreaking, 3));
        list.add((Object)new MerchantRecipe(new ItemStack(VanillaItemsOther.enchantChunk, 39), tome2));
        ItemStack tome3 = new ItemStack((Item)Items.enchanted_book);
        Items.enchanted_book.addEnchantment(tome3, new EnchantmentData(Enchantment.unbreaking, 2));
        list.add((Object)new MerchantRecipe(new ItemStack(VanillaItemsOther.enchantChunk, 23), tome3));
        ItemStack tome9 = new ItemStack((Item)Items.enchanted_book);
        Items.enchanted_book.addEnchantment(tome9, new EnchantmentData(Enchantment.unbreaking, 1));
        list.add((Object)new MerchantRecipe(new ItemStack(VanillaItemsOther.enchantChunk, 11), tome9));
        ItemStack tome10 = new ItemStack((Item)Items.enchanted_book);
        Items.enchanted_book.addEnchantment(tome10, new EnchantmentData(Enchantment.featherFalling, 4));
        list.add((Object)new MerchantRecipe(new ItemStack(VanillaItemsOther.enchantChunk, 27), tome10));
        ItemStack tome11 = new ItemStack((Item)Items.enchanted_book);
        Map map1 = EnchantmentHelper.getEnchantments((ItemStack)tome11);
        map1.put(0, 5);
        EnchantmentHelper.setEnchantments((Map)map1, (ItemStack)tome11);
        list.add((Object)new MerchantRecipe(new ItemStack(VanillaItemsOther.mysticSoul, 4), tome11));
        ItemStack tome12 = new ItemStack((Item)Items.enchanted_book);
        Map map2 = EnchantmentHelper.getEnchantments((ItemStack)tome12);
        map2.put(34, 5);
        EnchantmentHelper.setEnchantments((Map)map2, (ItemStack)tome12);
        list.add((Object)new MerchantRecipe(new ItemStack(VanillaItemsOther.mysticSoul, 4), tome12));
        ItemStack tome13 = new ItemStack((Item)Items.enchanted_book);
        Map map3 = EnchantmentHelper.getEnchantments((ItemStack)tome13);
        map3.put(16, 7);
        EnchantmentHelper.setEnchantments((Map)map3, (ItemStack)tome13);
        list.add((Object)new MerchantRecipe(new ItemStack(VanillaItemsOther.mysticSoul, 6), tome13));
        ItemStack tome14 = new ItemStack((Item)Items.enchanted_book);
        Map map4 = EnchantmentHelper.getEnchantments((ItemStack)tome14);
        map4.put(2, 5);
        EnchantmentHelper.setEnchantments((Map)map4, (ItemStack)tome14);
        list.add((Object)new MerchantRecipe(new ItemStack(VanillaItemsOther.mysticSoul, 2), tome14));
        ItemStack tome28 = new ItemStack((Item)Items.enchanted_book);
        Map map28 = EnchantmentHelper.getEnchantments((ItemStack)tome28);
        map28.put(35, 5);
        EnchantmentHelper.setEnchantments((Map)map28, (ItemStack)tome28);
        list.add((Object)new MerchantRecipe(new ItemStack(VanillaItemsOther.mysticSoul, 5), tome28));
    }

    @Override
    public String mobName() {
        return "RuneTrader";
    }

    @Override
    public boolean canDespawn() {
        return true;
    }
}

