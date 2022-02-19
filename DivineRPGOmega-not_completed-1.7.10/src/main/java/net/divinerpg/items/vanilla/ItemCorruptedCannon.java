/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 */
package net.divinerpg.items.vanilla;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.divinerpg.entities.vanilla.projectile.EntityCorruptedBullet;
import net.divinerpg.items.base.ItemMod;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.TooltipLocalizer;
import net.divinerpg.utils.events.Ticker;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemCorruptedCannon
extends ItemMod {
    private Random rand = new Random();

    public ItemCorruptedCannon(String name) {
        super(name);
        this.setCreativeTab(DivineRPGTabs.ranged);
        this.setMaxStackSize(1);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }
        if ((long)Ticker.tick >= stack.getTagCompound().getLong("CanShootTime") && (player.inventory.hasItem(VanillaItemsOther.corruptedBullet) || player.capabilities.isCreativeMode)) {
            if (!world.isRemote) {
                world.playSoundAtEntity((Entity)player, Sounds.ghastCannon.getPrefixedName(), 1.0f, 1.0f);
            }
            for (int i = 0; i < 4; ++i) {
                EntityCorruptedBullet entity = new EntityCorruptedBullet(world, (EntityLivingBase)player);
                entity.posX += (this.rand.nextDouble() - this.rand.nextDouble()) / 2.0;
                entity.posY += (this.rand.nextDouble() - this.rand.nextDouble()) / 2.0;
                entity.posZ += (this.rand.nextDouble() - this.rand.nextDouble()) / 2.0;
                if (world.isRemote) continue;
                world.spawnEntityInWorld((Entity)entity);
                stack.getTagCompound().setLong("CanShootTime", (long)(Ticker.tick + 20));
            }
            if (!player.capabilities.isCreativeMode && !world.isRemote) {
                player.inventory.consumeInventoryItem(VanillaItemsOther.corruptedBullet);
            }
        }
        if (player instanceof EntityPlayerMP) {
            ((EntityPlayerMP)player).sendContainerToPlayer(player.inventoryContainer);
        }
        if (stack.getTagCompound().getLong("CanShootTime") >= 100000L || stack.getTagCompound().getLong("CanShootTime") > (long)(Ticker.tick + 141)) {
            stack.getTagCompound().setLong("CanShootTime", 0L);
        }
        return stack;
    }

    @SideOnly(value=Side.CLIENT)
    public boolean isFull3D() {
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        list.add(TooltipLocalizer.bowDam("4x10"));
        list.add(TooltipLocalizer.ammo(VanillaItemsOther.corruptedBullet));
        list.add(TooltipLocalizer.infiniteUses());
    }
}

