package mireille.common.items;

import net.minecraft.item.*;
import mireille.*;
import cpw.mods.fml.common.registry.*;

public class ItemsAxe extends ItemAxe
{
    public ItemsAxe(final String name, final String texture) {
        super(Item.ToolMaterial.EMERALD);
        this.setUnlocalizedName(name);
        this.setTextureName(texture);
        this.setCreativeTab(StrangerMind.StrangerMind);
        this.setMaxDamage(1000);
        GameRegistry.registerItem((Item)this, name);
    }
}
