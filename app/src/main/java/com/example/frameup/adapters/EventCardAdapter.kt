package com.example.frameup.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.amaze.R
import com.example.frameup.models.Event
import kotlinx.android.synthetic.main.component_event_card.view.*

class EventCardAdapter(val events : List<Event>) : RecyclerView.Adapter<EventCardAdapter.EventCardViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): EventCardViewHolder {
        return EventCardViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.component_event_card, parent, false)
        )
    }

    // Return the size of the events list
    override fun getItemCount() = events.size

    override fun onBindViewHolder(holder: EventCardViewHolder, position: Int) {
        // Récupère l'event
        val event = events[position]

        // Affecte les valeurs à l'event récupéré
        holder.view.event_card_title.text = event.title
        holder.view.event_card_date.text = event.date.toString()
        holder.view.event_card_host_name.text = "LE NOM DE L'HOTE"
        holder.view.event_card_description.text = event.description
        holder.view.event_card_hour.text = event.date.time.toString()
        //holder.view.event_card_location.text = event.location

    }


    class EventCardViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}