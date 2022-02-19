package thaumcraft.common.items.relics;

import com.gamerforea.eventhelper.util.EventUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.tiles.TileMirror;

import java.util.List;

import static com.gamerforea.thaumcraft.ChatUtils.*;

public class ItemHandMirror extends Item
{
	private IIcon icon;

	public ItemHandMirror()
	{
		this.setMaxStackSize(1);
		this.setHasSubtypes(false);
		this.setMaxDamage(0);
		this.setCreativeTab(Thaumcraft.tabTC);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister)
	{
		this.icon = par1IconRegister.registerIcon("thaumcraft:mirrorhand");
	}

	@Override
	public IIcon getIconFromDamage(int par1)
	{
		return this.icon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List)
	{
		par3List.add(new ItemStack(this));
	}

	@Override
	public boolean getShareTag()
	{
		return true;
	}

	@Override
	public EnumRarity getRarity(ItemStack itemstack)
	{
		return EnumRarity.uncommon;
	}

	@Override
	public boolean hasEffect(ItemStack par1ItemStack)
	{
		return par1ItemStack.hasTagCompound();
	}

	@Override
	public boolean onItemUseFirst(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10)
	{
		Block bi = world.getBlock(x, y, z);
		if (bi == ConfigBlocks.blockMirror)
		{
			if (world.isRemote)
			{
				player.swingItem();
				return super.onItemUseFirst(itemstack, player, world, x, y, z, par7, par8, par9, par10);
			}
			TileEntity tm = world.getTileEntity(x, y, z);
			if (tm instanceof TileMirror)
			{
				// TODO gamerforEA code start
				if (EventUtils.cantBreak(player, x, y, z))
					return true;
				// TODO gamerforEA code end

				itemstack.setTagInfo("linkX", new NBTTagInt(tm.xCoord));
				itemstack.setTagInfo("linkY", new NBTTagInt(tm.yCoord));
				itemstack.setTagInfo("linkZ", new NBTTagInt(tm.zCoord));
				itemstack.setTagInfo("linkDim", new NBTTagInt(world.provider.dimensionId));
				itemstack.setTagInfo("dimname", new NBTTagString(DimensionManager.getProvider(world.provider.dimensionId).getDimensionName()));
				world.playSoundEffect((double) x, (double) y, (double) z, "thaumcraft:jar", 1.0F, 2.0F);

				// TODO gamerforEA code replace, old code:
				// player.addChatMessage(new ChatComponentText("§5§o" + StatCollector.translateToLocal("tc.handmirrorlinked")));
				player.addChatMessage(italic(color(translate("tc.handmirrorlinked"), EnumChatFormatting.DARK_PURPLE)));
				// TODO gamerforEA code end

				player.inventoryContainer.detectAndSendChanges();
			}

			return true;
		}
		return false;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer player)
	{
		if (!par2World.isRemote && par1ItemStack.hasTagCompound())
		{
			int lx = par1ItemStack.stackTagCompound.getInteger("linkX");
			int ly = par1ItemStack.stackTagCompound.getInteger("linkY");
			int lz = par1ItemStack.stackTagCompound.getInteger("linkZ");
			int ldim = par1ItemStack.stackTagCompound.getInteger("linkDim");
			World targetWorld = MinecraftServer.getServer().worldServerForDimension(ldim);
			if (targetWorld == null)
				return super.onItemRightClick(par1ItemStack, par2World, player);

			TileEntity te = targetWorld.getTileEntity(lx, ly, lz);
			if (!(te instanceof TileMirror))
			{
				par1ItemStack.setTagCompound(null);
				par2World.playSoundAtEntity(player, "thaumcraft:zap", 1.0F, 0.8F);

				// TODO gamerforEA code replace, old code:
				// player.addChatMessage(new ChatComponentText("§5§o" + StatCollector.translateToLocal("tc.handmirrorerror")));
				player.addChatMessage(italic(color(translate("tc.handmirrorerror"), EnumChatFormatting.DARK_PURPLE)));
				// TODO gamerforEA code end

				return super.onItemRightClick(par1ItemStack, par2World, player);
			}

			player.openGui(Thaumcraft.instance, 16, par2World, MathHelper.floor_double(player.posX), MathHelper.floor_double(player.posY), MathHelper.floor_double(player.posZ));
		}

		return super.onItemRightClick(par1ItemStack, par2World, player);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack item, EntityPlayer par2EntityPlayer, List list, boolean par4)
	{
		if (item.hasTagCompound())
		{
			int lx = item.stackTagCompound.getInteger("linkX");
			int ly = item.stackTagCompound.getInteger("linkY");
			int lz = item.stackTagCompound.getInteger("linkZ");
			int ldim = item.stackTagCompound.getInteger("linkDim");
			String dimname = item.stackTagCompound.getString("dimname");
			list.add(StatCollector.translateToLocal("tc.handmirrorlinkedto") + " " + lx + "," + ly + "," + lz + " in " + dimname);
		}

	}

	public static boolean transport(ItemStack mirror, ItemStack items, EntityPlayer player, World worldObj)
	{
		if (mirror.hasTagCompound())
		{
			int lx = mirror.stackTagCompound.getInteger("linkX");
			int ly = mirror.stackTagCompound.getInteger("linkY");
			int lz = mirror.stackTagCompound.getInteger("linkZ");
			int ldim = mirror.stackTagCompound.getInteger("linkDim");
			World targetWorld = MinecraftServer.getServer().worldServerForDimension(ldim);
			if (targetWorld == null)
				return false;
			TileEntity te = targetWorld.getTileEntity(lx, ly, lz);
			if (te instanceof TileMirror)
			{
				TileMirror tm = (TileMirror) te;
				ForgeDirection linkedFacing = ForgeDirection.getOrientation(targetWorld.getBlockMetadata(lx, ly, lz));
				EntityItem ie2 = new EntityItem(targetWorld, (double) lx + 0.5D - (double) linkedFacing.offsetX * 0.3D, (double) ly + 0.5D - (double) linkedFacing.offsetY * 0.3D, (double) lz + 0.5D - (double) linkedFacing.offsetZ * 0.3D, items.copy());
				ie2.motionX = (double) ((float) linkedFacing.offsetX * 0.15F);
				ie2.motionY = (double) ((float) linkedFacing.offsetY * 0.15F);
				ie2.motionZ = (double) ((float) linkedFacing.offsetZ * 0.15F);
				ie2.timeUntilPortal = 20;
				targetWorld.spawnEntityInWorld(ie2);
				items = null;
				worldObj.playSoundAtEntity(player, "mob.endermen.portal", 0.1F, 1.0F);
				targetWorld.addBlockEvent(lx, ly, lz, ConfigBlocks.blockMirror, 1, 0);
				return true;
			}
			mirror.setTagCompound(null);
			worldObj.playSoundAtEntity(player, "thaumcraft:zap", 1.0F, 0.8F);

			// TODO gamerforEA code replace, old code:
			// player.addChatMessage(new ChatComponentText("§5§o" + StatCollector.translateToLocal("tc.handmirrorerror")));
			player.addChatMessage(italic(color(translate("tc.handmirrorerror"), EnumChatFormatting.DARK_PURPLE)));
			// TODO gamerforEA code end

			return false;
		}
		return false;
	}
}
