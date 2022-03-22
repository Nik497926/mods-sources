/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.item.relic.legendary.armor.killer;

import com.meteor.extrabotany.common.item.relic.legendary.armor.CoreArmor;
import cpw.mods.fml.common.Optional;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import thaumcraft.api.IGoggles;
import thaumcraft.api.IVisDiscountGear;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.nodes.IRevealer;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.api.item.IAncientWillContainer;
import vazkii.botania.api.item.IManaProficiencyArmor;
import vazkii.botania.api.mana.BurstProperties;
import vazkii.botania.api.mana.ILensEffect;
import vazkii.botania.api.mana.IManaDiscountArmor;
import vazkii.botania.api.mana.IManaGivingItem;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.entity.EntityManaBurst;
import vazkii.botania.common.item.relic.ItemRelic;

@Optional.InterfaceList(value={@Optional.Interface(modid="Thaumcraft", iface="thaumcraft.api.IGoggles", striprefs=true), @Optional.Interface(modid="Thaumcraft", iface="thaumcraft.api.nodes.IRevealer", striprefs=true)})
public class ItemKillerHelm extends ItemKillerArmor implements IGoggles, IRevealer, IManaDiscountArmor, IManaProficiencyArmor, IAncientWillContainer, IManaGivingItem, ILensEffect, IVisDiscountGear {
    private static final String TAG_ATTACKER_USERNAME = "attackerUsername";
    private static final String TAG_HOME_ID = "homeID";
    private static final String TAG_ANCIENT_WILL = "AncientWill";
    public static List damageNegations;
    List playersWithFlight = new ArrayList();

    public ItemKillerHelm() {
        super(0, "killerhelm", null);
        MinecraftForge.EVENT_BUS.register(this);
        damageNegations.add(ItemRelic.damageSource().getDamageType());
        damageNegations.add(DamageSource.anvil.getDamageType());
        damageNegations.add(DamageSource.cactus.getDamageType());
        damageNegations.add(DamageSource.drown.getDamageType());
        damageNegations.add(DamageSource.fall.getDamageType());
        damageNegations.add(DamageSource.fallingBlock.getDamageType());
        damageNegations.add(DamageSource.inFire.getDamageType());
        damageNegations.add(DamageSource.inWall.getDamageType());
        damageNegations.add(DamageSource.lava.getDamageType());
        damageNegations.add(DamageSource.onFire.getDamageType());
        damageNegations.add(DamageSource.outOfWorld.getDamageType());
        damageNegations.add(DamageSource.wither.getDamageType());
        damageNegations.add(DamageSource.starve.getDamageType());
    }

    @SubscribeEvent
    public void updatePlayerFlyStatus(LivingEvent.LivingUpdateEvent event) {
        if (event.entityLiving instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)event.entityLiving;
            if (this.playersWithFlight.contains(this.playerStr(player))) {
                if (this.shouldPlayerHaveFlight(player)) {
                    player.capabilities.allowFlying = true;
                } else {
                    if (!player.capabilities.isCreativeMode) {
                        player.capabilities.allowFlying = false;
                        player.capabilities.isFlying = false;
                        player.capabilities.disableDamage = false;
                    }
                    this.playersWithFlight.remove(this.playerStr(player));
                }
            } else if (this.shouldPlayerHaveFlight(player)) {
                this.playersWithFlight.add(this.playerStr(player));
                player.capabilities.allowFlying = true;
            }
        }
    }

    @SubscribeEvent
    public void playerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
        String username = event.player.getGameProfile().getName();
        this.playersWithFlight.remove(username + ":false");
        this.playersWithFlight.remove(username + ":true");
    }

    public String playerStr(EntityPlayer p) {
        return p.getGameProfile().getName() + ":" + p.worldObj.isRemote;
    }

    private boolean shouldPlayerHaveFlight(EntityPlayer p) {
        return this.hasArmorSet(p);
    }

    public boolean shouldGiveProficiency(ItemStack stack, int slot, EntityPlayer player) {
        return this.hasArmorSet(player);
    }

    public void addAncientWill(ItemStack stack, int will) {
        ItemNBTHelper.setBoolean(stack, TAG_ANCIENT_WILL + will, true);
    }

    public boolean hasAncientWill(ItemStack stack, int will) {
        return ItemKillerHelm.hasAncientWill_(stack, will);
    }

    public static boolean hasAncientWill_(ItemStack stack, int will) {
        return ItemNBTHelper.getBoolean(stack, TAG_ANCIENT_WILL + will, false);
    }

    public static boolean hasAnyWill(ItemStack stack) {
        for (int i = 0; i < 6; ++i) {
            if (!ItemKillerHelm.hasAncientWill_(stack, i)) continue;
            return true;
        }
        return false;
    }

    public EntityManaBurst getBurst(EntityPlayer player, ItemStack stack) {
        EntityManaBurst burst = new EntityManaBurst(player);
        float motionModifier = 25.0f;
        burst.setColor(0xFFFF20);
        burst.setMana(1);
        burst.setStartingMana(1);
        burst.setMinManaLoss(100);
        burst.setManaLossPerTick(1.0f);
        burst.setGravity(0.0f);
        burst.setMotion(burst.motionX * (double)motionModifier, burst.motionY * (double)motionModifier, burst.motionZ * (double)motionModifier);
        ItemStack lens = stack.copy();
        ItemNBTHelper.setString(lens, TAG_ATTACKER_USERNAME, player.getCommandSenderName());
        burst.setSourceLens(lens);
        return burst;
    }

    public void apply(ItemStack arg0, BurstProperties arg1) {
    }

    public boolean collideBurst(IManaBurst burst, MovingObjectPosition pos, boolean isManaBlock, boolean dead, ItemStack stack) {
        return dead;
    }

    public void updateBurst(IManaBurst burst, ItemStack stack) {
    }

    public boolean doParticles(IManaBurst arg0, ItemStack arg1) {
        return true;
    }

    public boolean showNodes(ItemStack itemstack, EntityLivingBase player) {
        return true;
    }

    public boolean showIngamePopups(ItemStack itemstack, EntityLivingBase player) {
        return true;
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
        super.onArmorTick(world, player, stack);
        if (ItemNBTHelper.getBoolean(stack, "potion", true)) {
            player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 401, 10, true));
        }
        if (!CoreArmor.getAnother(player, (byte)0, (byte)0) && !world.isRemote) {
            CoreArmor.generateManaAndVis(player, stack);
        }
    }

    public float getDiscount(ItemStack stack, int slot, EntityPlayer player) {
        return this.hasArmorSet(player) ? 0.75f : 0.15f;
    }

    public int getVisDiscount(ItemStack itemStack, EntityPlayer entityPlayer, Aspect aspect) {
        return CoreArmor.getVisDiscount(itemStack, entityPlayer, aspect);
    }

    static {
        damageNegations = new ArrayList();
    }
}

