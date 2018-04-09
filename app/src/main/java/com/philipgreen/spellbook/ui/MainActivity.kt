package com.philipgreen.spellbook.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.Toolbar
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.philipgreen.spellbook.R
import com.philipgreen.spellbook.event.LoadSpellDetailFragmentEvent
import com.philipgreen.spellbook.event.SpellCastingClassChangedEvent
import com.philipgreen.spellbook.event.SpellSearchEvent
import com.philipgreen.spellbook.model.ActiveSpellCastingClass
import com.philipgreen.spellbook.model.SpellCastingClass
import com.philipgreen.spellbook.storage.SharedPreferenceManager
import com.philipgreen.spellbook.storage.SpellList
import com.philipgreen.spellbook.ui.navigationmenu.MenuNavigationColor
import com.philipgreen.spellbook.ui.navigationmenu.getIntentForMenuItem
import com.philipgreen.spellbook.ui.selectclass.ClassSelectionDialogFragment
import com.philipgreen.spellbook.ui.spelldetail.SpellDetailFragment
import com.philipgreen.spellbook.ui.spelllist.SpellListFragment
import com.philipgreen.spellbook.utils.convertJSONStringToSpells
import com.philipgreen.spellbook.utils.loadJSONFromAsset
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.json.JSONArray
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainActivityContract.View, RemovableAppBar {

    private val presenter: MainActivityContract.Presenter = MainActivityPresenter(this, SpellList, SharedPreferenceManager(this))

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.loadActiveSpellCasterClass()

        val jsonString = loadJSONFromAsset(this, "spells.json")
        val spells = JSONArray(jsonString)
        presenter.loadSpellList(convertJSONStringToSpells(spells))

        val toolbar = findViewById<Toolbar>(R.id.main_toolbar)
        setSupportActionBar(toolbar)

        updateSpellCasterClassUI(ActiveSpellCastingClass.activeClass)

        var fragment = supportFragmentManager.findFragmentById(R.id.spell_list_container)

        if (fragment == null) {
            fragment = SpellListFragment()
            supportFragmentManager.beginTransaction()
                    .add(R.id.spell_list_container, fragment)
                    .commit()
        }

        class_spell_list_button.setOnClickListener {
            val ft = fragmentManager.beginTransaction()
            val prevFragment = fragmentManager.findFragmentByTag("dialog")
            if (prevFragment != null) {
                ft.remove(prevFragment)
            }
            ft.addToBackStack(null)

            val classSelectionDialog = ClassSelectionDialogFragment()
            classSelectionDialog.show(ft, "dialog")
        }

        main_search_bar_close_button.setOnClickListener {
            main_search_bar_editText.text.clear()
        }


        main_search_bar_editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                EventBus.getDefault().post(SpellSearchEvent(s.toString()))
            }
        })

        val menuDrawer: DrawerLayout = findViewById(R.id.main_drawer_layout)
        val navigationMenu: NavigationView = findViewById(R.id.main_menu_navigationView)
        val navigationTextcolors = MenuNavigationColor()
        navigationMenu.itemTextColor = navigationTextcolors.getNavigationMenuTextColors()
        navigationMenu.setNavigationItemSelectedListener {
            it.isChecked = true
            menuDrawer.closeDrawers()
            val intent = getIntentForMenuItem(this, it)
            if (intent != null) {
                startActivity(intent)
            }

            true
        }

    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    override fun updateSpellCasterClassUI(spellCastingClass: SpellCastingClass) {
        selected_class_title.setText(spellCastingClass.getUIStringID())
        window.statusBarColor = resources.getColor(spellCastingClass.getPrimaryDarkColorResId())
        main_appBarLayout.setBackgroundColor(resources.getColor(spellCastingClass.getPrimaryColorResId()))
        main_toolbar.setBackgroundColor(resources.getColor(spellCastingClass.getPrimaryColorResId()))
        main_search_bar.setBackgroundColor(resources.getColor(spellCastingClass.getPrimaryColorResId()))
        main_menu_navigationView.setBackgroundColor(resources.getColor(spellCastingClass.getPrimaryColorResId()))
    }

    override fun removeSearchBar() {
        main_search_bar?.visibility = View.GONE
    }

    override fun showSearchBar() {
        main_search_bar?.visibility = View.VISIBLE
    }

    override fun collapseAppBar() {
//        main_search_bar_editText.height = 0
//        main_search_bar_close_button.visibility = View.GONE
//        main_search_bar_icon.visibility = View.GONE
//        TransitionManager.beginDelayedTransition(main_appBarLayout)
    }

    override fun expandAppBar() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun pauseAnimatingAppBar() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @Subscribe
    fun onLoadSpellDetailEvent(event: LoadSpellDetailFragmentEvent) {
        val spellDetailFragment = SpellDetailFragment.newInstance(event.spell)

        supportFragmentManager.beginTransaction()
                .replace(R.id.spell_list_container, spellDetailFragment)
                .addToBackStack(null)
                .commit()
    }

    @Subscribe
    fun onSpellCastingClassChangedEvent(event: SpellCastingClassChangedEvent) {
        presenter.changeActiveSpellCasterClass(event.spellCastingClass)

        val fragment = supportFragmentManager.findFragmentById(R.id.spell_detail_container)

        if (fragment == null || fragment is SpellDetailFragment) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.spell_list_container, SpellListFragment())
                    .commit()
        }
    }

}
