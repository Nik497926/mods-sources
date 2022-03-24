package mireille.network;

import io.netty.buffer.*;
import cpw.mods.fml.common.network.*;
import net.minecraft.nbt.*;
import cpw.mods.fml.common.network.simpleimpl.*;
import mireille.common.tileentity.*;
import mireille.core.*;
import net.minecraft.item.*;

public class PacketSkinWorkbench implements IMessage
{
    public int buttonID;
    public int x;
    public int y;
    public int z;
    public int skinID;
    public String name;
    
    public PacketSkinWorkbench() {
    }
    
    public PacketSkinWorkbench(final int buttonID, final int x, final int y, final int z, final int skinID, final String name) {
        this.buttonID = buttonID;
        this.x = x;
        this.y = y;
        this.z = z;
        this.skinID = skinID;
        this.name = name;
    }
    
    public void fromBytes(final ByteBuf buf) {
        final NBTTagCompound nbt = ByteBufUtils.readTag(buf);
        this.buttonID = nbt.getInteger("buttonID");
        this.x = nbt.getInteger("x");
        this.y = nbt.getInteger("y");
        this.z = nbt.getInteger("z");
        this.skinID = nbt.getInteger("skinID");
        this.name = nbt.getString("name");
    }
    
    public void toBytes(final ByteBuf buf) {
        final NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("buttonID", this.buttonID);
        nbt.setInteger("x", this.x);
        nbt.setInteger("y", this.y);
        nbt.setInteger("z", this.z);
        nbt.setInteger("skinID", this.skinID);
        nbt.setString("name", this.name);
        ByteBufUtils.writeTag(buf, nbt);
    }
    
    public static class Handler implements IMessageHandler<PacketSkinWorkbench, IMessage>
    {
        public IMessage onMessage(final PacketSkinWorkbench message, final MessageContext ctx) {
            if (ctx.getServerHandler().playerEntity.worldObj.getTileEntity(message.x, message.y, message.z) instanceof TileSkinWorkbench) {
                final TileSkinWorkbench tile = (TileSkinWorkbench)ctx.getServerHandler().playerEntity.worldObj.getTileEntity(message.x, message.y, message.z);
                ModItems.AllSkins[0] = new ItemStack(com.brandon3055.draconicevolution.common.ModItems.draconicDestructionStaff);
                if (message.buttonID == 0) {
                    final ItemStack itemSlot0 = tile.getStackInSlot(0);
                    if (itemSlot0 != null) {
                        final int[] copyFrom = itemSlot0.getTagCompound().getIntArray("allSkins");
                        if (!this.checkArray(copyFrom, 0)) {
                            final ItemStack result = new ItemStack(com.brandon3055.draconicevolution.common.ModItems.draconicDestructionStaff);
                            result.setTagCompound((NBTTagCompound)itemSlot0.getTagCompound().copy());
                            if (openSkin(result, itemSlot0)) {
                                tile.setInventorySlotContents(0, result);
                            }
                        }
                    }
                }
                else if (message.buttonID == 1) {
                    final ItemStack itemSlot0 = tile.getStackInSlot(0);
                    final ItemStack result = tile.getStackInSlot(1);
                    if (itemSlot0 != null && result != null) {
                        final ItemStack result2 = new ItemStack(com.brandon3055.draconicevolution.common.ModItems.draconicDestructionStaff);
                        result2.setTagCompound((NBTTagCompound)itemSlot0.getTagCompound().copy());
                        if (openSkin(result2, result)) {
                            tile.setInventorySlotContents(0, result2);
                            tile.setInventorySlotContents(1, null);
                        }
                    }
                }
                else if (message.buttonID == 2) {
                    final ItemStack itemSlot0 = tile.getStackInSlot(0);
                    if (itemSlot0 != null) {
                        final ItemStack result = new ItemStack(com.brandon3055.draconicevolution.common.ModItems.draconicDestructionStaff);
                        result.setTagCompound((NBTTagCompound)itemSlot0.getTagCompound().copy());
                        setSkin(result, message.skinID);
                        result.setStackDisplayName(message.name);
                        tile.setInventorySlotContents(0, result);
                    }
                }
            }
            return null;
        }
        
        public boolean checkArray(final int[] array, final int n) {
            for (final int value : array) {
                if (value == n) {
                    return true;
                }
            }
            return false;
        }
        
        public static void setSkin(final ItemStack itemstack, final int skinID) {
            if (!itemstack.hasTagCompound()) {
                itemstack.setTagCompound(new NBTTagCompound());
            }
            itemstack.getTagCompound().setInteger("Skin", skinID);
        }
        
        public static boolean openSkin(final ItemStack itemstack, final ItemStack skin) {
            if (!itemstack.hasTagCompound()) {
                itemstack.setTagCompound(new NBTTagCompound());
            }
            final int[] copyFrom = itemstack.getTagCompound().getIntArray("allSkins");
            final int[] copyTo = new int[copyFrom.length + 1];
            for (int skinID = 0; skinID < copyFrom.length; ++skinID) {
                copyTo[skinID] = copyFrom[skinID];
            }
            int skinID = -1;
            for (int i = 0; i < ModItems.AllSkins.length; ++i) {
                final String str1 = ModItems.AllSkins[i].getUnlocalizedName();
                final String str2 = skin.getUnlocalizedName();
                if (str1.equals(str2)) {
                    skinID = i;
                    break;
                }
            }
            boolean haveSkin = false;
            for (final int value : copyFrom) {
                if (value == skinID) {
                    haveSkin = true;
                    break;
                }
            }
            if (!haveSkin) {
                copyTo[copyTo.length - 1] = skinID;
                itemstack.getTagCompound().setIntArray("allSkins", copyTo);
                return true;
            }
            return false;
        }
    }
}
