package ru.obvilion.additionpanels.common.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.core.ContainerBase;
import ic2.core.block.comp.Redstone;
import ic2.core.block.machine.tileentity.TileEntityStandardMachine;
import ic2.core.upgrade.UpgradableProperty;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import ru.obvilion.additionpanels.client.gui.GuiFruitGenerator;
import ru.obvilion.additionpanels.common.container.ContainerFruitGenerator;
import ru.obvilion.additionpanels.common.invslot.InvSlotFruitGenerator;

import java.util.EnumSet;
import java.util.Set;

public class TileEntityFruitGenerator extends TileEntityStandardMachine {

    protected final Redstone redstone;

    public TileEntityFruitGenerator() {
        super(8128, 20, 12);
        this.inputSlot = new InvSlotFruitGenerator(this, "input", 8, 12);
        this.redstone = this.addComponent(new Redstone(this));
    }

    @Override
    public String getInventoryName() {
        return "tile.fruitgenerator.name";
    }

    @Override
    public double getEnergy() {
        return this.energy;
    }

    public String getStartSoundFile() {
        return "Machines/Electro Furnace/ElectroFurnaceLoop.ogg";
    }

    public ContainerBase<TileEntityFruitGenerator> getGuiContainer(EntityPlayer entityPlayer) {
        return new ContainerFruitGenerator(entityPlayer, this);
    }

    @SideOnly(Side.CLIENT)
    public GuiScreen getGui(EntityPlayer entityPlayer, boolean isAdmin) {
        return new GuiFruitGenerator(new ContainerFruitGenerator(entityPlayer, this));
    }


    @Override
    public Set<UpgradableProperty> getUpgradableProperties() {
        return EnumSet.of(UpgradableProperty.Processing, UpgradableProperty.Transformer, UpgradableProperty.EnergyStorage, UpgradableProperty.ItemConsuming, UpgradableProperty.ItemProducing);
    }
}