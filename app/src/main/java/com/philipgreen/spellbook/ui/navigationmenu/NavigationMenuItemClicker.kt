package com.philipgreen.spellbook.ui.navigationmenu

import android.app.Activity
import android.content.Intent
import android.view.MenuItem
import com.philipgreen.spellbook.R
import com.philipgreen.spellbook.ui.MainActivity
import com.philipgreen.spellbook.ui.spellbooks.SpellBookActivity

fun getIntentForMenuItem(activity: Activity, item: MenuItem): Intent? {

    return when (item.itemId) {
        R.id.main_menu_navigation_item_spellbooks   -> buildIntent(activity, SpellBookActivity::class.java)
        R.id.main_menu_navigation_item_spells       -> buildIntent(activity, MainActivity::class.java)
        else                                        -> throw IllegalArgumentException("Invalid MenuItem: $item")
    }

}

private fun <T> buildIntent(activity: Activity, activityClass: Class<T>): Intent? {
    return if (activity::class.java == activityClass) {
        null
    } else {
        Intent(activity, activityClass)
    }
}