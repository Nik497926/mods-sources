/*
 * Decompiled with CFR 0.152.
 */
package net.divinerpg.items.base;

import net.divinerpg.items.base.ItemDivineArmor;
import net.divinerpg.utils.material.EnumArmor;

public class ItemSpeedArmor
extends ItemDivineArmor {
    public float speed;

    public ItemSpeedArmor(EnumArmor armorMaterial, int type, Object[] info, float speed) {
        super(armorMaterial, type, info);
        this.speed = speed;
    }
}

