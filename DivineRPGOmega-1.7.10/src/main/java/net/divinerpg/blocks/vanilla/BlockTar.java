/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.util.IIcon
 *  net.minecraftforge.fluids.BlockFluidClassic
 */
package net.divinerpg.blocks.vanilla;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.divinerpg.DivineRPG;
import net.divinerpg.utils.LangRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraftforge.fluids.BlockFluidClassic;

public class BlockTar
extends BlockFluidClassic {
    private IIcon[] theIIcon;

    public BlockTar() {
        super(DivineRPG.tarFluid, Material.lava);
        DivineRPG.tarFluid.setBlock((Block)this);
        this.setLightLevel(1.0f);
        GameRegistry.registerBlock((Block)this, (String)"Tar");
        this.setBlockName("Tar");
        LangRegistry.addBlock((Block)this);
    }

    public IIcon getIcon(int par1, int par2) {
        return par1 != 0 && par1 != 1 ? this.theIIcon[1] : this.theIIcon[0];
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister registry) {
        this.theIIcon = new IIcon[]{registry.registerIcon("divinerpg:Tar"), registry.registerIcon("divinerpg:Tar")};
    }
}

