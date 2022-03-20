package ua.limon4ik.client.render.tiles;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import ua.limon4ik.client.render.SRenderHelper;
import ua.limon4ik.tiles.TileEntityAutoMatrix;


public class RenderAutoMatrixBlock extends TileEntitySpecialRenderer{
    private final ResourceLocation texture, base;
    private IModelCustom model;
    private static float pxl = 1F / 256F;
    public RenderAutoMatrixBlock(){
        model= AdvancedModelLoader.loadModel(new ResourceLocation("legendaryhorizons:models/AutoMatrixBlock.obj"));
        texture=new ResourceLocation("legendaryhorizons:textures/models/AutoMatrixBlock.png");
        base=new ResourceLocation("legendaryhorizons:textures/models/AutoMatrixBlock_base.png");
    }
    @Override
    public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float some) {
        if(tile instanceof TileEntityAutoMatrix){
            GL11.glPushMatrix();
            GL11.glTranslated(x,y,z);
            Tessellator tess=Tessellator.instance;
            tess.setBrightness(240);
            drawBase(tess);
            GL11.glPushMatrix();
            GL11.glTranslated(0.5,0.5,0.5);
            //System.out.println("Trying to render item:" +((TileEntityAutoMatrix) tile).getItemStack());
            if(((TileEntityAutoMatrix) tile).getItemStack()!=null){
                SRenderHelper.renderItemStackInWorld(tile.getWorldObj(),((TileEntityAutoMatrix) tile).getItemStack(),1,Minecraft.getMinecraft().theWorld.getWorldTime());
            }
            GL11.glPopMatrix();
            drawModel();
            //tess.setColorRGBA(255,0,10,255);
            /*GL11.glPushMatrix();
            GL11.glScaled(0.0188,0.018,0.0188);
            GL11.glRotated(90,-1,0,0);
            //GL11.glRotated(-Minecraft.getMinecraft().theWorld.getWorldTime(),0,-1,0);
            ///GL11.glColor3d(255,0,125);
            bindTexture(texture);
            model.renderAll();
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glTranslated(0,1.6,0);
            SRenderHelper.renderItemStackInWorld(tile.getWorldObj(),((TileEntityAutoMatrix) tile).getItemStack(),1,Minecraft.getMinecraft().theWorld.getWorldTime());
            GL11.glPopMatrix();*/
            GL11.glPopMatrix();
        }
    }

    private void drawModel(){
        GL11.glPushMatrix();
        bindTexture(texture);
        GL11.glTranslated(0.5,1.3,0.5);
        GL11.glScaled(0.45,0.45,0.45);
        GL11.glRotated(-Minecraft.getMinecraft().theWorld.getWorldTime(),0,-1,0);
        model.renderAll();
        GL11.glPopMatrix();
    }

    private void drawBase(Tessellator tess) {
        GL11.glPushMatrix();
        bindTexture(base);
        tess.startDrawingQuads();
        tess.setNormal(0F, 0F, 1.0F);

        double srcXMin = 0D;
        double srcYMin = 0D;
        double srcXMax = 64D * pxl;
        double srcYMax = 64D * pxl;

        tess.addVertexWithUV(0, 0.0005D, 0, srcXMin, srcYMin);
        tess.addVertexWithUV(1, 0.0005D, 0, srcXMax, srcYMin);
        tess.addVertexWithUV(1, 0.0005D, 1, srcXMax, srcYMax);
        tess.addVertexWithUV(0, 0.0005D, 1, srcXMin, srcYMax);


        srcXMin = 128D * pxl;
        srcYMin = 0D;
        srcXMax = 192D * pxl;
        srcYMax = 64D * pxl;

        tess.addVertexWithUV(0, 0.3745D, 0, srcXMin, srcYMin);
        tess.addVertexWithUV(0, 0.3745D, 1, srcXMax, srcYMin);
        tess.addVertexWithUV(1, 0.3745D, 1, srcXMax, srcYMax);
        tess.addVertexWithUV(1, 0.3745D, 0, srcXMin, srcYMax);

        srcXMin = 64D * pxl;
        srcYMin = 0D;
        srcXMax = 128D * pxl;
        srcYMax = 24D * pxl;

        tess.addVertexWithUV(1, 0, 0, srcXMin, srcYMin);
        tess.addVertexWithUV(0, 0, 0, srcXMax, srcYMin);
        tess.addVertexWithUV(0, 0.375D, 0, srcXMax, srcYMax);
        tess.addVertexWithUV(1, 0.375D, 0, srcXMin, srcYMax);

        tess.addVertexWithUV(1, 0, 1, srcXMin, srcYMin);
        tess.addVertexWithUV(1, 0.375D, 1, srcXMin, srcYMax);
        tess.addVertexWithUV(0, 0.375D, 1, srcXMax, srcYMax);
        tess.addVertexWithUV(0, 0, 1, srcXMax, srcYMin);

        tess.addVertexWithUV(0, 0.375D, 1, srcXMin, srcYMin);
        tess.addVertexWithUV(0, 0.375D, 0, srcXMax, srcYMin);
        tess.addVertexWithUV(0, 0, 0, srcXMax, srcYMax);
        tess.addVertexWithUV(0, 0, 1, srcXMin, srcYMax);

        tess.addVertexWithUV(1, 0.375D, 1, srcXMin, srcYMin);
        tess.addVertexWithUV(1, 0, 1, srcXMin, srcYMax);
        tess.addVertexWithUV(1, 0, 0, srcXMax, srcYMax);
        tess.addVertexWithUV(1, 0.375D, 0, srcXMax, srcYMin);

        tess.draw();
        GL11.glPopMatrix();

    }
}
