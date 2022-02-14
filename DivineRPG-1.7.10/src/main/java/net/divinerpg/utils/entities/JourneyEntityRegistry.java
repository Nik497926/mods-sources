/*
 * Decompiled with CFR 0.152.
 */
package net.divinerpg.utils.entities;

import net.divinerpg.entities.base.EntityTrollTrader;
import net.divinerpg.entities.boil.EntityBrisonScreamer;
import net.divinerpg.entities.boil.EntityBurningLight;
import net.divinerpg.entities.boil.EntityFrightener;
import net.divinerpg.entities.boil.EntityLavasnake;
import net.divinerpg.entities.boil.EntityScreamer;
import net.divinerpg.entities.boil.npc.EntityBoilTrader;
import net.divinerpg.entities.boil.npc.EntityEscapedConvict;
import net.divinerpg.entities.euca.EntityAlloyMender;
import net.divinerpg.entities.euca.EntityCorallator;
import net.divinerpg.entities.euca.EntityDynaster;
import net.divinerpg.entities.euca.EntityEucaCharger;
import net.divinerpg.entities.euca.EntityGoldbot;
import net.divinerpg.entities.euca.EntityGolder;
import net.divinerpg.entities.euca.EntityGolditeMage;
import net.divinerpg.entities.euca.EntityGoldwing;
import net.divinerpg.entities.euca.EntityNoExpShimmerer;
import net.divinerpg.entities.euca.EntityShimmerer;
import net.divinerpg.entities.euca.EntitySilverbot;
import net.divinerpg.entities.lelyetia.EntityExohead;
import net.divinerpg.entities.lelyetia.EntityFlye;
import net.divinerpg.entities.lelyetia.EntityGraw;
import net.divinerpg.entities.lelyetia.EntityGrawShot;
import net.divinerpg.entities.lelyetia.EntityGrobbler;
import net.divinerpg.entities.lelyetia.EntityLelyetianCaster;
import net.divinerpg.entities.lelyetia.EntityLelyetianShot;
import net.divinerpg.entities.lelyetia.EntityLelyetianWarrior;
import net.divinerpg.entities.vanilla.EntityCaveling;
import net.divinerpg.entities.vanilla.EntityHellBot;
import net.divinerpg.entities.vanilla.EntityJourneyBlacksmith;
import net.divinerpg.entities.vanilla.EntityJourneyMage;
import net.divinerpg.entities.vanilla.EntityNetherBeast;
import net.divinerpg.entities.vanilla.EntityNoExpHellBot;
import net.divinerpg.entities.vanilla.EntityReaper;
import net.divinerpg.entities.vanilla.EntityRockiteGolem;
import net.divinerpg.entities.vanilla.EntityStonewalker;
import net.divinerpg.entities.vanilla.EntityTempleGuardian;
import net.divinerpg.entities.vanilla.EntityWitherspine;
import net.divinerpg.utils.Util;

public class JourneyEntityRegistry {
    public static void init() {
        Util.registerDivineRPGMob(EntityDynaster.class, "Dynaster");
        Util.registerDivineRPGMob(EntityAlloyMender.class, "AlloyMender");
        Util.registerDivineRPGMob(EntityEucaCharger.class, "EucaCharger");
        Util.registerDivineRPGMob(EntityGoldbot.class, "GoldBot");
        Util.registerDivineRPGMob(EntityGolder.class, "Golder");
        Util.registerDivineRPGMob(EntityGoldwing.class, "Roc");
        Util.registerDivineRPGMob(EntityShimmerer.class, "Shimmerer");
        Util.registerDivineRPGMob(EntitySilverbot.class, "Silverbot");
        Util.registerDivineRPGMob(EntityGolditeMage.class, "GolditeMage");
        Util.registerDivineRPGMob(EntityCorallator.class, "Corallator");
        Util.registerDivineRPGMob(EntityNoExpShimmerer.class, "CorallatorShimmerer");
        Util.registerDivineRPGMob(EntityJourneyMage.class, "JourneyMage");
        Util.registerDivineRPGMob(EntityJourneyBlacksmith.class, "JourneyBlacksmith");
        Util.registerDivineRPGMob(EntityRockiteGolem.class, "RockiteGolem");
        Util.registerDivineRPGMob(EntityStonewalker.class, "Stonewalker");
        Util.registerDivineRPGMob(EntityCaveling.class, "Caveling");
        Util.registerDivineRPGMob(EntityTempleGuardian.class, "JourneyGuard");
        Util.registerDivineRPGMob(EntityHellBot.class, "HellBot");
        Util.registerDivineRPGMob(EntityNoExpHellBot.class, "DungeonBot");
        Util.registerDivineRPGMob(EntityReaper.class, "Reaper");
        Util.registerDivineRPGMob(EntityWitherspine.class, "Witherspine");
        Util.registerDivineRPGMob(EntityNetherBeast.class, "NetherBeast");
        Util.registerDivineRPGMob(EntityExohead.class, "exohead");
        Util.registerDivineRPGMob(EntityFlye.class, "flye");
        Util.registerDivineRPGMob(EntityGrobbler.class, "grobbler");
        Util.registerDivineRPGMob(EntityGrawShot.class, "GrawShot");
        Util.registerDivineRPGMob(EntityGraw.class, "graw");
        Util.registerDivineRPGMob(EntityLelyetianShot.class, "lelyetianshot");
        Util.registerDivineRPGMob(EntityLelyetianCaster.class, "lelyetianCaster");
        Util.registerDivineRPGMob(EntityLelyetianWarrior.class, "lelyetianWarrior");
        Util.registerDivineRPGMob(EntityTrollTrader.class, "trolltrader");
        Util.registerDivineRPGMob(EntityScreamer.class, "Screamer");
        Util.registerDivineRPGMob(EntityBrisonScreamer.class, "BrisonScreamer");
        Util.registerDivineRPGMob(EntityBurningLight.class, "BurningLight");
        Util.registerDivineRPGMob(EntityFrightener.class, "Frightener");
        Util.registerDivineRPGMob(EntityLavasnake.class, "Lavasnake");
        Util.registerDivineRPGMob(EntityEscapedConvict.class, "EscapedConvict");
        Util.registerDivineRPGMob(EntityBoilTrader.class, "BoilTrader");
    }
}

