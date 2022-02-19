package thaumcraft.common.tiles;

import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.entities.monster.EntityTaintacle;
import thaumcraft.common.entities.monster.boss.EntityCultistPortal;
import thaumcraft.common.entities.monster.boss.EntityEldritchGolem;
import thaumcraft.common.entities.monster.boss.EntityEldritchWarden;
import thaumcraft.common.entities.monster.boss.EntityTaintacleGiant;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.fx.PacketFXBlockSparkle;
import thaumcraft.common.lib.utils.BlockUtils;
import thaumcraft.common.lib.utils.EntityUtils;
import thaumcraft.common.lib.utils.Utils;
import thaumcraft.common.lib.world.ThaumcraftWorldGenerator;
import thaumcraft.common.lib.world.dim.*;

import static com.gamerforea.thaumcraft.ChatUtils.translate;

public class TileEldritchLock extends TileThaumcraft
{
	public int count = -1;
	int[][] ped = { { 2, 2, 2 }, { 0, -1, 1 }, { 3, 3, 3 } };
	byte facing = 0;

	@Override
	public boolean canUpdate()
	{
		return true;
	}

	@Override
	public void updateEntity()
	{
		super.updateEntity();
		if (this.count != -1)
		{
			++this.count;
			if (this.count % 5 == 0)
				this.worldObj.playSoundEffect((double) this.xCoord, (double) this.yCoord, (double) this.zCoord, "thaumcraft:pump", 1.0F, 1.0F);

			if (this.count > 100)
				this.doBossSpawn();
		}

	}

	private void doBossSpawn()
	{
		this.worldObj.playSoundEffect((double) this.xCoord, (double) this.yCoord, (double) this.zCoord, "thaumcraft:ice", 1.0F, 1.0F);
		if (!this.worldObj.isRemote)
		{
			int cx = this.xCoord >> 4;
			int cz = this.zCoord >> 4;
			int centerx = this.xCoord >> 4;
			int centerz = this.zCoord >> 4;
			int exit = 0;

			for (int a = -2; a <= 2; ++a)
			{
				for (int b = -2; b <= 2; ++b)
				{
					Cell c = MazeHandler.getFromHashMap(new CellLoc(cx + a, cz + b));
					if (c != null && c.feature == 2)
					{
						centerx = cx + a;
						centerz = cz + b;
					}

					if (c != null && c.feature >= 2 && c.feature <= 5 && (c.north || c.south || c.east || c.west))
						exit = c.feature;
				}
			}

			MapBossData mbd = (MapBossData) this.worldObj.loadItemData(MapBossData.class, "BossMapData");
			if (mbd == null)
			{
				mbd = new MapBossData("BossMapData");
				mbd.bossCount = 0;
				mbd.markDirty();
				this.worldObj.setItemData("BossMapData", mbd);
			}

			++mbd.bossCount;
			if (this.worldObj.rand.nextFloat() < 0.25F)
				++mbd.bossCount;

			mbd.markDirty();
			switch (mbd.bossCount % 4)
			{
				case 0:
					this.spawnGolemBossRoom(centerx, centerz, exit);
					break;
				case 1:
					this.spawnWardenBossRoom(centerx, centerz, exit);
					break;
				case 2:
					this.spawnCultistBossRoom(centerx, centerz, exit);
					break;
				case 3:
					this.spawnTaintBossRoom(centerx, centerz, exit);
			}

			for (int a = -2; a <= 2; ++a)
			{
				for (int b = -2; b <= 2; ++b)
				{
					for (int c = -2; c <= 2; ++c)
					{
						if (this.worldObj.getBlock(this.xCoord + a, this.yCoord + b, this.zCoord + c) == ConfigBlocks.blockAiry)
						{
							PacketHandler.INSTANCE.sendToAllAround(new PacketFXBlockSparkle(this.xCoord + a, this.yCoord + b, this.zCoord + c, 4194368), new TargetPoint(this.worldObj.provider.dimensionId, (double) (this.xCoord + a), (double) (this.yCoord + b), (double) (this.zCoord + c), 32.0D));
							this.worldObj.setBlockToAir(this.xCoord + a, this.yCoord + b, this.zCoord + c);
						}
					}
				}
			}

			this.worldObj.setBlockToAir(this.xCoord, this.yCoord, this.zCoord);
		}

	}

