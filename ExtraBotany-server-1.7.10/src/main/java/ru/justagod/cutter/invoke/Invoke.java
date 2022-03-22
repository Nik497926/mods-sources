/*
 * Decompiled with CFR 0.152.
 */
package ru.justagod.cutter.invoke;

import ru.justagod.cutter.invoke.InvokeClient;
import ru.justagod.cutter.invoke.InvokeClientValue;
import ru.justagod.cutter.invoke.InvokeServer;
import ru.justagod.cutter.invoke.InvokeServerValue;

public class Invoke {
    public static void client(InvokeClient block) {
        block.run();
    }

    public static void server(InvokeServer block) {
        block.run();
    }

    public static <T> T serverValue(InvokeServerValue<T> block) {
        return block.run();
    }

    public static <T> T clientValue(InvokeClientValue<T> block) {
        return block.run();
    }
}

