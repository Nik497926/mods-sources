/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.item.equipment;

import baubles.api.BaubleType;
import baubles.common.container.InventoryBaubles;
import baubles.common.lib.PlayerHandler;
import com.meteor.extrabotany.common.entity.gaia.EntityGaiaIII;
import com.meteor.extrabotany.common.item.ModItems;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.item.IBaubleRender;
import vazkii.botania.api.item.ICosmeticBauble;
import vazkii.botania.common.item.equipment.bauble.ItemBauble;

public class ItemGaiaWise
extends ItemBauble
implements ICosmeticBauble {
    public ItemGaiaWise(String name) {
        super(name);
        MinecraftForge.EVENT_BUS.register(this);
        FMLCommonHandler.instance().bus().register(this);
        this.setMaxStackSize(1);
    }

    public void onPlayerBaubleRender(ItemStack stack, RenderPlayerEvent event, IBaubleRender.RenderType type) {
        Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationItemsTexture);
        if (type != IBaubleRender.RenderType.HEAD) {
            IBaubleRender.Helper.rotateIfSneaking(event.entityPlayer);
            GL11.glRotatef(180.0f, 1.0f, 0.0f, 0.0f);
            GL11.glTranslatef(-0.5f, -0.8f, 0.15f);
            GL11.glScalef(0.255f, 0.255f, 0.255f);
            GL11.glTranslatef(0.9f, 1.9f, 0.0f);
            IIcon icon = this.getIconFromDamage(0);
            float f = icon.getMinU();
            float f1 = icon.getMaxU();
            float f2 = icon.getMinV();
            float f3 = icon.getMaxV();
            ItemRenderer.renderItemIn2D(Tessellator.instance, f1, f2, f, f3, icon.getIconWidth(), icon.getIconHeight(), 0.0625f);
        }
    }

    public BaubleType getBaubleType(ItemStack arg0) {
        return BaubleType.RING;
    }

    public static ItemStack getGaiaWise(EntityPlayer player) {
        InventoryBaubles baubles = PlayerHandler.getPlayerBaubles(player);
        ItemStack stack1 = baubles.func_70301_a(1);
        ItemStack stack2 = baubles.func_70301_a(2);
        return ItemGaiaWise.isGaiaWise(stack1) ? stack1 : (ItemGaiaWise.isGaiaWise(stack2) ? stack2 : null);
    }

    private static boolean isGaiaWise(ItemStack stack) {
        return stack != null && stack.getItem() == ModItems.gaiawise;
    }

    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
        return EntityGaiaIII.spawn(par2EntityPlayer, par1ItemStack, par3World, par4, par5, par6, true);
    }
}

