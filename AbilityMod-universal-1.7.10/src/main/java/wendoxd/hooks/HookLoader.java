package wendoxd.hooks;

import tueajtwut.twautjwta.minecraft.PrimaryClassTransformer;

public class HookLoader extends tueajtwut.twautjwta.minecraft.HookLoader {
	@Override
	public String[] getASMTransformerClass() {
		return new String[] { PrimaryClassTransformer.class.getName() };
	}

	@Override
	public void registerHooks() {
		registerHookContainer("wendoxd.hooks.Hooks");
	}
}
