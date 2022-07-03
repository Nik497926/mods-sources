/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.item.relic.legendary.armor;

import com.meteor.extrabotany.client.gui.Inventarium.LibSkillID;
import com.meteor.extrabotany.common.event.EventSkill;
import com.meteor.extrabotany.common.item.ModItems;
import com.meteor.extrabotany.common.item.relic.legendary.armor.awake.ItemAwakeOGArmor;
import com.meteor.extrabotany.common.item.relic.legendary.armor.killer.ItemKillerArmor;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.items.wands.ItemWandCasting;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.item.relic.ItemRelic;

public class CoreArmor {
    public static final CoreArmor instance = new CoreArmor();

    public void init() {
        MinecraftForge.EVENT_BUS.register((Object)this);
        FMLCommonHandler.instance().bus().register((Object)this);
    }

    public static void chargeWand(EntityPlayer player, ItemStack stack, boolean s) {
        Boolean findedWand = false;
        int tick = ItemNBTHelper.getInt((ItemStack)stack, (String)"tick", (int)0);
        if (tick > 0) {
            --tick;
        } else {
            tick = 20;
            for (int i = 0; i < 9; ++i) {
                ItemStack wand = player.inventory.getStackInSlot(i);
                if (wand == null || !(wand.getItem() instanceof ItemWandCasting) || findedWand.booleanValue()) continue;
                ItemWandCasting wandCasting = (ItemWandCasting)wand.getItem();
                int maxVis = wandCasting.getMaxVis(wand);
                String[] listAspect = new String[]{"aer", "aqua", "terra", "ignis", "ordo", "perditio"};
                for (int j = 0; j < listAspect.length; ++j) {
                    int currentVIS = ItemNBTHelper.getInt((ItemStack)wand, (String)listAspect[j], (int)0);
                    if (currentVIS >= maxVis) continue;
                    int nextVIS = currentVIS + CoreArmor.getCountArmor(player, s);
                    findedWand = true;
                    if (nextVIS > maxVis) {
                        ItemNBTHelper.setInt((ItemStack)wand, (String)listAspect[j], (int)maxVis);
                        continue;
                    }
                    ItemNBTHelper.setInt((ItemStack)wand, (String)listAspect[j], (int)nextVIS);
                }
            }
        }
        ItemNBTHelper.setInt((ItemStack)stack, (String)"tick", (int)tick);
    }

    public static int getCountArmor(EntityPlayer p, boolean s) {
        int r = 0;
        for (int i = 0; i < 4; i = (int)((byte)(i + 1))) {
            if (p.inventory.armorInventory[i] != null && p.inventory.armorInventory[i].getItem() instanceof ItemKillerArmor) {
                r = (short)(r + (s ? 50 : 25));
                continue;
            }
            if (p.inventory.armorInventory[i] == null || !(p.inventory.armorInventory[i].getItem() instanceof ItemAwakeOGArmor)) continue;
            r = (short)(r + 15);
        }
        return r;
    }

    public static boolean getAnother(EntityPlayer p, byte t, byte n) {
        switch (t) {
            case 0: {
                return false;
            }
            case 1: {
                return CoreArmor.getAnotherS(p.inventory.armorInventory[0], n);
            }
            case 2: {
                return CoreArmor.getAnotherS(p.inventory.armorInventory[0], n) && CoreArmor.getAnotherS(p.inventory.armorInventory[1], n);
            }
            case 3: {
                return CoreArmor.getAnotherS(p.inventory.armorInventory[0], n) && CoreArmor.getAnotherS(p.inventory.armorInventory[1], n) && CoreArmor.getAnotherS(p.inventory.armorInventory[2], n);
            }
        }
        return true;
    }

    public static boolean getAnotherS(ItemStack s, byte n) {
        if (n == 0) {
            return s != null && s.getItem() instanceof ItemKillerArmor;
        }
        return s != null && s.getItem() instanceof ItemAwakeOGArmor;
    }

    public static void generateManaAndVis(EntityPlayer pl, ItemStack stack) {
        boolean sv;
        NBTTagCompound n = ItemNBTHelper.getNBT((ItemStack)stack);
        boolean sm = n.hasKey("sMana") && n.getBoolean("sMana");
        boolean bl = sv = n.hasKey("sVis") && n.getBoolean("sVis");
        if (n.hasKey("lastDMG")) {
            return;
        }
        if (sm && sv) {
            sv = false;
            ItemNBTHelper.setBoolean((ItemStack)stack, (String)"sVis", (boolean)false);
        }
        if (sm) {
            ManaItemHandler.dispatchManaExact((ItemStack)stack, (EntityPlayer)pl, (int)CoreArmor.getCountArmor(pl, true), (boolean)true);
        } else if (sv) {
            CoreArmor.chargeWand(pl, stack, true);
        } else if (!sm && !sv) {
            ManaItemHandler.dispatchManaExact((ItemStack)stack, (EntityPlayer)pl, (int)CoreArmor.getCountArmor(pl, false), (boolean)true);
            CoreArmor.chargeWand(pl, stack, false);
        }
    }

    public static int getVisDiscount(ItemStack itemStack, EntityPlayer entityPlayer, Aspect aspect) {
        if (itemStack != null && ItemRelic.isRightPlayer((EntityPlayer)entityPlayer, (ItemStack)itemStack)) {
            switch (ItemNBTHelper.getInt((ItemStack)itemStack, (String)"level", (int)1)) {
                case 2: {
                    return 5;
                }
                case 5: {
                    return 10;
                }
            }
            if (itemStack.getItem() instanceof ItemKillerArmor) {
                return 15;
            }
        }
        return 0;
    }

