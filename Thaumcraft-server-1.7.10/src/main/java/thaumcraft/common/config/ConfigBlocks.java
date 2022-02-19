package thaumcraft.common.config;

import com.gamerforea.thaumcraft.EventConfig;
import com.gamerforea.thaumcraft.tile.OwnerTileEntity;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.common.blocks.*;
import thaumcraft.common.tiles.*;

public class ConfigBlocks
{
	public static Fluid FLUXGOO = new Fluid("fluxGoo").setGaseous(false).setLuminosity(7).setDensity(8).setViscosity(6000);
	public static Fluid FLUXGAS = new Fluid("fluxGas").setGaseous(true).setLuminosity(7).setDensity(-4).setViscosity(2500);
	public static Fluid FLUIDPURE = new Fluid("fluidPure").setGaseous(false).setLuminosity(10).setViscosity(1000).setRarity(EnumRarity.rare);
	public static Fluid FLUIDDEATH = new Fluid("fluidDeath").setGaseous(false).setLuminosity(8).setViscosity(1500).setRarity(EnumRarity.rare);
	public static Block blockFluxGoo;
	public static Block blockFluxGas;
	public static Block blockFluidPure;
	public static Block blockFluidDeath;
	public static Block blockHole;
	public static Block blockArcaneFurnace;
	public static Block blockMetalDevice;
	public static Block blockMagicalLog;
	public static Block blockStoneDevice;
	public static Block blockWoodenDevice;
	public static Block blockMagicalLeaves;
	public static Block blockTable;
	public static Block blockChestHungry;
	public static Block blockCustomOre;
	public static Block blockCustomPlant;
	public static Block blockCandle;
	public static Block blockJar;
	public static Block blockArcaneDoor;
	public static Block blockWarded;
	public static Block blockLifter;
	public static Block blockAiry;
	public static Block blockCrystal;
	public static Block blockCosmeticOpaque;
	public static Block blockCosmeticSolid;
	public static Block blockMirror;
	public static Block blockTaint;
	public static Block blockTaintFibres;
	public static Block blockManaPod;
	public static Block blockTube;
	public static Block blockMagicBox;
	public static Block blockEldritch;
	public static Block blockEldritchPortal;
	public static Block blockEssentiaReservoir;
	public static Block blockStairsArcaneStone;
	public static Block blockStairsSilverwood;
	public static Block blockStairsGreatwood;
	public static Block blockStairsEldritch;
	public static Block blockSlabWood;
	public static Block blockSlabStone;
	public static Block blockDoubleSlabWood;
	public static Block blockDoubleSlabStone;
	public static Block blockEldritchNothing;
	public static Block blockAlchemyFurnace;
	public static Block blockLootUrn;
	public static Block blockLootCrate;
	public static int blockCosmeticOpaqueRI = -1;
	public static int blockWoodenDeviceRI = -1;
	public static int blockLifterRI = -1;
	public static int blockCrystalRI = -1;
	public static int blockFluxGasRI = -1;
	public static int blockCustomOreRI = -1;
	public static int blockTaintRI = -1;
	public static int blockTaintFibreRI = -1;
	public static int blockMirrorRI = -1;
	public static int blockCandleRI = -1;
	public static int blockWardedRI = -1;
	public static int blockArcaneFurnaceRI = -1;
	public static int blockMetalDeviceRI = -1;
	public static int blockStoneDeviceRI = -1;
	public static int blockChestHungryRI = -1;
	public static int blockTableRI = -1;
	public static int blockJarRI = -1;
	public static int blockLootUrnRI = -1;
	public static int blockLootCrateRI = -1;
	public static int blockTubeRI = -1;
	public static int blockEssentiaReservoirRI = -1;
	public static int blockEldritchRI = -1;

	public static void init()
	{
		initializeBlocks();
		registerBlocks();
		registerTileEntities();
		blockCustomOre.setHarvestLevel("pickaxe", 2, 0);
		blockCustomOre.setHarvestLevel("pickaxe", 2, 7);
		ThaumcraftApi.portableHoleBlackList.add(Blocks.bed);
		ThaumcraftApi.portableHoleBlackList.add(blockEldritch);
		ThaumcraftApi.portableHoleBlackList.add(blockEldritchPortal);
		ThaumcraftApi.portableHoleBlackList.add(Blocks.piston);
		ThaumcraftApi.portableHoleBlackList.add(Blocks.piston_head);
		ThaumcraftApi.portableHoleBlackList.add(Blocks.sticky_piston);
		ThaumcraftApi.portableHoleBlackList.add(Blocks.piston_extension);
		ThaumcraftApi.portableHoleBlackList.add(Blocks.wooden_door);
		ThaumcraftApi.portableHoleBlackList.add(Blocks.iron_door);
		ThaumcraftApi.portableHoleBlackList.add(blockFluxGoo);
		ThaumcraftApi.portableHoleBlackList.add(blockFluxGas);
		ThaumcraftApi.portableHoleBlackList.add(blockArcaneFurnace);
	}

