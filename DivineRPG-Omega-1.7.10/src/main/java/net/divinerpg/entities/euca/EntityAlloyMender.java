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
package net.divinerpg.entities.euca;

import net.divinerpg.DivineRPG;
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

public class EntityAlloyMender
extends EntityDivineRPGVillager {
    private static final String[] MESSAGE = new String[]{"message.alloymender.one", "message.alloymender.two", "message.alloymender.three"};

    public EntityAlloyMender(World var1) {
        super(var1);
    }

    @Override
    public void extraInteract(EntityPlayer p) {
        p.addChatMessage(Util.getChatComponent(MessageLocalizer.normal("alloymender.name") + " " + MessageLocalizer.normal(MESSAGE[this.rand.nextInt(3)])));
    }

    @Override
    public boolean interact(EntityPlayer var1) {
        this.extraInteract(var1);
        var1.openGui((Object)DivineRPG.instance, this.guiID(), this.worldObj, this.getEntityId(), 0, 0);
        return true;
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
    public int guiID() {
        return GuiHandler.alloyMender;
    }

    @Override
    public void addRecipies(MerchantRecipeList list) {
        list.add((Object)new MerchantRecipe(new ItemStack(Items.stick, 10), new ItemStack(VanillaItemsOther.repairEssence, 4), new ItemStack(JourneyItemsWeapon.poisonSword)));
        list.add((Object)new MerchantRecipe(new ItemStack(Items.stick, 4), new ItemStack(VanillaItemsOther.repairEssence, 20), new ItemStack(JourneyItemsWeapon.cloudSlicer)));
        list.add((Object)new MerchantRecipe(new ItemStack(VanillaItemsOther.repairEssence, 64), new ItemStack(VanillaItemsOther.repairEssence, 64), new ItemStack(VanillaItemsOther.clanScroll)));
    }

    @Override
    public String mobName() {
        return "Alloy Mender";
    }

    @Override
    public boolean canDespawn() {
        return false;
    }
}

