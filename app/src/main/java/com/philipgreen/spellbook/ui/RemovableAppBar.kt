package com.philipgreen.spellbook.ui

/**
 * Created by pgreen on 12/6/17.
 */
interface RemovableAppBar {
    fun removeSearchBar()
    fun showSearchBar()
    fun collapseAppBar()
    fun expandAppBar()
    fun pauseAnimatingAppBar()
}