/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Event
 *  cpw.mods.fml.common.eventhandler.Event$Result
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.ItemTool
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.entity.player.UseHoeEvent
 */
package net.divinerpg.items.base;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.registry.GameRegistry;
import java.util.List;
import net.divinerpg.utils.LangRegistry;
import net.divinerpg.utils.TooltipLocalizer;
import net.divinerpg.utils.tabs.DivineRPGTabs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.UseHoeEvent;

public class ItemShickaxe
extends ItemTool {
    protected String name;

    public ItemShickaxe(Item.ToolMaterial toolMaterial, String name) {
        super(0.0f, toolMaterial, null);
        this.setCreativeTab(DivineRPGTabs.tools);
        this.setTextureName("divinerpg:" + name);
        this.setUnlocalizedName(name);
        GameRegistry.registerItem((Item)this, (String)name);
        LangRegistry.addItem((Item)this);
    }

    public boolean canHarvestBlock(Block block, ItemStack stack) {
        return block != Blocks.bedrock;
    }

    public boolean func_150897_b(Block block) {
        return this.isEfficient(block);
    }

    public float func_150893_a(ItemStack stack, Block block) {
        return this.toolMaterial.getEfficiencyOnProperMaterial();
    }

    protected boolean isEfficient(Block block) {
        return block == Blocks.obsidian ? this.toolMaterial.getHarvestLevel() == 3 : (block != Blocks.diamond_block && block != Blocks.diamond_ore ? (block != Blocks.emerald_ore && block != Blocks.emerald_block ? (block != Blocks.gold_block && block != Blocks.gold_ore ? (block != Blocks.iron_block && block != Blocks.iron_ore ? (block != Blocks.lapis_block && block != Blocks.lapis_ore ? (block != Blocks.redstone_ore && block != Blocks.lit_redstone_ore ? (block.getMaterial() == Material.rock ? this.toolMaterial.getHarvestLevel() >= 3 : (block.getMaterial() == Material.iron ? this.toolMaterial.getHarvestLevel() >= 3 : block.getMaterial() == Material.anvil)) : this.toolMaterial.getHarvestLevel() >= 3) : this.toolMaterial.getHarvestLevel() >= 3) : this.toolMaterial.getHarvestLevel() >= 3) : this.toolMaterial.getHarvestLevel() >= 3) : this.toolMaterial.getHarvestLevel() >= 3) : this.toolMaterial.getHarvestLevel() >= 3);
    }

    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10) {
        if (!player.canPlayerEdit(x, y, z, par7, stack)) {
            return false;
        }
        UseHoeEvent event = new UseHoeEvent(player, stack, world, x, y, z);
        if (MinecraftForge.EVENT_BUS.post((Event)event)) {
            return false;
        }
        if (event.getResult() == Event.Result.ALLOW) {
            stack.damageItem(1, (EntityLivingBase)player);
            return true;
        }
        Block block = world.getBlock(x, y, z);
        if (par7 != 0 && world.getBlock(x, y + 1, z).isAir((IBlockAccess)world, x, y + 1, z) && (block == Blocks.grass || block == Blocks.dirt)) {
            Block block1 = Blocks.farmland;
            world.playSoundEffect((double)((float)x + 0.5f), (double)((float)y + 0.5f), (double)((float)z + 0.5f), block1.stepSound.getStepResourcePath(), (block1.stepSound.getVolume() + 1.0f) / 2.0f, block1.stepSound.getPitch() * 0.8f);
            if (world.isRemote) {
                return true;
            }
            world.setBlock(x, y, z, block1);
            stack.damageItem(1, (EntityLivingBase)player);
            return true;
        }
        return false;
    }

    public void addInformation(ItemStack item, EntityPlayer player, List infoList, boolean par4) {
        infoList.add(TooltipLocalizer.efficiency(this.toolMaterial.getEfficiencyOnProperMaterial()));
        if (item.getMaxDamage() != -1) {
            infoList.add(TooltipLocalizer.usesRemaining(item.getMaxDamage() - item.getItemDamage()));
        } else {
            infoList.add(TooltipLocalizer.infiniteUses());
        }
    }
}

