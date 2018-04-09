package com.philipgreen.spellbook.ui.spelllist

import android.content.Context
import com.philipgreen.spellbook.model.Spell
import com.philipgreen.spellbook.ui.ClassThemeable

/**
 * Created by pgreen on 12/2/17.
 */
interface SpellListContract {

    interface View: ClassThemeable {
        fun provideContext(): Context
    }

    interface Presenter {
        fun getSpellListForClass(charClass: String): List<Spell>
        fun getColorResourceID(): Int
        fun searchSpellsByName(spellName: String): List<Spell>
    }

}