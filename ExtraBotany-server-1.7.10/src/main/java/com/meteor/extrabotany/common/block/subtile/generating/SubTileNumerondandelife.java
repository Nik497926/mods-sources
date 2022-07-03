/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block.subtile.generating;

import com.meteor.extrabotany.common.lexicon.LexiconModData;
import java.util.ArrayList;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileGenerating;

public class SubTileNumerondandelife
extends SubTileGenerating {
    private static final int RANGE = 2;
    private static int isEnable = 0;
    private static final String TAG_ISENABLE = "enable";
    private static final int DELAY = 8000;
    private static ArrayList<String> coords = new ArrayList();
    private static ArrayList<Long> timer = new ArrayList();
    public static DamageSource magic = new DamageSource("magic").setDamageBypassesArmor().setMagicDamage();

    public int getColor() {
        return 509223;
    }

    public LexiconEntry getEntry() {
        return LexiconModData.numerondandelife;
    }

    public static DamageSource damageSource() {
        return new DamageSource("botania-relic");
    }

    public int getDelayBetweenPassiveGeneration() {
        return 40;
    }

    public boolean isPassiveFlower() {
        return false;
    }

    public void writeToPacketNBT(NBTTagCompound cmp) {
        super.writeToPacketNBT(cmp);
        cmp.setInteger(TAG_ISENABLE, isEnable);
    }

    public void readFromPacketNBT(NBTTagCompound cmp) {
        super.readFromPacketNBT(cmp);
        isEnable = cmp.getInteger(TAG_ISENABLE);
    }

    public int getMaxMana() {
        return 2400;
    }

    public boolean acceptsRedstone() {
        return true;
    }

    public RadiusDescriptor getRadius() {
        return new RadiusDescriptor.Square(this.toChunkCoordinates(), 3);
    }
}

