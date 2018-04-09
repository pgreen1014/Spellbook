package com.philipgreen.spellbook.ui.selectclass

import android.app.DialogFragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.philipgreen.spellbook.R
import com.philipgreen.spellbook.event.SpellCastingClassChangedEvent
import com.philipgreen.spellbook.model.ActiveSpellCastingClass
import com.philipgreen.spellbook.model.SpellCastingClass
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

/**
 * Created by pgreen on 12/3/17.
 */
class ClassSelectionDialogFragment: DialogFragment() {

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onResume() {
        super.onResume()

        val window = dialog.window
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater?.inflate(R.layout.dialog_fragment_class_selection, container, false)!!

        val recyclerView = view.findViewById<RecyclerView>(R.id.class_selection_recyclerView)
        recyclerView?.layoutManager = LinearLayoutManager(activity)
        recyclerView?.adapter = ClassListAdapter()

        return view
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe
    fun onSpellCastingClassChangedEvent(event: SpellCastingClassChangedEvent) {
        dismiss()
    }




    class ClassListAdapter(): RecyclerView.Adapter<ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val layoutInflater = LayoutInflater.from(parent?.context)
            val view = layoutInflater.inflate(R.layout.item_class_selection, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder?.bindViewHolder(classes[position], position)
        }

        private val classes = SpellCastingClass.values()

        override fun getItemCount(): Int = SpellCastingClass.values().size


    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        lateinit private var classType: SpellCastingClass
        private val classNameView = itemView.findViewById<TextView>(R.id.class_selection_item_class_name)
        private val classItem = itemView.findViewById<RelativeLayout>(R.id.class_selection_item)

        fun bindViewHolder(spellCastingClass: SpellCastingClass, position: Int) {
            classType = spellCastingClass
            classNameView.setText(spellCastingClass.getUIStringID())

            if (position % 2 == 0) {
                classItem.setBackgroundResource(ActiveSpellCastingClass.activeClass.getItemListColorAccentResId())
            } else {
                classItem.setBackgroundColor(itemView.resources.getColor(R.color.list_item_off_white))
            }

            classItem.setOnClickListener {
                EventBus.getDefault().post(SpellCastingClassChangedEvent(classType))
            }
        }

    }

}