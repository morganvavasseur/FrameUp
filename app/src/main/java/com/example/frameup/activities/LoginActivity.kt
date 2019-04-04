package com.example.frameup.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.frameup.models.ConnectResults
import kotlinx.android.synthetic.main.activity_login.*
import com.example.frameup.network.RetrofitClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.io.IOException


class LoginActivity : AppCompatActivity() {

    lateinit var identifier: String
    lateinit var password: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.amaze.R.layout.activity_login)

        loginLocalConnectButton.setOnClickListener({connect()})



    }


    public fun connect(){

        identifier = loginIdentifierTextview.text.toString()
        password = loginPasswordTextview.text.toString()


        val connectLocalRequest = RetrofitClient.userService.connectLocal(identifier, password)

        connectLocalRequest.enqueue(object : Callback<ConnectResults> {
            override fun onFailure(call: Call<ConnectResults>, t: Throwable) {
                error("Lerreur é ici")
            }

            override fun onResponse(call: Call<ConnectResults>, response: Response<ConnectResults>) {
                Toast.makeText(this@LoginActivity, "${response.body()?.user?.username} est connecté", Toast.LENGTH_SHORT).show()
            }

        })


    }
}
