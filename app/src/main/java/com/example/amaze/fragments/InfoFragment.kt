package com.example.amaze.fragments


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.amaze.AmazeApp
import com.example.amaze.MainActivity

import com.example.amaze.R
import com.example.amaze.activities.LoginSignUpActivity
import com.example.amaze.utils.SecureStorageServices
import kotlinx.android.synthetic.main.fragment_info.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, onFoundedPlaceItemListener1.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class InfoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false)

    }

    override fun onStart() {
        super.onStart()
        var user = SecureStorageServices.authUser
        info_firstName.text = user?.firstName
        info_lastName.text = user?.lastName
        info_email.text = user?.email
        info_phoneNumber.text = user?.phone
        info_dietInformation.text = user?.dietOther
        info_logoutButton.setOnClickListener({logout()})
    }

    fun logout() {
        SecureStorageServices.authJwtToken = null
        var intent = Intent(AmazeApp.sharedInstance, LoginSignUpActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }
}
