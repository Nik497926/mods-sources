/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod.common.block;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import ru.simplemc.simplecore.mod.SimpleCore;
import ru.simplemc.simplecore.mod.common.block.BlockBaseContainer;
import ru.simplemc.simplecore.mod.common.block.BlockTradeStationCap;
import ru.simplemc.simplecore.mod.common.tileentity.TileEntityTradeStation;

public class BlockTradeStation
extends BlockBaseContainer
implements ITileEntityProvider {
    public BlockTradeStation() {
        super(Material.wood, "trade_station");
        this.setStepSound(soundTypeWood);
        this.setLightLevel(0.9f);
        this.setHardness(0.9f);
        GameRegistry.registerTileEntity(TileEntityTradeStation.class, (String)this.getUnlocalizedName());
    }

    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityTradeStation();
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase placer, ItemStack itemStack) {
        super.onBlockPlacedBy(world, x, y, z, placer, itemStack);
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (tileEntity instanceof TileEntityTradeStation) {
            ((TileEntityTradeStation)tileEntity).getData().setOwner(placer.getCommandSenderName());
        }
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        ItemStack heldItemStack = player.getHeldItem();
        if (heldItemStack != null && side == 1 && heldItemStack.getItem() instanceof ItemBlock && ((ItemBlock)heldItemStack.getItem()).field_150939_a instanceof BlockTradeStationCap) {
            return false;
        }
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (tileEntity instanceof TileEntityTradeStation) {
            if (!world.isRemote) {
                TileEntityTradeStation tradeStation = (TileEntityTradeStation)tileEntity;
                if (tradeStation.isAlreadyUsed()) {
                    player.addChatComponentMessage((IChatComponent)new ChatComponentTranslation("message.trade_station.already_used", new Object[0]));
                } else if (tradeStation.getData().hasInventoryAccess(player) && !player.isSneaking()) {
                    player.openGui((Object)SimpleCore.INSTANCE, 1, world, tradeStation.xCoord, tradeStation.yCoord, tradeStation.zCoord);
                } else {
                    player.openGui((Object)SimpleCore.INSTANCE, 0, world, tradeStation.xCoord, tradeStation.yCoord, tradeStation.zCoord);
                }
            }
            return true;
        }
        return false;
    }

    public void breakBlock(World world, int x, int y, int z, Block block, int side) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if (tileEntity instanceof TileEntityTradeStation) {
            TileEntityTradeStation tradeStation = (TileEntityTradeStation)tileEntity;
            for (int slotIndex : tradeStation.getInventory().getAccessibleSlotsFromSide(0)) {
                ItemStack itemstack = tradeStation.getInventory().getStackInSlot(slotIndex);
                if (itemstack == null) continue;
                float f = world.rand.nextFloat() * 0.8f + 0.1f;
                float f1 = world.rand.nextFloat() * 0.8f + 0.1f;
                float f2 = world.rand.nextFloat() * 0.8f + 0.1f;
                while (itemstack.stackSize > 0) {
                    int j1 = world.rand.nextInt(21) + 10;
                    if (j1 > itemstack.stackSize) {
                        j1 = itemstack.stackSize;
                    }
                    itemstack.stackSize -= j1;
                    EntityItem entityitem = new EntityItem(world, (double)((float)x + f), (double)((float)y + f1), (double)((float)z + f2), new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));
                    if (itemstack.hasTagCompound()) {
                        entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
                    }
                    float f3 = 0.05f;
                    entityitem.motionX = (float)world.rand.nextGaussian() * f3;
                    entityitem.motionY = (float)world.rand.nextGaussian() * f3 + 0.2f;
                    entityitem.motionZ = (float)world.rand.nextGaussian() * f3;
                    world.spawnEntityInWorld((Entity)entityitem);
                }
            }
            world.func_147453_f(x, y, z, block);
        }
        super.breakBlock(world, x, y, z, block, side);
    }
}

