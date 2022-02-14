/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemSeeds
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.EnumPlantType
 */
package net.divinerpg.items.twilight;

import cpw.mods.fml.common.registry.GameRegistry;
import net.divinerpg.utils.LangRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;

public class ItemTwilightSeeds
extends ItemSeeds {
    private Block grass;
    private Block crop;

    public ItemTwilightSeeds(String name, Block block, Block grass) {
        super(block, grass);
        this.grass = grass;
        this.crop = block;
        this.setUnlocalizedName(name);
        this.setTextureName("divinerpg:" + name);
        LangRegistry.addItem((Item)this);
        GameRegistry.registerItem((Item)this, (String)name);
    }

    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
        return EnumPlantType.Plains;
    }

    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        if (side != 1) {
            return false;
        }
        if (player.canPlayerEdit(x, y, z, side, stack) && player.canPlayerEdit(x, y + 1, z, side, stack) && world.getBlock(x, y, z) == this.grass && this.crop.canPlaceBlockAt(world, x, y, z) && world.isAirBlock(x, y + 1, z)) {
            world.setBlock(x, y + 1, z, this.crop);
            --stack.stackSize;
            return true;
        }
        return false;
    }
}

