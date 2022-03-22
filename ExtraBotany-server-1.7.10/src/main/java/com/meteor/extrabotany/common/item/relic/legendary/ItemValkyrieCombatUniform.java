/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.item.relic.legendary;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.meteor.extrabotany.common.entity.EntityItemUnbreakable;
import com.meteor.extrabotany.common.item.equipment.shield.ItemShieldGeneratorBase;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import java.util.List;
import java.util.UUID;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.Achievement;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.item.IRelic;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.item.equipment.armor.manasteel.ItemManasteelArmor;
import vazkii.botania.common.item.relic.ItemRelic;

public class ItemValkyrieCombatUniform
extends ItemManasteelArmor
implements IRelic {
    private static final String TAG_SOULBIND = "soulbind";
    Achievement achievement;
    int count = 0;

    public ItemValkyrieCombatUniform(String name) {
        super(1, name, BotaniaAPI.elementiumArmorMaterial);
        MinecraftForge.EVENT_BUS.register(this);
        FMLCommonHandler.instance().bus().register(this);
    }

    public String getArmorTextureAfterInk(ItemStack stack, int slot) {
        return "extrabotania:textures/models/armor/vcu.png";
    }

    public Multimap getItemAttributeModifiers() {
        HashMultimap multimap = HashMultimap.create();
        UUID uuid = new UUID(this.getUnlocalizedName().hashCode(), 0L);
        multimap.put(SharedMonsterAttributes.knockbackResistance.getAttributeUnlocalizedName(), new AttributeModifier(uuid, "Relic modifier " + this.type, (double)this.getArmorDisplay(null, new ItemStack(this), this.type) / 20.0, 0));
        multimap.put(SharedMonsterAttributes.maxHealth.getAttributeUnlocalizedName(), new AttributeModifier(uuid, "Relic modifier" + this.type, 20.0, 0));
        return multimap;
    }

    public boolean hasCustomEntity(ItemStack stack) {
        return true;
    }

    public Entity createEntity(World world, Entity location, ItemStack itemstack) {
        EntityItemUnbreakable entity = new EntityItemUnbreakable(world, location.posX, location.posY, location.posZ, itemstack);
        if (location instanceof EntityItem) {
            entity.delayBeforeCanPickup = 40;
        }
        entity.motionX = location.motionX;
        entity.motionY = location.motionY;
        entity.motionZ = location.motionZ;
        return entity;
    }

    public void onUpdate(ItemStack stack, World world, Entity player, int par4, boolean par5) {
        if (player instanceof EntityPlayer) {
            ItemValkyrieCombatUniform.updateRelic(stack, (EntityPlayer)player);
        }
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean adv) {
        ItemValkyrieCombatUniform.addBindInfo(list, stack, player);
    }

    public static void addBindInfo(List list, ItemStack stack, EntityPlayer player) {
        if (GuiScreen.isShiftKeyDown()) {
            String bind = ItemValkyrieCombatUniform.getSoulbindUsernameS(stack);
            if (bind.isEmpty()) {
                ItemValkyrieCombatUniform.addStringToTooltips(StatCollector.translateToLocal("botaniamisc.relicUnbound"), list);
            } else {
                ItemValkyrieCombatUniform.addStringToTooltips(String.format(StatCollector.translateToLocal("botaniamisc.relicSoulbound"), bind), list);
                if (!ItemValkyrieCombatUniform.isRightPlayer(player, stack)) {
                    ItemValkyrieCombatUniform.addStringToTooltips(String.format(StatCollector.translateToLocal("botaniamisc.notYourSagittarius"), bind), list);
                }
            }
        } else {
            ItemValkyrieCombatUniform.addStringToTooltips(StatCollector.translateToLocal("botaniamisc.shiftinfo"), list);
        }
    }

    static void addStringToTooltips(String s, List tooltip) {
        tooltip.add(s.replaceAll("&", "\u00a7"));
    }

    public boolean shouldDamageWrongPlayer() {
        return true;
    }

    public int getEntityLifespan(ItemStack itemStack, World world) {
        return Integer.MAX_VALUE;
    }

    public static String getSoulbindUsernameS(ItemStack stack) {
        return ItemNBTHelper.getString(stack, TAG_SOULBIND, "");
    }

    public static void updateRelic(ItemStack stack, EntityPlayer player) {
        String soulbind;
        if (stack != null && stack.getItem() instanceof IRelic && (soulbind = ItemValkyrieCombatUniform.getSoulbindUsernameS(stack)).isEmpty()) {
            player.addStat(((IRelic)stack.getItem()).getBindAchievement(), 1);
            ItemValkyrieCombatUniform.bindToPlayer(player, stack);
            String string = ItemValkyrieCombatUniform.getSoulbindUsernameS(stack);
        }
    }

    public static void bindToPlayer(EntityPlayer player, ItemStack stack) {
        ItemValkyrieCombatUniform.bindToUsernameS(player.getCommandSenderName(), stack);
    }

    public static void bindToUsernameS(String username, ItemStack stack) {
        ItemNBTHelper.setString(stack, TAG_SOULBIND, username);
    }

    public static boolean isRightPlayer(EntityPlayer player, ItemStack stack) {
        return ItemValkyrieCombatUniform.isRightPlayer(player.getCommandSenderName(), stack);
    }

    public static boolean isRightPlayer(String player, ItemStack stack) {
        return ItemValkyrieCombatUniform.getSoulbindUsernameS(stack).equals(player);
    }

    public static DamageSource damageSource() {
        return new DamageSource("botania-relic");
    }

    public void bindToUsername(String playerName, ItemStack stack) {
        ItemValkyrieCombatUniform.bindToUsernameS(playerName, stack);
    }

    public String getSoulbindUsername(ItemStack stack) {
        return ItemValkyrieCombatUniform.getSoulbindUsernameS(stack);
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

    public void onWornTick(ItemStack stack, EntityLivingBase player) {
    }

    public static boolean hasVCU(EntityPlayer player) {
        boolean bool = false;
        for (ItemStack stack : player.inventory.armorInventory) {
            if (stack == null || !(stack.getItem() instanceof ItemValkyrieCombatUniform)) continue;
            bool = ItemRelic.isRightPlayer(player, stack);
        }
        return bool;
    }

    @SubscribeEvent
    public void TickEvent(TickEvent.PlayerTickEvent event) {
        EntityPlayer player = event.player;
        if (ItemValkyrieCombatUniform.hasVCU(player)) {
            List mobs = player.worldObj.getEntitiesWithinAABB(IMob.class, AxisAlignedBB.getBoundingBox(player.posX - 4.0, player.posY - 4.0, player.posZ - 4.0, player.posX + 5.0, player.posY + 5.0, player.posZ + 5.0));
            if (mobs.isEmpty()) {
                ++this.count;
                if (this.count % 30 == 0) {
                    ItemShieldGeneratorBase.recoverShield(player, 4.0f);
                }
            } else if (mobs.size() > 2) {
                player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 15, 1));
            }
        }
    }
}

