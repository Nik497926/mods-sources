package ru.obvilion.additionpanels.common.tileentity;

import appeng.tile.misc.TileInterface;
import ru.obvilion.additionpanels.IAppEngInternalInventory;

public class BaseTileAeInterface extends TileInterface {

    public BaseTileAeInterface(int patternSize) {
        ((IAppEngInternalInventory) getInterfaceDuality().getPatterns()).setSize(patternSize);
    }
}
