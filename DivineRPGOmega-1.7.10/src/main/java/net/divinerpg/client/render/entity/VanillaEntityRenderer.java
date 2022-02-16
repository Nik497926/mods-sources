/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.registry.ClientRegistry
 *  cpw.mods.fml.client.registry.RenderingRegistry
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.model.ModelBlaze
 *  net.minecraft.client.model.ModelPig
 *  net.minecraft.client.model.ModelSpider
 *  net.minecraft.client.model.ModelZombie
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.item.Item
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.MinecraftForgeClient
 */
package net.divinerpg.client.render.entity;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.divinerpg.blocks.vanilla.container.tileentity.TileEntityAltarOfCorruption;
import net.divinerpg.blocks.vanilla.container.tileentity.TileEntityAyeracoBeam;
import net.divinerpg.blocks.vanilla.container.tileentity.TileEntityAyeracoSpawn;
import net.divinerpg.blocks.vanilla.container.tileentity.TileEntityBoneChest;
import net.divinerpg.client.render.EntityResourceLocation;
import net.divinerpg.client.render.RenderDivineArrow;
import net.divinerpg.client.render.RenderDivineBoss;
import net.divinerpg.client.render.RenderDivineMob;
import net.divinerpg.client.render.RenderIconProjectile;
import net.divinerpg.client.render.RenderProjectile;
import net.divinerpg.client.render.RenderSizeable;
import net.divinerpg.client.render.RenderSpecialProjectile;
import net.divinerpg.client.render.block.RenderAltarOfCorruption;
import net.divinerpg.client.render.block.RenderAyeracoBeam;
import net.divinerpg.client.render.block.RenderAyeracoSpawn;
import net.divinerpg.client.render.block.RenderBoneChest;
import net.divinerpg.client.render.block.RenderStatue;
import net.divinerpg.client.render.block.TileEntityStatue;
import net.divinerpg.client.render.entity.vanilla.RenderAridWarrior;
import net.divinerpg.client.render.entity.vanilla.RenderGrizzle;
import net.divinerpg.client.render.entity.vanilla.RenderHellPig;
import net.divinerpg.client.render.entity.vanilla.RenderModBiped;
import net.divinerpg.client.render.entity.vanilla.RenderSaguaroWorm;
import net.divinerpg.client.render.entity.vanilla.RenderSaguaroWormShot;
import net.divinerpg.client.render.entity.vanilla.RenderWildfire;
import net.divinerpg.client.render.entity.vanilla.model.ModelAncientEntity;
import net.divinerpg.client.render.entity.vanilla.model.ModelAridWarrior;
import net.divinerpg.client.render.entity.vanilla.model.ModelAyeraco;
import net.divinerpg.client.render.entity.vanilla.model.ModelCrab;
import net.divinerpg.client.render.entity.vanilla.model.ModelCrawler;
import net.divinerpg.client.render.entity.vanilla.model.ModelDramcryx;
import net.divinerpg.client.render.entity.vanilla.model.ModelEhu;
import net.divinerpg.client.render.entity.vanilla.model.ModelEnderTriplets;
import net.divinerpg.client.render.entity.vanilla.model.ModelEye;
import net.divinerpg.client.render.entity.vanilla.model.ModelFrost;
import net.divinerpg.client.render.entity.vanilla.model.ModelGlacon;
import net.divinerpg.client.render.entity.vanilla.model.ModelGrue;
import net.divinerpg.client.render.entity.vanilla.model.ModelHellSpider;
import net.divinerpg.client.render.entity.vanilla.model.ModelHusk;
import net.divinerpg.client.render.entity.vanilla.model.ModelJackOMan;
import net.divinerpg.client.render.entity.vanilla.model.ModelJungleBat;
import net.divinerpg.client.render.entity.vanilla.model.ModelJungleSpider;
import net.divinerpg.client.render.entity.vanilla.model.ModelKingScorcher;
import net.divinerpg.client.render.entity.vanilla.model.ModelKobblin;
import net.divinerpg.client.render.entity.vanilla.model.ModelLiopleurodon;
import net.divinerpg.client.render.entity.vanilla.model.ModelLivestockMerchant;
import net.divinerpg.client.render.entity.vanilla.model.ModelPumpkinSpider;
import net.divinerpg.client.render.entity.vanilla.model.ModelRainbour;
import net.divinerpg.client.render.entity.vanilla.model.ModelRotatick;
import net.divinerpg.client.render.entity.vanilla.model.ModelRuneTrader;
import net.divinerpg.client.render.entity.vanilla.model.ModelScorcher;
import net.divinerpg.client.render.entity.vanilla.model.ModelShark;
import net.divinerpg.client.render.entity.vanilla.model.ModelSnapper;
import net.divinerpg.client.render.entity.vanilla.model.ModelStoneGolem;
import net.divinerpg.client.render.entity.vanilla.model.ModelWatcher;
import net.divinerpg.client.render.entity.vanilla.model.ModelWhale;
import net.divinerpg.client.render.entity.vanilla.model.ModelWildfire;
import net.divinerpg.client.render.item.ItemRendererBoneChest;
import net.divinerpg.client.render.item.RenderStatueItem;
import net.divinerpg.entities.base.EntityDivineArrow;
import net.divinerpg.entities.base.EntityStats;
import net.divinerpg.entities.twilight.EntityRuneTrader;
import net.divinerpg.entities.vanilla.EntityAncientEntity;
import net.divinerpg.entities.vanilla.EntityAridWarrior;
import net.divinerpg.entities.vanilla.EntityAyeracoBlue;
import net.divinerpg.entities.vanilla.EntityAyeracoGreen;
import net.divinerpg.entities.vanilla.EntityAyeracoPurple;
import net.divinerpg.entities.vanilla.EntityAyeracoRed;
import net.divinerpg.entities.vanilla.EntityAyeracoYellow;
import net.divinerpg.entities.vanilla.EntityCaveCrawler;
import net.divinerpg.entities.vanilla.EntityCaveclops;
import net.divinerpg.entities.vanilla.EntityCrab;
import net.divinerpg.entities.vanilla.EntityCyclops;
import net.divinerpg.entities.vanilla.EntityDesertCrawler;
import net.divinerpg.entities.vanilla.EntityEhu;
import net.divinerpg.entities.vanilla.EntityEnderBlaze;
import net.divinerpg.entities.vanilla.EntityEnderSpider;
import net.divinerpg.entities.vanilla.EntityEnderTriplets;
import net.divinerpg.entities.vanilla.EntityEnderWatcher;
import net.divinerpg.entities.vanilla.EntityEnthralledDramcryx;
import net.divinerpg.entities.vanilla.EntityFrost;
import net.divinerpg.entities.vanilla.EntityGlacon;
import net.divinerpg.entities.vanilla.EntityGrizzle;
import net.divinerpg.entities.vanilla.EntityHellPig;
import net.divinerpg.entities.vanilla.EntityHellSpider;
import net.divinerpg.entities.vanilla.EntityHusk;
import net.divinerpg.entities.vanilla.EntityJackOMan;
import net.divinerpg.entities.vanilla.EntityJungleBat;
import net.divinerpg.entities.vanilla.EntityJungleDramcryx;
import net.divinerpg.entities.vanilla.EntityJungleSpider;
import net.divinerpg.entities.vanilla.EntityKingCrab;
import net.divinerpg.entities.vanilla.EntityKingOfScorchers;
import net.divinerpg.entities.vanilla.EntityKobblin;
import net.divinerpg.entities.vanilla.EntityLiopleurodon;
import net.divinerpg.entities.vanilla.EntityLivestockMerchant;
import net.divinerpg.entities.vanilla.EntityMiner;
import net.divinerpg.entities.vanilla.EntityPumpkinSpider;
import net.divinerpg.entities.vanilla.EntityRainbour;
import net.divinerpg.entities.vanilla.EntityRotatick;
import net.divinerpg.entities.vanilla.EntitySaguaroWorm;
import net.divinerpg.entities.vanilla.EntityScorcher;
import net.divinerpg.entities.vanilla.EntityShark;
import net.divinerpg.entities.vanilla.EntitySmelter;
import net.divinerpg.entities.vanilla.EntitySnapper;
import net.divinerpg.entities.vanilla.EntityStoneGolem;
import net.divinerpg.entities.vanilla.EntityTheEye;
import net.divinerpg.entities.vanilla.EntityTheGrue;
import net.divinerpg.entities.vanilla.EntityTheWatcher;
import net.divinerpg.entities.vanilla.EntityWhale;
import net.divinerpg.entities.vanilla.EntityWildfire;
import net.divinerpg.entities.vanilla.projectile.EntityCaveRock;
import net.divinerpg.entities.vanilla.projectile.EntityCorruptedBullet;
import net.divinerpg.entities.vanilla.projectile.EntityDeath;
import net.divinerpg.entities.vanilla.projectile.EntityEnderTripletFireball;
import net.divinerpg.entities.vanilla.projectile.EntityFrostShot;
import net.divinerpg.entities.vanilla.projectile.EntityKingOfScorchersMeteor;
import net.divinerpg.entities.vanilla.projectile.EntityKingOfScorchersShot;
import net.divinerpg.entities.vanilla.projectile.EntitySaguaroWormShot;
import net.divinerpg.entities.vanilla.projectile.EntityScorcherShot;
import net.divinerpg.entities.vanilla.projectile.EntityShooterBullet;
import net.divinerpg.entities.vanilla.projectile.EntityVileStorm;
import net.divinerpg.entities.vanilla.projectile.EntityWatcherShot;
import net.divinerpg.utils.Util;
import net.divinerpg.utils.blocks.VanillaBlocks;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.divinerpg.utils.items.VanillaItemsWeapons;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelBlaze;
import net.minecraft.client.model.ModelPig;
import net.minecraft.client.model.ModelSpider;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.Item;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

