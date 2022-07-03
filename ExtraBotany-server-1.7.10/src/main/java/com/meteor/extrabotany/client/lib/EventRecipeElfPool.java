/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.lib;

import com.meteor.extrabotany.common.block.tile.TileElfPool;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.ItemStack;
import net.minecraft.profiler.Profiler;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.mana.IManaItem;
import vazkii.botania.api.recipe.RecipeManaInfusion;
import vazkii.botania.client.core.handler.ItemsRemainingRenderHandler;
import vazkii.botania.client.core.helper.RenderHelper;

public class EventRecipeElfPool {
    public static final ResourceLocation manaBar = new ResourceLocation("botania:textures/gui/manaHud.png");

    @SubscribeEvent
    public void toolTipEv(ItemTooltipEvent e) {
        try {
            if (e.itemStack != null && e.itemStack.getItem() instanceof IManaItem) {
                e.toolTip.add("\u00a7e\u0422\u0435\u043a\u0443\u0449\u0435\u0435 \u043a\u043e\u043b-\u0432\u043e \u043c\u0430\u043d\u044b: \u00a7b" + ((IManaItem)e.itemStack.getItem()).getMana(e.itemStack));
                e.toolTip.add("\u00a7e\u041c\u0430\u043a\u0441\u0438\u043c\u0430\u043b\u044c\u043d\u043e\u0435 \u043a\u043e\u043b-\u0432\u043e \u043c\u0430\u043d\u044b: \u00a7b" + ((IManaItem)e.itemStack.getItem()).getMaxMana(e.itemStack));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @SubscribeEvent
    public void onDrawRecipeElfPool(RenderGameOverlayEvent.Post event) {
        Minecraft mc = Minecraft.getMinecraft();
        Profiler profiler = mc.mcProfiler;
        ItemStack equippedStack = mc.thePlayer.getCurrentEquippedItem();
        profiler.startSection("ExtraBotania-hud");
        try {
            MovingObjectPosition pos;
            if (event.type == RenderGameOverlayEvent.ElementType.ALL && (pos = mc.objectMouseOver) != null) {
                Block block = mc.theWorld.getBlock(pos.blockX, pos.blockY, pos.blockZ);
                TileEntity tile = mc.theWorld.getTileEntity(pos.blockX, pos.blockY, pos.blockZ);
                if (tile != null && tile instanceof TileElfPool && equippedStack != null) {
                    this.renderPoolRecipeHUD(event.resolution, (TileElfPool)tile, equippedStack);
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        ItemsRemainingRenderHandler.render((ScaledResolution)event.resolution, (float)event.partialTicks);
        profiler.endSection();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    private void renderPoolRecipeHUD(ScaledResolution res, TileElfPool tile, ItemStack stack) {
        Minecraft mc = Minecraft.getMinecraft();
        for (RecipeManaInfusion recipe : BotaniaAPI.manaInfusionRecipes) {
            if (!recipe.matches(stack) || recipe.isAlchemy() && !tile.alchemy || recipe.isConjuration() && !tile.conjuration) continue;
            int x = res.getScaledWidth() / 2 - 11;
            int y = res.getScaledHeight() / 2 + 10;
            int u = tile.getCurrentMana() >= recipe.getManaToConsume() ? 0 : 22;
            int v = mc.thePlayer.getCommandSenderName().equals("haighyorkie") && mc.thePlayer.isSneaking() ? 23 : 8;
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            mc.renderEngine.bindTexture(manaBar);
            RenderHelper.drawTexturedModalRect((int)x, (int)y, (float)0.0f, (int)u, (int)v, (int)22, (int)15);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            net.minecraft.client.renderer.RenderHelper.enableGUIStandardItemLighting();
            RenderItem.getInstance().renderItemAndEffectIntoGUI(mc.fontRenderer, mc.renderEngine, stack, x - 20, y);
            RenderItem.getInstance().renderItemAndEffectIntoGUI(mc.fontRenderer, mc.renderEngine, recipe.getOutput(), x + 26, y);
            RenderItem.getInstance().renderItemOverlayIntoGUI(mc.fontRenderer, mc.renderEngine, recipe.getOutput(), x + 26, y);
            net.minecraft.client.renderer.RenderHelper.disableStandardItemLighting();
            GL11.glDisable((int)2896);
            GL11.glDisable((int)3042);
            break;
        }
    }
}

