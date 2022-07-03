/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.lib;

import com.meteor.extrabotany.client.gui.Inventarium.LibSkillID;
import com.meteor.extrabotany.common.block.BlockPoolEfir;
import com.meteor.extrabotany.common.block.ModBlocks;
import com.meteor.extrabotany.common.block.tile.TileBlockPoolEfir;
import com.meteor.extrabotany.common.block.tile.TileEAltar;
import com.meteor.extrabotany.common.block.tile.TileTradeMana;
import com.meteor.extrabotany.common.block.tile.TileTransformater;
import com.meteor.extrabotany.common.core.util.ItemNBTHelper;
import com.meteor.extrabotany.common.item.ItemManaReader;
import com.meteor.extrabotany.common.item.relic.legendary.armor.awake.ItemAwakeOGArmor;
import com.meteor.extrabotany.common.item.relic.legendary.armor.killer.ItemKillerArmor;
import com.meteor.extrabotany.common.recipe.ModEAltarRecipe;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.profiler.Profiler;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.lwjgl.opengl.GL11;

public class HUDHandler {
    private int[][] texture = new int[][]{{128, 35}, {144, 35}, {160, 35}, {176, 35}, {192, 35}, {208, 35}, {128, 51}, {144, 51}, {160, 51}, {176, 51}, {192, 51}, {208, 51}, {128, 67}, {144, 67}, {160, 67}, {176, 67}, {192, 67}, {208, 67}, {128, 83}, {144, 83}, {160, 83}, {176, 83}, {192, 83}, {208, 83}};
    public static int id_skill_hud = 0;

    @SubscribeEvent
    public void onDrawScreenPost(RenderGameOverlayEvent.Post event) {
        Minecraft mc = Minecraft.getMinecraft();
        Profiler profiler = mc.mcProfiler;
        ItemStack equippedStack = mc.thePlayer.getCurrentEquippedItem();
        if (event.type == RenderGameOverlayEvent.ElementType.ALL) {
            profiler.startSection("extrabotania-hud");
            RenderHelper.enableGUIStandardItemLighting();
            GL11.glEnable((int)32826);
            try {
                HashMap<String, Integer[]> $listText;
                MovingObjectPosition pos = mc.objectMouseOver;
                TileEntity $tile = mc.theWorld.getTileEntity(pos.blockX, pos.blockY, pos.blockZ);
                HashMap<String, Integer[]> listText = new HashMap<String, Integer[]>();
                boolean blockinfo = false;
                boolean showHUDreader = false;
                if ($tile != null && $tile instanceof TileTradeMana) {
                    $listText = this.showTradeMana(mc, event.resolution, (TileTradeMana)$tile);
                    listText.putAll($listText);
                }
                if (this.checkFullArm8((EntityPlayer)mc.thePlayer)) {
                    $listText = this.showSkills((EntityPlayer)mc.thePlayer, event.resolution);
                    listText.putAll($listText);
                }
                if ($tile != null && $tile instanceof TileBlockPoolEfir) {
                    blockinfo = true;
                }
                if (equippedStack != null && equippedStack.getItem() instanceof ItemManaReader && ItemManaReader.getSeeSomething(equippedStack)) {
                    showHUDreader = true;
                }
                if (blockinfo) {
                    $listText = this.showBlockInfo(mc, event.resolution, $tile);
                    listText.putAll($listText);
                }
                if (showHUDreader) {
                    TileEntity te;
                    int x = ItemNBTHelper.getInteger(equippedStack, "x", 0);
                    int y = ItemNBTHelper.getInteger(equippedStack, "y", -1);
                    int z = ItemNBTHelper.getInteger(equippedStack, "z", 0);
                    int DIM = ItemNBTHelper.getInteger(equippedStack, "DIM", 0);
                    if (ItemManaReader.getSeeTransformater(equippedStack)) {
                        if (y == -1) {
                            return;
                        }
                        te = mc.theWorld.getTileEntity(x, y, z);
                        if (te == null) {
                            return;
                        }
                        if (!(te instanceof TileTransformater)) {
                            return;
                        }
                        int efir = ((TileTransformater)te).getEfir();
                        HashMap<String, Integer[]> $listText2 = this.showTransformater(mc.thePlayer.worldObj, (EntityPlayer)mc.thePlayer, event.resolution, new ChunkCoordinates(x, y, z), efir, DIM, mc, blockinfo ? 65 : 0);
                        listText.putAll($listText2);
                    } else if (ItemManaReader.getSeeEAltar(equippedStack)) {
                        if (y == -1) {
                            return;
                        }
                        te = mc.theWorld.getTileEntity(x, y, z);
                        if (te == null) {
                            return;
                        }
                        if (!(te instanceof TileEAltar)) {
                            return;
                        }
                        HashMap<String, Integer[]> $listText3 = this.showEAltar(mc, event.resolution, te, blockinfo ? 65 : 0);
                        listText.putAll($listText3);
                    }
                }
                RenderHelper.disableStandardItemLighting();
                GL11.glDisable((int)2929);
                FontRenderer font = Minecraft.getMinecraft().fontRenderer;
                boolean unicode = font.getUnicodeFlag();
                for (Map.Entry entry : listText.entrySet()) {
                    if (((Integer[])entry.getValue()).length == 4 && ((Integer[])entry.getValue())[3] != null) {
                        font.setUnicodeFlag(true);
                        font.drawString((String)entry.getKey(), ((Integer[])entry.getValue())[1].intValue(), ((Integer[])entry.getValue())[2].intValue(), ((Integer[])entry.getValue())[0].intValue());
                        continue;
                    }
                    font.setUnicodeFlag(unicode);
                    font.drawString((String)entry.getKey(), ((Integer[])entry.getValue())[1].intValue(), ((Integer[])entry.getValue())[2].intValue(), ((Integer[])entry.getValue())[0].intValue());
                }
                font.setUnicodeFlag(unicode);
            }
            catch (Exception exception) {
                // empty catch block
            }
            GL11.glEnable((int)2929);
            profiler.endSection();
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        }
    }

