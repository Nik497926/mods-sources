/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block.tile;

import com.meteor.extrabotany.common.block.ModBlocks;
import com.meteor.extrabotany.common.block.decor.BlockElfirium;
import com.meteor.extrabotany.common.core.util.ItemNBTHelper;
import com.meteor.extrabotany.common.item.ModItems;
import com.meteor.extrabotany.common.item.basic.ItemFragmentEfirium;
import com.meteor.extrabotany.common.item.basic.ItemMaterial;
import com.meteor.extrabotany.common.item.basic.awakeArmController;
import com.meteor.extrabotany.common.item.equipment.shield.ItemSGBee;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import vazkii.botania.api.internal.VanillaPacketDispatcher;
import vazkii.botania.api.lexicon.multiblock.Multiblock;
import vazkii.botania.api.lexicon.multiblock.MultiblockSet;
import vazkii.botania.api.mana.IManaPool;
import vazkii.botania.api.mana.spark.ISparkAttachable;
import vazkii.botania.api.mana.spark.ISparkEntity;
import vazkii.botania.api.mana.spark.SparkHelper;
import vazkii.botania.common.Botania;
import vazkii.botania.common.block.ModFluffBlocks;
import vazkii.botania.common.block.tile.TileMod;
import vazkii.botania.common.item.ItemOvergrowthSeed;
import vazkii.botania.common.item.equipment.armor.terrasteel.ItemTerrasteelArmor;
import vazkii.botania.common.item.equipment.armor.terrasteel.ItemTerrasteelChest;
import vazkii.botania.common.item.equipment.armor.terrasteel.ItemTerrasteelHelm;
import vazkii.botania.common.item.equipment.armor.terrasteel.ItemTerrasteelLegs;
import vazkii.botania.common.item.equipment.tool.terrasteel.ItemTerraPick;

