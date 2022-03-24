package mireille.client.model.skins;

import cpw.mods.fml.relauncher.*;
import net.minecraft.client.model.*;

@SideOnly(Side.CLIENT)
public class ModelMolot extends ModelBase
{
    public ModelRenderer shape1;
    public ModelRenderer shape2;
    public ModelRenderer shape3;
    public ModelRenderer shape5;
    public ModelRenderer shape7;
    public ModelRenderer shape7_1;
    public ModelRenderer shape12;
    public ModelRenderer shape12_1;
    public ModelRenderer shape17;
    public ModelRenderer shape18;
    public ModelRenderer shape5_1;
    public ModelRenderer shape3_1;
    public ModelRenderer shape12_2;
    public ModelRenderer shape12_3;
    public ModelRenderer shape7_2;
    public ModelRenderer shape7_3;
    public ModelRenderer shape5_2;
    public ModelRenderer shape5_3;
    
    public ModelMolot() {
        super.textureWidth = 64;
        super.textureHeight = 32;
        (this.shape5_2 = new ModelRenderer((ModelBase)this, 20, 6)).setRotationPoint(-7.7f, 1.1f, -3.5f);
        this.shape5_2.addBox(0.0f, 0.0f, 0.0f, 3, 3, 7, 0.0f);
        this.setRotateAngle(this.shape5_2, 0.0f, 0.0f, 0.7853982f);
        (this.shape1 = new ModelRenderer((ModelBase)this, 0, 9)).setRotationPoint(-1.0f, 0.0f, -1.0f);
        this.shape1.addBox(0.0f, 0.0f, 0.0f, 2, 21, 2, 0.0f);
        (this.shape3 = new ModelRenderer((ModelBase)this, 40, 0)).setRotationPoint(4.0f, 1.0f, -4.0f);
        this.shape3.addBox(0.0f, 0.0f, 0.0f, 4, 8, 8, 0.0f);
        (this.shape7 = new ModelRenderer((ModelBase)this, 9, 22)).setRotationPoint(5.5f, 0.5f, -2.5f);
        this.shape7.addBox(0.0f, 0.0f, 0.0f, 1, 9, 1, 0.0f);
        (this.shape3_1 = new ModelRenderer((ModelBase)this, 40, 0)).setRotationPoint(-8.0f, 1.0f, -4.0f);
        this.shape3_1.addBox(0.0f, 0.0f, 0.0f, 4, 8, 8, 0.0f);
        (this.shape12_1 = new ModelRenderer((ModelBase)this, 0, 0)).setRotationPoint(5.5f, 6.5f, -4.5f);
        this.shape12_1.addBox(0.0f, 0.0f, 0.0f, 1, 1, 9, 0.0f);
        (this.shape7_3 = new ModelRenderer((ModelBase)this, 9, 22)).setRotationPoint(-6.5f, 0.5f, -2.5f);
        this.shape7_3.addBox(0.0f, 0.0f, 0.0f, 1, 9, 1, 0.0f);
        (this.shape5_3 = new ModelRenderer((ModelBase)this, 20, 6)).setRotationPoint(-7.7f, 4.6f, -3.5f);
        this.shape5_3.addBox(0.0f, 0.0f, 0.0f, 3, 3, 7, 0.0f);
        this.setRotateAngle(this.shape5_3, 0.0f, 0.0f, 0.7853982f);
        (this.shape18 = new ModelRenderer((ModelBase)this, 13, 0)).setRotationPoint(-1.5f, 21.0f, -1.5f);
        this.shape18.addBox(0.0f, 0.0f, 0.0f, 3, 3, 3, 0.0f);
        (this.shape12_2 = new ModelRenderer((ModelBase)this, 0, 0)).setRotationPoint(-6.5f, 6.5f, -4.5f);
        this.shape12_2.addBox(0.0f, 0.0f, 0.0f, 1, 1, 9, 0.0f);
        (this.shape7_2 = new ModelRenderer((ModelBase)this, 9, 22)).setRotationPoint(5.5f, 0.5f, 1.5f);
        this.shape7_2.addBox(0.0f, 0.0f, 0.0f, 1, 9, 1, 0.0f);
        (this.shape17 = new ModelRenderer((ModelBase)this, 15, 17)).setRotationPoint(-0.5f, 4.5f, -3.5f);
        this.shape17.addBox(0.0f, 0.0f, 0.0f, 1, 1, 7, 0.0f);
        (this.shape12_3 = new ModelRenderer((ModelBase)this, 0, 0)).setRotationPoint(-6.5f, 2.5f, -4.5f);
        this.shape12_3.addBox(0.0f, 0.0f, 0.0f, 1, 1, 9, 0.0f);
        (this.shape2 = new ModelRenderer((ModelBase)this, 28, 20)).setRotationPoint(-6.0f, 2.0f, -3.0f);
        this.shape2.addBox(0.0f, 0.0f, 0.0f, 12, 6, 6, 0.0f);
        (this.shape7_1 = new ModelRenderer((ModelBase)this, 9, 22)).setRotationPoint(-6.5f, 0.5f, 1.5f);
        this.shape7_1.addBox(0.0f, 0.0f, 0.0f, 1, 9, 1, 0.0f);
        (this.shape5 = new ModelRenderer((ModelBase)this, 20, 6)).setRotationPoint(7.7f, 4.6f, -3.5f);
        this.shape5.addBox(0.0f, 0.0f, 0.0f, 3, 3, 7, 0.0f);
        this.setRotateAngle(this.shape5, 0.0f, 0.0f, 0.7853982f);
        (this.shape12 = new ModelRenderer((ModelBase)this, 0, 0)).setRotationPoint(5.5f, 2.5f, -4.5f);
        this.shape12.addBox(0.0f, 0.0f, 0.0f, 1, 1, 9, 0.0f);
        (this.shape5_1 = new ModelRenderer((ModelBase)this, 20, 6)).setRotationPoint(7.7f, 1.1f, -3.5f);
        this.shape5_1.addBox(0.0f, 0.0f, 0.0f, 3, 3, 7, 0.0f);
        this.setRotateAngle(this.shape5_1, 0.0f, 0.0f, 0.7853982f);
    }
    
    public void render(final float f5) {
        this.shape5_2.render(f5);
        this.shape1.render(f5);
        this.shape3.render(f5);
        this.shape3_1.render(f5);
        this.shape5_3.render(f5);
        this.shape18.render(f5);
        this.shape2.render(f5);
        this.shape5.render(f5);
        this.shape5_1.render(f5);
    }
    
    public void setRotateAngle(final ModelRenderer modelRenderer, final float x, final float y, final float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
