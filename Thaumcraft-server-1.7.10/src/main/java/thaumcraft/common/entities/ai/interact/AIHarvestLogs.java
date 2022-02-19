package thaumcraft.common.entities.ai.interact;

import net.minecraft.block.Block;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.FakePlayer;
import thaumcraft.common.config.Config;
import thaumcraft.common.entities.golems.EntityGolemBase;
import thaumcraft.common.lib.utils.BlockUtils;
import thaumcraft.common.lib.utils.Utils;

import java.util.Random;

public class AIHarvestLogs extends EntityAIBase
{
	private EntityGolemBase theGolem;
	private int xx;
	private int yy;
	private int zz;
	private float movementSpeed;
	private float distance;
	private World theWorld;
	private Block block = Blocks.air;
	private int blockMd = 0;
	private int delay = -1;
	private int maxDelay = 1;
	private int mod = 1;
	FakePlayer player;
	private int count = 0;

	public AIHarvestLogs(EntityGolemBase par1EntityCreature)
	{
		this.theGolem = par1EntityCreature;
		this.theWorld = par1EntityCreature.worldObj;
		this.setMutexBits(3);
		this.distance = MathHelper.ceiling_float_int(this.theGolem.getRange() / 3.0F);
		if (this.theWorld instanceof WorldServer)
			// TODO gamerforEA code replace, old code: this.player = FakePlayerFactory.get((WorldServer) this.theWorld, new GameProfile(null, "FakeThaumcraftGolem"));
			this.player = this.theGolem.fake.getPlayer();
		// TODO gamerforEA code end
	}

	@Override
	public boolean shouldExecute()
	{
		if (this.delay < 0 && this.theGolem.ticksExisted % Config.golemDelay <= 0 && this.theGolem.getNavigator().noPath())
		{
			Vec3 var1 = this.findLog();
			if (var1 == null)
				return false;
			this.xx = (int) var1.xCoord;
			this.yy = (int) var1.yCoord;
			this.zz = (int) var1.zCoord;
			this.block = this.theWorld.getBlock(this.xx, this.yy, this.zz);
			this.blockMd = this.theWorld.getBlockMetadata(this.xx, this.yy, this.zz);
			return true;
		}
		return false;
	}

	@Override
	public boolean continueExecuting()
	{
		return this.theWorld.getBlock(this.xx, this.yy, this.zz) == this.block && this.theWorld.getBlockMetadata(this.xx, this.yy, this.zz) == this.blockMd && this.count-- > 0 && (this.delay > 0 || Utils.isWoodLog(this.theWorld, this.xx, this.yy, this.zz) || !this.theGolem.getNavigator().noPath());
	}

	@Override
	public void updateTask()
	{
		double dist = this.theGolem.getDistanceSq(this.xx + 0.5D, this.yy + 0.5D, this.zz + 0.5D);
		this.theGolem.getLookHelper().setLookPosition(this.xx + 0.5D, this.yy + 0.5D, this.zz + 0.5D, 30.0F, 30.0F);
		if (dist <= 4.0D)
		{
			if (this.delay < 0)
			{
				this.delay = (int) Math.max(5.0F, (20.0F - this.theGolem.getGolemStrength() * 3.0F) * this.block.getBlockHardness(this.theWorld, this.xx, this.yy, this.zz));
				this.maxDelay = this.delay;
				this.mod = this.delay / Math.round(this.delay / 6.0F);
			}

			if (this.delay > 0)
			{
				if (--this.delay > 0 && this.delay % this.mod == 0 && this.theGolem.getNavigator().noPath())
				{
					this.theGolem.startActionTimer();
					this.theWorld.playSoundEffect(this.xx + 0.5F, this.yy + 0.5F, this.zz + 0.5F, this.block.stepSound.getBreakSound(), (this.block.stepSound.getVolume() + 0.7F) / 8.0F, this.block.stepSound.getPitch() * 0.5F);
					BlockUtils.destroyBlockPartially(this.theWorld, this.theGolem.getEntityId(), this.xx, this.yy, this.zz, (int) (9.0F * (1.0F - (float) this.delay / (float) this.maxDelay)));
				}

				if (this.delay == 0)
				{
					this.harvest();
					if (Utils.isWoodLog(this.theWorld, this.xx, this.yy, this.zz))
					{
						this.delay = -1;
						this.block = this.theWorld.getBlock(this.xx, this.yy, this.zz);
						this.blockMd = this.theWorld.getBlockMetadata(this.xx, this.yy, this.zz);
						this.startExecuting();
					}
					else
						this.checkAdjacent();
				}
			}
		}

	}

