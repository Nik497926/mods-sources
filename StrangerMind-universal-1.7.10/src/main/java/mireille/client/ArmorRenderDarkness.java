package mireille.client;

import net.minecraft.util.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.*;
import net.minecraftforge.client.model.*;

public class ArmorRenderDarkness extends ArmorRenderObjBase
{
    public static final IModelCustom bootsLeft;
    public static final IModelCustom bootsRight;
    public static final IModelCustom stanciLeft;
    public static final IModelCustom stanciRight;
    public static final IModelCustom armRight;
    public static final IModelCustom armLeft;
    public static final IModelCustom head;
    public static final IModelCustom chest;
    public static final ResourceLocation texture;
    private final int partType;
    
    public ArmorRenderDarkness(final int armorType) {
        this.partType = armorType;
    }
    
    @Override
    public void pre() {
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
    }
    
    @Override
    public void post() {
        GL11.glDisable(3042);
    }
    
    @Override
    public void partHead() {
        if (this.partType == 0) {
            GL11.glTranslatef(-0.37f, -0.7f, 0.4f);
            GL11.glScalef(0.625f, 0.625f, 0.625f);
            Minecraft.getMinecraft().renderEngine.bindTexture(ArmorRenderDarkness.texture);
            ArmorRenderDarkness.head.renderAll();
        }
    }
    
    @Override
    public void partBody() {
        if (this.partType == 1) {
            GL11.glTranslatef(-0.75f, -1.4f, 0.25f);
            GL11.glScalef(0.625f, 0.625f, 0.625f);
            Minecraft.getMinecraft().renderEngine.bindTexture(ArmorRenderDarkness.texture);
            ArmorRenderDarkness.chest.renderAll();
        }
    }
    
    @Override
    public void partLeftArm() {
        if (this.partType == 1) {
            GL11.glTranslatef(-0.135f, -1.965f, 0.25f);
            GL11.glScalef(0.655f, 0.625f, 0.625f);
            Minecraft.getMinecraft().renderEngine.bindTexture(ArmorRenderDarkness.texture);
            ArmorRenderDarkness.armLeft.renderAll();
        }
    }
    
    @Override
    public void partRightArm() {
        if (this.partType == 1) {
            GL11.glTranslatef(-0.45f, -1.965f, 0.25f);
            GL11.glScalef(0.645f, 0.625f, 0.625f);
            Minecraft.getMinecraft().renderEngine.bindTexture(ArmorRenderDarkness.texture);
            ArmorRenderDarkness.armRight.renderAll();
        }
    }
    
    @Override
    public void partRightLeg() {
        if (this.partType == 2) {
            GL11.glPushMatrix();
            Minecraft.getMinecraft().renderEngine.bindTexture(ArmorRenderDarkness.texture);
            GL11.glTranslatef(-0.325f, -1.925f, 0.2f);
            GL11.glScalef(0.635f, 0.625f, 0.625f);
            ArmorRenderDarkness.stanciRight.renderAll();
            GL11.glPopMatrix();
        }
        if (this.partType == 3) {
            Minecraft.getMinecraft().renderEngine.bindTexture(ArmorRenderDarkness.texture);
            GL11.glTranslatef(-0.15f, -2.05f, 0.3f);
            GL11.glScalef(0.625f, 0.625f, 0.625f);
            ArmorRenderDarkness.bootsLeft.renderAll();
        }
    }
    
    @Override
    public void partLeftLeg() {
        if (this.partType == 2) {
            GL11.glPushMatrix();
            Minecraft.getMinecraft().renderEngine.bindTexture(ArmorRenderDarkness.texture);
            GL11.glTranslatef(-0.125f, -1.925f, 0.2f);
            GL11.glScalef(0.625f, 0.625f, 0.625f);
            ArmorRenderDarkness.stanciLeft.renderAll();
            GL11.glPopMatrix();
        }
        if (this.partType == 3) {
            Minecraft.getMinecraft().renderEngine.bindTexture(ArmorRenderDarkness.texture);
            GL11.glTranslatef(-0.155f, -2.05f, 0.3f);
            GL11.glScalef(0.625f, 0.625f, 0.625f);
            ArmorRenderDarkness.bootsRight.renderAll();
        }
    }
    
    static {
        bootsLeft = AdvancedModelLoader.loadModel(new ResourceLocation("mireille:model/darkness/bootLeft.obj"));
        bootsRight = AdvancedModelLoader.loadModel(new ResourceLocation("mireille:model/darkness/bootRight.obj"));
        stanciLeft = AdvancedModelLoader.loadModel(new ResourceLocation("mireille:model/darkness/stanciLeft.obj"));
        stanciRight = AdvancedModelLoader.loadModel(new ResourceLocation("mireille:model/darkness/stanciRight.obj"));
        armRight = AdvancedModelLoader.loadModel(new ResourceLocation("mireille:model/darkness/chestRight.obj"));
        armLeft = AdvancedModelLoader.loadModel(new ResourceLocation("mireille:model/darkness/chestLeft.obj"));
        head = AdvancedModelLoader.loadModel(new ResourceLocation("mireille:model/darkness/helm.obj"));
        chest = AdvancedModelLoader.loadModel(new ResourceLocation("mireille:model/darkness/chest.obj"));
        texture = new ResourceLocation("mireille:textures/armor/DarknessArmorTex.png");
    }
}
