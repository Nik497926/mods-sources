/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.projectile.EntityPotion
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemPotion
 *  net.minecraft.potion.PotionHelper
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package net.divinerpg.client.render;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.potion.PotionHelper;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class RenderIconProjectile
extends Render {
    private Item item;
    private int damage;

    public RenderIconProjectile(Item par1Item, int par2) {
        this.item = par1Item;
        this.damage = par2;
    }

    public RenderIconProjectile(Item par1Item) {
        this(par1Item, 0);
    }

    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        IIcon iicon = this.item.getIconFromDamage(this.damage);
        if (iicon != null) {
            GL11.glPushMatrix();
            GL11.glTranslatef((float)((float)par2), (float)((float)par4), (float)((float)par6));
            GL11.glEnable((int)32826);
            GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
            this.bindEntityTexture(par1Entity);
            Tessellator tessellator = Tessellator.instance;
            if (iicon == ItemPotion.func_94589_d((String)"bottle_splash")) {
                int i = PotionHelper.func_77915_a((int)((EntityPotion)par1Entity).getPotionDamage(), (boolean)false);
                float f2 = (float)(i >> 16 & 0xFF) / 255.0f;
                float f3 = (float)(i >> 8 & 0xFF) / 255.0f;
                float f4 = (float)(i & 0xFF) / 255.0f;
                GL11.glColor3f((float)f2, (float)f3, (float)f4);
                GL11.glPushMatrix();
                this.render(tessellator, ItemPotion.func_94589_d((String)"overlay"));
                GL11.glPopMatrix();
                GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
            }
            this.render(tessellator, iicon);
            GL11.glDisable((int)32826);
            GL11.glPopMatrix();
        }
    }

    protected ResourceLocation getEntityTexture(Entity par1Entity) {
        return TextureMap.locationItemsTexture;
    }

    private void render(Tessellator par1Tessellator, IIcon par2Icon) {
        float f = par2Icon.getMinU();
        float f1 = par2Icon.getMaxU();
        float f2 = par2Icon.getMinV();
        float f3 = par2Icon.getMaxV();
        float f4 = 1.0f;
        float f5 = 0.5f;
        float f6 = 0.25f;
        GL11.glRotatef((float)(180.0f - this.renderManager.playerViewY), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)(-this.renderManager.playerViewX), (float)1.0f, (float)0.0f, (float)0.0f);
        par1Tessellator.startDrawingQuads();
        par1Tessellator.setNormal(0.0f, 1.0f, 0.0f);
        par1Tessellator.addVertexWithUV((double)(0.0f - f5), (double)(0.0f - f6), 0.0, (double)f, (double)f3);
        par1Tessellator.addVertexWithUV((double)(f4 - f5), (double)(0.0f - f6), 0.0, (double)f1, (double)f3);
        par1Tessellator.addVertexWithUV((double)(f4 - f5), (double)(f4 - f6), 0.0, (double)f1, (double)f2);
        par1Tessellator.addVertexWithUV((double)(0.0f - f5), (double)(f4 - f6), 0.0, (double)f, (double)f2);
        par1Tessellator.draw();
    }
}

