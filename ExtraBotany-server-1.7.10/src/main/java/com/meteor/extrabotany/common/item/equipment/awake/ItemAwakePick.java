/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.item.equipment.awake;

import com.meteor.extrabotany.common.core.util.ItemNBTHelper;
import java.awt.Color;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import vazkii.botania.api.item.ISequentialBreaker;
import vazkii.botania.api.mana.IManaItem;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.equipment.tool.manasteel.ItemManasteelPick;

public class ItemAwakePick
extends ItemManasteelPick
implements IManaItem,
ISequentialBreaker {
    private static final String TAG_MANA = "mana";
    private static final Integer MAX_MANA = Integer.MAX_VALUE;
    private static final int MANA_PER_DAMAGE = 100;
    private final Integer tick = null;
    IIcon[] icons;
    IIcon[] iconsDop;
    public static Item.ToolMaterial awakeToolMaterial = EnumHelper.addToolMaterial("AWAKE", 50, 2300, 9.0f, 3.0f, 26);
    public static final int[] LEVELS = new int[]{0, 10000, 1000000, 10000000, 100000000, 1000000000};
    private static final int[] RANGE = new int[]{1, 3, 5, 7, 9, 11};
    private static final int[] DEPTH = new int[]{1, 1, 3, 3, 5, 5};

    public ItemAwakePick() {
        super(awakeToolMaterial, "awakepick");
        this.setUnlocalizedName("awakepick");
        this.setTextureName("extrabotania:awakepick");
        this.setHarvestLevel("pickaxe", 50);
    }

    public int getColorFromItemStack(ItemStack par1ItemStack, int side) {
        int glub = ItemNBTHelper.getInteger(par1ItemStack, "thisGlub", 0);
        return side == 1 ? Color.HSBtoRGB(glub == 0 ? 0.3334f : (glub == 2 ? 0.5834f : 0.0f), 1.0f, 1.0f) : 0xFFFFFF;
    }

    public IIcon getIcon(ItemStack stack, int pass) {
        int rad = ItemNBTHelper.getInteger(stack, "thisRad", 0);
        if (rad < this.iconsDop.length) {
            this.icons[0] = this.iconsDop[rad];
        }
        return this.icons[Math.min(1, pass)];
    }

    public boolean requiresMultipleRenderPasses() {
        return true;
    }

    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        String rankFormat = StatCollector.translateToLocal("botaniamisc.toolRank");
        String rank = StatCollector.translateToLocal("botania.rank" + ItemAwakePick.getLevel(par1ItemStack));
        par3List.add(String.format(rankFormat, rank).replaceAll("&", "\u00a7"));
        if (this.getMana(par1ItemStack) == Integer.MAX_VALUE) {
            par3List.add(EnumChatFormatting.RED + StatCollector.translateToLocal("botaniamisc.getALife"));
        }
        if (ItemAwakePick.getLevel(par1ItemStack) < 5) {
            par3List.add(EnumChatFormatting.GRAY + StatCollector.translateToLocal("\u0422\u0440\u0435\u0431\u0443\u0435\u0442\u0441\u044f \u043c\u0430\u043d\u044b \u0434\u043e \u0440\u0430\u043d\u0433\u0430 \u00a7l" + this.getNextLvl(par1ItemStack) + "\u00a7r\u00a77: \u00a76\u00a7l" + this.getNeedMana(par1ItemStack)));
        }
        par3List.add(EnumChatFormatting.WHITE + StatCollector.translateToLocal(""));
        par3List.add(EnumChatFormatting.DARK_GREEN + StatCollector.translateToLocal("\u0422\u0435\u043a\u0443\u0449\u0438\u0439 \u0440\u0430\u0434\u0438\u0443\u0441 \u043a\u043e\u043f\u0430\u043d\u0438\u044f: \u00a76\u00a7l" + this.getThisRad(par1ItemStack)));
        par3List.add(EnumChatFormatting.DARK_GREEN + StatCollector.translateToLocal("\u0422\u0435\u043a\u0443\u0449\u0430\u044f \u0433\u043b\u0443\u0431\u0438\u043d\u0430 \u043a\u043e\u043f\u0430\u043d\u0438\u044f: \u00a76\u00a7l" + this.getThisGlub(par1ItemStack)));
        par3List.add(EnumChatFormatting.BLUE + StatCollector.translateToLocal("\u041c\u0430\u043a\u0441\u0438\u043c\u0430\u043b\u044c\u043d\u044b\u0439 \u0440\u0430\u0434\u0438\u0443\u0441 \u043a\u043e\u043f\u0430\u043d\u0438\u044f: \u00a76\u00a7l" + this.getMaxRad(par1ItemStack)));
        par3List.add(EnumChatFormatting.BLUE + StatCollector.translateToLocal("\u041c\u0430\u043a\u0441\u0438\u043c\u0430\u043b\u044c\u043d\u0430\u044f \u0433\u043b\u0443\u0431\u0438\u043d\u0430 \u043a\u043e\u043f\u0430\u043d\u0438\u044f: \u00a76\u00a7l" + this.getMaxGlub(par1ItemStack)));
    }

    private String getMaxGlub(ItemStack item) {
        int lvl = ItemAwakePick.getLevel(item);
        return Integer.toString(DEPTH[lvl]);
    }

    private String getNextLvl(ItemStack item) {
        int lvl = ItemAwakePick.getLevel(item) + 1;
        String rank = StatCollector.translateToLocal("botania.rank" + lvl);
        return rank.replaceAll("&", "\u00a7");
    }

    private String getNeedMana(ItemStack item) {
        int lvl = ItemAwakePick.getLevel(item);
        int mana = ItemAwakePick.getMana_(item);
        return Integer.toString(LEVELS[lvl + 1] - mana);
    }

    private String getThisRad(ItemStack item) {
        int thisRang = ItemNBTHelper.getInteger(item, "thisRad", 0);
        return RANGE[thisRang] + "x" + RANGE[thisRang];
    }

    private String getMaxRad(ItemStack item) {
        int lvl = ItemAwakePick.getLevel(item);
        return RANGE[lvl] + "x" + RANGE[lvl];
    }

    private String getThisGlub(ItemStack item) {
        int glub = ItemNBTHelper.getInteger(item, "thisGlub", 0);
        return Integer.toString(glub + 1);
    }

    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (!world.isRemote) {
            if (player.isSneaking()) {
                int rang = ItemAwakePick.getLevel(stack);
                int thisRad = ItemNBTHelper.getInteger(stack, "thisRad", 0);
                int newRad = thisRad + 1;
                if (newRad > rang) {
                    newRad = 0;
                }
                String s = RANGE[newRad] + "x" + RANGE[newRad];
                player.addChatMessage(new ChatComponentTranslation("\u00a7f[\u00a76\u041a\u0440\u0443\u0448\u0438\u0442\u0435\u043b\u044c \u0438\u0437 \u044d\u043b\u044c\u0444\u0438\u0440\u0438\u0443\u043c\u0430\u00a7f]: \u00a79\u0420\u0430\u0434\u0438\u0443\u0441 \u043a\u043e\u043f\u0430\u043d\u0438\u044f \u0443\u0441\u0442\u0430\u043d\u043e\u0432\u043b\u0435\u043d: \u00a72" + s));
                ItemNBTHelper.setInteger(stack, "thisRad", newRad);
            } else {
                int rang = ItemAwakePick.getLevel(stack);
                int glub = ItemNBTHelper.getInteger(stack, "thisGlub", 0);
                int newGlub = glub + 2;
                if (newGlub > 4 || newGlub >= 2 && rang < 2 || newGlub >= 4 && rang < 4) {
                    newGlub = 0;
                }
                player.addChatMessage(new ChatComponentTranslation("\u00a7f[\u00a76\u041a\u0440\u0443\u0448\u0438\u0442\u0435\u043b\u044c \u0438\u0437 \u044d\u043b\u044c\u0444\u0438\u0440\u0438\u0443\u043c\u0430\u00a7f]: \u00a79\u0413\u043b\u0443\u0431\u0438\u043d\u0430 \u043a\u043e\u043f\u0430\u043d\u0438\u044f \u0443\u0441\u0442\u0430\u043d\u043e\u0432\u043b\u043d\u0430: \u00a72" + (newGlub + 1)));
                ItemNBTHelper.setInteger(stack, "thisGlub", newGlub);
            }
        }
        return stack;
    }

    public boolean onBlockDestroyed(ItemStack stack, World p_150894_2_, Block p_150894_3_, int p_150894_4_, int p_150894_5_, int p_150894_6_, EntityLivingBase p_150894_7_) {
        return super.onBlockDestroyed(stack, p_150894_2_, p_150894_3_, p_150894_4_, p_150894_5_, p_150894_6_, p_150894_7_);
    }

    public static int getLevel(ItemStack stack) {
        int mana = ItemAwakePick.getMana_(stack);
        for (int i = LEVELS.length - 1; i > 0; --i) {
            if (mana < LEVELS[i]) continue;
            return i;
        }
        return 0;
    }

    public int getMana(ItemStack itemStack) {
        return ItemAwakePick.getMana_(itemStack);
    }

    public static int getMana_(ItemStack stack) {
        return vazkii.botania.common.core.helper.ItemNBTHelper.getInt(stack, TAG_MANA, 0);
    }

    public static void setMana(ItemStack stack, int mana) {
        vazkii.botania.common.core.helper.ItemNBTHelper.setInt(stack, TAG_MANA, mana);
    }

    public int getMaxMana(ItemStack itemStack) {
        return MAX_MANA;
    }

    public void addMana(ItemStack itemStack, int i) {
        ItemAwakePick.setMana(itemStack, Math.min(this.getMana(itemStack) + i, MAX_MANA));
    }

    public boolean canReceiveManaFromPool(ItemStack itemStack, TileEntity tileEntity) {
        return true;
    }

    public boolean canReceiveManaFromItem(ItemStack itemStack, ItemStack itemStack1) {
        return false;
    }

    public boolean canExportManaToPool(ItemStack itemStack, TileEntity tileEntity) {
        return false;
    }

    public boolean canExportManaToItem(ItemStack itemStack, ItemStack itemStack1) {
        return false;
    }

    public boolean isNoExport(ItemStack itemStack) {
        return true;
    }

    public void breakOtherBlock(EntityPlayer entityPlayer, ItemStack itemStack, int i, int i1, int i2, int i3, int i4, int i5, int i6) {
    }

    public boolean disposeOfTrashBlocks(ItemStack itemStack) {
        return false;
    }

    public void onUpdate(ItemStack stack, World world, Entity entity, int i, boolean b) {
        super.onUpdate(stack, world, entity, i, b);
        if (!(entity instanceof EntityPlayer)) {
            return;
        }
        if (stack.getItemDamage() > 0 && ManaItemHandler.requestManaExact(new ItemStack(ModItems.manaRing), (EntityPlayer) entity, 100, true)) {
            stack.setItemDamage(stack.getItemDamage() - 1);
        }
    }
}

