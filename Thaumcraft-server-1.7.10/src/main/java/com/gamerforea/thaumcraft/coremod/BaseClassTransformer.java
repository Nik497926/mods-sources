package com.gamerforea.thaumcraft.coremod;

import net.minecraft.launchwrapper.IClassTransformer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

public abstract class BaseClassTransformer implements IClassTransformer
{
	protected static final boolean DEBUG = false;
	protected static final Logger LOGGER = LogManager.getLogger("ThaumCraft");

	@Override
	public final byte[] transform(String name, String transformedName, byte[] basicClass)
	{
		if (basicClass == null || basicClass.length == 0)
			return basicClass;

		ClassNode classNode = new ClassNode();
		new ClassReader(basicClass).accept(classNode, 0);

		boolean transformed = this.transform(classNode);
		if (transformed)
		{
			ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
			classNode.accept(classWriter);
			byte[] bytes = classWriter.toByteArray();
			if (DEBUG)
				LOGGER.info("Class {} transformed", classNode.name);
			return bytes;
		}

		return basicClass;
	}

	public abstract boolean transform(ClassNode classNode);

	protected static MethodNode findMethod(ClassNode classNode, String name, String desc)
	{
		for (MethodNode methodNode : classNode.methods)
		{
			if (methodNode.name.equals(name) && methodNode.desc.equals(desc))
				return methodNode;
		}
		return null;
	}
}
