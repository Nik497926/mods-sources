/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block.subtile;

import com.meteor.extrabotany.api.ExtraBotanyAPI;
import com.meteor.extrabotany.api.extrabotany.recipe.RecipeInfernoidisy;
import com.meteor.extrabotany.common.lexicon.LexiconModData;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileEntity;
import vazkii.botania.common.Botania;
import vazkii.botania.common.core.handler.ConfigHandler;

public class SubTileInfernoidisy
extends SubTileEntity {
    private static final String TAG_POSITION = "position";
    private static final String TAG_TICKS_REMAINING = "ticksRemaining";
    private static final int TOTAL_TIME = 800;
    private static final int TIME_PER = 100;
    private static final int[][] POSITIONS = new int[][]{{-1, 0, -1}, {-1, 0, 0}, {-1, 0, 1}, {0, 0, 1}, {1, 0, 1}, {1, 0, 0}, {1, 0, -1}, {0, 0, -1}};
    int positionAt = 0;
    int[] ticksRemaining = new int[]{100, 100, 100, 100, 100, 100, 100, 100};

    public LexiconEntry getEntry() {
        return LexiconModData.infernoidisy;
    }

    public void onUpdate() {
        super.onUpdate();
        ++this.positionAt;
        if (this.positionAt == POSITIONS.length) {
            this.positionAt = 0;
        }
        int[] acoords = POSITIONS[this.positionAt];
        ChunkCoordinates coords = new ChunkCoordinates(this.supertile.xCoord + acoords[0], this.supertile.yCoord + acoords[1], this.supertile.zCoord + acoords[2]);
        World world = this.supertile.getWorldObj();
        if (!world.isAirBlock(coords.posX, coords.posY, coords.posZ)) {
            Block block = world.getBlock(coords.posX, coords.posY, coords.posZ);
            int meta = world.getBlockMetadata(coords.posX, coords.posY, coords.posZ);
            RecipeInfernoidisy recipe = null;
            for (Object x1 : ExtraBotanyAPI.infernoidisyRecipes) {
                RecipeInfernoidisy x = (RecipeInfernoidisy) x1;
                if (!x.matches(world, coords.posX, coords.posY, coords.posZ, this, block, meta)) continue;
                recipe = x;
                break;
            }
            if (recipe != null) {
                int n = this.positionAt;
                this.ticksRemaining[n] = this.ticksRemaining[n] - 1;
                Botania.proxy.sparkleFX(this.supertile.getWorldObj(), (double)coords.posX + Math.random(), (double)coords.posY + Math.random(), (double)coords.posZ + Math.random(), 2.5f, 0.5f, 0.5f, (float)Math.random(), 5);
                if (this.ticksRemaining[this.positionAt] <= 0) {
                    this.ticksRemaining[this.positionAt] = 100;
                    if (recipe.set(world, coords.posX, coords.posY, coords.posZ, this)) {
                        for (int var14 = 0; var14 < 25; ++var14) {
                            double var15 = (double)coords.posX + Math.random();
                            double y = (double)coords.posY + Math.random() + 0.5;
                            double z = (double)coords.posZ + Math.random();
                            Botania.proxy.wispFX(this.supertile.getWorldObj(), var15, y, z, 1.0f, 1.0f, 1.0f, (float)Math.random() / 2.0f);
                        }
                        if (ConfigHandler.blockBreakParticles) {
                            this.supertile.getWorldObj().playAuxSFX(2001, coords.posX, coords.posY, coords.posZ, Block.getIdFromBlock(recipe.getOutput()) + (recipe.getOutputMeta() << 12));
                        }
                    }
                }
            } else {
                this.ticksRemaining[this.positionAt] = 100;
            }
        }
    }

    public RadiusDescriptor getRadius() {
        return new RadiusDescriptor.Square(this.toChunkCoordinates(), 1);
    }

    public void readFromPacketNBT(NBTTagCompound cmp) {
        this.positionAt = cmp.getInteger(TAG_POSITION);
        if (this.supertile.getWorldObj() != null && !this.supertile.getWorldObj().isRemote) {
            for (int i = 0; i < this.ticksRemaining.length; ++i) {
                this.ticksRemaining[i] = cmp.getInteger(TAG_TICKS_REMAINING + i);
            }
        }
    }

    public void writeToPacketNBT(NBTTagCompound cmp) {
        cmp.setInteger(TAG_POSITION, this.positionAt);
        for (int i = 0; i < this.ticksRemaining.length; ++i) {
            cmp.setInteger(TAG_TICKS_REMAINING + i, this.ticksRemaining[i]);
        }
    }
}

