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
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import ua.limon4ik.LegendaryHorizons;
import ua.limon4ik.tiles.pattern.BotaniaElvenTradePattern;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.mana.IManaPool;
import ru.justagod.cutter.*;

public class TileEntityAutoElvenTrade extends AENetworkTile implements ICraftingProvider {
    private boolean busy=false;
    private IManaPool pool;
    private MachineSource source;
    private BotaniaElvenTradePattern pattern;
    public TileEntityAutoElvenTrade(World w){
        this();
        worldObj=w;
    }
    public TileEntityAutoElvenTrade(){
        getProxy().setFlags(GridFlags.REQUIRE_CHANNEL);
        getProxy().setIdlePowerUsage(1000);
        source=new MachineSource(this);
    }
    @Override
    public void provideCrafting(ICraftingProviderHelper iCraftingProviderHelper) {
        BotaniaAPI.elvenTradeRecipes.forEach(recipe->{
			ItemStack out=recipe.getOutput();
			boolean add=true;
			for( Object o : recipe.getInputs()){
				ItemStack in=null;
				if(o instanceof ItemStack){
					in=(ItemStack) o;
				}else if(o instanceof String){
					String name=(String)o;
					if(OreDictionary.doesOreNameExist(name)){
						in = OreDictionary.getOres(name).get(0);
					}
				}
				if(in!=null){
					if(in.getItem().equals(out.getItem()) && in.getItemDamage()==out.getItemDamage()){
						add=false;
						break;
					}
				}
			}
		
            if(add){
				iCraftingProviderHelper.addCraftingOption(this,new BotaniaElvenTradePattern(recipe));
			}
        });
    }

    @Override
    public boolean pushPattern(ICraftingPatternDetails iCraftingPatternDetails, InventoryCrafting inventoryCrafting) {
        if(worldObj==null)return false;
        TileEntity te=worldObj.getTileEntity(xCoord,yCoord+1,zCoord);
        if(te instanceof IManaPool){
            pool= (IManaPool) te;
        }else {
            pool=null;
        }
        if(iCraftingPatternDetails instanceof BotaniaElvenTradePattern){
            BotaniaElvenTradePattern pattern= (BotaniaElvenTradePattern) iCraftingPatternDetails;
            if(pool!=null){
                if(pool.getCurrentMana()>=2000){
                    try {
                        IStorageGrid grid=getProxy().getStorage();
                            IAEItemStack i=grid.getItemInventory().injectItems(pattern.getOutputs()[0],Actionable.SIMULATE,source);
                            if(i==null || i.getStackSize()<=0){
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
                /*for (IAEItemStack stack : pattern.getInputs()) {
                    grid.getItemInventory().extractItems(stack, Actionable.MODULATE,source);
                }*/
                    grid.getItemInventory().injectItems(pattern.getOutputs()[0], Actionable.MODULATE, source);
                    pool.recieveMana(-2000);
                    busy = false;
                } catch (GridAccessException e) {
                    e.printStackTrace();
                }
            }
    }
}