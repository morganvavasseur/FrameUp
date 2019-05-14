package com.example.amaze.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.example.amaze.R
import com.example.amaze.adapters.EventCardAdapter
import com.example.amaze.adapters.SearchedFriendCardAdapter
import com.example.amaze.models.Event
import com.example.amaze.models.User
import com.example.amaze.network.EventResult
import com.example.amaze.network.RetrofitClient
import com.example.amaze.network.UserResult
import com.example.amaze.utils.SecureStorageServices
import kotlinx.android.synthetic.main.activity_add_guest_to_event.*
import kotlinx.android.synthetic.main.activity_create_event.*
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddGuestToEventActivity : AppCompatActivity() {

    var event : EventResult? = null
    lateinit var searchedUsername : String // Stocke le username recherché
    var users : ArrayList<UserResult> = ArrayList<UserResult>()

    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_guest_to_event)
        event = intent.extras.getSerializable(CreateEventActivity.EVENT_CODE) as? EventResult

        friendsRecyclerView.layoutManager = LinearLayoutManager(this)
        friendsRecyclerView.adapter = SearchedFriendCardAdapter(users)

        linearLayoutManager = LinearLayoutManager(this)
        friendsRecyclerView.layoutManager = linearLayoutManager

        searchFriendButton.setOnClickListener({onSearchButtonClick()})

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
                    users = result

                    friendsRecyclerView.layoutManager = LinearLayoutManager(this@AddGuestToEventActivity)
                    friendsRecyclerView.adapter = SearchedFriendCardAdapter(users)
                }
            }
        })

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
