package com.example.amaze.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.amaze.R
import com.example.amaze.network.EventResult
import com.example.amaze.utils.EventSupportFunctions
import com.example.amaze.utils.ExtraStrings
import kotlinx.android.synthetic.main.activity_event.*
import kotlinx.android.synthetic.main.activity_event.view.*
import kotlinx.android.synthetic.main.amaze_event_description.view.*
import kotlinx.android.synthetic.main.amaze_guests_component.view.*
import kotlinx.android.synthetic.main.event_summary_card.view.*
import java.text.SimpleDateFormat
import java.time.ZoneId
import java.util.*

class EventActivity : AppCompatActivity() {

    lateinit var event: EventResult

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)

        event = intent.extras.getSerializable(ExtraStrings.EXTRA_EVENT) as EventResult

        eventSummaryCard.eventSummaryCardTitle.text = event.title
        eventSummaryCard.eventSummaryCardHostName.text = EventSupportFunctions.getHost(event)?.fullName()
        eventSummaryCard.eventSummaryCardDescription.text = event.description
        eventSummaryCard.eventSummaryCardDate.text = getEventDate(event.date)
        eventSummaryCard.eventSummaryCardHour.text = getEventHour(event.date)
        eventSummaryCard.eventSummaryCardAddress.text = event.location.formattedAddress
        eventSummaryCard.amazeEventPrice.value = event.entrancePrice

        amazeDescription.amazeDescriptionText.text = event.description

        amazeGuestsComponent.amazeGuestsComming.text = event.guestsComming.count().toString()
        amazeGuestsComponent.amazeGuestsNotComming.text = event.guestsNotComming.count().toString()
        amazeGuestsComponent.amazeGuestsMaybe.text = event.guestsMaybe.count().toString()

    }

    // Formatte la date pour une date adaptée a l'event card
    fun getEventDate(originalDate: String) : String {
        val date = EventSupportFunctions.convertInstantStringDateToLocaleDateTime(originalDate)
        val formatter =  SimpleDateFormat(ExtraStrings.EVENT_SUMMARY_DATE_FORMAT)
        return formatter.format(date)
    }

    // Formatte la date pour une date adaptée a l'event card
    fun getEventHour(originalDate: String) : String {
        val date = EventSupportFunctions.convertInstantStringDateToLocaleDateTime(originalDate)
        val formatter =  SimpleDateFormat(ExtraStrings.EVENT_SUMMARY_HOUR_FORMAT)
        return formatter.format(date)
    }
}
