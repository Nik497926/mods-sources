package mireille.client.model;

import cpw.mods.fml.relauncher.*;
import net.minecraft.util.*;
import net.minecraft.client.model.*;
import net.minecraftforge.client.model.*;
import mireille.client.handler.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.*;

@SideOnly(Side.CLIENT)
public class ModelRenderOBJ extends ModelRenderer
{
    private IModelCustom model;
    private ResourceLocation texture;
    private int displayList;
    private boolean compiled;
    public float scale;
    
    public ModelRenderOBJ(final ModelBase baseModel, final ResourceLocation customModel, final ResourceLocation texture) {
        super(baseModel);
        this.compiled = false;
        this.scale = 0.0f;
        try {
            this.model = AdvancedModelLoader.loadModel(customModel);
        }
        catch (Exception var5) {
            this.model = AdvancedModelLoader.loadModel(ResourceHandler.getResource("model/armor/null.obj"));
        }
        this.texture = texture;
    }
    
    public void render(final float scale) {
        if (!super.isHidden && super.showModel) {
            if (!this.compiled) {
                this.compileDisplayList(scale);
            }
            GL11.glTranslatef(super.offsetX, super.offsetY, super.offsetZ);
            if (super.rotateAngleX == 0.0f && super.rotateAngleY == 0.0f && super.rotateAngleZ == 0.0f) {
                if (super.rotationPointX == 0.0f && super.rotationPointY == 0.0f && super.rotationPointZ == 0.0f) {
                    GL11.glCallList(this.displayList);
                }
                else {
                    GL11.glTranslatef(super.rotationPointX * scale, super.rotationPointY * scale, super.rotationPointZ * scale);
                    GL11.glCallList(this.displayList);
                    GL11.glTranslatef(-super.rotationPointX * scale, -super.rotationPointY * scale, -super.rotationPointZ * scale);
                }
            }
            else {
                GL11.glPushMatrix();
                GL11.glTranslatef(super.rotationPointX * scale, super.rotationPointY * scale, super.rotationPointZ * scale);
                if (super.rotateAngleZ != 0.0f) {
                    GL11.glRotatef(super.rotateAngleZ * 57.295776f, 0.0f, 0.0f, 1.0f);
                }
                if (super.rotateAngleY != 0.0f) {
                    GL11.glRotatef(super.rotateAngleY * 57.295776f, 0.0f, 1.0f, 0.0f);
                }
                if (super.rotateAngleX != 0.0f) {
                    GL11.glRotatef(super.rotateAngleX * 57.295776f, 1.0f, 0.0f, 0.0f);
                }
                GL11.glCallList(this.displayList);
                GL11.glPopMatrix();
            }
            GL11.glTranslatef(-super.offsetX, -super.offsetY, -super.offsetZ);
        }
    }
    
    private void compileDisplayList(float scale) {
        if (this.scale == 0.0f) {
            this.scale = scale;
        }
        scale = this.scale;
        GL11.glNewList(this.displayList = GLAllocation.generateDisplayLists(1), 4864);
        GL11.glPushMatrix();
        ResourceHandler.bindTexture(this.texture);
        GL11.glScalef(scale, scale, scale);
        GL11.glRotatef(180.0f, -1.0f, 0.0f, 1.0f);
        this.model.renderAll();
        GL11.glPopMatrix();
        GL11.glEndList();
        this.compiled = true;
    }
    
    public void renderWithRotation(final float scale) {
        if (!super.isHidden && super.showModel) {
            if (!this.compiled) {
                this.compileDisplayList(scale);
            }
            GL11.glPushMatrix();
            GL11.glTranslatef(super.rotationPointX * scale, super.rotationPointY * scale, super.rotationPointZ * scale);
            if (super.rotateAngleY != 0.0f) {
                GL11.glRotatef(super.rotateAngleY * 57.295776f, 0.0f, 1.0f, 0.0f);
            }
            if (super.rotateAngleX != 0.0f) {
                GL11.glRotatef(super.rotateAngleX * 57.295776f, 1.0f, 0.0f, 0.0f);
            }
            if (super.rotateAngleZ != 0.0f) {
                GL11.glRotatef(super.rotateAngleZ * 57.295776f, 0.0f, 0.0f, 1.0f);
            }
            GL11.glCallList(this.displayList);
            GL11.glPopMatrix();
        }
    }
    
    public void postRender(final float scale) {
        if (!super.isHidden && super.showModel) {
            if (super.rotateAngleX == 0.0f && super.rotateAngleY == 0.0f && super.rotateAngleZ == 0.0f) {
                if (super.rotationPointX != 0.0f || super.rotationPointY != 0.0f || super.rotationPointZ != 0.0f) {
                    GL11.glTranslatef(super.rotationPointX * scale, super.rotationPointY * scale, super.rotationPointZ * scale);
                }
            }
            else {
                GL11.glTranslatef(super.rotationPointX * scale, super.rotationPointY * scale, super.rotationPointZ * scale);
                if (super.rotateAngleZ != 0.0f) {
                    GL11.glRotatef(super.rotateAngleZ * 57.295776f, 0.0f, 0.0f, 1.0f);
                }
                if (super.rotateAngleY != 0.0f) {
                    GL11.glRotatef(super.rotateAngleY * 57.295776f, 0.0f, 1.0f, 0.0f);
                }
                if (super.rotateAngleX != 0.0f) {
                    GL11.glRotatef(super.rotateAngleX * 57.295776f, 1.0f, 0.0f, 0.0f);
                }
            }
        }
    }
}
