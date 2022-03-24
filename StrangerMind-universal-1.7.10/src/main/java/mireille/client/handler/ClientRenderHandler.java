package mireille.client.handler;

import cpw.mods.fml.relauncher.*;
import net.minecraft.client.*;
import net.minecraft.entity.player.*;
import cpw.mods.fml.common.eventhandler.*;
import net.minecraftforge.client.event.*;
import net.minecraft.client.renderer.entity.*;
import mireille.core.*;
import mireille.utils.*;
import net.minecraft.item.*;
import java.util.*;
import mireille.client.particles.*;
import net.minecraft.client.particle.*;

@SideOnly(Side.CLIENT)
public class ClientRenderHandler
{
    private Minecraft mc;
    public static float partialTicks;
    public static int tick;
    private static int oldTick;
    private List<EntityPlayer> players;
    
    public ClientRenderHandler() {
        this.mc = Minecraft.getMinecraft();
        this.players = new ArrayList<EntityPlayer>();
    }
    
    @SubscribeEvent
    public void onWorldTick(final RenderWorldLastEvent event) {
        ClientRenderHandler.partialTicks = event.partialTicks;
        ClientRenderHandler.tick = (int)(Minecraft.getMinecraft().theWorld.getWorldTime() % 20L);
    }
    
    @SubscribeEvent
    public void onPlayerTick(final RenderPlayerEvent.Post event) {
        final EntityPlayer player = event.entityPlayer;
        if (ClientRenderHandler.oldTick != ClientRenderHandler.tick) {
            ClientRenderHandler.oldTick = ClientRenderHandler.tick;
            this.players.clear();
        }
        else if (this.players.contains(player)) {
            return;
        }
        this.players.add(player);
        if (RenderManager.instance.playerViewY == 180.0f) {
            return;
        }
        if (!CosmeticArmorUtils.isWear(player, (Item)ModItems.CrystalArmorHead, (Item)ModItems.CrystalArmorChest, (Item)ModItems.CrystalArmorLegs, (Item)ModItems.CrystalArmorBoots) || (player.isInvisible() && (player != this.mc.thePlayer || this.mc.gameSettings.thirdPersonView == 0))) {
            if (CosmeticArmorUtils.isWear(player, (Item)ModItems.FairyArmorHead, (Item)ModItems.FairyArmorChest, (Item)ModItems.FairyArmorLegs, (Item)ModItems.FairyArmorBoots) && (!player.isInvisible() || (player == this.mc.thePlayer && this.mc.gameSettings.thirdPersonView != 0))) {
                final Random rand = new Random();
                final double x = (rand.nextDouble() - 0.5) * player.width;
                double y = rand.nextDouble() * player.height;
                final double z = (rand.nextDouble() - 0.5) * player.width;
                final float[] color1 = { 1.0f, 0.4f, 0.5f };
                final float[] color2 = { 1.0f, 1.0f, 0.0f };
                if (player == this.mc.thePlayer) {
                    --y;
                }
                for (int i = 0; i < 1; ++i) {
                    final EntityEnderFx particle = new EntityEnderFx(player, x, y, z, (rand.nextDouble() - 0.5) * 2.0, -rand.nextDouble(), (rand.nextDouble() - 0.5) * 2.0, color1, color2);
                    this.mc.effectRenderer.addEffect((EntityFX)particle);
                }
            }
            else if ((CosmeticArmorUtils.isWear(player, (Item)ModItems.DevilArmorHead, (Item)ModItems.DevilArmorChest, (Item)ModItems.DevilArmorLegs, (Item)ModItems.DevilArmorBoots) || CosmeticArmorUtils.isWear(player, (Item)ModItems.DarknessHead, (Item)ModItems.DarknessChest, (Item)ModItems.DarknessLegs, (Item)ModItems.DarknessBoots)) && (!player.isInvisible() || (player == this.mc.thePlayer && this.mc.gameSettings.thirdPersonView != 0))) {
                final Random rand = new Random();
                final double x = (rand.nextDouble() - 0.5) * player.width;
                double y = rand.nextDouble() * player.height;
                final double z = (rand.nextDouble() - 0.5) * player.width;
                final float[] color1 = { 0.0f, 0.0f, 0.0f };
                final float[] color2 = { 0.0f, 0.0f, 0.0f };
                if (player == this.mc.thePlayer) {
                    --y;
                }
                for (int i = 0; i < 1; ++i) {
                    final EntityEnderFx particle = new EntityEnderFx(player, x, y, z, (rand.nextDouble() - 0.5) * 2.0, -rand.nextDouble(), (rand.nextDouble() - 0.5) * 2.0, color1, color2);
                    this.mc.effectRenderer.addEffect((EntityFX)particle);
                }
            }
            else if (CosmeticArmorUtils.isWear(player, (Item)ModItems.CreepArmorHead, (Item)ModItems.CreepArmorChest, (Item)ModItems.CreepArmorLegs, (Item)ModItems.CreepArmorBoots) && (!player.isInvisible() || (player == this.mc.thePlayer && this.mc.gameSettings.thirdPersonView != 0))) {
                final Random rand = new Random();
                final double x = (rand.nextDouble() - 0.5) * player.width;
                double y = rand.nextDouble() * player.height;
                final double z = (rand.nextDouble() - 0.5) * player.width;
                final float[] color1 = { 0.0f, 0.255f, 0.0f };
                final float[] color2 = { 0.0f, 0.255f, 0.0f };
                if (player == this.mc.thePlayer) {
                    --y;
                }
                for (int i = 0; i < 1; ++i) {
                    final EntityEnderFx particle = new EntityEnderFx(player, x, y, z, (rand.nextDouble() - 0.5) * 2.0, -rand.nextDouble(), (rand.nextDouble() - 0.5) * 2.0, color1, color2);
                    this.mc.effectRenderer.addEffect((EntityFX)particle);
                }
            }
            else if (CosmeticArmorUtils.isWear(player, (Item)ModItems.DaedraHead, (Item)ModItems.DaedraChest, (Item)ModItems.DaedraStanci, (Item)ModItems.DaedraBoots) && (!player.isInvisible() || (player == this.mc.thePlayer && this.mc.gameSettings.thirdPersonView != 0))) {
                final Random rand = new Random();
                final double x = (rand.nextDouble() - 0.5) * player.width;
                double y = rand.nextDouble() * player.height;
                final double z = (rand.nextDouble() - 0.5) * player.width;
                final float[] color1 = { 0.0f, 0.2f, 0.6f };
                final float[] color2 = { 0.0f, 0.2f, 0.6f };
                if (player == this.mc.thePlayer) {
                    --y;
                }
                for (int i = 0; i < 1; ++i) {
                    final EntityEnderFx particle = new EntityEnderFx(player, x, y, z, (rand.nextDouble() - 0.5) * 2.0, -rand.nextDouble(), (rand.nextDouble() - 0.5) * 2.0, color1, color2);
                    this.mc.effectRenderer.addEffect((EntityFX)particle);
                }
            }
        }
        else {
            final Random rand = new Random();
            final double x = (rand.nextDouble() - 0.5) * player.width;
            double y = rand.nextDouble() * player.height;
            final double z = (rand.nextDouble() - 0.5) * player.width;
            final float[] color1 = { 1.0f, 1.0f, 1.0f };
            final float[] color2 = { 1.0f, 1.0f, 1.0f };
            if (player == this.mc.thePlayer) {
                --y;
            }
            for (int i = 0; i < 1; ++i) {
                final EntityEnderFx particle = new EntityEnderFx(player, x, y, z, (rand.nextDouble() - 0.5) * 2.0, -rand.nextDouble(), (rand.nextDouble() - 0.5) * 2.0, color1, color2);
                this.mc.effectRenderer.addEffect((EntityFX)particle);
            }
        }
    }
    
    static {
        ClientRenderHandler.partialTicks = 0.0f;
        ClientRenderHandler.tick = 0;
        ClientRenderHandler.oldTick = 0;
    }
}
