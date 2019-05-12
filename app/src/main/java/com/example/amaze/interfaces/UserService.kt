package com.example.amaze.interfaces

import com.example.amaze.models.ConnectResults
import com.example.amaze.models.Event
import com.example.amaze.models.User
import com.example.amaze.utils.SecureStorageServices
import retrofit2.Call
import retrofit2.http.*

interface UserService {

    @FormUrlEncoded
    @POST("auth/local")
    fun connectLocal(
        @Field("identifier") identifier: String,
        @Field("password") password: String
    ) : Call<ConnectResults>


}
