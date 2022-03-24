package mireille.client.opf;

public class OrdinaryTexture extends PictureTexture
{
    private final int textureID;
    
    public OrdinaryTexture(final ProcessedImageData image) {
        super(image.getWidth(), image.getHeight(), false);
        this.textureID = image.uploadFrame(0);
    }
    
    @Override
    public void tick() {
    }
    
    @Override
    public int getTextureID() {
        return this.textureID;
    }
}
