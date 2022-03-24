package mireille.client.renderer.player;

import net.minecraft.client.*;
import net.minecraftforge.client.event.*;
import mireille.*;
import net.minecraft.client.renderer.culling.*;
import net.minecraft.client.multiplayer.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.entity.player.*;
import mireille.proxy.*;
import net.minecraft.entity.*;
import net.minecraft.util.*;
import java.util.*;
import cpw.mods.fml.common.eventhandler.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraftforge.client.*;
import mireille.core.*;
import net.minecraft.client.renderer.*;
import java.awt.*;
import mireille.client.gui.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.texture.*;
import net.minecraft.item.*;

@SideOnly(Side.CLIENT)
public class ArmorRenderer
{
    private int backVisible;
    private RenderBlocks renderBlocks;
    private RenderItem renderItem;
    private Minecraft mc;
    
    public ArmorRenderer() {
        this.backVisible = 64;
        this.renderBlocks = new RenderBlocks();
        this.renderItem = new RenderItem();
        this.mc = Minecraft.getMinecraft();
    }
    
    @SubscribeEvent
    public void onRenderWorldLast(RenderWorldLastEvent event) {
       if (ModConfig.ArmorStatus) {
          Minecraft mc = Minecraft.getMinecraft();
          if (Minecraft.isGuiEnabled()) {
             EntityLivingBase cameraEntity = mc.renderViewEntity;
             Vec3 renderingVector = cameraEntity.getPosition(event.partialTicks);
             Frustrum frustrum = new Frustrum();
             double viewX = cameraEntity.lastTickPosX + (cameraEntity.posX - cameraEntity.lastTickPosX) * (double)event.partialTicks;
             double viewY = cameraEntity.lastTickPosY + (cameraEntity.posY - cameraEntity.lastTickPosY) * (double)event.partialTicks;
             double viewZ = cameraEntity.lastTickPosZ + (cameraEntity.posZ - cameraEntity.lastTickPosZ) * (double)event.partialTicks;
             frustrum.setPosition(viewX, viewY, viewZ);
             WorldClient client = mc.theWorld;
             Set entities = (Set)ReflectionHelper.getPrivateValue(WorldClient.class, client, new String[]{"entityList", "field_73032_d", "J"});
             Iterator var14 = entities.iterator();

             while(true) {
                Entity entity;
                do {
                   do {
                      do {
                         do {
                            if (!var14.hasNext()) {
                               return;
                            }

                            entity = (Entity)var14.next();
                         } while(entity == null);
                      } while(!(entity instanceof EntityPlayer));
                   } while(!entity.isInRangeToRender3d(renderingVector.xCoord, renderingVector.yCoord, renderingVector.zCoord));
                } while(!entity.ignoreFrustumCheck && !frustrum.isBoundingBoxInFrustum(entity.boundingBox));

                if (entity.isEntityAlive() && !entity.getCommandSenderName().equals(ClientProxy.myName)) {
                   this.RenderArmorBar((EntityPlayer)entity, event.partialTicks, cameraEntity);
                }
             }
          }
       }

    }
    