    private HashMap<String, Integer[]> showSkills(EntityPlayer pl, ScaledResolution res) {
        HashMap<String, Integer[]> result = new HashMap<String, Integer[]>();
        int x = res.getScaledWidth();
        int y = res.getScaledHeight() / 2;
        ItemStack st = pl.inventory.armorInventory[1];
        NBTTagList list = vazkii.botania.common.core.helper.ItemNBTHelper.getList((ItemStack)st, (String)"activeSkill", (int)10, (boolean)false);
        int rendID = 0;
        boolean findNeedSkill = false;
        for (int i = 0; i < list.tagCount(); ++i) {
            NBTTagCompound n = list.getCompoundTagAt(i);
            if (!n.hasKey("active") || !n.hasKey("id") || !n.getBoolean("active")) continue;
            int idSkill = n.getInteger("id");
            if (id_skill_hud == rendID) {
                findNeedSkill = true;
                HUDHandler.drawTexturedModalRect(x - 32, y - (132 - rendID * 22), 1.0f, 150, 0, 22, 22, Minecraft.getMinecraft(), 0);
            }
            HUDHandler.drawTexturedModalRect(x - 29, y - (129 - rendID * 22), 1.0f, this.texture[idSkill - 1][0], this.texture[idSkill - 1][1], 16, 16, Minecraft.getMinecraft(), 0);
            if (n.hasKey("cd")) {
                HUDHandler.drawTexturedModalRect(x - 29, y - (129 - rendID * 22), 2.0f, 128, 99, 16, 16, Minecraft.getMinecraft(), 0);
            }
            if (pl.isSneaking()) {
                String s = (id_skill_hud == rendID ? "\u00a72" : "") + StatCollector.translateToLocal((String)LibSkillID.allSkill[idSkill]);
                if (n.hasKey("cd")) {
                    s = s + " \u00a7c\u043f\u0435\u0440\u0435\u0437\u0430\u0440\u0435\u0436\u0430\u0435\u0442\u0441\u044f";
                }
                result.put(s, new Integer[]{new Color(0xFFFFFF).getRGB(), x - (n.hasKey("cd") ? 25 : 35) - s.length() * 4, y - (125 - rendID * 22), 1});
            }
            ++rendID;
        }
        if (!findNeedSkill) {
            id_skill_hud = 0;
        }
        return result;
    }

