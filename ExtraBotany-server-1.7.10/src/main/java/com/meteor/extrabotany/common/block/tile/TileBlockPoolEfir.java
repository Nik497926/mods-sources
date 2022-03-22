/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block.tile;

import com.meteor.extrabotany.common.block.tile.TileEAltar;
import com.meteor.extrabotany.common.block.tile.TileTransformater;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import vazkii.botania.api.internal.VanillaPacketDispatcher;

public class TileBlockPoolEfir
extends TileEntity {
    private int stageClient = 0;
    private int tickClient = 0;
    private int efir = 0;
    private int stability = 100;
    private final ArrayList<TileEntity> connected = new ArrayList();
    private int[][] withConnect = new int[0][];

    public Packet getDescriptionPacket() {
        NBTTagCompound data = new NBTTagCompound();
        this.writeToNBT(data);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 2, data);
    }

    public void onDataPacket(NetworkManager netManager, S35PacketUpdateTileEntity packet) {
        this.readFromNBT(packet.func_148857_g());
    }

    private int nextState() {
        return this.stageClient == 11 ? 0 : this.stageClient + 1;
    }

    @SideOnly(value=Side.CLIENT)
    public int getStageCenter() {
        return this.stageClient;
    }

    public void updateEntity() {
        super.updateEntity();
        if (this.withConnect.length > 0) {
            this.readConnect();
        }
        if (this.worldObj.isRemote) {
            ++this.tickClient;
            if (this.tickClient == 2) {
                this.stageClient = this.nextState();
                this.tickClient = 0;
            }
        }
        this.stability = this.getStability();
        VanillaPacketDispatcher.dispatchTEToNearbyPlayers(this);
    }

    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        if (nbt.hasKey("efir")) {
            this.efir = nbt.getInteger("efir");
        }
        if (nbt.hasKey("stability")) {
            this.stability = nbt.getInteger("stability");
        }
        if (nbt.hasKey("link")) {
            NBTTagList connect = nbt.getTagList("link", 10);
            int[][] _te = new int[connect.tagCount()][3];
            for (int i = 0; i < connect.tagCount(); ++i) {
                NBTTagCompound _n = connect.getCompoundTagAt(i);
                int[] __x = new int[]{_n.getInteger("x"), _n.getInteger("y"), _n.getInteger("z")};
                _te[i] = __x;
            }
            this.withConnect = _te;
        }
    }

    private void readConnect() {
        int[][] cd = this.withConnect;
        if (cd.length == 0) {
            return;
        }
        if (this.worldObj == null) {
            return;
        }
        for (int[] coord : cd) {
            TileEntity te;
            if (coord.length != 3 || !((te = this.worldObj.getTileEntity(coord[0], coord[1], coord[2])) instanceof TileEAltar) && !(te instanceof TileTransformater)) continue;
            this.connected.add(te);
        }
        this.withConnect = new int[0][];
    }

    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setInteger("efir", this.efir);
        nbt.setInteger("stability", this.stability);
        NBTTagList connect = new NBTTagList();
        for (int i = 0; i < this.connected.size(); ++i) {
            if (this.connected.get(i) == null) continue;
            NBTTagCompound _n = new NBTTagCompound();
            _n.setInteger("x", this.connected.get(i).xCoord);
            _n.setInteger("y", this.connected.get(i).yCoord);
            _n.setInteger("z", this.connected.get(i).zCoord);
            connect.appendTag(_n);
        }
        nbt.setTag("link", connect);
    }

    public int getStability(TileEntity tile) {
        return this.stability;
    }

    private int getStability() {
        int stab = 100;
        if (!this.worldObj.isAirBlock(this.xCoord, this.yCoord + 1, this.zCoord)) {
            stab -= 50;
        }
        int[][] $patternAir = new int[][]{{1, 0, 0}, {-1, 0, 0}, {0, 0, 1}, {0, 0, -1}, {1, 1, 0}, {-1, 1, 0}, {0, 1, 1}, {0, 1, -1}};
        for (int $i = 0; $i < $patternAir.length; ++$i) {
            if (this.worldObj.isAirBlock(this.xCoord + $patternAir[$i][0], this.yCoord + $patternAir[$i][1], this.zCoord + $patternAir[$i][2])) continue;
            stab -= 5;
        }
        if (this.efir >= 900000) {
            stab -= 10;
        }
        return Math.max(0, stab);
    }

    public int getEfir() {
        return Math.max(this.efir, 0);
    }

    public boolean canAdd(int _efir) {
        return this.efir + _efir <= 1000000;
    }

    public void addEfir(int count) {
        this.efir += count;
    }

    public boolean takeEfir(int count) {
        if (this.efir < count) {
            return false;
        }
        this.efir -= count;
        return true;
    }

    public boolean hasLink(TileEntity te) {
        for (TileEntity tile : this.connected) {
            if (te.xCoord != tile.xCoord || te.yCoord != tile.yCoord || te.zCoord != tile.zCoord) continue;
            return true;
        }
        return false;
    }

    public void renderHUD(Minecraft minecraft, ScaledResolution scaledResolution, World world, int i, int i1, int i2) {
    }
}

