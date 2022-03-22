/*
 * Decompiled with CFR 0.152.
 */
package com.prototype.extraamulets.common.resource;

import com.prototype.extraamulets.common.resource.Resource;
import java.util.function.Supplier;

public interface ResourceFactory<T> {
    public Supplier<T> create(Resource<T> var1) throws Exception;

    public void destroy(Resource<T> var1);
}

