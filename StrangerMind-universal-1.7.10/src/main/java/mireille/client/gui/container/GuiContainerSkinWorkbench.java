// 
// Decompiled by Procyon v0.5.36
// 

package mireille.client.gui.container;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import org.lwjgl.opengl.GL11;
import org.lwjgl.input.Mouse;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import mireille.network.PacketSkinWorkbench;
import mireille.network.NetworkHandler;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.item.ItemStack;
import mireille.core.ModItems;
import mireille.StrangerMind;
import net.minecraft.inventory.Container;
import mireille.common.container.ContainerSkinWorkbench;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.client.gui.GuiButton;
import mireille.common.tileentity.TileSkinWorkbench;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.inventory.GuiContainer;

@SideOnly(Side.CLIENT)
public class GuiContainerSkinWorkbench extends GuiContainer
{
    private static final ResourceLocation guiSkinWorkbench;
    private TileSkinWorkbench tile;
    private GuiButton addButton;
    private GuiButton selectButton;
    private int xSizeImage;
    private int ySizeImage;
    private int scrollPos;
    private int selectSkin;
    private String customName;
    private String open;
    private String choose;
    private String standart;
    private String color1;
    private String color2;
    private String color3;
    private String color4;
    
    public GuiContainerSkinWorkbench(final InventoryPlayer inventory, final TileSkinWorkbench tile) {
        super((Container)new ContainerSkinWorkbench(inventory, tile));
        this.xSizeImage = 176;
        this.ySizeImage = 172;
        this.scrollPos = 0;
        this.selectSkin = -1;
        this.customName = StrangerMind.resource("tile.SkinWorkbench.name");
        this.open = StrangerMind.resource("skin_workbench.open");
        this.choose = StrangerMind.resource("skin_workbench.choose");
        this.standart = StrangerMind.resource("skin_workbench.standart");
        this.color1 = StrangerMind.resource("skin_workbench.color1");
        this.color2 = StrangerMind.resource("skin_workbench.color2");
        this.color3 = StrangerMind.resource("skin_workbench.color3");
        this.color4 = StrangerMind.resource("skin_workbench.color4");
        this.tile = tile;
    }
    
    public void initGui() {
        super.initGui();
        ModItems.AllSkins[0] = new ItemStack(com.brandon3055.draconicevolution.common.ModItems.draconicDestructionStaff);
        this.scrollPos = 0;
        final int x = (super.width - this.xSizeImage) / 2;
        final int y = (super.height - this.ySizeImage) / 2;
        super.buttonList.clear();
        super.buttonList.add(this.addButton = new GuiCustomButton(0, x + 7, y + 54, 0, 194, 62, 12, this.open));
        super.buttonList.add(this.selectButton = new GuiCustomButton(1, x + 7, y + 70, 0, 194, 62, 12, this.choose));
        super.buttonList.add(new GuiCustomButton(2, x + 75, y + 8, 0, 172, 86, 11, ""));
        super.buttonList.add(new GuiCustomButton(3, x + 75, y + 19, 0, 172, 86, 11, ""));
        super.buttonList.add(new GuiCustomButton(4, x + 75, y + 30, 0, 172, 86, 11, ""));
        super.buttonList.add(new GuiCustomButton(5, x + 75, y + 41, 0, 172, 86, 11, ""));
        super.buttonList.add(new GuiCustomButton(6, x + 75, y + 52, 0, 172, 86, 11, ""));
        super.buttonList.add(new GuiCustomButton(7, x + 75, y + 63, 0, 172, 86, 11, ""));
        super.buttonList.add(new GuiCustomButton(8, x + 75, y + 74, 0, 172, 86, 11, ""));
    }
    
    public void updateScreen() {
        super.updateScreen();
        final ItemStack itemSlot0 = super.inventorySlots.getSlot(0).getStack();
        final ItemStack itemSlot2 = super.inventorySlots.getSlot(1).getStack();
        if (itemSlot0 != null) {
            final int[] copyFrom = itemSlot0.getTagCompound().getIntArray("allSkins");
            if (!this.checkArray(copyFrom, 0)) {
                final ItemStack result = new ItemStack(com.brandon3055.draconicevolution.common.ModItems.draconicDestructionStaff);
                result.setTagCompound((NBTTagCompound)itemSlot0.getTagCompound().copy());
                if (openSkin(result, result)) {
                    NetworkHandler.network.sendToServer((IMessage)new PacketSkinWorkbench(0, this.tile.xCoord, this.tile.yCoord, this.tile.zCoord, 0, ""));
                }
            }
        }
        if (itemSlot0 != null && itemSlot2 != null) {
            if (itemSlot0.getItem() == com.brandon3055.draconicevolution.common.ModItems.draconicDestructionStaff && this.isSkinItem(itemSlot2)) {
                this.addButton.enabled = true;
                this.addButton.displayString = this.color1 + this.open;
            }
            else {
                this.addButton.enabled = false;
                this.addButton.displayString = this.color2 + this.open;
            }
        }
        else {
            this.addButton.enabled = false;
            this.addButton.displayString = this.color2 + this.open;
        }
        if (itemSlot0 != null) {
            final int Skin = itemSlot0.getTagCompound().getInteger("Skin");
            if (this.selectSkin != -1 && this.selectSkin != Skin) {
                this.selectButton.enabled = true;
                this.selectButton.displayString = this.color1 + this.choose;
            }
            else {
                this.selectButton.enabled = false;
                this.selectButton.displayString = this.color2 + this.choose;
            }
        }
        else {
            this.selectButton.enabled = false;
            this.selectButton.displayString = this.color2 + this.choose;
        }
    }
    