	private void spawnWardenBossRoom(int cx, int cz, int exit)
	{
		for (int i = 0; i < this.worldObj.playerEntities.size(); ++i)
		{
			EntityPlayer player = (EntityPlayer) this.worldObj.playerEntities.get(i);
			if (player.getDistanceSq((double) this.xCoord, (double) this.yCoord, (double) this.zCoord) < 300.0D)
				// TODO gamerforEA code replace, old code:
				// player.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("tc.boss.warden")));
				player.addChatComponentMessage(translate("tc.boss.warden"));
			// TODO gamerforEA code end
		}

		int x = cx * 16 + 16;
		int y = 50;
		int z = cz * 16 + 16;
		int x2 = x;
		int z2 = z;
		switch (exit)
		{
			case 2:
				x2 = x + 8;
				z2 = z + 8;
				break;
			case 3:
				x2 = x - 8;
				z2 = z + 8;
				break;
			case 4:
				x2 = x + 8;
				z2 = z - 8;
				break;
			case 5:
				x2 = x - 8;
				z2 = z - 8;
		}

		GenCommon.genObelisk(this.worldObj, x2, y + 4, z);
		GenCommon.genObelisk(this.worldObj, x, y + 4, z2);
		this.worldObj.setBlock(x2, y + 2, z, ConfigBlocks.blockEldritch, 3, 3);
		this.worldObj.setBlock(x, y + 2, z2, ConfigBlocks.blockEldritch, 3, 3);

		for (int a = -1; a <= 1; ++a)
		{
			for (int b = -1; b <= 1; ++b)
			{
				if (a != 0 && b != 0 && this.worldObj.rand.nextFloat() < 0.9F)
				{
					float rr = this.worldObj.rand.nextFloat();
					int md = rr < 0.1F ? 2 : rr < 0.3F ? 1 : 0;
					this.worldObj.setBlock(x2 + a, y + 2, z + b, ConfigBlocks.blockLootUrn, md, 3);
				}

				if (a != 0 && b != 0 && this.worldObj.rand.nextFloat() < 0.9F)
				{
					float rr = this.worldObj.rand.nextFloat();
					int md = rr < 0.1F ? 2 : rr < 0.3F ? 1 : 0;
					this.worldObj.setBlock(x + a, y + 2, z2 + b, ConfigBlocks.blockLootUrn, md, 3);
				}
			}
		}

		this.worldObj.setBlock(x - 2, y + 3, z - 2, ConfigBlocks.blockEldritch, 10, 3);
		this.worldObj.setBlock(x - 2, y + 3, z + 2, ConfigBlocks.blockEldritch, 10, 3);
		this.worldObj.setBlock(x + 2, y + 3, z + 2, ConfigBlocks.blockEldritch, 10, 3);
		this.worldObj.setBlock(x + 2, y + 3, z - 2, ConfigBlocks.blockEldritch, 10, 3);
		this.worldObj.setBlock(x - 2, y + 2, z - 2, ConfigBlocks.blockCosmeticSolid, 15, 3);
		this.worldObj.setBlock(x - 2, y + 2, z + 2, ConfigBlocks.blockCosmeticSolid, 15, 3);
		this.worldObj.setBlock(x + 2, y + 2, z + 2, ConfigBlocks.blockCosmeticSolid, 15, 3);
		this.worldObj.setBlock(x + 2, y + 2, z - 2, ConfigBlocks.blockCosmeticSolid, 15, 3);

		for (int a = 0; a < 3; ++a)
		{
			for (int b = 0; b < 3; ++b)
			{
				if (this.ped[a][b] < 0)
					this.worldObj.setBlock(x2 - 1 + b, y + 2, z2 - 1 + a, ConfigBlocks.blockEldritch, 4, 3);
				else
					this.worldObj.setBlock(x2 - 1 + b, y + 2, z2 - 1 + a, ConfigBlocks.blockStairsEldritch, this.ped[a][b], 3);
			}
		}

		EntityEldritchWarden boss = new EntityEldritchWarden(this.worldObj);
		double d0 = (double) this.xCoord - ((double) x2 + 0.5D);
		double d1 = (double) ((float) this.yCoord - ((float) (y + 3) + boss.getEyeHeight()));
		double d2 = (double) this.zCoord - ((double) z2 + 0.5D);
		double d3 = (double) MathHelper.sqrt_double(d0 * d0 + d2 * d2);
		float f = (float) (Math.atan2(d2, d0) * 180.0D / 3.141592653589793D) - 90.0F;
		float f1 = (float) -(Math.atan2(d1, d3) * 180.0D / 3.141592653589793D);
		boss.setLocationAndAngles((double) x2 + 0.5D, (double) (y + 3), (double) z2 + 0.5D, f, f1);
		boss.onSpawnWithEgg(null);
		boss.setHomeArea(x, y + 2, z, 32);
		this.worldObj.spawnEntityInWorld(boss);
	}

