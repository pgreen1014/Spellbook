package com.philipgreen.spellbook.ui

import com.philipgreen.spellbook.model.Spell
import com.philipgreen.spellbook.model.SpellCastingClass

/**
 * Created by pgreen on 12/2/17.
 */
interface MainActivityContract {

    interface View: ClassThemeable

    interface Presenter {
        fun loadSpellList(spellList: List<Spell>)
        fun changeActiveSpellCasterClass(spellCastingClass: SpellCastingClass)
        fun loadActiveSpellCasterClass()
    }

}