package mireille.client.renderer.tileentity;

import net.minecraft.client.renderer.tileentity.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.tileentity.*;
import mireille.common.tileentity.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.*;
import net.minecraftforge.common.util.*;
import com.creativemd.creativecore.client.rendering.*;

@SideOnly(Side.CLIENT)
public class PicTileRenderer extends TileEntitySpecialRenderer
{
    public void renderTileEntityAt(final TileEntity te, final double x, final double y, final double z, final float partialTicks) {
        if (te instanceof TileEntityPicFrame) {
            final TileEntityPicFrame frame = (TileEntityPicFrame)te;
            if (!frame.url.equals("")) {
                if (frame.isTextureLoaded()) {
                    final int textureID = frame.texture.getTextureID();
                    if (!frame.anim && frame.texture.animation) {
                        return;
                    }
                    if (textureID != -1 && frame.transparency > 0.0f) {
                        final float sizeX = frame.sizeX;
                        final float sizeY = frame.sizeY;
                        GL11.glEnable(3042);
                        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
                        GL11.glDisable(2896);
                        GL11.glColor4f(frame.brightness, frame.brightness, frame.brightness, frame.transparency);
                        GL11.glBindTexture(3553, textureID);
                        GL11.glTexParameteri(3553, 10241, 9728);
                        GL11.glTexParameteri(3553, 10240, 9728);
                        GL11.glPushMatrix();
                        GL11.glTranslated(x + 0.5, y + 0.5, z + 0.5);
                        final ForgeDirection direction = ForgeDirection.getOrientation(frame.getBlockMetadata());
                        RenderHelper3D.applyDirection(direction);
                        if (direction == ForgeDirection.UP || direction == ForgeDirection.DOWN) {
                            GL11.glRotatef(90.0f, 0.0f, 1.0f, 0.0f);
                        }
                        double posX = -0.5 + sizeX / 2.0;
                        if (frame.posX == 1) {
                            posX = 0.0;
                        }
                        else if (frame.posX == 2) {
                            posX = -posX;
                        }
                        double posY = -0.5 + sizeY / 2.0;
                        if (frame.posY == 1) {
                            posY = 0.0;
                        }
                        else if (frame.posY == 2) {
                            posY = -posY;
                        }
                        if ((frame.rotation == 1 || frame.rotation == 3) && (frame.posX == 2 ^ frame.posY == 2)) {
                            GL11.glRotated(180.0, 1.0, 0.0, 0.0);
                        }
                        GL11.glRotated((double)(frame.rotation * 90), 1.0, 0.0, 0.0);
                        GL11.glRotated((double)frame.rotationX, 0.0, 1.0, 0.0);
                        GL11.glRotated((double)frame.rotationY, 0.0, 0.0, 1.0);
                        GL11.glTranslated(-0.945, posY, posX);
                        GL11.glEnable(32826);
                        GL11.glScaled(1.0, (double)frame.sizeY, (double)frame.sizeX);
                        GL11.glBegin(9);
                        GL11.glNormal3f(1.0f, 0.0f, 0.0f);
                        GL11.glTexCoord3f(frame.flippedY ? 0.0f : 1.0f, frame.flippedX ? 0.0f : 1.0f, 0.0f);
                        GL11.glVertex3f(0.5f, -0.5f, -0.5f);
                        GL11.glTexCoord3f(frame.flippedY ? 0.0f : 1.0f, frame.flippedX ? 1.0f : 0.0f, 0.0f);
                        GL11.glVertex3f(0.5f, 0.5f, -0.5f);
                        GL11.glTexCoord3f(frame.flippedY ? 1.0f : 0.0f, frame.flippedX ? 1.0f : 0.0f, 0.0f);
                        GL11.glVertex3f(0.5f, 0.5f, 0.5f);
                        GL11.glTexCoord3f(frame.flippedY ? 1.0f : 0.0f, frame.flippedX ? 0.0f : 1.0f, 0.0f);
                        GL11.glVertex3f(0.5f, -0.5f, 0.5f);
                        GL11.glEnd();
                        GL11.glPopMatrix();
                        GL11.glDisable(32826);
                        GL11.glDisable(3042);
                        GL11.glEnable(2896);
                    }
                }
                else {
                    frame.loadTexture();
                }
            }
        }
    }
}
