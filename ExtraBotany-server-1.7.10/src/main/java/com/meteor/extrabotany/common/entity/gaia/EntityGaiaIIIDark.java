/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.entity.gaia;

import com.meteor.extrabotany.common.achievement.ModAchievement;
import com.meteor.extrabotany.common.block.ModBlocks;
import com.meteor.extrabotany.common.block.tile.TileGaiaChest;
import com.meteor.extrabotany.common.entity.EntityGaiaQuickened;
import com.meteor.extrabotany.common.entity.gaia.EntityGaiaIII;
import com.meteor.extrabotany.common.entity.gaia.EntityGaiaIIIPhantom;
import com.meteor.extrabotany.common.entity.gaia.EntityMagicCycloneChaos;
import com.meteor.extrabotany.common.entity.gaia.EntityMagicMissileII;
import com.meteor.extrabotany.common.entity.gaia.IMinion;
import com.meteor.extrabotany.common.item.ModItems;
import com.meteor.extrabotany.common.lib.LibReference;
import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.fluids.IFluidBlock;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.internal.ShaderCallback;
import vazkii.botania.client.core.helper.ShaderHelper;
import vazkii.botania.common.Botania;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.core.helper.Vector3;
import vazkii.botania.common.entity.EntityPixie;
import vazkii.botania.common.item.relic.ItemRelic;
import vazkii.botania.common.lib.LibObfuscation;

