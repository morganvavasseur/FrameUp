/*
 * Developed by Yann Malanda on 5/25/19 4:24 PM.
 * Last modified 5/25/19 4:10 PM
 * Copyright (c) 2019.
 *
 */

package com.example.amaze.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.amaze.models.Organizer
import com.example.amaze.network.EventResult
import com.example.amaze.utils.EventSupportFunctions
import com.example.amaze.utils.ExtraStrings
import kotlinx.android.synthetic.main.component_event_card.view.*
import java.text.SimpleDateFormat


class EventCardAdapter(val events : ArrayList<EventResult>, val onEventCardListener: OnEventCardListener, val isGuestEventCard : Boolean = true) : RecyclerView.Adapter<EventCardAdapter.EventCardViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): EventCardViewHolder {
        return EventCardViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(com.example.amaze.R.layout.component_event_card, parent, false)
        )
    }

    // Return the size of the events list
    override fun getItemCount() = events.size

    override fun onBindViewHolder(holder: EventCardViewHolder, position: Int) {
        // Récupère l'event
        val event = events[position]
        var host : Organizer? = EventSupportFunctions.getHost(event)

        // Affecte les valeurs à l'event récupéré
        holder.view.event_card_title.text = event.title
        holder.view.event_card_date.text = getEventCardDate(event.date)
        holder.view.event_card_description.text = event.description
        holder.view.event_card_hour.text = getEventCardHour(event.date)

        if (host != null)
            holder.view.event_card_host_name.text = host.fullName()
        else
            holder.view.event_card_host_name.text = "No host"

        // Hide states buttons if it's not a guest event card
        if (!isGuestEventCard){
            holder.view.eventCardCommingButton.visibility = View.GONE
            holder.view.eventCardMaybeButton.visibility = View.GONE
            holder.view.eventCardNotCommingButton.visibility = View.GONE
        }

        holder.view.setOnClickListener( {onEventCardListener.onEventCardClick(event)})

        //holder.view.event_card_location.text = event.location

    }

    // Formatte la date pour une date adaptée a l'event card
    fun getEventCardDate(originalDate: String) : String {
        val date = EventSupportFunctions.convertInstantStringDateToLocaleDateTime(originalDate)

        // Format Date
        val formatter =  SimpleDateFormat(ExtraStrings.EVENT_CARD_DATE_FORMAT)
        val formatedDate = formatter.format(date)
        return formatedDate
    }

    // Formatte la date pour une date adaptée a l'event card
    fun getEventCardHour(originalDate: String) : String {
        val date = EventSupportFunctions.convertInstantStringDateToLocaleDateTime(originalDate)

        // Format Date
        val formatter =  SimpleDateFormat(ExtraStrings.EVENT_CARD_HOUR_FORMAT)
        val formatedDate = formatter.format(date)
        return formatedDate
    }


    class EventCardViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    interface OnEventCardListener{
        fun onEventCardClick(event: EventResult)
    }
}