/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.block.subtile.functional;

import com.meteor.extrabotany.common.block.subtile.functional.SubTileFunctional;
import com.meteor.extrabotany.common.lexicon.LexiconModData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.items.wands.ItemWandCasting;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.common.Botania;

public class SubTileJudasvow
extends SubTileFunctional {
    private static final int RANGE = 9;
    private static final String TAG_NAME = "playername";
    int cd = 0;

    @Override
    public int getColor() {
        return 16406359;
    }

    @Override
    public int getMaxMana() {
        return 50000;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (this.cd > 0) {
            --this.cd;
            return;
        }
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        ItemStack itemStack = player.getCurrentEquippedItem();
        if (itemStack != null && itemStack.getItem() instanceof ItemWandCasting && this.cd == 0 && this.mana >= 1500) {
            int i;
            ItemWandCasting wand = (ItemWandCasting)itemStack.getItem();
            int max = wand.getMaxVis(itemStack);
            Aspect[] asp = new Aspect[]{Aspect.AIR, Aspect.EARTH, Aspect.FIRE, Aspect.WATER, Aspect.ORDER, Aspect.ENTROPY};
            for (i = 0; i < asp.length; ++i) {
                int aspect = wand.getVis(itemStack, asp[i]);
                if (aspect >= max || this.mana < 1500) continue;
                wand.addRealVis(itemStack, asp[i], 1000, true);
                this.mana -= 1500;
            }
            this.cd = 200;
            for (i = 0; i < 50; ++i) {
                float red = (float)Math.random();
                float green = (float)Math.random();
                float blue = (float)Math.random();
                Botania.proxy.wispFX(this.supertile.getWorldObj(), (double)this.supertile.xCoord + 0.5, (double)this.supertile.yCoord + 0.5, (double)this.supertile.zCoord + 0.5, red, green, blue, (float)Math.random() * 0.15f + 0.15f, (float)(Math.random() - 0.5) * 0.25f, (float)(Math.random() - 0.5) * 0.25f, (float)(Math.random() - 0.5) * 0.25f);
            }
            return true;
        }
        return false;
    }

    public LexiconEntry getEntry() {
        return LexiconModData.judasvow;
    }

    public boolean usesMana(ItemStack arg0) {
        return true;
    }
}

