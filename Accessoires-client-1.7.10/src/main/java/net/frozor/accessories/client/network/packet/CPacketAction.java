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

public class CPacketAction
implements IPacket {
    private Action action;
    private String indexItem;

    @Override
    public byte[] getBytes() {
        try {
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(bytes);
            out.writeUTF(AbstractAnimation.l(" c2\u001a") + this.action.name());
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

    public static final class Action
    extends Enum<Action> {
        private static final /* synthetic */ Action[] $VALUES;
        public static final /* enum */ Action TAKEOFF;
        public static final /* enum */ Action PURCHASE;
        public static final /* enum */ Action EQUIP;

        /*
         * WARNING - Possible parameter corruption
         * WARNING - void declaration
         */
        private /* synthetic */ Action() {
            void var2_-1;
            void var1_-1;
        }

        static {
            PURCHASE = new Action(UIItem.l("u\u001fw\tm\u000bv\u000f"), 0);
            EQUIP = new Action(AbstractAnimation.l("e0u(p"), 1);
            TAKEOFF = new Action(UIItem.l("\u001ed\u0001`\u0005c\f"), 2);
            Action[] actionArray = new Action[3];
            actionArray[0] = PURCHASE;
            actionArray[1] = EQUIP;
            actionArray[2] = TAKEOFF;
            $VALUES = actionArray;
        }

        public static Action valueOf(String name) {
            return Enum.valueOf(Action.class, name);
        }

        public static Action[] values() {
            return (Action[])$VALUES.clone();
        }
    }
}

