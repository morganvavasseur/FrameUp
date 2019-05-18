package com.example.amaze.utils

import com.example.amaze.models.Event
import com.example.amaze.models.Organizer
import com.example.amaze.models.User
import com.example.amaze.network.EventResult
import com.example.amaze.network.UserResult
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class EventSupportFunctions {

    companion object {

        fun getHost(event: EventResult) : Organizer? {
            if (event.organizers != null)
                return event.organizers?.firstOrNull()
            else
                return null
        }

        fun convertInstantStringDateToLocaleDateTime(isoDate : String) : LocalDateTime {
            // Convert ISO_Instant Date to Date
            val isoFormatter = DateTimeFormatter.ISO_INSTANT
            val dateInstant = Instant.from(isoFormatter.parse(isoDate))
            return LocalDateTime.ofInstant(dateInstant, ZoneId.of(ZoneOffset.UTC.id))
        }
    }

}