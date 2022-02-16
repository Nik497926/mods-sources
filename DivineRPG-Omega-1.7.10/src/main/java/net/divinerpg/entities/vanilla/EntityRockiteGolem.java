/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.village.MerchantRecipeList
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vanilla;

import net.divinerpg.client.GuiHandler;
import net.divinerpg.entities.base.EntityDivineRPGVillager;
import net.divinerpg.libs.Sounds;
import net.divinerpg.utils.MessageLocalizer;
import net.divinerpg.utils.Util;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.World;

public class EntityRockiteGolem
extends EntityDivineRPGVillager {
    private static final String[] MESSAGE = new String[]{"rockite.valuables", "rockite.valuables", "rockite.greetings"};

    public EntityRockiteGolem(World var1) {
        super(var1);
    }

    @Override
    public void extraInteract(EntityPlayer p) {
        p.addChatMessage(Util.getChatComponent(MessageLocalizer.normal("rockite.name") + " " + MessageLocalizer.normal(MESSAGE[this.rand.nextInt(3)])));
    }

    @Override
    public int guiID() {
        return GuiHandler.rockite;
    }

    @Override
    public void addRecipies(MerchantRecipeList list) {
    }

    @Override
    protected String getLivingSound() {
        return Sounds.getSoundName(Sounds.rockiteGolem);
    }

    @Override
    protected String getHurtSound() {
        return Sounds.getSoundName(Sounds.rock);
    }

    @Override
    protected String getDeathSound() {
        return Sounds.getSoundName(Sounds.caveMob);
    }

    @Override
    public String mobName() {
        return "RockiteGolem";
    }

    @Override
    public boolean canDespawn() {
        return true;
    }

    public boolean getCanSpawnHere() {
        return this.posY < 50.0 && super.getCanSpawnHere() && this.worldObj.getBlock((int)this.posX, (int)this.posY - 1, (int)this.posZ) == Blocks.stone;
    }
}

