/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.world.biome.BiomeGenBase
 */
package net.divinerpg.dimensions.twilight.eden;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import net.divinerpg.utils.blocks.TwilightBlocks;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenEden
extends BiomeGenBase {
    public BiomeGenEden(int par1) {
        super(par1);
        this.setBiomeName("Eden");
        this.topBlock = TwilightBlocks.edenGrass;
        this.fillerBlock = TwilightBlocks.edenDirt;
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.theBiomeDecorator.treesPerChunk = 6;
        this.waterColorMultiplier = 0x242424;
    }

    @SideOnly(value=Side.CLIENT)
    public int getSkyColorByTemp(float par1) {
        return Color.getHSBColor(0.1361f, 0.95f, 1.0f).getRGB();
    }
}

