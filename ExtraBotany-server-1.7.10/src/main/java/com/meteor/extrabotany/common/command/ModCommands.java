/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.command;

import com.meteor.extrabotany.common.command.CommandAddShieldAmount;
import com.meteor.extrabotany.common.command.CommandGetShieldAmount;
import com.meteor.extrabotany.common.command.CommandRenderShield;
import com.meteor.extrabotany.common.command.CommandSetShieldAmount;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import net.minecraft.command.ICommand;

public class ModCommands {
    public ModCommands(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandGetShieldAmount());
        event.registerServerCommand(new CommandSetShieldAmount());
        event.registerServerCommand(new CommandAddShieldAmount());
        event.registerServerCommand(new CommandRenderShield());
    }
}

