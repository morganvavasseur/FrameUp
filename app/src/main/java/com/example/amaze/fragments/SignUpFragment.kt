/*
 * Developed by Yann Malanda on 5/31/19 7:25 PM.
 * Last modified 5/31/19 7:23 PM
 * Copyright (c) 2019.
 *
 */

package com.example.amaze.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.example.amaze.AmazeApp
import com.example.amaze.MainActivity
import com.example.amaze.R
import com.example.amaze.models.ConnectResults
import com.example.amaze.network.RetrofitClient
import com.example.amaze.utils.SecureStorageServices
import com.ybs.passwordstrengthmeter.PasswordStrength
import kotlinx.android.synthetic.main.fragment_sign_up.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, onFoundedPlaceItemListener1.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [SignUpFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [SignUpFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class SignUpFragment : Fragment(), TextWatcher {

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(com.example.amaze.R.layout.fragment_sign_up, container, false)
    }

    override fun onStart() {
        super.onStart()
        signUpPasswordTextview.addTextChangedListener(this)
        loginLocalConnectButton.setOnClickListener{register()}
    }

    override fun afterTextChanged(s: Editable?) {
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        updatePasswordStrengthView(s.toString())
    }

    fun checkUsername() : Boolean {
        val username = loginIdentifierTextview.text.toString()
        return username.length >= 6
    }

    fun checkEmail() : Boolean{
        val email = signUpEmail.text.toString()

        if (TextUtils.isEmpty(email)) {
            return false
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

    }

    fun checkPassword() : Boolean{
        val p1 = signUpPasswordTextview.text.toString()
        val p2 = signUpPasswordVerifTextview.text.toString()

        val str = PasswordStrength.calculateStrength(p1)

        if (str != PasswordStrength.STRONG && str != PasswordStrength.VERY_STRONG) {
            return false
        } else
            return (p1 == p2)
    }

    fun areRegisterFieldCorrect() : Boolean{

        if(!checkUsername())
            return false
        if(!checkEmail())
            return false
        if(!checkPassword())
            return false
        return true
    }

    fun register() {
        val username = loginIdentifierTextview.text.toString()
        val email = signUpEmail.text.toString()
        val password = signUpPasswordTextview.text.toString()

        if(!areRegisterFieldCorrect())
            return

        val connectLocalRequest = RetrofitClient.userService.authRegister(username, email, password)

        connectLocalRequest.enqueue(object : Callback<ConnectResults> {
            override fun onFailure(call: Call<ConnectResults>, t: Throwable) {
                error(t.message.toString())
            }

            override fun onResponse(call: Call<ConnectResults>, response: Response<ConnectResults>) {
                var jwt = response.body()?.jwt
                var user = response.body()?.user

                // On vérifie que le token est bien
                // une String puis on le stock de manière sécurisé
                if (jwt is String){
                    SecureStorageServices.authJwtToken = jwt
                    SecureStorageServices.authUser = user
                } else {
                    Log.d("SECRET_APP", "Token has no value")
                }

                // Si le token à bien été stocké on connecte l'utilisateur à l'activité principale
                if (SecureStorageServices.authJwtToken != null){
                    val intent = Intent(AmazeApp.sharedInstance, MainActivity::class.java)
                    startActivity(intent)
                }


            }

        })
    }

    private fun updatePasswordStrengthView(password: String) {

        val progressBar = signUpProgressBar
        val strengthView = signUpPasswordStrength
        if (TextView.VISIBLE != strengthView.visibility)
            return

        if (password.isEmpty()) {
            strengthView.text = ""
            progressBar.progress = 0
            return
        }

        val str = PasswordStrength.calculateStrength(password)
        strengthView.text = str.getText(context)
        strengthView.setTextColor(str.color)

        progressBar.progressDrawable.setColorFilter(str.color, android.graphics.PorterDuff.Mode.SRC_IN)
        if (str.getText(context) == "Weak") {
            progressBar.progress = 25
        } else if (str.getText(context) == "Medium") {
            progressBar.progress = 50
        } else if (str.getText(context) == "Strong") {
            progressBar.progress = 75
        } else {
            progressBar.progress = 100
        }

    }



}
