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
        MinecraftForge.EVENT_BUS.register(new EventKnowledgeTypeUnlock());
        FMLCommonHandler.instance().bus().register(new EventKnowledgeTypeUnlock());
        MinecraftForge.EVENT_BUS.register(new EventPunish());
        FMLCommonHandler.instance().bus().register(new EventPunish());
        MinecraftForge.EVENT_BUS.register(new EventHighDamageResistance());
        FMLCommonHandler.instance().bus().register(new EventHighDamageResistance());
        MinecraftForge.EVENT_BUS.register(new EventShield());
        FMLCommonHandler.instance().bus().register(new EventShield());
        MinecraftForge.EVENT_BUS.register(new EventUnbreakable());
        FMLCommonHandler.instance().bus().register(new EventUnbreakable());
        MinecraftForge.EVENT_BUS.register(new EventGaiaIII());
        FMLCommonHandler.instance().bus().register(new EventGaiaIII());
        MinecraftForge.EVENT_BUS.register(new EventElven());
        FMLCommonHandler.instance().bus().register(new EventElven());
        MinecraftForge.EVENT_BUS.register(new EventAchievement());
        FMLCommonHandler.instance().bus().register(new EventAchievement());
        MinecraftForge.EVENT_BUS.register(new EventMobDrop());
        FMLCommonHandler.instance().bus().register(new EventMobDrop());
        MinecraftForge.EVENT_BUS.register(new EventSkill());
        FMLCommonHandler.instance().bus().register(new EventSkill());
        CoreArmor.instance.init();
    }
}

