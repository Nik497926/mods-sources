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
package net.divinerpg.entities.vanilla;

import net.divinerpg.client.GuiHandler;
import net.divinerpg.entities.base.EntityDivineRPGVillager;
import net.divinerpg.utils.MessageLocalizer;
import net.divinerpg.utils.Util;
import net.divinerpg.utils.items.JourneyItemsWeapon;
import net.divinerpg.utils.items.VanillaItemsOther;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;

public class EntityJourneyBlacksmith
extends EntityDivineRPGVillager {
    private static final String[] MESSAGE = new String[]{"smith.valuables", "smith.valuables", "smith.greetings"};

    public EntityJourneyBlacksmith(World var1) {
        super(var1);
    }

    @Override
    public void extraInteract(EntityPlayer p) {
        p.addChatMessage(Util.getChatComponent(MessageLocalizer.normal("smith.name") + " " + MessageLocalizer.normal(MESSAGE[this.rand.nextInt(3)])));
    }

    @Override
    public int guiID() {
        return GuiHandler.blacksmith;
    }

    @Override
    public void addRecipies(MerchantRecipeList list) {
        list.add((Object)new MerchantRecipe(new ItemStack(Items.stick, 10), new ItemStack(VanillaItemsOther.repairEssence, 4), new ItemStack(JourneyItemsWeapon.poisonSword)));
        list.add((Object)new MerchantRecipe(new ItemStack(Items.stick, 10), new ItemStack(VanillaItemsOther.repairEssence, 20), new ItemStack(JourneyItemsWeapon.cloudSlicer)));
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
    public String mobName() {
        return "Blacksmith";
    }

    @Override
    public boolean canDespawn() {
        return false;
    }
}

