/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.item.equipment;

import com.meteor.extrabotany.common.core.util.ItemNBTHelper;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C07PacketPlayerDigging;
import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.world.BlockEvent;
import ru.justagod.cutter.invoke.Invoke;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.api.mana.IManaBlock;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.lens.ItemLens;

public class CoreTool {
    public static Material[] materialsPick = new Material[]{Material.rock, Material.iron, Material.ice, Material.glass, Material.piston, Material.anvil};
    public static Material[] materialsShovel = new Material[]{Material.grass, Material.ground, Material.sand, Material.snow, Material.craftedSnow, Material.clay};
    public static Material[] materialsAxe = new Material[]{Material.coral, Material.leaves, Material.plants, Material.wood, Material.vine};

    public static boolean CoreBreak(EntityPlayer player, Map<Block, Integer> blockMap, Block block, int meta, World world, int x, int y, int z, boolean breakSound) {
        if (player.capabilities.isCreativeMode || blockMap.containsKey(block) && blockMap.get(block) == meta) {
            block.onBlockHarvested(world, x, y, z, meta, player);
            if (block.removedByPlayer(world, player, x, y, z, false)) {
                block.onBlockDestroyedByPlayer(world, x, y, z, meta);
            }
            if (!world.isRemote) {
                ((EntityPlayerMP)player).playerNetServerHandler.sendPacket((Packet)new S23PacketBlockChange(x, y, z, world));
            }
            if (breakSound) {
                world.playAuxSFX(2001, x, y, z, Block.getIdFromBlock((Block)block) + (meta << 12));
            }
            return true;
        }
        if (!world.isRemote) {
            block.onBlockHarvested(world, x, y, z, meta, player);
            if (block.removedByPlayer(world, player, x, y, z, true)) {
                block.onBlockDestroyedByPlayer(world, x, y, z, meta);
                block.harvestBlock(world, player, x, y, z, meta);
                player.addExhaustion(-0.025f);
                if (block.getExpDrop((IBlockAccess)world, meta, EnchantmentHelper.getFortuneModifier((EntityLivingBase)player)) > 0) {
                    player.addExperience(block.getExpDrop((IBlockAccess)world, meta, EnchantmentHelper.getFortuneModifier((EntityLivingBase)player)));
                }
            }
            EntityPlayerMP mpPlayer = (EntityPlayerMP)player;
            mpPlayer.playerNetServerHandler.sendPacket((Packet)new S23PacketBlockChange(x, y, z, world));
        } else {
            if (breakSound) {
                world.playAuxSFX(2001, x, y, z, Block.getIdFromBlock((Block)block) + (meta << 12));
            }
            if (block.removedByPlayer(world, player, x, y, z, true)) {
                block.onBlockDestroyedByPlayer(world, x, y, z, meta);
            }
            Minecraft.getMinecraft().getNetHandler().addToSendQueue((Packet)new C07PacketPlayerDigging(2, x, y, z, Minecraft.getMinecraft().objectMouseOver.sideHit));
        }
        return true;
    }

    protected static boolean breakExtraBlock(ItemStack stack, World world, int x, int y, int z, int totalSize, EntityPlayer player, float refStrength, boolean breakSound, Map<Block, Integer> blockMap, int typeTool) {
        BlockEvent.BreakEvent event;
        if (world.isAirBlock(x, y, z)) {
            return true;
        }
        Block block = world.getBlock(x, y, z);
        if (typeTool != -1) {
            Material[] materailsBlock;
            boolean breakBlock = false;
            for (Material material : materailsBlock = typeTool == 0 ? materialsAxe : (typeTool == 1 ? materialsShovel : materialsPick)) {
                if (block.getMaterial() != material) continue;
                breakBlock = true;
                break;
            }
            if (!breakBlock) {
                return true;
            }
        }
        if (block.getMaterial() instanceof MaterialLiquid || block.getBlockHardness(world, x, y, x) <= 0.0f && !player.capabilities.isCreativeMode) {
            return true;
        }
        int dmg = stack.getItemDamage();
        if (!ManaItemHandler.requestManaExact((ItemStack)new ItemStack(ModItems.manaRing), (EntityPlayer)player, (int)100, (boolean)true)) {
            if (typeTool != -1) {
                return false;
            }
            stack.setItemDamage(++dmg);
        }
        if (dmg >= 2299) {
            if (!player.worldObj.isRemote) {
                player.addChatMessage((IChatComponent)new ChatComponentTranslation("\u00a7f[\u00a76\u041a\u0440\u0443\u0448\u0438\u0442\u0435\u043b\u044c \u0438\u0437 \u044d\u043b\u044c\u0444\u0438\u0440\u0438\u0443\u043c\u0430\u00a7f]: \u00a7c\u041a\u0440\u0443\u0448\u0438\u0442\u0435\u043b\u044c \u043f\u043e\u0432\u0440\u0435\u0436\u0434\u0435\u043d! \u041a\u043e\u043f\u0430\u043d\u0438\u0435 \u043d\u0435\u0432\u043e\u0437\u043c\u043e\u0436\u043d\u043e", new Object[0]));
            }
            return false;
        }
        TileEntity te = world.getTileEntity(x, y, z);
        if (ItemNBTHelper.getInteger(stack, "thisRad", 0) != 0 && ItemNBTHelper.getInteger(stack, "thisGlub", 0) != 0 && te != null) {
            if (!player.worldObj.isRemote) {
                player.addChatMessage((IChatComponent)new ChatComponentTranslation("\u00a7f[\u00a76Guard ExtraBotania\u00a7f]: \u041e\u0431\u043d\u0430\u0440\u0443\u0436\u0435\u043d \u0444\u0443\u043d\u043a\u0446\u0438\u043e\u043d\u0430\u043b\u044c\u043d\u044b\u0439 \u0431\u043b\u043e\u043a! \u041b\u043e\u043c\u0430\u043d\u0438\u0435 \u00a7f\u043f\u043e \u00a7f\u043e\u0431\u043b\u0430\u0441\u0442\u0438 \u00a7f\u043f\u0440\u0435\u0440\u0432\u0430\u043d\u043e, \u00a7f\u0442\u0430\u043a \u00a7f\u043a\u0430\u043a \u00a7f\u0433\u043b\u0443\u0431\u0438\u043d\u0430 \u00a7f\u0438 \u00a7f\u0440\u0430\u0434\u0438\u0443\u0441 \u00a7f\u043d\u0435 \u00a7f\u0440\u0430\u0432\u043d\u044b \u00a7f1", new Object[0]));
            }
            return false;
        }
        int meta = world.getBlockMetadata(x, y, z);
        float strength = ForgeHooks.blockStrength((Block)block, (EntityPlayer)player, (World)world, (int)x, (int)y, (int)z);
        if (!ForgeHooks.canHarvestBlock((Block)block, (EntityPlayer)player, (int)meta) || strength <= 0.0f && !player.capabilities.isCreativeMode) {
            return true;
        }
        if (!world.isRemote && (event = ForgeHooks.onBlockBreakEvent((World)world, (WorldSettings.GameType)world.getWorldInfo().getGameType(), (EntityPlayerMP)((EntityPlayerMP)player), (int)x, (int)y, (int)z)).isCanceled()) {
            ((EntityPlayerMP)player).playerNetServerHandler.sendPacket((Packet)new S23PacketBlockChange(x, y, z, world));
            return true;
        }
        return CoreTool.CoreBreak(player, blockMap, block, meta, world, x, y, z, breakSound);
    }

