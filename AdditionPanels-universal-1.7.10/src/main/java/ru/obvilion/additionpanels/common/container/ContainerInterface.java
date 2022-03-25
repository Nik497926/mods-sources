//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ru.obvilion.additionpanels.common.container;

import appeng.api.config.SecurityPermissions;
import appeng.api.config.Settings;
import appeng.api.config.YesNo;
import appeng.api.util.IConfigManager;
import appeng.container.guisync.GuiSync;
import appeng.container.implementations.ContainerUpgradeable;
import appeng.container.slot.SlotFake;
import appeng.container.slot.SlotNormal;
import appeng.container.slot.SlotRestrictedInput;
import appeng.container.slot.SlotRestrictedInput.PlacableItemType;
import appeng.helpers.DualityInterface;
import appeng.helpers.IInterfaceHost;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import ru.obvilion.additionpanels.mixin.ContainerUpgradeableAccessor;
import ru.obvilion.additionpanels.common.utils.InterfaceData;

public class ContainerInterface extends ContainerUpgradeable{
    private final DualityInterface myDuality;
    @GuiSync(3)
    public YesNo bMode;
    @GuiSync(4)
    public YesNo iTermMode;

    public ContainerInterface(InventoryPlayer ip, IInterfaceHost te) {
        super(ip, te.getInterfaceDuality().getHost());
        this.bMode = YesNo.NO;
        this.iTermMode = YesNo.YES;
        this.myDuality = te.getInterfaceDuality();

        int x;
        IInventory iInventory = this.myDuality.getPatterns();
        int inventorySize = iInventory.getSizeInventory();
        int t = 0;
        InterfaceData data = InterfaceData.getFromPatterSize(inventorySize);
        for (x = 0; x < inventorySize; ++x) {
            this.addSlotToContainer(new SlotRestrictedInput(PlacableItemType.ENCODED_PATTERN, iInventory, x, 8 + 18 * (x - t * 9), data.yPattern + t * 18, this.getInventoryPlayer()));
            if ((x + 1) % 9 == 0) {
                t = t + 1;
            }
        }


        for(x = 0; x < 9; ++x) {
            this.addSlotToContainer(new SlotFake(this.myDuality.getConfig(), x, 8 + 18 * x, data.yConfig));
        }

        for(x = 0; x < 9; ++x) {
            this.addSlotToContainer(new SlotNormal(this.myDuality.getStorage(), x, 8 + 18 * x, data.yStorage));
        }


    }
    public int getHeight() {
        ContainerUpgradeableAccessor containerUpgradeableAccessor = (ContainerUpgradeableAccessor)this;
        return InterfaceData.getFromPatterSize(((IInterfaceHost) containerUpgradeableAccessor.getUpgradeable()).getInterfaceDuality().getPatterns().getSizeInventory()).height;
    }

    public int availableUpgrades() {
        return 1;
    }

    protected void setupConfig() {
        ContainerUpgradeableAccessor containerUpgradeableAccessor = (ContainerUpgradeableAccessor)this;
        IInventory upgrades = containerUpgradeableAccessor.getUpgradeable().getInventoryByName("upgrades");

        ( this).addSlotToContainer((new SlotRestrictedInput(SlotRestrictedInput.PlacableItemType.UPGRADES, upgrades, 0, 187, ((IInterfaceHost) containerUpgradeableAccessor.getUpgradeable()).getInterfaceDuality().getPatterns().getSizeInventory() > 9 ? 5: 8, this.getInventoryPlayer())).setNotDraggable());
    }

    public void detectAndSendChanges() {
        this.verifyPermissions(SecurityPermissions.BUILD, false);
        super.detectAndSendChanges();
    }

    protected void loadSettingsFromHost(IConfigManager cm) {
        this.setBlockingMode((YesNo) cm.getSetting(Settings.BLOCK));
        this.setInterfaceTerminalMode((YesNo) cm.getSetting(Settings.INTERFACE_TERMINAL));
    }

    public YesNo getBlockingMode() {
        return this.bMode;
    }

    private void setBlockingMode(YesNo bMode) {
        this.bMode = bMode;
    }

    public YesNo getInterfaceTerminalMode() {
        return this.iTermMode;
    }

    private void setInterfaceTerminalMode(YesNo iTermMode) {
        this.iTermMode = iTermMode;
    }








}
