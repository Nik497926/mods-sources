package tueajtwut.twautjwta.minecraft;

import cpw.mods.fml.common.Loader;
import tueajtwut.twautjwta.asm.Hook;
import net.minecraft.launchwrapper.LaunchClassLoader;

public class SecondaryTransformerHook {

    /**
     * Регистрирует хук-трансформер последним.
     */
    @Hook
    public static void injectData(Loader loader, Object... data) {
        ClassLoader classLoader = SecondaryTransformerHook.class.getClassLoader();
        if (classLoader instanceof LaunchClassLoader) {
            ((LaunchClassLoader)classLoader).registerTransformer(MinecraftClassTransformer.class.getName());
        } else {
            System.out.println("twautjwta was not loaded by LaunchClassLoader. Hooks will not be injected.");
        }
    }

}
