/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.integration.minetweaker.utils;

import minetweaker.IUndoableAction;

public abstract class BaseUndoable
implements IUndoableAction {
    protected final String name;
    protected boolean success = false;

    protected BaseUndoable(String name) {
        this.name = name;
    }

    protected String getRecipeInfo() {
        return "Unknown item";
    }

    public boolean canUndo() {
        return this.success;
    }

    public String describe() {
        return String.format("[ModTweaker2] Altering %s Recipe(s) for %s", this.name, this.getRecipeInfo());
    }

    public String describeUndo() {
        return String.format("[ModTweaker2] Reverting %s Recipe(s) changes for %s", this.name, this.getRecipeInfo());
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof BaseUndoable)) {
            return false;
        }
        BaseUndoable u = (BaseUndoable)obj;
        return this.name.equals(u.name);
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public Object getOverrideKey() {
        return null;
    }
}

