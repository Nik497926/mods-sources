/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.entity;

import alfheim.common.item.AlfheimItems;
import com.meteor.extrabotany.ExtraBotany;
import com.meteor.extrabotany.common.block.ModBlocks;
import com.meteor.extrabotany.common.block.tile.TileGaiaChest;
import com.meteor.extrabotany.common.entity.EntitySpear;
import com.meteor.extrabotany.common.entity.gaia.EntityGaiaIII;
import com.meteor.extrabotany.common.entity.gaia.EntityMagicLandmineII;
import com.meteor.extrabotany.common.item.ModItems;
import com.meteor.extrabotany.common.item.relic.legendary.armor.ItemOGArmor;
import com.meteor.extrabotany.common.item.relic.legendary.armor.awake.ItemAwakeOGArmor;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
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
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
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
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.GL11;
import thaumcraft.common.config.ConfigItems;
import vazkii.botania.api.boss.IBotaniaBossWithShader;
import vazkii.botania.api.internal.ShaderCallback;
import vazkii.botania.api.lexicon.multiblock.component.MultiblockComponent;
import vazkii.botania.client.core.handler.BossBarHandler;
import vazkii.botania.client.core.helper.ShaderHelper;
import vazkii.botania.common.Botania;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.core.helper.Vector3;
import vazkii.botania.common.entity.EntityDoppleganger;
import vazkii.botania.common.entity.EntityMagicMissile;
import vazkii.botania.common.item.equipment.armor.manasteel.ItemManasteelArmor;

