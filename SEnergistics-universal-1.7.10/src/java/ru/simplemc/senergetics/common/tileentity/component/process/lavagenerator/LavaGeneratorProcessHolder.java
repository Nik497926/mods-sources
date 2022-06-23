/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.tileentity.component.process.lavagenerator;

import ru.simplemc.senergetics.common.tileentity.component.process.AbstractProcessHolder;
import ru.simplemc.senergetics.common.tileentity.component.process.lavagenerator.LavaGeneratorProcess;
import ru.simplemc.senergetics.common.tileentity.machine.TileEntityLavaGenerator;

public class LavaGeneratorProcessHolder
extends AbstractProcessHolder<TileEntityLavaGenerator, LavaGeneratorProcess> {
    public LavaGeneratorProcessHolder(TileEntityLavaGenerator tileEntity) {
        super(tileEntity, LavaGeneratorProcess::new, () -> new LavaGeneratorProcess[7]);
    }
}

