/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.lib;

import com.meteor.extrabotany.client.gui.GuiSettingArmor;
import com.meteor.extrabotany.client.gui.GuiSkill;
import com.meteor.extrabotany.client.lib.HUDHandler;
import com.meteor.extrabotany.client.lib.KeyBindings;
import com.meteor.extrabotany.common.core.network.NetworkHandler2;
import com.meteor.extrabotany.common.core.network.networkItem.MessageHandleGuiItemButtonPress;
import com.meteor.extrabotany.common.event.EventSkill;
import com.meteor.extrabotany.common.item.relic.legendary.armor.awake.ItemAwakeOGArmor;
import com.meteor.extrabotany.common.item.relic.legendary.armor.killer.ItemKillerArmor;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class KeyInputHandler {
    @SideOnly(value=Side.CLIENT)
    @SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
    public void onEventHudPress(InputEvent.KeyInputEvent event) {
        ArrayList<Integer> arr;
        EntityClientPlayerMP pl = Minecraft.getMinecraft().thePlayer;
        Minecraft mc = Minecraft.getMinecraft();
        if (KeyBindings.toolConfig.isPressed() && this.fullArm8lvl()) {
            mc.displayGuiScreen((GuiScreen)new GuiSkill("textures/gui/skill0.png", 0, 0));
        }
        if (KeyBindings.knifeInBack.isPressed() && ItemKillerArmor.hasFullArmor((EntityPlayer)pl)) {
            mc.displayGuiScreen((GuiScreen)new GuiSettingArmor());
        }
        if (KeyBindings.bloodVictim.isPressed() && EventSkill.fullArm8lvl((EntityPlayer)pl)) {
            arr = ItemAwakeOGArmor.checkChange((EntityPlayer)pl);
            HUDHandler.id_skill_hud = HUDHandler.id_skill_hud == 0 ? arr.size() - 1 : --HUDHandler.id_skill_hud;
        }
        if (KeyBindings.selfSacrifice.isPressed() && EventSkill.fullArm8lvl((EntityPlayer)pl)) {
            arr = ItemAwakeOGArmor.checkChange((EntityPlayer)pl);
            HUDHandler.id_skill_hud = HUDHandler.id_skill_hud == arr.size() - 1 ? 0 : ++HUDHandler.id_skill_hud;
        }
        if (KeyBindings.perjurer.isPressed() && EventSkill.fullArm8lvl((EntityPlayer)pl)) {
            arr = ItemAwakeOGArmor.checkChange((EntityPlayer)pl);
            if (arr.size() == 0) {
                return;
            }
            if (HUDHandler.id_skill_hud >= arr.size()) {
                HUDHandler.id_skill_hud = 0;
            }
            int id = ItemKillerArmor.hasFullArmor((EntityPlayer)pl) ? 1000 + arr.get(HUDHandler.id_skill_hud) : 1000 + arr.get(HUDHandler.id_skill_hud);
            NetworkHandler2.INSTANCE2.sendToServer((IMessage)new MessageHandleGuiItemButtonPress(mc.thePlayer.inventory.armorInventory[1], id, (EntityPlayer)pl));
        }
    }

    private boolean fullArm8lvl() {
        for (int i = 0; i < 4; ++i) {
            ItemStack st = Minecraft.getMinecraft().thePlayer.inventory.armorInventory[i];
            if (this.is8lvl(st)) continue;
            return false;
        }
        return true;
    }

    private boolean is8lvl(ItemStack stack) {
        EntityClientPlayerMP pl = Minecraft.getMinecraft().thePlayer;
        return EventSkill.is8lvl(stack, (EntityPlayer)pl);
    }
}

