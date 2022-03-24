package mireille.common.items;

import mireille.*;
import cpw.mods.fml.common.registry.*;
import net.minecraft.item.*;
import net.minecraft.entity.player.*;
import java.util.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.world.*;
import mireille.network.*;
import cpw.mods.fml.common.network.simpleimpl.*;
import net.minecraft.entity.*;

public class ItemCase extends Item
{
    private ArrayList<ItemBox> Case;
    private boolean giveAll;
    private int tooltips;
    private String tag;
    
    public ItemCase(final String name, final String texture, final ArrayList<ItemBox> Case, final boolean giveAll) {
        this.tooltips = 0;
        this.tag = "";
        this.setUnlocalizedName(name);
        this.setTextureName(texture);
        this.setCreativeTab(StrangerMind.StrangerMind);
        this.setMaxStackSize(1);
        GameRegistry.registerItem((Item)this, name);
        this.Case = Case;
        this.giveAll = giveAll;
    }
    
    public ItemCase(final String name, final String texture, final ArrayList<ItemBox> Case, final boolean giveAll, final String tag, final int tooltips) {
        this.tooltips = 0;
        this.tag = "";
        this.setUnlocalizedName(name);
        this.setTextureName(texture);
        this.setCreativeTab(StrangerMind.StrangerMind);
        this.setMaxStackSize(1);
        GameRegistry.registerItem((Item)this, name);
        this.Case = Case;
        this.giveAll = giveAll;
        this.tag = tag;
        this.tooltips = tooltips;
    }
    
    @SideOnly(Side.CLIENT)
    public void addInformation(final ItemStack itemStack, final EntityPlayer player, final List list, final boolean isAdv) {
        for (int i = 1; i <= this.tooltips; ++i) {
            list.add(StrangerMind.resource("case.tooltip." + this.tag + "_" + i));
        }
    }
    
    public ItemStack onItemRightClick(final ItemStack stack, final World world, final EntityPlayer player) {
        if (world.isRemote) {
            NetworkHandler.network.sendToServer((IMessage)new OpenBox(this));
        }
        else {
            player.getEntityData().setInteger("fireworksLeft", 10);
            player.getEntityData().setBoolean("fireworkDelay", false);
            world.playSoundAtEntity((Entity)player, "mireille:open_box", 0.75f, 1.0f);
        }
        return stack;
    }
    
    public int getSize() {
        return this.Case.size();
    }
    
    public ItemBox getCurrentItem(final int id) {
        return this.Case.get(id);
    }
    
    public boolean isGiveAll() {
        return this.giveAll;
    }
}
