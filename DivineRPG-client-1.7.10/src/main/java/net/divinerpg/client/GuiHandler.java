/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.IGuiHandler
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.IMerchant
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 */
package net.divinerpg.client;

import cpw.mods.fml.common.network.IGuiHandler;
import net.divinerpg.blocks.arcana.container.ContainerExtractor;
import net.divinerpg.blocks.arcana.container.tile_entity.TileEntityExtractor;
import net.divinerpg.blocks.base.tileentity.TileEntityInfiniteFurnace;
import net.divinerpg.blocks.base.tileentity.TileEntityModFurnace;
import net.divinerpg.blocks.base.tileentity.container.ContainerDivineMerchant;
import net.divinerpg.blocks.base.tileentity.container.ContainerInfiniteFurnace;
import net.divinerpg.blocks.base.tileentity.container.ContainerModFurnace;
import net.divinerpg.blocks.vanilla.container.ContainerAltarCorruption;
import net.divinerpg.blocks.vethea.container.ContainerDreamLamp;
import net.divinerpg.blocks.vethea.container.ContainerInfusionTable;
import net.divinerpg.blocks.vethea.container.tileentity.TileEntityInfusionTable;
import net.divinerpg.client.render.gui.GuiAltarCorruption;
import net.divinerpg.client.render.gui.GuiCoalstoneFurnace;
import net.divinerpg.client.render.gui.GuiDatticon;
import net.divinerpg.client.render.gui.GuiDemonFurnace;
import net.divinerpg.client.render.gui.GuiDreamLamp;
import net.divinerpg.client.render.gui.GuiExtractor;
import net.divinerpg.client.render.gui.GuiGreenlightFurnace;
import net.divinerpg.client.render.gui.GuiHunger;
import net.divinerpg.client.render.gui.GuiInfusionTable;
import net.divinerpg.client.render.gui.GuiJackOMan;
import net.divinerpg.client.render.gui.GuiLeorna;
import net.divinerpg.client.render.gui.GuiLivestockMerchant;
import net.divinerpg.client.render.gui.GuiMerik;
import net.divinerpg.client.render.gui.GuiMoltenFurnace;
import net.divinerpg.client.render.gui.GuiMoonlightFurnace;
import net.divinerpg.client.render.gui.GuiOceanfireFurnace;
import net.divinerpg.client.render.gui.GuiTinker;
import net.divinerpg.client.render.gui.GuiVatticus;
import net.divinerpg.client.render.gui.GuiWarGeneral;
import net.divinerpg.client.render.gui.GuiWhitefireFurnace;
import net.divinerpg.client.render.gui.GuiWorkshopMerchant;
import net.divinerpg.client.render.gui.GuiZelus;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandler
implements IGuiHandler {
    public static int guiID = 0;
    public static int twilightFurnace = guiID++;
    public static int infusionTable = guiID++;
    public static int hunger = guiID++;
    public static int coalstone = guiID++;
    public static int tinker = guiID++;
    public static int merchant = guiID++;
    public static int jackOMan = guiID++;
    public static int zelus = guiID++;
    public static int vatticus = guiID++;
    public static int warGeneral = guiID++;
    public static int leorna = guiID++;
    public static int captainMerik = guiID++;
    public static int datticon = guiID++;
    public static int extractor = guiID++;
    public static int greenlight = guiID++;
    public static int oceanfire = guiID++;
    public static int molten = guiID++;
    public static int whitefire = guiID++;
    public static int moonlight = guiID++;
    public static int demon = guiID++;
    public static int altar = guiID++;
    public static int dreamLamp = guiID++;
    public static int livestockMerchant = guiID++;

    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity entity = world.getTileEntity(x, y, z);
        if (ID == infusionTable) {
            return new ContainerInfusionTable(player.inventory, (TileEntityInfusionTable)entity);
        }
        if (ID == merchant || ID == tinker || ID == zelus || ID == hunger || ID == jackOMan || ID == vatticus || ID == leorna || ID == datticon || ID == captainMerik || ID == warGeneral || ID == livestockMerchant) {
            return new ContainerDivineMerchant(player.inventory, (IMerchant)this.getEntityByID(x, world), world);
        }
        if (ID == extractor) {
            return new ContainerExtractor(player.inventory, (TileEntityModFurnace)entity);
        }
        if (ID == oceanfire || ID == molten || ID == whitefire || ID == demon || ID == coalstone) {
            return new ContainerInfiniteFurnace(player.inventory, (TileEntityInfiniteFurnace)entity);
        }
        if (ID == greenlight || ID == moonlight) {
            return new ContainerModFurnace(player.inventory, (TileEntityModFurnace)entity);
        }
        if (ID == altar) {
            return new ContainerAltarCorruption(player.inventory, world, x, y, z);
        }
        if (ID == dreamLamp) {
            return new ContainerDreamLamp((IInventory)player.inventory, (IInventory)entity);
        }
        return null;
    }

    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity entity = world.getTileEntity(x, y, z);
        if (ID == infusionTable) {
            return new GuiInfusionTable(new ContainerInfusionTable(player.inventory, (TileEntityInfusionTable)entity));
        }
        if (ID == coalstone) {
            return new GuiCoalstoneFurnace(player.inventory, (TileEntityInfiniteFurnace)entity);
        }
        if (ID == jackOMan) {
            return new GuiJackOMan(new ContainerDivineMerchant(player.inventory, (IMerchant)this.getEntityByID(x, world), world), (IMerchant)this.getEntityByID(x, world));
        }
        if (ID == merchant) {
            return new GuiWorkshopMerchant(new ContainerDivineMerchant(player.inventory, (IMerchant)this.getEntityByID(x, world), world), (IMerchant)this.getEntityByID(x, world));
        }
        if (ID == tinker) {
            return new GuiTinker(new ContainerDivineMerchant(player.inventory, (IMerchant)this.getEntityByID(x, world), world), (IMerchant)this.getEntityByID(x, world));
        }
        if (ID == zelus) {
            return new GuiZelus(new ContainerDivineMerchant(player.inventory, (IMerchant)this.getEntityByID(x, world), world), (IMerchant)this.getEntityByID(x, world));
        }
        if (ID == vatticus) {
            return new GuiVatticus(new ContainerDivineMerchant(player.inventory, (IMerchant)this.getEntityByID(x, world), world), (IMerchant)this.getEntityByID(x, world));
        }
        if (ID == leorna) {
            return new GuiLeorna(new ContainerDivineMerchant(player.inventory, (IMerchant)this.getEntityByID(x, world), world), (IMerchant)this.getEntityByID(x, world));
        }
        if (ID == datticon) {
            return new GuiDatticon(new ContainerDivineMerchant(player.inventory, (IMerchant)this.getEntityByID(x, world), world), (IMerchant)this.getEntityByID(x, world));
        }
        if (ID == captainMerik) {
            return new GuiMerik(new ContainerDivineMerchant(player.inventory, (IMerchant)this.getEntityByID(x, world), world), (IMerchant)this.getEntityByID(x, world));
        }
        if (ID == warGeneral) {
            return new GuiWarGeneral(new ContainerDivineMerchant(player.inventory, (IMerchant)this.getEntityByID(x, world), world), (IMerchant)this.getEntityByID(x, world));
        }
        if (ID == hunger) {
            return new GuiHunger(new ContainerDivineMerchant(player.inventory, (IMerchant)this.getEntityByID(x, world), world), (IMerchant)this.getEntityByID(x, world));
        }
        if (ID == extractor) {
            return new GuiExtractor(player.inventory, (TileEntityExtractor)entity);
        }
        if (ID == greenlight) {
            return new GuiGreenlightFurnace(player.inventory, (TileEntityModFurnace)entity);
        }
        if (ID == oceanfire) {
            return new GuiOceanfireFurnace(player.inventory, (TileEntityInfiniteFurnace)entity);
        }
        if (ID == molten) {
            return new GuiMoltenFurnace(player.inventory, (TileEntityInfiniteFurnace)entity);
        }
        if (ID == whitefire) {
            return new GuiWhitefireFurnace(player.inventory, (TileEntityInfiniteFurnace)entity);
        }
        if (ID == moonlight) {
            return new GuiMoonlightFurnace(player.inventory, (TileEntityModFurnace)entity);
        }
        if (ID == demon) {
            return new GuiDemonFurnace(player.inventory, (TileEntityInfiniteFurnace)entity);
        }
        if (ID == altar) {
            return new GuiAltarCorruption(player.inventory, world, x, y, z);
        }
        if (ID == dreamLamp) {
            return new GuiDreamLamp((IInventory)player.inventory, (IInventory)entity);
        }
        if (ID == livestockMerchant) {
            return new GuiLivestockMerchant(new ContainerDivineMerchant(player.inventory, (IMerchant)this.getEntityByID(x, world), world), (IMerchant)this.getEntityByID(x, world));
        }
        return null;
    }

    private Entity getEntityByID(int entityID, World world) {
        for (int i = 0; i < world.loadedEntityList.size(); ++i) {
            if (((Entity)world.loadedEntityList.get(i)).getEntityId() != entityID) continue;
            return (Entity)world.loadedEntityList.get(i);
        }
        return null;
    }
}