    private HashMap<String, Integer[]> showTradeMana(Minecraft mc, ScaledResolution res, TileTradeMana te) {
        HashMap<String, Integer[]> result = new HashMap<String, Integer[]>();
        int x = res.getScaledWidth() / 2;
        int y = res.getScaledHeight() / 2;
        String s = "\u041f\u0440\u043e\u0434\u0430\u0436\u0430 \u043c\u0430\u043d\u044b (500.000 \u043c\u0430\u043d\u044b)";
        result.put(s, new Integer[]{new Color(0xFFFFFF).getRGB(), x - s.length() * 5 / 2, y - 20});
        s = te.getCurrentMana() >= 500000 ? "\u00a72\u041c\u0430\u043d\u0430 \u0432 \u043d\u0430\u043b\u0438\u0447\u0438\u0438" : "\u00a74\u0412\u043d\u0438\u043c\u0430\u043d\u0438\u0435! \u00a7f\u041d\u0435\u0434\u043e\u0441\u0442\u0430\u0442\u043e\u0447\u043d\u043e \u043c\u0430\u043d\u044b \u0432 \u0430\u043f\u043f\u0430\u0440\u0430\u0442\u0435 \u0434\u043b\u044f \u043f\u0440\u043e\u0434\u0430\u0436\u0438";
        result.put(s, new Integer[]{new Color(0xFFFFFF).getRGB(), x - s.length() * 5 / 2, y - 5});
        if (te.getPrice() != null) {
            ItemStack it = te.getPrice();
            s = "\u0426\u0435\u043d\u0430: ";
            result.put(s, new Integer[]{new Color(0xFFFFFF).getRGB(), x - 100, y + 25});
            RenderHelper.enableGUIStandardItemLighting();
            int i1 = x - 70;
            try {
                RenderItem.getInstance().renderItemAndEffectIntoGUI(mc.fontRenderer, mc.renderEngine, it, i1, y + 20);
                RenderItem.getInstance().renderItemOverlayIntoGUI(mc.fontRenderer, mc.renderEngine, it, i1, y + 20);
                RenderHelper.disableStandardItemLighting();
            }
            catch (Exception exception) {
                // empty catch block
            }
            if (!mc.thePlayer.isSneaking()) {
                s = "\u0414\u043b\u044f \u043f\u043e\u0434\u0440\u043e\u0431\u043d\u043e\u0441\u0442\u0435\u0439 \u043e \u0446\u0435\u043d\u0435 \u043d\u0430\u0436\u043c\u0438\u0442\u0435 \u00a76SHIFT";
                result.put(s, new Integer[]{new Color(0xFFFFFF).getRGB(), x - 50, y + 25});
            } else {
                try {
                    ArrayList l = (ArrayList)it.getTooltip((EntityPlayer)mc.thePlayer, true);
                    for (int i = 0; i < l.size(); ++i) {
                        String ls = (String)l.get(i);
                        result.put(ls, new Integer[]{new Color(0xFFFFFF).getRGB(), x - 50, y + (25 + i * 10)});
                    }
                }
                catch (Exception exception) {}
            }
        } else {
            s = "\u00a74\u0412\u043d\u0438\u043c\u0430\u043d\u0438\u0435! \u00a7f\u0426\u0435\u043d\u0430 \u043d\u0435 \u0443\u0441\u0442\u0430\u043d\u043e\u0432\u043b\u0435\u043d\u0430";
            result.put(s, new Integer[]{new Color(0xFFFFFF).getRGB(), x - 70, y + 25});
        }
        s = "\u0414\u043b\u044f \u043f\u043e\u043a\u0443\u043f\u043a\u0438 \u043d\u0430\u0436\u043c\u0438\u0442\u0435 \u043f\u0440\u0435\u0434\u043c\u0435\u0442\u043e\u043c \u041f\u041a\u041c";
        result.put(s, new Integer[]{new Color(0xFFFFFF).getRGB(), x - s.length() * 5 / 2, y + 10});
        return result;
    }

