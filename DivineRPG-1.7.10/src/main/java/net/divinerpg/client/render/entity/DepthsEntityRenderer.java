/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.RenderingRegistry
 *  net.minecraft.client.renderer.entity.Render
 */
package net.divinerpg.client.render.entity;

import cpw.mods.fml.client.registry.RenderingRegistry;
import net.divinerpg.client.render.EntityResourceLocation;
import net.divinerpg.client.render.RenderDivineMob;
import net.divinerpg.client.render.entity.depths.ModelDarkfish;
import net.divinerpg.client.render.entity.depths.ModelDepthsBeast;
import net.divinerpg.entities.depths.EntityDarkfish;
import net.divinerpg.entities.depths.EntityDepthsBeast;
import net.minecraft.client.renderer.entity.Render;

public class DepthsEntityRenderer {
    private static EntityResourceLocation x;

    public static void init() {
        RenderingRegistry.registerEntityRenderingHandler(EntityDepthsBeast.class, (Render)new RenderDivineMob(new ModelDepthsBeast(), 0.8f, 1.5f, EntityResourceLocation.DEPTHS_BEAST));
        RenderingRegistry.registerEntityRenderingHandler(EntityDarkfish.class, (Render)new RenderDivineMob(new ModelDarkfish(), 0.1f, 1.3f, EntityResourceLocation.DARKFISH));
    }
}

