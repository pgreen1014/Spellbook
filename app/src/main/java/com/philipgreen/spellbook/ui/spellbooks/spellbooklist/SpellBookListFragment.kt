package com.philipgreen.spellbook.ui.spellbooks.spellbooklist

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.philipgreen.spellbook.R

class SpellBookListFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_spellbook_list, container, false)

        val spellbookList = view.findViewById<RecyclerView>(R.id.spellbook_list_recyclerView)
        spellbookList.layoutManager = LinearLayoutManager(activity)
        spellbookList.adapter = SpellBookListAdapter()

        return view
    }

}