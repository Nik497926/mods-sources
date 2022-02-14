/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.ItemSword
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package net.divinerpg.blocks.vanilla;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.divinerpg.DivineRPG;
import net.divinerpg.blocks.base.BlockMod;
import net.divinerpg.items.base.ItemRepair;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockRepairer
extends BlockMod {
    private final Random random = new Random();

    public BlockRepairer(String name) {
        super(name, 3.0f);
    }

    public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player) {
        ItemStack itemstack = player.getCurrentEquippedItem();
        if (itemstack != null && itemstack.isItemDamaged()) {
            ItemSword itemSword;
            ItemArmor itemArmor;
            if (itemstack.getItem() instanceof ItemArmor && DivineRPG.instance.armorManager.isCanRepair((itemArmor = (ItemArmor)itemstack.getItem()).getArmorMaterial())) {
                this.repair(player, itemstack);
            }
            if (itemstack.getItem() instanceof ItemSword && DivineRPG.instance.swordManager.isCanRepair((itemSword = (ItemSword)itemstack.getItem()).getToolMaterialName())) {
                this.repair(player, itemstack);
            }
        }
    }

    private void repair(EntityPlayer player, ItemStack itemstack) {
        ItemStack[] inventory = player.inventory.mainInventory;
        for (int index = 0; index < inventory.length; ++index) {
            if (inventory[index] == null || !(inventory[index].getItem() instanceof ItemRepair)) continue;
            this.splitStack(player.inventory, inventory[index], index);
            this.repairItem(player, itemstack);
            return;
        }
    }

    private void repairItem(EntityPlayer player, ItemStack itemstack) {
        itemstack.setItemDamage(itemstack.getItemDamage() - (this.random.nextInt(100) + 100));
        player.setCurrentItemOrArmor(0, itemstack);
    }

    private void splitStack(InventoryPlayer inventory, ItemStack itemstack, int index) {
        if (itemstack.stackSize > 1) {
            --itemstack.stackSize;
            inventory.setInventorySlotContents(index, itemstack);
        } else {
            inventory.setInventorySlotContents(index, null);
        }
    }

    public int getRenderBlockPass() {
        return 1;
    }

    public boolean renderAsNormalBlock() {
        return false;
    }

    public boolean isOpaqueCube() {
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess blockAccess, int x, int y, int z, int side) {
        Block i1 = blockAccess.getBlock(x, y, z);
        return i1 != this && super.shouldSideBeRendered(blockAccess, x, y, z, side);
    }
}

