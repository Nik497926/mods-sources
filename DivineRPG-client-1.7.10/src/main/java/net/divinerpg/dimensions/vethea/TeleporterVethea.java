/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.Teleporter
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 */
package net.divinerpg.dimensions.vethea;

import net.divinerpg.utils.blocks.TwilightBlocks;
import net.divinerpg.utils.blocks.VetheaBlocks;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class TeleporterVethea
extends Teleporter {
    protected WorldServer myWorld;

    public TeleporterVethea(WorldServer var1) {
        super(var1);
        this.myWorld = var1;
    }

    public boolean placeInExistingPortal(Entity entity, double notUsed_entityX, double notUsed_entityY, double notUsed_entityZ, float notUsed_entityRotation) {
        double var24;
        int searchRange = 200;
        double var10 = -1.0;
        int var12 = 0;
        int var13 = 0;
        int var14 = 0;
        int entityPosX_floored = MathHelper.floor_double((double)entity.posX);
        int entityPosY = MathHelper.floor_double((double)entity.posZ);
        for (int searchX = entityPosX_floored - searchRange; searchX <= entityPosX_floored + searchRange; ++searchX) {
            double var18 = (double)searchX + 0.5 - entity.posX;
            for (int searchZ = entityPosY - searchRange; searchZ <= entityPosY + searchRange; ++searchZ) {
                double var21 = (double)searchZ + 0.5 - entity.posZ;
                for (int searchY = 63; searchY >= 0; --searchY) {
                    if (!this.isBlockPortal((World)this.myWorld, searchX, searchY, searchZ)) continue;
                    while (this.isBlockPortal((World)this.myWorld, searchX, searchY - 1, searchZ)) {
                        --searchY;
                    }
                    var24 = (double)searchY + 0.5 - entity.posY;
                    double var26 = var18 * var18 + var24 * var24 + var21 * var21;
                    if (!(var10 < 0.0) && !(var26 < var10)) continue;
                    var10 = var26;
                    var12 = searchX;
                    var13 = searchY;
                    var14 = searchZ;
                }
            }
        }
        if (var10 >= 0.0) {
            double var28 = (double)var12 + 0.5;
            double var22 = (double)var13 + 0.5;
            var24 = (double)var14 + 0.5;
            if (this.isBlockPortal((World)this.myWorld, var12 - 1, var13, var14)) {
                var28 -= 0.5;
            }
            if (this.isBlockPortal((World)this.myWorld, var12 + 1, var13, var14)) {
                var28 += 0.5;
            }
            if (this.isBlockPortal((World)this.myWorld, var12, var13, var14 - 1)) {
                var24 -= 0.5;
            }
            if (this.isBlockPortal((World)this.myWorld, var12, var13, var14 + 1)) {
                var24 += 0.5;
            }
            entity.setLocationAndAngles(var28, var22 + 1.0, var24 + 1.0, entity.rotationYaw, 0.0f);
            entity.motionZ = 0.0;
            entity.motionY = 0.0;
            entity.motionX = 0.0;
            return true;
        }
        return false;
    }

    public boolean isBlockPortal(World var1, int var2, int var3, int var4) {
        return var1.getBlock(var2, var3, var4) == VetheaBlocks.vetheaPortal;
    }

    public boolean makePortal(Entity entity) {
        double var33;
        double var32;
        int var29;
        int var28;
        int var27;
        int var26;
        int var25;
        int var24;
        int var23;
        int var22;
        int var21;
        double var19;
        int var18;
        double var16;
        int var15;
        int var4 = 16;
        double var5 = -1.0;
        double var2 = this.myWorld.provider.dimensionId == 0 ? 2.0 : 0.5;
        int var7 = MathHelper.floor_double((double)entity.posX);
        int var8 = MathHelper.floor_double((double)(entity.posY * var2));
        int var9 = MathHelper.floor_double((double)entity.posZ);
        int var10 = var7;
        int var11 = var8;
        int var12 = var9;
        int var13 = 0;
        int var14 = this.myWorld.rand.nextInt(4);
        for (var15 = var7 - var4; var15 <= var7 + var4; ++var15) {
            var16 = (double)var15 + 0.5 - entity.posX;
            for (var18 = var9 - var4; var18 <= var9 + var4; ++var18) {
                var19 = (double)var18 + 0.5 - entity.posZ;
                block2: for (var21 = 127; var21 >= 0; --var21) {
                    if (!this.myWorld.isAirBlock(var15, var21, var18)) continue;
                    while (var21 > 0 && this.myWorld.isAirBlock(var15, var21 - 1, var18)) {
                        --var21;
                    }
                    for (var22 = var14; var22 < var14 + 4; ++var22) {
                        var23 = var22 % 2;
                        var24 = 1 - var23;
                        if (var22 % 4 >= 2) {
                            var23 = -var23;
                            var24 = -var24;
                        }
                        for (var25 = 0; var25 < 3; ++var25) {
                            for (var26 = 0; var26 < 4; ++var26) {
                                for (var27 = -1; var27 < 4; ++var27) {
                                    var28 = var15 + (var26 - 1) * var23 + var25 * var24;
                                    var29 = var21 + var27;
                                    int var30 = var18 + (var26 - 1) * var24 - var25 * var23;
                                    if (var27 < 0 && !this.myWorld.getBlock(var28, var29, var30).getMaterial().isSolid() || var27 >= 0 && !this.myWorld.isAirBlock(var28, var29, var30)) continue block2;
                                }
                            }
                        }
                        var32 = (double)var21 + 0.5 - entity.posY * var2;
                        var33 = var16 * var16 + var32 * var32 + var19 * var19;
                        if (!(var5 < 0.0) && !(var33 < var5)) continue;
                        var5 = var33;
                        var10 = var15;
                        var11 = var21;
                        var12 = var18;
                        var13 = var22 % 4;
                    }
                }
            }
        }
        if (var5 < 0.0) {
            for (var15 = var7 - var4; var15 <= var7 + var4; ++var15) {
                var16 = (double)var15 + 0.5 - entity.posX;
                for (var18 = var9 - var4; var18 <= var9 + var4; ++var18) {
                    var19 = (double)var18 + 0.5 - entity.posZ;
                    block10: for (var21 = 127; var21 >= 0; --var21) {
                        if (!this.myWorld.isAirBlock(var15, var21, var18)) continue;
                        while (var21 > 0 && this.myWorld.isAirBlock(var15, var21 - 1, var18)) {
                            --var21;
                        }
                        for (var22 = var14; var22 < var14 + 2; ++var22) {
                            var23 = var22 % 2;
                            var24 = 1 - var23;
                            for (var25 = 0; var25 < 4; ++var25) {
                                for (var26 = -1; var26 < 4; ++var26) {
                                    var27 = var15 + (var25 - 1) * var23;
                                    var28 = var21 + var26;
                                    var29 = var18 + (var25 - 1) * var24;
                                    if (var26 < 0 && !this.myWorld.getBlock(var27, var28, var29).getMaterial().isSolid() || var26 >= 0 && !this.myWorld.isAirBlock(var27, var28, var29)) continue block10;
                                }
                            }
                            var32 = (double)var21 + 0.5 - entity.posY * var2;
                            var33 = var16 * var16 + var32 * var32 + var19 * var19;
                            if (!(var5 < 0.0) && !(var33 < var5)) continue;
                            var5 = var33;
                            var10 = var15;
                            var11 = var21;
                            var12 = var18;
                            var13 = var22 % 2;
                        }
                    }
                }
            }
        }
        int var31 = var13 % 2;
        int var20 = 1 - var31;
        if (var13 % 4 >= 2) {
            var31 = -var31;
            var20 = -var20;
        }
        this.makePortalAt((World)this.myWorld, var10, var11, var12);
        return true;
    }

    private void makePortalAt(World world, int x, int y, int z) {
        y = 16;
        for (int i = 0; i < 5; ++i) {
            for (int j = 0; j < 5; ++j) {
                for (int k = 0; k < 6; ++k) {
                    world.setBlock(x + i - 3, y + j + 1, z + k - 4, Blocks.air);
                }
            }
        }
        world.setBlock(x + 1, y, z, TwilightBlocks.mortumBlock);
        world.setBlock(x, y, z, TwilightBlocks.mortumBlock);
        world.setBlock(x + 2, y, z, TwilightBlocks.mortumBlock);
        world.setBlock(x + 1, y + 1, z - 1, TwilightBlocks.mortumBlock);
        world.setBlock(x + 1, y + 1, z + 1, TwilightBlocks.mortumBlock);
        world.setBlock(x + 1, y + 2, z - 2, TwilightBlocks.mortumBlock);
        world.setBlock(x + 1, y + 2, z + 2, TwilightBlocks.mortumBlock);
        world.setBlock(x + 1, y + 3, z + 1, TwilightBlocks.mortumBlock);
        world.setBlock(x + 1, y + 3, z - 1, TwilightBlocks.mortumBlock);
        world.setBlock(x + 1, y + 4, z, TwilightBlocks.mortumBlock);
        world.setBlock(x + 1, y + 1, z, VetheaBlocks.vetheaPortal);
        world.setBlock(x + 1, y + 2, z + 1, VetheaBlocks.vetheaPortal);
        world.setBlock(x + 1, y + 2, z, VetheaBlocks.vetheaPortal);
        world.setBlock(x + 1, y + 2, z - 1, VetheaBlocks.vetheaPortal);
        world.setBlock(x + 1, y + 3, z, VetheaBlocks.vetheaPortal);
    }
}

