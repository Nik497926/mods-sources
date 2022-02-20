/*
 * Decompiled with CFR 0.152.
 */
package net.frozor.accessories.client.data;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import net.frozor.accessories.AccessoriesCore;
import net.frozor.accessories.client.item.AccessoryItem;
import net.frozor.accessories.client.item.CategoryType;
import net.frozor.accessories.client.ui.UIScroll;
import net.minecraft.client.Minecraft;

public class EquipManager {
    public LinkedHashMap<CategoryType, LinkedHashMap<String, AccessoryItem>> items;
    private EquipData equipData;
    private int userBalance;
    private EquipData previewData;
    private HashMap<String, EquipData> players;
    private Minecraft minecraft = Minecraft.getMinecraft();

    public void updateItemPrice(String itemName, int price, boolean isHas, String author) {
        for (Map.Entry<CategoryType, LinkedHashMap<String, AccessoryItem>> mapEntry : this.items.entrySet()) {
            for (AccessoryItem item : mapEntry.getValue().values()) {
                if (!item.getINDEX().equalsIgnoreCase(itemName)) continue;
                item.setPrice(price);
                item.setHas(isHas);
                item.setAuthor(author);
            }
        }
    }

    public AccessoryItem getBody(String playerName) {
        if (this.players.containsKey(playerName)) {
            return this.players.get(playerName).BODY;
        }
        return null;
    }

    public int getUserBalance() {
        return this.userBalance;
    }

    public void setFamiliar(String playerName, String item) {
        if (this.minecraft.thePlayer.getDisplayName().equalsIgnoreCase(playerName)) {
            this.equipData.setFAMILIAR(this.items.get((Object)CategoryType.FAMILIAR).get(item));
        } else {
            EquipData equipData = this.players.containsKey(playerName) ? this.players.get(playerName) : new EquipData();
            equipData.setFAMILIAR(this.items.get((Object)CategoryType.FAMILIAR).get(item));
            this.players.put(playerName, equipData);
        }
    }

    public AccessoryItem getHead() {
        if (this.previewData.HEAD != null) {
            return this.previewData.HEAD;
        }
        return this.equipData.HEAD;
    }

    public void setShowCape(String playerName, boolean value) {
        if (!this.minecraft.thePlayer.getDisplayName().equalsIgnoreCase(playerName)) {
            EquipData equipData = this.players.containsKey(playerName) ? this.players.get(playerName) : new EquipData();
            equipData.setShowCape(value);
            this.players.put(playerName, equipData);
        }
    }

    public boolean isEquip(AccessoryItem item) {
        return item.equals(this.equipData.HEAD) || item.equals(this.equipData.FACE) || item.equals(this.equipData.BODY) || item.equals(this.equipData.FAMILIAR);
    }

    public EquipData getEquipData() {
        return this.equipData;
    }

    public AccessoryItem getFace() {
        if (this.previewData.FACE != null) {
            return this.previewData.FACE;
        }
        return this.equipData.FACE;
    }

    public EquipManager() {
        this.equipData = new EquipData();
        this.equipData.setShowCape(AccessoriesCore.SHOW_MY_CAPE_SETTING);
        this.previewData = new EquipData();
        this.players = new HashMap();
        this.items = new LinkedHashMap();
    }

    public AccessoryItem getFamiliar(String playerName) {
        if (this.players.containsKey(playerName)) {
            return this.players.get(playerName).FAMILIAR;
        }
        return null;
    }

    public boolean isShowCape(String playerName) {
        if (this.players.containsKey(playerName)) {
            return this.players.get(playerName).isShowCape();
        }
        return true;
    }

    public boolean isShowCape() {
        return this.equipData.isShowCape();
    }

    public AccessoryItem getBody() {
        if (this.previewData.BODY != null) {
            return this.previewData.BODY;
        }
        return this.equipData.BODY;
    }

