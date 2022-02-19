package thaumcraft.client;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.VillagerRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.model.ModelChicken;
import net.minecraft.client.model.ModelCow;
import net.minecraft.client.model.ModelPig;
import net.minecraft.client.model.ModelSlime;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.particle.EntityDiggingFX;
import net.minecraft.client.particle.EntityLavaFX;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.client.fx.ParticleEngine;
import thaumcraft.client.fx.beams.*;
import thaumcraft.client.fx.bolt.FXLightningBolt;
import thaumcraft.client.fx.other.FXBlockWard;
import thaumcraft.client.fx.particles.*;
import thaumcraft.client.gui.*;
import thaumcraft.client.lib.ClientTickEventsFML;
import thaumcraft.client.renderers.block.*;
import thaumcraft.client.renderers.entity.*;
import thaumcraft.client.renderers.item.*;
import thaumcraft.client.renderers.models.entities.*;
import thaumcraft.client.renderers.tile.*;
import thaumcraft.common.CommonProxy;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigEntities;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.entities.*;
import thaumcraft.common.entities.golems.EntityGolemBase;
import thaumcraft.common.entities.golems.EntityGolemBobber;
import thaumcraft.common.entities.golems.EntityTravelingTrunk;
import thaumcraft.common.entities.monster.*;
import thaumcraft.common.entities.monster.boss.*;
import thaumcraft.common.entities.projectile.*;
import thaumcraft.common.items.wands.WandManager;
import thaumcraft.common.lib.events.KeyHandler;
import thaumcraft.common.lib.research.PlayerKnowledge;
import thaumcraft.common.lib.research.ResearchManager;
import thaumcraft.common.tiles.*;

import java.awt.*;
import java.util.HashMap;

public class ClientProxy extends CommonProxy
{
	protected PlayerKnowledge playerResearch = new PlayerKnowledge();
	protected ResearchManager researchManager = new ResearchManager();
	public WandManager wandManager = new WandManager();
	private HashMap<String, IIcon> customIcons = new HashMap();

	@Override
	public void registerHandlers()
	{
		FMLCommonHandler.instance().bus().register(new ClientTickEventsFML());
		MinecraftForge.EVENT_BUS.register(Thaumcraft.instance.renderEventHandler);
		MinecraftForge.EVENT_BUS.register(ConfigBlocks.blockTube);
		MinecraftForge.EVENT_BUS.register(ParticleEngine.instance);
		FMLCommonHandler.instance().bus().register(ParticleEngine.instance);
	}

	@Override
	public void registerKeyBindings()
	{
		FMLCommonHandler.instance().bus().register(new KeyHandler());
	}

	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
	{
		if (world instanceof WorldClient)
		{
			if (id == 0)
				return new GuiGolem(player, (EntityGolemBase) world.getEntityByID(x));
			if (id == 1)
				return new GuiPech(player.inventory, world, (EntityPech) world.getEntityByID(x));
			if (id == 2)
				return new GuiTravelingTrunk(player, (EntityTravelingTrunk) world.getEntityByID(x));
			if (id == 5)
				return new GuiFocusPouch(player.inventory, world, x, y, z);
			if (id == 12)
				return new GuiResearchBrowser();
			if (id == 16)
				return new GuiHandMirror(player.inventory, world, x, y, z);
			if (id == 17)
				return new GuiHoverHarness(player.inventory, world, x, y, z);

			TileEntity tile = world.getTileEntity(x, y, z);

			// TODO gamerforEA code start
			if (tile == null)
				return null;
			// TODO gamerforEA code end

			if (id == 3)
				return new GuiThaumatorium(player.inventory, (TileThaumatorium) tile);
			if (id == 8)
				return new GuiDeconstructionTable(player.inventory, (TileDeconstructionTable) tile);
			if (id == 9)
				return new GuiAlchemyFurnace(player.inventory, (TileAlchemyFurnace) tile);
			if (id == 10)
				return new GuiResearchTable(player, (TileResearchTable) tile);
			if (id == 13)
				return new GuiArcaneWorkbench(player.inventory, (TileArcaneWorkbench) tile);
			if (id == 15)
				return new GuiArcaneBore(player.inventory, (TileArcaneBore) tile);
			if (id == 18)
				return new GuiMagicBox(player.inventory, (TileMagicBox) tile);
			if (id == 19)
				return new GuiSpa(player.inventory, (TileSpa) tile);
			if (id == 20)
				return new GuiFocalManipulator(player.inventory, (TileFocalManipulator) tile);

		}

		return null;
	}

	@Override
	public void registerDisplayInformation()
	{
		Thaumcraft.instance.aspectShift = FMLClientHandler.instance().hasOptifine();
		if (Loader.isModLoaded("NotEnoughItems"))
			Thaumcraft.instance.aspectShift = true;

		this.setupItemRenderers();
		this.setupEntityRenderers();
		this.setupBlockRenderers();
		this.setupTileRenderers();
	}

