package com.philipgreen.spellbook.storage

/**
 * Created by pgreen on 2/19/18.
 */
interface PreferenceStorage {
    fun storeLastUsedActiveClass(activeClass: String)
    fun getLastUsedActiveClass(): String
}