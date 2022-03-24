/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.entity.gaia;

import com.meteor.extrabotany.common.achievement.ModAchievement;
import com.meteor.extrabotany.common.block.ModBlocks;
import com.meteor.extrabotany.common.block.tile.TileGaiaChest;
import com.meteor.extrabotany.common.entity.EntityExMachine;
import com.meteor.extrabotany.common.entity.gaia.EntityMagicLandmineII;
import com.meteor.extrabotany.common.item.ModItems;
import com.meteor.extrabotany.common.item.relic.legendary.armor.awake.ItemAwakeOGArmor;
import com.meteor.extrabotany.common.lib.LibReference;
import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
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
import net.minecraft.entity.EntityCreature;
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
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.fluids.IFluidBlock;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.boss.IBotaniaBossWithShader;
import vazkii.botania.api.internal.ShaderCallback;
import vazkii.botania.api.lexicon.multiblock.Multiblock;
import vazkii.botania.api.lexicon.multiblock.MultiblockSet;
import vazkii.botania.api.lexicon.multiblock.component.MultiblockComponent;
import vazkii.botania.client.core.helper.ShaderHelper;
import vazkii.botania.common.Botania;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.core.helper.Vector3;
import vazkii.botania.common.entity.EntityDoppleganger;
import vazkii.botania.common.entity.EntityMagicMissile;
import vazkii.botania.common.entity.EntityPixie;
import vazkii.botania.common.item.relic.ItemRelic;
import vazkii.botania.common.lib.LibObfuscation;

