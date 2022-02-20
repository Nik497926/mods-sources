/*
 * Decompiled with CFR 0.152.
 */
package ru.deathcry.deathtrade;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import ru.deathcry.deathtrade.barter.BarterContainer;
import ru.deathcry.deathtrade.barter.BarterGui;
import ru.deathcry.deathtrade.barter.BarterHolder;

public class ModGuiHandler
implements IGuiHandler {
    public static final int EXCHANGE_BLOCK_GUI = 0;

    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case 0: {
                return new BarterGui(new BarterContainer(player, new BarterHolder(player.getDisplayName())));
            }
        }
        return null;
    }
}

