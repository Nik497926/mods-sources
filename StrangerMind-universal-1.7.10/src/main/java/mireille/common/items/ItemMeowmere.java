package mireille.common.items;

import mireille.*;
import cpw.mods.fml.common.registry.*;
import net.minecraft.item.*;
import net.minecraft.entity.player.*;
import java.util.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.world.*;
import net.minecraft.entity.*;
import mireille.api.*;
import mireille.core.*;

public class ItemMeowmere extends ItemSword
{
    public ItemMeowmere(final String name, final String texture) {
        super(ModMaterials.MatMeowmere);
        this.setUnlocalizedName(name);
        this.setTextureName(texture);
        this.setCreativeTab(StrangerMind.StrangerMind);
        GameRegistry.registerItem((Item)this, name);
    }
    
    @SideOnly(Side.CLIENT)
    public void addInformation(final ItemStack itemStack, final EntityPlayer player, final List list, final boolean isAdv) {
        list.add(StrangerMind.resource("item_meowmere.tooltip_1"));
        list.add(StrangerMind.resource("item_meowmere.tooltip_2"));
    }
    
    public ItemStack onItemRightClick(final ItemStack par1ItemStack, final World par2World, final EntityPlayer par3EntityPlayer) {
        if (!par3EntityPlayer.isSwingInProgress) {
            par2World.playSoundAtEntity((Entity)par3EntityPlayer, "mob.cat.meow", 0.5f, 1.0f / (Item.itemRand.nextFloat() * 0.4f + 0.8f));
            par3EntityPlayer.swingItem();
            if (par1ItemStack.attemptDamageItem(1, par3EntityPlayer.getRNG())) {
                --par1ItemStack.stackSize;
                if (par1ItemStack.stackSize < 1) {
                    par1ItemStack.stackSize = 0;
                }
                par1ItemStack.setItemDamage(0);
                par2World.playSoundAtEntity((Entity)par3EntityPlayer, "random.break", 0.5f, 0.4f / (Item.itemRand.nextFloat() * 0.4f + 0.8f));
            }
            par1ItemStack.damageItem(5, (EntityLivingBase)par3EntityPlayer);
            par2World.spawnEntityInWorld((Entity)new Meowmere_prog(par2World, (EntityLivingBase)par3EntityPlayer));
        }
        return par1ItemStack;
    }
    
    public boolean getIsRepairable(final ItemStack toRepairItem, final ItemStack repairItem) {
        return repairItem.getItem() == ModItems.Rainbow;
    }
}
