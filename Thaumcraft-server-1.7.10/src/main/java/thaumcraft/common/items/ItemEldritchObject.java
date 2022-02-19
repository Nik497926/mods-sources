package thaumcraft.common.items;

import com.gamerforea.eventhelper.util.EventUtils;
import com.gamerforea.eventhelper.util.ExplosionByPlayer;
import com.gamerforea.thaumcraft.EventConfig;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.nodes.NodeModifier;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.playerdata.PacketResearchComplete;
import thaumcraft.common.lib.research.ResearchManager;
import thaumcraft.common.tiles.TileNode;

import java.util.Iterator;
import java.util.List;

public class ItemEldritchObject extends Item
{
	public IIcon[] icon = new IIcon[5];

	public ItemEldritchObject()
	{
		this.setMaxStackSize(1);
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.setCreativeTab(Thaumcraft.tabTC);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir)
	{
		this.icon[0] = ir.registerIcon("thaumcraft:eldritch_object");
		this.icon[1] = ir.registerIcon("thaumcraft:crimson_rites");
		this.icon[2] = ir.registerIcon("thaumcraft:eldritch_object_2");
		this.icon[3] = ir.registerIcon("thaumcraft:eldritch_object_3");
		this.icon[4] = ir.registerIcon("thaumcraft:ob_placer");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int par1)
	{
		return par1 < this.icon.length ? this.icon[par1] : this.icon[0];
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack)
	{
		return this.getUnlocalizedName() + "." + par1ItemStack.getItemDamage();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List)
	{
		par3List.add(new ItemStack(this, 1, 0));
		par3List.add(new ItemStack(this, 1, 1));
		par3List.add(new ItemStack(this, 1, 2));
		par3List.add(new ItemStack(this, 1, 3));
		par3List.add(new ItemStack(this, 1, 4));
	}

