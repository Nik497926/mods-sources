/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.tileentity.machine.smart;

import ic2.api.recipe.IMachineRecipeManager;
import ic2.api.recipe.Recipes;
import ru.simplemc.senergetics.common.tileentity.machine.smart.TileEntitySmartMachine;

public class TileEntitySmartCrusher
extends TileEntitySmartMachine<TileEntitySmartCrusher> {
    @Override
    protected IMachineRecipeManager getMachineRecipeManager() {
        return Recipes.macerator;
    }
}

