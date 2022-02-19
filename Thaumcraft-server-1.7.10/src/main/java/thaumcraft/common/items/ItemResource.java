package thaumcraft.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IEssentiaContainerItem;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.entities.EntityAspectOrb;
import thaumcraft.common.entities.projectile.EntityAlumentum;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.playerdata.PacketAspectPool;
import thaumcraft.common.lib.network.playerdata.PacketResearchComplete;
import thaumcraft.common.lib.research.ResearchManager;
import thaumcraft.common.lib.utils.InventoryUtils;

import java.util.List;
import java.util.Random;

import static com.gamerforea.thaumcraft.ChatUtils.*;

public class ItemResource extends Item implements IEssentiaContainerItem
{
	public IIcon[] icon = new IIcon[19];
	public IIcon iconOverlay;

	public ItemResource()
	{
		this.setMaxStackSize(64);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.setCreativeTab(Thaumcraft.tabTC);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir)
	{
		this.icon[0] = ir.registerIcon("thaumcraft:alumentum");
		this.icon[1] = ir.registerIcon("thaumcraft:nitor");
		this.icon[2] = ir.registerIcon("thaumcraft:thaumiumingot");
		this.icon[3] = ir.registerIcon("thaumcraft:quicksilver");
		this.icon[4] = ir.registerIcon("thaumcraft:tallow");
		this.icon[5] = ir.registerIcon("thaumcraft:brain");
		this.icon[6] = ir.registerIcon("thaumcraft:amber");
		this.icon[7] = ir.registerIcon("thaumcraft:cloth");
		this.icon[8] = ir.registerIcon("thaumcraft:filter");
		this.icon[9] = ir.registerIcon("thaumcraft:knowledgefragment");
		this.icon[10] = ir.registerIcon("thaumcraft:mirrorglass");
		this.icon[11] = ir.registerIcon("thaumcraft:taint_slime");
		this.icon[12] = ir.registerIcon("thaumcraft:taint_tendril");
		this.icon[13] = ir.registerIcon("thaumcraft:label");
		this.iconOverlay = ir.registerIcon("thaumcraft:label_over");
		this.icon[14] = ir.registerIcon("thaumcraft:dust");
		this.icon[15] = ir.registerIcon("thaumcraft:charm");
		this.icon[16] = ir.registerIcon("thaumcraft:voidingot");
		this.icon[17] = ir.registerIcon("thaumcraft:voidseed");
		this.icon[18] = ir.registerIcon("thaumcraft:coin");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int par1)
	{
		return this.icon[par1];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderPasses(int metadata)
	{
		return metadata == 13 ? 2 : 1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(ItemStack stack, int pass)
	{
		return pass != 0 && this.getAspects(stack) != null ? this.iconOverlay : this.getIconFromDamage(stack.getItemDamage());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack stack, int par2)
	{
		return par2 == 1 && stack.getItemDamage() == 13 && this.getAspects(stack) != null ? this.getAspects(stack).getAspects()[0].getColor() : 16777215;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses()
	{
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List)
	{
		for (int a = 0; a <= 18; ++a)
		{
			if (a != 5)
				par3List.add(new ItemStack(this, 1, a));
		}

	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack)
	{
		return this.getUnlocalizedName() + "." + par1ItemStack.getItemDamage();
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5)
	{
		super.onUpdate(stack, world, entity, par4, par5);
		if (!entity.worldObj.isRemote && (stack.getItemDamage() == 11 || stack.getItemDamage() == 12) && entity instanceof EntityLivingBase && !((EntityLivingBase) entity).isEntityUndead() && !((EntityLivingBase) entity).isPotionActive(Config.potionTaintPoisonID) && world.rand.nextInt(4321) <= stack.stackSize)
		{
			((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Config.potionTaintPoisonID, 120, 0, false));
			if (entity instanceof EntityPlayer)
			{
				/* TODO gamerforEA code replace, old code:
				String msg = StatCollector.translateToLocal("tc.taint_item_poison").replace("%s", "§5§o" + stack.getDisplayName() + "§r");
				((EntityPlayer) entity).addChatMessage(new ChatComponentTranslation(msg)); */
				((EntityPlayer) entity).addChatComponentMessage(translate("tc.taint_item_poison", italic(color(stack.func_151000_E(), EnumChatFormatting.DARK_PURPLE))));
				// TODO gamerforEA code end

				InventoryUtils.consumeInventoryItem((EntityPlayer) entity, stack.getItem(), stack.getItemDamage());
			}
		}
		else if (!entity.worldObj.isRemote && stack.getItemDamage() == 15)
		{
			int r = world.rand.nextInt(20000);
			if (stack.hasTagCompound() && stack.stackTagCompound.hasKey("blurb"))
				stack.stackTagCompound.removeTag("blurb");

			if (r < 20)
			{
				Aspect aspect = null;
				switch (world.rand.nextInt(6))
				{
					case 0:
						aspect = Aspect.AIR;
						break;
					case 1:
						aspect = Aspect.EARTH;
						break;
					case 2:
						aspect = Aspect.FIRE;
						break;
					case 3:
						aspect = Aspect.WATER;
						break;
					case 4:
						aspect = Aspect.ORDER;
						break;
					case 5:
						aspect = Aspect.ENTROPY;
				}

				if (aspect != null)
				{
					EntityAspectOrb orb = new EntityAspectOrb(world, entity.posX, entity.posY, entity.posZ, aspect, 1);
					world.spawnEntityInWorld(orb);
				}
			}
			else if (r == 42 && entity instanceof EntityPlayer && !ResearchManager.isResearchComplete(entity.getCommandSenderName(), "FOCUSPRIMAL") && !ResearchManager.isResearchComplete(entity.getCommandSenderName(), "@FOCUSPRIMAL"))
			{
				// TODO gamerforEA code replace, old code:
				// ((EntityPlayer) entity).addChatMessage(new ChatComponentTranslation("§5§o" + StatCollector.translateToLocal("tc.primalcharm.trigger")));
				((EntityPlayer) entity).addChatComponentMessage(italic(color(translate("tc.primalcharm.trigger"), EnumChatFormatting.DARK_PURPLE)));
				// TODO gamerforEA code end

				PacketHandler.INSTANCE.sendTo(new PacketResearchComplete("@FOCUSPRIMAL"), (EntityPlayerMP) entity);
				Thaumcraft.proxy.getResearchManager().completeResearch((EntityPlayer) entity, "@FOCUSPRIMAL");
			}
		}

	}

	@Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10)
	{
		if (itemstack.getItemDamage() != 1)
			return super.onItemUse(itemstack, player, world, x, y, z, par7, par8, par9, par10);
		Block var11 = world.getBlock(x, y, z);
		if (var11 == Blocks.snow_layer && (world.getBlockMetadata(x, y, z) & 7) < 1)
			par7 = 1;
		else if (var11 != Blocks.vine && var11 != Blocks.tallgrass && var11 != Blocks.deadbush && !var11.isReplaceable(world, x, y, z))
		{
			if (par7 == 0)
				--y;

			if (par7 == 1)
				++y;

			if (par7 == 2)
				--z;

			if (par7 == 3)
				++z;

			if (par7 == 4)
				--x;

			if (par7 == 5)
				++x;
		}

		if (itemstack.stackSize == 0)
			return false;
		if (!player.canPlayerEdit(x, y, z, par7, itemstack))
			return false;
		if (world.canPlaceEntityOnSide(ConfigBlocks.blockAiry, x, y, z, false, par7, player, itemstack))
		{
			if (this.placeBlockAt(itemstack, player, world, x, y, z, par7, par8, par9, par10, ConfigBlocks.blockAiry, itemstack.getItemDamage()))
			{
				world.playSoundEffect((double) ((float) x + 0.5F), (double) ((float) y + 0.5F), (double) ((float) z + 0.5F), ConfigBlocks.blockAiry.stepSound.getStepResourcePath(), (ConfigBlocks.blockAiry.stepSound.getVolume() + 1.0F) / 2.0F, ConfigBlocks.blockAiry.stepSound.getPitch() * 0.8F);
				--itemstack.stackSize;
				return true;
			}
			return false;
		}
		return false;
	}

	public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, Block bid, int metadata)
	{
		if (!world.setBlock(x, y, z, bid, metadata, 3))
			return false;
		if (world.getBlock(x, y, z) == bid)
		{
			bid.onBlockPlacedBy(world, x, y, z, player, stack);
			bid.onPostBlockPlaced(world, x, y, z, metadata);
		}

		return true;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player)
	{
		if (itemstack.getItemDamage() == 0)
		{
			if (!player.capabilities.isCreativeMode)
				--itemstack.stackSize;

			world.playSoundAtEntity(player, "random.bow", 0.3F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
			if (!world.isRemote)
				world.spawnEntityInWorld(new EntityAlumentum(world, player));
		}
		else if (itemstack.getItemDamage() == 9)
		{
			if (!player.capabilities.isCreativeMode)
				--itemstack.stackSize;

			if (!world.isRemote)
				for (Aspect a : Aspect.getPrimalAspects())
				{
					short q = (short) (world.rand.nextInt(2) + 1);
					Thaumcraft.proxy.playerKnowledge.addAspectPool(player.getCommandSenderName(), a, q);
					ResearchManager.scheduleSave(player);
					PacketHandler.INSTANCE.sendTo(new PacketAspectPool(a.getTag(), q, Thaumcraft.proxy.playerKnowledge.getAspectPoolFor(player.getCommandSenderName(), a)), (EntityPlayerMP) player);
				}
		}

		return itemstack;
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
	{
		AspectList aspects = this.getAspects(stack);
		if (aspects != null && aspects.size() > 0)
			for (Aspect tag : aspects.getAspectsSorted())
			{
				if (Thaumcraft.proxy.playerKnowledge.hasDiscoveredAspect(player.getCommandSenderName(), tag))
					list.add(tag.getName());
				else
					list.add(StatCollector.translateToLocal("tc.aspect.unknown"));
			}

		if (stack.getItemDamage() == 15)
		{
			Random rand = new Random((long) (stack.hashCode() + player.ticksExisted / 120));
			int r = rand.nextInt(200);
			if (r < 25)
				list.add("§6" + StatCollector.translateToLocal("tc.primalcharm." + rand.nextInt(5)));
		}

		super.addInformation(stack, player, list, par4);
	}

	@Override
	public AspectList getAspects(ItemStack itemstack)
	{
		if (itemstack.hasTagCompound())
		{
			AspectList aspects = new AspectList();
			aspects.readFromNBT(itemstack.getTagCompound());
			return aspects.size() > 0 ? aspects : null;
		}
		return null;
	}

	@Override
	public void setAspects(ItemStack itemstack, AspectList aspects)
	{
		if (!itemstack.hasTagCompound())
			itemstack.setTagCompound(new NBTTagCompound());

		aspects.writeToNBT(itemstack.getTagCompound());
	}

	@Override
	public int getItemStackLimit(ItemStack stack)
	{
		return stack.getItemDamage() == 15 ? 1 : super.getItemStackLimit(stack);
	}
}
