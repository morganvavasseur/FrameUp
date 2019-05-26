package com.example.amaze.network

import android.app.Activity
import android.content.Context
import android.support.v4.app.Fragment
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.amaze.models.User
import com.example.amaze.utils.SecureStorageServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SupportFunctions() {
    companion object {

        fun updateUserInfos(){
            val connectLocalRequest = RetrofitClient.userService.getAuthenticatedUserInfos()

            connectLocalRequest.enqueue(object : Callback<UserResult> {
                override fun onFailure(call: Call<UserResult>, t: Throwable) {
                    error(t.message.toString())
                }

                override fun onResponse(call: Call<UserResult>, response: Response<UserResult>) {
                    var user = response.body()
                    SecureStorageServices.authUser = user as AuthUser
                }
            })


        }


    }


}