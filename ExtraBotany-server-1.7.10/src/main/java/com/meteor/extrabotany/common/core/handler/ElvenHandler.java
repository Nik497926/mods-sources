/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.core.handler;

import com.meteor.extrabotany.common.entity.EntityElven;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class ElvenHandler
implements IExtendedEntityProperties {
    public static float extraATK = 0.0f;
    public static float extraASPD = 0.0f;
    public static float extraRange = 0.0f;
    public static boolean skillMissile = false;
    public static boolean skillLightning = false;
    public static boolean skillSpear = false;
    public static int levelSkill = 0;
    public static double lastPosX = 0.0;
    public static double lastPosY = 0.0;
    public static double lastPosZ = 0.0;
    public static final String TAG_ELVEN_ABILITY = "elven";
    private static final String TAG_EXTRA_ATK = "extra_ATK";
    private static final String TAG_EXTRA_ASPD = "extra_ASPD";
    private static final String TAG_EXTRA_RANGE = "extra_RANGE";
    private static final String TAG_SKILL_MISSILE = "skill_missile";
    private static final String TAG_SKILL_LIGHTNING = "skill_lightning";
    private static final String TAG_SKILL_SPEAR = "skill_spear";
    private static final String TAG_LEVEL_SKILL = "level_skill";
    private static final String TAG_LASTPOS_X = "lastpos_x";
    private static final String TAG_LASTPOS_Y = "lastpos_y";
    private static final String TAG_LASTPOS_Z = "lastpos_z";
    private static EntityElven elven;

    public ElvenHandler(EntityElven e) {
        elven = e;
        extraATK = 0.0f;
        extraASPD = 0.0f;
        skillMissile = false;
        skillLightning = false;
        skillSpear = false;
        levelSkill = 0;
        lastPosX = 0.0;
        lastPosZ = 0.0;
        lastPosY = 0.0;
    }

    public void saveNBTData(NBTTagCompound compound) {
        NBTTagCompound properties = new NBTTagCompound();
        properties.setFloat(TAG_EXTRA_ATK, extraATK);
        properties.setFloat(TAG_EXTRA_ASPD, extraASPD);
        properties.setFloat(TAG_EXTRA_RANGE, extraRange);
        properties.setBoolean(TAG_SKILL_LIGHTNING, skillLightning);
        properties.setBoolean(TAG_SKILL_SPEAR, skillSpear);
        properties.setBoolean(TAG_SKILL_MISSILE, skillMissile);
        properties.setInteger(TAG_LEVEL_SKILL, levelSkill);
        properties.setDouble(TAG_LASTPOS_X, lastPosX);
        properties.setDouble(TAG_LASTPOS_Y, lastPosY);
        properties.setDouble(TAG_LASTPOS_Z, lastPosZ);
        compound.setTag(TAG_ELVEN_ABILITY, properties);
    }

    public void loadNBTData(NBTTagCompound compound) {
        NBTTagCompound properties = (NBTTagCompound)compound.getTag(TAG_ELVEN_ABILITY);
        extraASPD = properties.getFloat(TAG_EXTRA_ASPD);
        extraRange = properties.getFloat(TAG_EXTRA_RANGE);
        extraATK = properties.getFloat(TAG_EXTRA_ATK);
        skillLightning = properties.getBoolean(TAG_SKILL_LIGHTNING);
        skillMissile = properties.getBoolean(TAG_SKILL_MISSILE);
        skillSpear = properties.getBoolean(TAG_SKILL_SPEAR);
        levelSkill = properties.getInteger(TAG_LEVEL_SKILL);
        lastPosX = properties.getDouble(TAG_LASTPOS_X);
        lastPosY = properties.getDouble(TAG_LASTPOS_Y);
        lastPosZ = properties.getDouble(TAG_LASTPOS_Z);
    }

    public void init(Entity entity, World world) {
    }

    public static final void register(EntityElven e) {
        e.registerExtendedProperties(TAG_ELVEN_ABILITY, new ElvenHandler(e));
    }

    public static final ElvenHandler get(EntityElven e) {
        return (ElvenHandler)e.getExtendedProperties(TAG_ELVEN_ABILITY);
    }

    public static float getExtraATK() {
        return extraATK;
    }

    public static float getExtraASPD() {
        return extraASPD;
    }

    public static float getExtraRange() {
        return extraRange;
    }

    public static boolean isMissileLearned() {
        return skillMissile;
    }

    public static boolean isLightningLearned() {
        return skillLightning;
    }

    public static boolean isSpearLearned() {
        return skillSpear;
    }

    public static int getSkillLevel() {
        return levelSkill;
    }

    public static double getLastPosX() {
        return lastPosX;
    }

    public static double getLastPosY() {
        return lastPosY;
    }

    public static double getLastPosZ() {
        return lastPosZ;
    }

    public static void setLastPos(double x, double y, double z) {
        lastPosX = x;
        lastPosY = y;
        lastPosZ = z;
    }

    public static void setExtraATK(float f) {
        extraATK = f;
    }

    public static void setExtraASPD(float f) {
        extraASPD = f;
    }

    public static void setExtraRange(float f) {
        extraRange = f;
    }

    public static void setMissileLearned(boolean b) {
        skillMissile = b;
    }

    public static void setLightningLearned(boolean b) {
        skillLightning = b;
    }

    public static void setSpearLearned(boolean b) {
        skillSpear = b;
    }

    public static void setSkillLevel(int i) {
        levelSkill = i;
    }
}

