package com.gamerforea.thaumcraft;

import com.gamerforea.eventhelper.nexus.ModNexus;
import com.gamerforea.eventhelper.nexus.ModNexusFactory;
import com.gamerforea.eventhelper.nexus.NexusUtils;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.common.util.FakePlayer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@ModNexus(name = "ThaumCraft", uuid = "745dd166-13e9-41db-999d-6af5bacba7fd")
public final class ModUtils
{
	public static final Logger LOGGER = LogManager.getLogger("ThaumCraft");
	public static final ModNexusFactory NEXUS_FACTORY = NexusUtils.getFactory();

	public static FakePlayer getModFake(World world)
	{
		return NEXUS_FACTORY.getFake(world);
	}

	public static void init()
	{
		EventConfig.init();
		FixHandler.init();
	}

	public static void killEntity(Entity entity)
	{
		World world = entity.worldObj;
		world.removeEntity(entity);

		IChunkProvider provider = world.getChunkProvider();
		int chunkX = entity.chunkCoordX;
		int chunkZ = entity.chunkCoordZ;
		if (provider.chunkExists(chunkX, chunkZ))
		{
			Chunk chunk = provider.provideChunk(chunkX, chunkZ);
			if (chunk != null)
				chunk.removeEntity(entity);
		}
	}

	public static boolean isValidStack(ItemStack stack)
	{
		return stack != null && stack.stackSize > 0;
	}
}
