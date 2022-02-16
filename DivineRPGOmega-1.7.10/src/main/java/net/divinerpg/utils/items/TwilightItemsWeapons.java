/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.Item
 */
package net.divinerpg.utils.items;

import net.divinerpg.client.render.EntityResourceLocation;
import net.divinerpg.items.base.ItemMod;
import net.divinerpg.items.base.ItemModBow;
import net.divinerpg.items.base.ItemModSword;
import net.divinerpg.items.base.ItemProjectileShooter;
import net.divinerpg.items.base.ItemThrowable;
import net.divinerpg.items.twilight.ItemTwilightBlitz;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.items.TwilightItemsOther;
import net.divinerpg.utils.material.ToolMaterialMod;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.item.Item;

public class TwilightItemsWeapons {
    private static EntityResourceLocation x;
    public static Item edenBlade;
    public static Item wildwoodBlade;
    public static Item apalachiaBlade;
    public static Item skythernBlade;
    public static Item mortumBlade;
    public static Item haliteBlade;
    public static Item edenSlicer;
    public static Item wildwoodSlicer;
    public static Item apalachiaSlicer;
    public static Item skythernSlicer;
    public static Item mortumSlicer;
    public static Item haliteSlicer;
    public static Item edenBlitz;
    public static Item wildwoodBlitz;
    public static Item apalachiaBlitz;
    public static Item skythernBlitz;
    public static Item mortumBlitz;
    public static Item haliteBlitz;
    public static Item edenPhaser;
    public static Item wildwoodPhaser;
    public static Item apalachiaPhaser;
    public static Item skythernPhaser;
    public static Item mortumPhaser;
    public static Item halitePhaser;
    public static Item edenArrow;
    public static Item wildwoodArrow;
    public static Item furyArrow;
    public static Item edenBow;
    public static Item wildwoodBow;
    public static Item apalachiaBow;
    public static Item skythernBow;
    public static Item mortumBow;
    public static Item haliteBow;
    public static Item twilightBow;

    public static void init() {
    }

    static {
        edenBlade = new ItemModSword(ToolMaterialMod.Eden, "edenBlade");
        wildwoodBlade = new ItemModSword(ToolMaterialMod.Wildwood, "wildwoodBlade");
        apalachiaBlade = new ItemModSword(ToolMaterialMod.Apalachia, "apalachiaBlade");
        skythernBlade = new ItemModSword(ToolMaterialMod.Skythern, "skythernBlade");
        mortumBlade = new ItemModSword(ToolMaterialMod.Mortum, "mortumBlade");
        haliteBlade = new ItemModSword(ToolMaterialMod.Halite, "haliteBlade");
        edenSlicer = new ItemThrowable("edenSlicer", 8.0f);
        wildwoodSlicer = new ItemThrowable("wildwoodSlicer", 10.0f);
        apalachiaSlicer = new ItemThrowable("apalachiaSlicer", 12.0f);
        skythernSlicer = new ItemThrowable("skythernSlicer", 14.0f);
        mortumSlicer = new ItemThrowable("mortumSlicer", 16.0f);
        haliteSlicer = new ItemThrowable("haliteSlicer", 22.0f);
        edenBlitz = new ItemTwilightBlitz("edenBlitz", EntityResourceLocation.blitzEden.toString(), TwilightItemsOther.edenDust, 10.0f).setHasParticle("eden");
        wildwoodBlitz = new ItemTwilightBlitz("wildwoodBlitz", EntityResourceLocation.blitzWild.toString(), TwilightItemsOther.wildwoodDust, 12.0f).setHasParticle("wildwood");
        apalachiaBlitz = new ItemTwilightBlitz("apalachiaBlitz", EntityResourceLocation.blitzApalachia.toString(), TwilightItemsOther.apalachiaDust, 14.0f).setHasParticle("apalachia");
        skythernBlitz = new ItemTwilightBlitz("skythernBlitz", EntityResourceLocation.blitzSkythern.toString(), TwilightItemsOther.skythernDust, 16.0f).setHasParticle("skythern");
        mortumBlitz = new ItemTwilightBlitz("mortumBlitz", EntityResourceLocation.blitzMortum.toString(), TwilightItemsOther.mortumDust, 18.0f).setHasParticle("mortum");
        haliteBlitz = new ItemTwilightBlitz("haliteBlitz", EntityResourceLocation.blitzHalite.toString(), TwilightItemsOther.mortumDust, 20.0f).setHasParticle("halite");
        edenPhaser = new ItemProjectileShooter("edenPhaser", 14.0f, Sounds.phaser.getPrefixedName(), EntityResourceLocation.phaserEden.toString(), 3000, 3).setHasParticle("eden");
        wildwoodPhaser = new ItemProjectileShooter("wildwoodPhaser", 17.0f, Sounds.phaser.getPrefixedName(), EntityResourceLocation.phaserWild.toString(), 3000, 3).setHasParticle("wildwood");
        apalachiaPhaser = new ItemProjectileShooter("apalachiaPhaser", 20.0f, Sounds.phaser.getPrefixedName(), EntityResourceLocation.phaserApalachia.toString(), 3000, 3).setHasParticle("apalachia");
        skythernPhaser = new ItemProjectileShooter("skythernPhaser", 23.0f, Sounds.phaser.getPrefixedName(), EntityResourceLocation.phaserSkythern.toString(), 3000, 3).setHasParticle("skythern");
        mortumPhaser = new ItemProjectileShooter("mortumPhaser", 26.0f, Sounds.phaser.getPrefixedName(), EntityResourceLocation.phaserMortum.toString(), 3000, 3).setHasParticle("mortum");
        halitePhaser = new ItemProjectileShooter("halitePhaser", 29.0f, Sounds.phaser.getPrefixedName(), EntityResourceLocation.phaserHalite.toString(), 3000, 3).setHasParticle("halite");
        edenArrow = new ItemMod("edenArrow").setCreativeTab(DivineRPGTabs.ranged);
        wildwoodArrow = new ItemMod("wildwoodArrow").setCreativeTab(DivineRPGTabs.ranged);
        furyArrow = new ItemMod("furyArrow").setCreativeTab(DivineRPGTabs.ranged);
        edenBow = new ItemModBow("edenBow", -1, 10, 20, edenArrow);
        wildwoodBow = new ItemModBow("wildwoodBow", -1, 13, 23, 36000, wildwoodArrow);
        apalachiaBow = new ItemModBow("apalachiaBow", -1, 12, 22, wildwoodArrow);
        skythernBow = new ItemModBow("skythernBow", -1, 16, 26, 36000, wildwoodArrow);
        mortumBow = new ItemModBow("mortumBow", -1, 18, 29, furyArrow);
        haliteBow = new ItemModBow("haliteBow", -1, 20, 32, 36000, furyArrow);
        twilightBow = new ItemModBow("twilightBow", -1, 20, 36, 14400, furyArrow).setCreativeTab(null);
    }
}

