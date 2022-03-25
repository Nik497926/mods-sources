package ru.obvilion.additionpanels.common.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.recipe.RecipeOutput;
import ic2.core.ContainerBase;
import ic2.core.block.machine.tileentity.TileEntityStandardMachine;
import ic2.core.upgrade.UpgradableProperty;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import ru.obvilion.additionpanels.common.container.ContainerCobblestoneGenerator;
import ru.obvilion.additionpanels.client.gui.GuiCobblestoneGenerator;

import java.util.*;

public class TileEntityСobblestoneGenerator extends TileEntityStandardMachine {

    private final static RecipeOutput coblOutput = new RecipeOutput(new NBTTagCompound(), new ItemStack(Blocks.cobblestone,64));

    public TileEntityСobblestoneGenerator() {
        super(4064, 20, 30);
        this.inputSlot = null;
    }

    public void operateOnce(RecipeOutput output, List<ItemStack> processResult) {
        this.outputSlot.add(processResult);
    }

    public RecipeOutput getOutput() {
        return this.outputSlot.canAdd(coblOutput.items) ? coblOutput : null;
    }

    //    @Override
//    protected void updateEntityServer() {
//        super.updateEntityServer();
////        this.setUpgradestat();
//        this.upgradeSlot.onChanged();
//        if (this.work()) {
//            this.markDirty();
//            if (!this.getActive()) {
//                this.setActive(true);
//            }
//        } else if (this.getActive()) {
//            this.setActive(false);
//        }
//
//    }
////    public void setUpgradestat() {
////        this.upgradeSlot.onChanged();
////        this.setTier(applyModifier(this.defaultTier, this.upgradeSlot.extraTier, 1.0D));
////        this.blockscanncount = 5 * (this.upgradeSlot.augmentation + 1);
////    }
//
//    private static int applyModifier(int base, int extra, double multiplier) {
//        double ret = (double)Math.round(((double)base + (double)extra) * multiplier);
//        return ret > 2.147483647E9D ? 2147483647 : (int)ret;
//    }
//
//    private boolean work() {
//        if (this.energy < (double)this.energyConsume) {
//            return false;
//        } else if (this.redstone.hasRedstoneInput()) {
//            return false;
//        } else {
//
//            if (this.ticker == 20) {
//                if (slots.canAdd(cobl)) {
//                    slots.add(cobl);
//                    this.energy -= this.energyConsume;
//                }
//                this.ticker = 0;
//            } else {
//                ++this.ticker;
//            }
//
//            return true;
//        }
//    }


    @Override
    public String getInventoryName() {
        return "tile.cobblestonegenerator.name";
    }

    @Override
    public double getEnergy() {
        return this.energy;
    }

    public ContainerBase<TileEntityСobblestoneGenerator> getGuiContainer(EntityPlayer entityPlayer) {
        return new ContainerCobblestoneGenerator(entityPlayer, this);
    }

    @SideOnly(Side.CLIENT)
    public GuiScreen getGui(EntityPlayer entityPlayer, boolean isAdmin) {
        return new GuiCobblestoneGenerator(new ContainerCobblestoneGenerator(entityPlayer, this));
    }


    @Override
    public Set<UpgradableProperty> getUpgradableProperties() {
        return EnumSet.of(UpgradableProperty.Processing, UpgradableProperty.Transformer, UpgradableProperty.EnergyStorage, UpgradableProperty.ItemConsuming, UpgradableProperty.ItemProducing);
    }
}