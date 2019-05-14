package com.example.amaze.network

import com.example.amaze.models.User
import com.example.amaze.utils.SecureStorageServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SupportFunctions() {
    companion object {

        fun updateUserInfos(){
            val connectLocalRequest = RetrofitClient.userService.getAuthenticatedUserInfos()

            connectLocalRequest.enqueue(object : Callback<User> {
                override fun onFailure(call: Call<User>, t: Throwable) {
                    //error(t.message.toString())
                }

                override fun onResponse(call: Call<User>, response: Response<User>) {
                    var user = response.body()
                    SecureStorageServices.authUser = user
                }
            })


        }
    }
}