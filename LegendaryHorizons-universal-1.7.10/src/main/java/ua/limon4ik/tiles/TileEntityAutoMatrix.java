package ua.limon4ik.tiles;

import appeng.api.config.Actionable;
import appeng.api.networking.GridFlags;
import appeng.api.networking.IGrid;
import appeng.api.networking.IGridNode;
import appeng.api.networking.crafting.ICraftingPatternDetails;
import appeng.api.networking.crafting.ICraftingProvider;
import appeng.api.networking.crafting.ICraftingProviderHelper;
import appeng.api.networking.events.MENetworkCraftingPatternChange;
import appeng.api.networking.security.MachineSource;
import appeng.api.networking.storage.IStorageGrid;
import appeng.api.storage.data.IAEItemStack;
import appeng.me.GridAccessException;
import appeng.tile.TileEvent;
import appeng.tile.events.TileEventType;
import appeng.tile.grid.AENetworkTile;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.item.ItemStack;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumicenergistics.api.grid.IEssentiaGrid;
import thaumicenergistics.api.grid.IMEEssentiaMonitor;
import ua.limon4ik.LegendaryHorizons;
import ua.limon4ik.tiles.pattern.SPattern;
import ru.justagod.cutter.*;

import java.util.List;
import java.util.Map;

public class TileEntityAutoMatrix extends AENetworkTile implements ICraftingProvider {
    private ItemStack stack;
    private boolean busy=false;
    private SPattern pattern;
    private MachineSource source;
    public TileEntityAutoMatrix(){
        source=new MachineSource(this);
        getProxy().setFlags(GridFlags.REQUIRE_CHANNEL);
    }
    @Override
    public void provideCrafting(ICraftingProviderHelper iCraftingProviderHelper) {
        if(hasItem()){
            iCraftingProviderHelper.addCraftingOption(this,new SPattern(stack));
        }
    }
    public boolean setItem(ItemStack item){
        if(stack==null){
            if(ThaumcraftApi.getInfusionRecipe(item)!=null && ThaumcraftApi.getInfusionRecipe(item).getRecipeOutput() instanceof ItemStack){
                stack=item.copy();
                stack.stackSize=1;
                markDirty();
                try {
                    this.getProxy().getGrid().postEvent( new MENetworkCraftingPatternChange( this, this.getActionableNode() ) );
                } catch (GridAccessException e) {
                    e.printStackTrace();
                }
                return true;
            }
        }
        return false;
    }
    public boolean hasItem(){
        return stack!=null;
    }
    public void removeItemStack(){
        stack=null;
        markDirty();
        try {
            this.getProxy().getGrid().postEvent( new MENetworkCraftingPatternChange( this, this.getActionableNode() ) );
        } catch (GridAccessException e) {
            e.printStackTrace();
        }
    }

    public ItemStack getItemStack() {
        return stack;
    }

    @Override
    public void getDrops(World w, int x, int y, int z, List<ItemStack> drops) {
        if(hasItem()){
            drops.add(stack);
        }
        super.getDrops(w, x, y, z, drops);
    }

    @Override
    public boolean pushPattern(ICraftingPatternDetails iCraftingPatternDetails, InventoryCrafting inventoryCrafting) {
            if (iCraftingPatternDetails instanceof SPattern) {
                SPattern sPattern = (SPattern) iCraftingPatternDetails;
                try {
                    IStorageGrid igrid = getProxy().getStorage();
                    boolean rejected = false;
                    for (IAEItemStack output : sPattern.getOutputs()) {
                        IAEItemStack rejectedResult = igrid.getItemInventory().injectItems(output, Actionable.SIMULATE,
                                source);
                        if ((rejectedResult != null) && (rejectedResult.getStackSize() > 0)) {
                            rejected = true;
                            break;
                        }
                    }
                    IMEEssentiaMonitor essentiaMonitor = null;
                    IGrid grid = null;
                    IGridNode node = getProxy().getNode();
                    if (node != null) {
                        grid = node.getGrid();
                        if (grid != null) {
                            essentiaMonitor = grid.getCache(IEssentiaGrid.class);
                        }
                    }
                    if(essentiaMonitor==null)return false;
                    for(Map.Entry<Aspect,Integer> aspect:sPattern.getRecipe().getAspects().aspects.entrySet()){
                        long l=essentiaMonitor.extractEssentia(aspect.getKey(),((long)aspect.getValue()),Actionable.SIMULATE,source,false);
                        int i=(int)l;
                        if(i<aspect.getValue()){
                            rejected=true;
                        }
                    }
                    if (!rejected) {
                        busy=true;
                        this.pattern=sPattern;
                        return true;
                    }


                } catch (GridAccessException e) {
                    return false;
                }
            }
        return false;
    }

    @TileEvent(TileEventType.TICK)
	@GradleSideOnly(GradleSide.SERVER)
    public void onTick() {
            if (isBusy() && pattern != null) {
                try {
                    IStorageGrid igrid = getProxy().getStorage();
                    IMEEssentiaMonitor essentiaMonitor = null;
                    IGrid grid = null;
                    IGridNode node = getProxy().getNode();
                    if (node != null) {
                        grid = node.getGrid();
                        if (grid != null) {
                            essentiaMonitor = grid.getCache(IEssentiaGrid.class);
                        }
                    }
					if(igrid==null || essentiaMonitor==null)return;
                    for (IAEItemStack output : pattern.getOutputs()) {
                        igrid.getItemInventory().injectItems(output, Actionable.MODULATE, source);
                    }
                for(Map.Entry<Aspect,Integer> aspect:ThaumcraftApi.getInfusionRecipe(pattern.getOutput(null,null)).getAspects().aspects.entrySet()){
                    essentiaMonitor.extractEssentia(aspect.getKey(),((long)aspect.getValue()),Actionable.MODULATE,source,false);
                }
                    busy = false;
                } catch (Exception e) {

                }
            }
    }

    @Override
    public boolean isBusy() {
        return busy;
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        if(stack!=null)tag.setTag("stack",stack.writeToNBT(new NBTTagCompound()));
        super.writeToNBT(tag);
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        if(tag.hasKey("stack")){
            stack=ItemStack.loadItemStackFromNBT(tag.getCompoundTag("stack"));
        }
        super.readFromNBT(tag);
    }


}
