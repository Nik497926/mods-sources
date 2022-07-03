/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.item.equipment;

import baubles.api.BaubleType;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.event.RenderPlayerEvent;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.item.IBaubleRender;
import vazkii.botania.api.item.ICosmeticBauble;
import vazkii.botania.api.recipe.IFlowerComponent;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.item.equipment.bauble.ItemBauble;

public class ItemBaubleDog
extends ItemBauble
implements ICosmeticBauble,
IFlowerComponent {
    private static final int SUBTYPES = 4;
    IIcon[] icons;

    public ItemBaubleDog() {
        super("dog");
        this.setHasSubtypes(true);
        this.setMaxStackSize(1);
    }

    public void registerIcons(IIconRegister par1IconRegister) {
        this.icons = new IIcon[4];
        for (int i = 0; i < 4; ++i) {
            this.icons[i] = IconHelper.forItem((IIconRegister)par1IconRegister, (Item)this, (int)i);
        }
    }

    public void getSubItems(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < 4; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    public IIcon getIconFromDamage(int dmg) {
        return this.icons[Math.min(3, dmg)];
    }

    public String getUnlocalizedName(ItemStack par1ItemStack) {
        return super.getUnlocalizedName(par1ItemStack) + par1ItemStack.getItemDamage();
    }

    public void onPlayerBaubleRender(ItemStack stack, RenderPlayerEvent event, IBaubleRender.RenderType type) {
        Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationItemsTexture);
        IBaubleRender.Helper.translateToHeadLevel((EntityPlayer)event.entityPlayer);
        if (type == IBaubleRender.RenderType.HEAD) {
            this.faceTranslate();
            this.scale(0.5f);
            GL11.glTranslatef((float)0.3f, (float)0.7f, (float)0.5f);
            this.renderIcon(stack.getItemDamage());
        }
    }

    public BaubleType getBaubleType(ItemStack arg0) {
        return BaubleType.AMULET;
    }

    public void faceTranslate() {
        GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glTranslatef((float)-0.4f, (float)0.1f, (float)-0.25f);
    }

    public void chestTranslate() {
        GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glTranslatef((float)-0.5f, (float)-0.7f, (float)0.15f);
    }

    public void scale(float f) {
        GL11.glScalef((float)f, (float)f, (float)f);
    }

    public void renderIcon(int i) {
        IIcon icon = this.icons[i];
        float f = icon.getMinU();
        float f1 = icon.getMaxU();
        float f2 = icon.getMinV();
        float f3 = icon.getMaxV();
        ItemRenderer.renderItemIn2D((Tessellator)Tessellator.instance, (float)f1, (float)f2, (float)f, (float)f3, (int)icon.getIconWidth(), (int)icon.getIconHeight(), (float)0.25f);
    }

    public boolean canFit(ItemStack arg0, IInventory arg1) {
        return true;
    }

    public int getParticleColor(ItemStack arg0) {
        return 0;
    }
}