	private void checkAdjacent()
	{
		for (int x2 = -1; x2 <= 1; ++x2)
		{
			for (int z2 = -1; z2 <= 1; ++z2)
			{
				for (int y2 = -1; y2 <= 1; ++y2)
				{
					int x = this.xx + x2;
					int y = this.yy + y2;
					int z = this.zz + z2;
					if (Math.abs(this.theGolem.getHomePosition().posX - x) <= this.distance && Math.abs(this.theGolem.getHomePosition().posY - y) <= this.distance && Math.abs(this.theGolem.getHomePosition().posZ - z) <= this.distance && Utils.isWoodLog(this.theWorld, x, y, z))
					{
						Vec3 var1 = Vec3.createVectorHelper(x, y, z);
						if (var1 != null)
						{
							this.xx = (int) var1.xCoord;
							this.yy = (int) var1.yCoord;
							this.zz = (int) var1.zCoord;
							this.block = this.theWorld.getBlock(this.xx, this.yy, this.zz);
							this.blockMd = this.theWorld.getBlockMetadata(this.xx, this.yy, this.zz);
							this.delay = -1;
							this.startExecuting();
							return;
						}
					}
				}
			}
		}

	}

	@Override
	public void resetTask()
	{
		BlockUtils.destroyBlockPartially(this.theWorld, this.theGolem.getEntityId(), this.xx, this.yy, this.zz, -1);
		this.delay = -1;
	}

	@Override
	public void startExecuting()
	{
		this.count = 200;
		this.theGolem.getNavigator().tryMoveToXYZ(this.xx + 0.5D, this.yy + 0.5D, this.zz + 0.5D, this.theGolem.getAIMoveSpeed());
	}

	void harvest()
	{
		this.count = 200;
		this.theWorld.playAuxSFX(2001, this.xx, this.yy, this.zz, Block.getIdFromBlock(this.block) + (this.blockMd << 12));
		BlockUtils.breakFurthestBlock(this.theWorld, this.xx, this.yy, this.zz, this.block, this.player);
		this.theGolem.startActionTimer();
	}

	private Vec3 findLog()
	{
		Random rand = this.theGolem.getRNG();

		for (int var2 = 0; var2 < this.distance * 4.0F; ++var2)
		{
			int x = (int) (this.theGolem.getHomePosition().posX + rand.nextInt((int) (1.0F + this.distance * 2.0F)) - this.distance);
			int y = (int) (this.theGolem.getHomePosition().posY + rand.nextInt((int) (1.0F + this.distance)) - this.distance / 2.0F);
			int z = (int) (this.theGolem.getHomePosition().posZ + rand.nextInt((int) (1.0F + this.distance * 2.0F)) - this.distance);
			if (Utils.isWoodLog(this.theWorld, x, y, z))
			{
				Vec3 v = Vec3.createVectorHelper(x, y, z);
				double dist = this.theGolem.getDistanceSq(x + 0.5D, y + 0.5D, z + 0.5D);

				for (int yy = 1; Utils.isWoodLog(this.theWorld, x, y - yy, z) && this.theGolem.getDistanceSq(x + 0.5D, y - yy + 0.5D, z + 0.5D) < dist; ++yy)
				{
					v = Vec3.createVectorHelper(x, y - yy, z);
					dist = this.theGolem.getDistanceSq(x + 0.5D, y - yy + 0.5D, z + 0.5D);
				}

				return v;
			}
		}

		return null;
	}
}
