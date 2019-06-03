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
import com.example.amaze.AmazeApp
import com.example.amaze.MainActivity
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
        signUpUsernameTv.addTextChangedListener(this)
        signUpFirstnameTv.addTextChangedListener(this)
        signUpLastnameTv.addTextChangedListener(this)
        signUpEmailTv.addTextChangedListener(this)
        signUpPasswordTv.addTextChangedListener(this)
        signUpRepeatPasswordTv.addTextChangedListener(this)

        loginLocalConnectButton.setOnClickListener{register()}
    }

    override fun afterTextChanged(s: Editable?) {
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        when(s.hashCode()) {
            signUpUsernameTv.text.hashCode() -> checkUsername()

            signUpFirstnameTv.text.hashCode() -> checkFirstName(s.toString())

            signUpLastnameTv.text.hashCode() -> checkLastName(s.toString())

            signUpEmailTv.text.hashCode() -> checkEmail()

            signUpPasswordTv.text.hashCode() -> updatePasswordStrengthView(s.toString())

            signUpRepeatPasswordTv.text.hashCode() -> checkRepeatPassword()
        }
    }

    fun checkUsername() : Boolean {
        val username = signUpUsernameTv.text.toString()
        val testUsername = username.length >= 6
        if (testUsername)
            signUpUsernameHelper.visibility = View.GONE
        else
            signUpUsernameHelper.visibility = View.VISIBLE
        return testUsername
    }

    fun checkFirstName(name: String) : Boolean {
        val testFirstName = signUpFirstnameTv.text.isEmpty()
        if(testFirstName)
            signUpfirstnameHelper.visibility = View.VISIBLE
        else signUpfirstnameHelper.visibility = View.GONE

        return !testFirstName
    }

    fun checkLastName(name: String) : Boolean {
        val testLastName = signUpLastnameTv.text.isEmpty()
        if(testLastName)
            signUpLastnameHelper.visibility = View.VISIBLE
        else signUpLastnameHelper.visibility = View.GONE

        return !testLastName
    }

    fun checkEmail() : Boolean{
        val email = signUpEmailTv.text.toString()
        var testEmail : Boolean

        if (TextUtils.isEmpty(email)) {
            testEmail =  false
        } else {
            testEmail = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

        if(testEmail)
            signUpEmailHelper.visibility = View.GONE
        else
            signUpEmailHelper.visibility = View.VISIBLE

        return testEmail
    }

    fun checkRepeatPassword() : Boolean {
        val p1 = signUpPasswordTv.text.toString()
        val p2 = signUpRepeatPasswordTv.text.toString()
        val testRepeatPassword : Boolean = (p1 == p2)

        if(testRepeatPassword)
            signUpRepeatPasswordHelper.visibility = View.GONE
        else signUpRepeatPasswordHelper.visibility = View.VISIBLE

        return testRepeatPassword
    }

    fun checkPassword() : Boolean{
        val p1 = signUpPasswordTv.text.toString()
        val p2 = signUpRepeatPasswordTv.text.toString()

        val str = PasswordStrength.calculateStrength(p1)

        if (str != PasswordStrength.STRONG && str != PasswordStrength.VERY_STRONG) {
            return false
        } else
            return (p1 == p2)
    }

    fun areRegisterFieldCorrect() : Boolean{
        if(!checkUsername())
            return false
        if(!checkFirstName(signUpFirstnameTv.text.toString()))
            return false
        if(!checkLastName(signUpLastnameTv.text.toString()))
            return false
        if(!checkEmail())
            return false
        if(!checkPassword())
            return false
        return true
    }

    fun register() {
        val username = signUpUsernameTv.text.toString()
        val email = signUpEmailTv.text.toString()
        val firstName = signUpFirstnameTv.text.toString()
        val lastName = signUpLastnameTv.text.toString()
        val password = signUpPasswordTv.text.toString()

        if(!areRegisterFieldCorrect())
            return

        val registerLocalRequest = RetrofitClient.userService.authRegister(username.toLowerCase(), email, password, firstName, lastName)

        registerLocalRequest.enqueue(object : Callback<ConnectResults> {
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
                    activity?.finish()
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
