package com.example.amaze.utils

import com.example.amaze.models.Event
import com.example.amaze.models.Organizer
import com.example.amaze.models.User
import com.example.amaze.network.EventResult
import com.example.amaze.network.UserResult
import java.lang.Error
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.*
import java.time.format.DateTimeFormatter
import java.util.*

class EventSupportFunctions {

    companion object {

        fun getHost(event: EventResult) : Organizer? {
            if (event.organizers != null)
                return event.organizers?.firstOrNull()
            else
                return null
        }

        fun convertInstantStringDateToLocaleDateTime(isoDate : String) : Date {
            // Convert ISO_Instant Date to Date
//            val isoFormatter = DateTimeFormatter.ISO_INSTANT
//            val dateInstant = Instant.from(isoFormatter.parse(isoDate))
//            return LocalDateTime.ofInstant(dateInstant, ZoneId.of(ZoneOffset.UTC.id))

            var formatterIn = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:sss")
            try {
                var date = formatterIn.parse(isoDate.removeSuffix("Z"))
                return date
            } catch(e: Error) {
                error(e)
            }

        }
    }

}