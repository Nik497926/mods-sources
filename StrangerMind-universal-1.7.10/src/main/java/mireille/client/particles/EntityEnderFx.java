package mireille.client.particles;

import net.minecraft.util.*;
import net.minecraft.entity.player.*;
import java.util.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.particle.*;
import cpw.mods.fml.relauncher.*;

@SideOnly(Side.CLIENT)
public class EntityEnderFx extends EntityPortalFX
{
    private static ResourceLocation particles;
    private boolean move;
    private float startX;
    private float startY;
    private float startZ;
    private EntityPlayer player;
    private static ResourceLocation defaultParticles;
    
    public EntityEnderFx(final EntityPlayer player, final double x, final double y, final double z, final double x1, final double y1, final double z1, final float[] color1, final float[] color2) {
        super(player.worldObj, x, y, z, x1, y1, z1);
        this.move = true;
        this.startX = 0.0f;
        this.startY = 0.0f;
        this.startZ = 0.0f;
        this.player = player;
        if (super.rand.nextInt(10) != 1) {
            super.particleRed = color1[0];
            super.particleGreen = color1[1];
            super.particleBlue = color1[2];
        }
        else {
            super.particleRed = color2[0];
            super.particleGreen = color2[1];
            super.particleBlue = color2[2];
        }
        if (player.getRNG().nextInt(3) == 1) {
            this.move = false;
            this.startX = (float)player.posX;
            this.startY = (float)player.posY;
            this.startZ = (float)player.posZ;
        }
        final Random rand = new Random();
        switch (rand.nextInt(2)) {
            case 0: {
                super.particleTextureIndexX = 0;
                super.particleTextureIndexY = 0;
                break;
            }
            case 1: {
                super.particleTextureIndexX = 1;
                super.particleTextureIndexY = 0;
                break;
            }
        }
    }
    
    public void renderParticle(final Tessellator par1Tessellator, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
        if (this.move) {
            this.startX = (float)(this.player.prevPosX + (this.player.posX - this.player.prevPosX) * par2);
            this.startY = (float)(this.player.prevPosY + (this.player.posY - this.player.prevPosY) * par2);
            this.startZ = (float)(this.player.prevPosZ + (this.player.posZ - this.player.prevPosZ) * par2);
        }
        final Tessellator tessellator = Tessellator.instance;
        tessellator.draw();
        float scale = (super.particleAge + par2) / super.particleMaxAge;
        scale = 1.0f - scale;
        scale *= scale;
        scale = 1.0f - scale;
        super.particleScale = 0.7f * scale;
        Minecraft.getMinecraft().renderEngine.bindTexture(EntityEnderFx.particles);
        final double x1 = super.particleTextureIndexX / 2.0f;
        final double y1 = super.particleTextureIndexY;
        final double x2 = x1 + 0.5;
        final double y2 = y1 + 1.0;
        final float f4 = 0.1f * super.particleScale;
        final float f5 = (float)(super.prevPosX + (super.posX - super.prevPosX) * par2 - EntityFX.interpPosX + this.startX);
        final float f6 = (float)(super.prevPosY + (super.posY - super.prevPosY) * par2 - EntityFX.interpPosY + this.startY);
        final float f7 = (float)(super.prevPosZ + (super.posZ - super.prevPosZ) * par2 - EntityFX.interpPosZ + this.startZ);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        tessellator.startDrawingQuads();
        tessellator.setBrightness(240);
        par1Tessellator.setColorOpaque_F(1.0f, 1.0f, 1.0f);
        par1Tessellator.setColorRGBA_F(super.particleRed, super.particleGreen, super.particleBlue, 1.0f);
        par1Tessellator.addVertexWithUV((double)(f5 - par3 * f4 - par6 * f4), (double)(f6 - par4 * f4), (double)(f7 - par5 * f4 - par7 * f4), x1, y1);
        par1Tessellator.addVertexWithUV((double)(f5 - par3 * f4 + par6 * f4), (double)(f6 + par4 * f4), (double)(f7 - par5 * f4 + par7 * f4), x1, y2);
        par1Tessellator.addVertexWithUV((double)(f5 + par3 * f4 + par6 * f4), (double)(f6 + par4 * f4), (double)(f7 + par5 * f4 + par7 * f4), x2, y2);
        par1Tessellator.addVertexWithUV((double)(f5 + par3 * f4 - par6 * f4), (double)(f6 - par4 * f4), (double)(f7 + par5 * f4 - par7 * f4), x2, y1);
        tessellator.draw();
        bindDefaultParticles();
        tessellator.startDrawingQuads();
    }
    
    public static void bindDefaultParticles() {
        if (EntityEnderFx.defaultParticles == null) {
            try {
                EntityEnderFx.defaultParticles = (ResourceLocation)ReflectionHelper.getPrivateValue((Class)EffectRenderer.class, (Object)null, new String[] { "particleTextures", "b", "field_110737_b" });
            }
            catch (Exception var1) {
                var1.printStackTrace();
            }
        }
        if (EntityEnderFx.defaultParticles != null) {
            Minecraft.getMinecraft().renderEngine.bindTexture(EntityEnderFx.defaultParticles);
        }
    }
    
    static {
        EntityEnderFx.particles = new ResourceLocation("mireille:textures/particles/devil.png");
    }
}
