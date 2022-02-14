/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.EnumSkyBlock
 *  net.minecraft.world.World
 */
package net.divinerpg.items.arcana;

import net.divinerpg.items.base.ItemMod;
import net.divinerpg.utils.events.Ticker;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public class ItemOrbOfLight
extends ItemMod {
    public ItemOrbOfLight() {
        super("orbOfLight");
        this.setCreativeTab(DivineRPGTabs.utility);
    }

    public void onUpdate(ItemStack stack, World world, Entity entity, int var4, boolean var5) {
        if (Ticker.tick % 2 == 0) {
            world.setLightValue(EnumSkyBlock.Block, (int)entity.posX, (int)entity.posY, (int)entity.posZ, 15);
            world.markBlockRangeForRenderUpdate((int)entity.posX, (int)entity.posY, (int)entity.posX, 12, 12, 12);
            world.markBlockForUpdate((int)entity.posX, (int)entity.posY, (int)entity.posZ);
            world.updateLightByType(EnumSkyBlock.Block, (int)entity.posX, (int)entity.posY + 1, (int)entity.posZ);
            world.updateLightByType(EnumSkyBlock.Block, (int)entity.posX + 1, (int)entity.posY + 1, (int)entity.posZ);
            world.updateLightByType(EnumSkyBlock.Block, (int)entity.posX + 1, (int)entity.posY + 1, (int)entity.posZ + 1);
            world.updateLightByType(EnumSkyBlock.Block, (int)entity.posX + 1, (int)entity.posY + 1, (int)entity.posZ - 1);
            world.updateLightByType(EnumSkyBlock.Block, (int)entity.posX - 1, (int)entity.posY + 1, (int)entity.posZ + 1);
            world.updateLightByType(EnumSkyBlock.Block, (int)entity.posX - 1, (int)entity.posY + 1, (int)entity.posZ - 1);
            world.updateLightByType(EnumSkyBlock.Block, (int)entity.posX - 1, (int)entity.posY + 1, (int)entity.posZ);
            world.updateLightByType(EnumSkyBlock.Block, (int)entity.posX, (int)entity.posY + 1, (int)entity.posZ + 1);
            world.updateLightByType(EnumSkyBlock.Block, (int)entity.posX, (int)entity.posY + 1, (int)entity.posZ - 1);
            world.updateLightByType(EnumSkyBlock.Block, (int)entity.posX, (int)entity.posY - 1, (int)entity.posZ);
            world.updateLightByType(EnumSkyBlock.Block, (int)entity.posX + 1, (int)entity.posY - 1, (int)entity.posZ);
            world.updateLightByType(EnumSkyBlock.Block, (int)entity.posX + 1, (int)entity.posY - 1, (int)entity.posZ + 1);
            world.updateLightByType(EnumSkyBlock.Block, (int)entity.posX + 1, (int)entity.posY - 1, (int)entity.posZ - 1);
            world.updateLightByType(EnumSkyBlock.Block, (int)entity.posX - 1, (int)entity.posY - 1, (int)entity.posZ + 1);
            world.updateLightByType(EnumSkyBlock.Block, (int)entity.posX - 1, (int)entity.posY - 1, (int)entity.posZ - 1);
            world.updateLightByType(EnumSkyBlock.Block, (int)entity.posX - 1, (int)entity.posY - 1, (int)entity.posZ);
            world.updateLightByType(EnumSkyBlock.Block, (int)entity.posX, (int)entity.posY - 1, (int)entity.posZ + 1);
            world.updateLightByType(EnumSkyBlock.Block, (int)entity.posX, (int)entity.posY - 1, (int)entity.posZ - 1);
            world.updateLightByType(EnumSkyBlock.Block, (int)entity.posX + 1, (int)entity.posY, (int)entity.posZ);
            world.updateLightByType(EnumSkyBlock.Block, (int)entity.posX + 1, (int)entity.posY, (int)entity.posZ + 1);
            world.updateLightByType(EnumSkyBlock.Block, (int)entity.posX + 1, (int)entity.posY, (int)entity.posZ - 1);
            world.updateLightByType(EnumSkyBlock.Block, (int)entity.posX - 1, (int)entity.posY, (int)entity.posZ + 1);
            world.updateLightByType(EnumSkyBlock.Block, (int)entity.posX - 1, (int)entity.posY, (int)entity.posZ - 1);
            world.updateLightByType(EnumSkyBlock.Block, (int)entity.posX - 1, (int)entity.posY, (int)entity.posZ);
            world.updateLightByType(EnumSkyBlock.Block, (int)entity.posX, (int)entity.posY, (int)entity.posZ + 1);
            world.updateLightByType(EnumSkyBlock.Block, (int)entity.posX, (int)entity.posY, (int)entity.posZ - 1);
        }
    }
}

