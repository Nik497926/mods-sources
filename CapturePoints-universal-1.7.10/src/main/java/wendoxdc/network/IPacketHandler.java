package wendoxdc.network;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.WorldServer;

public interface IPacketHandler {

	@SideOnly(Side.CLIENT)
	public void handleClientSide(PacketBuffer buf, byte id, Minecraft mc, WorldClient world,
			EntityClientPlayerMP player);

	public void handleServerSide(PacketBuffer buf, byte id, WorldServer world, EntityPlayerMP player);

	public String getChannel();
}