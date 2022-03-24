package mireille.common.items;

import mireille.api.*;
import mireille.core.*;
import mireille.*;
import cpw.mods.fml.common.registry.*;
import net.minecraft.entity.player.*;
import java.util.*;
import cpw.mods.fml.relauncher.*;
import org.lwjgl.opengl.*;
import net.minecraft.world.*;
import net.minecraft.potion.*;
import net.minecraft.entity.*;
import net.minecraft.item.*;

public class ItemSmoke extends ItemSword implements ItemRenderInterface
{
    public ItemSmoke(final String name, final String texture) {
        super(ModMaterials.MatShield);
        this.setUnlocalizedName(name);
        this.setTextureName(texture);
        this.setCreativeTab(StrangerMind.StrangerMind);
        this.setMaxStackSize(1);
        this.setMaxDamage(200);
        GameRegistry.registerItem((Item)this, name);
    }
    
    @SideOnly(Side.CLIENT)
    public void addInformation(final ItemStack itemStack, final EntityPlayer player, final List list, final boolean isAdv) {
        list.add(StrangerMind.resource("item_smoke.tooltip_1"));
        list.add(StrangerMind.resource("item_smoke.tooltip_2"));
    }
    
    public void renderSpecial() {
        GL11.glScalef(0.7f, 0.7f, 0.7f);
        GL11.glTranslatef(-0.2f, 1.0f, 0.3f);
        GL11.glRotatef(-500.0f, 10.0f, 5.0f, 0.0f);
    }
    
    public ItemStack onItemUseFinish(final ItemStack par1ItemStack, final World par2World, final EntityPlayer par3EntityPlayer) {
        par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.jump.id, 300, 6));
        par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 300, 3));
        par1ItemStack.damageItem(10, (EntityLivingBase)par3EntityPlayer);
        return par1ItemStack;
    }
    
    public void onUsingTick(final ItemStack stack, final EntityPlayer player, final int count) {
        this.createParticleHandEffect(player.worldObj, (Entity)player);
    }
    
    public void createParticleHandEffect(final World w, final Entity e) {
        final double adjAngle = 60.0;
        final double dist = 1.0;
        final double posX = e.posX - Math.cos((-e.rotationYaw + adjAngle) * 0.01745329) * dist;
        final double posY = e.posY - 0.5 - Math.sin(e.rotationPitch / 180.0f * 3.1415927f) * 0.8;
        final double posZ = e.posZ + Math.sin((-e.rotationYaw + adjAngle) * 0.01745329) * dist;
        w.spawnParticle("flame", posX, posY + 0.4, posZ, 0.0, 0.0, 0.0);
        w.spawnParticle("smoke", posX, posY + 0.8, posZ, 0.0, 0.0, 0.0);
        w.spawnParticle("smoke", posX, posY + 0.6, posZ, 0.0, 0.0, 0.0);
    }
    
    public int getMaxItemUseDuration(final ItemStack par1ItemStack) {
        return 48;
    }
    
    public EnumAction getItemUseAction(final ItemStack par1ItemStack) {
        return EnumAction.bow;
    }
    
    public ItemStack onItemRightClick(final ItemStack par1ItemStack, final World par2World, final EntityPlayer par3EntityPlayer) {
        par2World.playSoundAtEntity((Entity)par3EntityPlayer, "mireille:smoke", 1.0f, 1.0f);
        par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
        return par1ItemStack;
    }
}
