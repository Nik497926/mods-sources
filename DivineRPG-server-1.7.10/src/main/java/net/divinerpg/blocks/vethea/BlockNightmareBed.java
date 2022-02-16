package net.divinerpg.blocks.vethea;

import com.gamerforea.divinerpg.EventConfig;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.divinerpg.dimensions.base.WorldGenAPI;
import net.divinerpg.dimensions.vethea.TeleporterVethea;
import net.divinerpg.libs.Reference;
import net.divinerpg.utils.LangRegistry;
import net.divinerpg.utils.Util;
import net.divinerpg.utils.blocks.TwilightBlocks;
import net.divinerpg.utils.config.ConfigurationHelper;
import net.divinerpg.utils.items.VetheaItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Direction;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.Iterator;
import java.util.Random;

public class BlockNightmareBed extends BlockBed
{

	@SideOnly(Side.CLIENT)
	private IIcon[] top;
	@SideOnly(Side.CLIENT)
	private IIcon[] end;
	@SideOnly(Side.CLIENT)
	private IIcon[] side;

	private NBTTagCompound persistantData;

	public BlockNightmareBed()
	{
		String name = "nightmareBedBlock";
		this.setStepSound(Block.soundTypeStone);
		this.setCreativeTab(null);
		this.setBlockName(name);
		this.setHardness(9);
		GameRegistry.registerBlock(this, name);
		LangRegistry.addBlock(this);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
	{
		// TODO gamerforEA code start
		if (!EventConfig.nightmareBed)
			return true;
		// TODO gamerforEA code end

		if (world.isRemote)
			return true;
		else
		{
			EntityPlayerMP MPPlayer = (EntityPlayerMP) player;
			int i1 = world.getBlockMetadata(x, y, z);

			if (!isBlockHeadOfBed(i1))
			{
				int j1 = getDirection(i1);
				x += field_149981_a[j1][0];
				z += field_149981_a[j1][1];

				if (world.getBlock(x, y, z) != this)
					return true;

				i1 = world.getBlockMetadata(x, y, z);
			}

			this.persistantData = player.getEntityData().getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG);

			if (player.worldObj.provider.dimensionId != ConfigurationHelper.vethea)
			{
				if (world.getBlockLightValue(x, y, z) > 7)
				{
					player.addChatMessage(Util.getChatComponent("You can only use the Nightmare Bed in a dark place."));
					return true;
				}
				EntityPlayer entityplayer1 = null;
				Iterator iterator = world.playerEntities.iterator();

				while (iterator.hasNext())
				{
					EntityPlayer entityplayer2 = (EntityPlayer) iterator.next();

					if (entityplayer2.isPlayerSleeping())
					{
						ChunkCoordinates chunkcoordinates = entityplayer2.playerLocation;

						if (chunkcoordinates.posX == x && chunkcoordinates.posY == y && chunkcoordinates.posZ == z)
							entityplayer1 = entityplayer2;
					}

					if (entityplayer1 != null)
					{
						player.addChatComponentMessage(new ChatComponentTranslation("tile.bed.occupied"));
						return true;
					}

					EntityPlayer.EnumStatus enumstatus = player.sleepInBedAt(x, y, z);
					MPPlayer.timeUntilPortal = 10;
					MPPlayer.mcServer.getConfigurationManager().transferPlayerToDimension(MPPlayer, ConfigurationHelper.vethea, new TeleporterVethea(MPPlayer.mcServer.worldServerForDimension(ConfigurationHelper.vethea)));

					/* TODO gamerforEA code clear:
					this.persistantData.setTag("OverworldInv", player.inventory.writeToNBT(new NBTTagList()));
					player.getEntityData().setTag("PlayerPersisted", this.persistantData);
					player.inventory.clearInventory(null, -1);
					NBTTagList inv = this.persistantData.getTagList("VetheaInv", 10);
					player.inventory.readFromNBT(inv);
					player.inventoryContainer.detectAndSendChanges(); */

					ChunkCoordinates c = new ChunkCoordinates();
					c.posX = (int) player.posX + 2;
					c.posY = 18;
					c.posZ = (int) player.posZ - 2;
					player.setSpawnChunk(c, true, ConfigurationHelper.vethea);
					return true;
				}
				return true;
			}
			else if (player.worldObj.provider.dimensionId == ConfigurationHelper.vethea)
			{
				MPPlayer.mcServer.getConfigurationManager().transferPlayerToDimension(MPPlayer, 0, new TeleporterVethea(MPPlayer.mcServer.worldServerForDimension(0)));

				/* TODO gamerforEA code clear:
				this.persistantData.setTag("VetheaInv", player.inventory.writeToNBT(new NBTTagList()));
				player.getEntityData().setTag("PlayerPersisted", this.persistantData);
				player.inventory.clearInventory(null, -1);
				NBTTagList inv = this.persistantData.getTagList("OverworldInv", 10);
				player.inventory.readFromNBT(inv);
				player.inventoryContainer.detectAndSendChanges(); */

				return true;
			}
			else
			{
				double d2 = x + 0.5D;
				double d0 = y + 0.5D;
				double d1 = z + 0.5D;
				world.setBlockToAir(x, y, z);
				int k1 = getDirection(i1);
				x += field_149981_a[k1][0];
				z += field_149981_a[k1][1];

				if (world.getBlock(x, y, z) == this)
				{
					world.setBlockToAir(x, y, z);
					d2 = (d2 + x + 0.5D) / 2.0D;
					d0 = (d0 + y + 0.5D) / 2.0D;
					d1 = (d1 + z + 0.5D) / 2.0D;
				}

				// TODO gamerforEA code replace, old code: WorldGenAPI.addRectangle(2, 2, 1, world, x, y - 1, z, TwilightBlocks.mortumBlock);
				Block frame = EventConfig.portalStoneFrame ? Blocks.stone : TwilightBlocks.mortumBlock;
				WorldGenAPI.addRectangle(2, 2, 1, world, x, y - 1, z, frame);
				// TODO gamerforEA code end

				return true;
			}
		}
	}

	@Override
	public Item getItemDropped(int par1, Random rand, int par3)
	{
		return VetheaItems.nightmareBed;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		if (side == 0)
			return TwilightBlocks.mortumBlock.getBlockTextureFromSide(side);
		else
		{
			int k = getDirection(meta);
			int l = Direction.bedDirection[k][side];
			int i1 = isBlockHeadOfBed(meta) ? 1 : 0;
			return (i1 != 1 || l != 2) && (i1 != 0 || l != 3) ? l != 5 && l != 4 ? this.top[i1] : this.side[i1] : this.end[i1];
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register)
	{
		this.top = new IIcon[] { register.registerIcon(Reference.PREFIX + "nightmareBedFeetTop"), register.registerIcon(Reference.PREFIX + "nightmareBedHeadTop") };
		this.end = new IIcon[] { register.registerIcon(Reference.PREFIX + "nightmareBedFeetEnd"), register.registerIcon(Reference.PREFIX + "nightmareBedHeadEnd") };
		this.side = new IIcon[] { register.registerIcon(Reference.PREFIX + "nightmareBedFeetSide"), register.registerIcon(Reference.PREFIX + "nightmareBedHeadSide") };
	}
}
