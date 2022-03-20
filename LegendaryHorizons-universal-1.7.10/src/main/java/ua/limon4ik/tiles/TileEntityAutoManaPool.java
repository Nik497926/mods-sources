package ua.limon4ik.tiles;

import appeng.api.AEApi;
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
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ua.limon4ik.LegendaryHorizons;
import ua.limon4ik.tiles.pattern.BotaniaPattern;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.mana.IManaPool;
import vazkii.botania.api.recipe.RecipeManaInfusion;
import vazkii.botania.common.item.ModItems;
import ru.justagod.cutter.*;

import java.util.ArrayList;

public class TileEntityAutoManaPool extends AENetworkTile implements ICraftingProvider {
    private IManaPool pool;
    private MachineSource source;
    public TileEntityAutoManaPool(){
        getProxy().setFlags(GridFlags.REQUIRE_CHANNEL);
        getProxy().setIdlePowerUsage(1000);
        source=new MachineSource(this);
    }
    public TileEntityAutoManaPool(World w){
        this();
        worldObj=w;
    }
    private boolean busy=false;
    private RecipeManaInfusion recipe;
    @Override
    public void provideCrafting(ICraftingProviderHelper iCraftingProviderHelper) {
        ArrayList<String> out=new ArrayList<>();
        RecipeManaInfusion r=new RecipeManaInfusion(new ItemStack(ModItems.manaResource,1,23),new ItemStack(Items.redstone,1),10000);
        out.add(r.getOutput().getUnlocalizedName());
        iCraftingProviderHelper.addCraftingOption(this,new BotaniaPattern(r));
        BotaniaAPI.manaInfusionRecipes.forEach(recipe->{
            if(recipe.getInput() instanceof ItemStack && !out.contains(recipe.getOutput().getUnlocalizedName())){
                iCraftingProviderHelper.addCraftingOption(this,new BotaniaPattern(recipe));
                out.add(recipe.getOutput().getUnlocalizedName());
            }
        });
        iCraftingProviderHelper.addCraftingOption(this,new BotaniaPattern(new RecipeManaInfusion(new ItemStack(ModItems.manaResource, 1, 2), new ItemStack(Items.diamond,1), 10000)));
        iCraftingProviderHelper.addCraftingOption(this,new BotaniaPattern(new RecipeManaInfusion(new ItemStack(ModItems.manaResource, 1, 0), new ItemStack(Items.iron_ingot,1), 10000)));
    }
    @Override
    public boolean pushPattern(ICraftingPatternDetails iCraftingPatternDetails, InventoryCrafting inventoryCrafting) {
        TileEntity te=worldObj.getTileEntity(xCoord,yCoord+1,zCoord);
        if(te instanceof IManaPool){
            pool= (IManaPool) te;
        }else {
            pool=null;
        }
        if(iCraftingPatternDetails instanceof BotaniaPattern){
            BotaniaPattern pattern= (BotaniaPattern) iCraftingPatternDetails;
            if(pool!=null){
                if(pool.getCurrentMana()>=pattern.getRecipe().getManaToConsume()){
                    try {
                        IStorageGrid grid=getProxy().getStorage();
                        IAEItemStack a;
                        //IAEItemStack a=grid.getItemInventory().extractItems(pattern.getInputs()[0], Actionable.SIMULATE,source);
                        //if(a!=null && a.getStackSize()>0) {
                            a=grid.getItemInventory().injectItems(pattern.getOutputs()[0],Actionable.SIMULATE,source);
                            if(a==null || a.getStackSize()<=0){
                                busy = true;
                                recipe = pattern.getRecipe();
                                return true;
                            }
                        //}
                    } catch (GridAccessException e) {
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
            if (isBusy()) {
                if (recipe != null) {
                    try {
                        System.out.println("OK89");
                        IStorageGrid grid = getProxy().getStorage();
                        pool.recieveMana(-recipe.getManaToConsume());
                        grid.getItemInventory().injectItems(AEApi.instance().storage().createItemStack(recipe.getOutput()), Actionable.MODULATE, source);
                        busy = false;
                    } catch (GridAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
    }
}
