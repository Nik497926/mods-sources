/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.FMLClientHandler
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.particle.EntityFX
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.Item
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.twilight;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import net.divinerpg.DivineRPG;
import net.divinerpg.entities.fx.EntityColoredFX;
import net.divinerpg.entities.vanilla.projectile.EntityShooterBullet;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityParticleBullet
extends EntityShooterBullet {
    public EntityParticleBullet(World w) {
        super(w);
    }

    public EntityParticleBullet(World world, EntityLivingBase entity, float damage, String texture, String particle) {
        super(world, entity, damage, texture);
        this.setParticle(particle);
    }

    public EntityParticleBullet(World world, EntityLivingBase entity, float damage, Item ammo, String particle) {
        super(world, entity, damage, ammo);
        this.setParticle(particle);
    }

    public EntityParticleBullet(World world, EntityLivingBase entity, float damage) {
        super(world, entity, damage);
        this.setParticle("off");
    }

    public EntityParticleBullet(World world, int time, int lvl, EntityLivingBase entity, float damage, String effect) {
        super(world, time, lvl, entity, damage, effect);
        this.setParticle("off");
    }

    public EntityParticleBullet(World world, EntityLivingBase entity, float damage, String texture, Color c) {
        super(world, entity, damage, texture);
        this.setColor(c);
    }

    public EntityParticleBullet(World world, double posX, double posY, double posZ, float damage, String texture, Color c) {
        super(world, posX, posY, posZ, damage, texture);
        this.setColor(c);
    }

    public EntityParticleBullet(World world, EntityLivingBase entity, int time, int lvl, String type, int damage, String fx) {
        super(world, entity, time, lvl, type, damage, fx);
        this.setParticle(fx);
    }

    public EntityParticleBullet(World world, EntityLivingBase entity, int damage, String fx) {
        super(world, entity, fx, damage);
        this.setParticle(fx);
    }

    @Override
    public void entityInit() {
        super.entityInit();
        this.dataWatcher.addObject(19, (Object)0);
        this.dataWatcher.addObject(20, (Object)0);
        this.dataWatcher.addObject(21, (Object)0);
        this.dataWatcher.addObject(22, (Object)"");
        this.dataWatcher.addObject(23, (Object)0);
    }

    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tag.setString("Particle", this.getParticle());
        tag.setInteger("Red", this.getColor().getRed());
        tag.setInteger("Green", this.getColor().getGreen());
        tag.setInteger("Blue", this.getColor().getBlue());
    }

    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        this.setParticle(tag.getString("Particle"));
        if (tag.hasKey("Red")) {
            this.setColor(new Color(tag.getInteger("Red"), tag.getInteger("Green"), tag.getInteger("Blue")));
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void onUpdate() {
        block4: {
            block3: {
                super.onUpdate();
                if (this.getParticle().equals("")) break block3;
                if (this.getParticle().equals("off")) break block4;
                for (int var3 = 0; var3 < 8; ++var3) {
                    DivineRPG.proxy.spawnParticle(this.worldObj, this.posX, this.posY, this.posZ, this.getParticle(), true);
                }
                break block4;
            }
            for (int var3 = 0; var3 < 8; ++var3) {
                EntityColoredFX e = new EntityColoredFX(this.worldObj, this.posX + (this.rand.nextDouble() - this.rand.nextDouble()) / 4.0, this.posY + (this.rand.nextDouble() - this.rand.nextDouble()) / 4.0, this.posZ + (this.rand.nextDouble() - this.rand.nextDouble()) / 4.0, 0.0, 0.0, 0.0, this.getColor());
                if (this.getBiggerParticles()) {
                    e.bigger = true;
                }
                FMLClientHandler.instance().getClient().effectRenderer.addEffect((EntityFX)e);
            }
        }
    }

    public EntityParticleBullet setMoreParticles() {
        this.setBiggerParticles();
        return this;
    }

    public void setColor(Color c) {
        this.dataWatcher.updateObject(19, (Object)c.getRed());
        this.dataWatcher.updateObject(20, (Object)c.getGreen());
        this.dataWatcher.updateObject(21, (Object)c.getBlue());
    }

    public Color getColor() {
        return new Color(this.dataWatcher.getWatchableObjectInt(19), this.dataWatcher.getWatchableObjectInt(20), this.dataWatcher.getWatchableObjectInt(21));
    }

    public String getParticle() {
        return this.dataWatcher.getWatchableObjectString(22);
    }

    public void setParticle(String p) {
        this.dataWatcher.updateObject(22, (Object)p);
    }

    public void setBiggerParticles() {
        this.dataWatcher.updateObject(23, (Object)1);
    }

    public boolean getBiggerParticles() {
        return this.dataWatcher.getWatchableObjectInt(23) == 1;
    }
}

