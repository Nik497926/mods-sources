package mireille.common.items;

import mireille.core.*;
import mireille.*;
import cpw.mods.fml.common.registry.*;
import net.minecraft.item.*;
import net.minecraft.world.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.potion.*;

public class MythicalSword extends ItemSword
{
    public MythicalSword(final String name, final String texture) {
        super(ModItems.MYTHICAL);
        this.setUnlocalizedName(name);
        this.setTextureName(texture);
        this.setCreativeTab(StrangerMind.StrangerMind);
        this.setMaxDamage(100000);
        GameRegistry.registerItem((Item)this, name);
    }
    
    public void onUpdate(final ItemStack stack, final World world, final Entity entity, final int par4, final boolean par5) {
        super.onUpdate(stack, world, entity, par4, par5);
        if (entity instanceof EntityPlayer) {
            final EntityPlayer player = (EntityPlayer)entity;
            final ItemStack equipped = player.getCurrentEquippedItem();
            if (equipped == stack) {
                player.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 2, 5, true));
                player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 2, 2, true));
                player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 2, 5, true));
                player.addPotionEffect(new PotionEffect(Potion.jump.id, 2, 5, true));
            }
        }
    }
}
