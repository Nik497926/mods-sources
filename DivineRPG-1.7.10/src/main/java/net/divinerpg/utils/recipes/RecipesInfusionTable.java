/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 */
package net.divinerpg.utils.recipes;

import java.util.HashMap;
import net.divinerpg.utils.items.VetheaItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RecipesInfusionTable {
    public static HashMap<InfusionRecipe, Item> recipes = new HashMap();

    public RecipesInfusionTable() {
        RecipesInfusionTable.addRecipes();
    }

    public static void addRecipes() {
        RecipesInfusionTable.addRecipe(VetheaItems.teakerLump, VetheaItems.bowTemplate, 4, new ItemStack(VetheaItems.teakerBow));
        RecipesInfusionTable.addRecipe(VetheaItems.amthirmisLump, VetheaItems.bowTemplate, 5, new ItemStack(VetheaItems.amthrimisBow));
        RecipesInfusionTable.addRecipe(VetheaItems.darvenLump, VetheaItems.bowTemplate, 6, new ItemStack(VetheaItems.darvenBow));
        RecipesInfusionTable.addRecipe(VetheaItems.cermileLump, VetheaItems.bowTemplate, 8, new ItemStack(VetheaItems.cermileBow));
        RecipesInfusionTable.addRecipe(VetheaItems.pardimalLump, VetheaItems.bowTemplate, 11, new ItemStack(VetheaItems.pardimalBow));
        RecipesInfusionTable.addRecipe(VetheaItems.quadroticLump, VetheaItems.bowTemplate, 13, new ItemStack(VetheaItems.quadroticBow));
        RecipesInfusionTable.addRecipe(VetheaItems.karosLump, VetheaItems.bowTemplate, 18, new ItemStack(VetheaItems.karosBow));
        RecipesInfusionTable.addRecipe(VetheaItems.heliosisLump, VetheaItems.bowTemplate, 20, new ItemStack(VetheaItems.heliosisBow));
        RecipesInfusionTable.addRecipe(VetheaItems.arksianeLump, VetheaItems.bowTemplate, 24, new ItemStack(VetheaItems.arksianeBow));
        RecipesInfusionTable.addRecipe(VetheaItems.teakerLump, VetheaItems.clawTemplate, 2, new ItemStack(VetheaItems.teakerClaw));
        RecipesInfusionTable.addRecipe(VetheaItems.amthirmisLump, VetheaItems.clawTemplate, 3, new ItemStack(VetheaItems.amthrimisClaw));
        RecipesInfusionTable.addRecipe(VetheaItems.darvenLump, VetheaItems.clawTemplate, 4, new ItemStack(VetheaItems.darvenClaw));
        RecipesInfusionTable.addRecipe(VetheaItems.cermileLump, VetheaItems.clawTemplate, 6, new ItemStack(VetheaItems.cermileClaw));
        RecipesInfusionTable.addRecipe(VetheaItems.pardimalLump, VetheaItems.clawTemplate, 8, new ItemStack(VetheaItems.pardimalClaw));
        RecipesInfusionTable.addRecipe(VetheaItems.quadroticLump, VetheaItems.clawTemplate, 10, new ItemStack(VetheaItems.quadroticClaw));
        RecipesInfusionTable.addRecipe(VetheaItems.karosLump, VetheaItems.clawTemplate, 16, new ItemStack(VetheaItems.karosClaw));
        RecipesInfusionTable.addRecipe(VetheaItems.heliosisLump, VetheaItems.clawTemplate, 17, new ItemStack(VetheaItems.heliosisClaw));
        RecipesInfusionTable.addRecipe(VetheaItems.arksianeLump, VetheaItems.clawTemplate, 21, new ItemStack(VetheaItems.arksianeClaw));
        RecipesInfusionTable.addRecipe(VetheaItems.teakerLump, VetheaItems.staffTemplate, 2, new ItemStack(VetheaItems.teakerStaff));
        RecipesInfusionTable.addRecipe(VetheaItems.amthirmisLump, VetheaItems.staffTemplate, 3, new ItemStack(VetheaItems.amthrimisStaff));
        RecipesInfusionTable.addRecipe(VetheaItems.darvenLump, VetheaItems.staffTemplate, 4, new ItemStack(VetheaItems.darvenStaff));
        RecipesInfusionTable.addRecipe(VetheaItems.cermileLump, VetheaItems.staffTemplate, 6, new ItemStack(VetheaItems.cermileStaff));
        RecipesInfusionTable.addRecipe(VetheaItems.pardimalLump, VetheaItems.staffTemplate, 8, new ItemStack(VetheaItems.pardimalStaff));
        RecipesInfusionTable.addRecipe(VetheaItems.quadroticLump, VetheaItems.staffTemplate, 10, new ItemStack(VetheaItems.quadroticStaff));
        RecipesInfusionTable.addRecipe(VetheaItems.karosLump, VetheaItems.staffTemplate, 16, new ItemStack(VetheaItems.karosStaff));
        RecipesInfusionTable.addRecipe(VetheaItems.heliosisLump, VetheaItems.staffTemplate, 17, new ItemStack(VetheaItems.heliosisStaff));
        RecipesInfusionTable.addRecipe(VetheaItems.arksianeLump, VetheaItems.staffTemplate, 21, new ItemStack(VetheaItems.arksianeStaff));
        RecipesInfusionTable.addRecipe(VetheaItems.teakerLump, VetheaItems.diskTemplate, 4, new ItemStack(VetheaItems.teakerDisk));
        RecipesInfusionTable.addRecipe(VetheaItems.amthirmisLump, VetheaItems.diskTemplate, 5, new ItemStack(VetheaItems.amthrimisDisk));
        RecipesInfusionTable.addRecipe(VetheaItems.darvenLump, VetheaItems.diskTemplate, 6, new ItemStack(VetheaItems.darvenDisk));
        RecipesInfusionTable.addRecipe(VetheaItems.cermileLump, VetheaItems.diskTemplate, 8, new ItemStack(VetheaItems.cermileDisk));
        RecipesInfusionTable.addRecipe(VetheaItems.pardimalLump, VetheaItems.diskTemplate, 11, new ItemStack(VetheaItems.pardimalDisk));
        RecipesInfusionTable.addRecipe(VetheaItems.quadroticLump, VetheaItems.diskTemplate, 13, new ItemStack(VetheaItems.quadroticDisk));
        RecipesInfusionTable.addRecipe(VetheaItems.karosLump, VetheaItems.diskTemplate, 18, new ItemStack(VetheaItems.karosDisk));
        RecipesInfusionTable.addRecipe(VetheaItems.heliosisLump, VetheaItems.diskTemplate, 20, new ItemStack(VetheaItems.heliosisDisk));
        RecipesInfusionTable.addRecipe(VetheaItems.arksianeLump, VetheaItems.diskTemplate, 24, new ItemStack(VetheaItems.arksianeDisk));
        RecipesInfusionTable.addRecipe(VetheaItems.teakerLump, VetheaItems.backswordTemplate, 4, new ItemStack(VetheaItems.teakerSword));
        RecipesInfusionTable.addRecipe(VetheaItems.amthirmisLump, VetheaItems.backswordTemplate, 5, new ItemStack(VetheaItems.amthrimisSword));
        RecipesInfusionTable.addRecipe(VetheaItems.darvenLump, VetheaItems.backswordTemplate, 6, new ItemStack(VetheaItems.darvenSword));
        RecipesInfusionTable.addRecipe(VetheaItems.cermileLump, VetheaItems.backswordTemplate, 8, new ItemStack(VetheaItems.cermileSword));
        RecipesInfusionTable.addRecipe(VetheaItems.pardimalLump, VetheaItems.backswordTemplate, 11, new ItemStack(VetheaItems.pardimalSword));
        RecipesInfusionTable.addRecipe(VetheaItems.quadroticLump, VetheaItems.backswordTemplate, 13, new ItemStack(VetheaItems.quadroticSword));
        RecipesInfusionTable.addRecipe(VetheaItems.karosLump, VetheaItems.backswordTemplate, 18, new ItemStack(VetheaItems.karosSword));
        RecipesInfusionTable.addRecipe(VetheaItems.heliosisLump, VetheaItems.backswordTemplate, 20, new ItemStack(VetheaItems.heliosisSword));
        RecipesInfusionTable.addRecipe(VetheaItems.arksianeLump, VetheaItems.backswordTemplate, 24, new ItemStack(VetheaItems.arksianeSword));
        RecipesInfusionTable.addRecipe(VetheaItems.teakerLump, VetheaItems.hammerTemplate, 7, new ItemStack(VetheaItems.teakerHammer));
        RecipesInfusionTable.addRecipe(VetheaItems.amthirmisLump, VetheaItems.hammerTemplate, 9, new ItemStack(VetheaItems.amthrimisHammer));
        RecipesInfusionTable.addRecipe(VetheaItems.darvenLump, VetheaItems.hammerTemplate, 11, new ItemStack(VetheaItems.darvenHammer));
        RecipesInfusionTable.addRecipe(VetheaItems.cermileLump, VetheaItems.hammerTemplate, 14, new ItemStack(VetheaItems.cermileHammer));
        RecipesInfusionTable.addRecipe(VetheaItems.pardimalLump, VetheaItems.hammerTemplate, 17, new ItemStack(VetheaItems.pardimalHammer));
        RecipesInfusionTable.addRecipe(VetheaItems.quadroticLump, VetheaItems.hammerTemplate, 19, new ItemStack(VetheaItems.quadroticHammer));
        RecipesInfusionTable.addRecipe(VetheaItems.karosLump, VetheaItems.hammerTemplate, 22, new ItemStack(VetheaItems.karosHammer));
        RecipesInfusionTable.addRecipe(VetheaItems.heliosisLump, VetheaItems.hammerTemplate, 27, new ItemStack(VetheaItems.heliosisHammer));
        RecipesInfusionTable.addRecipe(VetheaItems.arksianeLump, VetheaItems.hammerTemplate, 31, new ItemStack(VetheaItems.arksianeHammer));
        RecipesInfusionTable.addRecipe(VetheaItems.teakerLump, VetheaItems.cannonTemplate, 7, new ItemStack(VetheaItems.teakerCannon));
        RecipesInfusionTable.addRecipe(VetheaItems.amthirmisLump, VetheaItems.cannonTemplate, 9, new ItemStack(VetheaItems.amthrimisCannon));
        RecipesInfusionTable.addRecipe(VetheaItems.darvenLump, VetheaItems.cannonTemplate, 11, new ItemStack(VetheaItems.darvenCannon));
        RecipesInfusionTable.addRecipe(VetheaItems.cermileLump, VetheaItems.cannonTemplate, 14, new ItemStack(VetheaItems.cermileCannon));
        RecipesInfusionTable.addRecipe(VetheaItems.pardimalLump, VetheaItems.cannonTemplate, 17, new ItemStack(VetheaItems.pardimalCannon));
        RecipesInfusionTable.addRecipe(VetheaItems.quadroticLump, VetheaItems.cannonTemplate, 19, new ItemStack(VetheaItems.quadroticCannon));
        RecipesInfusionTable.addRecipe(VetheaItems.karosLump, VetheaItems.cannonTemplate, 22, new ItemStack(VetheaItems.karosCannon));
        RecipesInfusionTable.addRecipe(VetheaItems.heliosisLump, VetheaItems.cannonTemplate, 27, new ItemStack(VetheaItems.heliosisCannon));
        RecipesInfusionTable.addRecipe(VetheaItems.arksianeLump, VetheaItems.cannonTemplate, 31, new ItemStack(VetheaItems.arksianeCannon));
        RecipesInfusionTable.addRecipe(VetheaItems.teakerLump, VetheaItems.degradedTemplate, 7, new ItemStack(VetheaItems.degradedHelmet));
        RecipesInfusionTable.addRecipe(VetheaItems.teakerLump, VetheaItems.degradedTemplate, 8, new ItemStack(VetheaItems.degradedHood));
        RecipesInfusionTable.addRecipe(VetheaItems.teakerLump, VetheaItems.degradedTemplate, 6, new ItemStack(VetheaItems.degradedMask));
        RecipesInfusionTable.addRecipe(VetheaItems.teakerLump, VetheaItems.degradedTemplate, 5, new ItemStack(VetheaItems.degradedBoots));
        RecipesInfusionTable.addRecipe(VetheaItems.teakerLump, VetheaItems.degradedTemplate, 9, new ItemStack(VetheaItems.degradedLegs));
        RecipesInfusionTable.addRecipe(VetheaItems.teakerLump, VetheaItems.degradedTemplate, 10, new ItemStack(VetheaItems.degradedBody));
        RecipesInfusionTable.addRecipe(VetheaItems.amthirmisLump, VetheaItems.finishedTemplate, 10, new ItemStack(VetheaItems.finishedHelmet));
        RecipesInfusionTable.addRecipe(VetheaItems.amthirmisLump, VetheaItems.finishedTemplate, 11, new ItemStack(VetheaItems.finishedHood));
        RecipesInfusionTable.addRecipe(VetheaItems.amthirmisLump, VetheaItems.finishedTemplate, 9, new ItemStack(VetheaItems.finishedMask));
        RecipesInfusionTable.addRecipe(VetheaItems.amthirmisLump, VetheaItems.finishedTemplate, 8, new ItemStack(VetheaItems.finishedBoots));
        RecipesInfusionTable.addRecipe(VetheaItems.amthirmisLump, VetheaItems.finishedTemplate, 12, new ItemStack(VetheaItems.finishedLegs));
        RecipesInfusionTable.addRecipe(VetheaItems.amthirmisLump, VetheaItems.finishedTemplate, 14, new ItemStack(VetheaItems.finishedBody));
        RecipesInfusionTable.addRecipe(VetheaItems.darvenLump, VetheaItems.glisteningTemplate, 10, new ItemStack(VetheaItems.glisteningHelmet));
        RecipesInfusionTable.addRecipe(VetheaItems.darvenLump, VetheaItems.glisteningTemplate, 11, new ItemStack(VetheaItems.glisteningHood));
        RecipesInfusionTable.addRecipe(VetheaItems.darvenLump, VetheaItems.glisteningTemplate, 9, new ItemStack(VetheaItems.glisteningMask));
        RecipesInfusionTable.addRecipe(VetheaItems.darvenLump, VetheaItems.glisteningTemplate, 8, new ItemStack(VetheaItems.glisteningBoots));
        RecipesInfusionTable.addRecipe(VetheaItems.darvenLump, VetheaItems.glisteningTemplate, 12, new ItemStack(VetheaItems.glisteningLegs));
        RecipesInfusionTable.addRecipe(VetheaItems.darvenLump, VetheaItems.glisteningTemplate, 14, new ItemStack(VetheaItems.glisteningBody));
        RecipesInfusionTable.addRecipe(VetheaItems.cermileLump, VetheaItems.demonizedTemplate, 10, new ItemStack(VetheaItems.demonizedHelmet));
        RecipesInfusionTable.addRecipe(VetheaItems.cermileLump, VetheaItems.demonizedTemplate, 11, new ItemStack(VetheaItems.demonizedHood));
        RecipesInfusionTable.addRecipe(VetheaItems.cermileLump, VetheaItems.demonizedTemplate, 9, new ItemStack(VetheaItems.demonizedMask));
        RecipesInfusionTable.addRecipe(VetheaItems.cermileLump, VetheaItems.demonizedTemplate, 8, new ItemStack(VetheaItems.demonizedBoots));
        RecipesInfusionTable.addRecipe(VetheaItems.cermileLump, VetheaItems.demonizedTemplate, 12, new ItemStack(VetheaItems.demonizedLegs));
        RecipesInfusionTable.addRecipe(VetheaItems.cermileLump, VetheaItems.demonizedTemplate, 14, new ItemStack(VetheaItems.demonizedBody));
        RecipesInfusionTable.addRecipe(VetheaItems.pardimalLump, VetheaItems.tormentedTemplate, 10, new ItemStack(VetheaItems.tormentedHelmet));
        RecipesInfusionTable.addRecipe(VetheaItems.pardimalLump, VetheaItems.tormentedTemplate, 11, new ItemStack(VetheaItems.tormentedHood));
        RecipesInfusionTable.addRecipe(VetheaItems.pardimalLump, VetheaItems.tormentedTemplate, 9, new ItemStack(VetheaItems.tormentedMask));
        RecipesInfusionTable.addRecipe(VetheaItems.pardimalLump, VetheaItems.tormentedTemplate, 8, new ItemStack(VetheaItems.tormentedBoots));
        RecipesInfusionTable.addRecipe(VetheaItems.pardimalLump, VetheaItems.tormentedTemplate, 12, new ItemStack(VetheaItems.tormentedLegs));
        RecipesInfusionTable.addRecipe(VetheaItems.pardimalLump, VetheaItems.tormentedTemplate, 14, new ItemStack(VetheaItems.tormentedBody));
    }

    public static void addRecipe(Item lump, Item template, int count, ItemStack res) {
        recipes.put(new InfusionRecipe(lump, template, count), res.getItem());
    }

    public static Item getOutput(Item lump, Item template, int count) {
        if (recipes.containsKey(new InfusionRecipe(lump, template, count))) {
            return recipes.get(new InfusionRecipe(lump, template, count));
        }
        return null;
    }

    private static class InfusionRecipe {
        public final Item template;
        public final Item lump;
        private final int lumpCount;

        public InfusionRecipe(Item lump, Item template, int lumpCount) {
            this.lump = lump;
            this.template = template;
            this.lumpCount = lumpCount;
        }

        public int hashCode() {
            return Item.getIdFromItem((Item)this.template) * 200 + Item.getIdFromItem((Item)this.lump) + this.lumpCount;
        }

        public boolean equals(Object o) {
            return o instanceof InfusionRecipe && ((InfusionRecipe)o).template == this.template && ((InfusionRecipe)o).lump == this.lump && ((InfusionRecipe)o).lumpCount == this.lumpCount;
        }
    }
}