    public void RenderArmorBar(final EntityLivingBase passedEntity, final float partialTicks, final Entity viewPoint) {
        if (passedEntity.riddenByEntity == null) {
            EntityLivingBase entity;
            for (entity = passedEntity; entity.ridingEntity instanceof EntityLivingBase; entity = (EntityLivingBase)entity.ridingEntity) {}
            while (true) {
                final float distance = passedEntity.getDistanceToEntity(viewPoint);
                if (distance <= ModConfig.ArmorStatusMaxDistance && passedEntity.canEntityBeSeen(viewPoint) && !entity.isInvisible()) {
                    final double x = passedEntity.lastTickPosX + (passedEntity.posX - passedEntity.lastTickPosX) * partialTicks;
                    final double y = passedEntity.lastTickPosY + (passedEntity.posY - passedEntity.lastTickPosY) * partialTicks;
                    final double z = passedEntity.lastTickPosZ + (passedEntity.posZ - passedEntity.lastTickPosZ) * partialTicks;
                    final float scale = 0.026666673f;
                    final float maxHealth = entity.getMaxHealth();
                    if (maxHealth > 0.0f) {
                        GL11.glPushMatrix();
                        GL11.glTranslatef((float)(x - RenderManager.renderPosX), (float)(y - RenderManager.renderPosY + passedEntity.height / 2.0f), (float)(z - RenderManager.renderPosZ));
                        GL11.glRotatef(-RenderManager.instance.playerViewY, 0.0f, 1.0f, 0.0f);
                        GL11.glScalef(-scale, -scale, -1.0E-4f);
                        GL11.glDisable(3008);
                        if (ModConfig.HpAndHungerBar) {
                            final float health = Math.min(maxHealth, entity.getHealth());
                            this.HealthBar(health, maxHealth);
                            if (passedEntity instanceof EntityPlayer) {
                                final float offset = (float)Math.min(20, ((EntityPlayer)passedEntity).getFoodStats().getFoodLevel());
                                this.HungerBar(offset);
                            }
                        }
                        if (passedEntity.getHeldItem() != null) {
                            this.BackgroundItem(40.0f, -8.0f);
                            GL11.glPushMatrix();
                            GL11.glDisable(2896);
                            GL11.glTranslatef(40.0f, -8.0f, 0.0f);
                            if (!ForgeHooksClient.renderInventoryItem(this.renderBlocks, this.mc.renderEngine, passedEntity.getHeldItem(), true, 0.0f, 0.0f, 0.0f)) {
                                if (passedEntity.getHeldItem().getItem() != ModItemsImport.QuantumHelm && passedEntity.getHeldItem().getItem() != ModItemsImport.QuantumChest && passedEntity.getHeldItem().getItem() != ModItemsImport.QuantumLegs && passedEntity.getHeldItem().getItem() != ModItemsImport.QuantumBoots) {
                                    this.renderItem.renderItemIntoGUI(this.mc.fontRenderer, this.mc.renderEngine, passedEntity.getHeldItem(), 0, 0);
                                }
                                else {
                                    GL11.glDepthMask(false);
                                    this.renderItem.renderItemIntoGUI(this.mc.fontRenderer, this.mc.renderEngine, passedEntity.getHeldItem(), 0, 0);
                                    GL11.glDepthMask(true);
                                }
                            }
                            this.renderItemDamage(this.mc.fontRenderer, this.mc.renderEngine, passedEntity.getHeldItem());
                            GL11.glEnable(2896);
                            GL11.glPopMatrix();
                        }
                        for (int i = 4; i > 0; --i) {
                            final float offset = i * 17.0f - 34.0f;
                            if (passedEntity.getEquipmentInSlot(i) != null) {
                                this.BackgroundItem(-56.0f, -offset);
                                GL11.glPushMatrix();
                                GL11.glDisable(2896);
                                GL11.glTranslatef(-56.0f, -offset, 0.0f);
                                if (!ForgeHooksClient.renderInventoryItem(this.renderBlocks, this.mc.renderEngine, passedEntity.getEquipmentInSlot(i), true, 0.0f, 0.0f, 0.0f)) {
                                    if (passedEntity.getEquipmentInSlot(i).getItem() != ModItemsImport.QuantumHelm && passedEntity.getEquipmentInSlot(i).getItem() != ModItemsImport.QuantumChest && passedEntity.getEquipmentInSlot(i).getItem() != ModItemsImport.QuantumLegs && passedEntity.getEquipmentInSlot(i).getItem() != ModItemsImport.QuantumBoots) {
                                        this.renderItem.renderItemIntoGUI(this.mc.fontRenderer, this.mc.renderEngine, passedEntity.getEquipmentInSlot(i), 0, 0);
                                    }
                                    else {
                                        GL11.glDepthMask(false);
                                        this.renderItem.renderItemIntoGUI(this.mc.fontRenderer, this.mc.renderEngine, passedEntity.getEquipmentInSlot(i), 0, 0);
                                        GL11.glDepthMask(true);
                                    }
                                }
                                this.renderItemDamage(this.mc.fontRenderer, this.mc.renderEngine, passedEntity.getEquipmentInSlot(i));
                                GL11.glEnable(2896);
                                GL11.glPopMatrix();
                            }
                        }
                        GL11.glEnable(3008);
                        GL11.glEnable(2896);
                        GL11.glPopMatrix();
                    }
                }
                final Entity riddenBy = entity.riddenByEntity;
                if (!(riddenBy instanceof EntityLivingBase)) {
                    break;
                }
                entity = (EntityLivingBase)riddenBy;
            }
        }
    }
    
