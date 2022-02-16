package net.divinerpg.entities.arcana;

import net.divinerpg.client.render.EntityResourceLocation;
import net.divinerpg.entities.twilight.EntityParticleBullet;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import java.awt.*;

public class EntityGeneralsStaff extends EntityParticleBullet
{
	public EntityGeneralsStaff(World world, EntityLivingBase entity)
	{
		super(world, entity, 18, EntityResourceLocation.generalsStaff.toString(), new Color(31, 93, 210));
		this.setMoreParticles();
	}

	@Override
	protected void onImpact(MovingObjectPosition position)
	{
		super.onImpact(position);
		if (!this.worldObj.isRemote)
		{
			for (double theta = 0; theta < Math.PI * 2; theta += Math.PI / 2)
			{
				EntityParticleBullet e = new EntityParticleBullet(this.worldObj, this.posX, this.posY, this.posZ, 18, this.getTextureName(), new Color(56, 152, 186)).setMoreParticles();

				// TODO gamerforEA code start
				e.fake.setParent(this.fake);
				// TODO gamerforEA code end

				e.setThrowableHeading(Math.cos(theta), 0.4, Math.sin(theta), 0.7f, 0);
				this.worldObj.spawnEntityInWorld(e);
			}

			EntityParticleBullet e = new EntityParticleBullet(this.worldObj, this.posX, this.posY, this.posZ, 18, this.getTextureName(), new Color(56, 152, 186)).setMoreParticles();

			// TODO gamerforEA code start
			e.fake.setParent(this.fake);
			// TODO gamerforEA code end

			e.setThrowableHeading(0, 1, 0, 0.7f, 0);
			this.worldObj.spawnEntityInWorld(e);
		}
	}

}
