package KAMKEEL.PluginMod.Items.Weapons;

import org.lwjgl.opengl.GL11;

public class ItemGlaive extends ItemPluginWeaponInterface{

	public ItemGlaive(int par1, ToolMaterial tool) {
		super(par1, tool);
	}

	@Override
	public void renderSpecial(){
        GL11.glTranslatef(0.03F, -0.4f, 0.08f);
	}
}
