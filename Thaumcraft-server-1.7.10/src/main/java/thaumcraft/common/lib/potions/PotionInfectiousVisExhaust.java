package thaumcraft.common.lib.potions;

import com.gamerforea.eventhelper.util.FastUtils;
import com.gamerforea.thaumcraft.EventConfig;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import thaumcraft.common.config.Config;

import java.util.List;

public class PotionInfectiousVisExhaust extends Potion
{
	public static PotionInfectiousVisExhaust instance = null;
	private int statusIconIndex = -1;
	static final ResourceLocation rl = new ResourceLocation("thaumcraft", "textures/misc/potions.png");

	public PotionInfectiousVisExhaust(int par1, boolean par2, int par3)
	{
		super(par1, par2, par3);
		this.setIconIndex(0, 0);
	}

	public static void init()
	{
		instance.setPotionName("potion.infvisexhaust");
		instance.setIconIndex(6, 1);
		instance.setEffectiveness(0.25D);
	}

	@Override
	public boolean isBadEffect()
	{
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getStatusIconIndex()
	{
		Minecraft.getMinecraft().renderEngine.bindTexture(rl);
		return super.getStatusIconIndex();
	}

	@Override
	public void performEffect(EntityLivingBase target, int par2)
	{
		// TODO gamerforEA code start
		if (!EventConfig.enableInfectiousVisExhaust)
		{
			FastUtils.stopPotionEffect(target, this);
			return;
		}
		// TODO gamerforEA code end

		List<EntityLivingBase> targets = target.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, target.boundingBox.expand(4.0D, 4.0D, 4.0D));
		if (targets.size() > 0)
			for (EntityLivingBase e : targets)
			{
				if (!e.isPotionActive(Config.potionInfVisExhaustID))
					if (par2 > 0)
						e.addPotionEffect(new PotionEffect(Config.potionInfVisExhaustID, 6000, par2 - 1, false));
					else
						e.addPotionEffect(new PotionEffect(Config.potionVisExhaustID, 6000, 0, false));
			}

	}

	@Override
	public boolean isReady(int par1, int par2)
	{
		return par1 % 40 == 0;
	}
}
