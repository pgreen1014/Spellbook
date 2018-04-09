package com.philipgreen.spellbook.ui.navigationmenu

import android.content.res.ColorStateList
import android.graphics.Color

class MenuNavigationColor {

    val states = arrayOf(
            intArrayOf(-android.R.attr.state_pressed),
            intArrayOf(android.R.attr.state_enabled),
            intArrayOf(-android.R.attr.state_checked),
            intArrayOf(android.R.attr.state_pressed)
    )

    val colors = intArrayOf(
            Color.WHITE,
            Color.WHITE,
            Color.WHITE,
            Color.WHITE
    )

    fun getNavigationMenuTextColors(): ColorStateList {
        return ColorStateList(states, colors)
    }

}