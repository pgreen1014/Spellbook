package com.philipgreen.spellbook.ui.spelldetail

import android.os.Bundle

/**
 * Created by pgreen on 12/3/17.
 */
class SpellDetailPresenter(val view: SpellDetailContract.View): SpellDetailContract.Presenter {

    override fun parseArgumentsToViews(arguments: Bundle) {
        view.setSpellName(arguments.getString(SPELL_NAME_KEY).toUpperCase())
        view.setSpellSchool(arguments.getString(MAGIC_SCHOOL_KEY).toLowerCase())
        view.setSpellLevel(arguments.getString(SPELL_LEVEL_KEY))
        view.setRitual(arguments.getBoolean(RITUAL_KEY))
        view.setConcentration(arguments.getBoolean(CONCENTRATION_KEY))
        view.setCastingTime(arguments.getString(CASTING_TIME_KEY))
        view.setSpellRange(arguments.getString(RANGE_KEY))
        view.setComponents(parseComponents(arguments.getStringArrayList(COMPONENTS_LIST_KEY)))
        view.setDuration(arguments.getString(DURATION_KEY))
        view.setDescription(arguments.getString(DESCRIPTION_KEY))
        determineHigherLevelDescriptionVisibility(arguments.getString(HIGHER_LEVEL_DESCRIPTION))
        determineClericDomainVisibility(arguments.getString(CLERIC_DOMAIN))
    }

    private fun parseComponents(components: List<String>): String {
        var componentString: String = ""

        for (component in components) {
            componentString += if(componentString.isEmpty()) component.toUpperCase() else ", ${component.toUpperCase()}"
        }

        return componentString
    }

    private fun determineHigherLevelDescriptionVisibility(description: String) {
        if (description.isEmpty()) {
            view.hideHigherLevelDescription()
            view.makeDescriptionBottomField()
        } else {
            view.setHigherLevelDescription(description)
        }
    }

    private fun determineClericDomainVisibility(domain: String) {
        if (domain.isEmpty()) {
            view.hideClericDomain()
        } else {
            view.setClericDomain(domain)
        }
    }

}