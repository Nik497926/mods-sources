package arima.anotherpack.items.weapons;

import org.lwjgl.opengl.GL11;

import arima.anotherpack.model.model_ruby_gun;
import arima.anotherpack.model.model_ruby_scythe;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

public class ScytheRenderer implements IItemRenderer {

	protected model_ruby_scythe ScytheModel = new model_ruby_scythe();
	protected model_ruby_gun GunModel = new model_ruby_gun();

	public ScytheRenderer() {
		ScytheModel = new model_ruby_scythe();
		GunModel = new model_ruby_gun();
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		switch (type) {
		case EQUIPPED:
			return true;
		default:
			return false;
		}
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		switch (type) {
		case EQUIPPED: {
			GL11.glPushMatrix();
			if (data[1] != null && data[1] instanceof EntityPlayer) {
				Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("arima:textures/items/weapons/Scythes/ruby_scythe_2.png"));
				if (Scythes.get_Crescent_Rose_State(item)) {
					GL11.glRotatef(100.0F, 1.0F, 0.0F, 0.0F);
					GL11.glRotatef(-150.0F, 0.0F, 1.0F, 0.0F);
					GL11.glRotatef(-95.0F, 0.0F, 0.0F, 1.0F);
					GL11.glTranslatef(0.1F, -0.75F, -0.1F);
					this.GunModel.render((Entity) data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
				} else {
					GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
					GL11.glRotatef(-40.0F, 0.0F, 1.0F, 0.0F);
					GL11.glRotatef(-90.0F, 0.0F, 0.0F, 1.0F);
					GL11.glTranslatef(-0.1F, -0.5F, -0.7F);
					this.ScytheModel.render((Entity) data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
				}
			} else {
				Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("arima:textures/items/weapons/Scythes/ruby_scythe_2.png"));
				GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(-40.0F, 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(-90.0F, 0.0F, 0.0F, 1.0F);
				GL11.glTranslatef(-0.1F, -0.5F, -0.7F);
				this.ScytheModel.render((Entity) data[1], 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
			}

			GL11.glPopMatrix();
		}
		default:
			break;
		}

	}

}
