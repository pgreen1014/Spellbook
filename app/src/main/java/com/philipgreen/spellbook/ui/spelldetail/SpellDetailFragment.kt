package com.philipgreen.spellbook.ui.spelldetail

import android.content.Context
import android.content.res.ColorStateList
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.philipgreen.spellbook.R
import com.philipgreen.spellbook.event.SpellCastingClassChangedEvent
import com.philipgreen.spellbook.event.SpellSearchEvent
import com.philipgreen.spellbook.model.ActiveSpellCastingClass
import com.philipgreen.spellbook.model.Spell
import com.philipgreen.spellbook.model.SpellCastingClass
import com.philipgreen.spellbook.ui.RemovableAppBar
import com.philipgreen.spellbook.ui.hideKeyboard
import java.util.ArrayList
import kotlinx.android.synthetic.main.fragment_spell_detail.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import java.lang.ClassCastException

/**
 * Created by pgreen on 12/3/17.
 */
class SpellDetailFragment: Fragment(), SpellDetailContract.View {
    var appBarCallback: RemovableAppBar? = null

    private val presenter: SpellDetailContract.Presenter = SpellDetailPresenter(this)

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        try {
            appBarCallback = context as RemovableAppBar
        } catch (e: ClassCastException) {
            throw ClassCastException(context.toString() + " must implement RemovableAppBar")
        }
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    companion object {
        fun newInstance(spell: Spell): SpellDetailFragment {
            val fragment = SpellDetailFragment()

            val args = Bundle()
            args.putString(SPELL_NAME_KEY, spell.name)
            args.putString(SPELL_LEVEL_KEY, spell.spellLevel)
            args.putString(MAGIC_SCHOOL_KEY, spell.magicSchool)
            args.putBoolean(RITUAL_KEY, spell.ritual)
            args.putBoolean(CONCENTRATION_KEY, spell.concentration)
            args.putString(CASTING_TIME_KEY, spell.castingTime)
            args.putString(RANGE_KEY, spell.range)
            args.putStringArrayList(COMPONENTS_LIST_KEY, spell.components as ArrayList<String>)
            args.putString(DURATION_KEY, spell.duration)
            args.putString(DESCRIPTION_KEY, spell.description)
            args.putString(HIGHER_LEVEL_DESCRIPTION, spell.higherLevelDescription)
            args.putString(CLERIC_DOMAIN, spell.clericDomain)

            fragment.arguments = args
            return fragment
        }
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        appBarCallback?.removeSearchBar()
        return inflater.inflate(R.layout.fragment_spell_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.hideKeyboard()
        updateSpellCasterClassUI(ActiveSpellCastingClass.activeClass)
        spell_detail_spellDescription.movementMethod = ScrollingMovementMethod()
        if (arguments != null) {
            presenter.parseArgumentsToViews(arguments!!)
        }
    }

    override fun setSpellName(name: String) {
        spell_detail_name.text = name
    }

    override fun setSpellSchool(school: String) {
        spell_detail_school.text = school
    }

    override fun setSpellLevel(level: String) {
        spell_detail_spellLevel.text = level
    }

    override fun setRitual(isRitual: Boolean) {
        if (isRitual) {
            spell_detail_ritual.visibility = View.VISIBLE
        } else {
            spell_detail_ritual.visibility = View.GONE
        }
    }

    override fun setConcentration(isConcentration: Boolean) {
        if (isConcentration) {
            spell_detail_concentration_symbol.visibility = View.VISIBLE
        } else {
            spell_detail_concentration_symbol.visibility = View.GONE
        }
    }

    override fun setCastingTime(castingTime: String) {
        spell_detail_castingTime.text = castingTime
    }

    override fun setSpellRange(range: String) {
        spell_detail_range.text = range
    }

    override fun setComponents(components: String) {
        spell_detail_components.text = components
    }

    override fun setDuration(duration: String) {
        spell_detail_duration.text = duration
    }

    override fun setDescription(description: String) {
        spell_detail_spellDescription.text = description
    }

    override fun setHigherLevelDescription(description: String) {
        spell_detail_higherLevelDescription.text = description
    }

    override fun setClericDomain(domain: String) {
        spell_detail_clericDomain.text = domain
    }

    override fun hideClericDomain() {
        spell_detail_clericDomain.visibility = View.INVISIBLE
    }

    override fun hideHigherLevelDescription() {
        spell_detail_higherLevelDescription.visibility = View.GONE
        spell_detail_higherLevelDescription_title.visibility = View.GONE
    }

    override fun makeDescriptionBottomField() {
        spell_detail_spellDescription.background = resources.getDrawable(R.drawable.background_spell_description)
    }

    override fun updateSpellCasterClassUI(spellCastingClass: SpellCastingClass) {
        val primaryColor = resources.getColor(spellCastingClass.getPrimaryColorResId())
        spell_detail_container.setBackgroundColor(primaryColor)
        spell_detail_castingTime_title.setTextColor(primaryColor)
        spell_detail_range_title.setTextColor(primaryColor)
        spell_detail_components_title.setTextColor(primaryColor)
        spell_detail_duration_title.setTextColor(primaryColor)
        spell_detail_concentration_symbol.backgroundTintList = ColorStateList.valueOf(primaryColor)
    }

    @Subscribe
    fun onSpellCastingClassChangeEvent(event: SpellCastingClassChangedEvent) {
        updateSpellCasterClassUI(event.spellCastingClass)
    }

}