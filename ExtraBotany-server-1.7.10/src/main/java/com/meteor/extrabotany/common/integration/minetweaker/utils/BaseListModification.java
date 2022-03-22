/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.integration.minetweaker.utils;

import com.meteor.extrabotany.common.integration.minetweaker.utils.BaseUndoable;
import java.util.LinkedList;
import java.util.List;

public abstract class BaseListModification
extends BaseUndoable {
    protected final List list;
    protected final LinkedList recipes;
    protected final LinkedList successful;

    protected BaseListModification(String name, List list) {
        super(name);
        this.list = list;
        this.recipes = new LinkedList();
        this.successful = new LinkedList();
    }

    @Override
    public boolean canUndo() {
        return !this.recipes.isEmpty() && !this.successful.isEmpty();
    }

    @Override
    protected String getRecipeInfo() {
        if (!this.recipes.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (Object recipe : this.recipes) {
                if (recipe == null) continue;
                sb.append(this.getRecipeInfo(recipe)).append(", ");
            }
            if (sb.length() > 0) {
                sb.setLength(sb.length() - 2);
            }
            return sb.toString();
        }
        return super.getRecipeInfo();
    }

    protected abstract String getRecipeInfo(Object var1);

    @Override
    public int hashCode() {
        return 37 * super.hashCode() ^ (this.list != null ? 43 * this.list.hashCode() : 0);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (!(obj instanceof BaseListModification)) {
            return false;
        }
        BaseListModification mod = (BaseListModification)obj;
        if (this.list != mod.list) {
            return false;
        }
        if (this.recipes.size() != mod.recipes.size()) {
            return false;
        }
        for (Object recipe : this.recipes) {
            boolean found = false;
            for (Object otherRecipe : mod.recipes) {
                if (!this.equals(recipe, otherRecipe)) continue;
                found = true;
                break;
            }
            if (found) continue;
            return false;
        }
        return true;
    }

    protected boolean equals(Object recipe, Object otherRecipe) {
        return recipe == otherRecipe || recipe.equals(otherRecipe);
    }
}

