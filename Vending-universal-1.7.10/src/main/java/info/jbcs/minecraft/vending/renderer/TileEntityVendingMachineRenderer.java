/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  org.lwjgl.opengl.GL11
 */
package info.jbcs.minecraft.vending.renderer;

import info.jbcs.minecraft.vending.EconomyControl;
import info.jbcs.minecraft.vending.Vending;
import info.jbcs.minecraft.vending.tileentity.TileEntityVendingMachine;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;

public class TileEntityVendingMachineRenderer
extends TileEntitySpecialRenderer {
    RenderItem renderer = new RenderItem();

    public TileEntityVendingMachineRenderer() {
        this.renderer.setRenderManager(RenderManager.instance);
    }

    public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {
        int minPriceBought;
        TileEntityVendingMachine machine = (TileEntityVendingMachine)tileentity;
        if (machine == null || machine.getBlockType() == null) {
            return;
        }
        int minPriceSold = EconomyControl.getMinPrice(machine.getSoldItems());
        int n = minPriceBought = tileentity.getWorldObj().getBlockMetadata(tileentity.xCoord, tileentity.yCoord, tileentity.zCoord) == 8 ? machine.getMoney() : EconomyControl.getMinPrice(machine.getBoughtItems()[0]);
        if ((double)minPriceSold > (double)minPriceBought * Vending.priceMultiplier) {
            return;
        }
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)x + 0.5f), (float)((float)y + 0.35f), (float)((float)z + 0.5f));
        GL11.glEnable((int)32826);
        int A = 0;
        for (ItemStack itemStack : machine.getSoldItems()) {
            if (itemStack == null) continue;
            EntityItem entity = new EntityItem(null, x, y, z, itemStack);
            entity.hoverStart = 0.0f;
            if (Minecraft.getMinecraft() != null && Minecraft.getMinecraft().thePlayer != null) {
                entity.age = Minecraft.getMinecraft().thePlayer.ticksExisted;
            }
            int meta = tileentity.getBlockMetadata();
            try {
                this.renderer.doRender(entity, -0.1 + (double)(A % 2) * 0.2, 0.0, -0.1 + (double)(A >= 2 ? 1 : 0) * 0.2, f, f);
            }
            catch (Throwable throwable) {
                // empty catch block
            }
            ++A;
        }
        GL11.glDisable((int)32826);
        GL11.glPopMatrix();
    }
}

