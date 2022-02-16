/*
 * Decompiled with CFR 0.152.
 */
package net.divinerpg.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import net.divinerpg.utils.LogHelper;

public abstract class RegistryFile {
    protected String filePath;
    protected BufferedWriter writer;
    protected File file;
    protected StringBuilder builder = new StringBuilder();

    public RegistryFile(String filePath) {
        this.filePath = filePath;
        this.file = new File(filePath);
    }

    public abstract void addNames();

    public void localizeName(String prefixOfLine, String unlocalizedName) {
        String inGame;
        String name = unlocalizedName.substring(5);
        char firstLetter = name.charAt(0);
        if (Character.isLowerCase(firstLetter)) {
            firstLetter = Character.toUpperCase(firstLetter);
        }
        String temp = inGame = name.substring(1);
        for (int p = 1; p < temp.length(); ++p) {
            int c;
            int code = c = inGame.charAt(p);
            if (inGame.charAt(p - 1) == ' ') continue;
            for (int n = 65; n < 90; ++n) {
                if (code != n) continue;
                inGame = new StringBuffer(inGame).insert(p, " ").toString();
            }
        }
        inGame = inGame.replaceAll(" Of ", " of ").replaceAll(" The ", " the ");
        String finalName = "" + firstLetter + inGame;
        this.addToFile(prefixOfLine + "." + name + ".name=" + finalName, name);
    }

    public static String getLocalizedMobName(String str) {
        int k = str.length();
        if (str.length() > 1) {
            for (int i = 1; i < k; ++i) {
                if (!Character.isUpperCase(str.charAt(i)) || str.charAt(i - 1) == ' ') continue;
                str = new StringBuffer(str).insert(i, " ").toString();
            }
        }
        str = str.replace("DRPG", "");
        return str;
    }

    public void addName(String prefix, String keyName, String inGameName) {
        this.addToFile(prefix + '.' + keyName + '=' + inGameName, keyName);
    }

    public String readFile(String path) {
        StringBuilder source = new StringBuilder();
        BufferedReader reader = null;
        File file = new File(path);
        try {
            String line;
            reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                source.append(line);
            }
            reader.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return source.toString();
    }

    public void addToFile(String inGame, String oldName) {
        String temp = inGame;
        LogHelper.dev("Registered new name, " + oldName + " became: " + temp.substring(temp.indexOf(61) + 1));
        this.builder.append(inGame + "\n");
    }

    public void addToFile(String text) {
        this.builder.append(text + "\n");
    }

    public void write() {
        try {
            if (!this.file.exists()) {
                this.file.createNewFile();
            }
            this.writer = new BufferedWriter(new FileWriter(this.file));
            this.writer.write(this.builder.toString());
            this.writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

