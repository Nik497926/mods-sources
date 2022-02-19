/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  org.apache.commons.lang3.builder.HashCodeBuilder
 */
package info.jbcs.minecraft.vending;

import java.io.Serializable;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class CustomStack
implements Serializable {
    public String name;
    public int damage;
    public NBTTagCompound tag;

    public int hashCode() {
        return new HashCodeBuilder(41, 59).append((Object)this.name).append(this.damage).append((Object)this.tag).hashCode();
    }

    public String toString() {
        return this.name + "|" + this.damage + "|" + (this.tag != null ? this.tag.toString() : "");
    }

    public CustomStack(ItemStack stack) {
        this.name = stack.getItem().delegate.name();
        this.damage = stack.getItemDamage();
        this.tag = stack.stackTagCompound;
    }

    public CustomStack(String name, short damage) {
        this.name = name;
        this.damage = damage;
    }

    public boolean equals(Object p_equals_1_) {
        if (p_equals_1_ instanceof CustomStack) {
            CustomStack stack = (CustomStack)p_equals_1_;
            return stack.name.equals(this.name) && stack.damage == this.damage && this.compareTag(stack, this);
        }
        return false;
    }

    private boolean compareTag(CustomStack stack, CustomStack customStack) {
        if (stack.tag == null == (customStack.tag == null)) {
            if (stack.tag != null && customStack.tag != null) {
                return stack.tag.equals((Object)customStack.tag);
            }
            return true;
        }
        return false;
    }
}

