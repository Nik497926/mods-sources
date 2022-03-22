/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.entity;

import com.meteor.extrabotany.ExtraBotany;
import com.meteor.extrabotany.common.entity.EntityAsgard;
import com.meteor.extrabotany.common.entity.EntityElven;
import com.meteor.extrabotany.common.entity.EntityExMachine;
import com.meteor.extrabotany.common.entity.EntityGaiaQuickened;
import com.meteor.extrabotany.common.entity.EntityItemUnbreakable;
import com.meteor.extrabotany.common.entity.EntityLycorisradiataGreen;
import com.meteor.extrabotany.common.entity.EntityLycorisradiataPurple;
import com.meteor.extrabotany.common.entity.EntityLycorisradiataRed;
import com.meteor.extrabotany.common.entity.EntityOdin;
import com.meteor.extrabotany.common.entity.EntityOdinMissile;
import com.meteor.extrabotany.common.entity.EntitySakuraFall;
import com.meteor.extrabotany.common.entity.EntitySpear;
import com.meteor.extrabotany.common.entity.EntityTeleportPearl;
import com.meteor.extrabotany.common.entity.gaia.EntityGaiaIII;
import com.meteor.extrabotany.common.entity.gaia.EntityMagicCycloneAqua;
import com.meteor.extrabotany.common.entity.gaia.EntityMagicCycloneChaos;
import com.meteor.extrabotany.common.entity.gaia.EntityMagicCycloneIgnis;
import com.meteor.extrabotany.common.entity.gaia.EntityMagicLandmineII;
import com.meteor.extrabotany.common.entity.gaia.EntityMagicMissileII;
import cpw.mods.fml.common.registry.EntityRegistry;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;

public class ModEntities {
    public static void init() {
        int id = 0;
        int var1 = id + 1;
        EntityRegistry.registerModEntity(EntityLycorisradiataRed.class, "lycorisred", id, ExtraBotany.instance, 64, 10, true);
        EntityRegistry.registerModEntity(EntityLycorisradiataGreen.class, "lycorisgreen", var1++, ExtraBotany.instance, 64, 10, true);
        EntityRegistry.registerModEntity(EntityLycorisradiataPurple.class, "lycorispurple", var1++, ExtraBotany.instance, 64, 10, true);
        EntityRegistry.registerModEntity(EntityTeleportPearl.class, "teleportpearl", var1++, ExtraBotany.instance, 64, 10, true);
        EntityRegistry.registerModEntity(EntityGaiaIII.class, "gaia3", var1++, ExtraBotany.instance, 256, 3, true);
        EntityRegistry.registerModEntity(EntityAsgard.class, "asgard", var1++, ExtraBotany.instance, 256, 3, true);
        EntityRegistry.registerModEntity(EntityOdin.class, "odin", var1++, ExtraBotany.instance, 256, 3, true);
        EntityRegistry.registerModEntity(EntityExMachine.class, "exmachine", var1++, ExtraBotany.instance, 256, 4, true);
        EntityRegistry.registerModEntity(EntitySakuraFall.class, "sakurafall", 40, ExtraBotany.instance, 256, 10, true);
        EntityRegistry.registerModEntity(EntityElven.class, "magicelven", var1++, ExtraBotany.instance, 256, 3, true);
        EntityRegistry.registerModEntity(EntityItemUnbreakable.class, "unbreakableitem", var1++, ExtraBotany.instance, 256, 3, true);
        EntityRegistry.registerModEntity(EntitySpear.class, "spear", var1++, ExtraBotany.instance, 256, 3, true);
        EntityRegistry.registerModEntity(EntityMagicCycloneChaos.class, "magiccyclonechaos", var1++, ExtraBotany.instance, 256, 3, true);
        EntityRegistry.registerModEntity(EntityMagicCycloneAqua.class, "magiccycloneaqua", var1++, ExtraBotany.instance, 256, 3, true);
        EntityRegistry.registerModEntity(EntityMagicCycloneIgnis.class, "magiccycloneignis", var1++, ExtraBotany.instance, 256, 3, true);
        EntityRegistry.registerModEntity(EntityMagicLandmineII.class, "magiclandmine2", var1++, ExtraBotany.instance, 128, 40, false);
        EntityRegistry.registerModEntity(EntityMagicMissileII.class, "magicmissile2", var1++, ExtraBotany.instance, 128, 40, false);
        EntityRegistry.addSpawn(EntityLycorisradiataRed.class, 2, 1, 1, EnumCreatureType.monster, BiomeGenBase.forest);
        EntityRegistry.addSpawn(EntityLycorisradiataGreen.class, 2, 1, 1, EnumCreatureType.monster, BiomeGenBase.plains);
        EntityRegistry.addSpawn(EntityLycorisradiataPurple.class, 2, 1, 1, EnumCreatureType.monster, BiomeGenBase.desert);
        EntityRegistry.registerModEntity(EntityGaiaQuickened.class, "gaiaquickened", var1++, ExtraBotany.instance, 256, 3, true);
        EntityRegistry.registerModEntity(EntityOdinMissile.class, "odinMissile", var1++, ExtraBotany.instance, 128, 40, false);
    }
}

