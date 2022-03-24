package mireille.utils;

import net.minecraft.entity.Entity;
import org.bukkit.Bukkit;
import net.minecraft.world.World;
import org.bukkit.entity.Player;
import net.minecraft.entity.player.EntityPlayer;
import java.lang.reflect.Method;
public class Utils
{
    private static final Method getBukkitEntity;
    
    public static Player toBukkitEntity(final EntityPlayer player) throws Exception {
        return (Player)Utils.getBukkitEntity.invoke(player, new Object[0]);
    }
    
    public static org.bukkit.World toBukkitWorld(final World world) {
        return Bukkit.getWorld(world.getWorldInfo().getWorldName());
    }
    
    public static final boolean hasPermission(final EntityPlayer player, final String permission) {
        try {
            return toBukkitEntity(player).hasPermission(permission);
        }
        catch (Exception var3) {
            return false;
        }
    }
    
    static {
        try {
            (getBukkitEntity = Entity.class.getDeclaredMethod("getBukkitEntity", (Class<?>[])new Class[0])).setAccessible(true);
        }
        catch (Throwable var1) {
            throw new RuntimeException("Failed hooking CraftBukkit methods!", var1);
        }
    }
}
