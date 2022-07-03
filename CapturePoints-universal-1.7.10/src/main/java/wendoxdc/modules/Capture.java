package wendoxdc.modules;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import scala.reflect.internal.util.Origins.OneLine;
import wendoxdc.events.ServerEvents;
import wendoxdc.init.CaptureMod;

public class Capture {
	public static ArrayList<Capture> captures = new ArrayList<Capture>();

	private Capture() {

	}

	public String name, targetPlayer;
	public int x, z, radius, radiusCapt, color, dimId, timeCapt, maxTimeCapt, cooldown, currentCooldown, minOnline,
			maxHeight, itemsDrop;
	public boolean exception, canCapt, onlineItsOk;
	public ArrayList<ItemStack> items = new ArrayList<ItemStack>();

	public void update() {
		if (!CaptureMod.SERVER)
			return;
		if (!exception)
			try {
				if (ServerEvents.currentOnline > minOnline)
					onlineItsOk = true;
				WorldServer server = DimensionManager.getWorld(dimId);
				if (server.getWorldTime() % 20 == 0) {
					ArrayList<EntityPlayer> list = (ArrayList<EntityPlayer>) server
							.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(x - radiusCapt, 0,
									z - radiusCapt, x + radiusCapt, maxHeight, z + radiusCapt));
					currentCooldown++;
					if (onlineItsOk) {
						if (currentCooldown == cooldown - 15) {
							server.playerEntities.forEach(w -> {
								EntityPlayer player = (EntityPlayer) w;
								player.addChatComponentMessage(new ChatComponentText("§7[§cZonix§7] §7Точка §c" + name
										+ " §7на §7координатах" + " §cX §7" + x + " §cZ §7" + z
										+ ",§7будет §7реактивирована §7через §c15 §7секунд"));
							});
						}
						if (currentCooldown > cooldown) {
							canCapt = true;
						} else
							canCapt = false;
						if (list.size() == 0 || !canCapt) {
							timeCapt = 0;
							targetPlayer = null;
							return;
						}
						if (targetPlayer == null) {
							targetPlayer = list.get(0).getCommandSenderName();
						}
						boolean inZone = false;
						for (EntityPlayer player : list) {
							if (player.getCommandSenderName().equals(targetPlayer))
								inZone = true;
						}
						if (!inZone) {
							timeCapt = 0;
							targetPlayer = null;
						} else if (timeCapt < maxTimeCapt) {
							timeCapt++;
						} else if (timeCapt >= maxTimeCapt) {
							try {
								EntityPlayer target = server.getPlayerEntityByName(targetPlayer);
								list.forEach(w -> {
									w.addChatComponentMessage(new ChatComponentText("§7[§cZonix§7] §7Игрок §c"
											+ target.getCommandSenderName() + " §7захватил §7точку §c" + name
											+ " §7на §7координатах §7X " + x + " §cZ " + z));
								});
								ArrayList<ItemStack> copy = new ArrayList<ItemStack>();
								for (int i = 0; i < items.size(); i++) {
									if (i < itemsDrop) {
										int w = new Random().nextInt(items.size());
										copy.add(ItemStack.copyItemStack(items.get(w)));
									} else {
										break;
									}
								}
								for (ItemStack s : copy) {
									target.inventory.addItemStackToInventory(s);
								}
								currentCooldown = 0;
								timeCapt = 0;
								onlineItsOk = false;
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				exception = true;
			}

	}

	public static Capture newCapt(String name, int x, int z, int radius, int color, int dimid, int maxTimeCapt,
			int cooldown, int minOnline, int radiusCapt, int maxHeight, int itemsDrop) {
		Capture c = new Capture();
		c.name = name;
		c.x = x;
		c.z = z;
		c.radius = radius;
		c.color = color;
		c.dimId = dimid;
		c.maxTimeCapt = maxTimeCapt;
		c.cooldown = cooldown;
		c.minOnline = minOnline;
		c.radiusCapt = radiusCapt;
		c.maxHeight = maxHeight;
		c.itemsDrop = itemsDrop;
		return c;
	}
}
