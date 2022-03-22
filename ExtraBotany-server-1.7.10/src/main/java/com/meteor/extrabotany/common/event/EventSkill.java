/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.event;

import com.meteor.extrabotany.common.block.ModBlocks;
import com.meteor.extrabotany.common.block.subtile.functional.SubTileIcebirdium;
import com.meteor.extrabotany.common.item.relic.legendary.armor.awake.ItemAwakeOGArmor;
import com.meteor.extrabotany.common.item.relic.legendary.armor.killer.ItemKillerArmor;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.oredict.OreDictionary;
import vazkii.botania.common.block.tile.TileSpecialFlower;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.entity.EntityDoppleganger;

public class EventSkill {
    @SubscribeEvent(priority=EventPriority.LOWEST)
    public void EventSkills(LivingHurtEvent event) {
        EntityPlayer pl;
        if (event.entity instanceof EntityPlayer) {
            pl = (EntityPlayer)event.entity;
            if (!event.isCanceled() && EventSkill.fullArm8lvl(pl)) {
                event.ammount *= 0.6f;
            }
            if (!event.isCanceled() && EventSkill.fullArm8lvl(pl) && EventSkill.isActiveSkill(1, pl) && EventSkill.checkCD(pl, 1)) {
                pl.setHealth(pl.getHealth() + 2.0f);
            }
            if (!event.isCanceled() && !(event.source.getSourceOfDamage() instanceof EntityPlayer) && EventSkill.isLetal(pl, event.ammount) && EventSkill.fullArm8lvl(pl) && EventSkill.isActiveSkill(2, pl) && EventSkill.checkCD(pl, 2)) {
                EventSkill.installCD(pl, 2);
                pl.setHealth(20.0f);
                event.setCanceled(true);
            }
            if (!event.isCanceled() && event.source.getSourceOfDamage() instanceof EntityPlayer && EventSkill.fullArm8lvl(pl) && EventSkill.isActiveSkill(3, pl)) {
                event.ammount *= 0.92f;
            }
            if (!event.isCanceled() && !(event.source.getSourceOfDamage() instanceof EntityPlayer) && EventSkill.fullArm8lvl(pl) && EventSkill.isActiveSkill(4, pl) && pl.worldObj.provider.dimensionId == 0) {
                event.ammount *= 0.7f;
            }
            if (!event.isCanceled() && !(event.source.getSourceOfDamage() instanceof EntityPlayer) && EventSkill.fullArm8lvl(pl) && EventSkill.isActiveSkill(5, pl) && pl.worldObj.provider.dimensionId != 0) {
                event.ammount *= 0.85f;
            }
            if (!event.isCanceled() && EventSkill.fullArm8lvl(pl) && EventSkill.isActiveSkill(6, pl) && Math.random() <= 0.05) {
                event.setCanceled(true);
            }
            if (!event.isCanceled() && EventSkill.fullArm8lvl(pl) && EventSkill.isActiveSkill(8, pl) && Math.random() <= 0.03 && event.source.getSourceOfDamage() instanceof EntityPlayer) {
                event.source.getSourceOfDamage().attackEntityFrom(DamageSource.causePlayerDamage(pl), event.ammount);
                event.setCanceled(true);
            }
        }
        if (event.source.getSourceOfDamage() instanceof EntityPlayer) {
            pl = (EntityPlayer)event.source.getSourceOfDamage();
            if (!event.isCanceled() && EventSkill.fullArm8lvl(pl) && EventSkill.isActiveSkill(7, pl)) {
                boolean crit;
                boolean bl = crit = pl.fallDistance > 0.0f && !pl.onGround && !pl.isOnLadder() && !pl.isInWater() && !pl.isPotionActive(Potion.blindness) && pl.ridingEntity == null;
                if (crit) {
                    event.ammount *= 1.2f;
                }
            }
            if (!event.isCanceled() && EventSkill.fullArm8lvl(pl) && EventSkill.isActiveSkill(9, pl) && Math.random() < 0.03) {
                event.entity.attackEntityFrom(DamageSource.magic, 5.0f);
                event.setCanceled(true);
            }
            if (!event.isCanceled() && EventSkill.fullArm8lvl(pl) && EventSkill.isActiveSkill(11, pl) && pl.getCurrentEquippedItem() == null) {
                event.ammount *= 1.5f;
            }
            if (!event.isCanceled() && EventSkill.fullArm8lvl(pl) && EventSkill.isActiveSkill(14, pl) && (event.entity instanceof EntityZombie || event.entity instanceof EntitySkeleton)) {
                event.ammount = 100000.0f;
            }
            if (!event.isCanceled() && EventSkill.fullArm8lvl(pl) && EventSkill.isActiveSkill(17, pl) && event.entity instanceof EntityDragon) {
                event.ammount *= 1.05f;
            }
            if (!event.isCanceled() && EventSkill.fullArm8lvl(pl) && EventSkill.isActiveSkill(13, pl) && event.entity instanceof EntityDoppleganger) {
                event.ammount *= 1.2f;
            }
            if (!event.isCanceled() && EventSkill.fullArm8lvl(pl) && EventSkill.isActiveSkill(19, pl) && !(event.entity instanceof EntityPlayer) && EventSkill.checkCD(pl, 19) && Math.random() <= 0.1) {
                ((EntityLiving)event.entity).addPotionEffect(new PotionEffect(Potion.weakness.id, 201, 0, true));
            }
        }
    }

