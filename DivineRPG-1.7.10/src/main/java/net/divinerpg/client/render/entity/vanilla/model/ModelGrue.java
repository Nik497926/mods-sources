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

public class ModelGrue
extends ModelBase {
    ModelRenderer toeRightOut;
    ModelRenderer toeLeftOut;
    ModelRenderer toeRightIn;
    ModelRenderer toeLeftIn;
    ModelRenderer legRight;
    ModelRenderer legLeft;
    ModelRenderer thighRight;
    ModelRenderer thighLeft;
    ModelRenderer torso;
    ModelRenderer body;
    ModelRenderer Shape6;
    ModelRenderer tail1;
    ModelRenderer tail3;
    ModelRenderer tail2;
    ModelRenderer mouth;
    ModelRenderer head;

    public ModelGrue() {
        this.textureWidth = 64;
        this.textureHeight = 27;
        this.toeRightOut = new ModelRenderer((ModelBase)this, 0, 0);
        this.toeRightOut.addBox(3.0f, -6.0f, 15.0f, 2, 11, 2);
        this.toeRightOut.setRotationPoint(-5.0f, 2.0f, 4.0f);
        this.toeRightOut.setTextureSize(32, 27);
        this.toeRightOut.mirror = true;
        this.setRotation(this.toeRightOut, -1.152537f, 0.8922867f, 0.0f);
        this.toeLeftOut = new ModelRenderer((ModelBase)this, 0, 0);
        this.toeLeftOut.addBox(-6.0f, -6.0f, 15.0f, 2, 11, 2);
        this.toeLeftOut.setRotationPoint(8.0f, 2.0f, 4.0f);
        this.toeLeftOut.setTextureSize(32, 27);
        this.toeLeftOut.mirror = true;
        this.setRotation(this.toeLeftOut, -1.152537f, -0.8179294f, 0.0f);
        this.toeRightIn = new ModelRenderer((ModelBase)this, 0, 0);
        this.toeRightIn.addBox(11.0f, 3.0f, 12.0f, 2, 11, 2);
        this.toeRightIn.setRotationPoint(-5.0f, 2.0f, 4.0f);
        this.toeRightIn.setTextureSize(32, 27);
        this.toeRightIn.mirror = true;
        this.setRotation(this.toeRightIn, -1.152537f, -0.0743572f, 0.0f);
        this.toeLeftIn = new ModelRenderer((ModelBase)this, 0, 0);
        this.toeLeftIn.addBox(-14.0f, 3.0f, 11.0f, 2, 11, 2);
        this.toeLeftIn.setRotationPoint(8.0f, 2.0f, 4.0f);
        this.toeLeftIn.setTextureSize(32, 27);
        this.toeLeftIn.mirror = true;
        this.setRotation(this.toeLeftIn, -1.152537f, 0.1487144f, 0.0f);
        this.legRight = new ModelRenderer((ModelBase)this, 0, 0);
        this.legRight.addBox(4.0f, 4.0f, 6.0f, 3, 10, 3);
        this.legRight.setRotationPoint(-5.0f, 2.0f, 4.0f);
        this.legRight.setTextureSize(32, 27);
        this.legRight.mirror = true;
        this.setRotation(this.legRight, -0.3717861f, 0.0f, 0.2230717f);
        this.legLeft = new ModelRenderer((ModelBase)this, 0, 0);
        this.legLeft.addBox(-6.0f, 4.0f, 7.0f, 3, 10, 3);
        this.legLeft.setRotationPoint(8.0f, 2.0f, 4.0f);
        this.legLeft.setTextureSize(32, 27);
        this.legLeft.mirror = true;
        this.setRotation(this.legLeft, -0.4089647f, 0.0f, -0.2230717f);
        this.thighRight = new ModelRenderer((ModelBase)this, 0, 0);
        this.thighRight.addBox(-2.0f, 0.0f, -2.0f, 4, 11, 4);
        this.thighRight.setRotationPoint(-5.0f, 2.0f, 4.0f);
        this.thighRight.setTextureSize(32, 27);
        this.thighRight.mirror = true;
        this.setRotation(this.thighRight, 0.6145831f, 0.0f, -0.4089647f);
        this.thighLeft = new ModelRenderer((ModelBase)this, 0, 0);
        this.thighLeft.addBox(-2.0f, 0.0f, -2.0f, 4, 11, 4);
        this.thighLeft.setRotationPoint(8.0f, 2.0f, 4.0f);
        this.thighLeft.setTextureSize(32, 27);
        this.thighLeft.mirror = true;
        this.setRotation(this.thighLeft, 0.6145831f, 0.0f, 0.4089647f);
        this.torso = new ModelRenderer((ModelBase)this, 0, 0);
        this.torso.addBox(0.0f, 0.0f, 0.0f, 12, 6, 4);
        this.torso.setRotationPoint(-5.0f, -3.0f, 3.0f);
        this.torso.setTextureSize(32, 27);
        this.torso.mirror = true;
        this.setRotation(this.torso, -0.2230717f, 0.0f, 0.0f);
        this.body = new ModelRenderer((ModelBase)this, 0, 0);
        this.body.addBox(0.0f, 0.0f, 0.0f, 8, 9, 10);
        this.body.setRotationPoint(-3.0f, -9.333333f, 0.0f);
        this.body.setTextureSize(32, 27);
        this.body.mirror = true;
        this.setRotation(this.body, -0.0743572f, 0.0f, 0.0f);
        this.Shape6 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape6.addBox(0.0f, 0.0f, 0.0f, 6, 8, 8);
        this.Shape6.setRotationPoint(-2.0f, -6.0f, -7.0f);
        this.Shape6.setTextureSize(32, 27);
        this.Shape6.mirror = true;
        this.setRotation(this.Shape6, 0.3717861f, 0.0f, 0.0f);
        this.tail1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.tail1.addBox(0.0f, 0.0f, 0.0f, 6, 6, 7);
        this.tail1.setRotationPoint(-2.0f, -2.0f, 7.0f);
        this.tail1.setTextureSize(32, 27);
        this.tail1.mirror = true;
        this.setRotation(this.tail1, 1.226894f, 0.0f, 0.0f);
        this.tail3 = new ModelRenderer((ModelBase)this, 0, 0);
        this.tail3.addBox(0.0f, 0.0f, 0.0f, 2, 2, 2);
        this.tail3.setRotationPoint(0.0f, -3.0f, 17.0f);
        this.tail3.setTextureSize(32, 27);
        this.tail3.mirror = true;
        this.setRotation(this.tail3, 0.7807508f, 0.0f, 0.0f);
        this.tail2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.tail2.addBox(0.0f, 0.0f, 0.0f, 4, 4, 4);
        this.tail2.setRotationPoint(-1.0f, -3.0f, 13.0f);
        this.tail2.setTextureSize(32, 27);
        this.tail2.mirror = true;
        this.setRotation(this.tail2, 1.041001f, 0.0f, 0.0f);
        this.mouth = new ModelRenderer((ModelBase)this, 0, 0);
        this.mouth.addBox(0.0f, 0.0f, 0.0f, 8, 2, 6);
        this.mouth.setRotationPoint(-3.0f, 4.0f, -9.466666f);
        this.mouth.setTextureSize(32, 27);
        this.mouth.mirror = true;
        this.setRotation(this.mouth, 0.669215f, 0.0f, 0.0f);
        this.head = new ModelRenderer((ModelBase)this, 0, 0);
        this.head.addBox(0.0f, 0.0f, 0.0f, 8, 7, 11);
        this.head.setRotationPoint(-3.0f, -3.0f, -17.0f);
        this.head.setTextureSize(32, 27);
        this.head.mirror = true;
        this.setRotation(this.head, 0.3346075f, 0.0f, 0.0f);
        this.legLeft.addChild(this.toeLeftIn);
        this.legLeft.addChild(this.toeLeftOut);
        this.legRight.addChild(this.toeRightIn);
        this.legRight.addChild(this.toeRightOut);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)0.0f, (float)0.1875f, (float)0.0f);
        this.legRight.render(f5);
        this.legLeft.render(f5);
        this.thighRight.render(f5);
        this.thighLeft.render(f5);
        this.torso.render(f5);
        this.body.render(f5);
        this.Shape6.render(f5);
        this.tail1.render(f5);
        this.tail3.render(f5);
        this.tail2.render(f5);
        this.mouth.render(f5);
        this.head.render(f5);
        GL11.glPopMatrix();
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
        this.legLeft.rotateAngleX = -0.4089647f + MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * 1.4f * f1;
        this.thighLeft.rotateAngleX = 0.6145831f + MathHelper.cos((float)(f * 0.6662f + (float)Math.PI)) * 1.4f * f1;
        this.legRight.rotateAngleX = -0.4089647f + MathHelper.cos((float)(f * 0.6662f)) * 1.4f * f1;
        this.thighRight.rotateAngleX = 0.6145831f + MathHelper.cos((float)(f * 0.6662f)) * 1.4f * f1;
    }
}

