/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.world.World
 */
package net.divinerpg.entities.vethea;

import net.divinerpg.entities.base.EntityGive;
import net.divinerpg.utils.Util;
import net.divinerpg.utils.items.VetheaItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityMysteriousManLayer1
extends EntityGive {
    public int spawnLayer = 1;

    public EntityMysteriousManLayer1(World par1) {
        super(par1, VetheaItems.teakerLump, 3);
    }

    @Override
    public boolean getCanSpawnHere() {
        return this.posY < 48.0 * (double)this.spawnLayer && this.posY > 48.0 * (double)(this.spawnLayer - 1) && super.getCanSpawnHere();
    }

    @Override
    public void Interact(EntityPlayer var1) {
        switch (this.rand.nextInt(3)) {
            case 0: {
                var1.addChatMessage(Util.getChatComponent("Mysterious Man: You look new around here. Be careful."));
                break;
            }
            case 1: {
                var1.addChatMessage(Util.getChatComponent("Mysterious Man: Take these, use them at an infusion table."));
                break;
            }
            case 2: {
                var1.addChatMessage(Util.getChatComponent("Mysterious Man: Don't tell anybody you saw me."));
            }
        }
    }

    @Override
    public String mobName() {
        return "Mysterious Man";
    }
}

