package com.example.amaze.interfaces

import com.example.amaze.models.ConnectResults
import com.example.amaze.models.Event
import com.example.amaze.models.User
import com.example.amaze.network.SearchedGuest
import com.example.amaze.network.UserResult
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

    // Récupère les informations de l'utilisateur connecté
    // via le token stocké lorsqu'il s'est connecté
    @GET("users/me")
    fun getAuthenticatedUserInfos(
        @Header("Authorization") auth : String = "Bearer ${SecureStorageServices.authJwtToken}") : Call<UserResult>


    // Récupère une liste d'utilisateur à partir d'un nom d'utilisateur
    @GET("users")
    fun searchByUsername(
        @Query("username_contains", encoded = true) username : String,
        @Query("role_in", encoded = true) publicRoleId : String,
        @Query("role_in", encoded = true) authenticatedRoleId : String,
        @Header("Authorization") auth : String = "Bearer ${SecureStorageServices.authJwtToken}") : Call<ArrayList<SearchedGuest>>

    // Récupère une liste d'utilisateur à partir d'un nom d'utilisateur
    @FormUrlEncoded
    @POST("/auth/local/register")
    fun authRegister(
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("firstName") firstName: String,
        @Field("lastName") lastName: String): Call<ConnectResults>

    @DELETE("users/{id}")
    fun deleteUser(
        @Path("id") id : String,
        @Header("Authorization") auth : String = "Bearer ${SecureStorageServices.authJwtToken}") : Call<Any>
}