public class EntityGaiaIIIDark
extends EntityGaiaIII {
    private static ModItems instance = new ModItems();
    public static final int SPAWN_TICKS = 200;
    private static final float RANGE = 12.0f;
    private static final float MAX_HP = 12.0f;
    public static final int MOB_SPAWN_START_TICKS = 20;
    public static final int MOB_SPAWN_END_TICKS = 80;
    public static final int MOB_SPAWN_BASE_TICKS = 800;
    public static final int MOB_SPAWN_TICKS = 900;
    public static final int MOB_SPAWN_WAVES = 10;
    public static final int MOB_SPAWN_WAVE_TIME = 80;
    private static final String TAG_INVUL_TIME = "invulTime";
    private static final String TAG_AGGRO = "aggro";
    private static final String TAG_SOURCE_X = "sourceX";
    private static final String TAG_SOURCE_Y = "sourceY";
    private static final String TAG_SOURCE_Z = "sourcesZ";
    private static final String TAG_MOB_SPAWN_TICKS = "mobSpawnTicks";
    private static final String TAG_HARD_MODE = "hardMode";
    private static final String TAG_PLAYER_COUNT = "playerCount";
    private static final List<String> CHEATY_BLOCKS = Arrays.asList("OpenBlocks:beartrap", "ThaumicTinkerer:magnet");
    boolean spawnLandmines = false;
    boolean spawnPixies = false;
    boolean anyWithArmor = false;
    List<String> playersWhoAttacked = new ArrayList<String>();
    private static final int[][] PYLON_LOCATIONS = new int[][]{{4, 1, 4}, {4, 1, -4}, {-4, 1, 4}, {-4, 1, -4}};
    private static boolean isPlayingMusic = false;
    EntityGaiaIII summoner;
    private static final Pattern FAKE_PLAYER_PATTERN = Pattern.compile("^(?:\\[.*\\])|(?:ComputerCraft)$");
    @SideOnly(value=Side.CLIENT)
    private static Rectangle barRect;
    @SideOnly(value=Side.CLIENT)
    private static Rectangle hpBarRect;
    @SideOnly(value=Side.CLIENT)
    private ShaderCallback shaderCallback;

    public EntityGaiaIIIDark(World par1World) {
        super(par1World);
        this.setSize(0.6f, 1.8f);
        this.getNavigator().setCanSwim(true);
        this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.tasks.addTask(1, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, Float.MAX_VALUE));
        this.isImmuneToFire = true;
        this.experienceValue = 1625;
    }

    public static boolean spawn(World par3World, int par4, int par5, int par6, boolean hard, EntityGaiaIII gaia) {
        if (par3World.isRemote) {
            return true;
        }
        EntityGaiaIIIDark e = new EntityGaiaIIIDark(par3World);
        e.summoner = gaia;
        e.setPosition((double)par4 + 0.5, par5 + 3, (double)par6 + 0.5);
        e.setInvulTime(200);
        e.setHealth(1.0f);
        e.setSource(par4, par5, par6);
        e.setMobSpawnTicks(900);
        String b = "Obvilion.ru";
        ItemStack s2 = new ItemStack(ModItems.oghelm);
        ItemRelic.bindToUsernameS((String)b, (ItemStack)s2);
        ItemStack s3 = new ItemStack(ModItems.ogchest);
        ItemRelic.bindToUsernameS((String)b, (ItemStack)s3);
        ItemStack s4 = new ItemStack(ModItems.oglegs);
        ItemRelic.bindToUsernameS((String)b, (ItemStack)s4);
        ItemStack s5 = new ItemStack(ModItems.ogboots);
        ItemRelic.bindToUsernameS((String)b, (ItemStack)s5);
        e.setCurrentItemOrArmor(1, s2);
        e.setCurrentItemOrArmor(2, s3);
        e.setCurrentItemOrArmor(3, s4);
        e.setCurrentItemOrArmor(4, s5);
        e.setEquipmentDropChance(0, -1.0f);
        e.setEquipmentDropChance(1, -1.0f);
        e.setEquipmentDropChance(2, -1.0f);
        e.setEquipmentDropChance(3, -1.0f);
        e.setEquipmentDropChance(4, -1.0f);
        e.setHardMode(hard);
        List<EntityPlayer> players = e.getPlayersAround();
        int playerCount = 0;
        for (EntityPlayer p : players) {
            if (!EntityGaiaIIIDark.isTruePlayer((Entity)p)) continue;
            ++playerCount;
        }
        e.setPlayerCount(playerCount);
        e.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.maxHealth).setBaseValue((double)(2880.0f * (float)playerCount));
        par3World.playSoundAtEntity((Entity)e, "mob.enderdragon.growl", 10.0f, 0.1f);
        par3World.spawnEntityInWorld((Entity)e);
        return true;
    }

    @Override
    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
        Entity e = par1DamageSource.getEntity();
        if ((par1DamageSource.damageType.equals("player") || e instanceof EntityPixie) && e != null && EntityGaiaIIIDark.isTruePlayer(e) && this.getInvulTime() == 0) {
            EntityPlayer player = (EntityPlayer)e;
            if (!this.playersWhoAttacked.contains(player.getCommandSenderName())) {
                this.playersWhoAttacked.add(player.getCommandSenderName());
            }
            float dmg = par2;
            boolean crit = false;
            if (e instanceof EntityPlayer) {
                EntityPlayer p = (EntityPlayer)e;
                crit = p.fallDistance > 0.0f && !p.onGround && !p.isOnLadder() && !p.isInWater() && !p.isPotionActive(Potion.blindness) && p.ridingEntity == null;
            }
            return super.attackEntityFrom(par1DamageSource, 1.0f);
        }
        return false;
    }

    public static boolean isTruePlayer(Entity e) {
        if (!(e instanceof EntityPlayer)) {
            return false;
        }
        EntityPlayer player = (EntityPlayer)e;
        String name = player.getCommandSenderName();
        return !(player instanceof FakePlayer) && !FAKE_PLAYER_PATTERN.matcher(name).matches();
    }

    @Override
    protected void damageEntity(DamageSource par1DamageSource, float par2) {
        super.damageEntity(par1DamageSource, par2);
        Entity attacker = par1DamageSource.getEntity();
        if (attacker != null) {
            Vector3 thisVector = Vector3.fromEntityCenter((Entity)this);
            Vector3 playerVector = Vector3.fromEntityCenter((Entity)attacker);
            Vector3 motionVector = thisVector.copy().sub(playerVector).copy().normalize().multiply(0.75);
            if (this.getHealth() > 0.0f) {
                this.motionX = -motionVector.x;
                this.motionY = 0.5;
                this.motionZ = -motionVector.z;
                this.setTPDelay(4);
                this.spawnPixies = this.isAggored();
            }
            this.setAggroed(true);
        }
    }

    @Override
    public void onDeath(DamageSource p_70645_1_) {
        super.onDeath(p_70645_1_);
        AxisAlignedBB axis = AxisAlignedBB.getBoundingBox((double)((float)this.getSource().posX - 12.0f), (double)((float)this.getSource().posY - 12.0f), (double)((float)this.getSource().posZ - 12.0f), (double)((float)this.getSource().posX + 12.0f), (double)((float)this.getSource().posY + 12.0f), (double)((float)this.getSource().posZ + 12.0f)).expand(1.0, 1.0, 1.0);
        List<IMinion> ms = this.worldObj.getEntitiesWithinAABB(IMinion.class, axis);
        for (IMinion m : ms) {
            if (!m.canDestroy()) continue;
            ((Entity)m).setDead();
        }
        EntityLivingBase entitylivingbase = this.func_94060_bK();
        if (entitylivingbase instanceof EntityPlayer) {
            ((EntityPlayer)entitylivingbase).addStat((StatBase)ModAchievement.Gaia_gaia3DarkKill, 1);
            if (!this.anyWithArmor) {
                ((EntityPlayer)entitylivingbase).addStat((StatBase)ModAchievement.Gaia_gaia3DarkNoArmor, 1);
            }
        }
        for (int pl = 0; pl < this.playersWhoAttacked.size(); ++pl) {
            this.generate(this.worldObj, this.worldObj.rand, this.getSource().posX, this.getSource().posY + 1 + pl, this.getSource().posZ, pl);
        }
        this.worldObj.playSoundAtEntity((Entity)this, "random.explode", 20.0f, (1.0f + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2f) * 0.7f);
        this.worldObj.spawnParticle("hugeexplosion", this.posX, this.posY, this.posZ, 1.0, 0.0, 0.0);
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.4);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(12.0);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0);
    }

    @Override
    public void onLivingUpdate() {
        boolean spawnMissiles;
        boolean peaceful;
        super.onLivingUpdate();
        int maxTries = 35;
        if (this.ridingEntity != null) {
            if (this.ridingEntity.riddenByEntity != null) {
                this.ridingEntity.riddenByEntity = null;
            }
            this.ridingEntity = null;
        }
        boolean bl = peaceful = this.worldObj.difficultySetting == EnumDifficulty.PEACEFUL;
        if (!this.worldObj.isRemote && peaceful) {
            this.setDead();
        }
        if (!this.worldObj.isRemote) {
            int radius = 1;
            int posXInt = MathHelper.floor_double((double)this.posX);
            int posYInt = MathHelper.floor_double((double)this.posY);
            int posZInt = MathHelper.floor_double((double)this.posZ);
            for (int i = -radius; i < radius + 1; ++i) {
                for (int j = -radius; j < radius + 1; ++j) {
                    for (int k = -radius; k < radius + 1; ++k) {
                        int xp = posXInt + i;
                        int yp = posYInt + j;
                        int zp = posZInt + k;
                        if (!EntityGaiaIIIDark.isCheatyBlock(this.worldObj, xp, yp, zp)) continue;
                        Block block = this.worldObj.getBlock(xp, yp, zp);
                        ArrayList<ItemStack> items = block.getDrops(this.worldObj, xp, yp, zp, 0, 0);
                        for (ItemStack stack : items) {
                            if (ConfigHandler.blockBreakParticles) {
                                this.worldObj.playAuxSFX(2001, xp, yp, zp, Block.getIdFromBlock((Block)block) + (this.worldObj.getBlockMetadata(xp, yp, zp) << 12));
                            }
                            this.worldObj.spawnEntityInWorld((Entity)new EntityItem(this.worldObj, (double)xp + 0.5, (double)yp + 0.5, (double)zp + 0.5, stack));
                        }
                        this.worldObj.setBlockToAir(xp, yp, zp);
                    }
                }
            }
        }
        ChunkCoordinates source = this.getSource();
        boolean hard = this.isHardMode();
        float range = 15.0f;
        List<EntityPlayer> players = this.getPlayersAround();
        int playerCount = this.getPlayerCount();
        if (this.worldObj.isRemote && !isPlayingMusic && !this.isDead && !players.isEmpty()) {
            Botania.proxy.playRecordClientSided(this.worldObj, source.posX, source.posY, source.posZ, (ItemRecord)ModItems.recordC);
            isPlayingMusic = true;
        }
        range = 12.0f;
        for (int i = 0; i < 360; i += 8) {
            float r = 0.6f;
            float g = 0.0f;
            float b = 0.2f;
            float m = 0.15f;
            float mv = 0.35f;
            float rad = (float)i * (float)Math.PI / 180.0f;
            double x = (double)source.posX + 0.5 - Math.cos(rad) * (double)range;
            double y = (double)source.posY + 0.5;
            double z = (double)source.posZ + 0.5 - Math.sin(rad) * (double)range;
            Botania.proxy.wispFX(this.worldObj, x, y, z, r, g, b, 0.5f, (float)(Math.random() - 0.5) * m, (float)(Math.random() - 0.5) * mv, (float)(Math.random() - 0.5) * m);
        }
        if (players.isEmpty() && !this.worldObj.playerEntities.isEmpty()) {
            this.setDead();
        } else {
            for (EntityPlayer player : players) {
                if (player.inventory.armorInventory[0] != null || player.inventory.armorInventory[1] != null || player.inventory.armorInventory[2] != null || player.inventory.armorInventory[3] != null) {
                    this.anyWithArmor = true;
                }
                ArrayList<PotionEffect> remove = new ArrayList<PotionEffect>();
                Collection<PotionEffect> active = player.getActivePotionEffects();
                for (PotionEffect effect : active) {
                    if (effect.getDuration() >= 200 || !effect.getIsAmbient() || ((Boolean)ReflectionHelper.getPrivateValue(Potion.class, Potion.potionTypes[effect.getPotionID()], (String[])LibObfuscation.IS_BAD_EFFECT)).booleanValue()) continue;
                    remove.add(effect);
                }
                active.removeAll(remove);
                boolean bl2 = player.capabilities.isFlying = player.capabilities.isFlying && player.capabilities.isCreativeMode;
                if (!(vazkii.botania.common.core.helper.MathHelper.pointDistanceSpace((double)player.posX, (double)player.posY, (double)player.posZ, (double)((double)source.posX + 0.5), (double)((double)source.posY + 0.5), (double)((double)source.posZ + 0.5)) >= range)) continue;
                Vector3 sourceVector = new Vector3((double)source.posX + 0.5, (double)source.posY + 0.5, (double)source.posZ + 0.5);
                Vector3 playerVector = Vector3.fromEntityCenter((Entity)player);
                Vector3 motion = sourceVector.copy().sub(playerVector).copy().normalize();
                player.motionX = motion.x;
                player.motionY = 0.2;
                player.motionZ = motion.z;
            }
        }
        if (this.isDead) {
            return;
        }
        int invul = this.getInvulTime();
        int mobTicks = this.getMobSpawnTicks();
        boolean bl3 = spawnMissiles = hard && this.ticksExisted % 15 < 4;
        if (invul > 10) {
            Vector3 pos = Vector3.fromEntityCenter((Entity)this).subtract(new Vector3(0.0, 0.2, 0.0));
            for (int i = 0; i < PYLON_LOCATIONS.length; ++i) {
                int[] arr = PYLON_LOCATIONS[i];
                int x = arr[0];
                int y = arr[1];
                int z = arr[2];
                Vector3 pylonPos = new Vector3((double)(source.posX + x), (double)(source.posY + y), (double)(source.posZ + z));
                double worldTime = this.ticksExisted;
                float rad = 0.75f + (float)Math.random() * 0.05f;
                double xp = pylonPos.x + 0.5 + Math.cos(worldTime /= 5.0) * (double)rad;
                double zp = pylonPos.z + 0.5 + Math.sin(worldTime) * (double)rad;
                Vector3 partPos = new Vector3(xp, pylonPos.y, zp);
                Vector3 mot = pos.copy().sub(partPos).multiply(0.04);
                float r = 0.7f + (float)Math.random() * 0.3f;
                float g = (float)Math.random() * 0.3f;
                float b = 0.7f + (float)Math.random() * 0.3f;
                Botania.proxy.wispFX(this.worldObj, partPos.x, partPos.y, partPos.z, r, g, b, 0.25f + (float)Math.random() * 0.1f, -0.075f - (float)Math.random() * 0.015f);
                Botania.proxy.wispFX(this.worldObj, partPos.x, partPos.y, partPos.z, r, g, b, 0.4f, (float)mot.x, (float)mot.y, (float)mot.z);
            }
        }
        if (invul > 0 && mobTicks == 900) {
            if (invul < 200 && invul > 100 && this.worldObj.rand.nextInt(200 - invul + 1) == 0) {
                for (int i = 0; i < 2; ++i) {
                    this.spawnExplosionParticle();
                }
            }
            if (!this.worldObj.isRemote) {
                this.setHealth(this.getHealth() + (this.getMaxHealth() - 1.0f) / 200.0f);
                this.setInvulTime(invul - 1);
            }
            this.motionY = 0.0;
        } else if (this.isAggored()) {
            boolean dying;
            boolean bl4 = dying = (double)(this.getHealth() / this.getMaxHealth()) < 0.2;
            if (dying && mobTicks > 0) {
                this.motionX = 0.0;
                this.motionY = 0.0;
                this.motionZ = 0.0;
                int reverseTicks = 900 - mobTicks;
                if (reverseTicks < 20) {
                    this.motionY = 0.2;
                    this.setInvulTime(invul + 1);
                }
                if (reverseTicks > 40 && mobTicks > 80 && mobTicks % 80 == 0 && !this.worldObj.isRemote) {
                    for (int pl = 0; pl < playerCount; ++pl) {
                        for (int i = 0; i < 3 + this.worldObj.rand.nextInt(2); ++i) {
                            EntityMob entity = null;
                            switch (this.worldObj.rand.nextInt(2)) {
                                case 0: {
                                    entity = new EntityZombie(this.worldObj);
                                    ItemStack ss1 = new ItemStack(vazkii.botania.common.item.ModItems.terraSword);
                                    ss1.setItemDamage(20);
                                    ItemStack ss2 = new ItemStack(vazkii.botania.common.item.ModItems.terrasteelHelm);
                                    ss2.setItemDamage(20);
                                    ItemStack ss3 = new ItemStack(vazkii.botania.common.item.ModItems.terrasteelChest);
                                    ss3.setItemDamage(20);
                                    ItemStack ss4 = new ItemStack(vazkii.botania.common.item.ModItems.terrasteelLegs);
                                    ss4.setItemDamage(20);
                                    ItemStack ss5 = new ItemStack(vazkii.botania.common.item.ModItems.terrasteelBoots);
                                    ss5.setItemDamage(20);
                                    entity.setCurrentItemOrArmor(0, ss1);
                                    entity.setCurrentItemOrArmor(1, ss2);
                                    entity.setCurrentItemOrArmor(2, ss3);
                                    entity.setCurrentItemOrArmor(3, ss4);
                                    entity.setCurrentItemOrArmor(4, ss5);
                                    entity.setEquipmentDropChance(0, -1.0f);
                                    entity.setEquipmentDropChance(1, -1.0f);
                                    entity.setEquipmentDropChance(2, -1.0f);
                                    entity.setEquipmentDropChance(3, -1.0f);
                                    entity.setEquipmentDropChance(4, -1.0f);
                                    if (this.worldObj.rand.nextInt(hard ? 3 : 12) != 0) break;
                                    entity = new EntityWitch(this.worldObj);
                                    break;
                                }
                                case 1: {
                                    entity = new EntitySkeleton(this.worldObj);
                                    ItemStack ss1 = new ItemStack(vazkii.botania.common.item.ModItems.elementiumSword);
                                    ItemStack ss2 = new ItemStack(vazkii.botania.common.item.ModItems.elementiumHelm);
                                    ItemStack ss3 = new ItemStack(vazkii.botania.common.item.ModItems.elementiumChest);
                                    ItemStack ss4 = new ItemStack(vazkii.botania.common.item.ModItems.elementiumLegs);
                                    ItemStack ss5 = new ItemStack(vazkii.botania.common.item.ModItems.elementiumBoots);
                                    ss1.setItemDamage(20);
                                    ss2.setItemDamage(20);
                                    ss3.setItemDamage(20);
                                    ss4.setItemDamage(20);
                                    ss5.setItemDamage(20);
                                    ((EntitySkeleton)entity).setCurrentItemOrArmor(0, new ItemStack((Item)Items.bow));
                                    ((EntitySkeleton)entity).setCurrentItemOrArmor(1, ss2);
                                    ((EntitySkeleton)entity).setCurrentItemOrArmor(2, ss3);
                                    ((EntitySkeleton)entity).setCurrentItemOrArmor(3, ss4);
                                    ((EntitySkeleton)entity).setCurrentItemOrArmor(4, ss5);
                                    ((EntitySkeleton)entity).setEquipmentDropChance(0, -1.0f);
                                    ((EntitySkeleton)entity).setEquipmentDropChance(1, -1.0f);
                                    ((EntitySkeleton)entity).setEquipmentDropChance(2, -1.0f);
                                    ((EntitySkeleton)entity).setEquipmentDropChance(3, -1.0f);
                                    ((EntitySkeleton)entity).setEquipmentDropChance(4, -1.0f);
                                    if (this.worldObj.rand.nextInt(4) != 0) break;
                                    ((EntitySkeleton)entity).setSkeletonType(1);
                                    ((EntitySkeleton)entity).setCurrentItemOrArmor(0, ss1);
                                    break;
                                }
                                case 3: {
                                    if (players.isEmpty()) break;
                                    for (int j = 0; j < 1 + this.worldObj.rand.nextInt(hard ? 8 : 5); ++j) {
                                        EntityPixie pixie = new EntityPixie(this.worldObj);
                                        pixie.setProps((EntityLivingBase)players.get(this.rand.nextInt(players.size())), (EntityLivingBase)this, 1, 8.0f);
                                        pixie.setPosition(this.posX + (double)(this.width / 2.0f), this.posY + 2.0, this.posZ + (double)(this.width / 2.0f));
                                        this.worldObj.spawnEntityInWorld((Entity)pixie);
                                    }
                                    break;
                                }
                            }
                            if (entity == null) continue;
                            range = 6.0f;
                            entity.setPosition(this.posX + 0.5 + Math.random() * (double)range - (double)(range / 2.0f), this.posY - 1.0, this.posZ + 0.5 + Math.random() * (double)range - (double)(range / 2.0f));
                            this.worldObj.spawnEntityInWorld((Entity)entity);
                        }
                    }
                    if (hard && this.ticksExisted % 3 < 2) {
                        for (int i = 0; i < playerCount; ++i) {
                            this.spawnMissile();
                        }
                        spawnMissiles = false;
                    }
                }
                this.setMobSpawnTicks(mobTicks - 1);
                this.setTPDelay(10);
            } else if (this.getTPDelay() > 0 && !this.worldObj.isRemote) {
                if (invul > 0) {
                    this.setInvulTime(invul - 1);
                }
                this.setTPDelay(this.getTPDelay() - 1);
                if (this.getTPDelay() == 0 && this.getHealth() > 0.0f) {
                    int tries;
                    for (tries = 0; !this.teleportRandomly() && tries < maxTries; ++tries) {
                    }
                    if (tries >= maxTries) {
                        this.teleportTo((double)source.posX + 0.5, (double)source.posY + 1.6, (double)source.posZ + 0.5);
                    }
                    if (!players.isEmpty()) {
                        for (int pl = 0; pl < playerCount; ++pl) {
                            for (int i = 0; i < (this.spawnPixies ? this.worldObj.rand.nextInt(hard ? 6 : 3) : 1); ++i) {
                                EntityPixie pixie = new EntityPixie(this.worldObj);
                                pixie.setProps((EntityLivingBase)players.get(this.rand.nextInt(players.size())), (EntityLivingBase)this, 1, 8.0f);
                                pixie.setPosition(this.posX + (double)(this.width / 2.0f), this.posY + 2.0, this.posZ + (double)(this.width / 2.0f));
                                this.worldObj.spawnEntityInWorld((Entity)pixie);
                            }
                        }
                    }
                    this.setTPDelay(55);
                    this.spawnPixies = false;
                }
            }
            if (spawnMissiles) {
                this.spawnMissile();
            }
        } else {
            range = 3.0f;
            players = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox((double)(this.posX - (double)range), (double)(this.posY - (double)range), (double)(this.posZ - (double)range), (double)(this.posX + (double)range), (double)(this.posY + (double)range), (double)(this.posZ + (double)range)));
            if (!players.isEmpty()) {
                this.damageEntity(DamageSource.causePlayerDamage((EntityPlayer)((EntityPlayer)players.get(0))), 0.0f);
            }
        }
        Botania.proxy.sparkleFX(this.worldObj, this.posX, this.posY, this.posZ, 2.11f, 0.29f, 0.29f, 2.0f + (float)this.hurtTime * 3.0f * (this.getHealth() / this.getMaxHealth()) * (float)Math.max(0, this.worldObj.rand.nextInt(4) - 2), 6);
        if (this.ticksExisted % 35 == 0 && this.onGround) {
            this.spawnCyclone();
        }
        if (this.onGround && this.ticksExisted % 140 == 0 && Math.random() > 0.75) {
            EntityGaiaQuickened g = new EntityGaiaQuickened((EntityLivingBase)this, true, 11.0f);
            g.setPosition(this.posX, this.posY, this.posZ);
            this.worldObj.spawnEntityInWorld((Entity)g);
        }
        this.cleanFluid();
    }

    void cleanFluid() {
        if (!this.worldObj.isRemote) {
            int x = -12;
            while ((float)x < 13.0f) {
                int y = -12;
                while ((float)y < 13.0f) {
                    int z = -12;
                    while ((float)z < 13.0f) {
                        int cx = (int)(this.posX + (double)x);
                        int cy = (int)(this.posY + (double)y);
                        int cz = (int)(this.posZ + (double)z);
                        Block b = this.worldObj.getBlock(cx, cy, cz);
                        if (b instanceof IFluidBlock || b instanceof BlockLiquid) {
                            this.worldObj.setBlockToAir(cx, cy, cz);
                        }
                        ++z;
                    }
                    ++y;
                }
                ++x;
            }
        }
    }

    @Override
    void spawnMissile() {
        if (!this.worldObj.isRemote) {
            EntityMagicMissileII missile = new EntityMagicMissileII((EntityLivingBase)this, true);
            missile.setPosition(this.posX + (Math.random() - 0.05), this.posY + 2.4 + (Math.random() - 0.05), this.posZ + (Math.random() - 0.05));
            if (missile.getTarget()) {
                this.worldObj.playSoundAtEntity((Entity)this, "botania:missile", 0.6f, 0.8f + (float)Math.random() * 0.2f);
                this.worldObj.spawnEntityInWorld((Entity)missile);
            }
        }
    }

    void spawnCyclone() {
        if (!this.worldObj.isRemote) {
            float size = (float)(1.0 + Math.random() * 1.0);
            double rand = 2.0 - Math.random() * 4.0;
            EntityMagicCycloneChaos.spawn(this.worldObj, this.posX, this.posY, this.posZ, size, size);
        }
    }

    public static boolean isCheatyBlock(World world, int x, int y, int z) {
        Block block = world.getBlock(x, y, z);
        String name = Block.blockRegistry.getNameForObject((Object)block);
        return CHEATY_BLOCKS.contains(name);
    }

    @Override
    protected boolean teleportRandomly() {
        double d0 = this.posX + (this.rand.nextDouble() - 0.5) * 64.0;
        double d1 = this.posY + (double)(this.rand.nextInt(64) - 32);
        double d2 = this.posZ + (this.rand.nextDouble() - 0.5) * 64.0;
        return this.teleportTo(d0, d1, d2);
    }

    @Override
    protected boolean teleportTo(double par1, double par3, double par5) {
        List livings;
        int k;
        int j;
        double d3 = this.posX;
        double d4 = this.posY;
        double d5 = this.posZ;
        this.posX = par1;
        this.posY = par3;
        this.posZ = par5;
        boolean flag = false;
        int i = MathHelper.floor_double((double)this.posX);
        if (this.worldObj.blockExists(i, j = MathHelper.floor_double((double)this.posY), k = MathHelper.floor_double((double)this.posZ))) {
            boolean flag1 = false;
            while (!flag1 && j > 0) {
                Block block = this.worldObj.getBlock(i, j - 1, k);
                if (block.getMaterial().blocksMovement()) {
                    flag1 = true;
                    continue;
                }
                this.posY -= 1.0;
                --j;
            }
            if (flag1) {
                this.setPosition(this.posX, this.posY, this.posZ);
                if (this.worldObj.getCollidingBoundingBoxes((Entity)this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox)) {
                    flag = true;
                }
                ChunkCoordinates source = this.getSource();
                if (vazkii.botania.common.core.helper.MathHelper.pointDistanceSpace((double)this.posX, (double)this.posY, (double)this.posZ, (double)source.posX, (double)source.posY, (double)source.posZ) > 12.0f) {
                    flag = false;
                }
            }
        }
        if (!flag) {
            this.setPosition(d3, d4, d5);
            return false;
        }
        int short1 = 128;
        for (int l = 0; l < short1; ++l) {
            double d6 = (double)l / ((double)short1 - 1.0);
            float f = (this.rand.nextFloat() - 0.5f) * 0.2f;
            float f1 = (this.rand.nextFloat() - 0.5f) * 0.2f;
            float f2 = (this.rand.nextFloat() - 0.5f) * 0.2f;
            double d7 = d3 + (this.posX - d3) * d6 + (this.rand.nextDouble() - 0.5) * (double)this.width * 2.0;
            double d8 = d4 + (this.posY - d4) * d6 + this.rand.nextDouble() * (double)this.height;
            double d9 = d5 + (this.posZ - d5) * d6 + (this.rand.nextDouble() - 0.5) * (double)this.width * 2.0;
            this.worldObj.spawnParticle("portal", d7, d8, d9, (double)f, (double)f1, (double)f2);
        }
        if (!this.worldObj.isRemote && (livings = this.worldObj.getEntitiesWithinAABB(EntityGaiaIIIPhantom.class, AxisAlignedBB.getBoundingBox((double)(this.posX - 12.0 - 24.0), (double)(this.posY - 12.0 - 24.0), (double)(this.posZ - 12.0 - 24.0), (double)(this.posX + 12.0 + 25.0), (double)(this.posY + 12.0 + 25.0), (double)(this.posZ + 12.0 + 25.0)))).size() <= 1) {
            EntityGaiaIIIPhantom.spawn(this.worldObj, this.posX, this.posY, this.posZ, this.summoner);
        }
        this.worldObj.playSoundEffect(d3, d4, d5, "mob.endermen.portal", 1.0f, 1.0f);
        this.playSound("mob.endermen.portal", 1.0f, 1.0f);
        return true;
    }

    @Override
    protected void dropFewItems(boolean par1, int par2) {
    }

    @Override
    public boolean generate(World world, Random rand, int cx, int cy, int cz, int p) {
        return this.generate(world, rand, cx, cy, cz, ModBlocks.gaiachest, p);
    }

    @Override
    public boolean generate(World world, Random rand, int cx, int cy, int cz, Block chestBlock, int p) {
        world.rand.setSeed(world.getSeed() * (long)cx + (long)cy ^ (long)cz);
        world.setBlock(cx, cy, cz, chestBlock, 0, 2);
        this.addItemToChest(world, rand, cx, cy, cz, new ItemStack(vazkii.botania.common.item.ModItems.manaResource, p == 0 ? 16 : 8, 5));
        this.addItemToChest(world, rand, cx, cy, cz, new ItemStack(vazkii.botania.common.item.ModItems.ancientWill, 1, rand.nextInt(6)));
        if (ConfigHandler.relicsEnabled) {
            // empty if block
        }
        this.addItemToChest(world, rand, cx, cy, cz, new ItemStack(vazkii.botania.common.item.ModItems.overgrowthSeed, rand.nextInt(3) + 1));
        boolean voidLotus = Math.random() < (double)0.3f;
        this.addItemToChest(world, rand, cx, cy, cz, new ItemStack(vazkii.botania.common.item.ModItems.blackLotus, voidLotus ? 1 : rand.nextInt(3) + 1, voidLotus ? 1 : 0));
        this.addItemToChest(world, rand, cx, cy, cz, new ItemStack(vazkii.botania.common.item.ModItems.manaResource, 24 + rand.nextInt(12)));
        this.addItemToChest(world, rand, cx, cy, cz, new ItemStack(ModItems.recordC));
        this.addItemToChest(world, rand, cx, cy, cz, new ItemStack(vazkii.botania.common.item.ModItems.manaResource, 14 + rand.nextInt(6), 1));
        this.addItemToChest(world, rand, cx, cy, cz, new ItemStack(vazkii.botania.common.item.ModItems.manaResource, 8 + rand.nextInt(3), 2));
        this.addItemToChest(world, rand, cx, cy, cz, new ItemStack(vazkii.botania.common.item.ModItems.manaResource, 8 + rand.nextInt(5), 7));
        this.addItemToChest(world, rand, cx, cy, cz, new ItemStack(vazkii.botania.common.item.ModItems.manaResource, 4 + rand.nextInt(3), 8));
        this.addItemToChest(world, rand, cx, cy, cz, new ItemStack(vazkii.botania.common.item.ModItems.manaResource, 2 + rand.nextInt(3), 9));
        this.addItemToChest(world, rand, cx, cy, cz, new ItemStack(ModItems.boxs));
        this.addItemToChest(world, rand, cx, cy, cz, new ItemStack(ModItems.material, rand.nextInt(3), 3));
        this.addItemToChest(world, rand, cx, cy, cz, new ItemStack(ModItems.material, rand.nextInt(3), 4));
        this.addItemToChest(world, rand, cx, cy, cz, new ItemStack(ModItems.material, rand.nextInt(3), 5));
        this.addItemToChest(world, rand, cx, cy, cz, new ItemStack(ModItems.material, 4 + rand.nextInt(10), 1));
        this.addItemToChest(world, rand, cx, cy, cz, new ItemStack(ModItems.material, 1 + rand.nextInt(3), 2));
        this.addItemToChest(world, rand, cx, cy, cz, new ItemStack(vazkii.botania.common.block.ModBlocks.livingrock, 14 + rand.nextInt(22), 0));
        this.addItemToChest(world, rand, cx, cy, cz, new ItemStack(vazkii.botania.common.block.ModBlocks.livingwood, 14 + rand.nextInt(22), 0));
        if (Math.random() < 0.43) {
            this.addItemToChest(world, rand, cx, cy, cz, new ItemStack(Items.emerald, 2 + rand.nextInt(5), 0));
            this.addItemToChest(world, rand, cx, cy, cz, new ItemStack(Items.fish, 1 + rand.nextInt(4), 3));
            this.addItemToChest(world, rand, cx, cy, cz, new ItemStack(vazkii.botania.common.item.ModItems.gaiaHead));
        }
        if (Math.random() < 0.26) {
            this.addItemToChest(world, rand, cx, cy, cz, new ItemStack(ModItems.material, 1, 0));
            this.addItemToChest(world, rand, cx, cy, cz, new ItemStack(ModItems.material, 2 + rand.nextInt(10), 7));
            this.addItemToChest(world, rand, cx, cy, cz, new ItemStack(ModItems.material, 1, 9));
        }
        if (Math.random() < 0.88) {
            this.addItemToChest(world, rand, cx, cy, cz, new ItemStack(vazkii.botania.common.block.ModBlocks.dreamwood, 7 + rand.nextInt(11), 0));
            this.addItemToChest(world, rand, cx, cy, cz, new ItemStack(vazkii.botania.common.item.ModItems.manaCookie, 4 + rand.nextInt(8), 0));
            this.addItemToChest(world, rand, cx, cy, cz, new ItemStack(vazkii.botania.common.item.ModItems.manaResource, 18 + rand.nextInt(5), 23));
        }
        if (Math.random() < 0.82) {
            this.addItemToChest(world, rand, cx, cy, cz, new ItemStack(ModItems.material, rand.nextInt(3), 6));
        }
        if (Math.random() < 0.72) {
            this.addItemToChest(world, rand, cx, cy, cz, new ItemStack(vazkii.botania.common.item.ModItems.pinkinator));
            this.addItemToChest(world, rand, cx, cy, cz, new ItemStack(ModItems.recordA));
        }
        if (Math.random() < 0.63) {
            this.addItemToChest(world, rand, cx, cy, cz, new ItemStack(ModItems.recordB));
        }
        if (Math.random() < 0.57) {
            int i = Item.getIdFromItem((Item)Items.record_13);
            int j = Item.getIdFromItem((Item)Items.record_wait);
            int k = i + rand.nextInt(j - i + 1);
            this.addItemToChest(world, rand, cx, cy, cz, new ItemStack(Item.getItemById((int)k)));
        }
        if (Math.random() < 0.41) {
            this.addItemToChest(world, rand, cx, cy, cz, new ItemStack(ModItems.heliacalclaymore));
        }
        int runes = rand.nextInt(6) + 1;
        for (int i = 0; i < runes; ++i) {
            this.addItemToChest(world, rand, cx, cy, cz, new ItemStack(vazkii.botania.common.item.ModItems.rune, 3 + rand.nextInt(3), rand.nextInt(16)));
        }
        return true;
    }

    @Override
    protected boolean addItemToChest(World world, Random rand, int cx, int cy, int cz, ItemStack itemStack) {
        int slot;
        TileGaiaChest chest = (TileGaiaChest)world.getTileEntity(cx, cy, cz);
        if (chest != null && (slot = this.findRandomInventorySlot(chest, rand)) != -1) {
            chest.setInventorySlotContents(slot, itemStack);
            return true;
        }
        return false;
    }

    @Override
    protected int findRandomInventorySlot(TileGaiaChest chest, Random rand) {
        for (int i = 0; i < 100; ++i) {
            int slot = rand.nextInt(chest.getSizeInventory());
            if (chest.getStackInSlot(slot) != null) continue;
            return slot;
        }
        return -1;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public ResourceLocation getBossBarTexture() {
        return LibReference.BAR_BOSSB;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public Rectangle getBossBarTextureRect() {
        if (barRect == null) {
            barRect = new Rectangle(0, 0, 185, 15);
        }
        return barRect;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public Rectangle getBossBarHPTextureRect() {
        if (hpBarRect == null) {
            hpBarRect = new Rectangle(0, EntityGaiaIIIDark.barRect.y + EntityGaiaIIIDark.barRect.height, 181, 7);
        }
        return hpBarRect;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void bossBarRenderCallback(ScaledResolution res, int x, int y) {
        GL11.glPushMatrix();
        int px = x + 160;
        int py = y + 12;
        Minecraft mc = Minecraft.getMinecraft();
        ItemStack stack = new ItemStack(Items.skull, 1, 3);
        mc.renderEngine.bindTexture(TextureMap.locationItemsTexture);
        RenderHelper.enableGUIStandardItemLighting();
        GL11.glEnable((int)32826);
        RenderItem.getInstance().renderItemIntoGUI(mc.fontRenderer, mc.renderEngine, stack, px, py);
        RenderHelper.disableStandardItemLighting();
        boolean unicode = mc.fontRenderer.getUnicodeFlag();
        mc.fontRenderer.setUnicodeFlag(true);
        mc.fontRenderer.drawStringWithShadow("" + this.getPlayerCount(), px + 15, py + 4, 0xFFFFFF);
        mc.fontRenderer.setUnicodeFlag(unicode);
        GL11.glPopMatrix();
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public int getBossBarShaderProgram(boolean background) {
        return background ? 0 : ShaderHelper.dopplegangerBar;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public ShaderCallback getBossBarShaderCallback(boolean background, int shader) {
        if (this.shaderCallback == null) {
            this.shaderCallback = new ShaderCallback(){

                public void call(int shader) {
                    int grainIntensityUniform = ARBShaderObjects.glGetUniformLocationARB((int)shader, (CharSequence)"grainIntensity");
                    int hpFractUniform = ARBShaderObjects.glGetUniformLocationARB((int)shader, (CharSequence)"hpFract");
                    float time = EntityGaiaIIIDark.this.getInvulTime();
                    float grainIntensity = time > 20.0f ? 1.0f : Math.max(EntityGaiaIIIDark.this.isHardMode() ? 0.5f : 0.0f, time / 20.0f);
                    ARBShaderObjects.glUniform1fARB((int)grainIntensityUniform, (float)grainIntensity);
                    ARBShaderObjects.glUniform1fARB((int)hpFractUniform, (float)(EntityGaiaIIIDark.this.getHealth() / EntityGaiaIIIDark.this.getMaxHealth()));
                }
            };
        }
        return background ? null : this.shaderCallback;
    }
}

