/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraftforge.oredict.OreDictionary
 */
package net.divinerpg.utils;

import net.divinerpg.utils.blocks.IceikaBlocks;
import net.divinerpg.utils.blocks.TwilightBlocks;
import net.divinerpg.utils.blocks.VanillaBlocks;
import net.minecraft.block.Block;
import net.minecraftforge.oredict.OreDictionary;

public class DivineOredict {
    public static void init() {
        OreDictionary.registerOre((String)"plankWood", (Block)VanillaBlocks.divinePlanks);
        OreDictionary.registerOre((String)"plankWood", (Block)IceikaBlocks.frozenPlanks);
        OreDictionary.registerOre((String)"plankWood", (Block)TwilightBlocks.edenPlanks);
        OreDictionary.registerOre((String)"plankWood", (Block)TwilightBlocks.wildwoodPlanks);
        OreDictionary.registerOre((String)"plankWood", (Block)TwilightBlocks.apalachiaPlanks);
        OreDictionary.registerOre((String)"plankWood", (Block)TwilightBlocks.skythernPlanks);
        OreDictionary.registerOre((String)"plankWood", (Block)TwilightBlocks.mortumPlanks);
        OreDictionary.registerOre((String)"stairWood", (Block)VanillaBlocks.divineStairs);
        OreDictionary.registerOre((String)"stairWood", (Block)IceikaBlocks.frozenStairs);
        OreDictionary.registerOre((String)"stairWood", (Block)TwilightBlocks.edenStairs);
        OreDictionary.registerOre((String)"stairWood", (Block)TwilightBlocks.wildwoodStairs);
        OreDictionary.registerOre((String)"stairWood", (Block)TwilightBlocks.apalachiaStairs);
        OreDictionary.registerOre((String)"stairWood", (Block)TwilightBlocks.skythernStairs);
        OreDictionary.registerOre((String)"stairWood", (Block)TwilightBlocks.mortumStairs);
        OreDictionary.registerOre((String)"slabWood", (Block)VanillaBlocks.divineSlab);
        OreDictionary.registerOre((String)"slabWood", (Block)IceikaBlocks.frozenSlab);
        OreDictionary.registerOre((String)"slabWood", (Block)TwilightBlocks.edenSlab);
        OreDictionary.registerOre((String)"slabWood", (Block)TwilightBlocks.wildwoodSlab);
        OreDictionary.registerOre((String)"slabWood", (Block)TwilightBlocks.apalachiaSlab);
        OreDictionary.registerOre((String)"slabWood", (Block)TwilightBlocks.skythernSlab);
        OreDictionary.registerOre((String)"slabWood", (Block)TwilightBlocks.mortumSlab);
    }
}

