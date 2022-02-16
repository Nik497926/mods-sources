/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.village.MerchantRecipe
 *  net.minecraft.village.MerchantRecipeList
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.arcana;

import net.divinerpg.client.GuiHandler;
import net.divinerpg.entities.base.EntityDivineRPGVillager;
import net.divinerpg.utils.MessageLocalizer;
import net.divinerpg.utils.Util;
import net.divinerpg.utils.items.ArcanaItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;

public class EntityWarGeneral
extends EntityDivineRPGVillager {
    private static final String[] MESSAGE = new String[]{"message.general.weapons", "message.general.1", "message.general.2"};

    public EntityWarGeneral(World w) {
        super(w);
    }

    public boolean getCanSpawnHere() {
        return this.rand.nextInt(3) == 0 && this.posY < 40.0 && this.worldObj.checkNoEntityCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes((Entity)this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox);
    }

    @Override
    public void extraInteract(EntityPlayer p) {
        p.addChatMessage(Util.getChatComponent("War General: " + MessageLocalizer.normal(MESSAGE[this.rand.nextInt(3)])));
    }

    @Override
    public int guiID() {
        return GuiHandler.warGeneral;
    }

    @Override
    protected boolean canDespawn() {
        return true;
    }

    @Override
    public void addRecipies(MerchantRecipeList list) {
        list.add((Object)new MerchantRecipe(new ItemStack(ArcanaItems.arcanium, 4), new ItemStack(ArcanaItems.divineAccumulator)));
        list.add((Object)new MerchantRecipe(new ItemStack(ArcanaItems.dungeonTokens, 6), new ItemStack(ArcanaItems.meteorMash)));
        list.add((Object)new MerchantRecipe(new ItemStack(ArcanaItems.arcanium, 8), new ItemStack(ArcanaItems.arcaniteBlaster)));
        list.add((Object)new MerchantRecipe(new ItemStack(ArcanaItems.arcanium, 10), new ItemStack(ArcanaItems.arcaniteBlade)));
        list.add((Object)new MerchantRecipe(new ItemStack(ArcanaItems.arcanium, 10), new ItemStack(ArcanaItems.generalsStaff)));
        list.add((Object)new MerchantRecipe(new ItemStack(ArcanaItems.arcanium, 7), new ItemStack(ArcanaItems.reflector, 1, 0)));
        list.add((Object)new MerchantRecipe(new ItemStack(ArcanaItems.arcanium, 7), new ItemStack(ArcanaItems.attractor, 1, 0)));
    }

    @Override
    public String mobName() {
        return "War General";
    }
}