    public boolean checkArray(final int[] array, final int n) {
        for (final int value : array) {
            if (value == n) {
                return true;
            }
        }
        return false;
    }
    
    public void handleMouseInput() {
        super.handleMouseInput();
        int i = Mouse.getEventDWheel();
        final int x = (super.width - this.xSizeImage) / 2;
        final int y = (super.height - this.ySizeImage) / 2;
        final int xMouse = Mouse.getEventX() * super.width / super.mc.displayWidth;
        final int yMouse = super.height - Mouse.getEventY() * super.height / super.mc.displayHeight - 1;
        final boolean flag = xMouse >= x + 75 && yMouse >= y + 8 && xMouse < x + 75 + 86 && yMouse < y + 85;
        if (flag && i != 0) {
            if (i > 0) {
                i = -1;
            }
            else {
                i = 1;
            }
            this.scrollPos += i;
            if (this.scrollPos < 0) {
                this.scrollPos = 0;
            }
            if (this.scrollPos > ModItems.AllSkins.length - 7) {
                this.scrollPos = ModItems.AllSkins.length - 7;
            }
        }
    }
    
    protected void drawListBox() {
        final int x = (super.width - this.xSizeImage) / 2;
        final int y = (super.height - this.ySizeImage) / 2;
        final ItemStack itemSlot0 = super.inventorySlots.getSlot(0).getStack();
        final int[] copyFrom = itemSlot0.getTagCompound().getIntArray("allSkins");
        if (ModItems.AllSkins.length > 7) {
            for (int i = 0; i < 7; ++i) {
                final GuiButton buttonSkin = (GuiButton) super.buttonList.get(2 + i);
                String str;
                if (i + this.scrollPos == 0) {
                    str = this.color1 + this.standart;
                }
                else {
                    str = ModItems.AllSkins[i + this.scrollPos].getDisplayName();
                }
                if (!this.checkArray(copyFrom, i + this.scrollPos)) {
                    if (str.contains(this.color4)) {
                        str = str.substring(2);
                    }
                    buttonSkin.displayString = this.color3 + str;
                }
                else {
                    buttonSkin.displayString = str;
                }
                buttonSkin.visible = true;
            }
            super.mc.getTextureManager().bindTexture(GuiContainerSkinWorkbench.guiSkinWorkbench);
            GL11.glPushMatrix();
            this.drawTexturedModalRect(x + 162, y + 7 + 59 * this.scrollPos / (ModItems.AllSkins.length - 7), 0, 218, 6, 19);
            GL11.glPopMatrix();
        }
    }
    
    protected void clearListBox() {
        for (int i = 0; i < 7; ++i) {
            final GuiButton buttonSkin = (GuiButton) super.buttonList.get(2 + i);
            buttonSkin.visible = false;
        }
    }
    
    protected boolean isSkinItem(final ItemStack stack) {
        for (int i = 0; i < ModItems.AllSkins.length; ++i) {
            final String str1 = ModItems.AllSkins[i].getUnlocalizedName();
            final String str2 = stack.getUnlocalizedName();
            if (str1.equals(str2)) {
                return true;
            }
        }
        return false;
    }
    
    protected void actionPerformed(final GuiButton button) {
        if (button.id == 0) {
            final ItemStack itemSlot0 = super.inventorySlots.getSlot(0).getStack();
            final ItemStack result = super.inventorySlots.getSlot(1).getStack();
            final ItemStack result2 = new ItemStack(com.brandon3055.draconicevolution.common.ModItems.draconicDestructionStaff);
            result2.setTagCompound((NBTTagCompound)itemSlot0.getTagCompound().copy());
            if (openSkin(result2, result)) {
                NetworkHandler.network.sendToServer((IMessage)new PacketSkinWorkbench(1, this.tile.xCoord, this.tile.yCoord, this.tile.zCoord, -1, ""));
            }
        }
        else if (button.id == 1) {
            final ItemStack itemSlot0 = super.inventorySlots.getSlot(0).getStack();
            final ItemStack result = new ItemStack(com.brandon3055.draconicevolution.common.ModItems.draconicDestructionStaff);
            result.setTagCompound((NBTTagCompound)itemSlot0.getTagCompound().copy());
            setSkin(result, this.selectSkin);
            super.inventorySlots.getSlot(0).putStack(result);
            final String name = ModItems.AllSkins[this.selectSkin].getDisplayName();
            result.setStackDisplayName(name);
            NetworkHandler.network.sendToServer((IMessage)new PacketSkinWorkbench(2, this.tile.xCoord, this.tile.yCoord, this.tile.zCoord, this.selectSkin, name));
        }
        else if (button.id >= 2 && button.id <= 8) {
            final ItemStack itemSlot0 = super.inventorySlots.getSlot(0).getStack();
            final int[] copyFrom = itemSlot0.getTagCompound().getIntArray("allSkins");
            final int sel = button.id - 2 + this.scrollPos;
            if (this.checkArray(copyFrom, sel)) {
                this.selectSkin = sel;
            }
            else {
                this.selectSkin = -1;
            }
        }
    }
    
