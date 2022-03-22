/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block.subtile.generating;

import com.meteor.extrabotany.common.lexicon.LexiconModData;
import java.util.List;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import thaumcraft.common.items.ItemCrystalEssence;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileGenerating;
import vazkii.botania.common.core.helper.ItemNBTHelper;

public class SubTileBellflower
extends SubTileGenerating {
    private static final int RANGE = 4;
    private static final int DELAY = 15;
    private static int blockCount;
    protected int mana;
    private int timer = 0;
    public String[][] asp = new String[][]{{"aurum", "700"}, {"desidia", "900"}, {"instrumentum", "1200"}, {"invidia", "900"}, {"luxuria", "900"}, {"perfodio", "900"}, {"praecantatio", "1200"}, {"sano", "3000"}, {"vitium", "1500"}, {"tutamen", "900"}, {"venenum", "1000"}, {"tempestas", "1500"}};

    public int getColor() {
        return 15856529;
    }

    public void onUpdate() {
        super.onUpdate();
        ++this.timer;
        World world = this.supertile.getWorldObj();
        int x = this.supertile.xCoord;
        int y = this.supertile.yCoord;
        int z = this.supertile.zCoord;
        if (this.timer >= 40) {
            this.timer = 0;
            List<EntityItem> items = this.supertile.getWorldObj().getEntitiesWithinAABB(EntityItem.class, AxisAlignedBB.getBoundingBox(x - 4, y - 4, z - 4, x + 4 + 1, y + 4, z + 4 + 1));
            int finishMana = 0;
            EntityItem item = null;
            Boolean needFound = false;
            for (EntityItem item0 : items) {
                if (item0 == null || item0.isDead || !(item0.getEntityItem().getItem() instanceof ItemCrystalEssence) || needFound.booleanValue()) continue;
                item = item0;
                needFound = true;
            }
            if (item != null && this.mana != this.getMaxMana()) {
                NBTTagCompound var0 = ItemNBTHelper.getNBT(item.getEntityItem());
                NBTTagList var1 = var0.getTagList("Aspects", 10);
                NBTTagCompound var2 = var1.getCompoundTagAt(0);
                String s = var2.getString("key");
                Boolean findAsp = false;
                for (String[] as : this.asp) {
                    if (s != as[0]) continue;
                    findAsp = true;
                    finishMana = Integer.parseInt(as[1]);
                }
                if (!findAsp.booleanValue()) {
                    finishMana = 300;
                }
                int xc = this.supertile.xCoord;
                int yc = this.supertile.yCoord;
                int zc = this.supertile.zCoord;
                ItemStack ent = item.getEntityItem();
                --ent.stackSize;
                if (world.isRemote) {
                    world.spawnParticle("largesmoke", item.posX, item.posY + 0.1, item.posZ, 0.0, 0.0, 0.0);
                    world.spawnParticle("flame", item.posX, item.posY, item.posZ, 0.0, 0.0, 0.0);
                }
                this.addMana(finishMana);
                return;
            }
        }
    }

    public LexiconEntry getEntry() {
        return LexiconModData.bellflower;
    }

    public int getDelayBetweenPassiveGeneration() {
        return 10;
    }

    public RadiusDescriptor getRadius() {
        return new RadiusDescriptor.Square(this.toChunkCoordinates(), 4);
    }

    public int getMaxMana() {
        return 200;
    }
}

