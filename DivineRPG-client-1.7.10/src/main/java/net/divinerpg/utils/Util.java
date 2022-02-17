/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.ObfuscationReflectionHelper
 *  cpw.mods.fml.common.registry.EntityRegistry
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityList$EntityEggInfo
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemArmor$ArmorMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.ChatComponentText
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EntityDamageSourceIndirect
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 *  net.minecraftforge.client.IItemRenderer
 *  net.minecraftforge.client.MinecraftForgeClient
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.common.util.EnumHelper
 *  net.minecraftforge.fluids.Fluid
 *  net.minecraftforge.fluids.FluidContainerRegistry
 *  net.minecraftforge.fluids.FluidRegistry
 *  net.minecraftforge.fluids.FluidStack
 */
package net.divinerpg.utils;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import net.divinerpg.DivineRPG;
import net.divinerpg.blocks.base.BlockModSlab;
import net.divinerpg.items.base.ItemModSlab;
import net.divinerpg.utils.LangRegistry;
import net.divinerpg.utils.blocks.VanillaBlocks;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class Util {
    private static final String[] devs = new String[]{"RadioactiveStud", "Eternaldoom1", "BossLetsPlays", "Xolova", "sheenrox82", "The_SlayerMC", "insanity414all", "deathman12e3", "Fire_Sight", "krwminer"};
    public static int mobID = 500;
    public static int projectileID = 0;
    public static int entityListID = 2500;
    private static Object reflectionFactory = null;
    private static Method newConstructorAccessor = null;
    private static Method newInstance = null;
    private static Method newFieldAccessor = null;
    private static Method fieldAccessorSet = null;
    private static boolean isSetup = false;
    public static String mobName;
    public static String BLACK;
    public static String DARK_BLUE;
    public static String DARK_GREEN;
    public static String DARK_AQUA;
    public static String DARK_RED;
    public static String DARK_PURPLE;
    public static String GOLD;
    public static String GRAY;
    public static String DARK_GRAY;
    public static String BLUE;
    public static String GREEN;
    public static String AQUA;
    public static String RED;
    public static String LIGHT_PURPLE;
    public static String YELLOW;
    public static String WHITE;
    public static DamageSource acidSource;
    public static DamageSource trapSource;
    public static DamageSource arcanaSource;
    public static DamageSource spikeSource;

    public static void addRecipe(ItemStack i, Object ... o) {
        GameRegistry.addRecipe((ItemStack)i, (Object[])o);
    }

    public static void addBucket(Fluid fluid, ItemStack modBucket) {
        FluidContainerRegistry.registerFluidContainer((FluidStack)FluidRegistry.getFluidStack((String)fluid.getName(), (int)1000), (ItemStack)modBucket, (ItemStack)new ItemStack(Items.bucket));
    }

    public static void postForgeEvent(Object o) {
        MinecraftForge.EVENT_BUS.register(o);
    }

    public static void postFMLEvent(Object o) {
        FMLCommonHandler.instance().bus().register(o);
    }

    public static void addShapelessRecipe(ItemStack i, Object ... o) {
        GameRegistry.addShapelessRecipe((ItemStack)i, (Object[])o);
    }

    public static void addSmelting(ItemStack input, ItemStack output, float XP) {
        GameRegistry.addSmelting((ItemStack)input, (ItemStack)output, (float)XP);
    }

    public static void registerDivineRPGMob(Class entityClass, String entityName) {
        LangRegistry.addMob(entityName);
        EntityRegistry.registerModEntity((Class)entityClass, (String)entityName, (int)mobID++, (Object)DivineRPG.instance, (int)128, (int)3, (boolean)true);
        int id = entityListID++;
        entityName = "DRPG" + entityName;
        EntityList.stringToClassMapping.put(entityName, entityClass);
        EntityList.classToStringMapping.put(entityClass, entityName);
        EntityList.IDtoClassMapping.put(id, entityClass);
        ((Map)ObfuscationReflectionHelper.getPrivateValue(EntityList.class, null, (String[])new String[]{"f", "classToIDMapping", "classToIDMapping"})).put(entityClass, id);
        ((Map)ObfuscationReflectionHelper.getPrivateValue(EntityList.class, null, (String[])new String[]{"g", "stringToIDMapping", "stringToIDMapping"})).put(entityName, id);
        EntityList.entityEggs.put(id, new EntityList.EntityEggInfo(id, 0, 0xFFFFFF));
    }

    public static void registerEgglessMob(Class entityClass, String entityName) {
        LangRegistry.addMob(entityName);
        EntityRegistry.registerModEntity((Class)entityClass, (String)entityName, (int)mobID++, (Object)DivineRPG.instance, (int)128, (int)3, (boolean)true);
        int id = entityListID++;
        entityName = "DRPG" + entityName;
        EntityList.stringToClassMapping.put(entityName, entityClass);
        EntityList.classToStringMapping.put(entityClass, entityName);
        EntityList.IDtoClassMapping.put(id, entityClass);
        ((Map)ObfuscationReflectionHelper.getPrivateValue(EntityList.class, null, (String[])new String[]{"f", "classToIDMapping", "classToIDMapping"})).put(entityClass, id);
        ((Map)ObfuscationReflectionHelper.getPrivateValue(EntityList.class, null, (String[])new String[]{"g", "stringToIDMapping", "stringToIDMapping"})).put(entityName, id);
    }

    public static void registerProjectile(Class entityClass, String entityName) {
        EntityRegistry.registerModEntity((Class)entityClass, (String)(entityName + "Projectile"), (int)projectileID, (Object)DivineRPG.instance, (int)250, (int)5, (boolean)true);
        ++projectileID;
    }

    public static ItemArmor.ArmorMaterial addArmorMaterial(String name, int durability, int enchantability, Item repair) {
        int duraNew = (int)Math.round((double)durability / 13.75);
        ItemArmor.ArmorMaterial mat = (ItemArmor.ArmorMaterial)EnumHelper.addEnum(ItemArmor.ArmorMaterial.class, (String)name, (Object[])new Object[]{duraNew, new int[]{0, 0, 0, 0}, enchantability});
        mat.customCraftingMaterial = repair;
        return mat;
    }

    public static ItemArmor.ArmorMaterial addArmorMaterial(String name, int durability, int enchantability) {
        int duraNew = (int)Math.round((double)durability / 13.75);
        return (ItemArmor.ArmorMaterial)EnumHelper.addEnum(ItemArmor.ArmorMaterial.class, (String)name, (Object[])new Object[]{duraNew, new int[]{0, 0, 0, 0}, enchantability});
    }

    public static ChatComponentTranslation addChatMessage(String str, Object ... args) {
        ChatComponentTranslation ret = new ChatComponentTranslation(str, args);
        ret.getChatStyle().setColor(EnumChatFormatting.WHITE);
        return ret;
    }

    public static ChatComponentTranslation addChatMessage(EnumChatFormatting color, String str, Object ... args) {
        ChatComponentTranslation ret = new ChatComponentTranslation(str, args);
        ret.getChatStyle().setColor(color);
        return ret;
    }

    public static IChatComponent getChatComponent(String str) {
        ChatComponentText ret = new ChatComponentText(str);
        return ret;
    }

    public static void registerItemRenderer(Item i, IItemRenderer te) {
        MinecraftForgeClient.registerItemRenderer((Item)i, (IItemRenderer)te);
    }

    public static void registerItemRenderer(Block b, IItemRenderer te) {
        MinecraftForgeClient.registerItemRenderer((Item)Item.getItemFromBlock((Block)b), (IItemRenderer)te);
    }

    public static void sendMessageToAll(ChatComponentTranslation chatComponentTranslation) {
        MinecraftServer.getServer().getConfigurationManager().sendChatMsg((IChatComponent)chatComponentTranslation);
    }

    public static void sendMessageToAll(String message) {
        MinecraftServer.getServer().getConfigurationManager().sendChatMsg((IChatComponent)new ChatComponentTranslation(message, new Object[0]));
    }

    public static void sendMessageToAll(String message, String color) {
        MinecraftServer.getServer().getConfigurationManager().sendChatMsg((IChatComponent)new ChatComponentTranslation(AQUA + "[" + BLUE + "DivineRPG" + AQUA + "] " + color + message, new Object[0]));
    }

    public static Item.ToolMaterial addMeleeMaterial(int maxUses, float damage, int enchantability, Item repair) {
        return ((Item.ToolMaterial)EnumHelper.addEnum(Item.ToolMaterial.class, (String)"", (Object[])new Object[]{0, maxUses, 0, Float.valueOf(damage - 5.0f), enchantability})).setRepairItem(new ItemStack(repair, 1));
    }

    public static Item.ToolMaterial addHammerMaterial(float damage) {
        return EnumHelper.addToolMaterial((String)"sword", (int)0, (int)-1, (float)0.0f, (float)(damage - 4.0f), (int)22);
    }

    public static Item.ToolMaterial addMeleeMaterial(float damage, int enchantability) {
        return (Item.ToolMaterial)EnumHelper.addEnum(Item.ToolMaterial.class, (String)"", (Object[])new Object[]{0, -1, 0, Float.valueOf(damage - 5.0f), enchantability});
    }

    public static Item.ToolMaterial addAxeMaterial(int harvestLevel, int maxUses, float efficiency, float damage, int enchantability, Item repair) {
        return ((Item.ToolMaterial)EnumHelper.addEnum(Item.ToolMaterial.class, (String)"", (Object[])new Object[]{harvestLevel, maxUses, Float.valueOf(efficiency), Float.valueOf(damage - 4.0f), enchantability})).setRepairItem(new ItemStack(repair, 1));
    }

    public static Item.ToolMaterial addAxeMaterial(int harvestLevel, float efficiency, float damage, int enchantability) {
        return (Item.ToolMaterial)EnumHelper.addEnum(Item.ToolMaterial.class, (String)"", (Object[])new Object[]{harvestLevel, -1, Float.valueOf(efficiency), Float.valueOf(damage - 4.0f), enchantability});
    }

    public static Item.ToolMaterial addPickMaterial(int harvestLevel, int maxUses, float efficiency, float damage, int enchantability, Item repair) {
        return ((Item.ToolMaterial)EnumHelper.addEnum(Item.ToolMaterial.class, (String)"", (Object[])new Object[]{harvestLevel, maxUses, Float.valueOf(efficiency), Float.valueOf(damage - 3.0f), enchantability})).setRepairItem(new ItemStack(repair, 1));
    }

    public static Item.ToolMaterial addPickMaterial(int harvestLevel, float efficiency, float damage, int enchantability) {
        return (Item.ToolMaterial)EnumHelper.addEnum(Item.ToolMaterial.class, (String)"", (Object[])new Object[]{harvestLevel, -1, Float.valueOf(efficiency), Float.valueOf(damage - 3.0f), enchantability});
    }

    public static Item.ToolMaterial addSpadeMaterial(int harvestLevel, int maxUses, float efficiency, float damage, int enchantability, Item repair) {
        return ((Item.ToolMaterial)EnumHelper.addEnum(Item.ToolMaterial.class, (String)"", (Object[])new Object[]{harvestLevel, maxUses, Float.valueOf(efficiency), Float.valueOf(damage - 2.0f), enchantability})).setRepairItem(new ItemStack(repair, 1));
    }

    public static Item.ToolMaterial addSpadeMaterial(int harvestLevel, float efficiency, float damage, int enchantability) {
        return (Item.ToolMaterial)EnumHelper.addEnum(Item.ToolMaterial.class, (String)"", (Object[])new Object[]{harvestLevel, -1, Float.valueOf(efficiency), Float.valueOf(damage - 2.0f), enchantability});
    }

    public static Item.ToolMaterial addShickMaterial(int harvestLevel, int maxUses, float efficiency, float damage, int enchantability, Item repair) {
        return ((Item.ToolMaterial)EnumHelper.addEnum(Item.ToolMaterial.class, (String)"", (Object[])new Object[]{harvestLevel, maxUses, Float.valueOf(efficiency), Float.valueOf(damage - 1.0f), enchantability})).setRepairItem(new ItemStack(repair, 1));
    }

    public static Item.ToolMaterial addShickMaterial(int harvestLevel, float efficiency, float damage, int enchantability) {
        return (Item.ToolMaterial)EnumHelper.addEnum(Item.ToolMaterial.class, (String)"", (Object[])new Object[]{harvestLevel, -1, Float.valueOf(efficiency), Float.valueOf(damage - 1.0f), enchantability});
    }

    public static Item toItem(Block block) {
        return Item.getItemFromBlock((Block)block);
    }

    public static void registerSlab(Block single, Block stack) {
        GameRegistry.registerBlock((Block)single, ItemModSlab.class, (String)((BlockModSlab)single).NAME, (Object[])new Object[]{(BlockModSlab)single, (BlockModSlab)stack, false});
        GameRegistry.registerBlock((Block)stack, ItemModSlab.class, (String)(((BlockModSlab)stack).NAME + "Double"), (Object[])new Object[]{(BlockModSlab)single, (BlockModSlab)stack, true});
    }

    public static boolean isDeveloperName(String name) {
        for (int i = 0; i < devs.length; ++i) {
            if (!name.equalsIgnoreCase(devs[i])) continue;
            return true;
        }
        return false;
    }

    public static DamageSource causeArcanaDamage(Entity projectile, Entity shooter) {
        return new EntityDamageSourceIndirect("arrow", projectile, shooter).setMagicDamage();
    }

    public static ChunkCoordinates getPlayerSpawnChunk(EntityPlayer player) {
        String[] spawnChunkNames = new String[]{"c", "spawnChunk", "spawnChunk"};
        ChunkCoordinates coords = (ChunkCoordinates)ObfuscationReflectionHelper.getPrivateValue(EntityPlayer.class, player, (String[])spawnChunkNames);
        return coords;
    }

    public static HashMap<Integer, ChunkCoordinates> getPlayerSpawnChunkMap(EntityPlayer player) {
        String[] spawnChunkMapNames = new String[]{"spawnChunkMap", "spawnChunkMap", "spawnChunkMap"};
        HashMap map = (HashMap)ObfuscationReflectionHelper.getPrivateValue(EntityPlayer.class, player, (String[])spawnChunkMapNames);
        return map;
    }

    public static Entity findEntityByUUID(String uuid, World world) {
        for (int i = 0; i < world.loadedEntityList.size(); ++i) {
            Entity e = (Entity)world.loadedEntityList.get(i);
            if (!((Entity)world.loadedEntityList.get(i)).getPersistentID().toString().equals(uuid)) continue;
            return (Entity)world.loadedEntityList.get(i);
        }
        return null;
    }

    public static void drawTexturedModalRect(int x1, int y1, int u, int v, int x2, int y2) {
        float f = 0.00390625f;
        float f1 = 0.00390625f;
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV((double)(x1 + 0), (double)(y1 + y2), 1.0, (double)((float)(u + 0) * f), (double)((float)(v + y2) * f1));
        tessellator.addVertexWithUV((double)(x1 + x2), (double)(y1 + y2), 1.0, (double)((float)(u + x2) * f), (double)((float)(v + y2) * f1));
        tessellator.addVertexWithUV((double)(x1 + x2), (double)(y1 + 0), 1.0, (double)((float)(u + x2) * f), (double)((float)(v + 0) * f1));
        tessellator.addVertexWithUV((double)(x1 + 0), (double)(y1 + 0), 1.0, (double)((float)(u + 0) * f), (double)((float)(v + 0) * f1));
        tessellator.draw();
    }

    public static MovingObjectPosition rayTrace(EntityPlayer player, double distance) {
        Vec3 pos = Vec3.createVectorHelper((double)player.posX, (double)(player.posY + (double)player.getEyeHeight()), (double)player.posZ);
        Vec3 look = player.getLook(1.0f);
        Vec3 vec32 = pos.addVector(look.xCoord * distance, look.yCoord * distance, look.zCoord * distance);
        return player.worldObj.func_147447_a(pos, vec32, false, false, true);
    }

    public static boolean bordersTar(World w, int x, int y, int z) {
        for (int i = x - 4; i <= x + 4; ++i) {
            for (int j = y; j <= y + 1; ++j) {
                for (int k = z - 4; k <= z + 4; ++k) {
                    if (w.getBlock(i, j, k) != VanillaBlocks.tar) continue;
                    return true;
                }
            }
        }
        return false;
    }

    public static ChunkCoordinates newChunkCoordinates(int x, int y, int z) {
        ChunkCoordinates c = new ChunkCoordinates();
        c.set(x, y, z);
        return c;
    }

    static {
        BLACK = "\u00a70";
        DARK_BLUE = "\u00a71";
        DARK_GREEN = "\u00a72";
        DARK_AQUA = "\u00a73";
        DARK_RED = "\u00a74";
        DARK_PURPLE = "\u00a75";
        GOLD = "\u00a76";
        GRAY = "\u00a77";
        DARK_GRAY = "\u00a78";
        BLUE = "\u00a79";
        GREEN = "\u00a7a";
        AQUA = "\u00a7b";
        RED = "\u00a7c";
        LIGHT_PURPLE = "\u00a7d";
        YELLOW = "\u00a7e";
        WHITE = "\u00a7f";
        acidSource = new DamageSource("acid");
        trapSource = new DamageSource("trap");
        arcanaSource = new DamageSource("arcana");
        spikeSource = new DamageSource("spikes");
    }
}

