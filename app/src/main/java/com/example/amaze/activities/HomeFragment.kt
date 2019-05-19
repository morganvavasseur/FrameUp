package com.example.amaze.activities


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.amaze.R
import android.support.v7.widget.LinearLayoutManager
import android.widget.*
import com.example.amaze.AmazeApp
import com.example.amaze.adapters.EventCardAdapter
import com.example.amaze.adapters.HomeFragmentAdapter
import com.example.amaze.models.Event
import com.example.amaze.network.EventResult
import com.example.amaze.network.RetrofitClient
import com.example.amaze.utils.ExtraStrings
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onStart() {
        super.onStart()
        val homeFragmentAdapter = HomeFragmentAdapter(AmazeApp.sharedInstance, childFragmentManager)
        val viewPager : ViewPager = homeViewPager
        viewPager.adapter = homeFragmentAdapter
        homeTabs.setupWithViewPager(homeViewPager)
    }



}
