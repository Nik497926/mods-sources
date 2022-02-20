/*
 * Decompiled with CFR 0.152.
 */
package net.frozor.accessories.client.item;

public enum CategoryType {
    HEAD("Голова"),
    FACE("Лицо"),
    BODY("Тело"),
    FAMILIAR("Питомец");

    private String name;

    private CategoryType(String type) {
        this.name = type;
    }

    public String getName() {
        return this.name;
    }
}

