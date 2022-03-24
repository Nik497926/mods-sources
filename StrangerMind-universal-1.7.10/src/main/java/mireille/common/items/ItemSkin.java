package mireille.common.items;

import mireille.*;
import cpw.mods.fml.common.registry.*;
import net.minecraft.item.*;
import net.minecraft.entity.player.*;
import java.util.*;
import cpw.mods.fml.relauncher.*;

public class ItemSkin extends ItemSword
{
    public ItemSkin(final String name) {
        super(Item.ToolMaterial.WOOD);
        this.setUnlocalizedName(name);
        this.setMaxDamage(-1);
        this.setCreativeTab(StrangerMind.StrangerMind);
        GameRegistry.registerItem((Item)this, name);
    }
    
    @SideOnly(Side.CLIENT)
    public void addInformation(final ItemStack itemStack, final EntityPlayer player, final List list, final boolean isAdv) {
        list.add(StrangerMind.resource("dw.skin.tooltip_1"));
        list.add(StrangerMind.resource("dw.skin.tooltip_2"));
        list.add(StrangerMind.resource("dw.skin.tooltip_3"));
        list.add(StrangerMind.resource("dw.skin.tooltip_4"));
    }
}
