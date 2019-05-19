package com.example.amaze.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat.startActivity
import com.example.amaze.R
import com.example.amaze.activities.CreateEventActivity.Companion.EVENT_CODE
import com.example.amaze.components.AmazeNextButton
import com.example.amaze.models.Organizer
import com.example.amaze.models.User
import com.example.amaze.network.AuthUser
import com.example.amaze.network.EventResult
import com.example.amaze.network.SendableEvent
import com.example.amaze.network.UserResult
import com.example.amaze.utils.SecureStorageServices
import kotlinx.android.synthetic.main.activity_create_event.*
import kotlin.collections.ArrayList

class CreateEventActivity : AppCompatActivity(), AmazeNextButton.OnNextButtonListener {

    private lateinit var event : SendableEvent

    companion object{
        val EVENT_CODE = "CREATED_EVENT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_event)
        createEventNextButton.setNextButtonOnClickListener(this)
    }

    fun getOrganizersFromAuthenticatedUser() : ArrayList<String>
    {
        val organizers = ArrayList<String>()
        // Si le token à bien été stocké on connecte l'utilisateur à l'activité principale
        val hostUser : AuthUser? = SecureStorageServices.authUser
        if (hostUser != null)
            organizers.add(hostUser.id)

        return organizers
    }

    override fun onNextButtonClick() {

        // Initialise event
        event = SendableEvent()

        event.title = event_creation_title.text.toString()
        event.date = event_creation_date.text.toString() // A CHANGER
        event.location = event_creation_location.text.toString()
        event.description = eventCreationDescription.text.toString()
        event.entrancePrice = Integer.parseInt(event_creation_price.text.toString())
        event.organizers = getOrganizersFromAuthenticatedUser()

        val intent = Intent(this@CreateEventActivity, AddGuestToEventActivity::class.java)
        intent.putExtra(EVENT_CODE, event)
        startActivity(intent)
    }


}
