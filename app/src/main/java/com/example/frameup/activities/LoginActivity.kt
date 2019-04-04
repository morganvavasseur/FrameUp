package com.example.frameup.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.example.amaze.MainActivity
import com.example.frameup.models.ConnectResults
import com.example.frameup.network.RetrofitClient
import com.example.frameup.utils.SecureStorageServices
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {

    lateinit var identifier: String
    lateinit var password: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.amaze.R.layout.activity_login)

        loginLocalConnectButton.setOnClickListener({connect()})



    }


    public fun connect(){

        // On récupère l'identifiant et le mot de passe
        identifier = loginIdentifierTextview.text.toString()
        password = loginPasswordTextview.text.toString()


        val connectLocalRequest = RetrofitClient.userService.connectLocal(identifier, password)

        connectLocalRequest.enqueue(object : Callback<ConnectResults> {
            override fun onFailure(call: Call<ConnectResults>, t: Throwable) {
                error("Lerreur é ici")
            }

            override fun onResponse(call: Call<ConnectResults>, response: Response<ConnectResults>) {
                var jwt = response.body()?.jwt
                var user = response.body()?.user

                Toast.makeText(this@LoginActivity, "${user?.username} est connecté", Toast.LENGTH_SHORT).show()

                // On vérifie que le token est bien
                // une String puis on le stock de manière sécurisé
                if (jwt is String){
                    SecureStorageServices.authJwtToken = jwt
                } else {
                    Log.d("SECRET_APP", "Token has no value")
                }

                // Si le token à bien été stocké on connecte l'utilisateur à l'activité principale
                if (SecureStorageServices.authJwtToken != null){
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                }


            }

        })


    }
}
