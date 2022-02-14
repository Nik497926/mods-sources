/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package net.divinerpg.items.arcana;

import java.util.List;
import net.divinerpg.entities.arcana.EntityWraith;
import net.divinerpg.items.base.ItemMod;
import net.divinerpg.utils.TooltipLocalizer;
import net.divinerpg.utils.Util;
import net.divinerpg.utils.events.ArcanaHelper;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemGhostbane
extends ItemMod {
    public ItemGhostbane() {
        super("ghostbane", DivineRPGTabs.spawner);
        this.setMaxStackSize(1);
    }

    public boolean onItemUse(ItemStack i, EntityPlayer p, World w, int x, int y, int z, int i1, float j, float k, float l) {
        EntityWraith wraith = new EntityWraith(w, p);
        if (!w.isRemote && ArcanaHelper.getProperties(p).useBar(200.0f)) {
            wraith.setLocationAndAngles(x, y + 1, z, 0.0f, 0.0f);
            w.spawnEntityInWorld((Entity)wraith);
            return true;
        }
        return false;
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        list.add(TooltipLocalizer.arcanaConsumed(200));
        list.add(Util.GOLD + StatCollector.translateToLocal((String)"item.ghostbane1"));
        list.add(Util.BLUE + StatCollector.translateToLocal((String)"item.ghostbane2"));
        list.add(Util.RED + StatCollector.translateToLocal((String)"item.ghostbane3"));
        list.add(Util.GOLD + StatCollector.translateToLocal((String)"item.ghostbane4"));
    }
}

