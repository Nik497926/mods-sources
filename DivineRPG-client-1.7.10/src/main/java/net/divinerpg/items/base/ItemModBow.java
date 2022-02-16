/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Event
 *  cpw.mods.fml.common.registry.GameRegistry
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBow
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.World
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.entity.player.ArrowLooseEvent
 *  net.minecraftforge.event.entity.player.ArrowNockEvent
 */
package net.divinerpg.items.base;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import net.divinerpg.entities.base.EntityDivineArrow;
import net.divinerpg.utils.LangRegistry;
import net.divinerpg.utils.TooltipLocalizer;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;

public class ItemModBow
extends ItemBow {
    public static List<Item> bowList = new ArrayList<Item>();
    public static final int DEFAULT_MAX_USE_DURATION = 72000;
    @SideOnly(value=Side.CLIENT)
    protected IIcon[] IIconArray;
    protected String name;
    protected String textureName;
    protected final String[] texture;
    protected int damageMin;
    protected int damageMax;
    protected int maxUseDuration;
    protected boolean unbreakable;
    protected Item arrow;
    protected boolean needArrow = true;
    protected String arrowTex;
    private String sound = "random.bow";
    private boolean vethean = false;

    public ItemModBow(String name, int uses, int damageMin, int damageMax, Item arrow) {
        this(name, uses, damageMin, damageMax, 72000, arrow);
    }

    public ItemModBow(String name, int uses, int damageMin, int damageMax, String arrowTex) {
        this(name, uses, damageMin, damageMax, 72000, null);
        this.arrowTex = arrowTex;
    }

    public ItemModBow(String name, int uses, int damageMin, int damageMax, Item arrow, String arrowTex) {
        this(name, uses, damageMin, damageMax, 72000, arrow);
        this.arrowTex = arrowTex;
    }

    public ItemModBow(String name, int uses, int damageMin, int damageMax, int maxUseDuraction) {
        this(name, uses, damageMin, damageMax, maxUseDuraction, null);
    }

    public ItemModBow(String name, int uses, int damageMin, int damageMax, int maxUseDuraction, Item arrow, String arrowTex) {
        this(name, uses, damageMin, damageMax, maxUseDuraction, arrow);
        this.arrowTex = arrowTex;
    }

    public ItemModBow(String name, int uses, int damageMin, int damageMax, int maxUseDuraction, Item arrow) {
        this.setMaxDamage(uses);
        this.name = name;
        this.textureName = "divinerpg:" + name;
        if (arrow == null) {
            this.needArrow = false;
        } else {
            this.arrow = arrow;
        }
        this.damageMin = damageMin;
        this.damageMax = damageMax;
        this.maxUseDuration = maxUseDuraction;
        this.unbreakable = true;
        this.maxStackSize = 1;
        this.setCreativeTab(DivineRPGTabs.ranged);
        this.texture = new String[]{this.textureName + "_0", this.textureName + "_1", this.textureName + "_2", this.textureName + "_3"};
        this.setUnlocalizedName(name);
        GameRegistry.registerItem((Item)this, (String)name);
        LangRegistry.addItem((Item)this);
        bowList.add((Item)this);
    }

    @SideOnly(value=Side.CLIENT)
    public void registerIcons(IIconRegister icon) {
        this.itemIcon = icon.registerIcon(this.texture[0]);
        this.IIconArray = new IIcon[this.texture.length];
        for (int i = 0; i < this.texture.length; ++i) {
            this.IIconArray[i] = icon.registerIcon(this.texture[i]);
        }
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getItemIconForUseDuration(int icon) {
        return this.IIconArray[icon];
    }

    public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) {
        if (player.getItemInUse() == null) {
            return this.itemIcon;
        }
        int pulling = stack.getMaxItemUseDuration() - useRemaining;
        if (pulling >= 18 / (72000 / this.getMaxItemUseDuration(stack))) {
            return this.IIconArray[3];
        }
        if (pulling > 13 / (72000 / this.getMaxItemUseDuration(stack))) {
            return this.IIconArray[2];
        }
        if (pulling > 0) {
            return this.IIconArray[1];
        }
        return this.IIconArray[0];
    }

    public void onPlayerStoppedUsing(ItemStack stack, World world, EntityPlayer player, int itemUseCount) {
        boolean infiniteAmmo;
        int maxItemUse = this.getMaxItemUseDuration(stack) - itemUseCount;
        ArrowLooseEvent event = new ArrowLooseEvent(player, stack, maxItemUse);
        MinecraftForge.EVENT_BUS.post((Event)event);
        if (event.isCanceled()) {
            return;
        }
        maxItemUse = event.charge;
        boolean bl = infiniteAmmo = !this.needArrow || player.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel((int)Enchantment.infinity.effectId, (ItemStack)stack) > 0;
        if (infiniteAmmo || player.inventory.hasItem(this.arrow)) {
            int punchLevel;
            int powerLevel;
            float timeRatio = 72000 / this.getMaxItemUseDuration(stack);
            float scaledItemUse = (float)maxItemUse / 20.0f * timeRatio;
            if ((double)(scaledItemUse = (scaledItemUse * scaledItemUse + scaledItemUse * 2.0f) / 3.0f) < 0.1) {
                return;
            }
            if (scaledItemUse > 1.0f) {
                scaledItemUse = 1.0f;
            }
            EntityDivineArrow entityarrow = this.arrow != null && this.arrowTex == null ? new EntityDivineArrow(world, (EntityLivingBase)player, scaledItemUse * 2.0f, this.damageMin, this.damageMax, this.arrow.getUnlocalizedName().replace("item.", "")) : new EntityDivineArrow(world, (EntityLivingBase)player, scaledItemUse * 2.0f, this.damageMin, this.damageMax, this.arrowTex);
            entityarrow.setAmmoItem(this.arrow);
            if (scaledItemUse == 1.0f) {
                entityarrow.setIsCritical(true);
            }
            if ((powerLevel = EnchantmentHelper.getEnchantmentLevel((int)Enchantment.power.effectId, (ItemStack)stack)) > 0) {
                entityarrow.setDamage(entityarrow.getDamage() + (double)powerLevel * 0.5 + 0.5);
            }
            if ((punchLevel = EnchantmentHelper.getEnchantmentLevel((int)Enchantment.punch.effectId, (ItemStack)stack)) > 0) {
                entityarrow.setKnockbackStrength(punchLevel);
            }
            if (EnchantmentHelper.getEnchantmentLevel((int)Enchantment.flame.effectId, (ItemStack)stack) > 0) {
                entityarrow.setFire(100);
            }
            if (!this.unbreakable) {
                stack.damageItem(1, (EntityLivingBase)player);
            }
            world.playSoundAtEntity((Entity)player, this.sound, 1.0f, 1.0f / (itemRand.nextFloat() * 0.4f + 1.2f) + scaledItemUse * 0.5f);
            if (infiniteAmmo) {
                entityarrow.canBePickedUp = 2;
            } else {
                player.inventory.consumeInventoryItem(this.arrow);
            }
            if (!world.isRemote) {
                world.spawnEntityInWorld((Entity)entityarrow);
            }
        }
    }

    public int getMaxItemUseDuration(ItemStack stack) {
        return this.maxUseDuration;
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        list.add(TooltipLocalizer.bowDam(this.damageMin + "-" + this.damageMax));
        double speed = 72000.0 / (double)this.getMaxItemUseDuration(stack);
        if (speed > 1.0) {
            list.add(speed + " Times Faster");
        }
        if (speed < 1.0) {
            list.add(1.0 / speed + " Times Slower");
        }
        list.add(!this.unbreakable ? TooltipLocalizer.usesRemaining(stack.getMaxDamage() - stack.getItemDamage()) : TooltipLocalizer.infiniteUses());
        if (this.arrowTex == "bluefireArrow" || this.arrowTex == "snowstormArrow") {
            list.add(TooltipLocalizer.explosiveShots());
        }
        list.add(this.arrow == null ? TooltipLocalizer.infiniteAmmo() : TooltipLocalizer.ammo(this.arrow));
        if (this.vethean) {
            list.add(TooltipLocalizer.vethean());
        }
    }

    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        boolean infiniteAmmo;
        ArrowNockEvent nockEvent = new ArrowNockEvent(player, stack);
        MinecraftForge.EVENT_BUS.post((Event)nockEvent);
        if (nockEvent.isCanceled()) {
            return nockEvent.result;
        }
        boolean bl = infiniteAmmo = !this.needArrow || player.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel((int)Enchantment.infinity.effectId, (ItemStack)stack) > 0 && player.inventory.hasItem(this.arrow);
        if (infiniteAmmo || player.inventory.hasItem(this.arrow)) {
            player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
        }
        return stack;
    }

    public ItemModBow setSound(String newSound) {
        this.sound = newSound;
        return this;
    }

    public ItemModBow setVethean() {
        this.setCreativeTab(DivineRPGTabs.vethea);
        this.vethean = true;
        return this;
    }
}

