/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.core.network.client;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import thaumcraft.common.Thaumcraft;
import vazkii.botania.common.Botania;
import vazkii.botania.common.core.helper.Vector3;

public class MessageToClientVisual
implements IMessage,
IMessageHandler<MessageToClientVisual, IMessage> {
    private NBTTagCompound nbt;
    private EntityPlayer player;

    public MessageToClientVisual() {
    }

    public MessageToClientVisual(NBTTagCompound nbt) {
        this.nbt = nbt;
    }

    public void toBytes(ByteBuf buffer) {
        ByteBufUtils.writeTag(buffer, this.nbt);
    }

    public void fromBytes(ByteBuf buffer) {
        this.nbt = ByteBufUtils.readTag(buffer);
    }

    @SideOnly(value=Side.CLIENT)
    public IMessage onMessage(MessageToClientVisual message, MessageContext ctx) {
        try {
            NBTTagCompound _n;
            NBTTagCompound n = message.nbt;
            if (n.hasKey("light") && (_n = n.getCompoundTag("light")).hasKey("x") && _n.hasKey("y") && _n.hasKey("z")) {
                Thaumcraft.proxy.arcLightning(Minecraft.getMinecraft().thePlayer.worldObj, _n.getDouble("x"), _n.getDouble("y"), _n.getDouble("z"), Minecraft.getMinecraft().thePlayer.posX, Minecraft.getMinecraft().thePlayer.posY, Minecraft.getMinecraft().thePlayer.posZ, 0.1f, 1.0f, 1.0f, 0.4f);
            }
            if (n.hasKey("lighting") && (_n = n.getCompoundTag("lighting")).hasKey("player")) {
                World world = Minecraft.getMinecraft().thePlayer.worldObj;
                EntityPlayer player = world.getPlayerEntityByName(_n.getString("player"));
                EntityClientPlayerMP player1 = Minecraft.getMinecraft().thePlayer;
                if (player == null) {
                    return null;
                }
                Vector3 vec = Vector3.fromEntityCenter(player);
                double[] pos = new double[]{player1.posX - player.posX, player1.posY - player.posY, player1.posZ - player.posZ};
                Vector3 endVec = vec.copy().add(pos[0], pos[1], pos[2]);
                Botania.proxy.lightningFX(world, vec, endVec, 2.0f, 38027, 58583);
            }
            if (n.hasKey("jump")) {
                Minecraft.getMinecraft().thePlayer.motionY = n.getFloat("jump");
            }
            if (n.hasKey("damageNear")) {
                NBTTagCompound nb = n.getCompoundTag("damageNear");
                double xc = nb.getDouble("xc");
                double yc = nb.getDouble("yc");
                double zc = nb.getDouble("zc");
                if (nb.hasKey("ent")) {
                    NBTTagList nl = nb.getTagList("ent", 10);
                    for (int i = 0; i < nl.tagCount(); ++i) {
                        NBTTagCompound nbl = nl.getCompoundTagAt(i);
                        double xs = nbl.getDouble("xs");
                        double ys = nbl.getDouble("ys");
                        double zs = nbl.getDouble("zs");
                        Thaumcraft.proxy.arcLightning(Minecraft.getMinecraft().thePlayer.worldObj, xc, yc + 0.5, zc, xs, ys + 0.5, zs, 1.0f, 0.0f, 0.0f, 0.4f);
                    }
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return null;
    }
}

