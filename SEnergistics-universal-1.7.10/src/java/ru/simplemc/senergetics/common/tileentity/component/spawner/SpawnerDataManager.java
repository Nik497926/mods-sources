/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.tileentity.component.spawner;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidTank;
import ru.simplemc.senergetics.common.block.machine.BlockSpawner;
import ru.simplemc.senergetics.common.tileentity.component.TileEnergySinkComponent;
import ru.simplemc.senergetics.common.tileentity.machine.TileEntitySpawner;

public class SpawnerDataManager {
    @SideOnly(value=Side.CLIENT)
    public EntityLivingBase createdEntity;
    public double angleRenderFromLastTick;
    public double angleRenderFromThisTick;
    private String entityClassName;
    public int currentEnergyPercent = 0;
    public int currentWorkingPercent = 0;
    public int currentExperiencePercent = 0;
    public int experienceTankAmount = 0;
    public int experienceTankCapacity = 0;
    public int standalone = 0;
    public int currentStep = BlockSpawner.IDLE_STEP;
    public int spawnerLevel = 0;
    public int workCounterMax = 0;

    public void updateServerEntity(TileEntitySpawner spawner, int workCounter, int workCounterMax, TileEnergySinkComponent energySink, FluidTank experienceTank, String entityClassName) {
        this.currentEnergyPercent = this.calculateCurrentEnergyPercent(energySink);
        this.currentWorkingPercent = this.calculateCurrentWorkPercent(workCounter, workCounterMax);
        this.currentExperiencePercent = this.calculateCurrentExperiencePercent(experienceTank);
        this.experienceTankAmount = experienceTank.getFluidAmount();
        this.experienceTankCapacity = experienceTank.getCapacity();
        this.currentStep = this.currentWorkingPercent > 90 ? BlockSpawner.COMPLETE_STEP : (this.currentWorkingPercent > 0 ? BlockSpawner.IN_PROGRESS_STEP : BlockSpawner.IDLE_STEP);
        this.workCounterMax = workCounterMax;
        this.entityClassName = entityClassName;
        this.standalone = energySink.isStandalone() ? 1 : 0;
        spawner.syncTileWithClient();
    }

    @SideOnly(value=Side.CLIENT)
    public void updateClientEntity(TileEntitySpawner spawner) {
        if (this.createdEntity != null) {
            this.angleRenderFromLastTick = this.angleRenderFromThisTick;
            this.angleRenderFromThisTick = (this.angleRenderFromThisTick + (double)(1000.0f / ((float)this.workCounterMax + 200.0f))) % 360.0;
        }
        if (this.createdEntity == null && this.entityClassName != null) {
            try {
                Class<?> entityClass = Class.forName(this.entityClassName);
                Constructor<?> entityClassConstructor = entityClass.getConstructor(World.class);
                this.createdEntity = (EntityLivingBase)entityClassConstructor.newInstance(spawner.getWorldObj());
                this.createdEntity.setPosition((double)spawner.xCoord, (double)spawner.yCoord, (double)spawner.zCoord);
            }
            catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException reflectiveOperationException) {
                // empty catch block
            }
        }
    }

    private int calculateCurrentEnergyPercent(TileEnergySinkComponent energySink) {
        return (int)Math.min(100.0f, (float)energySink.getEnergyStored() / (float)energySink.getCapacity() * 100.0f);
    }

    private int calculateCurrentWorkPercent(int workCounter, int maxWorkCounter) {
        return (int)Math.min(100.0f, (float)workCounter / (float)maxWorkCounter * 100.0f);
    }

    private int calculateCurrentExperiencePercent(FluidTank experienceTank) {
        return (int)Math.min(100.0f, (float)experienceTank.getFluidAmount() / (float)experienceTank.getCapacity() * 100.0f);
    }

    public S35PacketUpdateTileEntity createServerDataPacket(TileEntitySpawner spawner) {
        NBTTagCompound tagCompound = new NBTTagCompound();
        tagCompound.setByte("CurrentStep", (byte)this.currentStep);
        tagCompound.setByte("SpawnerLevel", (byte)this.spawnerLevel);
        tagCompound.setShort("WorkCounterMax", (short)this.workCounterMax);
        tagCompound.setInteger("ExperienceTankAmount", this.experienceTankAmount);
        tagCompound.setInteger("ExperienceTankCapacity", this.experienceTankCapacity);
        if (this.entityClassName != null) {
            tagCompound.setString("EntityClassName", this.entityClassName);
        }
        return new S35PacketUpdateTileEntity(spawner.xCoord, spawner.yCoord, spawner.zCoord, 3, tagCompound);
    }

    @SideOnly(value=Side.CLIENT)
    public void receiveServerDataPacket(TileEntitySpawner spawner, S35PacketUpdateTileEntity dataPacket) {
        NBTTagCompound tagCompound = dataPacket.func_148857_g();
        int currentStepClient = this.currentStep;
        int spawnerLevelClient = this.spawnerLevel;
        String entityClassClient = this.entityClassName;
        this.currentStep = tagCompound.getByte("CurrentStep");
        this.spawnerLevel = tagCompound.getByte("SpawnerLevel");
        this.workCounterMax = tagCompound.getShort("WorkCounterMax");
        this.entityClassName = tagCompound.getString("EntityClassName");
        this.experienceTankAmount = tagCompound.getInteger("ExperienceTankAmount");
        this.experienceTankCapacity = tagCompound.getInteger("ExperienceTankCapacity");
        if (entityClassClient != null && this.entityClassName != null && !entityClassClient.equals(this.entityClassName)) {
            this.createdEntity = null;
        }
        if (currentStepClient != this.currentStep || spawnerLevelClient != this.spawnerLevel) {
            spawner.getWorldObj().markBlockRangeForRenderUpdate(spawner.xCoord, spawner.yCoord, spawner.zCoord, spawner.xCoord, spawner.yCoord, spawner.zCoord);
        }
    }
}