public class EntityGaiaIII
extends EntityCreature
implements IBotaniaBossWithShader {
    public static final int SPAWN_TICKS = 160;
    private static final float RANGE = 10.0f;
    private static final float MAX_HP = 1.0f;
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
    private static final int[][] PYLON_LOCATIONS = new int[][]{{4, 1, 4}, {4, 1, -4}, {-4, 1, 4}, {-4, 1, -4}};
    private static final List CHEATY_BLOCKS = Arrays.asList("OpenBlocks:beartrap", "ThaumicTinkerer:magnet");
    boolean spawnLandmines = false;
    boolean spawnPixies = false;
    boolean anyWithArmor = false;
    List playersWhoAttacked = new ArrayList();
    private static boolean isPlayingMusic = false;
    private static final Pattern FAKE_PLAYER_PATTERN = Pattern.compile("^(?:\\[.*\\])|(?:ComputerCraft)$");
    private static final ModItems instance = new ModItems();
    @SideOnly(value=Side.CLIENT)
    private static Rectangle barRect;
    @SideOnly(value=Side.CLIENT)
    private static Rectangle hpBarRect;
    @SideOnly(value=Side.CLIENT)
    private ShaderCallback shaderCallback;
    private final int DEFEAT = 0;

    public EntityGaiaIII(World par1World) {
        super(par1World);
        this.setSize(0.6f, 1.8f);
        this.getNavigator().setCanSwim(true);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIWatchClosest(this, EntityPlayer.class, Float.MAX_VALUE));
        this.isImmuneToFire = true;
        this.experienceValue = 1225;
    }

    public static MultiblockSet makeMultiblockSet() {
        Multiblock mb = new Multiblock();
        int[][] i = PYLON_LOCATIONS;
        int j = i.length;
        for (int i$ = 0; i$ < j; ++i$) {
            int[] p = i[i$];
            mb.addComponent(p[0], p[1] + 1, p[2], vazkii.botania.common.block.ModBlocks.pylon, 2);
        }
        for (int var5 = 0; var5 < 3; ++var5) {
            for (j = 0; j < 3; ++j) {
                mb.addComponent(new BeaconComponent(new ChunkCoordinates(var5 - 1, 0, j - 1)));
            }
        }
        mb.addComponent(new BeaconBeamComponent(new ChunkCoordinates(0, 1, 0)));
        mb.setRenderOffset(0, -1, 0);
        return mb.makeSet();
    }

    protected List<EntityGaiaIII> getGaia(int x, int y, int z) {
        float range = 25.0f;
        List exs = this.worldObj.getEntitiesWithinAABB(EntityGaiaIII.class, AxisAlignedBB.getBoundingBox((double)x + 0.5 - (double)range, (double)y + 0.5 - 7.0, (double)z + 0.5 - (double)range, (double)x + 0.5 + (double)range, (double)y + 0.5 + 7.0, (double)z + 0.5 + (double)range));
        return exs;
    }

    protected List<EntityDoppleganger> getDopple(int x, int y, int z) {
        float range = 25.0f;
        List exs = this.worldObj.getEntitiesWithinAABB(EntityDoppleganger.class, AxisAlignedBB.getBoundingBox((double)x + 0.5 - (double)range, (double)y + 0.5 - 7.0, (double)z + 0.5 - (double)range, (double)x + 0.5 + (double)range, (double)y + 0.5 + 7.0, (double)z + 0.5 + (double)range));
        return exs;
    }

    protected List<EntityExMachine> getExs(int x, int y, int z) {
        float range = 25.0f;
        List exs = this.worldObj.getEntitiesWithinAABB(EntityExMachine.class, AxisAlignedBB.getBoundingBox((double)x + 0.5 - (double)range, (double)y + 0.5 - 7.0, (double)z + 0.5 - (double)range, (double)x + 0.5 + (double)range, (double)y + 0.5 + 7.0, (double)z + 0.5 + (double)range));
        return exs;
    }

    private static void teleportToSpawnPlayer(EntityPlayer player, World world) {
        ChunkCoordinates coords = world.getSpawnPoint();
        if (vazkii.botania.common.core.helper.MathHelper.pointDistanceSpace((double)coords.posX + 0.5, (double)coords.posY + 0.5, (double)coords.posZ + 0.5, player.posX, player.posY, player.posZ) > 24.0f) {
            player.rotationPitch = 0.0f;
            player.rotationYaw = 0.0f;
            player.setPositionAndUpdate((double)coords.posX + 0.5, (double)coords.posY + 1.6, (double)coords.posZ + 0.5);
            while (!world.getCollidingBoundingBoxes(player, player.boundingBox).isEmpty()) {
                player.setPositionAndUpdate(player.posX, player.posY + 1.0, player.posZ);
            }
            world.playSoundAtEntity(player, "mob.endermen.portal", 1.0f, 1.0f);
            for (int i = 0; i < 50; ++i) {
                Botania.proxy.sparkleFX(world, player.posX + Math.random() * (double)player.width, player.posY - 1.6 + Math.random() * (double)player.height, player.posZ + Math.random() * (double)player.width, 0.25f, 1.0f, 0.25f, 1.0f, 10);
            }
        }
    }

    public static boolean spawn(EntityPlayer player, ItemStack par1ItemStack, World par3World, int par4, int par5, int par6, boolean hard) {
        if (par3World.getTileEntity(par4, par5, par6) instanceof TileEntityBeacon && EntityGaiaIII.isTruePlayer(player)) {
            if (par3World.difficultySetting == EnumDifficulty.PEACEFUL) {
                if (!par3World.isRemote) {
                    player.addChatMessage(new ChatComponentTranslation("botaniamisc.peacefulNoob").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.RED)));
                }
                return false;
            }
            for (int[] i$ : PYLON_LOCATIONS) {
                int p = par4 + i$[0];
                int x = par5 + i$[1];
                int z = par6 + i$[2];
                Block y = par3World.getBlock(p, x, z);
                int meta = par3World.getBlockMetadata(p, x, z);
                if (y == vazkii.botania.common.block.ModBlocks.pylon && meta == 2) continue;
                if (!par3World.isRemote) {
                    player.addChatMessage(new ChatComponentTranslation("botaniamisc.needsCatalysts").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.RED)));
                }
                return false;
            }
            if (EntityGaiaIII.hasProperArena(par3World, par4, par5, par6)) {
                if (par3World.isRemote) {
                    return true;
                }
                EntityGaiaIII var19 = new EntityGaiaIII(par3World);
                List<EntityGaiaIII> var55 = var19.getGaia(par4, par5, par6);
                if (!var55.isEmpty()) {
                    player.addChatMessage(new ChatComponentTranslation("\u00a76\u0421\u0442\u0440\u0430\u0436 \u0413\u0430\u0439\u0438 III\u00a7f: \u00a73\u043d\u0438\u043a\u0442\u043e \u0431\u043e\u043b\u044c\u0448\u0435 \u043d\u0435 \u043f\u0440\u0438\u0434\u0435\u0442, \u0434\u0430\u0436\u0435 \u043d\u0435 \u043f\u044b\u0442\u0430\u0439\u0441\u044f"));
                    return false;
                }
                List<EntityDoppleganger> var56 = var19.getDopple(par4, par5, par6);
                if (!var56.isEmpty()) {
                    player.addChatMessage(new ChatComponentTranslation("\u00a76\u0421\u0442\u0440\u0430\u0436 \u0413\u0430\u0439\u0438\u00a7f: \u00a73\u043d\u0438\u043a\u0442\u043e \u0431\u043e\u043b\u044c\u0448\u0435 \u043d\u0435 \u043f\u0440\u0438\u0434\u0435\u0442, \u0434\u0430\u0436\u0435 \u043d\u0435 \u043f\u044b\u0442\u0430\u0439\u0441\u044f"));
                    return false;
                }
                List<EntityExMachine> var57 = var19.getExs(par4, par5, par6);
                if (!var57.isEmpty()) {
                    player.addChatMessage(new ChatComponentTranslation("\u00a766ExMachine\u00a7f: \u00a73\u043d\u0438\u043a\u0442\u043e \u0431\u043e\u043b\u044c\u0448\u0435 \u043d\u0435 \u043f\u0440\u0438\u0434\u0435\u0442, \u0434\u0430\u0436\u0435 \u043d\u0435 \u043f\u044b\u0442\u0430\u0439\u0441\u044f"));
                    return false;
                }
                var19.setPosition((double)par4 + 0.5, par5 + 3, (double)par6 + 0.5);
                var19.setInvulTime(160);
                var19.setHealth(MAX_HP);
                var19.setSource(par4, par5, par6);
                var19.setMobSpawnTicks(900);
                var19.setHardMode(hard);
                List var21 = var19.getPlayersAround();
                int playerCount = 0;
                Iterator var24 = var21.iterator();
                int limit = 0;
                while (var24.hasNext()) {
                    EntityPlayer var26;
                    if (limit < 3) {
                        ++limit;
                        var26 = (EntityPlayer)var24.next();
                        if (!EntityGaiaIII.isTruePlayer(var26)) continue;
                        ++playerCount;
                        var19.playersWhoAttacked.add(var26.getCommandSenderName());
                        continue;
                    }
                    var26 = (EntityPlayer)var24.next();
                    EntityGaiaIII.teleportToSpawnPlayer(var26, par3World);
                    var26.addChatComponentMessage(new ChatComponentText("\u00a7f[\u00a76Gaurd ExtraBotania\u00a7f]: \u0412\u0432\u0435\u0434\u0435\u043d\u044b \u043e\u0433\u0440\u0430\u043d\u0438\u0447\u0435\u043d\u0438\u044f. \u041d\u0435 \u0431\u043e\u043b\u0435\u0435 3 \u0438\u0433\u0440\u043e\u043a\u043e\u0432 \u043d\u0430 \u0443\u0431\u0438\u0439\u0441\u0442\u0432\u043e Gaia III"));
                }
                var19.setPlayerCount(Math.min(playerCount, 3));
                var19.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.maxHealth).setBaseValue(1800.0f * (float)Math.min(playerCount, 3));
                par3World.playSoundAtEntity(var19, "mob.enderdragon.growl", 10.0f, 0.1f);
                par3World.spawnEntityInWorld(var19);
                --par1ItemStack.stackSize;
                return true;
            }
            for (int var18 = 0; var18 < 360; var18 += 8) {
                float var20 = 1.0f;
                float var22 = 0.0f;
                float var23 = 1.0f;
                float var25 = (float)var18 * (float)Math.PI / 180.0f;
                double var27 = (double)par4 + 0.5 - Math.cos(var25) * 10.0;
                double var28 = (double)par5 + 0.5;
                double z1 = (double)par6 + 0.5 - Math.sin(var25) * 10.0;
                Botania.proxy.sparkleFX(par3World, var27, var28, z1, var20, var22, var23, 5.0f, 120);
            }
            if (!par3World.isRemote) {
                player.addChatMessage(new ChatComponentTranslation("botaniamisc.badArena").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.RED)));
            }
            return false;
        }
        return false;
    }

    public static boolean hasProperArena(World world, int sx, int sy, int sz) {
        int heightCheck = 3;
        int heightMin = 2;
        int range = (int)Math.ceil(10.0);
        for (int i = -range; i < range + 1; ++i) {
            block1: for (int j = -range; j < range + 1; ++j) {
                if (Math.abs(i) == 4 && Math.abs(j) == 4 || vazkii.botania.common.core.helper.MathHelper.pointDistancePlane(i, j, 0.0, 0.0) > 10.0f) continue;
                int x = sx + i;
                int z = sz + j;
                int air = 0;
                for (int k = heightCheck + heightMin + 1; k >= -heightCheck; --k) {
                    int y = sy + k;
                    boolean isAir = world.getBlock(x, y, z).getCollisionBoundingBoxFromPool(world, x, y, z) == null;
                    Block blCheck = Block.getBlockFromName("Natura:Cloud");
                    boolean bl = blCheck != null && !isAir ? world.getBlock(x, y, z) == blCheck : (isAir = isAir);
                    if (isAir && blCheck != null && world.getBlock(x, y, z) == blCheck) {
                        world.setBlockToAir(x, y, z);
                    }
                    if (isAir) {
                        ++air;
                        continue;
                    }
                    if (k > heightCheck) continue;
                    if (air > 2) continue block1;
                    air = 0;
                }
                return false;
            }
        }
        return true;
    }

    protected boolean isAIEnabled() {
        return true;
    }

    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(20, 0);
        this.dataWatcher.addObject(21, 0);
        this.dataWatcher.addObject(22, 0);
        this.dataWatcher.addObject(23, 0);
        this.dataWatcher.addObject(24, 0);
        this.dataWatcher.addObject(25, 0);
        this.dataWatcher.addObject(26, 0);
        this.dataWatcher.addObject(27, 0);
        this.dataWatcher.addObject(28, 0);
    }

    public int getInvulTime() {
        return this.dataWatcher.getWatchableObjectInt(20);
    }

    public boolean isAggored() {
        try {
            return this.dataWatcher.getWatchableObjectInt(21) == 1;
        } catch (Exception e) {

        }

        try {
            return this.dataWatcher.getWatchableObjectByte(21) == 1;
        } catch (Exception e) {

        }

        return false;
    }

    public int getTPDelay() {
        return this.dataWatcher.getWatchableObjectInt(22);
    }

    public ChunkCoordinates getSource() {
        int x = this.dataWatcher.getWatchableObjectInt(23);
        int y = this.dataWatcher.getWatchableObjectInt(24);
        int z = this.dataWatcher.getWatchableObjectInt(25);
        return new ChunkCoordinates(x, y, z);
    }

    public int getMobSpawnTicks() {
        return this.dataWatcher.getWatchableObjectInt(26);
    }

    public boolean isHardMode() {
        try {
            return this.dataWatcher.getWatchableObjectInt(27) == 1;
        } catch (Exception e) {

        }

        try {
            return this.dataWatcher.getWatchableObjectByte(27) == 1;
        } catch (Exception e) {

        }

        return false;
    }

    public int getPlayerCount() {
        return this.dataWatcher.getWatchableObjectInt(28);
    }

    public void setInvulTime(int time) {
        this.dataWatcher.updateObject(20, time);
    }

    public void setAggroed(boolean aggored) {
        this.dataWatcher.updateObject(21, (byte)(aggored ? 1 : 0));
    }

    public void setTPDelay(int delay) {
        this.dataWatcher.updateObject(22, delay);
    }

    public void setSource(int x, int y, int z) {
        this.dataWatcher.updateObject(23, x);
        this.dataWatcher.updateObject(24, y);
        this.dataWatcher.updateObject(25, z);
    }

    public void setMobSpawnTicks(int ticks) {
        this.dataWatcher.updateObject(26, ticks);
    }

    public void setHardMode(boolean hardMode) {
        this.dataWatcher.updateObject(27, (byte)(hardMode ? 1 : 0));
    }

    public void setPlayerCount(int count) {
        this.dataWatcher.updateObject(28, count);
    }

    public void writeEntityToNBT(NBTTagCompound par1nbtTagCompound) {
        super.writeEntityToNBT(par1nbtTagCompound);
        par1nbtTagCompound.setInteger(TAG_INVUL_TIME, this.getInvulTime());
        par1nbtTagCompound.setBoolean(TAG_AGGRO, this.isAggored());
        par1nbtTagCompound.setInteger(TAG_MOB_SPAWN_TICKS, this.getMobSpawnTicks());
        ChunkCoordinates source = this.getSource();
        par1nbtTagCompound.setInteger(TAG_SOURCE_X, source.posX);
        par1nbtTagCompound.setInteger(TAG_SOURCE_Y, source.posY);
        par1nbtTagCompound.setInteger(TAG_SOURCE_Z, source.posZ);
        par1nbtTagCompound.setBoolean(TAG_HARD_MODE, this.isHardMode());
        par1nbtTagCompound.setInteger(TAG_PLAYER_COUNT, this.getPlayerCount());
    }

    public void readEntityFromNBT(NBTTagCompound par1nbtTagCompound) {
        super.readEntityFromNBT(par1nbtTagCompound);
        this.setInvulTime(par1nbtTagCompound.getInteger(TAG_INVUL_TIME));
        this.setAggroed(par1nbtTagCompound.getBoolean(TAG_AGGRO));
        this.setMobSpawnTicks(par1nbtTagCompound.getInteger(TAG_MOB_SPAWN_TICKS));
        int x = par1nbtTagCompound.getInteger(TAG_SOURCE_X);
        int y = par1nbtTagCompound.getInteger(TAG_SOURCE_Y);
        int z = par1nbtTagCompound.getInteger(TAG_SOURCE_Z);
        this.setSource(x, y, z);
        this.setHardMode(par1nbtTagCompound.getBoolean(TAG_HARD_MODE));
        if (par1nbtTagCompound.hasKey(TAG_PLAYER_COUNT)) {
            this.setPlayerCount(par1nbtTagCompound.getInteger(TAG_PLAYER_COUNT));
        } else {
            this.setPlayerCount(1);
        }
    }

    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
        Entity e = par1DamageSource.getEntity();
        if ((par1DamageSource.damageType.equals("player") || e instanceof EntityPixie) && e != null && EntityGaiaIII.isTruePlayer(e) && this.getInvulTime() == 0) {
            if (!this.isAggored()) {
                this.setAggroed(true);
            }
            boolean crit = false;
            if (e instanceof EntityPlayer) {
                EntityPlayer cap = (EntityPlayer)e;
                crit = cap.fallDistance > 0.0f && !cap.onGround && !cap.isOnLadder() && !cap.isInWater() && !cap.isPotionActive(Potion.blindness) && cap.ridingEntity == null;
                float dmg = crit ? Math.min(90.0f, par2) : Math.min(60.0f, par2);
                super.attackEntityFrom(par1DamageSource, dmg);
            }
            return true;
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

    protected void damageEntity(DamageSource par1DamageSource, float par2) {
        super.damageEntity(par1DamageSource, par2);
        Entity attacker = par1DamageSource.getEntity();
        if (attacker != null) {
            Vector3 thisVector = Vector3.fromEntityCenter(this);
            Vector3 playerVector = Vector3.fromEntityCenter(attacker);
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

    public void onDeath(DamageSource source) {
        super.onDeath(source);
        EntityLivingBase entitylivingbase = this.func_94060_bK();
        Entity entity = source.getSourceOfDamage();
        if (entity instanceof EntityPlayer && !entity.worldObj.isRemote) {
            ItemAwakeOGArmor.addStat((EntityPlayer)entity, 0);
        }
        if (entitylivingbase instanceof EntityPlayer) {
            ((EntityPlayer)entitylivingbase).addStat(ModAchievement.Gaia_gaia3Kill, 1);
            if (!this.anyWithArmor) {
                ((EntityPlayer)entitylivingbase).addStat(ModAchievement.Gaia_gaia3NoArmor, 1);
            }
        }
        for (int pl = 0; pl < Math.min(3, this.playersWhoAttacked.size()); ++pl) {
            this.generate(this.worldObj, this.worldObj.rand, this.getSource().posX, this.getSource().posY + 1 + pl, this.getSource().posZ, pl);
        }
        this.worldObj.playSoundAtEntity(this, "random.explode", 20.0f, (1.0f + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2f) * 0.7f);
        this.worldObj.spawnParticle("hugeexplosion", this.posX, this.posY, this.posZ, 1.0, 0.0, 0.0);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.4);
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(1000.0);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0);
    }

    protected boolean canDespawn() {
        return false;
    }

    public boolean generate(World world, Random rand, int cx, int cy, int cz, int p) {
        return this.generate(world, rand, cx, cy, cz, ModBlocks.gaiachest, p);
    }

    public boolean generate(World world, Random rand, int cx, int cy, int cz, Block chestBlock, int p) {
        int i;
        int i2;
        if (world.getTileEntity(cx, cy, cz) != null && world.getTileEntity(cx, cy, cz) instanceof TileGaiaChest) {
            return this.generate(world, rand, cx, cy + 1, cz, p);
        }
        world.rand.setSeed(world.getSeed() * (long)cx + (long)cy ^ (long)cz);
        world.setBlock(cx, cy, cz, chestBlock, 0, 2);
        int randind = 0;
        for (i2 = 0; i2 < 13; ++i2) {
            int meta = rand.nextInt(23);
            ItemStack v0 = new ItemStack(vazkii.botania.common.item.ModItems.manaResource, 1 + rand.nextInt(10), meta == 14 || meta == 4 ? 5 : meta);
            this.addItemToChest(world, rand, cx, cy, cz, v0);
        }
        randind = rand.nextInt(3);
        if (randind > 0) {
            for (i2 = 0; i2 < randind; ++i2) {
                this.addItemToChest(world, rand, cx, cy, cz, new ItemStack(vazkii.botania.common.item.ModItems.blackLotus));
            }
        }
        ItemStack v0 = new ItemStack(ModItems.dice6);
        ItemRelic.bindToUsernameS((String) this.playersWhoAttacked.get(p), v0);
        this.addItemToChest(world, rand, cx, cy, cz, v0);
        if (Math.random() < 0.3) {
            this.addItemToChest(world, rand, cx, cy, cz, new ItemStack(vazkii.botania.common.item.ModItems.overgrowthSeed, 1 + rand.nextInt(3)));
            this.addItemToChest(world, rand, cx, cy, cz, new ItemStack(ModItems.recordC));
            this.addItemToChest(world, rand, cx, cy, cz, new ItemStack(vazkii.botania.common.item.ModItems.gaiaHead));
        }
        if (Math.random() < 0.3) {
            this.addItemToChest(world, rand, cx, cy, cz, new ItemStack(vazkii.botania.common.item.ModItems.pinkinator));
        }
        if (Math.random() < 0.2) {
            this.addItemToChest(world, rand, cx, cy, cz, new ItemStack(ModItems.SGGaia));
        }
        if (Math.random() < 0.7) {
            this.addItemToChest(world, rand, cx, cy, cz, new ItemStack(ModItems.material, 1 + rand.nextInt(2), 0));
        }
        this.addItemToChest(world, rand, cx, cy, cz, new ItemStack(ModItems.material, 2 + rand.nextInt(4), 9));
        this.addItemToChest(world, rand, cx, cy, cz, new ItemStack(ModItems.material, 2 + rand.nextInt(4), 2));
        this.addItemToChest(world, rand, cx, cy, cz, new ItemStack(ModItems.material, 2 + rand.nextInt(2), 12));
        randind = rand.nextInt(4);
        if (randind > 0) {
            for (i = 0; i < randind; ++i) {
                this.addItemToChest(world, rand, cx, cy, cz, new ItemStack(ModItems.material, 1 + rand.nextInt(2), rand.nextInt(8)));
            }
        }
        if ((randind = 5 + rand.nextInt(9)) > 0) {
            for (i = 0; i < randind; ++i) {
                this.addItemToChest(world, rand, cx, cy, cz, new ItemStack(vazkii.botania.common.item.ModItems.rune, 1 + rand.nextInt(5), rand.nextInt(16)));
            }
        }
        this.addItemToChest(world, rand, cx, cy, cz, new ItemStack(ModItems.key, 1 + rand.nextInt(2)));
        randind = rand.nextInt(6);
        for (i = 0; i <= randind; ++i) {
            this.addItemToChest(world, rand, cx, cy, cz, new ItemStack(vazkii.botania.common.item.ModItems.lens, 1, rand.nextInt(13)));
        }
        return true;
    }

    protected boolean addItemToChest(World world, Random rand, int cx, int cy, int cz, ItemStack itemStack) {
        int slot;
        TileGaiaChest chest;
        TileEntity tile = world.getTileEntity(cx, cy, cz);
        if (tile != null && (chest = (TileGaiaChest)tile) != null && (slot = this.findRandomInventorySlot(chest, rand)) != -1) {
            chest.setInventorySlotContents(slot, itemStack);
            return true;
        }
        return false;
    }

    protected int findRandomInventorySlot(TileGaiaChest chest, Random rand) {
        for (int i = 0; i < 100; ++i) {
            int slot = rand.nextInt(chest.getSizeInventory());
            if (chest.getStackInSlot(slot) != null) continue;
            return slot;
        }
        return -1;
    }

    protected void dropFewItems(boolean par1, int par2) {
    }

    public void setDead() {
        ChunkCoordinates source = this.getSource();
        Botania.proxy.playRecordClientSided(this.worldObj, source.posX, source.posY, source.posZ, null);
        isPlayingMusic = false;
        super.setDead();
    }

    public List getPlayersAround() {
        ChunkCoordinates source = this.getSource();
        float range = 15.0f;
        List players = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox((double)source.posX + 0.5 - (double)range, (double)source.posY + 0.5 - (double)range, (double)source.posZ + 0.5 - (double)range, (double)source.posX + 0.5 + (double)range, (double)source.posY + 0.5 + (double)range, (double)source.posZ + 0.5 + (double)range));
        return players;
    }

    public void onLivingUpdate() {
        int y;
        int x;
        int mobTicks;
        int invul;
        int playerCount;
        boolean peaceful;
        super.onLivingUpdate();
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
            int source = 1;
            int hard = MathHelper.floor_double(this.posX);
            int range = MathHelper.floor_double(this.posY);
            int players = MathHelper.floor_double(this.posZ);
            for (playerCount = -source; playerCount < source + 1; ++playerCount) {
                for (invul = -source; invul < source + 1; ++invul) {
                    for (mobTicks = -source; mobTicks < source + 1; ++mobTicks) {
                        int spawnMissiles = hard + playerCount;
                        x = range + invul;
                        y = players + mobTicks;
                        if (!EntityGaiaIII.isCheatyBlock(this.worldObj, spawnMissiles, x, y)) continue;
                        Block z = this.worldObj.getBlock(spawnMissiles, x, y);
                        ArrayList<ItemStack> cx = z.getDrops(this.worldObj, spawnMissiles, x, y, 0, 0);
                        for (ItemStack cz : cx) {
                            if (ConfigHandler.blockBreakParticles) {
                                this.worldObj.playAuxSFX(2001, spawnMissiles, x, y, Block.getIdFromBlock(z) + (this.worldObj.getBlockMetadata(spawnMissiles, x, y) << 12));
                            }
                            this.worldObj.spawnEntityInWorld(new EntityItem(this.worldObj, (double)spawnMissiles + 0.5, (double)x + 0.5, (double)y + 0.5, cz));
                        }
                        this.worldObj.setBlockToAir(spawnMissiles, x, y);
                    }
                }
            }
        }
        ChunkCoordinates var29 = this.getSource();
        boolean var30 = this.isHardMode();
        float var31 = 13.0f;
        List<EntityPlayer> var32 = this.getPlayersAround();
        playerCount = this.getPlayerCount();
        if (this.worldObj.isRemote && !isPlayingMusic && !this.isDead && !var32.isEmpty()) {
            ModItems var10005 = instance;
            Botania.proxy.playRecordClientSided(this.worldObj, var29.posX, var29.posY, var29.posZ, (ItemRecord)ModItems.recordA);
            isPlayingMusic = true;
        }
        var31 = 10.0f;
        for (invul = 0; invul < 360; invul += 8) {
            float var35 = 0.6f;
            float var36 = 0.0f;
            float var39 = 0.2f;
            float var44 = 0.15f;
            float var47 = 0.35f;
            float var51 = (float)invul * (float)Math.PI / 180.0f;
            double var56 = (double)var29.posX + 0.5 - Math.cos(var51) * (double)var31;
            double b = (double)var29.posY + 0.5;
            double z1 = (double)var29.posZ + 0.5 - Math.sin(var51) * (double)var31;
            Botania.proxy.wispFX(this.worldObj, var56, b, z1, var35, var36, var39, 0.5f, (float)(Math.random() - 0.5) * var44, (float)(Math.random() - 0.5) * var47, (float)(Math.random() - 0.5) * var44);
        }
        if (var32.isEmpty() && !this.worldObj.playerEntities.isEmpty()) {
            this.setDead();
        } else {
            for (EntityPlayer var34 : var32) {
                if (var34.inventory.armorInventory[0] != null || var34.inventory.armorInventory[1] != null || var34.inventory.armorInventory[2] != null || var34.inventory.armorInventory[3] != null) {
                    this.anyWithArmor = true;
                }
                ArrayList<PotionEffect> var38 = new ArrayList<PotionEffect>();
                Collection<PotionEffect> var41 = var34.getActivePotionEffects();
                for (PotionEffect var49 : var41) {
                    if (var49.getDuration() >= 200 || !var49.getIsAmbient() || ((Boolean)ReflectionHelper.getPrivateValue(Potion.class, Potion.potionTypes[var49.getPotionID()], LibObfuscation.IS_BAD_EFFECT)).booleanValue()) continue;
                    var38.add(var49);
                }
                var41.removeAll(var38);
                boolean bl2 = var34.capabilities.isFlying = var34.capabilities.isFlying && var34.capabilities.isCreativeMode;
                if (!(vazkii.botania.common.core.helper.MathHelper.pointDistanceSpace(var34.posX, var34.posY, var34.posZ, (double)var29.posX + 0.5, (double)var29.posY + 0.5, (double)var29.posZ + 0.5) >= var31)) continue;
                Vector3 var48 = new Vector3((double)var29.posX + 0.5, (double)var29.posY + 0.5, (double)var29.posZ + 0.5);
                Vector3 var50 = Vector3.fromEntityCenter(var34);
                Vector3 var53 = var48.copy().sub(var50).copy().normalize();
                var34.motionX = var53.x;
                var34.motionY = 0.2;
                var34.motionZ = var53.z;
            }
        }
        if (!this.isDead) {
            boolean var40;
            invul = this.getInvulTime();
            mobTicks = this.getMobSpawnTicks();
            boolean bl3 = var40 = var30 && this.ticksExisted % 15 < 4;
            if (invul > 10) {
                Vector3 var43 = Vector3.fromEntityCenter(this).subtract(new Vector3(0.0, 0.2, 0.0));
                for (y = 0; y < PYLON_LOCATIONS.length; ++y) {
                    int[] var52 = PYLON_LOCATIONS[y];
                    int var55 = var52[0];
                    int var57 = var52[1];
                    int var58 = var52[2];
                    Vector3 var61 = new Vector3(var29.posX + var55, var29.posY + var57, var29.posZ + var58);
                    double landmine = this.ticksExisted;
                    float rad = 0.75f + (float)Math.random() * 0.05f;
                    double xp = var61.x + 0.5 + Math.cos(landmine /= 5.0) * (double)rad;
                    double zp = var61.z + 0.5 + Math.sin(landmine) * (double)rad;
                    Vector3 partPos = new Vector3(xp, var61.y, zp);
                    Vector3 mot = var43.copy().sub(partPos).multiply(0.04);
                    float r = 0.7f + (float)Math.random() * 0.3f;
                    float g = (float)Math.random() * 0.3f;
                    float b1 = 0.7f + (float)Math.random() * 0.3f;
                    Botania.proxy.wispFX(this.worldObj, partPos.x, partPos.y, partPos.z, r, g, b1, 0.25f + (float)Math.random() * 0.1f, -0.075f - (float)Math.random() * 0.015f);
                    Botania.proxy.wispFX(this.worldObj, partPos.x, partPos.y, partPos.z, r, g, b1, 0.4f, (float)mot.x, (float)mot.y, (float)mot.z);
                }
            }
            if (invul > 0 && mobTicks == 900) {
                if (invul < 160 && invul > 80 && this.worldObj.rand.nextInt(160 - invul + 1) == 0) {
                    for (x = 0; x < 2; ++x) {
                        this.spawnExplosionParticle();
                    }
                }
                if (!this.worldObj.isRemote) {
                    this.setHealth(this.getHealth() + (this.getMaxHealth() - 1.0f) / 160.0f);
                    this.setInvulTime(invul - 1);
                }
                this.motionY = 0.0;
            } else if (this.isAggored()) {
                boolean var45;
                boolean bl4 = var45 = (double)(this.getHealth() / this.getMaxHealth()) < 0.2;
                if (var45 && mobTicks > 0) {
                    this.motionX = 0.0;
                    this.motionY = 0.0;
                    this.motionZ = 0.0;
                    y = 900 - mobTicks;
                    if (y < 20) {
                        this.motionY = 0.2;
                        this.setInvulTime(invul + 1);
                    }
                    if (y > 40 && mobTicks > 80 && mobTicks % 80 == 0 && !this.worldObj.isRemote) {
                        int var54;
                        for (var54 = 0; var54 < playerCount; ++var54) {
                            for (int var55 = 0; var55 < 3 + this.worldObj.rand.nextInt(2); ++var55) {
                                EntityMob var60 = null;
                                switch (this.worldObj.rand.nextInt(2)) {
                                    case 0: {
                                        var60 = new EntityZombie(this.worldObj);
                                        if (this.worldObj.rand.nextInt(var30 ? 3 : 12) != 0) break;
                                        var60 = new EntityWitch(this.worldObj);
                                        break;
                                    }
                                    case 1: {
                                        var60 = new EntitySkeleton(this.worldObj);
                                        var60.setCurrentItemOrArmor(0, new ItemStack(Items.bow));
                                        if (this.worldObj.rand.nextInt(8) == 0) {
                                            ((EntitySkeleton)var60).setSkeletonType(1);
                                            var60.setCurrentItemOrArmor(0, new ItemStack(var30 ? vazkii.botania.common.item.ModItems.elementiumSword : Items.stone_sword));
                                        }
                                    }
                                    default: {
                                        break;
                                    }
                                    case 3: {
                                        if (var32.isEmpty()) break;
                                        for (int var58 = 0; var58 < 1 + this.worldObj.rand.nextInt(var30 ? 8 : 5); ++var58) {
                                            EntityPixie var63 = new EntityPixie(this.worldObj);
                                            var63.setProps((EntityLivingBase)var32.get(this.rand.nextInt(var32.size())), this, 1, 8.0f);
                                            var63.setPosition(this.posX + (double)(this.width / 2.0f), this.posY + 2.0, this.posZ + (double)(this.width / 2.0f));
                                            this.worldObj.spawnEntityInWorld(var63);
                                        }
                                    }
                                }
                                if (var60 == null) continue;
                                var31 = 6.0f;
                                var60.setPosition(this.posX + 0.5 + Math.random() * (double)var31 - (double)(var31 / 2.0f), this.posY - 1.0, this.posZ + 0.5 + Math.random() * (double)var31 - (double)(var31 / 2.0f));
                                this.worldObj.spawnEntityInWorld(var60);
                            }
                        }
                        if (var30 && this.ticksExisted % 3 < 2) {
                            for (var54 = 0; var54 < playerCount; ++var54) {
                                this.spawnMissile();
                            }
                            var40 = false;
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
                        for (y = 0; !this.teleportRandomly() && y < 50; ++y) {
                        }
                        if (y >= 50) {
                            this.teleportTo((double)var29.posX + 0.5, (double)var29.posY + 1.6, (double)var29.posZ + 0.5);
                        }
                        if (this.spawnLandmines) {
                            int var54 = var45 && var30 ? 7 : 6;
                            for (int var55 = 0; var55 < var54; ++var55) {
                                int var57 = var29.posX - 10 + this.rand.nextInt(20);
                                int var58 = var29.posZ - 10 + this.rand.nextInt(20);
                                int var62 = this.worldObj.getTopSolidOrLiquidBlock(var57, var58);
                                EntityMagicLandmineII var65 = new EntityMagicLandmineII(this.worldObj);
                                var65.setPosition((double)var57 + 0.5, var62, (double)var58 + 0.5);
                                var65.summoner = this;
                                this.worldObj.spawnEntityInWorld(var65);
                            }
                        }
                        if (!var32.isEmpty()) {
                            for (int var54 = 0; var54 < playerCount; ++var54) {
                                for (int var55 = 0; var55 < (this.spawnPixies ? this.worldObj.rand.nextInt(var30 ? 6 : 3) : 1); ++var55) {
                                    EntityPixie var59 = new EntityPixie(this.worldObj);
                                    var59.setProps((EntityLivingBase)var32.get(this.rand.nextInt(var32.size())), this, 1, 8.0f);
                                    var59.setPosition(this.posX + (double)(this.width / 2.0f), this.posY + 2.0, this.posZ + (double)(this.width / 2.0f));
                                    this.worldObj.spawnEntityInWorld(var59);
                                }
                            }
                        }
                        this.setTPDelay(var30 ? (var45 ? 30 : 40) : (var45 ? 35 : 55));
                        this.spawnLandmines = true;
                        this.spawnPixies = false;
                    }
                }
                if (var40) {
                    this.spawnMissile();
                }
            } else {
                var31 = 3.0f;
                var32 = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(this.posX - (double)var31, this.posY - (double)var31, this.posZ - (double)var31, this.posX + (double)var31, this.posY + (double)var31, this.posZ + (double)var31));
                if (!var32.isEmpty()) {
                    this.damageEntity(DamageSource.causePlayerDamage(var32.get(0)), 0.0f);
                }
            }
            if (!this.worldObj.isRemote) {
                x = -10;
                while ((float)x < 11.0f) {
                    y = -10;
                    while ((float)y < 11.0f) {
                        int var54 = -10;
                        while ((float)var54 < 11.0f) {
                            int var55 = (int)(this.posX + (double)x);
                            int var57 = (int)(this.posY + (double)y);
                            int var58 = (int)(this.posZ + (double)var54);
                            Block var64 = this.worldObj.getBlock(var55, var57, var58);
                            if (var64 instanceof IFluidBlock || var64 instanceof BlockLiquid) {
                                this.worldObj.setBlockToAir(var55, var57, var58);
                            }
                            ++var54;
                        }
                        ++y;
                    }
                    ++x;
                }
            }
        }
    }

    void spawnMissile() {
        if (!this.worldObj.isRemote) {
            EntityMagicMissile missile = new EntityMagicMissile(this, true);
            missile.setPosition(this.posX + (Math.random() - 0.05), this.posY + 2.4 + (Math.random() - 0.05), this.posZ + (Math.random() - 0.05));
            if (missile.getTarget()) {
                this.worldObj.playSoundAtEntity(this, "botania:missile", 0.6f, 0.8f + (float)Math.random() * 0.2f);
                this.worldObj.spawnEntityInWorld(missile);
            }
        }
    }

    public static boolean isCheatyBlock(World world, int x, int y, int z) {
        Block block = world.getBlock(x, y, z);
        String name = Block.blockRegistry.getNameForObject(block);
        return CHEATY_BLOCKS.contains(name);
    }

    protected boolean teleportRandomly() {
        double d0 = this.posX + (this.rand.nextDouble() - 0.5) * 64.0;
        double d1 = this.posY + (double)(this.rand.nextInt(64) - 32);
        double d2 = this.posZ + (this.rand.nextDouble() - 0.5) * 64.0;
        return this.teleportTo(d0, d1, d2);
    }

    protected boolean teleportTo(double par1, double par3, double par5) {
        int k;
        int j;
        double d3 = this.posX;
        double d4 = this.posY;
        double d5 = this.posZ;
        this.posX = par1;
        this.posY = par3;
        this.posZ = par5;
        boolean flag = false;
        int i = MathHelper.floor_double(this.posX);
        if (this.worldObj.blockExists(i, j = MathHelper.floor_double(this.posY), k = MathHelper.floor_double(this.posZ))) {
            boolean short1 = false;
            while (!short1 && j > 0) {
                Block l = this.worldObj.getBlock(i, j - 1, k);
                if (l.getMaterial().blocksMovement()) {
                    short1 = true;
                    continue;
                }
                this.posY -= 1.0;
                --j;
            }
            if (short1) {
                this.setPosition(this.posX, this.posY, this.posZ);
                if (this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox)) {
                    flag = true;
                }
                ChunkCoordinates var31 = this.getSource();
                if (vazkii.botania.common.core.helper.MathHelper.pointDistanceSpace(this.posX, this.posY, this.posZ, var31.posX, var31.posY, var31.posZ) > 12.0f) {
                    flag = false;
                }
            }
        }
        if (!flag) {
            this.setPosition(d3, d4, d5);
            return false;
        }
        int var30 = 128;
        for (int var32 = 0; var32 < var30; ++var32) {
            double d6 = (double)var32 / ((double)var30 - 1.0);
            float f = (this.rand.nextFloat() - 0.5f) * 0.2f;
            float f1 = (this.rand.nextFloat() - 0.5f) * 0.2f;
            float f2 = (this.rand.nextFloat() - 0.5f) * 0.2f;
            double d7 = d3 + (this.posX - d3) * d6 + (this.rand.nextDouble() - 0.5) * (double)this.width * 2.0;
            double d8 = d4 + (this.posY - d4) * d6 + this.rand.nextDouble() * (double)this.height;
            double d9 = d5 + (this.posZ - d5) * d6 + (this.rand.nextDouble() - 0.5) * (double)this.width * 2.0;
            this.worldObj.spawnParticle("portal", d7, d8, d9, f, f1, f2);
        }
        this.worldObj.playSoundEffect(d3, d4, d5, "mob.endermen.portal", 1.0f, 1.0f);
        this.playSound("mob.endermen.portal", 1.0f, 1.0f);
        return true;
    }

    @SideOnly(Side.CLIENT)
    public ResourceLocation getBossBarTexture() {
        return LibReference.BAR_BOSS;
    }

    @SideOnly(Side.CLIENT)
    public Rectangle getBossBarTextureRect() {
        if (barRect == null) {
            barRect = new Rectangle(0, 0, 185, 15);
        }

        return barRect;
    }

    @SideOnly(Side.CLIENT)
    public Rectangle getBossBarHPTextureRect() {
        if (hpBarRect == null) {
            hpBarRect = new Rectangle(0, barRect.y + barRect.height, 181, 7);
        }

        return hpBarRect;
    }

    @SideOnly(Side.CLIENT)
    public void bossBarRenderCallback(ScaledResolution res, int x, int y) {
        GL11.glPushMatrix();
        int px = x + 160;
        int py = y + 12;
        Minecraft mc = Minecraft.getMinecraft();
        ItemStack stack = new ItemStack(Items.skull, 1, 3);
        mc.renderEngine.bindTexture(TextureMap.locationItemsTexture);
        RenderHelper.enableGUIStandardItemLighting();
        GL11.glEnable(32826);
        RenderItem.getInstance().renderItemIntoGUI(mc.fontRenderer, mc.renderEngine, stack, px, py);
        RenderHelper.disableStandardItemLighting();
        boolean unicode = mc.fontRenderer.getUnicodeFlag();
        mc.fontRenderer.setUnicodeFlag(true);
        mc.fontRenderer.drawStringWithShadow("" + this.getPlayerCount(), px + 15, py + 4, 16777215);
        mc.fontRenderer.setUnicodeFlag(unicode);
        GL11.glPopMatrix();
    }

    @SideOnly(Side.CLIENT)
    public int getBossBarShaderProgram(boolean background) {
        return background ? 0 : ShaderHelper.dopplegangerBar;
    }

    @SideOnly(Side.CLIENT)
    public ShaderCallback getBossBarShaderCallback(boolean background, int shader) {
        if (this.shaderCallback == null) {
            this.shaderCallback = new ShaderCallback(){

                public void call(int shader) {
                    int grainIntensityUniform = ARBShaderObjects.glGetUniformLocationARB((int)shader, (CharSequence)"grainIntensity");
                    int hpFractUniform = ARBShaderObjects.glGetUniformLocationARB((int)shader, (CharSequence)"hpFract");
                    float time = EntityGaiaIII.this.getInvulTime();
                    float grainIntensity = time > 20.0f ? 1.0f : Math.max(EntityGaiaIII.this.isHardMode() ? 0.5f : 0.0f, time / 20.0f);
                    ARBShaderObjects.glUniform1fARB((int)grainIntensityUniform, (float)grainIntensity);
                    ARBShaderObjects.glUniform1fARB((int)hpFractUniform, (float)(EntityGaiaIII.this.getHealth() / EntityGaiaIII.this.getMaxHealth()));
                }
            };
        }
        return background ? null : this.shaderCallback;
    }

    public static class BeaconComponent
    extends MultiblockComponent {
        public BeaconComponent(ChunkCoordinates relPos) {
            super(relPos, Blocks.iron_block, 0);
        }

        public boolean matches(World world, int x, int y, int z) {
            return world.getBlock(x, y, z).isBeaconBase(world, x, y, z, x - this.relPos.posX, y - this.relPos.posY, z - this.relPos.posZ);
        }
    }

    public static class BeaconBeamComponent
    extends MultiblockComponent {
        public BeaconBeamComponent(ChunkCoordinates relPos) {
            super(relPos, Blocks.beacon, 0);
        }

        public boolean matches(World world, int x, int y, int z) {
            return world.getTileEntity(x, y, z) instanceof TileEntityBeacon;
        }
    }
}

