/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.item.relic.legendary.killerTool;

import com.meteor.extrabotany.ExtraBotany;
import com.meteor.extrabotany.common.entity.gaia.EntityGaiaIII;
import com.meteor.extrabotany.common.item.equipment.CoreTool;
import com.meteor.extrabotany.common.item.relic.legendary.armor.killer.ItemKillerArmor;
import com.meteor.extrabotany.common.item.relic.legendary.killerTool.ItemKillerAxe;
import com.meteor.extrabotany.common.item.relic.legendary.killerTool.ItemKillerSword;
import cpw.mods.fml.common.registry.GameRegistry;
import java.awt.Color;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.api.item.IRelic;
import vazkii.botania.api.mana.BurstProperties;
import vazkii.botania.api.mana.ILensEffect;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.entity.EntityManaBurst;

public class ItemKillerPick
extends ItemPickaxe
implements IRelic,
ILensEffect,
IManaUsingItem {
    IIcon[] icons;
    String name = "itemKillerPick";

    public ItemKillerPick() {
        super(ItemKillerAxe.material);
        this.setUnlocalizedName(this.name).setCreativeTab(ExtraBotany.tabExtraBotany).setMaxStackSize(1);
        this.setHarvestLevel("pickaxe", 50);
        GameRegistry.registerItem(this, this.name);
    }

    public void registerIcons(IIconRegister iconRegister) {
        this.icons = new IIcon[3];
        this.icons[0] = iconRegister.registerIcon("extrabotania:" + this.name);
        this.itemIcon = this.icons[0];
        this.icons[1] = iconRegister.registerIcon("extrabotania:" + this.name + "0");
        this.icons[2] = iconRegister.registerIcon("extrabotania:" + this.name + "1");
    }

    public IIcon getIcon(ItemStack stack, int pass) {
        int type = ItemNBTHelper.getInt(stack, "type", 0);
        switch (type) {
            case 0: {
                this.itemIcon = this.icons[0];
                break;
            }
            case 1: {
                this.itemIcon = this.icons[1];
                break;
            }
            default: {
                this.itemIcon = this.icons[2];
            }
        }
        return type == 0 ? this.icons[0] : (type == 1 ? this.icons[1] : this.icons[2]);
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean isShift) {
        ItemKillerSword.addBindInfo(list, stack, player);
    }

    public EntityManaBurst getBurst(EntityPlayer player, ItemStack stack) {
        EntityManaBurst burst = new EntityManaBurst(player);
        float motionModifier = 25.0f;
        burst.setColor(new Color(0xFF0000).getRGB());
        burst.setMana(1);
        burst.setStartingMana(1);
        burst.setMinManaLoss(100);
        burst.setManaLossPerTick(1.0f);
        burst.setGravity(0.0f);
        burst.setMotion(burst.motionX * (double)motionModifier, burst.motionY * (double)motionModifier, burst.motionZ * (double)motionModifier);
        ItemStack lens = stack.copy();
        ItemNBTHelper.setString(lens, "attackerUsername", player.getCommandSenderName());
        burst.setSourceLens(lens);
        return burst;
    }

    public void bindToUsername(String s, ItemStack itemStack) {
        ItemNBTHelper.setString(itemStack, "soulbinds", s);
    }

    public String getSoulbindUsername(ItemStack itemStack) {
        return ItemNBTHelper.getString(itemStack, "soulbinds", "");
    }

    public void setBindAchievement(Achievement achievement) {
    }

    public Achievement getBindAchievement() {
        return null;
    }

    public void apply(ItemStack itemStack, BurstProperties burstProperties) {
    }

    public boolean collideBurst(IManaBurst burst, MovingObjectPosition pos, boolean isManaBlock, boolean dead, ItemStack stack) {
        return CoreTool.collideBurst(burst, pos, isManaBlock, dead, stack, 2);
    }

    public void updateBurst(IManaBurst burst, ItemStack itemStack) {
    }

    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (!world.isRemote && EntityGaiaIII.isTruePlayer(player) && ItemKillerArmor.isRightPlayer(player, stack) && player.isSneaking()) {
            int type = ItemNBTHelper.getInt(stack, "type", 0);
            switch (type) {
                case 0: {
                    ItemNBTHelper.setInt(stack, "type", 1);
                    player.addChatComponentMessage(new ChatComponentText(StatCollector.translateToLocal("misc.killerTool.mod1")));
                    break;
                }
                case 1: {
                    ItemNBTHelper.setInt(stack, "type", 2);
                    player.addChatComponentMessage(new ChatComponentText(StatCollector.translateToLocal("misc.killerTool.mod2")));
                    break;
                }
                default: {
                    ItemNBTHelper.setInt(stack, "type", 0);
                    player.addChatComponentMessage(new ChatComponentText(StatCollector.translateToLocal("misc.killerTool.mod0")));
                }
            }
        }
        return super.onItemRightClick(stack, world, player);
    }

    public boolean doParticles(IManaBurst iManaBurst, ItemStack itemStack) {
        return true;
    }

    public boolean usesMana(ItemStack itemStack) {
        return false;
    }

    public boolean hasEffect(ItemStack par1ItemStack, int pass) {
        return true;
    }
}

