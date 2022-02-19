/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.Item$ToolMaterial
 */
package net.divinerpg.managers;

import java.util.ArrayList;
import java.util.List;
import net.divinerpg.utils.material.ToolMaterialMod;
import net.minecraft.item.Item;

public class SwordManager {
    private List<String> swords = new ArrayList<String>();

    public void putSwords() {
        this.swords.add(this.getToolMaterialName(ToolMaterialMod.EnderFlame));
        this.swords.add(this.getToolMaterialName(ToolMaterialMod.Dark));
        this.swords.add(this.getToolMaterialName(ToolMaterialMod.Light));
        this.swords.add(this.getToolMaterialName(ToolMaterialMod.Heritage));
        this.swords.add(this.getToolMaterialName(ToolMaterialMod.godlyDagger));
        this.swords.add(this.getToolMaterialName(ToolMaterialMod.everlight));
        this.swords.add(this.getToolMaterialName(ToolMaterialMod.godly));
        this.swords.add(this.getToolMaterialName(ToolMaterialMod.karos));
        this.swords.add(this.getToolMaterialName(ToolMaterialMod.arcana));
        this.swords.add(this.getToolMaterialName(ToolMaterialMod.aoesword));
        this.swords.add(this.getToolMaterialName(ToolMaterialMod.hitheal));
        this.swords.add(this.getToolMaterialName(ToolMaterialMod.fastsword));
        this.swords.add(this.getToolMaterialName(ToolMaterialMod.regensword));
        this.swords.add(this.getToolMaterialName(ToolMaterialMod.premregensword));
        this.swords.add(this.getToolMaterialName(ToolMaterialMod.celestiumSword));
        this.swords.add(this.getToolMaterialName(ToolMaterialMod.premcelestiumSword));
        this.swords.add(this.getToolMaterialName(ToolMaterialMod.netherBeastSword));
        this.swords.add(this.getToolMaterialName(ToolMaterialMod.coreMender));
        this.swords.add(this.getToolMaterialName(ToolMaterialMod.Halite));
        this.swords.add(this.getToolMaterialName(ToolMaterialMod.Frossivence));
        this.swords.add(this.getToolMaterialName(ToolMaterialMod.Massivence));
    }

    public boolean isCanRepair(String toolMaterial) {
        return this.swords.contains(toolMaterial);
    }

    private String getToolMaterialName(Item.ToolMaterial toolMaterial) {
        return toolMaterial.name();
    }
}

