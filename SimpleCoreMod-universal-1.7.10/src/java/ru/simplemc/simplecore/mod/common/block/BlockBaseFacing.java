/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import ru.simplemc.simplecore.mod.common.block.BlockBase;

public class BlockBaseFacing
extends BlockBase {
    private IIcon topIcon;
    private IIcon frontIcon;
    private IIcon bottomIcon;

    public BlockBaseFacing(Material material, String name) {
        super(material, name);
    }

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase placer, ItemStack itemStack) {
        world.setBlockMetadataWithNotify(x, y, z, this.getFacingFromEntity(placer), 2);
    }

    private int getFacingFromEntity(EntityLivingBase entity) {
        return MathHelper.floor_double((double)((double)(entity.rotationYaw * 4.0f / 360.0f) + 2.5)) & 3;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return side == 1 ? this.topIcon : (side == 0 ? this.bottomIcon : (meta == 2 && side == 2 ? this.frontIcon : (meta == 3 && side == 5 ? this.frontIcon : (meta == 0 && side == 3 ? this.frontIcon : (meta == 1 && side == 4 ? this.frontIcon : this.blockIcon)))));
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.frontIcon = iconRegister.registerIcon(this.getTextureName() + "_front");
        this.blockIcon = iconRegister.registerIcon(this.getTextureName() + "_side");
        this.topIcon = iconRegister.registerIcon(this.getTextureName() + "_top");
        this.bottomIcon = iconRegister.registerIcon(this.getTextureName() + "_bottom");
    }
}

