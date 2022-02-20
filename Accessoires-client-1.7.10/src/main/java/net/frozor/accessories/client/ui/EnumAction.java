/*
 * Decompiled with CFR 0.152.
 */
package net.frozor.accessories.client.ui;

import net.frozor.accessories.client.item.animation.AbstractAnimation;

public enum EnumAction {
    BACK("Назад"),
    VIEW("Посмотреть"),
    EQUIP("Надеть"),
    TAKEOFF("Снять"),
    PURCHASE(AbstractAnimation.l("\u047b\u0463\u045e\u0418\u0423\u046c")),
    BUY_PROCESS(AbstractAnimation.l("\u047f\u0416\u0459\u0414\u0451\u0419\u0423\u0415O\u000e")),
    CONFIRM(AbstractAnimation.l("\u043f\u045f\u0414\u0423\u0412\u0454\u0460\u0455\u0418\u0423\u046c")),
    CANCEL(AbstractAnimation.l("\u047f\u0462\u045d\u0415\u045c\u0410")),
    NONE("");

    private String name;

    public String getName() {
        return this.name;
    }

    private EnumAction(String str) {
        this.name = str;
    }
}

