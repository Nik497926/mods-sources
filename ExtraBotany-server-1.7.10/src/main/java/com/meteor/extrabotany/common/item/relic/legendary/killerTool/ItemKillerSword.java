/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.item.relic.legendary.killerTool;

import com.meteor.extrabotany.ExtraBotany;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import java.awt.Color;
import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.server.MinecraftServer;
import net.minecraft.stats.Achievement;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import ru.justagod.cutter.invoke.Invoke;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.api.item.IRelic;
import vazkii.botania.api.mana.BurstProperties;
import vazkii.botania.api.mana.ILensEffect;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.entity.EntityManaBurst;

public class ItemKillerSword
extends ItemSword
implements IRelic,
ILensEffect,
IManaUsingItem {
    public static Item.ToolMaterial material = EnumHelper.addToolMaterial((String)"KILLERTOOL", (int)6, (int)-1, (float)9.0f, (float)26.0f, (int)45);
    IIcon[] icons;
    String name = "killerSword";
    public static int c_tick = 0;
    int s_tick = 0;
    boolean ignoreLeftClick = false;

    public ItemKillerSword() {
        super(material);
        this.setUnlocalizedName(this.name).setCreativeTab((CreativeTabs)ExtraBotany.tabExtraBotany).setMaxStackSize(1);
        MinecraftForge.EVENT_BUS.register((Object)this);
        FMLCommonHandler.instance().bus().register((Object)this);
        GameRegistry.registerItem((Item)this, (String)this.name);
    }

    public IIcon getIcon(ItemStack stack, int pass) {
        int type = ItemNBTHelper.getInt((ItemStack)stack, (String)"type", (int)0);
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

    public void bindToUsername(String s, ItemStack itemStack) {
        ItemNBTHelper.setString((ItemStack)itemStack, (String)"soulbinds", (String)s);
    }

    public String getSoulbindUsername(ItemStack itemStack) {
        return ItemNBTHelper.getString((ItemStack)itemStack, (String)"soulbinds", (String)"");
    }

    public void setBindAchievement(Achievement achievement) {
    }

    public Achievement getBindAchievement() {
        return null;
    }

    public boolean hasEffect(ItemStack par1ItemStack, int pass) {
        return true;
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean isShift) {
        ItemKillerSword.addBindInfo(list, stack, player);
        list.add(StatCollector.translateToLocal((String)"killerSword.misc0"));
    }

    public static void addBindInfo(List list, ItemStack stack, EntityPlayer player) {

    }

    public static String getSoulbindUsernameS(ItemStack stack) {
        return ItemNBTHelper.getString((ItemStack)stack, (String)"soulbinds", (String)"");
    }

    static void addStringToTooltips(String s, List tooltip) {
        tooltip.add(s.replaceAll("&", "\u00a7"));
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
        ItemNBTHelper.setString((ItemStack)lens, (String)"attackerUsername", (String)player.getCommandSenderName());
        burst.setSourceLens(lens);
        return burst;
    }

    public void apply(ItemStack itemStack, BurstProperties burstProperties) {
    }

    public boolean collideBurst(IManaBurst iManaBurst, MovingObjectPosition movingObjectPosition, boolean b, boolean b1, ItemStack itemStack) {
        return b1;
    }

    public void updateBurst(IManaBurst burst, ItemStack stack) {
        EntityThrowable entity = (EntityThrowable)burst;
        AxisAlignedBB axis = AxisAlignedBB.getBoundingBox((double)entity.posX, (double)entity.posY, (double)entity.posZ, (double)entity.lastTickPosX, (double)entity.lastTickPosY, (double)entity.lastTickPosZ).expand(1.0, 1.0, 1.0);
        List<EntityLivingBase> entities = entity.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, axis);
        String attacker = ItemNBTHelper.getString((ItemStack)burst.getSourceLens(), (String)"attackerUsername", (String)"");
        for (EntityLivingBase living : entities) {
            if (living instanceof EntityPlayer && (((EntityPlayer)living).getCommandSenderName().equals(attacker) || MinecraftServer.getServer() != null && !MinecraftServer.getServer().isPVPEnabled()) || living.hurtTime != 0) continue;
            int cost = 0;
            int mana = burst.getMana();
            if (mana < cost) continue;
            burst.setMana(mana - cost);
            float damage = material.getDamageVsEntity() * 0.75f;
            if (burst.isFake() || entity.worldObj.isRemote) continue;
            Invoke.server(() -> {});
            break;
        }
    }

    public boolean doParticles(IManaBurst iManaBurst, ItemStack itemStack) {
        return true;
    }

    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        if (!this.ignoreLeftClick && ItemKillerAxe.canUse(player, stack) && entity instanceof EntityLivingBase && ((EntityLivingBase)entity).hurtTime == 0 && !((EntityLivingBase)entity).isDead && ItemNBTHelper.getInt((ItemStack)stack, (String)"type", (int)0) == 1) {
            int range = 5;
            List<Entity> entities = player.worldObj.getEntitiesWithinAABB(entity.getClass(), AxisAlignedBB.getBoundingBox((double)(entity.posX - (double)range), (double)(entity.posY - (double)range), (double)(entity.posZ - (double)range), (double)(entity.posX + (double)range), (double)(entity.posY + (double)range), (double)(entity.posZ + (double)range)));
            this.ignoreLeftClick = true;
            for (Entity entity_ : entities) {
                Invoke.server(() -> {});
            }
            this.ignoreLeftClick = false;
        }
        return super.onLeftClickEntity(stack, player, entity);
    }

    @SubscribeEvent(priority=EventPriority.HIGHEST)
    public void EventKiller(LivingHurtEvent event) {
        ItemStack s;
        if (event.source.getSourceOfDamage() instanceof EntityPlayer && (s = ((EntityPlayer)event.source.getSourceOfDamage()).inventory.getCurrentItem()) != null && s.getItem() == this && !((EntityPlayer)event.source.getSourceOfDamage()).getCommandSenderName().equals(this.getSoulbindUsername(s))) {
            event.setCanceled(true);
        }
    }

    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (!world.isRemote && player.isSneaking()) {
            int type = ItemNBTHelper.getInt((ItemStack)stack, (String)"type", (int)0);
            if (type == 0) {
                player.addChatComponentMessage((IChatComponent)new ChatComponentText(StatCollector.translateToLocal((String)"misc.killerTool.mod1")));
                ItemNBTHelper.setInt((ItemStack)stack, (String)"type", (int)1);
            } else if (type == 1) {
                player.addChatComponentMessage((IChatComponent)new ChatComponentText(StatCollector.translateToLocal((String)"misc.killerTool.mod2")));
                ItemNBTHelper.setInt((ItemStack)stack, (String)"type", (int)2);
            } else {
                player.addChatComponentMessage((IChatComponent)new ChatComponentText(StatCollector.translateToLocal((String)"misc.killerTool.mod0")));
                ItemNBTHelper.setInt((ItemStack)stack, (String)"type", (int)0);
            }
        }
        return super.onItemRightClick(stack, world, player);
    }

    public boolean usesMana(ItemStack itemStack) {
        return false;
    }
}

