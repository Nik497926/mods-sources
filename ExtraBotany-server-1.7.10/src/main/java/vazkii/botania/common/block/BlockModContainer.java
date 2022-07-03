package vazkii.botania.common.block;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import vazkii.botania.client.core.helper.IconHelper;
import vazkii.botania.common.core.BotaniaCreativeTab;
import vazkii.botania.common.item.block.ItemBlockMod;

public abstract class BlockModContainer<T extends TileEntity> extends BlockContainer {
    public int originalLight;

    protected BlockModContainer(Material par2Material) {
        super(par2Material);
        if (this.registerInCreative()) {
            this.setCreativeTab(BotaniaCreativeTab.INSTANCE);
        }

    }

    public Block setBlockName(String par1Str) {
        if (this.shouldRegisterInNameSet()) {
            GameRegistry.registerBlock(this, ItemBlockMod.class, par1Str);
        }

        return super.setBlockName(par1Str);
    }

    protected boolean shouldRegisterInNameSet() {
        return true;
    }

    public Block setLightLevel(float p_149715_1_) {
        this.originalLight = (int)(p_149715_1_ * 15.0F);
        return super.setLightLevel(p_149715_1_);
    }

    public boolean registerInCreative() {
        return true;
    }

    public abstract T createNewTileEntity(World var1, int var2);
}
