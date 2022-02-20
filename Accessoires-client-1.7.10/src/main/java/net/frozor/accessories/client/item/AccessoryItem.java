/*
 * Decompiled with CFR 0.152.
 */
package net.frozor.accessories.client.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.nio.FloatBuffer;
import java.util.Arrays;
import net.frozor.accessories.client.ItemTransformVec3f;
import net.frozor.accessories.client.item.CategoryType;
import net.frozor.accessories.client.item.animation.AbstractAnimation;
import net.frozor.accessories.client.item.animation.AvatarAnim;
import net.frozor.accessories.client.item.animation.WingAnim;
import net.frozor.accessories.client.ui.UIScroll;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Quaternion;

public class AccessoryItem {
    private boolean news;
    private boolean wing;
    @SideOnly(value=Side.CLIENT)
    private int indexRenderer;
    @SideOnly(value=Side.CLIENT)
    private ResourceLocation texture;
    @SideOnly(value=Side.CLIENT)
    private IModelCustom model;
    private String INDEX;
    @SideOnly(value=Side.CLIENT)
    private CustomPreferences preferencesRenderer;
    private int price;
    private AbstractAnimation animation;
    private String author;
    @SideOnly(value=Side.CLIENT)
    private CustomPreferences preferencesGui;
    public ItemTransformVec3f headTransform;
    private String name;
    private boolean has;
    private CategoryType type;
    private long lastTryBuy;
    private static final FloatBuffer BUF_FLOAT_16 = BufferUtils.createFloatBuffer((int)16);

    public void setHas(boolean has) {
        this.has = has;
    }

    public void setModel(IModelCustom model) {
        this.model = model;
    }

    public boolean isNews() {
        return this.news;
    }

    public CustomPreferences getPreferencesRenderer() {
        return this.preferencesRenderer;
    }

    public String getName() {
        return this.name;
    }

    public void setWing(boolean wing) {
        this.wing = wing;
    }

    private static /* synthetic */ Quaternion makeQuaternion(float p_188035_0_, float p_188035_1_, float p_188035_2_) {
        float f = p_188035_0_ * ((float)Math.PI / 180);
        float f1 = p_188035_1_ * ((float)Math.PI / 180);
        float f2 = p_188035_2_ * ((float)Math.PI / 180);
        float f3 = MathHelper.sin((float)(0.5f * f));
        float f4 = MathHelper.cos((float)(0.5f * f));
        float f5 = MathHelper.sin((float)(0.5f * f1));
        float f6 = MathHelper.cos((float)(0.5f * f1));
        float f7 = MathHelper.sin((float)(0.5f * f2));
        float f8 = MathHelper.cos((float)(0.5f * f2));
        return new Quaternion(f3 * f6 * f8 + f4 * f5 * f7, f4 * f5 * f8 - f3 * f6 * f7, f3 * f5 * f8 + f4 * f6 * f7, f4 * f6 * f8 - f3 * f5 * f7);
    }

    public AccessoryItem(String INDEX, CategoryType type) {
        this.INDEX = INDEX;
        this.type = type;
        this.preferencesRenderer = new CustomPreferences();
        this.preferencesGui = new CustomPreferences();
        this.lastTryBuy = -1L;
    }

    @SideOnly(value=Side.CLIENT)
    public int getIndexRenderer() {
        return this.indexRenderer;
    }

    public CustomPreferences getPreferencesGui() {
        return this.preferencesGui;
    }

    public CategoryType getType() {
        return this.type;
    }

    public void setNews(boolean news) {
        this.news = news;
    }

    public boolean canTryBuy() {
        return this.lastTryBuy == -1L || this.lastTryBuy + 3000L < System.currentTimeMillis();
    }

    public String getAuthor() {
        return this.author;
    }

