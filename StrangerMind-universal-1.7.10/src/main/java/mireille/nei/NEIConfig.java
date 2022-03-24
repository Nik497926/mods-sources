package mireille.nei;

import cpw.mods.fml.common.*;
import mireille.*;
import codechicken.nei.api.*;
import codechicken.nei.api.API;
import codechicken.nei.recipe.*;
import mireille.core.*;
import net.minecraft.item.*;

@Optional.Interface(iface = "codechicken.nei.api.API", modid = "NotEnoughItems")
public class NEIConfig implements IConfigureNEI
{
    @Optional.Method(modid = "NotEnoughItems")
    public String getName() {
        return "StrangerMind NEIIntegration";
    }
    
    @Optional.Method(modid = "NotEnoughItems")
    public String getVersion() {
        return StrangerMind.class.getAnnotation(Mod.class).version();
    }
    
    @Optional.Method(modid = "NotEnoughItems")
    public void loadConfig() {
        API.registerRecipeHandler((ICraftingHandler)new NEIHandlerMirrorWorkbench());
        API.registerUsageHandler((IUsageHandler)new NEIHandlerMirrorWorkbench());
        API.hideItem(new ItemStack(ModBlocks.frame));
        API.hideItem(new ItemStack(ModBlocks.frameAnim));
    }
}
