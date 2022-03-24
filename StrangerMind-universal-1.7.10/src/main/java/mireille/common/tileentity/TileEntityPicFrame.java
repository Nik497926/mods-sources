package mireille.common.tileentity;

import com.creativemd.creativecore.common.tileentity.*;
import cpw.mods.fml.relauncher.*;
import mireille.client.opf.*;
import cpw.mods.fml.common.*;
import mireille.client.renderer.tileentity.*;
import net.minecraft.tileentity.*;
import net.minecraft.util.*;
import net.minecraft.nbt.*;
import net.minecraft.network.*;
import net.minecraft.network.play.server.*;

public class TileEntityPicFrame extends TileEntityCreative
{
    @SideOnly(Side.CLIENT)
    public DownloadThread downloader;
    @SideOnly(Side.CLIENT)
    public PictureTexture texture;
    @SideOnly(Side.CLIENT)
    public boolean failed;
    public int renderDistance;
    public String url;
    public float sizeX;
    public float sizeY;
    public boolean flippedX;
    public boolean flippedY;
    public float rotationX;
    public float rotationY;
    public byte rotation;
    public byte posX;
    public byte posY;
    public boolean visibleFrame;
    public boolean anim;
    public float transparency;
    public float brightness;
    
    public TileEntityPicFrame() {
        this.renderDistance = 64;
        this.url = "";
        this.sizeX = 1.0f;
        this.sizeY = 1.0f;
        this.rotation = 0;
        this.posX = 0;
        this.posY = 0;
        this.visibleFrame = true;
        this.anim = true;
        this.transparency = 1.0f;
        this.brightness = 1.0f;
        if (FMLCommonHandler.instance().getSide().isClient()) {
            this.initClient();
        }
    }
    
    @SideOnly(Side.CLIENT)
    public void initClient() {
        this.texture = null;
        this.failed = false;
    }
    
    @SideOnly(Side.CLIENT)
    public boolean shouldLoadTexture() {
        return !this.isTextureLoaded() && !this.failed;
    }
    
    @SideOnly(Side.CLIENT)
    public void loadTexture() {
        if (this.shouldLoadTexture()) {
            if (this.downloader == null && DownloadThread.activeDownloads < 5) {
                final PictureTexture loadedTexture = DownloadThread.loadedImages.get(this.url);
                if (loadedTexture == null) {
                    synchronized (DownloadThread.LOCK) {
                        if (!DownloadThread.loadingImages.contains(this.url)) {
                            this.downloader = new DownloadThread(this.url);
                            return;
                        }
                    }
                }
                else {
                    this.texture = loadedTexture;
                    final PicTileRenderer pr = new PicTileRenderer();
                    pr.renderTileEntityAt((TileEntity)this, 0.0, 0.0, 0.0, 0.0f);
                }
            }
            if (this.downloader != null && this.downloader.hasFinished()) {
                if (this.downloader.hasFailed()) {
                    this.failed = true;
                }
                else {
                    this.texture = DownloadThread.loadImage(this.downloader);
                    final PicTileRenderer pr2 = new PicTileRenderer();
                    pr2.renderTileEntityAt((TileEntity)this, 0.0, 0.0, 0.0, 0.0f);
                }
                this.downloader = null;
            }
        }
    }
    
    @SideOnly(Side.CLIENT)
    public boolean isTextureLoaded() {
        return this.texture != null;
    }
    
    @SideOnly(Side.CLIENT)
    public double getMaxRenderDistanceSquared() {
        return Math.pow(this.renderDistance, 2.0);
    }
    
    public AxisAlignedBB getBoundingBox() {
        this.loadTexture();
        if (this.texture != null) {
            this.texture.tick();
        }
        return TileEntityPicFrame.INFINITE_EXTENT_AABB;
    }
    
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox() {
        return this.getBoundingBox();
    }
    
    public void writeToNBT(final NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setString("url", this.url);
        nbt.setFloat("sizeX", this.sizeX);
        nbt.setFloat("sizeY", this.sizeY);
        nbt.setInteger("render", this.renderDistance);
        nbt.setByte("offsetX", this.posX);
        nbt.setByte("offsetY", this.posY);
        nbt.setByte("rotation", this.rotation);
        nbt.setBoolean("visibleFrame", this.visibleFrame);
        nbt.setBoolean("flippedX", this.flippedX);
        nbt.setBoolean("flippedY", this.flippedY);
        nbt.setFloat("rotX", this.rotationX);
        nbt.setFloat("rotY", this.rotationY);
        nbt.setFloat("transparency", this.transparency);
        nbt.setFloat("brightness", this.brightness);
    }
    
    public void readFromNBT(final NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.url = nbt.getString("url");
        this.sizeX = nbt.getFloat("sizeX");
        this.sizeY = nbt.getFloat("sizeY");
        this.renderDistance = nbt.getInteger("render");
        this.posX = nbt.getByte("offsetX");
        this.posY = nbt.getByte("offsetY");
        this.rotation = nbt.getByte("rotation");
        this.visibleFrame = nbt.getBoolean("visibleFrame");
        this.flippedX = nbt.getBoolean("flippedX");
        this.flippedY = nbt.getBoolean("flippedY");
        this.rotationX = nbt.getFloat("rotX");
        this.rotationY = nbt.getFloat("rotY");
        if (nbt.hasKey("transparency")) {
            this.transparency = nbt.getFloat("transparency");
        }
        else {
            this.transparency = 1.0f;
        }
        if (nbt.hasKey("brightness")) {
            this.brightness = nbt.getFloat("brightness");
        }
        else {
            this.brightness = 1.0f;
        }
    }
    
    public void getDescriptionNBT(final NBTTagCompound nbt) {
        super.getDescriptionNBT(nbt);
        nbt.setString("url", this.url);
        nbt.setFloat("sizeX", this.sizeX);
        nbt.setFloat("sizeY", this.sizeY);
        nbt.setInteger("render", this.renderDistance);
        nbt.setByte("offsetX", this.posX);
        nbt.setByte("offsetY", this.posY);
        nbt.setByte("rotation", this.rotation);
        nbt.setBoolean("visibleFrame", this.visibleFrame);
        nbt.setBoolean("anim", this.anim);
        nbt.setBoolean("flippedX", this.flippedX);
        nbt.setBoolean("flippedY", this.flippedY);
        nbt.setFloat("rotX", this.rotationX);
        nbt.setFloat("rotY", this.rotationY);
        nbt.setFloat("transparency", this.transparency);
        nbt.setFloat("brightness", this.brightness);
    }
    
    @SideOnly(Side.CLIENT)
    public void onDataPacket(final NetworkManager net, final S35PacketUpdateTileEntity pkt) {
        super.onDataPacket(net, pkt);
        this.url = pkt.func_148857_g().getString("url");
        this.sizeX = pkt.func_148857_g().getFloat("sizeX");
        this.sizeY = pkt.func_148857_g().getFloat("sizeY");
        this.renderDistance = pkt.func_148857_g().getInteger("render");
        this.posX = pkt.func_148857_g().getByte("offsetX");
        this.posY = pkt.func_148857_g().getByte("offsetY");
        this.rotation = pkt.func_148857_g().getByte("rotation");
        this.visibleFrame = pkt.func_148857_g().getBoolean("visibleFrame");
        this.anim = pkt.func_148857_g().getBoolean("anim");
        this.flippedX = pkt.func_148857_g().getBoolean("flippedX");
        this.flippedY = pkt.func_148857_g().getBoolean("flippedY");
        this.failed = false;
        this.loadTexture();
    }
}
