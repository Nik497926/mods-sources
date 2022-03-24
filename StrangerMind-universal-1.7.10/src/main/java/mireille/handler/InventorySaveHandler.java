package mireille.handler;

import java.util.*;
import net.minecraft.item.*;
import net.minecraftforge.event.entity.living.*;
import net.minecraft.entity.player.*;
import mireille.utils.*;
import mireille.*;
import lain.mods.cos.*;
import baubles.common.lib.*;
import cpw.mods.fml.common.*;
import lain.mods.cos.inventory.*;
import baubles.common.container.*;
import cpw.mods.fml.common.eventhandler.*;
import cpw.mods.fml.common.gameevent.*;
import net.minecraft.util.*;

public class InventorySaveHandler
{
    protected HashMap<String, InventoryPlayer> playerKeepsMap;
    protected HashMap<String, ItemStack[]> playerCosmeticInventory;
    protected HashMap<String, ItemStack[]> playerBaublesInventory;
    protected HashMap<String, Integer> playerExpMap;
    
    public InventorySaveHandler() {
        this.playerKeepsMap = new HashMap<String, InventoryPlayer>();
        this.playerCosmeticInventory = new HashMap<String, ItemStack[]>();
        this.playerBaublesInventory = new HashMap<String, ItemStack[]>();
        this.playerExpMap = new HashMap<String, Integer>();
    }
    
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void livingDies(final LivingDeathEvent event) throws Exception {
        if (event.entityLiving instanceof EntityPlayer && !event.entityLiving.worldObj.getGameRules().getGameRuleBooleanValue("keepInventory")) {
            final EntityPlayer player = (EntityPlayer)event.entityLiving;
            if (Utils.hasPermission(player, ModConfig.KeepInventory)) {
                final InventoryPlayer keepInventory = new InventoryPlayer((EntityPlayer)null);
                for (int i = 0; i < player.inventory.armorInventory.length; ++i) {
                    keepInventory.armorInventory[i] = ItemStack.copyItemStack(player.inventory.armorInventory[i]);
                    player.inventory.armorInventory[i] = null;
                }
                for (int i = 0; i < player.inventory.mainInventory.length; ++i) {
                    keepInventory.mainInventory[i] = ItemStack.copyItemStack(player.inventory.mainInventory[i]);
                    player.inventory.mainInventory[i] = null;
                }
                this.playerKeepsMap.put(player.getCommandSenderName(), keepInventory);
                if (StrangerMind.CosmeticArmor) {
                    final InventoryCosArmor CosmeticSlots = CosmeticArmorReworked.invMan.getCosArmorInventory(player.getUniqueID());
                    final int l = CosmeticSlots.getSizeInventory();
                    final ItemStack[] stacks = new ItemStack[l];
                    for (int i = 0; i < l; ++i) {
                        stacks[i] = ItemStack.copyItemStack(CosmeticSlots.getInventory()[i]);
                        CosmeticSlots.setInventorySlotContents(i, (ItemStack)null);
                    }
                    this.playerCosmeticInventory.put(player.getCommandSenderName(), stacks);
                }
                if (StrangerMind.Baubles) {
                    final InventoryBaubles BaublesSlots = PlayerHandler.getPlayerBaubles(player);
                    final int l = BaublesSlots.getSizeInventory();
                    final ItemStack[] stacks = new ItemStack[l];
                    for (int i = 0; i < l; ++i) {
                        stacks[i] = ItemStack.copyItemStack(BaublesSlots.stackList[i]);
                        BaublesSlots.setInventorySlotContents(i, (ItemStack)null);
                    }
                    this.playerBaublesInventory.put(player.getCommandSenderName(), stacks);
                }
            }
            if (Utils.hasPermission(player, ModConfig.ExpSave)) {
                this.playerExpMap.put(player.getCommandSenderName(), player.experienceTotal);
                player.addExperience((int)(-player.experience));
                player.addExperienceLevel(-player.experienceLevel);
            }
        }
        if (this.playerKeepsMap.size() > 1 || this.playerCosmeticInventory.size() > 1 || this.playerBaublesInventory.size() > 1 || this.playerExpMap.size() > 1) {
            FMLLog.warning("[StrangerMind] Mod is keeping track of a lot of dead player inventories. Has there been an apocalypse?", new Object[0]);
        }
    }
    
    @SubscribeEvent
    public void onPlayerRespawn(final PlayerEvent.PlayerRespawnEvent event) throws Exception {
        final EntityPlayer player = event.player;
        if (this.playerKeepsMap.containsKey(player.getCommandSenderName())) {
            FMLLog.info("[StrangerMind] Player %s respawned and recieved items held in storage", new Object[] { player.getCommandSenderName() });
            final InventoryPlayer keepInventory = this.playerKeepsMap.get(player.getCommandSenderName());
            for (int i = 0; i < player.inventory.armorInventory.length; ++i) {
                if (keepInventory.armorInventory[i] != null) {
                    player.inventory.armorInventory[i] = keepInventory.armorInventory[i];
                }
            }
            for (int i = 0; i < player.inventory.mainInventory.length; ++i) {
                if (keepInventory.mainInventory[i] != null) {
                    player.inventory.mainInventory[i] = keepInventory.mainInventory[i];
                }
            }
            this.playerKeepsMap.remove(player.getCommandSenderName());
            player.addChatComponentMessage((IChatComponent)new ChatComponentText(ModConfig.SaveInvMessage));
        }
        if (this.playerExpMap.containsKey(player.getCommandSenderName())) {
            player.addExperience((int)this.playerExpMap.get(player.getCommandSenderName()));
            this.playerExpMap.remove(player.getCommandSenderName());
            player.addChatComponentMessage((IChatComponent)new ChatComponentText(ModConfig.SaveExpMessage));
        }
        if (StrangerMind.CosmeticArmor && this.playerCosmeticInventory.containsKey(player.getCommandSenderName())) {
            final InventoryCosArmor CosmeticSlots = CosmeticArmorReworked.invMan.getCosArmorInventory(player.getUniqueID());
            final ItemStack[] list = this.playerCosmeticInventory.get(player.getCommandSenderName());
            for (int j = 0; j < list.length; ++j) {
                CosmeticSlots.setInventorySlotContents(j, list[j]);
            }
            this.playerCosmeticInventory.remove(player.getCommandSenderName());
        }
        if (StrangerMind.Baubles && this.playerBaublesInventory.containsKey(player.getCommandSenderName())) {
            final InventoryBaubles BaublesSlots = PlayerHandler.getPlayerBaubles(player);
            final ItemStack[] list = this.playerBaublesInventory.get(player.getCommandSenderName());
            for (int j = 0; j < list.length; ++j) {
                BaublesSlots.setInventorySlotContents(j, list[j]);
            }
            this.playerBaublesInventory.remove(player.getCommandSenderName());
        }
    }
}
