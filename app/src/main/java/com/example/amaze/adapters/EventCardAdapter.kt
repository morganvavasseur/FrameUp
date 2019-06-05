/*
 * Developed by Yann Malanda on 5/25/19 4:24 PM.
 * Last modified 5/25/19 4:10 PM
 * Copyright (c) 2019.
 *
 */

package com.example.amaze.adapters

import android.support.v4.content.ContextCompat.getDrawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.amaze.AmazeApp
import com.example.amaze.R
import com.example.amaze.components.AmazeEventStateButton
import com.example.amaze.models.Organizer
import com.example.amaze.network.EventResult
import com.example.amaze.network.RetrofitClient
import com.example.amaze.network.SendableEvent
import com.example.amaze.utils.EventSupportFunctions
import com.example.amaze.utils.ExtraStrings
import com.example.amaze.utils.SecureStorageServices
import kotlinx.android.synthetic.main.amaze_state_button.view.*
import kotlinx.android.synthetic.main.component_event_card.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat


class EventCardAdapter(var events : ArrayList<EventResult>, val onEventCardListener: OnEventCardListener, val isGuestEventCard : Boolean = true) : RecyclerView.Adapter<EventCardAdapter.EventCardViewHolder>(), AmazeEventStateButton.OnEventStateListener {


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
        var event = events[position]
        var host: Organizer? = EventSupportFunctions.getHost(event)

        // Affecte les valeurs à l'event récupéré
        holder.view.event_card_title.text = event.title
        holder.view.event_card_date.text = getEventCardDate(event.date)
        holder.view.event_card_description.text = event.description
        holder.view.event_card_hour.text = getEventCardHour(event.date)
        holder.view.event_card_location.text = event.location.formattedAddress

        if (host != null)
            holder.view.event_card_host_name.text = host.fullName()
        else
            holder.view.event_card_host_name.text = "No host"

        // On donne l'event en question aux boutons
        holder.view.eventCardCommingButton.event = SendableEvent(event)
        holder.view.eventCardMaybeButton.event = SendableEvent(event)
        holder.view.eventCardNotCommingButton.event = SendableEvent(event)

        if (event.guestsComming.any { g -> g.id == SecureStorageServices.authUser?.id }) {
            holder.view.eventCardCommingButton.stateButton.background = getDrawable(AmazeApp.sharedInstance, R.drawable.comming_button)
            holder.view.eventCardMaybeButton.stateButton.background = getDrawable(AmazeApp.sharedInstance, R.drawable.maybe_button)
            holder.view.eventCardNotCommingButton.stateButton.background = getDrawable(AmazeApp.sharedInstance, R.drawable.maybe_button)
        }
        if (event.guestsMaybe.any { g -> g.id == SecureStorageServices.authUser?.id }) {
            holder.view.eventCardMaybeButton.stateButton.background = getDrawable(AmazeApp.sharedInstance, R.drawable.comming_button)
            holder.view.eventCardCommingButton.stateButton.background = getDrawable(AmazeApp.sharedInstance, R.drawable.maybe_button)
            holder.view.eventCardNotCommingButton.stateButton.background = getDrawable(AmazeApp.sharedInstance, R.drawable.maybe_button)
        }
        if (event.guestsNotComming.any { g -> g.id == SecureStorageServices.authUser?.id }) {
            holder.view.eventCardNotCommingButton.stateButton.background = getDrawable(AmazeApp.sharedInstance, R.drawable.comming_button)
            holder.view.eventCardCommingButton.stateButton.background = getDrawable(AmazeApp.sharedInstance, R.drawable.maybe_button)
            holder.view.eventCardMaybeButton.stateButton.background = getDrawable(AmazeApp.sharedInstance, R.drawable.maybe_button)
        }

        holder.view.eventCardCommingButton.setOnEventStateListen(this)
        holder.view.eventCardMaybeButton.setOnEventStateListen(this)
        holder.view.eventCardNotCommingButton.setOnEventStateListen(this)

        holder.view.eventCardCommingButton.stateButton.setOnClickListener({
            updateStateButtons(
                holder.view.eventCardCommingButton.stateButton,
                holder.view.eventCardMaybeButton.stateButton,
                holder.view.eventCardNotCommingButton.stateButton,
                event,
                0
            )
        })
        holder.view.eventCardMaybeButton.stateButton.setOnClickListener({
            updateStateButtons(
                holder.view.eventCardCommingButton.stateButton,
                holder.view.eventCardMaybeButton.stateButton,
                holder.view.eventCardNotCommingButton.stateButton,
                event,
                1
            )
        })
        holder.view.eventCardNotCommingButton.stateButton.setOnClickListener({
            updateStateButtons(
                holder.view.eventCardCommingButton.stateButton,
                holder.view.eventCardMaybeButton.stateButton,
                holder.view.eventCardNotCommingButton.stateButton,
                event,
                2
            )
        })

