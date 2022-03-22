/*
 * Decompiled with CFR 0.152.
 */
package com.prototype.extraamulets.common.resource;

import com.prototype.extraamulets.common.resource.ResourceProperties;
import net.minecraft.util.ResourceLocation;

public interface Resource<T> {
    public ResourceLocation getLocation();

    public ResourceProperties getProperties();

    public T getContent();
}

