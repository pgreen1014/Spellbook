package com.philipgreen.spellbook.ui

import com.philipgreen.spellbook.model.ActiveSpellCastingClass
import com.philipgreen.spellbook.model.Spell
import com.philipgreen.spellbook.model.SpellCastingClass
import com.philipgreen.spellbook.storage.PreferenceStorage
import com.philipgreen.spellbook.storage.SpellStorage

/**
 * Created by pgreen on 12/2/17.
 */
class MainActivityPresenter(private val view: MainActivityContract.View, private val storage: SpellStorage, private val preferenceStorage: PreferenceStorage): MainActivityContract.Presenter {

    override fun loadSpellList(spellList: List<Spell>) {
        storage.saveSpells(spellList)
    }

    override fun changeActiveSpellCasterClass(spellCastingClass: SpellCastingClass) {
        ActiveSpellCastingClass.activeClass = spellCastingClass
        view.updateSpellCasterClassUI(spellCastingClass)
        preferenceStorage.storeLastUsedActiveClass(spellCastingClass.toString())
    }

    override fun loadActiveSpellCasterClass() {
        val activeClass = preferenceStorage.getLastUsedActiveClass()
        val constructedClass = constructSpellCastingClassFromString(activeClass)
        ActiveSpellCastingClass.activeClass = constructedClass
    }

    // Move this to a constructor for SpellCastingClass enum
    private fun constructSpellCastingClassFromString(classString: String): SpellCastingClass {

        return when(classString) {
            SpellCastingClass.ALL_SPELLS.toString() -> SpellCastingClass.ALL_SPELLS
            SpellCastingClass.BARD.toString()       -> SpellCastingClass.BARD
            SpellCastingClass.CLERIC.toString()     -> SpellCastingClass.CLERIC
            SpellCastingClass.DRUID.toString()      -> SpellCastingClass.DRUID
            SpellCastingClass.PALADIN.toString()    -> SpellCastingClass.PALADIN
            SpellCastingClass.RANGER.toString()     -> SpellCastingClass.RANGER
            SpellCastingClass.SORCERER.toString()   -> SpellCastingClass.SORCERER
            SpellCastingClass.WARLOCK.toString()    -> SpellCastingClass.WARLOCK
            SpellCastingClass.WIZARD.toString()     -> SpellCastingClass.WIZARD
            else                                    -> SpellCastingClass.ALL_SPELLS
        }

    }

}