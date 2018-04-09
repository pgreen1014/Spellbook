package com.philipgreen.spellbook.model

import com.philipgreen.spellbook.R

/**
 * Created by pgreen on 12/3/17.
 */
enum class SpellCastingClass {

    ALL_SPELLS {
        override fun getUIStringID(): Int = R.string.all_spells
        override fun getPrimaryColorResId(): Int = R.color.all_spells_colorPrimary
        override fun getPrimaryDarkColorResId(): Int = R.color.all_spells_colorPrimaryDark
        override fun getItemListColorAccentResId(): Int = R.color.all_spells_itemListAccent
        override fun getAccentColorResId(): Int = R.color.all_spells_colorAccent
        override fun toString(): String = "all spells"
    },

    BARD {
        override fun getPrimaryColorResId(): Int = R.color.bard_colorPrimary
        override fun getPrimaryDarkColorResId(): Int = R.color.bard_colorPrimaryDark
        override fun getAccentColorResId(): Int = R.color.bard_colorAccent
        override fun getItemListColorAccentResId(): Int = R.color.bard_itemListAccent
        override fun getUIStringID(): Int = R.string.bard
        override fun toString(): String = "bard"
    },

    CLERIC {
        override fun getPrimaryColorResId(): Int = R.color.cleric_colorPrimary
        override fun getPrimaryDarkColorResId(): Int = R.color.cleric_colorPrimaryDark
        override fun getAccentColorResId(): Int = R.color.cleric_colorAccent
        override fun getItemListColorAccentResId(): Int = R.color.cleric_itemListAccent
        override fun getUIStringID(): Int = R.string.cleric
        override fun toString(): String = "cleric"
    },

    DRUID {
        override fun getPrimaryColorResId(): Int = R.color.druid_colorPrimary
        override fun getPrimaryDarkColorResId(): Int = R.color.druid_colorPrimaryDark
        override fun getAccentColorResId(): Int = R.color.druid_colorAccent
        override fun getItemListColorAccentResId(): Int = R.color.druid_itemListAccent
        override fun getUIStringID(): Int = R.string.druid
        override fun toString(): String = "druid"
    },

    PALADIN {
        override fun getPrimaryColorResId(): Int = R.color.paladin_colorPrimary
        override fun getPrimaryDarkColorResId(): Int = R.color.paladin_colorPrimaryDark
        override fun getAccentColorResId(): Int = R.color.paladin_colorAccent
        override fun getItemListColorAccentResId(): Int = R.color.paladin_itemListAccent
        override fun getUIStringID(): Int = R.string.paladin
        override fun toString(): String = "paladin"
    },

    RANGER {
        override fun getPrimaryColorResId(): Int = R.color.ranger_colorPrimary
        override fun getPrimaryDarkColorResId(): Int = R.color.ranger_colorPrimaryDark
        override fun getAccentColorResId(): Int = R.color.ranger_colorAccent
        override fun getItemListColorAccentResId(): Int = R.color.ranger_itemListAccent
        override fun getUIStringID(): Int = R.string.ranger
        override fun toString(): String = "ranger"
    },

    SORCERER {
        override fun getPrimaryColorResId(): Int = R.color.sorcerer_colorPrimary
        override fun getPrimaryDarkColorResId(): Int = R.color.sorcerer_colorPrimaryDark
        override fun getAccentColorResId(): Int = R.color.sorcerer_colorAccent
        override fun getItemListColorAccentResId(): Int = R.color.sorcerer_itemListAccent
        override fun getUIStringID(): Int = R.string.sorcerer
        override fun toString(): String = "sorcerer"
    },

    WARLOCK {
        override fun getPrimaryColorResId(): Int = R.color.warlock_colorPrimary
        override fun getPrimaryDarkColorResId(): Int = R.color.warlock_colorPrimaryDark
        override fun getAccentColorResId(): Int = R.color.warlock_colorAccent
        override fun getItemListColorAccentResId(): Int = R.color.warlock_itemListAccent
        override fun getUIStringID(): Int = R.string.warlock
        override fun toString(): String = "warlock"
    },

    WIZARD {
        override fun getPrimaryColorResId(): Int = R.color.wizard_colorPrimary
        override fun getPrimaryDarkColorResId(): Int = R.color.wizard_colorPrimaryDark
        override fun getAccentColorResId(): Int = R.color.wizard_colorAccent
        override fun getItemListColorAccentResId(): Int = R.color.wizard_itemListAccent
        override fun getUIStringID(): Int = R.string.wizard
        override fun toString(): String = "wizard"
    };

    abstract fun getUIStringID(): Int
    abstract fun getPrimaryColorResId(): Int
    abstract fun getPrimaryDarkColorResId(): Int
    abstract fun getItemListColorAccentResId(): Int
    abstract fun getAccentColorResId(): Int
}