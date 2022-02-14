/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Event$Result
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraft.block.Block
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 *  net.minecraftforge.event.entity.player.FillBucketEvent
 */
package net.divinerpg.utils.events;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.divinerpg.utils.blocks.VanillaBlocks;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.FillBucketEvent;

public class EventBucketFill {
    @SubscribeEvent
    public void onBucketFilledEvent(FillBucketEvent e) {
        if (e.current.getItem() != Items.bucket) {
            return;
        }
        ItemStack filledBucket = this.getLiquid(e.world, e.target);
        if (filledBucket == null) {
            return;
        }
        e.world.setBlockToAir(e.target.blockX, e.target.blockY, e.target.blockZ);
        e.result = filledBucket;
        e.setResult(Event.Result.ALLOW);
    }

    public ItemStack getLiquid(World w, MovingObjectPosition m) {
        Block block = w.getBlock(m.blockX, m.blockY, m.blockZ);
        if (block == VanillaBlocks.tar) {
            return new ItemStack(VanillaItemsOther.tarBucket);
        }
        return null;
    }
}

