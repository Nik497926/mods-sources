/*
 * Decompiled with CFR 0.152.
 */
package ru.simplemc.senergetics.common.item.tool;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.item.IC2Items;
import java.util.Collections;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import ru.simplemc.senergetics.SEnergetics;
import ru.simplemc.senergetics.SEnergeticsRegistry;
import ru.simplemc.senergetics.common.item.ItemBase;

public class ItemMobScanner
extends ItemBase {
    public static final String MOB_CLASS_NAME_TAG = "MobClassName";
    public static final String MOB_UNLOCALIZED_NAME = "MobUnlocalizedName";
    @SideOnly(value=Side.CLIENT)
    private IIcon iconContainsMob;

    public static boolean isMobWritten(ItemStack stack) {
        NBTTagCompound tagCompound = stack.getTagCompound();
        return tagCompound != null && tagCompound.hasKey(MOB_CLASS_NAME_TAG) && tagCompound.hasKey(MOB_UNLOCALIZED_NAME);
    }

    public static boolean canWriteMob(Entity entity) {
        if (entity instanceof EntityLivingBase && !(entity instanceof EntityPlayer) && !(entity instanceof EntityDragon)) {
            String entityClassName = entity.getClass().getName();
            for (String allowedClass : SEnergetics.getConfig().getMobScannerBlacklist()) {
                if (!entityClassName.contains(allowedClass.replace("*", ""))) continue;
                return true;
            }
        }
        return false;
    }

    public ItemMobScanner() {
        super("item_mob_scanner");
        this.setNoRepair();
        this.setMaxStackSize(1);
        MinecraftForge.EVENT_BUS.register((Object)this);
        FMLCommonHandler.instance().bus().register((Object)this);
        GameRegistry.addRecipe((ItemStack)new ItemStack((Item)this, 1, 0), (Object[])new Object[]{"III", "PFP", "III", Character.valueOf('I'), IC2Items.getItem((String)"denseplateadviron"), Character.valueOf('P'), IC2Items.getItem((String)"advancedCircuit"), Character.valueOf('F'), IC2Items.getItem((String)"frequencyTransmitter")});
    }

    @SideOnly(value=Side.CLIENT)
    public void registerIcons(IIconRegister register) {
        this.iconContainsMob = register.registerIcon(this.getIconString() + "_active");
        super.registerIcons(register);
    }

    @SideOnly(value=Side.CLIENT)
    public IIcon getIconFromDamage(int damage) {
        if (damage == 1) {
            return this.iconContainsMob;
        }
        return super.getIconFromDamage(damage);
    }

    @SubscribeEvent
    public void onEntityInteractEvent(EntityInteractEvent event) {
        EntityPlayer player;
        ItemStack stack;
        if (!event.target.worldObj.isRemote && event.target instanceof EntityLivingBase && (stack = (player = event.entityPlayer).getHeldItem()) != null && (stack.getItem() == SEnergeticsRegistry.itemMobScanner || stack.getItem() == SEnergeticsRegistry.itemMobDebug)) {
            if (stack.getItem() == SEnergeticsRegistry.itemMobDebug) {
                String entityClass = event.target.getClass().getName();
                System.out.println(entityClass);
                player.addChatComponentMessage((IChatComponent)new ChatComponentText(entityClass));
                player.addChatComponentMessage((IChatComponent)new ChatComponentText("Can write = " + ItemMobScanner.canWriteMob(event.target)));
                return;
            }
            if (ItemMobScanner.canWriteMob(event.target)) {
                if (ItemMobScanner.isMobWritten(stack) && !player.isSneaking()) {
                    player.addChatComponentMessage((IChatComponent)new ChatComponentTranslation("message.item_mob_scanner_override.text", new Object[0]));
                    return;
                }
                NBTTagCompound tagCompound = stack.hasTagCompound() ? stack.getTagCompound() : new NBTTagCompound();
                tagCompound.setString(MOB_CLASS_NAME_TAG, event.target.getClass().getName());
                tagCompound.setString(MOB_UNLOCALIZED_NAME, EntityList.getEntityString((Entity)event.target));
                stack.setTagCompound(tagCompound);
                stack.setItemDamage(1);
                player.addChatComponentMessage((IChatComponent)new ChatComponentTranslation("message.item_mob_scanner_success.text", new Object[0]));
            } else {
                player.addChatComponentMessage((IChatComponent)new ChatComponentTranslation("message.item_mob_scanner_no_allowed.text", new Object[0]));
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag) {
        if (ItemMobScanner.isMobWritten(stack)) {
            Collections.addAll(list, String.valueOf(StatCollector.translateToLocal((String)(this.getUnlocalizedNameInefficiently(stack) + "_selected.lore"))).trim().replace("%entityName%", stack.getTagCompound().getString(MOB_UNLOCALIZED_NAME)).split("<br>"));
        } else {
            list.add(StatCollector.translateToLocal((String)(this.getUnlocalizedNameInefficiently(stack) + ".lore")) + ".");
        }
    }
}

