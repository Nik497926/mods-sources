package thaumcraft.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.aspects.IEssentiaContainerItem;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.items.ItemEssence;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.lib.utils.InventoryUtils;
import thaumcraft.common.tiles.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockWoodenDevice extends BlockContainer
{
	private Random random = new Random();
	public IIcon iconDefault;
	public IIcon iconSilverwood;
	public IIcon iconGreatwood;
	public IIcon[] iconAPPlate = new IIcon[3];
	public IIcon[] iconAEar = new IIcon[7];
	public int renderState = 0;

	public BlockWoodenDevice()
	{
		super(Material.wood);
		this.setHardness(2.5F);
		this.setResistance(10.0F);
		this.setStepSound(soundTypeWood);
		this.setTickRandomly(true);
		this.setCreativeTab(Thaumcraft.tabTC);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister ir)
	{
		this.iconDefault = ir.registerIcon("thaumcraft:woodplain");
		this.iconSilverwood = ir.registerIcon("thaumcraft:planks_silverwood");
		this.iconGreatwood = ir.registerIcon("thaumcraft:planks_greatwood");
		this.iconAPPlate[0] = ir.registerIcon("thaumcraft:applate1");
		this.iconAPPlate[1] = ir.registerIcon("thaumcraft:applate2");
		this.iconAPPlate[2] = ir.registerIcon("thaumcraft:applate3");
		this.iconAEar[0] = ir.registerIcon("thaumcraft:arcaneearsideon");
		this.iconAEar[1] = ir.registerIcon("thaumcraft:arcaneearsideoff");
		this.iconAEar[2] = ir.registerIcon("thaumcraft:arcaneearbottom");
		this.iconAEar[3] = ir.registerIcon("thaumcraft:arcaneeartopon");
		this.iconAEar[4] = ir.registerIcon("thaumcraft:arcaneeartopoff");
		this.iconAEar[5] = ir.registerIcon("thaumcraft:arcaneearbellside");
		this.iconAEar[6] = ir.registerIcon("thaumcraft:arcaneearbelltop");
	}

	public int tickRate()
	{
		return 20;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List)
	{
		par3List.add(new ItemStack(par1, 1, 0));
		par3List.add(new ItemStack(par1, 1, 1));
		par3List.add(new ItemStack(par1, 1, 2));
		par3List.add(new ItemStack(par1, 1, 4));
		par3List.add(new ItemStack(par1, 1, 5));
		par3List.add(new ItemStack(par1, 1, 6));
		par3List.add(new ItemStack(par1, 1, 7));
		par3List.add(new ItemStack(par1, 1, 8));

		for (int a = 0; a < 16; ++a)
		{
			ItemStack banner = new ItemStack(par1, 1, 8);
			banner.setTagCompound(new NBTTagCompound());
			banner.stackTagCompound.setByte("color", (byte) a);
			par3List.add(banner);
		}

	}

	@Override
	public IIcon getIcon(int par1, int par2)
	{
		if (par2 == 0)
			return this.iconDefault;
		if (par2 == 6)
			return this.iconGreatwood;
		if (par2 == 7)
			return this.iconSilverwood;
		if (par2 != 2 && par2 != 3)
		{
			if (this.renderState == 0)
				switch (par1)
				{
					case 0:
						return this.iconAEar[2];
					case 1:
						return this.iconAEar[4];
				}
			else
			{
				if (this.renderState != 1)
				{
					if (par1 <= 1)
						return this.iconAEar[6];

					return this.iconAEar[5];
				}

				switch (par1)
				{
					case 0:
						return this.iconAEar[2];
					case 1:
						return this.iconAEar[3];
				}
			}

			return this.iconAEar[0];
		}
		return this.iconAPPlate[0];
	}

	@Override
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side)
	{
		int meta = world.getBlockMetadata(x, y, z);
		if (meta == 2 || meta == 3)
		{
			TileEntity tile = world.getTileEntity(x, y, z);
			if (tile instanceof TileArcanePressurePlate)
				return this.iconAPPlate[((TileArcanePressurePlate) tile).setting];
		}

		return super.getIcon(world, x, y, z, side);
	}

	@Override
	public int damageDropped(int par1)
	{
		return par1 == 3 ? 2 : par1;
	}

	@Override
	public Item getItemDropped(int par1, Random par2Random, int par3)
	{
		return !Config.wardedStone || par1 != 2 && par1 != 3 ? par1 == 8 ? Item.getItemById(0) : super.getItemDropped(par1, par2Random, par3) : Item.getItemById(0);
	}

	@Override
	public float getBlockHardness(World world, int x, int y, int z)
	{
		if (world.getBlock(x, y, z) != this)
			return super.getBlockHardness(world, x, y, z);
		int md = world.getBlockMetadata(x, y, z);
		return md != 2 && md != 3 ? super.getBlockHardness(world, x, y, z) : Config.wardedStone ? -1.0F : 2.0F;
	}

	@Override
	public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ)
	{
		if (world.getBlock(x, y, z) != this)
			return super.getExplosionResistance(par1Entity, world, x, y, z, explosionX, explosionY, explosionZ);
		int md = world.getBlockMetadata(x, y, z);
		return md != 2 && md != 3 ? super.getExplosionResistance(par1Entity, world, x, y, z, explosionX, explosionY, explosionZ) : 999.0F;
	}

	@Override
	public void onBlockExploded(World world, int x, int y, int z, Explosion explosion)
	{
		if (world.getBlock(x, y, z) == this)
		{
			int md = world.getBlockMetadata(x, y, z);
			if (md != 2 && md != 3)
				super.onBlockExploded(world, x, y, z, explosion);
		}
		else
			super.onBlockExploded(world, x, y, z, explosion);

	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public int getRenderType()
	{
		return ConfigBlocks.blockWoodenDeviceRI;
	}

	@Override
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World p_149633_1_, int p_149633_2_, int p_149633_3_, int p_149633_4_)
	{
		return p_149633_1_.getBlock(p_149633_2_, p_149633_3_, p_149633_4_) != this ? AxisAlignedBB.getBoundingBox(p_149633_2_, p_149633_3_, p_149633_4_, p_149633_2_ + 1.0D, p_149633_3_ + 1.0D, p_149633_4_ + 1.0D) : super.getSelectedBoundingBoxFromPool(p_149633_1_, p_149633_2_, p_149633_3_, p_149633_4_);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess par1iBlockAccess, int par2, int par3, int par4)
	{
		if (par1iBlockAccess.getBlock(par2, par3, par4) != this)
			super.setBlockBoundsBasedOnState(par1iBlockAccess, par2, par3, par4);
		else
		{
			int meta = par1iBlockAccess.getBlockMetadata(par2, par3, par4);
			if (meta == 0)
				this.setBlockBounds(0.1F, 0.0F, 0.1F, 0.9F, 1.0F, 0.9F);
			else if (meta == 2)
			{
				float var6 = 0.0625F;
				this.setBlockBounds(var6, 0.0F, var6, 1.0F - var6, 0.0625F, 1.0F - var6);
			}
			else if (meta == 3)
			{
				float var6 = 0.0625F;
				this.setBlockBounds(var6, 0.0F, var6, 1.0F - var6, 0.03125F, 1.0F - var6);
			}
			else if (meta == 5)
			{
				ForgeDirection dir = ForgeDirection.UNKNOWN;
				TileEntity tile = par1iBlockAccess.getTileEntity(par2, par3, par4);
				if (tile instanceof TileArcaneBore)
					dir = ((TileArcaneBore) tile).orientation;

				this.setBlockBounds(dir.offsetX < 0 ? -1 : 0, dir.offsetY < 0 ? -1 : 0, dir.offsetZ < 0 ? -1 : 0, 1 + (dir.offsetX > 0 ? 1 : 0), 1 + (dir.offsetY > 0 ? 1 : 0), 1 + (dir.offsetZ > 0 ? 1 : 0));
			}
			else if (meta == 8)
			{
				TileEntity tile = par1iBlockAccess.getTileEntity(par2, par3, par4);
				if (tile instanceof TileBanner)
					if (((TileBanner) tile).getWall())
						switch (((TileBanner) tile).getFacing())
						{
							case 0:
								this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 0.25F);
								break;
							case 4:
								this.setBlockBounds(0.75F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F);
								break;
							case 8:
								this.setBlockBounds(0.0F, 0.0F, 0.75F, 1.0F, 2.0F, 1.0F);
								break;
							case 12:
								this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.25F, 2.0F, 1.0F);
						}
					else
						this.setBlockBounds(0.33F, 0.0F, 0.33F, 0.66F, 2.0F, 0.66F);
				else
					this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
			}
			else
				this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);

			super.setBlockBoundsBasedOnState(par1iBlockAccess, par2, par3, par4);
		}
	}

	@Override
	public void addCollisionBoxesToList(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, List arraylist, Entity par7Entity)
	{
		if (world.getBlock(i, j, k) != this)
			super.addCollisionBoxesToList(world, i, j, k, axisalignedbb, arraylist, par7Entity);
		else
		{
			int meta = world.getBlockMetadata(i, j, k);
			if (meta == 0)
				this.setBlockBounds(0.1F, 0.0F, 0.1F, 0.9F, 1.0F, 0.9F);
			else if (meta != 2 && meta != 3 && meta != 8)
				if (meta == 5)
				{
					ForgeDirection dir = ForgeDirection.UNKNOWN;
					TileEntity tile = world.getTileEntity(i, j, k);
					if (tile instanceof TileArcaneBore)
						dir = ((TileArcaneBore) tile).orientation;

					this.setBlockBounds(dir.offsetX < 0 ? -1 : 0, dir.offsetY < 0 ? -1 : 0, dir.offsetZ < 0 ? -1 : 0, 1 + (dir.offsetX > 0 ? 1 : 0), 1 + (dir.offsetY > 0 ? 1 : 0), 1 + (dir.offsetZ > 0 ? 1 : 0));
				}
				else
					this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
			else
				this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);

			super.addCollisionBoxesToList(world, i, j, k, axisalignedbb, arraylist, par7Entity);
		}
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block par5)
	{
		int meta = world.getBlockMetadata(x, y, z);
		if (meta == 1)
		{
			TileEntity tile = world.getTileEntity(x, y, z);
			if (tile instanceof TileSensor)
				((TileSensor) tile).updateTone();
		}
		else if (meta == 5)
		{
			TileEntity tile = world.getTileEntity(x, y, z);
			if (tile instanceof TileArcaneBore)
			{
				ForgeDirection baseOrientation = ((TileArcaneBore) tile).baseOrientation;
				ForgeDirection d = baseOrientation.getOpposite();
				Block block = world.getBlock(x + d.offsetX, y + d.offsetY, z + d.offsetZ);
				if (block != ConfigBlocks.blockWoodenDevice || !block.isSideSolid(world, x + d.offsetX, y + d.offsetY, z + d.offsetZ, baseOrientation))
				{
					InventoryUtils.dropItems(world, x, y, z);
					this.dropBlockAsItem(world, x, y, z, 5, 0);
					world.setBlockToAir(x, y, z);
				}
			}
		}

		super.onNeighborBlockChange(world, x, y, z, par5);
	}

	@Override
	public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side)
	{
		int meta = world.getBlockMetadata(x, y, z);
		return meta == 4 || meta == 6 || meta == 7 || super.isSideSolid(world, x, y, z, side);
	}

	@Override
	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer p, int par6, float par7, float par8, float par9)
	{
		if (w.getBlock(x, y, z) != this)
			return false;
		int meta = w.getBlockMetadata(x, y, z);
		if (meta != 4 && meta != 6 && meta != 7)
		{
			if (w.isRemote)
				return true;
			if (meta != 5 || p.inventory.getCurrentItem() != null && p.inventory.getCurrentItem() != null && p.inventory.getCurrentItem().getItem() instanceof ItemWandCasting)
			{
				if (meta == 1)
				{
					TileSensor var6 = (TileSensor) w.getTileEntity(x, y, z);
					if (var6 != null)
					{
						var6.changePitch();
						var6.triggerNote(w, x, y, z, true);
					}
				}
				else if (meta != 2 && meta != 3)
				{
					if (meta == 8 && (p.isSneaking() || p.inventory.getCurrentItem() != null && p.inventory.getCurrentItem().getItem() instanceof ItemEssence))
					{
						TileBanner te = (TileBanner) w.getTileEntity(x, y, z);
						if (te != null && te.getColor() >= 0)
						{
							if (p.isSneaking())
								te.setAspect(null);
							else if (((IEssentiaContainerItem) p.getHeldItem().getItem()).getAspects(p.getHeldItem()) != null)
							{
								te.setAspect(((IEssentiaContainerItem) p.getHeldItem().getItem()).getAspects(p.getHeldItem()).getAspects()[0]);
								--p.getHeldItem().stackSize;
							}

							w.markBlockForUpdate(x, y, z);
							te.markDirty();
							w.playSoundEffect(x, y, z, "step.cloth", 1.0F, 1.0F);
						}
					}
				}
				else
				{
					TileArcanePressurePlate var6 = (TileArcanePressurePlate) w.getTileEntity(x, y, z);
					if (var6 != null && (var6.owner.equals(p.getCommandSenderName()) || var6.accessList.contains("1" + p.getCommandSenderName())))
					{
						++var6.setting;
						if (var6.setting > 2)
							var6.setting = 0;

						switch (var6.setting)
						{
							case 0:
								p.addChatMessage(new ChatComponentTranslation("It will now trigger on everything."));
								break;
							case 1:
								p.addChatMessage(new ChatComponentTranslation("It will now trigger on everything except you."));
								break;
							case 2:
								p.addChatMessage(new ChatComponentTranslation("It will now trigger on just you."));
						}

						w.playSoundEffect(x + 0.5D, y + 0.1D, z + 0.5D, "random.click", 0.1F, 0.9F);
						w.markBlockForUpdate(x, y, z);
						var6.markDirty();
					}
				}

				return true;
			}
			p.openGui(Thaumcraft.instance, 15, w, x, y, z);
			return true;
		}
		return false;
	}

	@Override
	public void onBlockHarvested(World par1World, int par2, int par3, int par4, int par5, EntityPlayer par6EntityPlayer)
	{
		int md = par1World.getBlockMetadata(par2, par3, par4);
		if (md == 8)
			this.dropBlockAsItem(par1World, par2, par3, par4, par5, 0);

		super.onBlockHarvested(par1World, par2, par3, par4, par5, par6EntityPlayer);
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
	{
		int md = world.getBlockMetadata(x, y, z);
		if (md != 8)
			return super.getDrops(world, x, y, z, metadata, fortune);
		ArrayList<ItemStack> drops = new ArrayList();
		TileEntity te = world.getTileEntity(x, y, z);
		if (te instanceof TileBanner)
		{
			ItemStack drop = new ItemStack(this, 1, 8);
			if (((TileBanner) te).getColor() >= 0 || ((TileBanner) te).getAspect() != null)
			{
				drop.setTagCompound(new NBTTagCompound());
				if (((TileBanner) te).getAspect() != null)
					drop.stackTagCompound.setString("aspect", ((TileBanner) te).getAspect().getTag());

				drop.stackTagCompound.setByte("color", ((TileBanner) te).getColor());
			}

			drops.add(drop);
		}

		return drops;
	}

	@Override
	public void onBlockPlacedBy(World w, int x, int y, int z, EntityLivingBase p, ItemStack s)
	{
		TileEntity tile = w.getTileEntity(x, y, z);
		if (tile != null && p instanceof EntityPlayer)
		{
			// TODO gamerforEA code start
			if (tile instanceof TileArcaneBore)
				((TileArcaneBore) tile).fake.setProfile(((EntityPlayer) p).getGameProfile());
			// TODO gamerforEA code end

			if (tile instanceof TileOwned)
				((TileOwned) tile).owner = p.getCommandSenderName();
			tile.markDirty();
		}

		super.onBlockPlacedBy(w, x, y, z, p, s);
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z)
	{
		super.onBlockAdded(world, x, y, z);
		if (world.getBlock(x, y, z) == this)
		{
			int meta = world.getBlockMetadata(x, y, z);
			if (meta == 1)
			{
				TileEntity tile = world.getTileEntity(x, y, z);
				if (tile instanceof TileSensor)
				{
					((TileSensor) tile).updateTone();
					tile.markDirty();
				}
			}

		}
	}

	@Override
	public boolean canConnectRedstone(IBlockAccess world, int x, int y, int z, int side)
	{
		int meta = world.getBlockMetadata(x, y, z);
		return meta != 0 && (meta == 1 || meta == 2 || meta == 3 || meta == 4 || meta == 5 || super.canConnectRedstone(world, x, y, z, side));
	}

	@Override
	public TileEntity createTileEntity(World world, int metadata)
	{
		return metadata == 0 ? new TileBellows() : metadata == 1 ? new TileSensor() : metadata == 2 ? new TileArcanePressurePlate() : metadata == 3 ? new TileArcanePressurePlate() : metadata == 4 ? new TileArcaneBoreBase() : metadata == 5 ? new TileArcaneBore() : metadata == 8 ? new TileBanner() : super.createTileEntity(world, metadata);
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int md)
	{
		return null;
	}

	@Override
	public boolean onBlockEventReceived(World par1World, int par2, int par3, int par4, int par5, int par6)
	{
		float var7 = (float) Math.pow(2.0D, (par6 - 12) / 12.0D);
		if (par5 <= 4)
		{
			if (par5 >= 0)
			{
				String var8 = "harp";
				if (par5 == 1)
					var8 = "bd";

				if (par5 == 2)
					var8 = "snare";

				if (par5 == 3)
					var8 = "hat";

				if (par5 == 4)
					var8 = "bassattack";

				par1World.playSoundEffect(par2 + 0.5D, par3 + 0.5D, par4 + 0.5D, "note." + var8, 3.0F, var7);
			}

			par1World.spawnParticle("note", par2 + 0.5D, par3 + 1.2D, par4 + 0.5D, par6 / 24.0D, 0.0D, 0.0D);
			return true;
		}
		return par5 == 99 ? super.onBlockEventReceived(par1World, par2, par3, par4, par5, par6) : super.onBlockEventReceived(par1World, par2, par3, par4, par5, par6);
	}

	@Override
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
	{
		if (par1World.getBlock(par2, par3, par4) == this)
			if (!par1World.isRemote && par1World.getBlockMetadata(par2, par3, par4) == 3)
				this.setStateIfMobInteractsWithPlate(par1World, par2, par3, par4);
	}

	@Override
	public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity)
	{
		if (par1World.getBlock(par2, par3, par4) == this)
			if (!par1World.isRemote && par1World.getBlockMetadata(par2, par3, par4) == 2)
				this.setStateIfMobInteractsWithPlate(par1World, par2, par3, par4);
	}

	private void setStateIfMobInteractsWithPlate(World world, int x, int y, int z)
	{
		boolean var5 = world.getBlockMetadata(x, y, z) == 3;
		boolean var6 = false;
		float var7 = 0.125F;
		List<Entity> var8 = null;
		String username = "";
		byte setting = 0;
		ArrayList<String> accessList = new ArrayList();
		TileEntity tile = world.getTileEntity(x, y, z);
		if (tile instanceof TileArcanePressurePlate)
		{
			setting = ((TileArcanePressurePlate) tile).setting;
			username = ((TileArcanePressurePlate) tile).owner;
			accessList = ((TileArcanePressurePlate) tile).accessList;
		}

		if (setting == 0)
			var8 = world.getEntitiesWithinAABBExcludingEntity(null, AxisAlignedBB.getBoundingBox(x + var7, y, z + var7, x + 1 - var7, y + 0.25D, z + 1 - var7));

		if (setting == 1)
			var8 = world.getEntitiesWithinAABB(Entity.class, AxisAlignedBB.getBoundingBox(x + var7, y, z + var7, x + 1 - var7, y + 0.25D, z + 1 - var7));

		if (setting == 2)
			var8 = world.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(x + var7, y, z + var7, x + 1 - var7, y + 0.25D, z + 1 - var7));

		if (!var8.isEmpty())
			for (Entity var10 : var8)
			{
				if (!var10.doesEntityNotTriggerPressurePlate() && (setting != 1 || !(var10 instanceof EntityPlayer) || !var10.getCommandSenderName().equals(username) && !accessList.contains("0" + var10.getCommandSenderName()) && !accessList.contains("1" + var10.getCommandSenderName())) && (setting != 2 || !(var10 instanceof EntityPlayer) || var10.getCommandSenderName().equals(username) || accessList.contains("0" + var10.getCommandSenderName()) || accessList.contains("1" + var10.getCommandSenderName())))
				{
					var6 = true;
					break;
				}
			}

		if (var6 && !var5)
		{
			world.setBlockMetadataWithNotify(x, y, z, 3, 2);
			world.notifyBlocksOfNeighborChange(x, y, z, this);
			world.notifyBlocksOfNeighborChange(x, y - 1, z, this);
			world.markBlockRangeForRenderUpdate(x, y, z, x, y, z);
			world.playSoundEffect(x + 0.5D, y + 0.1D, z + 0.5D, "random.click", 0.2F, 0.6F);
		}

		if (!var6 && var5)
		{
			world.setBlockMetadataWithNotify(x, y, z, 2, 2);
			world.notifyBlocksOfNeighborChange(x, y, z, this);
			world.notifyBlocksOfNeighborChange(x, y - 1, z, this);
			world.markBlockRangeForRenderUpdate(x, y, z, x, y, z);
			world.playSoundEffect(x + 0.5D, y + 0.1D, z + 0.5D, "random.click", 0.2F, 0.5F);
		}

		if (var6)
			world.scheduleBlockUpdate(x, y, z, this, this.tickRate());

	}

	@Override
	public void breakBlock(World par1World, int par2, int par3, int par4, Block par5, int par6)
	{
		if (par6 == 3)
		{
			par1World.notifyBlocksOfNeighborChange(par2, par3, par4, this);
			par1World.notifyBlocksOfNeighborChange(par2, par3 - 1, par4, this);
		}
		else if (par6 == 5)
			InventoryUtils.dropItems(par1World, par2, par3, par4);

		super.breakBlock(par1World, par2, par3, par4, par5, par6);
	}

	@Override
	public int isProvidingStrongPower(IBlockAccess world, int x, int y, int z, int side)
	{
		int meta = world.getBlockMetadata(x, y, z);
		if (meta == 1)
		{
			TileEntity tile = world.getTileEntity(x, y, z);
			return tile instanceof TileSensor ? ((TileSensor) tile).redstoneSignal > 0 ? 15 : 0 : super.isProvidingStrongPower(world, x, y, z, side);
		}
		return world.getBlockMetadata(x, y, z) == 2 ? 0 : side == 1 && world.getBlockMetadata(x, y, z) == 3 ? 15 : 0;
	}

	@Override
	public int isProvidingWeakPower(IBlockAccess world, int x, int y, int z, int side)
	{
		int meta = world.getBlockMetadata(x, y, z);
		if (meta == 1)
		{
			TileEntity tile = world.getTileEntity(x, y, z);
			if (tile instanceof TileSensor)
				return ((TileSensor) tile).redstoneSignal > 0 ? 15 : 0;
		}
		else if (meta == 3)
			return 15;

		return super.isProvidingStrongPower(world, x, y, z, side);
	}

	@Override
	public boolean canProvidePower()
	{
		return true;
	}

	@Override
	public int getMobilityFlag()
	{
		return 1;
	}

	@Override
	public int getLightOpacity(IBlockAccess world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);
		return meta != 6 && meta != 7 ? super.getLightOpacity(world, x, y, z) : 255;
	}

	@Override
	public boolean canEntityDestroy(IBlockAccess world, int x, int y, int z, Entity entity)
	{
		int meta = world.getBlockMetadata(x, y, z);
		return meta != 2 && meta != 3 && super.canEntityDestroy(world, x, y, z, entity);
	}

	@Override
	public int getFlammability(IBlockAccess world, int x, int y, int z, ForgeDirection face)
	{
		int meta = world.getBlockMetadata(x, y, z);
		return meta != 6 && meta != 7 ? 0 : 20;
	}

	@Override
	public int getFireSpreadSpeed(IBlockAccess world, int x, int y, int z, ForgeDirection face)
	{
		int meta = world.getBlockMetadata(x, y, z);
		return meta != 6 && meta != 7 ? 0 : 5;
	}
}
