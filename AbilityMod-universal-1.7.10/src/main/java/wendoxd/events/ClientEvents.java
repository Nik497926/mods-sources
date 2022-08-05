package wendoxd.events;

import java.util.ArrayList;

import java.util.HashMap;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import wendoxd.gui.GuiAbilities;
import wendoxd.init.AbilityMod;
import wendoxd.modules.Ability;

public class ClientEvents {
	public static KeyBinding openGui = new KeyBinding("Open p Gui", Keyboard.KEY_L, "LevelSystem Binds");
	public static ArrayList<Ability> unlockedAbilities = new ArrayList<Ability>();
	public static ArrayList<Ability> abilities = new ArrayList<Ability>();
	public static int currentLvls;
	public static double speedModifier = 1, speedWaterModifier = 1, jumpModifier = 1, distance = 1;

	@SubscribeEvent
	public void tick(TickEvent.ClientTickEvent event) {
		Minecraft mc = Minecraft.getMinecraft();
		if (!(mc.currentScreen instanceof GuiAbilities) && openGui.isPressed()) {
			mc.displayGuiScreen(new GuiAbilities());
		}
		if (abilities.size() == 0) {
			AbilityMod.network.sendToServer(AbilityMod.network.createPacket(1, 0));
		}
		if (mc.thePlayer == null && abilities.size() > 0)
			abilities.clear();
	}

	@SubscribeEvent
	public void livingHurtEvent(LivingHurtEvent event) {
	}

	public static Ability getAbility(Ability main) {
		for (Ability a : unlockedAbilities) {
			if (a.name.equals(main.name)) {
				return a;
			}
		}
		return null;
	}
}
