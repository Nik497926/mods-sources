/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.potion;

import com.meteor.extrabotany.common.potion.PotionCure;
import com.meteor.extrabotany.common.potion.PotionDurance;
import com.meteor.extrabotany.common.potion.PotionFastParticleSorting;
import com.meteor.extrabotany.common.potion.PotionSlowParticleSorting;
import java.lang.reflect.Field;
import net.minecraft.potion.Potion;

public class ModPotions {
    public static Potion fastparticlesorting;
    public static Potion slowparticlesorting;
    public static Potion residualpain;
    public static Potion cure;
    public static Potion durance;

    public static void init() {
        if (Potion.potionTypes.length < 256) {
            ModPotions.extendPotionArray();
        }
        fastparticlesorting = new PotionFastParticleSorting();
        slowparticlesorting = new PotionSlowParticleSorting();
        cure = new PotionCure();
        durance = new PotionDurance();
    }

    private static void extendPotionArray() {
        Potion[] potionTypes = null;
        for (Field f : Potion.class.getDeclaredFields()) {
            f.setAccessible(true);
            try {
                if (!f.getName().equals("potionTypes") && !f.getName().equals("potionTypes")) continue;
                Field e = Field.class.getDeclaredField("modifiers");
                e.setAccessible(true);
                e.setInt(f, f.getModifiers() & 0xFFFFFFEF);
                potionTypes = (Potion[])f.get(null);
                Potion[] newPotionTypes = new Potion[256];
                System.arraycopy(potionTypes, 0, newPotionTypes, 0, potionTypes.length);
                f.set(null, newPotionTypes);
            }
            catch (Exception var7) {
                System.err.println("Severe error, please report this to the mod author:");
                System.err.println(var7);
            }
        }
    }
}

