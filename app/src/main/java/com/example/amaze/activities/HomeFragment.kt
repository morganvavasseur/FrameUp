package com.example.amaze.activities


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.amaze.R
import android.support.v7.widget.LinearLayoutManager
import android.widget.*
import com.example.amaze.AmazeApp
import com.example.amaze.adapters.EventCardAdapter
import com.example.amaze.models.Event
import com.example.amaze.network.RetrofitClient
import com.example.amaze.utils.SecureStorageServices
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"



/**
 * A simple [Fragment] subclass.
 *
 */
class HomeFragment : Fragment() {

    var events: ArrayList<Event> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val connectLocalRequest = RetrofitClient.eventService.getInvitedEvent("5ca34fcc82ecd255bf1be017")

        connectLocalRequest.enqueue(object : Callback<ArrayList<Event>> {
            override fun onFailure(call: Call<ArrayList<Event>>, t: Throwable) {
                //error(t.message.toString())
            }

            override fun onResponse(call: Call<ArrayList<Event>>, response: Response<ArrayList<Event>>) {
                var responseEvents = response.body()
                if(responseEvents is ArrayList<Event>) {
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