public class VanillaEntityRenderer {
    private static EntityResourceLocation x;
    private static EntityStats s;

    public static void init() {
        RenderingRegistry.registerEntityRenderingHandler(EntityEnderBlaze.class, (Render)new RenderDivineMob((ModelBase)new ModelBlaze(), 0.4f, 1.0f, EntityResourceLocation.ANCIENT_BLAZE));
        RenderingRegistry.registerEntityRenderingHandler(EntityVileStorm.class, (Render)new RenderIconProjectile(VanillaItemsWeapons.vileStorm));
        RenderingRegistry.registerEntityRenderingHandler(EntityDeath.class, (Render)new RenderSpecialProjectile(EntityResourceLocation.death));
        RenderingRegistry.registerEntityRenderingHandler(EntityCaveRock.class, (Render)new RenderSpecialProjectile(EntityResourceLocation.cave));
        RenderingRegistry.registerEntityRenderingHandler(EntityWatcherShot.class, (Render)new RenderSpecialProjectile(EntityResourceLocation.watcherShot));
        RenderingRegistry.registerEntityRenderingHandler(EntityCorruptedBullet.class, (Render)new RenderIconProjectile(VanillaItemsOther.corruptedBullet));
        RenderingRegistry.registerEntityRenderingHandler(EntityEnderTripletFireball.class, (Render)new RenderSpecialProjectile(EntityResourceLocation.tripletFireball));
        RenderingRegistry.registerEntityRenderingHandler(EntityShooterBullet.class, (Render)new RenderProjectile());
        RenderingRegistry.registerEntityRenderingHandler(EntityKingOfScorchersMeteor.class, (Render)new RenderSpecialProjectile(EntityResourceLocation.kosMeteor));
        RenderingRegistry.registerEntityRenderingHandler(EntityKingOfScorchersShot.class, (Render)new RenderSpecialProjectile(EntityResourceLocation.kosShot));
        RenderingRegistry.registerEntityRenderingHandler(EntitySaguaroWormShot.class, (Render)new RenderSaguaroWormShot());
        RenderingRegistry.registerEntityRenderingHandler(EntityEnderWatcher.class, (Render)new RenderDivineMob(new ModelWatcher(), 0.0f, EntityResourceLocation.enderWatcher));
        RenderingRegistry.registerEntityRenderingHandler(EntityCrab.class, (Render)new RenderSizeable(new ModelCrab(), 0.0f, 1.0f, EntityResourceLocation.crab));
        RenderingRegistry.registerEntityRenderingHandler(EntityKingCrab.class, (Render)new RenderSizeable(new ModelCrab(), 0.0f, 2.0f, EntityResourceLocation.crab));
        RenderingRegistry.registerEntityRenderingHandler(EntityCaveCrawler.class, (Render)new RenderDivineMob(new ModelCrawler(), 0.0f, EntityResourceLocation.caveCrawler));
        RenderingRegistry.registerEntityRenderingHandler(EntityDesertCrawler.class, (Render)new RenderDivineMob(new ModelCrawler(), 0.0f, EntityResourceLocation.desertCrawler));
        RenderingRegistry.registerEntityRenderingHandler(EntityJungleDramcryx.class, (Render)new RenderDivineMob(new ModelDramcryx(), 0.0f, EntityResourceLocation.jungleDramcryx));
        RenderingRegistry.registerEntityRenderingHandler(EntityJungleSpider.class, (Render)new RenderDivineMob(new ModelJungleSpider(), 0.0f, EntityResourceLocation.jungleSpider));
        RenderingRegistry.registerEntityRenderingHandler(EntityCyclops.class, (Render)new RenderSizeable((ModelBase)new ModelBiped(), 0.0f, 2.0f, EntityResourceLocation.cyclops));
        RenderingRegistry.registerEntityRenderingHandler(EntityCaveclops.class, (Render)new RenderSizeable((ModelBase)new ModelBiped(), 0.0f, 2.0f, EntityResourceLocation.caveclops));
        RenderingRegistry.registerEntityRenderingHandler(EntityWhale.class, (Render)new RenderSizeable(new ModelWhale(), 0.0f, 5.0f, EntityResourceLocation.whale));
        RenderingRegistry.registerEntityRenderingHandler(EntityScorcher.class, (Render)new RenderSizeable(new ModelScorcher(), 0.0f, 1.8f, EntityResourceLocation.scorcher));
        RenderingRegistry.registerEntityRenderingHandler(EntityHellSpider.class, (Render)new RenderSizeable(new ModelHellSpider(), 0.6f, 1.0f, EntityResourceLocation.hellSpider));
        RenderingRegistry.registerEntityRenderingHandler(EntityEnderSpider.class, (Render)new RenderSizeable((ModelBase)new ModelSpider(), 0.0f, 0.5f, EntityResourceLocation.enderSpider));
        RenderingRegistry.registerEntityRenderingHandler(EntityEnderTriplets.class, (Render)new RenderDivineMob(new ModelEnderTriplets(), 0.0f, EntityResourceLocation.enderTriplets));
        RenderingRegistry.registerEntityRenderingHandler(EntityAridWarrior.class, (Render)new RenderAridWarrior(new ModelAridWarrior(), EntityResourceLocation.aridWarrior));
        RenderingRegistry.registerEntityRenderingHandler(EntityEnthralledDramcryx.class, (Render)new RenderSizeable(new ModelDramcryx(), 0.0f, 1.5f, EntityResourceLocation.caveDramcryx));
        RenderingRegistry.registerEntityRenderingHandler(EntityTheEye.class, (Render)new RenderDivineMob(new ModelEye(), 0.0f, EntityResourceLocation.theEye));
        RenderingRegistry.registerEntityRenderingHandler(EntityFrost.class, (Render)new RenderDivineMob(new ModelFrost(), 0.0f, EntityResourceLocation.frost));
        RenderingRegistry.registerEntityRenderingHandler(EntityRainbour.class, (Render)new RenderDivineMob(new ModelRainbour(), 0.0f, EntityResourceLocation.rainbour));
        RenderingRegistry.registerEntityRenderingHandler(EntityRotatick.class, (Render)new RenderDivineMob(new ModelRotatick(), 0.0f, EntityResourceLocation.rotatick));
        RenderingRegistry.registerEntityRenderingHandler(EntityShark.class, (Render)new RenderDivineMob(new ModelShark(), 0.0f, EntityResourceLocation.shark));
        RenderingRegistry.registerEntityRenderingHandler(EntityWildfire.class, (Render)new RenderWildfire(new ModelWildfire(), EntityResourceLocation.wildFire));
        RenderingRegistry.registerEntityRenderingHandler(EntityGlacon.class, (Render)new RenderDivineMob(new ModelGlacon(), 0.0f, EntityResourceLocation.glacon));
        RenderingRegistry.registerEntityRenderingHandler(EntityMiner.class, (Render)new RenderModBiped((ModelBase)new ModelZombie(), EntityResourceLocation.miner));
        RenderingRegistry.registerEntityRenderingHandler(EntityJackOMan.class, (Render)new RenderModBiped((ModelBase)new ModelJackOMan(), EntityResourceLocation.jackOMan));
        RenderingRegistry.registerEntityRenderingHandler(EntityRuneTrader.class, (Render)new RenderDivineMob(new ModelRuneTrader(), 0.5f, EntityResourceLocation.gemTrader));
        RenderingRegistry.registerEntityRenderingHandler(EntityLivestockMerchant.class, (Render)new RenderDivineMob(new ModelLivestockMerchant(), 0.5f, EntityResourceLocation.livestockMerchant));
        RenderingRegistry.registerEntityRenderingHandler(EntityHellPig.class, (Render)new RenderHellPig((ModelBase)new ModelPig()));
        RenderingRegistry.registerEntityRenderingHandler(EntityJungleBat.class, (Render)new RenderSizeable(new ModelJungleBat(), 0.0f, 0.5f, EntityResourceLocation.jungleBat));
        RenderingRegistry.registerEntityRenderingHandler(EntityKobblin.class, (Render)new RenderDivineMob(new ModelKobblin(), 0.0f, EntityResourceLocation.kobblin));
        RenderingRegistry.registerEntityRenderingHandler(EntityTheGrue.class, (Render)new RenderDivineMob(new ModelGrue(), 0.0f, EntityResourceLocation.theGrue));
        RenderingRegistry.registerEntityRenderingHandler(EntitySaguaroWorm.class, (Render)new RenderSaguaroWorm());
        RenderingRegistry.registerEntityRenderingHandler(EntityPumpkinSpider.class, (Render)new RenderDivineMob(new ModelPumpkinSpider(), 0.0f, EntityResourceLocation.pumpkinSpider));
        RenderingRegistry.registerEntityRenderingHandler(EntityLiopleurodon.class, (Render)new RenderDivineMob(new ModelLiopleurodon(), 0.0f, EntityResourceLocation.liopleurodon));
        RenderingRegistry.registerEntityRenderingHandler(EntityEhu.class, (Render)new RenderDivineMob(new ModelEhu(), 0.0f, EntityResourceLocation.ehu));
        RenderingRegistry.registerEntityRenderingHandler(EntityHusk.class, (Render)new RenderDivineMob(new ModelHusk(), 0.0f, EntityResourceLocation.husk));
        RenderingRegistry.registerEntityRenderingHandler(EntityStoneGolem.class, (Render)new RenderDivineMob(new ModelStoneGolem(), 0.0f, EntityResourceLocation.stoneGolem));
        RenderingRegistry.registerEntityRenderingHandler(EntitySmelter.class, (Render)new RenderDivineMob(new ModelStoneGolem(), 0.0f, EntityResourceLocation.smelter));
        RenderingRegistry.registerEntityRenderingHandler(EntityGrizzle.class, (Render)new RenderGrizzle());
        RenderingRegistry.registerEntityRenderingHandler(EntitySnapper.class, (Render)new RenderDivineMob(new ModelSnapper(), 0.0f, EntityResourceLocation.snapper));
        RenderingRegistry.registerEntityRenderingHandler(EntityKingOfScorchers.class, (Render)new RenderDivineBoss(new ModelKingScorcher(), 0.0f, EntityResourceLocation.kingOfScorchers, 7));
        RenderingRegistry.registerEntityRenderingHandler(EntityTheWatcher.class, (Render)new RenderDivineBoss(new ModelWatcher(), 0.0f, 4.5f, EntityResourceLocation.netherWatcher, 8));
        RenderingRegistry.registerEntityRenderingHandler(EntityAyeracoBlue.class, (Render)new RenderDivineBoss(new ModelAyeraco(), 0.0f, 2.0f, EntityResourceLocation.ayeraco_Blue, 3));
        RenderingRegistry.registerEntityRenderingHandler(EntityAyeracoGreen.class, (Render)new RenderDivineBoss(new ModelAyeraco(), 0.0f, 2.0f, EntityResourceLocation.ayeraco_Green, 2));
        RenderingRegistry.registerEntityRenderingHandler(EntityAyeracoPurple.class, (Render)new RenderDivineBoss(new ModelAyeraco(), 0.0f, 2.0f, EntityResourceLocation.ayeraco_Purple, 4));
        RenderingRegistry.registerEntityRenderingHandler(EntityAyeracoRed.class, (Render)new RenderDivineBoss(new ModelAyeraco(), 0.0f, 2.0f, EntityResourceLocation.ayeraco_Red, 5));
        RenderingRegistry.registerEntityRenderingHandler(EntityAyeracoYellow.class, (Render)new RenderDivineBoss(new ModelAyeraco(), 0.0f, 2.0f, EntityResourceLocation.ayeraco_Yellow, 6));
        RenderingRegistry.registerEntityRenderingHandler(EntityAncientEntity.class, (Render)new RenderDivineBoss(new ModelAncientEntity(), 0.0f, 5.0f, EntityResourceLocation.ancient, 1));
        RenderingRegistry.registerEntityRenderingHandler(EntityFrostShot.class, (Render)new RenderSpecialProjectile(EntityResourceLocation.frostShot, 0.75f));
        RenderingRegistry.registerEntityRenderingHandler(EntityScorcherShot.class, (Render)new RenderSpecialProjectile(EntityResourceLocation.scorcherShot, 0.5f));
        RenderingRegistry.registerEntityRenderingHandler(EntityDivineArrow.class, (Render)new RenderDivineArrow());
        Util.registerItemRenderer(VanillaBlocks.kosStatue, (IItemRenderer)new RenderStatueItem(VanillaBlocks.kosStatue));
        Util.registerItemRenderer(VanillaBlocks.ancientEntityStatue, (IItemRenderer)new RenderStatueItem(VanillaBlocks.ancientEntityStatue));
        Util.registerItemRenderer(VanillaBlocks.ayeracoStatue, (IItemRenderer)new RenderStatueItem(VanillaBlocks.ayeracoStatue));
        Util.registerItemRenderer(VanillaBlocks.reyvorStatue, (IItemRenderer)new RenderStatueItem(VanillaBlocks.reyvorStatue));
        Util.registerItemRenderer(VanillaBlocks.twilightDemonStatue, (IItemRenderer)new RenderStatueItem(VanillaBlocks.twilightDemonStatue));
        Util.registerItemRenderer(VanillaBlocks.vamacheronStatue, (IItemRenderer)new RenderStatueItem(VanillaBlocks.vamacheronStatue));
        Util.registerItemRenderer(VanillaBlocks.densosStatue, (IItemRenderer)new RenderStatueItem(VanillaBlocks.densosStatue));
        Util.registerItemRenderer(VanillaBlocks.dramixStatue, (IItemRenderer)new RenderStatueItem(VanillaBlocks.dramixStatue));
        Util.registerItemRenderer(VanillaBlocks.soulFiendStatue, (IItemRenderer)new RenderStatueItem(VanillaBlocks.soulFiendStatue));
        Util.registerItemRenderer(VanillaBlocks.watcherStatue, (IItemRenderer)new RenderStatueItem(VanillaBlocks.watcherStatue));
        Util.registerItemRenderer(VanillaBlocks.eternalArcherStatue, (IItemRenderer)new RenderStatueItem(VanillaBlocks.eternalArcherStatue));
        Util.registerItemRenderer(VanillaBlocks.karotStatue, (IItemRenderer)new RenderStatueItem(VanillaBlocks.karotStatue));
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityStatue.class, (TileEntitySpecialRenderer)new RenderStatue());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAyeracoBeam.class, (TileEntitySpecialRenderer)new RenderAyeracoBeam());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAyeracoSpawn.class, (TileEntitySpecialRenderer)new RenderAyeracoSpawn());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAltarOfCorruption.class, (TileEntitySpecialRenderer)new RenderAltarOfCorruption());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBoneChest.class, (TileEntitySpecialRenderer)new RenderBoneChest());
        MinecraftForgeClient.registerItemRenderer((Item)Util.toItem(VanillaBlocks.boneChest), (IItemRenderer)new ItemRendererBoneChest());
    }
}

