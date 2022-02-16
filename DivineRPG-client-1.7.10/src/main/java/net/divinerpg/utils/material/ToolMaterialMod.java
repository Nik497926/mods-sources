/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.Item$ToolMaterial
 */
package net.divinerpg.utils.material;

import net.divinerpg.utils.Util;
import net.divinerpg.utils.items.IceikaItems;
import net.divinerpg.utils.items.TwilightItemsOther;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class ToolMaterialMod {
    public static Item.ToolMaterial IcicleBane = Util.addMeleeMaterial(14.0f, 15);
    public static Item.ToolMaterial Icine = Util.addMeleeMaterial(22.0f, 15);
    public static Item.ToolMaterial Enderice = Util.addMeleeMaterial(19.0f, 15);
    public static Item.ToolMaterial Snowslash = Util.addMeleeMaterial(150, 25.0f, 15, IceikaItems.snowflake);
    public static Item.ToolMaterial Glacier = Util.addMeleeMaterial(1000, 18.0f, 15, IceikaItems.snowflake);
    public static Item.ToolMaterial Frostking = Util.addMeleeMaterial(6000, 16.0f, 15, IceikaItems.snowflake);
    public static Item.ToolMaterial FrozenMaul = Util.addMeleeMaterial(17.0f, 15);
    public static Item.ToolMaterial Slime = Util.addMeleeMaterial(1000, 11.0f, 22, Items.diamond_sword);
    public static Item.ToolMaterial OceanKnife = Util.addMeleeMaterial(2000, 14.0f, 10, VanillaItemsOther.aquaticIngot);
    public static Item.ToolMaterial JungleKnife = Util.addMeleeMaterial(2000, 12.0f, 10, VanillaItemsOther.jungleStone);
    public static Item.ToolMaterial JungleRapier = Util.addMeleeMaterial(1200, 16.0f, 10, VanillaItemsOther.jungleStone);
    public static Item.ToolMaterial Bedrock = Util.addMeleeMaterial(14000, 14.0f, 22, Item.getItemFromBlock((Block)Blocks.bedrock));
    public static Item.ToolMaterial BedrockMaul = Util.addMeleeMaterial(14.0f, 22);
    public static Item.ToolMaterial Ender = Util.addMeleeMaterial(16.0f, 16);
    public static Item.ToolMaterial Divine = Util.addMeleeMaterial(19.0f, 22);
    public static Item.ToolMaterial AquaMaul = Util.addMeleeMaterial(4000, 18.0f, 10, VanillaItemsOther.aquaticIngot);
    public static Item.ToolMaterial AquaDagger = Util.addMeleeMaterial(2000, 10.0f, 15, VanillaItemsOther.aquaticIngot);
    public static Item.ToolMaterial AquaTrident = Util.addMeleeMaterial(7000, 14.0f, 15, VanillaItemsOther.aquaticIngot);
    public static Item.ToolMaterial Aquaton = Util.addMeleeMaterial(5000, 16.0f, 15, VanillaItemsOther.aquaticIngot);
    public static Item.ToolMaterial Frost = Util.addMeleeMaterial(5000, 10.0f, 15, IceikaItems.iceStone);
    public static Item.ToolMaterial Molten = Util.addMeleeMaterial(5000, 10.0f, 15, VanillaItemsOther.moltenStone);
    public static Item.ToolMaterial Shark = Util.addMeleeMaterial(2000, 14.0f, 15, VanillaItemsOther.aquaticIngot);
    public static Item.ToolMaterial DeathBringer = Util.addMeleeMaterial(6000, 12.0f, 15, VanillaItemsOther.corruptedStone);
    public static Item.ToolMaterial CrabclawMaul = Util.addMeleeMaterial(4000, 11.0f, 15, VanillaItemsOther.crabClaw);
    public static Item.ToolMaterial PoisonSaber = Util.addMeleeMaterial(5000, 16.0f, 15, VanillaItemsOther.jungleStone);
    public static Item.ToolMaterial FuryMaul = Util.addMeleeMaterial(4000, 18.0f, 15, Item.getItemFromBlock((Block)Blocks.bedrock));
    public static Item.ToolMaterial Scorching = Util.addMeleeMaterial(500, 20.0f, 15, VanillaItemsOther.purpleBlaze);
    public static Item.ToolMaterial Bluefire = Util.addMeleeMaterial(3000, 20.0f, 15, VanillaItemsOther.shadowBar);
    public static Item.ToolMaterial CorruptedMaul = Util.addMeleeMaterial(6000, 13.0f, 15, VanillaItemsOther.corruptedStone);
    public static Item.ToolMaterial Halite = Util.addMeleeMaterial(2000, 36.0f, 22, TwilightItemsOther.mortumGem);
    public static Item.ToolMaterial Skythern = Util.addMeleeMaterial(2000, 31.0f, 22, TwilightItemsOther.skythernGem);
    public static Item.ToolMaterial Eden = Util.addMeleeMaterial(2000, 24.0f, 22, TwilightItemsOther.edenGem);
    public static Item.ToolMaterial Wildwood = Util.addMeleeMaterial(2000, 26.0f, 22, TwilightItemsOther.wildwoodGem);
    public static Item.ToolMaterial Apalachia = Util.addMeleeMaterial(2000, 29.0f, 22, TwilightItemsOther.apalachiaGem);
    public static Item.ToolMaterial Mortum = Util.addMeleeMaterial(2000, 33.0f, 22, TwilightItemsOther.mortumGem);
    public static Item.ToolMaterial Arlemite = Util.addMeleeMaterial(4000, 12.0f, 10, VanillaItemsOther.arlemiteIngot);
    public static Item.ToolMaterial Sandslash = Util.addMeleeMaterial(100, 21.0f, 22, VanillaItemsOther.shadowBar);
    public static Item.ToolMaterial Rupee = Util.addMeleeMaterial(2500, 12.0f, 15, VanillaItemsOther.rupeeIngot);
    public static Item.ToolMaterial Inferno = Util.addMeleeMaterial(12.0f, 22);
    public static Item.ToolMaterial Cyclops = Util.addMeleeMaterial(1000, 6.0f, 10, VanillaItemsOther.cyclopsEye);
    public static Item.ToolMaterial Bloodgem = Util.addMeleeMaterial(9.0f, 15);
    public static Item.ToolMaterial Realmite = Util.addMeleeMaterial(4000, 6.0f, 10, VanillaItemsOther.realmiteIngot);
    public static Item.ToolMaterial Storm = Util.addMeleeMaterial(5.0f, 22);
    public static Item.ToolMaterial Saber = Util.addMeleeMaterial(22.0f, 22);
    public static Item.ToolMaterial ShadowSaber = Util.addMeleeMaterial(20.0f, 22);
    public static Item.ToolMaterial LiviciaSword = Util.addMeleeMaterial(10000, 26.0f, 22, VanillaItemsOther.shadowStone);
    public static Item.ToolMaterial Palavence = Util.addMeleeMaterial(60, 1.0f, 0, null);
    public static Item.ToolMaterial Frossivence = Util.addMeleeMaterial(270, 1.0f, 0, IceikaItems.snowflake);
    public static Item.ToolMaterial FlamingFury = Util.addMeleeMaterial(6000, 26.0f, 15, VanillaItemsOther.moltenStone);
    public static Item.ToolMaterial ArcaniteBlade = Util.addMeleeMaterial(7000, 34.0f, 15, null);
    public static Item.ToolMaterial DualClaw = Util.addMeleeMaterial(4000, 12.0f, 15, VanillaItemsOther.crabClaw);
    public static Item.ToolMaterial TerranDagger = Util.addMeleeMaterial(350, 13.0f, 10, VanillaItemsOther.terranStone);
    public static Item.ToolMaterial TerranKnife = Util.addMeleeMaterial(750, 12.0f, 10, VanillaItemsOther.terranStone);
    public static Item.ToolMaterial TerranMaul = Util.addMeleeMaterial(1200, 15.0f, 10, VanillaItemsOther.terranStone);
    public static Item.ToolMaterial AquatoothSword = Util.addMeleeMaterial(3500, 15.0f, 10, VanillaItemsOther.liopleurodonSkull);
    public static Item.ToolMaterial AquatoothMaul = Util.addMeleeMaterial(3500, 18.0f, 10, VanillaItemsOther.liopleurodonSkull);
    public static Item.ToolMaterial RupeeShick = Util.addShickMaterial(6, 16.0f, 8.0f, 15);
    public static Item.ToolMaterial ArlemiteShick = Util.addShickMaterial(6, 12000, 13.0f, 8.0f, 15, VanillaItemsOther.arlemiteIngot);
    public static Item.ToolMaterial DivineShick = Util.addShickMaterial(8, 20.0f, 9.0f, 10);
    public static Item.ToolMaterial RupeeAxe = Util.addAxeMaterial(6, 2500, 16.0f, 7.0f, 0, VanillaItemsOther.rupeeIngot);
    public static Item.ToolMaterial ArlemiteAxe = Util.addAxeMaterial(3, 3000, 13.0f, 7.0f, 10, VanillaItemsOther.arlemiteIngot);
    public static Item.ToolMaterial RealmiteAxe = Util.addAxeMaterial(3, 4000, 12.0f, 6.0f, 10, VanillaItemsOther.realmiteIngot);
    public static Item.ToolMaterial CyclopsAxe = Util.addAxeMaterial(0, 1000, 8.0f, 6.0f, 10, VanillaItemsOther.cyclopsEye);
    public static Item.ToolMaterial BedrockAxe = Util.addAxeMaterial(3, 20.0f, 6.0f, 10);
    public static Item.ToolMaterial CorruptedAxe = Util.addAxeMaterial(3, 6000, 16.0f, 7.0f, 10, VanillaItemsOther.corruptedStone);
    public static Item.ToolMaterial SkythernAxe = Util.addAxeMaterial(100, 6000, 48.0f, 10.0f, 22, TwilightItemsOther.skythernGem);
    public static Item.ToolMaterial EdenAxe = Util.addAxeMaterial(100, 6000, 20.0f, 7.0f, 22, TwilightItemsOther.edenGem);
    public static Item.ToolMaterial WildwoodAxe = Util.addAxeMaterial(100, 6000, 28.0f, 8.0f, 22, TwilightItemsOther.wildwoodGem);
    public static Item.ToolMaterial ApalachiaAxe = Util.addAxeMaterial(100, 6000, 40.0f, 9.0f, 22, TwilightItemsOther.apalachiaGem);
    public static Item.ToolMaterial MortumAxe = Util.addAxeMaterial(100, 6000, 55.0f, 11.0f, 22, TwilightItemsOther.mortumGem);
    public static Item.ToolMaterial DivineAxe = Util.addAxeMaterial(8, 35000, 20.0f, 8.0f, 10, VanillaItemsOther.divineStone);
    public static Item.ToolMaterial RupeePick = Util.addPickMaterial(6, 2500, 16.0f, 5.0f, 0, VanillaItemsOther.rupeeIngot);
    public static Item.ToolMaterial ArlemitePick = Util.addPickMaterial(3, 3000, 13.0f, 5.0f, 10, VanillaItemsOther.arlemiteIngot);
    public static Item.ToolMaterial RealmitePick = Util.addPickMaterial(3, 4000, 12.0f, 4.0f, 10, VanillaItemsOther.realmiteIngot);
    public static Item.ToolMaterial CyclopsPick = Util.addPickMaterial(0, 1000, 8.0f, 4.0f, 10, VanillaItemsOther.cyclopsEye);
    public static Item.ToolMaterial BedrockPick = Util.addPickMaterial(3, 9.0f, 4.0f, 10);
    public static Item.ToolMaterial CorruptedPick = Util.addPickMaterial(3, 6000, 16.0f, 5.0f, 10, VanillaItemsOther.corruptedStone);
    public static Item.ToolMaterial SkythernPick = Util.addPickMaterial(100, 6000, 48.0f, 8.0f, 22, TwilightItemsOther.skythernGem);
    public static Item.ToolMaterial EdenPick = Util.addPickMaterial(100, 6000, 20.0f, 5.0f, 22, TwilightItemsOther.edenGem);
    public static Item.ToolMaterial WildwoodPick = Util.addPickMaterial(100, 6000, 28.0f, 6.0f, 22, TwilightItemsOther.wildwoodGem);
    public static Item.ToolMaterial ApalachiaPick = Util.addPickMaterial(100, 6000, 40.0f, 7.0f, 22, TwilightItemsOther.apalachiaGem);
    public static Item.ToolMaterial MortumPick = Util.addPickMaterial(100, 6000, 55.0f, 9.0f, 22, TwilightItemsOther.mortumGem);
    public static Item.ToolMaterial DivinePick = Util.addPickMaterial(8, 35000, 20.0f, 6.0f, 10, VanillaItemsOther.divineStone);
    public static Item.ToolMaterial RupeeSpade = Util.addSpadeMaterial(6, 2500, 16.0f, 5.0f, 0, VanillaItemsOther.rupeeIngot);
    public static Item.ToolMaterial ArlemiteSpade = Util.addSpadeMaterial(3, 3000, 13.0f, 5.0f, 10, VanillaItemsOther.arlemiteIngot);
    public static Item.ToolMaterial RealmiteSpade = Util.addSpadeMaterial(3, 4000, 12.0f, 4.0f, 10, VanillaItemsOther.realmiteIngot);
    public static Item.ToolMaterial CyclopsSpade = Util.addSpadeMaterial(0, 1000, 8.0f, 4.0f, 10, VanillaItemsOther.cyclopsEye);
    public static Item.ToolMaterial BedrockSpade = Util.addSpadeMaterial(3, 20.0f, 4.0f, 10);
    public static Item.ToolMaterial CorruptedSpade = Util.addSpadeMaterial(3, 6000, 16.0f, 5.0f, 10, VanillaItemsOther.corruptedStone);
    public static Item.ToolMaterial SkythernSpade = Util.addSpadeMaterial(100, 6000, 48.0f, 8.0f, 22, TwilightItemsOther.skythernGem);
    public static Item.ToolMaterial EdenSpade = Util.addSpadeMaterial(100, 6000, 20.0f, 5.0f, 22, TwilightItemsOther.edenGem);
    public static Item.ToolMaterial WildwoodSpade = Util.addSpadeMaterial(100, 6000, 28.0f, 6.0f, 22, TwilightItemsOther.wildwoodGem);
    public static Item.ToolMaterial ApalachiaSpade = Util.addSpadeMaterial(100, 6000, 40.0f, 7.0f, 22, TwilightItemsOther.apalachiaGem);
    public static Item.ToolMaterial MortumSpade = Util.addSpadeMaterial(100, 6000, 55.0f, 9.0f, 22, TwilightItemsOther.mortumGem);
    public static Item.ToolMaterial DivineSpade = Util.addSpadeMaterial(8, 35000, 20.0f, 6.0f, 10, VanillaItemsOther.divineStone);
}

