package thaumcraft.common.items.wands.foci;

import com.gamerforea.eventhelper.util.EventUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.*;
import net.minecraft.world.World;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.FocusUpgradeType;
import thaumcraft.api.wands.ItemFocusBasic;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.entities.monster.EntityFireBat;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.lib.utils.EntityUtils;

public class ItemFocusHellbat extends ItemFocusBasic
{
	public IIcon iconOrnament;
	private static final AspectList costBase = new AspectList().add(Aspect.FIRE, 200).add(Aspect.ENTROPY, 100).add(Aspect.AIR, 100);
	private static final AspectList costBomb = new AspectList().add(Aspect.FIRE, 100).add(Aspect.ENTROPY, 200).add(Aspect.AIR, 100);
	private static final AspectList costDevil = new AspectList().add(Aspect.FIRE, 100).add(Aspect.ENTROPY, 100).add(Aspect.AIR, 100).add(Aspect.EARTH, 100);
	public static FocusUpgradeType batbombs = new FocusUpgradeType(13, new ResourceLocation("thaumcraft", "textures/foci/batbombs.png"), "focus.upgrade.batbombs.name", "focus.upgrade.batbombs.text", new AspectList().add(Aspect.ENERGY, 1).add(Aspect.TRAP, 1));
	public static FocusUpgradeType devilbats = new FocusUpgradeType(14, new ResourceLocation("thaumcraft", "textures/foci/devilbats.png"), "focus.upgrade.devilbats.name", "focus.upgrade.devilbats.text", new AspectList().add(Aspect.ARMOR, 1));
	public static FocusUpgradeType vampirebats = new FocusUpgradeType(19, new ResourceLocation("thaumcraft", "textures/foci/vampirebats.png"), "focus.upgrade.vampirebats.name", "focus.upgrade.vampirebats.text", new AspectList().add(Aspect.HUNGER, 1).add(Aspect.LIFE, 1));

	public ItemFocusHellbat()
	{
		this.setCreativeTab(Thaumcraft.tabTC);
	}

	@Override
	public String getSortingHelper(ItemStack itemstack)
	{
		return "HH" + super.getSortingHelper(itemstack);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir)
	{
		this.icon = ir.registerIcon("thaumcraft:focus_hellbat");
		this.iconOrnament = ir.registerIcon("thaumcraft:focus_hellbat_orn");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamageForRenderPass(int par1, int renderPass)
	{
		return renderPass == 1 ? this.icon : this.iconOrnament;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses()
	{
		return true;
	}

	@Override
	public IIcon getOrnament(ItemStack itemstack)
	{
		return this.iconOrnament;
	}

	@Override
	public ItemStack onFocusRightClick(ItemStack stack, World world, EntityPlayer player, MovingObjectPosition mop)
	{
		ItemWandCasting wand = (ItemWandCasting) stack.getItem();
		Entity pointedEntity = EntityUtils.getPointedEntity(player.worldObj, player, 32.0D, EntityFireBat.class);
		double px = player.posX;
		double py = player.posY;
		double pz = player.posZ;
		py = player.boundingBox.minY + player.height / 2.0F + 0.25D;
		px -= MathHelper.cos(player.rotationYaw / 180.0F * (float) Math.PI) * 0.16F;
		py -= 0.05000000014901161D;
		pz -= MathHelper.sin(player.rotationYaw / 180.0F * (float) Math.PI) * 0.16F;
		Vec3 vec3d = player.getLook(1.0F);
		px += vec3d.xCoord * 0.5D;
		py += vec3d.yCoord * 0.5D;
		pz += vec3d.zCoord * 0.5D;
		if (pointedEntity instanceof EntityLivingBase)
		{
			if (!world.isRemote)
			{
				if (pointedEntity instanceof EntityPlayer && !MinecraftServer.getServer().isPVPEnabled())
					return stack;

				// TODO gamerforEA code start
				if (EventUtils.cantDamage(player, pointedEntity))
					return stack;
				// TODO gamerforEA code end

				EntityFireBat firebat = new EntityFireBat(world);

				// TODO gamerforEA code start
				firebat.fake.setRealPlayer(player);
				// TODO gamerforEA code end

				firebat.setLocationAndAngles(px, py + firebat.height, pz, player.rotationYaw, 0.0F);
				firebat.setTarget(pointedEntity);
				firebat.damBonus = wand.getFocusPotency(stack);
				firebat.setIsSummoned(true);
				firebat.setIsBatHanging(false);
				if (this.isUpgradedWith(wand.getFocusItem(stack), devilbats))
					firebat.setIsDevil(true);

				if (this.isUpgradedWith(wand.getFocusItem(stack), batbombs))
					firebat.setIsExplosive(true);

				if (this.isUpgradedWith(wand.getFocusItem(stack), vampirebats))
				{
					firebat.owner = player;
					firebat.setIsVampire(true);
				}

				if (wand.consumeAllVis(stack, player, this.getVisCost(stack), true, false) && world.spawnEntityInWorld(firebat))
				{
					world.playAuxSFX(2004, (int) px, (int) py, (int) pz, 0);
					world.playSoundAtEntity(firebat, "thaumcraft:ice", 0.2F, 0.95F + world.rand.nextFloat() * 0.1F);
				}
				else
					world.playSoundAtEntity(player, "thaumcraft:wandfail", 0.1F, 0.8F + world.rand.nextFloat() * 0.1F);
			}

			player.swingItem();
		}

		return stack;
	}

	@Override
	public int getFocusColor(ItemStack itemstack)
	{
		return 14431746;
	}

	@Override
	public AspectList getVisCost(ItemStack itemstack)
	{
		return this.isUpgradedWith(itemstack, batbombs) ? costBomb : this.isUpgradedWith(itemstack, devilbats) ? costDevil : costBase;
	}

	@Override
	public int getActivationCooldown(ItemStack focusstack)
	{
		return 1000;
	}

	@Override
	public FocusUpgradeType[] getPossibleUpgradesByRank(ItemStack itemstack, int rank)
	{
		switch (rank)
		{
			case 1:
				return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency };
			case 2:
				return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency };
			case 3:
				return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency, batbombs, devilbats };
			case 4:
				return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency };
			case 5:
				return new FocusUpgradeType[] { FocusUpgradeType.frugal, FocusUpgradeType.potency, vampirebats };
			default:
				return null;
		}
	}

	@Override
	public boolean canApplyUpgrade(ItemStack focusstack, EntityPlayer player, FocusUpgradeType type, int rank)
	{
		return !type.equals(vampirebats) || ThaumcraftApiHelper.isResearchComplete(player.getCommandSenderName(), "VAMPBAT");
	}
}
