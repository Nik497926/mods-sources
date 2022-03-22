/*
 * Decompiled with CFR 0.152.
 */
package com.prototype.extraamulets.common.render;

import com.prototype.extraamulets.common.resource.Resource;
import com.prototype.extraamulets.common.resource.model.base.ObjModel;
import com.prototype.extraamulets.common.resource.model.base.Texture;
import net.minecraft.entity.player.EntityPlayer;

public interface IRenderAmulet {
    public Resource<ObjModel> getModel();

    public Resource<Texture> getTexture();

    public void render(EntityPlayer var1, float var2);
}

