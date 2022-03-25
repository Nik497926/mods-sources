package ru.obvilion.additionpanels.common.utils;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.justagod.cutter.GradleSide;
import ru.justagod.cutter.GradleSideOnly;

import java.util.Random;
@GradleSideOnly(GradleSide.SERVER)
public class ItemStackUtils {


    public static void dropItems(TileEntity tileentity, World world){
        Random rand = new Random();
        if (tileentity instanceof IInventory) {
            IInventory inventory = (IInventory) tileentity;

            for(int i = 0; i < inventory.getSizeInventory(); ++i) {
                ItemStack item = inventory.getStackInSlot(i);
                if (item != null && item.stackSize > 0) {
                    float rx = rand.nextFloat() * 0.8F + 0.1F;
                    float ry = rand.nextFloat() * 0.8F + 0.1F;
                    float rz = rand.nextFloat() * 0.8F + 0.1F;
                    EntityItem entityItem = new EntityItem(world, (double)((float)tileentity.xCoord + rx), (double)((float)tileentity.yCoord + ry), (double)((float)tileentity.zCoord + rz), new ItemStack(item.getItem(), item.stackSize, item.getItemDamage()));
                    if (item.hasTagCompound()) {
                        entityItem.getEntityItem().setTagCompound((NBTTagCompound)item.getTagCompound().copy());
                    }

                    float factor = 0.05F;
                    entityItem.motionX = rand.nextGaussian() * (double)factor;
                    entityItem.motionY = rand.nextGaussian() * (double)factor + 0.20000000298023224D;
                    entityItem.motionZ = rand.nextGaussian() * (double)factor;
                    world.spawnEntityInWorld(entityItem);
                    item.stackSize = 0;
                }
            }

        }
    }
}
