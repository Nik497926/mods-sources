package mireille.core;

import net.minecraft.item.*;
import net.minecraftforge.common.util.*;

public class ModMaterials
{
    public static final Item.ToolMaterial MatShield;
    public static final Item.ToolMaterial MatMeowmere;
    
    static {
        MatShield = EnumHelper.addToolMaterial("MatShield", 3, 1000, 2.0f, -4.0f, 10);
        MatMeowmere = EnumHelper.addToolMaterial("MatMeowmere", 3, 1561, 8.0f, 4.0f, 10);
    }
}
