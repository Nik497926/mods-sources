/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fluids.Fluid
 *  net.minecraftforge.fluids.FluidRegistry
 */
package net.divinerpg.blocks.base;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class ModFluid
extends Fluid {
    public ModFluid(String fluidName, int density, int viscosity) {
        super(fluidName);
        this.setDensity(density);
        this.setViscosity(viscosity);
        FluidRegistry.registerFluid((Fluid)this);
    }
}

