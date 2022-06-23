/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.tileentity.component.process.molecular;

import ru.simplemc.senergetics.common.tileentity.component.process.AbstractProcessHolder;
import ru.simplemc.senergetics.common.tileentity.component.process.molecular.MolecularProcess;
import ru.simplemc.senergetics.common.tileentity.machine.TileEntityMolecularCollector;

public class MolecularProcessHolder
extends AbstractProcessHolder<TileEntityMolecularCollector, MolecularProcess> {
    public MolecularProcessHolder(TileEntityMolecularCollector machine) {
        super(machine, MolecularProcess::new, () -> new MolecularProcess[5]);
    }
}

