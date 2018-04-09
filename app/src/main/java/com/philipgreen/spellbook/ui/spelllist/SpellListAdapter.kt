package com.philipgreen.spellbook.ui.spelllist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.philipgreen.spellbook.R
import com.philipgreen.spellbook.event.LoadSpellDetailFragmentEvent
import com.philipgreen.spellbook.model.ActiveSpellCastingClass
import com.philipgreen.spellbook.model.Spell
import com.philipgreen.spellbook.model.SpellCastingClass
import org.greenrobot.eventbus.EventBus

/**
 * Created by pgreen on 12/2/17.
 */

class SpellListAdapter(val presenter: SpellListContract.Presenter): RecyclerView.Adapter<SpellListAdapter.ItemHolder>() {

    var spellList: List<Spell> = presenter.getSpellListForClass(ActiveSpellCastingClass.activeClass.toString())


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_spell, parent, false)
        return ItemHolder(presenter, view)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bindViewHolder(spellList[position], position)
    }

    override fun getItemCount(): Int = spellList.size


    class ItemHolder(private val presenter: SpellListContract.Presenter, itemView: View): RecyclerView.ViewHolder(itemView) {
        lateinit var spell: Spell
        private val spellName = itemView.findViewById<TextView>(R.id.spell_name)
        private val spellLevel = itemView.findViewById<TextView>(R.id.spell_level)
        private val domain = itemView.findViewById<TextView>(R.id.spell_list_cleric_domain)
        private val background = itemView.findViewById<RelativeLayout>(R.id.spell_item_layout)

        fun bindViewHolder(spell: Spell, position: Int) {
            this.spell = spell
            spellName.text = spell.name
            spellLevel.text = spell.spellLevel.toLowerCase()

            setClericDomainIfApplicable()
            setItemBackgroundColor(position)

            itemView.setOnClickListener { EventBus.getDefault().post(LoadSpellDetailFragmentEvent(this.spell)) }
        }


        private fun setClericDomainIfApplicable() {
            if (spell.clericDomain.isEmpty() || ActiveSpellCastingClass.activeClass !=  SpellCastingClass.CLERIC) {
                domain.visibility = View.GONE
            } else {
                domain.visibility = View.VISIBLE
                domain.text = spell.clericDomain
            }
        }

        private fun setItemBackgroundColor(position: Int) {
            if (position % 2 == 0) {
                background.setBackgroundColor(presenter.getColorResourceID())
            } else {
                background.setBackgroundColor(itemView.resources.getColor(R.color.list_item_off_white))
            }
        }

    }
}