package mireille.client.model.skins;

import cpw.mods.fml.relauncher.*;
import net.minecraft.client.model.*;
import net.minecraft.entity.*;

@SideOnly(Side.CLIENT)
public class ModelSkinWorkbench extends ModelBase
{
    public ModelRenderer shape5;
    public ModelRenderer shape2;
    public ModelRenderer shape1;
    public ModelRenderer shape7;
    public ModelRenderer shape6;
    public ModelRenderer shape10;
    public ModelRenderer shape4;
    public ModelRenderer shape3;
    public ModelRenderer shape8;
    public ModelRenderer shape11;
    public ModelRenderer shape12;
    public ModelRenderer shape9;
    public ModelRenderer shape24;
    public ModelRenderer shape23;
    public ModelRenderer shape22;
    public ModelRenderer shape14;
    public ModelRenderer shape13;
    public ModelRenderer shape15;
    public ModelRenderer shape16;
    public ModelRenderer shape21;
    public ModelRenderer shape20;
    public ModelRenderer shape19;
    public ModelRenderer shape18;
    public ModelRenderer shape17;
    
    public ModelSkinWorkbench() {
        super.textureWidth = 64;
        super.textureHeight = 32;
        (this.shape4 = new ModelRenderer((ModelBase)this, 48, 0)).setRotationPoint(-2.0f, 22.0f, -8.0f);
        this.shape4.addBox(0.0f, 0.0f, 0.0f, 4, 2, 4, 0.0f);
        (this.shape14 = new ModelRenderer((ModelBase)this, 0, 9)).setRotationPoint(5.72f, 18.0f, 5.58f);
        this.shape14.addBox(-1.5f, -2.5f, -1.0f, 3, 5, 2, 0.0f);
        this.setRotateAngle(this.shape14, 0.3926991f, 3.9269907f, 0.0f);
        (this.shape1 = new ModelRenderer((ModelBase)this, 48, 0)).setRotationPoint(4.0f, 22.0f, -2.0f);
        this.shape1.addBox(0.0f, 0.0f, 0.0f, 4, 2, 4, 0.0f);
        (this.shape5 = new ModelRenderer((ModelBase)this, 30, 0)).setRotationPoint(7.4f, 22.0f, -1.5f);
        this.shape5.addBox(0.0f, 0.0f, 0.0f, 4, 2, 8, 0.0f);
        this.setRotateAngle(this.shape5, 0.0f, 3.9269907f, 0.0f);
        (this.shape9 = new ModelRenderer((ModelBase)this, 0, 0)).setRotationPoint(4.54f, 21.0f, -4.39f);
        this.shape9.addBox(-2.0f, -2.5f, -1.0f, 4, 5, 2, 0.0f);
        this.setRotateAngle(this.shape9, 0.7853982f, -0.7853982f, 0.0f);
        (this.shape12 = new ModelRenderer((ModelBase)this, 0, 0)).setRotationPoint(-4.39f, 21.0f, -4.49f);
        this.shape12.addBox(-2.0f, -2.5f, -1.0f, 4, 5, 2, 0.0f);
        this.setRotateAngle(this.shape12, 0.7853982f, 0.7853982f, 0.0f);
        (this.shape6 = new ModelRenderer((ModelBase)this, 30, 0)).setRotationPoint(1.67f, 22.0f, 7.37f);
        this.shape6.addBox(0.0f, 0.0f, 0.0f, 4, 2, 8, 0.0f);
        this.setRotateAngle(this.shape6, 0.0f, 2.3561945f, 0.0f);
        (this.shape13 = new ModelRenderer((ModelBase)this, 0, 9)).setRotationPoint(5.7f, 18.0f, -5.56f);
        this.shape13.addBox(-1.5f, -2.5f, -1.0f, 3, 5, 2, 0.0f);
        this.setRotateAngle(this.shape13, 0.3926991f, -0.7853982f, 0.0f);
        (this.shape17 = new ModelRenderer((ModelBase)this, 0, 18)).setRotationPoint(5.6f, 14.2f, -5.46f);
        this.shape17.addBox(-1.0f, -2.5f, -1.0f, 2, 5, 2, 0.0f);
        this.setRotateAngle(this.shape17, -0.3926991f, -0.7853982f, 0.0f);
        (this.shape7 = new ModelRenderer((ModelBase)this, 30, 0)).setRotationPoint(-7.34f, 22.0f, 1.62f);
        this.shape7.addBox(0.0f, 0.0f, 0.0f, 4, 2, 8, 0.0f);
        this.setRotateAngle(this.shape7, 0.0f, 0.7853982f, 0.0f);
        (this.shape10 = new ModelRenderer((ModelBase)this, 0, 0)).setRotationPoint(4.61f, 21.0f, 4.51f);
        this.shape10.addBox(-2.0f, -2.5f, -1.0f, 4, 5, 2, 0.0f);
        this.setRotateAngle(this.shape10, 0.7853982f, 3.9269907f, 0.0f);
        (this.shape2 = new ModelRenderer((ModelBase)this, 48, 0)).setRotationPoint(-2.0f, 22.0f, 4.0f);
        this.shape2.addBox(0.0f, 0.0f, 0.0f, 4, 2, 4, 0.0f);
        (this.shape11 = new ModelRenderer((ModelBase)this, 0, 0)).setRotationPoint(-4.39f, 21.0f, 4.51f);
        this.shape11.addBox(-2.0f, -2.5f, -1.0f, 4, 5, 2, 0.0f);
        this.setRotateAngle(this.shape11, 0.7853982f, 2.3561945f, 0.0f);
        (this.shape16 = new ModelRenderer((ModelBase)this, 0, 9)).setRotationPoint(-5.4f, 18.0f, -5.52f);
        this.shape16.addBox(-1.5f, -2.5f, -1.0f, 3, 5, 2, 0.0f);
        this.setRotateAngle(this.shape16, 0.3926991f, 0.7853982f, 0.0f);
        (this.shape21 = new ModelRenderer((ModelBase)this, 0, 27)).setRotationPoint(5.6f, 12.6f, -5.46f);
        this.shape21.addBox(-0.5f, -0.5f, -0.5f, 1, 1, 3, 0.0f);
        this.setRotateAngle(this.shape21, -0.3926991f, -0.7853982f, 0.0f);
        (this.shape8 = new ModelRenderer((ModelBase)this, 30, 0)).setRotationPoint(-4.4f, 22.0f, 1.14f);
        this.shape8.addBox(0.0f, 0.0f, 0.0f, 4, 2, 8, 0.0f);
        this.setRotateAngle(this.shape8, 0.0f, 2.3561945f, 0.0f);
        (this.shape22 = new ModelRenderer((ModelBase)this, 0, 27)).setRotationPoint(5.67f, 12.6f, 5.54f);
        this.shape22.addBox(-0.5f, -0.5f, -0.5f, 1, 1, 3, 0.0f);
        this.setRotateAngle(this.shape22, -0.3926991f, 3.9269907f, 0.0f);
        (this.shape20 = new ModelRenderer((ModelBase)this, 0, 18)).setRotationPoint(-5.3f, 14.2f, -5.42f);
        this.shape20.addBox(-1.0f, -2.5f, -1.0f, 2, 5, 2, 0.0f);
        this.setRotateAngle(this.shape20, -0.3926991f, 0.7853982f, 0.0f);
        (this.shape24 = new ModelRenderer((ModelBase)this, 0, 27)).setRotationPoint(-5.3f, 12.6f, -5.42f);
        this.shape24.addBox(-0.5f, -0.5f, -0.5f, 1, 1, 3, 0.0f);
        this.setRotateAngle(this.shape24, -0.3926991f, 0.7853982f, 0.0f);
        (this.shape23 = new ModelRenderer((ModelBase)this, 0, 27)).setRotationPoint(-5.44f, 12.6f, 5.57f);
        this.shape23.addBox(-0.5f, -0.5f, -0.5f, 1, 1, 3, 0.0f);
        this.setRotateAngle(this.shape23, -0.3926991f, 2.3561945f, 0.0f);
        (this.shape15 = new ModelRenderer((ModelBase)this, 0, 9)).setRotationPoint(-5.54f, 18.0f, 5.67f);
        this.shape15.addBox(-1.5f, -2.5f, -1.0f, 3, 5, 2, 0.0f);
        this.setRotateAngle(this.shape15, 0.3926991f, 2.3561945f, 0.0f);
        (this.shape3 = new ModelRenderer((ModelBase)this, 48, 0)).setRotationPoint(-8.0f, 22.0f, -2.0f);
        this.shape3.addBox(0.0f, 0.0f, 0.0f, 4, 2, 4, 0.0f);
        (this.shape18 = new ModelRenderer((ModelBase)this, 0, 18)).setRotationPoint(5.67f, 14.2f, 5.54f);
        this.shape18.addBox(-1.0f, -2.5f, -1.0f, 2, 5, 2, 0.0f);
        this.setRotateAngle(this.shape18, -0.3926991f, 3.9269907f, 0.0f);
        (this.shape19 = new ModelRenderer((ModelBase)this, 0, 18)).setRotationPoint(-5.44f, 14.2f, 5.57f);
        this.shape19.addBox(-1.0f, -2.5f, -1.0f, 2, 5, 2, 0.0f);
        this.setRotateAngle(this.shape19, -0.3926991f, 2.3561945f, 0.0f);
    }
    
    public void renderModel(final float f5) {
        this.shape4.render(f5);
        this.shape14.render(f5);
        this.shape1.render(f5);
        this.shape5.render(f5);
        this.shape9.render(f5);
        this.shape12.render(f5);
        this.shape6.render(f5);
        this.shape13.render(f5);
        this.shape17.render(f5);
        this.shape7.render(f5);
        this.shape10.render(f5);
        this.shape2.render(f5);
        this.shape11.render(f5);
        this.shape16.render(f5);
        this.shape21.render(f5);
        this.shape8.render(f5);
        this.shape22.render(f5);
        this.shape20.render(f5);
        this.shape24.render(f5);
        this.shape23.render(f5);
        this.shape15.render(f5);
        this.shape3.render(f5);
        this.shape18.render(f5);
        this.shape19.render(f5);
    }
    
    public void setRotateAngle(final ModelRenderer modelRenderer, final float x, final float y, final float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
    
    public void setRotationAngles(final float f, final float f1, final float f2, final float f3, final float f4, final float f5, final Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }
}
