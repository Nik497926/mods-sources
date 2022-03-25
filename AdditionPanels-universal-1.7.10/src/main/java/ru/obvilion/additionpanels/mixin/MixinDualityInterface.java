package ru.obvilion.additionpanels.mixin;

import appeng.api.networking.crafting.ICraftingPatternDetails;
import appeng.api.networking.crafting.ICraftingProvider;
import appeng.api.networking.events.MENetworkCraftingPatternChange;
import appeng.helpers.DualityInterface;
import appeng.me.GridAccessException;
import appeng.me.helpers.AENetworkProxy;
import appeng.tile.inventory.AppEngInternalInventory;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Mixin(value = DualityInterface.class, remap = false)
public abstract class MixinDualityInterface {

    @Shadow(remap = false) @Final private AppEngInternalInventory patterns;
    @Shadow(remap = false)  @Final private AENetworkProxy gridProxy;
    @Shadow(remap = false)  private List<ICraftingPatternDetails> craftingList;
    @Shadow(remap = false)  protected abstract void addToCraftingList(ItemStack is);

    @Overwrite(remap = false)
    private void updateCraftingList() {
        Boolean[] accountedFor = new Boolean[this.patterns.getSizeInventory()];
        Arrays.fill(accountedFor, false);
        assert accountedFor.length == this.patterns.getSizeInventory();

        if (this.gridProxy.isReady()) {
            if (this.craftingList != null) {
                Iterator i = this.craftingList.iterator();

                while(i.hasNext()) {
                    ICraftingPatternDetails details = (ICraftingPatternDetails)i.next();
                    boolean found = false;

                    for(int x = 0; x < accountedFor.length; ++x) {
                        ItemStack is = this.patterns.getStackInSlot(x);
                        if (details.getPattern() == is) {
                            found = true;
                            accountedFor[x] = true;
                        }
                    }

                    if (!found) {
                        i.remove();
                    }
                }
            }

            for(int x = 0; x < accountedFor.length; ++x) {
                if (!accountedFor[x]) {
                    this.addToCraftingList(this.patterns.getStackInSlot(x));
                }
            }

            try {
                this.gridProxy.getGrid().postEvent(new MENetworkCraftingPatternChange((ICraftingProvider) this, this.gridProxy.getNode()));
            } catch (GridAccessException var7) {
            }

        }
    }
}
