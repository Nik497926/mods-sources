/*
 * Decompiled with CFR 0.152.
 */
package com.prototype.extraamulets.common.resource;

import com.prototype.extraamulets.ExtraAmuletsMod;
import com.prototype.extraamulets.common.resource.Resource;
import com.prototype.extraamulets.common.resource.ResourceFactory;
import com.prototype.extraamulets.common.resource.ResourceProperties;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import net.minecraft.util.ResourceLocation;

public class ResourceManager<T> {
    private static final Supplier EMPTY = () -> null;
    private final Map<ResourceLocation, DefaultResource> resources = new HashMap<ResourceLocation, DefaultResource>();
    private final ResourceFactory<T> factory;
    private int holdTicks = -1;
    private int ticks = 0;

    public ResourceManager(ResourceFactory<T> factory) {
        this.factory = factory;
        FMLCommonHandler.instance().bus().register((Object)this);
    }

    public ResourceFactory<T> getFactory() {
        return this.factory;
    }

    public int getHoldTicks() {
        return this.holdTicks;
    }

    public ResourceManager<T> setHoldTicks(int holdTicks) {
        this.holdTicks = holdTicks;
        return this;
    }

    public Resource<T> registerResource(ResourceLocation path, ResourceProperties properties) {
        DefaultResource resource = null;
        if (path != null && (resource = (DefaultResource)this.getResource(path)) == null) {
            resource = new DefaultResource(path, properties);
            this.resources.put(path, resource);
        }
        return resource;
    }

    public Resource<T> getResource(ResourceLocation path) {
        return this.resources.get(path);
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END && this.holdTicks >= 0 && ++this.ticks >= this.holdTicks) {
            for (DefaultResource resource : this.resources.values()) {
                resource.update();
            }
            this.ticks = 0;
        }
    }

    public void reload() {
        for (DefaultResource resource : this.resources.values()) {
            resource.reload();
        }
    }

    private class DefaultResource
    implements Resource<T> {
        private final ResourceLocation location;
        private final ResourceProperties properties;
        private boolean accessed = false;
        private T content = null;
        private boolean loaded = false;
        private Thread thread = null;
        private Supplier<T> supplier = null;

        public DefaultResource(ResourceLocation location, ResourceProperties properties) {
            this.location = location;
            this.properties = properties;
        }

        @Override
        public ResourceLocation getLocation() {
            return this.location;
        }

        @Override
        public ResourceProperties getProperties() {
            return this.properties;
        }

        @Override
        public T getContent() {
            this.accessed = true;
            return this.load();
        }

        protected synchronized T load() {
            if (!this.loaded) {
                if (this.supplier != null) {
                    this.content = this.supplier.get();
                    this.supplier = null;
                    this.loaded = true;
                } else if (this.thread == null) {
                    this.thread = new Thread(() -> {
                        Supplier supplier = null;
                        try {
                            supplier = ResourceManager.this.getFactory().create(this);
                        }
                        catch (Exception e) {
                            ExtraAmuletsMod.getLogger().warn("Failed to load resource from: " + this.getLocation());
                            e.printStackTrace();
                        }
                        if (supplier == null) {
                            supplier = EMPTY;
                        }
                        this.commit(supplier);
                    });
                    this.thread.start();
                }
            }
            return this.content;
        }

        private synchronized void commit(Supplier<T> supplier) {
            this.supplier = supplier;
            this.thread = null;
        }

        protected synchronized void unload() {
            if (this.loaded) {
                this.supplier = null;
                if (this.content != null) {
                    ResourceManager.this.getFactory().destroy(this);
                    this.content = null;
                }
                this.loaded = false;
            }
        }

        protected synchronized void update() {
            if (this.loaded) {
                if (this.accessed) {
                    this.accessed = false;
                } else {
                    this.unload();
                }
            }
        }

        protected synchronized void reload() {
            if (this.loaded) {
                this.unload();
                this.load();
            }
        }
    }
}

