package com.example.frameup.activities


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.amaze.R
import com.example.frameup.adapters.EventCard
import kotlinx.android.synthetic.main.fragment_home.view.*
import android.R.layout
import android.R.attr.data
import android.R.id
import android.content.Intent
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.*
import com.example.amaze.MainActivity
import com.example.frameup.AmazeApp
import com.example.frameup.adapters.EventCardAdapter
import com.example.frameup.models.ConnectResults
import com.example.frameup.models.Event
import com.example.frameup.models.User
import com.example.frameup.network.RetrofitClient
import com.example.frameup.utils.SecureStorageServices
import kotlinx.android.synthetic.main.activity_order.*
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.sql.Date


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"



/**
 * A simple [Fragment] subclass.
 *
 */
class HomeFragment : Fragment() {

    var events: ArrayList<Event> = ArrayList<Event>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val connectLocalRequest = RetrofitClient.eventService.getInvitedEvent("5ca34fcc82ecd255bf1be017")

        connectLocalRequest.enqueue(object : Callback<ArrayList<Event>> {
            override fun onFailure(call: Call<ArrayList<Event>>, t: Throwable) {
                error(t.message.toString())

            }

            override fun onResponse(call: Call<ArrayList<Event>>, response: Response<ArrayList<Event>>) {
                var responseEvents = response.body()
                if(responseEvents is ArrayList<Event>){
                    events = responseEvents
                    recyclerViewEvents.layoutManager = LinearLayoutManager(context)
                    recyclerViewEvents.adapter = EventCardAdapter(events)
                }
            }

        })

        Toast.makeText(AmazeApp.sharedInstance, "${events.count()}", Toast.LENGTH_SHORT).show()


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onStart() {
        super.onStart()

        recyclerViewEvents.layoutManager = LinearLayoutManager(context)
        recyclerViewEvents.adapter = EventCardAdapter(events)



    }


}
