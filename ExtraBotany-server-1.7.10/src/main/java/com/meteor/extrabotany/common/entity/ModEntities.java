/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.entity;

import com.meteor.extrabotany.ExtraBotany;
import com.meteor.extrabotany.common.entity.EntityAdvanceSpark;
import com.meteor.extrabotany.common.entity.EntityAdvanceSpark1;
import com.meteor.extrabotany.common.entity.EntityAdvanceSpark2;
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
import com.meteor.extrabotany.common.entity.gaia.EntityGaiaIV;
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
        EntityRegistry.registerModEntity(EntityLycorisradiataRed.class, (String)"lycorisred", (int)id, (Object)ExtraBotany.instance, (int)64, (int)10, (boolean)true);
        EntityRegistry.registerModEntity(EntityLycorisradiataGreen.class, (String)"lycorisgreen", (int)var1++, (Object)ExtraBotany.instance, (int)64, (int)10, (boolean)true);
        EntityRegistry.registerModEntity(EntityLycorisradiataPurple.class, (String)"lycorispurple", (int)var1++, (Object)ExtraBotany.instance, (int)64, (int)10, (boolean)true);
        EntityRegistry.registerModEntity(EntityTeleportPearl.class, (String)"teleportpearl", (int)var1++, (Object)ExtraBotany.instance, (int)64, (int)10, (boolean)true);
        EntityRegistry.registerModEntity(EntityGaiaIII.class, (String)"gaia3", (int)var1++, (Object)ExtraBotany.instance, (int)256, (int)3, (boolean)true);
        EntityRegistry.registerModEntity(EntityAsgard.class, (String)"asgard", (int)var1++, (Object)ExtraBotany.instance, (int)256, (int)3, (boolean)true);
        EntityRegistry.registerModEntity(EntityOdin.class, (String)"odin", (int)var1++, (Object)ExtraBotany.instance, (int)256, (int)3, (boolean)true);
        EntityRegistry.registerModEntity(EntityExMachine.class, (String)"exmachine", (int)var1++, (Object)ExtraBotany.instance, (int)256, (int)4, (boolean)true);
        EntityRegistry.registerModEntity(EntitySakuraFall.class, (String)"sakurafall", (int)40, (Object)ExtraBotany.instance, (int)256, (int)10, (boolean)true);
        EntityRegistry.registerModEntity(EntityElven.class, (String)"magicelven", (int)var1++, (Object)ExtraBotany.instance, (int)256, (int)3, (boolean)true);
        EntityRegistry.registerModEntity(EntityItemUnbreakable.class, (String)"unbreakableitem", (int)var1++, (Object)ExtraBotany.instance, (int)256, (int)3, (boolean)true);
        EntityRegistry.registerModEntity(EntitySpear.class, (String)"spear", (int)var1++, (Object)ExtraBotany.instance, (int)256, (int)3, (boolean)true);
        EntityRegistry.registerModEntity(EntityMagicCycloneChaos.class, (String)"magiccyclonechaos", (int)var1++, (Object)ExtraBotany.instance, (int)256, (int)3, (boolean)true);
        EntityRegistry.registerModEntity(EntityMagicCycloneAqua.class, (String)"magiccycloneaqua", (int)var1++, (Object)ExtraBotany.instance, (int)256, (int)3, (boolean)true);
        EntityRegistry.registerModEntity(EntityMagicCycloneIgnis.class, (String)"magiccycloneignis", (int)var1++, (Object)ExtraBotany.instance, (int)256, (int)3, (boolean)true);
        EntityRegistry.registerModEntity(EntityMagicLandmineII.class, (String)"magiclandmine2", (int)var1++, (Object)ExtraBotany.instance, (int)128, (int)40, (boolean)false);
        EntityRegistry.registerModEntity(EntityMagicMissileII.class, (String)"magicmissile2", (int)var1++, (Object)ExtraBotany.instance, (int)128, (int)40, (boolean)false);
        EntityRegistry.addSpawn(EntityLycorisradiataRed.class, (int)2, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])new BiomeGenBase[]{BiomeGenBase.forest});
        EntityRegistry.addSpawn(EntityLycorisradiataGreen.class, (int)2, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])new BiomeGenBase[]{BiomeGenBase.plains});
        EntityRegistry.addSpawn(EntityLycorisradiataPurple.class, (int)2, (int)1, (int)1, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])new BiomeGenBase[]{BiomeGenBase.desert});
        EntityRegistry.registerModEntity(EntityGaiaQuickened.class, (String)"gaiaquickened", (int)var1++, (Object)ExtraBotany.instance, (int)256, (int)3, (boolean)true);
        EntityRegistry.registerModEntity(EntityOdinMissile.class, (String)"odinMissile", (int)var1++, (Object)ExtraBotany.instance, (int)128, (int)40, (boolean)false);
        EntityRegistry.registerModEntity(EntityAdvanceSpark.class, (String)"advanceSpark", (int)var1++, (Object)ExtraBotany.instance, (int)128, (int)40, (boolean)false);
        EntityRegistry.registerModEntity(EntityAdvanceSpark1.class, (String)"advanceSpark1", (int)var1++, (Object)ExtraBotany.instance, (int)128, (int)40, (boolean)false);
        EntityRegistry.registerModEntity(EntityAdvanceSpark2.class, (String)"advanceSpark2", (int)var1++, (Object)ExtraBotany.instance, (int)128, (int)40, (boolean)false);
        EntityRegistry.registerModEntity(EntityGaiaIV.class, (String)"Gaia IV", (int)var1++, (Object)ExtraBotany.instance, (int)256, (int)3, (boolean)true);
    }
}