public class TileRelicPlate
extends TileMod
implements ISparkAttachable {
    public static int MAX_MANA = 0;
    private static final int[][] BLOCK_GAIA = new int[][]{{1, -1, 0}, {-1, -1, 0}, {0, -1, 1}, {0, -1, -1}};
    private static final int[][] BLOCK_MANA_QUARZ = new int[][]{{0, -1, 0}, {1, -1, 1}, {1, -1, -1}, {-1, -1, 1}, {-1, -1, -1}, {-2, -2, 1}, {-1, -2, 2}, {1, -2, 2}, {2, -2, 1}, {-2, -2, -1}, {-1, -2, -2}, {1, -2, -2}, {2, -2, -1}};
    private static final int[][] BLOCK_LAUND_QUARZ = new int[][]{{0, -2, 2}, {2, -2, 2}, {2, -2, 0}, {2, -2, -2}, {0, -2, -2}, {-2, -2, -2}, {-2, -2, 0}, {-2, -2, 2}};
    private static final int[][] BLOCK_LIVINGROCK = new int[][]{{2, -3, 3}, {3, -3, 3}, {3, -3, 2}, {2, -3, -3}, {3, -3, -3}, {3, -3, -2}, {-2, -3, 3}, {-3, -3, 3}, {-3, -3, 2}, {-2, -3, -3}, {-3, -3, -3}, {-3, -3, -2}};
    private static final int[][] BLOCK_BIFROST = new int[][]{{-1, -3, 3}, {0, -3, 3}, {1, -3, 3}, {-1, -3, -3}, {0, -3, -3}, {1, -3, -3}, {3, -3, 1}, {3, -3, 0}, {3, -3, -1}, {-3, -3, 1}, {-3, -3, 0}, {-3, -3, -1}};
    private static final int[][] BLOCK_SOUL = new int[][]{{3, -2, 3}, {3, -1, 3}, {3, 0, 3}, {3, -2, -3}, {3, -1, -3}, {3, 0, -3}, {-3, -2, 3}, {-3, -1, 3}, {-3, 0, 3}, {-3, -2, -3}, {-3, -1, -3}, {-3, 0, -3}};
    private static final int[][] BLOCK_LAMP = new int[][]{{3, 1, 3}, {3, 1, -3}, {-3, 1, 3}, {-3, 1, -3}};
    private static final String TAG_MANA = "mana";
    int mana;
    ItemStack[] safe = new ItemStack[4];
    boolean work = false;
    byte type = (byte)-1;

    public static MultiblockSet makeMultiblockSet() {
        Multiblock mb = new Multiblock();
        for (int[] a : BLOCK_GAIA) {
            mb.addComponent(a[0], a[1] + 3, a[2], ModBlocks.gaiaquartz, 0);
        }
        for (int[] a : BLOCK_MANA_QUARZ) {
            mb.addComponent(a[0], a[1] + 3, a[2], ModFluffBlocks.manaQuartz, 0);
        }
        for (int[] a : BLOCK_LAUND_QUARZ) {
            mb.addComponent(a[0], a[1] + 3, a[2], ModFluffBlocks.lavenderQuartz, 0);
        }
        for (int[] a : BLOCK_LIVINGROCK) {
            mb.addComponent(a[0], a[1] + 3, a[2], vazkii.botania.common.block.ModBlocks.livingrock, 0);
        }
        for (int[] a : BLOCK_BIFROST) {
            mb.addComponent(a[0], a[1] + 3, a[2], vazkii.botania.common.block.ModBlocks.bifrostPerm, 0);
        }
        for (int[] a : BLOCK_SOUL) {
            mb.addComponent(a[0], a[1] + 3, a[2], ModBlocks.blocksoulsteel, 0);
        }
        for (int[] a : BLOCK_LAMP) {
            mb.addComponent(a[0], a[1] + 3, a[2], vazkii.botania.common.block.ModBlocks.seaLamp, 0);
        }
        mb.addComponent(0, 3, 0, ModBlocks.relicplate, 0);
        mb.setRenderOffset(0, 0, 0);
        return mb.makeSet();
    }

    EntityItem[] getItemNearElf(List a) {
        EntityItem[] elf = new EntityItem[4];
        for (int i = 0; i < Math.min(127, a.size()); i = (int)((byte)(i + 1))) {
            EntityItem b = (EntityItem)a.get(i);
            if (b.age <= 25 || b.isDead) continue;
            ItemStack c = ((EntityItem)a.get(i)).getEntityItem();
            if (c != null && c.getItem() instanceof ItemFragmentEfirium) {
                elf[0] = b;
                continue;
            }
            if (c != null && c.getItem() instanceof ItemMaterial && c.getItemDamage() == 11) {
                elf[1] = b;
                continue;
            }
            if (c != null && c.getItem() instanceof ItemMaterial && c.getItemDamage() == 2) {
                elf[2] = b;
                continue;
            }
            if (c == null || !(c.getItem() instanceof ItemMaterial) || c.getItemDamage() != 9) continue;
            elf[3] = b;
        }
        return elf;
    }

    EntityItem[] getItemNearArm(List a) {
        EntityItem[] elf = new EntityItem[4];
        for (int i = 0; i < Math.min(127, a.size()); i = (int)((byte)(i + 1))) {
            EntityItem b = (EntityItem)a.get(i);
            if (b.age <= 25 || b.isDead) continue;
            ItemStack c = ((EntityItem)a.get(i)).getEntityItem();
            if (c != null && c.getItem() instanceof ItemTerrasteelArmor) {
                elf[0] = b;
                continue;
            }
            if (c != null && c.getItem() instanceof ItemBlock && Block.getBlockFromItem((Item)c.getItem()) instanceof BlockElfirium) {
                elf[1] = b;
                continue;
            }
            if (c != null && c.getItem() instanceof ItemSGBee) {
                elf[2] = b;
                continue;
            }
            if (c == null || !(c.getItem() instanceof awakeArmController)) continue;
            elf[3] = b;
        }
        return elf;
    }

    EntityItem[] getItemNearPick(List a) {
        EntityItem[] elf = new EntityItem[4];
        for (int i = 0; i < Math.min(127, a.size()); i = (int)((byte)(i + 1))) {
            EntityItem b = (EntityItem)a.get(i);
            if (b.age <= 25 || b.isDead) continue;
            ItemStack c = ((EntityItem)a.get(i)).getEntityItem();
            if (c != null && c.getItem() instanceof ItemTerraPick) {
                elf[0] = b;
                continue;
            }
            if (c != null && c.getItem() instanceof ItemBlock && Block.getBlockFromItem((Item)c.getItem()) instanceof BlockElfirium) {
                elf[1] = b;
                continue;
            }
            if (c != null && c.getItem() instanceof ItemOvergrowthSeed) {
                elf[2] = b;
                continue;
            }
            if (c == null || !(c.getItem() instanceof awakeArmController)) continue;
            elf[3] = b;
        }
        return elf;
    }

    void startProccess(EntityItem[] elf, byte type) {
        ItemStack elfB = elf[0].getEntityItem().copy();
        elfB.stackSize = 1;
        ItemStack fragB = elf[2].getEntityItem().copy();
        fragB.stackSize = 1;
        ItemStack essenB = elf[2].getEntityItem().copy();
        essenB.stackSize = 1;
        ItemStack soulB = elf[1].getEntityItem().copy();
        soulB.stackSize = 1;
        --elf[0].getEntityItem().stackSize;
        if (elf[0].getEntityItem().stackSize <= 0) {
            elf[0].setDead();
        }
        --elf[1].getEntityItem().stackSize;
        if (elf[1].getEntityItem().stackSize <= 0) {
            elf[1].setDead();
        }
        --elf[2].getEntityItem().stackSize;
        if (elf[2].getEntityItem().stackSize <= 0) {
            elf[2].setDead();
        }
        --elf[3].getEntityItem().stackSize;
        if (elf[3].getEntityItem().stackSize <= 0) {
            elf[3].setDead();
        }
        MAX_MANA = type == 0 ? 5000000 : 10000000;
        this.work = true;
        this.safe = new ItemStack[]{elfB, fragB, essenB, soulB};
        this.type = type == 1 ? (byte) (elfB.getItem() instanceof ItemTerrasteelHelm ? 1 : (elfB.getItem() instanceof ItemTerrasteelChest ? 2 : (elfB.getItem() instanceof ItemTerrasteelLegs ? 3 : 4))) : type;
        VanillaPacketDispatcher.dispatchTEToNearbyPlayers((TileEntity)this);
    }

    public void updateEntity() {
        block27: {
            block26: {
                block29: {
                    block28: {
                        super.updateEntity();
                        if (this.worldObj.isRemote) break block26;
                        if (!this.hasValidPlatform()) break block27;
                        if (this.safe[0] != null) break block28;
                        List a = this.getItems();
                        if (a.size() >= 4) {
                            EntityItem[] elf = this.getItemNearElf(a);
                            if (elf[0] != null && elf[1] != null && elf[2] != null && elf[3] != null) {
                                this.startProccess(elf, (byte)0);
                                return;
                            }
                            elf = this.getItemNearArm(a);
                            if (elf[0] != null && elf[1] != null && elf[2] != null && elf[3] != null) {
                                this.startProccess(elf, (byte)1);
                                return;
                            }
                            elf = this.getItemNearPick(a);
                            if (elf[0] != null && elf[1] != null && elf[2] != null && elf[3] != null) {
                                this.startProccess(elf, (byte)5);
                                return;
                            }
                            if (this.work) {
                                for (int i = 0; i < 4; i = (int)((byte)(i + 1))) {
                                    this.safe[i] = null;
                                }
                                this.work = false;
                                VanillaPacketDispatcher.dispatchTEToNearbyPlayers((TileEntity)this);
                            }
                            return;
                        }
                        if (this.safe[0] == null && this.work) {
                            this.work = false;
                            for (int i = 0; i < 4; i = (int)((byte)(i + 1))) {
                                this.safe[i] = null;
                            }
                            VanillaPacketDispatcher.dispatchTEToNearbyPlayers((TileEntity)this);
                        }
                        break block27;
                    }
                    if (this.safe[0] == null) break block27;
                    if (this.mana >= MAX_MANA) break block29;
                    ISparkEntity spark = this.getAttachedSpark();
                    if (spark != null) {
                        List<ISparkEntity> sparkEntities = SparkHelper.getSparksAround((World)this.worldObj, (double)((double)this.xCoord + 0.5), (double)((double)this.yCoord + 0.5), (double)((double)this.zCoord + 0.5));
                        for (ISparkEntity otherSpark : sparkEntities) {
                            if (spark == otherSpark || otherSpark.getAttachedTile() == null || !(otherSpark.getAttachedTile() instanceof IManaPool)) continue;
                            otherSpark.registerTransfer(spark);
                        }
                    }
                    VanillaPacketDispatcher.dispatchTEToNearbyPlayers((TileEntity)this);
                    break block27;
                }
                if (this.mana < MAX_MANA) break block27;
                EntityItem it = null;
                if (this.type == 0) {
                    it = new EntityItem(this.worldObj, (double)this.xCoord + 0.5, (double)(this.yCoord + 1), (double)this.zCoord + 0.5, new ItemStack(ModItems.elfirium));
                } else if (this.type == 1) {
                    it = new EntityItem(this.worldObj, (double)this.xCoord + 0.5, (double)(this.yCoord + 1), (double)this.zCoord + 0.5, new ItemStack(ModItems.awakeoghelm));
                } else if (this.type == 2) {
                    it = new EntityItem(this.worldObj, (double)this.xCoord + 0.5, (double)(this.yCoord + 1), (double)this.zCoord + 0.5, new ItemStack(ModItems.awakeogchest));
                } else if (this.type == 3) {
                    it = new EntityItem(this.worldObj, (double)this.xCoord + 0.5, (double)(this.yCoord + 1), (double)this.zCoord + 0.5, new ItemStack(ModItems.awakeoglegs));
                } else if (this.type == 4) {
                    it = new EntityItem(this.worldObj, (double)this.xCoord + 0.5, (double)(this.yCoord + 1), (double)this.zCoord + 0.5, new ItemStack(ModItems.awakeogboots));
                } else if (this.type == 5) {
                    ItemStack st = new ItemStack(ModItems.awakepick);
                    ItemNBTHelper.setInteger(st, TAG_MANA, ItemNBTHelper.getInteger(this.safe[0], TAG_MANA, 0));
                    it = new EntityItem(this.worldObj, (double)this.xCoord + 0.5, (double)(this.yCoord + 1), (double)this.zCoord + 0.5, st);
                }
                for (int i = 0; i < 4; i = (int)((byte)(i + 1))) {
                    this.safe[i] = null;
                }
                if (it != null) {
                    this.worldObj.spawnEntityInWorld((Entity)it);
                }
                MAX_MANA = 0;
                this.mana = 0;
                this.work = false;
                VanillaPacketDispatcher.dispatchTEToNearbyPlayers((TileEntity)this);
                break block27;
            }
            if (this.work) {
                this.doParticles();
                ISparkEntity spark = this.getAttachedSpark();
                if (spark != null) {
                    List<ISparkEntity> sparkEntities = SparkHelper.getSparksAround((World)this.worldObj, (double)((double)this.xCoord + 0.5), (double)((double)this.yCoord + 0.5), (double)((double)this.zCoord + 0.5));
                    for (ISparkEntity otherSpark : sparkEntities) {
                        if (spark == otherSpark || otherSpark.getAttachedTile() == null || !(otherSpark.getAttachedTile() instanceof IManaPool)) continue;
                        otherSpark.registerTransfer(spark);
                    }
                }
            }
        }
    }

    void doParticles() {
        if (this.worldObj.isRemote) {
            int ticks = (int)(100.0 * ((double)this.getCurrentMana() / (double)MAX_MANA));
            int totalSpiritCount = 5;
            double tickIncrement = 360.0 / (double)totalSpiritCount;
            int speed = 5;
            double wticks = (double)(ticks * speed) - tickIncrement;
            double r = Math.sin((double)(ticks - 100) / 10.0) * 2.0;
            double g = Math.sin(wticks * Math.PI / 180.0 * 0.55);
            for (int i = 0; i < totalSpiritCount; ++i) {
                double x = (double)this.xCoord + Math.sin(wticks * Math.PI / 180.0) * r + 0.5;
                double y = (double)this.yCoord + 0.25 + Math.abs(r) * 0.7;
                double z = (double)this.zCoord + Math.cos(wticks * Math.PI / 180.0) * r + 0.5;
                wticks += tickIncrement;
                float[] colorsfx = new float[]{255.0f, (float)ticks / 100.0f, 1.0f - (float)ticks / 100.0f};
                Botania.proxy.wispFX(this.worldObj, x, y, z, colorsfx[2], colorsfx[2], colorsfx[1], 0.85f, (float)g * 0.05f, 0.25f);
                Botania.proxy.wispFX(this.worldObj, x, y, z, colorsfx[2], colorsfx[2], colorsfx[1], (float)Math.random() * 0.1f + 0.1f, (float)(Math.random() - 0.5) * 0.05f, (float)(Math.random() - 0.5) * 0.05f, (float)(Math.random() - 0.5) * 0.05f, 0.9f);
                if (ticks != 100) continue;
                for (int j = 0; j < 15; ++j) {
                    Botania.proxy.wispFX(this.worldObj, (double)this.xCoord + 0.5, (double)this.yCoord + 0.5, (double)this.zCoord + 0.5, colorsfx[2], colorsfx[2], colorsfx[1], (float)Math.random() * 0.15f + 0.15f, (float)(Math.random() - 0.5) * 0.125f, (float)(Math.random() - 0.5) * 0.125f, (float)(Math.random() - 0.5) * 0.125f);
                }
            }
        }
    }

    public boolean canRecieveManaFromBursts() {
        return this.mana <= MAX_MANA;
    }

    public boolean areIncomingTranfersDone() {
        return this.mana >= MAX_MANA;
    }

    List getItems() {
        return this.worldObj.getEntitiesWithinAABB(EntityItem.class, AxisAlignedBB.getBoundingBox((double)this.xCoord, (double)this.yCoord, (double)this.zCoord, (double)(this.xCoord + 1), (double)(this.yCoord + 1), (double)(this.zCoord + 1)));
    }

    boolean hasValidPlatform() {
        return this.checkAll(BLOCK_GAIA, ModBlocks.gaiaquartz) && this.checkAll(BLOCK_MANA_QUARZ, ModFluffBlocks.manaQuartz) && this.checkAll(BLOCK_LAUND_QUARZ, ModFluffBlocks.lavenderQuartz) && this.checkAll(BLOCK_LIVINGROCK, vazkii.botania.common.block.ModBlocks.livingrock) && this.checkAll(BLOCK_BIFROST, vazkii.botania.common.block.ModBlocks.bifrostPerm) && this.checkAll(BLOCK_SOUL, ModBlocks.blocksoulsteel) && this.checkAll(BLOCK_LAMP, vazkii.botania.common.block.ModBlocks.seaLamp);
    }

    boolean checkAll(int[][] positions, Block block) {
        int[][] arr$ = positions;
        int len$ = positions.length;
        for (int i$ = 0; i$ < len$; ++i$) {
            int[] position = arr$[i$];
            if (this.checkPlatform(position[0], position[1], position[2], block)) continue;
            return false;
        }
        return true;
    }

    boolean checkPlatform(int xOff, int yOff, int zOff, Block block) {
        return this.worldObj.getBlock(this.xCoord + xOff, this.yCoord + yOff, zOff + this.zCoord) == block;
    }

    public void writeCustomNBT(NBTTagCompound cmp) {
        cmp.setInteger(TAG_MANA, this.mana);
        cmp.setBoolean("work", this.work);
        cmp.setInteger("MAX_MANA", MAX_MANA);
        NBTTagList n = new NBTTagList();
        for (int i = 0; i < 4; i = (int)((byte)(i + 1))) {
            if (this.safe[i] == null) continue;
            NBTTagCompound nb = new NBTTagCompound();
            this.safe[i].writeToNBT(nb);
            n.appendTag((NBTBase)nb);
        }
        cmp.setTag("safe", (NBTBase)n);
        cmp.setByte("type", this.type);
    }

    public void readCustomNBT(NBTTagCompound cmp) {
        this.mana = cmp.getInteger(TAG_MANA);
        this.work = cmp.getBoolean("work");
        MAX_MANA = cmp.getInteger("MAX_MANA");
        NBTTagList n = cmp.getTagList("safe", 10);
        for (int i = 0; i < Math.min(4, n.tagCount()); i = (int)((byte)(i + 1))) {
            this.safe[i] = ItemStack.loadItemStackFromNBT((NBTTagCompound)n.getCompoundTagAt(i));
        }
        this.type = cmp.getByte("type");
    }

    public int getCurrentMana() {
        return this.mana;
    }

    public boolean isFull() {
        return this.mana >= MAX_MANA;
    }

    public void recieveMana(int mana) {
        this.mana = Math.max(0, Math.min(MAX_MANA, this.mana + mana));
        this.worldObj.func_147453_f(this.xCoord, this.yCoord, this.zCoord, this.worldObj.getBlock(this.xCoord, this.yCoord, this.zCoord));
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

    public int getAvailableSpaceForMana() {
        return Math.max(0, MAX_MANA - this.getCurrentMana());
    }

    public Packet getDescriptionPacket() {
        NBTTagCompound data = new NBTTagCompound();
        this.writeToNBT(data);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 2, data);
    }

    public void onDataPacket(NetworkManager netManager, S35PacketUpdateTileEntity packet) {
        this.readFromNBT(packet.func_148857_g());
    }
}