    public static void checkCD(EntityPlayer pl, ItemStack stack) {
        NBTTagList skills = ItemNBTHelper.getList((ItemStack)stack, (String)"activeSkill", (int)10, (boolean)false);
        NBTTagList _skills = new NBTTagList();
        for (int i = 0; i < skills.tagCount(); ++i) {
            NBTTagCompound _n = skills.getCompoundTagAt(i);
            if (!_n.hasKey("id")) continue;
            int id_skill = _n.getInteger("id");
            if (LibSkillID.hasCD[id_skill] != 0 && _n.hasKey("cd") && EventSkill.checkCD(pl, id_skill)) {
                NBTTagCompound __n = new NBTTagCompound();
                __n.setInteger("id", id_skill);
                if (_n.hasKey("active")) {
                    __n.setBoolean("active", _n.getBoolean("active"));
                } else {
                    __n.setBoolean("active", true);
                }
                _skills.appendTag((NBTBase)__n);
                continue;
            }
            _skills.appendTag((NBTBase)_n);
        }
        ItemStack _out = stack.copy();
        ItemNBTHelper.setList((ItemStack)_out, (String)"activeSkill", (NBTTagList)_skills);
        pl.inventory.armorInventory[1] = _out;
    }

    public static void awakeLevels(ItemStack st, byte type, EntityPlayer pl) {
        Boolean repair = false;
        NBTTagCompound nbt = ItemNBTHelper.getNBT((ItemStack)st);
        if (st.getItemDamage() > 0 && ItemAwakeOGArmor.requestMana(st, pl)) {
            st.setItemDamage(st.getItemDamage() - 1);
            repair = true;
        }
        if (ItemNBTHelper.getInt((ItemStack)st, (String)"level", (int)1) > 1) {
            PotionEffect effect;
            if (type == 3 && !nbt.hasKey("lastDMG")) {
                CoreArmor.addPotion(type, pl, (byte)0);
            } else if (type == 2 && !nbt.hasKey("lastDMG")) {
                CoreArmor.addPotion(type, pl, (byte)0);
            } else if (type == 1) {
                PotionEffect effect2 = pl.getActivePotionEffect(Potion.resistance);
                if (effect2 == null || effect2.getAmplifier() <= 2) {
                    CoreArmor.addPotion(type, pl, (byte)0);
                }
            } else if (type == 0 && ((effect = pl.getActivePotionEffect(Potion.nightVision)) == null || effect.getDuration() < 301)) {
                CoreArmor.addPotion(type, pl, (byte)0);
            }
        }
        if (ItemNBTHelper.getInt((ItemStack)st, (String)"level", (int)1) > 3 && !repair.booleanValue() && !nbt.hasKey("lastDMG")) {
            ManaItemHandler.dispatchManaExact((ItemStack)st, (EntityPlayer)pl, (int)CoreArmor.getMana(st), (boolean)true);
        }
        if (ItemNBTHelper.getInt((ItemStack)st, (String)"level", (int)1) > 4 && !CoreArmor.getAnother(pl, type, (byte)1) && !nbt.hasKey("lastDMG")) {
            CoreArmor.chargeWand(pl, st, false);
        }
    }

    public static Integer getMana(ItemStack stack) {
        if (stack.getDisplayName().contains("damned")) {
            return 20;
        }
        return 5;
    }

    public static void addPotion(byte type, EntityPlayer pl, byte s) {
        int amplifier = type == 0 ? Potion.nightVision.id : (type == 1 ? Potion.resistance.id : (type == 2 ? Potion.regeneration.id : 23));
        int ids = type == 0 ? 0 : (type == 1 ? 1 : (amplifier = type == 2 ? 0 : 2));

        if (s == 1) {
            ++amplifier;
        }

        int duration = type == 0 ? 401 : 40;
        pl.addPotionEffect(new PotionEffect(ids, duration, amplifier, true));
    }

    @SubscribeEvent(priority=EventPriority.LOWEST)
    public void DamageEntity(LivingHurtEvent event) {
        if (event.entity instanceof EntityPlayer && !event.entity.worldObj.isRemote) {
            EntityPlayer pl = (EntityPlayer)event.entity;
            if (event.source.getEntity() instanceof EntityPlayer || event.source.getSourceOfDamage() instanceof EntityPlayer) {
                for (byte i = 3; i >= 0; i = (byte)((byte)(i - 1))) {
                    if (pl.inventory.armorInventory[i] == null || !(pl.inventory.armorInventory[i].getItem() instanceof ItemKillerArmor) && !(pl.inventory.armorInventory[i].getItem() instanceof ItemAwakeOGArmor) || !ItemKillerArmor.isRightPlayer(pl, pl.inventory.armorInventory[i])) continue;
                    this.installLastDMG(pl, pl.inventory.armorInventory[i], !CoreArmor.getAnother(pl, i, (byte)0) && !CoreArmor.getAnother(pl, i, (byte)1));
                }
            }
            if (ItemKillerArmor.hasFullArmor(pl)) {
                int needMana = (int)event.ammount * 1000;
                event.ammount = ManaItemHandler.requestManaExact((ItemStack)new ItemStack(ModItems.gaiatablet), (EntityPlayer)pl, (int)needMana, (boolean)true) ? 0.0f : (event.ammount *= 0.3f);
            }
        }
    }

    void installLastDMG(EntityPlayer p, ItemStack s, boolean push) {
        NBTTagCompound n = ItemNBTHelper.getNBT((ItemStack)s);
        if (!n.hasKey("lastDMG")) {
            ItemNBTHelper.setLong((ItemStack)s, (String)"lastDMG", (long)System.currentTimeMillis());
        }
    }
}

