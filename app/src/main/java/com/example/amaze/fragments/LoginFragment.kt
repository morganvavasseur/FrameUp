package com.example.amaze.fragments

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.amaze.AmazeApp
import com.example.amaze.MainActivity

import com.example.amaze.R
import com.example.amaze.activities.CreateEventActivity
import com.example.amaze.activities.ProfileActivity
import com.example.amaze.models.ConnectResults
import com.example.amaze.network.RetrofitClient
import com.example.amaze.utils.SecureStorageServices
import kotlinx.android.synthetic.main.fragment_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [LoginFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class LoginFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var identifier: String
    lateinit var password: String

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
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onStart() {
        super.onStart()
        loginLocalConnectButton.setOnClickListener({onloginlocalConnectButtonClick()})

    }

    fun onloginlocalConnectButtonClick(){

        // On récupère l'identifiant et le mot de passe
        identifier = loginIdentifierTextview.text.toString()
        password = signUpPasswordTextview.text.toString()


        val connectLocalRequest = RetrofitClient.userService.connectLocal(identifier, password)

        connectLocalRequest.enqueue(object : Callback<ConnectResults> {
            override fun onFailure(call: Call<ConnectResults>, t: Throwable) {
                error(t.message.toString())
            }

            override fun onResponse(call: Call<ConnectResults>, response: Response<ConnectResults>) {
                var jwt = response.body()?.jwt
                var user = response.body()?.user

                Toast.makeText(AmazeApp.sharedInstance, "${jwt}", Toast.LENGTH_SHORT).show()

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
                    val intent = Intent(AmazeApp.sharedInstance, ProfileActivity::class.java)
                    startActivity(intent)
                }


            }

        })


    }

}
