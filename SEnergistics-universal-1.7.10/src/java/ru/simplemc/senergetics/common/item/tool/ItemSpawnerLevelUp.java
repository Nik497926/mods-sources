/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.item.tool;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import ru.simplemc.senergetics.SEnergetics;
import ru.simplemc.senergetics.common.item.ItemBase;
import ru.simplemc.senergetics.common.tileentity.machine.TileEntitySpawner;
import ru.simplemc.senergetics.util.TranslateUtils;

public class ItemSpawnerLevelUp
extends ItemBase {
    private final int spawnerLevel;

    public ItemSpawnerLevelUp(String name, int spawnerLevel) {
        super("item_spawner_upgrade_" + name);
        this.setMaxStackSize(1);
        this.spawnerLevel = spawnerLevel;
    }

    @SideOnly(value=Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag) {
        list.addAll(TranslateUtils.translateForItemStackFormatted(this.getUnlocalizedName(stack) + ".lore", SEnergetics.getConfig().getSpawnerLevels().get(this.spawnerLevel).getEnergyUsage()));
    }

    private int getDisplayedSpawnerLevel() {
        return this.spawnerLevel + 1;
    }

    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int blockX, int blockY, int blockZ, int side, float hitX, float hitY, float hitZ) {
        boolean flag = super.onItemUse(stack, player, world, blockX, blockY, blockZ, side, hitX, hitY, hitZ);
        TileEntity tile = world.getTileEntity(blockX, blockY, blockZ);
        if (!world.isRemote && tile instanceof TileEntitySpawner) {
            ItemSpawnerLevelUp item = (ItemSpawnerLevelUp)stack.getItem();
            TileEntitySpawner tileEntitySpawner = (TileEntitySpawner)tile;
            if (tileEntitySpawner.getSpawnerLevel() == item.spawnerLevel) {
                player.addChatComponentMessage((IChatComponent)new ChatComponentTranslation("message.item_spawner_upgrade_lvl_equals.text", new Object[]{item.getDisplayedSpawnerLevel()}));
                return false;
            }
            if (tileEntitySpawner.getSpawnerLevel() < item.spawnerLevel - 1) {
                player.addChatComponentMessage((IChatComponent)new ChatComponentTranslation("message.item_spawner_upgrade_lvl_not_exists.text", new Object[]{item.getDisplayedSpawnerLevel()}));
                return false;
            }
            player.setCurrentItemOrArmor(0, null);
            tileEntitySpawner.onSpawnerLevelUp(item.spawnerLevel);
            player.addChatComponentMessage((IChatComponent)new ChatComponentTranslation("message.item_spawner_upgrade_lvl_setup.text", new Object[]{item.getDisplayedSpawnerLevel()}));
            return true;
        }
        return flag;
    }
}

