/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.item.electicity.panel;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Collections;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.simplemc.senergetics.SEnergetics;
import ru.simplemc.senergetics.common.item.ItemBase;
import ru.simplemc.senergetics.common.tileentity.electricity.TileEntitySolarPanel;
import ru.simplemc.senergetics.config.ConfigParticleCollector;
import ru.simplemc.senergetics.util.TranslateUtils;

public class ItemPanelParticleCollector
extends ItemBase {
    private final double energyGenerated;
    private final double energyCapacity;
    private final int textureId;

    public ItemPanelParticleCollector(String name, int textureId) {
        super("panel_particle_collector_" + name);
        ConfigParticleCollector config = SEnergetics.getConfig().getParticleCollectors().get(name);
        this.energyGenerated = config.getEnergyGenerated();
        this.energyCapacity = config.getEnergyCapacity();
        this.textureId = textureId;
    }

    @SideOnly(value=Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag) {
        Collections.addAll(list, String.format("\u0412\u044b\u0440\u0430\u0431\u0430\u0442\u044b\u0432\u0430\u0435\u0442 \u0434\u043d\u0435\u043c: \u00a7e%s", TranslateUtils.translateValueOf(this.energyGenerated, "EU/t")), String.format("\u0412\u044b\u0440\u0430\u0431\u0430\u0442\u044b\u0432\u0430\u0435\u0442 \u043d\u043e\u0447\u044c\u044e: \u00a7e%s", TranslateUtils.translateValueOf(this.energyGenerated / 2.0, "EU/t")), String.format("\u0420\u0430\u0437\u043c\u0435\u0440 \u0445\u0440\u0430\u043d\u0438\u043b\u0438\u0449\u0430: \u00a7e%s", TranslateUtils.translateValueOf(this.energyCapacity, "EU")));
    }

    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int blockX, int blockY, int blockZ, int side, float hitX, float hitY, float hitZ) {
        TileEntity tileEntity = world.getTileEntity(blockX, blockY, blockZ);
        if (tileEntity instanceof TileEntitySolarPanel) {
            TileEntitySolarPanel solarPanel = (TileEntitySolarPanel)tileEntity;
            ItemStack itemStackHeld = player.getHeldItem();
            if (itemStackHeld != null && itemStackHeld.getItem() instanceof ItemPanelParticleCollector) {
                ItemStack itemStackCurrentParticleCollector = solarPanel.getInventory().getParticleCollectorItemStack().orElse(null);
                solarPanel.getInventory().setParticleCollector(itemStackHeld);
                player.setCurrentItemOrArmor(0, itemStackCurrentParticleCollector);
                return true;
            }
        }
        return false;
    }

    public double getEnergyGenerated() {
        return this.energyGenerated;
    }

    public double getEnergyCapacity() {
        return this.energyCapacity;
    }

    public int getTextureId() {
        return this.textureId;
    }
}

