/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.village.MerchantRecipeList
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vanilla;

import net.divinerpg.client.GuiHandler;
import net.divinerpg.entities.base.EntityDivineRPGVillager;
import net.divinerpg.utils.MessageLocalizer;
import net.divinerpg.utils.Util;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;

public class EntityJourneyMage
extends EntityDivineRPGVillager {
    private static final String[] MESSAGE = new String[]{"mage.valuables", "mage.greetings", "mage.deals"};

    public EntityJourneyMage(World var1) {
        super(var1);
    }

    @Override
    public void extraInteract(EntityPlayer p) {
        p.addChatMessage(Util.getChatComponent(MessageLocalizer.normal("mage.name") + " " + MessageLocalizer.normal(MESSAGE[this.rand.nextInt(3)])));
    }

    @Override
    public int guiID() {
        return GuiHandler.mage;
    }

    @Override
    public void addRecipies(MerchantRecipeList list) {
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
        return "JouneyMage";
    }

    @Override
    public boolean canDespawn() {
        return false;
    }
}

