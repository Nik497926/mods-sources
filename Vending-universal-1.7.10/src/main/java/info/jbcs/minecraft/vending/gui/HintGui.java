/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.EventPriority
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityClientPlayerMP
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$ElementType
 *  net.minecraftforge.client.event.RenderGameOverlayEvent$Post
 *  org.lwjgl.opengl.GL11
 */
package info.jbcs.minecraft.vending.gui;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import info.jbcs.minecraft.vending.General;
import info.jbcs.minecraft.vending.GeneralClient;
import info.jbcs.minecraft.vending.tileentity.TileEntityVendingMachine;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.lwjgl.opengl.GL11;

public class HintGui
extends Gui {
    int xx;
    int yy;
    private Minecraft mc;
    RenderItem render = new RenderItem();

    public HintGui(Minecraft mc) {
        this.mc = mc;
    }

    @SubscribeEvent(priority=EventPriority.NORMAL)
    public void onRenderInfo(RenderGameOverlayEvent.Post event) {
        if (event.isCancelable() || event.type != RenderGameOverlayEvent.ElementType.EXPERIENCE) {
            return;
        }
        if (this.mc == null || this.mc.thePlayer == null || this.mc.theWorld == null) {
            return;
        }
        WorldClient world = this.mc.theWorld;
        EntityClientPlayerMP player = this.mc.thePlayer;
        MovingObjectPosition mop = General.getMovingObjectPositionFromPlayer((World)world, (EntityPlayer)player, false);
        if (mop == null) {
            return;
        }
        TileEntity te = world.getTileEntity(mop.blockX, mop.blockY, mop.blockZ);
        if (te == null) {
            return;
        }
        if (!(te instanceof TileEntityVendingMachine)) {
            return;
        }
        TileEntityVendingMachine tileEntity = (TileEntityVendingMachine)te;
        this.draw(tileEntity.ownerName, tileEntity.getSoldItems(), tileEntity.getBoughtItems(), tileEntity);
        GeneralClient.bind("textures/gui/icons.png");
    }

    void drawNumberForItem(FontRenderer fontRenderer, ItemStack stack, int ux, int uy) {
        if (stack == null || stack.stackSize < 2) {
            return;
        }
        String line = "" + stack.stackSize;
        int x = ux + 19 - 2 - fontRenderer.getStringWidth(line);
        int y = uy + 6 + 3;
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)50.0f);
        this.drawString(fontRenderer, line, x + 1, y + 1, 0x888888);
        this.drawString(fontRenderer, line, x, y, 0xFFFFFF);
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-50.0f);
    }

    void drawLabel(FontRenderer fontRenderer, String label, int x, int y, int colour) {
        int w;
        int witdth = w = fontRenderer.getStringWidth(StatCollector.translateToLocal((String)label)) + 2;
        this.drawString(fontRenderer, label, x -= witdth / 2 - 20, y, colour);
    }

    void drawItems(FontRenderer fontRenderer, int x, int y, int colour, ItemStack[] itemStacks, boolean drawDescription, int descWidth) {
        ItemStack itemStack;
        ArrayList<ItemStack> list = new ArrayList<ItemStack>();
        for (ItemStack itemStack2 : itemStacks) {
            if (itemStack2 == null) continue;
            list.add(itemStack2);
        }
        int numOfItems = list.size();
        int w = 0;
        for (ItemStack itemStack2 : list) {
            this.drawNumberForItem(fontRenderer, itemStack2, x + w, y - 4);
            this.render.renderItemIntoGUI(fontRenderer, this.mc.renderEngine, itemStack2, x + w, y - 4);
            GL11.glDisable((int)2896);
            w += 18;
        }
        y += 20;
        if (drawDescription && (itemStack = this.getFromList(list, (int)this.mc.thePlayer.worldObj.getWorldTime() / 50 % numOfItems)) != null) {
            for (Object object : itemStack.getTooltip((EntityPlayer)this.mc.thePlayer, false).toArray()) {
                String line = object.toString();
                if (line.isEmpty()) continue;
                this.drawString(fontRenderer, line, x, y, 0xA0A0A0);
                y += 16;
            }
        }
    }

    private ItemStack getFromList(ArrayList<ItemStack> list, int i) {
        return i < list.size() ? list.get(i) : null;
    }

    void drawItemsWithLabel(FontRenderer fontRenderer, String label, int x, int y, int colour, ItemStack[] itemStacks, boolean drawDescription, int descWidth) {
        ItemStack itemStack;
        int w = fontRenderer.getStringWidth(StatCollector.translateToLocal((String)label)) + 2;
        int numOfItems = General.countNotNull(itemStacks);
        int witdth = drawDescription ? Math.max(w + 18 * numOfItems, descWidth) : w + 18 * numOfItems;
        this.drawString(fontRenderer, StatCollector.translateToLocal((String)label), x -= witdth / 2, y, colour);
        for (ItemStack itemStack2 : itemStacks) {
            if (itemStack2 == null) continue;
            this.drawNumberForItem(fontRenderer, itemStack2, x + w, y - 4);
            this.render.renderItemIntoGUI(fontRenderer, this.mc.renderEngine, itemStack2, x + w, y - 4);
            GL11.glDisable((int)2896);
            w += 18;
        }
        y += 20;
        if (drawDescription && (itemStack = General.getNotNull(itemStacks, (int)this.mc.thePlayer.worldObj.getWorldTime() / 50 % numOfItems)) != null) {
            for (Object object : itemStack.getTooltip((EntityPlayer)this.mc.thePlayer, false).toArray()) {
                String line = object.toString();
                if (line.isEmpty()) continue;
                this.drawString(fontRenderer, line, x, y, 0xA0A0A0);
                y += 16;
            }
        }
    }

    public static void drawTextureCustomSize(double posX, double posY, double startPixX, double startPixY, double pieceSizeX, double pieceSizeY, float sizeTextureX, float sizeTextureY) {
        float f4 = 1.0f / sizeTextureX;
        float f5 = 1.0f / sizeTextureY;
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(posX, posY + pieceSizeY, 0.0, startPixX * (double)f4, (startPixY + (double)((float)pieceSizeY)) * (double)f5);
        tessellator.addVertexWithUV(posX + pieceSizeX, posY + pieceSizeY, 0.0, (startPixX + (double)((float)pieceSizeX)) * (double)f4, (startPixY + (double)((float)pieceSizeY)) * (double)f5);
        tessellator.addVertexWithUV(posX + pieceSizeX, posY, 0.0, (startPixX + (double)((float)pieceSizeX)) * (double)f4, startPixY * (double)f5);
        tessellator.addVertexWithUV(posX, posY, 0.0, startPixX * (double)f4, startPixY * (double)f5);
        tessellator.draw();
    }

    void draw(String seller, ItemStack[] soldItems, ItemStack[] boughtItems, TileEntityVendingMachine tile) {
        String tooltip;
        int i;
        int c;
        boolean isSoldEmpty = General.countNotNull(soldItems) == 0;
        boolean isBoughtEmpty = General.countNotNull(boughtItems) == 0;
        boolean isMoney = tile.eco;
        if (isMoney && isSoldEmpty) {
            return;
        }
        if ((isBoughtEmpty || isSoldEmpty) && !isMoney) {
            return;
        }
        ScaledResolution resolution = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
        int screenwidth = resolution.getScaledWidth();
        FontRenderer fontRenderer = this.mc.fontRenderer;
        int width = resolution.getScaledWidth();
        int height = resolution.getScaledHeight();
        int linesBought = 0;
        int lengthBought = 0;
        int linesSold = 0;
        int lengthSold = 0;
        for (ItemStack bought : boughtItems) {
            if (bought == null) continue;
            c = 0;
            for (i = 0; i < bought.getTooltip((EntityPlayer)this.mc.thePlayer, false).size(); ++i) {
                tooltip = bought.getTooltip((EntityPlayer)this.mc.thePlayer, false).get(i).toString();
                if (!tooltip.isEmpty()) {
                    ++c;
                }
                if (tooltip.length() <= lengthBought) continue;
                lengthBought = fontRenderer.getStringWidth(tooltip);
            }
            linesBought = Math.max(linesBought, c);
        }
        for (ItemStack sold : soldItems) {
            if (sold == null) continue;
            c = 0;
            for (i = 0; i < sold.getTooltip((EntityPlayer)this.mc.thePlayer, false).size(); ++i) {
                tooltip = sold.getTooltip((EntityPlayer)this.mc.thePlayer, false).get(i).toString();
                if (!tooltip.isEmpty()) {
                    ++c;
                }
                if (tooltip.length() <= lengthSold) continue;
                lengthSold = fontRenderer.getStringWidth(tooltip);
            }
            linesSold = Math.max(linesBought, c);
            linesBought = Math.max(linesBought, c);
        }
        boolean drawDesc = this.mc.thePlayer.isSneaking();
        int descHeight = Math.max(linesBought, linesSold) * 16;
        int w = 120 + fontRenderer.getStringWidth(seller);
        int h = 44 + (drawDesc ? descHeight : 0) + 8;
        int centerYOff = -80 + (drawDesc ? descHeight / 2 : 0) + 8;
        if (drawDesc && !isBoughtEmpty && !isSoldEmpty) {
            h -= 16;
            centerYOff -= 8;
        }
        int cx = width / 2;
        int x = cx - w / 2;
        int y = height / 2 - h / 2 + centerYOff;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)0.0f, (float)0.0f, (float)-100.0f);
        GL11.glDisable((int)2896);
        this.drawGradientRect(cx - w / 2 - (drawDesc ? lengthSold / 2 : 0), y - 10, cx + w / 2 + (drawDesc ? lengthSold / 2 : 0), y + h + 10, -1072689136, -804253680);
        if (!isMoney || tile.mode == 0) {
            this.drawCenteredString(fontRenderer, "\u0412 \u043d\u0430\u043b\u0438\u0447\u0438\u0438: " + tile.count, cx + 7, y + 8, 0xAAFFAA);
        }
        this.drawCenteredString(fontRenderer, StatCollector.translateToLocal((String)"gui.vendingBlock.owner") + ": " + seller, cx, y - 2, 0xFFFFFF);
        this.mc.getTextureManager().bindTexture(new ResourceLocation("vending:textures/arrow.png"));
        HintGui.drawTextureCustomSize(-30 + (cx += 20), y + 22, 0.0, 0.0, 25.0, 17.0, 25.0f, 17.0f);
        if (!isBoughtEmpty && !isSoldEmpty) {
            this.drawItems(fontRenderer, cx + (drawDesc ? 0 : 0), y + 26, 0xA0A0A0, soldItems, drawDesc, lengthSold);
            this.drawItems(fontRenderer, -53 + cx - (drawDesc ? lengthSold / 2 : 0), y + 26, 0xA0A0A0, boughtItems, drawDesc, lengthBought);
        } else if (!isSoldEmpty && isMoney) {
            int moneyWidth = ("" + tile.getMoney() + "$").length() * 10;
            this.drawItems(fontRenderer, tile.mode == 0 ? cx + (drawDesc ? 0 : 0) : cx - (drawDesc ? lengthSold / 2 : 0) - 50, y + 26, 0xA0A0A0, soldItems, drawDesc, lengthSold);
            this.drawLabel(fontRenderer, EnumChatFormatting.GREEN + "" + tile.getMoney() + "$", tile.mode == 0 ? cx - 74 : cx + (drawDesc ? 10 : 5), y + 26, 0xA0A0A0);
        }
        GL11.glDisable((int)2896);
        GL11.glPopMatrix();
    }
}

