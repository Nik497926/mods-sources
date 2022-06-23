/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.item.machine.upgrade;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.item.IItemHudInfo;
import ic2.core.upgrade.IUpgradableBlock;
import ic2.core.upgrade.IUpgradeItem;
import ic2.core.upgrade.UpgradableProperty;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import ru.simplemc.senergetics.common.item.ItemBase;

public class ItemMachineUpgradeOverclock
extends ItemBase
implements IUpgradeItem,
IItemHudInfo {
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.##");

    public ItemMachineUpgradeOverclock() {
        super("item_machine_upgrade_overclock");
    }

    public List<String> getHudInfo(ItemStack stack) {
        LinkedList<String> info = new LinkedList<String>();
        info.add("Machine Upgrade");
        return info;
    }

    @SideOnly(value=Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        super.addInformation(stack, player, list, par4);
        list.add(StatCollector.translateToLocalFormatted((String)"ic2.tooltip.upgrade.overclocker.time", (Object[])new Object[]{DECIMAL_FORMAT.format(100.0 * Math.pow(this.getProcessTimeMultiplier(stack, null), stack.stackSize))}));
        list.add(StatCollector.translateToLocalFormatted((String)"ic2.tooltip.upgrade.overclocker.power", (Object[])new Object[]{DECIMAL_FORMAT.format(100.0 * Math.pow(this.getEnergyDemandMultiplier(stack, null), stack.stackSize))}));
    }

    public boolean onItemUse(ItemStack stack, EntityPlayer entityplayer, World world, int x, int y, int z, int side, float xOffset, float yOffset, float zOffset) {
        return false;
    }

    public boolean isSuitableFor(ItemStack stack, Set<UpgradableProperty> types) {
        return types.contains(UpgradableProperty.Processing) || types.contains(UpgradableProperty.Augmentable);
    }

    public int getAugmentation(ItemStack stack, IUpgradableBlock parent) {
        return 0;
    }

    public int getExtraProcessTime(ItemStack stack, IUpgradableBlock parent) {
        return 0;
    }

    public double getProcessTimeMultiplier(ItemStack stack, IUpgradableBlock parent) {
        return 0.3;
    }

    public int getExtraEnergyDemand(ItemStack stack, IUpgradableBlock parent) {
        return 0;
    }

    public double getEnergyDemandMultiplier(ItemStack stack, IUpgradableBlock parent) {
        return 1.4;
    }

    public int getExtraEnergyStorage(ItemStack itemStack, IUpgradableBlock iUpgradableBlock) {
        return 0;
    }

    public double getEnergyStorageMultiplier(ItemStack stack, IUpgradableBlock parent) {
        return 1.0;
    }

    public int getExtraTier(ItemStack itemStack, IUpgradableBlock iUpgradableBlock) {
        return 0;
    }

    public boolean modifiesRedstoneInput(ItemStack stack, IUpgradableBlock parent) {
        return false;
    }

    public int getRedstoneInput(ItemStack stack, IUpgradableBlock parent, int externalInput) {
        return externalInput;
    }

    public boolean onTick(ItemStack stack, IUpgradableBlock parent) {
        return false;
    }

    public void onProcessEnd(ItemStack stack, IUpgradableBlock parent, List<ItemStack> output) {
    }
}