public class EntityExMachine
extends EntityCreature
implements IBotaniaBossWithShader {
    public EntityExMachine summoner;
    private ShaderCallback shaderCallback;
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
    boolean spawnPixies = false;
    private ArrayList<String> playersWhoAttacked = new ArrayList();
    private static final List CHEATY_BLOCKS = Arrays.asList("OpenBlocks:beartrap", "ThaumicTinkerer:magnet");
    private static boolean isPlayingMusic = false;
    private static ModItems instance = new ModItems();
    boolean anyWithArmor = false;
    boolean spawnLandmines = false;
    private int DEFEAT = 0;
    private boolean hasSpawned = false;
    private int spawned_tick = 0;
    private int CD_TP = 0;
    private int CD_SPAWN_L = 0;
    private int TYPE_ATACK = 0;
    private ChunkCoordinates COORDNEW = null;
    @SideOnly(value=Side.CLIENT)
    private static Rectangle barRect;
    @SideOnly(value=Side.CLIENT)
    private static Rectangle hpBarRect;

    public EntityExMachine(World world) {
        super(world);
        this.setSize(0.6f, 1.8f);
        this.getNavigator().setCanSwim(true);
        this.tasks.addTask(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.tasks.addTask(1, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, Float.MAX_VALUE));
        this.tasks.addTask(2, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.tasks.addTask(3, (EntityAIBase)new EntityAIMoveTowardsTarget((EntityCreature)this, 1.0, 48.0f));
        this.isImmuneToFire = true;
        this.experienceValue = 1225;
    }

    private static Boolean checkPylon(World world, int xs, int ys, int zs, EntityPlayer player) {
        for (int[] i$ : PYLON_LOCATIONS) {
            int p = xs + i$[0];
            int x = ys + i$[1];
            int z = zs + i$[2];
            Block y = world.getBlock(p, x, z);
            int meta = world.getBlockMetadata(p, x, z);
            if (y == vazkii.botania.common.block.ModBlocks.pylon && meta == 2) continue;
            if (!world.isRemote) {
                player.addChatMessage(new ChatComponentTranslation("botaniamisc.needsCatalysts", new Object[0]).setChatStyle(new ChatStyle().setColor(EnumChatFormatting.RED)));
            }
            return false;
        }
        return true;
    }

    private static void teleportToSpawnPlayer(EntityPlayer player, World world) {
        ChunkCoordinates coords = world.getSpawnPoint();
        if (vazkii.botania.common.core.helper.MathHelper.pointDistanceSpace((double)((double)coords.posX + 0.5), (double)((double)coords.posY + 0.5), (double)((double)coords.posZ + 0.5), (double)player.posX, (double)player.posY, (double)player.posZ) > 24.0f) {
            player.rotationPitch = 0.0f;
            player.rotationYaw = 0.0f;
            player.setPositionAndUpdate((double)coords.posX + 0.5, (double)coords.posY + 1.6, (double)coords.posZ + 0.5);
            while (!world.getCollidingBoundingBoxes((Entity)player, player.boundingBox).isEmpty()) {
                player.setPositionAndUpdate(player.posX, player.posY + 1.0, player.posZ);
            }
            world.playSoundAtEntity((Entity)player, "mob.endermen.portal", 1.0f, 1.0f);
            for (int i = 0; i < 50; ++i) {
                Botania.proxy.sparkleFX(world, player.posX + Math.random() * (double)player.width, player.posY - 1.6 + Math.random() * (double)player.height, player.posZ + Math.random() * (double)player.width, 0.25f, 1.0f, 0.25f, 1.0f, 10);
            }
        }
    }

    protected List<EntityExMachine> getExs(int x, int y, int z) {
        float range = 25.0f;
        List exs = this.worldObj.getEntitiesWithinAABB(EntityExMachine.class, AxisAlignedBB.getBoundingBox((double)((double)x + 0.5 - (double)range), (double)((double)y + 0.5 - 7.0), (double)((double)z + 0.5 - (double)range), (double)((double)x + 0.5 + (double)range), (double)((double)y + 0.5 + 7.0), (double)((double)z + 0.5 + (double)range)));
        return exs;
    }

    protected List<EntityGaiaIII> getGaia(int x, int y, int z) {
        float range = 25.0f;
        List exs = this.worldObj.getEntitiesWithinAABB(EntityGaiaIII.class, AxisAlignedBB.getBoundingBox((double)((double)x + 0.5 - (double)range), (double)((double)y + 0.5 - 7.0), (double)((double)z + 0.5 - (double)range), (double)((double)x + 0.5 + (double)range), (double)((double)y + 0.5 + 7.0), (double)((double)z + 0.5 + (double)range)));
        return exs;
    }

    protected List<EntityDoppleganger> getDopple(int x, int y, int z) {
        float range = 25.0f;
        List exs = this.worldObj.getEntitiesWithinAABB(EntityDoppleganger.class, AxisAlignedBB.getBoundingBox((double)((double)x + 0.5 - (double)range), (double)((double)y + 0.5 - 7.0), (double)((double)z + 0.5 - (double)range), (double)((double)x + 0.5 + (double)range), (double)((double)y + 0.5 + 7.0), (double)((double)z + 0.5 + (double)range)));
        return exs;
    }

    public static boolean spawn(EntityPlayer player, ItemStack item, World world, int xs, int ys, int zs, boolean hard) {
        if (EntityGaiaIII.hasProperArena(world, xs, ys, zs)) {
            if (world.isRemote) {
                return true;
            }
            int[][] e = PYLON_LOCATIONS;
            int players = e.length;
            if (!EntityExMachine.checkPylon(world, xs, ys, zs, player).booleanValue()) {
                return false;
            }
            if (EntityGaiaIII.hasProperArena(world, xs, ys, zs)) {
                if (world.isRemote) {
                    return true;
                }
                EntityExMachine entity = new EntityExMachine(world);
                List<EntityExMachine> exs = entity.getExs(xs, ys, zs);
                if (!exs.isEmpty()) {
                    player.addChatMessage((IChatComponent)new ChatComponentTranslation("\u00a76ExMachine\u00a7f: \u00a73\u0442\u044b \u043d\u0435 \u043c\u043e\u0436\u0435\u0448\u044c \u0432\u044b\u0437\u0432\u0430\u0442\u044c \u043d\u0430 \u0431\u043e\u0439 \u0431\u043e\u043b\u044c\u0448\u0435 \u00a7dExMachine\u00a73, \u043f\u043e\u043a\u0430 \u043d\u0435 \u043f\u043e\u0431\u0435\u0434\u0438\u0448\u044c \u043c\u0435\u043d\u044f", new Object[0]));
                    return false;
                }
                List<EntityGaiaIII> gaia = entity.getGaia(xs, ys, zs);
                if (!gaia.isEmpty()) {
                    player.addChatMessage((IChatComponent)new ChatComponentTranslation("\u00a76\u0421\u0442\u0440\u0430\u0436 \u0413\u0430\u0439\u0438 III\u00a7f: \u00a73\u043d\u0438\u043a\u0442\u043e \u0431\u043e\u043b\u044c\u0448\u0435 \u043d\u0435 \u043f\u0440\u0438\u0434\u0435\u0442, \u0434\u0430\u0436\u0435 \u043d\u0435 \u043f\u044b\u0442\u0430\u0439\u0441\u044f", new Object[0]));
                    return false;
                }
                List<EntityDoppleganger> dopple = entity.getDopple(xs, ys, zs);
                if (!dopple.isEmpty()) {
                    player.addChatMessage((IChatComponent)new ChatComponentTranslation("\u00a76\u0421\u0442\u0440\u0430\u0436 \u0413\u0430\u0439\u0438\u00a7f: \u00a73\u043d\u0438\u043a\u0442\u043e \u0431\u043e\u043b\u044c\u0448\u0435 \u043d\u0435 \u043f\u0440\u0438\u0434\u0435\u0442, \u0434\u0430\u0436\u0435 \u043d\u0435 \u043f\u044b\u0442\u0430\u0439\u0441\u044f", new Object[0]));
                    return false;
                }
                entity.setPosition((double)xs + 0.5, ys + 3, (double)zs + 0.5);
                entity.setInvulTime(0);
                entity.setHealth(2400.0f);
                entity.setSource(xs, ys, zs);
                entity.setSource(xs, ys, zs);
                entity.setMobSpawnTicks(900);
                List var21 = entity.getPlayersAround();
                int playerCount = 0;
                Iterator var24 = var21.iterator();
                Boolean isAnyManaArmor = false;
                while (var24.hasNext()) {
                    EntityPlayer var26 = (EntityPlayer)var24.next();
                    if (!EntityGaiaIII.isTruePlayer((Entity)var26)) continue;
                    ++playerCount;
                    entity.changeListAtacker(var26);
                    int manaArmor = 0;
                    for (int j = 0; j <= 3; ++j) {
                        ItemStack armPlayer = var26.inventory.armorInventory[j];
                        if (armPlayer == null || !(armPlayer.getItem() instanceof ItemManasteelArmor)) continue;
                        ++manaArmor;
                    }
                    if (manaArmor == 4) {
                        isAnyManaArmor = true;
                        continue;
                    }
                    if (!world.isRemote) {
                        var26.addChatMessage((IChatComponent)new ChatComponentTranslation("\u00a76ExMachine\u00a7f: \u00a73You can't defend yourself, how do you think you can fight me?", new Object[0]));
                    }
                    EntityExMachine.teleportToSpawnPlayer(var26, world);
                }
                if (!isAnyManaArmor.booleanValue()) {
                    return false;
                }
                entity.setPlayerCount(playerCount);
                entity.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.maxHealth).setBaseValue((double)(2400.0f * (float)playerCount));
                world.playSoundAtEntity((Entity)entity, "mob.enderdragon.growl", 10.0f, 0.1f);
                world.spawnEntityInWorld((Entity)entity);
                --item.stackSize;
                if (item.stackSize == 0) {
                    item = null;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    public void changeListAtacker(EntityPlayer pl) {
        this.playersWhoAttacked.add(pl.getCommandSenderName());
        this.dataWatcher.updateObject(31, (Object)this.playersWhoAttacked.size());
    }

    public int getListAtacker() {
        return this.dataWatcher.getWatchableObjectInt(31);
    }

    public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
        Entity e = par1DamageSource.getEntity();
        if (e != null && e instanceof EntityPlayer && EntityGaiaIII.isTruePlayer(e)) {
            if (this.getInvulTime() <= 100) {
                return false;
            }
            EntityPlayer player = (EntityPlayer)e;
            boolean crit = false;
            if (e instanceof EntityPlayer) {
                EntityPlayer cap = (EntityPlayer)e;
                crit = cap.fallDistance > 0.0f && !cap.onGround && !cap.isOnLadder() && !cap.isInWater() && !cap.isPotionActive(Potion.blindness) && cap.ridingEntity == null;
            }
            int cap1 = crit ? 60 : 40;
            cap1 = this.getOgArmor(player) != false ? (int)((double)cap1 * 1.5) : cap1;
            return super.attackEntityFrom(par1DamageSource, Math.min((float)cap1, par2) * (this.isHardMode() ? 0.6f : 1.0f));
        }
        return false;
    }

    public void setTPDelay(int delay) {
        this.dataWatcher.updateObject(22, (Object)delay);
    }

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

    public Boolean getOgArmor(EntityPlayer player) {
        int arm = 0;
        for (int i = 0; i <= 3; ++i) {
            ItemStack var1 = player.inventory.armorInventory[i];
            if (var1 == null || !(var1.getItem() instanceof ItemOGArmor)) continue;
            ++arm;
        }
        if (arm == 4) {
            return true;
        }
        return false;
    }

    public List getPlayersAround() {
        ChunkCoordinates source = this.getSource();
        float range = 15.0f;
        List players = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox((double)((double)source.posX + 0.5 - (double)range), (double)((double)source.posY + 0.5 - (double)range), (double)((double)source.posZ + 0.5 - (double)range), (double)((double)source.posX + 0.5 + (double)range), (double)((double)source.posY + 0.5 + (double)range), (double)((double)source.posZ + 0.5 + (double)range)));
        return players;
    }

    protected void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(20, (Object)0);
        this.dataWatcher.addObject(21, (Object)0);
        this.dataWatcher.addObject(22, (Object)0);
        this.dataWatcher.addObject(23, (Object)0);
        this.dataWatcher.addObject(24, (Object)0);
        this.dataWatcher.addObject(25, (Object)0);
        this.dataWatcher.addObject(26, (Object)0);
        this.dataWatcher.addObject(27, (Object)0);
        this.dataWatcher.addObject(28, (Object)0);
        this.dataWatcher.addObject(29, (Object)0);
        this.dataWatcher.addObject(30, (Object)0);
        this.dataWatcher.addObject(31, (Object)0);
    }

    protected boolean isAIEnabled() {
        return true;
    }

    public void setMobSpawnTicks(int ticks) {
        this.dataWatcher.updateObject(26, (Object)ticks);
    }

    public void setSource(int x, int y, int z) {
        this.dataWatcher.updateObject(23, (Object)x);
        this.dataWatcher.updateObject(24, (Object)y);
        this.dataWatcher.updateObject(25, (Object)z);
    }

    public boolean isHardMode() {
        return this.dataWatcher.getWatchableObjectByte(27) == 1;
    }

    public int getMobSpawnTicks() {
        return this.dataWatcher.getWatchableObjectInt(26);
    }

    public void setInvulTime(int time) {
        this.dataWatcher.updateObject(20, (Object)time);
    }

    @SideOnly(value=Side.CLIENT)
    public int getBossBarShaderProgram(boolean background) {
        return background ? 0 : ShaderHelper.dopplegangerBar;
    }

    @SideOnly(value=Side.CLIENT)
    public ShaderCallback getBossBarShaderCallback(boolean background, int shader) {
        if (this.shaderCallback == null) {
            this.shaderCallback = new ShaderCallback(){

                public void call(int shader) {
                    int grainIntensityUniform = ARBShaderObjects.glGetUniformLocationARB((int)shader, (CharSequence)"grainIntensity");
                    int hpFractUniform = ARBShaderObjects.glGetUniformLocationARB((int)shader, (CharSequence)"hpFract");
                    float time = EntityExMachine.this.getInvulTime();
                    float grainIntensity = 20.0f;
                    ARBShaderObjects.glUniform1fARB((int)grainIntensityUniform, (float)grainIntensity);
                    ARBShaderObjects.glUniform1fARB((int)hpFractUniform, (float)(EntityExMachine.this.getHealth() / EntityExMachine.this.getMaxHealth()));
                }
            };
        }
        return background ? null : this.shaderCallback;
    }

    public int getInvulTime() {
        return this.dataWatcher.getWatchableObjectInt(20);
    }

    @SideOnly(value=Side.CLIENT)
    public ResourceLocation getBossBarTexture() {
        return BossBarHandler.defaultBossBar;
    }

    @SideOnly(value=Side.CLIENT)
    public Rectangle getBossBarTextureRect() {
        if (barRect == null) {
            barRect = new Rectangle(0, 0, 185, 15);
        }
        return barRect;
    }

    @SideOnly(value=Side.CLIENT)
    public Rectangle getBossBarHPTextureRect() {
        if (hpBarRect == null) {
            hpBarRect = new Rectangle(0, EntityExMachine.barRect.y + EntityExMachine.barRect.height, 181, 7);
        }
        return hpBarRect;
    }

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
        mc.fontRenderer.drawStringWithShadow(Integer.toString(this.getPlayerCount()), px + 15, py + 4, 0xFFFFFF);
        mc.fontRenderer.setUnicodeFlag(unicode);
        GL11.glPopMatrix();
    }

    public void writeEntityToNBT(NBTTagCompound cmd) {
        super.writeEntityToNBT(cmd);
        cmd.setInteger(TAG_INVUL_TIME, this.getInvulTime());
        cmd.setBoolean(TAG_AGGRO, this.isAggored());
        cmd.setInteger(TAG_MOB_SPAWN_TICKS, this.getMobSpawnTicks());
        ChunkCoordinates source = this.getSource();
        cmd.setInteger(TAG_SOURCE_X, source.posX);
        cmd.setInteger(TAG_SOURCE_Y, source.posY);
        cmd.setInteger(TAG_SOURCE_Z, source.posZ);
        cmd.setBoolean(TAG_HARD_MODE, this.isHardMode());
        cmd.setInteger(TAG_PLAYER_COUNT, this.getPlayerCount());
        cmd.setBoolean("hasSpawned", this.hasSpawned);
    }

    public void setPlayerCount(int count) {
        this.dataWatcher.updateObject(28, (Object)count);
    }

    public void setAggroed(boolean aggored) {
        this.dataWatcher.updateObject(21, (Object)((byte)(aggored ? 1 : 0)));
    }

    public boolean isAggored() {
        return this.dataWatcher.getWatchableObjectByte(21) == 1;
    }

    public int getPlayerCount() {
        return this.dataWatcher.getWatchableObjectInt(28);
    }

    public ChunkCoordinates getSource() {
        int x = this.dataWatcher.getWatchableObjectInt(23);
        int y = this.dataWatcher.getWatchableObjectInt(24);
        int z = this.dataWatcher.getWatchableObjectInt(25);
        return new ChunkCoordinates(x, y, z);
    }

    public void setHardMode(boolean hardMode) {
        this.dataWatcher.updateObject(27, (Object)((byte)(hardMode ? 1 : 0)));
    }

    public void setStage(int stage) {
        this.dataWatcher.updateObject(29, (Object)stage);
    }

    public int getStage() {
        return this.dataWatcher.getWatchableObjectInt(29);
    }

    public void readEntityFromNBT(NBTTagCompound cmd) {
        super.readEntityFromNBT(cmd);
        this.setInvulTime(cmd.getInteger(TAG_INVUL_TIME));
        this.setAggroed(cmd.getBoolean(TAG_AGGRO));
        this.setMobSpawnTicks(cmd.getInteger(TAG_MOB_SPAWN_TICKS));
        int x = cmd.getInteger(TAG_SOURCE_X);
        int y = cmd.getInteger(TAG_SOURCE_Y);
        int z = cmd.getInteger(TAG_SOURCE_Z);
        this.setSource(x, y, z);
        this.setHardMode(cmd.getBoolean(TAG_HARD_MODE));
        if (cmd.hasKey(TAG_PLAYER_COUNT)) {
            this.setPlayerCount(cmd.getInteger(TAG_PLAYER_COUNT));
        } else {
            this.setPlayerCount(1);
        }
        if (cmd.hasKey("hasSpawned")) {
            this.hasSpawned = cmd.getBoolean("hasSpawned");
        }
    }

    public void onDeath(DamageSource source) {
        super.onDeath(source);
        Entity entity = source.getSourceOfDamage();
        if (entity instanceof EntityPlayer && !((EntityPlayer)entity).worldObj.isRemote) {
            ItemAwakeOGArmor.addStat((EntityPlayer)entity, 1);
        }
        this.loot(this.worldObj, this.worldObj.rand, this.getSource().posX, this.getSource().posY + 1, this.getSource().posZ);
        this.worldObj.playSoundAtEntity((Entity)this, "random.explode", 20.0f, (1.0f + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2f) * 0.7f);
        this.worldObj.spawnParticle("hugeexplosion", this.posX, this.posY, this.posZ, 1.0, 0.0, 0.0);
    }

    public void loot(World world, Random rand, int xs, int ys, int zs) {
        if (world.isAirBlock(xs, ys, zs)) {
            this.installChest(world, xs, ys, zs);
        } else {
            Block var1 = world.getBlock(xs, ys, zs);
            if (var1.getUnlocalizedName() != ModBlocks.gaiachest.getUnlocalizedName()) {
                this.loot(world, rand, xs, ys + 1, zs);
                return;
            }
        }
        TileGaiaChest var2 = (TileGaiaChest)this.worldObj.getTileEntity(xs, ys, zs);
        this.addLoot(var2);
    }

    public void installChest(World world, int xs, int ys, int zs) {
        world.rand.setSeed(world.getSeed() * (long)xs + (long)ys ^ (long)zs);
        world.setBlock(xs, ys, zs, ModBlocks.gaiachest, 0, 2);
    }

    public List<EntityPlayer> getNearPlayer(World world, int xs, int ys, int zs, int RANGE) {
        List players = world.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox((double)(xs - RANGE), (double)(ys - RANGE / 2), (double)(zs - RANGE), (double)(xs + RANGE + 1), (double)(ys + RANGE / 2 + 1), (double)(zs + RANGE + 1)));
        return players;
    }

    public void addLoot(TileGaiaChest chest) {
        ItemStack shema = new ItemStack(ModItems.awakearmcontrol);
        shema.stackSize = this.getCountItems(1, 1);
        this.putItemInChest(shema, chest);
        if (this.getRandomSingl() == 1) {
            ItemStack singl = new ItemStack(ConfigItems.itemEldritchObject);
            singl.setItemDamage(3);
            this.putItemInChest(singl, chest);
        }
        ItemStack seed = new ItemStack(vazkii.botania.common.item.ModItems.overgrowthSeed);
        seed.stackSize = this.getCountItems(2, 1);
        this.putItemInChest(seed, chest);
        int maxRune = 1 + this.rand.nextInt(10);
        for (int k = 0; k < maxRune; ++k) {
            ItemStack rune = new ItemStack(vazkii.botania.common.item.ModItems.rune, 1 + this.rand.nextInt(7));
            rune.setItemDamage(this.rand.nextInt(15));
            this.putItemInChest(rune, chest);
        }
        for (int i = 0; i < 13; ++i) {
            int meta = this.rand.nextInt(23);
            ItemStack v0 = new ItemStack(vazkii.botania.common.item.ModItems.manaResource, 1 + this.rand.nextInt(10), meta == 14 || meta == 4 ? 5 : meta);
            this.putItemInChest(v0, chest);
        }
        int randind = this.rand.nextInt(3);
        if (randind > 0) {
            for (int i = 0; i < randind; ++i) {
                this.putItemInChest(new ItemStack(vazkii.botania.common.item.ModItems.blackLotus), chest);
            }
        }
        if (Math.random() < 0.3) {
            this.putItemInChest(new ItemStack(vazkii.botania.common.item.ModItems.overgrowthSeed, 1 + this.rand.nextInt(3)), chest);
            this.putItemInChest(new ItemStack(ModItems.recordC), chest);
            this.putItemInChest(new ItemStack(vazkii.botania.common.item.ModItems.gaiaHead), chest);
        }
        if (ExtraBotany.alfheimLoaded && this.playersWhoAttacked.size() > 0) {
            for (int i = 0; i < this.playersWhoAttacked.size(); ++i) {
                ItemStack st;
                if (this.playersWhoAttacked.get(i) == null || this.worldObj.getPlayerEntityByName(this.playersWhoAttacked.get(i)) == null) continue;
                EntityPlayer pl = this.worldObj.getPlayerEntityByName(this.playersWhoAttacked.get(i));
                double rnd = Math.random();
                if (rnd < 0.33) {
                    st = new ItemStack(AlfheimItems.INSTANCE.getPriestRingHeimdall());
                    ItemNBTHelper.setString((ItemStack)st, (String)"soulbind", (String)pl.getCommandSenderName());
                    this.putItemInChest(st, chest);
                    continue;
                }
                if (rnd < 0.66) {
                    st = new ItemStack(AlfheimItems.INSTANCE.getPriestRingSif());
                    ItemNBTHelper.setString((ItemStack)st, (String)"soulbind", (String)pl.getCommandSenderName());
                    this.putItemInChest(st, chest);
                    continue;
                }
                st = new ItemStack(AlfheimItems.INSTANCE.getPriestRingNjord());
                ItemNBTHelper.setString((ItemStack)st, (String)"soulbind", (String)pl.getCommandSenderName());
                this.putItemInChest(st, chest);
            }
        }
    }

    private void putItemInChest(ItemStack item, TileGaiaChest chest) {
        int slot = this.addItemLoot(chest);
        if (slot != 99) {
            chest.setInventorySlotContents(slot, item);
        }
    }

    public int getRandomSingl() {
        return Math.random() <= 0.05 ? 1 : 0;
    }

    private int getCountItems(int rnd, int stab) {
        int var1 = 0;
        for (int i = 0; i < this.getPlayerCount(); ++i) {
            int var2 = var1 + (this.rand.nextInt(rnd) + stab);
            if (var2 >= 64) continue;
            var1 = var2;
        }
        return var1;
    }

    private int addItemLoot(TileGaiaChest chest) {
        int i = 99;
        for (int j = 0; j < chest.getSizeInventory(); ++j) {
            ItemStack slot = chest.getStackInSlot(j);
            if (slot != null) continue;
            return j;
        }
        return i;
    }

    public static boolean isCheatyBlock(World world, int x, int y, int z) {
        Block block = world.getBlock(x, y, z);
        String name = Block.blockRegistry.getNameForObject((Object)block);
        return CHEATY_BLOCKS.contains(name);
    }

    public void onLivingUpdate() {
        int i;
        List pls;
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
        if ((pls = this.getPlayersAround()).size() == 0) {
            this.motionX = 0.0;
            this.motionY = 0.0;
            this.motionZ = 0.0;
            this.setPositionAndUpdate((double)this.getSource().posX + 0.5, this.getSource().posY + 2, (double)this.getSource().posZ + 0.5);
            float healForTick = this.getMaxHealth() / 300.0f;
            this.heal(healForTick);
            return;
        }
        this.setInvulTime(this.getInvulTime() + 1);
        for (i = 0; i < 360; i += 8) {
            float r = 0.6f;
            float g = 0.0f;
            float b = 0.2f;
            float m = 0.15f;
            float mv = 0.35f;
            float rad = (float)i * (float)Math.PI / 180.0f;
            double x1 = (double)this.getSource().posX + 0.5 - Math.cos(rad) * 13.0;
            double y1 = (double)this.getSource().posY + 0.5;
            double z1 = (double)this.getSource().posZ + 0.5 - Math.sin(rad) * 13.0;
            Botania.proxy.wispFX(this.worldObj, x1, y1, z1, r, g, b, 0.5f, (float)(Math.random() - 0.5) * m, (float)(Math.random() - 0.5) * mv, (float)(Math.random() - 0.5) * m);
        }
        if (this.getInvulTime() == 0) {
            this.setHealth(1.0f);
        }
        if (this.getInvulTime() < 100) {
            float healForTick = this.getMaxHealth() / 100.0f;
            this.heal(healForTick);
            ++this.spawned_tick;
            this.motionX = 0.0;
            this.motionY = 0.0;
            this.motionZ = 0.0;
            this.setPositionAndUpdate((double)this.getSource().posX + 0.5, this.getSource().posY + 2, (double)this.getSource().posZ + 0.5);
            Vector3 var43 = Vector3.fromEntityCenter((Entity)this).subtract(new Vector3(0.0, 0.2, 0.0));
            for (int i2 = 0; i2 < PYLON_LOCATIONS.length; ++i2) {
                int[] PYLON_L = PYLON_LOCATIONS[i2];
                int xc = PYLON_L[0];
                int yc = PYLON_L[1];
                int zc = PYLON_L[2];
                Vector3 vec = new Vector3((double)(this.getSource().posX + xc), (double)(this.getSource().posY + yc), (double)(this.getSource().posZ + zc));
                double landmine = this.ticksExisted;
                float rad = 0.75f + (float)Math.random() * 0.05f;
                double xp = vec.x + 0.5 + Math.cos(landmine /= 5.0) * (double)rad;
                double zp = vec.z + 0.5 + Math.sin(landmine) * (double)rad;
                Vector3 partPos = new Vector3(xp, vec.y, zp);
                Vector3 mot = var43.copy().sub(partPos).multiply(0.04);
                float r = 0.0235f;
                float g = 0.235f;
                float b1 = 0.22f;
                Botania.proxy.wispFX(this.worldObj, partPos.x, partPos.y, partPos.z, r, g, b1, 0.25f + (float)Math.random() * 0.1f, -0.075f - (float)Math.random() * 0.015f);
                Botania.proxy.wispFX(this.worldObj, partPos.x, partPos.y, partPos.z, r, g, b1, 0.4f, (float)mot.x, (float)mot.y, (float)mot.z);
            }
            return;
        }
        if (this.getInvulTime() == 100) {
            this.setHealth(this.getMaxHealth());
        }
        if (this.getInvulTime() > 100 && this.getInvulTime() < 2147483547) {
            if (this.CD_TP == 0) {
                double rndTP = Math.random();
                if (rndTP <= 0.33) {
                    this.firstRTP();
                } else if (rndTP > 0.33 && rndTP < 0.66) {
                    this.secondRTP();
                } else {
                    this.thirstRTP();
                }
            } else {
                --this.CD_TP;
            }
            if (this.COORDNEW != null) {
                this.motionX = 0.0;
                this.motionY = 0.0;
                this.motionZ = 0.0;
                this.setPositionAndUpdate(this.COORDNEW.posX, this.COORDNEW.posY, this.COORDNEW.posZ);
            }
            if (this.getInvulTime() % 15 == 0) {
                double rndTP = Math.random();
                if (rndTP <= 0.7) {
                    this.firstAtack();
                } else if (rndTP > 0.7 && rndTP < 0.95) {
                    this.secondAtack();
                } else {
                    this.thirdAtack();
                }
            }
            return;
        }
        if (this.getInvulTime() >= 2147483547) {
            for (i = 0; i < pls.size(); ++i) {
                if (this.worldObj.isRemote) continue;
                ((EntityPlayer)pls.get(i)).addChatComponentMessage((IChatComponent)new ChatComponentText(StatCollector.translateToLocal((String)"ExMachina.text.finishTime")));
            }
            EntityItem _a = new EntityItem(this.worldObj, (double)this.getSource().posX, (double)(this.getSource().posY + 1), (double)this.getSource().posZ, new ItemStack(ModItems.elfirium));
            this.worldObj.spawnEntityInWorld((Entity)_a);
            this.setDead();
        }
    }

    private void firstRTP() {
        int max = 7;
        int min = -7;
        int maxY = 7;
        int minY = 0;
        ChunkCoordinates newCoord = new ChunkCoordinates(this.getSource().posX + (this.rand.nextInt(max - min + 1) + min), this.getSource().posY + (this.rand.nextInt(maxY - minY + 1) + minY), this.getSource().posZ + (this.rand.nextInt(max - min + 1) + min));
        while (newCoord.posX == this.getSource().posX && newCoord.posY == this.getSource().posY && newCoord.posZ == this.getSource().posZ) {
            newCoord = new ChunkCoordinates(this.getSource().posX + (this.rand.nextInt(max - min + 1) + min), this.getSource().posY + (this.rand.nextInt(maxY - minY + 1) + minY), this.getSource().posZ + (this.rand.nextInt(max - min + 1) + min));
            if (newCoord.posX == this.getSource().posX && newCoord.posY == this.getSource().posY && newCoord.posZ == this.getSource().posZ) continue;
        }
        this.COORDNEW = newCoord;
        this.setPositionAndUpdate(newCoord.posX, newCoord.posY, newCoord.posZ);
        this.CD_TP = 120;
    }

    private void secondRTP() {
        List<EntityPlayer> pls = this.getNearPlayer(this.worldObj, (int)this.posX, (int)this.posY, (int)this.posZ, 6);
        if (pls.size() == 0) {
            this.CD_TP = 20;
            return;
        }
        int nextPl = this.rand.nextInt(pls.size());
        EntityPlayer nextVictim = pls.get(nextPl);
        Vec3 vec3 = nextVictim.getLookVec();
        double xc = vec3.xCoord * -1.0 + nextVictim.posX;
        double yc = vec3.yCoord * -1.0 + nextVictim.posY;
        double zc = vec3.zCoord * -1.0 + nextVictim.posZ;
        double xxc = this.posX;
        double yyc = this.posY;
        double zzc = this.posZ;
        this.setPositionAndUpdate(xc, yc, zc);
        this.COORDNEW = new ChunkCoordinates((int)xc, (int)yc, (int)zc);
        nextVictim.setPositionAndUpdate(xxc, yyc, zzc);
        this.CD_TP = 120;
    }

    private void thirstRTP() {
        this.motionX = 0.0;
        this.motionY = 0.0;
        this.motionZ = 0.0;
        this.posX = (double)this.getSource().posX + 0.5;
        this.posY = this.getSource().posY + 1;
        this.posZ = (double)this.getSource().posZ + 0.5;
        this.setPositionAndUpdate(this.getSource().posX, this.getSource().posY + 2, this.getSource().posZ);
        this.COORDNEW = new ChunkCoordinates(this.getSource().posX, this.getSource().posY + 2, this.getSource().posZ);
        this.CD_TP = 120;
    }

    private void thirdAtack() {
        List pls = this.getPlayersAround();
        if (pls.size() == 0) {
            this.CD_TP = 20;
            return;
        }
        for (int i = 0; i < pls.size(); ++i) {
            EntitySpear weapon = new EntitySpear(this.worldObj, (EntityLivingBase)pls.get(i));
            weapon.setPosition(((Entity)pls.get((int)i)).posX, ((Entity)pls.get((int)i)).posY + 12.0 + (double)Math.min(2 * i * this.rand.nextInt(55), 20), ((Entity)pls.get((int)i)).posZ);
            weapon.setDelay((int)((float)(i * this.rand.nextInt(55)) * 1.2f));
            this.worldObj.spawnEntityInWorld((Entity)weapon);
            this.worldObj.playSoundAtEntity((Entity)weapon, "botania:babylonSpawn", 1.0f, 1.0f + this.worldObj.rand.nextFloat() * 3.0f);
        }
        this.CD_SPAWN_L = 20;
    }

    private void secondAtack() {
        List pls = this.getPlayersAround();
        for (int i = 0; i < pls.size(); ++i) {
            this.worldObj.addWeatherEffect((Entity)new EntityLightningBolt(this.worldObj, ((EntityPlayer)pls.get((int)i)).posX, ((EntityPlayer)pls.get((int)i)).posY + 1.0, ((EntityPlayer)pls.get((int)i)).posZ));
        }
    }

    private void firstAtack() {
        List pls = this.getPlayersAround();
        EntityMagicLandmineII land = new EntityMagicLandmineII(this.worldObj);
        int xc = this.getSource().posX - 10 + this.rand.nextInt(20);
        int zc = this.getSource().posZ - 10 + this.rand.nextInt(20);
        int yc = this.worldObj.getTopSolidOrLiquidBlock(xc, zc);
        land.setPosition((double)xc + 0.5, yc, (double)zc + 0.5);
        land.summoner2 = this;
        this.worldObj.spawnEntityInWorld((Entity)land);
        this.spawnMissile();
    }

    void spawnMissile() {
        if (!this.worldObj.isRemote) {
            EntityMagicMissile missile = new EntityMagicMissile((EntityLivingBase)this, true);
            missile.setPosition(this.posX + (Math.random() - 0.05), this.posY + 2.4 + (Math.random() - 0.05), this.posZ + (Math.random() - 0.05));
            if (missile.getTarget()) {
                this.worldObj.playSoundAtEntity((Entity)this, "botania:missile", 0.6f, 0.8f + (float)Math.random() * 0.2f);
                this.worldObj.spawnEntityInWorld((Entity)missile);
            }
        }
    }

    private int randomTypeAtack() {
        double _a = Math.random();
        if (_a <= 0.25) {
            return 0;
        }
        if (_a > 0.25 && _a <= 0.5) {
            return 1;
        }
        if (_a > 0.5 && _a <= 0.75) {
            return 2;
        }
        return 3;
    }

    public int getTPDelay() {
        return this.dataWatcher.getWatchableObjectInt(22);
    }

    protected boolean teleportRandomly() {
        double d0 = this.posX + (this.rand.nextDouble() - 0.5) * 40.0;
        double d1 = this.posY + (double)(this.rand.nextInt(64) - 32);
        double d2 = this.posZ + (this.rand.nextDouble() - 0.5) * 40.0;
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
        int i = MathHelper.floor_double((double)this.posX);
        if (this.worldObj.blockExists(i, j = MathHelper.floor_double((double)this.posY), k = MathHelper.floor_double((double)this.posZ))) {
            boolean short1 = false;
            while (!short1 && j > 0) {
                Block block = this.worldObj.getBlock(i, j - 1, k);
                if (block.getMaterial().blocksMovement()) {
                    short1 = true;
                    continue;
                }
                this.posY -= 1.0;
                --j;
            }
            if (short1) {
                this.setPosition(this.posX, this.posY, this.posZ);
                if (this.worldObj.getCollidingBoundingBoxes((Entity)this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox)) {
                    flag = true;
                }
                ChunkCoordinates var31 = this.getSource();
                if (vazkii.botania.common.core.helper.MathHelper.pointDistanceSpace((double)this.posX, (double)this.posY, (double)this.posZ, (double)var31.posX, (double)var31.posY, (double)var31.posZ) > 12.0f) {
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
            this.worldObj.spawnParticle("portal", d7, d8, d9, (double)f, (double)f1, (double)f2);
        }
        this.worldObj.playSoundEffect(d3, d4, d5, "mob.endermen.portal", 1.0f, 1.0f);
        this.playSound("mob.endermen.portal", 1.0f, 1.0f);
        return true;
    }

    public static class BeaconComponent
    extends MultiblockComponent {
        public BeaconComponent(ChunkCoordinates relPos) {
            super(relPos, Blocks.iron_block, 0);
        }

        public boolean matches(World world, int x, int y, int z) {
            return world.getBlock(x, y, z).isBeaconBase((IBlockAccess)world, x, y, z, x - this.relPos.posX, y - this.relPos.posY, z - this.relPos.posZ);
        }
    }

    public static class BeaconBeamComponent
    extends MultiblockComponent {
        public BeaconBeamComponent(ChunkCoordinates relPos) {
            super(relPos, (Block)Blocks.beacon, 0);
        }

        public boolean matches(World world, int x, int y, int z) {
            return world.getTileEntity(x, y, z) instanceof TileEntityBeacon;
        }
    }
}