	private void spawnGolemBossRoom(int cx, int cz, int exit)
	{
		for (int i = 0; i < this.worldObj.playerEntities.size(); ++i)
		{
			EntityPlayer player = (EntityPlayer) this.worldObj.playerEntities.get(i);
			if (player.getDistanceSq((double) this.xCoord, (double) this.yCoord, (double) this.zCoord) < 300.0D)
				// TODO gamerforEA code replace, old code:
				// player.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("tc.boss.golem")));
				player.addChatComponentMessage(translate("tc.boss.golem"));
			// TODO gamerforEA code end
		}

		int x = cx * 16 + 16;
		int y = 50;
		int z = cz * 16 + 16;
		int x2 = 0;
		int z2 = 0;
		switch (exit)
		{
			case 2:
				x2 = 8;
				z2 = 8;
				break;
			case 3:
				x2 = -8;
				z2 = 8;
				break;
			case 4:
				x2 = 8;
				z2 = -8;
				break;
			case 5:
				x2 = -8;
				z2 = -8;
		}

		GenCommon.genObelisk(this.worldObj, x + x2, y + 4, z + z2);
		GenCommon.genObelisk(this.worldObj, x - x2, y + 4, z + z2);
		GenCommon.genObelisk(this.worldObj, x + x2, y + 4, z - z2);
		this.worldObj.setBlock(x + x2, y + 2, z + z2, ConfigBlocks.blockEldritch, 3, 3);
		this.worldObj.setBlock(x - x2, y + 2, z + z2, ConfigBlocks.blockEldritch, 3, 3);
		this.worldObj.setBlock(x + x2, y + 2, z - z2, ConfigBlocks.blockEldritch, 3, 3);

		for (int a = 0; a < 3; ++a)
		{
			for (int b = 0; b < 3; ++b)
			{
				if (this.ped[a][b] < 0)
					this.worldObj.setBlock(x - 1 + b, y + 2, z - 1 + a, ConfigBlocks.blockEldritch, 4, 3);
				else
					this.worldObj.setBlock(x - 1 + b, y + 2, z - 1 + a, ConfigBlocks.blockStairsEldritch, this.ped[a][b], 3);
			}
		}

		for (int a = -10; a <= 10; ++a)
		{
			for (int b = -10; b <= 10; ++b)
			{
				if ((a < -2 && b < -2 || a > 2 && b > 2 || a < -2 && b > 2 || a > 2 && b < -2) && this.worldObj.rand.nextFloat() < 0.15F && this.worldObj.isAirBlock(x + a, y + 2, z + b))
				{
					float rr = this.worldObj.rand.nextFloat();
					int md = rr < 0.05F ? 2 : rr < 0.2F ? 1 : 0;
					this.worldObj.setBlock(x + a, y + 2, z + b, this.worldObj.rand.nextFloat() < 0.3F ? ConfigBlocks.blockLootCrate : ConfigBlocks.blockLootUrn, md, 3);
				}
			}
		}

		EntityEldritchGolem boss = new EntityEldritchGolem(this.worldObj);
		double d0 = (double) this.xCoord - ((double) x + 0.5D);
		double d1 = (double) ((float) this.yCoord - ((float) (y + 3) + boss.getEyeHeight()));
		double d2 = (double) this.zCoord - ((double) z + 0.5D);
		double d3 = (double) MathHelper.sqrt_double(d0 * d0 + d2 * d2);
		float f = (float) (Math.atan2(d2, d0) * 180.0D / 3.141592653589793D) - 90.0F;
		float f1 = (float) -(Math.atan2(d1, d3) * 180.0D / 3.141592653589793D);
		boss.setLocationAndAngles((double) x + 0.5D, (double) (y + 3), (double) z + 0.5D, f, f1);
		boss.onSpawnWithEgg(null);
		this.worldObj.spawnEntityInWorld(boss);
	}

