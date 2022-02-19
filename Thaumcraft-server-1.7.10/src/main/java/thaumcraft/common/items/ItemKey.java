package thaumcraft.common.items;

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
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.blocks.BlockArcaneDoor;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.tiles.TileOwned;

import java.util.List;

import static com.gamerforea.thaumcraft.ChatUtils.*;

public class ItemKey extends Item
{
	public IIcon iconIron;
	public IIcon iconGold;

	public ItemKey()
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
		this.iconIron = ir.registerIcon("thaumcraft:keyiron");
		this.iconGold = ir.registerIcon("thaumcraft:keygold");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int par1)
	{
		return par1 == 0 ? this.iconIron : this.iconGold;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List)
	{
		par3List.add(new ItemStack(this, 1, 0));
		par3List.add(new ItemStack(this, 1, 1));
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack)
	{
		return this.getUnlocalizedName() + "." + par1ItemStack.getItemDamage();
	}

	@Override
	public boolean hasEffect(ItemStack par1ItemStack)
	{
		return par1ItemStack.hasTagCompound();
	}

	@Override
	public EnumRarity getRarity(ItemStack itemstack)
	{
		return EnumRarity.uncommon;
	}

	@Override
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10)
	{
		Block bi = world.getBlock(x, y, z);
		int md = world.getBlockMetadata(x, y, z);
		if (bi == ConfigBlocks.blockArcaneDoor || bi == ConfigBlocks.blockWoodenDevice && (md == 2 || md == 3))
		{
			int mod = 0;
			int mod2 = 1;
			byte type = 0;
			if (bi == ConfigBlocks.blockArcaneDoor)
			{
				int var10 = ((BlockArcaneDoor) bi).getFullMetadata(world, x, y, z);
				if ((var10 & 8) != 0)
				{
					mod = -1;
					mod2 = 0;
				}
			}
			else
				type = 1;

			String loc = x + "," + (y + mod) + "," + z;
			TileEntity tile = world.getTileEntity(x, y + mod, z);
			if (tile instanceof TileOwned)
				if (!stack.hasTagCompound())
				{
					if (player.getCommandSenderName().equals(((TileOwned) tile).owner) || ((TileOwned) tile).accessList.contains("1" + player.getCommandSenderName()) && stack.getItemDamage() == 0)
					{
						ItemStack st = new ItemStack(ConfigItems.itemKey, 1, stack.getItemDamage());
						st.setTagInfo("location", new NBTTagString(loc));
						st.setTagInfo("type", new NBTTagByte(type));
						if (!player.inventory.addItemStackToInventory(st) && !world.isRemote)
							world.spawnEntityInWorld(new EntityItem(world, player.posX, player.posY, player.posZ, st));

						if (!player.capabilities.isCreativeMode)
							--stack.stackSize;

						if (!world.isRemote)
						{
							switch (type)
							{
								case 0:
									// TODO gamerforEA code replace, old code:
									// player.addChatMessage(new ChatComponentText("§5§o" + StatCollector.translateToLocal("tc.key1")));
									player.addChatComponentMessage(italic(color(translate("tc.key1"), EnumChatFormatting.DARK_PURPLE)));
									// TODO gamerforEA code end
									break;
								case 1:
									// TODO gamerforEA code replace, old code:
									// player.addChatMessage(new ChatComponentText("§5§o" + StatCollector.translateToLocal("tc.key2")));
									player.addChatComponentMessage(italic(color(translate("tc.key2"), EnumChatFormatting.DARK_PURPLE)));
									// TODO gamerforEA code end
							}

							world.playSoundEffect((double) x, (double) y, (double) z, "thaumcraft:key", 1.0F, 0.9F);
						}

						player.swingItem();
					}
				}
				else if (!player.getCommandSenderName().equals(((TileOwned) tile).owner) && !((TileOwned) tile).accessList.contains(stack.getItemDamage() + player.getCommandSenderName()) && !((TileOwned) tile).accessList.contains("1" + player.getCommandSenderName()) && loc.equals(stack.stackTagCompound.getString("location")))
				{
					((TileOwned) tile).accessList.add(stack.getItemDamage() + player.getCommandSenderName());
					if (type == 0)
					{
						TileEntity tile2 = world.getTileEntity(x, y + mod2, z);
						if (tile2 instanceof TileOwned)
							((TileOwned) tile2).accessList.add(stack.getItemDamage() + player.getCommandSenderName());

						world.markBlockForUpdate(x, y + mod2, z);
					}

					world.markBlockForUpdate(x, y + mod, z);
					if (!world.isRemote)
					{
						switch (type)
						{
							case 0:
								// TODO gamerforEA code replace, old code:
								// player.addChatMessage(new ChatComponentText("§5§o" + StatCollector.translateToLocal("tc.key3") + (stack.getItemDamage() == 0 ? "" : StatCollector.translateToLocal("tc.key4"))));
								IChatComponent msg = translate("tc.key3");
								if (stack.getItemDamage() != 0)
									msg = msg.appendSibling(translate("tc.key4"));
								player.addChatComponentMessage(italic(color(msg, EnumChatFormatting.DARK_PURPLE)));
								// TODO gamerforEA code end

								break;
							case 1:
								// TODO gamerforEA code replace, old code:
								// player.addChatMessage(new ChatComponentText("§5§o" + StatCollector.translateToLocal("tc.key5") + (stack.getItemDamage() == 0 ? "" : StatCollector.translateToLocal("tc.key6"))));
								msg = translate("tc.key5");
								if (stack.getItemDamage() != 0)
									msg = msg.appendSibling(translate("tc.key6"));
								player.addChatComponentMessage(italic(color(msg, EnumChatFormatting.DARK_PURPLE)));
								// TODO gamerforEA code end
						}

						world.playSoundEffect((double) x, (double) y, (double) z, "thaumcraft:key", 1.0F, 1.1F);
					}

					if (!player.capabilities.isCreativeMode)
						--stack.stackSize;

					player.swingItem();
				}
				else if (!world.isRemote)
					if (!loc.equals(stack.stackTagCompound.getString("location")))
						// TODO gamerforEA code replace, old code:
						// player.addChatMessage(new ChatComponentText("§5§o" + StatCollector.translateToLocal("tc.key7")));
						player.addChatComponentMessage(italic(color(translate("tc.key7"), EnumChatFormatting.DARK_PURPLE)));
						// TODO gamerforEA code end
					else
						// TODO gamerforEA code replace, old code:
						// player.addChatMessage(new ChatComponentText("§5§o" + StatCollector.translateToLocal("tc.key8")));
						player.addChatComponentMessage(italic(color(translate("tc.key8"), EnumChatFormatting.DARK_PURPLE)));
			// TODO gamerforEA code end

			return !world.isRemote;
		}
		return true;
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer par2EntityPlayer, List list, boolean par4)
	{
		if (stack.hasTagCompound() && stack.stackTagCompound.hasKey("location"))
		{
			String location = stack.stackTagCompound.getString("location");

			try
			{
				String[] ss = location.split(",");
				location = "x " + ss[0] + ", z " + ss[2] + ", y " + ss[1];
			}
			catch (Exception ignored)
			{
			}

			byte type = stack.stackTagCompound.getByte("type");
			list.add("§5§o" + StatCollector.translateToLocal("tc.key9"));
			list.add("§5§o" + (type == 0 ? StatCollector.translateToLocal("tc.key10") : StatCollector.translateToLocal("tc.key11")));
			list.add("§5§o" + location);
		}

	}
}
