package mireille.network;

import cpw.mods.fml.common.network.simpleimpl.*;
import cpw.mods.fml.common.network.*;
import cpw.mods.fml.relauncher.*;

public class NetworkHandler
{
    public static SimpleNetworkWrapper network;
    private static int ID;
    
    private static int nextID() {
        return NetworkHandler.ID++;
    }
    
    public static void init(final String channelName) {
        (NetworkHandler.network = NetworkRegistry.INSTANCE.newSimpleChannel(channelName)).registerMessage((Class)OpenBox.Handler.class, (Class)OpenBox.class, nextID(), Side.SERVER);
        NetworkHandler.network.registerMessage((Class)OpenTrophyGuiOnClient.Handler.class, (Class)OpenTrophyGuiOnClient.class, nextID(), Side.CLIENT);
        NetworkHandler.network.registerMessage((Class)UpdateConfig.Handler.class, (Class)UpdateConfig.class, nextID(), Side.CLIENT);
        NetworkHandler.network.registerMessage((Class)PacketSkinWorkbench.Handler.class, (Class)PacketSkinWorkbench.class, nextID(), Side.SERVER);
    }
    
    static {
        NetworkHandler.ID = 0;
    }
}
