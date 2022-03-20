package ua.limon4ik.tiles;

import appeng.api.config.Actionable;
import appeng.api.networking.GridFlags;
import appeng.api.networking.crafting.ICraftingPatternDetails;
import appeng.api.networking.crafting.ICraftingProvider;
import appeng.api.networking.crafting.ICraftingProviderHelper;
import appeng.api.networking.security.MachineSource;
import appeng.api.networking.storage.IStorageGrid;
import appeng.api.storage.data.IAEItemStack;
import appeng.me.GridAccessException;
import appeng.tile.TileEvent;
import appeng.tile.events.TileEventType;
import appeng.tile.grid.AENetworkTile;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ua.limon4ik.LegendaryHorizons;
import ua.limon4ik.tiles.pattern.BotaniaRunicAltarPattern;
import vazkii.botania.api.mana.IManaPool;
import vazkii.botania.api.recipe.RecipeRuneAltar;
import vazkii.botania.common.item.ModItems;
import ru.justagod.cutter.*;

import java.util.ArrayList;

public class TileEntityAutoRunicAltar extends AENetworkTile implements ICraftingProvider {
    private World w;
    private boolean busy=false;
    private IManaPool pool;
    private MachineSource source;
    private BotaniaRunicAltarPattern pattern;
    private static ArrayList<RecipeRuneAltar> recipes=new ArrayList<>();
    static {
        final int costTier1 = 5200;
        final int costTier2 = 8000;
        final int costTier3 = 12000;
        recipes.add(new RecipeRuneAltar(new ItemStack(ModItems.rune, 2, 0), costTier1,new ItemStack(ModItems.manaResource, 1, 23), new ItemStack(ModItems.manaResource, 1), new ItemStack(Items.dye, 1, 15), new ItemStack(Items.reeds), new ItemStack(Items.fishing_rod)));
        recipes.add(new RecipeRuneAltar(new ItemStack(ModItems.rune, 2, 1), costTier1, new ItemStack(ModItems.manaResource, 1, 23), new ItemStack(ModItems.manaResource, 1), new ItemStack(Items.netherbrick), new ItemStack(Items.gunpowder), new ItemStack(Items.nether_wart)));
        recipes.add(new RecipeRuneAltar(new ItemStack(ModItems.rune, 2, 2), costTier1, new ItemStack(ModItems.manaResource, 1, 23), new ItemStack(ModItems.manaResource, 1), "stone", new ItemStack(Blocks.coal_block), new ItemStack(Blocks.brown_mushroom)));
        recipes.add(new RecipeRuneAltar(new ItemStack(ModItems.rune, 2, 3), costTier1, new ItemStack(ModItems.manaResource, 1, 23), new ItemStack(ModItems.manaResource, 1), new ItemStack(Blocks.carpet, 1), new ItemStack(Items.feather), new ItemStack(Items.string)));
        recipes.add(new RecipeRuneAltar(new ItemStack(ModItems.rune, 1, 4), costTier2, new ItemStack(ModItems.rune, 1,0), new ItemStack(ModItems.rune, 1,1), "treeSapling", "treeSapling", "treeSapling", new ItemStack(Items.wheat)));
        recipes.add(new RecipeRuneAltar(new ItemStack(ModItems.rune, 1, 5), costTier2, new ItemStack(ModItems.rune, 1,2), new ItemStack(ModItems.rune, 1,3), new ItemStack(Block.getBlockFromName("sand")), new ItemStack(Block.getBlockFromName("sand")), new ItemStack(Items.slime_ball), new ItemStack(Items.melon)));
        recipes.add(new RecipeRuneAltar(new ItemStack(ModItems.rune, 1, 6), costTier2, new ItemStack(ModItems.rune, 1,1), new ItemStack(ModItems.rune, 1,3), "treeLeaves", "treeLeaves", "treeLeaves", new ItemStack(Items.spider_eye)));
        recipes.add(new RecipeRuneAltar(new ItemStack(ModItems.rune, 1, 7), costTier2, new ItemStack(ModItems.rune, 1,0), new ItemStack(ModItems.rune, 1,2), new ItemStack(Blocks.snow), new ItemStack(Blocks.snow), new ItemStack(Blocks.wool, 1), new ItemStack(Items.cake)));
        recipes.add(new RecipeRuneAltar(new ItemStack(ModItems.rune, 1, 8), costTier2, new ItemStack(ModItems.manaResource, 1), new ItemStack(ModItems.manaResource, 1), new ItemStack(ModItems.manaResource, 1), new ItemStack(ModItems.manaResource, 1), new ItemStack(ModItems.manaResource, 1), new ItemStack(ModItems.manaResource, 1,1)));
        recipes.add(new RecipeRuneAltar(new ItemStack(ModItems.rune, 1, 9), costTier3, new ItemStack(ModItems.manaResource, 2), new ItemStack(ModItems.manaResource, 1,2), new ItemStack(ModItems.rune, 1,5), new ItemStack(ModItems.rune, 1,3)));
        recipes.add(new RecipeRuneAltar(new ItemStack(ModItems.rune, 1, 10), costTier3, new ItemStack(ModItems.manaResource, 2), new ItemStack(ModItems.manaResource, 1,2), new ItemStack(ModItems.rune, 1,7), new ItemStack(ModItems.rune, 1,1)));
        recipes.add(new RecipeRuneAltar(new ItemStack(ModItems.rune, 1, 11), costTier3, new ItemStack(ModItems.manaResource, 2), new ItemStack(ModItems.manaResource, 1,2), new ItemStack(ModItems.rune, 1,4), new ItemStack(ModItems.rune, 1,0)));
        recipes.add(new RecipeRuneAltar(new ItemStack(ModItems.rune, 1, 12), costTier3, new ItemStack(ModItems.manaResource, 2), new ItemStack(ModItems.manaResource, 1,2), new ItemStack(ModItems.rune, 1,6), new ItemStack(ModItems.rune, 1,3)));
        recipes.add(new RecipeRuneAltar(new ItemStack(ModItems.rune, 1, 13), costTier3, new ItemStack(ModItems.manaResource, 2), new ItemStack(ModItems.manaResource, 1,2), new ItemStack(ModItems.rune, 1,7), new ItemStack(ModItems.rune, 1,2)));
        recipes.add(new RecipeRuneAltar(new ItemStack(ModItems.rune, 1, 14), costTier3, new ItemStack(ModItems.manaResource, 2), new ItemStack(ModItems.manaResource, 1,2), new ItemStack(ModItems.rune, 1,7), new ItemStack(ModItems.rune, 1,0)));
        recipes.add(new RecipeRuneAltar(new ItemStack(ModItems.rune, 1, 15), costTier3, new ItemStack(ModItems.manaResource, 2), new ItemStack(ModItems.manaResource, 1,2), new ItemStack(ModItems.rune, 1,5), new ItemStack(ModItems.rune, 1,1)));
        recipes.add(new RecipeRuneAltar(new ItemStack(Items.skull, 1, 3), 22500, new ItemStack(Items.skull), new ItemStack(ModItems.manaResource, 1,8), new ItemStack(ModItems.manaResource, 1,10), new ItemStack(Items.name_tag), new ItemStack(Items.golden_apple)));
    }
    public TileEntityAutoRunicAltar(World w){
        this();
        this.w=w;
    }
    public TileEntityAutoRunicAltar(){
        getProxy().setFlags(GridFlags.REQUIRE_CHANNEL);
        getProxy().setIdlePowerUsage(1000);
        source=new MachineSource(this);
        this.w=worldObj;
    }
    @Override
    public void provideCrafting(ICraftingProviderHelper iCraftingProviderHelper) {
        recipes.forEach(recipe->{
            iCraftingProviderHelper.addCraftingOption(this,new BotaniaRunicAltarPattern(recipe));
        });
    }

