package com.example.amaze.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.example.amaze.R
import com.example.amaze.adapters.SearchedFriendCardAdapter
import com.example.amaze.models.Event
import com.example.amaze.network.EventResult
import com.example.amaze.network.RetrofitClient
import com.example.amaze.network.UserResult
import kotlinx.android.synthetic.main.activity_add_guest_to_event.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddGuestToEventActivity : AppCompatActivity(), SearchedFriendCardAdapter.OnFriendItemListener{
    
    var event : EventResult? = null
    lateinit var searchedUsername : String // Stocke le username recherché
    var usersResults : ArrayList<UserResult> = ArrayList<UserResult>()

    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_guest_to_event)
        event = intent.extras.getSerializable(CreateEventActivity.EVENT_CODE) as? EventResult

        friendsRecyclerView.layoutManager = LinearLayoutManager(this)
        friendsRecyclerView.adapter = SearchedFriendCardAdapter(usersResults,this)

        linearLayoutManager = LinearLayoutManager(this)
        friendsRecyclerView.layoutManager = linearLayoutManager

        searchFriendButton.setOnClickListener({onSearchButtonClick()})
        createEventButton.setOnClickListener({onCreateEventButtonClick()})

        event?.guests = ArrayList<String>()

    }

    fun onSearchButtonClick(){
        searchedUsername = friendsSearchbar.text.toString() // Get searched username from textedit
        val searchUserRequest = RetrofitClient.userService.searchByUsername(searchedUsername)

        searchUserRequest.enqueue(object : Callback<ArrayList<UserResult>> {
            override fun onFailure(call: Call<ArrayList<UserResult>>, t: Throwable) {
                error(t.message.toString())
            }

            override fun onResponse(call: Call<ArrayList<UserResult>>, response: Response<ArrayList<UserResult>>) {
                var result = response.body()
                if (result is ArrayList<UserResult>) {
                    usersResults = result

                    friendsRecyclerView.layoutManager = LinearLayoutManager(this@AddGuestToEventActivity)
                    friendsRecyclerView.adapter = SearchedFriendCardAdapter(usersResults, this@AddGuestToEventActivity)
                }
            }
        })

    }

    override fun onFriendClick(user: UserResult) {
        Log.d("Listener", "OnFriendClick Clicked")
        if (user.id in event!!.guests)
            event?.guests?.remove(user.id) // Supprime un utilisateur de la liste des invités
        else
            event?.guests?.add(user.id) // Ajoute un utilisateur à la liste des invités

        event!!.guests.forEach {
            Log.d("eventUpdate", it)
        }
    }


    fun onCreateEventButtonClick(){

        val createEventRequest = RetrofitClient.eventService.createEvent(event as EventResult)

        createEventRequest.enqueue(object : Callback<Event> {
            override fun onFailure(call: Call<Event>, t: Throwable) {
                error(t.message.toString())
            }

            override fun onResponse(call: Call<Event>, response: Response<Event>) {
                Toast.makeText(this@AddGuestToEventActivity, response.message(), Toast.LENGTH_LONG)
            }
        })
    }

}
