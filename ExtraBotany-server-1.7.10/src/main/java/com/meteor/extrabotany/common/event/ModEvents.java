/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.event;

import com.meteor.extrabotany.common.event.EventAchievement;
import com.meteor.extrabotany.common.event.EventElven;
import com.meteor.extrabotany.common.event.EventGaiaIII;
import com.meteor.extrabotany.common.event.EventHighDamageResistance;
import com.meteor.extrabotany.common.event.EventKnowledgeTypeUnlock;
import com.meteor.extrabotany.common.event.EventMobDrop;
import com.meteor.extrabotany.common.event.EventPunish;
import com.meteor.extrabotany.common.event.EventShield;
import com.meteor.extrabotany.common.event.EventSkill;
import com.meteor.extrabotany.common.event.EventUnbreakable;
import com.meteor.extrabotany.common.item.relic.legendary.armor.CoreArmor;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraftforge.common.MinecraftForge;

public class ModEvents {
    public static void init() {
        MinecraftForge.EVENT_BUS.register((Object)new EventKnowledgeTypeUnlock());
        FMLCommonHandler.instance().bus().register((Object)new EventKnowledgeTypeUnlock());
        MinecraftForge.EVENT_BUS.register((Object)new EventPunish());
        FMLCommonHandler.instance().bus().register((Object)new EventPunish());
        MinecraftForge.EVENT_BUS.register((Object)new EventHighDamageResistance());
        FMLCommonHandler.instance().bus().register((Object)new EventHighDamageResistance());
        MinecraftForge.EVENT_BUS.register((Object)new EventShield());
        FMLCommonHandler.instance().bus().register((Object)new EventShield());
        MinecraftForge.EVENT_BUS.register((Object)new EventUnbreakable());
        FMLCommonHandler.instance().bus().register((Object)new EventUnbreakable());
        MinecraftForge.EVENT_BUS.register((Object)new EventGaiaIII());
        FMLCommonHandler.instance().bus().register((Object)new EventGaiaIII());
        MinecraftForge.EVENT_BUS.register((Object)new EventElven());
        FMLCommonHandler.instance().bus().register((Object)new EventElven());
        MinecraftForge.EVENT_BUS.register((Object)new EventAchievement());
        FMLCommonHandler.instance().bus().register((Object)new EventAchievement());
        MinecraftForge.EVENT_BUS.register((Object)new EventMobDrop());
        FMLCommonHandler.instance().bus().register((Object)new EventMobDrop());
        MinecraftForge.EVENT_BUS.register((Object)new EventSkill());
        FMLCommonHandler.instance().bus().register((Object)new EventSkill());
        CoreArmor.instance.init();
    }
}

