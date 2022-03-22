/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import ru.justagod.cutter.invoke.Invoke;

public class CommandRenderShield
extends CommandBase {
    public String getCommandName() {
        return "extrarender";
    }

    public String getCommandUsage(ICommandSender p_71518_1_) {
        return "\u00a7c[ExtraBotania]\u00a7f: /extrarender [\u0412\u044b\u0431\u0435\u0440\u0438\u0442\u0435 \u0442\u0438\u043f 1-3]";
    }

    public void processCommand(ICommandSender iCommandSender, String[] strings) {
        Invoke.server(() -> {});
    }

    private static boolean isDigit(String s) {
        try {
            Integer.parseInt(s);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }
}

