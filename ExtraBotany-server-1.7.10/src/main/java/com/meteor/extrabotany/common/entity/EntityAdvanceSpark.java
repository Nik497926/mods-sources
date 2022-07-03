/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.entity;

import baubles.common.lib.PlayerHandler;
import com.meteor.extrabotany.common.entity.IAdvanceSpark;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import vazkii.botania.api.mana.IManaItem;
import vazkii.botania.api.mana.IManaPool;
import vazkii.botania.api.mana.spark.ISparkAttachable;
import vazkii.botania.api.mana.spark.ISparkEntity;
import vazkii.botania.api.mana.spark.SparkHelper;
import vazkii.botania.common.Botania;
import vazkii.botania.common.core.helper.Vector3;
import vazkii.botania.common.item.ModItems;

public class EntityAdvanceSpark
extends Entity
implements ISparkEntity,
IAdvanceSpark {
    private static final int[] TRANSFER_RATE = new int[]{10000, 100000, 1000000};
    private static final String TAG_UPGRADE = "upgrade";
    private static final String TAG_INVIS = "invis";
    public static final int INVISIBILITY_DATA_WATCHER_KEY = 27;
    public final int type = 0;
    Set<ISparkEntity> transfers = Collections.newSetFromMap(new WeakHashMap());
    int removeTransferants = 2;
    boolean firstTick = false;

    public EntityAdvanceSpark(World world) {
        super(world);
        this.isImmuneToFire = true;
    }

    @Override
    public void spawn(double x, double y, double z, World world, ISparkAttachable attach) {
        this.setPosition(x, y, z);
        world.spawnEntityInWorld((Entity)this);
        attach.attachSpark((ISparkEntity)this);
    }

    protected void entityInit() {
        this.setSize(0.1f, 0.5f);
        this.dataWatcher.addObject(27, (Object)0);
        this.dataWatcher.addObject(28, (Object)0);
        this.dataWatcher.setObjectWatched(27);
        this.dataWatcher.setObjectWatched(28);
        this.dataWatcher.addObject(29, (Object)0);
    }

    public void onUpdate() {
        super.onUpdate();
        ISparkAttachable tile = this.getAttachedTile();
        if (tile == null) {
            if (!this.worldObj.isRemote) {
                this.setDead();
            }
            return;
        }
        boolean first = this.worldObj.isRemote && !this.firstTick;
        int upgrade = this.getUpgrade();
        List<ISparkEntity> allSparks = null;
        if (first || upgrade == 2 || upgrade == 3) {
            allSparks = SparkHelper.getSparksAround((World)this.worldObj, (double)this.posX, (double)this.posY, (double)this.posZ);
        }
        if (first) {
            first = true;
        }
        Collection<ISparkEntity> transfers = this.getTransfers();
        if (upgrade != 0) {
            switch (upgrade) {
                case 1: {
                    EntityPlayer player2;
                    List<EntityPlayer> players = SparkHelper.getEntitiesAround(EntityPlayer.class, (World)this.worldObj, (double)this.posX, (double)this.posY, (double)this.posZ);
                    HashMap receivingPlayers = new HashMap();
                    ItemStack input = new ItemStack(com.meteor.extrabotany.common.item.ModItems.advanceSpark, 1, 0);
                    for (EntityPlayer player : players) {
                        player2 = player;
                        ArrayList<ItemStack> stacks = new ArrayList<ItemStack>();
                        stacks.addAll(Arrays.asList(player2.inventory.mainInventory));
                        stacks.addAll(Arrays.asList(player2.inventory.armorInventory));
                        stacks.addAll(Arrays.asList(PlayerHandler.getPlayerBaubles((EntityPlayer)player2).stackList));
                        for (ItemStack stack : stacks) {
                            int recv;
                            HashMap<ItemStack, Integer> receivingStacks;
                            IManaItem manaItem;
                            if (stack == null || !(stack.getItem() instanceof IManaItem) || !(manaItem = (IManaItem)stack.getItem()).canReceiveManaFromItem(stack, input)) continue;
                            boolean add = false;
                            if (!receivingPlayers.containsKey(player2)) {
                                add = true;
                                receivingStacks = new HashMap<ItemStack, Integer>();
                            } else {
                                receivingStacks = (HashMap<ItemStack, Integer>)receivingPlayers.get(player2);
                            }
                            if ((recv = Math.min(this.getAttachedTile().getCurrentMana(), Math.min(TRANSFER_RATE[0], manaItem.getMaxMana(stack) - manaItem.getMana(stack)))) <= 0) continue;
                            receivingStacks.put(stack, recv);
                            if (!add) continue;
                            receivingPlayers.put(player2, receivingStacks);
                        }
                    }
                    if (receivingPlayers.isEmpty()) break;
                    ArrayList keys = new ArrayList(receivingPlayers.keySet());
                    Collections.shuffle(keys);
                    player2 = (EntityPlayer)keys.iterator().next();
                    Map items = (Map)receivingPlayers.get(player2);
                    ItemStack stack = (ItemStack)items.keySet().iterator().next();
                    int cost = (Integer)items.get(stack);
                    int manaToPut = Math.min(this.getAttachedTile().getCurrentMana(), cost);
                    ((IManaItem)stack.getItem()).addMana(stack, manaToPut);
                    this.getAttachedTile().recieveMana(-manaToPut);
                    this.particlesTowards((Entity)player2);
                    break;
                }
                case 2: {
                    ArrayList validSparks = new ArrayList();
                    for (ISparkEntity spark : allSparks) {
                        int upgrade_;
                        if (spark == this || (upgrade_ = spark.getUpgrade()) != 0 || !(spark.getAttachedTile() instanceof IManaPool)) continue;
                        validSparks.add(spark);
                    }
                    if (validSparks.size() <= 0) break;
                    ((ISparkEntity)validSparks.get(this.worldObj.rand.nextInt(validSparks.size()))).registerTransfer((ISparkEntity)this);
                    break;
                }
                case 3: {
                    for (ISparkEntity spark : allSparks) {
                        int upgrade_;
                        if (spark == this || (upgrade_ = spark.getUpgrade()) == 2 || upgrade_ == 3 || upgrade_ == 4) continue;
                        transfers.add(spark);
                    }
                    break;
                }
            }
        }
        if (!transfers.isEmpty()) {
            int manaTotal = Math.min(TRANSFER_RATE[0] * transfers.size(), tile.getCurrentMana());
            int manaForEach = manaTotal / transfers.size();
            int manaSpent = 0;
            if (manaForEach > transfers.size()) {
                for (ISparkEntity spark : transfers) {
                    if (spark.getAttachedTile() == null || spark.getAttachedTile().isFull() || spark.areIncomingTransfersDone()) {
                        manaTotal -= manaForEach;
                        continue;
                    }
                    ISparkAttachable attached = spark.getAttachedTile();
                    int spend = Math.min(attached.getAvailableSpaceForMana(), manaForEach);
                    attached.recieveMana(spend);
                    manaSpent += spend;
                    this.particlesTowards((Entity)spark);
                }
                tile.recieveMana(-manaSpent);
            }
        }
        if (this.removeTransferants > 0) {
            --this.removeTransferants;
        }
        this.getTransfers();
    }

    void particlesTowards(Entity e) {
        Vector3 thisVec = Vector3.fromEntityCenter((Entity)this).add(0.0, 0.0, 0.0);
        Vector3 receiverVec = Vector3.fromEntityCenter((Entity)e).add(0.0, 0.0, 0.0);
        double rc = 0.45;
        thisVec.add((Math.random() - 0.5) * rc, (Math.random() - 0.5) * rc, (Math.random() - 0.5) * rc);
        receiverVec.add((Math.random() - 0.5) * rc, (Math.random() - 0.5) * rc, (Math.random() - 0.5) * rc);
        Vector3 motion = receiverVec.copy().sub(thisVec);
        motion.multiply((double)0.04f);
        float r = 0.4f + 0.3f * (float)Math.random();
        float g = 0.4f + 0.3f * (float)Math.random();
        float b = 0.4f + 0.3f * (float)Math.random();
        float size = 0.125f + 0.125f * (float)Math.random();
        Botania.proxy.wispFX(this.worldObj, thisVec.x, thisVec.y, thisVec.z, r, g, b, size, (float)motion.x, (float)motion.y, (float)motion.z);
    }

    public static void particleBeam(Entity e1, Entity e2) {
        if (e1 == null || e2 == null) {
            return;
        }
        Vector3 orig = new Vector3(e1.posX, e1.posY + 0.25, e1.posZ);
        Vector3 end = new Vector3(e2.posX, e2.posY + 0.25, e2.posZ);
        Vector3 diff = end.copy().sub(orig);
        Vector3 movement = diff.copy().normalize().multiply(0.1);
        int iters = (int)(diff.mag() / movement.mag());
        float huePer = 1.0f / (float)iters;
        float hueSum = (float)Math.random();
        Vector3 currentPos = orig.copy();
        for (int i = 0; i < iters; ++i) {
            float hue = (float)i * huePer + hueSum;
            Color color = Color.getHSBColor(hue, 1.0f, 1.0f);
            float r = Math.min(1.0f, (float)color.getRed() / 255.0f + 0.4f);
            float g = Math.min(1.0f, (float)color.getGreen() / 255.0f + 0.4f);
            float b = Math.min(1.0f, (float)color.getBlue() / 255.0f + 0.4f);
            Botania.proxy.setSparkleFXNoClip(true);
            Botania.proxy.sparkleFX(e1.worldObj, currentPos.x, currentPos.y, currentPos.z, r, g, b, 1.0f, 12);
            Botania.proxy.setSparkleFXNoClip(false);
            currentPos.add(movement);
        }
    }

    public boolean canBeCollidedWith() {
        return true;
    }

    public boolean interactFirst(EntityPlayer player) {
        ItemStack stack = player.getCurrentEquippedItem();
        if (stack != null) {
            int upgrade = this.getUpgrade();
            if (stack.getItem() == ModItems.twigWand) {
                if (player.isSneaking()) {
                    if (upgrade > 0) {
                        if (!this.worldObj.isRemote) {
                            this.entityDropItem(new ItemStack(ModItems.sparkUpgrade, 1, upgrade - 1), 0.0f);
                        }
                        this.setUpgrade(0);
                        this.transfers.clear();
                        this.removeTransferants = 2;
                    } else {
                        this.setDead();
                    }
                    if (player.worldObj.isRemote) {
                        player.swingItem();
                    }
                    return true;
                }
                List<ISparkEntity> allSparks = SparkHelper.getSparksAround((World)this.worldObj, (double)this.posX, (double)this.posY, (double)this.posZ);
                for (ISparkEntity spark : allSparks) {
                    EntityAdvanceSpark.particleBeam(this, (Entity)spark);
                }
                return true;
            }
            if (stack.getItem() == ModItems.sparkUpgrade && upgrade == 0) {
                int newUpgrade = stack.getItemDamage() + 1;
                this.setUpgrade(newUpgrade);
                --stack.stackSize;
                if (player.worldObj.isRemote) {
                    player.swingItem();
                }
                return true;
            }
        }
        return this.doPhantomInk(stack);
    }

    public boolean doPhantomInk(ItemStack stack) {
        if (stack != null && stack.getItem() == ModItems.phantomInk && !this.worldObj.isRemote) {
            int invis = this.dataWatcher.getWatchableObjectInt(27);
            this.dataWatcher.updateObject(27, (Object)(~invis & 1));
            return true;
        }
        return false;
    }

    protected void readEntityFromNBT(NBTTagCompound cmp) {
        this.setUpgrade(cmp.getInteger(TAG_UPGRADE));
        this.dataWatcher.updateObject(27, (Object)cmp.getInteger(TAG_INVIS));
    }

    protected void writeEntityToNBT(NBTTagCompound cmp) {
        cmp.setInteger(TAG_UPGRADE, this.getUpgrade());
        cmp.setInteger(TAG_INVIS, this.dataWatcher.getWatchableObjectInt(27));
    }

    public void setDead() {
        super.setDead();
        if (!this.worldObj.isRemote) {
            int upgrade = this.getUpgrade();
            this.entityDropItem(new ItemStack(com.meteor.extrabotany.common.item.ModItems.advanceSpark, 1, 0), 0.0f);
            if (upgrade > 0) {
                this.entityDropItem(new ItemStack(ModItems.sparkUpgrade, 1, upgrade - 1), 0.0f);
            }
        }
    }

    public ISparkAttachable getAttachedTile() {
        int z;
        int y;
        int x = MathHelper.floor_double((double)this.posX);
        TileEntity tile = this.worldObj.getTileEntity(x, y = MathHelper.floor_double((double)this.posY) - 1, z = MathHelper.floor_double((double)this.posZ));
        if (tile != null && tile instanceof ISparkAttachable) {
            return (ISparkAttachable)tile;
        }
        return null;
    }

    public Collection<ISparkEntity> getTransfers() {
        ArrayList<ISparkEntity> removals = new ArrayList<ISparkEntity>();
        Iterator<ISparkEntity> iterator = this.transfers.iterator();
        while (iterator.hasNext()) {
            ISparkEntity e;
            ISparkEntity spark = e = iterator.next();
            int upgr = this.getUpgrade();
            int supgr = spark.getUpgrade();
            ISparkAttachable atile = spark.getAttachedTile();
            if (spark != this && !spark.areIncomingTransfersDone() && atile != null && !atile.isFull() && (upgr == 0 && supgr == 2 || upgr == 3 && (supgr == 0 || supgr == 1) || !(atile instanceof IManaPool))) continue;
            removals.add(e);
        }
        if (!removals.isEmpty()) {
            this.transfers.removeAll(removals);
        }
        return this.transfers;
    }

    private boolean hasTransfer(ISparkEntity entity) {
        return this.transfers.contains(entity);
    }

    public void registerTransfer(ISparkEntity entity) {
        if (this.hasTransfer(entity)) {
            return;
        }
        this.transfers.add(entity);
    }

    @Override
    public int getUpgrade() {
        return this.dataWatcher.getWatchableObjectInt(28);
    }

    public void setUpgrade(int upgrade) {
        this.dataWatcher.updateObject(28, (Object)upgrade);
    }

    public boolean areIncomingTransfersDone() {
        ISparkAttachable tile = this.getAttachedTile();
        if (tile instanceof IManaPool) {
            return this.removeTransferants > 0;
        }
        return tile != null && tile.areIncomingTranfersDone();
    }

    @Override
    public int getType() {
        return this.type;
    }
}

