package com.philipgreen.spellbook.ui.spelllist

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.philipgreen.spellbook.R
import com.philipgreen.spellbook.event.SpellCastingClassChangedEvent
import com.philipgreen.spellbook.event.SpellSearchEvent
import com.philipgreen.spellbook.model.ActiveSpellCastingClass
import com.philipgreen.spellbook.model.SpellCastingClass
import com.philipgreen.spellbook.storage.SpellList
import com.philipgreen.spellbook.ui.RemovableAppBar
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import java.lang.ClassCastException

/**
 * Created by pgreen on 12/2/17.
 */
class SpellListFragment: Fragment(), SpellListContract.View {
    var appBarCallback: RemovableAppBar? = null

    val presenter: SpellListContract.Presenter = SpellListPresenter(this, SpellList)
    private var spellListRecyclerView: RecyclerView? = null
    private var spellListAdapter: SpellListAdapter = SpellListAdapter(presenter)
    private var previousActiveClassSinceUpdate: SpellCastingClass = ActiveSpellCastingClass.activeClass

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        try {
            appBarCallback = context as RemovableAppBar
        } catch (e: ClassCastException) {
            throw ClassCastException(context.toString() + " must implement RemovableAppBar")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_spell_list, container, false)

        spellListRecyclerView = view?.findViewById<RecyclerView>(R.id.spell_list_recyclerView)
        spellListRecyclerView?.layoutManager = LinearLayoutManager(activity)
        spellListRecyclerView?.adapter = spellListAdapter

        updateSpellListIfClassChanged()

        spellListRecyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy < 0) {
                    Log.d("SCROLL", "UP")
                } else if (dy > 0) {
                    Log.d("SCROLL", "DOWN")
                    appBarCallback?.collapseAppBar()
                }
            }
        })


        return view
    }

    override fun onResume() {
        super.onResume()
        appBarCallback?.showSearchBar()
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    override fun provideContext(): Context = context!!

    override fun updateSpellCasterClassUI(spellCastingClass: SpellCastingClass) {
        spellListAdapter.spellList = presenter.getSpellListForClass(spellCastingClass.toString())
        spellListRecyclerView?.adapter?.notifyDataSetChanged()
        previousActiveClassSinceUpdate = spellCastingClass
    }

    private fun updateSpellListIfClassChanged() {
        if (previousActiveClassSinceUpdate != ActiveSpellCastingClass.activeClass) {
            updateSpellCasterClassUI(ActiveSpellCastingClass.activeClass)
            previousActiveClassSinceUpdate = ActiveSpellCastingClass.activeClass
        }
    }

    @Subscribe
    fun onSpellCastingClassChangedEvent(event: SpellCastingClassChangedEvent) {
        updateSpellCasterClassUI(event.spellCastingClass)
    }

    @Subscribe
    fun onSpellSearchEvent(event: SpellSearchEvent) {
        spellListAdapter.spellList = presenter.searchSpellsByName(event.text)
        spellListRecyclerView?.adapter?.notifyDataSetChanged()
    }

}