package KAMKEEL.PluginMod.Items.Weapons;

import net.minecraft.item.Item;
import org.lwjgl.opengl.GL11;


public class ItemBroadSword extends ItemPluginWeaponInterface{

	public ItemBroadSword(Item.ToolMaterial tool) {
		super(tool);
	}
	
	public void renderSpecial(){
        GL11.glScalef(1f, 1.2f, 1f);
        GL11.glTranslatef(-0.12f, 0.14f, -0.16f);
        GL11.glRotatef(180, 0, 1, 0);
	}
	
}
