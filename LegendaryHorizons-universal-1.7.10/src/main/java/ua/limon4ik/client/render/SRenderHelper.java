package ua.limon4ik.client.render;

import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class SRenderHelper {
    public static void renderItemStackInWorld(World world, ItemStack stack,double scale,double rotate){
        EntityItem item=new EntityItem(world,0,0,0,stack);
        item.hoverStart=0;
        GL11.glScaled(scale,scale,scale);
        GL11.glRotated(rotate,0,-1,0);
        if(stack.getItem() instanceof ItemBlock){
            GL11.glTranslated(0,0.045,0);
        }
        RenderItem.renderInFrame=true;
        RenderManager.instance.renderEntityWithPosYaw(item,0,0,0,0,0);
        RenderItem.renderInFrame=false;
    }
}
