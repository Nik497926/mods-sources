package net.divinerpg.dimensions.vethea;

import com.gamerforea.divinerpg.EventConfig;
import net.divinerpg.utils.DimensionHelper;
import net.divinerpg.utils.config.ConfigurationHelper;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldProviderVethea extends WorldProvider
{

	@Override
	public void registerWorldChunkManager()
	{
		this.worldChunkMgr = new WorldChunkManagerHell(DimensionHelper.vetheaBiome, 1.0F);
		this.dimensionId = ConfigurationHelper.vethea;
	}

	@Override
	public float getCloudHeight()
	{
		return 256.0F;
	}

	@Override
	public IChunkProvider createChunkGenerator()
	{
		return new ChunkProviderVethea(this.worldObj, this.worldObj.getSeed());
	}

	@Override
	public boolean isSurfaceWorld()
	{
		return false;
	}

	@Override
	public boolean canCoordinateBeSpawn(int var1, int var2)
	{
		return false;
	}

	@Override
	public float calculateCelestialAngle(long var1, float var3)
	{
		return 0.3F;
	}

	@Override
	public boolean canRespawnHere()
	{
		return true;
	}

	@Override
	public String getSaveFolder()
	{
		// TODO gamerforEA code start
		if (EventConfig.safeWorldSave)
			return super.getSaveFolder();
		// TODO gamerforEA code end

		return "Vethea";
	}

	@Override
	public double getMovementFactor()
	{
		return 1.0D;
	}

	@Override
	public String getDimensionName()
	{
		return "Vethea";
	}
}
