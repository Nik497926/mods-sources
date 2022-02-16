package com.gamerforea.divinerpg;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.PlayerChangedDimensionEvent;
import net.divinerpg.utils.config.ConfigurationHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants;

public final class VetheaInvSwapper
{
	private static final String NBT_OVERWORLD = "OverworldInv";
	private static final String NBT_VETHEA = "VetheaInv";

	@SubscribeEvent
	public void onWorldChange(PlayerChangedDimensionEvent event)
	{
		if (EventConfig.vetherInvSwapper)
		{
			boolean fromVethea = event.fromDim == ConfigurationHelper.vethea;
			boolean toVethea = event.toDim == ConfigurationHelper.vethea;
			if (fromVethea != toVethea)
				swapInventory(event.player, toVethea);
		}
	}

	private static void swapInventory(EntityPlayer player, boolean toVethea)
	{
		String fromTag = toVethea ? NBT_OVERWORLD : NBT_VETHEA;
		String toTag = toVethea ? NBT_VETHEA : NBT_OVERWORLD;

		NBTTagCompound persistantData = player.getEntityData().getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG);
		persistantData.setTag(fromTag, player.inventory.writeToNBT(new NBTTagList()));
		player.getEntityData().setTag(EntityPlayer.PERSISTED_NBT_TAG, persistantData);
		player.inventory.clearInventory(null, -1);
		NBTTagList inv = persistantData.getTagList(toTag, Constants.NBT.TAG_COMPOUND);
		player.inventory.readFromNBT(inv);
		player.inventoryContainer.detectAndSendChanges();
	}
}
