package com.philipgreen.spellbook.storage

import android.content.Context
import com.philipgreen.spellbook.model.SpellCastingClass

/**
 * Created by pgreen on 2/19/18.
 */

class SharedPreferenceManager(private val context: Context): PreferenceStorage {

    companion object {
        private val FILE_NAME = "GeneralPreferences"
        private val LAST_ACTIVE_CLASS = "com.philipgreen.spellbook.storage.last_active_class"
    }

    override fun storeLastUsedActiveClass(activeClass: String) {
        val prefs = context.getSharedPreferences(FILE_NAME, 0)
        val editor = prefs.edit()
        editor.putString(LAST_ACTIVE_CLASS, activeClass)
        editor.apply()
    }

    override fun getLastUsedActiveClass(): String {
        val prefs = context.getSharedPreferences(FILE_NAME, 0)
        return prefs.getString(LAST_ACTIVE_CLASS, SpellCastingClass.ALL_SPELLS.toString())
    }

}