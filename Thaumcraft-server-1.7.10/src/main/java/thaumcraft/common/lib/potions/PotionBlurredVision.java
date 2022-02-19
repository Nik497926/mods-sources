package thaumcraft.common.lib.potions;

import com.gamerforea.eventhelper.util.FastUtils;
import com.gamerforea.thaumcraft.EventConfig;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class PotionBlurredVision extends Potion
{
	public static PotionBlurredVision instance = null;
	private int statusIconIndex = -1;
	static final ResourceLocation rl = new ResourceLocation("thaumcraft", "textures/misc/potions.png");

	public PotionBlurredVision(int par1, boolean par2, int par3)
	{
		super(par1, par2, par3);
		this.setIconIndex(0, 0);
	}

	public static void init()
	{
		instance.setPotionName("potion.blurred");
		instance.setIconIndex(5, 2);
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
		if (!EventConfig.enableBlurredVision)
			FastUtils.stopPotionEffect(target, this);
		// TODO gamerforEA code end
	}
}
