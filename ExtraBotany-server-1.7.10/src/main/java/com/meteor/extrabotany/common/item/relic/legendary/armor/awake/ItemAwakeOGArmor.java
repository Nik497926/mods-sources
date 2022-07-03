/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.item.relic.legendary.armor.awake;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.meteor.extrabotany.client.model.ModelRelicAwakeArmor;
import com.meteor.extrabotany.common.event.EventSkill;
import com.meteor.extrabotany.common.item.ModItems;
import com.meteor.extrabotany.common.item.relic.legendary.armor.ItemOGArmor;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.StatBase;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import thaumcraft.api.IVisDiscountGear;
import thaumcraft.api.aspects.Aspect;
import vazkii.botania.api.item.IRelic;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.core.helper.ItemNBTHelper;

public class ItemAwakeOGArmor
extends ItemOGArmor
implements IVisDiscountGear {
    private static final String TAG_SOULBIND = "soulbind";
    Achievement achievement;
    static ItemStack[] armorset;
    List playersWithFlight = new ArrayList();
    private static final int[] gaia;
    private static final int[] exmachina;
    private static final int[] asgard;
    public static ItemArmor.ArmorMaterial material;

    public ItemAwakeOGArmor(int type, String name, ItemArmor.ArmorMaterial mat) {
        super(type, name, mat == null ? material : mat);
    }

    @Override
    public String getArmorTextureAfterInk(ItemStack stack, int slot) {
        if (stack != null && stack.getItem() instanceof ItemAwakeOGArmor) {
            int lvl = ItemNBTHelper.getInt((ItemStack)stack, (String)"level", (int)1);
            if (lvl < 3) {
                return "extrabotania:textures/models/armor/awakearm0.png";
            }
            if (lvl < 5) {
                return "extrabotania:textures/models/armor/awakearm1.png";
            }
            if (lvl < 7) {
                return "extrabotania:textures/models/armor/awakearm2.png";
            }
        }
        return "extrabotania:textures/models/armor/awakearm.png";
    }

    public static boolean requestMana(ItemStack st, EntityPlayer pl) {
        if (EventSkill.fullArm8lvl(pl) && EventSkill.isActiveSkill(20, pl)) {
            st.setItemDamage(0);
            return false;
        }
        return ManaItemHandler.requestManaExact((ItemStack)st, (EntityPlayer)pl, (int)600, (boolean)true);
    }

    public static void addStat(EntityPlayer pl, int tp) {
        for (int i = 0; i < 4; ++i) {
            ItemStack it = pl.inventory.armorInventory[i];
            if (it == null || !(it.getItem() instanceof ItemAwakeOGArmor)) continue;
            int g = ItemNBTHelper.getInt((ItemStack)it, (String)"gaia", (int)0) + (tp == 0 ? 1 : 0);
            int e = ItemNBTHelper.getInt((ItemStack)it, (String)"exmachina", (int)0) + (tp == 1 ? 1 : 0);
            int a = ItemNBTHelper.getInt((ItemStack)it, (String)"asgard", (int)0) + (tp == 2 ? 1 : 0);
            int lvl = ItemNBTHelper.getInt((ItemStack)it, (String)"level", (int)1);
            if (lvl < 7 && g >= gaia[lvl - 1] && e >= exmachina[lvl - 1] && a >= asgard[lvl - 1]) {
                g -= gaia[lvl - 1];
                e -= exmachina[lvl - 1];
                a -= asgard[lvl - 1];
                pl.addChatComponentMessage((IChatComponent)new ChatComponentText("\u00a7f[\u00a76ExtraBotania\u00a7f]: \u0412\u0430\u0448 " + it.getDisplayName() + " \u00a7f\u0443\u0441\u043f\u0435\u0448\u043d\u043e \u043f\u0440\u043e\u0431\u0443\u0436\u0434\u0435\u043d \u0434\u043e \u0441\u043b\u0435\u0434. \u0443\u0440\u043e\u0432\u043d\u044f!"));
                ItemNBTHelper.setInt((ItemStack)it, (String)"level", (int)(lvl + 1));
            }
            ItemNBTHelper.setInt((ItemStack)it, (String)"gaia", (int)g);
            ItemNBTHelper.setInt((ItemStack)it, (String)"exmachina", (int)e);
            ItemNBTHelper.setInt((ItemStack)it, (String)"asgard", (int)a);
        }
    }

    public static Boolean getFullArmorSeven(EntityPlayer player, int lvl) {
        if (player != null) {
            for (int i = 0; i < 4; ++i) {
                ItemStack stack = player.inventory.armorInventory[i];
                if (stack == null || !ItemAwakeOGArmor.isRightPlayer(player, stack)) {
                    return false;
                }
                if (ItemAwakeOGArmor.getArmorSeven(stack, lvl).booleanValue()) continue;
                return false;
            }
            return true;
        }
        return false;
    }

    private static Boolean getArmorSeven(ItemStack stack, int lvl) {
        int var1 = ItemNBTHelper.getInt((ItemStack)stack, (String)"level", (int)1);
        if (var1 >= lvl) {
            return true;
        }
        return false;
    }

    @Override
    public Multimap getItemAttributeModifiers() {
        HashMultimap multimap = HashMultimap.create();
        UUID uuid = new UUID(this.getUnlocalizedName().hashCode(), 0L);
        multimap.put((Object)SharedMonsterAttributes.knockbackResistance.getAttributeUnlocalizedName(), (Object)new AttributeModifier(uuid, "Relic modifier " + this.type, 0.25, 0));
        multimap.put((Object)SharedMonsterAttributes.movementSpeed.getAttributeUnlocalizedName(), (Object)new AttributeModifier(uuid, "Relic modifier" + this.type, 0.15, 1));
        multimap.put((Object)SharedMonsterAttributes.maxHealth.getAttributeUnlocalizedName(), (Object)new AttributeModifier(uuid, "Relic modifier" + this.type, 15.0, 0));
        return multimap;
    }

    public static ArrayList<Integer> checkChange(EntityPlayer pl) {
        ItemStack st = pl.inventory.armorInventory[1];
        NBTTagList list = ItemNBTHelper.getList((ItemStack)st, (String)"activeSkill", (int)10, (boolean)false);
        ArrayList<Integer> ids = new ArrayList<Integer>();
        for (int i = 0; i < list.tagCount(); ++i) {
            NBTTagCompound n = list.getCompoundTagAt(i);
            if (!n.hasKey("active") || !n.hasKey("id") || !n.getBoolean("active")) continue;
            ids.add(n.getInteger("id"));
        }
        return ids;
    }

    @Override
    public ItemStack[] getArmorSetStacks() {
        if (armorset == null) {
            armorset = new ItemStack[]{new ItemStack(ModItems.awakeoghelm), new ItemStack(ModItems.awakeogchest), new ItemStack(ModItems.awakeoglegs), new ItemStack(ModItems.awakeogboots)};
        }
        return armorset;
    }

    static void addStringToTooltips(String s, List tooltip) {
        tooltip.add(s.replaceAll("&", "\u00a7"));
    }

    private String getEffect(ItemStack stack) {
        String ret = "";
        Item var2 = stack.getItem();
        if (var2 instanceof ItemAwakeOGHelm) {
            ret = " " + StatCollector.translateToLocal((String)"armor.misc.potion.night");
        }
        if (var2 instanceof ItemAwakeOGChest) {
            ret = " " + StatCollector.translateToLocal((String)"armor.misc.potion.resistance");
        }
        if (var2 instanceof ItemAwakeOGLegs) {
            ret = " " + StatCollector.translateToLocal((String)"armor.misc.potion.heal");
        }
        if (var2 instanceof ItemAwakeOGBoots) {
            ret = " " + StatCollector.translateToLocal((String)"armor.misc.potion.eat");
        }
        return ret;
    }

    public static int getLevel(ItemStack stack) {
        return ItemNBTHelper.getInt((ItemStack)stack, (String)"level", (int)1);
    }

    @Override
    public boolean hasArmorSetItem(EntityPlayer player, int i) {
        ItemStack stack = player.inventory.armorInventory[3 - i];
        if (stack == null) {
            return false;
        }
        switch (i) {
            case 0: {
                return stack.getItem() == ModItems.awakeoghelm && ItemAwakeOGArmor.isRightPlayer(player, stack);
            }
            case 1: {
                return stack.getItem() == ModItems.awakeogchest && ItemAwakeOGArmor.isRightPlayer(player, stack);
            }
            case 2: {
                return stack.getItem() == ModItems.awakeoglegs && ItemAwakeOGArmor.isRightPlayer(player, stack);
            }
            case 3: {
                return stack.getItem() == ModItems.awakeogboots && ItemAwakeOGArmor.isRightPlayer(player, stack);
            }
        }
        return false;
    }

    public static void updateRelic(ItemStack stack, EntityPlayer player) {
        String soulbind;
        if (stack != null && stack.getItem() instanceof IRelic && (soulbind = ItemAwakeOGArmor.getSoulbindUsernameS(stack)).isEmpty()) {
            player.addStat((StatBase)((IRelic)stack.getItem()).getBindAchievement(), 1);
            ItemAwakeOGArmor.bindToPlayer(player, stack);
            String string = ItemAwakeOGArmor.getSoulbindUsernameS(stack);
        }
    }

    public static void bindToPlayer(EntityPlayer player, ItemStack stack) {
        ItemAwakeOGArmor.bindToUsernameS(player.getCommandSenderName(), stack);
    }

    @Override
    public String getArmorSetName() {
        return StatCollector.translateToLocal((String)"botania.armorset.awakeoldgod.name");
    }

    @Override
    public void addArmorSetDescription(ItemStack stack, List list) {
        this.addStringToTooltip(StatCollector.translateToLocal((String)"botania.armorset.awakeoldgod.desc0"), list);
    }

    public int getVisDiscount(ItemStack itemStack, EntityPlayer entityPlayer, Aspect aspect) {
        int ret = 0;
        if (itemStack != null && ItemAwakeOGArmor.isRightPlayer(entityPlayer, itemStack)) {
            int var1 = ItemNBTHelper.getInt((ItemStack)itemStack, (String)"level", (int)1);
            if (var1 > 2) {
                ret = 5;
            }
            if (var1 > 5) {
                ret = 10;
            }
        }
        return ret;
    }

    public static void bindToUsernameS(String s, ItemStack itemStack) {
        ItemNBTHelper.setString((ItemStack)itemStack, (String)TAG_SOULBIND, (String)s);
    }

    public static String getSoulbindUsernameS(ItemStack stack) {
        return ItemNBTHelper.getString((ItemStack)stack, (String)TAG_SOULBIND, (String)"");
    }

    @Override
    public boolean hasArmorSet(EntityPlayer player) {
        return this.hasArmorSetItem(player, 0) && this.hasArmorSetItem(player, 1) && this.hasArmorSetItem(player, 2) && this.hasArmorSetItem(player, 3);
    }

    public static void instantDeath(EntityLivingBase entity, EntityLivingBase attacker) {
        if (entity != null && entity.worldObj != null && !entity.worldObj.isRemote && entity instanceof EntityLiving) {
            entity.setHealth(0.0f);
            if (attacker == null) {
                entity.onDeath(DamageSource.magic);
            } else {
                entity.onDeath((DamageSource)new EntityDamageSource(DamageSource.magic.getDamageType(), (Entity)attacker));
            }
            entity.setDead();
        }
    }

    public static boolean hasFullArmor(EntityPlayer pl) {
        for (int i = 0; i < 4; ++i) {
            ItemStack s = pl.inventory.armorInventory[i];
            if (s == null || s != null && !(s.getItem() instanceof ItemAwakeOGArmor)) {
                return false;
            }
            if (s == null || !(s.getItem() instanceof ItemAwakeOGArmor) || ItemAwakeOGArmor.isRightPlayer(pl, s)) continue;
            return false;
        }
        return true;
    }

    public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
        super.onArmorTick(world, player, stack);
        NBTTagCompound n = ItemNBTHelper.getNBT((ItemStack)stack);
        if (!world.isRemote && n.hasKey("lastDMG")) {
            Long cd = n.getLong("lastDMG");
            if (System.currentTimeMillis() >= cd + 180000L) {
                n.removeTag("lastDMG");
                ItemNBTHelper.injectNBT((ItemStack)stack, (NBTTagCompound)n);
            }
        }
    }

    static {
        gaia = new int[]{8, 16, 32, 64, 128, 128};
        exmachina = new int[]{0, 1, 2, 8, 16, 48};
        asgard = new int[]{0, 4, 8, 32, 64, 256};
        material = EnumHelper.addArmorMaterial((String)"EXTRAELFIRIUM", (int)38, (int[])new int[]{4, 9, 8, 4}, (int)24);
    }
}

