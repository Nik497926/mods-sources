package wendoxd.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.PacketBuffer;

public interface ICustomValue {
	void send(ByteBuf buf);
}
