/*
 * Decompiled with CFR 0.152.
 */
package com.prototype.extraamulets.common.resource.model.base;

import com.prototype.extraamulets.common.resource.Resource;
import com.prototype.extraamulets.common.resource.ResourceFactory;
import com.prototype.extraamulets.common.resource.ResourceManager;
import com.prototype.extraamulets.common.resource.ResourceProperties;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.Supplier;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.IResource;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.IOUtils;
import org.lwjgl.opengl.GL11;

public class Texture {
    private static final ResourceManager<Texture> manager = new ResourceManager<Texture>((ResourceFactory)new ResourceFactory<Texture>(){

        @Override
        public Supplier<Texture> create(Resource<Texture> resource) throws Exception {
            BufferedImage image = this.readBufferedImage(resource.getLocation());
            return () -> {
                int texId = TextureUtil.glGenTextures();
                Properties properties = (Properties)resource.getProperties();
                TextureUtil.uploadTextureImageAllocate((int)texId, (BufferedImage)image, (boolean)properties.isBlur(), (boolean)properties.isClamp());
                return new Texture(texId);
            };
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        private BufferedImage readBufferedImage(ResourceLocation path) throws Exception {
            BufferedImage bufferedimage;
            IResource resource = Minecraft.getMinecraft().getResourceManager().getResource(path);
            InputStream imageStream = resource.getInputStream();
            try {
                bufferedimage = ImageIO.read(imageStream);
            }
            finally {
                IOUtils.closeQuietly((InputStream)imageStream);
            }
            return bufferedimage;
        }

        private BufferedImage readBufferedImage(InputStream imageStream) throws IOException {
            BufferedImage bufferedimage;
            try {
                bufferedimage = ImageIO.read(imageStream);
            }
            finally {
                IOUtils.closeQuietly((InputStream)imageStream);
            }
            return bufferedimage;
        }

        @Override
        public void destroy(Resource<Texture> resource) {
            GL11.glDeleteTextures((int)resource.getContent().id);
        }
    }){

        @Override
        public Resource<Texture> registerResource(ResourceLocation path, ResourceProperties properties) {
            if (!(properties instanceof Properties)) {
                properties = new Properties(){

                    @Override
                    public boolean isClamp() {
                        return false;
                    }

                    @Override
                    public boolean isBlur() {
                        return false;
                    }
                };
            }
            return super.registerResource(path, properties);
        }
    }.setHoldTicks(1200);
    private final int id;

    public static ResourceManager<Texture> getManager() {
        return manager;
    }

    public Texture(int id) {
        this.id = id;
    }

    public int id() {
        return this.id;
    }

    public void bind() {
        GL11.glBindTexture((int)3553, (int)this.id);
    }

    public static interface Properties
    extends ResourceProperties {
        public boolean isClamp();

        public boolean isBlur();
    }
}

