/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block.subtile.generating;

import com.meteor.extrabotany.api.ExtraBotanyAPI;
import com.meteor.extrabotany.api.extrabotany.recipe.RecipeStonesia;
import com.meteor.extrabotany.common.lexicon.LexiconModData;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileEntity;
import vazkii.botania.api.subtile.SubTileGenerating;
import vazkii.botania.common.Botania;

public class SubTileStonesia
extends SubTileGenerating {
    private static final int RANGE = 3;
    private static final int DELAY = 40;
    int burnTime = 0;
    private static final String TAG_BURN_TIME = "burnTime";
    private static final int[][] POSITIONS = new int[][]{{-1, 0, -1}, {-1, 0, 0}, {-1, 0, 1}, {0, 0, 1}, {1, 0, 1}, {1, 0, 0}, {1, 0, -1}, {0, 0, -1}};
    int positionAt = 0;

    public LexiconEntry getEntry() {
        return LexiconModData.stonesia;
    }

    public int getColor() {
        return 0x8F8F8F;
    }

    public void writeToPacketNBT(NBTTagCompound cmp) {
        super.writeToPacketNBT(cmp);
        cmp.setInteger(TAG_BURN_TIME, this.burnTime);
    }

    public void readFromPacketNBT(NBTTagCompound cmp) {
        super.readFromPacketNBT(cmp);
        this.burnTime = cmp.getInteger(TAG_BURN_TIME);
    }

    public void onUpdate() {
        super.onUpdate();
        ++this.positionAt;
        if (this.positionAt == POSITIONS.length) {
            this.positionAt = 0;
        }
        if (this.burnTime == 0) {
            int[] acoords = POSITIONS[this.positionAt];
            ChunkCoordinates coords = new ChunkCoordinates(this.supertile.xCoord + acoords[0], this.supertile.yCoord + acoords[1], this.supertile.zCoord + acoords[2]);
            World world = this.supertile.getWorldObj();
            if (!world.isAirBlock(coords.posX, coords.posY, coords.posZ)) {
                Block block = world.getBlock(coords.posX, coords.posY, coords.posZ);
                int meta = world.getBlockMetadata(coords.posX, coords.posY, coords.posZ);
                RecipeStonesia recipe = null;
                for (Object x : ExtraBotanyAPI.stonesiaRecipes) {
                    if (!((RecipeStonesia)x).matches(world, coords.posX, coords.posY, coords.posZ, (SubTileEntity)this, block, meta)) continue;
                    recipe = (RecipeStonesia)x;
                    break;
                }
                if (recipe != null) {
                    Botania.proxy.sparkleFX(this.supertile.getWorldObj(), (double)coords.posX + Math.random(), (double)coords.posY + Math.random(), (double)coords.posZ + Math.random(), 1.0f, 1.0f, 1.0f, (float)Math.random(), 5);
                    if (recipe.set(world, coords.posX, coords.posY, coords.posZ, (SubTileEntity)this)) {
                        this.burnTime += recipe.getMana();
                        for (int var14 = 0; var14 < 25; ++var14) {
                            double var15 = (double)coords.posX + Math.random();
                            double y = (double)coords.posY + Math.random() + 0.5;
                            double z = (double)coords.posZ + Math.random();
                            Botania.proxy.wispFX(this.supertile.getWorldObj(), var15, y, z, 1.0f, 1.0f, 1.0f, (float)Math.random() / 2.0f);
                        }
                    }
                }
            }
        } else if (this.burnTime > 0) {
            --this.burnTime;
        }
    }

    public int getValueForPassiveGeneration() {
        return 5;
    }

    public boolean canGeneratePassively() {
        return this.burnTime > 0;
    }

    public int getDelayBetweenPassiveGeneration() {
        return 1;
    }

    public boolean acceptsRedstone() {
        return true;
    }

    public RadiusDescriptor getRadius() {
        return new RadiusDescriptor.Square(this.toChunkCoordinates(), 3);
    }

    public int getMaxMana() {
        return 500;
    }
}