    public static boolean isSkillTouch(ItemStack st) {
        if (st.isItemEnchanted()) {
            NBTTagList nbt = st.getEnchantmentTagList();
            for (int i = 0; i < nbt.tagCount(); ++i) {
                NBTTagCompound n = nbt.getCompoundTagAt(i);
                if (!n.hasKey("id") || n.getInteger("id") != Enchantment.silkTouch.effectId) continue;
                return true;
            }
        }
        return false;
    }

    @SubscribeEvent
    public void FixOdin(BlockEvent.HarvestDropsEvent event) {
        if (event.block == ModBlocks.summonOdin) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent(priority=EventPriority.LOWEST)
    public void EventSkillsToo(BlockEvent.BreakEvent event) {
        if (event.block == ModBlocks.summonOdin) {
            event.setCanceled(true);
            return;
        }
        EntityPlayer pl = event.getPlayer();
        if (pl != null && !event.isCanceled() && EventSkill.fullArm8lvl(pl) && EventSkill.isActiveSkill(18, pl) && Math.random() <= 0.15 && pl.inventory.getCurrentItem() != null && !EventSkill.isSkillTouch(pl.inventory.getCurrentItem())) {
            int[] ids;
            Block _bl = event.block;
            if (event.block != null && Item.getItemFromBlock(_bl) != null && (ids = OreDictionary.getOreIDs(new ItemStack(Item.getItemFromBlock(_bl)))).length > 0 && (OreDictionary.getOreName(ids[0]).contains("ore") || OreDictionary.getOreName(ids[0]).contains("Ore")) && (_bl.getUnlocalizedName().contains("ore") || _bl.getUnlocalizedName().contains("Ore"))) {
                EntityItem _it = new EntityItem(pl.worldObj, pl.posX, pl.posY, pl.posZ, new ItemStack(Item.getItemFromBlock(_bl), 1, event.blockMetadata));
                pl.worldObj.spawnEntityInWorld(_it);
            }
        }
    }

    public static void installCD(EntityPlayer pl, int id_skill) {
        ItemStack st = pl.inventory.armorInventory[1];
        NBTTagList list = ItemNBTHelper.getList(st, "activeSkill", 10, false);
        NBTTagList _list = new NBTTagList();
        for (int i = 0; i < list.tagCount(); ++i) {
            NBTTagCompound _n = list.getCompoundTagAt(i);
            if (_n.hasKey("id") && _n.getInteger("id") == id_skill) {
                _n.setLong("cd", System.currentTimeMillis());
                _list.appendTag(_n);
                continue;
            }
            _list.appendTag(_n);
        }
    }

    public static boolean checkCD(EntityPlayer pl, int id_skill) {
        ItemStack st = pl.inventory.armorInventory[1];
        NBTTagList list = ItemNBTHelper.getList(st, "activeSkill", 10, false);
        for (int i = 0; i < list.tagCount(); ++i) {
            NBTTagCompound _n = list.getCompoundTagAt(i);
            if (!_n.hasKey("id") || _n.getInteger("id") != id_skill) continue;
            return true;
        }
        return false;
    }

    public static boolean isLetal(EntityPlayer pl, float ammount) {
        return pl.getHealth() <= ammount;
    }

    public static boolean isActiveSkill(int id_skill, EntityPlayer pl) {
        ItemStack st = pl.inventory.armorInventory[1];
        NBTTagList list = ItemNBTHelper.getList(st, "activeSkill", 10, false);
        for (int i = 0; i < list.tagCount(); ++i) {
            NBTTagCompound _n = list.getCompoundTagAt(i);
            if (!_n.hasKey("id") || _n.getInteger("id") != id_skill || !_n.hasKey("active") || !_n.getBoolean("active")) continue;
            return true;
        }
        return false;
    }

    public static boolean isNearIceBirdium(EntityPlayer pl) {
        double xc = pl.posX;
        double yc = pl.posY;
        double zc = pl.posZ;
        int RANGE = 5;
        for (int _x = (int)(xc - (double)RANGE); _x < (int)(xc + (double)RANGE + 1.0); ++_x) {
            for (int _y = (int)(yc - (double)RANGE); _y < (int)(yc + (double)RANGE + 1.0); ++_y) {
                for (int _z = (int)(zc - (double)RANGE); _z < (int)(zc + (double)RANGE + 1.0); ++_z) {
                    TileEntity te = pl.worldObj.getTileEntity(_x, _y, _z);
                    if (te == null || !(te instanceof TileSpecialFlower) || !(((TileSpecialFlower)te).getSubTile() instanceof SubTileIcebirdium)) continue;
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean fullArm8lvl(EntityPlayer pl) {
        if (EventSkill.isNearIceBirdium(pl)) {
            return false;
        }
        for (int i = 0; i < 4; ++i) {
            ItemStack st = pl.inventory.armorInventory[i];
            if (EventSkill.is8lvl(st, pl)) continue;
            return false;
        }
        return true;
    }

    public static boolean fullArm7lvl(EntityPlayer pl) {
        for (int i = 0; i < 4; ++i) {
            ItemStack st = pl.inventory.armorInventory[i];
            if (EventSkill.is7lvl(st, pl)) continue;
            return false;
        }
        return true;
    }

    public static boolean is8lvl(ItemStack stack, EntityPlayer pl) {
        if (stack != null && stack.getItem() instanceof ItemAwakeOGArmor && ItemAwakeOGArmor.isRightPlayer(pl, stack) && ItemNBTHelper.getInt(stack, "level", 0) >= 8) {
            return true;
        }
        return stack != null && stack.getItem() instanceof ItemKillerArmor && ItemKillerArmor.isRightPlayer(pl, stack);
    }

    public static boolean is7lvl(ItemStack stack, EntityPlayer pl) {
        if (stack == null) {
            return false;
        }
        if (ItemNBTHelper.getInt(stack, "level", 0) < 7) {
            return false;
        }
        return ItemAwakeOGArmor.isRightPlayer(pl, stack);
    }

    public static boolean is7lvl(ItemStack stack) {
        if (stack == null) {
            return false;
        }
        return ItemNBTHelper.getInt(stack, "level", 0) >= 7;
    }
}

