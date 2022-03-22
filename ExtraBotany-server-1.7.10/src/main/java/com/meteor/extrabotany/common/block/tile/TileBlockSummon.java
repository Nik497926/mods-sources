/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block.tile;

import com.djgiannuzz.thaumcraftneiplugin.ModItems;
import com.meteor.extrabotany.common.entity.EntityAsgard;
import java.awt.Color;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGiantZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
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
import org.lwjgl.opengl.GL11;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.visnet.VisNetHandler;
import vazkii.botania.api.internal.VanillaPacketDispatcher;
import vazkii.botania.api.mana.spark.ISparkAttachable;
import vazkii.botania.api.mana.spark.ISparkEntity;
import vazkii.botania.client.core.helper.RenderHelper;
import vazkii.botania.common.core.helper.ItemNBTHelper;

public class TileBlockSummon
extends TileEntity
implements ISparkAttachable {
    private boolean work = false;
    private ItemStack save = null;
    private NBTTagCompound item = new NBTTagCompound();
    private int DELAY = 0;
    private int needMana = 0;
    private AspectList needAsp = new AspectList();
    private String entity = "";
    private int countEnt = 0;
    private int mana = 0;
    private final double[][] patternSpawn = new double[][]{{1.0, 1.0, 0.0, 1.0}, {1.0, 1.0, 0.0}, {1.0, 1.0, -1.0}, {0.0, 1.0, 0.0, 1.0}, {0.0, 1.0, 0.0}, {0.0, 1.0, -1.0}, {-1.0, 1.0, 0.0, 1.0}, {-1.0, 1.0, 0.0}, {-1.0, 1.0, -1.0}};
    private final int[] xCoordHudAsp = new int[]{-53, -35, -17, 1, 19, 37};
    private final int[] xCoordHudAspText = new int[]{-50, -32, -14, 4, 22, 40};
    private final int[] xCoordHudAspTextSafe = new int[]{-47, -29, -11, 7, 25, 43};
    private final Aspect[] aspListHUD = new Aspect[]{Aspect.AIR, Aspect.EARTH, Aspect.FIRE, Aspect.WATER, Aspect.ORDER, Aspect.ENTROPY};
    private ItemStack[] aspHUD = new ItemStack[0];

    public void updateEntity() {
        super.updateEntity();
        if (this.worldObj.isRemote) {
            if (this.aspHUD.length == 0) {
                this.aspHUD = this.setItemsWithHUD();
            }
            return;
        }
        if (this.worldObj.getBlock(this.xCoord, this.yCoord + 1, this.zCoord) == Blocks.fire) {
            this.worldObj.setBlock(this.xCoord, this.yCoord, this.zCoord, Blocks.air);
        }
        if (this.work) {
            if (this.DELAY > 0) {
                --this.DELAY;
            } else {
                VanillaPacketDispatcher.dispatchTEToNearbyPlayers(this);
                LinkedHashMap _asp = this.needAsp.aspects;
                if (_asp.size() == 0) {
                    if (this.mana >= this.needMana && !this.worldObj.isRemote) {
                        for (int i = 0; i < this.countEnt; ++i) {
                            double[] _pattern;
                            Object _ent = null;
                            if (this.entity == "skeleton") {
                                _ent = new EntitySkeleton(this.worldObj);
                                _pattern = this.patternSpawn[this.worldObj.rand.nextInt(8)];
                                ((EntityLiving)_ent).setPosition((double)this.xCoord + _pattern[0], (double)this.yCoord + _pattern[1], (double)this.zCoord + _pattern[2]);
                                this.worldObj.spawnEntityInWorld((Entity)_ent);
                                continue;
                            }
                            if (this.entity == "zombie") {
                                _ent = this.worldObj.rand.nextInt(50) == 0 ? new EntityGiantZombie(this.worldObj) : new EntityZombie(this.worldObj);
                                _pattern = this.patternSpawn[this.worldObj.rand.nextInt(8)];
                                ((EntityLiving)_ent).setPosition((double)this.xCoord + _pattern[0], (double)this.yCoord + _pattern[1], (double)this.zCoord + _pattern[2]);
                                this.worldObj.spawnEntityInWorld((Entity)_ent);
                                continue;
                            }
                            if (this.entity == "creeper") {
                                _ent = new EntityCreeper(this.worldObj);
                                _pattern = this.patternSpawn[this.worldObj.rand.nextInt(8)];
                                ((EntityLiving)_ent).setPosition((double)this.xCoord + _pattern[0], (double)this.yCoord + _pattern[1], (double)this.zCoord + _pattern[2]);
                                this.worldObj.spawnEntityInWorld((Entity)_ent);
                                continue;
                            }
                            if (this.entity == "hellskeleton") {
                                _ent = new EntitySkeleton(this.worldObj);
                                ((EntitySkeleton) _ent).setSkeletonType(1);
                                _pattern = this.patternSpawn[this.worldObj.rand.nextInt(8)];
                                ((EntityLiving)_ent).setPosition((double)this.xCoord + _pattern[0], (double)this.yCoord + _pattern[1], (double)this.zCoord + _pattern[2]);
                                this.worldObj.spawnEntityInWorld((Entity)_ent);
                                continue;
                            }
                            _ent = new EntityAsgard(this.worldObj);
                            _pattern = this.patternSpawn[this.worldObj.rand.nextInt(8)];
                            ((EntityLiving)_ent).setPosition((double)this.xCoord + _pattern[0], (double)this.yCoord + _pattern[1], (double)this.zCoord + _pattern[2]);
                            this.worldObj.spawnEntityInWorld((Entity)_ent);
                        }
                        this.work = false;
                        this.entity = "";
                        this.needMana = 0;
                        this.mana = 0;
                        this.save = null;
                        VanillaPacketDispatcher.dispatchTEToNearbyPlayers(this);
                    } else {
                        return;
                    }
                }
                for (Object ee : _asp.entrySet()) {
                    Map.Entry entry = (Map.Entry) ee;
                    int aspect = VisNetHandler.drainVis(this.worldObj, this.xCoord, this.yCoord, this.zCoord, (Aspect) entry.getKey(), 1);
                    if (aspect <= 0) continue;
                    this.DELAY = 10;
                    if ((Integer)entry.getValue() > 1) {
                        _asp.replace(entry.getKey(), entry.getValue(), (Integer)entry.getValue() - 1);
                        this.needAsp.aspects = _asp;
                    } else {
                        this.needAsp.aspects = _asp;
                        this.needAsp.remove((Aspect)entry.getKey());
                    }
                    return;
                }
            }
        }
    }

    public Packet getDescriptionPacket() {
        NBTTagCompound data = this.item;
        this.writeToNBT(data);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 2, data);
    }

    public void onDataPacket(NetworkManager netManager, S35PacketUpdateTileEntity packet) {
        this.readFromNBT(packet.func_148857_g());
    }

    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        if (nbt.hasKey("worked")) {
            this.work = nbt.getBoolean("worked");
        }
        if (nbt.hasKey("saveItem")) {
            this.save = ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("saveItem"));
        }
        if (nbt.hasKey("mana")) {
            this.mana = nbt.getInteger("mana");
        }
        if (nbt.hasKey("needMana")) {
            this.needMana = nbt.getInteger("needMana");
        }
        if (nbt.hasKey("Aspects")) {
            AspectList _asp = new AspectList();
            NBTTagCompound _aspect = nbt.getCompoundTag("Aspects");
            if (_aspect.hasKey("aer")) {
                _asp.add(Aspect.AIR, _aspect.getInteger("aer"));
            }
            if (_aspect.hasKey("terra")) {
                _asp.add(Aspect.EARTH, _aspect.getInteger("terra"));
            }
            if (_aspect.hasKey("ignis")) {
                _asp.add(Aspect.FIRE, _aspect.getInteger("ignis"));
            }
            if (_aspect.hasKey("aqua")) {
                _asp.add(Aspect.WATER, _aspect.getInteger("aqua"));
            }
            if (_aspect.hasKey("ordo")) {
                _asp.add(Aspect.ORDER, _aspect.getInteger("ordo"));
            }
            if (_aspect.hasKey("perditio")) {
                _asp.add(Aspect.ENTROPY, _aspect.getInteger("perditio"));
            }
            this.needAsp = _asp;
        }
        if (nbt.hasKey("entity")) {
            this.entity = nbt.getString("entity");
        }
        this.item = nbt;
    }

    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        if (this.save != null) {
            NBTTagCompound n = new NBTTagCompound();
            this.save.writeToNBT(n);
            nbt.setTag("saveItem", n);
        }
        nbt.setBoolean("worked", this.work);
        nbt.setInteger("delay", this.DELAY);
        nbt.setInteger("mana", this.mana);
        nbt.setInteger("needMana", this.needMana);
        NBTTagCompound _asp = new NBTTagCompound();
        LinkedHashMap _aspect = this.needAsp.aspects;
        for (Object en : _aspect.entrySet()) {
            Map.Entry entry = (Map.Entry) en;
            _asp.setInteger(((Aspect)entry.getKey()).getTag(), ((Integer)entry.getValue()).intValue());
        }
        nbt.setTag("Aspects", _asp);
        if (!this.entity.isEmpty()) {
            nbt.setString("entity", this.entity);
        }
        nbt.setInteger("countEntity", this.countEnt);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void onClickBlock(World world, int x, int y, int z, EntityPlayer player) {
        boolean hellSkeleton;
        ItemStack in;
        block23: {
            if (world.isRemote) {
                return;
            }
            if (player.inventory.getCurrentItem() != null && this.work) {
                return;
            }
            if (player.inventory.getCurrentItem() == null && !this.work) {
                return;
            }
            if (player.inventory.getCurrentItem() == null && this.work) {
                this.work = false;
                this.needMana = 0;
                this.mana = 0;
                this.needAsp = new AspectList();
                this.entity = "";
                this.countEnt = 0;
                player.inventory.setInventorySlotContents(player.inventory.currentItem, this.save.copy());
                this.save = null;
                VanillaPacketDispatcher.dispatchTEToNearbyPlayers(this);
                return;
            }
            in = player.inventory.getCurrentItem().copy();
            in.stackSize = 1;
            hellSkeleton = false;
            if (ItemStack.areItemStacksEqual(in, new ItemStack(Items.skull, 1, 0))) {
                if (world.provider.dimensionId == -1) {
                    hellSkeleton = true;
                    break block23;
                } else {
                    this.work = true;
                    this.save = in;
                    this.needMana = 30000;
                    AspectList _asp = new AspectList();
                    _asp.add(Aspect.ENTROPY, 5);
                    _asp.add(Aspect.EARTH, 5);
                    _asp.add(Aspect.WATER, 5);
                    this.needAsp = _asp;
                    this.entity = "skeleton";
                    this.countEnt = 3;
                    ItemStack out = player.inventory.getCurrentItem().copy();
                    if (out.stackSize == 1) {
                        out = null;
                    } else {
                        --out.stackSize;
                    }
                    player.inventory.setInventorySlotContents(player.inventory.currentItem, out);
                    return;
                }
            }
            if (ItemStack.areItemStacksEqual(in, new ItemStack(Items.skull, 1, 1))) {
                hellSkeleton = true;
            } else {
                if (ItemStack.areItemStacksEqual(in, new ItemStack(Items.skull, 1, 2))) {
                    this.work = true;
                    this.save = in;
                    this.needMana = 40000;
                    AspectList _asp = new AspectList();
                    _asp.add(Aspect.ENTROPY, 5);
                    _asp.add(Aspect.AIR, 5);
                    _asp.add(Aspect.FIRE, 5);
                    this.needAsp = _asp;
                    this.entity = "zombie";
                    this.countEnt = 3;
                    ItemStack out = player.inventory.getCurrentItem().copy();
                    if (out.stackSize == 1) {
                        out = null;
                    } else {
                        --out.stackSize;
                    }
                    player.inventory.setInventorySlotContents(player.inventory.currentItem, out);
                    return;
                }
                if (ItemStack.areItemStacksEqual(in, new ItemStack(Items.skull, 1, 4))) {
                    this.work = true;
                    this.save = in;
                    this.needMana = 40000;
                    AspectList _asp = new AspectList();
                    _asp.add(Aspect.ENTROPY, 5);
                    _asp.add(Aspect.AIR, 5);
                    _asp.add(Aspect.FIRE, 5);
                    this.needAsp = _asp;
                    this.entity = "creeper";
                    this.countEnt = 3;
                    ItemStack out = player.inventory.getCurrentItem().copy();
                    if (out.stackSize == 1) {
                        out = null;
                    } else {
                        --out.stackSize;
                    }
                    player.inventory.setInventorySlotContents(player.inventory.currentItem, out);
                    return;
                }
                if (ItemStack.areItemStacksEqual(in, new ItemStack(com.meteor.extrabotany.common.item.ModItems.material, 1, 9))) {
                    this.work = true;
                    this.save = in;
                    this.needMana = 150000;
                    AspectList _asp = new AspectList();
                    _asp.add(Aspect.ENTROPY, 10);
                    _asp.add(Aspect.AIR, 10);
                    _asp.add(Aspect.FIRE, 10);
                    _asp.add(Aspect.EARTH, 10);
                    _asp.add(Aspect.WATER, 10);
                    _asp.add(Aspect.ORDER, 10);
                    this.needAsp = _asp;
                    this.entity = "asgard";
                    this.countEnt = 1;
                    ItemStack out = player.inventory.getCurrentItem().copy();
                    if (out.stackSize == 1) {
                        out = null;
                    } else {
                        --out.stackSize;
                    }
                    player.inventory.setInventorySlotContents(player.inventory.currentItem, out);
                    return;
                }
            }
        }
        if (!hellSkeleton) {
            return;
        }
        this.work = true;
        this.save = in;
        this.needMana = 600000;
        AspectList _asp = new AspectList();
        _asp.add(Aspect.ENTROPY, 10);
        _asp.add(Aspect.AIR, 10);
        _asp.add(Aspect.FIRE, 10);
        _asp.add(Aspect.EARTH, 10);
        _asp.add(Aspect.WATER, 10);
        _asp.add(Aspect.ORDER, 10);
        this.needAsp = _asp;
        this.entity = "hellskeleton";
        this.countEnt = 2;
        ItemStack out = player.inventory.getCurrentItem().copy();
        if (out.stackSize == 1) {
            out = null;
        } else {
            --out.stackSize;
        }
        player.inventory.setInventorySlotContents(player.inventory.currentItem, out);
    }

    public boolean canAttachSpark(ItemStack itemStack) {
        return true;
    }

    public void attachSpark(ISparkEntity iSparkEntity) {
    }

    public int getAvailableSpaceForMana() {
        return Math.max(0, this.needMana - this.mana);
    }

    public ISparkEntity getAttachedSpark() {
        List sparks = this.worldObj.getEntitiesWithinAABB(ISparkEntity.class, AxisAlignedBB.getBoundingBox(this.xCoord, this.yCoord + 1, this.zCoord, this.xCoord + 1, this.yCoord + 2, this.zCoord + 1));
        if (sparks.size() == 1) {
            Entity e = (Entity)sparks.get(0);
            return (ISparkEntity)e;
        }
        return null;
    }

    public boolean areIncomingTranfersDone() {
        return this.mana >= this.needMana;
    }

    public boolean isFull() {
        return this.mana >= this.needMana;
    }

    public void recieveMana(int i) {
        this.mana = Math.min(this.needMana, this.mana + i);
    }

    public boolean canRecieveManaFromBursts() {
        return this.needMana > 0 && this.needMana > this.mana;
    }

    public int getCurrentMana() {
        return this.mana;
    }

    private ItemStack[] getItemsWithHUD() {
        if (this.aspHUD.length == 0) {
            return this.setItemsWithHUD();
        }
        return this.aspHUD;
    }

    private ItemStack[] setItemsWithHUD() {
        ItemStack[] _res = new ItemStack[6];
        String[] _resName = new String[]{"aer", "terra", "ignis", "aqua", "ordo", "perditio"};
        for (int i = 0; i < _res.length; ++i) {
            ItemStack var0 = new ItemStack(ModItems.itemAspect);
            NBTTagList _nbt = new NBTTagList();
            NBTTagCompound _n = new NBTTagCompound();
            _n.setInteger("amount", 2);
            _n.setString("key", _resName[i]);
            _nbt.appendTag(_n);
            ItemNBTHelper.setList(var0, "Aspects", _nbt);
            _res[i] = var0;
        }
        return _res;
    }

    public void renderHUD(Minecraft minecraft, ScaledResolution res) {
        if (this.work && this.save != null) {
            int i;
            int x = res.getScaledWidth() / 2;
            int y = res.getScaledHeight() / 2 - 8;
            RenderHelper.renderProgressPie(x - 8, y - 5, (float)this.mana / (float)this.needMana, this.save);
            minecraft.fontRenderer.drawString(Integer.toString(this.mana), x - 50 - Integer.toString(this.mana).length(), y, new Color(52735).getRGB());
            minecraft.fontRenderer.drawString(Integer.toString(this.needMana), x + 30 - Integer.toString(this.needMana).length(), y, new Color(52735).getRGB());
            ItemStack[] _aspect = this.getItemsWithHUD();
            net.minecraft.client.renderer.RenderHelper.enableGUIStandardItemLighting();
            GL11.glEnable(32826);
            for (i = 0; i < _aspect.length; ++i) {
                RenderItem.getInstance().renderItemAndEffectIntoGUI(minecraft.fontRenderer, minecraft.renderEngine, _aspect[i], x + this.xCoordHudAsp[i], y + 24);
            }
            net.minecraft.client.renderer.RenderHelper.disableStandardItemLighting();
            GL11.glDisable(2929);
            for (i = 0; i < _aspect.length; ++i) {
                minecraft.fontRenderer.drawString(Integer.toString(this.needAsp.getAmount(this.aspListHUD[i])), x + (this.needAsp.getAmount(this.aspListHUD[i]) > 9 ? this.xCoordHudAspText[i] : this.xCoordHudAspTextSafe[i]), y + 40, this.aspListHUD[i].getColor());
            }
            GL11.glEnable(2929);
        } else {
            int xc = res.getScaledWidth() / 2 - minecraft.fontRenderer.getStringWidth("\u041d\u0435 \u0440\u0430\u0431\u043e\u0442\u0430\u0435\u0442") / 2;
            int yc = res.getScaledHeight() / 2 + 10;
            minecraft.fontRenderer.drawStringWithShadow("\u041d\u0435 \u0440\u0430\u0431\u043e\u0442\u0430\u0435\u0442", xc, yc, new Color(0xFF5500).getRGB());
        }
    }
}

