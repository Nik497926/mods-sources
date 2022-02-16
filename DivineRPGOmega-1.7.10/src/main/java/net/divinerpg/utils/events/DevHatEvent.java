/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.event.RenderPlayerEvent$Specials$Pre
 *  org.lwjgl.opengl.GL11
 */
package net.divinerpg.utils.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.divinerpg.utils.Util;
import net.divinerpg.utils.events.ModelHat;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderPlayerEvent;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class DevHatEvent {
    private static ModelHat hat = new ModelHat();

    @SubscribeEvent
    public void playerRender(RenderPlayerEvent.Specials.Pre evt) {
        if (Util.isDonatorName(evt.entityPlayer.getCommandSenderName())) {
            GL11.glPushMatrix();
            evt.renderer.modelBipedMain.bipedHead.postRender(0.0625f);
            GL11.glRotatef((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glTranslatef((float)-0.5f, (float)0.5f, (float)-0.5f);
            Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("divinerpg:textures/model/devhat.png"));
            hat.renderAll();
            GL11.glPopMatrix();
        }
    }
}

