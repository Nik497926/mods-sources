/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod.proxy;

import ru.simplemc.simplecore.mod.manager.IntegrationManager;
import ru.simplemc.simplecore.mod.proxy.CommonProxy;

public class ServerProxy
extends CommonProxy {
    @Override
    public IntegrationManager getIntegrationManager() {
        return this.integrationManager;
    }

    @Override
    public boolean isIntegrated() {
        if (this.integrationManager == null) {
            this.integrationManager = new IntegrationManager();
        }
        return super.isIntegrated();
    }
}

