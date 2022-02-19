package thaumcraft.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.playerdata.PacketResearchComplete;
import thaumcraft.common.lib.research.ResearchManager;
import thaumcraft.common.lib.research.ResearchNoteData;

import java.util.List;

import static com.gamerforea.thaumcraft.ChatUtils.translate;

public class ItemResearchNotes extends Item
{
	@SideOnly(Side.CLIENT)
	public IIcon iconNote;
	@SideOnly(Side.CLIENT)
	public IIcon iconNoteOver;
	@SideOnly(Side.CLIENT)
	public IIcon iconDiscovery;
	@SideOnly(Side.CLIENT)
	public IIcon iconDiscoveryOver;

	public ItemResearchNotes()
	{
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.setMaxStackSize(1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir)
	{
		this.iconNote = ir.registerIcon("thaumcraft:researchnotes");
		this.iconNoteOver = ir.registerIcon("thaumcraft:researchnotesoverlay");
		this.iconDiscovery = ir.registerIcon("thaumcraft:discovery");
		this.iconDiscoveryOver = ir.registerIcon("thaumcraft:discoveryoverlay");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int par1)
	{
		return par1 / 64 == 0 ? this.iconNote : this.iconDiscovery;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamageForRenderPass(int par1, int renderPass)
	{
		return renderPass == 0 ? par1 / 64 == 0 ? this.iconNote : this.iconDiscovery : par1 / 64 == 0 ? this.iconNoteOver : this.iconDiscoveryOver;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		if (!world.isRemote)
			if (ResearchManager.getData(stack) != null && ResearchManager.getData(stack).isComplete() && !ResearchManager.isResearchComplete(player.getCommandSenderName(), ResearchManager.getData(stack).key))
				if (ResearchManager.doesPlayerHaveRequisites(player.getCommandSenderName(), ResearchManager.getData(stack).key))
				{
					PacketHandler.INSTANCE.sendTo(new PacketResearchComplete(ResearchManager.getData(stack).key), (EntityPlayerMP) player);
					Thaumcraft.proxy.getResearchManager().completeResearch(player, ResearchManager.getData(stack).key);
					if (ResearchCategories.getResearch(ResearchManager.getData(stack).key).siblings != null)
						for (String sibling : ResearchCategories.getResearch(ResearchManager.getData(stack).key).siblings)
						{
							if (!ResearchManager.isResearchComplete(player.getCommandSenderName(), sibling) && ResearchManager.doesPlayerHaveRequisites(player.getCommandSenderName(), sibling))
							{
								PacketHandler.INSTANCE.sendTo(new PacketResearchComplete(sibling), (EntityPlayerMP) player);
								Thaumcraft.proxy.getResearchManager().completeResearch(player, sibling);
							}
						}

					--stack.stackSize;
					world.playSoundAtEntity(player, "thaumcraft:learn", 0.75F, 1.0F);
				}
				else
					// TODO gamerforEA code replace, old code:
					// player.addChatMessage(new ChatComponentTranslation(StatCollector.translateToLocal("tc.researcherror")));
					player.addChatComponentMessage(translate("tc.researcherror"));
				// TODO gamerforEA code end
			else if (stack.getItemDamage() == 42 || stack.getItemDamage() == 24)
			{
				String key = ResearchManager.findHiddenResearch(player);
				if (key.equals("FAIL"))
				{
					--stack.stackSize;
					EntityItem entityItem = new EntityItem(world, player.posX, player.posY + (double) (player.getEyeHeight() / 2.0F), player.posZ, new ItemStack(ConfigItems.itemResource, 7 + world.rand.nextInt(3), 9));
					world.spawnEntityInWorld(entityItem);
					world.playSoundAtEntity(player, "thaumcraft:erase", 0.75F, 1.0F);
				}
				else
				{
					stack.setItemDamage(0);
					stack.stackTagCompound = ResearchManager.createNote(stack, key, player.worldObj).stackTagCompound;
					world.playSoundAtEntity(player, "thaumcraft:write", 0.75F, 1.0F);
				}
			}

		return stack;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack stack, int par2)
	{
		if (par2 == 1)
		{
			int c = 10066329;
			ResearchNoteData rd = ResearchManager.getData(stack);
			if (rd != null)
				c = rd.color;

			return c;
		}
		return super.getColorFromItemStack(stack, par2);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses()
	{
		return true;
	}

	@Override
	public boolean getShareTag()
	{
		return true;
	}

	@Override
	public String getItemStackDisplayName(ItemStack itemstack)
	{
		return itemstack.getItemDamage() < 64 ? StatCollector.translateToLocal("item.researchnotes.name") : StatCollector.translateToLocal("item.discovery.name");
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer par2EntityPlayer, List list, boolean par4)
	{
		if (stack.getItemDamage() == 24 || stack.getItemDamage() == 42)
		{
			list.add(EnumChatFormatting.GOLD + StatCollector.translateToLocal("item.researchnotes.unknown.1"));
			list.add(EnumChatFormatting.BLUE + StatCollector.translateToLocal("item.researchnotes.unknown.2"));
		}

		ResearchNoteData rd = ResearchManager.getData(stack);
		if (rd != null && rd.key != null && ResearchCategories.getResearch(rd.key) != null)
		{
			list.add("§6" + ResearchCategories.getResearch(rd.key).getName());
			list.add("§o" + ResearchCategories.getResearch(rd.key).getText());
			int warp = ThaumcraftApi.getWarp(rd.key);
			if (warp > 0)
			{
				if (warp > 5)
					warp = 5;

				String ws = StatCollector.translateToLocal("tc.forbidden");
				String wr = StatCollector.translateToLocal("tc.forbidden.level." + warp);
				String wte = ws.replaceAll("%n", wr);
				list.add(EnumChatFormatting.DARK_PURPLE + wte);
			}
		}

	}

	@Override
	public EnumRarity getRarity(ItemStack itemstack)
	{
		return itemstack.getItemDamage() < 64 ? EnumRarity.rare : EnumRarity.epic;
	}
}
