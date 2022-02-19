package thaumcraft.common.items.equipment;

import com.gamerforea.eventhelper.util.EventUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import thaumcraft.api.IRepairable;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.blocks.BlockCustomPlant;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.lib.utils.Utils;

public class ItemElementalHoe extends ItemHoe implements IRepairable
{
	public IIcon icon;

	public ItemElementalHoe(ToolMaterial enumtoolmaterial)
	{
		super(enumtoolmaterial);
		this.setCreativeTab(Thaumcraft.tabTC);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir)
	{
		this.icon = ir.registerIcon("thaumcraft:elementalhoe");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int par1)
	{
		return this.icon;
	}

	@Override
	public int getItemEnchantability()
	{
		return 5;
	}

	@Override
	public EnumRarity getRarity(ItemStack itemstack)
	{
		return EnumRarity.rare;
	}

	@Override
	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
	{
		return par2ItemStack.isItemEqual(new ItemStack(ConfigItems.itemResource, 1, 2)) || super.getIsRepairable(par1ItemStack, par2ItemStack);
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10)
	{
		if (player.isSneaking())
			return super.onItemUse(stack, player, world, x, y, z, par7, par8, par9, par10);
		else
		{
			boolean did = false;

			for (int xOffset = -1; xOffset <= 1; ++xOffset)
			{
				for (int zOffset = -1; zOffset <= 1; ++zOffset)
				{
					// TODO gamerforEA code start
					if (EventUtils.cantBreak(player, x + xOffset, y, z + zOffset))
						continue;
					// TODO gamerforEA code end

					if (super.onItemUse(stack, player, world, x + xOffset, y, z + zOffset, par7, par8, par9, par10))
					{
						Thaumcraft.proxy.blockSparkle(world, x + xOffset, y, z + zOffset, 8401408, 2);
						if (!did)
							did = true;
					}
				}
			}

			if (!did)
			{
				did = Utils.useBonemealAtLoc(world, player, x, y, z);
				if (!did)
				{
					Block block = world.getBlock(x, y, z);
					int meta = world.getBlockMetadata(x, y, z);
					if (block == ConfigBlocks.blockCustomPlant && meta == 0 && stack.getItemDamage() + 20 <= stack.getMaxDamage())
					{
						((BlockCustomPlant) block).growGreatTree(world, x, y, z, world.rand);
						stack.damageItem(5, player);
						Thaumcraft.proxy.blockSparkle(world, x, y, z, 0, 2);
						did = true;
					}
					else if (block == ConfigBlocks.blockCustomPlant && meta == 1 && stack.getItemDamage() + 150 <= stack.getMaxDamage())
					{
						((BlockCustomPlant) block).growSilverTree(world, x, y, z, world.rand);
						stack.damageItem(25, player);
						Thaumcraft.proxy.blockSparkle(world, x, y, z, 0, 2);
						did = true;
					}
				}
				else
				{
					stack.damageItem(1, player);
					Thaumcraft.proxy.blockSparkle(world, x, y, z, 0, 3);
				}

				if (did)
					world.playSoundEffect(x + 0.5D, y + 0.5D, z + 0.5D, "thaumcraft:wand", 0.75F, 0.9F + world.rand.nextFloat() * 0.2F);
			}

			return did;
		}
	}
}
