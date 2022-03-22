/*
 * Decompiled with CFR 0.152.
 */
package com.meteor.extrabotany.common.lexicon;

import com.meteor.extrabotany.ExtraBotany;
import vazkii.botania.api.lexicon.LexiconCategory;
import vazkii.botania.common.lexicon.BLexiconEntry;

public class LLexiconEntry
extends BLexiconEntry {
    public LLexiconEntry(String unlocalizedName, LexiconCategory category) {
        super(unlocalizedName, category);
        this.setKnowledgeType(ExtraBotany.legendaryKnowledge);
    }
}

