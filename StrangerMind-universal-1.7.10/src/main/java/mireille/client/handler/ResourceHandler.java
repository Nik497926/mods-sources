package mireille.client.handler;

import net.minecraft.util.*;
import net.minecraft.client.*;
import java.util.*;

public class ResourceHandler
{
    private static Map<String, ResourceLocation> cachedResources;
    
    public static void bindTexture(final ResourceLocation texture) {
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
    }
    
    public static ResourceLocation getResource(final String rs) {
        if (!ResourceHandler.cachedResources.containsKey(rs)) {
            ResourceHandler.cachedResources.put(rs, new ResourceLocation("mireille:" + rs));
        }
        return ResourceHandler.cachedResources.get(rs);
    }
    
    public static void bindResource(final String rs) {
        bindTexture(getResource(rs));
    }
    
    static {
        ResourceHandler.cachedResources = new HashMap<String, ResourceLocation>();
    }
}
