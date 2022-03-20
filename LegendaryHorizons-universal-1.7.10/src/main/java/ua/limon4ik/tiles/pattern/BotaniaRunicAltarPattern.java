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
import vazkii.botania.api.recipe.RecipeRuneAltar;
import vazkii.botania.common.block.ModBlocks;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BotaniaRunicAltarPattern implements ICraftingPatternDetails {
    private static ItemStack pattern=new ItemStack(ItemEnum.KNOWLEDGE_CORE.getItem(),1,0);
    private RecipeRuneAltar recipe;
    private IAEItemStack[] inputs;
    public BotaniaRunicAltarPattern(RecipeRuneAltar recipe){
        this.recipe=recipe;
        ArrayList<IAEItemStack> list=new ArrayList<>();
        recipe.getInputs().forEach(a->{
            if(a instanceof ItemStack){
                ItemStack stack= (ItemStack) a;
                list.add(AEApi.instance().storage().createItemStack(stack));
            }else if(a instanceof String){
                ArrayList<ItemStack> l= OreDictionary.getOres((String) a);
                Comparator<ItemStack> comparator= Ordering.explicit(l);
                l.sort(comparator);
                if(l.size()>=1){
                    list.add(AEApi.instance().storage().createItemStack(l.get(0)));
                }
            }
        });
        list.add(AEApi.instance().storage().createItemStack(new ItemStack(ModBlocks.livingrock,1)));
        inputs= list.toArray(new IAEItemStack[list.size()]);
    }

    public RecipeRuneAltar getRecipe() {
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