    private HashMap<String, Integer[]> showBlockInfo(Minecraft mc, ScaledResolution res, TileEntity tile) {
        int yc = res.getScaledHeight() / 2;
        HUDHandler.drawTexturedModalRect(1, yc - 35, 0.0f, 0, 100, 128, 59, mc, 0);
        HUDHandler.drawTexturedModalRect(131, yc - 35, 0.0f, 128, 0, 22, 22, mc, 0);
        HashMap<String, Integer[]> returnList = new HashMap<String, Integer[]>();
        if (tile == null) {
            return returnList;
        }
        if (tile.blockType == null) {
            return returnList;
        }
        int safe = tile.blockType instanceof BlockPoolEfir ? 2 : 0;
        ItemStack $stack = new ItemStack(tile.blockType);
        RenderItem.getInstance().renderItemAndEffectIntoGUI(mc.fontRenderer, mc.renderEngine, $stack, 134, res.getScaledHeight() / 2 - 32 + safe);
        returnList.put(tile.blockType.getLocalizedName(), new Integer[]{new Color(0).getRGB(), 10, res.getScaledHeight() / 2 - 25});
        if (tile instanceof TileBlockPoolEfir) {
            int _efir = ((TileBlockPoolEfir)tile).getEfir();
            String efir = "\u042d\u0444\u0438\u0440: \u00a74" + Integer.toString(Math.max(0, _efir));
            int stab = ((TileBlockPoolEfir)tile).getStability(tile);
            returnList.put(efir, new Integer[]{new Color(0).getRGB(), 10, res.getScaledHeight() / 2 - 10});
            returnList.put("\u0421\u0442\u0430\u0431\u0438\u043b\u044c\u043d\u043e\u0441\u0442\u044c: " + (stab < 50 ? "\u00a74" : "\u00a72") + Integer.toString(stab) + "%", new Integer[]{new Color(0).getRGB(), 10, res.getScaledHeight() / 2 + 5});
        }
        return returnList;
    }

    private HashMap<String, Integer[]> showTransformater(World world, EntityPlayer player, ScaledResolution res, ChunkCoordinates chunk, int efir, int DIM, Minecraft mc, int index) {
        HashMap<String, Integer[]> returnList = new HashMap<String, Integer[]>();
        int yc = res.getScaledHeight() / 2;
        HUDHandler.drawTexturedModalRect(1, yc + (-35 + index), 0.0f, 0, 0, 128, 100, mc, 0);
        TileEntity te = world.getTileEntity(chunk.posX, chunk.posY, chunk.posZ);
        boolean link = ((TileTransformater)te).hasLink();
        RenderHelper.enableGUIStandardItemLighting();
        RenderItem.getInstance().renderItemAndEffectIntoGUI(mc.fontRenderer, mc.renderEngine, new ItemStack(ModBlocks.transformater), 80, res.getScaledHeight() / 2 - 30 + index);
        if (link) {
            RenderItem.getInstance().renderItemAndEffectIntoGUI(mc.fontRenderer, mc.renderEngine, new ItemStack(ModBlocks.poolefir), 80, res.getScaledHeight() / 2 + 32 + index);
        }
        RenderHelper.disableStandardItemLighting();
        returnList.put("\u0412\u044b\u0431\u0440\u0430\u043d \u0431\u043b\u043e\u043a: ", new Integer[]{new Color(0).getRGB(), 10, res.getScaledHeight() / 2 - 25 + index});
        if (DIM == mc.thePlayer.worldObj.provider.dimensionId) {
            returnList.put("\u00a72\u00a7n\u0412 \u044d\u0442\u043e\u043c \u043c\u0438\u0440\u0435", new Integer[]{new Color(0).getRGB(), 10, res.getScaledHeight() / 2 - 10 + index});
        } else {
            returnList.put("\u00a74\u00a7n\u0412\u044b\u0431\u0440\u0430\u043d\u043d\u044b\u0439 \u0431\u043b\u043e\u043a \u043d\u0430\u0445\u043e\u0434\u0438\u0442\u0441\u044f \u0432 \u0434\u0440\u0443\u0433\u043e\u043c \u043c\u0438\u0440\u0435", new Integer[]{new Color(0).getRGB(), 10, res.getScaledHeight() / 2 - 10 + index});
        }
        returnList.put("x: \u00a79" + Integer.toString(chunk.posX) + "\u00a70, y: \u00a79" + Integer.toString(chunk.posY) + "\u00a70, z: \u00a79" + Integer.toString(chunk.posZ), new Integer[]{new Color(0).getRGB(), 10, res.getScaledHeight() / 2 + 5 + index});
        returnList.put("\u042d\u0444\u0438\u0440: \u00a74" + Integer.toString(efir), new Integer[]{new Color(0).getRGB(), 10, res.getScaledHeight() / 2 + 20 + index});
        returnList.put(link ? "\u00a72\u041f\u0440\u0438\u0432\u044f\u0437\u0430\u043d \u043a:" : "\u00a74\u041d\u0435 \u043f\u0440\u0438\u0432\u044f\u0437\u0430\u043d\u00a7r\u00a72", new Integer[]{new Color(0).getRGB(), 10, res.getScaledHeight() / 2 + 35 + index});
        if (link) {
            ChunkCoordinates chunk2 = ((TileTransformater)te).getLinkCoord();
            if (chunk2.posY != -1) {
                returnList.put("x: \u00a79" + Integer.toString(chunk2.posX) + "\u00a70, y: \u00a79" + Integer.toString(chunk2.posY) + "\u00a70, z: \u00a79" + Integer.toString(chunk2.posZ), new Integer[]{new Color(0).getRGB(), 10, res.getScaledHeight() / 2 + 50 + index});
            }
        }
        return returnList;
    }

