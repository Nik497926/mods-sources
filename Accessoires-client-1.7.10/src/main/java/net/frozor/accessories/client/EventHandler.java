/*
 * Decompiled with CFR 0.152.
 */
package net.frozor.accessories.client;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.util.concurrent.GenericFutureListener;
import net.frozor.accessories.AccessoriesCore;
import net.frozor.accessories.client.ClientProxy;
import net.frozor.accessories.client.item.AccessoryItem;
import net.frozor.accessories.client.item.CategoryType;
import net.frozor.accessories.client.item.animation.AbstractAnimation;
import net.frozor.accessories.client.item.animation.WingAnim;
import net.frozor.accessories.client.network.packet.CPacketCapeSetting;
import net.frozor.accessories.client.ui.TabButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import org.lwjgl.opengl.GL11;

public class EventHandler {
    private RenderPlayer renderPlayer;
    private Minecraft minecraft = Minecraft.getMinecraft();
    private static final ResourceLocation steveTextures = new ResourceLocation("textures/entity/steve.png");

    public EventHandler() {
        this.renderPlayer = new RenderPlayer();
    }

    public void renderItem(EntityPlayer player, RenderPlayer renderPlayer, AccessoryItem item, float ticksAge) {
        if (item == null) {
            return;
        }
        GL11.glPushMatrix();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glColor4d((double)1.0, (double)1.0, (double)1.0, (double)1.0);
        GL11.glDisable((int)3008);
        GL11.glEnable((int)32826);
        GL11.glEnable((int)3042);
        OpenGlHelper.glBlendFunc((int)770, (int)771, (int)1, (int)0);
        switch (item.getType()) {
            case HEAD: 
            case FACE: {
                renderPlayer.modelBipedMain.bipedHead.postRender(0.0625f);
                Minecraft.getMinecraft().getTextureManager().bindTexture(item.getTexture());
                GL11.glCallList((int)item.getIndexRenderer());
                break;
            }
            case FAMILIAR: {
                renderPlayer.modelBipedMain.bipedBody.postRender(0.0625f);
                Minecraft.getMinecraft().getTextureManager().bindTexture(item.getTexture());
                if (item.getAnimation() != null) {
                    item.getAnimation().play(player, ticksAge);
                }
                GL11.glCallList((int)item.getIndexRenderer());
                break;
            }
            case BODY: {
                renderPlayer.modelBipedMain.bipedBody.postRender(0.0625f);
                Minecraft.getMinecraft().getTextureManager().bindTexture(item.getTexture());
                if (item.isWing()) {
                    WingAnim wingAnim = (WingAnim)item.getAnimation();
                    wingAnim.play(player, ticksAge);
                    GL11.glPushMatrix();
                    GL11.glRotatef((float)wingAnim.getWings(), (float)0.0f, (float)1.0f, (float)0.0f);
                    GL11.glCallList((int)item.getIndexRenderer());
                    GL11.glPopMatrix();
                    GL11.glPushMatrix();
                    GL11.glScalef((float)-1.0f, (float)1.0f, (float)1.0f);
                    GL11.glRotatef((float)wingAnim.getWings(), (float)0.0f, (float)1.0f, (float)0.0f);
                    GL11.glCallList((int)item.getIndexRenderer());
                    GL11.glPopMatrix();
                    break;
                }
                GL11.glCallList((int)item.getIndexRenderer());
            }
        }
        GL11.glPopMatrix();
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void onRenderPlayerEventPre(RenderPlayerEvent.Specials.Pre event) {
        event.renderCape = this.minecraft.thePlayer.getDisplayName().equalsIgnoreCase(event.entityPlayer.getDisplayName()) ? ClientProxy.equipManager.isShowCape() : ClientProxy.equipManager.isShowCape(event.entityPlayer.getDisplayName());
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void onInitGuiEventPost(GuiScreenEvent.InitGuiEvent.Post event) {
        if (event.gui instanceof GuiInventory) {
            int xSize = 176;
            int ySize = 166;
            int guiLeft = (event.gui.width - xSize) / 2;
            int guiTop = (event.gui.height - ySize) / 2;
            GuiInventory guiInventory = (GuiInventory)event.gui;
            event.buttonList.add(new TabButton(guiLeft + 87, guiTop + 64, 80, 20, "Аксессуары"));
        }
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void onConnectedToServerEvent(FMLNetworkEvent.ClientConnectedToServerEvent event) {
        CPacketCapeSetting packet = new CPacketCapeSetting(AccessoriesCore.SHOW_MY_CAPE_SETTING, true);
        event.manager.scheduleOutboundPacket((Packet)new C17PacketCustomPayload("ACS", packet.getBytes()), new GenericFutureListener[0]);
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void onRenderPlayerEventPost(RenderPlayerEvent.Specials.Post event) {
        if (!AccessoriesCore.SHOW_ACCESSORIES_SETTING) {
            return;
        }
        float ticksAge = (float)event.entityLiving.ticksExisted + event.partialRenderTick;
        if (this.minecraft.thePlayer.getDisplayName().equalsIgnoreCase(event.entityPlayer.getDisplayName())) {
            this.renderItem(event.entityPlayer, event.renderer, ClientProxy.equipManager.getHead(), ticksAge);
            this.renderItem(event.entityPlayer, event.renderer, ClientProxy.equipManager.getFace(), ticksAge);
            this.renderItem(event.entityPlayer, event.renderer, ClientProxy.equipManager.getBody(), ticksAge);
            this.renderItem(event.entityPlayer, event.renderer, ClientProxy.equipManager.getFamiliar(), ticksAge);
        } else {
            this.renderItem(event.entityPlayer, event.renderer, ClientProxy.equipManager.getHead(event.entityPlayer.getDisplayName()), ticksAge);
            this.renderItem(event.entityPlayer, event.renderer, ClientProxy.equipManager.getFace(event.entityPlayer.getDisplayName()), ticksAge);
            this.renderItem(event.entityPlayer, event.renderer, ClientProxy.equipManager.getBody(event.entityPlayer.getDisplayName()), ticksAge);
            this.renderItem(event.entityPlayer, event.renderer, ClientProxy.equipManager.getFamiliar(event.entityPlayer.getDisplayName()), ticksAge);
        }
    }

    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent
    public void onWorldRenderer(RenderWorldLastEvent event) {
        AccessoryItem item = ClientProxy.equipManager.items.get((Object)CategoryType.HEAD).get("crown_king");
        if (item == null) {
            return;
        }
        double doubleX = this.minecraft.thePlayer.posX - 0.5;
        double doubleY = this.minecraft.thePlayer.posY - 5.64;
        double doubleZ = this.minecraft.thePlayer.posZ - 0.5;
        GL11.glPushMatrix();
        GL11.glTranslated((double)(-doubleX), (double)(-doubleY), (double)(-doubleZ));
        this.renderPlayer.modelBipedMain.render((Entity)this.minecraft.thePlayer, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f);
        GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glScalef((float)0.0625f, (float)0.0625f, (float)0.0625f);
        float mx = 9.0f;
        float my = 9.0f;
        float mz = 9.0f;
        GL11.glRotatef((float)180.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        Minecraft.getMinecraft().getTextureManager().bindTexture(item.getTexture());
        float scaled = 0.038f;
        GL11.glTranslatef((float)(item.getPreferencesRenderer().getTranslation()[0] * scaled), (float)(-item.getPreferencesRenderer().getTranslation()[1] * scaled), (float)(item.getPreferencesRenderer().getTranslation()[2] * scaled));
        GL11.glTranslatef((float)0.0f, (float)-0.25f, (float)0.0f);
        GL11.glRotatef((float)item.getPreferencesRenderer().getRotation()[2], (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glRotatef((float)item.getPreferencesRenderer().getRotation()[1], (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)item.getPreferencesRenderer().getRotation()[0], (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glScalef((float)1.0f, (float)1.0f, (float)2.0f);
        GL11.glTranslatef((float)8.0f, (float)-8.0f, (float)8.0f);
        item.getModel().renderAll();
        GL11.glPopMatrix();
    }
}

