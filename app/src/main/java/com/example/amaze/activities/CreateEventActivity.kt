package com.example.amaze.activities

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.amaze.fragments.AddFriendsToEventFragment
import com.example.amaze.fragments.EventParamsFragment
import com.example.amaze.fragments.EventSuccessfullyCreatedFragment
import com.example.amaze.fragments.PlacesFragment
import com.example.amaze.models.Place
import com.example.amaze.network.EventResult
import com.example.amaze.network.RetrofitClient
import com.example.amaze.network.SendableEvent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CreateEventActivity : AppCompatActivity(), EventParamsFragment.OnEventParamsListener, AddFriendsToEventFragment.OnAddFriendsFragmentListener,
    PlacesFragment.OnPlacesFragmentListener {
    override fun onPlaceSelected(selectedPlace: Place) {

    }


    private lateinit var event : SendableEvent

    companion object{
        val EVENT_CODE = "CREATED_EVENT"
    }

    lateinit var eventParamsFragment: EventParamsFragment
    lateinit var addFriendsToEventFragment: AddFriendsToEventFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.amaze.R.layout.activity_create_event)
        event = SendableEvent()
        eventParamsFragment = EventParamsFragment.newInstance(event)

        eventParamsFragment.setOnEventParamsListener(this)

        setFragment(eventParamsFragment, tag = "tag")
    }


    private fun setFragment(fragment: Fragment, args : Place? = null, tag : String? = "") {
        if (args != null) {
            var arguments = Bundle()
            arguments.putSerializable("param2", args)
            fragment.arguments = arguments
        }


        var fragmentTransaction : FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(com.example.amaze.R.id.createEventFragment, fragment,tag)
        fragmentTransaction.addToBackStack("tag")
        fragmentTransaction.commit()
    }

    override fun onParamsDone(event: SendableEvent) {
        // Affecte le fragment principale à la fragment view
        addFriendsToEventFragment = AddFriendsToEventFragment.newInstance(event)
        addFriendsToEventFragment.setOnAddFriendsFragmentListener(this)
        setFragment(addFriendsToEventFragment)
    }

    override fun onBackPressed() {

        val fm = supportFragmentManager
        if (fm.backStackEntryCount > 0) {
            Log.i("MainActivity", "popping backstack")
            fm.popBackStack()
        } else {
            Log.i("MainActivity", "nothing on backstack, calling super")
            super.onBackPressed()
        }
        super.onBackPressed()
    }

    override fun onEventCreated(finishedEvent: SendableEvent) {
        val createEventRequest = RetrofitClient.eventService.createEvent(finishedEvent)

        createEventRequest.enqueue(object : Callback<EventResult> {
            override fun onFailure(call: Call<EventResult>, t: Throwable) {
                error(t.message.toString())
            }

            override fun onResponse(call: Call<EventResult>, response: Response<EventResult>) {
                setFragment(EventSuccessfullyCreatedFragment())
            }
        })

    }

}
