/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.item.relic.legendary.armor.killer;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.meteor.extrabotany.common.item.ModItems;
import com.meteor.extrabotany.common.item.relic.legendary.armor.ItemOGArmor;
import com.meteor.extrabotany.common.item.relic.legendary.armor.awake.ItemAwakeOGHelm;
import com.meteor.extrabotany.common.world.Asgard.TeleportAsgard;
import cpw.mods.fml.common.FMLCommonHandler;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.StatBase;
import net.minecraft.util.StatCollector;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.common.util.EnumHelper;
import vazkii.botania.api.item.IRelic;
import vazkii.botania.common.core.helper.ItemNBTHelper;

public class ItemKillerArmor
extends ItemOGArmor
implements ISpecialArmor {
    private static final String TAG_SOULBIND = "soulbind";
    Achievement achievement;
    static ItemStack[] armorset;
    List playersWithFlight = new ArrayList();
    public static ItemArmor.ArmorMaterial material;

    public ItemKillerArmor(int type, String name, ItemArmor.ArmorMaterial mat) {
        super(type, name, mat == null ? material : mat);
    }

    @Override
    public String getArmorTextureAfterInk(ItemStack stack, int slot) {
        return "extrabotania:textures/models/armor/killer.png";
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
        multimap.put((Object)SharedMonsterAttributes.movementSpeed.getAttributeUnlocalizedName(), (Object)new AttributeModifier(uuid, "Relic modifier" + this.type, 0.25, 1));
        multimap.put((Object)SharedMonsterAttributes.maxHealth.getAttributeUnlocalizedName(), (Object)new AttributeModifier(uuid, "Relic modifier" + this.type, 30.0, 0));
        multimap.put((Object)SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), (Object)new AttributeModifier(uuid, "Relic modifier" + this.type, 5.0, 0));
        return multimap;
    }

    @Override
    public ItemStack[] getArmorSetStacks() {
        if (armorset == null) {
            armorset = new ItemStack[]{new ItemStack(ModItems.killerhelm), new ItemStack(ModItems.killerchest), new ItemStack(ModItems.killerlegs), new ItemStack(ModItems.killerboots)};
        }
        return armorset;
    }

    private boolean fullTrueArmor(EntityPlayer pl) {
        for (int i = 0; i < 4; ++i) {
            ItemStack a = pl.inventory.armorInventory[i];
            if (a == null) {
                return false;
            }
            if (a == null || a.getItem() instanceof ItemKillerArmor) continue;
            return false;
        }
        return true;
    }

    private String getCount(EntityPlayer pl) {
        int i = 0;
        for (int j = 0; j < 4; ++j) {
            ItemStack k = pl.inventory.armorInventory[j];
            if (k == null || !(k.getItem() instanceof ItemKillerArmor)) continue;
            ++i;
        }
        return " \u00a7f(" + Integer.toString(i) + "/4)";
    }

    public static boolean hasFullArmor(EntityPlayer pl) {
        for (int i = 0; i < 4; ++i) {
            ItemStack s = pl.inventory.armorInventory[i];
            if (s == null || s != null && !(s.getItem() instanceof ItemKillerArmor)) {
                return false;
            }
            if (s == null || !(s.getItem() instanceof ItemKillerArmor) || ItemKillerArmor.isRightPlayer(pl, s)) continue;
            return false;
        }
        return true;
    }

    @Override
    public boolean hasArmorSetItem(EntityPlayer player, int i) {
        ItemStack stack = player.inventory.armorInventory[3 - i];
        if (stack == null) {
            return false;
        }
        switch (i) {
            case 0: {
                return stack.getItem() == ModItems.killerhelm && ItemKillerArmor.isRightPlayer(player, stack);
            }
            case 1: {
                return stack.getItem() == ModItems.killerchest && ItemKillerArmor.isRightPlayer(player, stack);
            }
            case 2: {
                return stack.getItem() == ModItems.killerlegs && ItemKillerArmor.isRightPlayer(player, stack);
            }
            case 3: {
                return stack.getItem() == ModItems.killerboots && ItemKillerArmor.isRightPlayer(player, stack);
            }
        }
        return false;
    }

    public static void updateRelic(ItemStack stack, EntityPlayer player) {
        String soulbind;
        if (stack != null && stack.getItem() instanceof IRelic && (soulbind = ItemKillerArmor.getSoulbindUsernameS(stack)).isEmpty()) {
            player.addStat((StatBase)((IRelic)stack.getItem()).getBindAchievement(), 1);
            ItemKillerArmor.bindToPlayer(player, stack);
            String string = ItemKillerArmor.getSoulbindUsernameS(stack);
        }
    }

    public static void bindToPlayer(EntityPlayer player, ItemStack stack) {
        ItemKillerArmor.bindToUsernameS(player.getCommandSenderName(), stack);
    }

    @Override
    public String getArmorSetName() {
        return StatCollector.translateToLocal((String)"botania.armorset.killer.name");
    }

    @Override
    public void addArmorSetDescription(ItemStack stack, List list) {
        this.addStringToTooltip(StatCollector.translateToLocal((String)"botania.armorset.killer.desc0"), list);
    }

    public static void bindToUsernameS(String s, ItemStack itemStack) {
        ItemNBTHelper.setString((ItemStack)itemStack, (String)"soulbinds", (String)s);
    }

    public static String getSoulbindUsernameS(ItemStack stack) {
        return ItemNBTHelper.getString((ItemStack)stack, (String)"soulbinds", (String)"");
    }

    @Override
    public boolean hasArmorSet(EntityPlayer player) {
        return this.hasArmorSetItem(player, 0) && this.hasArmorSetItem(player, 1) && this.hasArmorSetItem(player, 2) && this.hasArmorSetItem(player, 3);
    }

    public void guiButtonPress(int id, EntityPlayer pl) {
        switch (id) {
            case 0: 
            case 1: 
            case 2: 
            case 3: {
                ItemStack st = pl.inventory.armorInventory[3 - id];
                if (st == null || !(st.getItem() instanceof ItemKillerArmor)) break;
                boolean potion = ItemNBTHelper.getBoolean((ItemStack)st, (String)"potion", (boolean)true);
                ItemNBTHelper.setBoolean((ItemStack)st, (String)"potion", (!potion ? 1 : 0) != 0);
                break;
            }
            case 4: 
            case 5: {
                ItemStack st = pl.inventory.armorInventory[2];
                if (st == null || !(st.getItem() instanceof ItemKillerChest)) break;
                int idCircle = ItemNBTHelper.getInt((ItemStack)st, (String)"render", (int)1);
                idCircle = id == 4 ? (byte)(idCircle < 2 ? 15 : (byte)(idCircle - 1)) : (byte)(idCircle == 15 ? 1 : (byte)(idCircle + 1));
                ItemNBTHelper.setInt((ItemStack)st, (String)"render", (int)idCircle);
                break;
            }
            case 6: {
                ItemNBTHelper.setBoolean((ItemStack)pl.inventory.armorInventory[3], (String)"sMana", (!ItemNBTHelper.getBoolean((ItemStack)pl.inventory.armorInventory[3], (String)"sMana", (boolean)false) ? 1 : 0) != 0);
                if (!ItemNBTHelper.getBoolean((ItemStack)pl.inventory.armorInventory[3], (String)"sVis", (boolean)false)) break;
                ItemNBTHelper.setBoolean((ItemStack)pl.inventory.armorInventory[3], (String)"sVis", (boolean)false);
                break;
            }
            case 7: {
                ItemNBTHelper.setBoolean((ItemStack)pl.inventory.armorInventory[3], (String)"sVis", (!ItemNBTHelper.getBoolean((ItemStack)pl.inventory.armorInventory[3], (String)"sVis", (boolean)false) ? 1 : 0) != 0);
                if (!ItemNBTHelper.getBoolean((ItemStack)pl.inventory.armorInventory[3], (String)"sMana", (boolean)false)) break;
                ItemNBTHelper.setBoolean((ItemStack)pl.inventory.armorInventory[3], (String)"sMana", (boolean)false);
                break;
            }
            case 8: {
                if (!(pl instanceof EntityPlayerMP) || pl.worldObj.provider.dimensionId == 150) break;
                ((EntityPlayerMP)pl).timeUntilPortal = 10;
                MinecraftServer mServer = FMLCommonHandler.instance().getMinecraftServerInstance();
                ((EntityPlayerMP)pl).mcServer.getConfigurationManager().transferPlayerToDimension((EntityPlayerMP)pl, 150, (Teleporter)new TeleportAsgard(mServer.worldServerForDimension(150)));
                break;
            }
            default: {
//                if (id >= 50 && id < 1000) {
//                    ItemAwakeOGArmor.onGuiButtonPress((int)(id - 100), (EntityPlayer)pl);
//                    break;
//                }
//                ItemAwakeOGArmor.onUseSkill((int)(id - 1000), (EntityPlayer)pl);
                System.out.println("Пиши фатоше, #1");
            }
        }
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
        if (this.fullTrueArmor(player) && stack != null && stack.getItem() instanceof ItemKillerHelm) {
            ItemAwakeOGHelm.clearEffect(player);
        }
    }

    static {
        material = EnumHelper.addArmorMaterial((String)"EXTRAELFIRIUM", (int)-1, (int[])new int[]{6, 11, 9, 6}, (int)24);
    }
}

