/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.entity;

import com.mojang.authlib.GameProfile;
import java.lang.ref.WeakReference;
import java.util.UUID;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayerFactory;

public class FakePlayer {
    private static GameProfile gameProfile;
    private static WeakReference fakePlayer;

    public FakePlayer() {
        gameProfile = new GameProfile(UUID.fromString("C3F2EF82-E759-53EA-9D69-0D6E394A00B8"), "[ExtraBotany]");
        fakePlayer = new WeakReference<Object>(null);
    }

    public static WeakReference getFakePlayer(WorldServer server) {
        if (fakePlayer.get() == null) {
            fakePlayer = new WeakReference<net.minecraftforge.common.util.FakePlayer>(FakePlayerFactory.get((WorldServer)server, (GameProfile)gameProfile));
        } else {
            ((EntityPlayerMP)FakePlayer.fakePlayer.get()).worldObj = server;
        }
        return fakePlayer;
    }
}

