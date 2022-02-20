/*
 * Decompiled with CFR 0.152.
 */
package net.frozor.accessories.client.ui;

public enum EnumAction {
    BACK("Назад"),
    VIEW("Посмотреть"),
    EQUIP("Надеть"),
    TAKEOFF("Снять"),
    PURCHASE("Купить"),
    BUY_PROCESS("Ожидайте.."),
    CONFIRM("Подтвердить"),
    CANCEL("Отмена"),
    NONE("");

    private String name;

    public String getName() {
        return this.name;
    }

    private EnumAction(String str) {
        this.name = str;
    }
}

