/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.item.relic.legendary;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.meteor.extrabotany.common.entity.EntityItemUnbreakable;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.stats.Achievement;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.api.item.IRelic;
import vazkii.botania.api.mana.BurstProperties;
import vazkii.botania.api.mana.ILensEffect;
import vazkii.botania.api.mana.IManaUsingItem;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.core.helper.Vector3;
import vazkii.botania.common.entity.EntityManaBurst;
import vazkii.botania.common.item.equipment.tool.manasteel.ItemManasteelSword;
import vazkii.botania.common.item.relic.ItemRelic;

public class ItemExcaliber
extends ItemManasteelSword
implements IRelic,
ILensEffect,
IManaUsingItem {
    private static final String TAG_ATTACKER_USERNAME = "attackerUsername";
    private static final String TAG_HOME_ID = "homeID";
    public static Item.ToolMaterial toolMaterial = EnumHelper.addToolMaterial("B_EXCALIBER", 3, -1, 6.2f, 20.0f, 40);
    Achievement achievement;

    public ItemExcaliber() {
        super(toolMaterial, "excaliber");
    }

    public EnumRarity getRarity(ItemStack p_77613_1_) {
        return BotaniaAPI.rarityRelic;
    }

    public boolean hasCustomEntity(ItemStack stack) {
        return true;
    }

    public Entity createEntity(World world, Entity location, ItemStack itemstack) {
        EntityItemUnbreakable entity = new EntityItemUnbreakable(world, location.posX, location.posY, location.posZ, itemstack);
        if (location instanceof EntityItem) {
            entity.delayBeforeCanPickup = 40;
        }
        entity.motionX = location.motionX;
        entity.motionY = location.motionY;
        entity.motionZ = location.motionZ;
        return entity;
    }

    public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {
        if (par3Entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)par3Entity;
            ItemRelic.updateRelic(par1ItemStack, player);
            if (ItemRelic.isRightPlayer(player, par1ItemStack)) {
                PotionEffect haste = player.getActivePotionEffect(Potion.digSpeed);
                float check = haste == null ? 0.16666667f : (check = haste.getAmplifier() == 1 ? 0.5f : 0.2f);
                if (player.getCurrentEquippedItem() == par1ItemStack && player.swingProgress == check && !par2World.isRemote) {
                    EntityManaBurst burst = this.getBurst(player, par1ItemStack);
                    par2World.spawnEntityInWorld(burst);
                    par2World.playSoundAtEntity(player, "botania:terraBlade", 0.4f, 1.4f);
                }
            }
        }
    }

    public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
        ItemRelic.addBindInfo(p_77624_3_, p_77624_1_, p_77624_2_);
    }

    public void bindToUsername(String playerName, ItemStack stack) {
        ItemRelic.bindToUsernameS(playerName, stack);
    }

    public String getSoulbindUsername(ItemStack stack) {
        return ItemRelic.getSoulbindUsernameS(stack);
    }

    public Achievement getBindAchievement() {
        return this.achievement;
    }

    public void setBindAchievement(Achievement achievement) {
        this.achievement = achievement;
    }

    public boolean usesMana(ItemStack stack) {
        return true;
    }

    public int getEntityLifespan(ItemStack itemStack, World world) {
        return Integer.MAX_VALUE;
    }

    public Multimap getItemAttributeModifiers() {
        HashMultimap multimap = HashMultimap.create();
        multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", 16.0, 0));
        multimap.put(SharedMonsterAttributes.movementSpeed.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", 0.4, 1));
        return multimap;
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

    public void apply(ItemStack stack, BurstProperties props) {
    }

    public boolean collideBurst(IManaBurst burst, MovingObjectPosition pos, boolean isManaBlock, boolean dead, ItemStack stack) {
        return dead;
    }

    public void updateBurst(IManaBurst burst, ItemStack stack) {
        Entity i$1;
        EntityThrowable entity = (EntityThrowable)burst;
        AxisAlignedBB axis = AxisAlignedBB.getBoundingBox(entity.posX, entity.posY, entity.posZ, entity.lastTickPosX, entity.lastTickPosY, entity.lastTickPosZ).expand(1.0, 1.0, 1.0);
        String attacker = ItemNBTHelper.getString(burst.getSourceLens(), TAG_ATTACKER_USERNAME, "");
        int homeID = ItemNBTHelper.getInt(stack, TAG_HOME_ID, -1);
        if (homeID == -1) {
            AxisAlignedBB entities = AxisAlignedBB.getBoundingBox(entity.posX, entity.posY, entity.posZ, entity.lastTickPosX, entity.lastTickPosY, entity.lastTickPosZ).expand(5.0, 5.0, 5.0);
            List<EntityLivingBase> i$ = entity.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, entities);
            for (EntityLivingBase cost : i$) {
                if (cost instanceof EntityPlayer || !(cost instanceof IMob) || cost.hurtTime != 0) continue;
                homeID = cost.getEntityId();
                ItemNBTHelper.setInt(stack, TAG_HOME_ID, homeID);
                break;
            }
        }
        List<EntityLivingBase> entities1 = entity.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, axis);
        if (homeID != -1 && (i$1 = entity.worldObj.getEntityByID(homeID)) != null) {
            Vector3 living1 = Vector3.fromEntityCenter(i$1);
            Vector3 cost1 = Vector3.fromEntityCenter(entity);
            Vector3 mana = living1.sub(cost1);
            Vector3 damage = new Vector3(entity.motionX, entity.motionY, entity.motionZ);
            mana.normalize().multiply(damage.mag());
            burst.setMotion(mana.x, mana.y, mana.z);
        }
        for (EntityLivingBase living2 : entities1) {
            if (living2 instanceof EntityPlayer && (living2.getCommandSenderName().equals(attacker) || MinecraftServer.getServer() != null && !MinecraftServer.getServer().isPVPEnabled()) || living2.hurtTime != 0) continue;
            int cost2 = 1;
            int mana1 = burst.getMana();
            if (mana1 < cost2) continue;
            burst.setMana(mana1 - cost2);
            float damage1 = 6.0f + toolMaterial.getDamageVsEntity();
            if (burst.isFake() || entity.worldObj.isRemote) continue;
            EntityPlayer player = living2.worldObj.getPlayerEntityByName(attacker);
            living2.attackEntityFrom(player == null ? DamageSource.magic : DamageSource.causePlayerDamage(player), damage1);
            entity.setDead();
            break;
        }
    }

    public boolean doParticles(IManaBurst burst, ItemStack stack) {
        return true;
    }

    public int getMaxItemUseDuration(ItemStack par1ItemStack) {
        return 200;
    }

    public boolean isFull3D() {
        return true;
    }
}

