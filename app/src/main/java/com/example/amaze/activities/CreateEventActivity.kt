package com.example.amaze.activities

import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.util.Log
import android.widget.Toast
import com.example.amaze.AmazeApp
import com.example.amaze.MainActivity
import com.example.amaze.R
import com.example.amaze.components.AmazeNextButton
import com.example.amaze.fragments.AddFriendsToEventFragment
import com.example.amaze.fragments.EventParamsFragment
import com.example.amaze.fragments.PlacesFragment
import com.example.amaze.network.*
import com.example.amaze.utils.SecureStorageServices
import kotlinx.android.synthetic.main.fragment_event__params.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class CreateEventActivity : AppCompatActivity(), EventParamsFragment.OnEventParamsListener, AddFriendsToEventFragment.OnAddFriendsFragmentListener, PlacesFragment.OnPlacesFragmentListener {

    private lateinit var event : SendableEvent

    companion object{
        val EVENT_CODE = "CREATED_EVENT"
    }

    lateinit var eventParamsFragment: EventParamsFragment
    lateinit var addFriendsToEventFragment: AddFriendsToEventFragment
    lateinit var placesFragment: PlacesFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_event)
        event = SendableEvent()
        eventParamsFragment = EventParamsFragment.newInstance(event)

        eventParamsFragment.setOnEventParamsListener(this)

        setFragment(eventParamsFragment)
    }


    private fun setFragment(fragment: Fragment) {
        var fragmentTransaction : FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.createEventFragment, fragment)
        fragmentTransaction.commit()
    }

    override fun onParamsLocationIsGoingToBeEdited() {
        placesFragment = PlacesFragment.newInstance(event)
        setFragment(placesFragment)
    }

    override fun onParamsDone(event: SendableEvent) {
        // Affecte le fragment principale à la fragment view
        addFriendsToEventFragment = AddFriendsToEventFragment.newInstance(event)
        addFriendsToEventFragment.setOnAddFriendsFragmentListener(this)
        setFragment(addFriendsToEventFragment)
    }

    override fun onEventCreated(finishedEvent: SendableEvent) {
        val createEventRequest = RetrofitClient.eventService.createEvent(finishedEvent)

        createEventRequest.enqueue(object : Callback<EventResult> {
            override fun onFailure(call: Call<EventResult>, t: Throwable) {
                error(t.message.toString())
            }

            override fun onResponse(call: Call<EventResult>, response: Response<EventResult>) {
                finish()
            }
        })

    }

}
