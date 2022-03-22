/*
 * Decompiled with CFR 0.152.
 */
package com.prototype.extraamulets.common.render;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.prototype.extraamulets.common.render.IRenderAmulet;
import com.prototype.extraamulets.common.resource.Resource;
import com.prototype.extraamulets.common.resource.model.base.ObjModel;
import com.prototype.extraamulets.common.resource.model.base.Texture;
import java.lang.reflect.Type;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderAmulet
implements IRenderAmulet {
    protected final Resource<ObjModel> model;
    protected final Resource<Texture> texture;
    protected final boolean ignoreLightning;
    protected final Vector offset = new Vector();
    protected final Vector direction = new Vector();
    protected final Vector rotation = new Vector();
    protected final Vector scale = new Vector(1.0f, 1.0f, 1.0f);

    public RenderAmulet(Resource<ObjModel> model, Resource<Texture> texture, boolean ignoreLightning) {
        this.model = model;
        this.texture = texture;
        this.ignoreLightning = ignoreLightning;
    }

    @Override
    public Resource<ObjModel> getModel() {
        return this.model;
    }

    @Override
    public Resource<Texture> getTexture() {
        return this.texture;
    }

    @Override
    public void render(EntityPlayer player, float partialTicks) {
        if (this.model.getContent() != null & this.texture.getContent() != null) {
            GL11.glPushMatrix();
            double d0 = player.lastTickPosX + (player.posX - player.lastTickPosX) * (double)partialTicks;
            double d1 = player.lastTickPosY + (player.posY - player.lastTickPosY) * (double)partialTicks;
            double d2 = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * (double)partialTicks;
            if (player == Minecraft.getMinecraft().thePlayer) {
                d1 -= (double)player.yOffset;
            }
            GL11.glTranslated((double)(d0 - RenderManager.renderPosX + (double)this.offset.x), (double)(d1 - RenderManager.renderPosY + (double)this.offset.y), (double)(d2 - RenderManager.renderPosZ + (double)this.offset.z));
            if (this.direction.z != 0.0f || this.rotation.z != 0.0f) {
                GL11.glRotatef((float)(this.direction.z + this.rotation.z * ((float)player.ticksExisted + partialTicks) / 20.0f), (float)0.0f, (float)0.0f, (float)1.0f);
            }
            if (this.direction.y != 0.0f || this.rotation.y != 0.0f) {
                GL11.glRotatef((float)(this.direction.y + this.rotation.y * ((float)player.ticksExisted + partialTicks) / 20.0f), (float)0.0f, (float)1.0f, (float)0.0f);
            }
            if (this.direction.x != 0.0f || this.rotation.x != 0.0f) {
                GL11.glRotatef((float)(this.direction.x + this.rotation.x * ((float)player.ticksExisted + partialTicks) / 20.0f), (float)1.0f, (float)0.0f, (float)0.0f);
            }
            if (this.scale.x != 1.0f || this.scale.y != 1.0f || this.scale.z != 1.0f) {
                GL11.glScalef((float)this.scale.x, (float)this.scale.y, (float)this.scale.z);
            }
            float lastBrightTexCoordX = OpenGlHelper.lastBrightnessX;
            float lastBrightTexCoordY = OpenGlHelper.lastBrightnessY;
            if (this.ignoreLightning) {
                int i = 0xF000F0;
                int j = i % 65536;
                int k = i / 65536;
                OpenGlHelper.setLightmapTextureCoords((int)OpenGlHelper.lightmapTexUnit, (float)((float)j / 1.0f), (float)((float)k / 1.0f));
            }
            GL11.glDisable((int)2896);
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            this.texture.getContent().bind();
            this.model.getContent().getCompiledList().draw();
            GL11.glEnable((int)2896);
            if (this.ignoreLightning) {
                OpenGlHelper.setLightmapTextureCoords((int)OpenGlHelper.lightmapTexUnit, (float)lastBrightTexCoordX, (float)lastBrightTexCoordY);
            }
            GL11.glPopMatrix();
        }
    }

    public static class Vector {
        private float x;
        private float y;
        private float z;

        public Vector() {
        }

        public Vector(float x, float y, float z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public void set(float x, float y, float z) {
            this.setX(x);
            this.setY(y);
            this.setZ(z);
        }

        public float getX() {
            return this.x;
        }

        public void setX(float x) {
            this.x = x;
        }

        public float getY() {
            return this.y;
        }

        public void setY(float y) {
            this.y = y;
        }

        public float getZ() {
            return this.z;
        }

        public void setZ(float z) {
            this.z = z;
        }
    }

    public static class RenderAmuletAdapter
    implements JsonSerializer<RenderAmulet>,
    JsonDeserializer<RenderAmulet> {
        public RenderAmulet deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject object = json.getAsJsonObject();
            Resource<ObjModel> model = RenderAmuletAdapter.readModel(object.get("model").getAsJsonObject());
            Resource<Texture> texture = RenderAmuletAdapter.readTexture(object.get("texture").getAsJsonObject());
            boolean ignoreLightning = object.has("ignore-lightning") && object.get("ignore-lightning").getAsBoolean();
            RenderAmulet render = new RenderAmulet(model, texture, ignoreLightning);
            if (object.has("position")) {
                JsonObject position = object.get("position").getAsJsonObject();
                if (position.has("offset")) {
                    RenderAmuletAdapter.readOffset(position.get("offset").getAsJsonObject(), render.offset);
                }
                if (position.has("direction")) {
                    RenderAmuletAdapter.readDirection(position.get("direction").getAsJsonObject(), render.direction);
                }
                if (position.has("rotation")) {
                    RenderAmuletAdapter.readRotation(position.get("rotation").getAsJsonObject(), render.rotation);
                }
                if (position.has("scale")) {
                    RenderAmuletAdapter.readScale(position.get("scale"), render.scale);
                }
            }
            return render;
        }

        private static Resource<ObjModel> readModel(JsonObject object) {
            ResourceLocation location = new ResourceLocation(object.get("location").getAsString());
            boolean backCulling = object.has("back-culling") && object.get("back-culling").getAsBoolean();
            return ObjModel.getManager().registerResource(location, (ObjModel.Properties) () -> backCulling);
        }

        private static Resource<Texture> readTexture(JsonObject object) {
            ResourceLocation location = new ResourceLocation(object.get("location").getAsString());
            final boolean clamp = object.has("clamp") && object.get("clamp").getAsBoolean();
            final boolean blur = object.has("blur") && object.get("blur").getAsBoolean();
            return Texture.getManager().registerResource(location, new Texture.Properties(){

                @Override
                public boolean isClamp() {
                    return clamp;
                }

                @Override
                public boolean isBlur() {
                    return blur;
                }
            });
        }

        private static void readDirection(JsonObject object, Vector direction) {
            direction.set(object.get("pitch").getAsFloat(), object.get("yaw").getAsFloat(), object.get("roll").getAsFloat());
        }

        private static void readRotation(JsonObject object, Vector rotation) {
            rotation.set(object.get("pitch").getAsFloat(), object.get("yaw").getAsFloat(), object.get("roll").getAsFloat());
        }

        private static void readOffset(JsonObject object, Vector offset) {
            offset.set(object.get("x").getAsFloat(), object.get("y").getAsFloat(), object.get("z").getAsFloat());
        }

        private static void readScale(JsonElement object, Vector scale) {
            float value = Math.max(1.0E-4f, object.getAsFloat());
            scale.set(value, value, value);
        }

        public JsonElement serialize(RenderAmulet src, Type type, JsonSerializationContext context) {
            JsonObject object = new JsonObject();
            JsonObject model = new JsonObject();
            JsonObject texture = new JsonObject();
            JsonObject position = new JsonObject();
            RenderAmuletAdapter.writeModel(model, src.getModel());
            RenderAmuletAdapter.writeTexture(texture, src.getTexture());
            RenderAmuletAdapter.writeOffset(position, src.offset);
            RenderAmuletAdapter.writeDirection(position, src.direction);
            RenderAmuletAdapter.writeRotation(position, src.rotation);
            RenderAmuletAdapter.writeScale(position, src.scale);
            object.add("model", (JsonElement)model);
            object.add("texture", (JsonElement)texture);
            object.add("position", (JsonElement)position);
            object.addProperty("ignore-lightning", Boolean.valueOf(src.ignoreLightning));
            return object;
        }

        private static void writeModel(JsonObject object, Resource<ObjModel> model) {
            object.addProperty("location", model.getLocation().toString());
            object.addProperty("back-culling", Boolean.valueOf(false));
        }

        private static void writeTexture(JsonObject object, Resource<Texture> texture) {
            object.addProperty("location", texture.getLocation().toString());
            object.addProperty("clamp", Boolean.valueOf(true));
            object.addProperty("blur", Boolean.valueOf(true));
        }

        private static void writeOffset(JsonObject object, Vector vector) {
            JsonObject offset = new JsonObject();
            offset.addProperty("x", (Number)Float.valueOf(vector.getX()));
            offset.addProperty("y", (Number)Float.valueOf(vector.getY()));
            offset.addProperty("z", (Number)Float.valueOf(vector.getZ()));
            object.add("offset", (JsonElement)offset);
        }

        private static void writeDirection(JsonObject object, Vector vector) {
            JsonObject direction = new JsonObject();
            direction.addProperty("pitch", (Number)Float.valueOf(vector.getX()));
            direction.addProperty("yaw", (Number)Float.valueOf(vector.getY()));
            direction.addProperty("roll", (Number)Float.valueOf(vector.getZ()));
            object.add("direction", (JsonElement)direction);
        }

        private static void writeRotation(JsonObject object, Vector vector) {
            JsonObject rotation = new JsonObject();
            rotation.addProperty("pitch", (Number)Float.valueOf(vector.getX()));
            rotation.addProperty("yaw", (Number)Float.valueOf(vector.getY()));
            rotation.addProperty("roll", (Number)Float.valueOf(vector.getZ()));
            object.add("rotation", (JsonElement)rotation);
        }

        private static void writeScale(JsonObject object, Vector vector) {
            object.addProperty("scale", (Number)Float.valueOf(vector.getX()));
        }
    }
}

