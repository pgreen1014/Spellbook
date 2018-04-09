package com.philipgreen.spellbook.utils

import com.philipgreen.spellbook.model.Spell
import org.json.JSONArray
import org.json.JSONObject

/**
 * Created by pgreen on 12/2/17.
 */

fun convertJSONStringToSpells(jsonSpellsArray: JSONArray): List<Spell> {
    return List(jsonSpellsArray.length()) {
        constructSpell(jsonSpellsArray.getJSONObject(it))
    }
}

private fun constructSpell(jsonSpell: JSONObject): Spell {
    val name = jsonSpell.getString("name")
    val spellLevel = jsonSpell.getString("spellLevel")
    val magicSchool = jsonSpell.getString("school")
    val ritual = jsonSpell.getBoolean("ritual")
    val concentration = jsonSpell.getBoolean("concentration")
    val castingTime = jsonSpell.getString("castingTime")
    val range = jsonSpell.getString("range")
    val components = deconstructJSONArrayToStringsList(jsonSpell.getJSONArray("components"))
    val duration = jsonSpell.getString("duration")
    val description = jsonSpell.getString("description")
    val higherLevelDescription = jsonSpell.getString("higherLevelDescription")
    val clericDomain = jsonSpell.getString("clericDomain")
    val availableClasses = deconstructJSONArrayToStringsList(jsonSpell.getJSONArray("availableClasses"))

    return Spell(name, spellLevel, magicSchool, ritual, concentration,
            castingTime, range, components, duration, description,
            higherLevelDescription, clericDomain, availableClasses)
}

private fun deconstructJSONArrayToStringsList(jsonArray: JSONArray): List<String> {

    return List<String>(jsonArray.length()) {
        jsonArray.getString(it).toLowerCase()
    }

}