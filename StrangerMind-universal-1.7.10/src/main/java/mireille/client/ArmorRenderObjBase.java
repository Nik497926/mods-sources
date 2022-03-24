package mireille.client;

import net.minecraft.entity.*;
import org.lwjgl.opengl.*;
import net.minecraft.entity.monster.*;
import net.minecraft.util.*;
import net.minecraft.client.model.*;

public abstract class ArmorRenderObjBase extends ModelBiped
{
    public int color;
    
    public ArmorRenderObjBase() {
        this.color = -1;
    }
    
    public abstract void pre();
    
    public abstract void post();
    
    public abstract void partHead();
    
    public abstract void partBody();
    
    public abstract void partRightArm();
    
    public abstract void partLeftArm();
    
    public abstract void partRightLeg();
    
    public abstract void partLeftLeg();
    
    public void render(final Entity entity, final float x, final float y, final float z, final float yaw, final float pitch, final float parTicks) {
        super.render(entity, x, y, z, yaw, pitch, parTicks);
        GL11.glPushMatrix();
        if (entity instanceof EntityZombie || entity instanceof EntitySkeleton || entity instanceof EntityGiantZombie) {
            final float f6 = MathHelper.sin(this.onGround * 3.1415927f);
            final float f7 = MathHelper.sin((1.0f - (1.0f - this.onGround) * (1.0f - this.onGround)) * 3.1415927f);
            this.bipedRightArm.rotateAngleZ = 0.0f;
            this.bipedRightArm.rotateAngleY = -(0.1f - f6 * 0.6f);
            this.bipedRightArm.rotateAngleX = -1.5707964f;
            final ModelRenderer bipedRightArm = this.bipedRightArm;
            bipedRightArm.rotateAngleX -= f6 * 1.2f - f7 * 0.4f;
            final ModelRenderer bipedRightArm2 = this.bipedRightArm;
            bipedRightArm2.rotateAngleZ += MathHelper.cos(z * 0.09f) * 0.05f + 0.05f;
            final ModelRenderer bipedRightArm3 = this.bipedRightArm;
            bipedRightArm3.rotateAngleX += MathHelper.sin(z * 0.067f) * 0.05f;
            this.bipedLeftArm.rotateAngleZ = 0.0f;
            this.bipedLeftArm.rotateAngleY = 0.1f - f6 * 0.6f;
            this.bipedLeftArm.rotateAngleX = -1.5707964f;
            final ModelRenderer bipedLeftArm = this.bipedLeftArm;
            bipedLeftArm.rotateAngleX -= f6 * 1.2f - f7 * 0.4f;
            final ModelRenderer bipedLeftArm2 = this.bipedLeftArm;
            bipedLeftArm2.rotateAngleZ -= MathHelper.cos(z * 0.09f) * 0.05f + 0.05f;
            final ModelRenderer bipedLeftArm3 = this.bipedLeftArm;
            bipedLeftArm3.rotateAngleX -= MathHelper.sin(z * 0.067f) * 0.05f;
            if (entity instanceof EntitySkeleton && ((EntitySkeleton)entity).getSkeletonType() == 1) {
                GL11.glScalef(1.2f, 1.2f, 1.2f);
            }
            else if (entity instanceof EntityGiantZombie) {
                GL11.glScalef(6.0f, 6.0f, 6.0f);
            }
        }
        if (this.color != -1) {
            final float red = (this.color >> 16 & 0xFF) / 255.0f;
            final float blue = (this.color >> 8 & 0xFF) / 255.0f;
            final float green = (this.color & 0xFF) / 255.0f;
            GL11.glColor3f(red, blue, green);
        }
        this.pre();
        final float f6 = 2.0f;
        GL11.glPushMatrix();
        if (this.isChild) {
            GL11.glScalef(1.5f / f6, 1.5f / f6, 1.5f / f6);
            GL11.glTranslatef(0.0f, 16.0f * parTicks, 0.0f);
        }
        GL11.glTranslatef(this.bipedHead.rotationPointX * parTicks, this.bipedHead.rotationPointY * parTicks, this.bipedHead.rotationPointZ * parTicks);
        GL11.glRotatef(this.bipedHead.rotateAngleZ * 57.295776f, 0.0f, 0.0f, 1.0f);
        GL11.glRotatef(this.bipedHead.rotateAngleY * 57.295776f, 0.0f, 1.0f, 0.0f);
        GL11.glRotatef(this.bipedHead.rotateAngleX * 57.295776f, 1.0f, 0.0f, 0.0f);
        GL11.glRotatef(180.0f, 1.0f, 0.0f, 0.0f);
        this.partHead();
        GL11.glPopMatrix();
        if (this.isChild) {
            GL11.glPushMatrix();
            GL11.glScalef(1.0f / f6, 1.0f / f6, 1.0f / f6);
            GL11.glTranslatef(0.0f, 24.0f * parTicks, 0.0f);
        }
        GL11.glPushMatrix();
        GL11.glTranslatef(this.bipedBody.rotationPointX * parTicks, this.bipedBody.rotationPointY * parTicks, this.bipedBody.rotationPointZ * parTicks);
        GL11.glRotatef(this.bipedBody.rotateAngleZ * 57.295776f, 0.0f, 0.0f, 1.0f);
        GL11.glRotatef(this.bipedBody.rotateAngleY * 57.295776f, 0.0f, 1.0f, 0.0f);
        GL11.glRotatef(this.bipedBody.rotateAngleX * 57.295776f, 1.0f, 0.0f, 0.0f);
        GL11.glRotatef(180.0f, 1.0f, 0.0f, 0.0f);
        this.partBody();
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslatef(this.bipedRightArm.rotationPointX * parTicks, this.bipedRightArm.rotationPointY * parTicks, this.bipedRightArm.rotationPointZ * parTicks);
        GL11.glRotatef(this.bipedRightArm.rotateAngleZ * 57.295776f, 0.0f, 0.0f, 1.0f);
        GL11.glRotatef(this.bipedRightArm.rotateAngleY * 57.295776f, 0.0f, 1.0f, 0.0f);
        GL11.glRotatef(this.bipedRightArm.rotateAngleX * 57.295776f, 1.0f, 0.0f, 0.0f);
        GL11.glRotatef(180.0f, 1.0f, 0.0f, 0.0f);
        this.partRightArm();
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslatef(this.bipedLeftArm.rotationPointX * parTicks, this.bipedLeftArm.rotationPointY * parTicks, this.bipedLeftArm.rotationPointZ * parTicks);
        GL11.glRotatef(this.bipedLeftArm.rotateAngleZ * 57.295776f, 0.0f, 0.0f, 1.0f);
        GL11.glRotatef(this.bipedLeftArm.rotateAngleY * 57.295776f, 0.0f, 1.0f, 0.0f);
        GL11.glRotatef(this.bipedLeftArm.rotateAngleX * 57.295776f, 1.0f, 0.0f, 0.0f);
        GL11.glRotatef(180.0f, 1.0f, 0.0f, 0.0f);
        this.partLeftArm();
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslatef(this.bipedRightLeg.rotationPointX * parTicks, this.bipedRightLeg.rotationPointY * parTicks, this.bipedRightLeg.rotationPointZ * parTicks);
        GL11.glRotatef(this.bipedRightLeg.rotateAngleZ * 57.295776f, 0.0f, 0.0f, 1.0f);
        GL11.glRotatef(this.bipedRightLeg.rotateAngleY * 57.295776f, 0.0f, 1.0f, 0.0f);
        GL11.glRotatef(this.bipedRightLeg.rotateAngleX * 57.295776f, 1.0f, 0.0f, 0.0f);
        GL11.glRotatef(180.0f, 1.0f, 0.0f, 0.0f);
        this.partRightLeg();
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslatef(this.bipedLeftLeg.rotationPointX * parTicks, this.bipedLeftLeg.rotationPointY * parTicks, this.bipedLeftLeg.rotationPointZ * parTicks);
        GL11.glRotatef(this.bipedLeftLeg.rotateAngleZ * 57.295776f, 0.0f, 0.0f, 1.0f);
        GL11.glRotatef(this.bipedLeftLeg.rotateAngleY * 57.295776f, 0.0f, 1.0f, 0.0f);
        GL11.glRotatef(this.bipedLeftLeg.rotateAngleX * 57.295776f, 1.0f, 0.0f, 0.0f);
        GL11.glRotatef(180.0f, 1.0f, 0.0f, 0.0f);
        this.partLeftLeg();
        GL11.glPopMatrix();
        if (this.isChild) {
            GL11.glPopMatrix();
        }
        this.post();
        GL11.glColor3f(1.0f, 1.0f, 1.0f);
        GL11.glPopMatrix();
    }
}
