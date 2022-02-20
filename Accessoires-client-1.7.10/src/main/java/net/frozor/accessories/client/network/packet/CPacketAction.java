/*
 * Decompiled with CFR 0.152.
 */
package net.frozor.accessories.client.network.packet;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import net.frozor.accessories.client.item.animation.AbstractAnimation;
import net.frozor.accessories.client.network.packet.IPacket;
import net.frozor.accessories.client.ui.UIItem;

public class CPacketAction implements IPacket {
    private Action action;
    private String indexItem;

    @Override
    public byte[] getBytes() {
        try {
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(bytes);
            out.writeUTF("ACS:" + this.action.name());
            out.writeUTF(this.indexItem);
            return bytes.toByteArray();
        }
        catch (IOException ex) {
            ex.printStackTrace();
            return new byte[0];
        }
    }

    public CPacketAction(Action action, String indexItem) {
        this.action = action;
        this.indexItem = indexItem;
    }

    public static final class Action {
        private static final Action[] $VALUES;
        public static final Action TAKEOFF;
        public static final Action PURCHASE;
        public static final Action EQUIP;

        public String name;

        private Action(String name, int i) {
            this.name = name;
        }

        public String name() {
            return name;
        }

        static {
            PURCHASE = new Action("PURCHASE", 0);
            EQUIP = new Action("EQUIP", 1);
            TAKEOFF = new Action("TAKEOFF", 2);
            Action[] actionArray = new Action[3];
            actionArray[0] = PURCHASE;
            actionArray[1] = EQUIP;
            actionArray[2] = TAKEOFF;
            $VALUES = actionArray;
        }

        public static Action valueOf(String name) {
            for (Action a : $VALUES) {
                if (a.name.equals(name)) return a;
            }

            return TAKEOFF;
        }

        public static Action[] values() {
            return (Action[])$VALUES.clone();
        }
    }
}

