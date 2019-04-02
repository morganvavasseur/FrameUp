package com.example.frameup.activities


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ListAdapter
import android.widget.ListView
import com.example.amaze.R
import com.example.frameup.adapters.EventCard
import kotlinx.android.synthetic.main.fragment_home.view.*
import android.R.layout
import android.R.attr.data
import android.R.id
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.LinearLayoutManager
import android.widget.FrameLayout
import com.example.frameup.adapters.EventCardAdapter
import com.example.frameup.models.Event
import com.example.frameup.models.User
import kotlinx.android.synthetic.main.activity_order.*
import kotlinx.android.synthetic.main.fragment_home.*
import java.sql.Date


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"



/**
 * A simple [Fragment] subclass.
 *
 */
class HomeFragment : Fragment() {

    lateinit var events: List<Event>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        events = listOf(
            Event(
                "01",
                "Thomas’s Birthday party",
                Date(System.currentTimeMillis()),
                User("00", "Yann", "Malanda"),
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua...",
                false,
                "1 Place du Châtelet, 75001 Paris"),
            Event(
                "01",
                "Anniversaire de Maxime",
                Date(System.currentTimeMillis()),
                User("00", "Adrien", "Vande"),
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua...",
                false,
                "1 Place du Châtelet, 75001 Paris"),
            Event(
                "01",
                "Thomas’s Birthday party",
                Date(System.currentTimeMillis()),
                User("00", "Bob", "Marley"),
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua...",
                false,
                "1 Place du Châtelet, 75001 Paris"),
            Event(
                "01",
                "Thomas’s Birthday party",
                Date(System.currentTimeMillis()),
                User("00", "Harley", "Davinson"),
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua...",
                false,
                "1 Place du Châtelet, 75001 Paris"),
            Event(
                "01",
                "Thomas’s Birthday party",
                Date(System.currentTimeMillis()),
                User("00", "Betho", "Ven"),
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua...",
                false,
                "1 Place du Châtelet, 75001 Paris")
        )

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onStart() {
        super.onStart()

        recyclerViewEvents.layoutManager = LinearLayoutManager(context)
        recyclerViewEvents.adapter = EventCardAdapter(events)

    }


}
