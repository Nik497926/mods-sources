/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.core.network;

import com.meteor.extrabotany.common.core.network.MessageHandleGuiButtonPress;
import com.meteor.extrabotany.common.core.network.client.MessageToClientVisual;
import com.meteor.extrabotany.common.core.network.networkItem.MessageHandleGuiItemButtonPress;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class NetworkHandler2 {
    public static final SimpleNetworkWrapper INSTANCE2 = NetworkRegistry.INSTANCE.newSimpleChannel("ExtraBotania".toLowerCase());

    public static void init() {
        INSTANCE2.registerMessage(MessageHandleGuiButtonPress.class, MessageHandleGuiButtonPress.class, 1, Side.SERVER);
        INSTANCE2.registerMessage(MessageToClientVisual.class, MessageToClientVisual.class, 2, Side.SERVER);
        INSTANCE2.registerMessage(MessageToClientVisual.class, MessageToClientVisual.class, 3, Side.CLIENT);
        INSTANCE2.registerMessage(MessageHandleGuiItemButtonPress.class, MessageHandleGuiItemButtonPress.class, 4, Side.SERVER);
    }
}