	private void setupItemRenderers()
	{
		MinecraftForgeClient.registerItemRenderer(ConfigItems.itemJarFilled, new ItemJarFilledRenderer());
		MinecraftForgeClient.registerItemRenderer(ConfigItems.itemJarNode, new ItemJarNodeRenderer());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ConfigBlocks.blockAiry), new ItemNodeRenderer());
		MinecraftForgeClient.registerItemRenderer(ConfigItems.itemThaumometer, new ItemThaumometerRenderer());
		MinecraftForgeClient.registerItemRenderer(ConfigItems.itemWandCasting, new ItemWandRenderer());
		MinecraftForgeClient.registerItemRenderer(ConfigItems.itemTrunkSpawner, new ItemTrunkSpawnerRenderer());
		MinecraftForgeClient.registerItemRenderer(ConfigItems.itemBowBone, new ItemBowBoneRenderer());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ConfigBlocks.blockWoodenDevice), new ItemBannerRenderer());
	}

	private void setupEntityRenderers()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityItemGrate.class, new RenderItem());
		RenderingRegistry.registerEntityRenderingHandler(EntitySpecialItem.class, new RenderSpecialItem());
		RenderingRegistry.registerEntityRenderingHandler(EntityFollowingItem.class, new RenderFollowingItem());
		RenderingRegistry.registerEntityRenderingHandler(EntityPermanentItem.class, new RenderSpecialItem());
		RenderingRegistry.registerEntityRenderingHandler(EntityAspectOrb.class, new RenderAspectOrb());
		RenderingRegistry.registerEntityRenderingHandler(EntityGolemBobber.class, new RenderGolemBobber());
		RenderingRegistry.registerEntityRenderingHandler(EntityGolemBase.class, new RenderGolemBase(new ModelGolem(false)));
		RenderingRegistry.registerEntityRenderingHandler(EntityWisp.class, new RenderWisp());
		RenderingRegistry.registerEntityRenderingHandler(EntityAlumentum.class, new RenderAlumentum());
		RenderingRegistry.registerEntityRenderingHandler(EntityPrimalOrb.class, new RenderPrimalOrb());
		RenderingRegistry.registerEntityRenderingHandler(EntityEldritchOrb.class, new RenderEldritchOrb());
		RenderingRegistry.registerEntityRenderingHandler(EntityGolemOrb.class, new RenderElectricOrb());
		RenderingRegistry.registerEntityRenderingHandler(EntityEmber.class, new RenderEmber());
		RenderingRegistry.registerEntityRenderingHandler(EntityShockOrb.class, new RenderElectricOrb());
		RenderingRegistry.registerEntityRenderingHandler(EntityExplosiveOrb.class, new RenderExplosiveOrb());
		RenderingRegistry.registerEntityRenderingHandler(EntityPechBlast.class, new RenderPechBlast());
		RenderingRegistry.registerEntityRenderingHandler(EntityBrainyZombie.class, new RenderBrainyZombie());
		RenderingRegistry.registerEntityRenderingHandler(EntityInhabitedZombie.class, new RenderInhabitedZombie());
		RenderingRegistry.registerEntityRenderingHandler(EntityGiantBrainyZombie.class, new RenderBrainyZombie());
		RenderingRegistry.registerEntityRenderingHandler(EntityPech.class, new RenderPech(new ModelPech(), 0.25F));
		RenderingRegistry.registerEntityRenderingHandler(EntityFireBat.class, new RenderFireBat());
		RenderingRegistry.registerEntityRenderingHandler(EntityFrostShard.class, new RenderFrostShard());
		RenderingRegistry.registerEntityRenderingHandler(EntityDart.class, new RenderDart());
		RenderingRegistry.registerEntityRenderingHandler(EntityPrimalArrow.class, new RenderPrimalArrow());
		RenderingRegistry.registerEntityRenderingHandler(EntityFallingTaint.class, new RenderFallingTaint());
		RenderingRegistry.registerEntityRenderingHandler(EntityThaumicSlime.class, new RenderThaumicSlime(new ModelSlime(16), new ModelSlime(0), 0.25F));
		RenderingRegistry.registerEntityRenderingHandler(EntityTaintSpider.class, new RenderTaintSpider());
		RenderingRegistry.registerEntityRenderingHandler(EntityTaintacle.class, new RenderTaintacle(0.6F, 10));
		RenderingRegistry.registerEntityRenderingHandler(EntityTaintacleSmall.class, new RenderTaintacle(0.2F, 6));
		RenderingRegistry.registerEntityRenderingHandler(EntityTaintacleGiant.class, new RenderTaintacle(1.0F, 14));
		RenderingRegistry.registerEntityRenderingHandler(EntityTaintSpore.class, new RenderTaintSpore());
		RenderingRegistry.registerEntityRenderingHandler(EntityTaintSporeSwarmer.class, new RenderTaintSporeSwarmer());
		RenderingRegistry.registerEntityRenderingHandler(EntityTaintSwarm.class, new RenderTaintSwarm());
		RenderingRegistry.registerEntityRenderingHandler(EntityTaintChicken.class, new RenderTaintChicken(new ModelChicken(), 0.3F));
		RenderingRegistry.registerEntityRenderingHandler(EntityTaintCow.class, new RenderTaintCow(new ModelCow(), 0.7F));
		RenderingRegistry.registerEntityRenderingHandler(EntityTaintCreeper.class, new RenderTaintCreeper());
		RenderingRegistry.registerEntityRenderingHandler(EntityTaintPig.class, new RenderTaintPig(new ModelPig(), 0.7F));
		RenderingRegistry.registerEntityRenderingHandler(EntityTaintSheep.class, new RenderTaintSheep(new ModelTaintSheep2(), new ModelTaintSheep1(), 0.7F));
		RenderingRegistry.registerEntityRenderingHandler(EntityTaintVillager.class, new RenderTaintVillager());
		RenderingRegistry.registerEntityRenderingHandler(EntityTravelingTrunk.class, new RenderTravelingTrunk(new ModelTrunk(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityMindSpider.class, new RenderMindSpider());
		RenderingRegistry.registerEntityRenderingHandler(EntityEldritchGuardian.class, new RenderEldritchGuardian(new ModelEldritchGuardian(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityEldritchWarden.class, new RenderEldritchGuardian(new ModelEldritchGuardian(), 0.6F));
		RenderingRegistry.registerEntityRenderingHandler(EntityCultistPortal.class, new RenderCultistPortal());
		RenderingRegistry.registerEntityRenderingHandler(EntityCultistKnight.class, new RenderCultist());
		RenderingRegistry.registerEntityRenderingHandler(EntityCultistLeader.class, new RenderCultist());
		RenderingRegistry.registerEntityRenderingHandler(EntityCultistCleric.class, new RenderCultist());
		RenderingRegistry.registerEntityRenderingHandler(EntityEldritchGolem.class, new RenderEldritchGolem(new ModelEldritchGolem(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityBottleTaint.class, new RenderSnowball(ConfigItems.itemBottleTaint, 0));
		RenderingRegistry.registerEntityRenderingHandler(EntityEldritchCrab.class, new RenderEldritchCrab());
		VillagerRegistry.instance().registerVillagerSkin(ConfigEntities.entWizardId, new ResourceLocation("thaumcraft", "textures/models/wizard.png"));
		VillagerRegistry.instance().registerVillagerSkin(ConfigEntities.entBankerId, new ResourceLocation("thaumcraft", "textures/models/moneychanger.png"));
	}

	void setupTileRenderers()
	{
		this.registerTileEntitySpecialRenderer(TileAlembic.class, new TileAlembicRenderer());
		this.registerTileEntitySpecialRenderer(TileArcaneBore.class, new TileArcaneBoreRenderer());
		this.registerTileEntitySpecialRenderer(TileArcaneBoreBase.class, new TileArcaneBoreBaseRenderer());
		this.registerTileEntitySpecialRenderer(TileArcaneLamp.class, new TileArcaneLampRenderer());
		this.registerTileEntitySpecialRenderer(TileArcaneLampGrowth.class, new TileArcaneLampRenderer());
		this.registerTileEntitySpecialRenderer(TileArcaneLampFertility.class, new TileArcaneLampRenderer());
		this.registerTileEntitySpecialRenderer(TileArcaneWorkbench.class, new TileArcaneWorkbenchRenderer());
		this.registerTileEntitySpecialRenderer(TileBanner.class, new TileBannerRenderer());
		this.registerTileEntitySpecialRenderer(TileBellows.class, new TileBellowsRenderer());
		this.registerTileEntitySpecialRenderer(TileCentrifuge.class, new TileCentrifugeRenderer());
		this.registerTileEntitySpecialRenderer(TileChestHungry.class, new TileChestHungryRenderer());
		this.registerTileEntitySpecialRenderer(TileCrucible.class, new TileCrucibleRenderer());
		this.registerTileEntitySpecialRenderer(TileCrystal.class, new TileCrystalRenderer());
		this.registerTileEntitySpecialRenderer(TileEldritchCrystal.class, new TileEldritchCrystalRenderer());
		this.registerTileEntitySpecialRenderer(TileDeconstructionTable.class, new TileDeconstructionTableRenderer());
		this.registerTileEntitySpecialRenderer(TileEldritchAltar.class, new TileEldritchCapRenderer("textures/models/obelisk_cap_altar.png"));
		this.registerTileEntitySpecialRenderer(TileEldritchCap.class, new TileEldritchCapRenderer("textures/models/obelisk_cap.png"));
		this.registerTileEntitySpecialRenderer(TileEldritchCrabSpawner.class, new TileEldritchCrabSpawnerRenderer());
		this.registerTileEntitySpecialRenderer(TileEldritchNothing.class, new TileEldritchNothingRenderer());
		this.registerTileEntitySpecialRenderer(TileEldritchObelisk.class, new TileEldritchObeliskRenderer());
		this.registerTileEntitySpecialRenderer(TileEldritchPortal.class, new TileEldritchPortalRenderer());
		this.registerTileEntitySpecialRenderer(TileEldritchLock.class, new TileEldritchLockRenderer());
		this.registerTileEntitySpecialRenderer(TileEssentiaCrystalizer.class, new TileEssentiaCrystalizerRenderer());
		this.registerTileEntitySpecialRenderer(TileEssentiaReservoir.class, new TileEssentiaReservoirRenderer());
		this.registerTileEntitySpecialRenderer(TileEtherealBloom.class, new TileEtherealBloomRenderer());
		this.registerTileEntitySpecialRenderer(TileHole.class, new TileHoleRenderer());
		this.registerTileEntitySpecialRenderer(TileInfusionMatrix.class, new TileRunicMatrixRenderer(0));
		this.registerTileEntitySpecialRenderer(TileInfusionPillar.class, new TileInfusionPillarRenderer());
		this.registerTileEntitySpecialRenderer(TileJar.class, new TileJarRenderer());
		this.registerTileEntitySpecialRenderer(TileMagicWorkbenchCharger.class, new TileMagicWorkbenchChargerRenderer());
		this.registerTileEntitySpecialRenderer(TileManaPod.class, new TileManaPodRenderer());
		TileMirrorRenderer tmr = new TileMirrorRenderer();
		this.registerTileEntitySpecialRenderer(TileMirror.class, tmr);
		this.registerTileEntitySpecialRenderer(TileMirrorEssentia.class, tmr);
		this.registerTileEntitySpecialRenderer(TileNode.class, new TileNodeRenderer());
		this.registerTileEntitySpecialRenderer(TileNodeEnergized.class, new TileNodeEnergizedRenderer());
		this.registerTileEntitySpecialRenderer(TileNodeConverter.class, new TileNodeConverterRenderer());
		this.registerTileEntitySpecialRenderer(TileNodeStabilizer.class, new TileNodeStabilizerRenderer());
		this.registerTileEntitySpecialRenderer(TilePedestal.class, new TilePedestalRenderer());
		this.registerTileEntitySpecialRenderer(TileResearchTable.class, new TileResearchTableRenderer());
		this.registerTileEntitySpecialRenderer(TileTable.class, new TileTableRenderer());
		this.registerTileEntitySpecialRenderer(TileThaumatorium.class, new TileThaumatoriumRenderer());
		this.registerTileEntitySpecialRenderer(TileTubeBuffer.class, new TileTubeBufferRenderer());
		this.registerTileEntitySpecialRenderer(TileTubeOneway.class, new TileTubeOnewayRenderer());
		this.registerTileEntitySpecialRenderer(TileTubeValve.class, new TileTubeValveRenderer());
		this.registerTileEntitySpecialRenderer(TileVisRelay.class, new TileVisRelayRenderer());
		this.registerTileEntitySpecialRenderer(TileWandPedestal.class, new TileWandPedestalRenderer());
		this.registerTileEntitySpecialRenderer(TileWarded.class, new TileWardedRenderer());
		this.registerTileEntitySpecialRenderer(TileFocalManipulator.class, new TileFocalManipulatorRenderer());
		this.registerTileEntitySpecialRenderer(TileAlchemyFurnaceAdvanced.class, new TileAlchemyFurnaceAdvancedRenderer());
		this.registerTileEntitySpecialRenderer(TileFluxScrubber.class, new TileFluxScrubberRenderer());
	}

	void setupBlockRenderers()
	{
		ConfigBlocks.blockFluxGasRI = RenderingRegistry.getNextAvailableRenderId();
		this.registerBlockRenderer(new BlockGasRenderer());
		ConfigBlocks.blockArcaneFurnaceRI = RenderingRegistry.getNextAvailableRenderId();
		this.registerBlockRenderer(new BlockArcaneFurnaceRenderer());
		ConfigBlocks.blockMetalDeviceRI = RenderingRegistry.getNextAvailableRenderId();
		this.registerBlockRenderer(new BlockMetalDeviceRenderer());
		ConfigBlocks.blockStoneDeviceRI = RenderingRegistry.getNextAvailableRenderId();
		this.registerBlockRenderer(new BlockStoneDeviceRenderer());
		ConfigBlocks.blockTaintRI = RenderingRegistry.getNextAvailableRenderId();
		this.registerBlockRenderer(new BlockTaintRenderer());
		ConfigBlocks.blockCosmeticOpaqueRI = RenderingRegistry.getNextAvailableRenderId();
		this.registerBlockRenderer(new BlockCosmeticOpaqueRenderer());
		ConfigBlocks.blockTubeRI = RenderingRegistry.getNextAvailableRenderId();
		this.registerBlockRenderer(new BlockTubeRenderer());
		ConfigBlocks.blockTaintFibreRI = RenderingRegistry.getNextAvailableRenderId();
		this.registerBlockRenderer(new BlockTaintFibreRenderer());
		ConfigBlocks.blockJarRI = RenderingRegistry.getNextAvailableRenderId();
		this.registerBlockRenderer(new BlockJarRenderer());
		ConfigBlocks.blockCustomOreRI = RenderingRegistry.getNextAvailableRenderId();
		this.registerBlockRenderer(new BlockCustomOreRenderer());
		ConfigBlocks.blockChestHungryRI = RenderingRegistry.getNextAvailableRenderId();
		this.registerBlockRenderer(new BlockChestHungryRenderer());
		ConfigBlocks.blockTableRI = RenderingRegistry.getNextAvailableRenderId();
		this.registerBlockRenderer(new BlockTableRenderer());
		ConfigBlocks.blockCandleRI = RenderingRegistry.getNextAvailableRenderId();
		this.registerBlockRenderer(new BlockCandleRenderer());
		ConfigBlocks.blockWoodenDeviceRI = RenderingRegistry.getNextAvailableRenderId();
		this.registerBlockRenderer(new BlockWoodenDeviceRenderer());
		ConfigBlocks.blockLifterRI = RenderingRegistry.getNextAvailableRenderId();
		this.registerBlockRenderer(new BlockLifterRenderer());
		ConfigBlocks.blockCrystalRI = RenderingRegistry.getNextAvailableRenderId();
		this.registerBlockRenderer(new BlockCrystalRenderer());
		ConfigBlocks.blockWardedRI = RenderingRegistry.getNextAvailableRenderId();
		this.registerBlockRenderer(new BlockWardedRenderer());
		ConfigBlocks.blockEldritchRI = RenderingRegistry.getNextAvailableRenderId();
		this.registerBlockRenderer(new BlockEldritchRenderer());
		ConfigBlocks.blockEssentiaReservoirRI = RenderingRegistry.getNextAvailableRenderId();
		this.registerBlockRenderer(new BlockEssentiaReservoirRenderer());
		ConfigBlocks.blockLootUrnRI = RenderingRegistry.getNextAvailableRenderId();
		this.registerBlockRenderer(new BlockLootUrnRenderer());
		ConfigBlocks.blockLootCrateRI = RenderingRegistry.getNextAvailableRenderId();
		this.registerBlockRenderer(new BlockLootCrateRenderer());
	}

	public void registerTileEntitySpecialRenderer(Class tile, TileEntitySpecialRenderer renderer)
	{
		ClientRegistry.bindTileEntitySpecialRenderer(tile, renderer);
	}

	public void registerBlockRenderer(ISimpleBlockRenderingHandler renderer)
	{
		RenderingRegistry.registerBlockHandler(renderer);
	}

	@Override
	public World getClientWorld()
	{
		return FMLClientHandler.instance().getClient().theWorld;
	}

	@Override
	public void blockSparkle(World world, int x, int y, int z, int c, int count)
	{
		Color color = new Color(c);
		float r = (float) color.getRed() / 255.0F;
		float g = (float) color.getGreen() / 255.0F;
		float b = (float) color.getBlue() / 255.0F;

		for (int a = 0; a < Thaumcraft.proxy.particleCount(count); ++a)
		{
			if (c == -9999)
			{
				r = 0.33F + world.rand.nextFloat() * 0.67F;
				g = 0.33F + world.rand.nextFloat() * 0.67F;
				b = 0.33F + world.rand.nextFloat() * 0.67F;
			}

			Thaumcraft.proxy.drawGenericParticles(world, (double) ((float) x - 0.1F + world.rand.nextFloat() * 1.2F), (double) ((float) y - 0.1F + world.rand.nextFloat() * 1.2F), (double) ((float) z - 0.1F + world.rand.nextFloat() * 1.2F), 0.0D, (double) world.rand.nextFloat() * 0.02D, 0.0D, r - 0.2F + world.rand.nextFloat() * 0.4F, g - 0.2F + world.rand.nextFloat() * 0.4F, b - 0.2F + world.rand.nextFloat() * 0.4F, 0.9F, false, 112, 9, 1, 5 + world.rand.nextInt(8), world.rand.nextInt(10), 0.7F + world.rand.nextFloat() * 0.4F);
		}

	}

	@Override
	public void sparkle(float x, float y, float z, float size, int color, float gravity)
	{
		if (this.getClientWorld() != null && this.getClientWorld().rand.nextInt(6) < this.particleCount(2))
		{
			FXSparkle fx = new FXSparkle(this.getClientWorld(), (double) x, (double) y, (double) z, size, color, 6);
			fx.noClip = true;
			fx.setGravity(gravity);
			ParticleEngine.instance.addEffect(this.getClientWorld(), fx);
		}

	}

	@Override
	public void sparkle(float x, float y, float z, int color)
	{
		if (this.getClientWorld() != null && this.getClientWorld().rand.nextInt(6) < this.particleCount(2))
		{
			FXSparkle fx = new FXSparkle(this.getClientWorld(), (double) x, (double) y, (double) z, 1.5F, color, 6);
			fx.noClip = true;
			ParticleEngine.instance.addEffect(this.getClientWorld(), fx);
		}

	}

	@Override
	public void spark(float x, float y, float z, float size, float r, float g, float b, float a)
	{
		if (this.getClientWorld() != null)
		{
			FXSpark fx = new FXSpark(this.getClientWorld(), (double) x, (double) y, (double) z, size);
			fx.setRBGColorF(r, g, b);
			fx.setAlphaF(a);
			ParticleEngine.instance.addEffect(this.getClientWorld(), fx);
		}

	}

	@Override
	public void smokeSpiral(World world, double x, double y, double z, float rad, int start, int miny, int color)
	{
		FXSmokeSpiral fx = new FXSmokeSpiral(this.getClientWorld(), x, y, z, rad, start, miny);
		Color c = new Color(color);
		fx.setRBGColorF((float) c.getRed() / 255.0F, (float) c.getGreen() / 255.0F, (float) c.getBlue() / 255.0F);
		ParticleEngine.instance.addEffect(world, fx);
	}

	@Override
	public void crucibleBoilSound(World world, int xCoord, int yCoord, int zCoord)
	{
		world.playSound((double) ((float) xCoord + 0.5F), (double) ((float) yCoord + 0.5F), (double) ((float) zCoord + 0.5F), "thaumcraft:spill", 0.2F, 1.0F, false);
	}

	@Override
	public void crucibleBoil(World world, int xCoord, int yCoord, int zCoord, TileCrucible tile, int j)
	{
		for (int a = 0; a < this.particleCount(1); ++a)
		{
			FXBubble fb = new FXBubble(world, (double) ((float) xCoord + 0.2F + world.rand.nextFloat() * 0.6F), (double) ((float) yCoord + 0.1F + tile.getFluidHeight()), (double) ((float) zCoord + 0.2F + world.rand.nextFloat() * 0.6F), 0.0D, 0.0D, 0.0D, 3);
			if (tile.aspects.size() == 0)
				fb.setRBGColorF(1.0F, 1.0F, 1.0F);
			else
			{
				Color color = new Color(tile.aspects.getAspects()[world.rand.nextInt(tile.aspects.getAspects().length)].getColor());
				fb.setRBGColorF((float) color.getRed() / 255.0F, (float) color.getGreen() / 255.0F, (float) color.getBlue() / 255.0F);
			}

			fb.bubblespeed = 0.003D * (double) j;
			ParticleEngine.instance.addEffect(world, fb);
		}

	}

	@Override
	public void crucibleBubble(World world, float x, float y, float z, float cr, float cg, float cb)
	{
		FXBubble fb = new FXBubble(world, (double) x, (double) y, (double) z, 0.0D, 0.0D, 0.0D, 1);
		fb.setRBGColorF(cr, cg, cb);
		ParticleEngine.instance.addEffect(world, fb);
	}

	@Override
	public void crucibleFroth(World world, float x, float y, float z)
	{
		FXBubble fb = new FXBubble(world, (double) x, (double) y, (double) z, 0.0D, 0.0D, 0.0D, -4);
		fb.setRBGColorF(0.5F, 0.5F, 0.7F);
		fb.setFroth();
		ParticleEngine.instance.addEffect(world, fb);
	}

	@Override
	public void crucibleFrothDown(World world, float x, float y, float z)
	{
		FXBubble fb = new FXBubble(world, (double) x, (double) y, (double) z, 0.0D, 0.0D, 0.0D, -4);
		fb.setRBGColorF(0.5F, 0.5F, 0.7F);
		fb.setFroth2();
		ParticleEngine.instance.addEffect(world, fb);
	}

	@Override
	public void wispFX(World worldObj, double posX, double posY, double posZ, float f, float g, float h, float i)
	{
		FXWisp ef = new FXWisp(worldObj, posX, posY, posZ, f, g, h, i);
		ef.setGravity(0.02F);
		ParticleEngine.instance.addEffect(worldObj, ef);
	}

	@Override
	public void wispFX2(World worldObj, double posX, double posY, double posZ, float size, int type, boolean shrink, boolean clip, float gravity)
	{
		FXWisp ef = new FXWisp(worldObj, posX, posY, posZ, size, type);
		ef.setGravity(gravity);
		ef.shrink = shrink;
		ef.noClip = clip;
		ParticleEngine.instance.addEffect(worldObj, ef);
	}

	@Override
	public void wispFXEG(World worldObj, double posX, double posY, double posZ, Entity target)
	{
		for (int a = 0; a < this.particleCount(1); ++a)
		{
			FXWispEG ef = new FXWispEG(worldObj, posX, posY, posZ, target);
			ParticleEngine.instance.addEffect(worldObj, ef);
		}

	}

	@Override
	public void wispFX3(World worldObj, double posX, double posY, double posZ, double posX2, double posY2, double posZ2, float size, int type, boolean shrink, float gravity)
	{
		FXWisp ef = new FXWisp(worldObj, posX, posY, posZ, posX2, posY2, posZ2, size, type);
		ef.setGravity(gravity);
		ef.shrink = shrink;
		ParticleEngine.instance.addEffect(worldObj, ef);
	}

	@Override
	public void wispFX4(World worldObj, double posX, double posY, double posZ, Entity target, int type, boolean shrink, float gravity)
	{
		FXWisp ef = new FXWisp(worldObj, posX, posY, posZ, target, type);
		ef.setGravity(gravity);
		ef.shrink = shrink;
		ParticleEngine.instance.addEffect(worldObj, ef);
	}

	@Override
	public void burst(World worldObj, double sx, double sy, double sz, float size)
	{
		FXBurst ef = new FXBurst(worldObj, sx, sy, sz, size);
		FMLClientHandler.instance().getClient().effectRenderer.addEffect(ef);
	}

	@Override
	public void sourceStreamFX(World worldObj, double sx, double sy, double sz, float tx, float ty, float tz, int tagColor)
	{
		Color c = new Color(tagColor);
		FXWispArcing ef = new FXWispArcing(worldObj, (double) tx, (double) ty, (double) tz, sx, sy, sz, 0.1F, (float) c.getRed() / 255.0F, (float) c.getGreen() / 255.0F, (float) c.getBlue() / 255.0F);
		ef.setGravity(0.0F);
		ParticleEngine.instance.addEffect(worldObj, ef);
	}

	@Override
	public void bolt(World worldObj, Entity sourceEntity, Entity targetedEntity)
	{
		FXLightningBolt bolt = new FXLightningBolt(worldObj, sourceEntity, targetedEntity, worldObj.rand.nextLong(), 4);
		bolt.defaultFractal();
		bolt.setType(0);
		bolt.finalizeBolt();
	}

	@Override
	public void nodeBolt(World worldObj, float x, float y, float z, Entity targetedEntity)
	{
		FXLightningBolt bolt = new FXLightningBolt(worldObj, (double) x, (double) y, (double) z, targetedEntity.posX, targetedEntity.posY, targetedEntity.posZ, worldObj.rand.nextLong(), 10, 4.0F, 5);
		bolt.defaultFractal();
		bolt.setType(3);
		bolt.finalizeBolt();
	}

	@Override
	public void nodeBolt(World worldObj, float x, float y, float z, float x2, float y2, float z2)
	{
		FXLightningBolt bolt = new FXLightningBolt(worldObj, (double) x, (double) y, (double) z, (double) x2, (double) y2, (double) z2, worldObj.rand.nextLong(), 10, 4.0F, 5);
		bolt.defaultFractal();
		bolt.setType(0);
		bolt.finalizeBolt();
	}

	@Override
	public void excavateFX(int x, int y, int z, EntityPlayer p, int bi, int md, int progress)
	{
		RenderGlobal rg = Minecraft.getMinecraft().renderGlobal;
		rg.destroyBlockPartially(p.getEntityId(), x, y, z, progress);
	}

	@Override
	public void beam(World worldObj, double sx, double sy, double sz, double tx, double ty, double tz, int type, int color, boolean reverse, float endmod, int age)
	{
		FXBeam beamcon = null;
		Color c = new Color(color);
		beamcon = new FXBeam(worldObj, sx, sy, sz, tx, ty, tz, (float) c.getRed() / 255.0F, (float) c.getGreen() / 255.0F, (float) c.getBlue() / 255.0F, age);
		beamcon.setType(type);
		beamcon.setEndMod(endmod);
		beamcon.setReverse(reverse);
		beamcon.setPulse(false);
		FMLClientHandler.instance().getClient().effectRenderer.addEffect(beamcon);
	}

	@Override
	public Object beamCont(World worldObj, EntityPlayer p, double tx, double ty, double tz, int type, int color, boolean reverse, float endmod, Object input, int impact)
	{
		FXBeamWand beamcon = null;
		Color c = new Color(color);
		if (input instanceof FXBeamWand)
			beamcon = (FXBeamWand) input;

		if (beamcon != null && !beamcon.isDead)
		{
			beamcon.updateBeam(tx, ty, tz);
			beamcon.setEndMod(endmod);
			beamcon.impact = impact;
		}
		else
		{
			beamcon = new FXBeamWand(worldObj, p, tx, ty, tz, (float) c.getRed() / 255.0F, (float) c.getGreen() / 255.0F, (float) c.getBlue() / 255.0F, 8);
			beamcon.setType(type);
			beamcon.setEndMod(endmod);
			beamcon.setReverse(reverse);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(beamcon);
		}

		return beamcon;
	}

	@Override
	public Object beamBore(World worldObj, double px, double py, double pz, double tx, double ty, double tz, int type, int color, boolean reverse, float endmod, Object input, int impact)
	{
		FXBeamBore beamcon = null;
		Color c = new Color(color);
		if (input instanceof FXBeamBore)
			beamcon = (FXBeamBore) input;

		if (beamcon != null && !beamcon.isDead)
		{
			beamcon.updateBeam(tx, ty, tz);
			beamcon.setEndMod(endmod);
			beamcon.impact = impact;
		}
		else
		{
			beamcon = new FXBeamBore(worldObj, px, py, pz, tx, ty, tz, (float) c.getRed() / 255.0F, (float) c.getGreen() / 255.0F, (float) c.getBlue() / 255.0F, 8);
			beamcon.setType(type);
			beamcon.setEndMod(endmod);
			beamcon.setReverse(reverse);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(beamcon);
		}

		return beamcon;
	}

	@Override
	public void boreDigFx(World worldObj, int x, int y, int z, int x2, int y2, int z2, Block bi, int md)
	{
		if (worldObj.rand.nextInt(10) == 0)
		{
			FXBoreSparkle fb = new FXBoreSparkle(worldObj, (double) ((float) x + worldObj.rand.nextFloat()), (double) ((float) y + worldObj.rand.nextFloat()), (double) ((float) z + worldObj.rand.nextFloat()), (double) x2 + 0.5D, (double) y2 + 0.5D, (double) z2 + 0.5D);
			ParticleEngine.instance.addEffect(worldObj, fb);
		}
		else
		{
			FXBoreParticles fb = new FXBoreParticles(worldObj, (double) ((float) x + worldObj.rand.nextFloat()), (double) ((float) y + worldObj.rand.nextFloat()), (double) ((float) z + worldObj.rand.nextFloat()), (double) x2 + 0.5D, (double) y2 + 0.5D, (double) z2 + 0.5D, bi, worldObj.rand.nextInt(6), md).func_70596_a(x, y, z);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(fb);
		}

	}

	@Override
	public void essentiaTrailFx(World worldObj, int x, int y, int z, int x2, int y2, int z2, int count, int color, float scale)
	{
		FXEssentiaTrail fb = new FXEssentiaTrail(worldObj, (double) x + 0.5D, (double) y + 0.5D, (double) z + 0.5D, (double) x2 + 0.5D, (double) y2 + 0.5D, (double) z2 + 0.5D, count, color, scale);
		ParticleEngine.instance.addEffect(worldObj, fb);
	}

	@Override
	public void soulTrail(World world, Entity source, Entity target, float r, float g, float b)
	{
		for (int a = 0; a < this.particleCount(2); ++a)
		{
			if (world.rand.nextInt(10) == 0)
			{
				FXSparkleTrail st = new FXSparkleTrail(world, source.posX - (double) (source.width / 2.0F) + (double) (world.rand.nextFloat() * source.width), source.posY + (double) (world.rand.nextFloat() * source.height), source.posZ - (double) (source.width / 2.0F) + (double) (world.rand.nextFloat() * source.width), target, r, g, b);
				st.noClip = true;
				ParticleEngine.instance.addEffect(world, st);
			}
			else
			{
				FXSmokeTrail st = new FXSmokeTrail(world, source.posX - (double) (source.width / 2.0F) + (double) (world.rand.nextFloat() * source.width), source.posY + (double) (world.rand.nextFloat() * source.height), source.posZ - (double) (source.width / 2.0F) + (double) (world.rand.nextFloat() * source.width), target, r, g, b);
				st.noClip = true;
				ParticleEngine.instance.addEffect(world, st);
			}
		}

	}

	@Override
	public int particleCount(int base)
	{
		return FMLClientHandler.instance().getClient().gameSettings.particleSetting == 2 ? 0 : FMLClientHandler.instance().getClient().gameSettings.particleSetting == 1 ? base * 1 : base * 2;
	}

	@Override
	public void furnaceLavaFx(World worldObj, int x, int y, int z, int facingX, int facingZ)
	{
		EntityLavaFX fb = new EntityLavaFX(worldObj, (double) ((float) x + 0.5F + (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.3F + (float) facingX * 1.0F), (double) ((float) y + 0.3F), (double) ((float) z + 0.5F + (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.3F + (float) facingZ * 1.0F));
		fb.motionY = (double) (0.2F * worldObj.rand.nextFloat());
		float qx = facingX == 0 ? (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.5F : (float) facingX * worldObj.rand.nextFloat();
		float qz = facingZ == 0 ? (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.5F : (float) facingZ * worldObj.rand.nextFloat();
		fb.motionX = (double) (0.15F * qx);
		fb.motionZ = (double) (0.15F * qz);
		FMLClientHandler.instance().getClient().effectRenderer.addEffect(fb);
	}

	@Override
	public void blockRunes(World world, double x, double y, double z, float r, float g, float b, int dur, float grav)
	{
		FXBlockRunes fb = new FXBlockRunes(world, x + 0.5D, y + 0.5D, z + 0.5D, r, g, b, dur);
		fb.setGravity(grav);
		ParticleEngine.instance.addEffect(world, fb);
	}

	@Override
	public void blockWard(World world, double x, double y, double z, ForgeDirection side, float f, float f1, float f2)
	{
		FXBlockWard fb = new FXBlockWard(world, x + 0.5D, y + 0.5D, z + 0.5D, side, f, f1, f2);
		FMLClientHandler.instance().getClient().effectRenderer.addEffect(fb);
	}

	@Override
	public Object swarmParticleFX(World worldObj, Entity targetedEntity, float f1, float f2, float pg)
	{
		FXSwarm fx = new FXSwarm(worldObj, targetedEntity.posX + (double) ((worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 2.0F), targetedEntity.posY + (double) ((worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 2.0F), targetedEntity.posZ + (double) ((worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 2.0F), targetedEntity, 0.8F + worldObj.rand.nextFloat() * 0.2F, worldObj.rand.nextFloat() * 0.4F, 1.0F - worldObj.rand.nextFloat() * 0.2F, f1, f2, pg);
		ParticleEngine.instance.addEffect(worldObj, fx);
		return fx;
	}

	@Override
	public void splooshFX(Entity e)
	{
		float f = e.worldObj.rand.nextFloat() * 3.1415927F * 2.0F;
		float f1 = e.worldObj.rand.nextFloat() * 0.5F + 0.5F;
		float f2 = MathHelper.sin(f) * 2.0F * 0.5F * f1;
		float f3 = MathHelper.cos(f) * 2.0F * 0.5F * f1;
		FXBreaking fx = new FXBreaking(e.worldObj, e.posX + (double) f2, e.posY + (double) (e.worldObj.rand.nextFloat() * e.height), e.posZ + (double) f3, Items.slime_ball);
		if (e.worldObj.rand.nextBoolean())
		{
			fx.setRBGColorF(0.6F, 0.0F, 0.3F);
			fx.setAlphaF(0.4F);
		}
		else
		{
			fx.setRBGColorF(0.3F, 0.0F, 0.3F);
			fx.setAlphaF(0.6F);
		}

		fx.setParticleMaxAge((int) (66.0F / (e.worldObj.rand.nextFloat() * 0.9F + 0.1F)));
		FMLClientHandler.instance().getClient().effectRenderer.addEffect(fx);
	}

	@Override
	public void splooshFX(World worldObj, int x, int y, int z)
	{
		float f = worldObj.rand.nextFloat() * 3.1415927F * 2.0F;
		float f1 = worldObj.rand.nextFloat() * 0.5F + 0.5F;
		float f2 = MathHelper.sin(f) * 2.0F * 0.5F * f1;
		float f3 = MathHelper.cos(f) * 2.0F * 0.5F * f1;
		FXBreaking fx = new FXBreaking(worldObj, (double) x + (double) f2 + 0.5D, (double) ((float) y + worldObj.rand.nextFloat()), (double) z + (double) f3 + 0.5D, Items.slime_ball);
		if (worldObj.rand.nextBoolean())
		{
			fx.setRBGColorF(0.6F, 0.0F, 0.3F);
			fx.setAlphaF(0.4F);
		}
		else
		{
			fx.setRBGColorF(0.3F, 0.0F, 0.3F);
			fx.setAlphaF(0.6F);
		}

		fx.setParticleMaxAge((int) (66.0F / (worldObj.rand.nextFloat() * 0.9F + 0.1F)));
		FMLClientHandler.instance().getClient().effectRenderer.addEffect(fx);
	}

	@Override
	public void taintsplosionFX(Entity e)
	{
		FXBreaking fx = new FXBreaking(e.worldObj, e.posX, e.posY + (double) (e.worldObj.rand.nextFloat() * e.height), e.posZ, Items.slime_ball);
		if (e.worldObj.rand.nextBoolean())
		{
			fx.setRBGColorF(0.6F, 0.0F, 0.3F);
			fx.setAlphaF(0.4F);
		}
		else
		{
			fx.setRBGColorF(0.3F, 0.0F, 0.3F);
			fx.setAlphaF(0.6F);
		}

		fx.motionX = (double) (float) (Math.random() * 2.0D - 1.0D);
		fx.motionY = (double) (float) (Math.random() * 2.0D - 1.0D);
		fx.motionZ = (double) (float) (Math.random() * 2.0D - 1.0D);
		float f = (float) (Math.random() + Math.random() + 1.0D) * 0.15F;
		float f1 = MathHelper.sqrt_double(fx.motionX * fx.motionX + fx.motionY * fx.motionY + fx.motionZ * fx.motionZ);
		fx.motionX = fx.motionX / (double) f1 * (double) f * 0.9640000000596046D;
		fx.motionY = fx.motionY / (double) f1 * (double) f * 0.9640000000596046D + 0.10000000149011612D;
		fx.motionZ = fx.motionZ / (double) f1 * (double) f * 0.9640000000596046D;
		fx.setParticleMaxAge((int) (66.0F / (e.worldObj.rand.nextFloat() * 0.9F + 0.1F)));
		FMLClientHandler.instance().getClient().effectRenderer.addEffect(fx);
	}

	@Override
	public void tentacleAriseFX(Entity e)
	{
		int xx = MathHelper.floor_double(e.posX);
		int yy = MathHelper.floor_double(e.posY) - 1;
		int zz = MathHelper.floor_double(e.posZ);

		for (int j = 0; (float) j < 2.0F * e.height; ++j)
		{
			float f = e.worldObj.rand.nextFloat() * 3.1415927F * e.height;
			float f1 = e.worldObj.rand.nextFloat() * 0.5F + 0.5F;
			float f2 = MathHelper.sin(f) * e.height * 0.25F * f1;
			float f3 = MathHelper.cos(f) * e.height * 0.25F * f1;
			FXBreaking fx = new FXBreaking(e.worldObj, e.posX + (double) f2, e.posY, e.posZ + (double) f3, Items.slime_ball);
			fx.setRBGColorF(0.4F, 0.0F, 0.4F);
			fx.setAlphaF(0.5F);
			fx.setParticleMaxAge((int) (66.0F / (e.worldObj.rand.nextFloat() * 0.9F + 0.1F)));
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(fx);
			if (!e.worldObj.isAirBlock(xx, yy, zz))
			{
				f = e.worldObj.rand.nextFloat() * 3.1415927F * e.height;
				f1 = e.worldObj.rand.nextFloat() * 0.5F + 0.5F;
				f2 = MathHelper.sin(f) * e.height * 0.25F * f1;
				f3 = MathHelper.cos(f) * e.height * 0.25F * f1;
				EntityDiggingFX fx2 = new EntityDiggingFX(e.worldObj, e.posX + (double) f2, e.posY, e.posZ + (double) f3, 0.0D, 0.0D, 0.0D, e.worldObj.getBlock(xx, yy, zz), e.worldObj.getBlockMetadata(xx, yy, zz), 1).applyColourMultiplier(xx, yy, zz);
				FMLClientHandler.instance().getClient().effectRenderer.addEffect(fx2);
			}
		}

	}

	@Override
	public void slimeJumpFX(Entity e, int i)
	{
		float f = e.worldObj.rand.nextFloat() * 3.1415927F * 2.0F;
		float f1 = e.worldObj.rand.nextFloat() * 0.5F + 0.5F;
		float f2 = MathHelper.sin(f) * (float) i * 0.5F * f1;
		float f3 = MathHelper.cos(f) * (float) i * 0.5F * f1;
		FXBreaking fx = new FXBreaking(e.worldObj, e.posX + (double) f2, (e.boundingBox.minY + e.boundingBox.maxY) / 2.0D, e.posZ + (double) f3, Items.slime_ball);
		fx.setRBGColorF(0.7F, 0.0F, 1.0F);
		fx.setAlphaF(0.4F);
		fx.setParticleMaxAge((int) (66.0F / (e.worldObj.rand.nextFloat() * 0.9F + 0.1F)));
		FMLClientHandler.instance().getClient().effectRenderer.addEffect(fx);
	}

	@Override
	public void dropletFX(World world, float i, float j, float k, float r, float g, float b)
	{
		FXDrop obj = new FXDrop(world, (double) i, (double) j, (double) k, r, g, b);
		FMLClientHandler.instance().getClient().effectRenderer.addEffect(obj);
	}

	@Override
	public void taintLandFX(Entity e)
	{
		float f = e.worldObj.rand.nextFloat() * 3.1415927F * 2.0F;
		float f1 = e.worldObj.rand.nextFloat() * 0.5F + 0.5F;
		float f2 = MathHelper.sin(f) * 2.0F * 0.5F * f1;
		float f3 = MathHelper.cos(f) * 2.0F * 0.5F * f1;
		if (e.worldObj.isRemote)
		{
			FXBreaking fx = new FXBreaking(e.worldObj, e.posX + (double) f2, (e.boundingBox.minY + e.boundingBox.maxY) / 2.0D, e.posZ + (double) f3, Items.slime_ball);
			fx.setRBGColorF(0.1F, 0.0F, 0.1F);
			fx.setAlphaF(0.4F);
			fx.setParticleMaxAge((int) (66.0F / (e.worldObj.rand.nextFloat() * 0.9F + 0.1F)));
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(fx);
		}

	}

	@Override
	public void hungryNodeFX(World worldObj, int sourceX, int sourceY, int sourceZ, int targetX, int targetY, int targetZ, Block block, int md)
	{
		FXBoreParticles fb = new FXBoreParticles(worldObj, (double) ((float) sourceX + worldObj.rand.nextFloat()), (double) ((float) sourceY + worldObj.rand.nextFloat()), (double) ((float) sourceZ + worldObj.rand.nextFloat()), (double) targetX + 0.5D, (double) targetY + 0.5D, (double) targetZ + 0.5D, block, worldObj.rand.nextInt(6), md).func_70596_a(sourceX, sourceY, sourceZ);
		FMLClientHandler.instance().getClient().effectRenderer.addEffect(fb);
	}

	@Override
	public void drawInfusionParticles1(World worldObj, double x, double y, double z, int x2, int y2, int z2, Item id, int md)
	{
		FXBoreParticles fb = new FXBoreParticles(worldObj, x, y, z, (double) x2 + 0.5D, (double) y2 - 0.5D, (double) z2 + 0.5D, id, worldObj.rand.nextInt(6), md).func_70596_a(x2, y2, z2);
		fb.setAlphaF(0.3F);
		fb.motionX = (double) ((float) worldObj.rand.nextGaussian() * 0.03F);
		fb.motionY = (double) ((float) worldObj.rand.nextGaussian() * 0.03F);
		fb.motionZ = (double) ((float) worldObj.rand.nextGaussian() * 0.03F);
		FMLClientHandler.instance().getClient().effectRenderer.addEffect(fb);
	}

	@Override
	public void drawInfusionParticles2(World worldObj, double x, double y, double z, int x2, int y2, int z2, Block id, int md)
	{
		FXBoreParticles fb = new FXBoreParticles(worldObj, x, y, z, (double) x2 + 0.5D, (double) y2 - 0.5D, (double) z2 + 0.5D, id, worldObj.rand.nextInt(6), md).func_70596_a(x2, y2, z2);
		fb.setAlphaF(0.3F);
		FMLClientHandler.instance().getClient().effectRenderer.addEffect(fb);
	}

	@Override
	public void drawInfusionParticles3(World worldObj, double x, double y, double z, int x2, int y2, int z2)
	{
		FXBoreSparkle fb = new FXBoreSparkle(worldObj, x, y, z, (double) x2 + 0.5D, (double) y2 - 0.5D, (double) z2 + 0.5D);
		fb.setRBGColorF(0.4F + worldObj.rand.nextFloat() * 0.2F, 0.2F, 0.6F + worldObj.rand.nextFloat() * 0.3F);
		ParticleEngine.instance.addEffect(worldObj, fb);
	}

	@Override
	public void drawInfusionParticles4(World worldObj, double x, double y, double z, int x2, int y2, int z2)
	{
		FXBoreSparkle fb = new FXBoreSparkle(worldObj, x, y, z, (double) x2 + 0.5D, (double) y2 - 0.5D, (double) z2 + 0.5D);
		fb.setRBGColorF(0.2F, 0.6F + worldObj.rand.nextFloat() * 0.3F, 0.3F);
		ParticleEngine.instance.addEffect(worldObj, fb);
	}

	@Override
	public void drawVentParticles(World worldObj, double x, double y, double z, double x2, double y2, double z2, int color)
	{
		FXVent fb = new FXVent(worldObj, x, y, z, x2, y2, z2, color);
		fb.setAlphaF(0.4F);
		ParticleEngine.instance.addEffect(worldObj, fb);
	}

	@Override
	public void drawGenericParticles(World worldObj, double x, double y, double z, double x2, double y2, double z2, float r, float g, float b, float alpha, boolean loop, int start, int num, int inc, int age, int delay, float scale)
	{
		FXGeneric fb = new FXGeneric(worldObj, x, y, z, x2, y2, z2);
		fb.setMaxAge(age, delay);
		fb.setRBGColorF(r, g, b);
		fb.setAlphaF(alpha);
		fb.setLoop(loop);
		fb.setParticles(start, num, inc);
		fb.setScale(scale);
		ParticleEngine.instance.addEffect(worldObj, fb);
	}

	@Override
	public void drawVentParticles(World worldObj, double x, double y, double z, double x2, double y2, double z2, int color, float scale)
	{
		FXVent fb = new FXVent(worldObj, x, y, z, x2, y2, z2, color);
		fb.setAlphaF(0.4F);
		fb.setScale(scale);
		ParticleEngine.instance.addEffect(worldObj, fb);
	}

	@Override
	public Object beamPower(World worldObj, double px, double py, double pz, double tx, double ty, double tz, float r, float g, float b, boolean pulse, Object input)
	{
		FXBeamPower beamcon = null;
		if (input instanceof FXBeamPower)
			beamcon = (FXBeamPower) input;

		if (beamcon != null && !beamcon.isDead)
		{
			beamcon.updateBeam(px, py, pz, tx, ty, tz);
			beamcon.setPulse(pulse, r, g, b);
		}
		else
		{
			beamcon = new FXBeamPower(worldObj, px, py, pz, tx, ty, tz, r, g, b, 8);
			FMLClientHandler.instance().getClient().effectRenderer.addEffect(beamcon);
		}

		return beamcon;
	}

	@Override
	public boolean isShiftKeyDown()
	{
		return GuiScreen.isShiftKeyDown();
	}

	@Override
	public void bottleTaintBreak(World world, double x, double y, double z)
	{
		String s = "iconcrack_" + Item.getIdFromItem(ConfigItems.itemBottleTaint) + "_" + 0;

		for (int k1 = 0; k1 < 8; ++k1)
		{
			Minecraft.getMinecraft().renderGlobal.spawnParticle(s, x, y, z, world.rand.nextGaussian() * 0.15D, world.rand.nextDouble() * 0.2D, world.rand.nextGaussian() * 0.15D);
		}

		world.playSound(x, y, z, "game.potion.smash", 1.0F, world.rand.nextFloat() * 0.1F + 0.9F, false);
	}

	@Override
	public void arcLightning(World world, double x, double y, double z, double tx, double ty, double tz, float r, float g, float b, float h)
	{
		FXSparkle ef2 = new FXSparkle(world, tx, ty, tz, tx, ty, tz, 3.0F, 6, 2);
		ef2.setGravity(0.0F);
		ef2.noClip = true;
		ef2.setRBGColorF(r, g, b);
		ParticleEngine.instance.addEffect(world, ef2);
		FXArc efa = new FXArc(world, x, y, z, tx, ty, tz, r, g, b, (double) h);
		FMLClientHandler.instance().getClient().effectRenderer.addEffect(efa);
	}
}
