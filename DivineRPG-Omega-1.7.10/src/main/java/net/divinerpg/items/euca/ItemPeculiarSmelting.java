/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package net.divinerpg.items.euca;

import net.divinerpg.entities.euca.EntityCorallator;
import net.divinerpg.items.base.ItemMod;
import net.divinerpg.utils.Util;
import net.divinerpg.utils.config.ConfigurationHelper;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemPeculiarSmelting
extends ItemMod {
    public ItemPeculiarSmelting(String name) {
        super(name);
        this.setMaxStackSize(1);
        this.setCreativeTab(DivineRPGTabs.spawner);
    }

    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10) {
        if (world.provider.dimensionId != ConfigurationHelper.euca) {
            player.addChatMessage(Util.getChatComponent(Util.AQUA + StatCollector.translateToLocal((String)"item.eucacall")));
        }
        if (!world.isRemote && world.provider.dimensionId == ConfigurationHelper.euca) {
            EntityCorallator entity = new EntityCorallator(world);
            entity.setPosition(x, y + 1, z);
            if (world.getCollidingBoundingBoxes((Entity)entity, entity.boundingBox).isEmpty()) {
                world.spawnEntityInWorld((Entity)entity);
                if (!player.capabilities.isCreativeMode) {
                    --stack.stackSize;
                }
            }
        }
        return true;
    }
}

