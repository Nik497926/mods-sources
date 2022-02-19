/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package net.divinerpg.client.render.entity.vanilla;

import net.divinerpg.client.render.entity.vanilla.model.ModelSaguaroWormShot;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderSaguaroWormShot
extends Render {
    private ModelSaguaroWormShot model = new ModelSaguaroWormShot();

    public void doRender(Entity projectile, double x, double y, double z, float par8, float par9) {
        GL11.glPushMatrix();
        this.bindEntityTexture(projectile);
        GL11.glTranslatef((float)((float)x), (float)((float)y), (float)((float)z));
        GL11.glEnable((int)32826);
        this.model.renderAll();
        GL11.glPopMatrix();
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        return new ResourceLocation("divinerpg:textures/projectiles/saguaroWorm.png");
    }
}

