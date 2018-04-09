package com.philipgreen.spellbook.storage

import com.philipgreen.spellbook.model.Spell

/**
 * Created by pgreen on 12/2/17.
 */
object SpellList: SpellStorage {

    val spells = mutableListOf<Spell>()

    override fun saveSpells(spellList: List<Spell>) {
        spellList.forEach {
            if (!spells.contains(it)) {
                spells.add(it)
            }
        }
    }

    override fun getSpellListForClass(characterCass: String) = spells.filter { it.availableClasses.contains(characterCass) }

    override fun getAllSpells(): List<Spell> = spells
}