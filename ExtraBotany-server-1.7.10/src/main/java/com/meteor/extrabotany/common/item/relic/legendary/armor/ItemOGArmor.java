/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.item.relic.legendary.armor;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.meteor.extrabotany.common.item.ModItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import java.util.List;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.StatBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.item.IRelic;
import vazkii.botania.client.model.armor.ModelArmorTerrasteel;
import vazkii.botania.common.Botania;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.item.equipment.armor.manasteel.ItemManasteelArmor;
import vazkii.botania.common.item.relic.ItemRelic;

public class ItemOGArmor
extends ItemManasteelArmor
implements IRelic {
    private static final String TAG_SOULBIND = "soulbind";
    Achievement achievement;
    static ItemStack[] armorset;
    static ItemArmor.ArmorMaterial material;

    public ItemOGArmor(int type, String name, ItemArmor.ArmorMaterial arm) {
        super(type, name, arm == null ? material : arm);
    }

    public Multimap getItemAttributeModifiers() {
        HashMultimap multimap = HashMultimap.create();
        UUID uuid = new UUID(this.getUnlocalizedName().hashCode(), 0L);
        multimap.put((Object)SharedMonsterAttributes.knockbackResistance.getAttributeUnlocalizedName(), (Object)new AttributeModifier(uuid, "Relic modifier " + this.type, 0.25, 0));
        multimap.put((Object)SharedMonsterAttributes.movementSpeed.getAttributeUnlocalizedName(), (Object)new AttributeModifier(uuid, "Relic modifier" + this.type, 0.1, 1));
        multimap.put((Object)SharedMonsterAttributes.maxHealth.getAttributeUnlocalizedName(), (Object)new AttributeModifier(uuid, "Relic modifier" + this.type, 10.0, 0));
        return multimap;
    }

    public String getArmorTextureAfterInk(ItemStack stack, int slot) {
        return "extrabotania:textures/models/armor/armorrelic.png";
    }

    public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack) {
        return par2ItemStack.getItem() == ModItems.material && par2ItemStack.getItemDamage() == 11 ? true : super.getIsRepairable(par1ItemStack, par2ItemStack);
    }

    public ItemStack[] getArmorSetStacks() {
        if (armorset == null) {
            armorset = new ItemStack[]{new ItemStack(ModItems.oghelm), new ItemStack(ModItems.ogchest), new ItemStack(ModItems.oglegs), new ItemStack(ModItems.ogboots)};
        }
        return armorset;
    }

    public boolean hasArmorSetItem(EntityPlayer player, int i) {
        ItemStack stack = player.inventory.armorInventory[3 - i];
        if (stack == null) {
            return false;
        }
        switch (i) {
            case 0: {
                return (stack.getItem() == ModItems.oghelm || stack.getItem() == ModItems.awakeoghelm) && ItemRelic.isRightPlayer((EntityPlayer)player, (ItemStack)stack);
            }
            case 1: {
                return (stack.getItem() == ModItems.ogchest || stack.getItem() == ModItems.awakeogchest) && ItemRelic.isRightPlayer((EntityPlayer)player, (ItemStack)stack);
            }
            case 2: {
                return (stack.getItem() == ModItems.oglegs || stack.getItem() == ModItems.awakeoglegs) && ItemRelic.isRightPlayer((EntityPlayer)player, (ItemStack)stack);
            }
            case 3: {
                return (stack.getItem() == ModItems.ogboots || stack.getItem() == ModItems.awakeogboots) && ItemRelic.isRightPlayer((EntityPlayer)player, (ItemStack)stack);
            }
        }
        return false;
    }

    public boolean hasArmorSet(EntityPlayer player) {
        return this.hasArmorSetItem(player, 0) && this.hasArmorSetItem(player, 1) && this.hasArmorSetItem(player, 2) && this.hasArmorSetItem(player, 3);
    }

    public String getArmorSetName() {
        return StatCollector.translateToLocal((String)"botania.armorset.oldgod.name");
    }

    public void addArmorSetDescription(ItemStack stack, List list) {
        this.addStringToTooltip(StatCollector.translateToLocal((String)"botania.armorset.oldgod.desc0"), list);
        this.addStringToTooltip(StatCollector.translateToLocal((String)"botania.armorset.oldgod.desc1"), list);
        this.addStringToTooltip(StatCollector.translateToLocal((String)"botania.armorset.oldgod.desc2"), list);
    }

    public void onUpdate(ItemStack stack, World p_77663_2_, Entity player, int p_77663_4_, boolean p_77663_5_) {
        if (player instanceof EntityPlayer) {
            ItemOGArmor.updateRelic(stack, (EntityPlayer)player);
        }
    }

    public boolean shouldDamageWrongPlayer() {
        return true;
    }

    public int getEntityLifespan(ItemStack itemStack, World world) {
        return Integer.MAX_VALUE;
    }

    static void addStringToTooltips(String s, List tooltip) {
        tooltip.add(s.replaceAll("&", "\u00a7"));
    }

    public static String getSoulbindUsernameS(ItemStack stack) {
        return ItemNBTHelper.getString((ItemStack)stack, (String)TAG_SOULBIND, (String)"");
    }

    public static void updateRelic(ItemStack stack, EntityPlayer player) {
        if (stack != null && stack.getItem() instanceof IRelic) {
            String soulbind = ItemOGArmor.getSoulbindUsernameS(stack);
            if (soulbind.isEmpty()) {
                player.addStat((StatBase)((IRelic)stack.getItem()).getBindAchievement(), 1);
                ItemOGArmor.bindToPlayer(player, stack);
                soulbind = ItemOGArmor.getSoulbindUsernameS(stack);
            }
            if (ItemOGArmor.isRightPlayer(player, stack) || player.ticksExisted % 10 != 0 || !(stack.getItem() instanceof ItemOGArmor) || ((ItemOGArmor)stack.getItem()).shouldDamageWrongPlayer()) {
                // empty if block
            }
        }
    }

    public static void bindToPlayer(EntityPlayer player, ItemStack stack) {
        ItemOGArmor.bindToUsernameS(player.getCommandSenderName(), stack);
    }

    public static void bindToUsernameS(String username, ItemStack stack) {
        ItemNBTHelper.setString((ItemStack)stack, (String)TAG_SOULBIND, (String)username);
    }

    public static boolean isRightPlayer(EntityPlayer player, ItemStack stack) {
        return ItemOGArmor.isRightPlayer(player.getCommandSenderName(), stack);
    }

    public static boolean isRightPlayer(String player, ItemStack stack) {
        return ItemOGArmor.getSoulbindUsernameS(stack).equals(player);
    }

    public static DamageSource damageSource() {
        return new DamageSource("botania-relic");
    }

    public void bindToUsername(String playerName, ItemStack stack) {
        ItemOGArmor.bindToUsernameS(playerName, stack);
    }

    public String getSoulbindUsername(ItemStack stack) {
        return ItemOGArmor.getSoulbindUsernameS(stack);
    }

    public Achievement getBindAchievement() {
        return this.achievement;
    }

    public void setBindAchievement(Achievement achievement) {
        this.achievement = achievement;
    }

    public EnumRarity getRarity(ItemStack p_77613_1_) {
        return BotaniaAPI.rarityRelic;
    }

    @SideOnly(value=Side.CLIENT)
    public int getColorFromItemStack(ItemStack par1ItemStack, int par2) {
        return Color.HSBtoRGB((float)(Botania.proxy.getWorldElapsedTicks() * 2L % 360L) / 360.0f, 0.25f, 1.0f);
    }

    static {
        material = EnumHelper.addArmorMaterial((String)"EXTRASOULSTEELMATERIAL", (int)30, (int[])new int[]{3, 8, 7, 3}, (int)21);
    }
}

