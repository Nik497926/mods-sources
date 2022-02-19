/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 */
package net.divinerpg.client.render.entity.depths;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelDarkfish
extends ModelBase {
    private ModelRenderer lavasnakeBody;
    private ModelRenderer lavasnakeEye;
    private ModelRenderer[] lavasnakeTail;
    private static final String __OBFID = "CL_00002628";

    public ModelDarkfish() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.lavasnakeBody = new ModelRenderer((ModelBase)this);
        this.lavasnakeBody.setTextureOffset(0, 0).addBox(-6.0f, 10.0f, -8.0f, 12, 12, 16);
        this.lavasnakeEye = new ModelRenderer((ModelBase)this, 8, 0);
        this.lavasnakeEye.addBox(-1.0f, 15.0f, 0.0f, 2, 2, 1);
        this.lavasnakeBody.addChild(this.lavasnakeEye);
        this.lavasnakeTail = new ModelRenderer[3];
        this.lavasnakeTail[0] = new ModelRenderer((ModelBase)this, 40, 0);
        this.lavasnakeTail[0].addBox(-2.0f, 14.0f, 7.0f, 4, 4, 8);
        this.lavasnakeTail[1] = new ModelRenderer((ModelBase)this, 0, 54);
        this.lavasnakeTail[1].addBox(0.0f, 14.0f, 0.0f, 3, 3, 7);
        this.lavasnakeTail[2] = new ModelRenderer((ModelBase)this);
        this.lavasnakeTail[2].setTextureOffset(41, 32).addBox(0.0f, 14.0f, 0.0f, 2, 2, 6);
        this.lavasnakeTail[2].setTextureOffset(25, 19).addBox(1.0f, 10.5f, 3.0f, 1, 9, 9);
        this.lavasnakeBody.addChild(this.lavasnakeTail[0]);
        this.lavasnakeTail[0].addChild(this.lavasnakeTail[1]);
        this.lavasnakeTail[1].addChild(this.lavasnakeTail[2]);
    }

    public int func_178706_a() {
        return 54;
    }

    public void render(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
        this.setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);
        this.lavasnakeBody.render(p_78088_7_);
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        this.lavasnakeBody.rotateAngleY = par4 / 57.295776f;
        this.lavasnakeBody.rotateAngleX = par5 / 57.295776f;
        float[] afloat = new float[]{1.75f, 0.25f, 0.0f, 0.0f, 0.5f, 0.5f, 0.5f, 0.5f, 1.25f, 0.75f, 0.0f, 0.0f};
        float[] afloat1 = new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.25f, 1.75f, 1.25f, 0.75f, 0.0f, 0.0f, 0.0f, 0.0f};
        float[] afloat2 = new float[]{0.0f, 0.0f, 0.25f, 1.75f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.75f, 1.25f};
        float[] afloat3 = new float[]{0.0f, 0.0f, 8.0f, -8.0f, -8.0f, 8.0f, 8.0f, -8.0f, 0.0f, 0.0f, 8.0f, -8.0f};
        float[] afloat4 = new float[]{-8.0f, -8.0f, -8.0f, -8.0f, 0.0f, 0.0f, 0.0f, 0.0f, 8.0f, 8.0f, 8.0f, 8.0f};
        float[] afloat5 = new float[]{8.0f, -8.0f, 0.0f, 0.0f, -8.0f, -8.0f, 8.0f, 8.0f, 8.0f, -8.0f, 0.0f, 0.0f};
        this.lavasnakeEye.rotationPointZ = -8.25f;
        this.lavasnakeTail[0].rotateAngleY = MathHelper.sin((float)par3) * (float)Math.PI * 0.01f;
        this.lavasnakeTail[1].rotateAngleY = MathHelper.sin((float)par3) * (float)Math.PI * 0.1f;
        this.lavasnakeTail[1].rotationPointX = -1.5f;
        this.lavasnakeTail[1].rotationPointY = 0.5f;
        this.lavasnakeTail[1].rotationPointZ = 14.0f;
        this.lavasnakeTail[2].rotateAngleY = MathHelper.sin((float)par3) * (float)Math.PI * 0.1f;
        this.lavasnakeTail[2].rotationPointX = 0.5f;
        this.lavasnakeTail[2].rotationPointY = 0.5f;
        this.lavasnakeTail[2].rotationPointZ = 6.0f;
    }
}

