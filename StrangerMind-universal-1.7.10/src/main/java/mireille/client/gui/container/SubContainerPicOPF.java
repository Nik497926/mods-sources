package mireille.client.gui.container;

import com.creativemd.creativecore.common.container.*;
import mireille.common.tileentity.*;
import net.minecraft.entity.player.*;
import net.minecraft.nbt.*;
import mireille.*;

public class SubContainerPicOPF extends SubContainer
{
    public TileEntityPicFrame frame;
    
    public SubContainerPicOPF(final TileEntityPicFrame frame, final EntityPlayer player, final boolean animation) {
        super(player);
        this.frame = frame;
    }
    
    public void createControls() {
    }
    
    public void onGuiPacket(final int controlID, final NBTTagCompound nbt, final EntityPlayer player) {
        if (controlID == 0) {
            this.frame.url = nbt.getString("url");
            final float x = nbt.getFloat("x");
            final float y = nbt.getFloat("y");
            final float x2 = Math.min(ModConfig.OnlinePictureMaxSize, Math.abs(x));
            final float y2 = Math.min(ModConfig.OnlinePictureMaxSize, Math.abs(y));
            this.frame.sizeX = ((x < 0.0f) ? (-x2) : x2);
            this.frame.sizeY = ((y < 0.0f) ? (-y2) : y2);
            this.frame.renderDistance = nbt.getInteger("render");
            this.frame.posX = nbt.getByte("posX");
            this.frame.posY = nbt.getByte("posY");
            this.frame.rotation = nbt.getByte("rotation");
            this.frame.visibleFrame = nbt.getBoolean("visibleFrame");
            this.frame.anim = nbt.getBoolean("anim");
            this.frame.flippedX = nbt.getBoolean("flippedX");
            this.frame.flippedY = nbt.getBoolean("flippedY");
            this.frame.updateBlock();
        }
    }
}
