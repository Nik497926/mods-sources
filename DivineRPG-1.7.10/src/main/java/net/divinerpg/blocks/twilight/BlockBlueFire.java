/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.World
 */
package net.divinerpg.blocks.twilight;

import net.divinerpg.blocks.base.BlockModFire;
import net.divinerpg.blocks.base.portal.BlockModPortal;
import net.divinerpg.utils.LogHelper;
import net.divinerpg.utils.blocks.JourneyBlocks;
import net.divinerpg.utils.blocks.TwilightBlocks;
import net.divinerpg.utils.blocks.VanillaBlocks;
import net.minecraft.world.World;

public class BlockBlueFire
extends BlockModFire {
    public BlockBlueFire(String name) {
        super(name);
    }

    @Override
    protected void lightPortal(World world, int x, int y, int z) {
        BlockModPortal portal = (BlockModPortal)TwilightBlocks.edenPortal;
        if (world.getBlock(x, y - 1, z).equals(VanillaBlocks.divineRock)) {
            LogHelper.debug("Divine Rock lit");
            if (!portal.tryCreatePortal(world, x, y, z)) {
                LogHelper.debug("Eden portal did not light");
            }
        }
        portal = (BlockModPortal)TwilightBlocks.wildwoodPortal;
        if (world.getBlock(x, y - 1, z).equals(TwilightBlocks.edenBlock)) {
            LogHelper.debug("Eden block lit");
            if (!portal.tryCreatePortal(world, x, y, z)) {
                LogHelper.debug("Wildwood portal did not light");
            }
        }
        portal = (BlockModPortal)TwilightBlocks.lleyetiaPortal;
        if (world.getBlock(x, y - 1, z).equals(JourneyBlocks.LelyetiaPortalFrame)) {
            LogHelper.debug("Lelyetia block lit");
            if (!portal.tryCreatePortal(world, x, y, z)) {
                LogHelper.debug("Lelyetia portal did not light");
            }
        }
        portal = (BlockModPortal)TwilightBlocks.apalachiaPortal;
        if (world.getBlock(x, y - 1, z).equals(TwilightBlocks.wildwoodBlock)) {
            LogHelper.debug("Wildwood block lit");
            if (!portal.tryCreatePortal(world, x, y, z)) {
                LogHelper.debug("Apalachia portal did not light");
            }
        }
        portal = (BlockModPortal)TwilightBlocks.skythernPortal;
        if (world.getBlock(x, y - 1, z).equals(TwilightBlocks.apalachiaBlock)) {
            LogHelper.debug("Apalachia block lit");
            if (!portal.tryCreatePortal(world, x, y, z)) {
                LogHelper.debug("Skythern portal did not light");
            }
        }
        portal = (BlockModPortal)TwilightBlocks.mortumPortal;
        if (world.getBlock(x, y - 1, z).equals(TwilightBlocks.skythernBlock)) {
            LogHelper.debug("Skythern block lit");
            if (!portal.tryCreatePortal(world, x, y, z)) {
                LogHelper.debug("Mortum portal did not light");
            }
        }
        portal = (BlockModPortal)TwilightBlocks.eucaPortal;
        if (world.getBlock(x, y - 1, z).equals(JourneyBlocks.eucaPortalFrame)) {
            LogHelper.debug("Euca block lit");
            if (!portal.tryCreatePortal(world, x, y, z)) {
                LogHelper.debug("Euca portal did not light");
            }
        }
        portal = (BlockModPortal)TwilightBlocks.boilingPortal;
        if (world.getBlock(x, y - 1, z).equals(JourneyBlocks.boilingPortalFrame)) {
            LogHelper.debug("Boiling block lit");
            if (!portal.tryCreatePortal(world, x, y, z)) {
                LogHelper.debug("Boiling portal did not light");
            }
        }
    }
}

