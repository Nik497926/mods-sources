/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockBed
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayer$EnumStatus
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.item.Item
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.Direction
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.IIcon
 *  net.minecraft.world.Teleporter
 *  net.minecraft.world.World
 */
package net.divinerpg.blocks.vethea;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Iterator;
import java.util.Random;
import net.divinerpg.dimensions.base.WorldGenAPI;
import net.divinerpg.dimensions.vethea.TeleporterVethea;
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
import net.minecraft.item.Item;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Direction;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;

public class BlockNightmareBed
extends BlockBed {
    @SideOnly(value=Side.CLIENT)
    private IIcon[] top;
    @SideOnly(value=Side.CLIENT)
    private IIcon[] end;
    @SideOnly(value=Side.CLIENT)
    private IIcon[] side;

    public BlockNightmareBed() {
        String name = "nightmareBedBlock";
        this.setStepSound(Block.soundTypeStone);
        this.setCreativeTab(null);
        this.setBlockName(name);
        this.setHardness(9.0f);
        GameRegistry.registerBlock((Block)this, (String)name);
        LangRegistry.addBlock((Block)this);
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
        if (world.isRemote) {
            return true;
        }
        EntityPlayerMP MPPlayer = (EntityPlayerMP)player;
        int i1 = world.getBlockMetadata(x, y, z);
        if (!BlockNightmareBed.isBlockHeadOfBed((int)i1)) {
            int j1 = BlockNightmareBed.getDirection((int)i1);
            if (world.getBlock(x += field_149981_a[j1][0], y, z += field_149981_a[j1][1]) != this) {
                return true;
            }
            i1 = world.getBlockMetadata(x, y, z);
        }
        if (player.worldObj.provider.dimensionId != ConfigurationHelper.vethea) {
            if (world.getBlockLightValue(x, y, z) > 7) {
                player.addChatMessage(Util.getChatComponent("You can only use the Nightmare Bed in a dark place."));
                return true;
            }
            EntityPlayer entityplayer1 = null;
            Iterator iterator = world.playerEntities.iterator();
            if (iterator.hasNext()) {
                EntityPlayer entityplayer2 = (EntityPlayer)iterator.next();
                if (entityplayer2.isPlayerSleeping()) {
                    ChunkCoordinates chunkcoordinates = entityplayer2.playerLocation;
                    if (chunkcoordinates.posX == x && chunkcoordinates.posY == y && chunkcoordinates.posZ == z) {
                        entityplayer1 = entityplayer2;
                    }
                }
                if (entityplayer1 != null) {
                    player.addChatComponentMessage((IChatComponent)new ChatComponentTranslation("tile.bed.occupied", new Object[0]));
                    return true;
                }
                EntityPlayer.EnumStatus enumstatus = player.sleepInBedAt(x, y, z);
                MPPlayer.timeUntilPortal = 10;
                MPPlayer.mcServer.getConfigurationManager().transferPlayerToDimension(MPPlayer, ConfigurationHelper.vethea, (Teleporter)new TeleporterVethea(MPPlayer.mcServer.worldServerForDimension(ConfigurationHelper.vethea)));
                ChunkCoordinates c = new ChunkCoordinates();
                c.posX = (int)player.posX + 2;
                c.posY = 18;
                c.posZ = (int)player.posZ - 2;
                player.setSpawnChunk(c, true, ConfigurationHelper.vethea);
                return true;
            }
            return true;
        }
        if (player.worldObj.provider.dimensionId == ConfigurationHelper.vethea) {
            MPPlayer.mcServer.getConfigurationManager().transferPlayerToDimension(MPPlayer, 0, (Teleporter)new TeleporterVethea(MPPlayer.mcServer.worldServerForDimension(0)));
            return true;
        }
        double d2 = (double)x + 0.5;
        double d0 = (double)y + 0.5;
        double d1 = (double)z + 0.5;
        world.setBlockToAir(x, y, z);
        int k1 = BlockNightmareBed.getDirection((int)i1);
        if (world.getBlock(x += field_149981_a[k1][0], y, z += field_149981_a[k1][1]) == this) {
            world.setBlockToAir(x, y, z);
            d2 = (d2 + (double)x + 0.5) / 2.0;
            d0 = (d0 + (double)y + 0.5) / 2.0;
            d1 = (d1 + (double)z + 0.5) / 2.0;
        }
        WorldGenAPI.addRectangle(2, 2, 1, world, x, y - 1, z, TwilightBlocks.mortumBlock);
        return true;
    }

    public Item getItemDropped(int par1, Random rand, int par3) {
        return VetheaItems.nightmareBed;
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        int i1;
        if (side == 0) {
            return TwilightBlocks.mortumBlock.getBlockTextureFromSide(side);
        }
        int k = BlockNightmareBed.getDirection((int)meta);
        int l = Direction.bedDirection[k][side];
        int n = i1 = BlockNightmareBed.isBlockHeadOfBed((int)meta) ? 1 : 0;
        return !(i1 == 1 && l == 2 || i1 == 0 && l == 3) ? (l != 5 && l != 4 ? this.top[i1] : this.side[i1]) : this.end[i1];
    }

    @SideOnly(value=Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        this.top = new IIcon[]{register.registerIcon("divinerpg:nightmareBedFeetTop"), register.registerIcon("divinerpg:nightmareBedHeadTop")};
        this.end = new IIcon[]{register.registerIcon("divinerpg:nightmareBedFeetEnd"), register.registerIcon("divinerpg:nightmareBedHeadEnd")};
        this.side = new IIcon[]{register.registerIcon("divinerpg:nightmareBedFeetSide"), register.registerIcon("divinerpg:nightmareBedHeadSide")};
    }
}