    public void setFace(String playerName, String item) {
        if (this.minecraft.thePlayer.getDisplayName().equalsIgnoreCase(playerName)) {
            this.equipData.setFACE(this.items.get((Object)CategoryType.FACE).get(item));
        } else {
            EquipData equipData = this.players.containsKey(playerName) ? this.players.get(playerName) : new EquipData();
            equipData.setFACE(this.items.get((Object)CategoryType.FACE).get(item));
            this.players.put(playerName, equipData);
        }
    }

    public AccessoryItem getFace(String playerName) {
        if (this.players.containsKey(playerName)) {
            return this.players.get(playerName).FACE;
        }
        return null;
    }

    public AccessoryItem getHead(String playerName) {
        if (this.players.containsKey(playerName)) {
            return this.players.get(playerName).HEAD;
        }
        return null;
    }

    public void setBody(String playerName, String item) {
        if (this.minecraft.thePlayer.getDisplayName().equalsIgnoreCase(playerName)) {
            this.equipData.setBODY(this.items.get((Object)CategoryType.BODY).get(item));
        } else {
            EquipData equipData = this.players.containsKey(playerName) ? this.players.get(playerName) : new EquipData();
            equipData.setBODY(this.items.get((Object)CategoryType.BODY).get(item));
            this.players.put(playerName, equipData);
        }
    }

    public EquipData getPreviewData() {
        return this.previewData;
    }

    public AccessoryItem getFamiliar() {
        if (this.previewData.FAMILIAR != null) {
            return this.previewData.FAMILIAR;
        }
        return this.equipData.FAMILIAR;
    }

    public HashMap<String, EquipData> getPlayers() {
        return this.players;
    }

    public void setHead(String playerName, String item) {
        if (this.minecraft.thePlayer.getDisplayName().equalsIgnoreCase(playerName)) {
            this.equipData.setHEAD(this.items.get((Object)CategoryType.HEAD).get(item));
        } else {
            EquipData equipData = this.players.containsKey(playerName) ? this.players.get(playerName) : new EquipData();
            equipData.setHEAD(this.items.get((Object)CategoryType.HEAD).get(item));
            this.players.put(playerName, equipData);
        }
    }

    public void setUserBalance(int userBalance) {
        this.userBalance = userBalance;
    }

    public class EquipData {
        private boolean showCape = true;
        private AccessoryItem BODY;
        private AccessoryItem FACE;
        private AccessoryItem FAMILIAR;
        private AccessoryItem HEAD;

        public void setBODY(AccessoryItem BODY) {
            this.BODY = BODY;
        }

        public void setFACE(AccessoryItem FACE) {
            this.FACE = FACE;
        }

        public AccessoryItem getFAMILIAR() {
            return this.FAMILIAR;
        }

        public void setHEAD(AccessoryItem HEAD) {
            this.HEAD = HEAD;
        }

        public boolean isShowCape() {
            return this.showCape;
        }

        public void setFAMILIAR(AccessoryItem FAMILIAR) {
            this.FAMILIAR = FAMILIAR;
        }

        public String toString() {
            return UIScroll.l("\"Z\u0012B\u0017o\u0006_\u0006P/n&oZ") + this.HEAD + UIScroll.l("K\u000b!j$nZ") + this.FACE + UIScroll.l("K\u000b%d#rZ") + this.BODY + UIScroll.l("K\u000b!j*b+b&yZ") + this.FAMILIAR + UIScroll.l("K\u000b\u0014C\b\\$J\u0017NZ") + this.showCape + '}';
        }

        public AccessoryItem getBODY() {
            return this.BODY;
        }

        public void clear() {
            this.HEAD = null;
            this.FACE = null;
            this.BODY = null;
            this.FAMILIAR = null;
        }

        public void setShowCape(boolean showCape) {
            this.showCape = showCape;
        }

        public AccessoryItem getFACE() {
            return this.FACE;
        }

        public AccessoryItem getHEAD() {
            return this.HEAD;
        }
    }
}

