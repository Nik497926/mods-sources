/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import ru.simplemc.simplecore.mod.common.block.BlockBase;
import ru.simplemc.simplecore.mod.common.block.BlockTradeStation;

public class BlockTradeStationCap
extends BlockBase {
    public BlockTradeStationCap() {
        super(Material.glass, "trade_station_cap");
        this.setStepSound(soundTypeGlass);
        this.setLightLevel(0.9f);
        this.setHardness(0.5f);
    }

    @SideOnly(value=Side.CLIENT)
    public int getRenderBlockPass() {
        return 0;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        Block block = world.getBlock(x, y - 1, z);
        if (block instanceof BlockTradeStation) {
            return block.onBlockActivated(world, x, y - 1, z, player, side, hitX, hitY, hitZ);
        }
        return false;
    }
}

