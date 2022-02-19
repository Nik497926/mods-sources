/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.WorldProvider
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.biome.BiomeGenBase$Height
 *  net.minecraftforge.common.DimensionManager
 */
package net.divinerpg.utils;

import net.divinerpg.dimensions.arcana.BiomeGenArcana;
import net.divinerpg.dimensions.arcana.WorldProviderArcana;
import net.divinerpg.dimensions.boiling.BiomeGenBoiling;
import net.divinerpg.dimensions.boiling.WorldProviderBoiling;
import net.divinerpg.dimensions.euca.BiomeGenEuca;
import net.divinerpg.dimensions.euca.WorldProviderEuca;
import net.divinerpg.dimensions.iceika.BiomeGenIceika;
import net.divinerpg.dimensions.iceika.WorldProviderIceika;
import net.divinerpg.dimensions.lelyetia.BiomeGenLelyetia;
import net.divinerpg.dimensions.lelyetia.WorldProviderLl;
import net.divinerpg.dimensions.twilight.apalachia.BiomeGenApalachia;
import net.divinerpg.dimensions.twilight.apalachia.WorldProviderApalachia;
import net.divinerpg.dimensions.twilight.eden.BiomeGenEden;
import net.divinerpg.dimensions.twilight.eden.WorldProviderEden;
import net.divinerpg.dimensions.twilight.mortum.BiomeGenMortum;
import net.divinerpg.dimensions.twilight.mortum.WorldProviderMortum;
import net.divinerpg.dimensions.twilight.skythern.BiomeGenSkythern;
import net.divinerpg.dimensions.twilight.skythern.WorldProviderSkythern;
import net.divinerpg.dimensions.twilight.wildwood.BiomeGenWildwood;
import net.divinerpg.dimensions.twilight.wildwood.WorldProviderWildwood;
import net.divinerpg.dimensions.vethea.BiomeGenVethea;
import net.divinerpg.utils.LogHelper;
import net.divinerpg.utils.config.ConfigurationHelper;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.DimensionManager;

public class DimensionHelper {
    protected static final BiomeGenBase.Height iceikaHeight = new BiomeGenBase.Height(0.0f, 0.7f);
    protected static final BiomeGenBase.Height vetheaHeight = new BiomeGenBase.Height(0.0f, 0.7f);
    protected static final BiomeGenBase.Height apalachiaHeight = new BiomeGenBase.Height(0.5f, 2.0f);
    public static BiomeGenBase edenBiome = new BiomeGenEden(ConfigurationHelper.edenBiome);
    public static BiomeGenBase wildwoodBiome = new BiomeGenWildwood(ConfigurationHelper.wildwoodBiome);
    public static BiomeGenBase apalachiaBiome = new BiomeGenApalachia(ConfigurationHelper.apalachiaBiome);
    public static BiomeGenBase skythernBiome = new BiomeGenSkythern(ConfigurationHelper.skythernBiome);
    public static BiomeGenBase mortumBiome = new BiomeGenMortum(ConfigurationHelper.mortumBiome);
    public static BiomeGenBase iceikaBiome = new BiomeGenIceika(ConfigurationHelper.iceikaBiome).setHeight(iceikaHeight);
    public static BiomeGenBase vetheaBiome = new BiomeGenVethea(ConfigurationHelper.vetheaBiome).setHeight(vetheaHeight);
    public static BiomeGenBase arcanaBiome = new BiomeGenArcana(ConfigurationHelper.arcanaBiome);
    public static BiomeGenBase eucaBiome = new BiomeGenEuca(ConfigurationHelper.eucaBiome);
    public static BiomeGenBase boilingBiome = new BiomeGenBoiling(ConfigurationHelper.boilingBiome);
    public static BiomeGenBase LelyetiaBiome = new BiomeGenLelyetia(ConfigurationHelper.lelyetiaBiome);

    public static void init() {
        DimensionHelper.addDimension(ConfigurationHelper.eden, WorldProviderEden.class, ConfigurationHelper.keepLoadingEden);
        DimensionHelper.addDimension(ConfigurationHelper.wildwood, WorldProviderWildwood.class, ConfigurationHelper.keepLoadingWildwood);
        DimensionHelper.addDimension(ConfigurationHelper.arcana, WorldProviderArcana.class, ConfigurationHelper.keepLoadingArcana);
        DimensionHelper.addDimension(ConfigurationHelper.iceika, WorldProviderIceika.class, ConfigurationHelper.keepLoadingIceika);
        DimensionHelper.addDimension(ConfigurationHelper.apalachia, WorldProviderApalachia.class, ConfigurationHelper.keepLoadingApalachia);
        DimensionHelper.addDimension(ConfigurationHelper.skythern, WorldProviderSkythern.class, ConfigurationHelper.keepLoadingSkythern);
        DimensionHelper.addDimension(ConfigurationHelper.mortum, WorldProviderMortum.class, ConfigurationHelper.keepLoadingMortum);
        DimensionHelper.addDimension(ConfigurationHelper.euca, WorldProviderEuca.class, ConfigurationHelper.keepLoadingEuca);
        DimensionHelper.addDimension(ConfigurationHelper.boiling, WorldProviderBoiling.class, ConfigurationHelper.keepLoadingBoiling);
        DimensionHelper.addDimension(ConfigurationHelper.lelyetia, WorldProviderLl.class, ConfigurationHelper.keepLoadingLelyetia);
    }

    private static void addDimension(int id, Class<? extends WorldProvider> w, boolean keeploading) {
        LogHelper.debug("Registering dimension ID: " + id);
        DimensionManager.registerProviderType((int)id, w, (boolean)keeploading);
        DimensionManager.registerDimension((int)id, (int)id);
    }
}

