/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.world;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import vazkii.botania.common.Botania;

public class ExplosionMagic
extends Explosion {
    private World worldObj;
    private Random explosionRNG = new Random();

    public ExplosionMagic(World world, Entity entity, double x, double y, double z, float power) {
        super(world, entity, x, y, z, power);
        this.worldObj = world;
    }

    public void doExplosionA() {
        double d7;
        double d6;
        double d5;
        int k;
        int j;
        int i;
        float f = this.explosionSize;
        HashSet<ChunkPosition> hashset = new HashSet<ChunkPosition>();
        for (i = 0; i < 16; ++i) {
            for (j = 0; j < 16; ++j) {
                for (k = 0; k < 16; ++k) {
                    if (i != 0 && i != 15 && j != 0 && j != 15 && k != 0 && k != 15) continue;
                    double i2 = (float)i / 15.0f * 2.0f - 1.0f;
                    double j2 = (float)j / 15.0f * 2.0f - 1.0f;
                    double vec3 = (float)k / 15.0f * 2.0f - 1.0f;
                    double entity = Math.sqrt(i2 * i2 + j2 * j2 + vec3 * vec3);
                    i2 /= entity;
                    j2 /= entity;
                    vec3 /= entity;
                    d5 = this.explosionX;
                    d6 = this.explosionY;
                    d7 = this.explosionZ;
                    float d9 = 0.3f;
                    for (float f1 = this.explosionSize * (0.7f + this.worldObj.rand.nextFloat() * 0.6f); f1 > 0.0f; f1 -= d9 * 0.75f) {
                        int l1;
                        int d10;
                        int j1 = MathHelper.floor_double((double)d5);
                        Block d11 = this.worldObj.getBlock(j1, d10 = MathHelper.floor_double((double)d6), l1 = MathHelper.floor_double((double)d7));
                        if (d11.getMaterial() != Material.air) {
                            float f3 = this.exploder != null ? this.exploder.func_145772_a((Explosion)this, this.worldObj, j1, d10, l1, d11) : d11.getExplosionResistance(this.exploder, this.worldObj, j1, d10, l1, this.explosionX, this.explosionY, this.explosionZ);
                            f1 -= (f3 + 0.3f) * d9;
                        }
                        if (f1 > 0.0f && (this.exploder == null || this.exploder.func_145774_a((Explosion)this, this.worldObj, j1, d10, l1, d11, f1))) {
                            hashset.add(new ChunkPosition(j1, d10, l1));
                        }
                        d5 += i2 * (double)d9;
                        d6 += j2 * (double)d9;
                        d7 += vec3 * (double)d9;
                    }
                }
            }
        }
        this.affectedBlockPositions.addAll(hashset);
        this.explosionSize *= 2.0f;
        i = MathHelper.floor_double((double)(this.explosionX - (double)this.explosionSize - 1.0));
        j = MathHelper.floor_double((double)(this.explosionX + (double)this.explosionSize + 1.0));
        k = MathHelper.floor_double((double)(this.explosionY - (double)this.explosionSize - 1.0));
        int var29 = MathHelper.floor_double((double)(this.explosionY + (double)this.explosionSize + 1.0));
        int l = MathHelper.floor_double((double)(this.explosionZ - (double)this.explosionSize - 1.0));
        int var30 = MathHelper.floor_double((double)(this.explosionZ + (double)this.explosionSize + 1.0));
        List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this.exploder, AxisAlignedBB.getBoundingBox((double)i, (double)k, (double)l, (double)j, (double)var29, (double)var30));
        ForgeEventFactory.onExplosionDetonate((World)this.worldObj, (Explosion)this, (List)list, (double)this.explosionSize);
        Vec3 var31 = Vec3.createVectorHelper((double)this.explosionX, (double)this.explosionY, (double)this.explosionZ);
        for (int i1 = 0; i1 < list.size(); ++i1) {
            double var33;
            Entity var32 = (Entity)list.get(i1);
            double d4 = var32.getDistance(this.explosionX, this.explosionY, this.explosionZ) / (double)this.explosionSize;
            if (!(d4 <= 1.0) || (var33 = (double)MathHelper.sqrt_double((double)((d5 = var32.posX - this.explosionX) * d5 + (d6 = var32.posY + (double)var32.getEyeHeight() - this.explosionY) * d6 + (d7 = var32.posZ - this.explosionZ) * d7))) == 0.0) continue;
            d5 /= var33;
            d6 /= var33;
            d7 /= var33;
            double var34 = this.worldObj.getBlockDensity(var31, var32.boundingBox);
            double var35 = (1.0 - d4) * var34;
            if (var32 instanceof EntityPlayer && var32 instanceof EntityItem) continue;
            var32.attackEntityFrom(DamageSource.magic, (float)((int)((var35 * var35 + var35) / 2.0 * 8.0 * (double)this.explosionSize + 1.0)));
            double d8 = EnchantmentProtection.func_92092_a((Entity)var32, (double)var35);
            var32.motionX += d5 * d8;
            var32.motionY += d6 * d8;
            var32.motionZ += d7 * d8;
        }
        this.explosionSize = f;
    }

    public void doExplosionB(boolean p_77279_1_) {
        Block block;
        int k;
        int j;
        int i;
        int iterator;
        this.worldObj.playSoundEffect(this.explosionX, this.explosionY, this.explosionZ, "random.explode", 4.0f, (1.0f + (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2f) * 0.7f);
        if (this.explosionSize >= 2.0f && this.isSmoking) {
            for (iterator = 0; iterator < 16; ++iterator) {
                Botania.proxy.wispFX(this.worldObj, this.explosionX + Math.random() * 2.0 - 2.0, this.explosionY + (double)0.65f + Math.random() * 2.0 - 2.0, this.explosionZ + Math.random() * 2.0 - 2.0, 2.32f, 2.32f, 0.85f, 3.12f, 0.0f, 0.45f);
            }
        } else {
            for (iterator = 0; iterator < 16; ++iterator) {
                Botania.proxy.wispFX(this.worldObj, this.explosionX + Math.random() * 2.0 - 2.0, this.explosionY + (double)0.65f + Math.random() * 2.0 - 2.0, this.explosionZ + Math.random() * 2.0 - 2.0, 2.32f, 2.32f, 0.85f, 3.12f, 0.0f, 0.45f);
            }
        }
        if (this.isSmoking) {
            for (Object chunkposition1 : this.affectedBlockPositions) {
                ChunkPosition chunkposition = (ChunkPosition) chunkposition1;
                i = chunkposition.chunkPosX;
                j = chunkposition.chunkPosY;
                k = chunkposition.chunkPosZ;
                block = this.worldObj.getBlock(i, j, k);
                if (p_77279_1_) {
                    double block1 = (float)i + this.worldObj.rand.nextFloat();
                    double d1 = (float)j + this.worldObj.rand.nextFloat();
                    double d2 = (float)k + this.worldObj.rand.nextFloat();
                    double d3 = block1 - this.explosionX;
                    double d4 = d1 - this.explosionY;
                    double d5 = d2 - this.explosionZ;
                    double d6 = MathHelper.sqrt_double((double)(d3 * d3 + d4 * d4 + d5 * d5));
                    double d7 = 0.5 / (d6 / (double)this.explosionSize + 0.1);
                    double var10000 = (d3 /= d6) * (d7 *= (double)(this.worldObj.rand.nextFloat() * this.worldObj.rand.nextFloat() + 0.3f));
                    var10000 = (d4 /= d6) * d7;
                    var10000 = (d5 /= d6) * d7;
                    for (int b = 0; b < 34; ++b) {
                        Botania.proxy.wispFX(this.worldObj, (block1 + this.explosionX * 1.0) / 2.0, (d1 + this.explosionY * 1.0) / 2.0 + (double)0.65f, (d2 + this.explosionZ * 1.0) / 2.0, 2.3f, 1.0f, 1.0f, 1.3f, 0.0f, 0.55f);
                        Botania.proxy.wispFX(this.worldObj, block1, d1 + (double)0.65f, d2, 2.3f, 1.0f, 1.0f, 1.3f, 0.0f, 0.55f);
                    }
                }
                if (block.getMaterial() == Material.air) continue;
                if (block.canDropFromExplosion((Explosion)this)) {
                    block.dropBlockAsItemWithChance(this.worldObj, i, j, k, this.worldObj.getBlockMetadata(i, j, k), 1.0f / this.explosionSize, 0);
                }
                block.onBlockExploded(this.worldObj, i, j, k, (Explosion)this);
            }
        }
        if (this.isFlaming) {
            for (Object chunkposition1 : this.affectedBlockPositions) {
                ChunkPosition chunkposition = (ChunkPosition) chunkposition1;
                i = chunkposition.chunkPosX;
                j = chunkposition.chunkPosY;
                k = chunkposition.chunkPosZ;
                block = this.worldObj.getBlock(i, j, k);
                Block var26 = this.worldObj.getBlock(i, j - 1, k);
                if (block.getMaterial() != Material.air || !var26.func_149730_j() || this.explosionRNG.nextInt(3) != 0) continue;
                this.worldObj.setBlock(i, j, k, (Block)Blocks.fire);
            }
        }
    }
}

