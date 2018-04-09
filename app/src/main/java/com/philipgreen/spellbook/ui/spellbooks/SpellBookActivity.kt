package com.philipgreen.spellbook.ui.spellbooks

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.philipgreen.spellbook.R
import com.philipgreen.spellbook.ui.spellbooks.spellbooklist.SpellBookListFragment

class SpellBookActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spell_book)

        setSupportActionBar(findViewById(R.id.spellbook_toolbar))


        var fragment = supportFragmentManager.findFragmentById(R.id.spell_book_list_container)
        if (fragment == null) {
            fragment = SpellBookListFragment()
            supportFragmentManager.beginTransaction()
                    .add(R.id.spell_book_list_container, fragment)
                    .commit()
        }

    }
}
