/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client.render.entity;

import com.meteor.extrabotany.client.render.entity.RenderSparkBase;
import com.meteor.extrabotany.common.entity.IAdvanceSpark;
import com.meteor.extrabotany.common.item.ItemAdvanceSpark;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.util.IIcon;
import vazkii.botania.common.item.ItemSparkUpgrade;

@SideOnly(value= Side.CLIENT)
public class RenderAdvSpark
extends RenderSparkBase {
    @Override
    public IIcon getSpinningIcon(Entity entity) {
        if (entity instanceof IAdvanceSpark) {
            int upgrade = ((IAdvanceSpark)entity).getUpgrade() - 1;
            return upgrade >= 0 && upgrade < ItemSparkUpgrade.worldIcons.length ? ItemSparkUpgrade.worldIcons[upgrade] : null;
        }
        return null;
    }

    @Override
    public IIcon getBaseIcon(Entity entity) {
        if (entity instanceof IAdvanceSpark) {
            int m = ((IAdvanceSpark)entity).getType();
            if (m >= 3) {
                m = 0;
            }
            return ItemAdvanceSpark.worldIcon[m];
        }
        return ItemAdvanceSpark.worldIcon[0];
    }
}

