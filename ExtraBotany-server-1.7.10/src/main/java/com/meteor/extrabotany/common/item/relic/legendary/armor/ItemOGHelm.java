/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.item.relic.legendary.armor;

import com.meteor.extrabotany.common.item.ModItems;
import com.meteor.extrabotany.common.item.relic.legendary.armor.ItemOGArmor;
import cpw.mods.fml.common.Optional;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.IGoggles;
import thaumcraft.api.nodes.IRevealer;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.api.item.IAncientWillContainer;
import vazkii.botania.api.item.IManaProficiencyArmor;
import vazkii.botania.api.mana.BurstProperties;
import vazkii.botania.api.mana.ILensEffect;
import vazkii.botania.api.mana.IManaDiscountArmor;
import vazkii.botania.api.mana.IManaGivingItem;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.core.helper.Vector3;
import vazkii.botania.common.entity.EntityManaBurst;
import vazkii.botania.common.item.relic.ItemRelic;

@Optional.InterfaceList(value={@Optional.Interface(modid="Thaumcraft", iface="thaumcraft.api.IGoggles", striprefs=true), @Optional.Interface(modid="Thaumcraft", iface="thaumcraft.api.nodes.IRevealer", striprefs=true)})
public class ItemOGHelm
extends ItemOGArmor
implements IGoggles,
IRevealer,
IManaDiscountArmor,
IManaProficiencyArmor,
IAncientWillContainer,
IManaGivingItem,
ILensEffect {
    private static final String TAG_ATTACKER_USERNAME = "attackerUsername";
    private static final String TAG_HOME_ID = "homeID";
    private static final String TAG_ANCIENT_WILL = "AncientWill";
    static IIcon willIcon;
    public static List damageNegations;
    List playersWithFlight = new ArrayList();

    public ItemOGHelm() {
        super(0, "oghelm", null);
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

    public boolean fullArmor(EntityPlayer player) {
        ItemStack[] armor = player.inventory.armorInventory;
        Item[] armorNeed = new Item[]{ModItems.oghelm, ModItems.ogchest, ModItems.oglegs, ModItems.ogboots};
        Boolean flag = true;
        for (int i = 0; i < 4; ++i) {
            if (armor[3 - i] != null && armor[3 - i].getItem() == armorNeed[i] && ItemOGHelm.isRightPlayer(player, armor[3 - i])) continue;
            flag = false;
        }
        return flag;
    }

    public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
        super.onArmorTick(world, player, stack);
        if (player != null && this.fullArmor(player)) {
            Collection potions = player.getActivePotionEffects();
            Iterator food = potions.iterator();
            String[] nameItems = new String[]{"The Desecrated Helmet", "Desecrated Breastplate", "Desecrated leggings", "Defiled Bots"};
            Boolean flag = true;
            for (int i = 0; i < 4; ++i) {
                if (this.getItemStackDisplayName(player.inventory.armorInventory[3 - i]) == nameItems[i]) continue;
                flag = false;
            }
            if (flag.booleanValue()) {
                ManaItemHandler.dispatchManaExact(stack, player, 15, true);
            } else {
                ManaItemHandler.dispatchManaExact(stack, player, 5, true);
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

    public float getDiscount(ItemStack stack, int slot, EntityPlayer player) {
        return this.hasArmorSet(player) ? 0.75f : 0.15f;
    }

    public boolean shouldGiveProficiency(ItemStack stack, int slot, EntityPlayer player) {
        return this.hasArmorSet(player);
    }

    public void addAncientWill(ItemStack stack, int will) {
        ItemNBTHelper.setBoolean(stack, TAG_ANCIENT_WILL + will, true);
    }

    public boolean hasAncientWill(ItemStack stack, int will) {
        return ItemOGHelm.hasAncientWill_(stack, will);
    }

    public static boolean hasAncientWill_(ItemStack stack, int will) {
        return ItemNBTHelper.getBoolean(stack, TAG_ANCIENT_WILL + will, false);
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void addArmorSetDescription(ItemStack stack, List list) {
        super.addArmorSetDescription(stack, list);
        for (int i = 0; i < 6; ++i) {
            if (!this.hasAncientWill(stack, i)) continue;
            this.addStringToTooltip(StatCollector.translateToLocal("botania.armorset.will" + i + ".desc"), list);
        }
    }

    public static boolean hasAnyWill(ItemStack stack) {
        for (int i = 0; i < 6; ++i) {
            if (!ItemOGHelm.hasAncientWill_(stack, i)) continue;
            return true;
        }
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public static void renderOnPlayer(ItemStack stack, RenderPlayerEvent event) {
        if (ItemOGHelm.hasAnyWill(stack) && !((ItemOGArmor)stack.getItem()).hasPhantomInk(stack)) {
            GL11.glPushMatrix();
            float f = willIcon.getMinU();
            float f1 = willIcon.getMaxU();
            float f2 = willIcon.getMinV();
            float f3 = willIcon.getMaxV();
            IManaProficiencyArmor.Helper.hasProficiency(event.entityPlayer);
            Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationItemsTexture);
            GL11.glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
            GL11.glRotatef(180.0f, 1.0f, 0.0f, 0.0f);
            GL11.glTranslatef(-0.26f, 0.15f, -0.39f);
            GL11.glScalef(0.5f, 0.5f, 0.5f);
            ItemRenderer.renderItemIn2D(Tessellator.instance, f1, f2, f, f3, willIcon.getIconWidth(), willIcon.getIconHeight(), 0.0625f);
            GL11.glPopMatrix();
        }
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
            float damage1 = 8.0f;
            if (burst.isFake() || entity.worldObj.isRemote) continue;
            EntityPlayer player = living2.worldObj.getPlayerEntityByName(attacker);
            entity.setDead();
            break;
        }
    }

    public boolean doParticles(IManaBurst arg0, ItemStack arg1) {
        return true;
    }

    @SubscribeEvent
    public void onEntityAttacked(LivingHurtEvent event) {
        EntityPlayer player;
        Entity attacker = event.source.getEntity();
        if (attacker instanceof EntityPlayer && this.hasArmorSet(player = (EntityPlayer)attacker)) {
            if (damageNegations.contains(event.source)) {
                event.ammount = 0.0f;
            }
            boolean crit = player.fallDistance > 0.0f && !player.onGround && !player.isOnLadder() && !player.isInWater() && !player.isPotionActive(Potion.blindness) && player.ridingEntity == null;
            ItemStack stack = player.inventory.armorItemInSlot(3);
            if (stack != null && stack.getItem() instanceof ItemOGHelm) {
                EntityManaBurst burst = this.getBurst(player, stack);
                player.worldObj.spawnEntityInWorld(burst);
                if (crit) {
                    boolean ahrim = this.hasAncientWill(stack, 0);
                    boolean dharok = this.hasAncientWill(stack, 1);
                    boolean guthan = this.hasAncientWill(stack, 2);
                    boolean torag = this.hasAncientWill(stack, 3);
                    boolean verac = this.hasAncientWill(stack, 4);
                    boolean karil = this.hasAncientWill(stack, 5);
                    if (ahrim) {
                        event.entityLiving.addPotionEffect(new PotionEffect(Potion.weakness.id, 20, 1));
                    }
                    if (dharok) {
                        event.ammount *= 1.0f + (1.0f - player.getHealth() / player.getMaxHealth()) * 0.5f;
                    }
                    if (guthan) {
                        player.heal(event.ammount * 0.25f);
                    }
                    if (torag) {
                        event.entityLiving.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 60, 1));
                    }
                    if (verac) {
                        event.source.setDamageBypassesArmor();
                    }
                }
            }
        }
    }

    public boolean showNodes(ItemStack itemstack, EntityLivingBase player) {
        return true;
    }

    public boolean showIngamePopups(ItemStack itemstack, EntityLivingBase player) {
        return true;
    }

    static {
        damageNegations = new ArrayList();
    }
}

