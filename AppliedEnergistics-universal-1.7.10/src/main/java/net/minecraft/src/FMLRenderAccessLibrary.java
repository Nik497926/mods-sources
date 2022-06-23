/*
 * Forge Mod Loader
 * Copyright (c) 2012-2013 cpw.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v2.1
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 *
 * Contributors:
 *     cpw - implementation
 */

package net.minecraft.src;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.common.FMLLog;

/**
 *
 * A static hook library for optifine and other basemod editing code to access FML functions
 *
 * @author cpw
 *
 */
public class FMLRenderAccessLibrary
{
	public static Logger getLogger()
	{
		Logger l = LogManager.getLogger("FMLRenderAccessLibrary");
		return l;
	}

	public static void log(Level level, String message)
	{
		FMLLog.log("FMLRenderAccessLibrary", level, message);
	}

	public static void log(Level level, String message, Throwable throwable)
	{
		FMLLog.log(level, throwable, message);
	}

	public static boolean renderWorldBlock(RenderBlocks renderer, IBlockAccess world, int x, int y, int z, Block block, int modelId)
	{
		return false;
	}

	public static void renderInventoryBlock(RenderBlocks renderer, Block block, int metadata, int modelID)
	{

	}

	@SuppressWarnings("deprecation")
	public static boolean renderItemAsFull3DBlock(int modelId)
	{
		return false;
	}

//    public static void doTextureCopy(Texture atlas, Texture source, int targetX, int targetY)
//    {
//        TextureFXManager.instance().getHelper().doTextureCopy(atlas, source, targetX, targetY);
//    }
}