/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import ru.simplemc.senergetics.SEnergetics;
import ru.simplemc.senergetics.common.inventory.container.ContainerTileEntity;
import ru.simplemc.senergetics.common.tileentity.TileEntityMachine;
import ru.simplemc.senergetics.util.InventoryUtils;

public abstract class BlockMachineBase
extends BlockContainerBaseFacing {
    private static int LAST_GUI_ID = 0;
    protected final int guiId;
    protected final boolean hasTopTexture;
    protected IIcon frontIconActive;
    protected IIcon topIconActive;

    public BlockMachineBase(String name, int guiId, Class<? extends ItemBlock> itemClass) {
        this(name, guiId, false, itemClass);
    }

    public BlockMachineBase(String name, int guiId, boolean hasTopTexture, Class<? extends ItemBlock> itemClass) {
        super(Material.iron, name, itemClass);
        this.setStepSound(soundTypeMetal);
        this.setHardness(1.0f);
        this.guiId = guiId;
        this.hasTopTexture = hasTopTexture;
        if (LAST_GUI_ID == guiId) {
            throw new RuntimeException("GuiId " + guiId + " (" + LAST_GUI_ID + ") for " + ((Object)((Object)this)).getClass().getSimpleName() + " already used!");
        }
        LAST_GUI_ID = guiId;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.blockIcon = iconRegister.registerIcon("senergetics:block_machine_side");
        this.frontIcon = iconRegister.registerIcon(this.getTextureName() + "_front");
        this.frontIconActive = iconRegister.registerIcon(this.getTextureName() + "_front_active");
        this.topIcon = this.hasTopTexture ? iconRegister.registerIcon(this.getTextureName() + "_top") : this.blockIcon;
        this.topIconActive = this.hasTopTexture ? iconRegister.registerIcon(this.getTextureName() + "_top_active") : this.blockIcon;
        this.bottomIcon = this.blockIcon;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(IBlockAccess blockAccess, int posX, int posY, int posZ, int side) {
        TileEntity tileEntity = blockAccess.getTileEntity(posX, posY, posZ);
        if (tileEntity instanceof TileEntityMachine && ((TileEntityMachine)tileEntity).isActive()) {
            if (side == 1) {
                return this.topIconActive;
            }
            int meta = tileEntity.getBlockMetadata();
            if (meta == 2 && side == 2 || meta == 3 && side == 5 || meta == 0 && side == 3 || meta == 1 && side == 4) {
                return this.frontIconActive;
            }
        }
        return super.getIcon(blockAccess, posX, posY, posZ, side);
    }

    public boolean onBlockActivated(World world, int posX, int posY, int posZ, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if (!player.isSneaking() && world.getTileEntity(posX, posY, posZ) instanceof TileEntityMachine) {
            if (!world.isRemote) {
                player.openGui((Object)SEnergetics.getInstance(), this.guiId, world, posX, posY, posZ);
            }
            return true;
        }
        return super.onBlockActivated(world, posX, posY, posY, player, side, hitX, hitY, hitZ);
    }

    public List<ItemStack> generateBlockDrops(TileEntityMachine<?> tileEntity) {
        return InventoryUtils.generateDropsFromTileEntity(tileEntity);
    }

    public void breakBlock(World world, int posX, int posY, int posZ, Block block, int p_149749_6_) {
        TileEntity tileEntity = world.getTileEntity(posX, posY, posZ);
        if (tileEntity instanceof TileEntityMachine) {
            InventoryUtils.dropItemsAtPos(this.generateBlockDrops((TileEntityMachine)tileEntity), world, posX, posY, posZ);
            world.func_147453_f(posX, posY, posZ, block);
        }
        super.breakBlock(world, posX, posY, posZ, block, p_149749_6_);
    }

    protected abstract TileEntityMachine<?> getTileEntityMachineAt(World var1, int var2, int var3, int var4);

    public abstract ContainerTileEntity<?> createContainer(World var1, int var2, int var3, int var4, EntityPlayer var5);

    public int getGuiId() {
        return this.guiId;
    }
}

