/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block.subtile.functional;

import com.meteor.extrabotany.common.lexicon.LexiconModData;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileFunctional;

public class SubTileDiplopbamboo
extends SubTileFunctional {
    private final int cd = 0;
    private final int stage = 0;
    private final int[] lastCoord = new int[0];
    private final int NEED_MANA = 50;
    private String owner;

    public LexiconEntry getEntry() {
        return LexiconModData.diplopbamboo;
    }

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack) {
        if (!(entity instanceof EntityPlayer)) {
            return;
        }
        this.owner = entity.getCommandSenderName();
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
        return 0x664422;
    }

    public int getMaxMana() {
        return 6000;
    }

    public RadiusDescriptor getRadius() {
        return new RadiusDescriptor.Square(this.toChunkCoordinates(), 1);
    }
}

