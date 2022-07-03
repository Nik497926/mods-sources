/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.item.relic.legendary;

import com.meteor.extrabotany.common.item.ModItems;
import com.meteor.extrabotany.common.item.relic.ItemRelicAdv;
import java.util.List;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import vazkii.botania.api.item.IRelic;
import vazkii.botania.common.core.helper.ItemNBTHelper;

public class ItemDice6
extends ItemRelicAdv {
    public static ItemStack[] relicStacks;
    private int cd = 0;

    public ItemDice6() {
        super("dice6");
        relicStacks = new ItemStack[]{new ItemStack(vazkii.botania.common.item.ModItems.infiniteFruit), new ItemStack(vazkii.botania.common.item.ModItems.kingKey), new ItemStack(vazkii.botania.common.item.ModItems.flugelEye), new ItemStack(vazkii.botania.common.item.ModItems.thorRing), new ItemStack(vazkii.botania.common.item.ModItems.odinRing), new ItemStack(vazkii.botania.common.item.ModItems.lokiRing)};
    }

    public void onUpdate(ItemStack stack, World world, Entity entity, int slotID, boolean par1) {
        super.onUpdate(stack, world, entity, slotID, par1);
        if (this.cd > 0) {
            --this.cd;
        }
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean bol) {
        super.addInformation(stack, player, list, bol);
        list.add("");
        list.add(EnumChatFormatting.BLUE + "\u0415\u0441\u0442\u044c \u0448\u0430\u043d\u0441 \u043f\u043e\u043b\u0443\u0447\u0435\u043d\u0438\u044f \u043d\u0435 \u0441\u0432\u043e\u0435\u0439 \u0440\u0435\u043b\u0438\u043a\u0432\u0438\u0438");
        if (!GuiScreen.isCtrlKeyDown()) {
            list.add(EnumChatFormatting.BLUE + "\u041d\u0430\u0436\u043c\u0438\u0442\u0435 LCTRL \u0434\u043b\u044f \u043f\u043e\u0434\u0440\u043e\u0431\u043d\u043e\u0441\u0442\u0435\u0439");
        }
        if (GuiScreen.isCtrlKeyDown()) {
            list.add(EnumChatFormatting.DARK_GREEN + "\u0428\u0430\u043d\u0441 0.5% \u043f\u043e\u043b\u0443\u0447\u0435\u043d\u0438\u044f \u0441\u043b\u0438\u0442\u043a\u0430 \u044d\u043b\u044c\u0444\u0438\u0440\u0438\u0443\u043c\u0430");
            list.add(EnumChatFormatting.DARK_GREEN + "\u0428\u0430\u043d\u0441 1% \u043f\u043e\u043b\u0443\u0447\u0435\u043d\u0438\u044f \u0440\u0435\u043b\u0438\u043a\u0432\u0438\u0438 Vazkii (\u0430\u0432\u0442\u043e\u0440 Botania)");
            list.add(EnumChatFormatting.DARK_GREEN + "\u0428\u0430\u043d\u0441 1% \u043f\u043e\u043b\u0443\u0447\u0435\u043d\u0438\u044f \u0440\u0435\u043b\u0438\u043a\u0432\u0438\u0438 ECMAScript");
            list.add(EnumChatFormatting.DARK_GREEN + "\u0428\u0430\u043d\u0441 2% \u0434\u043e\u043f\u043e\u043b\u043d\u0438\u0442\u0435\u043b\u044c\u043d\u043e \u043f\u043e\u043b\u0443\u0447\u0438\u0442\u044c \u0437\u0430\u043f\u043e\u043b\u043d\u0435\u043d\u043d\u044b\u0439 \u043f\u043b\u0430\u043d\u0448\u0435\u0442 \u043c\u0430\u043d\u044b");
            list.add(EnumChatFormatting.DARK_GREEN + "\u0428\u0430\u043d\u0441 3% \u0441\u043e\u0445\u0440\u0430\u043d\u0435\u043d\u0438\u044f \u043a\u043e\u0440\u043e\u0431\u043a\u0438 D6");
            list.add(EnumChatFormatting.DARK_GREEN + "\u0428\u0430\u043d\u0441 5% \u043f\u043e\u043b\u0443\u0447\u0438\u0442\u044c \u0434\u043e\u043f\u043e\u043b\u043d\u0438\u0442\u0435\u043b\u044c\u043d\u043e \u0440\u0435\u0437\u043e\u043d\u0430\u043d\u0441 \u043c\u043e\u043b\u0438\u0442\u0432\u044b");
            list.add(EnumChatFormatting.DARK_GREEN + "\u0428\u0430\u043d\u0441 5% \u043f\u043e\u043b\u0443\u0447\u0438\u0442\u044c \u0434\u043e\u043f\u043e\u043b\u043d\u0438\u0442\u0435\u043b\u044c\u043d\u043e \u0441\u0443\u0449\u043d\u043e\u0441\u0442\u044c \u0433\u0430\u0439\u0438");
            list.add(EnumChatFormatting.DARK_GREEN + "\u0428\u0430\u043d\u0441 10% \u043f\u043e\u043b\u0443\u0447\u0438\u0442\u044c \u0434\u043e\u043f\u043e\u043b\u043d\u0438\u0442\u0435\u043b\u044c\u043d\u043e \u0433\u043e\u043b\u043e\u0432\u0443 \u0433\u0430\u0439\u0438");
        }
    }

    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (ItemDice6.isRightPlayer((EntityPlayer)player, (ItemStack)stack) && !world.isRemote) {
            if (Math.random() < 0.005) {
                EntityItem var1 = new EntityItem(world, player.posX, player.posY, player.posZ, new ItemStack(ModItems.elfirium));
                world.spawnEntityInWorld((Entity)var1);
                return null;
            }
            if (Math.random() < 0.01) {
                int rand = world.rand.nextInt(relicStacks.length);
                ItemStack var0 = relicStacks[rand];
                ((IRelic)var0.getItem()).bindToUsername("vazkii", var0);
                return var0;
            }
            if (Math.random() < 0.01) {
                int rand = world.rand.nextInt(relicStacks.length);
                ItemStack var0 = relicStacks[rand];
                ((IRelic)var0.getItem()).bindToUsername("ECMAScript", var0);
                return var0;
            }
            if (Math.random() < 0.02) {
                int rand = world.rand.nextInt(relicStacks.length);
                ItemStack var0 = new ItemStack(vazkii.botania.common.item.ModItems.manaTablet);
                ItemNBTHelper.setInt((ItemStack)var0, (String)"mana", (int)500000);
                EntityItem var2 = new EntityItem(world, player.posX, player.posY, player.posZ, var0);
                world.spawnEntityInWorld((Entity)var2);
                return relicStacks[rand];
            }
            if (Math.random() < 0.03) {
                int rand = world.rand.nextInt(relicStacks.length);
                ItemStack var0 = relicStacks[rand];
                EntityItem var1 = new EntityItem(world, player.posX, player.posY, player.posZ, var0);
                world.spawnEntityInWorld((Entity)var1);
                return stack;
            }
            if (Math.random() < 0.05) {
                int rand = world.rand.nextInt(relicStacks.length);
                EntityItem var2 = new EntityItem(world, player.posX, player.posY, player.posZ, new ItemStack(ModItems.material, 1, 9));
                world.spawnEntityInWorld((Entity)var2);
                return relicStacks[rand];
            }
            if (Math.random() < 0.05) {
                int rand = world.rand.nextInt(relicStacks.length);
                EntityItem var2 = new EntityItem(world, player.posX, player.posY, player.posZ, new ItemStack(ModItems.material, 1, 2));
                world.spawnEntityInWorld((Entity)var2);
                return relicStacks[rand];
            }
            if (Math.random() < 0.1) {
                int rand = world.rand.nextInt(relicStacks.length);
                EntityItem var2 = new EntityItem(world, player.posX, player.posY, player.posZ, new ItemStack(vazkii.botania.common.item.ModItems.gaiaHead));
                world.spawnEntityInWorld((Entity)var2);
                return relicStacks[rand];
            }
            int rand = world.rand.nextInt(relicStacks.length);
            return relicStacks[rand];
        }
        return stack;
    }

    public boolean shouldDamageWrongPlayer() {
        return false;
    }
}

