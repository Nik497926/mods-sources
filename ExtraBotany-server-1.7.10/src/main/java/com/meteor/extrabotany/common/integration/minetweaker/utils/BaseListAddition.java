/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.integration.minetweaker.utils;

import com.meteor.extrabotany.common.core.util.LogHelper;
import com.meteor.extrabotany.common.integration.minetweaker.utils.BaseListModification;
import java.util.List;

public abstract class BaseListAddition
extends BaseListModification {
    protected BaseListAddition(String name, List list) {
        super(name, list);
    }

    protected BaseListAddition(String name, List list, List recipies) {
        this(name, list);
        if (this.recipes != null) {
            this.recipes.addAll(recipies);
        }
    }

    public void apply() {
        if (!this.recipes.isEmpty()) {
            for (Object recipe : this.recipes) {
                if (recipe != null) {
                    if (this.list.add(recipe)) {
                        this.successful.add(recipe);
                        continue;
                    }
                    LogHelper.error(String.format("Error adding %s Recipe for %s", this.name, this.getRecipeInfo(recipe)));
                    continue;
                }
                LogHelper.error(String.format("Error adding %s Recipe: null object", this.name));
            }
        }
    }

    public void undo() {
        if (!this.successful.isEmpty()) {
            for (Object recipe : this.successful) {
                if (recipe != null) {
                    if (this.list.remove(recipe)) continue;
                    LogHelper.error(String.format("Error removing %s Recipe for %s", this.name, this.getRecipeInfo(recipe)));
                    continue;
                }
                LogHelper.error(String.format("Error removing %s Recipe: null object", this.name));
            }
        }
    }

    @Override
    public String describe() {
        return String.format("Adding %d %s Recipe(s) for %s", this.recipes.size(), this.name, this.getRecipeInfo());
    }

    @Override
    public String describeUndo() {
        return String.format("Removing %d %s Recipe(s) for %s", this.recipes.size(), this.name, this.getRecipeInfo());
    }
}

