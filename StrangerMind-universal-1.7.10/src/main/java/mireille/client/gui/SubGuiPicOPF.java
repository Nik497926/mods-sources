package mireille.client.gui;

import com.creativemd.creativecore.common.gui.*;
import cpw.mods.fml.relauncher.*;
import mireille.common.tileentity.*;
import com.creativemd.creativecore.common.gui.controls.*;
import com.creativemd.creativecore.common.gui.controls.GuiButton;
import com.creativemd.creativecore.common.gui.controls.GuiLabel;
import com.creativemd.creativecore.common.gui.event.*;
import net.minecraft.nbt.*;
import com.n247s.api.eventapi.eventsystem.*;
import net.minecraft.client.gui.*;

@SideOnly(Side.CLIENT)
public class SubGuiPicOPF extends SubGui
{
    public TileEntityPicFrame frame;
    private boolean anim;
    
    public SubGuiPicOPF(final TileEntityPicFrame frame, final boolean animation) {
        super(200, 200);
        this.frame = frame;
        this.anim = animation;
    }
    
    public void createControls() {
        final GuiTextfield url = new GuiTextfield("url", this.frame.url, 5, 5, 164, 20);
        url.maxLength = 512;
        super.controls.add(url);
        super.controls.add(new GuiTextfield("sizeX", this.frame.sizeX + "", 5, 30, 40, 20).setFloatOnly());
        super.controls.add(new GuiTextfield("sizeY", this.frame.sizeY + "", 50, 30, 40, 20).setFloatOnly());
        super.controls.add(new GuiButton("reX", "x->y", 95, 30, 50));
        super.controls.add(new GuiButton("reY", "y->x", 145, 30, 50));
        super.controls.add(new GuiCheckBox("flipX", "flip (x-axis)", 5, 50, this.frame.flippedX));
        super.controls.add(new GuiCheckBox("flipY", "flip (y-axis)", 80, 50, this.frame.flippedY));
        super.controls.add(new GuiStateButton("posX", (int)this.frame.posX, 5, 70, 70, 20, new String[] { "left (x)", "center (x)", "right (x)" }));
        super.controls.add(new GuiStateButton("posY", (int)this.frame.posY, 80, 70, 70, 20, new String[] { "left (y)", "center (y)", "right (y)" }));
        super.controls.add(new GuiStateButton("rotation", (int)this.frame.rotation, 5, 100, 80, 20, new String[] { "rotation: 0", "rotation: 1", "rotation: 2", "rotation: 3" }));
        super.controls.add(new GuiCheckBox("visibleFrame", "visible Frame", 90, 105, this.frame.visibleFrame));
        super.controls.add(new GuiLabel("render distance (blocks):", 5, 125));
        super.controls.add(new GuiSteppedSlider("renderDistance", 5, 140, 100, 20, 5, 128, this.frame.renderDistance));
        super.controls.add(new GuiButton("Save", 120, 140, 50));
    }
    
    @CustomEventSubscribe
    public void onClicked(final ControlClickEvent event) {
        if (event.source.is("reX") || event.source.is("reY")) {
            final GuiTextfield sizeXField = (GuiTextfield)this.getControl("sizeX");
            final GuiTextfield sizeYField = (GuiTextfield)this.getControl("sizeY");
            float x = 1.0f;
            try {
                x = Float.parseFloat(sizeXField.text);
            }
            catch (Exception var19) {
                x = 1.0f;
            }
            float y = 1.0f;
            try {
                y = Float.parseFloat(sizeYField.text);
            }
            catch (Exception var20) {
                y = 1.0f;
            }
            if (this.frame.texture != null) {
                if (event.source.is("reX")) {
                    sizeYField.text = "" + this.frame.texture.height / (this.frame.texture.width / x);
                }
                else {
                    sizeXField.text = "" + this.frame.texture.width / (this.frame.texture.height / y);
                }
            }
        }
        if (event.source.is("Save")) {
            final NBTTagCompound nbt = new NBTTagCompound();
            final GuiTextfield sizeYField = (GuiTextfield)this.getControl("url");
            final GuiTextfield sizeX = (GuiTextfield)this.getControl("sizeX");
            final GuiTextfield sizeY = (GuiTextfield)this.getControl("sizeY");
            final GuiStateButton buttonPosX = (GuiStateButton)this.getControl("posX");
            final GuiStateButton buttonPosY = (GuiStateButton)this.getControl("posY");
            final GuiStateButton rotation = (GuiStateButton)this.getControl("rotation");
            final GuiCheckBox flipX = (GuiCheckBox)this.getControl("flipX");
            final GuiCheckBox flipY = (GuiCheckBox)this.getControl("flipY");
            final GuiCheckBox visibleFrame = (GuiCheckBox)this.getControl("visibleFrame");
            final GuiSteppedSlider renderDistance = (GuiSteppedSlider)this.getControl("renderDistance");
            nbt.setByte("posX", (byte)buttonPosX.getState());
            nbt.setByte("posY", (byte)buttonPosY.getState());
            nbt.setByte("rotation", (byte)rotation.getState());
            nbt.setBoolean("flippedX", flipX.value);
            nbt.setBoolean("flippedY", flipY.value);
            nbt.setBoolean("visibleFrame", visibleFrame.value);
            nbt.setBoolean("anim", this.anim);
            nbt.setInteger("render", (int)renderDistance.value);
            nbt.setString("url", sizeYField.text);
            float x2 = 1.0f;
            float y2 = 1.0f;
            try {
                x2 = Float.parseFloat(sizeX.text);
            }
            catch (Exception var21) {
                x2 = 1.0f;
            }
            try {
                y2 = Float.parseFloat(sizeY.text);
            }
            catch (Exception var22) {
                y2 = 1.0f;
            }
            nbt.setFloat("x", x2);
            nbt.setFloat("y", y2);
            this.sendPacketToServer(0, nbt);
        }
    }
    
    public void drawOverlay(final FontRenderer fontRenderer) {
    }
}
