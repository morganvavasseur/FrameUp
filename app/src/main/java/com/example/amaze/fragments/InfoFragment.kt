package com.example.amaze.fragments


import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.amaze.AmazeApp

import com.example.amaze.R
import com.example.amaze.activities.LoginSignUpActivity
import com.example.amaze.models.Diet
import com.example.amaze.utils.SecureStorageServices
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.fragment_info.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class InfoFragment : Fragment() {
//    private lateinit var listView ListView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
        info_deleteAccountButton.setOnClickListener({deleteAccount()})
    }

    fun logout() {
        SecureStorageServices.authJwtToken = null
        var intent = Intent(AmazeApp.sharedInstance, LoginSignUpActivity::class.java)
        startActivity(intent)
    }

    fun deleteAccount() {
//        Suppression / Désactivation du compte
    }
}
