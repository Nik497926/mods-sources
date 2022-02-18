package ru.obvilion.tablist.events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiPlayerInfo;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Pre;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import ru.obvilion.tablist.core.Core;
import ru.obvilion.tablist.core.HeadThread;

public class GuiEvents {
    public static Map<String, Boolean> checker = new HashMap();
    public static Map<String, Boolean> done = new HashMap();
    public static Map<String, BufferedImage> icons = new HashMap();
    public static Map<String, ResourceLocation> res = new HashMap();
    public static List<String> names = new ArrayList();
    private boolean toggle = false;
    private int biggestPlayerWidth = 0;

    public GuiEvents() {
    }

    @SubscribeEvent
    public void onTabListDrawed(Pre e) {
        if ((e.type == ElementType.HOTBAR || e.type == ElementType.HEALTH || e.type == ElementType.HEALTHMOUNT || e.type == ElementType.EXPERIENCE || e.type == ElementType.FOOD || e.type == ElementType.ARMOR) && this.toggle) {
            e.setCanceled(true);
        }

        if (e.type == ElementType.PLAYER_LIST) {
            e.setCanceled(true);
            this.renderTabList();
        }

    }

    @SubscribeEvent
    public void onKeyInput(KeyInputEvent event) {
        if (Keyboard.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindPlayerList.getKeyCode())) {
            this.toggle = true;
        } else {
            this.toggle = false;
        }

    }

    private int get_admins_count(List<GuiPlayerInfo> players) {
        int result = 0;
        String[] codes = Core.admins_prefixes.split(";");

        for (GuiPlayerInfo player : players) {
            ScorePlayerTeam team = Minecraft.getMinecraft().theWorld.getScoreboard().getPlayersTeam(player.name);
            String displayname = ScorePlayerTeam.formatPlayerName(team, player.name);

            for (String code : codes) {
                System.err.println("DEBUG: " + displayname);
                if (displayname.contains(code.replace("&", "§"))) {
                    ++result;
                    break;
                }
            }
        }

        return result;
    }

    private void renderTabList() {
        Minecraft mc = Minecraft.getMinecraft();
        List<GuiPlayerInfo> players = Minecraft.getMinecraft().thePlayer.sendQueue.playerInfoList;
        int players_count = players.size();
        ScaledResolution res = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
        int width = res.getScaledWidth();
        int height = res.getScaledHeight();
        ScoreObjective scoreobjective = mc.theWorld.getScoreboard().func_96539_a(0);
        NetHandlerPlayClient handler = mc.thePlayer.sendQueue;
        if (mc.gameSettings.keyBindPlayerList.getIsKeyPressed() && (!mc.isIntegratedServerRunning() || handler.playerInfoList.size() > 1 || scoreobjective != null)) {
            int maxPlayers = handler.currentServerMaxPlayers;
            int rows = maxPlayers;

            int columns;
            for (columns = 1; rows > 20; rows = (maxPlayers + columns - 1) / columns) {
                ++columns;
            }

            int columnWidth = 300 / columns;
            this.biggestPlayerWidth = this.getBiggestWidth(players);
            if (columnWidth < this.biggestPlayerWidth) {
                columnWidth = this.biggestPlayerWidth;
            }

            int left = (width - columns * columnWidth) / 2;
            Gui.drawRect(left - 1, 1, left + columnWidth * columns, 13, -1879048192);
            mc.fontRenderer.drawStringWithShadow("§6Игроки онлайн", width / 2 - mc.fontRenderer.getStringWidth("Игроки онлайн") / 2, 3, 16777215);
            byte border = 15;
            int admins_count = this.get_admins_count(players);
            boolean draw_personal = false;
            Gui.drawRect(left - 1, border - 1, left + columnWidth * columns, border + 9 * rows + 2, -2147483648);
            if (admins_count > 0) {
                draw_personal = true;
            }

            if (Core.personal_calc && draw_personal) {
                Gui.drawRect(left - 1, border + 9 * rows + 2 + 5, left + columnWidth * columns, border + 9 * rows + 2 + 30, -1879048192);
                mc.fontRenderer.drawStringWithShadow("§e " + players.size() + " §6игрок" + this.getNumEnding(players.size(), new String[]{"", "а", "ов"}) + " из §e" + maxPlayers, width / 2 - mc.fontRenderer.getStringWidth(players.size() + " §6игрок" + this.getNumEnding(players.size(), new String[]{"", "а", "ов"}) + " из §e" + maxPlayers) / 2, border + 9 * rows + 2 + 9, 16777215);
                mc.fontRenderer.drawStringWithShadow("§aПерсонал онлайн: §2" + admins_count + " §aчел.", width / 2 - mc.fontRenderer.getStringWidth("Персонал онлайн: " + admins_count + " чел.") / 2, border + 9 * rows + 2 + 18, 16777215);
            } else {
                Gui.drawRect(left - 1, border + 9 * rows + 3, left + columnWidth * columns, border + 9 * rows + 17, -1879048192);
                mc.fontRenderer.drawStringWithShadow("§e " + players.size() + " §6игрок" + this.getNumEnding(players.size(), new String[]{"", "а", "ов"}) + " из §e" + maxPlayers, width / 2 - mc.fontRenderer.getStringWidth(players.size() + " §6игрок" + this.getNumEnding(players.size(), new String[]{"", "а", "ов"}) + " из §e" + maxPlayers) / 2, border + 9 * rows + 6, 16777215);
            }

            this.draw_copyright(height, width, mc);

            for(int i = 0; i < maxPlayers; ++i) {
                int xPos = left + i % columns * columnWidth;
                int yPos = border + i / columns * 9;
                Gui.drawRect(xPos, yPos, xPos + columnWidth - 1, yPos + 10, 553648127);
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                GL11.glEnable(3008);
                if (i < players_count) {
                    GuiPlayerInfo pl = (GuiPlayerInfo)players.get(i);
                    String realName = pl.name;
                    if (checker.getOrDefault(this.get_clean_nick(realName, 1), true)) {
                        checker.put(this.get_clean_nick(realName, 1), false);
                        (new Thread(new HeadThread(this.get_clean_nick(realName, 1)))).start();
                    }

                    if (done.getOrDefault(this.get_clean_nick(realName, 1), false)) {
                        if (!GuiEvents.res.containsKey(this.get_clean_nick(realName, 1))) {
                            ResourceLocation icon = new ResourceLocation("skins/heads/" + this.get_clean_nick(realName, 1));
                            DynamicTexture var10000 = (DynamicTexture)mc.getTextureManager().getTexture(icon);
                            BufferedImage bufferedimage = (BufferedImage)icons.get(this.get_clean_nick(realName, 1));
                            DynamicTexture dt = new DynamicTexture(bufferedimage.getWidth(), bufferedimage.getHeight());
                            mc.getTextureManager().loadTexture(icon, dt);
                            bufferedimage.getRGB(0, 0, bufferedimage.getWidth(), bufferedimage.getHeight(), dt.getTextureData(), 0, bufferedimage.getWidth());
                            dt.updateDynamicTexture();
                            GuiEvents.res.put(this.get_clean_nick(realName, 1), icon);
                            icons.remove(this.get_clean_nick(realName, 1));
                        }

                        this.drawHeadTextureRect((ResourceLocation)GuiEvents.res.get(this.get_clean_nick(realName, 1)), xPos, yPos, xPos + 9, yPos + 9);
                    } else {
                        this.drawHeadTextureRect(AbstractClientPlayer.locationStevePng, xPos, yPos, xPos + 9, yPos + 9);
                    }

                    ScorePlayerTeam team = mc.theWorld.getScoreboard().getPlayersTeam(this.get_clean_nick(realName, 1));
                    String displayName = "";
                    if (Core.use_scoreboards) {
                        displayName = ScorePlayerTeam.formatPlayerName(team, realName);
                    } else {
                        displayName = realName;
                    }

                    mc.fontRenderer.drawStringWithShadow(displayName, xPos + 11, yPos, 16777215);
                    if (scoreobjective != null) {
                        int endX = xPos + mc.fontRenderer.getStringWidth(displayName) + 5;
                        int maxX = xPos + columnWidth - 12 - 5;
                        if (maxX - endX > 5) {
                            Score score = scoreobjective.getScoreboard().func_96529_a(realName, scoreobjective);
                            String scoreDisplay = EnumChatFormatting.YELLOW + "" + score.getScorePoints();
                            mc.fontRenderer.drawStringWithShadow(scoreDisplay, maxX - mc.fontRenderer.getStringWidth(scoreDisplay), yPos, 16777215);
                        }
                    }

                    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                }
            }
        }

    }

    private void draw_copyright(int height, int width, Minecraft mc) {
        //Gui.drawRect(0, height, width, height - 13, 553648127);
        String copy = Core.copyright.replace("%server_name%", Core.server_name);
        mc.fontRenderer.drawStringWithShadow(copy, width / 2 - mc.fontRenderer.getStringWidth(this.get_clean_nick(copy, 2)) / 2, height - 10, 16777215);
    }

    private String get_clean_nick(String name, int type) {
        String[] colors = new String[]{"§1", "§2", "§3", "§4", "§5", "§6", "§7", "§8", "§9", "§0", "§a", "§b", "§c", "§d", "§e", "§f", "§r", "§l", "§o", "§m", "§n", "§k"};
        String result = name;
        String[] nick_data = Core.admins_prefixes.split(";");
        int var6 = nick_data.length;

        int var7;
        String color;
        for(var7 = 0; var7 < var6; ++var7) {
            color = nick_data[var7];
            result = result.replace(color.replace("&", "§"), "");
        }

        nick_data = colors;
        var6 = colors.length;

        for(var7 = 0; var7 < var6; ++var7) {
            color = nick_data[var7];
            result = result.replace(color, "");
        }

        nick_data = result.split(" ");
        if (nick_data.length > 0 && type == 1) {
            result = nick_data[nick_data.length - 1];
        }

        return result;
    }

    private int getBiggestWidth(List<GuiPlayerInfo> players) {
        int i = 0;

        for (GuiPlayerInfo info : players) {
            ScorePlayerTeam team = Minecraft.getMinecraft().theWorld.getScoreboard().getPlayersTeam(info.name);
            String displayName = ScorePlayerTeam.formatPlayerName(team, info.name);
            int size = Minecraft.getMinecraft().fontRenderer.getStringWidth(displayName) + 12;
            if (size > i) {
                i = size;
            }
        }

        return i;
    }

    private void drawTexturedModalRect(int p_73729_1_, int p_73729_2_, int p_73729_3_, int p_73729_4_, int p_73729_5_, int p_73729_6_) {
        float f = 0.00390625F;
        float f1 = 0.00390625F;
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(p_73729_1_, p_73729_2_ + p_73729_6_, 0.0D, (float)(p_73729_3_ + 0) * f, (float)(p_73729_4_ + p_73729_6_) * f1);
        tessellator.addVertexWithUV(p_73729_1_ + p_73729_5_, p_73729_2_ + p_73729_6_, 0.0D, (double)((float)(p_73729_3_ + p_73729_5_) * f), (double)((float)(p_73729_4_ + p_73729_6_) * f1));
        tessellator.addVertexWithUV(p_73729_1_ + p_73729_5_, p_73729_2_, 0.0D, (double)((float)(p_73729_3_ + p_73729_5_) * f), (double)((float)(p_73729_4_ + 0) * f1));
        tessellator.addVertexWithUV(p_73729_1_, p_73729_2_, 0.0D, (float) p_73729_3_ * f, (double)((float)(p_73729_4_ + 0) * f1));
        tessellator.draw();
    }

    private void drawHeadTextureRect(ResourceLocation res, int x1, int y1, int x2, int y2) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(res);
        GL11.glPushMatrix();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glEnable(3042);
        Tessellator t = Tessellator.instance;
        t.startDrawingQuads();
        t.addVertexWithUV((double)x1, (double)y2, 0.0D, 0.125D, 0.5D);
        t.addVertexWithUV((double)x2, (double)y2, 0.0D, 0.25D, 0.5D);
        t.addVertexWithUV((double)x2, (double)y1, 0.0D, 0.25D, 0.25D);
        t.addVertexWithUV((double)x1, (double)y1, 0.0D, 0.125D, 0.25D);
        t.draw();
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }

    private String getNumEnding(int num, String[] ends_arrays) {
        String result = "";
        int number = num % 100;
        if (number >= 11 && number <= 19) {
            result = ends_arrays[2];
        } else {
            int i = number % 10;
            switch(i) {
            case 1:
                result = ends_arrays[0];
                break;
            case 2:
            case 3:
            case 4:
                result = ends_arrays[1];
                break;
            default:
                result = ends_arrays[2];
            }
        }

        return result;
    }

    private void drawTextureRect(ResourceLocation res, int x1, int y1, int x2, int y2) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(res);
        GL11.glPushMatrix();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glTexParameteri(3553, 10241, 9729);
        GL11.glTexParameteri(3553, 10240, 9729);
        GL11.glEnable(3042);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glEnable(3042);
        Tessellator t = Tessellator.instance;
        t.startDrawingQuads();
        t.addVertexWithUV((double)x1, (double)y2, 0.0D, 0.0D, 1.0D);
        t.addVertexWithUV((double)x2, (double)y2, 0.0D, 1.0D, 1.0D);
        t.addVertexWithUV((double)x2, (double)y1, 0.0D, 1.0D, 0.0D);
        t.addVertexWithUV((double)x1, (double)y1, 0.0D, 0.0D, 0.0D);
        t.draw();
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }
}
