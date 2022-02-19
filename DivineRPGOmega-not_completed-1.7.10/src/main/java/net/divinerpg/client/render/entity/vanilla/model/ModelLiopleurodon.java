/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 *  org.lwjgl.opengl.GL11
 */
package net.divinerpg.client.render.entity.vanilla.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelLiopleurodon
extends ModelBase {
    ModelRenderer tail3;
    ModelRenderer tail4;
    ModelRenderer tooth1;
    ModelRenderer tooth2;
    ModelRenderer tooth3;
    ModelRenderer tooth4;
    ModelRenderer tooth5;
    ModelRenderer tooth6;
    ModelRenderer tail1;
    ModelRenderer body;
    ModelRenderer tail2;
    ModelRenderer front;
    ModelRenderer neck;
    ModelRenderer head;
    ModelRenderer jawTop;
    ModelRenderer jawBottom;
    ModelRenderer fin3A;
    ModelRenderer fin3B;
    ModelRenderer fin4B;
    ModelRenderer fin4A;
    ModelRenderer fin1A;
    ModelRenderer fin1B;
    ModelRenderer fin2B;
    ModelRenderer fin2A;

    public ModelLiopleurodon() {
        this.textureWidth = 80;
        this.textureHeight = 80;
        this.tail3 = new ModelRenderer((ModelBase)this, 5, 33);
        this.tail3.addBox(0.0f, 0.0f, 0.0f, 6, 6, 9);
        this.tail3.setRotationPoint(-3.0f, 13.0f, -41.0f);
        this.tail3.setTextureSize(80, 80);
        this.tail3.mirror = true;
        this.setRotation(this.tail3, 0.0f, 0.0f, 0.0f);
        this.tail4 = new ModelRenderer((ModelBase)this, 8, 30);
        this.tail4.addBox(0.0f, 0.0f, 0.0f, 4, 4, 7);
        this.tail4.setRotationPoint(-2.0f, 14.0f, -47.0f);
        this.tail4.setTextureSize(80, 80);
        this.tail4.mirror = true;
        this.setRotation(this.tail4, 0.0f, 0.0f, 0.0f);
        this.tooth1 = new ModelRenderer((ModelBase)this, 0, 77);
        this.tooth1.addBox(0.0f, 0.0f, 0.0f, 1, 1, 2);
        this.tooth1.setRotationPoint(3.0f, 14.0f, 46.0f);
        this.tooth1.setTextureSize(80, 80);
        this.tooth1.mirror = true;
        this.setRotation(this.tooth1, 0.0f, 0.0f, 0.0f);
        this.tooth2 = new ModelRenderer((ModelBase)this, 0, 77);
        this.tooth2.addBox(0.0f, 0.0f, 0.0f, 1, 1, 2);
        this.tooth2.setRotationPoint(3.0f, 14.0f, 49.0f);
        this.tooth2.setTextureSize(80, 80);
        this.tooth2.mirror = true;
        this.setRotation(this.tooth2, 0.0f, 0.0f, 0.0f);
        this.tooth3 = new ModelRenderer((ModelBase)this, 0, 77);
        this.tooth3.addBox(0.0f, 0.0f, 0.0f, 1, 1, 2);
        this.tooth3.setRotationPoint(3.0f, 14.0f, 52.0f);
        this.tooth3.setTextureSize(80, 80);
        this.tooth3.mirror = true;
        this.setRotation(this.tooth3, 0.0f, 0.0f, 0.0f);
        this.tooth4 = new ModelRenderer((ModelBase)this, 0, 77);
        this.tooth4.addBox(0.0f, 0.0f, 0.0f, 1, 1, 2);
        this.tooth4.setRotationPoint(-4.0f, 14.0f, 46.0f);
        this.tooth4.setTextureSize(80, 80);
        this.tooth4.mirror = true;
        this.setRotation(this.tooth4, 0.0f, 0.0f, 0.0f);
        this.tooth5 = new ModelRenderer((ModelBase)this, 0, 77);
        this.tooth5.addBox(0.0f, 0.0f, 0.0f, 1, 1, 2);
        this.tooth5.setRotationPoint(-4.0f, 14.0f, 49.0f);
        this.tooth5.setTextureSize(80, 80);
        this.tooth5.mirror = true;
        this.setRotation(this.tooth5, 0.0f, 0.0f, 0.0f);
        this.tooth6 = new ModelRenderer((ModelBase)this, 0, 77);
        this.tooth6.addBox(0.0f, 0.0f, 0.0f, 1, 1, 2);
        this.tooth6.setRotationPoint(-4.0f, 14.0f, 52.0f);
        this.tooth6.setTextureSize(80, 80);
        this.tooth6.mirror = true;
        this.setRotation(this.tooth6, 0.0f, 0.0f, 0.0f);
        this.tail1 = new ModelRenderer((ModelBase)this, 17, 14);
        this.tail1.addBox(-4.0f, -5.0f, -26.0f, 12, 11, 14);
        this.tail1.setRotationPoint(-2.0f, 15.0f, 0.0f);
        this.tail1.setTextureSize(80, 80);
        this.tail1.mirror = true;
        this.setRotation(this.tail1, 0.0f, 0.0f, 0.0f);
        this.body = new ModelRenderer((ModelBase)this, 5, 6);
        this.body.addBox(-7.0f, -17.0f, -12.0f, 16, 28, 15);
        this.body.setRotationPoint(-1.0f, 11.0f, 3.0f);
        this.body.setTextureSize(80, 80);
        this.body.mirror = true;
        this.setRotation(this.body, 1.570796f, 0.0f, 0.0f);
        this.tail2 = new ModelRenderer((ModelBase)this, 31, 9);
        this.tail2.addBox(0.0f, 0.0f, 0.0f, 8, 8, 11);
        this.tail2.setRotationPoint(-4.0f, 12.0f, -33.0f);
        this.tail2.setTextureSize(80, 80);
        this.tail2.mirror = true;
        this.setRotation(this.tail2, 0.0f, 0.0f, 0.0f);
        this.front = new ModelRenderer((ModelBase)this, 6, 22);
        this.front.addBox(0.0f, 0.0f, 0.0f, 12, 10, 14);
        this.front.setRotationPoint(-6.0f, 9.0f, 11.0f);
        this.front.setTextureSize(80, 80);
        this.front.mirror = true;
        this.setRotation(this.front, 0.0f, 0.0f, 0.0f);
        this.neck = new ModelRenderer((ModelBase)this, 21, 7);
        this.neck.addBox(0.0f, 0.0f, 0.0f, 10, 7, 13);
        this.neck.setRotationPoint(-5.0f, 10.0f, 21.0f);
        this.neck.setTextureSize(80, 80);
        this.neck.mirror = true;
        this.setRotation(this.neck, 0.0f, 0.0f, 0.0f);
        this.head = new ModelRenderer((ModelBase)this, 8, 39);
        this.head.addBox(0.0f, 0.0f, 0.0f, 12, 9, 13);
        this.head.setRotationPoint(-6.0f, 9.0f, 31.0f);
        this.head.setTextureSize(80, 80);
        this.head.mirror = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.jawTop = new ModelRenderer((ModelBase)this, 0, 0);
        this.jawTop.addBox(0.0f, 0.0f, 0.0f, 10, 2, 13);
        this.jawTop.setRotationPoint(-5.0f, 11.0f, 42.26667f);
        this.jawTop.setTextureSize(80, 80);
        this.jawTop.mirror = true;
        this.setRotation(this.jawTop, 0.0f, 0.0f, 0.0f);
        this.jawBottom = new ModelRenderer((ModelBase)this, 0, 0);
        this.jawBottom.addBox(0.0f, 0.0f, 0.0f, 10, 2, 13);
        this.jawBottom.setRotationPoint(-5.0f, 15.0f, 42.0f);
        this.jawBottom.setTextureSize(80, 80);
        this.jawBottom.mirror = true;
        this.setRotation(this.jawBottom, 0.0f, 0.0f, 0.0f);
        this.fin3A = new ModelRenderer((ModelBase)this, 5, 6);
        this.fin3A.addBox(-18.0f, 0.0f, 0.0f, 18, 10, 3);
        this.fin3A.setRotationPoint(-5.0f, 19.0f, 6.0f);
        this.fin3A.setTextureSize(80, 80);
        this.fin3A.mirror = true;
        this.setRotation(this.fin3A, 1.570796f, 0.0f, 0.0f);
        this.fin3B = new ModelRenderer((ModelBase)this, 5, 6);
        this.fin3B.addBox(-22.0f, 1.0f, 0.0f, 8, 8, 2);
        this.fin3B.setRotationPoint(-5.0f, 19.0f, 6.0f);
        this.fin3B.setTextureSize(80, 80);
        this.fin3B.mirror = true;
        this.setRotation(this.fin3B, 1.570796f, 0.0f, 0.0f);
        this.fin4B = new ModelRenderer((ModelBase)this, 5, 6);
        this.fin4B.addBox(-22.0f, 1.0f, 0.0f, 8, 8, 2);
        this.fin4B.setRotationPoint(-5.0f, 19.0f, -15.0f);
        this.fin4B.setTextureSize(80, 80);
        this.fin4B.mirror = true;
        this.setRotation(this.fin4B, 1.570796f, 0.0f, 0.0f);
        this.fin4A = new ModelRenderer((ModelBase)this, 5, 6);
        this.fin4A.addBox(-18.0f, 0.0f, 0.0f, 18, 10, 3);
        this.fin4A.setRotationPoint(-5.0f, 19.0f, -15.0f);
        this.fin4A.setTextureSize(80, 80);
        this.fin4A.mirror = true;
        this.setRotation(this.fin4A, 1.570796f, 0.0f, 0.0f);
        this.fin1A = new ModelRenderer((ModelBase)this, 5, 6);
        this.fin1A.addBox(0.0f, 0.0f, 0.0f, 18, 10, 3);
        this.fin1A.setRotationPoint(5.0f, 18.0f, 6.0f);
        this.fin1A.setTextureSize(80, 80);
        this.fin1A.mirror = true;
        this.setRotation(this.fin1A, 1.570796f, 0.0f, 0.0f);
        this.fin1B = new ModelRenderer((ModelBase)this, 5, 6);
        this.fin1B.addBox(14.0f, 1.0f, 0.0f, 8, 8, 2);
        this.fin1B.setRotationPoint(5.0f, 18.0f, 6.0f);
        this.fin1B.setTextureSize(80, 80);
        this.fin1B.mirror = true;
        this.setRotation(this.fin1B, 1.570796f, 0.0f, 0.0f);
        this.fin2B = new ModelRenderer((ModelBase)this, 5, 6);
        this.fin2B.addBox(14.0f, 1.0f, 0.0f, 8, 8, 2);
        this.fin2B.setRotationPoint(5.0f, 19.0f, -15.0f);
        this.fin2B.setTextureSize(80, 80);
        this.fin2B.mirror = true;
        this.setRotation(this.fin2B, 1.570796f, 0.0f, 0.0f);
        this.fin2A = new ModelRenderer((ModelBase)this, 5, 6);
        this.fin2A.addBox(0.0f, 0.0f, 0.0f, 18, 10, 3);
        this.fin2A.setRotationPoint(5.0f, 19.0f, -15.0f);
        this.fin2A.setTextureSize(80, 80);
        this.fin2A.mirror = true;
        this.setRotation(this.fin2A, 1.570796f, 0.0f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        GL11.glPushMatrix();
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        this.tail3.render(f5);
        this.tail4.render(f5);
        this.tooth1.render(f5);
        this.tooth2.render(f5);
        this.tooth3.render(f5);
        this.tooth4.render(f5);
        this.tooth5.render(f5);
        this.tooth6.render(f5);
        this.tail1.render(f5);
        this.body.render(f5);
        this.tail2.render(f5);
        this.front.render(f5);
        this.neck.render(f5);
        this.head.render(f5);
        this.jawTop.render(f5);
        this.jawBottom.render(f5);
        this.fin3A.render(f5);
        this.fin3B.render(f5);
        this.fin4B.render(f5);
        this.fin4A.render(f5);
        this.fin1A.render(f5);
        this.fin1B.render(f5);
        this.fin2B.render(f5);
        this.fin2A.render(f5);
        GL11.glPopMatrix();
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
        this.fin4A.rotateAngleZ = this.fin4B.rotateAngleZ = MathHelper.sin((float)(10.0f * f2 / 57.295776f)) * 0.3f;
        this.fin1B.rotateAngleZ = this.fin4B.rotateAngleZ;
        this.fin1A.rotateAngleZ = this.fin4B.rotateAngleZ;
        this.fin3A.rotateAngleZ = this.fin3B.rotateAngleZ = -MathHelper.sin((float)(10.0f * f2 / 57.295776f)) * 0.3f;
        this.fin2B.rotateAngleZ = this.fin3B.rotateAngleZ;
        this.fin2A.rotateAngleZ = this.fin3B.rotateAngleZ;
    }
}

