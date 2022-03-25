package ru.obvilion.additionpanels.mixin;

import appeng.api.implementations.IUpgradeableHost;
import appeng.container.implementations.ContainerUpgradeable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = ContainerUpgradeable.class, remap = false)
public interface ContainerUpgradeableAccessor {
    @Accessor()
    IUpgradeableHost getUpgradeable();
}
