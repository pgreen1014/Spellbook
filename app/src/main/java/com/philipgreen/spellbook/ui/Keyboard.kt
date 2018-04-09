package com.philipgreen.spellbook.ui

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * Created by pgreen on 12/8/17.
 */
fun View.hideKeyboard() {
    val imm = this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}