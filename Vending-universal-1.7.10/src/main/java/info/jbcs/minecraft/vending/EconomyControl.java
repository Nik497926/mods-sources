/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.CompressedStreamTools
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraftforge.common.DimensionManager
 *  net.minecraftforge.event.entity.EntityJoinWorldEvent
 *  net.minecraftforge.event.world.WorldEvent$Load
 *  net.minecraftforge.event.world.WorldEvent$Save
 */
package info.jbcs.minecraft.vending;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import info.jbcs.minecraft.vending.CustomStack;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.world.WorldEvent;

public class EconomyControl {
    public static boolean hasChanges;
    public static HashMap<CustomStack, Integer> prices;

    public static CustomStack loadFromNBT(NBTTagCompound tag) {
        CustomStack stack = new CustomStack(tag.getString("name"), tag.getShort("damage"));
        if (tag.hasKey("tag")) {
            stack.tag = tag.getCompoundTag("tag");
        }
        return stack;
    }

    public static NBTTagCompound writeToNBT(CustomStack stack) {
        if (stack == null) {
            return null;
        }
        NBTTagCompound p_77955_1_ = new NBTTagCompound();
        p_77955_1_.setString("name", stack.name);
        p_77955_1_.setShort("damage", (short)stack.damage);
        if (stack.tag != null) {
            p_77955_1_.setTag("tag", (NBTBase)stack.tag);
        }
        return p_77955_1_;
    }

    public static int getMinPrice(ItemStack[] soldItems) {
        int res = 0;
        for (ItemStack i : soldItems) {
            res += EconomyControl.getMinPrice(i);
        }
        return res;
    }

    public static void setMinPrice(ItemStack currentEquippedItem, int price) {
        prices.put(new CustomStack(currentEquippedItem), price);
    }

    public static void setNBTTagCompoud(NBTTagList prices2) {
        for (int i = 0; i < prices2.tagCount(); ++i) {
            NBTTagCompound tag = prices2.getCompoundTagAt(i);
            int price = tag.getInteger("price");
            prices.put(EconomyControl.loadFromNBT(tag), price);
        }
    }

    public static NBTTagCompound getNbtTagCompound() {
        NBTTagList prices2 = new NBTTagList();
        for (Map.Entry<CustomStack, Integer> i : prices.entrySet()) {
            CustomStack stack = i.getKey();
            NBTTagCompound tag = EconomyControl.writeToNBT(stack);
            tag.setInteger("price", i.getValue().intValue());
            prices2.appendTag((NBTBase)tag);
        }
        NBTTagCompound root = new NBTTagCompound();
        root.setTag("list", (NBTBase)prices2);
        return root;
    }

    public static Item getItem(String s) {
        String modId = "minecraft";
        if (s.indexOf(":") != -1) {
            modId = s.substring(0, s.indexOf(":"));
        }
        String name = s.substring(s.indexOf(":") + 1);
        return GameRegistry.findItem((String)modId, (String)name);
    }

    public static int getMinPrice(ItemStack stack) {
        if (stack != null) {
            CustomStack st = new CustomStack(stack);
            return prices.containsKey(st) ? prices.get(st) : 0;
        }
        return 0;
    }

    static {
        prices = new HashMap();
    }

    public static class EventLoad {
        @SubscribeEvent
        public void onLogin(EntityJoinWorldEvent e) {
            if (e.entity instanceof EntityPlayer) {
                // empty if block
            }
        }

        @SubscribeEvent
        public void onSave(WorldEvent.Save e) {
            if (!e.world.isRemote && e.world.provider.dimensionId == 0) {
                try {
                    String path = DimensionManager.getCurrentSaveRootDirectory().getCanonicalPath() + "/prices/";
                    File configFolder = new File(path);
                    configFolder.mkdir();
                    NBTTagCompound root = EconomyControl.getNbtTagCompound();
                    FileOutputStream fos = new FileOutputStream(path + "prices.data");
                    CompressedStreamTools.writeCompressed((NBTTagCompound)root, (OutputStream)fos);
                    fos.close();
                }
                catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
                catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }

        @SubscribeEvent
        public void onLoad(WorldEvent.Load e) {
            if (!e.world.isRemote && e.world.provider.dimensionId == 0) {
                try {
                    String path = DimensionManager.getCurrentSaveRootDirectory().getCanonicalPath() + "/prices/";
                    FileInputStream fis = new FileInputStream(path + "prices.data");
                    NBTTagList prices2 = (NBTTagList)CompressedStreamTools.readCompressed((InputStream)fis).getTag("list");
                    fis.close();
                    EconomyControl.setNBTTagCompoud(prices2);
                }
                catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
                catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}

