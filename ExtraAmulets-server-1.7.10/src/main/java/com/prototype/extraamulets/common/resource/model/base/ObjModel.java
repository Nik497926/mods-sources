/*
 * Decompiled with CFR 0.152.
 */
package com.prototype.extraamulets.common.resource.model.base;

import com.prototype.extraamulets.common.resource.Resource;
import com.prototype.extraamulets.common.resource.ResourceFactory;
import com.prototype.extraamulets.common.resource.ResourceManager;
import com.prototype.extraamulets.common.resource.ResourceProperties;
import com.prototype.extraamulets.common.resource.model.ModelBufferBuilder;
import com.prototype.extraamulets.common.resource.model.ModelCompiledList;
import com.prototype.extraamulets.common.resource.model.wavefront.WavefrontModel;
import com.prototype.extraamulets.common.resource.model.wavefront.WavefrontReader;
import com.prototype.extraamulets.common.resource.model.wavefront.WavefrontVector;
import java.util.function.Supplier;

public class ObjModel {
    private static final ResourceManager<ObjModel> manager = new ResourceManager<ObjModel>(new ResourceFactory<ObjModel>(){

        @Override
        public Supplier<ObjModel> create(Resource<ObjModel> resource) throws Exception {
            WavefrontModel model = WavefrontReader.read(resource.getLocation());
            return () -> {
                ObjBufferBuilder builder = new ObjBufferBuilder((Properties)resource.getProperties());
                ModelCompiledList compiledList = new ModelCompiledList();
                compiledList.gen();
                compiledList.begin();
                builder.model(model);
                builder.finish();
                compiledList.end();
                return new ObjModel(compiledList);
            };
        }

        @Override
        public void destroy(Resource<ObjModel> resource) {
            resource.getContent().compiledList.delete();
        }
    });
    private final ModelCompiledList compiledList;

    public static ResourceManager<ObjModel> getManager() {
        return manager;
    }

    private ObjModel(ModelCompiledList compiledList) {
        this.compiledList = compiledList;
    }

    public ModelCompiledList getCompiledList() {
        return this.compiledList;
    }

    public static interface Properties
    extends ResourceProperties {
        public boolean isBackCulling();
    }

    private static class ObjBufferBuilder
    extends ModelBufferBuilder {
        private final Properties properties;

        public ObjBufferBuilder(Properties properties) {
            this.properties = properties;
        }

        @Override
        public boolean isBackCulling() {
            return this.properties.isBackCulling();
        }

        @Override
        protected void texture(WavefrontVector vector) {
            this.tessellator.setTextureUV((double)vector.getX(), (double)(1.0f - vector.getY()));
        }

        @Override
        protected void pos(WavefrontVector vector) {
            this.tessellator.addVertex((double)vector.getX(), (double)(-vector.getY()), (double)(-vector.getZ()));
        }

        @Override
        protected void normal(WavefrontVector vector) {
            this.tessellator.setNormal(-1.0f, -1.0f, -1.0f);
        }
    }
}

