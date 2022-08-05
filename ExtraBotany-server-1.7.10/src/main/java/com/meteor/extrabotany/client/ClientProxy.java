/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.client;

import com.meteor.extrabotany.common.CommonProxy;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.client.model.IModelCustom;

public class ClientProxy
extends CommonProxy {
    public static int renderGaiaInvReader = 0;
    public static int renderGaiaMainInvReader = 0;
    public static int renderGaiaMainInvChest = 0;
    public static int renderPoolEfir = 0;
    public static int renderTransformater = 0;
    public static int renderEAltar = 0;
    public static int renderBoost = 0;
    public static int renderAutoPool = 0;
    public static int renderExPylon = 0;
    public static int renderPlate = 0;
    public static int renderTrade = 0;
    public static int renderDaisy = 0;
    public static int renderExtraSpreader = 0;

    @SideOnly(Side.CLIENT)
    public static IModelCustom model;
    @SideOnly(Side.CLIENT)
    public static IModelCustom modelPlate;
    @SideOnly(Side.CLIENT)
    public static IModelCustom modelTrade;
    @SideOnly(Side.CLIENT)
    public static IModelCustom modelDaisy;
}

