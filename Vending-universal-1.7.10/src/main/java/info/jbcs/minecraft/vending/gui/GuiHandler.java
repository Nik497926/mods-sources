/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.IGuiHandler
 *  cpw.mods.fml.common.network.NetworkRegistry
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.world.World
 */
package info.jbcs.minecraft.vending.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import java.util.ArrayList;
import java.util.Collections;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public abstract class GuiHandler
implements Comparable {
    static ArrayList<GuiHandler> items = new ArrayList();
    int index;
    Object mod;
    String name;

    public GuiHandler(String n) {
        items.add(this);
        this.name = n;
    }

    public void open(EntityPlayer player, World world, int x, int y, int z) {
        player.openGui(this.mod, this.index, world, x, y, z);
    }

    public int compareTo(Object a) {
        return this.name.compareTo(((GuiHandler)a).name);
    }

    public static void register(Object mod) {
        Collections.sort(items);
        int index = 0;
        for (GuiHandler h : items) {
            h.mod = mod;
            h.index = index++;
        }
        NetworkRegistry.INSTANCE.registerGuiHandler(mod, new IGuiHandler(){

            public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
                if (id < 0 || id >= items.size()) {
                    return null;
                }
                return items.get(id).getServerGuiElement(id, player, world, x, y, z);
            }

            public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
                if (id < 0 || id >= items.size()) {
                    return null;
                }
                return items.get(id).getClientGuiElement(id, player, world, x, y, z);
            }
        });
    }

    public abstract Object getServerGuiElement(int var1, EntityPlayer var2, World var3, int var4, int var5, int var6);

    public abstract Object getClientGuiElement(int var1, EntityPlayer var2, World var3, int var4, int var5, int var6);
}

