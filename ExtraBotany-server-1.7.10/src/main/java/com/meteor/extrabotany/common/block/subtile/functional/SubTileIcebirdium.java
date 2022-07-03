/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block.subtile.functional;

import com.meteor.extrabotany.common.lexicon.LexiconModData;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileFunctional;
import vazkii.botania.common.block.decor.IFloatingFlower;

public class SubTileIcebirdium
extends SubTileFunctional {
    private static final int RANGE_PLACE_MANA = 8;
    private static final int RANGE_PLACE = 6;
    private static final int RANGE_PLACE_Y = 6;
    private static final int RANGE_PLACE_MANA_MINI = 3;
    private static final int RANGE_PLACE_MINI = 2;
    private static final int RANGE_PLACE_Y_MINI = 2;
    private static final int DELAY = 100;
    private static final int DELAY_NO_MANA = 300;
    private String owner;
    private int cd;

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

    public BlockData getUnderlyingBlock() {
        return new BlockData(this.supertile.getWorldObj(), this.supertile.xCoord, this.supertile.yCoord - (this.supertile instanceof IFloatingFlower ? 1 : 2), this.supertile.zCoord);
    }

    public boolean acceptsRedstone() {
        return true;
    }

    public void renderHUD(Minecraft mc, ScaledResolution res) {
        super.renderHUD(mc, res);
    }

    public RadiusDescriptor getRadius() {
        return new RadiusDescriptor.Square(this.toChunkCoordinates(), this.getRange());
    }

    public int getRange() {
        return this.mana > 0 ? 8 : 6;
    }

    public int getRangeY() {
        return 6;
    }

    public int getMaxMana() {
        return 2000;
    }

    public int getColor() {
        return 16757375;
    }

    public LexiconEntry getEntry() {
        return LexiconModData.icebirdium;
    }

    static class BlockData {
        Block block;
        int meta;

        public BlockData(World world, int x, int y, int z) {
            this.block = world.getBlock(x, y, z);
            this.meta = world.getBlockMetadata(x, y, z);
        }

        public boolean equals(BlockData data) {
            return this.block == data.block && this.meta == data.meta;
        }

        public boolean equals(World world, int x, int y, int z) {
            return this.equals(new BlockData(world, x, y, z));
        }
    }

    public static class Mini
    extends SubTileIcebirdium {
        @Override
        public int getRange() {
            return this.mana > 0 ? 3 : 2;
        }

        @Override
        public int getRangeY() {
            return 2;
        }
    }
}

