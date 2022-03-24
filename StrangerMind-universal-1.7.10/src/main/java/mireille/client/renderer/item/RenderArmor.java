package mireille.client.renderer.item;

import net.minecraftforge.client.*;
import net.minecraft.item.*;
import org.lwjgl.opengl.*;
import mireille.client.handler.*;
import net.minecraft.entity.*;

public class RenderArmor implements IItemRenderer
{
    private ItemArmor armor;
    
    public RenderArmor() {
    }
    
    public RenderArmor(final ItemArmor armor) {
        this.armor = armor;
    }
    
    public boolean handleRenderType(final ItemStack item, final IItemRenderer.ItemRenderType type) {
        return true;
    }
    
    public boolean shouldUseRenderHelper(final IItemRenderer.ItemRenderType type, final ItemStack item, final IItemRenderer.ItemRendererHelper helper) {
        return true;
    }
    
    public void renderItem(final IItemRenderer.ItemRenderType type, final ItemStack stack, final Object... data) {
        GL11.glPushMatrix();
        GL11.glCullFace(1029);
        GL11.glEnable(2884);
        GL11.glEnable(2896);
        GL11.glEnable(16384);
        GL11.glEnable(16385);
        ResourceHandler.bindResource(this.armor.getArmorTexture(stack, (Entity)null, 0, (String)null).replace("mireille:", ""));
        if (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON || type == IItemRenderer.ItemRenderType.EQUIPPED) {
            GL11.glTranslated(0.5, 0.5, 0.5);
            GL11.glRotated(180.0, 0.0, 1.0, 0.0);
        }
        GL11.glTranslated(0.0, (this.armor.armorType == 0) ? -0.25 : ((this.armor.armorType == 1) ? 0.42 : ((this.armor.armorType == 2) ? 1.05 : 1.5)), 0.0);
        GL11.glRotated(180.0, -1.0, 0.0, 1.0);
        this.armor.getArmorModel((EntityLivingBase)null, stack, 0).render((Entity)null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
        GL11.glDisable(2884);
        GL11.glPopMatrix();
    }
}
