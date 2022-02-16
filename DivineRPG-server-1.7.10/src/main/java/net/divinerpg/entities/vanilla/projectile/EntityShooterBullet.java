package net.divinerpg.entities.vanilla.projectile;

import com.gamerforea.divinerpg.ModUtils;
import com.gamerforea.eventhelper.fake.FakePlayerContainer;
import com.gamerforea.eventhelper.fake.FakePlayerContainerEntity;
import net.divinerpg.libs.Reference;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityShooterBullet extends EntityThrowable
{
	public float damage;
	private short xTile;
	private short yTile;
	private short zTile;
	private Block inTile;
	private String throwerName;
	private EntityLivingBase thrower;

	// TODO gamerforEA code start
	public final FakePlayerContainer fake = new FakePlayerContainerEntity(ModUtils.profile, this);
	// TODO gamerforEA code end

	public EntityShooterBullet(World world)
	{
		super(world);
	}

	public EntityShooterBullet(World world, EntityLivingBase entity, float damage, Item ammo)
	{
		super(world, entity);
		this.damage = damage;
		this.dataWatcher.updateObject(17, Reference.PREFIX + "textures/items/" + ammo.getUnlocalizedName().replace("item.", "") + ".png");

		// TODO gamerforEA code start
		if (entity instanceof EntityPlayer)
			this.fake.setRealPlayer((EntityPlayer) entity);
		// TODO gamerforEA code end
	}

	public EntityShooterBullet(World world, EntityLivingBase entity, float damage, String texture)
	{
		super(world, entity);
		this.damage = damage;
		this.dataWatcher.updateObject(17, texture);

		// TODO gamerforEA code start
		if (entity instanceof EntityPlayer)
			this.fake.setRealPlayer((EntityPlayer) entity);
		// TODO gamerforEA code end
	}

	public EntityShooterBullet(World world, double posX, double posY, double posZ, float damage, String texture)
	{
		super(world, posX, posY, posZ);
		this.damage = damage;
		this.dataWatcher.updateObject(17, texture);
	}

	@Override
	protected void entityInit()
	{
		this.dataWatcher.addObject(17, "");
	}

	public int getBulletId()
	{
		return this.dataWatcher.getWatchableObjectInt(17);
	}

	@Override
	protected void onImpact(MovingObjectPosition position)
	{
		// TODO gamerforEA add condition
		if (position.entityHit != null && !this.fake.cantDamage(position.entityHit))
			position.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), this.damage);
		if (!this.worldObj.isRemote)
			this.setDead();
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tag)
	{
		tag.setShort("xTile", this.xTile);
		tag.setShort("yTile", this.yTile);
		tag.setShort("zTile", this.zTile);
		tag.setByte("inTile", (byte) Block.getIdFromBlock(this.inTile));
		tag.setByte("shake", (byte) this.throwableShake);
		tag.setByte("inGround", (byte) (this.inGround ? 1 : 0));
		tag.setString("texture", this.dataWatcher.getWatchableObjectString(17));
		tag.setFloat("damage", this.damage);

		if ((this.throwerName == null || this.throwerName.length() == 0) && this.thrower != null && this.thrower instanceof EntityPlayer)
			this.throwerName = this.thrower.getCommandSenderName();

		tag.setString("ownerName", this.throwerName == null ? "" : this.throwerName);

		// TODO gamerforEA code start
		this.fake.writeToNBT(tag);
		// TODO gamerforEA code end
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound tag)
	{
		this.xTile = tag.getShort("xTile");
		this.yTile = tag.getShort("yTile");
		this.zTile = tag.getShort("zTile");
		this.inTile = Block.getBlockById(tag.getByte("inTile") & 255);
		this.throwableShake = tag.getByte("shake") & 255;
		this.inGround = tag.getByte("inGround") == 1;
		this.throwerName = tag.getString("ownerName");
		this.dataWatcher.updateObject(17, tag.getString("texture"));
		this.damage = tag.getFloat("damage");

		if (this.throwerName != null && this.throwerName.length() == 0)
			this.throwerName = null;

		// TODO gamerforEA code start
		this.fake.readFromNBT(tag);
		// TODO gamerforEA code end
	}

	public String getTextureName()
	{
		return this.dataWatcher.getWatchableObjectString(17);
	}
}
