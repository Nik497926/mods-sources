package wendoxd.extendedplayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.BiConsumer;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import net.minecraftforge.common.util.Constants.NBT;
import wendoxd.init.AbilityMod;
import wendoxd.modules.Ability;

public class ExtendedPlayer implements IExtendedEntityProperties {
	public static String PROPERTIES_NAME = "wxdabilities";
	public EntityPlayerMP player;
	public ArrayList<Ability> unlockedAbilities = new ArrayList<Ability>();

	public ExtendedPlayer(EntityPlayerMP player) {
		this.player = player;
	}

	public static ExtendedPlayer get(EntityPlayer player) {
		return (ExtendedPlayer) player.getExtendedProperties(PROPERTIES_NAME);
	}

	@Override
	public void saveNBTData(NBTTagCompound tag) {
		if (!AbilityMod.SERVER)
			return;
		NBTTagList list = new NBTTagList();
		for (Ability a : unlockedAbilities) {
			NBTTagCompound compound = new NBTTagCompound();
			compound.setString("name", a.name);
			compound.setInteger("lvl", a.lvl);
			list.appendTag(compound);
		}
		tag.setTag(PROPERTIES_NAME, list);
	}

	@Override
	public void loadNBTData(NBTTagCompound tag) {
		if (!AbilityMod.SERVER)
			return;
		NBTTagList list = tag.getTagList(PROPERTIES_NAME, NBT.TAG_COMPOUND);
		for (int i = 0; i < list.tagCount(); i++) {
			NBTTagCompound com = list.getCompoundTagAt(i);
			Ability a = new Ability(com.getString("name"), "", new ArrayList<Integer>(), new ArrayList<Double>());
			a.lvl = new Integer(com.getInteger("lvl"));
			this.unlockedAbilities.add(a);
		}
	}

	public void copyNBT(ExtendedPlayer other) {
		this.unlockedAbilities = (ArrayList<Ability>) other.unlockedAbilities.clone();
		syncAbilities();
	}

	@Override
	public void init(Entity entity, World world) {
	}

	public void clearAbilities() {
		if (!AbilityMod.SERVER)
			return;
		this.unlockedAbilities.clear();
		syncAbilities();
	}

	public void syncAbilities() {
		if (!AbilityMod.SERVER)
			return;
		AbilityMod.network.sendTo(AbilityMod.network.createPacket(0, Ability.serverAbilities), player);
		AbilityMod.network.sendTo(AbilityMod.network.createPacket(1, unlockedAbilities), player);
		AbilityMod.network.sendTo(AbilityMod.network.createPacket(2, Ability.maxLvls), player);
		AbilityMod.network.sendTo(AbilityMod.network.createPacket(3, getTotalLvls()), player);
		player.getEntityAttribute(SharedMonsterAttributes.maxHealth)
				.setBaseValue((getUnlocked(Ability.MAX_HP) != null
						? 20 + (Ability.getModifier(Ability.MAX_HP, getUnlocked(Ability.MAX_HP)) * 2)
						: 20));
	}

	public void addLvl(Ability ab) {
		for (Ability a : unlockedAbilities) {
			if (a.name.equals(ab.name)) {
				if (a.lvl + 1 < ab.getSize()) {
					a.lvl++;
				}
				syncAbilities();
				return;
			}
		}
		for (Ability a : Ability.serverAbilities) {
			if (a.name.equals(ab.name)) {
				Ability newab = new Ability(a.name, a.desc, a.costs, a.modifiers);
				unlockedAbilities.add(newab);
				syncAbilities();
				return;
			}
		}
	}

	public Ability getUnlocked(Ability main) {
		for (Ability a : unlockedAbilities) {
			if (a.name.equals(main.name))
				return a;
		}
		return null;
	}

	public int getTotalLvls() {
		int total = 0;
		for (Ability a : unlockedAbilities) {
			total = total + (a.lvl + 1);
		}
		return total;
	}
}