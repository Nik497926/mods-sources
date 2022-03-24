package mireille.client.opf;

public abstract class PictureTexture
{
    public final int width;
    public final int height;
    public final boolean animation;
    
    public PictureTexture(final int width, final int height, final boolean animation) {
        this.width = width;
        this.height = height;
        this.animation = animation;
    }
    
    public abstract void tick();
    
    public abstract int getTextureID();
}
