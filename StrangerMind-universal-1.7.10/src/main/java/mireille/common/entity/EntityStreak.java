package mireille.common.entity;

import cpw.mods.fml.relauncher.*;
import net.minecraft.entity.*;
import net.minecraft.world.*;
import mireille.*;
import net.minecraft.nbt.*;

@SideOnly(Side.CLIENT)
public class EntityStreak extends Entity
{
    public EntityLivingBase parent;
    public long lastUpdate;
    
    public EntityStreak(final World par1World) {
        super(par1World);
        this.setSize(0.1f, 0.1f);
        this.lastUpdate = par1World.getWorldTime();
        super.ignoreFrustumCheck = true;
        super.renderDistanceWeight = 10.0;
    }
    
    public EntityStreak(final World par1World, final EntityLivingBase ent) {
        super(par1World);
        this.setSize(0.1f, 0.1f);
        this.parent = ent;
        this.setLocationAndAngles(this.parent.posX, this.parent.boundingBox.minY, this.parent.posZ, this.parent.rotationYaw, this.parent.rotationPitch);
        this.lastUpdate = par1World.getWorldTime();
        super.ignoreFrustumCheck = true;
        super.renderDistanceWeight = 10.0;
    }
    
    public void onUpdate() {
        ++super.ticksExisted;
        if (this.parent != null && this.parent.isEntityAlive() && !this.parent.isChild() && StrangerMind.tickHandlerClient.streaks.get(this.parent.getCommandSenderName()) == this) {
            this.lastUpdate = super.worldObj.getWorldTime();
        }
        else {
            this.setDead();
        }
    }
    
    public boolean shouldRenderInPass(final int pass) {
        return pass == 1;
    }
    
    public int getBrightnessForRender(final float par1) {
        return 15728880;
    }
    
    public void setDead() {
        super.setDead();
    }
    
    public void entityInit() {
    }
    
    public boolean writeToNBTOptional(final NBTTagCompound par1NBTTagCompound) {
        return false;
    }
    
    public void readEntityFromNBT(final NBTTagCompound nbttagcompound) {
    }
    
    public void writeEntityToNBT(final NBTTagCompound nbttagcompound) {
    }
}