    private void BackgroundItem(final float x, final float y) {
        GL11.glPushMatrix();
        GL11.glTranslatef(x, y, -1.0f);
        GL11.glDisable(2896);
        GL11.glDisable(3553);
        GL11.glEnable(3042);
        GL11.glDisable(2884);
        GL11.glBlendFunc(770, 771);
        GL11.glDepthMask(false);
        final Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.setColorRGBA(0, 0, 0, this.backVisible);
        tessellator.addVertex(0.0, 0.0, 0.0);
        tessellator.addVertex(0.0, 16.0, 0.0);
        tessellator.addVertex(16.0, 16.0, 0.0);
        tessellator.addVertex(16.0, 0.0, 0.0);
        tessellator.draw();
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glEnable(3553);
        GL11.glEnable(2896);
        GL11.glEnable(2884);
        GL11.glPopMatrix();
    }
    
    private void HealthBar(final float health, final float maxHealth) {
        GL11.glPushMatrix();
        GL11.glTranslatef(-39.0f, -30.0f, 0.0f);
        GL11.glDisable(2896);
        GL11.glDisable(3553);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        final Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.setColorRGBA(0, 0, 0, this.backVisible);
        tessellator.addVertex(0.0, 0.0, 0.0);
        tessellator.addVertex(0.0, 29.0, 0.0);
        tessellator.addVertex(3.0, 29.0, 0.0);
        tessellator.addVertex(3.0, 0.0, 0.0);
        tessellator.draw();
        final float s = Math.max(0.0f, health / maxHealth / 3.0f - 0.07f);
        final Color name = Color.getHSBColor(s, 1.0f, 1.0f);
        final int r = name.getRed();
        final int g = name.getGreen();
        final int b = name.getBlue();
        GL11.glTranslatef(0.0f, 0.0f, 1.0f);
        tessellator.startDrawingQuads();
        tessellator.setColorRGBA(r, g, b, 255);
        tessellator.addVertex(1.0, (double)(28.0f - 27.0f * (health / maxHealth)), 0.0);
        tessellator.addVertex(1.0, 28.0, 0.0);
        tessellator.addVertex(2.0, 28.0, 0.0);
        tessellator.addVertex(2.0, (double)(28.0f - 27.0f * (health / maxHealth)), 0.0);
        tessellator.draw();
        GL11.glDisable(3042);
        GL11.glEnable(3553);
        GL11.glTranslatef(-0.1f, -3.75f, 1.0f);
        GL11.glScalef(0.35f, 0.35f, 1.0f);
        GuiImage.bindTexture("minecraft:textures/gui/icons.png");
        GuiImage.drawImage(0, 0, 16, 0, 9, 9, 1.0f);
        GL11.glTranslatef(0.0f, 0.0f, 1.0f);
        GuiImage.drawImage(0, 0, 52, 0, 9, 8, 1.0f);
        GL11.glEnable(2896);
        GL11.glPopMatrix();
    }
    
    private void HungerBar(final float hunger) {
        GL11.glPushMatrix();
        GL11.glTranslatef(-39.0f, 0.0f, 0.0f);
        GL11.glDisable(2896);
        GL11.glDisable(3553);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        final Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.setColorRGBA(0, 0, 0, this.backVisible);
        tessellator.addVertex(0.0, 0.0, 0.0);
        tessellator.addVertex(0.0, 29.0, 0.0);
        tessellator.addVertex(3.0, 29.0, 0.0);
        tessellator.addVertex(3.0, 0.0, 0.0);
        tessellator.draw();
        final float s = Math.max(0.0f, hunger / 20.0f / 3.0f - 0.07f);
        final Color name = Color.getHSBColor(s, 1.0f, 1.0f);
        final int r = name.getRed();
        final int g = name.getGreen();
        final int b = name.getBlue();
        GL11.glTranslatef(0.0f, 0.0f, 1.0f);
        tessellator.startDrawingQuads();
        tessellator.setColorRGBA(r, g, b, 255);
        tessellator.addVertex(1.0, 1.0, 0.0);
        tessellator.addVertex(1.0, (double)(28.0f * (hunger / 20.0f)), 0.0);
        tessellator.addVertex(2.0, (double)(28.0f * (hunger / 20.0f)), 0.0);
        tessellator.addVertex(2.0, 1.0, 0.0);
        tessellator.draw();
        GL11.glDisable(3042);
        GL11.glEnable(3553);
        GL11.glTranslatef(-0.1f, 29.75f, 1.0f);
        GL11.glScalef(0.35f, 0.35f, 1.0f);
        GuiImage.bindTexture("minecraft:textures/gui/icons.png");
        GuiImage.drawImage(0, 0, 16, 27, 9, 9, 1.0f);
        GL11.glTranslatef(0.0f, 0.0f, 1.0f);
        GuiImage.drawImage(0, 0, 52, 27, 9, 8, 1.0f);
        GL11.glEnable(2896);
        GL11.glPopMatrix();
    }
    
