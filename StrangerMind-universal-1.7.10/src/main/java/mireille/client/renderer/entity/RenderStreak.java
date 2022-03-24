package mireille.client.renderer.entity;

import net.minecraft.client.model.*;
import mireille.common.entity.*;
import net.minecraft.client.entity.*;
import net.minecraft.client.*;
import mireille.core.*;
import mireille.utils.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import mireille.*;
import org.lwjgl.opengl.*;
import mireille.api.*;
import net.minecraft.util.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.renderer.*;
import java.util.*;
import net.minecraft.entity.*;

public class RenderStreak extends Render
{
    public ModelBiped modelBiped;
    private static final ResourceLocation tronYellow;
    private static final ResourceLocation tronBlue;
    private static final ResourceLocation tronRed;
    private static final ResourceLocation tronGreen;
    private static final ResourceLocation tronPurple;
    private ResourceLocation texture;
    
    public RenderStreak() {
        super.shadowSize = 0.0f;
        this.modelBiped = new ModelBiped(0.0f);
        this.modelBiped.isChild = false;
    }
    
    public void renderStreak(final EntityStreak hat, final double par2, final double par4, final double par6, final float par8, final float par9) {
        if (hat.parent instanceof AbstractClientPlayer && !hat.parent.isInvisible()) {
            final AbstractClientPlayer player = (AbstractClientPlayer)hat.parent;
            if (!player.isInvisible() && (player != Minecraft.getMinecraft().thePlayer || Minecraft.getMinecraft().gameSettings.thirdPersonView != 0)) {
                if (CosmeticArmorUtils.isWear((EntityPlayer)player, (Item)ModItems.Tron_head_yellow, (Item)ModItems.Tron_chest_yellow, (Item)ModItems.Tron_leggs_yellow, (Item)ModItems.Tron_boots_yellow)) {
                    this.texture = RenderStreak.tronYellow;
                }
                else if (CosmeticArmorUtils.isWear((EntityPlayer)player, (Item)ModItems.Tron_head_blue, (Item)ModItems.Tron_chest_blue, (Item)ModItems.Tron_leggs_blue, (Item)ModItems.Tron_boots_blue)) {
                    this.texture = RenderStreak.tronBlue;
                }
                else if (CosmeticArmorUtils.isWear((EntityPlayer)player, (Item)ModItems.Tron_head_red, (Item)ModItems.Tron_chest_red, (Item)ModItems.Tron_leggs_red, (Item)ModItems.Tron_boots_red)) {
                    this.texture = RenderStreak.tronRed;
                }
                else if (CosmeticArmorUtils.isWear((EntityPlayer)player, (Item)ModItems.Tron_head_green, (Item)ModItems.Tron_chest_green, (Item)ModItems.Tron_leggs_green, (Item)ModItems.Tron_boots_green)) {
                    this.texture = RenderStreak.tronGreen;
                }
                else {
                    if (!CosmeticArmorUtils.isWear((EntityPlayer)player, (Item)ModItems.Tron_head_purple, (Item)ModItems.Tron_chest_purple, (Item)ModItems.Tron_leggs_purple, (Item)ModItems.Tron_boots_purple)) {
                        return;
                    }
                    this.texture = RenderStreak.tronPurple;
                }
                final ArrayList loc = StrangerMind.tickHandlerClient.getPlayerStreakLocationInfo((EntityPlayer)player);
                GL11.glPushMatrix();
                final Minecraft mc = Minecraft.getMinecraft();
                GL11.glDisable(2884);
                GL11.glDisable(3008);
                GL11.glEnable(3042);
                GL11.glBlendFunc(770, 771);
                GL11.glShadeModel(7425);
                final float startGrad = 5.0f - par9;
                final float endGrad = 20.0f - par9;
                for (int i = loc.size() - 2; i >= 0; --i) {
                    int start = i;
                    final StreakLocationInfo infoStart = (StreakLocationInfo) loc.get(i);
                    final float startAlpha = (i < endGrad) ? MathHelper.clamp_float(0.8f * i / endGrad, 0.0f, 0.8f) : ((i > loc.size() - 2 - startGrad) ? MathHelper.clamp_float(0.8f * (loc.size() - 2 - i) / startGrad, 0.0f, 0.8f) : 0.8f);
                    if (player.worldObj.getWorldTime() - infoStart.lastTick > StrangerMind.streakTime + 20) {
                        break;
                    }
                    StreakLocationInfo infoEnd = null;
                    double grad = 500.0;
                    --i;
                    while (i >= 0) {
                        final StreakLocationInfo infoPoint = (StreakLocationInfo) loc.get(i);
                        if (StrangerMind.sprintTrail == 1 && infoStart.isSprinting && loc.size() - 2 - i < 6) {
                            infoEnd = infoPoint;
                            --start;
                            --i;
                            break;
                        }
                        if (infoPoint.hasSameCoords(infoStart)) {
                            --start;
                            --i;
                        }
                        else {
                            final double grad2 = infoPoint.posZ - infoStart.posZ / (infoPoint.posX - infoStart.posX);
                            if (grad == grad2 && infoPoint.posY == infoStart.posY) {
                                infoEnd = infoPoint;
                                --start;
                                --i;
                            }
                            else {
                                if (grad != 500.0) {
                                    break;
                                }
                                grad = grad2;
                                infoEnd = infoPoint;
                                --i;
                            }
                        }
                    }
                    if (infoEnd != null) {
                        i += 2;
                        final float endAlpha = (i < endGrad) ? MathHelper.clamp_float(0.8f * (i - 1) / endGrad, 0.0f, 0.8f) : ((i > loc.size() - 1 - startGrad) ? MathHelper.clamp_float(0.8f * (loc.size() - 1 - i) / startGrad, 0.0f, 0.8f) : 0.8f);
                        final double grad2 = infoStart.posX - RenderManager.renderPosX;
                        final double posY = infoStart.posY - RenderManager.renderPosY;
                        final double posZ = infoStart.posZ - RenderManager.renderPosZ;
                        final double nextPosX = infoEnd.posX - RenderManager.renderPosX;
                        final double nextPosY = infoEnd.posY - RenderManager.renderPosY;
                        final double nextPosZ = infoEnd.posZ - RenderManager.renderPosZ;
                        final Tessellator tessellator = Tessellator.instance;
                        GL11.glPushMatrix();
                        GL11.glTranslated(grad2, posY, posZ);
                        final int ii = hat.getBrightnessForRender(par9);
                        final int j = ii % 65536;
                        final int k = ii / 65536;
                        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, j / 1.0f, k / 1.0f);
                        RenderHelper.disableStandardItemLighting();
                        GL11.glDisable(2896);
                        mc.renderEngine.bindTexture(this.texture);
                        tessellator.startDrawingQuads();
                        tessellator.setColorRGBA_F(1.0f, 1.0f, 1.0f, startAlpha);
                        tessellator.addVertexWithUV(0.0, 0.0, 0.0, infoStart.startU, 1.0);
                        tessellator.addVertexWithUV(0.0, (double)(0.0f + infoStart.height), 0.0, infoStart.startU, 0.0);
                        tessellator.setColorRGBA_F(1.0f, 1.0f, 1.0f, endAlpha);
                        double endTex = infoEnd.startU - start + i;
                        if (endTex > infoStart.startU) {
                            --endTex;
                        }
                        final double distX = infoStart.posX - infoEnd.posX;
                        final double distZ = infoStart.posZ - infoEnd.posZ;
                        double scales = Math.sqrt(distX * distX + distZ * distZ) / infoStart.height;
                        final boolean far = scales > 1.0;
                        if (scales < 1.0) {}
                        while (scales > 1.0) {
                            ++endTex;
                            --scales;
                        }
                        tessellator.addVertexWithUV(nextPosX - grad2, nextPosY - posY + infoEnd.height, nextPosZ - posZ, endTex, 0.0);
                        tessellator.addVertexWithUV(nextPosX - grad2, nextPosY - posY, nextPosZ - posZ, endTex, 1.0);
                        tessellator.draw();
                        GL11.glEnable(2896);
                        RenderHelper.enableStandardItemLighting();
                        GL11.glPopMatrix();
                    }
                }
                GL11.glShadeModel(7424);
                GL11.glDisable(3042);
                GL11.glEnable(3008);
                GL11.glEnable(2884);
                GL11.glPopMatrix();
            }
        }
    }
    
    public void doRender(final Entity par1Entity, final double par2, final double par4, final double par6, final float par8, final float par9) {
        this.renderStreak((EntityStreak)par1Entity, par2, par4, par6, par8, par9);
    }
    
    protected ResourceLocation getEntityTexture(final Entity entity) {
        return new ResourceLocation("mireille:model/armor/tron/tronSkin.png");
    }
    
    static {
        tronYellow = new ResourceLocation("mireille:model/tron_yellow.png");
        tronBlue = new ResourceLocation("mireille:model/tron_blue.png");
        tronRed = new ResourceLocation("mireille:model/tron_red.png");
        tronGreen = new ResourceLocation("mireille:model/tron_green.png");
        tronPurple = new ResourceLocation("mireille:model/tron_purple.png");
    }
}
