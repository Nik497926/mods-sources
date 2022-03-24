package mireille.client;

import net.minecraft.util.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.*;
import net.minecraftforge.client.model.*;

public class ArmorRenderDaedra extends ArmorRenderObjBase
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
    
    public ArmorRenderDaedra(final int armorType) {
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
            GL11.glTranslatef(-0.52f, -1.7f, 0.4f);
            GL11.glScalef(0.65f, 0.775f, 0.665f);
            Minecraft.getMinecraft().renderEngine.bindTexture(ArmorRenderDaedra.texture);
            ArmorRenderDaedra.head.renderAll();
        }
    }
    
    @Override
    public void partBody() {
        if (this.partType == 1) {
            GL11.glTranslatef(0.0f, -0.85f, 0.0f);
            GL11.glScalef(0.0625f, 0.0625f, 0.0675f);
            Minecraft.getMinecraft().renderEngine.bindTexture(ArmorRenderDaedra.texture);
            ArmorRenderDaedra.chest.renderAll();
        }
    }
    
    @Override
    public void partRightArm() {
        if (this.partType == 1) {
            GL11.glTranslatef(-0.455f, -2.0f, 0.275f);
            GL11.glScalef(0.625f, 0.625f, 0.675f);
            Minecraft.getMinecraft().renderEngine.bindTexture(ArmorRenderDaedra.texture);
            ArmorRenderDaedra.armLeft.renderAll();
        }
    }
    
    @Override
    public void partLeftArm() {
        if (this.partType == 1) {
            GL11.glTranslatef(-0.155f, -2.0f, 0.275f);
            GL11.glScalef(0.625f, 0.625f, 0.675f);
            Minecraft.getMinecraft().renderEngine.bindTexture(ArmorRenderDaedra.texture);
            ArmorRenderDaedra.armRight.renderAll();
        }
    }
    
    @Override
    public void partRightLeg() {
        if (this.partType == 2) {
            GL11.glPushMatrix();
            Minecraft.getMinecraft().renderEngine.bindTexture(ArmorRenderDaedra.texture);
            GL11.glTranslatef(-0.2f, -0.85f, 0.25f);
            GL11.glScalef(0.625f, 0.625f, 0.63f);
            ArmorRenderDaedra.stanciRight.renderAll();
            GL11.glPopMatrix();
        }
        if (this.partType == 3) {
            GL11.glTranslatef(-0.15f, -0.95f, 0.24f);
            Minecraft.getMinecraft().renderEngine.bindTexture(ArmorRenderDaedra.texture);
            GL11.glScalef(0.625f, 0.625f, 0.625f);
            ArmorRenderDaedra.bootsLeft.renderAll();
        }
    }
    
    @Override
    public void partLeftLeg() {
        if (this.partType == 2) {
            GL11.glPushMatrix();
            Minecraft.getMinecraft().renderEngine.bindTexture(ArmorRenderDaedra.texture);
            GL11.glTranslatef(-0.55f, -0.85f, 0.25f);
            GL11.glScalef(0.625f, 0.625f, 0.63f);
            ArmorRenderDaedra.stanciLeft.renderAll();
            GL11.glPopMatrix();
        }
        if (this.partType == 3) {
            GL11.glTranslatef(-0.475f, -0.95f, 0.24f);
            Minecraft.getMinecraft().renderEngine.bindTexture(ArmorRenderDaedra.texture);
            GL11.glScalef(0.625f, 0.625f, 0.625f);
            ArmorRenderDaedra.bootsRight.renderAll();
        }
    }
    
    static {
        bootsLeft = AdvancedModelLoader.loadModel(new ResourceLocation("mireille:model/daedra/DaedraArmorBootsLeft.obj"));
        bootsRight = AdvancedModelLoader.loadModel(new ResourceLocation("mireille:model/daedra/DaedraArmorBootsRight.obj"));
        stanciLeft = AdvancedModelLoader.loadModel(new ResourceLocation("mireille:model/daedra/DaedraArmorStanciRight.obj"));
        stanciRight = AdvancedModelLoader.loadModel(new ResourceLocation("mireille:model/daedra/DaedraArmorStanciLeft.obj"));
        armRight = AdvancedModelLoader.loadModel(new ResourceLocation("mireille:model/daedra/DaedraArmorChestplateArmLeft.obj"));
        armLeft = AdvancedModelLoader.loadModel(new ResourceLocation("mireille:model/daedra/DaedraArmorChestplateArmRight.obj"));
        head = AdvancedModelLoader.loadModel(new ResourceLocation("mireille:model/daedra/DaedraArmoreHelmet.obj"));
        chest = AdvancedModelLoader.loadModel(new ResourceLocation("mireille:model/daedra/DaedraArmorChestplateGrudBlat.obj"));
        texture = new ResourceLocation("mireille:textures/armor/DaedraArmorTex.png");
    }
}
