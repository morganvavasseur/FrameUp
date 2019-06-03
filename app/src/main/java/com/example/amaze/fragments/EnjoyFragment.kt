package com.example.amaze.fragments


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.amaze.AmazeApp

import com.example.amaze.R
import com.example.amaze.activities.LoginSignUpActivity
import kotlinx.android.synthetic.main.fragment_enjoy.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class EnjoyFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_enjoy, container, false)
    }

    override fun onStart() {
        super.onStart()
        enjoy_button.setOnClickListener({letsgo()})
    }

    fun letsgo() {
        var intent = Intent(AmazeApp.sharedInstance, LoginSignUpActivity::class.java)
        startActivity(intent)
    }
}
