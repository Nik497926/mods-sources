/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 */
package info.jbcs.minecraft.vending.proxy;

import cpw.mods.fml.relauncher.Side;
import info.jbcs.minecraft.vending.network.MessagePipeline;
import info.jbcs.minecraft.vending.network.MsgAdvVenSetItem;
import info.jbcs.minecraft.vending.network.MsgPrice;
import info.jbcs.minecraft.vending.network.MsgWrench;
import info.jbcs.minecraft.vending.network.SetMoneyMsg;

public class CommonProxy {
    public void registerEventHandlers() {
    }

    public void registerPackets(MessagePipeline pipeline) {
        pipeline.registerMessage(SetMoneyMsg.Handler.class, SetMoneyMsg.class, 2, Side.SERVER);
        pipeline.registerMessage(MsgPrice.Handler.class, MsgPrice.class, 0, Side.CLIENT);
        pipeline.registerMessage(MsgAdvVenSetItem.Handler.class, MsgAdvVenSetItem.class, 0, Side.SERVER);
        pipeline.registerMessage(MsgWrench.Handler.class, MsgWrench.class, 1, Side.SERVER);
    }

    public void registerRenderers() {
    }
}