        // Hide states buttons if it's not a guest event card
        if (!isGuestEventCard) {
            holder.view.eventCardCommingButton.visibility = View.GONE
            holder.view.eventCardMaybeButton.visibility = View.GONE
            holder.view.eventCardNotCommingButton.visibility = View.GONE
        }

        holder.view.setOnClickListener({ onEventCardListener.onEventCardClick(event) })

    }

    fun updateStateButtons(
        commingBtn: Button,
        maybeButton: Button,
        notCommingButton: Button,
        eventChanged: EventResult,
        buttonId: Int
    ) {
        var sendableEventResult = SendableEvent(eventChanged)
        exitAuthUserFromEachStateList(sendableEventResult)
        when(buttonId){
            0 -> sendableEventResult.guestsComming.add(SecureStorageServices.authUser!!.id)
            1 -> sendableEventResult.guestsMaybe.add(SecureStorageServices.authUser!!.id)
            2 -> sendableEventResult.guestsNotComming.add(SecureStorageServices.authUser!!.id)
        }

        val updateEventRequest = RetrofitClient.eventService.updateEvent(eventChanged.id, sendableEventResult)

        updateEventRequest.enqueue(object : Callback<EventResult> {
            override fun onFailure(call: Call<EventResult>, t: Throwable) {
                Toast.makeText(AmazeApp.sharedInstance, "Error when updating event", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<EventResult>, response: Response<EventResult>) {

                var event = response.body()
                if(event is EventResult) {


                    commingBtn.background = getDrawable(AmazeApp.sharedInstance, R.drawable.maybe_button)
                    maybeButton.background = getDrawable(AmazeApp.sharedInstance, R.drawable.maybe_button)
                    notCommingButton.background = getDrawable(AmazeApp.sharedInstance, R.drawable.maybe_button)

                    if (event.guestsComming.any { g -> g.id == SecureStorageServices.authUser?.id }) {
                        commingBtn.stateButton.background = getDrawable(AmazeApp.sharedInstance, R.drawable.comming_button)
                        maybeButton.stateButton.background = getDrawable(AmazeApp.sharedInstance, R.drawable.maybe_button)
                        notCommingButton.stateButton.background = getDrawable(AmazeApp.sharedInstance, R.drawable.maybe_button)
                    }
                    if (event.guestsMaybe.any { g -> g.id == SecureStorageServices.authUser?.id }) {
                        maybeButton.stateButton.background = getDrawable(AmazeApp.sharedInstance, R.drawable.comming_button)
                        notCommingButton.stateButton.background = getDrawable(AmazeApp.sharedInstance, R.drawable.maybe_button)
                        commingBtn.stateButton.background = getDrawable(AmazeApp.sharedInstance, R.drawable.maybe_button)
                    }
                    if (event.guestsNotComming.any { g -> g.id == SecureStorageServices.authUser?.id }) {
                        notCommingButton.stateButton.background = getDrawable(AmazeApp.sharedInstance, R.drawable.comming_button)
                        commingBtn.stateButton.background = getDrawable(AmazeApp.sharedInstance, R.drawable.maybe_button)
                        maybeButton.stateButton.background = getDrawable(AmazeApp.sharedInstance, R.drawable.maybe_button)
                    }
                }

            }

        })


    }

    fun exitAuthUserFromEachStateList(event: SendableEvent){
        event.guestsComming = event.guestsComming.filter { id -> id != SecureStorageServices.authUser?.id } as ArrayList<String>
        event.guestsMaybe = event.guestsMaybe.filter { id -> id != SecureStorageServices.authUser?.id } as ArrayList<String>
        event.guestsNotComming = event.guestsNotComming.filter { id -> id != SecureStorageServices.authUser?.id } as ArrayList<String>
    }

    // Formatte la date pour une date adaptée a l'event card
    fun getEventCardDate(originalDate: String): String {
        val date = EventSupportFunctions.convertInstantStringDateToLocaleDateTime(originalDate)

        // Format Date
        val formatter = SimpleDateFormat(ExtraStrings.EVENT_CARD_DATE_FORMAT)
        val formatedDate = formatter.format(date)
        return formatedDate
    }

    // Formatte la date pour une date adaptée a l'event card
    fun getEventCardHour(originalDate: String): String {
        val date = EventSupportFunctions.convertInstantStringDateToLocaleDateTime(originalDate)

        // Format Date
        val formatter = SimpleDateFormat(ExtraStrings.EVENT_CARD_HOUR_FORMAT)
        val formatedDate = formatter.format(date)
        return formatedDate
    }

    override fun onEventStateChanged(eventChanged: EventResult, stateButton: AmazeEventStateButton) {
        // On met à jour l'event
        var index = this.events.indexOfFirst { e -> e.id == eventChanged.id }
        this.events[index] = eventChanged
    }


    class EventCardViewHolder(val view: View) : RecyclerView.ViewHolder(view) {


    }

    interface OnEventCardListener {
        fun onEventCardClick(event: EventResult)
    }

}