	@Override
	public EnumRarity getRarity(ItemStack stack)
	{
		switch (stack.getItemDamage())
		{
			case 2:
				return EnumRarity.rare;
			case 3:
				return EnumRarity.epic;
			default:
				return EnumRarity.uncommon;
		}
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
	{
		super.addInformation(stack, player, list, par4);
		if (stack != null)
			switch (stack.getItemDamage())
			{
				case 0:
					list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal("item.ItemEldritchObject.text.1"));
					break;
				case 1:
					list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal("item.ItemEldritchObject.text.2"));
					list.add(EnumChatFormatting.DARK_BLUE + StatCollector.translateToLocal("item.ItemEldritchObject.text.3"));
					break;
				case 2:
					list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal("item.ItemEldritchObject.text.4"));
					break;
				case 3:
					list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal("item.ItemEldritchObject.text.5"));
					list.add(EnumChatFormatting.DARK_PURPLE + StatCollector.translateToLocal("item.ItemEldritchObject.text.6"));
					break;
				case 4:
					list.add("§oCreative Mode Only");
			}

	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World par2World, EntityPlayer player)
	{
		if (!par2World.isRemote && stack.getItemDamage() == 1 && !ResearchManager.isResearchComplete(player.getCommandSenderName(), "CRIMSON"))
		{
			PacketHandler.INSTANCE.sendTo(new PacketResearchComplete("CRIMSON"), (EntityPlayerMP) player);
			Thaumcraft.proxy.getResearchManager().completeResearch(player, "CRIMSON");
			par2World.playSoundAtEntity(player, "thaumcraft:learn", 0.75F, 1.0F);
		}

		return stack;
	}

	@Override
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float par8, float par9, float par10)
	{
		if (stack.getItemDamage() != 3)
		{
			if (side == 1 && stack.getItemDamage() == 4)
			{
				// TODO gamerforEA code start
				if (EventConfig.obeliskPlacerCreativeOnly && !player.capabilities.isCreativeMode)
				{
					stack.stackSize = 0;
					player.inventoryContainer.detectAndSendChanges();
					return !world.isRemote;
				}
				// TODO gamerforEA code end

				player.swingItem();

				for (int var19 = 1; var19 <= 6; ++var19)
				{
					if (!world.isAirBlock(x, y + var19, z))
						return false;
				}

				world.setBlock(x, y + 1, z, ConfigBlocks.blockEldritch, 0, 3);
				world.setBlock(x, y + 3, z, ConfigBlocks.blockEldritch, 1, 3);
				world.setBlock(x, y + 4, z, ConfigBlocks.blockEldritch, 2, 3);
				world.setBlock(x, y + 5, z, ConfigBlocks.blockEldritch, 2, 3);
				world.setBlock(x, y + 6, z, ConfigBlocks.blockEldritch, 2, 3);
				world.setBlock(x, y + 7, z, ConfigBlocks.blockEldritch, 2, 3);
				return !world.isRemote;
			}
			else
				return super.onItemUseFirst(stack, player, world, x, y, z, side, par8, par9, par10);
		}
		else
		{
			TileEntity tile = world.getTileEntity(x, y, z);
			if (tile instanceof TileNode)
			{
				player.swingItem();
				// TODO gamerforEA add condition [2]
				if (!world.isRemote && !EventUtils.cantBreak(player, x, y, z))
				{
					--stack.stackSize;
					TileNode node = (TileNode) tile;
					boolean research = ThaumcraftApiHelper.isResearchComplete(player.getCommandSenderName(), "PRIMNODE");
					Aspect[] aspects = node.getAspects().getAspects();

					for (Aspect aspect : aspects)
					{
						int nodeVisBase = node.getNodeVisBase(aspect);
						if (!aspect.isPrimal())
						{
							if (world.rand.nextBoolean())
								node.setNodeVisBase(aspect, (short) (nodeVisBase - 1));
						}
						else
						{
							nodeVisBase = nodeVisBase - 2 + world.rand.nextInt(research ? 9 : 6);
							node.setNodeVisBase(aspect, (short) nodeVisBase);
						}
					}

					Iterator<Aspect> iter = Aspect.getPrimalAspects().iterator();

					int zz;
					while (iter.hasNext())
					{
						Aspect aspect = iter.next();
						int nodeVisBase = node.getNodeVisBase(aspect);
						zz = world.rand.nextInt(research ? 4 : 3);
						if (zz > 0 && zz > nodeVisBase)
						{
							node.setNodeVisBase(aspect, (short) zz);
							node.addToContainer(aspect, 1);
						}
					}

					if (node.getNodeModifier() == NodeModifier.FADING && world.rand.nextBoolean())
						node.setNodeModifier(NodeModifier.PALE);
					else if (node.getNodeModifier() == NodeModifier.PALE && world.rand.nextBoolean())
						node.setNodeModifier(null);
					else if (node.getNodeModifier() == null && world.rand.nextInt(5) == 0)
						node.setNodeModifier(NodeModifier.BRIGHT);

					world.markBlockForUpdate(x, y, z);
					node.markDirty();

					// TODO gamerforEA use ExplosionByPlayer
					ExplosionByPlayer.createExplosion(player, world, null, x + 0.5D, y + 1.5D, z + 0.5D, 3.0F + world.rand.nextFloat() * (research ? 3 : 5), true);

					for (int i = 0; i < 33; ++i)
					{
						int xx = x + world.rand.nextInt(6) - world.rand.nextInt(6);
						int yy = y + world.rand.nextInt(6) - world.rand.nextInt(6);
						zz = z + world.rand.nextInt(6) - world.rand.nextInt(6);
						// TODO gamerforEA add condition [2]
						if (world.isAirBlock(xx, yy, zz) && !EventUtils.cantBreak(player, xx, yy, zz))
							if (yy < y)
								world.setBlock(xx, yy, zz, ConfigBlocks.blockFluxGoo, 8, 3);
							else
								world.setBlock(xx, yy, zz, ConfigBlocks.blockFluxGas, 8, 3);
					}

					return true;
				}
			}

			return false;
		}
	}
}
