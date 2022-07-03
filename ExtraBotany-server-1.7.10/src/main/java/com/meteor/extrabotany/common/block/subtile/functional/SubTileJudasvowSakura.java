/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block.subtile.functional;

import com.meteor.extrabotany.common.lexicon.LexiconModData;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.mana.IManaItem;
import vazkii.botania.api.subtile.SubTileFunctional;

public class SubTileJudasvowSakura
extends SubTileFunctional {
    private static final int RANGE = 5;
    private static final int DELAY = 20;
    private int cd = 0;
    private String owner = "";

    public LexiconEntry getEntry() {
        return LexiconModData.judasvow;
    }

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
        if (!(entity instanceof EntityPlayer)) {
            return;
        }
        this.owner = ((EntityPlayer)entity).getCommandSenderName();
        super.onBlockPlacedBy(world, x, y, z, entity, stack);
    }

    public void writeToPacketNBT(NBTTagCompound cmp) {
        super.writeToPacketNBT(cmp);
        cmp.setString("owner", this.owner);
    }

    public void readFromPacketNBT(NBTTagCompound cmp) {
        super.readFromPacketNBT(cmp);
        this.owner = cmp.getString("owner");
    }

    public int getColor() {
        return 509223;
    }

    private ItemStack getItemForCharge(EntityPlayer pl) {
        boolean find = false;
        IInventory baublesInv = BotaniaAPI.internalHandler.getBaublesInventory(pl);
        for (int i = 0; i < baublesInv.getSizeInventory(); ++i) {
            int mx;
            int mn;
            ItemStack var0 = baublesInv.getStackInSlot(i);
            if (var0 == null || !(var0.getItem() instanceof IManaItem) || (mn = ((IManaItem)var0.getItem()).getMana(var0)) >= (mx = ((IManaItem)var0.getItem()).getMaxMana(var0))) continue;
            return var0;
        }
        return null;
    }

    public int getDelayBetweenPassiveGeneration() {
        return 10;
    }

    public int getMaxMana() {
        return 5000;
    }
}