    public static Map<Block, Integer> getObliterationList(ItemStack stack) {
        HashMap<Block, Integer> blockMap = new HashMap<Block, Integer>();
        NBTTagCompound compound = ItemNBTHelper.getCompound(stack);
        if (compound.hasNoTags()) {
            return blockMap;
        }
        for (int i = 0; i < 9; ++i) {
            ItemStack stack1;
            NBTTagCompound tag = new NBTTagCompound();
            if (compound.hasKey("Item" + i)) {
                tag = compound.getCompoundTag("Item" + i);
            }
            if (tag.hasNoTags() || (stack1 = ItemStack.loadItemStackFromNBT((NBTTagCompound)tag)) == null || !(stack1.getItem() instanceof ItemBlock)) continue;
            blockMap.put(Block.getBlockFromItem((Item)stack1.getItem()), stack1.getItemDamage());
        }
        return blockMap;
    }

    public static short hasToolEffectFortune(ItemStack stack) {
        NBTTagList ench = stack.getEnchantmentTagList();
        short fortune = 0;
        for (int i = 0; i < Math.min(ench.tagCount(), 127); i = (int)((byte)(i + 1))) {
            NBTTagCompound en = ench.getCompoundTagAt(i);
            if (!en.hasKey("id") || en.getShort("id") != Enchantment.fortune.effectId || !en.hasKey("lvl")) continue;
            fortune = en.getShort("lvl");
        }
        return fortune;
    }

    public static boolean collideBurst(IManaBurst burst, MovingObjectPosition pos, boolean isManaBlock, boolean dead, ItemStack stack, int tool) {
        boolean warp;
        EntityThrowable entity = (EntityThrowable)burst;
        World world = entity.worldObj;
        int x = pos.blockX;
        int y = pos.blockY;
        int z = pos.blockZ;
        Block block = world.getBlock(x, y, z);
        ItemStack composite = ((ItemLens)ModItems.lens).getCompositeLens(stack);
        boolean bl = warp = composite != null && composite.getItem() == ModItems.lens && composite.getItemDamage() == 18;
        if (warp && (block == ModBlocks.pistonRelay || block == Blocks.piston || block == Blocks.piston_extension || block == Blocks.piston_head)) {
            return false;
        }
        TileEntity tile = world.getTileEntity(x, y, z);
        if (!(tile instanceof IManaBlock) && !burst.isFake()) {
            String attacker = vazkii.botania.common.core.helper.ItemNBTHelper.getString((ItemStack)burst.getSourceLens(), (String)"attackerUsername", (String)"");
            EntityPlayer player = world.getPlayerEntityByName(attacker);
            if (CoreTool.checkMaterial(block, tool) && player != null) {
                dead = true;
                Invoke.server(() -> {});
            }
        }
        return dead;
    }

    public static boolean checkMaterial(Block bl, ItemStack stack) {
        if (stack == null) {
            return false;
        }
        if (stack.getItem() instanceof ItemTool) {
            if (stack.getItem() instanceof ItemAxe) {
                return CoreTool.checkMaterial(bl, 0);
            }
            if (stack.getItem() instanceof ItemSpade) {
                return CoreTool.checkMaterial(bl, 1);
            }
            if (stack.getItem() instanceof ItemPickaxe) {
                return CoreTool.checkMaterial(bl, 2);
            }
        }
        return false;
    }

    public static boolean checkMaterial(Block bl, int tool) {
        if (bl == null || bl == Blocks.air) {
            return false;
        }
        switch (tool) {
            case 0: {
                for (Material m : materialsAxe) {
                    if (bl.getMaterial() != m) continue;
                    return true;
                }
                break;
            }
            case 1: {
                for (Material m : materialsShovel) {
                    if (bl.getMaterial() != m) continue;
                    return true;
                }
                break;
            }
            case 2: {
                for (Material m : materialsPick) {
                    if (bl.getMaterial() != m) continue;
                    return true;
                }
                break;
            }
        }
        return false;
    }
}

