package com.philipgreen.spellbook.storage

import com.philipgreen.spellbook.model.Spell

/**
 * Created by pgreen on 12/2/17.
 */
interface SpellStorage {

    fun saveSpells(spellList: List<Spell>)
    fun getSpellListForClass(characterCass: String): List<Spell>
    fun getAllSpells(): List<Spell>

}