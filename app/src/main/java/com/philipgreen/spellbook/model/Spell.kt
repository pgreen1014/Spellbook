package com.philipgreen.spellbook.model

/**
 * Created by pgreen on 12/2/17.
 */
data class Spell(
        val name: String,
        val spellLevel: String,
        val magicSchool: String,
        val ritual: Boolean,
        val concentration: Boolean,
        val castingTime: String,
        val range: String,
        val components: List<String>,
        val duration: String,
        val description: String,
        val higherLevelDescription: String,
        val clericDomain: String,
        val availableClasses: List<String>
)