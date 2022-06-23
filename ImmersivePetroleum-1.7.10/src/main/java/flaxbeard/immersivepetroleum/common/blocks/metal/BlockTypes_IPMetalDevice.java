package flaxbeard.immersivepetroleum.common.blocks.metal;

import java.util.Locale;

import net.minecraft.util.IStringSerializable;

import flaxbeard.immersivepetroleum.common.blocks.BlockIPBase;

public enum BlockTypes_IPMetalDevice implements IStringSerializable, BlockIPBase.IBlockEnum
{
	AUTOMATIC_LUBRICATOR,
	GAS_GENERATOR;

	@Override
	public String getName()
	{
		return this.toString().toLowerCase(Locale.ENGLISH);
	}
	@Override
	public int getMeta()
	{
		return ordinal();
	}
	@Override
	public boolean listForCreative()
	{
		return true;
	}
}