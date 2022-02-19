/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockTorch
 *  net.minecraft.world.World
 */
package net.divinerpg.blocks.base;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.divinerpg.DivineRPG;
import net.divinerpg.utils.LangRegistry;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockTorch;
import net.minecraft.world.World;

public class BlockModTorch
extends BlockTorch {
    private String particle;

    public BlockModTorch(String name, String particle) {
        this.setCreativeTab(DivineRPGTabs.blocks);
        this.setBlockName(name);
        this.setBlockTextureName("divinerpg:" + name);
        this.setLightLevel(1.0f);
        this.setHardness(0.0f);
        GameRegistry.registerBlock((Block)this, (String)name);
        LangRegistry.addBlock((Block)this);
        this.particle = particle;
    }

    @SideOnly(value=Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
        if (this.particle != null) {
            int l = world.getBlockMetadata(x, y, z);
            double d0 = (float)x + 0.5f;
            double d1 = (float)y + 0.7f;
            double d2 = (float)z + 0.5f;
            double d3 = 0.22f;
            double d4 = 0.27f;
            if (l == 1) {
                DivineRPG.proxy.spawnParticle(world, d0 - d4, d1 + d3, d2, this.particle, false);
            } else if (l == 2) {
                DivineRPG.proxy.spawnParticle(world, d0 + d4, d1 + d3, d2, this.particle, false);
            } else if (l == 3) {
                DivineRPG.proxy.spawnParticle(world, d0, d1 + d3, d2 - d4, this.particle, false);
            } else if (l == 4) {
                DivineRPG.proxy.spawnParticle(world, d0, d1 + d3, d2 + d4, this.particle, false);
            } else {
                DivineRPG.proxy.spawnParticle(world, d0, d1, d2, this.particle, false);
            }
        }
    }
}

