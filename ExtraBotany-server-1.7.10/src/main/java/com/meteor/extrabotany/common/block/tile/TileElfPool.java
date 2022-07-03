/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block.tile;

import com.meteor.extrabotany.common.block.ModBlocks;
import java.awt.Color;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.internal.VanillaPacketDispatcher;
import vazkii.botania.api.item.IDyablePool;
import vazkii.botania.api.item.IManaDissolvable;
import vazkii.botania.api.mana.IKeyLocked;
import vazkii.botania.api.mana.IManaItem;
import vazkii.botania.api.mana.IManaPool;
import vazkii.botania.api.mana.IThrottledPacket;
import vazkii.botania.api.mana.ManaNetworkEvent;
import vazkii.botania.api.mana.spark.ISparkAttachable;
import vazkii.botania.api.mana.spark.ISparkEntity;
import vazkii.botania.api.recipe.RecipeManaInfusion;
import vazkii.botania.client.core.handler.HUDHandler;
import vazkii.botania.client.core.handler.LightningHandler;
import vazkii.botania.client.core.helper.RenderHelper;
import vazkii.botania.common.Botania;
import vazkii.botania.common.block.tile.TileMod;
import vazkii.botania.common.block.tile.mana.TileBellows;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.core.handler.ManaNetworkHandler;
import vazkii.botania.common.core.helper.Vector3;
import vazkii.botania.common.item.ItemManaTablet;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.lib.LibMisc;

