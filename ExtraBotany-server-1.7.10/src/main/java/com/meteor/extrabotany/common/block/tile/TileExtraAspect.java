/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block.tile;

import com.meteor.extrabotany.common.block.BlockExtraAspects;
import com.meteor.extrabotany.common.block.tile.TileEntityContainer;
import com.meteor.extrabotany.common.item.basic.ItemModuleExtraAspects;
import java.util.Map;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectSource;
import thaumcraft.api.aspects.IEssentiaTransport;
import vazkii.botania.api.internal.VanillaPacketDispatcher;

public class TileExtraAspect extends TileEntityContainer implements IAspectSource, IEssentiaTransport {
    private AspectList current = new AspectList();
    private BlockExtraAspects.ExtraAspectType type = BlockExtraAspects.ExtraAspectType.TIER0;
    private int cd = 0;

    public TileExtraAspect() {
        super(1);
    }

    public void setType(int type) {
        this.type = BlockExtraAspects.ExtraAspectType.values()[type];
    }

    @Override
    public int getInventoryStackLimit() {
        return 1;
    }

    public int getMaxOneAsp() {
        if (this.getStackInSlot(0) != null && this.getStackInSlot(0).getItem() instanceof ItemModuleExtraAspects) {
            int mt = this.getStackInSlot(0).getItemDamage();
            if (mt >= 4) {
                mt = 0;
            }
            return mt == 3 ? 4096 : (mt == 2 ? 2048 : (mt == 1 ? 1024 : 256));
        }
        return 64;
    }

    @Override
    public void updateEntityServer() {

    }

    @Override
    public void updateEntityClient() {
    }

    void fillReservoir() {
        for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
            TileEntity te = ThaumcraftApiHelper.getConnectableTile((World)this.worldObj, (int)this.xCoord, (int)this.yCoord, (int)this.zCoord, (ForgeDirection)dir);
            if (te == null) continue;
            IEssentiaTransport ic = (IEssentiaTransport)te;
            if (!ic.canOutputTo(dir.getOpposite())) {
                return;
            }
            Aspect ta = null;
            if (ic.getEssentiaAmount(dir.getOpposite()) > 0 && ic.getSuctionAmount(dir.getOpposite()) < this.getSuctionAmount(dir) && this.getSuctionAmount(dir) >= ic.getMinimumSuction()) {
                ta = ic.getEssentiaType(dir.getOpposite());
            }
            if (ta == null || ic.getSuctionAmount(dir.getOpposite()) >= this.getSuctionAmount(dir)) continue;
            this.addToContainer(ta, ic.takeEssentia(ta, 1, dir.getOpposite()));
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        this.writeCustomNBT(nbt);
    }

    public void writeCustomNBT(NBTTagCompound nbt) {
        nbt.setInteger("type", this.type.ordinal());
        NBTTagCompound n = new NBTTagCompound();
        for (Map.Entry _a : this.current.aspects.entrySet()) {
            n.setInteger(((Aspect)_a.getKey()).getTag(), ((Integer)_a.getValue()).intValue());
        }
        nbt.setTag("asp", (NBTBase)n);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.readCustomNBT(nbt);
    }

    public void readCustomNBT(NBTTagCompound nbt) {
        if (nbt.hasKey("type")) {
            this.type = BlockExtraAspects.ExtraAspectType.values()[nbt.getInteger("type")];
        }
        if (nbt.hasKey("asp")) {
            NBTTagCompound n = nbt.getCompoundTag("asp");
            this.current = new AspectList();
            for (Object s : n.func_150296_c()) {
                String key = (String)s;
                this.current.add(Aspect.getAspect((String)key), n.getInteger(key));
            }
        }
    }

    public AspectList getAspects() {
        return this.current;
    }

    public void setAspects(AspectList aspectList) {
        this.current = aspectList.copy();
    }

    public boolean doesContainerAccept(Aspect aspect) {
        int ca = this.current.getAmount(aspect);
        if (ca == 0 && this.current.aspects.size() >= this.type.countAsp) {
            return false;
        }
        return ca > 0 && ca < this.getMaxOneAsp();
    }

    public int addToContainer(Aspect aspect, int i) {
        int ca = this.current.getAmount(aspect);
        if (ca == 0 && this.current.aspects.size() >= this.type.countAsp) {
            return i;
        }
        if (ca == 0) {
            int t = this.getMaxOneAsp();
            if (i > t) {
                int a = i - t;
                this.current.add(aspect, t);
                VanillaPacketDispatcher.dispatchTEToNearbyPlayers((TileEntity)this);
                return a;
            }
            this.current.add(aspect, i);
            VanillaPacketDispatcher.dispatchTEToNearbyPlayers((TileEntity)this);
            return 0;
        }
        if (ca > 0 && ca < this.getMaxOneAsp()) {
            int t = this.getMaxOneAsp() - ca;
            if (i > t) {
                int a = i - t;
                this.current.add(aspect, t);
                VanillaPacketDispatcher.dispatchTEToNearbyPlayers((TileEntity)this);
                return a;
            }
            this.current.add(aspect, i);
            VanillaPacketDispatcher.dispatchTEToNearbyPlayers((TileEntity)this);
            return 0;
        }
        return i;
    }

    public boolean takeFromContainer(Aspect aspect, int i) {
        int ca = this.current.getAmount(aspect);
        if (ca < i) {
            return false;
        }
        this.current.remove(aspect, i);
        return true;
    }

    public boolean takeFromContainer(AspectList aspectList) {
        return false;
    }

    public boolean doesContainerContainAmount(Aspect aspect, int i) {
        return this.current.getAmount(aspect) >= i;
    }

    public boolean doesContainerContain(AspectList ot) {
        for (Aspect tt : ot.getAspects()) {
            if (this.current.getAmount(tt) >= ot.getAmount(tt)) continue;
            return false;
        }
        return true;
    }

    public int containerContains(Aspect aspect) {
        return this.current.getAmount(aspect);
    }

    public boolean isConnectable(ForgeDirection forgeDirection) {
        return true;
    }

    public boolean canInputFrom(ForgeDirection forgeDirection) {
        return true;
    }

    public boolean canOutputTo(ForgeDirection forgeDirection) {
        return true;
    }

    public void setSuction(Aspect aspect, int i) {
    }

    public Aspect getSuctionType(ForgeDirection forgeDirection) {
        return null;
    }

    public int getSuctionAmount(ForgeDirection forgeDirection) {
        if (this.current.aspects.size() < this.type.countAsp) {
            return 65;
        }
        for (Aspect as : this.current.aspects.keySet()) {
            if ((Integer)this.current.aspects.get(as) >= this.getMaxOneAsp()) continue;
            return 65;
        }
        return 0;
    }

    public int takeEssentia(Aspect aspect, int i, ForgeDirection forgeDirection) {
        return this.takeFromContainer(aspect, i) ? i : 0;
    }

    public int addEssentia(Aspect aspect, int amount, ForgeDirection forgeDirection) {
        return amount - this.addToContainer(aspect, amount);
    }

    public Aspect getEssentiaType(ForgeDirection forgeDirection) {
        return this.current.aspects.size() > 0 ? this.current.getAspects()[0] : null;
    }

    public int getEssentiaAmount(ForgeDirection forgeDirection) {
        return this.current.visSize();
    }

    public int getMinimumSuction() {
        return 65;
    }

    public boolean renderExtendedTube() {
        return false;
    }
}

