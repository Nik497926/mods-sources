/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block.tile;

import com.meteor.extrabotany.common.block.ModBlocks;
import java.util.List;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.api.internal.VanillaPacketDispatcher;
import vazkii.botania.api.mana.BurstProperties;
import vazkii.botania.api.mana.IKeyLocked;
import vazkii.botania.api.mana.ILens;
import vazkii.botania.api.mana.ILensControl;
import vazkii.botania.api.mana.ILensEffect;
import vazkii.botania.api.mana.IManaCollector;
import vazkii.botania.api.mana.IManaPool;
import vazkii.botania.api.mana.IManaReceiver;
import vazkii.botania.api.mana.IManaSpreader;
import vazkii.botania.api.mana.IRedirectable;
import vazkii.botania.api.mana.IThrottledPacket;
import vazkii.botania.api.mana.ManaNetworkEvent;
import vazkii.botania.api.wand.IWandBindable;
import vazkii.botania.client.core.handler.HUDHandler;
import vazkii.botania.common.block.tile.TileSimpleInventory;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.core.handler.ManaNetworkHandler;
import vazkii.botania.common.core.helper.Vector3;
import vazkii.botania.common.entity.EntityManaBurst;

public class TileExtraSpreader
extends TileSimpleInventory
implements IManaCollector,
IWandBindable,
IKeyLocked,
IThrottledPacket,
IManaSpreader,
IRedirectable {
    private static final int TICKS_ALLOWED_WITHOUT_PINGBACK = 20;
    private static final double PINGBACK_EXPIRED_SEARCH_DISTANCE = 0.5;
    private static final String TAG_HAS_IDENTITY = "hasIdentity";
    private static final String TAG_UUID_MOST = "uuidMost";
    private static final String TAG_UUID_LEAST = "uuidLeast";
    private static final String TAG_MANA = "mana";
    private static final String TAG_KNOWN_MANA = "knownMana";
    private static final String TAG_REQUEST_UPDATE = "requestUpdate";
    private static final String TAG_ROTATION_X = "rotationX";
    private static final String TAG_ROTATION_Y = "rotationY";
    private static final String TAG_PADDING_COLOR = "paddingColor";
    private static final String TAG_CAN_SHOOT_BURST = "canShootBurst";
    private static final String TAG_PINGBACK_TICKS = "pingbackTicks";
    private static final String TAG_LAST_PINGBACK_X = "lastPingbackX";
    private static final String TAG_LAST_PINGBACK_Y = "lastPingbackY";
    private static final String TAG_LAST_PINGBACK_Z = "lastPingbackZ";
    private static final String TAG_FORCE_CLIENT_BINDING_X = "forceClientBindingX";
    private static final String TAG_FORCE_CLIENT_BINDING_Y = "forceClientBindingY";
    private static final String TAG_FORCE_CLIENT_BINDING_Z = "forceClientBindingZ";
    private static final String TAG_INPUT_KEY = "inputKey";
    private static final String TAG_OUTPUT_KEY = "outputKey";
    private static final String TAG_MAPMAKER_OVERRIDE = "mapmakerOverrideEnabled";
    private static final String TAG_FORCED_COLOR = "mmForcedColor";
    private static final String TAG_FORCED_MANA_PAYLOAD = "mmForcedManaPayload";
    private static final String TAG_FORCED_TICKS_BEFORE_MANA_LOSS = "mmForcedTicksBeforeManaLoss";
    private static final String TAG_FORCED_MANA_LOSS_PER_TICK = "mmForcedManaLossPerTick";
    private static final String TAG_FORCED_GRAVITY = "mmForcedGravity";
    private static final String TAG_FORCED_VELOCITY_MULTIPLIER = "mmForcedVelocityMultiplier";
    boolean mapmakerOverride = false;
    int mmForcedColor = 0x20FF20;
    int mmForcedManaPayload = -1;
    int mmForcedTicksBeforeManaLoss = 60;
    float mmForcedManaLossPerTick = 4.0f;
    float mmForcedGravity = 0.0f;
    float mmForcedVelocityMultiplier = 1.0f;
    String inputKey = "";
    String outputKey = "";
    public static boolean staticRedstone = false;
    public static boolean staticDreamwood = false;
    public static boolean staticUltra = false;
    UUID identity;
    int mana;
    int knownMana = -1;
    public float rotationX;
    public float rotationY;
    public int paddingColor = -1;
    boolean requestsClientUpdate = false;
    boolean hasReceivedInitialPacket = false;
    IManaReceiver receiver = null;
    IManaReceiver receiverLastTick = null;
    boolean redstoneLastTick = true;
    public boolean canShootBurst = true;
    public int lastBurstDeathTick = -1;
    public int burstParticleTick = 0;
    public int pingbackTicks = 0;
    public double lastPingbackX = 0.0;
    public double lastPingbackY = -1.0;
    public double lastPingbackZ = 0.0;
    List<EntityManaBurst.PositionProperties> lastTentativeBurst;
    boolean invalidTentativeBurst = false;

    public boolean isFull() {
        return this.mana >= this.getMaxMana();
    }

    public void recieveMana(int mana) {
        this.mana = Math.min(this.mana + mana, this.getMaxMana());
    }

    public void invalidate() {
        super.invalidate();
        ManaNetworkEvent.removeCollector(this);
    }

    public void onChunkUnload() {
        super.onChunkUnload();
        this.invalidate();
    }

    public void updateEntity() {
        ItemStack lens;
        ILensControl control;
        boolean inNetwork;
        boolean wasInNetwork = inNetwork = ManaNetworkHandler.instance.isCollectorIn(this);
        if (!inNetwork && !this.isInvalid()) {
            ManaNetworkEvent.addCollector(this);
            inNetwork = true;
        }
        if (this.mmForcedManaPayload == -1) {
            this.mmForcedManaPayload = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord) == 1 ? 116640 : 12960;
            VanillaPacketDispatcher.dispatchTEToNearbyPlayers(this);
        }
        boolean redstone = false;
        for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
            int redstoneSide;
            TileEntity tileAt = this.worldObj.getTileEntity(this.xCoord + dir.offsetX, this.yCoord + dir.offsetY, this.zCoord + dir.offsetZ);
            if (tileAt instanceof IManaPool) {
                IManaPool pool = (IManaPool)tileAt;
                if (wasInNetwork && (pool != this.receiver || this.isRedstone())) {
                    if (pool instanceof IKeyLocked && !((IKeyLocked)pool).getOutputKey().equals(this.getInputKey())) continue;
                    int manaInPool = pool.getCurrentMana();
                    if (manaInPool > 0 && !this.isFull()) {
                        int manaMissing = this.getMaxMana() - this.mana;
                        int manaToRemove = Math.min(manaInPool, manaMissing);
                        pool.recieveMana(-manaToRemove);
                        this.recieveMana(manaToRemove);
                    }
                }
            }
            if ((redstoneSide = this.worldObj.getIndirectPowerLevelTo(this.xCoord + dir.offsetX, this.yCoord + dir.offsetY, this.zCoord + dir.offsetZ, dir.ordinal())) <= 0) continue;
            redstone = true;
        }
        if (this.needsNewBurstSimulation()) {
            this.checkForReceiver();
        }
        if (!this.canShootBurst) {
            if (this.pingbackTicks <= 0) {
                double x = this.lastPingbackX;
                double y = this.lastPingbackY;
                double z = this.lastPingbackZ;
                AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(x, y, z, x, y, z).expand(0.5, 0.5, 0.5);
                List<IManaBurst> bursts = this.worldObj.getEntitiesWithinAABB(IManaBurst.class, aabb);
                IManaBurst found = null;
                UUID identity = this.getIdentifier();
                for (IManaBurst burst : bursts) {
                    if (burst == null || !identity.equals(burst.getShooterUIID())) continue;
                    found = burst;
                    break;
                }
                if (found != null) {
                    found.ping();
                } else {
                    this.setCanShoot(true);
                }
            } else {
                --this.pingbackTicks;
            }
        }
        boolean shouldShoot = !redstone;
        boolean isredstone = this.isRedstone();
        if (isredstone) {
            boolean bl = shouldShoot = redstone && !this.redstoneLastTick;
        }
        if (shouldShoot && this.receiver != null && this.receiver instanceof IKeyLocked) {
            shouldShoot = ((IKeyLocked)this.receiver).getInputKey().equals(this.getOutputKey());
        }
        if ((control = this.getLensController(lens = this.getStackInSlot(0))) != null) {
            if (isredstone) {
                if (shouldShoot) {
                    control.onControlledSpreaderPulse(lens, this, redstone);
                }
            } else {
                control.onControlledSpreaderTick(lens, this, redstone);
            }
            shouldShoot &= control.allowBurstShooting(lens, this, redstone);
        }
        if (shouldShoot) {
            this.tryShootBurst();
        }
        if (this.receiverLastTick != this.receiver && !this.worldObj.isRemote) {
            this.requestsClientUpdate = true;
            VanillaPacketDispatcher.dispatchTEToNearbyPlayers(this.worldObj, this.xCoord, this.yCoord, this.zCoord);
        }
        this.redstoneLastTick = redstone;
        this.receiverLastTick = this.receiver;
    }

    public void writeCustomNBT(NBTTagCompound cmp) {
        super.writeCustomNBT(cmp);
        UUID identity = this.getIdentifier();
        cmp.setBoolean(TAG_HAS_IDENTITY, true);
        cmp.setLong(TAG_UUID_MOST, identity.getMostSignificantBits());
        cmp.setLong(TAG_UUID_LEAST, identity.getLeastSignificantBits());
        cmp.setInteger(TAG_MANA, this.mana);
        cmp.setFloat(TAG_ROTATION_X, this.rotationX);
        cmp.setFloat(TAG_ROTATION_Y, this.rotationY);
        cmp.setBoolean(TAG_REQUEST_UPDATE, this.requestsClientUpdate);
        cmp.setInteger(TAG_PADDING_COLOR, this.paddingColor);
        cmp.setBoolean(TAG_CAN_SHOOT_BURST, this.canShootBurst);
        cmp.setInteger(TAG_PINGBACK_TICKS, this.pingbackTicks);
        cmp.setDouble(TAG_LAST_PINGBACK_X, this.lastPingbackX);
        cmp.setDouble(TAG_LAST_PINGBACK_Y, this.lastPingbackY);
        cmp.setDouble(TAG_LAST_PINGBACK_Z, this.lastPingbackZ);
        cmp.setString(TAG_INPUT_KEY, this.inputKey);
        cmp.setString(TAG_OUTPUT_KEY, this.outputKey);
        cmp.setInteger(TAG_FORCE_CLIENT_BINDING_X, this.receiver == null ? 0 : ((TileEntity)this.receiver).xCoord);
        cmp.setInteger(TAG_FORCE_CLIENT_BINDING_Y, this.receiver == null ? -1 : ((TileEntity)this.receiver).yCoord);
        cmp.setInteger(TAG_FORCE_CLIENT_BINDING_Z, this.receiver == null ? 0 : ((TileEntity)this.receiver).zCoord);
        cmp.setBoolean(TAG_MAPMAKER_OVERRIDE, this.mapmakerOverride);
        cmp.setInteger(TAG_FORCED_COLOR, this.mmForcedColor);
        cmp.setInteger(TAG_FORCED_MANA_PAYLOAD, this.mmForcedManaPayload);
        cmp.setInteger(TAG_FORCED_TICKS_BEFORE_MANA_LOSS, this.mmForcedTicksBeforeManaLoss);
        cmp.setFloat(TAG_FORCED_MANA_LOSS_PER_TICK, this.mmForcedManaLossPerTick);
        cmp.setFloat(TAG_FORCED_GRAVITY, this.mmForcedGravity);
        cmp.setFloat(TAG_FORCED_VELOCITY_MULTIPLIER, this.mmForcedVelocityMultiplier);
        this.requestsClientUpdate = false;
    }

    public void readCustomNBT(NBTTagCompound cmp) {
        super.readCustomNBT(cmp);
        if (cmp.getBoolean(TAG_HAS_IDENTITY)) {
            long most = cmp.getLong(TAG_UUID_MOST);
            long least = cmp.getLong(TAG_UUID_LEAST);
            UUID identity = this.getIdentifierUnsafe();
            if (identity == null || most != identity.getMostSignificantBits() || least != identity.getLeastSignificantBits()) {
                identity = new UUID(most, least);
            }
        } else {
            this.getIdentifier();
        }
        this.mana = cmp.getInteger(TAG_MANA);
        this.rotationX = cmp.getFloat(TAG_ROTATION_X);
        this.rotationY = cmp.getFloat(TAG_ROTATION_Y);
        this.requestsClientUpdate = cmp.getBoolean(TAG_REQUEST_UPDATE);
        if (cmp.hasKey(TAG_INPUT_KEY)) {
            this.inputKey = cmp.getString(TAG_INPUT_KEY);
        }
        if (cmp.hasKey(TAG_OUTPUT_KEY)) {
            this.inputKey = cmp.getString(TAG_OUTPUT_KEY);
        }
        this.mapmakerOverride = cmp.getBoolean(TAG_MAPMAKER_OVERRIDE);
        this.mmForcedColor = cmp.getInteger(TAG_FORCED_COLOR);
        this.mmForcedManaPayload = cmp.getInteger(TAG_FORCED_MANA_PAYLOAD);
        this.mmForcedTicksBeforeManaLoss = cmp.getInteger(TAG_FORCED_TICKS_BEFORE_MANA_LOSS);
        this.mmForcedManaLossPerTick = cmp.getFloat(TAG_FORCED_MANA_LOSS_PER_TICK);
        this.mmForcedGravity = cmp.getFloat(TAG_FORCED_GRAVITY);
        this.mmForcedVelocityMultiplier = cmp.getFloat(TAG_FORCED_VELOCITY_MULTIPLIER);
        if (cmp.hasKey(TAG_KNOWN_MANA)) {
            this.knownMana = cmp.getInteger(TAG_KNOWN_MANA);
        }
        if (cmp.hasKey(TAG_PADDING_COLOR)) {
            this.paddingColor = cmp.getInteger(TAG_PADDING_COLOR);
        }
        if (cmp.hasKey(TAG_CAN_SHOOT_BURST)) {
            this.canShootBurst = cmp.getBoolean(TAG_CAN_SHOOT_BURST);
        }
        this.pingbackTicks = cmp.getInteger(TAG_PINGBACK_TICKS);
        this.lastPingbackX = cmp.getDouble(TAG_LAST_PINGBACK_X);
        this.lastPingbackY = cmp.getDouble(TAG_LAST_PINGBACK_Y);
        this.lastPingbackZ = cmp.getDouble(TAG_LAST_PINGBACK_Z);
        if (this.requestsClientUpdate && this.worldObj != null) {
            TileEntity tile;
            int x = cmp.getInteger(TAG_FORCE_CLIENT_BINDING_X);
            int y = cmp.getInteger(TAG_FORCE_CLIENT_BINDING_Y);
            int z = cmp.getInteger(TAG_FORCE_CLIENT_BINDING_Z);
            this.receiver = y != -1 ? ((tile = this.worldObj.getTileEntity(x, y, z)) instanceof IManaReceiver ? (IManaReceiver)tile : null) : null;
        }
        if (this.worldObj != null && this.worldObj.isRemote) {
            this.hasReceivedInitialPacket = true;
        }
    }

    public boolean canRecieveManaFromBursts() {
        return true;
    }

    public int getCurrentMana() {
        return this.mana;
    }

    public void onWanded(EntityPlayer player, ItemStack wand) {
        if (player == null) {
            return;
        }
        if (!player.isSneaking()) {
            if (!this.worldObj.isRemote) {
                NBTTagCompound nbttagcompound = new NBTTagCompound();
                this.writeCustomNBT(nbttagcompound);
                nbttagcompound.setInteger(TAG_KNOWN_MANA, this.mana);
                if (player instanceof EntityPlayerMP) {
                    ((EntityPlayerMP)player).playerNetServerHandler.sendPacket(new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, -999, nbttagcompound));
                }
            }
            this.worldObj.playSoundAtEntity(player, "botania:ding", 0.1f, 1.0f);
        } else {
            MovingObjectPosition pos = TileExtraSpreader.raytraceFromEntity(this.worldObj, player, true, 5.0);
            if (pos != null && pos.hitVec != null && !this.worldObj.isRemote) {
                double x = pos.hitVec.xCoord - (double)this.xCoord - 0.5;
                double y = pos.hitVec.yCoord - (double)this.yCoord - 0.5;
                double z = pos.hitVec.zCoord - (double)this.zCoord - 0.5;
                if (pos.sideHit != 0 && pos.sideHit != 1) {
                    Vector3 clickVector = new Vector3(x, 0.0, z);
                    Vector3 relative = new Vector3(-0.5, 0.0, 0.0);
                    double angle = Math.acos(clickVector.dotProduct(relative) / (relative.mag() * clickVector.mag())) * 180.0 / Math.PI;
                    this.rotationX = (float)angle + 180.0f;
                    if (clickVector.z < 0.0) {
                        this.rotationX = 360.0f - this.rotationX;
                    }
                }
                double angle = y * 180.0;
                this.rotationY = -((float)angle);
                this.checkForReceiver();
                this.requestsClientUpdate = true;
                VanillaPacketDispatcher.dispatchTEToNearbyPlayers(this.worldObj, this.xCoord, this.yCoord, this.zCoord);
            }
        }
    }

    private boolean needsNewBurstSimulation() {
        if (this.worldObj.isRemote && !this.hasReceivedInitialPacket) {
            return false;
        }
        if (this.lastTentativeBurst == null) {
            return true;
        }
        for (EntityManaBurst.PositionProperties props : this.lastTentativeBurst) {
            if (props.contentsEqual(this.worldObj)) continue;
            this.invalidTentativeBurst = props.invalid;
            return !this.invalidTentativeBurst;
        }
        return false;
    }

    public void tryShootBurst() {
        EntityManaBurst burst;
        if ((this.receiver != null || this.isRedstone()) && !this.invalidTentativeBurst && this.canShootBurst && (this.isRedstone() || this.receiver.canRecieveManaFromBursts() && !this.receiver.isFull()) && (burst = this.getBurst(false)) != null && !this.worldObj.isRemote) {
            this.mana -= burst.getStartingMana();
            burst.setShooterUUID(this.getIdentifier());
            this.worldObj.spawnEntityInWorld(burst);
            burst.ping();
            if (!ConfigHandler.silentSpreaders) {
                this.worldObj.playSoundEffect(this.xCoord, this.yCoord, this.zCoord, "botania:spreaderFire", 0.05f * (this.paddingColor != -1 ? 0.2f : 1.0f), 0.7f + 0.3f * (float)Math.random());
            }
        }
    }

    public boolean isRedstone() {
        return false;
    }

    public boolean isDreamwood() {
        return this.worldObj == null ? staticDreamwood : this.getBlockMetadata() == 2 || this.getBlockMetadata() == 3;
    }

    public boolean isULTRA_SPREADER() {
        return this.worldObj == null ? staticUltra : this.getBlockMetadata() == 3;
    }

    public void checkForReceiver() {
        ItemStack stack = this.getStackInSlot(0);
        ILensControl control = this.getLensController(stack);
        if (control != null && !control.allowBurstShooting(stack, this, false)) {
            return;
        }
        EntityManaBurst fakeBurst = this.getBurst(true);
        fakeBurst.setScanBeam();
        TileEntity receiver = fakeBurst.getCollidedTile(true);
        this.receiver = receiver != null && receiver instanceof IManaReceiver ? (IManaReceiver)receiver : null;
        this.lastTentativeBurst = fakeBurst.propsList;
    }

    public EntityManaBurst getBurst(boolean fake) {
        float manaLossPerTick;
        int color = 0;
        int maxMana;
        EntityManaBurst burst = new EntityManaBurst(this, fake);
        boolean dreamwood = this.isDreamwood();
        boolean ultra = this.isULTRA_SPREADER();
        int n = maxMana = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord) == 1 ? 116640 : 12960;
        int n2 = this.isRedstone() ? 0xFF2020 : (color = dreamwood ? 16729540 : 0x20FF20);
        int ticksBeforeManaLoss = ultra ? 120 : (dreamwood ? 80 : 60);
        float f = manaLossPerTick = ultra ? 20.0f : 4.0f;
        float motionModifier = ultra ? 2.0f : (dreamwood ? 1.25f : 1.0f);
        float gravity = 0.0f;
        BurstProperties props = new BurstProperties(maxMana, ticksBeforeManaLoss, manaLossPerTick, gravity, motionModifier, n2);
        ItemStack lens = this.getStackInSlot(0);
        if (lens != null && lens.getItem() instanceof ILensEffect) {
            ((ILensEffect)lens.getItem()).apply(lens, props);
        }
        burst.setSourceLens(lens);
        if (this.getCurrentMana() >= props.maxMana || fake) {
            if (this.mapmakerOverride) {
                burst.setColor(this.mmForcedColor);
                burst.setMana(this.mmForcedManaPayload);
                burst.setStartingMana(this.mmForcedManaPayload);
                burst.setMinManaLoss(this.mmForcedTicksBeforeManaLoss);
                burst.setManaLossPerTick(this.mmForcedManaLossPerTick);
                burst.setGravity(this.mmForcedGravity);
                burst.setMotion(burst.motionX * (double)this.mmForcedVelocityMultiplier, burst.motionY * (double)this.mmForcedVelocityMultiplier, burst.motionZ * (double)this.mmForcedVelocityMultiplier);
            } else {
                burst.setColor(props.color);
                burst.setMana(props.maxMana);
                burst.setStartingMana(props.maxMana);
                burst.setMinManaLoss(props.ticksBeforeManaLoss);
                burst.setManaLossPerTick(props.manaLossPerTick);
                burst.setGravity(props.gravity);
                burst.setMotion(burst.motionX * (double)props.motionModifier, burst.motionY * (double)props.motionModifier, burst.motionZ * (double)props.motionModifier);
            }
            return burst;
        }
        return null;
    }

    public ILensControl getLensController(ItemStack stack) {
        ILensControl control;
        if (stack != null && stack.getItem() instanceof ILensControl && (control = (ILensControl)stack.getItem()).isControlLens(stack)) {
            return control;
        }
        return null;
    }

    public static MovingObjectPosition raytraceFromEntity(World world, Entity player, boolean par3, double range) {
        float f = 1.0f;
        float f1 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * f;
        float f2 = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * f;
        double d0 = player.prevPosX + (player.posX - player.prevPosX) * (double)f;
        double d1 = player.prevPosY + (player.posY - player.prevPosY) * (double)f;
        if (!world.isRemote && player instanceof EntityPlayer) {
            d1 += 1.62;
        }
        double d2 = player.prevPosZ + (player.posZ - player.prevPosZ) * (double)f;
        Vec3 vec3 = Vec3.createVectorHelper(d0, d1, d2);
        float f3 = MathHelper.cos(-f2 * ((float)Math.PI / 180) - (float)Math.PI);
        float f4 = MathHelper.sin(-f2 * ((float)Math.PI / 180) - (float)Math.PI);
        float f5 = -MathHelper.cos(-f1 * ((float)Math.PI / 180));
        float f6 = MathHelper.sin(-f1 * ((float)Math.PI / 180));
        float f7 = f4 * f5;
        float f8 = f3 * f5;
        double d3 = range;
        if (player instanceof EntityPlayerMP) {
            d3 = ((EntityPlayerMP)player).theItemInWorldManager.getBlockReachDistance();
        }
        Vec3 vec31 = vec3.addVector((double)f7 * d3, (double)f6 * d3, (double)f8 * d3);
        return world.func_147447_a(vec3, vec31, par3, !par3, par3);
    }

    public void renderHUD(Minecraft mc, ScaledResolution res) {
        String name = StatCollector.translateToLocal(new ItemStack(ModBlocks.extraSpreader, 1, this.getBlockMetadata()).getUnlocalizedName().replaceAll("tile.", "tile.botania:") + ".name");
        int color = this.isRedstone() ? 0xFF0000 : (this.isDreamwood() ? 16711854 : 65280);
        HUDHandler.drawSimpleManaHUD(color, this.knownMana, this.getMaxMana(), name, res);
        ItemStack lens = this.getStackInSlot(0);
        if (lens != null) {
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 771);
            String lensName = lens.getDisplayName();
            int width = 16 + mc.fontRenderer.getStringWidth(lensName) / 2;
            int x = res.getScaledWidth() / 2 - width;
            int y = res.getScaledHeight() / 2 + 50;
            mc.fontRenderer.drawStringWithShadow(lensName, x + 20, y + 5, color);
            RenderHelper.enableGUIStandardItemLighting();
            RenderItem.getInstance().renderItemAndEffectIntoGUI(mc.fontRenderer, mc.renderEngine, lens, x, y);
            RenderHelper.disableStandardItemLighting();
            GL11.glDisable(2896);
            GL11.glDisable(3042);
        }
        if (this.receiver != null) {
            TileEntity receiverTile = (TileEntity)this.receiver;
            ItemStack recieverStack = new ItemStack(this.worldObj.getBlock(receiverTile.xCoord, receiverTile.yCoord, receiverTile.zCoord), 1, receiverTile.getBlockMetadata());
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 771);
            if (recieverStack != null && recieverStack.getItem() != null) {
                String stackName = recieverStack.getDisplayName();
                int width = 16 + mc.fontRenderer.getStringWidth(stackName) / 2;
                int x = res.getScaledWidth() / 2 - width;
                int y = res.getScaledHeight() / 2 + 30;
                mc.fontRenderer.drawStringWithShadow(stackName, x + 20, y + 5, color);
                RenderHelper.enableGUIStandardItemLighting();
                RenderItem.getInstance().renderItemAndEffectIntoGUI(mc.fontRenderer, mc.renderEngine, recieverStack, x, y);
                RenderHelper.disableStandardItemLighting();
            }
            GL11.glDisable(2896);
            GL11.glDisable(3042);
        }
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
    }

    public void onClientDisplayTick() {
        if (this.worldObj != null) {
            EntityManaBurst burst = this.getBurst(true);
            burst.getCollidedTile(false);
        }
    }

    public float getManaYieldMultiplier(IManaBurst burst) {
        return burst.getMana() < 16 ? 0.0f : 0.95f;
    }

    public int getSizeInventory() {
        return 1;
    }

    @Override
    public ItemStack getStackInSlot(int p_70301_1_) {
        return null;
    }

    @Override
    public ItemStack decrStackSize(int p_70298_1_, int p_70298_2_) {
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int p_70304_1_) {
        return null;
    }

    @Override
    public void setInventorySlotContents(int p_70299_1_, ItemStack p_70299_2_) {

    }

    public String getInventoryName() {
        return "spreader";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    public int getInventoryStackLimit() {
        return 1;
    }

    public boolean isItemValidForSlot(int i, ItemStack itemstack) {
        return itemstack.getItem() instanceof ILens;
    }

    public void markDirty() {
        this.checkForReceiver();
        VanillaPacketDispatcher.dispatchTEToNearbyPlayers(this.worldObj, this.xCoord, this.yCoord, this.zCoord);
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
        return false;
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }

    public ChunkCoordinates getBinding() {
        if (this.receiver == null) {
            return null;
        }
        TileEntity tile = (TileEntity)this.receiver;
        return new ChunkCoordinates(tile.xCoord, tile.yCoord, tile.zCoord);
    }

    public int getMaxMana() {
        return this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord) == 1 ? 1049760 : 166640;
    }

    public String getInputKey() {
        return this.inputKey;
    }

    public String getOutputKey() {
        return this.outputKey;
    }

    public boolean canSelect(EntityPlayer player, ItemStack wand, int x, int y, int z, int side) {
        return true;
    }

    public boolean bindTo(EntityPlayer player, ItemStack wand, int x, int y, int z, int side) {
        Vector3 thisVec = Vector3.fromTileEntityCenter(this);
        Vector3 blockVec = new Vector3((double)x + 0.5, (double)y + 0.5, (double)z + 0.5);
        AxisAlignedBB axis = player.worldObj.getBlock(x, y, z).getCollisionBoundingBoxFromPool(player.worldObj, x, y, z);
        if (axis == null) {
            axis = AxisAlignedBB.getBoundingBox(x, y, z, x + 1, y + 1, z + 1);
        }
        if (!blockVec.isInside(axis)) {
            blockVec = new Vector3(axis.minX + (axis.maxX - axis.minX) / 2.0, axis.minY + (axis.maxY - axis.minY) / 2.0, axis.minZ + (axis.maxZ - axis.minZ) / 2.0);
        }
        Vector3 diffVec = blockVec.copy().sub(thisVec);
        Vector3 diffVec2D = new Vector3(diffVec.x, diffVec.z, 0.0);
        Vector3 rotVec = new Vector3(0.0, 1.0, 0.0);
        double angle = rotVec.angle(diffVec2D) / Math.PI * 180.0;
        if (blockVec.x < thisVec.x) {
            angle = -angle;
        }
        this.rotationX = (float)angle + 90.0f;
        rotVec = new Vector3(diffVec.x, 0.0, diffVec.z);
        angle = diffVec.angle(rotVec) * 180.0 / Math.PI;
        if (blockVec.y < thisVec.y) {
            angle = -angle;
        }
        this.rotationY = (float)angle;
        this.checkForReceiver();
        return true;
    }

    public void markDispatchable() {
    }

    public float getRotationX() {
        return this.rotationX;
    }

    public float getRotationY() {
        return this.rotationY;
    }

    public void setRotationX(float rot) {
        this.rotationX = rot;
    }

    public void setRotationY(float rot) {
        this.rotationY = rot;
    }

    public void commitRedirection() {
        this.checkForReceiver();
    }

    public void setCanShoot(boolean canShoot) {
        this.canShootBurst = canShoot;
    }

    public int getBurstParticleTick() {
        return this.burstParticleTick;
    }

    public void setBurstParticleTick(int i) {
        this.burstParticleTick = i;
    }

    public int getLastBurstDeathTick() {
        return this.lastBurstDeathTick;
    }

    public void setLastBurstDeathTick(int i) {
        this.lastBurstDeathTick = i;
    }

    public void pingback(IManaBurst burst, UUID expectedIdentity) {
        if (this.getIdentifier().equals(expectedIdentity)) {
            this.pingbackTicks = 20;
            Entity e = (Entity)burst;
            this.lastPingbackX = e.posX;
            this.lastPingbackY = e.posY;
            this.lastPingbackZ = e.posZ;
            this.setCanShoot(false);
        }
    }

    public UUID getIdentifier() {
        if (this.identity == null) {
            this.identity = UUID.randomUUID();
        }
        return this.identity;
    }

    public UUID getIdentifierUnsafe() {
        return this.identity;
    }
}

