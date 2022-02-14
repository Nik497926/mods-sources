/*
 * Decompiled with CFR 0.152.
 */
package net.divinerpg.utils;

import java.util.ArrayList;
import net.divinerpg.utils.RegistryFile;
import net.divinerpg.utils.Sound;

public class SoundGenerator
extends RegistryFile {
    private static ArrayList<Sound> sounds = new ArrayList();
    private static final RegistryFile instance = new SoundGenerator();

    public SoundGenerator() {
        super("sounds.json");
    }

    public static void addSound(Sound sound) {
        sounds.add(sound);
    }

    public static void registerSounds() {
        instance.addNames();
        instance.write();
    }

    @Override
    public void addNames() {
        this.addToFile("{");
        for (int i = 0; i < sounds.size(); ++i) {
            String name = sounds.get(i).getName();
            boolean mob = sounds.get(i).isMob();
            boolean item = sounds.get(i).isItem();
            if (!item) {
                if (mob) {
                    name = name + "Hurt";
                }
                this.addToFile(" \"" + name + "\": {\n  \"category\":\"master\",\"sounds\": [{\n    \"name\":\n      \"" + name + "\",\n      \"stream\": false\n    }]\n  }");
            } else {
                this.addToFile(" \"" + name + "\": {\n  \"category\":\"neutral\",\"sounds\": [\"" + name + "\"] }");
            }
            if (i != sounds.size() - 1) {
                this.addToFile(",");
            }
            this.addToFile("\n");
        }
        this.addToFile("}");
    }
}

