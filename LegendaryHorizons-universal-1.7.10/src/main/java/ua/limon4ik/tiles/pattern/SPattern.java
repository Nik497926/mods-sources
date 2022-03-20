package ua.limon4ik.tiles.pattern;

import appeng.api.AEApi;
import appeng.api.networking.crafting.ICraftingPatternDetails;
import appeng.api.storage.data.IAEItemStack;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumicenergistics.common.items.ItemEnum;

import java.util.ArrayList;
import java.util.Arrays;

public class SPattern implements ICraftingPatternDetails {
    private ItemStack out;
    private static ItemStack core=new ItemStack(ItemEnum.KNOWLEDGE_CORE.getItem(),1,0);
    private InfusionRecipe recipe;
    public SPattern(ItemStack i){
        out=i;
        recipe= ThaumcraftApi.getInfusionRecipe(out);
    }
    @Override
    public ItemStack getPattern() {
        return core;
    }

    @Override
    public boolean isValidItemForSlot(int i, ItemStack itemStack, World world) {
        return true;
    }

    @Override
    public boolean isCraftable() {
        return false;
    }

    @Override
    public IAEItemStack[] getInputs() {
        ArrayList<IAEItemStack> cond=new ArrayList<>();
        Arrays.stream(recipe.getComponents()).forEach(t->{
            if(t!=null) {
                IAEItemStack a = AEApi.instance().storage().createItemStack(t);
                cond.add(a);
            }
        });
        cond.add(AEApi.instance().storage().createItemStack(recipe.getRecipeInput()));
        /*recipe.getAspects().aspects.forEach((k,v)->{
            cond.add(AEApi.instance().storage().createItemStack(ItemCraftingAspect.createStackForAspect(k,v)));
        });*/
        return cond.toArray(new IAEItemStack[cond.size()]);
        //return new IAEItemStack[]{AEApi.instance().storage().createItemStack(new ItemStack(Items.diamond,10))};
    }

    @Override
    public IAEItemStack[] getCondensedInputs() {
        ArrayList<IAEItemStack> cond=new ArrayList<>();
        Arrays.stream(recipe.getComponents()).forEach(t->{
            if(t!=null) {
                IAEItemStack a = AEApi.instance().storage().createItemStack(t);
                if(a!=null){
                    cond.add(a);
                }
            }
        });
        /*recipe.getAspects().aspects.forEach((k,v)->{
            cond.add(AEApi.instance().storage().createItemStack(ItemCraftingAspect.createStackForAspect(k,v)));
        });*/
        cond.add(AEApi.instance().storage().createItemStack(recipe.getRecipeInput()));
        return cond.toArray(new IAEItemStack[cond.size()]);
        //return new IAEItemStack[]{AEApi.instance().storage().createItemStack(new ItemStack(Items.diamond,10))};
    }


    @Override
    public IAEItemStack[] getCondensedOutputs() {
        return new IAEItemStack[]{AEApi.instance().storage().createItemStack((ItemStack) recipe.getRecipeOutput())};
    }

    @Override
    public IAEItemStack[] getOutputs() {
        return new IAEItemStack[]{AEApi.instance().storage().createItemStack((ItemStack) recipe.getRecipeOutput())};
    }

    @Override
    public boolean canSubstitute() {
        return true;
    }

    @Override
    public ItemStack getOutput(InventoryCrafting inventoryCrafting, World world) {
        return (ItemStack) recipe.getRecipeOutput();
    }
	
    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public void setPriority(int i) {

    }
	public InfusionRecipe getRecipe(){
		return recipe;
	}
}