    private HashMap<String, Integer[]> showEAltar(Minecraft mc, ScaledResolution res, TileEntity tile, int index) {
        int _id;
        World world = mc.thePlayer.worldObj;
        EntityClientPlayerMP player = mc.thePlayer;
        HashMap<String, Integer[]> returnList = new HashMap<String, Integer[]>();
        int yc = res.getScaledHeight() / 2;
        HUDHandler.drawTexturedModalRect(1, yc + (-35 + index), 0.0f, 0, 0, 128, 100, mc, 0);
        RenderHelper.enableGUIStandardItemLighting();
        RenderItem.getInstance().renderItemAndEffectIntoGUI(mc.fontRenderer, mc.renderEngine, new ItemStack(ModBlocks.eAltar), 80, res.getScaledHeight() / 2 - 30 + index);
        RenderHelper.disableStandardItemLighting();
        returnList.put("\u0412\u044b\u0431\u0440\u0430\u043d \u0431\u043b\u043e\u043a: ", new Integer[]{new Color(0).getRGB(), 10, res.getScaledHeight() / 2 - 25 + index});
        returnList.put("x: \u00a79" + Integer.toString(tile.xCoord) + "\u00a70, y: \u00a79" + Integer.toString(tile.yCoord) + "\u00a70, z: \u00a79" + Integer.toString(tile.zCoord), new Integer[]{new Color(0).getRGB(), 10, res.getScaledHeight() / 2 - 10 + index});
        boolean link = ((TileEAltar)tile).hasLink();
        returnList.put(link ? "\u00a72\u041f\u0440\u0438\u0432\u044f\u0437\u0430\u043d" : "\u00a74\u041d\u0435 \u043f\u0440\u0438\u0432\u044f\u0437\u0430\u043d", new Integer[]{new Color(0).getRGB(), 10, res.getScaledHeight() / 2 + 5 + index});
        String _recipe = ((TileEAltar)tile).getRecipe();
        ItemStack out = null;
        if (!_recipe.isEmpty()) {
            if (!ModEAltarRecipe.instance.isRecipe(_recipe)) {
                _recipe = new String();
            } else {
                out = ModEAltarRecipe.instance.getOutput(_recipe);
            }
        }
        int n = _id = !_recipe.isEmpty() ? Item.itemRegistry.getIDForObject((Object)out.getItem()) : 0;
        String meta = !_recipe.isEmpty() ? (out.getItemDamage() == 0 ? "" : ":" + out.getItemDamage()) : "";
        returnList.put("\u0420\u0435\u0446\u0435\u043f\u0442: " + (_recipe.isEmpty() ? "\u00a74\u043d\u0435\u0442" : "\u00a72" + Integer.toString(_id) + meta + " x" + Integer.toString(out.stackSize)), new Integer[]{new Color(0).getRGB(), 10, res.getScaledHeight() / 2 + 20 + index});
        int efir = ((TileEAltar)tile).getEfir();
        returnList.put("\u0422\u0440\u0435\u0431\u0443\u0435\u0442\u0441\u044f \u044d\u0444\u0438\u0440\u0430: " + (_recipe.isEmpty() ? "\u00a740" : "\u00a72" + Integer.toString(efir)), new Integer[]{new Color(0).getRGB(), 10, res.getScaledHeight() / 2 + 35 + index});
        return returnList;
    }