    @Override
    public boolean pushPattern(ICraftingPatternDetails iCraftingPatternDetails, InventoryCrafting inventoryCrafting) {
        TileEntity te=w.getTileEntity(xCoord,yCoord+1,zCoord);
        if(te instanceof IManaPool){
            pool= (IManaPool) te;
        }else {
            pool=null;
        }
        if(iCraftingPatternDetails instanceof BotaniaRunicAltarPattern){
            BotaniaRunicAltarPattern pattern= (BotaniaRunicAltarPattern) iCraftingPatternDetails;
            if(pool!=null){
                if(pool.getCurrentMana()>=pattern.getRecipe().getManaUsage()){
                    try {
                        IStorageGrid grid=getProxy().getStorage();
                        boolean a = true;
                        for (IAEItemStack stack : pattern.getInputs()) {
                            IAEItemStack i=grid.getItemInventory().extractItems(stack, Actionable.SIMULATE,source);
                            if(i==null || i.getStackSize()<=0){
                                a=false;
                                break;
                            }
                        }
                        if(a){
                            busy=true;
                            this.pattern=pattern;
                            return true;
                        }
                    }catch (GridAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean isBusy() {
        return busy;
    }
    @TileEvent(TileEventType.TICK)
	@GradleSideOnly(GradleSide.SERVER)
    public void onTick() {
        if (isBusy() && pattern != null) {
            try {
                IStorageGrid grid = getProxy().getStorage();
                grid.getItemInventory().injectItems(pattern.getOutputs()[0], Actionable.MODULATE, source);
                pool.recieveMana(-pattern.getRecipe().getManaUsage());
                busy = false;
            } catch (GridAccessException e) {
                    e.printStackTrace();
			}
        }
    }
}
