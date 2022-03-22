/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.item.relic.legendary.armor.awake;

import com.meteor.extrabotany.common.item.relic.legendary.armor.CoreArmor;
import com.meteor.extrabotany.common.item.relic.legendary.armor.ItemOGArmor;
import com.meteor.extrabotany.common.item.relic.legendary.armor.awake.ItemAwakeOGArmor;
import cpw.mods.fml.common.Optional;
import cpw.mods.fml.relauncher.ReflectionHelper;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
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
import vazkii.botania.common.core.helper.Vector3;

@Optional.InterfaceList(value={@Optional.Interface(modid="Thaumcraft", iface="thaumcraft.api.IGoggles", striprefs=true), @Optional.Interface(modid="Thaumcraft", iface="thaumcraft.api.nodes.IRevealer", striprefs=true)})
public class ItemAwakeOGHelm
extends ItemAwakeOGArmor
implements IGoggles,
IRevealer,
IManaDiscountArmor,
IManaProficiencyArmor,
IAncientWillContainer,
IManaGivingItem,
ILensEffect,
IVisDiscountGear {
    public ItemAwakeOGHelm() {
        super(0, "awakeoghelm", null);
    }

    public boolean showIngamePopups(ItemStack itemStack, EntityLivingBase entityLivingBase) {
        return true;
    }

    public boolean showNodes(ItemStack itemStack, EntityLivingBase entityLivingBase) {
        return true;
    }

    public void addAncientWill(ItemStack itemStack, int i) {
        ItemNBTHelper.setBoolean(itemStack, "AncientWill" + i, true);
    }

    public boolean hasAncientWill(ItemStack itemStack, int i) {
        return ItemAwakeOGHelm.hasAncientWill_(itemStack, i);
    }

    public static boolean hasAncientWill_(ItemStack stack, int will) {
        return ItemNBTHelper.getBoolean(stack, "AncientWill" + will, false);
    }

    public boolean shouldGiveProficiency(ItemStack itemStack, int i, EntityPlayer entityPlayer) {
        return this.hasArmorSet(entityPlayer);
    }

    public void apply(ItemStack itemStack, BurstProperties burstProperties) {
    }

    public boolean collideBurst(IManaBurst iManaBurst, MovingObjectPosition movingObjectPosition, boolean b, boolean b1, ItemStack itemStack) {
        return b1;
    }

    public void updateBurst(IManaBurst burst, ItemStack stack) {
        Entity i$1;
        EntityThrowable entity = (EntityThrowable)burst;
        AxisAlignedBB axis = AxisAlignedBB.getBoundingBox(entity.posX, entity.posY, entity.posZ, entity.lastTickPosX, entity.lastTickPosY, entity.lastTickPosZ).expand(1.0, 1.0, 1.0);
        String attacker = ItemNBTHelper.getString(burst.getSourceLens(), "attackerUsername", "");
        int homeID = ItemNBTHelper.getInt(stack, "homeID", -1);
        if (homeID == -1) {
            AxisAlignedBB entities = AxisAlignedBB.getBoundingBox(entity.posX, entity.posY, entity.posZ, entity.lastTickPosX, entity.lastTickPosY, entity.lastTickPosZ).expand(5.0, 5.0, 5.0);
            List<EntityLivingBase> i$ = entity.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, entities);
            for (EntityLivingBase cost : i$) {
                if (cost instanceof EntityPlayer || !(cost instanceof IMob) || cost.hurtTime != 0) continue;
                homeID = cost.getEntityId();
                ItemNBTHelper.setInt(stack, "homeID", homeID);
                break;
            }
        }
        List entities1 = entity.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, axis);
        if (homeID != -1 && (i$1 = entity.worldObj.getEntityByID(homeID)) != null) {
            Vector3 living1 = Vector3.fromEntityCenter(i$1);
            Vector3 cost1 = Vector3.fromEntityCenter(entity);
            Vector3 mana = living1.sub(cost1);
            Vector3 damage = new Vector3(entity.motionX, entity.motionY, entity.motionZ);
            mana.normalize().multiply(damage.mag());
            burst.setMotion(mana.x, mana.y, mana.z);
        }
        Iterator i$2 = entities1.iterator();
    }

    public boolean doParticles(IManaBurst iManaBurst, ItemStack itemStack) {
        return true;
    }

    public float getDiscount(ItemStack itemStack, int i, EntityPlayer entityPlayer) {
        return ItemNBTHelper.getInt(itemStack, "level", 1) > 2 ? 5.0f : 0.0f;
    }

    public static void clearEffect(EntityPlayer player) {
        boolean removed = false;
        Collection<PotionEffect> potions = player.getActivePotionEffects();
        if (player.isBurning()) {
            player.extinguish();
            removed = true;
        } else {
            for (PotionEffect potion : potions) {
                int id = potion.getPotionID();
                boolean badEffect = (Boolean)ReflectionHelper.getPrivateValue(Potion.class, Potion.potionTypes[id], new String[]{"isBadEffect", "isBadEffect"});
                if (!badEffect) continue;
                player.removePotionEffect(id);
                removed = true;
                break;
            }
        }
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
        super.onArmorTick(world, player, stack);
        ItemStack helm = player.inventory.armorInventory[3];
        if (!world.isRemote && helm != null && ItemOGArmor.isRightPlayer(player, helm)) {
            CoreArmor.awakeLevels(helm, (byte)0, player);
        }
    }

    @Override
    public int getVisDiscount(ItemStack itemStack, EntityPlayer entityPlayer, Aspect aspect) {
        return CoreArmor.getVisDiscount(itemStack, entityPlayer, aspect);
    }
}

