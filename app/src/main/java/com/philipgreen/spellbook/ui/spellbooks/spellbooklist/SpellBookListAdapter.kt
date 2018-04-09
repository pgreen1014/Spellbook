package com.philipgreen.spellbook.ui.spellbooks.spellbooklist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.philipgreen.spellbook.R

class SpellBookListAdapter: RecyclerView.Adapter<SpellBookListAdapter.ItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_spellbook, parent, false)
        return ItemHolder(view)
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bindViewHolder()
    }


    class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val characterName = itemView.findViewById<TextView>(R.id.spellbook_character_name_TextView)
        private val characterRace = itemView.findViewById<TextView>(R.id.spellbook_character_race_TextView)
        private val characterClass = itemView.findViewById<TextView>(R.id.spellbook_character_class_TextView)
        private val characterLevel = itemView.findViewById<TextView>(R.id.spellbook_character_level_TextView)

        fun bindViewHolder() {
            characterName.text = "Vrondiss Frostwind"
            characterRace.text = "Dragonborn"
            characterClass.text = "Sorcerer"
            characterLevel.text = "1"
        }
    }
}