    public static void setSkin(final ItemStack itemstack, final int skinID) {
        if (!itemstack.hasTagCompound()) {
            itemstack.setTagCompound(new NBTTagCompound());
        }
        itemstack.getTagCompound().setInteger("Skin", skinID);
    }
    
    public static boolean openSkin(final ItemStack itemstack, final ItemStack skin) {
        if (!itemstack.hasTagCompound()) {
            itemstack.setTagCompound(new NBTTagCompound());
        }
        final int[] copyFrom = itemstack.getTagCompound().getIntArray("allSkins");
        final int[] copyTo = new int[copyFrom.length + 1];
        for (int skinID = 0; skinID < copyFrom.length; ++skinID) {
            copyTo[skinID] = copyFrom[skinID];
        }
        int skinID = -1;
        for (int i = 0; i < ModItems.AllSkins.length; ++i) {
            final String str1 = ModItems.AllSkins[i].getUnlocalizedName();
            final String str2 = skin.getUnlocalizedName();
            if (str1.equals(str2)) {
                skinID = i;
                break;
            }
        }
        boolean haveSkin = false;
        for (final int value : copyFrom) {
            if (value == skinID) {
                haveSkin = true;
                break;
            }
        }
        if (!haveSkin) {
            copyTo[copyTo.length - 1] = skinID;
            itemstack.getTagCompound().setIntArray("allSkins", copyTo);
            return true;
        }
        return false;
    }
    
    protected void drawGuiContainerForegroundLayer(final int x, final int y) {
        super.fontRendererObj.drawString(this.customName, super.xSize / 2 - super.fontRendererObj.getStringWidth(this.customName) / 2, -13, 4210752);
        if (this.selectSkin == -1) {
            super.mc.getTextureManager().bindTexture(GuiContainerSkinWorkbench.guiSkinWorkbench);
            this.drawTexturedModalRect(37, 13, 86, 172, 22, 22);
        }
        else {
            GL11.glPushMatrix();
            final float scale = 2.0f;
            GL11.glTranslatef(32.0f, 8.0f, 0.0f);
            GL11.glScalef(scale, scale, 1.0f);
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            final ItemStack renderItem = ModItems.AllSkins[this.selectSkin];
            new RenderItem().renderItemAndEffectIntoGUI(super.fontRendererObj, Minecraft.getMinecraft().getTextureManager(), renderItem, 0, 0);
            GL11.glPopMatrix();
        }
    }
    
    protected void drawGuiContainerBackgroundLayer(final float partialTick, final int mouseX, final int mouseY) {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        super.mc.getTextureManager().bindTexture(GuiContainerSkinWorkbench.guiSkinWorkbench);
        final int x = (super.width - this.xSizeImage) / 2;
        final int y = (super.height - this.ySizeImage) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, this.xSizeImage, this.ySizeImage);
        final ItemStack itemSlot0 = super.inventorySlots.getSlot(0).getStack();
        if (itemSlot0 != null) {
            this.drawListBox();
        }
        else {
            this.clearListBox();
            this.selectSkin = -1;
        }
    }
    
    static {
        guiSkinWorkbench = new ResourceLocation("mireille:textures/gui/skin_workbench.png");
    }
    
    @SideOnly(Side.CLIENT)
    public class GuiCustomButton extends GuiButton
    {
        private int xImage;
        private int yImage;
        
        public GuiCustomButton(final int buttonID, final int xPos, final int yPos, final int xImage, final int yImage, final int wImage, final int hImage, final String buttonText) {
            super(buttonID, xPos, yPos, wImage, hImage, buttonText);
            this.xImage = xImage;
            this.yImage = yImage;
        }
        
        public void drawButton(final Minecraft mc, final int x, final int y) {
            if (super.visible) {
                mc.getTextureManager().bindTexture(GuiContainerSkinWorkbench.guiSkinWorkbench);
                final boolean flag = x >= super.xPosition && y >= super.yPosition && x < super.xPosition + super.width && y < super.yPosition + super.height;
                GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
                int offset = 0;
                if (flag && super.enabled) {
                    offset = super.height;
                }
                this.drawTexturedModalRect(super.xPosition, super.yPosition, this.xImage, this.yImage + offset, super.width, super.height);
                this.drawCenteredString(GuiContainerSkinWorkbench.this.fontRendererObj, super.displayString, super.xPosition + super.width / 2, super.yPosition + (super.height - 8) / 2, 14737632);
            }
        }
    }
}
