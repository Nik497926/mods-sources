/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.world.biome.BiomeGenBase
 */
package net.divinerpg.dimensions.lelyetia;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import net.divinerpg.utils.blocks.JourneyBlocks;
import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenLelyetia
extends BiomeGenBase {
    public BiomeGenLelyetia(int par1) {
        super(par1);
        this.setBiomeName("Lelyetia");
        this.topBlock = JourneyBlocks.GrassLelyetiaUp;
        this.fillerBlock = JourneyBlocks.StoneLelyetia;
        this.rainfall = 500.0f;
        this.spawnableCreatureList.clear();
        this.spawnableMonsterList.clear();
        this.enableSnow = false;
        this.spawnableCaveCreatureList.clear();
        this.temperature = 2.0f;
    }

    @SideOnly(value=Side.CLIENT)
    public int getSkyColorByTemp(float par1) {
        return Color.BLUE.getRGB();
    }
}

