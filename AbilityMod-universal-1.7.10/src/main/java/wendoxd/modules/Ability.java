package wendoxd.modules;

import java.awt.Font;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.newdawn.slick.UnicodeFont;

import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.S1FPacketSetExperience;
import wendoxd.events.ClientEvents;
import wendoxd.init.AbilityMod;
import wendoxd.network.ICustomValue;

public class Ability {
	public int maxlvl;
	public ArrayList<Integer> costs;
	public ArrayList<Double> modifiers;
	public int lvl;
	public String desc, name;
	public static ArrayList<Ability> serverAbilities = new ArrayList<Ability>();
	public static int maxLvls;
	public static final Ability SPEED, SPEED_IN_WATER, REGEN, PROTECTION, JUMP, FORCE, AGILITY, MAX_HP, FASTCLICK,
			DISTANCE;
	static {
		SPEED = new Ability("Скорость перемещения", "");
		SPEED_IN_WATER = new Ability("Скорость в воде", "");
		REGEN = new Ability("Регенерация", "");
		PROTECTION = new Ability("Защита", "");
		JUMP = new Ability("Высота прыжка", "");
		FORCE = new Ability("Урон", "");
		AGILITY = new Ability("Уклонение", "");
		MAX_HP = new Ability("Максимальное здоровье", "");
		FASTCLICK = new Ability("Скорость атаки", "");
		DISTANCE = new Ability("Дальность атаки", "");
		serverAbilities.add(SPEED);
		serverAbilities.add(SPEED_IN_WATER);
		serverAbilities.add(REGEN);
		serverAbilities.add(PROTECTION);
		serverAbilities.add(JUMP);
		serverAbilities.add(FORCE);
		serverAbilities.add(AGILITY);
		serverAbilities.add(MAX_HP);
		serverAbilities.add(FASTCLICK);
		serverAbilities.add(DISTANCE);
	}

	public Ability(String name, String desc, ArrayList<Integer> costs, ArrayList<Double> modifiers) {
		this.name = name;
		this.desc = desc;
		this.costs = costs;
		this.modifiers = modifiers;
		this.maxlvl = getSize();
	}

	public Ability(String name, String desc) {
		this(name, desc, new ArrayList<Integer>(), new ArrayList<Double>());
	}

	public boolean upLvl() {
		return this.lvl + 1 < getSize();
	}

	public static double getModifier(Ability main, Ability other) {
		if (other.lvl + 1 > main.modifiers.size()) {
			other.lvl = main.modifiers.size() - 1;
		}
		return main.modifiers.get(other.lvl);
	}

	public int getSize() {
		if (costs.size() != modifiers.size())
			throw new IllegalArgumentException("costs != size");
		return costs.size();
	}

	public void addCostAndModifier(int cost, double modifier) {
		this.costs.add(cost);
		this.modifiers.add(modifier);
	}

	public static int getCost(Ability a) {
		return a.costs.get(a.lvl);
	}

	public static Ability forName(String name, boolean client) {
		ArrayList<Ability> abilities = client ? ClientEvents.abilities : serverAbilities;
		for (Ability a : abilities) {
			if (a.name.equals(name))
				return a;
		}
		return null;
	}

	public static boolean buy(Ability a, EntityPlayer player, int current) {
		boolean buy = false;
		try {
			if (player.experienceLevel > a.costs.get(current)) {
				buy = true;
				if (player instanceof EntityPlayerMP) {
					player.experienceLevel = player.experienceLevel - a.costs.get(current);
					((EntityPlayerMP) player).playerNetServerHandler.sendPacket(new S1FPacketSetExperience(
							player.experience, player.experienceTotal, player.experienceLevel));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buy;
	}

	public static void readAllAbilities() {
		if (!AbilityMod.SERVER)
			return;
		try {
			File loc = new File("AbilityMod/");
			if (!loc.exists())
				loc.mkdir();
			for (Ability a : serverAbilities) {
				File config = new File("AbilityMod/" + a.name + ".cfg");
				File maxLvl = new File("AbilityMod/MaxLvl.cfg");
				maxLvls = Integer.valueOf(Files.readAllLines(maxLvl.toPath()).get(0));
				if (!config.exists())
					Files.write(config.toPath(), "Настройте конфиг нууу".getBytes(), StandardOpenOption.CREATE,
							StandardOpenOption.WRITE);
				else {
					List<String> strings = Files.readAllLines(config.toPath());
					a.costs.clear();
					a.modifiers.clear();
					try {
						strings.forEach(w -> {
							if (w.startsWith("addCostAndModifier[")) {
								String newString = w.replace("addCostAndModifier[", "").replace("]", "");
								Integer cost = Integer.valueOf(newString.split(",")[0]);
								Double modifier = Double.valueOf(newString.split(",")[1]);
								a.addCostAndModifier(cost.intValue(), modifier.doubleValue());
							}
							if (w.startsWith("addDesc[")) {
								String newString = w.replace("addDesc[", "").replace("]", "");
								a.desc = newString;
							}
						});
					} catch (Exception e) {
						Files.write(config.toPath(), e.getClass().getName().getBytes(), StandardOpenOption.APPEND);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
