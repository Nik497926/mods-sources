/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.core.version;

import com.meteor.extrabotany.common.core.version.UpdateChecker;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class ThreadUpdateChecker
extends Thread {
    public ThreadUpdateChecker() {
        this.setName("ExtraBotania Version Checker Thread");
        this.setDaemon(true);
        this.start();
    }

    @Override
    public void run() {
        try {
            URL e = new URL("https://raw.githubusercontent.com/ExtraMeteorP/ExtraBotany/master/build/1.7.10.txt");
            BufferedReader r = new BufferedReader(new InputStreamReader(e.openStream()));
            UpdateChecker.onlineVersion = r.readLine();
            r.close();
        }
        catch (Exception var3) {
            var3.printStackTrace();
        }
        UpdateChecker.doneChecking = true;
    }
}

