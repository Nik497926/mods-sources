/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.block.machine;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import ru.simplemc.senergetics.SEnergetics;
import ru.simplemc.senergetics.SEnergeticsRegistry;
import ru.simplemc.senergetics.common.block.BlockContainerBase;
import ru.simplemc.senergetics.common.item.machine.ItemBlockSpawner;
import ru.simplemc.senergetics.common.item.tool.ItemSpawnerLevelUp;
import ru.simplemc.senergetics.common.tileentity.component.spawner.SpawnerDataManager;
import ru.simplemc.senergetics.common.tileentity.machine.TileEntitySpawner;
import ru.simplemc.senergetics.util.InventoryUtils;

public class BlockSpawner
extends BlockContainerBase {
    public static byte IDLE_STEP = 0;
    public static byte IN_PROGRESS_STEP = 1;
    public static byte COMPLETE_STEP = (byte)2;
    @SideOnly(value=Side.CLIENT)
    protected IIcon textureDefault;
    @SideOnly(value=Side.CLIENT)
    protected IIcon textureTop;
    @SideOnly(value=Side.CLIENT)
    protected IIcon textureTopActive;
    @SideOnly(value=Side.CLIENT)
    protected IIcon textureDefaultLvl2;
    @SideOnly(value=Side.CLIENT)
    protected IIcon textureDefaultLvl3;

    public BlockSpawner(String name) {
        super(Material.iron, name, ItemBlockSpawner.class);
        this.setStepSound(soundTypeMetal);
        this.setHardness(1.0f);
    }

    @SideOnly(value=Side.CLIENT)
    public int getRenderBlockPass() {
        return 1;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        super.registerBlockIcons(register);
        this.textureDefault = register.registerIcon("senergetics:spawner/block_silent_spawner");
        this.textureTop = register.registerIcon("senergetics:spawner/block_silent_spawner_2");
        this.textureTopActive = register.registerIcon("senergetics:spawner/block_silent_spawner_3");
        this.textureDefaultLvl2 = register.registerIcon("senergetics:spawner/block_silent_spawner_lvl_2");
        this.textureDefaultLvl3 = register.registerIcon("senergetics:spawner/block_silent_spawner_lvl_3");
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        if (side == 1) {
            return this.textureTop;
        }
        return this.textureDefault;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(IBlockAccess blockAccess, int x, int y, int z, int side) {
        SpawnerDataManager dataManager = ((TileEntitySpawner)blockAccess.getTileEntity((int)x, (int)y, (int)z)).dataManager;
        if (side == 1) {
            if (dataManager.currentStep == IDLE_STEP) {
                return this.textureTop;
            }
            return this.textureTopActive;
        }
        if (dataManager.spawnerLevel == 1) {
            return this.textureDefaultLvl2;
        }
        if (dataManager.spawnerLevel == 2) {
            return this.textureDefaultLvl3;
        }
        return super.getIcon(blockAccess, x, y, z, side);
    }

    @SideOnly(value=Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random random) {
        SpawnerDataManager spawnerDataManager = ((TileEntitySpawner)world.getTileEntity((int)x, (int)y, (int)z)).dataManager;
        if (spawnerDataManager.currentStep != IDLE_STEP && random.nextInt(5) == 1) {
            if (this == SEnergeticsRegistry.blockSilentSpawnerStandalone) {
                world.spawnParticle("reddust", (double)x + 0.5, (double)y + 0.2, (double)z + 0.5, 0.0, 0.0, 0.0);
            } else {
                world.spawnParticle("smoke", (double)x + 0.5, (double)y + 0.2, (double)z + 0.5, 0.0, 0.0, 0.0);
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    public Block setBlockTextureName(String name) {
        return super.setBlockTextureName("senergetics:spawner/block_silent_spawner");
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (tileEntity instanceof TileEntitySpawner) {
            if (!player.isSneaking()) {
                if (!world.isRemote) {
                    player.openGui((Object)SEnergetics.getInstance(), 0, world, x, y, z);
                }
                return true;
            }
            if (!world.isRemote) {
                ItemStack heldItemStack = player.getHeldItem();
                TileEntitySpawner tileEntitySpawner = (TileEntitySpawner)tileEntity;
                int spawnerLevel = tileEntitySpawner.getSpawnerLevel();
                if (!(spawnerLevel <= 0 || heldItemStack != null && heldItemStack.getItem() instanceof ItemSpawnerLevelUp)) {
                    tileEntitySpawner.onSpawnerLevelUp(spawnerLevel - 1);
                    this.dropSpawnerLevelUpgradeItem(world, x, y, z, spawnerLevel);
                    player.addChatComponentMessage((IChatComponent)new ChatComponentTranslation("message.item_spawner_upgrade_lvl_remove.text", new Object[]{spawnerLevel + 1}));
                    return true;
                }
            }
        }
        return super.onBlockActivated(world, x, y, y, player, side, hitX, hitY, hitZ);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntitySpawner();
    }

    public void breakBlock(World world, int x, int y, int z, Block block, int p_149749_6_) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (tileEntity instanceof TileEntitySpawner) {
            InventoryUtils.dropItemsFromInventory(tileEntity);
            TileEntitySpawner tileEntitySpawner = (TileEntitySpawner)tileEntity;
            int spawnerLevel = tileEntitySpawner.getSpawnerLevel();
            if (spawnerLevel > 1) {
                this.dropSpawnerLevelUpgradeItem(world, x, y, z, (byte)(spawnerLevel - 1));
            }
            this.dropSpawnerLevelUpgradeItem(world, x, y, z, spawnerLevel);
            world.func_147453_f(x, y, z, block);
        }
        super.breakBlock(world, x, y, z, block, p_149749_6_);
    }

    private void dropSpawnerLevelUpgradeItem(World world, int x, int y, int z, int spawnerLevel) {
        if (spawnerLevel > 0) {
            float f = world.rand.nextFloat() * 0.8f + 0.1f;
            float f1 = world.rand.nextFloat() * 0.8f + 0.1f;
            float f2 = world.rand.nextFloat() * 0.8f + 0.1f;
            EntityItem entityItem = new EntityItem(world, (double)((float)x + f), (double)((float)y + f1), (double)((float)z + f2), new ItemStack((Item)(spawnerLevel == 2 ? SEnergeticsRegistry.itemSpawnerLevelUp_3 : SEnergeticsRegistry.itemSpawnerLevelUp_2)));
            world.spawnEntityInWorld((Entity)entityItem);
        }
    }
}

