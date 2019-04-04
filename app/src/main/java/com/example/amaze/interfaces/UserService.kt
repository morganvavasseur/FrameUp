package com.example.amaze.interfaces

import com.example.amaze.models.ConnectResults
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UserService {

    @FormUrlEncoded
    @POST("auth/local")
    fun connectLocal(
        @Field("identifier") identifier: String,
        @Field("password") password: String
    ) : Call<ConnectResults>

}
