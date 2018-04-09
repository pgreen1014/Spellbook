package com.philipgreen.spellbook.ui.spelllist

import android.widget.Toast
import com.philipgreen.spellbook.R
import com.philipgreen.spellbook.model.ActiveSpellCastingClass
import com.philipgreen.spellbook.model.Spell
import com.philipgreen.spellbook.model.SpellCastingClass
import com.philipgreen.spellbook.storage.SpellStorage

/**
 * Created by pgreen on 12/2/17.
 */
class SpellListPresenter(private val view: SpellListContract.View,
                         private val storage: SpellStorage
): SpellListContract.Presenter {

    private var allSpellsForCurrentClass: List<Spell> = storage.getSpellListForClass(ActiveSpellCastingClass.activeClass.toString())

    override fun getSpellListForClass(charClass: String): List<Spell> {

        val spellList = if (charClass == SpellCastingClass.ALL_SPELLS.toString()) {
            storage.getAllSpells()
        } else {
            storage.getSpellListForClass(charClass)
        }

        allSpellsForCurrentClass = formatSpellList(spellList)
        return allSpellsForCurrentClass
    }

    override fun getColorResourceID(): Int = view.provideContext().resources.getColor(ActiveSpellCastingClass.activeClass.getItemListColorAccentResId())

    override fun searchSpellsByName(spellName: String): List<Spell> {
        return allSpellsForCurrentClass.filter {
            it.name.toLowerCase().contains(spellName.toLowerCase())
        }
    }

    // Spells should be grouped by level in order followed by alphabetical order
    private fun formatSpellList(spells: List<Spell>): List<Spell> {
        val spellsGroupedByLevel = HashMap<Int, MutableList<Spell>>()

        for (i in 0..9) {
            spellsGroupedByLevel.put(i, mutableListOf())
        }

        for (spell in spells) {
            val spellLevel = parseSpellLevel(spell.spellLevel)
            val spellLevelList = spellsGroupedByLevel[spellLevel]
            spellLevelList?.add(spell)
        }

        val sortedSpells = mutableListOf<Spell>()

        for (spellList in spellsGroupedByLevel.values) {
            spellList.sortBy { it.name }
            sortedSpells.addAll(spellList)
        }

        return sortedSpells
    }

    private fun parseSpellLevel(spellLevel: String): Int {
        val firstChar = spellLevel[0]

        return if (firstChar.toString().toLowerCase() == "c") 0 else firstChar.toString().toInt()
    }

}