package com.philipgreen.spellbook.ui.spelldetail

import android.os.Bundle
import com.philipgreen.spellbook.model.Spell
import com.philipgreen.spellbook.ui.ClassThemeable

/**
 * Created by pgreen on 12/3/17.
 */
interface SpellDetailContract {

    interface View: ClassThemeable {
        fun setSpellName(name: String)
        fun setSpellSchool(school: String)
        fun setSpellLevel(level: String)
        fun setRitual(isRitual: Boolean)
        fun setConcentration(isConcentration:Boolean)
        fun setCastingTime(castingTime: String)
        fun setSpellRange(range: String)
        fun setComponents(components: String)
        fun setDuration(duration: String)
        fun setDescription(description: String)
        fun setHigherLevelDescription(description: String)
        fun setClericDomain(domain: String)
        fun hideClericDomain()
        fun hideHigherLevelDescription()
        fun makeDescriptionBottomField()
    }

    interface Presenter {
        fun parseArgumentsToViews(arguments: Bundle)
    }
}