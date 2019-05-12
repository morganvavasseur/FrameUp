package com.example.amaze.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.amaze.R
import com.example.amaze.models.Event
import com.example.amaze.network.RetrofitClient
import kotlinx.android.synthetic.main.activity_create_event.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class CreateEventActivity : AppCompatActivity() {

    private lateinit var eventTitle : String
    private lateinit var eventLocation: String
    private lateinit var eventDate: String
    private lateinit var eventPrice: Number
    private lateinit var eventDescription: String
    private lateinit var event : Event

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_event)
        createEventButton.setOnClickListener({onCreateEventButtonClick()})
    }

    fun onCreateEventButtonClick(){

        // On récupère l'identifiant et le mot de passe
        eventTitle = event_creation_title.text.toString()
        eventLocation = event_creation_location.text.toString()
        eventDate = event_creation_date.text.toString() // A CHANGER
        eventPrice = Integer.parseInt(event_creation_price.text.toString())
        eventDescription = event_creation_description.text.toString()

        event = Event(
            title = eventTitle,
            date = eventDate,
            location = eventLocation,
            locationIsFinal = false,
            description = eventDescription,
            entrancePrice = eventPrice
        )

        val createEventRequest = RetrofitClient.eventService.createEvent(event)

        createEventRequest.enqueue(object : Callback<Event> {
            override fun onFailure(call: Call<Event>, t: Throwable) {
                error(t.message.toString())
            }

            override fun onResponse(call: Call<Event>, response: Response<Event>) {
                Toast.makeText(this@CreateEventActivity, response.message(), Toast.LENGTH_LONG)
            }

        })


    }

}
