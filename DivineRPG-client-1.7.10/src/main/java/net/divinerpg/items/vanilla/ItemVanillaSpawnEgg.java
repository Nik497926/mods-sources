/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.stats.StatBase
 *  net.minecraft.world.World
 */
package net.divinerpg.items.vanilla;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.divinerpg.entities.vanilla.EntityEhu;
import net.divinerpg.entities.vanilla.EntityHusk;
import net.divinerpg.entities.vanilla.EntitySmelter;
import net.divinerpg.entities.vanilla.EntitySnapper;
import net.divinerpg.entities.vanilla.EntityStoneGolem;
import net.divinerpg.items.base.IDivineMetaItem;
import net.divinerpg.items.base.ItemMod;
import net.divinerpg.libs.DivineRPGAchievements;
import net.divinerpg.utils.LangRegistry;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatBase;
import net.minecraft.world.World;

public class ItemVanillaSpawnEgg
extends ItemMod
implements IDivineMetaItem {
    private Class[] mobs = new Class[]{EntityEhu.class, EntityHusk.class, EntityStoneGolem.class, EntitySmelter.class, EntitySnapper.class};

    public ItemVanillaSpawnEgg() {
        super("overworldPetSpawner", DivineRPGTabs.spawner);
        this.setMaxStackSize(1);
        this.setTextureName("divinerpg:overworldSpawnEgg");
        this.setHasSubtypes(true);
    }

    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            try {
                Entity e = (Entity)this.mobs[stack.getItemDamage()].getConstructor(World.class, EntityPlayer.class).newInstance(world, player);
                e.setLocationAndAngles((double)x, (double)(y + 1), (double)z, 0.0f, 0.0f);
                world.spawnEntityInWorld(e);
                player.triggerAchievement((StatBase)DivineRPGAchievements.petCollector);
                --stack.stackSize;
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }

    public String getUnlocalizedName(ItemStack stack) {
        String str = (String)EntityList.classToStringMapping.get(this.mobs[stack.getItemDamage()]);
        return "item." + str.substring(4) + "Egg";
    }

    @SideOnly(value=Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < this.mobs.length; ++i) {
            list.add(new ItemStack(item, 1, i));
        }
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        Item item = stack.getItem();
        list.add("Spawns a pet " + ((String)EntityList.classToStringMapping.get(this.mobs[stack.getItemDamage()])).substring(4));
    }

    @Override
    public void addNames() {
        for (int i = 0; i < this.mobs.length; ++i) {
            LangRegistry.instance.localizeName("item", this.getUnlocalizedName(new ItemStack((Item)this, 1, i)));
        }
    }
}

