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
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
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

public class ItemKillerArmor extends ItemOGArmor implements ISpecialArmor {
    private static final String TAG_SOULBIND = "soulbind";
    Achievement achievement;
    static ItemStack[] armorset;
    protected ModelBiped[] models = null;
    List playersWithFlight = new ArrayList();
    public static ItemArmor.ArmorMaterial material;

    public ItemKillerArmor(int type, String name, ItemArmor.ArmorMaterial mat) {
        super(type, name, mat == null ? material : mat);
    }

    @Override
    public String getArmorTextureAfterInk(ItemStack stack, int slot) {
        return "extrabotania:textures/models/armor/killer.png";
    }

    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot) {
        ModelBiped model = this.getArmorModelForSlot(entityLiving, itemStack, armorSlot);
        if (model == null) {
            model = this.provideArmorModelForSlot(itemStack, armorSlot);
        }
        if (model != null) {
            return model;
        }
        return super.getArmorModel(entityLiving, itemStack, armorSlot);
    }

    public ModelBiped getArmorModelForSlot(EntityLivingBase entity, ItemStack stack, int slot) {
        if (this.models == null) {
            this.models = new ModelBiped[4];
        }
        return this.models[slot];
    }

    private static Boolean getArmorSeven(ItemStack stack, int lvl) {
        int var1 = ItemNBTHelper.getInt(stack, "level", 1);
        return var1 >= lvl;
    }

    @Override
    public Multimap getItemAttributeModifiers() {
        HashMultimap multimap = HashMultimap.create();
        UUID uuid = new UUID(this.getUnlocalizedName().hashCode(), 0L);
        multimap.put(SharedMonsterAttributes.knockbackResistance.getAttributeUnlocalizedName(), new AttributeModifier(uuid, "Relic modifier " + this.type, 0.25, 0));
        multimap.put(SharedMonsterAttributes.movementSpeed.getAttributeUnlocalizedName(), new AttributeModifier(uuid, "Relic modifier" + this.type, 0.25, 1));
        multimap.put(SharedMonsterAttributes.maxHealth.getAttributeUnlocalizedName(), new AttributeModifier(uuid, "Relic modifier" + this.type, 30.0, 0));
        multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(uuid, "Relic modifier" + this.type, 5.0, 0));
        return multimap;
    }

    public void registerIcons(IIconRegister par1IconRegister) {
        switch (this.armorType) {
            case 0: {
                this.itemIcon = par1IconRegister.registerIcon("extrabotania:killerhelm");
                break;
            }
            case 1: {
                this.itemIcon = par1IconRegister.registerIcon("extrabotania:killerchest");
                break;
            }
            case 2: {
                this.itemIcon = par1IconRegister.registerIcon("extrabotania:killerlegs");
                break;
            }
            case 3: {
                this.itemIcon = par1IconRegister.registerIcon("extrabotania:killerboots");
            }
        }
    }

    @Override
    public ItemStack[] getArmorSetStacks() {
        if (armorset == null) {
            armorset = new ItemStack[]{new ItemStack(ModItems.killerhelm), new ItemStack(ModItems.killerchest), new ItemStack(ModItems.killerlegs), new ItemStack(ModItems.killerboots)};
        }
        return armorset;
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean adv) {
        list.add("\u00a7bArmor Set: " + StatCollector.translateToLocal("botania.armorset.killer.name"));
        list.add(StatCollector.translateToLocal("botania.armorset.killer.desc0"));
        if (!GuiScreen.isCtrlKeyDown()) {
            list.add(StatCollector.translateToLocal("botania.armorset.killer.desc1") + this.getCount(player));
        } else {
            list.add(" \u00a7f- " + StatCollector.translateToLocal("item.botania:killerhelm.name"));
            list.add(" \u00a7f- " + StatCollector.translateToLocal("item.botania:killerchest.name"));
            list.add(" \u00a7f- " + StatCollector.translateToLocal("item.botania:killerlegs.name"));
            list.add(" \u00a7f- " + StatCollector.translateToLocal("item.botania:killerboots.name"));
        }
        ItemKillerArmor.addBindInfo(list, stack, player);
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
        return " \u00a7f(" + i + "/4)";
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
            player.addStat(((IRelic)stack.getItem()).getBindAchievement(), 1);
            ItemKillerArmor.bindToPlayer(player, stack);
            String string = ItemKillerArmor.getSoulbindUsernameS(stack);
        }
    }

    public static void bindToPlayer(EntityPlayer player, ItemStack stack) {
        ItemKillerArmor.bindToUsernameS(player.getCommandSenderName(), stack);
    }

    @Override
    public String getArmorSetName() {
        return StatCollector.translateToLocal("botania.armorset.killer.name");
    }

    @Override
    public void addArmorSetDescription(ItemStack stack, List list) {
        this.addStringToTooltip(StatCollector.translateToLocal("botania.armorset.killer.desc0"), list);
    }

    public static void bindToUsernameS(String s, ItemStack itemStack) {
        ItemNBTHelper.setString(itemStack, "soulbinds", s);
    }

    public static String getSoulbindUsernameS(ItemStack stack) {
        return ItemNBTHelper.getString(stack, "soulbinds", "");
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
                boolean potion = ItemNBTHelper.getBoolean(st, "potion", true);
                ItemNBTHelper.setBoolean(st, "potion", (!potion ? 1 : 0) != 0);
                break;
            }
            case 4: 
            case 5: {
                ItemStack st = pl.inventory.armorInventory[2];
                if (st == null || !(st.getItem() instanceof ItemKillerChest)) break;
                int idCircle = ItemNBTHelper.getInt(st, "render", 1);
                idCircle = id == 4 ? idCircle < 2 ? 15 : (byte)(idCircle - 1) : idCircle == 15 ? 1 : (byte)(idCircle + 1);
                ItemNBTHelper.setInt(st, "render", idCircle);
                break;
            }
            case 6: {
                ItemNBTHelper.setBoolean(pl.inventory.armorInventory[3], "sMana", (!ItemNBTHelper.getBoolean(pl.inventory.armorInventory[3], "sMana", false) ? 1 : 0) != 0);
                if (!ItemNBTHelper.getBoolean(pl.inventory.armorInventory[3], "sVis", false)) break;
                ItemNBTHelper.setBoolean(pl.inventory.armorInventory[3], "sVis", false);
                break;
            }
            case 7: {
                ItemNBTHelper.setBoolean(pl.inventory.armorInventory[3], "sVis", (!ItemNBTHelper.getBoolean(pl.inventory.armorInventory[3], "sVis", false) ? 1 : 0) != 0);
                if (!ItemNBTHelper.getBoolean(pl.inventory.armorInventory[3], "sMana", false)) break;
                ItemNBTHelper.setBoolean(pl.inventory.armorInventory[3], "sMana", false);
                break;
            }
            case 8: {
                if (!(pl instanceof EntityPlayerMP) || pl.worldObj.provider.dimensionId == 150) break;
                pl.timeUntilPortal = 10;
                MinecraftServer mServer = FMLCommonHandler.instance().getMinecraftServerInstance();
                ((EntityPlayerMP)pl).mcServer.getConfigurationManager().transferPlayerToDimension((EntityPlayerMP)pl, 150, new TeleportAsgard(mServer.worldServerForDimension(150)));
                break;
            }
            default: {

            }
        }
    }

    public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
        super.onArmorTick(world, player, stack);
        NBTTagCompound n = ItemNBTHelper.getNBT(stack);
        if (!world.isRemote && n.hasKey("lastDMG")) {
            Long cd = n.getLong("lastDMG");
            if (System.currentTimeMillis() >= cd + 180000L) {
                n.removeTag("lastDMG");
                ItemNBTHelper.injectNBT(stack, n);
            }
        }
        if (this.fullTrueArmor(player) && stack != null && stack.getItem() instanceof ItemKillerHelm) {
            ItemAwakeOGHelm.clearEffect(player);
        }
    }

    static {
        material = EnumHelper.addArmorMaterial("EXTRAELFIRIUM", -1, new int[]{6, 11, 9, 6}, 24);
    }
}

