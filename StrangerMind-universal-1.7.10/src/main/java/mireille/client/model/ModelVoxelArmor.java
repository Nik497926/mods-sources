package mireille.client.model;

import cpw.mods.fml.relauncher.*;
import net.minecraft.client.model.*;
import mireille.client.handler.*;
import net.minecraft.entity.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.*;

@SideOnly(Side.CLIENT)
public class ModelVoxelArmor extends ModelBiped
{
    public ModelRenderOBJ head;
    public ModelRenderOBJ body;
    public ModelRenderOBJ rightArm;
    public ModelRenderOBJ leftArm;
    public ModelRenderOBJ belt;
    public ModelRenderOBJ rightLeg;
    public ModelRenderOBJ leftLeg;
    public ModelRenderOBJ rightBoot;
    public ModelRenderOBJ leftBoot;
    
    public ModelVoxelArmor(final float f, final int armorType, final String tag, final int scale) {
        super(f, 0.0f, 128, 128);
        (super.bipedHead = new ModelRenderer((ModelBase)this, 0, 0)).setRotationPoint(0.0f, 0.0f, 0.0f);
        super.bipedHead.addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8, 0.0f);
        (super.bipedBody = new ModelRenderer((ModelBase)this, 16, 16)).setRotationPoint(0.0f, 0.0f, 0.0f);
        super.bipedBody.addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4, 0.0f);
        (super.bipedLeftArm = new ModelRenderer((ModelBase)this, 40, 16)).setRotationPoint(5.0f, 2.0f, 0.0f);
        super.bipedLeftArm.addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4, 0.0f);
        (super.bipedRightArm = new ModelRenderer((ModelBase)this, 40, 16)).setRotationPoint(-5.0f, 2.0f, 0.0f);
        super.bipedRightArm.addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4, 0.0f);
        (super.bipedLeftLeg = new ModelRenderer((ModelBase)this, 0, 16)).setRotationPoint(2.0f, 12.0f, 0.0f);
        super.bipedLeftLeg.addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f);
        (super.bipedRightLeg = new ModelRenderer((ModelBase)this, 0, 16)).setRotationPoint(-2.0f, 12.0f, 0.0f);
        super.bipedRightLeg.addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4, 0.0f);
        final String path = "model/armor/" + tag + "_x" + scale + "/";
        this.head = new ModelRenderOBJ((ModelBase)this, ResourceHandler.getResource(path + "Head.obj"), ResourceHandler.getResource(path + "texturemap.png"));
        this.body = new ModelRenderOBJ((ModelBase)this, ResourceHandler.getResource(path + "Body.obj"), ResourceHandler.getResource(path + "texturemap.png"));
        this.rightArm = new ModelRenderOBJ((ModelBase)this, ResourceHandler.getResource(path + "RightArm.obj"), ResourceHandler.getResource(path + "texturemap.png"));
        this.leftArm = new ModelRenderOBJ((ModelBase)this, ResourceHandler.getResource(path + "LeftArm.obj"), ResourceHandler.getResource(path + "texturemap.png"));
        this.belt = new ModelRenderOBJ((ModelBase)this, ResourceHandler.getResource(path + "Belt.obj"), ResourceHandler.getResource(path + "texturemap.png"));
        this.rightLeg = new ModelRenderOBJ((ModelBase)this, ResourceHandler.getResource(path + "RightLeg.obj"), ResourceHandler.getResource(path + "texturemap.png"));
        this.leftLeg = new ModelRenderOBJ((ModelBase)this, ResourceHandler.getResource(path + "LeftLeg.obj"), ResourceHandler.getResource(path + "texturemap.png"));
        this.rightBoot = new ModelRenderOBJ((ModelBase)this, ResourceHandler.getResource(path + "RightBoot.obj"), ResourceHandler.getResource(path + "texturemap.png"));
        this.leftBoot = new ModelRenderOBJ((ModelBase)this, ResourceHandler.getResource(path + "LeftBoot.obj"), ResourceHandler.getResource(path + "texturemap.png"));
        super.bipedHead.cubeList.clear();
        super.bipedHeadwear.cubeList.clear();
        super.bipedBody.cubeList.clear();
        super.bipedRightArm.cubeList.clear();
        super.bipedLeftArm.cubeList.clear();
        super.bipedLeftLeg.cubeList.clear();
        super.bipedRightLeg.cubeList.clear();
        this.head.offsetY = 2.775f;
        this.body.offsetY = 2.58f;
        super.bipedRightArm.offsetY = -0.001f;
        super.bipedLeftArm.offsetY = -0.001f;
        this.rightArm.offsetX = 0.57f;
        this.rightArm.offsetY = 2.455f;
        this.leftArm.offsetX = -0.57f;
        this.leftArm.offsetY = 2.455f;
        this.belt.offsetY = 2.2f;
        super.bipedRightLeg.offsetY = -0.009f;
        super.bipedLeftLeg.offsetY = -0.009f;
        this.rightLeg.offsetX = 0.267f;
        this.rightLeg.offsetY = 1.28f;
        this.leftLeg.offsetX = -0.267f;
        this.leftLeg.offsetY = 1.28f;
        this.rightBoot.offsetX = 0.267f;
        this.rightBoot.offsetY = 0.82f;
        this.leftBoot.offsetX = -0.267f;
        this.leftBoot.offsetY = 0.82f;
        this.head.scale = 0.063f / scale;
        this.body.scale = 0.063f / scale;
        this.rightArm.scale = 0.063f / scale;
        this.leftArm.scale = 0.063f / scale;
        this.belt.scale = 0.063f / scale;
        this.leftLeg.scale = 0.065f / scale;
        this.rightLeg.scale = 0.065f / scale;
        this.leftBoot.scale = 0.065f / scale;
        this.rightBoot.scale = 0.065f / scale;
        if (armorType == 0) {
            super.bipedHead.addChild((ModelRenderer)this.head);
        }
        if (armorType == 1) {
            super.bipedBody.addChild((ModelRenderer)this.body);
            super.bipedLeftArm.addChild((ModelRenderer)this.leftArm);
            super.bipedRightArm.addChild((ModelRenderer)this.rightArm);
        }
        if (armorType == 2) {
            super.bipedLeftLeg.addChild((ModelRenderer)this.leftLeg);
            super.bipedRightLeg.addChild((ModelRenderer)this.rightLeg);
            super.bipedBody.addChild((ModelRenderer)this.belt);
        }
        if (armorType == 3) {
            super.bipedLeftLeg.addChild((ModelRenderer)this.leftBoot);
            super.bipedRightLeg.addChild((ModelRenderer)this.rightBoot);
        }
    }
    
    public void render(final Entity entity, final float f, final float f1, final float f2, final float f3, final float f4, final float f5) {
        if (entity == null) {
            super.isSneak = false;
            super.isRiding = false;
            super.isChild = false;
            super.aimedBow = false;
            super.bipedRightArm.rotateAngleX = 0.0f;
            super.bipedRightArm.rotateAngleY = 0.0f;
            super.bipedRightArm.rotateAngleZ = 0.0f;
            super.bipedLeftArm.rotateAngleX = 0.0f;
            super.bipedLeftArm.rotateAngleY = 0.0f;
            super.bipedLeftArm.rotateAngleZ = 0.0f;
            super.bipedBody.rotateAngleX = 0.0f;
            super.bipedBody.rotateAngleY = 0.0f;
            super.bipedBody.rotateAngleZ = 0.0f;
            super.bipedHead.rotateAngleX = 0.0f;
            super.bipedHead.rotateAngleY = 0.0f;
            super.bipedHead.rotateAngleZ = 0.0f;
            super.bipedLeftLeg.rotateAngleX = 0.0f;
            super.bipedLeftLeg.rotateAngleY = 0.0f;
            super.bipedLeftLeg.rotateAngleZ = 0.0f;
            super.bipedRightLeg.rotateAngleX = 0.0f;
            super.bipedRightLeg.rotateAngleY = 0.0f;
            this.setRotationAngles(super.bipedRightLeg.rotateAngleZ = 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, null);
        }
        else {
            super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        }
        GL11.glPushMatrix();
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0f, 240.0f);
        super.bipedHead.render(0.063f);
        super.bipedBody.render(0.063f);
        super.bipedRightArm.render(0.063f);
        super.bipedLeftArm.render(0.063f);
        super.bipedRightLeg.render(0.063f);
        super.bipedLeftLeg.render(0.063f);
        GL11.glPopMatrix();
    }
    
    public void setRotationAngles(final float f1, final float f2, final float f3, final float f4, final float f5, final float f6, final Entity entity) {
        super.bipedHead.rotationPointY = 0.0f;
        super.bipedHeadwear.rotationPointY = 0.0f;
        super.bipedBody.rotateAngleX = 0.0f;
        super.bipedRightArm.rotateAngleZ = 0.0f;
        super.bipedLeftArm.rotateAngleZ = 0.0f;
        super.bipedRightArm.rotationPointZ = 0.0f;
        super.bipedLeftArm.rotationPointZ = 0.0f;
        super.bipedRightArm.rotateAngleY = 0.0f;
        super.bipedLeftArm.rotateAngleY = 0.0f;
        super.bipedRightLeg.rotateAngleY = 0.0f;
        super.bipedLeftLeg.rotateAngleY = 0.0f;
        super.bipedRightLeg.rotationPointZ = 0.1f;
        super.bipedLeftLeg.rotationPointZ = 0.1f;
        super.bipedRightLeg.rotationPointY = 12.0f;
        super.bipedLeftLeg.rotationPointY = 12.0f;
        this.leftLeg.rotationPointZ = 0.0f;
        this.rightLeg.rotationPointZ = 0.0f;
    }
}
