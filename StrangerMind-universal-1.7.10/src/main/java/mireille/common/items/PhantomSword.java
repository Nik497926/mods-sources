package mireille.common.items;

import mireille.core.*;
import mireille.*;
import cpw.mods.fml.common.registry.*;
import net.minecraft.item.*;

public class PhantomSword extends ItemSword
{
    public PhantomSword(final String name, final String texture) {
        super(ModItems.PHANTOM);
        this.setUnlocalizedName(name);
        this.setTextureName(texture);
        this.setCreativeTab(StrangerMind.StrangerMind);
        this.setMaxDamage(1000);
        GameRegistry.registerItem((Item)this, name);
    }
}
