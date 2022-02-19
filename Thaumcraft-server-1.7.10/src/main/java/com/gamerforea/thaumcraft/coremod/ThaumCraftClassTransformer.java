package com.gamerforea.thaumcraft.coremod;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import thaumcraft.codechicken.core.launch.DepLoader;

import java.util.Collection;

public final class ThaumCraftClassTransformer extends BaseClassTransformer
{
	@Override
	public boolean transform(ClassNode classNode)
	{
		return classNode.name.equals("net/minecraft/item/Item") ? transformItem(classNode) : transformAnyClass(classNode);
	}

	private static boolean transformItem(ClassNode classNode)
	{
		if (addIfAbsent(classNode.interfaces, IItemHook.INTERNAL_CLASS_NAME))
		{
			if (DEBUG)
				LOGGER.info("Transforming class {}...", classNode.name);

			MethodVisitor mv = classNode.visitMethod(Opcodes.ACC_PUBLIC, IItemHook.METHOD_NAME, IItemHook.METHOD_DESC, null, null);
			mv.visitCode();
			mv.visitInsn(Opcodes.ICONST_0);
			mv.visitInsn(Opcodes.IRETURN);
			mv.visitEnd();

			if (DEBUG)
				LOGGER.info("Interface {} implemented in class {}", IItemHook.INTERNAL_CLASS_NAME, classNode.name);

			return true;
		}

		return false;
	}

	private static boolean transformAnyClass(ClassNode classNode)
	{
		String methodName = DepLoader.isObfuscated() ? "func_77659_a" : "onItemRightClick";
		String methodDesc = "(Lnet/minecraft/item/ItemStack;Lnet/minecraft/world/World;Lnet/minecraft/entity/player/EntityPlayer;)Lnet/minecraft/item/ItemStack;";

		if (findMethod(classNode, methodName, methodDesc) != null && findMethod(classNode, IItemHook.METHOD_NAME, IItemHook.METHOD_DESC) == null)
		{
			if (DEBUG)
				LOGGER.info("Transforming class {}...", classNode.name);

			MethodVisitor mv = classNode.visitMethod(Opcodes.ACC_PUBLIC, IItemHook.METHOD_NAME, IItemHook.METHOD_DESC, null, null);
			mv.visitCode();
			mv.visitInsn(Opcodes.ICONST_1);
			mv.visitInsn(Opcodes.IRETURN);
			mv.visitEnd();

			return true;
		}

		return false;
	}

	private static <T> boolean addIfAbsent(Collection<T> collection, T element)
	{
		return !collection.contains(element) && collection.add(element);
	}
}
