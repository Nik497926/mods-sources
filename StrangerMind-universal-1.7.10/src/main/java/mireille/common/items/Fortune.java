package mireille.common.items;

import net.minecraft.item.*;
import mireille.*;
import cpw.mods.fml.common.registry.*;

public class Fortune extends Item
{
    public Fortune(final String name, final String texture, final int j) {
        this.setUnlocalizedName(name);
        this.setTextureName(texture);
        this.setCreativeTab(StrangerMind.StrangerMind);
        this.canRepair = false;
        this.maxStackSize = j;
        GameRegistry.registerItem((Item)this, name);
    }
}