	private void spawnCultistBossRoom(int cx, int cz, int exit)
	{
		for (int i = 0; i < this.worldObj.playerEntities.size(); ++i)
		{
			EntityPlayer player = (EntityPlayer) this.worldObj.playerEntities.get(i);
			if (player.getDistanceSq((double) this.xCoord, (double) this.yCoord, (double) this.zCoord) < 300.0D)
				// TODO gamerforEA code replace, old code:
				// player.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("tc.boss.crimson")));
				player.addChatComponentMessage(translate("tc.boss.crimson"));
			// TODO gamerforEA code end
		}

		int x = cx * 16 + 16;
		int y = 50;
		int z = cz * 16 + 16;

		for (int a = -4; a <= 4; ++a)
		{
			for (int b = -4; b <= 4; ++b)
			{
				if ((Math.abs(a) != 2 && Math.abs(b) != 2 || !this.worldObj.rand.nextBoolean()) && (Math.abs(a) != 3 && Math.abs(b) != 3 || this.worldObj.rand.nextFloat() <= 0.33F) && (Math.abs(a) != 4 && Math.abs(b) != 4 || this.worldObj.rand.nextFloat() <= 0.25F))
					this.worldObj.setBlock(x + b, y + 1, z + a, ConfigBlocks.blockEldritch, 7, 3);
			}
		}

		for (int a = 0; a < 5; ++a)
		{
			for (int b = 0; b < 5; ++b)
			{
				if (a == 0 || a == 4 || b == 0 || b == 4)
				{
					this.worldObj.setBlock(x - 8 + b * 4, y + 2, z - 8 + a * 4, ConfigBlocks.blockCosmeticSolid, 11, 3);
					this.worldObj.setBlock(x - 8 + b * 4, y + 3, z - 8 + a * 4, ConfigBlocks.blockEldritch, 5, 3);
					this.worldObj.setBlock(x - 8 + b * 4, y + 4, z - 8 + a * 4, ConfigBlocks.blockSlabStone, 1, 3);
					this.worldObj.setBlock(x - 8 + b * 4, y + 10, z - 8 + a * 4, ConfigBlocks.blockCosmeticSolid, 11, 3);
					this.worldObj.setBlock(x - 8 + b * 4, y + 9, z - 8 + a * 4, ConfigBlocks.blockEldritch, 5, 3);
					this.worldObj.setBlock(x - 8 + b * 4, y + 8, z - 8 + a * 4, ConfigBlocks.blockSlabStone, 9, 3);
				}
			}
		}

		EntityCultistPortal boss = new EntityCultistPortal(this.worldObj);
		boss.setLocationAndAngles((double) x + 0.5D, (double) (y + 2), (double) z + 0.5D, 0.0F, 0.0F);
		this.worldObj.spawnEntityInWorld(boss);
	}