    private void renderItemDamage(final FontRenderer p_77021_1_, final TextureManager p_77021_2_, final ItemStack p_77021_3_) {
        this.renderItemDamage(p_77021_1_, p_77021_2_, p_77021_3_, 0, 0, null);
    }
    
    private void renderItemDamage(final FontRenderer p_94148_1_, final TextureManager p_94148_2_, final ItemStack p_94148_3_, final int p_94148_4_, final int p_94148_5_, final String p_94148_6_) {
        if (p_94148_3_ != null) {
            if (p_94148_3_.stackSize > 1 || p_94148_6_ != null) {
                final String s1 = (p_94148_6_ == null) ? String.valueOf(p_94148_3_.stackSize) : p_94148_6_;
                GL11.glPushMatrix();
                GL11.glDisable(3042);
                GL11.glDisable(2896);
                GL11.glDepthMask(false);
                GL11.glTranslatef(0.0f, 0.0f, 1.0f);
                p_94148_1_.drawStringWithShadow(s1, p_94148_4_ + 19 - 2 - p_94148_1_.getStringWidth(s1), p_94148_5_ + 6 + 3, 16777215);
                GL11.glDepthMask(true);
                GL11.glEnable(2896);
                GL11.glPopMatrix();
            }
            if (p_94148_3_.getItem().showDurabilityBar(p_94148_3_)) {
                final double health = p_94148_3_.getItem().getDurabilityForDisplay(p_94148_3_);
                final int j1 = (int)Math.round(13.0 - health * 13.0);
                final int k = (int)Math.round(255.0 - health * 255.0);
                GL11.glDisable(2896);
                GL11.glDisable(3553);
                GL11.glDisable(3008);
                GL11.glDisable(3042);
                final Tessellator tessellator = Tessellator.instance;
                final int l = 255 - k << 16 | k << 8;
                final int i1 = (255 - k) / 4 << 16 | 0x3F00;
                GL11.glTranslatef(0.0f, 0.0f, 50.0f);
                this.renderQuad(tessellator, p_94148_4_ + 2, p_94148_5_ + 13, 13, 2, 0);
                GL11.glTranslatef(0.0f, 0.0f, 50.0f);
                this.renderQuad(tessellator, p_94148_4_ + 2, p_94148_5_ + 13, 12, 1, i1);
                GL11.glTranslatef(0.0f, 0.0f, 50.0f);
                this.renderQuad(tessellator, p_94148_4_ + 2, p_94148_5_ + 13, j1, 1, l);
                GL11.glEnable(3008);
                GL11.glEnable(3553);
                GL11.glEnable(2896);
                GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            }
        }
    }
    
    private void renderQuad(final Tessellator p_77017_1_, final int p_77017_2_, final int p_77017_3_, final int p_77017_4_, final int p_77017_5_, final int p_77017_6_) {
        p_77017_1_.startDrawingQuads();
        p_77017_1_.setColorOpaque_I(p_77017_6_);
        p_77017_1_.addVertex((double)(p_77017_2_ + 0), (double)(p_77017_3_ + 0), 0.0);
        p_77017_1_.addVertex((double)(p_77017_2_ + 0), (double)(p_77017_3_ + p_77017_5_), 0.0);
        p_77017_1_.addVertex((double)(p_77017_2_ + p_77017_4_), (double)(p_77017_3_ + p_77017_5_), 0.0);
        p_77017_1_.addVertex((double)(p_77017_2_ + p_77017_4_), (double)(p_77017_3_ + 0), 0.0);
        p_77017_1_.draw();
    }
}
