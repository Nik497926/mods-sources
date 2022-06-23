/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.simplecore.mod.common.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.annotation.Nonnull;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;
import ru.simplemc.simplecore.mod.SimpleCore;
import ru.simplemc.simplecore.mod.common.item.ItemBase;

public class ItemBackpack
extends ItemBase {
    private int randomColor = 0xFFFFFF;
    private long randomColorChangedTimestamp = 0L;

    public ItemBackpack() {
        super("backpack");
        this.setNoRepair();
        this.setMaxDamage(0);
        this.setMaxStackSize(1);
        this.setHasSubtypes(false);
    }

    @Nonnull
    public static NBTTagCompound getPreparedTagCompound(@Nonnull ItemStack itemStack) {
        if (!itemStack.hasTagCompound()) {
            itemStack.setTagCompound(new NBTTagCompound());
        }
        return itemStack.getTagCompound();
    }

    public static boolean isLocked(@Nonnull ItemStack itemStack) {
        NBTTagCompound compound = ItemBackpack.getPreparedTagCompound(itemStack);
        return compound.hasKey("Locked") && compound.getBoolean("Locked");
    }

    public static void setLocked(@Nonnull ItemStack itemStack, boolean value) {
        NBTTagCompound compound = ItemBackpack.getPreparedTagCompound(itemStack);
        compound.setBoolean("Locked", value);
    }

    public static boolean hasAccess(@Nonnull ItemStack itemStack, @Nonnull EntityPlayer player) {
        NBTTagCompound compound = ItemBackpack.getPreparedTagCompound(itemStack);
        if (compound.hasKey("Locked") && compound.getBoolean("Locked")) {
            boolean hasOwnerAccess = ItemBackpack.isOwner(itemStack, player);
            if (!hasOwnerAccess && !player.worldObj.isRemote && SimpleCore.PROXY.isIntegrated()) {
                return SimpleCore.PROXY.getIntegrationManager().hasPermission(player, "group.headmoder");
            }
            return hasOwnerAccess;
        }
        return true;
    }

    public static boolean isOwner(@Nonnull ItemStack itemStack, @Nonnull EntityPlayer player) {
        Optional<String> optionalOwner = ItemBackpack.getOwner(itemStack);
        return optionalOwner.map(s -> s.equals(player.getCommandSenderName())).orElse(true);
    }

    public static void setOwner(@Nonnull ItemStack itemStack, EntityPlayer player) {
        NBTTagCompound compound = ItemBackpack.getPreparedTagCompound(itemStack);
        compound.setString("Owner", player.getCommandSenderName());
    }

    public static Optional<String> getOwner(@Nonnull ItemStack itemStack) {
        NBTTagCompound compound = ItemBackpack.getPreparedTagCompound(itemStack);
        return compound.hasKey("Owner") ? Optional.of(compound.getString("Owner")) : Optional.empty();
    }

    public static Optional<String> getUUID(@Nonnull ItemStack itemStack) {
        NBTTagCompound compound = ItemBackpack.getPreparedTagCompound(itemStack);
        return compound.hasKey("UUID") ? Optional.of(compound.getString("UUID")) : Optional.empty();
    }

    public static boolean isSameBackpacks(ItemStack itemStack1, ItemStack itemStack2) {
        if (itemStack1 == null || itemStack2 == null) {
            return false;
        }
        if (itemStack1.getItem() instanceof ItemBackpack && itemStack2.getItem() instanceof ItemBackpack) {
            NBTTagCompound compound1 = ItemBackpack.getPreparedTagCompound(itemStack1);
            NBTTagCompound compound2 = ItemBackpack.getPreparedTagCompound(itemStack2);
            return compound1.hasKey("UUID") && compound2.hasKey("UUID") && compound1.getString("UUID").equals(compound2.getString("UUID"));
        }
        return false;
    }

    public static boolean hasColor(@Nonnull ItemStack itemStack) {
        NBTTagCompound compound = itemStack.getTagCompound();
        return compound != null && compound.hasKey("Color");
    }

    public static void removeColor(@Nonnull ItemStack itemStack) {
        NBTTagCompound compound = ItemBackpack.getPreparedTagCompound(itemStack);
        compound.removeTag("Color");
    }

    public static void setColor(@Nonnull ItemStack itemStack, int color) {
        NBTTagCompound compound = ItemBackpack.getPreparedTagCompound(itemStack);
        compound.setInteger("Color", color);
    }

    @SideOnly(value=Side.CLIENT)
    public void addInformation(ItemStack itemStack, EntityPlayer player, List tooltip, boolean flag) {
        Optional<String> ownerOptional = ItemBackpack.getOwner(itemStack);
        if (ownerOptional.isPresent()) {
            tooltip.add(I18n.format((String)"lore.backpack.owned.line.0", (Object[])new Object[]{ownerOptional.get()}));
            tooltip.add(I18n.format((String)"lore.backpack.owned.line.1", (Object[])new Object[]{ItemBackpack.isLocked(itemStack) ? I18n.format((String)"gui.backpack.button.lock.locked", (Object[])new Object[0]) : I18n.format((String)"gui.backpack.button.lock.unlocked", (Object[])new Object[0])}));
            tooltip.add("");
            if (Keyboard.isKeyDown((int)42) || Keyboard.isKeyDown((int)54)) {
                tooltip.add(I18n.format((String)"lore.backpack.owned.line.contains", (Object[])new Object[0]));
                tooltip.addAll(ItemBackpack.getItemsFroTooltip(itemStack));
            } else {
                tooltip.add(I18n.format((String)"lore.backpack.owned.line.2", (Object[])new Object[0]));
                tooltip.add(I18n.format((String)"lore.backpack.owned.line.3", (Object[])new Object[0]));
            }
        } else {
            tooltip.add(I18n.format((String)"lore.backpack.not_owned.line.0", (Object[])new Object[0]));
            tooltip.add(I18n.format((String)"lore.backpack.not_owned.line.1", (Object[])new Object[0]));
        }
    }

    @SideOnly(value=Side.CLIENT)
    private static List<String> getItemsFroTooltip(@Nonnull ItemStack itemStack) {
        NBTTagCompound compound = ItemBackpack.getPreparedTagCompound(itemStack);
        ArrayList<String> itemsList = new ArrayList<String>();
        if (compound.hasKey("Items")) {
            LinkedHashMap<String, Integer> inventoryContents = new LinkedHashMap<String, Integer>();
            NBTTagList inventoryTagList = compound.getTagList("Items", 10);
            for (int i = 0; i < inventoryTagList.tagCount(); ++i) {
                ItemStack slotItemStack;
                NBTTagCompound itemStackCompound = inventoryTagList.getCompoundTagAt(i);
                if (itemStackCompound == null || (slotItemStack = ItemStack.loadItemStackFromNBT((NBTTagCompound)itemStackCompound)) == null) continue;
                String itemDisplayName = slotItemStack.getDisplayName();
                inventoryContents.put(itemDisplayName, inventoryContents.getOrDefault(itemDisplayName, 0) + slotItemStack.stackSize);
            }
            if (!inventoryContents.isEmpty()) {
                inventoryContents.forEach((item, amount) -> itemsList.add("\u00a76* \u00a77" + item + "\u00a7e x " + amount));
            }
        }
        if (itemsList.isEmpty()) {
            itemsList.add(I18n.format((String)"lore.backpack.owned.line.contains_empty", (Object[])new Object[0]));
        }
        return itemsList;
    }

    public static void checkIfNotRegistered(@Nonnull ItemStack itemStack, @Nonnull EntityPlayer player) {
        NBTTagCompound compound = ItemBackpack.getPreparedTagCompound(itemStack);
        if (!compound.hasKey("UUID")) {
            compound.setString("UUID", UUID.randomUUID().toString());
            compound.setString("Owner", player.getCommandSenderName());
            compound.setInteger("Color", itemRand.nextInt(0x1000000));
            player.inventory.markDirty();
        }
    }

    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        itemStack.animationsToGo = 5;
        if (!world.isRemote) {
            if (ItemBackpack.hasAccess(itemStack, player)) {
                ItemBackpack.checkIfNotRegistered(itemStack, player);
                player.openGui((Object)SimpleCore.INSTANCE, 3, world, MathHelper.floor_double((double)player.posX), MathHelper.floor_double((double)player.posY), MathHelper.floor_double((double)player.posZ));
            } else {
                player.addChatComponentMessage((IChatComponent)new ChatComponentTranslation("message.backpack.access_error", new Object[0]));
            }
        }
        return itemStack;
    }

    @SideOnly(value=Side.CLIENT)
    public int getColor(ItemStack itemStack) {
        int color;
        NBTTagCompound compound = ItemBackpack.getPreparedTagCompound(itemStack);
        int n = color = compound.hasKey("Color") ? compound.getInteger("Color") : 0xFFFFFF;
        if (color == 0xFFFFFF) {
            long currentTimeMillis = System.currentTimeMillis();
            if (this.randomColorChangedTimestamp == 0L || currentTimeMillis - this.randomColorChangedTimestamp > 1000L) {
                this.randomColorChangedTimestamp = currentTimeMillis;
                this.randomColor = this.randomColor == 0xFFFFFF ? 15719569 : 0xFFFFFF;
            }
            return this.randomColor;
        }
        return color;
    }

    @SideOnly(value=Side.CLIENT)
    public boolean requiresMultipleRenderPasses() {
        return true;
    }

    @SideOnly(value=Side.CLIENT)
    public int getColorFromItemStack(ItemStack itemStack, int var1) {
        return this.getColor(itemStack);
    }
}

