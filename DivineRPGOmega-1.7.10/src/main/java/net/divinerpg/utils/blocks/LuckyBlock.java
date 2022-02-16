/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityXPOrb
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 *  omegalevels.ItemGem$EnumType
 *  omegalevels.OmegaLevels
 */
package net.divinerpg.utils.blocks;

import net.divinerpg.blocks.base.BlockMod;
import net.divinerpg.utils.items.IceikaItems;
import net.divinerpg.utils.items.ItemsFood;
import net.divinerpg.utils.items.JourneyItemsOther;
import net.divinerpg.utils.items.JourneyItemsWeapon;
import net.divinerpg.utils.items.TwilightItemsArmor;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.divinerpg.utils.items.VanillaItemsWeapons;
import net.divinerpg.utils.material.EnumBlockType;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import omegalevels.ItemGem;
import omegalevels.OmegaLevels;

public class LuckyBlock
extends BlockMod {
    public LuckyBlock(EnumBlockType blockType, String name, float f) {
        super(blockType, name, f);
        this.setCreativeTab(DivineRPGTabs.items);
        this.setLightLevel(1.0f);
        this.setResistance(1.0E9f);
    }

    public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int var6, float var7, float var8, float var9) {
        if (!p.isSneaking() && p.getHeldItem() != null && p.getHeldItem().getItem() == JourneyItemsOther.LuckyPickaxe) {
            p.getHeldItem().damageItem(1, (EntityLivingBase)p);
            w.playSoundAtEntity((Entity)p, "divinerpg:Mineralization", 1.85f, 1.0f);
            w.setBlock(x, y, z, Blocks.air);
            int chance = this.rand.nextInt(99);
            if (!p.worldObj.isRemote) {
                p.worldObj.spawnEntityInWorld((Entity)new EntityXPOrb(p.worldObj, (double)x, (double)y, (double)z, 600));
            }
            p.worldObj.setBlock((int)Math.floor((double)x + 0.0), (int)Math.floor(y), (int)Math.floor((double)z + 0.0), (Block)Blocks.chest);
            IInventory te = (IInventory)p.worldObj.getTileEntity((int)Math.floor((double)x + 0.0), (int)Math.floor(y), (int)Math.floor((double)z + 0.0));
            if (te != null) {
                int randrupee = this.rand.nextInt(8);
                int randmeat = this.rand.nextInt(16);
                int randsoul = this.rand.nextInt(2);
                if (randrupee != 0) {
                    te.setInventorySlotContents(0, new ItemStack(VanillaItemsOther.rupeeIngot, randrupee));
                    te.setInventorySlotContents(1, new ItemStack(VanillaItemsOther.arlemiteIngot, randrupee));
                    if (chance < 85 && randmeat != 0) {
                        te.setInventorySlotContents(2, new ItemStack(ItemsFood.magicMeat, randmeat));
                    }
                }
                if (chance < 40 && chance > 30) {
                    te.setInventorySlotContents(3, new ItemStack(JourneyItemsWeapon.celestiumSword, 1));
                }
                if (chance < 50 && chance > 20) {
                    te.setInventorySlotContents(4, new ItemStack(IceikaItems.frossivence, 1));
                }
                if (chance < 60 && chance > 15) {
                    te.setInventorySlotContents(5, new ItemStack(VanillaItemsWeapons.lunaSoulSword, 1));
                }
                if (chance < 15 && chance > 7) {
                    te.setInventorySlotContents(6, new ItemStack(VanillaItemsWeapons.heritageSword, 1));
                }
                if (chance == 5) {
                    te.setInventorySlotContents(7, new ItemStack(TwilightItemsArmor.awakenedHelmet, 1));
                }
                if (chance == 6) {
                    te.setInventorySlotContents(7, new ItemStack(TwilightItemsArmor.awakenedChestplate, 1));
                }
                if (chance == 7) {
                    te.setInventorySlotContents(7, new ItemStack(TwilightItemsArmor.awakenedLeggings, 1));
                }
                if (chance == 8) {
                    te.setInventorySlotContents(7, new ItemStack(TwilightItemsArmor.awakenedBoots, 1));
                }
                if (chance < 25 && chance > 8) {
                    te.setInventorySlotContents(8, new ItemStack((Item)OmegaLevels.gem, 1, ItemGem.EnumType.BLUE.ordinal()));
                }
                if (chance < 50 && chance > 40) {
                    te.setInventorySlotContents(9, new ItemStack(TwilightItemsArmor.haliteHelmet));
                    te.setInventorySlotContents(10, new ItemStack(TwilightItemsArmor.haliteChestplate));
                    te.setInventorySlotContents(11, new ItemStack(TwilightItemsArmor.haliteLeggings));
                    te.setInventorySlotContents(12, new ItemStack(TwilightItemsArmor.haliteBoots));
                }
                if (chance < 4 && randsoul != 0) {
                    te.setInventorySlotContents(13, new ItemStack(VanillaItemsOther.mysticSoul, randsoul));
                }
                if (chance < 10) {
                    te.setInventorySlotContents(14, new ItemStack(JourneyItemsOther.LuckyPickaxe, 1));
                }
                if ((double)chance < 0.8 && p.worldObj.getWorldTime() >= 13000L) {
                    te.setInventorySlotContents(15, new ItemStack(VanillaItemsWeapons.enderFlameSword, 1));
                }
                if (chance < 50 && chance > 42) {
                    te.setInventorySlotContents(9, new ItemStack(TwilightItemsArmor.awakenedHelmet));
                    te.setInventorySlotContents(10, new ItemStack(TwilightItemsArmor.awakenedChestplate));
                    te.setInventorySlotContents(11, new ItemStack(TwilightItemsArmor.awakenedLeggings));
                    te.setInventorySlotContents(12, new ItemStack(TwilightItemsArmor.awakenedBoots));
                }
            }
            return true;
        }
        return true;
    }
}

