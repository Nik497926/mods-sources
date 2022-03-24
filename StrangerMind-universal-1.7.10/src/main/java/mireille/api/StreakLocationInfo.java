package mireille.api;

import net.minecraft.entity.player.*;

public class StreakLocationInfo
{
    public double posX;
    public double posY;
    public double posZ;
    public float renderYawOffset;
    public float rotationYawHead;
    public float rotationPitch;
    public float limbSwing;
    public float limbSwingAmount;
    public boolean isSprinting;
    public long lastTick;
    public float height;
    public double startU;
    
    public StreakLocationInfo(final EntityPlayer player) {
        this.update(player);
    }
    
    public void update(final EntityPlayer player) {
        this.posX = player.posX;
        this.posY = player.boundingBox.minY;
        this.posZ = player.posZ;
        this.renderYawOffset = player.renderYawOffset;
        this.rotationYawHead = player.rotationYawHead;
        this.rotationPitch = player.rotationPitch;
        this.limbSwing = player.limbSwing;
        this.limbSwingAmount = player.limbSwingAmount;
        this.isSprinting = player.isSprinting();
        this.lastTick = player.worldObj.getWorldTime();
        this.height = player.height;
    }
    
    public boolean hasSameCoords(final StreakLocationInfo info) {
        return info.posX == this.posX && info.posY == this.posY && info.posZ == this.posZ && info.height == this.height;
    }
}
