package com.example.amaze.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.amaze.R
import com.example.amaze.models.User
import com.example.amaze.network.EventResult
import com.example.amaze.utils.SecureStorageServices
import kotlinx.android.synthetic.main.activity_create_event.*
import kotlin.collections.ArrayList

class CreateEventActivity : AppCompatActivity() {

    private lateinit var event : EventResult

    companion object{
        val EVENT_CODE = "CREATED_EVENT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_event)
        createEventButton.setOnClickListener({onNextButtonClick()})
    }

    fun getOrganizersFromAuthenticatedUser() : ArrayList<String>
    {
        val organizers = ArrayList<String>()
        // Si le token à bien été stocké on connecte l'utilisateur à l'activité principale
        val hostUser : User? = SecureStorageServices.authUser
        if (hostUser != null)
            organizers.add(hostUser.id)

        return organizers
    }

    fun onNextButtonClick() {

        // Initialise event
        event = EventResult()

        event.title = event_creation_title.text.toString()
        event.date = event_creation_date.text.toString() // A CHANGER
        event.dateIsFinal = false
        event.location = event_creation_location.text.toString()
        event.locationIsFinal = false
        event.description = event_creation_description.text.toString()
        event.entrancePrice = Integer.parseInt(event_creation_price.text.toString())
        event.organizers = getOrganizersFromAuthenticatedUser()

        val intent = Intent(this@CreateEventActivity, AddGuestToEventActivity::class.java)
        intent.putExtra(EVENT_CODE, event)
        startActivity(intent)
    }


}
