/*
 * Decompiled with CFR 0.152.
 */
package com.prototype.extraamulets.common.ability;

import com.prototype.extraamulets.common.ability.Ability;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;

public final class Abilities {
    private final List<Ability> abilities;

    public Abilities(List<Ability> abilities) {
        this.abilities = abilities;
    }

    public void addInformation(EntityPlayer player, List<String> tooltip) {
        this.abilities.forEach(ability -> ability.addInformation(player, tooltip));
    }

    public void onEquipped(EntityPlayer player) {
        this.abilities.forEach(ability -> ability.onEquipped(player));
    }

    public void onUnequipped(EntityPlayer player) {
        this.abilities.forEach(ability -> ability.onUnequipped(player));
    }

    public <T extends Ability> T getAbility(Class<? extends Ability> clazz) {
        for (Ability ability : this.abilities) {
            if (clazz != ability.getClass()) continue;
            return (T)ability;
        }
        return null;
    }

    public List<Ability> getAbilities() {
        return this.abilities;
    }
}