    public IModelCustom getModel() {
        return this.model;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @SideOnly(value=Side.CLIENT)
    public void initRendererCall() {
        this.indexRenderer = GLAllocation.generateDisplayLists((int)1);
        GL11.glNewList((int)this.indexRenderer, (int)4864);
        float scaled = 0.038f;
        GL11.glTranslatef((float)0.0f, (float)-0.25f, (float)0.0035f);
        GL11.glScalef((float)scaled, (float)(-scaled), (float)(-scaled));
        GL11.glTranslatef((float)(-this.preferencesRenderer.translation[0]), (float)this.preferencesRenderer.translation[1], (float)(-this.preferencesRenderer.translation[2]));
        GL11.glEnable((int)32826);
        AccessoryItem.rotate(AccessoryItem.makeQuaternion(-this.headTransform.rotation.x, this.headTransform.rotation.y, -this.headTransform.rotation.z));
        GL11.glScalef((float)this.preferencesRenderer.scale[0], (float)this.preferencesRenderer.scale[1], (float)this.preferencesRenderer.scale[2]);
        GL11.glTranslatef((float)8.0f, (float)-8.0f, (float)8.0f);
        this.model.renderAll();
        switch (this.type) {
            case FAMILIAR: {
                this.animation = new AvatarAnim();
                break;
            }
            case BODY: {
                if (!this.isWing()) break;
                this.animation = new WingAnim();
            }
        }
        GL11.glEndList();
    }

    public boolean isWing() {
        return this.wing;
    }

    public String toString() {
        return this.INDEX;
    }

    public static FloatBuffer quatToGlMatrix(FloatBuffer buffer, Quaternion quaternionIn) {
        buffer.clear();
        float f = quaternionIn.x * quaternionIn.x;
        float f1 = quaternionIn.x * quaternionIn.y;
        float f2 = quaternionIn.x * quaternionIn.z;
        float f3 = quaternionIn.x * quaternionIn.w;
        float f4 = quaternionIn.y * quaternionIn.y;
        float f5 = quaternionIn.y * quaternionIn.z;
        float f6 = quaternionIn.y * quaternionIn.w;
        float f7 = quaternionIn.z * quaternionIn.z;
        float f8 = quaternionIn.z * quaternionIn.w;
        buffer.put(1.0f - 2.0f * (f4 + f7));
        buffer.put(2.0f * (f1 + f8));
        buffer.put(2.0f * (f2 - f6));
        buffer.put(0.0f);
        buffer.put(2.0f * (f1 - f8));
        buffer.put(1.0f - 2.0f * (f + f7));
        buffer.put(2.0f * (f5 + f3));
        buffer.put(0.0f);
        buffer.put(2.0f * (f2 + f6));
        buffer.put(2.0f * (f5 - f3));
        buffer.put(1.0f - 2.0f * (f + f4));
        buffer.put(0.0f);
        buffer.put(0.0f);
        buffer.put(0.0f);
        buffer.put(0.0f);
        buffer.put(1.0f);
        buffer.rewind();
        return buffer;
    }

    public void setTexture(ResourceLocation texture) {
        this.texture = texture;
    }

    public boolean isHas() {
        return this.has;
    }

    @SideOnly(value=Side.CLIENT)
    public ResourceLocation getTexture() {
        return this.texture;
    }

    public AbstractAnimation getAnimation() {
        return this.animation;
    }

    public static void multMatrix(FloatBuffer matrix) {
        GL11.glMultMatrix((FloatBuffer)matrix);
    }

    public void updateTryBuy() {
        if (!(this.lastTryBuy != -1L && this.lastTryBuy + 3000L >= System.currentTimeMillis() || this.isHas())) {
            this.lastTryBuy = System.currentTimeMillis();
        }
    }

    public static void rotate(Quaternion quaternionIn) {
        AccessoryItem.multMatrix(AccessoryItem.quatToGlMatrix(BUF_FLOAT_16, quaternionIn));
    }

    public int getPrice() {
        return this.price;
    }

    public String getINDEX() {
        return this.INDEX;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @SideOnly(value=Side.CLIENT)
    public class CustomPreferences {
        private float[] rotation = new float[]{0.0f, 0.0f, 0.0f};
        private float[] translation = new float[]{0.0f, 0.0f, 0.0f};
        private float[] scale = new float[]{1.0f, 1.0f, 1.0f};

        public float[] getTranslation() {
            return this.translation;
        }

        public void setScale(float x, float y, float z) {
            this.scale = new float[]{x, y, z};
        }

        public void setRotation(float x, float y, float z) {
            this.rotation = new float[]{x, y, z};
        }

        public void apply() {
        }

        public float[] getScale() {
            return this.scale;
        }

        public void setTranslation(float x, float y, float z) {
            this.translation = new float[]{x, y, z};
        }

        public String toString() {
            return "CustomPreferences{rotation=" + Arrays.toString(this.rotation) + UIScroll.l("\u0007G_\u0015J\tX\u000bJ\u0013B\bEZ") + Arrays.toString(this.translation) + ", scale=" + Arrays.toString(this.scale) + '}';
        }

        public float[] getRotation() {
            return this.rotation;
        }
    }
}

