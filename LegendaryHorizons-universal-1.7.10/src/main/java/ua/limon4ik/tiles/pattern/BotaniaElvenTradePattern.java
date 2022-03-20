package ua.limon4ik.tiles.pattern;

import appeng.api.AEApi;
import appeng.api.networking.crafting.ICraftingPatternDetails;
import appeng.api.storage.data.IAEItemStack;
import com.google.common.collect.Ordering;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import thaumicenergistics.common.items.ItemEnum;
import vazkii.botania.api.recipe.RecipeElvenTrade;

import java.util.ArrayList;
import java.util.Comparator;

public class BotaniaElvenTradePattern  implements ICraftingPatternDetails {
    private static ItemStack pattern=new ItemStack(ItemEnum.KNOWLEDGE_CORE.getItem(),1,0);
    private RecipeElvenTrade recipe;
    private IAEItemStack[] inputs;
    public BotaniaElvenTradePattern(RecipeElvenTrade recipe){
        this.recipe=recipe;
        ArrayList<IAEItemStack> list=new ArrayList<>();
        recipe.getInputs().forEach(a->{
            if(a instanceof ItemStack){
                ItemStack stack= (ItemStack) a;
                list.add(AEApi.instance().storage().createItemStack(stack));
            }else if(a instanceof String){
                ArrayList<ItemStack> l= OreDictionary.getOres((String) a);
                if(l.size()>=1){
                    list.add(AEApi.instance().storage().createItemStack(l.get(0)));
                }
            }
        });
        inputs= list.toArray(new IAEItemStack[list.size()]);
    }

    public RecipeElvenTrade getRecipe() {
        return recipe;
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
        return getOutputs();
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
}