	private void spawnTaintBossRoom(int cx, int cz, int exit)
	{
		for (int i = 0; i < this.worldObj.playerEntities.size(); ++i)
		{
			EntityPlayer player = (EntityPlayer) this.worldObj.playerEntities.get(i);
			if (player.getDistanceSq((double) this.xCoord, (double) this.yCoord, (double) this.zCoord) < 300.0D)
				// TODO gamerforEA code replace, old code:
				// player.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("tc.boss.taint")));
				player.addChatComponentMessage(translate("tc.boss.taint"));
			// TODO gamerforEA code end
		}

		int x = cx * 16 + 16;
		int y = 50;
		int z = cz * 16 + 16;

		for (int a = -12; a <= 12; ++a)
		{
			for (int b = -12; b <= 12; ++b)
			{
				Utils.setBiomeAt(this.worldObj, x + b, z + a, ThaumcraftWorldGenerator.biomeTaint);

				for (int c = 0; c < 9; ++c)
				{
					if (this.worldObj.isAirBlock(x + b, y + 2 + c, z + a) && BlockUtils.isAdjacentToSolidBlock(this.worldObj, x + b, y + 2 + c, z + a) && this.worldObj.rand.nextInt(3) != 0)
						this.worldObj.setBlock(x + b, y + 2 + c, z + a, ConfigBlocks.blockTaintFibres, this.worldObj.rand.nextInt(4) == 0 ? 1 : 0, 3);
				}

				if ((double) this.worldObj.rand.nextFloat() < 0.15D)
				{
					this.worldObj.setBlock(x + b, y + 2, z + a, ConfigBlocks.blockTaint, 0, 3);
					if ((double) this.worldObj.rand.nextFloat() < 0.2D)
						this.worldObj.setBlock(x + b, y + 3, z + a, ConfigBlocks.blockTaint, 0, 3);
				}

				if ((Math.abs(a) != 4 && Math.abs(b) != 4 || !this.worldObj.rand.nextBoolean()) && (Math.abs(a) < 5 && Math.abs(b) < 5 || this.worldObj.rand.nextFloat() <= 0.33F) && (Math.abs(a) < 7 && Math.abs(b) < 7 || this.worldObj.rand.nextFloat() <= 0.25F))
					this.worldObj.setBlock(x + b, y + 1, z + a, ConfigBlocks.blockTaint, 1, 3);
			}
		}

		EntityTaintacle boss1 = this.worldObj.difficultySetting != EnumDifficulty.HARD ? new EntityTaintacle(this.worldObj) : new EntityTaintacleGiant(this.worldObj);
		boss1.setLocationAndAngles((double) x + 0.5D, (double) (y + 3), (double) z + 0.5D, 0.0F, 0.0F);
		EntityUtils.makeChampion(boss1, true);
		this.worldObj.spawnEntityInWorld(boss1);
		EntityTaintacle boss2 = this.worldObj.rand.nextBoolean() ? new EntityTaintacle(this.worldObj) : new EntityTaintacleGiant(this.worldObj);
		boss2.setLocationAndAngles((double) x + 3.5D, (double) (y + 3), (double) z + 3.5D, 0.0F, 0.0F);
		EntityUtils.makeChampion(boss2, true);
		this.worldObj.spawnEntityInWorld(boss2);
		EntityTaintacle boss3 = boss2 instanceof EntityTaintacleGiant ? new EntityTaintacle(this.worldObj) : new EntityTaintacleGiant(this.worldObj);
		boss3.setLocationAndAngles((double) x - 2.5D, (double) (y + 3), (double) z + 3.5D, 0.0F, 0.0F);
		EntityUtils.makeChampion(boss3, true);
		this.worldObj.spawnEntityInWorld(boss3);
		EntityTaintacle boss4 = this.worldObj.rand.nextBoolean() ? new EntityTaintacle(this.worldObj) : new EntityTaintacleGiant(this.worldObj);
		boss4.setLocationAndAngles((double) x + 3.5D, (double) (y + 3), (double) z - 2.5D, 0.0F, 0.0F);
		EntityUtils.makeChampion(boss4, true);
		this.worldObj.spawnEntityInWorld(boss4);
		EntityTaintacle boss5 = boss4 instanceof EntityTaintacleGiant ? new EntityTaintacle(this.worldObj) : new EntityTaintacleGiant(this.worldObj);
		boss5.setLocationAndAngles((double) x - 2.5D, (double) (y + 3), (double) z - 2.5D, 0.0F, 0.0F);
		EntityUtils.makeChampion(boss5, true);
		this.worldObj.spawnEntityInWorld(boss5);
	}

	@Override
	public double getMaxRenderDistanceSquared()
	{
		return 9216.0D;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getRenderBoundingBox()
	{
		return AxisAlignedBB.getBoundingBox((double) this.xCoord - 2.25D, (double) this.yCoord - 2.25D, (double) this.zCoord - 2.25D, (double) this.xCoord + 3.25D, (double) this.yCoord + 3.25D, (double) this.zCoord + 3.25D);
	}

	public byte getFacing()
	{
		return this.facing;
	}

	public void setFacing(byte face)
	{
		this.facing = face;
		this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
		this.markDirty();
	}

	@Override
	public void readCustomNBT(NBTTagCompound nbttagcompound)
	{
		this.facing = nbttagcompound.getByte("facing");
		this.count = nbttagcompound.getShort("count");
	}

	@Override
	public void writeCustomNBT(NBTTagCompound nbttagcompound)
	{
		nbttagcompound.setByte("facing", this.facing);
		nbttagcompound.setShort("count", (short) this.count);
	}
}
