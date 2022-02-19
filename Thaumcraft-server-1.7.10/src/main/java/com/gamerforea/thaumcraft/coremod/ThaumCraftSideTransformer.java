package com.gamerforea.thaumcraft.coremod;

import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;
import thaumcraft.codechicken.core.launch.DepLoader;

import java.util.Iterator;
import java.util.List;

public final class ThaumCraftSideTransformer extends BaseClassTransformer
{
	@Override
	public boolean transform(ClassNode classNode)
	{
		boolean transformed = transformGetItemMethod(classNode);
		transformed = transformIsFlowerPotMethod(classNode) || transformed;
		return transformed;
	}

	private static boolean transformGetItemMethod(ClassNode classNode)
	{
		String methodName = DepLoader.isObfuscated() ? "func_149694_d" : "getItem";
		String methodDesc = "(Lnet/minecraft/world/World;III)Lnet/minecraft/item/Item;";
		MethodNode methodNode = findMethod(classNode, methodName, methodDesc);
		boolean transformed = transformMethodNode(classNode, methodNode);

		if (!transformed)
		{
			String methodObfName = "d";
			String methodObfDesc = "(Lahb;III)Ladb;";
			MethodNode methodObfNode = findMethod(classNode, methodObfName, methodObfDesc);
			transformed = transformMethodNode(classNode, methodObfNode);
		}

		return transformed;
	}

	private static boolean transformIsFlowerPotMethod(ClassNode classNode)
	{
		String methodName = DepLoader.isObfuscated() ? "func_149648_K" : "isFlowerPot";
		String methodDesc = "()Z";
		MethodNode methodNode = findMethod(classNode, methodName, methodDesc);
		boolean transformed = transformMethodNode(classNode, methodNode);

		if (!transformed)
		{
			String methodObfName = "K";
			String methodObfDesc = "()Z";
			MethodNode methodObfNode = findMethod(classNode, methodObfName, methodObfDesc);
			transformed = transformMethodNode(classNode, methodObfNode);
		}

		return transformed;
	}

	private static boolean transformMethodNode(ClassNode classNode, MethodNode methodNode)
	{
		if (methodNode == null)
			return false;

		if (DEBUG)
			LOGGER.info("Found {}{} in {}", methodNode.name, methodNode.desc, classNode.name);

		List<AnnotationNode> annotations = methodNode.visibleAnnotations;
		if (annotations != null)
			for (Iterator<AnnotationNode> iterator = annotations.iterator(); iterator.hasNext(); )
			{
				AnnotationNode annotationNode = iterator.next();

				if (DEBUG)
					LOGGER.info("{}: {}", annotationNode.desc, annotationNode.values);

				if (annotationNode.desc.equals("Lcpw/mods/fml/relauncher/SideOnly;"))
				{
					if (DEBUG)
						LOGGER.info("Transforming class {}...", classNode.name);

					iterator.remove();
					return true;
				}
			}

		return false;
	}
}
