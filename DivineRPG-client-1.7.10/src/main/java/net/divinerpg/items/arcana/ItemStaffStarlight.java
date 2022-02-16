/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 */
package net.divinerpg.items.arcana;

import java.util.List;
import net.divinerpg.entities.arcana.projectile.EntityStar;
import net.divinerpg.items.base.ItemModRanged;
import net.divinerpg.items.base.ItemProjectileShooter;
import net.divinerpg.items.vethea.ItemStaff;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.TooltipLocalizer;
import net.divinerpg.utils.events.ArcanaHelper;
import net.divinerpg.utils.items.ArcanaItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class ItemStaffStarlight
extends ItemModRanged {
    public ItemStaffStarlight(String name) {
        super(name, -1, Sounds.starlight, EntityStar.class);
        ItemProjectileShooter.gunList.add(this);
        this.setFull3D();
        ItemStaff.staffList.add(this);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        float zVec;
        float yVec;
        double multiplyer;
        float pitchAngle;
        float rotationPitch = player.rotationPitch;
        float rotationYaw = player.rotationYaw;
        double x = player.posX;
        double y = player.posY + 1.62 - (double)player.yOffset;
        double z = player.posZ;
        Vec3 worldVector = Vec3.createVectorHelper((double)x, (double)y, (double)z);
        float yawAngleCos = MathHelper.cos((float)(-rotationYaw * 0.01745329f - (float)Math.PI));
        float yawAngleSin = MathHelper.sin((float)(-rotationYaw * 0.01745329f - (float)Math.PI));
        float xVec = yawAngleSin * (pitchAngle = -MathHelper.cos((float)(-rotationPitch * 0.01745329f)));
        Vec3 worldVector2 = worldVector.addVector((double)xVec * (multiplyer = 30.0), (double)(yVec = MathHelper.sin((float)(-rotationPitch * 0.01745329f))) * multiplyer, (double)(zVec = yawAngleCos * pitchAngle) * multiplyer);
        MovingObjectPosition objPos = world.rayTraceBlocks(worldVector, worldVector2);
        if (objPos == null) {
            return stack;
        }
        if (objPos.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
            int blockX = objPos.blockX;
            int blockY = objPos.blockY;
            int blockZ = objPos.blockZ;
            int side = objPos.sideHit;
            if (side == 0) {
                --blockY;
            }
            if (side == 1) {
                ++blockY;
            }
            if (side == 2) {
                --blockZ;
            }
            if (side == 3) {
                ++blockZ;
            }
            if (side == 4) {
                --blockX;
            }
            if (side == 5) {
                ++blockX;
            }
            if (stack.getItem() == ArcanaItems.staffStarlight) {
                if (!world.isRemote && ArcanaHelper.getProperties(player).useBar(25.0f)) {
                    for (int i = 0; i < 8; ++i) {
                        world.spawnEntityInWorld((Entity)new EntityStar(world, (double)blockX + 0.5, (double)blockY + 25.0, (double)blockZ + 0.5));
                    }
                    Sounds.playSound(Sounds.starlight, world, (Entity)player, 1.0f, 0.5f);
                }
            } else if (!world.isRemote && ArcanaHelper.getProperties(player).useBar(5.0f)) {
                world.spawnEntityInWorld((Entity)new EntityStar(world, (double)blockX + 0.5, (double)blockY + 25.0, (double)blockZ + 0.5));
                Sounds.playSound((Entity)player, world, Sounds.starlight);
            }
            player.getLook(1.0f);
        }
        return stack;
    }

    @Override
    protected void addAdditionalInformation(ItemStack stack, EntityPlayer player, List list, boolean par4) {
        boolean damage = false;
        int arcana = 0;
        boolean stars = false;
        if (stack.getItem() == ArcanaItems.staffStarlight) {
            arcana = 25;
            stars = true;
        } else {
            arcana = 5;
            stars = false;
        }
        list.add(TooltipLocalizer.arcanaConsumed(25));
        list.add(stars ? "Drops several stars from the sky" : "Drops a star from the sky");
        list.add(TooltipLocalizer.rangedDam(20.0));
    }
}

