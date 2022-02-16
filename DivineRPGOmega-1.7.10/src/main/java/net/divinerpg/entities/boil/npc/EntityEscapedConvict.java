/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraft.village.MerchantRecipe
 *  net.minecraft.village.MerchantRecipeList
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.boil.npc;

import net.divinerpg.client.GuiHandler;
import net.divinerpg.entities.base.EntityDivineRPGVillager;
import net.divinerpg.utils.items.JourneyItemsOther;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;

public class EntityEscapedConvict
extends EntityDivineRPGVillager {
    private static final String[] MESSAGE = new String[]{"message.escapedConvict.one", "message.escapedConvict.two", "message.escapedConvict.three"};

    public EntityEscapedConvict(World var1) {
        super(var1);
    }

    @Override
    protected String getLivingSound() {
        return this.isTrading() ? "mob.villager.haggle" : "mob.villager.idle";
    }

    @Override
    protected String getHurtSound() {
        return "mob.villager.hit";
    }

    @Override
    protected String getDeathSound() {
        return "mob.villager.death";
    }

    @Override
    public void extraInteract(EntityPlayer p) {
    }

    @Override
    public int guiID() {
        return GuiHandler.escapedConvict;
    }

    @Override
    public void addRecipies(MerchantRecipeList list) {
        list.add((Object)new MerchantRecipe(new ItemStack(JourneyItemsOther.boilGem, 16), new ItemStack(Items.blaze_rod, 8), new ItemStack(VanillaItemsOther.enderBlazeSpawner)));
    }

    @Override
    public String mobName() {
        return "EscapedConvict";
    }
}

