package com.philipgreen.spellbook.utils

import android.app.Activity

/**
 * Created by pgreen on 12/2/17.
 */

fun loadJSONFromAsset(activity: Activity, filePath: String): String? {
    return activity.assets.open(filePath).bufferedReader().use {
        it.readText()
    }
}
