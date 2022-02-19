/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ClientRegistry
 *  cpw.mods.fml.client.registry.RenderingRegistry
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.item.Item
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.MinecraftForgeClient
 */
package net.divinerpg.client.render.entity;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.divinerpg.blocks.iceika.tileentity.TileEntityFrostedChest;
import net.divinerpg.blocks.iceika.tileentity.TileEntityPresentBox;
import net.divinerpg.client.render.EntityResourceLocation;
import net.divinerpg.client.render.RenderDivineMob;
import net.divinerpg.client.render.RenderIconProjectile;
import net.divinerpg.client.render.RenderSizeable;
import net.divinerpg.client.render.RenderSpecialProjectile;
import net.divinerpg.client.render.block.RenderFrostedChest;
import net.divinerpg.client.render.block.RenderPresentBox;
import net.divinerpg.client.render.entity.iceika.RenderFrosty;
import net.divinerpg.client.render.entity.iceika.model.ModelAlicanto;
import net.divinerpg.client.render.entity.iceika.model.ModelFractite;
import net.divinerpg.client.render.entity.iceika.model.ModelGlacide;
import net.divinerpg.client.render.entity.iceika.model.ModelHastreus;
import net.divinerpg.client.render.entity.iceika.model.ModelRollum;
import net.divinerpg.client.render.entity.iceika.model.ModelWorkshop;
import net.divinerpg.client.render.entity.vanilla.RenderModBiped;
import net.divinerpg.client.render.item.ItemRendererFrostedChest;
import net.divinerpg.client.render.item.ItemRendererPresentBox;
import net.divinerpg.entities.iceika.EntityAlicanto;
import net.divinerpg.entities.iceika.EntityFractite;
import net.divinerpg.entities.iceika.EntityFrostArcher;
import net.divinerpg.entities.iceika.EntityFrosty;
import net.divinerpg.entities.iceika.EntityGlacide;
import net.divinerpg.entities.iceika.EntityHastreus;
import net.divinerpg.entities.iceika.EntityRollum;
import net.divinerpg.entities.iceika.EntityWorkshopMerchant;
import net.divinerpg.entities.iceika.EntityWorkshopTinkerer;
import net.divinerpg.entities.iceika.projectile.EntityCarol;
import net.divinerpg.entities.iceika.projectile.EntityFractiteShot;
import net.divinerpg.entities.iceika.projectile.EntityMusic;
import net.divinerpg.entities.iceika.projectile.EntitySerenadeOfIce;
import net.divinerpg.entities.iceika.projectile.EntitySnowflakeShuriken;
import net.divinerpg.utils.Util;
import net.divinerpg.utils.blocks.IceikaBlocks;
import net.divinerpg.utils.items.IceikaItems;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

public class IceikaEntityRenderer {
    public static void init() {
        RenderingRegistry.registerEntityRenderingHandler(EntityCarol.class, (Render)new RenderSpecialProjectile(EntityResourceLocation.music));
        RenderingRegistry.registerEntityRenderingHandler(EntityMusic.class, (Render)new RenderSpecialProjectile(EntityResourceLocation.music));
        RenderingRegistry.registerEntityRenderingHandler(EntitySnowflakeShuriken.class, (Render)new RenderIconProjectile(IceikaItems.snowflakeShuriken));
        RenderingRegistry.registerEntityRenderingHandler(EntityFractiteShot.class, (Render)new RenderSpecialProjectile(EntityResourceLocation.fractiteShot, 2.0f));
        RenderingRegistry.registerEntityRenderingHandler(EntitySerenadeOfIce.class, (Render)new RenderSpecialProjectile(EntityResourceLocation.blank));
        RenderingRegistry.registerEntityRenderingHandler(EntityAlicanto.class, (Render)new RenderDivineMob(new ModelAlicanto(), 0.0f, EntityResourceLocation.ALICANTO));
        RenderingRegistry.registerEntityRenderingHandler(EntityFractite.class, (Render)new RenderDivineMob(new ModelFractite(), 0.5f, EntityResourceLocation.FRACTITE));
        RenderingRegistry.registerEntityRenderingHandler(EntityGlacide.class, (Render)new RenderSizeable(new ModelGlacide(), 0.0f, 1.0f, EntityResourceLocation.GLACON));
        RenderingRegistry.registerEntityRenderingHandler(EntityHastreus.class, (Render)new RenderDivineMob(new ModelHastreus(), 0.0f, EntityResourceLocation.HASTERUS));
        RenderingRegistry.registerEntityRenderingHandler(EntityFrostArcher.class, (Render)new RenderModBiped((ModelBase)new ModelBiped(), EntityResourceLocation.FROZEN_ARCHER));
        RenderingRegistry.registerEntityRenderingHandler(EntityRollum.class, (Render)new RenderDivineMob(new ModelRollum(), 0.0f, EntityResourceLocation.ROLLUM));
        RenderingRegistry.registerEntityRenderingHandler(EntityFrosty.class, (Render)new RenderFrosty());
        RenderingRegistry.registerEntityRenderingHandler(EntityWorkshopTinkerer.class, (Render)new RenderDivineMob(new ModelWorkshop(), 0.0f, EntityResourceLocation.WORKSHOP_TINKER));
        RenderingRegistry.registerEntityRenderingHandler(EntityWorkshopMerchant.class, (Render)new RenderDivineMob(new ModelWorkshop(), 0.0f, EntityResourceLocation.WORKSHOP_MERCHANT));
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityFrostedChest.class, (TileEntitySpecialRenderer)new RenderFrostedChest());
        MinecraftForgeClient.registerItemRenderer((Item)Util.toItem(IceikaBlocks.frostedChest), (IItemRenderer)new ItemRendererFrostedChest());
        MinecraftForgeClient.registerItemRenderer((Item)Util.toItem(IceikaBlocks.decorativeFrostedChest), (IItemRenderer)new ItemRendererFrostedChest());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPresentBox.class, (TileEntitySpecialRenderer)new RenderPresentBox());
        MinecraftForgeClient.registerItemRenderer((Item)Util.toItem(IceikaBlocks.presentBox), (IItemRenderer)new ItemRendererPresentBox());
    }
}

