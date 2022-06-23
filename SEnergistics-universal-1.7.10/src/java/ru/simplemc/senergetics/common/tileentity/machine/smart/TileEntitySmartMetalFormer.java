/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.tileentity.machine.smart;

import ic2.api.recipe.IMachineRecipeManager;
import ic2.api.recipe.Recipes;
import net.minecraft.nbt.NBTTagCompound;
import ru.simplemc.senergetics.common.tileentity.machine.smart.TileEntitySmartMachine;

public class TileEntitySmartMetalFormer
extends TileEntitySmartMachine<TileEntitySmartMetalFormer> {
    private int mode = 0;

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setInteger("MetalFormerMode", this.mode);
    }

    @Override
    protected IMachineRecipeManager getMachineRecipeManager() {
        if (this.mode == 0) {
            return Recipes.metalformerExtruding;
        }
        if (this.mode == 1) {
            return Recipes.metalformerCutting;
        }
        return Recipes.metalformerRolling;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.setMode(compound.hasKey("MetalFormerMode") ? compound.getInteger("MetalFormerMode") : 0);
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public int getMode() {
        return this.mode;
    }
}

