/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.ItemSword
 *  net.minecraft.stats.StatBase
 *  net.minecraft.world.World
 */
package net.divinerpg.items.base;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.divinerpg.libs.DivineRPGAchievements;
import net.divinerpg.utils.LangRegistry;
import net.divinerpg.utils.TooltipLocalizer;
import net.divinerpg.utils.items.VanillaItemsWeapons;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.stats.StatBase;
import net.minecraft.world.World;

public class ItemModSword
extends ItemSword {
    protected String name;
    protected String textureName;
    protected Item.ToolMaterial mat;

    public ItemModSword(Item.ToolMaterial toolMaterial, String name) {
        super(toolMaterial);
        this.name = name;
        this.textureName = "divinerpg:" + name;
        this.setUnlocalizedName(name);
        this.setTextureName(this.textureName);
        this.setCreativeTab(DivineRPGTabs.swords);
        GameRegistry.registerItem((Item)this, (String)name);
        LangRegistry.addItem((Item)this);
        this.mat = toolMaterial;
    }

    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (this.canUseSpecialEffect(player)) {
            this.useSpecialEffect(world, player);
        }
        return super.onItemRightClick(stack, world, player);
    }

    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        if (this.canUseSpecialEffect(player)) {
            this.useSpecialEffect(player.worldObj, player);
        }
        return false;
    }

    public boolean isItemTool(ItemStack par1ItemStack) {
        return true;
    }

    public void onCreated(ItemStack stack, World world, EntityPlayer player) {
        if (stack.getItem() == VanillaItemsWeapons.divineSword) {
            player.triggerAchievement((StatBase)DivineRPGAchievements.divinePlan);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void addInformation(ItemStack item, EntityPlayer player, List list, boolean par4) {
        list.add(TooltipLocalizer.meleeDam(this.mat.getDamageVsEntity() + 5.0f));
        this.addAdditionalInformation(list);
        if (item.getMaxDamage() != -1) {
            list.add(TooltipLocalizer.usesRemaining(item.getMaxDamage() - item.getItemDamage()));
        } else {
            list.add(TooltipLocalizer.infiniteUses());
        }
    }

    protected boolean canUseSpecialEffect(EntityPlayer player) {
        return false;
    }

    protected void useSpecialEffect(World world, EntityPlayer player) {
    }

    protected void addAdditionalInformation(List list) {
    }
}

