package mireille.common.items;

import net.minecraft.block.*;
import mireille.*;
import cpw.mods.fml.common.registry.*;
import net.minecraft.item.*;
import net.minecraft.entity.player.*;
import java.util.*;
import cpw.mods.fml.relauncher.*;

public class ItemReedSt extends ItemReed
{
    public ItemReedSt(final Block block, final String name, final String texture) {
        super(block);
        this.setUnlocalizedName(name);
        this.setTextureName(texture);
        this.setCreativeTab(StrangerMind.StrangerMind);
        GameRegistry.registerItem((Item)this, name);
    }
    
    @SideOnly(Side.CLIENT)
    public void addInformation(final ItemStack itemStack, final EntityPlayer player, final List list, final boolean isAdv) {
        int id = 0;
        if (this.getUnlocalizedName().equals("item.UrlPaint")) {
            id = 1;
        }
        else if (this.getUnlocalizedName().equals("item.UrlPaintAnim")) {
            id = 2;
        }
        switch (id) {
            case 1: {
                list.add(StrangerMind.resource("url_paint.tooltip_1"));
                list.add(StrangerMind.resource("url_paint.tooltip_2"));
                break;
            }
            case 2: {
                list.add(StrangerMind.resource("url_paint_anim.tooltip_1"));
                list.add(StrangerMind.resource("url_paint_anim.tooltip_2"));
                list.add(StrangerMind.resource("url_paint_anim.tooltip_3"));
                break;
            }
        }
    }
}