    private NBTTagList rearmSkill(NBTTagList in) {
        NBTTagList out = new NBTTagList();
        for (int i = 0; i < in.tagCount(); ++i) {
            NBTTagCompound _n = in.getCompoundTagAt(i);
            if (!_n.hasKey("cd") || !_n.hasKey("id")) continue;
            out.appendTag((NBTBase)_n);
        }
        return out;
    }

    private boolean checkFullArm8(EntityPlayer pl) {
        ItemStack v0 = pl.inventory.armorInventory[0];
        ItemStack v1 = pl.inventory.armorInventory[1];
        ItemStack v2 = pl.inventory.armorInventory[2];
        ItemStack v3 = pl.inventory.armorInventory[3];
        return v0 != null && this.has8lvl(v0, pl) && v1 != null && this.has8lvl(v1, pl) && v2 != null && this.has8lvl(v2, pl) && v3 != null && this.has8lvl(v3, pl);
    }

    private boolean has8lvl(ItemStack stack, EntityPlayer pl) {
        if (stack.getItem() instanceof ItemAwakeOGArmor && ItemNBTHelper.getInteger(stack, "level", 0) >= 8 && ItemAwakeOGArmor.isRightPlayer(pl, stack)) {
            return true;
        }
        return stack.getItem() instanceof ItemKillerArmor && ItemKillerArmor.isRightPlayer(pl, stack);
    }

    public static void drawTexturedModalRect(int x, int y, float z, int texturesX, int texturesY, int width, int height, Minecraft mc, int id) {
        HUDHandler.drawTexturedModalRect(x, y, z, texturesX, texturesY, width, height, 0.00390625f, 0.00390625f, mc, id);
    }

    public static void drawTexturedModalRect(int x, int y, float z, int texturesX, int texturesY, int width, int height, float f, float f1, Minecraft mc, int id) {
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        if (id == 0) {
            mc.renderEngine.bindTexture(new ResourceLocation("extrabotania", "textures/gui/HUD.png"));
        } else if (id == 1 || id == 2 || id == 6) {
            mc.renderEngine.bindTexture(new ResourceLocation("extrabotania", "textures/gui/HUD4.png"));
        } else if (id == 3 || id == 7) {
            mc.renderEngine.bindTexture(new ResourceLocation("extrabotania", "textures/gui/HUD3.png"));
        } else if (id == 4 || id == 5) {
            mc.renderEngine.bindTexture(new ResourceLocation("extrabotania", "textures/gui/HUD5.png"));
        } else {
            mc.renderEngine.bindTexture(new ResourceLocation("extrabotania", "textures/gui/HUD2.png"));
        }
        tessellator.addVertexWithUV((double)(x + 0), (double)(y + height), (double)z, (double)((float)(texturesX + 0) * f), (double)((float)(texturesY + height) * f1));
        tessellator.addVertexWithUV((double)(x + width), (double)(y + height), (double)z, (double)((float)(texturesX + width) * f), (double)((float)(texturesY + height) * f1));
        tessellator.addVertexWithUV((double)(x + width), (double)(y + 0), (double)z, (double)((float)(texturesX + width) * f), (double)((float)(texturesY + 0) * f1));
        tessellator.addVertexWithUV((double)(x + 0), (double)(y + 0), (double)z, (double)((float)(texturesX + 0) * f), (double)((float)(texturesY + 0) * f1));
        tessellator.draw();
    }
}