	private static void initializeBlocks()
	{
		FluidRegistry.registerFluid(FLUXGOO);
		FluidRegistry.registerFluid(FLUIDPURE);
		FluidRegistry.registerFluid(FLUIDDEATH);

		// TODO gamerforEA code start
		if (EventConfig.registerFluxGas)
			FluidRegistry.registerFluid(FLUXGAS);
		// TODO gamerforEA code end

		blockFluxGoo = new BlockFluxGoo().setBlockName("blockFluxGoo");
		blockFluxGas = new BlockFluxGas().setBlockName("blockFluxGas");
		blockHole = new BlockHole().setBlockName("blockHole");
		blockCustomOre = new BlockCustomOre().setBlockName("blockCustomOre");
		blockCustomPlant = new BlockCustomPlant().setBlockName("blockCustomPlant");
		blockMagicalLog = new BlockMagicalLog().setBlockName("blockMagicalLog");
		blockMagicalLeaves = new BlockMagicalLeaves().setBlockName("blockMagicalLeaves");
		blockArcaneFurnace = new BlockArcaneFurnace().setBlockName("blockArcaneFurnace");
		blockMetalDevice = new BlockMetalDevice().setBlockName("blockMetalDevice");
		blockAlchemyFurnace = new BlockAlchemyFurnace().setBlockName("blockAlchemyFurnace");
		blockTable = new BlockTable().setBlockName("blockTable");
		blockChestHungry = new BlockChestHungry().setBlockName("blockChestHungry");
		blockCandle = new BlockCandle().setBlockName("blockCandle");
		blockJar = new BlockJar().setBlockName("blockJar");
		blockArcaneDoor = new BlockArcaneDoor().setBlockName("blockArcaneDoor");
		blockWoodenDevice = new BlockWoodenDevice().setBlockName("blockWoodenDevice");
		blockLifter = new BlockLifter().setBlockName("blockLifter");
		blockAiry = new BlockAiry().setBlockName("blockAiry");
		blockCrystal = new BlockCrystal().setBlockName("blockCrystal");
		blockCosmeticOpaque = new BlockCosmeticOpaque().setBlockName("blockCosmeticOpaque");
		blockCosmeticSolid = new BlockCosmeticSolid().setBlockName("blockCosmeticSolid");
		blockMirror = new BlockMirror().setBlockName("blockMirror");
		blockTaint = new BlockTaint().setBlockName("blockTaint");
		blockTaintFibres = new BlockTaintFibres().setBlockName("blockTaintFibres");
		blockStoneDevice = new BlockStoneDevice().setBlockName("blockStoneDevice");
		blockManaPod = new BlockManaPod().setBlockName("blockManaPod");
		blockTube = new BlockTube().setBlockName("blockTube");
		blockWarded = new BlockWarded().setBlockName("blockWarded");
		blockMagicBox = new BlockMagicBox().setBlockName("blockMagicBox");
		blockFluidPure = new BlockFluidPure().setBlockName("blockFluidPure");
		blockFluidDeath = new BlockFluidDeath().setBlockName("blockFluidDeath");
		blockEldritch = new BlockEldritch().setBlockName("blockEldritch");
		blockEldritchPortal = new BlockEldritchPortal().setBlockName("blockPortalEldritch");
		blockEssentiaReservoir = new BlockEssentiaReservoir().setBlockName("blockEssentiaReservoir");
		blockEldritchNothing = new BlockEldritchNothing().setBlockName("blockPortalNothing");
		blockStairsArcaneStone = new BlockCosmeticStairs(blockCosmeticSolid, 7).setBlockName("blockStairsArcaneStone");
		blockStairsGreatwood = new BlockCosmeticStairs(blockWoodenDevice, 6).setBlockName("blockStairsGreatwood");
		blockStairsSilverwood = new BlockCosmeticStairs(blockWoodenDevice, 7).setBlockName("blockStairsSilverwood");
		blockStairsEldritch = new BlockCosmeticStairs(blockCosmeticSolid, 11).setBlockName("blockStairsEldritch");
		blockSlabWood = new BlockCosmeticWoodSlab(false).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundTypeWood).setBlockName("blockCosmeticSlabWood");
		blockSlabStone = new BlockCosmeticStoneSlab(false).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundTypePiston).setBlockName("blockCosmeticSlabStone");
		blockDoubleSlabWood = new BlockCosmeticWoodSlab(true).setHardness(2.0F).setResistance(5.0F).setStepSound(Block.soundTypeWood).setBlockName("blockCosmeticDoubleSlabWood");
		blockDoubleSlabStone = new BlockCosmeticStoneSlab(true).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundTypePiston).setBlockName("blockCosmeticDoubleSlabStone");
		blockLootUrn = new BlockLoot(Material.clay, "urn", 1).setStepSound(new CustomStepSound("urnbreak", 1.0F, 0.7F)).setBlockName("blockLootUrn");
		blockLootCrate = new BlockLoot(Material.wood, "crate", 2).setStepSound(Block.soundTypeWood).setBlockName("blockLootCrate");
	}

	private static void registerBlocks()
	{
		GameRegistry.registerBlock(blockFluxGoo, BlockFluxGooItem.class, "blockFluxGoo");
		GameRegistry.registerBlock(blockFluxGas, BlockFluxGasItem.class, "blockFluxGas");
		GameRegistry.registerBlock(blockFluidPure, "blockFluidPure");
		GameRegistry.registerBlock(blockFluidDeath, "blockFluidDeath");
		GameRegistry.registerBlock(blockCustomOre, BlockCustomOreItem.class, "blockCustomOre");
		GameRegistry.registerBlock(blockMagicalLog, BlockMagicalLogItem.class, "blockMagicalLog");
		GameRegistry.registerBlock(blockMagicalLeaves, BlockMagicalLeavesItem.class, "blockMagicalLeaves");
		GameRegistry.registerBlock(blockCustomPlant, BlockCustomPlantItem.class, "blockCustomPlant");
		GameRegistry.registerBlock(blockTaint, BlockTaintItem.class, "blockTaint");
		GameRegistry.registerBlock(blockTaintFibres, BlockTaintFibresItem.class, "blockTaintFibres");
		GameRegistry.registerBlock(blockCosmeticOpaque, BlockCosmeticOpaqueItem.class, "blockCosmeticOpaque");
		GameRegistry.registerBlock(blockCosmeticSolid, BlockCosmeticSolidItem.class, "blockCosmeticSolid");
		GameRegistry.registerBlock(blockCrystal, BlockCrystalItem.class, "blockCrystal");
		GameRegistry.registerBlock(blockTube, BlockTubeItem.class, "blockTube");
		GameRegistry.registerBlock(blockMetalDevice, BlockMetalDeviceItem.class, "blockMetalDevice");
		GameRegistry.registerBlock(blockWoodenDevice, BlockWoodenDeviceItem.class, "blockWoodenDevice");
		GameRegistry.registerBlock(blockStoneDevice, BlockStoneDeviceItem.class, "blockStoneDevice");
		GameRegistry.registerBlock(blockMirror, BlockMirrorItem.class, "blockMirror");
		GameRegistry.registerBlock(blockTable, BlockTableItem.class, "blockTable");
		GameRegistry.registerBlock(blockChestHungry, "blockChestHungry");
		GameRegistry.registerBlock(blockArcaneDoor, "blockArcaneDoor");
		GameRegistry.registerBlock(blockLifter, "blockLifter");
		GameRegistry.registerBlock(blockMagicBox, "blockMagicBox");
		GameRegistry.registerBlock(blockAlchemyFurnace, "blockAlchemyFurnace");
		GameRegistry.registerBlock(blockJar, BlockJarItem.class, "blockJar");
		GameRegistry.registerBlock(blockCandle, BlockCandleItem.class, "blockCandle");
		GameRegistry.registerBlock(blockEldritch, BlockEldritchItem.class, "blockEldritch");
		GameRegistry.registerBlock(blockAiry, BlockAiryItem.class, "blockAiry");
		GameRegistry.registerBlock(blockManaPod, "blockManaPod");
		GameRegistry.registerBlock(blockArcaneFurnace, "blockArcaneFurnace");
		GameRegistry.registerBlock(blockWarded, "blockWarded");
		GameRegistry.registerBlock(blockHole, "blockHole");
		GameRegistry.registerBlock(blockEldritchPortal, "blockPortalEldritch");
		GameRegistry.registerBlock(blockEssentiaReservoir, BlockEssentiaReservoirItem.class, "blockEssentiaReservoir");
		GameRegistry.registerBlock(blockEldritchNothing, "blockEldritchNothing");
		GameRegistry.registerBlock(blockStairsArcaneStone, "blockStairsArcaneStone");
		GameRegistry.registerBlock(blockStairsGreatwood, "blockStairsGreatwood");
		GameRegistry.registerBlock(blockStairsSilverwood, "blockStairsSilverwood");
		GameRegistry.registerBlock(blockStairsEldritch, "blockStairsEldritch");
		GameRegistry.registerBlock(blockSlabWood, BlockCosmeticWoodSlabItem.class, "blockCosmeticSlabWood");
		GameRegistry.registerBlock(blockSlabStone, BlockCosmeticStoneSlabItem.class, "blockCosmeticSlabStone");
		GameRegistry.registerBlock(blockDoubleSlabWood, "blockCosmeticDoubleSlabWood");
		GameRegistry.registerBlock(blockDoubleSlabStone, "blockCosmeticDoubleSlabStone");
		GameRegistry.registerBlock(blockLootUrn, BlockLootItem.class, "blockLootUrn");
		GameRegistry.registerBlock(blockLootCrate, BlockLootItem.class, "blockLootCrate");
	}

	private static void registerTileEntities()
	{
		GameRegistry.registerTileEntity(TileHole.class, "TileHole");
		GameRegistry.registerTileEntity(TileArcaneFurnace.class, "TileArcaneFurnace");
		GameRegistry.registerTileEntity(TileArcaneFurnaceNozzle.class, "TileArcaneFurnaceNozzle");
		GameRegistry.registerTileEntity(TileCrucible.class, "TileCrucible");
		GameRegistry.registerTileEntity(TileAlembic.class, "TileSiphon");
		GameRegistry.registerTileEntity(TileJarFillable.class, "TileJar");
		GameRegistry.registerTileEntity(TileJarFillableVoid.class, "TileJarVoid");
		GameRegistry.registerTileEntity(TileJarNode.class, "TileJarNode");
		GameRegistry.registerTileEntity(TileJarBrain.class, "TileJarBrain");
		GameRegistry.registerTileEntity(TileResearchTable.class, "TileResearchTable");
		GameRegistry.registerTileEntity(TileTable.class, "TileTable");
		GameRegistry.registerTileEntity(TileArcaneWorkbench.class, "TileArcaneWorkbench");
		GameRegistry.registerTileEntity(TileChestHungry.class, "TileChestHungry");
		GameRegistry.registerTileEntity(TileBellows.class, "TileBellows");
		GameRegistry.registerTileEntity(TileSensor.class, "TileSensor");
		GameRegistry.registerTileEntity(TileArcanePressurePlate.class, "TileArcanePressurePlate");
		GameRegistry.registerTileEntity(TileArcaneBore.class, "TileArcaneBore");
		GameRegistry.registerTileEntity(TileArcaneBoreBase.class, "TileArcaneBoreBase");
		GameRegistry.registerTileEntity(TileArcaneLamp.class, "TileArcaneLamp");
		GameRegistry.registerTileEntity(TileArcaneLampGrowth.class, "TileArcaneLampGrowth");
		GameRegistry.registerTileEntity(TileArcaneLampFertility.class, "TileArcaneLampFertility");
		GameRegistry.registerTileEntity(TileOwned.class, "TileOwned");
		GameRegistry.registerTileEntity(TileLifter.class, "TileLifter");
		GameRegistry.registerTileEntity(TileNitor.class, "TileNitor");
		GameRegistry.registerTileEntity(TileNode.class, "TileNode");
		GameRegistry.registerTileEntity(TileCrystal.class, "TileCrystal");
		GameRegistry.registerTileEntity(TileEldritchCrystal.class, "TileEldritchCrystal");
		GameRegistry.registerTileEntity(TileMirror.class, "TileMirror");
		GameRegistry.registerTileEntity(TileMirrorEssentia.class, "TileMirrorEssentia");
		GameRegistry.registerTileEntity(TilePedestal.class, "TilePedestal");
		GameRegistry.registerTileEntity(TileWandPedestal.class, "TileWandPedestal");
		GameRegistry.registerTileEntity(TileInfusionMatrix.class, "TileInfusionStone");
		GameRegistry.registerTileEntity(TileInfusionPillar.class, "TileInfusionPillar");
		GameRegistry.registerTileEntity(TileAlchemyFurnace.class, "TileAlchemyFurnace");
		GameRegistry.registerTileEntity(TileAlchemyFurnaceAdvanced.class, "TileAlchemyFurnaceAdvanced");
		GameRegistry.registerTileEntity(TileAlchemyFurnaceAdvancedNozzle.class, "TileAlchemyFurnaceAdvancedNozzle");
		GameRegistry.registerTileEntity(TileDeconstructionTable.class, "TileDeconstructionTable");
		GameRegistry.registerTileEntity(TileEtherealBloom.class, "TilePurifyTotem");
		GameRegistry.registerTileEntity(TileTube.class, "TileTube");
		GameRegistry.registerTileEntity(TileTubeValve.class, "TileTubeValve");
		GameRegistry.registerTileEntity(TileCentrifuge.class, "TileCentrifuge");
		GameRegistry.registerTileEntity(TileTubeFilter.class, "TileTubeFilter");
		GameRegistry.registerTileEntity(TileTubeBuffer.class, "TileTubeBuffer");
		GameRegistry.registerTileEntity(TileTubeRestrict.class, "TileTubeRestrict");
		GameRegistry.registerTileEntity(TileTubeOneway.class, "TileTubeOneway");
		GameRegistry.registerTileEntity(TileThaumatorium.class, "TileThaumatorium");
		GameRegistry.registerTileEntity(TileThaumatoriumTop.class, "TileThaumatoriumTop");
		GameRegistry.registerTileEntity(TileBrainbox.class, "TileBrainbox");
		GameRegistry.registerTileEntity(TileWarded.class, "TileWarded");
		GameRegistry.registerTileEntity(TileGrate.class, "TileGrate");
		GameRegistry.registerTileEntity(TileWardingStone.class, "TileWardingStone");
		GameRegistry.registerTileEntity(TileWardingStoneFence.class, "TileWardingStoneFence");
		GameRegistry.registerTileEntity(TileMagicBox.class, "TileMagicBox");
		GameRegistry.registerTileEntity(TileNodeStabilizer.class, "TileNodeStabilizer");
		GameRegistry.registerTileEntity(TileVisRelay.class, "TileVisRelay");
		GameRegistry.registerTileEntity(TileNodeEnergized.class, "TileNodeEnergized");
		GameRegistry.registerTileEntity(TileNodeConverter.class, "TileNodeConverter");
		GameRegistry.registerTileEntity(TileMagicWorkbenchCharger.class, "TileMagicWorkbenchCharger");
		GameRegistry.registerTileEntity(TileManaPod.class, "TileManaPod");
		GameRegistry.registerTileEntity(TileSpa.class, "TileSpa");
		GameRegistry.registerTileEntity(TileEldritchAltar.class, "TileEldritchAltar");
		GameRegistry.registerTileEntity(TileEldritchObelisk.class, "TileEldritchObelisk");
		GameRegistry.registerTileEntity(TileEldritchCap.class, "TileEldritchCap");
		GameRegistry.registerTileEntity(TileBanner.class, "TileBanner");
		GameRegistry.registerTileEntity(TileEldritchPortal.class, "TileEldritchPortal");
		GameRegistry.registerTileEntity(TileEssentiaReservoir.class, "TileEssentiaReservoir");
		GameRegistry.registerTileEntity(TileEssentiaCrystalizer.class, "TileEssentiaCrystalizer");
		GameRegistry.registerTileEntity(TileEldritchNothing.class, "TileEldritchNothing");
		GameRegistry.registerTileEntity(TileEldritchLock.class, "TileEldritchLock");
		GameRegistry.registerTileEntity(TileEldritchCrabSpawner.class, "TileEldritchCrabSpawner");
		GameRegistry.registerTileEntity(TileFocalManipulator.class, "TileFocalManipulator");
		GameRegistry.registerTileEntity(TileFluxScrubber.class, "TileFluxScrubber");
		GameRegistry.registerTileEntity(TileEldritchTrap.class, "TileEldritchTrap");

		// TODO gamerforEA code start
		GameRegistry.registerTileEntity(OwnerTileEntity.class, "ThaumCraftOwnerTileEntity");
		// TODO gamerforEA code end
	}
}
