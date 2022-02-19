package com.gamerforea.thaumcraft.coremod;

import org.objectweb.asm.Type;

public interface IItemHook
{
	String INTERNAL_CLASS_NAME = Type.getInternalName(IItemHook.class);
	String METHOD_NAME = "isOnItemRightClickOverridden";
	String METHOD_DESC = "()Z";

	boolean isOnItemRightClickOverridden();
}
