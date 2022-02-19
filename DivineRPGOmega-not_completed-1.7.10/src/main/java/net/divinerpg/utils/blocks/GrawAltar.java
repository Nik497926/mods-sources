/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.world.EnumDifficulty
 *  net.minecraft.world.World
 */
package net.divinerpg.utils.blocks;

import java.util.List;
import java.util.Random;
import net.divinerpg.entities.lelyetia.EntityGraw;
import net.divinerpg.utils.items.JourneyItemsOther;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class GrawAltar
extends Block {
    private static Block.SoundType rck = Block.soundTypeStone;
    Random rand = new Random();

    public GrawAltar(Material p_i45394_1_) {
        super(p_i45394_1_);
        this.setCreativeTab(DivineRPGTabs.blocks);
        this.setHardness(-1.0f);
        this.setResistance(1.0E9f);
        this.setSoundType(rck);
    }

    public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int var6, float var7, float var8, float var9) {
        if (!w.isRemote && !p.isSneaking() && p.getHeldItem() != null) {
            if (p.getHeldItem().getItem() == JourneyItemsOther.DarkBones) {
                p.inventory.consumeInventoryItem(JourneyItemsOther.DarkBones);
                w.playSoundAtEntity((Entity)p, "divinerpg:Mineralization", 1.85f, 1.0f);
                int chance = this.rand.nextInt(99);
                int chance2 = this.rand.nextInt(4);
                if (!w.isRemote) {
                    switch (chance2) {
                        case 0: {
                            p.dropItem(VanillaItemsOther.rupeeIngot, 1);
                            break;
                        }
                        case 1: {
                            p.dropItem(VanillaItemsOther.arlemiteIngot, 1);
                            break;
                        }
                        case 2: {
                            p.dropItem(VanillaItemsOther.bloodgem, 1);
                            break;
                        }
                        case 3: {
                            p.dropItem(VanillaItemsOther.mysticChunk, 1);
                            break;
                        }
                        default: {
                            p.dropItem(VanillaItemsOther.arlemiteIngot, 1);
                        }
                    }
                }
                if ((double)chance < 0.09943) {
                    p.dropItem(VanillaItemsOther.mysticSoul, 1);
                }
                if ((double)chance < 0.13) {
                    p.dropItem(VanillaItemsOther.godlyCrystal, 1);
                }
            }
            if (!w.isRemote && !p.isSneaking() && p.getHeldItem() != null && p.getHeldItem().getItem() == JourneyItemsOther.sozerItemActv) {
                if (w.difficultySetting == EnumDifficulty.PEACEFUL) {
                    return false;
                }
                if (p.inventory.consumeInventoryItem(JourneyItemsOther.sozerItemActv)) {
                    EntityGraw var10 = new EntityGraw(w);
                    var10.setLocationAndAngles(x, y + 3, z, 0.0f, 0.0f);
                    w.spawnEntityInWorld((Entity)var10);
                    List players = p.worldObj.getEntitiesWithinAABB(EntityPlayer.class, p.boundingBox.expand(50.0, 50.0, 50.0));
                    for (EntityPlayer entityPlayer : players) {
                    }
                    return true;
                }
            }
        }
        return true;
    }

    public Block setSoundType(Block.SoundType name) {
        return this.setStepSound(name);
    }
}

