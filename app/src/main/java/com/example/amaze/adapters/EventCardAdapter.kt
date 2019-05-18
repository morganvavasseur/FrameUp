package com.example.amaze.adapters

import android.support.v7.widget.RecyclerView
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.amaze.models.Event
import com.example.amaze.models.User
import com.example.amaze.utils.EventSupportFunctions
import kotlinx.android.synthetic.main.component_event_card.view.*
import java.text.DateFormat
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*
import android.R
import com.example.amaze.models.Organizer
import com.example.amaze.network.EventResult
import com.example.amaze.network.UserResult
import com.example.amaze.utils.ExtraStrings
import java.text.SimpleDateFormat
import java.time.*


class EventCardAdapter(val events : List<EventResult>, val onEventCardListener: OnEventCardListener) : RecyclerView.Adapter<EventCardAdapter.EventCardViewHolder>() {
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

        holder.view.setOnClickListener( {onEventCardListener.onEventCardClick(event)})

        //holder.view.event_card_location.text = event.location

    }

    // Formatte la date pour une date adaptée a l'event card
    fun getEventCardDate(originalDate: String) : String {
        val date = EventSupportFunctions.convertInstantStringDateToLocaleDateTime(originalDate)

        // Format Date
        val formatter =  SimpleDateFormat(ExtraStrings.EVENT_CARD_DATE_FORMAT)
        val formatedDate = formatter.format(Date.from(date.atZone(ZoneId.systemDefault()).toInstant()))
        return formatedDate
    }

    // Formatte la date pour une date adaptée a l'event card
    fun getEventCardHour(originalDate: String) : String {
        val date = EventSupportFunctions.convertInstantStringDateToLocaleDateTime(originalDate)

        // Format Date
        val formatter =  SimpleDateFormat(ExtraStrings.EVENT_CARD_HOUR_FORMAT)
        val formatedDate = formatter.format(Date.from(date.atZone(ZoneId.systemDefault()).toInstant()))
        return formatedDate
    }


    class EventCardViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    interface OnEventCardListener{
        fun onEventCardClick(event: EventResult)
    }
}