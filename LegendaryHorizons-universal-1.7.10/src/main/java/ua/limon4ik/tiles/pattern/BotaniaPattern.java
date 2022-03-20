package ua.limon4ik.tiles.pattern;

import appeng.api.AEApi;
import appeng.api.networking.crafting.ICraftingPatternDetails;
import appeng.api.storage.data.IAEItemStack;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import thaumicenergistics.common.items.ItemEnum;
import vazkii.botania.api.recipe.RecipeManaInfusion;

import java.util.ArrayList;

public class BotaniaPattern implements ICraftingPatternDetails {
    private static ItemStack pattern=new ItemStack(ItemEnum.KNOWLEDGE_CORE.getItem(),1,0);
    private RecipeManaInfusion recipe;
    private IAEItemStack[] inputs;
    public BotaniaPattern(RecipeManaInfusion recipe) {
        this.recipe = recipe;
        if(recipe.getInput() instanceof String){
            ArrayList<ItemStack> l= OreDictionary.getOres((String) recipe.getInput());
            if(l.size()>=1){
                inputs= new IAEItemStack[]{(AEApi.instance().storage().createItemStack(l.get(0)))};
            }
        }
        inputs= new IAEItemStack[]{AEApi.instance().storage().createItemStack((ItemStack) recipe.getInput())};
    }

    @Override
    public ItemStack getPattern() {
        return pattern;
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
        return inputs;
    }

    @Override
    public IAEItemStack[] getCondensedInputs() {
        return getInputs();
    }

    @Override
    public IAEItemStack[] getCondensedOutputs() {
        return new IAEItemStack[]{AEApi.instance().storage().createItemStack(recipe.getOutput())};
    }

    @Override
    public IAEItemStack[] getOutputs() {
        return new IAEItemStack[]{AEApi.instance().storage().createItemStack(recipe.getOutput())};
    }

    @Override
    public boolean canSubstitute() {
        return true;
    }

    @Override
    public ItemStack getOutput(InventoryCrafting inventoryCrafting, World world) {
        return recipe.getOutput();
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public void setPriority(int i) {

    }

    public RecipeManaInfusion getRecipe() {
        return recipe;
    }
}
