/*
 * Decompiled with CFR 0.152.
 */
package com.prototype.extraamulets.common.registry;

import com.prototype.extraamulets.common.ability.Ability;
import com.prototype.extraamulets.common.ability.BypassArmorAbility;
import com.prototype.extraamulets.common.ability.CriticalDamageAbility;
import com.prototype.extraamulets.common.ability.FallingProtectionAbility;
import com.prototype.extraamulets.common.ability.PotionEffectAbility;
import com.prototype.extraamulets.common.ability.ReduceMagicDamageAbility;
import java.util.HashMap;
import java.util.Map;

public final class AbilityRegistry {
    private static final Map<String, Class<? extends Ability>> ABILITIES = new HashMap<String, Class<? extends Ability>>();

    public static void register() {
        ABILITIES.put("potion_effect", PotionEffectAbility.class);
        ABILITIES.put("falling_protection", FallingProtectionAbility.class);
        ABILITIES.put("critical_damage", CriticalDamageAbility.class);
        ABILITIES.put("reduce_magic_damage", ReduceMagicDamageAbility.class);
        ABILITIES.put("bypass_armor", BypassArmorAbility.class);
    }

    public static Class<? extends Ability> getTypeByName(String name) {
        return ABILITIES.get(name);
    }
}