public class TileElfPool
extends TileMod
implements IManaPool,
IDyablePool,
IKeyLocked,
ISparkAttachable,
IThrottledPacket {
    public static final int MAX_MANA = 15000000;
    public static final int MAX_MANA_DILLUTED = 10000;
    private static final String TAG_MANA = "mana";
    private static final String TAG_KNOWN_MANA = "knownMana";
    private static final String TAG_OUTPUTTING = "outputting";
    private static final String TAG_COLOR = "color";
    private static final String TAG_MANA_CAP = "manaCap";
    private static final String TAG_CAN_ACCEPT = "canAccept";
    private static final String TAG_CAN_SPARE = "canSpare";
    private static final String TAG_FRAGILE = "fragile";
    private static final String TAG_INPUT_KEY = "inputKey";
    private static final String TAG_OUTPUT_KEY = "outputKey";
    boolean outputting = false;
    public boolean alchemy = false;
    public boolean conjuration = false;
    boolean catalystsRegistered = false;
    public int color = 0;
    int mana;
    int knownMana = -1;
    public int manaCap = -1;
    int soundTicks = 0;
    boolean canAccept = true;
    boolean canSpare = true;
    public boolean fragile = false;
    public boolean isDoingTransfer = false;
    public int ticksDoingTransfer = 0;
    String inputKey = "";
    String outputKey = "";
    int ticks = 0;
    boolean sendPacket = false;

    public boolean isFull() {
        Block blockBelow = this.worldObj.getBlock(this.xCoord, this.yCoord - 1, this.zCoord);
        return blockBelow != vazkii.botania.common.block.ModBlocks.manaVoid && this.getCurrentMana() >= this.manaCap;
    }

    public void recieveMana(int mana) {
        this.mana = Math.max(0, Math.min(this.getCurrentMana() + mana, this.manaCap));
        this.worldObj.func_147453_f(this.xCoord, this.yCoord, this.zCoord, this.worldObj.getBlock(this.xCoord, this.yCoord, this.zCoord));
        this.markDispatchable();
    }

    public void invalidate() {
        super.invalidate();
        ManaNetworkEvent.removePool((TileEntity)this);
    }

    public void onChunkUnload() {
        super.onChunkUnload();
        this.invalidate();
    }

    public boolean collideEntityItem(EntityItem item) {
        if (item.isDead) {
            return false;
        }
        boolean didChange = false;
        ItemStack stack = item.getEntityItem();
        if (stack == null) {
            return false;
        }
        if (stack.getItem() instanceof IManaDissolvable) {
            ((IManaDissolvable)stack.getItem()).onDissolveTick((IManaPool)this, stack, item);
            if (stack.stackSize == 0) {
                item.setDead();
            }
        }
        if (item.age > 100 && item.age < 130 || !this.catalystsRegistered) {
            return false;
        }
        for (RecipeManaInfusion recipe : BotaniaAPI.manaInfusionRecipes) {
            if (!recipe.matches(stack) || recipe.isAlchemy() && !this.alchemy || recipe.isConjuration() && !this.conjuration) continue;
            int mana = recipe.getManaToConsume();
            if (this.getCurrentMana() < mana) break;
            this.recieveMana(-mana);
            if (!this.worldObj.isRemote) {
                --stack.stackSize;
                if (stack.stackSize == 0) {
                    item.setDead();
                }
                ItemStack output = recipe.getOutput().copy();
                EntityItem outputItem = new EntityItem(this.worldObj, (double)this.xCoord + 0.5, (double)this.yCoord + 1.5, (double)this.zCoord + 0.5, output);
                outputItem.age = 105;
                this.worldObj.spawnEntityInWorld((Entity)outputItem);
            }
            this.craftingFanciness();
            didChange = true;
            break;
        }
        return didChange;
    }

    public void craftingFanciness() {
    }

    public void updateEntity() {
        boolean wasDoingTransfer = this.isDoingTransfer;
        this.isDoingTransfer = false;
        if (this.manaCap == -1) {
            int n = this.manaCap = this.getBlockMetadata() == 2 ? 10000 : 15000000;
        }
        if (!ManaNetworkHandler.instance.isPoolIn((TileEntity)this) && !this.isInvalid()) {
            ManaNetworkEvent.addPool((TileEntity)this);
        }
        if (this.soundTicks > 0) {
            --this.soundTicks;
        }
        if (this.worldObj.isRemote) {
            double particleChance = 1.0 - (double)this.getCurrentMana() / (double)this.manaCap * 0.1;
            Color color = new Color(50943);
            if (Math.random() > particleChance) {
                Botania.proxy.wispFX(this.worldObj, (double)this.xCoord + 0.3 + Math.random() * 0.5, (double)this.yCoord + 0.6 + Math.random() * 0.25, (double)this.zCoord + Math.random(), (float)color.getRed() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f, (float)Math.random() / 3.0f, (float)(-Math.random()) / 25.0f, 2.0f);
            }
        }
        if (this.sendPacket && this.ticks % 10 == 0) {
            VanillaPacketDispatcher.dispatchTEToNearbyPlayers((TileEntity)this);
            this.sendPacket = false;
        }
        this.alchemy = this.worldObj.getBlock(this.xCoord, this.yCoord - 1, this.zCoord) == vazkii.botania.common.block.ModBlocks.alchemyCatalyst;
        this.conjuration = this.worldObj.getBlock(this.xCoord, this.yCoord - 1, this.zCoord) == vazkii.botania.common.block.ModBlocks.conjurationCatalyst;
        this.catalystsRegistered = true;
        List<EntityItem> items = this.worldObj.getEntitiesWithinAABB(EntityItem.class, AxisAlignedBB.getBoundingBox((double)this.xCoord, (double)this.yCoord, (double)this.zCoord, (double)(this.xCoord + 1), (double)(this.yCoord + 1), (double)(this.zCoord + 1)));
        for (EntityItem item : items) {
            int manaVal;
            if (item.isDead) continue;
            ItemStack stack = item.getEntityItem();
            this.collideEntityItem(item);
            if (stack == null || !(stack.getItem() instanceof IManaItem)) continue;
            IManaItem mana = (IManaItem)stack.getItem();
            if ((!this.outputting || !mana.canReceiveManaFromPool(stack, (TileEntity)this)) && (this.outputting || !mana.canExportManaToPool(stack, (TileEntity)this))) continue;
            boolean didSomething = false;
            int bellowCount = 0;
            if (this.outputting) {
                for (ForgeDirection dir : LibMisc.CARDINAL_DIRECTIONS) {
                    TileEntity tile = this.worldObj.getTileEntity(this.xCoord + dir.offsetX, this.yCoord, this.zCoord + dir.offsetZ);
                    if (tile == null || !(tile instanceof TileBellows) || ((TileBellows)tile).getLinkedTile() != this) continue;
                    ++bellowCount;
                }
            }
            int transfRate = 1000 * (bellowCount + 1);
            if (this.outputting) {
                if (this.canSpare) {
                    if (this.getCurrentMana() > 0 && mana.getMana(stack) < mana.getMaxMana(stack)) {
                        didSomething = true;
                    }
                    manaVal = Math.min(transfRate, Math.min(this.getCurrentMana(), mana.getMaxMana(stack) - mana.getMana(stack)));
                    if (!this.worldObj.isRemote) {
                        mana.addMana(stack, manaVal);
                    }
                    this.recieveMana(-manaVal);
                }
            } else if (this.canAccept) {
                if (mana.getMana(stack) > 0 && !this.isFull()) {
                    didSomething = true;
                }
                manaVal = Math.min(transfRate, Math.min(this.manaCap - this.getCurrentMana(), mana.getMana(stack)));
                if (!this.worldObj.isRemote) {
                    mana.addMana(stack, -manaVal);
                }
                this.recieveMana(manaVal);
            }
            if (!didSomething) continue;
            if (this.worldObj.isRemote && ConfigHandler.chargingAnimationEnabled && this.worldObj.rand.nextInt(20) == 0) {
                Vector3 itemVec = Vector3.fromTileEntity((TileEntity)this).add(0.5, 0.5 + Math.random() * 0.3, 0.5);
                Vector3 tileVec = Vector3.fromTileEntity((TileEntity)this).add(0.2 + Math.random() * 0.6, 0.0, 0.2 + Math.random() * 0.6);
                LightningHandler.spawnLightningBolt((World)this.worldObj, (Vector3)(this.outputting ? tileVec : itemVec), (Vector3)(this.outputting ? itemVec : tileVec), (float)80.0f, (long)this.worldObj.rand.nextLong(), (int)1140881820, (int)1140901631);
            }
            this.isDoingTransfer = this.outputting;
        }
        if (this.isDoingTransfer) {
            ++this.ticksDoingTransfer;
        } else {
            this.ticksDoingTransfer = 0;
            if (wasDoingTransfer) {
                VanillaPacketDispatcher.dispatchTEToNearbyPlayers((TileEntity)this);
            }
        }
        ++this.ticks;
    }

    public void writeCustomNBT(NBTTagCompound cmp) {
        cmp.setInteger(TAG_MANA, this.mana);
        cmp.setBoolean(TAG_OUTPUTTING, this.outputting);
        cmp.setInteger(TAG_COLOR, this.color);
        cmp.setInteger(TAG_MANA_CAP, this.manaCap);
        cmp.setBoolean(TAG_CAN_ACCEPT, this.canAccept);
        cmp.setBoolean(TAG_CAN_SPARE, this.canSpare);
        cmp.setBoolean(TAG_FRAGILE, this.fragile);
        cmp.setString(TAG_INPUT_KEY, this.inputKey);
        cmp.setString(TAG_OUTPUT_KEY, this.outputKey);
    }

    public void readCustomNBT(NBTTagCompound cmp) {
        this.mana = cmp.getInteger(TAG_MANA);
        this.outputting = cmp.getBoolean(TAG_OUTPUTTING);
        this.color = cmp.getInteger(TAG_COLOR);
        if (cmp.hasKey(TAG_MANA_CAP)) {
            this.manaCap = cmp.getInteger(TAG_MANA_CAP);
        }
        if (cmp.hasKey(TAG_CAN_ACCEPT)) {
            this.canAccept = cmp.getBoolean(TAG_CAN_ACCEPT);
        }
        if (cmp.hasKey(TAG_CAN_SPARE)) {
            this.canSpare = cmp.getBoolean(TAG_CAN_SPARE);
        }
        this.fragile = cmp.getBoolean(TAG_FRAGILE);
        if (cmp.hasKey(TAG_INPUT_KEY)) {
            this.inputKey = cmp.getString(TAG_INPUT_KEY);
        }
        if (cmp.hasKey(TAG_OUTPUT_KEY)) {
            this.inputKey = cmp.getString(TAG_OUTPUT_KEY);
        }
        if (cmp.hasKey(TAG_KNOWN_MANA)) {
            this.knownMana = cmp.getInteger(TAG_KNOWN_MANA);
        }
    }

    public void onWanded(EntityPlayer player, ItemStack wand) {
        if (player == null) {
            return;
        }
        if (player.isSneaking()) {
            this.outputting = !this.outputting;
            VanillaPacketDispatcher.dispatchTEToNearbyPlayers((World)this.worldObj, (int)this.xCoord, (int)this.yCoord, (int)this.zCoord);
        }
        if (!this.worldObj.isRemote) {
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            this.writeCustomNBT(nbttagcompound);
            nbttagcompound.setInteger(TAG_KNOWN_MANA, this.getCurrentMana());
            if (player instanceof EntityPlayerMP) {
                ((EntityPlayerMP)player).playerNetServerHandler.sendPacket((Packet)new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, -999, nbttagcompound));
            }
        }
        this.worldObj.playSoundAtEntity((Entity)player, "botania:ding", 0.11f, 1.0f);
    }

    public void renderHUD(Minecraft mc, ScaledResolution res) {
        ItemStack pool = new ItemStack(ModBlocks.elfpool, 1, this.getBlockMetadata());
        String name = StatCollector.translateToLocal((String)(pool.getUnlocalizedName().replaceAll("tile.", "tile.botania:") + ".name"));
        int color = 0x4444FF;
        HUDHandler.drawSimpleManaHUD((int)color, (int)this.knownMana, (int)this.manaCap, (String)name, (ScaledResolution)res);
        int x = res.getScaledWidth() / 2 - 11;
        int y = res.getScaledHeight() / 2 + 30;
        int u = this.outputting ? 22 : 0;
        int v = 38;
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        mc.renderEngine.bindTexture(HUDHandler.manaBar);
        RenderHelper.drawTexturedModalRect((int)x, (int)y, (float)0.0f, (int)u, (int)v, (int)22, (int)15);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        ItemStack tablet = new ItemStack(ModItems.manaTablet);
        ItemManaTablet.setStackCreative((ItemStack)tablet);
        net.minecraft.client.renderer.RenderHelper.enableGUIStandardItemLighting();
        RenderItem.getInstance().renderItemAndEffectIntoGUI(mc.fontRenderer, mc.renderEngine, tablet, x - 20, y);
        RenderItem.getInstance().renderItemAndEffectIntoGUI(mc.fontRenderer, mc.renderEngine, pool, x + 26, y);
        net.minecraft.client.renderer.RenderHelper.disableStandardItemLighting();
        GL11.glDisable((int)2896);
        GL11.glDisable((int)3042);
    }

    public boolean canRecieveManaFromBursts() {
        return true;
    }

    public boolean isOutputtingPower() {
        return this.outputting;
    }

    public int getCurrentMana() {
        return this.worldObj != null && this.getBlockMetadata() == 1 ? 15000000 : this.mana;
    }

    public String getInputKey() {
        return this.inputKey;
    }

    public String getOutputKey() {
        return this.outputKey;
    }

    public boolean canAttachSpark(ItemStack stack) {
        return true;
    }

    public void attachSpark(ISparkEntity entity) {
    }

    public ISparkEntity getAttachedSpark() {
        List sparks = this.worldObj.getEntitiesWithinAABB(ISparkEntity.class, AxisAlignedBB.getBoundingBox((double)this.xCoord, (double)(this.yCoord + 1), (double)this.zCoord, (double)(this.xCoord + 1), (double)(this.yCoord + 2), (double)(this.zCoord + 1)));
        if (sparks.size() == 1) {
            Entity e = (Entity)sparks.get(0);
            return (ISparkEntity)e;
        }
        return null;
    }

    public boolean areIncomingTranfersDone() {
        return false;
    }

    public int getAvailableSpaceForMana() {
        return Math.max(0, this.manaCap - this.getCurrentMana());
    }

    public int getColor() {
        return this.color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void markDispatchable() {
        this.sendPacket = true;
    }
}

