package com.example.amaze.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.amaze.AmazeApp
import com.example.amaze.MainActivity
import com.example.amaze.R
import com.example.amaze.models.Event
import com.example.amaze.models.User
import com.example.amaze.network.EventResult
import com.example.amaze.network.RetrofitClient
import com.example.amaze.network.UserResult
import com.example.amaze.utils.SecureStorageServices
import kotlinx.android.synthetic.main.activity_create_event.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class CreateEventActivity : AppCompatActivity() {

    private lateinit var eventTitle : String
    private lateinit var eventLocation: String
    private lateinit var eventDate: String
    private lateinit var eventPrice: Number
    private lateinit var eventDescription: String
    private lateinit var event : EventResult
    private lateinit var eventOrganizer : User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_event)
        createEventButton.setOnClickListener({onCreateEventButtonClick()})
    }

    fun getOrganizers() : ArrayList<String>
    {
        val organizers = ArrayList<String>()
        // Si le token à bien été stocké on connecte l'utilisateur à l'activité principale
        val hostUser : UserResult? = SecureStorageServices.authUser
        if (hostUser != null)
            organizers.add(hostUser.id)

        return organizers
    }

    fun onCreateEventButtonClick(){

        // On récupère l'identifiant et le mot de passe
        eventTitle = event_creation_title.text.toString()
        eventLocation = event_creation_location.text.toString()
        eventDate = event_creation_date.text.toString() // A CHANGER
        eventPrice = Integer.parseInt(event_creation_price.text.toString())
        eventDescription = event_creation_description.text.toString()

        event = EventResult()

        event.title = eventTitle
        event.date = eventDate
        event.dateIsFinal = false
        event.location = eventLocation
        event.locationIsFinal = false
        event.description = eventDescription
        event.entrancePrice = eventPrice
        event.organizers = getOrganizers()

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
