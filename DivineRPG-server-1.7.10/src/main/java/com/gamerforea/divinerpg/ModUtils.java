package com.gamerforea.divinerpg;

import com.gamerforea.eventhelper.util.EventUtils;
import com.gamerforea.eventhelper.util.FastUtils;
import com.mojang.authlib.GameProfile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;

import java.util.UUID;

public final class ModUtils
{
	public static final GameProfile profile = new GameProfile(UUID.fromString("6762eb19-aa71-4346-963f-7c69cc8a8ab5"), "[DivineRPG]");
	private static FakePlayer player = null;

	public static FakePlayer getModFake(World world)
	{
		if (player == null)
			player = FastUtils.getFake(world, profile);
		else
			player.worldObj = world;

		return player;
	}

	public static boolean isMember(EntityPlayer player, int x, int y, int z)
	{
		FakePlayer fakePlayer = FastUtils.getFake(player.worldObj, player.getGameProfile());
		return !EventUtils.cantBreak(fakePlayer, x, y, z);
	}
}
